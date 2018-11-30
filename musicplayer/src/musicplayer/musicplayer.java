package musicplayer;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.reference.Tagger;

//import javafx.stage.Screen;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class musicplayer extends JFrame {

	private JPanel contentPane;
	private JScrollPane charttable;
	private JTable playlisttable;
	private JTable searchtable;
	private JTextField find;
	private JTable table;
	private JLabel cover, songname, singer, playTime, currentTime, currentLyric, nextLyric;
	private JButton allplay, shuffle;
	private JProgressBar seekBar;
	private Audio song;
	private int framePos, musicIndex = -1, curIndex = -1, repeatMod = 0, index = 0;
	private int[] randIndex;
	private MusicList musicList = new MusicList();
	private String[] header = { "곡명", "시간", "아티스트", "앨범" };
	private String[][] contents = musicList.getMusicTags(), lyric;
	private boolean playing = false, shuffleMod = false;
	private double volume = -37.5, cDuration, pDuration;
	private Timer t;

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
		ImageIcon mainback = new ImageIcon(musicplayer.class.getResource("../picture/mainback.PNG"));
		contentPane = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(mainback.getImage(), 0, 0, 1280, 700, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
			}
		};

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 750);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.LIGHT_GRAY);
		setTitle("Calamansi Player");
		setContentPane(contentPane);

		JButton logout = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/logout.PNG"))); // logout
																												// button
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

		JButton findicon = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/find.PNG"))); // findicon
																												// button
		findicon.setLayout(null);
		findicon.setBounds(654, 25, 52, 44);
		screen3.add(findicon);
		findicon.setToolTipText("\uAC80\uC0C9");

		find = new JTextField(); // Text field -> users will write what they want to search
		find.setBounds(64, 27, 568, 36);
		screen3.add(find);
		find.setLayout(null);
		find.setColumns(10);

		findicon.addActionListener(new ActionListener() { // Pressing the findicon button without writing anything to a
															// text field -> print warning
			public void actionPerformed(ActionEvent arg0) {
				if ((find.getText()).equals("")) {
					JOptionPane.showMessageDialog(null, "please enter your content.", "Warning!",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JPanel screen1 = new JPanel(); // include charttable
		screen1.setLayout(null);
		screen1.setBounds(14, 209, 799, 480);
		contentPane.add(screen1);

		/*charttable = new JTable(); // JTable for printing music chart
		charttable.setBounds(31, 24, 740, 430);*/
		
		JTable chart = new JTable(contents, header);
		chart.addMouseListener(new ChartEvent());
		charttable = new JScrollPane(chart);
		charttable.setBounds(31, 24, 740, 430);
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

		JButton sound = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/sound.PNG"))); // sound
																											// button
		sound.setBounds(1112, 497, 53, 47);
		contentPane.add(sound);

		JButton fastf = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/fastf.PNG"))); // forward
																											// button
		fastf.setBounds(1133, 588, 64, 70);
		// NextSong play button action
		fastf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (song != null) {
					song.stop();
					// ShuffleMod on
					if (shuffleMod) {
						index = (index + 1) % randIndex.length;
						musicIndex = randIndex[index];
						playSong(musicList.getMusicPath(randIndex[index]), volume, 0);
					}
					// ShuffleMod off
					else {
						curIndex = (curIndex + 1) % musicList.musicList.length;
						musicIndex = curIndex;
						playSong(musicList.getMusicPath(curIndex), volume, 0);
					}

				}
			}
		});
		contentPane.add(fastf);

		JButton fastb = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/fastb.PNG"))); // back
																											// button
		fastb.setBounds(897, 588, 64, 70);
		// PreviousSong play button action
		fastb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (song != null) {
					song.stop();
					// ShuffleMod on
					if (shuffleMod) {
						index = (index - 1 + randIndex.length) % randIndex.length;
						musicIndex = randIndex[index];
						playSong(musicList.getMusicPath(randIndex[index]), volume, 0);
					}
					// ShuffleMod off
					else {
						curIndex = (curIndex - 1 + musicList.musicList.length) % musicList.musicList.length;
						musicIndex = curIndex;
						playSong(musicList.getMusicPath(curIndex), volume, 0);
					}

				}
			}
		});
		contentPane.add(fastb);

		JButton play = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/play.PNG"))); // play button
		play.setToolTipText("\uC7AC\uC0DD");
		play.setBounds(983, 564, 127, 125);
		// play.setBorderPainted(false);
		play.setFocusPainted(false);
		play.setContentAreaFilled(false);
		// Play & Pause button Action
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Default (Not selected any song)
				if (musicIndex == -1)
					;
				// Play action
				else if (curIndex != musicIndex) {
					if (song != null)
						song.stop();
					playSong(musicList.getMusicPath(musicIndex), volume, 0);
					curIndex = musicIndex;
					playing = true;
					t.start();
				}
				// Resume action
				else if (!song.getIsRunning()) {
					song.playAfterPause(framePos);
					playing = true;
					t.start();
				}
				// Pause action
				else if (song.getIsRunning()) {
					framePos = song.getFramePosition();
					song.stop();
					playing = false;
					t.stop();
				}

			}

		});
		contentPane.add(play);

		JButton shuffle = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/shuffleoff.PNG"))); // shuffle
																												// button
		shuffle.setBounds(1024, 497, 53, 47);
		// Shuffle button action
		shuffle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ShuffleMod off
				if (shuffleMod) {
					shuffleMod = false;
					shuffle.setIcon(new ImageIcon(musicplayer.class.getResource("../picture/shuffleoff.png")));
				}
				// ShuffleMod on
				else {
					shuffleMod = true;
					index = 0;
					shuffle.setIcon(new ImageIcon(musicplayer.class.getResource("../picture/shuffle.png")));
					// Create random music list (De-duplication)
					Random r = new Random();
					randIndex = new int[musicList.musicList.length];
					for (int i = 0; i < musicList.musicList.length; i++) {
						if (i == 0 && song != null) {
							randIndex[0] = curIndex;
							continue;
						}
						randIndex[i] = r.nextInt(musicList.musicList.length);
						for (int j = 0; j < i; j++) {
							if (randIndex[i] == randIndex[j])
								i--;
						}
					}
				}
			}
		});
		contentPane.add(shuffle);

		JButton allplay = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/allplayoff.png")));
		allplay.setBounds(937, 496, 53, 47);
		// RepeatMod button action
		allplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// RepeatAll on
				if (repeatMod == 0) {
					repeatMod = 1;
					allplay.setIcon(new ImageIcon(musicplayer.class.getResource("../picture/allplay.png")));
				}
				// Repeat one song on
				else if (repeatMod == 1) {
					repeatMod = 2;
					allplay.setIcon(new ImageIcon(musicplayer.class.getResource("../picture/playone.png")));
				}
				// RepeatAll off
				else {
					repeatMod = 0;
					allplay.setIcon(new ImageIcon(musicplayer.class.getResource("../picture/allplayoff.png")));
				}
			}
		});
		contentPane.add(allplay);

		JButton search = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/search.PNG"))); // search
																												// button
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // if user click search button -> The screen3 containing the
															// searchtable is shown.
				screen1.setVisible(false);
				screen2.setVisible(false);
				screen3.setVisible(true);
			}
		});
		search.setToolTipText("search");
		search.setBounds(461, 54, 94, 30);
		contentPane.add(search);

		JButton playlist = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/playlist.PNG"))); // playlist
																													// button
		playlist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // if user click playlist button -> The screen2 containing the
															// playlisttable is shown.
				screen1.setVisible(false);
				screen2.setVisible(true);
				screen3.setVisible(false);
			}
		});
		playlist.setToolTipText("Favorite Music");
		playlist.setBounds(264, 54, 110, 30);
		contentPane.add(playlist);

		JButton musicchart = new JButton(new ImageIcon(musicplayer.class.getResource("../picture/musicchart.PNG"))); // musicchart
																														// button
		musicchart.setToolTipText("music chart");
		musicchart.setOpaque(true);
		musicchart.addActionListener(new ActionListener() { // if user click musicchart button -> The screen1 containing
															// the charttable is shown.
			public void actionPerformed(ActionEvent arg0) {
				screen1.setVisible(true);
				screen2.setVisible(false);
				screen3.setVisible(false);
			}
		});
		musicchart.setBounds(33, 54, 143, 30);
		contentPane.add(musicchart);

		// Volume slider
		JSlider slider = new JSlider(-100, 100, 0);
		slider.setBackground(Color.GRAY);
		slider.setBounds(1165, 512, 100, 15);
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider s = (JSlider) e.getSource();
				double val = (double) s.getValue();
				volume = val / 200 * 84 - 37.5;
				if (song != null)
					song.changeVolume(volume);
			}

		});
		contentPane.add(slider);

		cover = new JLabel(new ImageIcon(musicplayer.class.getResource("../picture/coverex.PNG"))); // print
																											// music
																											// album																						// pictures
		cover.setBounds(871, 93, 363, 356);
		contentPane.add(cover);

		songname = new JLabel("SOLO"); // Print music title
		songname.setForeground(Color.BLACK);
		songname.setFont(new Font("돋움", Font.BOLD, 30));
		songname.setHorizontalAlignment(JLabel.CENTER);
		songname.setBounds(871, 23, 360, 35);
		contentPane.add(songname);

		singer = new JLabel("Jenney"); // Print singer
		singer.setForeground(Color.BLACK);
		singer.setFont(new Font("돋움", Font.BOLD, 20));
		singer.setHorizontalAlignment(JLabel.CENTER);
		singer.setBounds(871, 57, 360, 27);
		
		contentPane.add(singer);
		
		
		seekBar = new JProgressBar();
		seekBar.setBounds(845, 460, 400, 10);
		seekBar.setValue(0);
		seekBar.addMouseListener(new SeekbarEvent());
		contentPane.add(seekBar);
		
		playTime = new JLabel("0:00");
		playTime.setBounds(1215, 470, 30, 20);
		contentPane.add(playTime);
		
		currentTime = new JLabel("0:00");
		currentTime.setBounds(845, 470, 30, 20);
		contentPane.add(currentTime);
		
		currentLyric = new JLabel("");
		currentLyric.setHorizontalAlignment(JLabel.CENTER);
		currentLyric.setForeground(Color.BLACK);
		currentLyric.setFont(new Font("돋움", Font.BOLD, 20));
		currentLyric.setBounds(80, 110, 565, 27);
		contentPane.add(currentLyric);
		
		nextLyric = new JLabel("");
		nextLyric.setHorizontalAlignment(JLabel.CENTER);
		nextLyric.setForeground(Color.GRAY);
		nextLyric.setFont(new Font("돋움", Font.BOLD, 20));
		nextLyric.setBounds(80, 145, 565, 27);
		contentPane.add(nextLyric);
		
		
		// TimeCheck for current playtime & seekbar
		t = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cDuration = (double)song.getFramePosition()/song.getFrameLength()*pDuration;
				double cSeek = cDuration/pDuration*100;
				currentTime.setText(String.format("%d:%02d%n", (int)cDuration/60, (int)cDuration%60));
				seekBar.setValue((int)cSeek);
				//When the song is over, play next song
				playNextSong();
				// Lyrics synchronized
				syncLyric();
				
			}
		});
	}

	private void playSong(String path, double volume, int framePos) {
		try {
			song = new Audio(path);
			AudioTag tag = new AudioTag(path);
			pDuration = tag.duration;
			songname.setText(tag.title);
			singer.setText(tag.artist);
			cover.setIcon(tag.img);
			playTime.setText(String.format("%d:%02d%n", (int) pDuration / 60, (int) pDuration % 60));
			lyric = tag.getLyric();

		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
		}

		song.play(volume, framePos);
	}
	private void playNextSong() {
		if(!song.getIsRunning()) {
			// repeatMod off
			if(repeatMod == 0) {
				if(shuffleMod) {
					if(index == randIndex.length) t.stop();
					else index++;
				}
				else {
					if(curIndex == musicList.musicList.length) t.stop();
					else curIndex++;
				}
			}
			// repeat one song
			else if(repeatMod == 2);
			
			// repeatMod on
			else if(repeatMod == 1) {
				if(shuffleMod) index = (index + 1) % randIndex.length;
				else curIndex = (curIndex + 1) % musicList.musicList.length;
			}
			if(shuffleMod) 
				if(t.isRunning()) playSong(musicList.getMusicPath(randIndex[index]), volume, 0);
			else 
				if(t.isRunning()) playSong(musicList.getMusicPath(curIndex), volume, 0);
		}
	}
	private void syncLyric() {
		if(lyric != null) {
			for(int i = lyric.length - 1; i >= 0 ; i-- ) {
				if(Double.parseDouble(lyric[i][0]) < cDuration) {
					currentLyric.setText(lyric[i][1]);
					if(i == lyric.length - 1) nextLyric.setText("");
					else nextLyric.setText(lyric[i+1][1]);
					break;
				}
			}
		}
	}
	private class ChartEvent extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			JTable jt = (JTable) e.getSource();
			musicIndex = jt.getSelectedRow();
		}
	}
	private class SeekbarEvent extends MouseAdapter {
		public void mouseClicked(MouseEvent e) {
			double prog = (double)e.getX()/400;
			cDuration = prog*pDuration;
			seekBar.setValue((int)(prog*100));
			song.playAfterPause((int)((double)prog*song.getFrameLength()));
		}
	}
}