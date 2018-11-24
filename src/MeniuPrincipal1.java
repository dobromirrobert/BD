import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;

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
		
		JLabel lblMeniuOperatiiFinanciar = new JLabel("Meniu operatii financiar contabile");
		lblMeniuOperatiiFinanciar.setFont(new Font("Times New Roman", Font.PLAIN, 43));
		lblMeniuOperatiiFinanciar.setBounds(146, 35, 584, 56);
		frame.getContentPane().add(lblMeniuOperatiiFinanciar);
		
		JButton btnProfitPoliclinici = new JButton("Profit policlinici");
		btnProfitPoliclinici.setBounds(419, 150, 101, 23);
		frame.getContentPane().add(btnProfitPoliclinici);
		
		JButton btnNewButton = new JButton("Profit medici");
		btnNewButton.setBounds(641, 150, 101, 23);
		frame.getContentPane().add(btnNewButton);
		
		table = new JTable();
		table.setBounds(287, 217, 591, 385);
		frame.getContentPane().add(table);
	}
}
