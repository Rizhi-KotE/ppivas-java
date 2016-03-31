package frm;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.swing.*;
import javax.xml.parsers.*;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLMenuLoader {
	private InputSource source;
	private SAXParser parser;
	private DefaultHandler documentHandler;

	private Map<String, JComponent> menuStorage = new HashMap<String, JComponent>();

	public XMLMenuLoader(InputStream stream) {
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

	public JMenuBar getMenuBar() {
		return (JMenuBar) menuStorage.get("mainMenu");
	}

	private JMenuBar currentMenuBar;// текущая строка меню

	private LinkedList<JMenu> menus = new LinkedList();

	class XMLParser extends DefaultHandler {
		public void startElement(String uri, String localName, String qName, Attributes attr) {
			switch (qName) {
			case ("menubar"):
				parseMenuBar(attr);
				break;
			case ("menuitem"):
				parseMenuItem(attr);
				break;
			case ("menu"):
				parseMenu(attr);
				break;
			}
		}

		public void endElement(String uri, String localName, String qName) throws SAXException {
			if(qName.equals("menu")){
				menus.removeFirst();
			}
		}

		public void parseMenuBar(Attributes attr) {
			JMenuBar bar = new JMenuBar();
			String name = attr.getValue("name");
			menuStorage.put(name, bar);
			currentMenuBar = bar;
		}

		public void parseMenuItem(Attributes attr) {
			String name = attr.getValue("name");
			if (name.equals("separator")) {
				menus.getFirst().addSeparator();
				return;
			}
			String text = attr.getValue("text");

			JMenuItem item = new JMenuItem();
			udjustProperties(item, attr);

			menuStorage.put(name, item);
			menus.getFirst().add(item);
		}

		public void parseMenu(Attributes attr) {
			JMenu menu = new JMenu();
			String name = attr.getValue("name");

			udjustProperties(menu, attr);
			menuStorage.put(name, menu);

			if (menus.size() != 0) {
				menus.getFirst().add(menu);
			} else {
				currentMenuBar.add(menu);
			}
			menus.addFirst(menu);
		}

		public void udjustProperties(JMenuItem menuItem, Attributes attrs) {
			String text = attrs.getValue("text");
			menuItem.setText(text);

			String mnemonic = attrs.getValue("mnemonic");
			String accelerator = attrs.getValue("accelerator");
			String enabled = attrs.getValue("enabled");
			// настраиваем свойства меню
			menuItem.setText(text);
			if (mnemonic != null) {
				menuItem.setMnemonic(mnemonic.charAt(0));
			}
			if (accelerator != null) {
				menuItem.setAccelerator(KeyStroke.getKeyStroke(accelerator));
			}
			if (enabled != null) {
				boolean isEnabled = true;
				if (enabled.equals("false"))
					isEnabled = false;
				menuItem.setEnabled(isEnabled);
			}
		}
	}
}
