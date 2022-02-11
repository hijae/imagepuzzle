import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.*;



public class Main extends JFrame {
    private drawmap panel = new drawmap();
    int[][] imgpin ={{0,0,1,1},{1,0,2,1},{2,0,3,1},{3,0,4,1},{0,1,1,2},{1,1,2,2},{2,1,3,2},{3,1,4,2},{0,2,1,3},{1,2,2,3},{2,2,3,3},{3,2,4,3},{0,3,1,4},{1,3,2,4},{2,3,3,4},{3,3,4,4}}; //이미지 위치 지정
    int[] mappin ={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}; //위치 번호 지정
    int pinloc=15;
    ImageIcon icon = new ImageIcon("image0.jpg"); //이미지 지정
    Image img = icon.getImage(); //이미지 불러오기
    public Main() {
        setContentPane(panel);
        setTitle("15퍼즐");
        createMenu(); // 메뉴 생성, 프레임에 삽입
        setSize(400, 450); // 프레임 크기 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 종료 설정
        Container c = getContentPane(); // 프레임의 컨테이너 설정
        c.setLayout(new FlowLayout());  // 프레임의 레이아웃 설정
        c.addKeyListener(new GameKey()); // 프레임에 키리스너 설정
        c.setFocusable(true); // 프레임에 포커스 설정
        c.requestFocus(); // 프레임에 포커스 요청
        setVisible(true); // 프레임 보이기
        randommap(); //랜덤으로 순서 섞기 함수 호출
    }


    class drawmap extends JPanel { // 맵 출력 패널 생성
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for(int i=0;i<16;i++) { //맵 이미지 출력
                if(mappin[i]!=15) //맵의 마지막 위치가 아니면
                {
                    g.drawImage(img, imgpin[i][0]*100, imgpin[i][1]*100, imgpin[i][2]*100, imgpin[i][3]*100, imgpin[mappin[i]][0]*100, imgpin[mappin[i]][1]*100, imgpin[mappin[i]][2]*100, imgpin[mappin[i]][3]*100, this);
                }
            }
        }
    }

    private void createMenu() // 메뉴 작성
    {
        JMenuBar mb = new JMenuBar();
        String[] itemName = {"게임", "기능"};
        String[][] itemTitle = {{"종료", "--", "정보보기"}, {"순서섞기", "순서초기화", "이미지보기"}};
        MenuActionListener listener = new MenuActionListener();
        JMenuItem[][] menuItem = new JMenuItem[2][4];
        JMenu screenMenu[] = new JMenu[2];
        for (int i = 0; i < itemName.length; i++) {
            screenMenu[i] = new JMenu(itemName[i]);
            for (int j = 0; j < itemTitle[i].length; j++) {
                if (itemTitle[i][j].equals("--")) {
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

    void mapreset() { // 맵 초기화
        for (int i = 0; i < 16; i++) {
            mappin[i] = i;
        }
        repaint();
    }
    void randommap() { // 랜덤 맵 생성
        for(int i=0;i<30;i++)
        {
            int r=(int)(Math.random()*4); // 랜덤 이동 방향 정하기
            switch (r) {
                case 0 -> up();
                case 1 -> down();
                case 2 -> left();
                case 3 -> right();
            }
        }
    }
    void up() // 위로 이동 함수
    {
        if(pinloc-4>=0)
        {
            int t=mappin[pinloc-4];
            mappin[pinloc-4]=mappin[pinloc];
            mappin[pinloc]=t;
            pinloc=pinloc-4;
            repaint();
            for(int j=0;j<16;j++) System.out.print(mappin[j]+" ");
            System.out.println(pinloc);
        }
    }
    void down() // 아래로 이동 함수
    {
        if (pinloc + 4 < 16) {
            int t = mappin[pinloc + 4];
            mappin[pinloc + 4] = mappin[pinloc];
            mappin[pinloc] = t;
            pinloc = pinloc + 4;
            repaint();
            for (int j = 0; j < 16; j++) System.out.print(mappin[j] + " ");
            System.out.println(pinloc);
        }
    }
    void left() // 왼쪽으로 이동 함수
    {
        if(pinloc%4!=0)
        {
            int t=mappin[pinloc-1];
            mappin[pinloc-1]=mappin[pinloc];
            mappin[pinloc]=t;
            pinloc=pinloc-1;
            repaint();
        }
    }
    void right() // 오른쪽으로 이동 함수
    {
        if(pinloc%4!=3)
        {
            int t=mappin[pinloc+1];
            mappin[pinloc+1]=mappin[pinloc];
            mappin[pinloc]=t;
            pinloc=pinloc+1;
            repaint();
        }
    }
    boolean clear() // 성공인지 확인
    {
        boolean flag=true;
        for(int i=0;i<16;i++) {
            if(mappin[i]!=i)
            {
                flag=false;
            }
        }
        return (flag);
    }
    class GameKey extends KeyAdapter { // 키 입력 확인
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP -> up();
                case KeyEvent.VK_DOWN -> down();
                case KeyEvent.VK_LEFT -> left();
                case KeyEvent.VK_RIGHT -> right();
            }
            if(clear()) // 성공시
            {
                JOptionPane.showMessageDialog(null,
                        "축하합니다! 성공입니다!", "성공",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    class MenuActionListener implements ActionListener { // 메뉴 액션 리스너
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();
            switch (cmd) {
                case "시작" -> {
                    setContentPane(panel);
                    repaint();
                }
                case "정보보기" -> new NewWindowInfo();
                case "순서섞기" -> randommap();
                case "순서초기화" -> mapreset();
                case "이미지보기"->new NewWindowImage();
                case "종료"->System.exit(0);
            }
        }
    }
    public class NewWindowImage extends JFrame{ // 이미지 보기 창
        JLabel label = new JLabel(icon);
        JButton okButton = new JButton("OK");
        public NewWindowImage() {
            setTitle("이미지보기");
            setLayout(new FlowLayout());
            Desktop desktop = Desktop.getDesktop();
            Container c = getContentPane();
            c.add(label);

            c.add(okButton);
            okButton.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    setVisible(false);
                }
            });
            setSize(400,500);
            setVisible(true);
        }
    }
    public class NewWindowInfo extends JFrame{ // 정보보기 창
        JButton githubButton= new JButton("Github");
        JButton okButton = new JButton("OK");
        public NewWindowInfo() {
            setTitle("정보보기");
            setLayout(new FlowLayout());
            Desktop desktop = java.awt.Desktop.getDesktop();

            Container c = getContentPane();
            c.add(new JLabel("18114022 송희재"));
            c.add(new JLabel("2021 자바 텀프로젝트"));
            c.add(githubButton);
            githubButton.addMouseListener(new MouseAdapter() {
                public void mousePressed(MouseEvent e) {
                    try {
                        URI oURL = new URI("https://github.com/hijae/imagepuzzle");
                        desktop.browse(oURL);
                    } catch (URISyntaxException | IOException i) {
                        i.printStackTrace();
                    }
                    new NewWindowInfo();
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
    public static void main(String[] args) {
        new Main();
    } // 메인 함수
}