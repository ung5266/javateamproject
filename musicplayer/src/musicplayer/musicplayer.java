package musicplayer;

import java.awt.EventQueue;
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
	
	public musicplayer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.GRAY);
		setTitle("뮤직 플레이어");
		setContentPane(contentPane);
		
		JButton findicon = new JButton(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/find.PNG"));
		findicon.setToolTipText("\uAC80\uC0C9");
		findicon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if((find.getText()).equals("")) {//내용이 없을 때
					JOptionPane.showMessageDialog(null,"내용을 입력해주세요","알림",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		JButton logout = new JButton(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/logout.PNG"));
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login framelog = new login();
				framelog.setVisible(true);
				dispose();
			}
		});
		logout.setToolTipText("\uB85C\uADF8\uC544\uC6C3");
		logout.setBounds(994, 10, 64, 57);
		contentPane.add(logout);
		
		findicon.setBounds(1005, 138, 48, 41);
		contentPane.add(findicon);
		
		JSlider slider = new JSlider();
		slider.setBackground(Color.GRAY);
		slider.setBounds(42, 20, 149, 19);
		contentPane.add(slider);
		
		JLabel maxsound = new JLabel(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/maxsound.PNG"));
		maxsound.setBounds(203, 10, 37, 41);
		contentPane.add(maxsound);

		JLabel minsound = new JLabel(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/minsound.PNG"));
		minsound.setBounds(0, 10, 37, 34);
		contentPane.add(minsound);
		
		JButton fastf = new JButton(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/fastf.PNG"));
		fastf.setBounds(129, 62, 46, 41);
		contentPane.add(fastf);
		
		JButton fastb = new JButton(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/fastb.PNG"));
		fastb.setBounds(44, 62, 46, 41);
		contentPane.add(fastb);
		
		JButton play = new JButton(new ImageIcon("C:/Users/\uC815\uD604\uC815/eclipse-workspace/musicplayer/src/picture/play.PNG"));
		play.setToolTipText("\uC7AC\uC0DD");
		play.setBounds(252, 38, 70, 65);
		play.setBorderPainted(false);
		play.setFocusPainted(false);
		play.setContentAreaFilled(false);
		contentPane.add(play);
		
		JButton shuffle = new JButton(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/shuffle.PNG"));
		shuffle.setBounds(1200, 148, 53, 47);
		contentPane.add(shuffle);
		
		JButton allplay = new JButton(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/allplay.PNG"));
		allplay.setBounds(1141, 148, 53, 47);
		contentPane.add(allplay);
		
		JButton list= new JButton(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/list.PNG"));
		list.setBounds(1082, 148, 53, 47);
		contentPane.add(list);
		
		JButton users = new JButton(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/users.PNG"));
		users.setToolTipText("Users");
		users.setBounds(24, 351, 140, 80);
		contentPane.add(users);
		
		JButton favoritemusic = new JButton(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/favoritemusic.PNG"));
		favoritemusic.setToolTipText("Favorite Music");
		favoritemusic.setBounds(24, 249, 140, 80);
		contentPane.add(favoritemusic);
		
		JButton Musicchart = new JButton(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/musicchart.PNG"));
		Musicchart.setToolTipText("music chart");
		Musicchart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Musicchart 맨앞으로 오게하기
				//charttable.setTableHeader(null);
				//JOptionPane.showMessageDialog(null, "music chart");
			}
		});
		Musicchart.setBounds(24, 148, 140, 80);
		contentPane.add(Musicchart);
		
		JLabel up_hpanel = new JLabel(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/재생bar.PNG"));
		up_hpanel.setBounds(0, 0, 1064, 125);
		contentPane.add(up_hpanel);
		
		JLabel left_vpanel = new JLabel(new ImageIcon("C:/Users/\uC815\uD604\uC815/eclipse-workspace/musicplayer/src/picture/\uC138\uB85C2.PNG"));
		left_vpanel.setBounds(0, 113, 194, 368);
		contentPane.add(left_vpanel);
		
		JLabel cover= new JLabel(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/coverex.PNG"));
		cover.setBounds(0, 483, 192, 207);
		contentPane.add(cover);
		
		JLabel logo = new JLabel(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/한기대.PNG"));
		logo.setBounds(1064, 0, 207, 125);
		contentPane.add(logo);
		
		JLabel playlist = new JLabel(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/세로2.PNG"));
		playlist.setBounds(1065, 108, 206, 592);
		contentPane.add(playlist);
		
		JTabbedPane screen = new JTabbedPane(JTabbedPane.TOP);
		screen.setBounds(203, 195, 851, 495);
		contentPane.add(screen);
		
		charttable = new JTable();
		screen.addTab("Music Chart", null, charttable, null);
		
		favoritetable = new JTable();
		screen.addTab("Favorite Music", null, favoritetable, null);
		
		JTabbedPane usertab = new JTabbedPane(JTabbedPane.TOP);
		screen.addTab("User", null,  usertab, null);
		
		find = new JTextField();
		find.setBounds(206, 138, 801, 41);
		contentPane.add(find);
		find.setColumns(10);
		
	}
}
