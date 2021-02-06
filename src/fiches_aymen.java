
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

public class fiches_aymen {

	public static void main(String[] args) throws ParserConfigurationException {
		String xmlFile="C:\\Users\\aymen\\Desktop\\zellal\\examen\\examen_bis\\poeme\\fiches/fiches.txt";
		DocumentBuilderFactory factory =DocumentBuilderFactory.newInstance();
		DocumentBuilder parseur = null;
		parseur = factory.newDocumentBuilder();
		DOMImplementation domimp = parseur.getDOMImplementation();
		DocumentType dtd = domimp.createDocumentType("FICHES", null, "fiches.dtd");
		Document doc = domimp.createDocument(null, "FICHES", dtd);
		doc.setXmlStandalone(true);
		Element rac = doc.getDocumentElement();
		
		int cpt_br = 1;
		int cpt_l = 1; 
		InputStream flux = null;
		try {
			flux = new FileInputStream(xmlFile);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} 
		InputStreamReader lecture=new InputStreamReader(flux);
		BufferedReader buff=new BufferedReader(lecture);
		String ligne="";
		Element fiche = doc.createElement("FICHE");

		String lastWord2;
		
		Element l = doc.createElement("Langue");

		try {
			while((ligne=buff.readLine())!=null){
				if(ligne.isEmpty()) {
					System.out.print("");
				}
				else {
					 String[] parts = ligne.split("\t");
					if(parts.length>1) {
						String lastWord = parts[parts.length - 1];
						String DO = null;
						String SD = null;
						if(lastWord.contains("BE")) {
							
							    fiche = doc.createElement("FICHE");
								fiche.setAttribute("id",Integer.toString(cpt_br));
								rac.appendChild(fiche);
								
								rac.appendChild(fiche);
								Element e = doc.createElement(lastWord);
								fiche.appendChild(e);
								ligne=ligne.replaceAll(lastWord, "");
								e.appendChild(doc.createTextNode(ligne));
								
								cpt_br++;
								
							ligne=buff.readLine();
							parts = ligne.split("\t");
							if(parts.length>1) {
								 lastWord = parts[parts.length - 1];
							}
							e = doc.createElement(lastWord);
							fiche.appendChild(e);
							if ( lastWord.contains("BE")) {
								e.appendChild(doc.createTextNode(ligne));
							}else {
							if (lastWord.contains(":")) {
								e.appendChild(doc.createTextNode(lastWord+ligne));
							}else
							{
								lastWord = lastWord+" :";
								e.appendChild(doc.createTextNode(lastWord+ligne));
								
							}
							
							}
							ligne=buff.readLine();
							 DO = ligne;
							ligne=buff.readLine();
							 SD = ligne;
							ligne=buff.readLine();
							parts = ligne.split("\t");
							if(parts.length>1) {
								 lastWord = parts[parts.length - 1];
							}
							e = doc.createElement(lastWord);
							fiche.appendChild(e);
							if ( lastWord.contains("BE")) {
								e.appendChild(doc.createTextNode(ligne));
							}else {
							if (lastWord.contains(":")) {
								e.appendChild(doc.createTextNode(lastWord+ligne));
							}else
							{
								lastWord = lastWord+" :";
								e.appendChild(doc.createTextNode(lastWord+ligne));
								
							}
							
							}
						}
						if(lastWord.contains("VE")) {
							 l = doc.createElement("Langue");
							if(cpt_l==1) {
								l.setAttribute("id", "AR");
								fiche.appendChild(l);
								
								cpt_l++;
							}else {
								l.setAttribute("id", "FR");
								fiche.appendChild(l);
								cpt_l--;
							}
							Element e = doc.createElement("DO");
							l.appendChild(e);
							e.appendChild(doc.createTextNode("DO"+DO));
							
							 e = doc.createElement("SD");
							l.appendChild(e);
							e.appendChild(doc.createTextNode("SD"+SD));
							
							lastWord2 = lastWord; 
							lastWord2 = lastWord2.replaceAll(" :", "");
							e = doc.createElement(lastWord2);
							l.appendChild(e);
							
							if (lastWord.contains(":")) {
								e.appendChild(doc.createTextNode(lastWord+ligne));
							}else
							{
								lastWord = lastWord+" :";
								e.appendChild(doc.createTextNode(lastWord+ligne));					
							}
							
						}else {
							lastWord2 = lastWord; 
							lastWord2 = lastWord2.replaceAll(" :", "");
							Element e = doc.createElement(lastWord2);
							l.appendChild(e);
							
							if (lastWord.contains(":")) {
								e.appendChild(doc.createTextNode(lastWord+ligne));
							}else
							{
								lastWord = lastWord+" :";
								e.appendChild(doc.createTextNode(lastWord+ligne));					
							}
						}
					}			
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
		StreamResult res = new StreamResult(new File("fiches1_aymen.xml"));
		TransformerFactory transform = TransformerFactory.newInstance();
		Transformer tr = null;
		try {
			tr = transform.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		
		tr.setOutputProperty(OutputKeys.INDENT, "yes");
		try {
			tr.transform(ds, res);
		} catch (TransformerException e) {
		
			e.printStackTrace();
		}
  	}
    
	}


