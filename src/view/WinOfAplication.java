package view;
import java.awt.Container;
import java.awt.Dimension;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import frm.XMLMenuLoader;
/**
 * @author DarkRaha
 *
 */
public class WinOfAplication extends JFrame {

    // �������� ����� ������
    private static final long serialVersionUID = 1L;
   

    public WinOfAplication() {
    	super();
    	putContainer("mainframe", this);
        // -------------------------------------------
        // ��������� ����
        setTitle("Example window"); // ��������� ����
        // ����������� ������� ����
        setPreferredSize(new Dimension(640, 480));
        //��������� ������ ����
        loadMenuBar();
        
        // ��������� ���������� ��� �������� ����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack(); // ������������� ����������� �������
        setVisible(true); // ���������� ����
    }
    
    static private Map<String, Container> containerMap;
    
    public static void putContainer(String name, Container comp){
    	if(containerMap == null){
    		containerMap = new HashMap<String, Container>();
    	}
    	containerMap.put(name, comp);
    }
    public static Container getContainer(String name){
    	return containerMap.get(name);
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
    	//menuBar.addLis(ListenerFactory.getInstance().getActionListener("controler.menubarListener.menuBarListener"));
    }
    
    void createNewDocument(){
    	
    }
    

}