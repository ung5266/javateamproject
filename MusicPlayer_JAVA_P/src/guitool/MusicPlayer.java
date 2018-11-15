package guitool;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MusicPlayer extends JFrame {
	public MusicPlayer() { // constructor
		// JFrame frame = new JFrame();
		JPanel panel = new JPanel();// panel use or not use?
		JLabel label = new JLabel("some text");
		JButton btn1 = new JButton("Click this");
		JButton btn2 = new JButton("Exit");
		JButton btn3 = new JButton("Login");
		JTextArea txtArea = new JTextArea();
		JTextField txtField = new JTextField(200);
		JPanel btnPanel = new JPanel();
		//
		panel.setLayout(new BorderLayout());
		btnPanel.add(btn1);
		btnPanel.add(btn2);
		btnPanel.add(btn3);
		//
		panel.add(label, BorderLayout.NORTH);
		panel.add(btnPanel, BorderLayout.WEST);
		panel.add(txtArea, BorderLayout.CENTER);
		//
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// txtArea.append("You are amzing\n");
				label.setText(txtArea.getText());
			}
		});
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new LogIn();
			}
		});

		//
		add(panel);
		//
		setUndecorated(true);
		setResizable(false);// size resizable
		setVisible(true);
		setPreferredSize(new Dimension(1280, 720));// setbounds - rectangle range
		setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);
		setLocationRelativeTo(null);// center displayed
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// swing exit button event
		// dislplay, frame Set
	}

}
