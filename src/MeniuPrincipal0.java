import java.awt.EventQueue;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


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
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JTabbedPane;
public class MeniuPrincipal0 {

	private JFrame frame;
	private JTable table_info;
	private JTextField txt_cnp;
	private JTextField txt_nume;
	private JTextField txt_prenume;
	private JTextField txt_telefon;
	private JTextField txt_adresa;
	private JTextField txt_salariu;
	private JTextField txt_nrcontract;
	private JTextField txt_iban;
	private JTextField txt_email;
	private JTextField txt_nrore;
	private JTextField txt_functia;
	private JTextField txt_data;
	private JTextField txt_id;

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
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 19));
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 1324, 1067);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 90, 1220, 2);
		frame.getContentPane().add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			
			}
		});
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane.setBounds(96, 340, 1042, 576);
		frame.getContentPane().add(scrollPane);
		
		table_info = new JTable();
		table_info.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e) {
				int i = table_info.getSelectedRow();
				TableModel model = table_info.getModel();
				txt_id.setText(model.getValueAt(i, 0).toString());
				txt_cnp.setText(model.getValueAt(i, 1).toString());
				txt_nume.setText(model.getValueAt(i, 2).toString());
				txt_prenume.setText(model.getValueAt(i, 3).toString());
				txt_adresa.setText(model.getValueAt(i, 4).toString());
				txt_telefon.setText(model.getValueAt(i, 5).toString());
				txt_email.setText(model.getValueAt(i, 6).toString());
				txt_iban.setText(model.getValueAt(i, 7).toString());
				txt_nrcontract.setText(model.getValueAt(i, 8).toString());
				txt_data.setText(model.getValueAt(i, 9).toString());
				txt_functia.setText(model.getValueAt(i, 10).toString());
				txt_salariu.setText(model.getValueAt(i, 11).toString());
				txt_nrore.setText(model.getValueAt(i, 12).toString());
			}
		});
		scrollPane.setViewportView(table_info);
		
		JButton btnLoadaPanel = new JButton("Load");
		btnLoadaPanel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "");
					PreparedStatement pst = db.getDbHandle().prepareStatement("SELECT * FROM angajat;");
					ResultSet rst = pst.executeQuery();
					table_info.setModel(DbUtils.resultSetToTableModel(rst));
				} catch (Exception e) {
					e.printStackTrace();
				}
			
			
			}
		});
		btnLoadaPanel.setBounds(1041, 929, 97, 25);
		frame.getContentPane().add(btnLoadaPanel);
		
		JLabel lblTabelAngajati = new JLabel("Tabel Angajati");
		lblTabelAngajati.setFont(new Font("Tahoma", Font.PLAIN, 31));
		lblTabelAngajati.setBounds(555, -19, 219, 140);
		frame.getContentPane().add(lblTabelAngajati);
		
		JButton save = new JButton("Save");
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "");
					String sql = "Insert into angajat (CNP,nume,prenume,adresa,Telefon,email,IBAN,Contract,functie,salariu,Ore,data) value(?,?,?,?,?,?,?,?,?,?,?,?)";
				    PreparedStatement pst = db.getDbHandle().prepareStatement(sql);
				    pst.setString(1, txt_cnp.getText());
				    pst.setString(2, txt_nume.getText());
				    pst.setString(3, txt_prenume.getText());
				    pst.setString(4, txt_adresa.getText());
				    pst.setString(5, txt_telefon.getText());
				    pst.setString(6, txt_email.getText());
				    pst.setString(7, txt_iban.getText());
				    pst.setString(8, txt_nrcontract.getText());
				    pst.setString(9, txt_functia.getText());
				    pst.setString(10, txt_salariu.getText());
				    pst.setString(11, txt_nrore.getText());
				    pst.setString(12, txt_data.getText());
				    pst.execute();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		save.setBounds(924, 929, 97, 25);
		frame.getContentPane().add(save);
		
		JLabel lblCnp = new JLabel("Cnp");
		lblCnp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCnp.setBounds(40, 105, 56, 16);
		frame.getContentPane().add(lblCnp);
		
		JLabel lblNume = new JLabel("Nume");
		lblNume.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNume.setBounds(40, 143, 56, 16);
		frame.getContentPane().add(lblNume);
		
		JLabel lblPrenume = new JLabel("Prenume");
		lblPrenume.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrenume.setBounds(40, 182, 73, 16);
		frame.getContentPane().add(lblPrenume);
		
		JLabel lblAdresa = new JLabel("Adresa");
		lblAdresa.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAdresa.setBounds(40, 267, 73, 18);
		frame.getContentPane().add(lblAdresa);
		
		JLabel lblTelefon = new JLabel("Telefon");
		lblTelefon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTelefon.setBounds(40, 223, 84, 16);
		frame.getContentPane().add(lblTelefon);
		
		JLabel lblNewLabel = new JLabel("IBAN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(294, 145, 56, 16);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEmail.setBounds(294, 105, 56, 16);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblNrcontract = new JLabel("Nrcontract");
		lblNrcontract.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNrcontract.setBounds(294, 176, 123, 28);
		frame.getContentPane().add(lblNrcontract);
		
		JLabel lblFunctia = new JLabel("Functia");
		lblFunctia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFunctia.setBounds(294, 268, 56, 16);
		frame.getContentPane().add(lblFunctia);
		
		JLabel lblNewLabel_1 = new JLabel("Salariu");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(40, 306, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNrore = new JLabel("NrOre");
		lblNrore.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNrore.setBounds(294, 225, 56, 16);
		frame.getContentPane().add(lblNrore);
		
		txt_cnp = new JTextField();
		txt_cnp.setBounds(116, 105, 116, 22);
		frame.getContentPane().add(txt_cnp);
		txt_cnp.setColumns(10);
		
		txt_nume = new JTextField();
		txt_nume.setBounds(116, 142, 116, 22);
		frame.getContentPane().add(txt_nume);
		txt_nume.setColumns(10);
		
		txt_prenume = new JTextField();
		txt_prenume.setBounds(116, 181, 116, 22);
		frame.getContentPane().add(txt_prenume);
		txt_prenume.setColumns(10);
		
		txt_telefon = new JTextField();
		txt_telefon.setBounds(116, 222, 116, 22);
		frame.getContentPane().add(txt_telefon);
		txt_telefon.setColumns(10);
		
		txt_adresa = new JTextField();
		txt_adresa.setBounds(116, 266, 116, 22);
		frame.getContentPane().add(txt_adresa);
		txt_adresa.setColumns(10);
		
		txt_salariu = new JTextField();
		txt_salariu.setBounds(116, 305, 116, 22);
		frame.getContentPane().add(txt_salariu);
		txt_salariu.setColumns(10);
		
		txt_nrcontract = new JTextField();
		txt_nrcontract.setBounds(390, 181, 116, 22);
		frame.getContentPane().add(txt_nrcontract);
		txt_nrcontract.setColumns(10);
		
		txt_iban = new JTextField();
		txt_iban.setBounds(390, 142, 116, 22);
		frame.getContentPane().add(txt_iban);
		txt_iban.setColumns(10);
		
		txt_email = new JTextField();
		txt_email.setBounds(390, 105, 116, 22);
		frame.getContentPane().add(txt_email);
		txt_email.setColumns(10);
		
		txt_nrore = new JTextField();
		txt_nrore.setBounds(390, 222, 116, 22);
		frame.getContentPane().add(txt_nrore);
		txt_nrore.setColumns(10);
		
		txt_functia = new JTextField();
		txt_functia.setBounds(390, 267, 116, 22);
		frame.getContentPane().add(txt_functia);
		txt_functia.setColumns(10);
		
		JLabel lblDataangajarii = new JLabel("DataAng");
		lblDataangajarii.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDataangajarii.setBounds(294, 297, 104, 30);
		frame.getContentPane().add(lblDataangajarii);
		
		txt_data = new JTextField();
		txt_data.setBounds(390, 305, 116, 22);
		frame.getContentPane().add(txt_data);
		txt_data.setColumns(10);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "");
				String sql = "Delete from angajat where id = ?";
			    PreparedStatement pst = db.getDbHandle().prepareStatement(sql);
			    pst.setString(1, txt_id.getText());
			    pst.executeUpdate();
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnDelete.setBounds(803, 929, 97, 25);
		frame.getContentPane().add(btnDelete);
		
		JLabel lblNewLabel_2 = new JLabel("ID\r\n");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(616, 931, 56, 16);
		frame.getContentPane().add(lblNewLabel_2);
		
		txt_id = new JTextField();
		txt_id.setBounds(656, 930, 116, 22);
		frame.getContentPane().add(txt_id);
		txt_id.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "");
					String sql = "update angajat set CNP = "+txt_cnp.getText()+", nume = '"+txt_nume.getText()+"', prenume = '"+txt_prenume.getText()+"', adresa = '"+txt_adresa.getText()+"',nrTelefon = '"+txt_telefon.getText()+"',email = '"+txt_email.getText()+"',IBAN= '"+txt_iban.getText()+"',nrContract = '"+txt_nrcontract.getText()+"',functia_detinuta = '"+txt_functia.getText()+"',salariu = '"+txt_salariu.getText()+"',nrOre = '"+txt_nrore.getText()+"',data_angajarii = '"+txt_data.getText()+"'where id = "+txt_id.getText()+";";
				    PreparedStatement pst = db.getDbHandle().prepareStatement(sql);
				    pst.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnUpdate.setBounds(518, 302, 97, 25);
		frame.getContentPane().add(btnUpdate);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 129, 64);
		frame.getContentPane().add(tabbedPane);
	}
}
