package test;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JLabel;
import javax.swing.JMenuBar;

import controler.menubarListener.createListener;
import frm.XMLMenuLoader;
import view.TwoButtonsFlip;
import view.WinOfAplication;

public class testButtonsFlip {
	public static void main(String [] args){
		WinOfAplication frame = new WinOfAplication();
		System.out.println(createListener.class.getName());
	}
}
