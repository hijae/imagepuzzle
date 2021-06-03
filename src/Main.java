import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Main extends JFrame {
    public Main() {
        setTitle("18114022 송희재");
        createMenu(); // 메뉴 생성, 프레임에 삽입
        setSize(250,200);
        setVisible(true);
    }
    private void createMenu() {

        JMenuBar mb = new JMenuBar();
        String[] itemTitle1 = {"시작","정지","이미지 보기","정보보기"};
        String[] itemTitle2 = {"이미지 불러오기","순서 섞기","이미지초기화"};
        MenuActionListener listener = new MenuActionListener();
        JMenuItem [] menuItem1 = new JMenuItem [4];
        JMenuItem [] menuItem2 = new JMenuItem [3];
        JMenu screenMenu1 = new JMenu("게임");
        for(int i=0;i<itemTitle1.length;i++) {
            menuItem1[i] = new JMenuItem(itemTitle1[i]);
            menuItem1[i].addActionListener(listener);
            screenMenu1.add(menuItem1[i]);
        }
        mb.add(screenMenu1);

        mb.add(screenMenu2);
        JMenu screenMenu22 = new JMenu("2-2번");
        screenMenu22.add(new JMenuItem("Open"));
        screenMenu22.addSeparator(); // 분리선 삽입
        screenMenu22.add(new JMenuItem("Color"));
        screenMenu22.addSeparator(); // 분리선 삽입
        screenMenu22.add(new JMenuItem("Line"));
        screenMenu22.add(new JMenuItem("Rect"));
        screenMenu22.add(new JMenuItem("Oval"));
        screenMenu22.add(new JMenuItem("RndRect"));
        screenMenu22.add(new JMenuItem("Text"));
        screenMenu22.add(new JMenuItem("FreeLine"));

        mb.add(screenMenu22);
        JMenu screenMenu3 = new JMenu("3,4,5번");
        screenMenu3.add(new JMenuItem("3.게임시작"));
        screenMenu3.add(new JMenuItem("4.계산기"));
        JMenuItem menujavaitem = new JMenuItem("5.자바란?");
        Main.MenuActionListener listener = new Main.MenuActionListener();
        menujavaitem.addActionListener(listener);
        screenMenu3.add(menujavaitem);

        mb.add(screenMenu3);
        setJMenuBar(mb);
    }
    class MenuActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {

        }
    }
    public static void main(String [] args) {
        new Main();
    }
}