package dynamic_beat_2;

import java.awt.Color; //setbackground - Color(0,0,0,0)
import java.awt.Cursor;
import java.awt.Graphics;//private Graphics screenImage;
import java.awt.Image;//private Images screenGraphic;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;//menuBar

//graphic 기반을 위한 JFrame 상속.
public class DynamicBeat extends JFrame {

	private Image screenImage;
	private Graphics screenGraphic;
	// 더블 버퍼링 위해서 전체 화면에 이미지를 담는 인스턴스.
	//private Graphics screenGraphic2;

	private Image introBackground = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage();
	// introBackground.jpg 를 담는 하나의 image 객체.
	private JLabel menuBar = new JLabel(new ImageIcon(Main.class.getResource("../images/menuBar.png")));
	// JLabel 의 객체 menuBar
	
	private ImageIcon exitButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/exitButtonEntered.png"));
	private ImageIcon exitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/exitButtonBasic.png"));
	//두개의 imageIcon초기화  (기본과 눌렀을때 두 경우)
	private ImageIcon startButtonBasicImage = new ImageIcon(Main.class.getResource("../images/startButtonBasic.png"));
	private ImageIcon startButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/startButtonEntered.png"));
	
	private ImageIcon quitButtonBasicImage = new ImageIcon(Main.class.getResource("../images/quitButtonBasic.png"));
	private ImageIcon quittButtonEnteredImage = new ImageIcon(Main.class.getResource("../images/quitButtonEntered.png"));
	
	private JButton exitButton = new JButton(exitButtonBasicImage);
	//exitButtonBasicImage 를 ImageIcon으로 한 JButton .
	private JButton startButton = new JButton(startButtonBasicImage);
	private JButton quitButton = new JButton(quitButtonBasicImage);
	private int mouseX, mouseY;
	//현재 프로그램에서 마우스 좌표.
	
	// constructor
	public DynamicBeat() {
		setUndecorated(true);
		// 실행했을때 기본적으로 있던 기본의 (조잡한) 메뉴바가 안보인다.
		setTitle("Dynamic Beat");
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		// display 설정.
		setResizable(false);
		// value 다시 설정 불가.
		setLocationRelativeTo(null);
		// game display 정중앙에 뜨게 함.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// game window 종료시 게임 전체를 끄게 해줌 - 안하면 프로그램 내부에서 계속돌아감.
		setVisible(true);
		// 정상적 코드일때 게임창을 display 에 visible 하게 해줌.
		
		setBackground(new Color(0, 0, 0, 0));
		// 배경이 전부 검은색으로 설정 - paintcomponet
		setLayout(null);
		// image 가 그위치에 정확히 삽입하게 해줌.

		//exitButton
		exitButton.setBounds(1245, 0, 32, 32);
		//크기와 출력 위치 설정.
		exitButton.setBorderPainted(false);
		exitButton.setContentAreaFilled(false);
		exitButton.setFocusPainted(false);
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				exitButton.setIcon(exitButtonEnteredImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//손가락 모양 마우스.
				//Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				//false - 한번만 실
			}//mouse 를 버튼에 올려 놓을때 enteredImage 로 전
			@Override
			public void mouseExited(MouseEvent e) {
				exitButton.setIcon(exitButtonBasicImage);
				exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));//손가락 모양 마우스.
			}//mouse 를 땔때 다시 basicImage
			@Override
			public void mousePressed(MouseEvent e) {
				/*Music buttonEnteredMusic = new Music("buttonEnteredMusic.mp3",false);
				buttonEnteredMusic.start();
				try {
					Thread.sleep(1000);//music이 나오고 1초 정도 이후 프로그램 종료.
				}catch(InterruptedException ex) {
					ex.printStackTrace();
				}*/
				System.exit(0);
			}//버튼 누르면 시스템 종료.
		});
		//추출 png 형태 그대로나타나게 함.
		exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		add(exitButton);
		
		//menuBar//마우스로 메뉴바 잡고 이동할 수 있게 처리. Override
				menuBar.setBounds(0, 0, 1280, 32); // 위치와 크기 설정
				menuBar.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						mouseX = e.getX(); 
						mouseY = e.getY();}
					//mouse로 해당 버튼 눌렀을때 이벤트 처리 
					// 오버라이드. 눌렀을때 x, y 좌표 get
				});
				menuBar.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseDragged(MouseEvent e) {
						int x = e.getXOnScreen();
						int y = e.getYOnScreen();//현재 스크린의 x,y좌표 가져옴.
						setLocation(x - mouseX, y - mouseY);
						//현재 jframe = game display 의 위치 바꾸어줌 
					}
				});
				add(menuBar);
		// introBackground = new ImageIcon(Main.class.getResource("../images/introBackground(Title).jpg")).getImage();
		// main.class.위치를 기반으로 introBackground의 image.jpg를 얻어오고 그것의image 인스턴스를
		// introBackground 라는
		// image 변수에 초기화. -> private Image introBackground;

		Music introMusic = new Music("introMusic(Cry).mp3", true);// introMusic(Cry).mp3 가 시작화면에서 무한 재생.
		introMusic.start();// game 시작시 introMusic.start
	}

	public void paint(Graphics g) {
		screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT); // 1280x720 의 image ->screenImage
		screenGraphic = screenImage.getGraphics();// 위의 screenImage로 screenGraphic - get
		
		//screenGraphic2 = screenImage.getGraphics();
		screenDraw(screenGraphic);// screenGraphic 의 그림을 그려줌.
		//screenDraw2(screenGraphic2);// screenGraphic 의 그림을 그려줌.
		
		g.drawImage(screenImage, 0, 0, null); // screenImage 를 0,0 위치에 그려짐.
	
	}
	


	// 원하는 화면 출력.
	public void screenDraw(Graphics g) {
		
		g.drawImage(introBackground, 0, 0, null); // screenImage 에 introBackground 그림.
		paintComponents(g);// image 를 단순하게 screenImage 에 그리는 것 이외에, JLabel - 같이 추가하는 것
		// 고정된 버튼이나 메뉴 추가에 용이. - 역동적이지 않으므로.
		this.repaint(); // gui 에서 가장 첫번째로 화면을 그려주는 함수 -paint();
		// 전체 paint 를 프로그램이 끝날때까지 계속 그려줌.
	}
	/*public void screenDraw2(Graphics g,Graphics g2) {
		//g2.drawImage(introBackground, 0, 0, null); // screenImage 에 introBackground 그림.
		paintComponents(g2);// image 를 단순하게 screenImage 에 그리는 것 이외에, JLabel - 같이 추가하는 것
		// 고정된 버튼이나 메뉴 추가에 용이. - 역동적이지 않으므로.
		//this.repaint(); // gui 에서 가장 첫번째로 화면을 그려주는 함수 -paint();
		// 전체 paint 를 프로그램이 끝날때까지 계속 그려줌.
	}*/
}
