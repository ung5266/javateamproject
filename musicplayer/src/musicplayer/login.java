package musicplayer;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField idtext;
	private JTextField pintext;
	private JButton loginbtn;
	ImageIcon loginback;


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
		loginback=new ImageIcon(musicplayer.class.getResource("../picture/loginscreen.PNG"));
		contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(loginback.getImage(), 0, 0,500,500, null);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
        };
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 500, 530);
		//contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		idtext = new JTextField();
		idtext.setBounds(53, 223, 284, 66);
		//idtext.setBackground(new Color(0,0,0,0));
		contentPane.add(idtext);
		idtext.setColumns(10);
		
		pintext = new JTextField();
		pintext.setBounds(53, 288, 284, 59);
		contentPane.add(pintext);
		pintext.setColumns(10);
/*		pintext.selectAll();   //비밀번호의 입력된 모든 문자열 선택가능
		pintext.setEchoChar('*');  //비밀번호의 입력을 *모양으로 표시되도록 설정
*/
		
		loginbtn = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/loginbtn.PNG")));
		loginbtn.addActionListener(new ActionListener() { //로그인 버튼을 눌렀을 때
			public void actionPerformed(ActionEvent e) {
				
				String input="";
				int count=0;
				int index=0;
				if(idtext.getText().equals("")) {//아이디 텍스트가 비어있을 때
					JOptionPane.showMessageDialog(null,"아이디를 입력해주세요","알림",JOptionPane.ERROR_MESSAGE);
				}
				else if(pintext.getText().equals("")) {//비밀번호 텍스트가 비어있을 때
					JOptionPane.showMessageDialog(null,"비밀번호를 입력해주세요","알림",JOptionPane.ERROR_MESSAGE);
				}
				else {//둘 다 입력이 되어있을 때
					
					//해당 아이디 가입여부 확인
					try {
						FileReader fileID=new FileReader("C:\\Users\\정현정\\eclipse-workspace\\musicplayer\\src\\TextFile\\ID.txt");
						BufferedReader bufReaderID=new BufferedReader(fileID);
			            while ((input = bufReaderID.readLine()) != null)
			            {
			                if (input.equals(idtext.getText()))
			                { count++; break; }
			                index++;
			            }
			            bufReaderID.close();
					}catch(FileNotFoundException a){
					}catch(IOException a){
					}
					
					//해당 비밀번호 가입 여부
					try {
						FileReader filePW=new FileReader("C:\\Users\\정현정\\eclipse-workspace\\musicplayer\\src\\TextFile\\PW.txt");
						BufferedReader bufReaderPW=new BufferedReader(filePW);
			            while ((input = bufReaderPW.readLine()) != null)
			            {
			                if (input.equals(pintext.getText()))
			                { count++; break; }
			                index--;
			            }
			            bufReaderPW.close();
					}catch(FileNotFoundException a){
					}catch(IOException a){
					}
				
					if((count==2) && (index==0)) {
						musicplayer frame = new musicplayer();
						frame.setVisible(true);
						idtext.setText("");
						pintext.setText("");
						dispose();
					}
					else {
						JOptionPane.showMessageDialog(null,"아이디 혹은 비밀번호가 틀렸습니다","알림",JOptionPane.ERROR_MESSAGE);
						idtext.setText("");
						pintext.setText("");
					}
				}

			}
		});
		loginbtn.setBounds(337, 223, 111, 124);
		contentPane.add(loginbtn);
		
		JButton sign = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/sign.PNG")));
		sign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sign framesign = new sign();
				framesign.setVisible(true);
				dispose();
			}
		});
		sign.setBounds(53, 377, 99, 42);
		//sign.setBorderPainted(false);
		sign.setFocusPainted(false);
		contentPane.add(sign);
		
		JButton exit = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/exit.PNG")));
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		exit.setBounds(384, 377, 64, 42);
		contentPane.add(exit);
		
		

	}
}
