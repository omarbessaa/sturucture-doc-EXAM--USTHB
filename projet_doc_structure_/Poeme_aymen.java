
import java.io.*;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;


public class Poeme_aymen {

	public static void main(String[] args) throws ParserConfigurationException {
		String xmlFile="C:\\Users\\aymen\\Desktop\\zellal\\examen\\examen_bis\\poeme/poeme.txt";
		DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
		DocumentBuilder parseur = null;
		parseur = factory.newDocumentBuilder();
		DOMImplementation domimp = parseur.getDOMImplementation();
  		DocumentType dtd = domimp.createDocumentType("poema", null, "neruda.dtd");
		Document doc = domimp.createDocument(null, "poema", dtd);
		doc.setXmlStandalone(true);
		Element rac = doc.getDocumentElement();
		
		
		InputStream flux = null;
		try {
			flux = new FileInputStream(xmlFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		InputStreamReader lecture=new InputStreamReader(flux);
		BufferedReader buff=new BufferedReader(lecture);
		String ligne = null;
		try {
			ligne=buff.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Element titulo = doc.createElement("titulo");
		rac.appendChild(titulo);
		titulo.appendChild(doc.createTextNode(ligne));
		try {
			ligne=buff.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}try {
			ligne=buff.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}try {
			ligne=buff.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Element estrofa = doc.createElement("estrofa");
		int cpt=0;
		int cpt_est=1;
		rac.appendChild(estrofa);
		try {
			while ((ligne=buff.readLine())!=null){
				if(ligne.equals("")) {
					if(cpt==0) {
						if(cpt_est==5) {
							System.out.print("");
						}else {
							estrofa = doc.createElement("estrofa");
							rac.appendChild(estrofa);
							cpt=1;
							cpt_est=cpt_est+1;
						}

					}
					else {
						System.out.print("");
					}


				}
				else {
					cpt=0;
					Element verso = doc.createElement("verso");
					estrofa.appendChild(verso);
					verso.appendChild(doc.createTextNode(ligne));
					
				}
			}
		} catch (DOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			buff.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		DOMSource ds =new DOMSource(doc);
		StreamResult res = new StreamResult(new File("neruda_aymen.xml"));
		TransformerFactory transform = TransformerFactory.newInstance();
		Transformer tr = null;
		try {
			tr = transform.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		
		tr.setOutputProperty(OutputKeys.INDENT, "yes");
		tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "neruda.dtd");
		try {
			tr.transform(ds, res);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
  	}

	}


