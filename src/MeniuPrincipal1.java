import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MeniuPrincipal1 {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeniuPrincipal1 window = new MeniuPrincipal1();
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
	public MeniuPrincipal1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 904, 754);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "silver250");
		
		JLabel lblMeniuOperatiiFinanciar = new JLabel("Meniu operatii financiar contabile");
		lblMeniuOperatiiFinanciar.setFont(new Font("Times New Roman", Font.PLAIN, 43));
		lblMeniuOperatiiFinanciar.setBounds(146, 35, 584, 56);
		frame.getContentPane().add(lblMeniuOperatiiFinanciar);
		
		JButton btnProfitPoliclinici = new JButton("Profit policlinici");
		btnProfitPoliclinici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "silver250");
					PreparedStatement stmt = db.getDbHandle().prepareStatement("SELECT id, denumire, Venituri FROM policlinica;");
					ResultSet rst = stmt.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rst));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnProfitPoliclinici.setBounds(419, 150, 173, 23);
		frame.getContentPane().add(btnProfitPoliclinici);
		
		JButton btnNewButton = new JButton("Profit medici");
		btnNewButton.setBounds(641, 150, 173, 23);
		frame.getContentPane().add(btnNewButton);
		
		table = new JTable();
		table.setBounds(287, 217, 591, 385);
		frame.getContentPane().add(table);
	}
}
