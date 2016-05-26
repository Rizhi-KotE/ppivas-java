package frm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import grapheditor.view.elements.ViewEdge;
import grapheditor.view.elements.ViewGraphElement;
import grapheditor.view.elements.ViewNode;

public class SaveGraph {

	private static final String ROOT_ELEM_TAG = "root";
	private static Document doc;

	private static Document newDoc() {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		Document doc = null;
		try {
			doc = factory.newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}

	public void save(String file, Collection<ViewGraphElement> elements) {

		doc = newDoc();
		Element root = doc.createElement(ROOT_ELEM_TAG);
		doc.appendChild(root);

		Element nodes = doc.createElement("nodes");
		root.appendChild(nodes);

		Element arcs = doc.createElement("arcs");
		root.appendChild(arcs);

		for (ViewGraphElement el : elements) {
			switch (el.getElementType()) {
			case "Node": {
				Element n = doc.createElement(el.getElementType());
				ViewNode v = (ViewNode) el;
				n.setAttribute("Content", el.getContent());
				n.setAttribute("Hash", new Integer(el.hashCode()).toString());
				n.setAttribute("X", (new Double(v.getX()).toString()));
				n.setAttribute("Y", (new Double(v.getY()).toString()));
				n.setAttribute("type", v.getElementType());
				nodes.appendChild(n);
				break;
			}
			case "Edge": {
				Element n = doc.createElement(el.getElementType());
				ViewEdge edge = (ViewEdge) el;
				n.setAttribute("Content", el.getContent());
				n.setAttribute("Hash", new Integer(el.hashCode()).toString());
				n.setAttribute("Node1", new Integer(edge.getNode1().hashCode()).toString());
				n.setAttribute("Node2", new Integer(edge.getNode2().hashCode()).toString());
				n.setAttribute("type", edge.getElementType());
				arcs.appendChild(n);
				break;
			}
			}
		}
		try {
			toXML(file, doc);
		} catch (TransformerException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void toXML(String s, Document document) throws TransformerException, IOException {

		File file = new File(s);

		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.transform(new DOMSource(doc), new StreamResult(file));
	}

	public ClipGraph loadGraph(String s) {
		InputStream in = null;
		try {
			in = new FileInputStream(s);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XMLGraphLoader loader = new XMLGraphLoader(in);
		try {
			loader.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ClipGraph clipGraph = loader.getGraph();

		return clipGraph;
	}

	private class XMLGraphLoader {
		private InputSource source;
		private SAXParser parser;
		private DefaultHandler documentHandler;

		private ClipGraph clipGraph;

		public XMLGraphLoader(InputStream stream) {
			clipGraph = new ClipGraph();
			try {
				Reader reader = new InputStreamReader(stream);
				source = new InputSource(reader);
				parser = SAXParserFactory.newInstance().newSAXParser();
			} catch (Exception ex) {
				throw new RuntimeException(ex);

			}
			documentHandler = new XMLParser();
		}

		public void parse() throws Exception {
			parser.parse(source, documentHandler);
		}

		public ClipGraph getGraph() {
			return clipGraph;
		}

		class XMLParser extends DefaultHandler {
			public void startElement(String uri, String localName, String qName, Attributes attr) {
				switch (qName) {
				case ("menubar"):
					break;
				case ("Edge"):
					parseEdge(attr);
					break;
				case ("Node"):
					parseNode(attr);
					break;
				}
			}

			private void parseEdge(Attributes attr) {
				String content = attr.getValue("Content");
				int n1 = Integer.parseInt(attr.getValue("Node1"));
				int n2 = Integer.parseInt(attr.getValue("Node2"));
				ViewNode node1 = clipGraph.getNode(n1);
				ViewNode node2 = clipGraph.getNode(n2);
				ViewEdge edge = new ViewEdge(node1, node2);
				try {
					edge.setContent(content);
				} catch (NumberFormatException e) {
					
				}
				String type = attr.getValue("type");
				edge.setType(type);
				clipGraph.addEdge(edge);
			}

			private void parseNode(Attributes attr) {
				String content = attr.getValue("Content");
				int hash = Integer.parseInt(attr.getValue("Hash"));
				double x = Double.parseDouble(attr.getValue("X"));
				double y = Double.parseDouble(attr.getValue("Y"));
				ViewNode node = new ViewNode(x,y);
				node.setContent(content);
				String type = attr.getValue("type");
				node.setType(type);
				clipGraph.addNode(hash, node);
			}

		}
	}
}
