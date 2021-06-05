import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import java.net.*;

public class NewWindowInfo extends JFrame{
    JLabel label1 = new JLabel("18114022 송희재");
    JLabel label2 = new JLabel("2021 자바 텀프로젝트");
    JButton githubButton= new JButton("Github");
    JButton okButton = new JButton("OK");
    public NewWindowInfo() {
        setTitle("정보보기");
    	setLayout(new FlowLayout());
        Desktop desktop = java.awt.Desktop.getDesktop();

    	Container c = getContentPane();
        c.add(label1);
        c.add(label2);
        c.add(githubButton);
        githubButton.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                try {
                    URI oURL = new URI("https://github.com/hijae/imagepuzzle");
                    desktop.browse(oURL);
                } catch (URISyntaxException | IOException i) {
                    i.printStackTrace();
                }
            }
        });
        c.add(okButton);
        okButton.addMouseListener(new MouseAdapter() {
        		public void mousePressed(MouseEvent e) {
        			setVisible(false);
        		}
        });
        setSize(200,100);
        setVisible(true);
    }
}
