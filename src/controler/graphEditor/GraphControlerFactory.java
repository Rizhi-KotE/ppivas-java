package controler.graphEditor;

import java.awt.event.InputMethodListener;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.event.MouseInputListener;
import javax.swing.plaf.ComponentUI;

import catchers.ReflectionCatcher;

public class GraphControlerFactory {
	private Map<String, EventListener> controlers = null;

	private static GraphControlerFactory factory = null;

	private Properties prop = null;

	private GraphControlerFactory() {
		createProperty();
	}

	static public GraphControlerFactory getInstance() {
		if (factory == null) {
			factory = new GraphControlerFactory();
		}
		return factory;
	}

	private void createProperty() {
		prop = new Properties();
		prop.setProperty("MouseListener", "controler.graphEditor.NodeEditor");
	}

	public MouseInputListener getMouseInputListener(String s, ComponentUI ui) {
		if (controlers == null) {
			controlers = new HashMap<String, EventListener>();
		}
		MouseInputListener listener = (MouseInputListener) controlers.get(s);
		if (listener == null) {
			if (prop == null) {
				createProperty();
			}
			String name = prop.getProperty(s);
			try {
				listener = (MouseInputListener) Class.forName(name).getConstructor(ui.getClass()).newInstance(ui);
			} catch (ReflectiveOperationException e) {
				ReflectionCatcher.handleExeption(e);
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		return listener;
	}
}
