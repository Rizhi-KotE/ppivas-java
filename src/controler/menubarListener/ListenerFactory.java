package controler.menubarListener;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ListenerFactory {
	static ListenerFactory factory = null;
	static Map<String, ActionListener> createdListeners;

	private ListenerFactory() {

	}

	public static ListenerFactory getInstance() {
		if (factory == null) {
			factory = new ListenerFactory();
		}
		return factory;
	}
	
	private static Map<String, ActionListener> getListenerMap(){
		if(createdListeners == null){
			createdListeners = new HashMap<String, ActionListener>();
		}
		return createdListeners;
	}

	public ActionListener getActionListener(String name) {
		ActionListener listener = getListenerMap().get(name);
		if (listener == null)
			try {
				listener = (ActionListener) Class.forName(name).newInstance();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				System.err.println(getClass().getName());
				System.err.println("Констуктор недоступен");
			} catch (ClassNotFoundException e) {
				System.err.println(getClass().getName());
				System.err.println("Слушатель не найден");
			}

		return listener;
	}

}
