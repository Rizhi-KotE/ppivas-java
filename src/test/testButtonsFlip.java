package test;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class testButtonsFlip {
	public static void main(String [] args){
		JFrame frame = new JFrame();
		//System.out.println(CreateListener.class.getName());
		JTextArea area = new JTextArea();
		
		frame.add(area);
		frame.setVisible(true);
		frame.pack();
	}
}
