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

//import javafx.stage.Screen;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;


public class musicplayer extends JFrame {

   private JPanel contentPane;
   private JTable charttable;
   private JTable playlisttable;
   private JTable searchtable;
   private JTextField find;
   private JTable table;
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
      ImageIcon mainback=new ImageIcon(musicplayer.class.getResource("../picture/mainback.PNG"));
      contentPane = new JPanel() {
            public void paintComponent(Graphics g) {
                g.drawImage(mainback.getImage(), 0, 0,1280,700, null);
                setOpaque(false); //그림을 표시하게 설정,투명하게 조절
                super.paintComponent(g);
            }
       };
       
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(0, 0, 1280,750);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      contentPane.setLayout(null);
      contentPane.setBackground(Color.LIGHT_GRAY);
      setTitle("Calamansi Player");
      setContentPane(contentPane);
      
      JButton logout = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/logout.PNG"))); // logout button
      logout.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent arg0) { // if user click logout button -> return to the login window
            login framelog = new login();
            framelog.setVisible(true);
            dispose();
         }
      });
      
      JPanel screen3 = new JPanel(); // include searchtable, find textfield, findicon button
      screen3.setLayout(null);
      screen3.setBounds(14, 209, 799, 480);
      contentPane.add(screen3);
      
      searchtable = new JTable(); // JTable for printing search list
      searchtable.setBounds(14, 86, 771, 382);
      screen3.add(searchtable);
      
      JButton findicon = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/find.PNG"))); // findicon button
      findicon.setLayout(null);
      findicon.setBounds(654,25,52,44);
      screen3.add(findicon);
      findicon.setToolTipText("\uAC80\uC0C9");
      
      find = new JTextField(); // Text field -> users will write what they want to search
      find.setBounds(64, 27, 568, 36);
      screen3.add(find);
      find.setLayout(null);
      find.setColumns(10);
      
      findicon.addActionListener(new ActionListener() { // Pressing the findicon button without writing anything to a text field -> print warning
         public void actionPerformed(ActionEvent arg0) {
            if((find.getText()).equals("")) { 
               JOptionPane.showMessageDialog(null,"please enter your content.","Warning!",JOptionPane.ERROR_MESSAGE); 
            }
         }
      });
      
      JPanel screen1 = new JPanel(); // include charttable
      screen1.setLayout(null);
      screen1.setBounds(14, 209, 799, 480);
      contentPane.add(screen1);
      
      charttable = new JTable(); // JTable for printing music chart
      charttable.setBounds(31, 24, 740, 430 );
      screen1.add(charttable);
      
      JPanel screen2 = new JPanel(); // include playlisttable
      screen2.setLayout(null);
      screen2.setBounds(14, 209, 799, 480);
      contentPane.add(screen2);
      
      playlisttable = new JTable(); // JTable for printing playlist
      playlisttable.setBounds(31, 24, 740, 430);
      screen2.add(playlisttable);
      
      logout.setToolTipText("\uB85C\uADF8\uC544\uC6C3");
      logout.setBounds(771, 0, 42, 41);
      logout.setBorderPainted(false);
      logout.setFocusPainted(false);
      contentPane.add(logout);
            
      JButton sound = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/sound.PNG"))); // sound button
      sound.setBounds(1112, 497, 53, 47);
      contentPane.add(sound);
      
      JButton fastf = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/fastf.PNG"))); // forward button
      fastf.setBounds(1133, 588, 64, 70);
      contentPane.add(fastf);
      
      JButton fastb = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/fastb.PNG"))); // back button
      fastb.setBounds(897, 588, 64, 70);
      contentPane.add(fastb);
      
      JButton play = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/play.PNG"))); // play button
      play.setToolTipText("\uC7AC\uC0DD");
      play.setBounds(983, 564, 127, 125);
      //play.setBorderPainted(false);
      play.setFocusPainted(false);
      play.setContentAreaFilled(false);
      contentPane.add(play);
      
      JButton shuffle = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/shuffle.PNG"))); // shuffle button
      shuffle.setBounds(1024, 497, 53, 47);
      contentPane.add(shuffle);
      
      JButton allplay = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/allplay.PNG")));
      allplay.setBounds(937, 496, 53, 47);
      contentPane.add(allplay);
      
      JButton search = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/search.PNG"))); // search button
      search.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { // if user click search button -> The screen3 containing the searchtable is shown.
            screen1.setVisible(false);
            screen2.setVisible(false);
            screen3.setVisible(true);
         }
      });
      search.setToolTipText("search");
      search.setBounds(461, 54, 94, 30);
      contentPane.add(search);
      
      JButton playlist = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/playlist.PNG"))); // playlist button
      playlist.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) { // if user click playlist button -> The screen2 containing the playlisttable is shown.
            screen1.setVisible(false);
            screen2.setVisible(true);
            screen3.setVisible(false);
         }
      });
      playlist.setToolTipText("Favorite Music");
      playlist.setBounds(264, 54, 110, 30);
      contentPane.add(playlist);
      
      JButton musicchart = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/musicchart.PNG"))); // musicchart button 
      musicchart.setToolTipText("music chart");
      musicchart.setOpaque(true);
      musicchart.addActionListener(new ActionListener() { // if user click musicchart button -> The screen1 containing the charttable is shown.
         public void actionPerformed(ActionEvent arg0) {
            screen1.setVisible(true);
            screen2.setVisible(false);
            screen3.setVisible(false);
         }
      });
      musicchart.setBounds(33, 54, 143, 30);
      contentPane.add(musicchart);
      
      JLabel cover= new JLabel(new ImageIcon(musicplayer.class.getResource("../picture/coverex.PNG"))); // print music album pictures
      cover.setBounds(871, 93, 363, 356);
      contentPane.add(cover);
      
      JLabel songname = new JLabel("SOLO"); // Print music title
      songname.setForeground(Color.BLACK);
      songname.setFont(new Font("돋움", Font.BOLD, 30));
      songname.setBounds(1013, 23, 103, 35);
      contentPane.add(songname);
      
      JLabel singer = new JLabel("Jenney"); // Print singer
      singer.setForeground(Color.BLACK);
      singer.setFont(new Font("돋움", Font.BOLD, 20));
      singer.setBounds(1023, 57, 73, 27);
      contentPane.add(singer);
   }
}