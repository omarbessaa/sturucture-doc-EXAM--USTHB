
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
public class renault_aymen {

	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException, TransformerException {
		DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();

		DOMImplementation domImplementation = parser.getDOMImplementation();
		DocumentType docType= null;
		docType = domImplementation.createDocumentType("Concessionnaires", null, null);
		Document doc = domImplementation.createDocument(null, "Concessionnaires", docType);
		doc.setXmlStandalone(true);

		Element documentElement = doc.getDocumentElement();
		String xmlFile = "C:\\Users\\aymen\\Desktop\\zellal\\examen\\examen_bis\\poeme\\fiches\\renault/renault.html";
		Element sourceElement = parser.parse(xmlFile).getDocumentElement();// récupère la racine du fichier d'entré
		boolean first= true;
		NodeList pList = sourceElement.getElementsByTagName("p");
		for(int j=0; j<pList.getLength(); j++){
			Element p = (Element)pList.item(j);
			NodeList strongList = p.getChildNodes();
			if(strongList.getLength()>=4 && strongList.item(1).getNodeName().compareTo("strong")==0){
				Element nom = doc.createElement("Nom");
				Element adresse = doc.createElement("Adresse");
				Element tel = doc.createElement("Num_téléphone");
				
				nom.appendChild(doc.createTextNode(strongList.item(1).getFirstChild().getNodeValue().replaceAll("[\n\r]", " ").trim()));
				
				String adresseValue = strongList.item(6).getNodeValue();
				if(first) adresseValue= adresseValue.replace(":", "");
				
				adresse.appendChild(doc.createTextNode(adresseValue.replaceAll("[\n\r]", " ").trim()));

				String telValue = strongList.item(first ? 10 : 8).getNodeValue();				
				if(first) telValue= telValue.replace(":", "");
				tel.appendChild(doc.createTextNode(telValue.replaceAll("[\n\r]", " ").trim()));

				documentElement.appendChild(nom);
				documentElement.appendChild(adresse);
				documentElement.appendChild(tel);
				
				if(first) first=false;
			}
		}
		
		DOMSource ds=new DOMSource(doc);
		StreamResult sr= new StreamResult(new File("RENAULT.xml"));
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer tr = tf.newTransformer();
		
		tr.setOutputProperty(OutputKeys.INDENT, "yes");
		tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tr.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"yes");
		tr.transform(ds, sr);		

	}

}
