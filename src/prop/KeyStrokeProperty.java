package prop;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

import javax.swing.KeyStroke;

public abstract class KeyStrokeProperty {
	public static final KeyStroke STEP_ALGO_ACTION = KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK);
	private static Map<String, KeyStroke> bind;
	public static KeyStroke get(String s){
		if(bind == null){
			bind = initBind();
		}
		return bind.get(s);
	}
	private static Map<String, KeyStroke> initBind() {
		Map<String, KeyStroke> bind = new HashMap<>();
		bind.put(STEP_ALGO_ACTION, KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.ALT_MASK));
		return bind;
	}
}
