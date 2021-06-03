import java.awt.event.*;
import javax.swing.*;


public class Main extends JFrame {
    public Main() {
        setTitle("15퍼즐");
        createMenu(); // 메뉴 생성, 프레임에 삽입
        setSize(250,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    private void createMenu() {
        JMenuBar mb = new JMenuBar();
        String[] itemName = {"게임","기능"};
        String[][] itemTitle = {{"시작","정지","--","정보보기"},{"이미지 불러오기","순서 섞기","이미지초기화","이미지보기"}};
        MenuActionListener listener = new MenuActionListener();
        JMenuItem [][] menuItem = new JMenuItem [2][4];
        JMenu screenMenu[]=new JMenu[2];
        for(int i=0;i<itemName.length;i++) {
            screenMenu[i] = new JMenu(itemName[i]);
            for (int j = 0; j < itemTitle[i].length; j++) {
                if(itemTitle[i][j]=="--")
                {
                    screenMenu[i].addSeparator();
                    continue;
                }
                menuItem[i][j] = new JMenuItem(itemTitle[i][j]);
                menuItem[i][j].addActionListener(listener);
                screenMenu[i].add(menuItem[i][j]);
            }
            mb.add(screenMenu[i]);
        }
        setJMenuBar(mb);
    }
    class MenuActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch(cmd) {
                case "시작":

                case "정보보기" :
                    new NewWindowInfo();
                    break;
            }
        }
    }
    public static void main(String [] args) {
        new Main();
    }
}