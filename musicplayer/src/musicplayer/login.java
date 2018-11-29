package musicplayer;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
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
      
      pintext = new JPasswordField();
      pintext.setBounds(53, 288, 284, 59);
      contentPane.add(pintext);
      pintext.setColumns(10);
      
      loginbtn = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/loginbtn.PNG")));
      loginbtn.addActionListener(new ActionListener() { //로그인 버튼을 눌렀을 때
         public void actionPerformed(ActionEvent e) {
            
            String input="";
            int count=0;
            int index=0;
            if(idtext.getText().equals("")) {//아이디 텍스트가 비어있을 때
               JOptionPane.showMessageDialog(null,"Please enter your ID","Warning!",JOptionPane.ERROR_MESSAGE);
            }
            else if(pintext.getText().equals("")) {//비밀번호 텍스트가 비어있을 때
               JOptionPane.showMessageDialog(null,"Please enter your Password","Warning!",JOptionPane.ERROR_MESSAGE);
            }
            else {//둘 다 입력이 되어있을 때
               try{
                  String fileID = "jdbc:ucanaccess://C:\\Users\\정수빈\\Desktop\\Java Team Project\\javateamproject\\musicplayer\\bin\\database\\signdata.mdb";
                  Connection conn = DriverManager.getConnection(fileID);
                      Statement st = conn.createStatement();
                      ResultSet rs = st.executeQuery("SELECT ID from 테이블1");

                         while(rs.next()){
                            String ID= rs.getString("ID");                       
                          if((idtext.getText()).equals(ID)) {
                               count++; break; //아이디 중복될 때 
                            }
                          index++;
                         }
                         rs.close();//데이터베이스 연결 끊기
                        conn.close();
               }catch (SQLException e1) {System.out.println(e1);}

               
               try{
                  String fileID = "jdbc:ucanaccess://C:\\Users\\정수빈\\Desktop\\Java Team Project\\javateamproject\\musicplayer\\bin\\database\\signdata.mdb";
                  Connection conn = DriverManager.getConnection(fileID);
                      Statement st = conn.createStatement();
                      ResultSet rs = st.executeQuery("SELECT Password from 테이블1");

                         while(rs.next()){
                            String Password= rs.getString("Password");                       
                          if((pintext.getText()).equals(Password)) {
                               count++; break; //아이디 중복될 때 
                            }
                          index--;                        
                          }
                         rs.close();//데이터베이스 연결 끊기
                        conn.close();
               }catch (SQLException e1) {System.out.println(e1);}
               
                if((count==2) && (index==0)) {
                        musicplayer frame = new musicplayer();
                        frame.setVisible(true);
                        idtext.setText("");
                        pintext.setText("");
                        dispose();
                     }
                     else {
                        JOptionPane.showMessageDialog(null,"The ID or password is incorrect.","Warning!",JOptionPane.ERROR_MESSAGE);
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