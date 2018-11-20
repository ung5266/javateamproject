package musicplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class sign extends JFrame {

	private JPanel contentPane;
	private JTextField mypin;
	private JTextField pincheck;
	private JTextField myid;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					sign framesign = new sign();
					framesign.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public sign() {
		ImageIcon signback=new ImageIcon(musicplayer.class.getResource("../picture/signscreen.PNG"));
		contentPane = new JPanel() {
	         public void paintComponent(Graphics g) {
	             g.drawImage(signback.getImage(), 0, 0,500,500, null);
	             setOpaque(false); //�׸��� ǥ���ϰ� ����,�����ϰ� ����
	             super.paintComponent(g);
	         }
	    };

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 500, 530);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		myid = new JTextField();
		myid.setBounds(182, 130, 194, 46);
		contentPane.add(myid);
		myid.setColumns(10);
		
		mypin = new JTextField();
/*		mypin.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}
			public void inputMethodTextChanged(InputMethodEvent arg0) {
				//��й�ȣ�� ��й�ȣ Ȯ�� ��
				if(!((mypin.getText()).equals(""))&&!((pincheck.getText()).equals(""))&&((mypin.getText()).equals(pincheck.getText()))) {
					pinokmsg.setVisible(true);
				}
			}
		});*/

		mypin.setBounds(182, 176, 279, 51);
		contentPane.add(mypin);
		mypin.setColumns(10);
		
		pincheck = new JTextField();
		pincheck.setBounds(182, 227, 279, 46);
		contentPane.add(pincheck);
		pincheck.setColumns(10);
		
		JRadioButton woman = new JRadioButton("��");
		woman.setFont(new Font("����", Font.BOLD, 15));
		woman.setBackground(Color.WHITE);
		woman.setBounds(261, 279, 50, 36);
		contentPane.add(woman);
		
		JRadioButton man = new JRadioButton("��");
		man.setFont(new Font("����", Font.BOLD, 15));
		man.setBackground(Color.WHITE);
		man.setBounds(397, 279, 50, 36);
		contentPane.add(man);
		
		JTextField call= new JTextField();
		call.setBounds(182, 321, 279, 46);
		contentPane.add(call);
		call.setColumns(10);
		
		JButton signok = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/ok.PNG")));
		signok.setBounds(137, 405, 95, 51);
		contentPane.add(signok);
		
		JButton signcancel = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/cancel.PNG")));
		signcancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login framelog = new login();
				framelog.setVisible(true);
				dispose();
			}
		});
		signcancel.setBounds(280, 405, 95, 51);
		contentPane.add(signcancel);
		
		JButton idcheck = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/idcheck.PNG")));
		//���̵� �ߺ� Ȯ�� ��ư ������ ��
		idcheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ID="";
				int sameid=0;
				if(myid.getText().equals("")) {//���̵� �Է����� �ʾ��� ��
					JOptionPane.showMessageDialog(null,"���̵� �Է����ּ���","�˸�",JOptionPane.ERROR_MESSAGE);
				}
				else {//���̵� �ԷµǾ����� ��
				try {
					FileReader fileIDcheck=new FileReader("C:\\Users\\������\\eclipse-workspace\\musicplayer\\src\\TextFile\\ID.txt");
					BufferedReader bufReaderIDcheck=new BufferedReader(fileIDcheck);
					while((ID = bufReaderIDcheck.readLine()) != null) {
						if(ID.equals(myid.getText())) {
							sameid++;
							JOptionPane.showMessageDialog(null,"���̵� �ߺ��˴ϴ�","�˸�",JOptionPane.ERROR_MESSAGE);
							break;
						}
					}
					bufReaderIDcheck.close();
					if(sameid==0) {
						JOptionPane.showMessageDialog(null,"��밡���� ���̵��Դϴ�","�˸�",JOptionPane.INFORMATION_MESSAGE);
					}
				}catch(FileNotFoundException b) {
				}catch(IOException b) {
				}
				}
			}
		});
		
		idcheck.setBounds(376, 130, 85, 46);
		contentPane.add(idcheck);
		

	}
}
