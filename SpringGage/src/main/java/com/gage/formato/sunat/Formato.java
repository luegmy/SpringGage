package com.gage.formato.sunat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.PDF417Writer;
import com.google.zxing.client.j2se.MatrixToImageWriter;

public class Formato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String RUTA_DATA = "C:/SFS_v1.2/sunat_archivos/sfs/DATA/";
	public static final String RUTA_FIRMA = "C:/SFS_v1.2/sunat_archivos/sfs/FIRMA/";
	public static final String RUTA_IMAGEN = "C:/SFS_v1.2/sunat_archivos/sfs/IMAGEN/";
	
	private String digestTexto;
	private String signatureTexto;

	// talonario: es una funcion que retorna el numero de venta
	// de la concatenacion entre el comprobante y el numero del comprobante
	// 1=factura, 2=nota, 3=boleta, 7=nota de credito 8=nota de debito
	// ejemplo:10000123,30000123,.......
	public int obtenerTalonario(int comprobante, int numeroComprobante) {
		String numero = String.format("%07d", numeroComprobante);
		String cadena = String.valueOf(comprobante).concat(numero);
		return Integer.valueOf(cadena);
	}
	
	//para obtener el formato del tipo de comprobante ejemplo:01 o 03 o 07 .....
	public String obtenerFormatoComprobante(int comprobante) {
		int a = comprobante;
		String patronA = "%02d";
		return String.format(patronA, a);
	}
	
	//para obtener el formato ejemplo:00001234
	public String obtenerFormatoNumeroComprobante(int numero) {
		int b = numero;
		String patronB = "%08d";
		return String.format(patronB, b);
	}
	
	//se obtiene el nombre del archivo sin extension todavia (cab, det, ....)
	public String generarNombreArchivo(String rucEmisor, int comprobante, String serie, int numero) {
		return rucEmisor.concat("-").concat(obtenerFormatoComprobante(comprobante)).concat("-").concat(serie)
				.concat("-").concat(obtenerFormatoNumeroComprobante(numero));

	}
	
	//generar las extension del nombre de archivo (cab, det, ......)
	public void generarCabeceraSunat(String rucEmisor, int comprobante, String serie, int numero, String cadenaCabecera,
			List<String> cadenaDetalle, String cadenaTributo, String cadenaLeyenda, String cadenaRelacionado)
			throws IOException {
		
		//se almacena en el archivo sunat llamada DATA
		String ruta = RUTA_DATA.concat(generarNombreArchivo(rucEmisor, comprobante, serie, numero));

		
		//asignar las extensiones al cada nombre de archivo
		String rutaDataCabecera;
		if (comprobante == 7 || comprobante == 8) {
			rutaDataCabecera = ruta.concat(".NOT");
		} else {
			rutaDataCabecera = ruta.concat(".CAB");
		}

		String rutaDataDetalle = ruta.concat(".DET");
		String rutaDataTributo = ruta.concat(".TRI");
		String rutaDataLeyenda = ruta.concat(".LEY");

		String rutaDataRelacionado = "";
		File archivoRelacionado = null;

		if (!"".equals(cadenaRelacionado)) {
			rutaDataRelacionado = ruta.concat(".REL");
			archivoRelacionado = new File(rutaDataRelacionado);
			if (archivoRelacionado.exists()) {
				archivoRelacionado.delete();
			}
			BufferedWriter bw5 = new BufferedWriter(new FileWriter(archivoRelacionado));
			bw5.write(cadenaRelacionado);
			bw5.close();
		}

		File archivoCabecera = new File(rutaDataCabecera);
		File archivoDetalle = new File(rutaDataDetalle);
		File archivoTributo = new File(rutaDataTributo);
		File archivoLeyenda = new File(rutaDataLeyenda);

		if (archivoCabecera.exists() || archivoDetalle.exists() || archivoTributo.exists() || archivoLeyenda.exists()) {
			archivoCabecera.delete();
			archivoDetalle.delete();
			archivoTributo.delete();
			archivoLeyenda.delete();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(archivoCabecera));
		bw.write(cadenaCabecera);

		BufferedWriter bw2 = new BufferedWriter(new FileWriter(archivoDetalle));
		for (String s : cadenaDetalle) {
			bw2.write(s);
		}

		BufferedWriter bw3 = new BufferedWriter(new FileWriter(archivoTributo));
		bw3.write(cadenaTributo);

		BufferedWriter bw4 = new BufferedWriter(new FileWriter(archivoLeyenda));
		bw4.write(cadenaLeyenda);

		bw.close();
		bw2.close();
		bw3.close();
		bw4.close();

	}
	
	public String verificarExistenciaArchivo(String rucEmisor, int comprobante, String serie, int numero) {

		File xml = new File(
				RUTA_FIRMA.concat(generarNombreArchivo(rucEmisor, comprobante, serie, numero)).concat(".xml"));
		if (!xml.exists()) {
			return "Generar el xml en el facturador para la factura ".concat(serie).concat("-")
					.concat(String.valueOf(numero));
		}
		return "";

	}
	
	public void generarCodigoBarra(String rucEmisor, int comprobante, String serie, int numero, String igv,
			String monto, String fecha, String identidad, String numeroIdentidad)
			throws IOException, WriterException {

		String codigoBarra = rucEmisor.concat("|").concat(obtenerFormatoComprobante(comprobante)).concat("|")
				.concat(serie).concat("|").concat(obtenerFormatoNumeroComprobante(numero)).concat("|")
				.concat(String.valueOf(igv)).concat("|").concat(String.valueOf(monto)).concat("|").concat(fecha)
				.concat("|").concat(identidad).concat("|").concat(numeroIdentidad).concat("|").concat(digestTexto)
				.concat("|").concat(signatureTexto);

		BitMatrix bitMatrix;
		Writer escritura = new PDF417Writer();
		bitMatrix = escritura.encode(codigoBarra, BarcodeFormat.PDF_417, 600, 200);
		MatrixToImageWriter.writeToStream(bitMatrix, "png", new FileOutputStream(new File(
				RUTA_IMAGEN.concat(generarNombreArchivo(rucEmisor, comprobante, serie, numero)).concat(".png"))));

	}

	
	public void leerNodosXml(File xml) throws ParserConfigurationException, SAXException, IOException {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xml);

		NodeList nList = doc.getElementsByTagName(doc.getDocumentElement().getNodeName());

		// Retorna el n�mero de nodos de la lista.
		Node root = nList.item(0);

		procesarNodos(root);

	}
	
	private void procesarNodos(Node inputNode) {

		for (int i = 0; i < inputNode.getChildNodes().getLength(); ++i) {

			Node node = inputNode.getChildNodes().item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				if (node.getNodeName().equals("ds:DigestValue")) {
					digestTexto = node.getTextContent().trim();
				} else if (node.getNodeName().equals("ds:SignatureValue")) {
					signatureTexto = node.getTextContent().trim();
				}

				procesarNodos(node);
			}
		}

	}

	public String getDigestTexto() {
		return digestTexto;
	}

	public void setDigestTexto(String digestTexto) {
		this.digestTexto = digestTexto;
	}

	public String getSignatureTexto() {
		return signatureTexto;
	}

	public void setSignatureTexto(String signatureTexto) {
		this.signatureTexto = signatureTexto;
	}

}
