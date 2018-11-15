package guitool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LogIn extends JFrame {
	public LogIn() {
		JPanel panel2 = new JPanel();
		JLabel label2 = new JLabel("ID : ");
		JLabel pswlabel = new JLabel("Password : ");
		JTextField txtID = new JTextField(10);// password -> ***** display
		JPasswordField txtPass = new JPasswordField(10);
		JButton logBtn = new JButton("Log In");
		//
		panel2.add(label2);
		panel2.add(txtID);
		panel2.add(pswlabel);
		panel2.add(txtPass);
		panel2.add(logBtn);

		logBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = "oowhat";
				String pw = "1234";

				if (id.equals(txtID.getText()) && pw.equals(txtPass.getText())) {
					JOptionPane.showMessageDialog(null, "LogIn Successfully");
				} else {
					JOptionPane.showMessageDialog(null, "Not Corrected");
				}
			}
		});
		//
		add(panel2);
		//
		setVisible(true);
		setSize(600, 400);
		setLocationRelativeTo(null);
		setResizable(false);
		// setDefaultCloseOperation(LogIn.EXIT_ON_CLOSE);
		//

	}

}
