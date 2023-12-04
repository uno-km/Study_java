package Java.File.XML;

import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLCreate1 {
	public static void main(String[] args) {
		try {
			String[] parameters = { "ID", "NAME", "AGE" };
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("Parameters");
			document.appendChild(root);

			// create child elements with attributes
			for (String parameter : parameters) {
				Element elem = document.createElement(parameter);
				elem.setAttribute("key", "qwe");
				elem.setAttribute("org", "zxc");
				elem.appendChild(document.createTextNode("Value")); // replace "Value" with the actual value
				root.appendChild(elem);
			}

			// create the XML string
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource domSource = new DOMSource(document);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);
			transformer.transform(domSource, result);

			// print the XML string
			System.out.println(writer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
