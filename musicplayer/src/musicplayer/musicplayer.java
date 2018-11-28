package musicplayer;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;

import javafx.stage.Screen;
import java.awt.Color;
import java.awt.Font;


public class musicplayer extends JFrame {

	private JPanel contentPane;
	private JTable charttable;
	private JTable favoritetable;
	private JTextField find;
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					musicplayer frame = new musicplayer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	
	public musicplayer() {//music player생성자
		//패널에 사진 넣기
		ImageIcon mainback=new ImageIcon(musicplayer.class.getResource("../picture/mainback.PNG")); 
		contentPane = new JPanel() {
	         public void paintComponent(Graphics g) {
	             g.drawImage(mainback.getImage(), 0, 0,1280,700, null);//mainback 이미지를 받아서 표시
	             setOpaque(false); //그림을 표시하게 설정,투명도 조절
	             super.paintComponent(g);
	         }
	    };
	    
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //프레임의 종료버튼이 눌렀을때 프로그램도 종료
		setBounds(0, 0, 1920, 1080);//프레임의 위치와 크기 조절
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.LIGHT_GRAY);
		setTitle("깔라만시 플레이어");
		setContentPane(contentPane);
		
		JButton findicon = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/find.PNG")));
		findicon.setToolTipText("\uAC80\uC0C9");
		findicon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((find.getText()).equals("")) {//내용이 없을 때
					JOptionPane.showMessageDialog(null,"내용을 입력해주세요","알림",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton logout = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/logout.PNG")));
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login framelog = new login();
				framelog.setVisible(true);
				dispose();
			}
		});
		logout.setToolTipText("\uB85C\uADF8\uC544\uC6C3");
		logout.setBounds(771, 0, 42, 41);
		logout.setBorderPainted(false);
		logout.setFocusPainted(false);
		contentPane.add(logout);
		
		findicon.setBounds(516, 111, 48, 41);
		contentPane.add(findicon);
		
		JButton sound = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/sound.PNG")));
		sound.setBounds(1110, 497, 53, 47);
		contentPane.add(sound);
		
		JButton fastf = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/fastf.PNG")));
		fastf.setBounds(1131, 590, 64, 75);
		contentPane.add(fastf);
		
		JButton fastb = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/fastb.PNG")));
		fastb.setBounds(895, 590, 64, 75);
		contentPane.add(fastb);
		
		JButton play = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/play.PNG")));
		play.setToolTipText("\uC7AC\uC0DD");
		play.setBounds(981, 565, 127, 125);
		//play.setBorderPainted(false);
		play.setFocusPainted(false);
		play.setContentAreaFilled(false);
		contentPane.add(play);
		
		JButton shuffle = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/shuffle.PNG")));
		shuffle.setBounds(1024, 497, 53, 47);
		contentPane.add(shuffle);
		
		JButton allplay = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/allplay.PNG")));
		allplay.setBounds(936, 497, 53, 47);
		contentPane.add(allplay);
		
		JButton list= new JButton(new ImageIcon(musicplayer.class.getResource("../picture/list.PNG")));
		list.setBounds(598, 111, 53, 47);
		contentPane.add(list);
		
		JButton users = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/users.PNG")));
		users.setToolTipText("Users");
		users.setBounds(466, 54, 79, 30);
		contentPane.add(users);
		
		JButton favoritemusic = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/favoritemusic.PNG")));
		favoritemusic.setToolTipText("Favorite Music");
		favoritemusic.setBounds(229, 54, 178, 30);
		contentPane.add(favoritemusic);
		
		JButton Musicchart = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/musicchart.PNG")));
		Musicchart.setToolTipText("music chart");
		//Musicchart.setBorderPainted(false);
		//Musicchart.setFocusPainted(false);
		Musicchart.setOpaque(true);
		Musicchart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Musicchart 맨앞으로 오게하기
				//charttable.setTableHeader(null);
				//JOptionPane.showMessageDialog(null, "music chart");
			}
		});
		Musicchart.setBounds(33, 54, 143, 30);
		contentPane.add(Musicchart);
		
		JLabel cover= new JLabel(new ImageIcon(musicplayer.class.getResource("../picture/coverex.PNG")));
		cover.setBounds(871, 93, 363, 356);
		contentPane.add(cover);
		
		JTabbedPane screen = new JTabbedPane(JTabbedPane.TOP);
		screen.setBounds(24, 172, 540, 495);
		contentPane.add(screen);
		
		charttable = new JTable();
		screen.addTab("Music Chart", null, charttable, null);
		
		favoritetable = new JTable();
		screen.addTab("Favorite Music", null, favoritetable, null);
		
		JTabbedPane usertab = new JTabbedPane(JTabbedPane.TOP);
		screen.addTab("User", null,  usertab, null);
		
		find = new JTextField();
		find.setBounds(24, 111, 474, 41);
		contentPane.add(find);
		find.setColumns(10);
		
		JLabel songname = new JLabel("SOLO");
		songname.setForeground(Color.BLACK);
		songname.setFont(new Font("돋움", Font.BOLD, 30));
		songname.setBounds(1013, 23, 103, 35);
		contentPane.add(songname);
		
		JLabel singer = new JLabel("제니");
		singer.setForeground(Color.BLACK);
		singer.setFont(new Font("돋움", Font.BOLD, 20));
		singer.setBounds(1035, 57, 48, 27);
		contentPane.add(singer);
		
	}
}
