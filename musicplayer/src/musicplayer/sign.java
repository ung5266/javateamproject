package musicplayer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class sign extends JFrame {

	private JPanel contentPane;
	private JTextField mypin;
	private JTextField pincheck;
	private JTextField myid;
	private String Password;
	private String ID;
	int count=0;
	int idcheckok=0;
	

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) { //메인 함수
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
	public void getForList(String ID,String Password) {
        this.ID = ID;
        this.Password = Password;  
     }

public void Insert() {
     try {
        
     String dbFileSpec = "jdbc:ucanaccess://C:\\Users\\정현정\\eclipse-workspace\\musicplayer\\src\\database\\signdata.mdb";
       Connection conn = DriverManager.getConnection(dbFileSpec);
       PreparedStatement pstmt;
       String insertQuery = "insert into 테이블1(ID,Password) values (?,?)";
       pstmt = conn.prepareStatement(insertQuery);

       pstmt.setString(1, ID);
       pstmt.setString(2, Password);       

       int cnt = pstmt.executeUpdate();
       if(cnt==1) System.out.println("Insert Success~~");
       else System.out.println("Insert Failure");
  }catch(SQLException e) {System.out.println(e);}}

	public sign() {
		//sign 배경
		ImageIcon signback=new ImageIcon(musicplayer.class.getResource("../picture/signscreen.PNG"));
		contentPane = new JPanel() {
	         public void paintComponent(Graphics g) {
	             g.drawImage(signback.getImage(), 0, 0,500,500, null);
	             setOpaque(false); //그림을 표시하게 설정,투명하게 조절
	             super.paintComponent(g);
	         }
	    };

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(400, 100, 500, 530);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setTitle("회원가입");
		setContentPane(contentPane);
		
		myid = new JTextField();
		myid.setBounds(182, 130, 194, 46);
		contentPane.add(myid);
		myid.setColumns(10);
		
		mypin = new JPasswordField();
		mypin.setBounds(182, 176, 279, 51);
		contentPane.add(mypin);
		mypin.setColumns(10);
		
		pincheck = new JPasswordField();
		pincheck.setBounds(182, 227, 279, 46);
		contentPane.add(pincheck);
		pincheck.setColumns(10);
		
		ButtonGroup group=new ButtonGroup();
		
		JRadioButton woman = new JRadioButton();
		woman.setFont(new Font("굴림", Font.BOLD, 15));
		woman.setBackground(Color.WHITE);
		woman.setBounds(261, 279, 50, 36);
		group.add(woman);
		contentPane.add(woman);
		
		JRadioButton man = new JRadioButton();
		man.setFont(new Font("굴림", Font.BOLD, 15));
		man.setBackground(Color.WHITE);
		man.setBounds(397, 279, 50, 36);
		group.add(man);
		contentPane.add(man);
		
		
		
		
		JTextField call= new JTextField();
		call.setBounds(182, 321, 279, 46);
		contentPane.add(call);
		call.setColumns(10);
		
		JButton signok = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/ok.PNG")));
	      signok.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent arg0) {
	             if(myid.getText().equals("")||mypin.getText().equals("")||call.getText().equals("")||pincheck.getText().equals("")||(woman.isSelected()   != true)&&(man.isSelected()!= true)) {
	                 JOptionPane.showMessageDialog(null,"입력하지 않은 정보가 있습니다.","알림",JOptionPane.ERROR_MESSAGE);
	             }
	             else {
		              if(idcheckok!=1) {
		            	 JOptionPane.showMessageDialog(null,"아이디 중복 확인을 하지 않았습니다.","알림",JOptionPane.ERROR_MESSAGE);
		             }
		             else if(!(mypin.getText().equals(pincheck.getText()))) {
		            	 JOptionPane.showMessageDialog(null,"비밀번호가 일치하지 않습니다.","알림",JOptionPane.ERROR_MESSAGE);
		            	 mypin.setText("");
		            	 pincheck.setText("");
		             }
		             else {
		                  ID=new String(myid.getText());
		                  Password=new String(mypin.getText());
		                  getForList(ID,Password);
		                  Insert();
		                  
		                  login framelog = new login();
		                  framelog.setVisible(true);
		                  dispose();   
		             }
	             }
	         }
	      });
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
		
		//아이디 중복확인 버튼 눌렀을 때
		JButton idcheck = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/idcheck.PNG")));
		idcheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			     if((myid.getText().equals(""))) {//빈칸일 때
			        	JOptionPane.showMessageDialog(null,"아이디를 입력해주세요","알림",JOptionPane.ERROR_MESSAGE);
			     }
			     else {//빈칸이 아닐때
			      try{
			          String dbFile = "jdbc:ucanaccess://C:\\Users\\정현정\\eclipse-workspace\\musicplayer\\src\\database\\signdata.mdb";
			          Connection conn = DriverManager.getConnection(dbFile);
			          Statement st = conn.createStatement();
			          ResultSet rs = st.executeQuery("SELECT ID from 테이블1");

			          while(rs.next()){
			             String ID= rs.getString("ID");
					     if((myid.getText()).equals(ID)) {
			            	 count++; //아이디 중복될 때 
			             }
			          }
		            if(count>0) {
		            	count=0;
		            	JOptionPane.showMessageDialog(null,"아이디가 중복됩니다","알림",JOptionPane.ERROR_MESSAGE);
		            	myid.setText("");
		            }
			        else if(count==0){
			        	JOptionPane.showMessageDialog(null,"사용가능한 아이디입니다","알림",JOptionPane.ERROR_MESSAGE);
			        	idcheckok=1;
			        }

			        rs.close();//데이터베이스 연결 끊기
			        conn.close();
			    }
			   catch (SQLException e) {System.out.println(e);}
			   }
			}
		});
		
		idcheck.setBounds(376, 130, 85, 46);
		contentPane.add(idcheck);
		

	}
}
