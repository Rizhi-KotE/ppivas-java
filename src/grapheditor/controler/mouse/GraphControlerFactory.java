package grapheditor.controler.mouse;

import java.lang.reflect.InvocationTargetException;
import java.util.EventListener;
import java.util.Map;
import java.util.Properties;

import javax.swing.event.MouseInputListener;

import catchers.ReflectionCatcher;
import grapheditor.algo.FindMinPathAlgo;
import grapheditor.view.main.PaintingPanel;

public class GraphControlerFactory {
	public static final String DIJKSTRA_S_ALGORITHM = "Dijkstra's algorithm";

	public static final String MIN_PATH_LISTENER = "algo_listener";

	public static final String SHAPED_COMPONENT = "ShapedComponent";

	public static final String ARC_TOOL = "Arc_tool";

	public static final String NODE_TOOL = "Node_tool";

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
		prop.setProperty(NODE_TOOL, "grapheditor.controler.mouse.NodeEditor");
		prop.setProperty(ARC_TOOL, "grapheditor.controler.mouse.ArcEditor");
		prop.setProperty(SHAPED_COMPONENT, "grapheditor.controler.mouse.SCMouseListener");
		prop.setProperty(MIN_PATH_LISTENER, "grapheditor.controler.mouse.AlgoMinPathFindListener");
		prop.setProperty(DIJKSTRA_S_ALGORITHM, "grapheditor.algo.FindMinPathDijkstra");
	}

	public MouseInputListener getMouseInputListener(String s, PaintingPanel ui) {
		MouseInputListener listener = null;
		if (listener == null) {
			if (prop == null) {
				createProperty();
			}
			String name = prop.getProperty(s);

			try {
				listener = (MouseInputListener) Class.forName(name).getConstructor(ui.getClass()).newInstance(ui);
			} catch (InstantiationException e) {
				ReflectionCatcher.instantiationException(e);
			} catch (IllegalAccessException e) {
				ReflectionCatcher.illegalAccessException(e);
			} catch (ClassNotFoundException e) {
				ReflectionCatcher.classNotFound(e);
			} catch (IllegalArgumentException e) {
				ReflectionCatcher.illegalArgumentException(e);
			} catch (InvocationTargetException e) {
				ReflectionCatcher.invocationTargetException(e);
			} catch (NoSuchMethodException e) {
				ReflectionCatcher.noSuchMethodException(e);
			} catch (SecurityException e) {
				ReflectionCatcher.securityException(e);
			}


		}
		return listener;
	}

	public MouseInputListener getAlgoInputListener(String s, String type, PaintingPanel ui) {
		MouseInputListener listener = null;
		if (listener == null) {
			if (prop == null) {
				createProperty();
			}
			String name = prop.getProperty(s);

			try {
				FindMinPathAlgo algo = (FindMinPathAlgo) Class.forName(prop.getProperty(type)).newInstance();
				listener = (MouseInputListener) Class.forName(name).getConstructor(ui.getClass(), FindMinPathAlgo.class)
						.newInstance(ui, algo);
			} catch (InstantiationException e) {
				ReflectionCatcher.instantiationException(e);
			} catch (IllegalAccessException e) {
				ReflectionCatcher.illegalAccessException(e);
			} catch (ClassNotFoundException e) {
				ReflectionCatcher.classNotFound(e);
			} catch (IllegalArgumentException e) {
				ReflectionCatcher.illegalArgumentException(e);
			} catch (InvocationTargetException e) {
				ReflectionCatcher.invocationTargetException(e);
			} catch (NoSuchMethodException e) {
				ReflectionCatcher.noSuchMethodException(e);
			} catch (SecurityException e) {
				ReflectionCatcher.securityException(e);
			}

		}
		return listener;
	}

	public MouseInputListener getMouseInputListener(String s) {
		MouseInputListener listener = null;
		if (listener == null) {
			if (prop == null) {
				createProperty();
			}
			String name = prop.getProperty(s);

			try {
				listener = (MouseInputListener) Class.forName(name).newInstance();
			} catch (ClassNotFoundException e) {
				ReflectionCatcher.classNotFound(e);

			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return listener;
	}
}
