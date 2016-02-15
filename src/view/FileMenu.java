package view;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

class FileMenu extends JMenu{
	public FileMenu(){
		JMenu menu = new JMenu();
		//------------------------
		JMenuItem open = new JMenuItem("open");
		JMenuItem close = new JMenuItem("close");
		//------------------------
		menu.add(open);
		menu.add(close);
		
		menu.addSeparator();
		
	}
}
