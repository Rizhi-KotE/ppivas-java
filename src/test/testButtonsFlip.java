package test;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.text.PlainDocument;

import controler.menubarListener.CreateListener;
import view.WinOfAplication;

public class testButtonsFlip {
	public static void main(String [] args){
		JFrame frame = new JFrame();
		//System.out.println(CreateListener.class.getName());
		JTextArea area = new JTextArea();
		
		frame.add(area);
		frame.setVisible(true);
		frame.pack();
		PlainDocument
		System.out.println(area.getDocument().getClass().getName());
	}
}
