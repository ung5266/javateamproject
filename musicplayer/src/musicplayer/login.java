package musicplayer;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField idtext;
	private JPasswordField pintext;
	private JButton loginbtn;
	private JButton enroll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login framelog = new login();
					framelog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel loginlabel = new JLabel(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/로그인 라벨.PNG"));
		loginlabel.setFont(new Font("굴림", Font.BOLD, 50));
		loginlabel.setBounds(460, 63, 323, 121);
		contentPane.add(loginlabel);
		
		JLabel idlabel = new JLabel("\uC544\uC774\uB514");
		idlabel.setFont(new Font("굴림", Font.PLAIN, 40));
		idlabel.setBounds(308, 246, 120, 46);
		contentPane.add(idlabel);
		
		JLabel pinlabel = new JLabel("\uBE44\uBC00\uBC88\uD638");
		pinlabel.setFont(new Font("굴림", Font.PLAIN, 40));
		pinlabel.setBounds(270, 318, 160, 46);
		contentPane.add(pinlabel);
		
		idtext = new JTextField();
		idtext.setBounds(465, 246, 305, 46);
		contentPane.add(idtext);
		idtext.setColumns(10);
		
		pintext = new JPasswordField();
		pintext.setBounds(465, 318, 305, 46);
		contentPane.add(pintext);
		pintext.setColumns(10);
		
		loginbtn = new JButton(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/로그인.PNG"));
		loginbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				musicplayer frame = new musicplayer();
				frame.setVisible(true);
				dispose();

			}
		});
		loginbtn.setBounds(804, 252, 113, 100);
		contentPane.add(loginbtn);
		
		enroll = new JButton(new ImageIcon("C:/Users/정현정/eclipse-workspace/musicplayer/src/picture/회원가입.PNG"));
		enroll.setBounds(516, 412, 196, 55);
		contentPane.add(enroll);
	}
}
