package view;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import frm.XMLMenuLoader;

public class WinOfAplication extends JFrame {

    // �������� ����� ������
    private static final long serialVersionUID = 1L;

    static Map<String, Container> winContainers;
    public static Container getContainer(String s){
    	if(winContainers == null){
    		winContainers = new HashMap<String, Container>();
    	}
    	return winContainers.get(s);
    }
    public WinOfAplication() {
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
    
    
    
    void loadMenuBar(){
    	InputStream stream = null;
    	try {
			stream = new FileInputStream("xml/menubar.xml");
		} catch (FileNotFoundException e) {
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