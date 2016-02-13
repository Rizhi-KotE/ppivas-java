package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import frm.XMLMenuLoader;
/**
 * @author DarkRaha
 *
 */
public class WinOfAplication extends JFrame {

    // серийный номер класса
    private static final long serialVersionUID = 1L;

    public WinOfAplication() {
        // -------------------------------------------
        // настройка окна
        setTitle("Example window"); // заголовок окна
        // желательные размеры окна
        setPreferredSize(new Dimension(640, 480));
        //загрузить панель меню
        loadMenuBar();
        
        // завершить приложение при закрытии окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // устанавливаем желательные размеры
        setVisible(true); // отображаем окно
    }
    
    void loadMenuBar(){
    	InputStream stream = null;
    	try {
			stream = new FileInputStream("xml/menubar.xml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	XMLMenuLoader loader = new XMLMenuLoader(stream);
    	try {
			loader.parse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	JMenuBar menuBar = loader.getMenuBar();
    	
    	setJMenuBar(menuBar);
    	
    }
    

}