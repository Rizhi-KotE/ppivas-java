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

    // �������� ����� ������
    private static final long serialVersionUID = 1L;

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