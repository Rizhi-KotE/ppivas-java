package view.grapheditor;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;

public class PaintingPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	public PaintingPanel() {
		super();
		setUI(new GraphPanelUI());
	}
	
	class ComponentListenerPP extends ComponentAdapter{

		@Override
		public void componentResized(ComponentEvent e) {
			
			
		}

		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
			
		}		
	}
}
