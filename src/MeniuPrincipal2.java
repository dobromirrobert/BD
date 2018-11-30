import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JDesktopPane;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class MeniuPrincipal2 {

	private JFrame frame;
	private JTextField textFieldID;
	private JTextField textField;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MeniuPrincipal2 window = new MeniuPrincipal2();
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
	public MeniuPrincipal2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 16));
		frame.setBounds(100, 100, 904, 754);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblMeniu = new JLabel("Meniu gestiunea activitatilor operationale");
		lblMeniu.setFont(new Font("Times New Roman", Font.PLAIN, 43));
		lblMeniu.setBounds(83, 29, 723, 87);
		frame.getContentPane().add(lblMeniu);
		
		JPanel panel = new JPanel();
		panel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Investigatii pacient", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(12, 242, 582, 452);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 13, 558, 426);
		panel.add(scrollPane);
		
		JTextPane textInvestigatii = new JTextPane();
		textInvestigatii.setFont(new Font("Tahoma", Font.PLAIN, 17));
		scrollPane.setViewportView(textInvestigatii);
		
		JTextPane textPane = new JTextPane();
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblId.setBounds(215, 177, 68, 25);
		frame.getContentPane().add(lblId);
		
		textFieldID = new JTextField();
		textFieldID.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try {
					BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "");
					ArrayList<String[]> rst = db.doQuery("SELECT investigatii FROM Raport_medical WHERE Pacient_id = "+textFieldID.getText()+";");
					
					textInvestigatii.setText(rst.get(0)[0].toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		textFieldID.setBounds(261, 181, 81, 22);
		frame.getContentPane().add(textFieldID);
		textFieldID.setColumns(10);
		
		JButton btnAnalize = new JButton("Adauga");
		btnAnalize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String selection = (String)comboBox.getSelectedItem();
					BD db = new BD("127.0.0.1", "3306", "policlinica", "root", "");
					ArrayList<String[]> rst = db.doQuery("SELECT investigatii FROM Raport_medical WHERE Pacient_id = "+textFieldID.getText()+";");
					
					String str = rst.get(0)[0].toString() + "\n" + selection + " = " + textField.getText();
					String sql = "update raport_medical set investigatii = " + "'" + str + "'" + ";";
				    PreparedStatement pst = db.getDbHandle().prepareStatement(sql);
				    pst.executeUpdate();
				    
				    rst = db.doQuery("SELECT investigatii FROM Raport_medical WHERE Pacient_id = "+textFieldID.getText()+";");
					
					textInvestigatii.setText(rst.get(0)[0].toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		btnAnalize.setBounds(685, 525, 121, 37);
		frame.getContentPane().add(btnAnalize);
		
		comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"NEW", "Acid uric", "Amilaze", "ASLO", "Bilirubina", "Calciu", "Colesterol", "Creatinina", "CT", "Eritrocite", "ECG", "Ecografie", "Fier", "FR", "GGT", "Glicemie", "HDL Colesterol", "Hemoglobina", "Hematocrit", "INR", "Kreatin Kinaza", "Leucocite", "LDL Colesterol", "Lipide", "Magneziu", "Potasiu", "Proteine", "PCR", "Rheuma Test", "RMN", "Radiografie", "Sideremie", "Sodiu", "TGP", "TGO", "Trombocite", "Trigliceride", "TSH", "TPHA", "Uree Serica", "VSH", "Zinc"}));
		comboBox.setBounds(648, 389, 182, 24);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(648, 449, 182, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 129, 862, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(617, 129, 1, 565);
		frame.getContentPane().add(separator_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Robert\\Downloads\\rsz_2logo31.png"));
		lblNewLabel.setBackground(Color.YELLOW);
		lblNewLabel.setBounds(697, 145, 133, 167);
		frame.getContentPane().add(lblNewLabel);
	}
}
