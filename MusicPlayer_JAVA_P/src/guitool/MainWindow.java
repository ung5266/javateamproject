package guitool;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JList;

public class MainWindow extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//
	private ImageIcon playbtnImage = new ImageIcon(Main.class.getResource("../images/play.png"));
	private ImageIcon forPlayImage = new ImageIcon(Main.class.getResource("../images/forward.png"));
	private ImageIcon backPlayImage = new ImageIcon(Main.class.getResource("../images/backward.png"));
	private ImageIcon stopbtnImage = new ImageIcon(Main.class.getResource("../images/pause.png"));
	//
	private ImageIcon listImage = new ImageIcon(Main.class.getResource("../images/list.png"));
	private ImageIcon musicaddImage = new ImageIcon(Main.class.getResource("../images/musicadd.png"));
	private ImageIcon shuffleImage = new ImageIcon(Main.class.getResource("../images/musicshuffle.png"));

	// private JButton playButton = new JButton(playbtnImage);
	//
	public MainWindow() {
		setUndecorated(true);
		setResizable(false);// size resizable
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLocationRelativeTo(null);// center displayed
		getContentPane().setLayout(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel menupanel = new JPanel();
		menupanel.setBounds(0, 0, 1280, 30);
		contentPane.add(menupanel);
		menupanel.setBackground(new Color(255, 160, 122));
		menupanel.setLayout(null);

		JButton btnExit = new JButton("");
		btnExit.setIcon(new ImageIcon(
				"/Users/seongwoongcho/eclipse-workspace/MusicPlayer_JAVA_P/src/images/exit-to-app-button-3.png"));
		btnExit.setBackground(new Color(255, 255, 224));
		btnExit.setBounds(1242, 0, 32, 30);
		menupanel.add(btnExit);
		//------------------------------------------
		JPanel category = new JPanel();
		category.setBackground(new Color(255, 248, 220));
		category.setBounds(0, 30, 250, 690);
		contentPane.add(category);
		category.setLayout(null);

		JButton btnLogIn = new JButton("LOG IN");
		btnLogIn.setBounds(6, 6, 88, 39);
		category.add(btnLogIn);

		JButton btnUsers = new JButton("");
		btnUsers.setBounds(6, 154, 238, 88);
		btnUsers.setIcon(
				new ImageIcon("/Users/seongwoongcho/eclipse-workspace/MusicPlayer_JAVA_P/src/images/btnprofile.png"));
		category.add(btnUsers);

		JButton btnChart = new JButton("");
		btnChart.setBounds(6, 244, 238, 88);
		btnChart.setIcon(
				new ImageIcon("/Users/seongwoongcho/eclipse-workspace/MusicPlayer_JAVA_P/src/images/btnchart.png"));
		category.add(btnChart);

		JButton btnLikeMusic = new JButton("");
		btnLikeMusic.setBounds(6, 334, 238, 88);
		btnLikeMusic.setIcon(
				new ImageIcon("/Users/seongwoongcho/eclipse-workspace/MusicPlayer_JAVA_P/src/images/btnfav.png"));
		category.add(btnLikeMusic);
		//----------------------------------------
		JPanel musicAlbum = new JPanel();
		musicAlbum.setBounds(6, 434, 238, 250);
		category.add(musicAlbum);
		musicAlbum.setBackground(new Color(255, 250, 250));
		musicAlbum.setLayout(null);

		JPanel playlist = new JPanel();
		playlist.setBackground(new Color(245, 245, 220));
		playlist.setBounds(1030, 30, 250, 690);
		contentPane.add(playlist);
		playlist.setLayout(null);
		//
		JButton btnAdd = new JButton(musicaddImage);
		btnAdd.setBounds(172, 5, 32, 32);
		// 크기와 출력 위치 설정.
		btnAdd.setBorderPainted(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setFocusPainted(false);
		playlist.add(btnAdd);

		JButton btnShuffle = new JButton(shuffleImage);
		btnShuffle.setBounds(212, 5, 32, 32);
		// 크기와 출력 위치 설정.
		btnShuffle.setBorderPainted(false);
		btnShuffle.setContentAreaFilled(false);
		btnShuffle.setFocusPainted(false);
		playlist.add(btnShuffle);

		JButton btnList = new JButton(listImage);
		btnList.setEnabled(false);
		btnList.setBounds(6, 5, 32, 32);
		// 크기와 출력 위치 설정.
		btnList.setBorderPainted(false);
		btnList.setContentAreaFilled(false);
		btnList.setFocusPainted(false);
		playlist.add(btnList);

		// -------------------------------------------
		JPanel displaywindow = new JPanel();
		displaywindow.setBackground(new Color(255, 255, 255));
		displaywindow.setBounds(250, 180, 780, 540);
		contentPane.add(displaywindow);
		displaywindow.setLayout(null);

		JPanel chart = new JPanel();
		chart.setBackground(new Color(255, 240, 245));
		chart.setBounds(0, 0, 780, 540);
		displaywindow.add(chart);
		chart.setLayout(null);
		chart.setVisible(false);

		JPanel favMusic = new JPanel();
		favMusic.setBackground(new Color(255, 255, 0));
		favMusic.setBounds(0, 0, 780, 540);
		displaywindow.add(favMusic);
		favMusic.setLayout(null);
		favMusic.setVisible(false);

		JPanel users = new JPanel();
		users.setBackground(new Color(255, 140, 0));
		users.setBounds(0, 0, 780, 540);
		displaywindow.add(users);
		users.setLayout(null);
		users.setVisible(false);

		//
		btnChart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				favMusic.setVisible(false);
				users.setVisible(false);
				chart.setVisible(true);
			}
		});
		btnUsers.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				favMusic.setVisible(false);
				chart.setVisible(false);
				users.setVisible(true);
			}
		});
		btnLikeMusic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				chart.setVisible(false);
				users.setVisible(false);
				favMusic.setVisible(true);
			}
		});
		// ------------------------------------------

		JPanel usermenu = new JPanel();
		usermenu.setBackground(new Color(255, 228, 181));
		usermenu.setBounds(250, 30, 780, 150);
		contentPane.add(usermenu);
		usermenu.setLayout(null);

		JButton btnPlay = new JButton(playbtnImage);
		btnPlay.setBounds(109, 43, 48, 48);
		// 크기와 출력 위치 설정.
		btnPlay.setBorderPainted(false);
		btnPlay.setContentAreaFilled(false);
		btnPlay.setFocusPainted(false);
		usermenu.add(btnPlay);
		//
		JButton btnStop = new JButton(stopbtnImage);
		btnStop.setBounds(108, 43, 48, 48);
		// 크기와 출력 위치 설정.
		btnStop.setBorderPainted(false);
		btnStop.setContentAreaFilled(false);
		btnStop.setFocusPainted(false);
		usermenu.add(btnStop);

		//
		btnStop.setVisible(false);

		btnPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnPlay.setVisible(false);
				btnStop.setVisible(true);
			}
		});

		btnStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				btnStop.setVisible(false);
				btnPlay.setVisible(true);
			}
		});
		//
		JButton btnforPlay = new JButton(forPlayImage);
		btnforPlay.setBounds(162, 43, 48, 48);
		// 크기와 출력 위치 설정.
		btnforPlay.setBorderPainted(false);
		btnforPlay.setContentAreaFilled(false);
		btnforPlay.setFocusPainted(false);
		usermenu.add(btnforPlay);

		JButton btnbackPlay = new JButton(backPlayImage);
		btnbackPlay.setBounds(49, 43, 48, 48);
		// 크기와 출력 위치 설정.
		btnbackPlay.setBorderPainted(false);
		btnbackPlay.setContentAreaFilled(false);
		btnbackPlay.setFocusPainted(false);
		usermenu.add(btnbackPlay);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 235, 205));
		panel.setBounds(0, 30, 1280, 690);
		contentPane.add(panel);

		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnLogIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LogIn();
			}
		});
	}
}
