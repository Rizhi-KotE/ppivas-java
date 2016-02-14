

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * SimpleComponentTest
 *
 * @author Eugene Matyushkin aka Skipy
 * @since 21.10.2010
 */
public class SimpleComponentTest extends JFrame {

    public SimpleComponentTest() {
        super("Simple component test");
        JPanel cp = new JPanel(new BorderLayout());
        cp.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5),
                BorderFactory.createLineBorder(Color.black)));
        setContentPane(cp);
        cp.add(new SimpleComponent(), BorderLayout.CENTER);
        JButton btn = new JButton("Close");
        btn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        cp.add(btn, BorderLayout.SOUTH);
        cp.setBackground(Color.green);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new SimpleComponentTest().setVisible(true);
    }
}