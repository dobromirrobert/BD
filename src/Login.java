import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.*;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Cursor;
import javax.swing.JSeparator;

public class Login {

	private JFrame frame;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 501, 306);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Yu Gothic", Font.BOLD, 18));
		lblUsername.setBounds(86, 72, 96, 50);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Yu Gothic", Font.BOLD, 18));
		lblPassword.setBounds(86, 118, 96, 50);
		frame.getContentPane().add(lblPassword);
		
		username = new JTextField();
		username.setBounds(194, 87, 177, 22);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public  void actionPerformed(ActionEvent arg0) {
				String uname = username.getText();
				String psd = password.getText();
				BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "");
				ArrayList<String[]> res = db.doQuery("SELECT * FROM utilizator WHERE username = '" +uname+ "' AND password = '" +psd+ "';");
				int count = 0;
				if(res != null) 	
				count = res.size();
				
				if (count == 1) {
					
					int nivel = Integer.parseInt((res.get(0))[3]);
					if( nivel == 0) {
						JOptionPane.showMessageDialog(frame,"You are sucessfully logined in the human resources department","True",JOptionPane.INFORMATION_MESSAGE);
						MeniuPrincipal0 meniu = new MeniuPrincipal0();
						meniu.main(null);
						frame.setVisible(false);
					}
					else if(nivel == 1) {
						JOptionPane.showMessageDialog(frame,"You are sucessfully logined in the economic department ","True",JOptionPane.INFORMATION_MESSAGE);
						MeniuPrincipal1 meniu = new MeniuPrincipal1();
						meniu.main(null);
						frame.setVisible(false);
					}
					if(nivel == 2) {
						JOptionPane.showMessageDialog(frame,"You are sucessfully logined in the medical department","True",JOptionPane.INFORMATION_MESSAGE);
						MeniuPrincipal2 meniu = new MeniuPrincipal2();
						meniu.main(null);
						frame.setVisible(false);
					}
					
				}
				else if(count == 0)
				{
					JOptionPane.showMessageDialog(frame,"Invalid password or username","Failure",JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(frame,"EROR");
				}
			}
		});
		btnLogin.setBounds(194, 194, 97, 25);
		frame.getContentPane().add(btnLogin);
		
		password = new JPasswordField();
		password.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		password.setBounds(194, 133, 177, 22);
		frame.getContentPane().add(password);
		
		JLabel lblPoliclinic = new JLabel("Polyclinic login system");
		lblPoliclinic.setFont(new Font("Mongolian Baiti", Font.PLAIN, 18));
		lblPoliclinic.setBounds(161, 40, 187, 19);
		frame.getContentPane().add(lblPoliclinic);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(26, 72, 423, 2);
		frame.getContentPane().add(separator);
	}

}
