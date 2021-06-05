import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class NewWindowClear extends JFrame{
    JLabel label1 = new JLabel("성공하셨습니다!!");
    JButton okButton = new JButton("OK");
    public NewWindowClear() {
        setTitle("성공!!");
    	setLayout(new BorderLayout());
        Desktop desktop = Desktop.getDesktop();

    	Container c = getContentPane();
        c.add(label1,BorderLayout.CENTER);

        c.add(okButton,BorderLayout.SOUTH);
        okButton.addMouseListener(new MouseAdapter() {
        		public void mousePressed(MouseEvent e) {
        			setVisible(false);
        		}
        });
        setSize(200,200);
        setVisible(true);
    }
}
