
import java.io.File;
import java.io.IOException;

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

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class M457_aymen {

	public static void main(String[] args) throws ParserConfigurationException {		
		String xmlFile="C:\\Users\\aymen\\Desktop\\zellal\\examen/M457.xml";
		DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
		DocumentBuilder parseur = null;
		parseur = factory.newDocumentBuilder();
		DOMImplementation domimp = parseur.getDOMImplementation();
  		DocumentType dtd = domimp.createDocumentType("TEI_S", null, "dom.dtd");

		Document doc = domimp.createDocument(null, "TEI_S", dtd);
		doc.setXmlStandalone(true);
		Element rac = doc.getDocumentElement();
		Element rac2 = doc.createElement("M457.xml");
		Document document = null;
		try {
			document = parseur.parse(xmlFile);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		rac.appendChild(rac2);
		Element tei = document.getDocumentElement();
		
		NodeList paragraphe_bis = tei.getElementsByTagName("p");
		NodeList newline = tei.getElementsByTagName("lb");
		
		Element txt = doc.createElement("texte");
		rac2.appendChild(txt);
		txt.appendChild(doc.createTextNode(paragraphe_bis.item(0).getFirstChild().getNodeValue()));

		for (int j=0;j<newline.getLength();j++){	
			 txt = doc.createElement("texte");
			rac2.appendChild(txt);
	
			txt.appendChild(doc.createTextNode(newline.item(j).getPreviousSibling().getNodeValue().replaceAll("\\n","" )));
	
				if(newline.item(j).getNextSibling().getNextSibling()!=null) {
					if(newline.item(j).getNextSibling().getNextSibling().getNodeName()=="pb") {
						 txt = doc.createElement("texte");
						 rac2.appendChild(txt);
						txt.appendChild(doc.createTextNode(newline.item(j).getNextSibling().getNextSibling().getPreviousSibling().getNodeValue().replaceAll("\\n","" )));			
						}
				}

		}
		DOMSource ds =new DOMSource(doc);
		StreamResult res = new StreamResult(new File("sortie2_aymen.xml"));
		
		TransformerFactory transform = TransformerFactory.newInstance();
		Transformer tr = null;
		try {
			tr = transform.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		
		tr.setOutputProperty(OutputKeys.INDENT, "yes");
		tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "dom.dtd");
		try {
			tr.transform(ds, res);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	
  	}

	}


