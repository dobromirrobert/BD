import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MeniuPrincipal0 {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeniuPrincipal0 window = new MeniuPrincipal0();
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
	public MeniuPrincipal0() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 1262, 836);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMeniu = new JLabel("Meniu resurse umane");
		lblMeniu.setFont(new Font("Times New Roman", Font.PLAIN, 43));
		lblMeniu.setBounds(454, 0, 370, 111);
		frame.getContentPane().add(lblMeniu);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 90, 1220, 2);
		frame.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(294, 272, 603, 327);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnLoadaPanel = new JButton("Load");
		btnLoadaPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "");
					PreparedStatement pst = db.getDbHandle().prepareStatement("SELECT * FROM angajat;");
					ResultSet rst = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rst));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
			}
		});
		btnLoadaPanel.setBounds(368, 162, 97, 25);
		frame.getContentPane().add(btnLoadaPanel);
	}
}
