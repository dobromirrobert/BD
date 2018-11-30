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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class MeniuPrincipal1 {

	private JFrame frame;
	private JTable table;
	private JTextField textFieldSearch;
	private JComboBox comboBox;

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
		//BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "silver250");
		
		JLabel lblMeniuOperatiiFinanciar = new JLabel("Meniu operatii financiar contabile");
		lblMeniuOperatiiFinanciar.setFont(new Font("Times New Roman", Font.PLAIN, 43));
		lblMeniuOperatiiFinanciar.setBounds(146, 35, 584, 56);
		frame.getContentPane().add(lblMeniuOperatiiFinanciar);
		
		JButton btnProfitPoliclinici = new JButton("Profit policlinici");
		btnProfitPoliclinici.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "");
					PreparedStatement stmt = db.getDbHandle().prepareStatement("SELECT id, denumire, Venituri FROM policlinica;");
					ResultSet rst = stmt.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rst));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnProfitPoliclinici.setBounds(347, 182, 173, 23);
		frame.getContentPane().add(btnProfitPoliclinici);
		
		JButton btnNewButton = new JButton("Profit medici");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "");
					PreparedStatement stmt = db.getDbHandle().prepareStatement("SELECT nume, prenume, Venituri FROM angajat a INNER JOIN medic m ON a.id = m.angajat_id ORDER BY a.policlinica;");
					ResultSet rst = stmt.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rst));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(583, 182, 173, 23);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(123, 298, 629, 385);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					String selection = (String)comboBox.getSelectedItem();
					BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "");
					PreparedStatement stmt = db.getDbHandle().prepareStatement("SELECT nume, prenume, Venituri FROM angajat a INNER JOIN medic m ON a.id = m.angajat_id WHERE" +selection+ "=?;");
					stmt.setString(1, textFieldSearch.getText() );
					ResultSet rst = stmt.executeQuery();
					
					table.setModel(DbUtils.resultSetToTableModel(rst));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		textFieldSearch.setBounds(53, 215, 166, 20);
		frame.getContentPane().add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Nume", "Prenume"}));
		comboBox.setBounds(53, 151, 166, 20);
		frame.getContentPane().add(comboBox);
	}
}
