package view;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Shape;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import frm.XMLMenuLoader;
import view.grapheditor.PaintingPanel;

public class WinOfAplication extends JFrame {

    // �������� ����� ������
    private static final long serialVersionUID = 1L;

    private static WinOfAplication winContainers;
    
    private PaintingPanel graphPanel;
    
    public static WinOfAplication getInstance(){
    	return winContainers;
    }
    
    public WinOfAplication() {
        // -------------------------------------------
        // ��������� ����
        setTitle("Example window"); // ��������� ����
        // ����������� ������� ����
        setPreferredSize(new Dimension(640, 480));
        setBackground(Color.gray);
        //��������� ������ ����
        loadMenuBar();

        winContainers = this;
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
    
    public void newGraph(){
    	graphPanel = new PaintingPanel();
    	add(graphPanel);
    	pack();
    }
    
    static public void main(String[] args){
    	WinOfAplication f = new WinOfAplication();
    }
}