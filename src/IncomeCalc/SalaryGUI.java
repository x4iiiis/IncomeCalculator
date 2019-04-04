package IncomeCalc;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class SalaryGUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SalaryGUI frame = new SalaryGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public SalaryGUI() throws FileNotFoundException {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		//Create / get the Finance Manager
		FinanceManager FM = FinanceManager.getInstance();
		
		//Decimal formatter
		DecimalFormat df = new DecimalFormat("#.##");
		
		//Date Formatter
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		
		
		
		JLabel lblNewLabel = new JLabel("Current Recorded Salary: £");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(66, 28, 166, 16);
		contentPane.add(lblNewLabel);
		
		JTextField txtCurrentSalary = new JTextField();
		txtCurrentSalary.setText("[insert salary]");
		txtCurrentSalary.setEditable(false);
		txtCurrentSalary.setEnabled(false);
		txtCurrentSalary.setBounds(234, 23, 130, 26);
		contentPane.add(txtCurrentSalary);
		txtCurrentSalary.setColumns(10);
		
		JLabel lblNewSalary = new JLabel("New Salary: £");
		lblNewSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewSalary.setBounds(76, 71, 156, 16);
		contentPane.add(lblNewSalary);
		
		JTextField txtNewSalary = new JTextField();
		txtNewSalary.setColumns(10);
		txtNewSalary.setBounds(234, 66, 130, 26);
		contentPane.add(txtNewSalary);
		
		txtCurrentSalary.setText(df.format(FM.GetIncome().Salary));
		
		JLabel lblLastPayday = new JLabel("Last Payday:");
		lblLastPayday.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastPayday.setBounds(76, 131, 156, 16);
		contentPane.add(lblLastPayday);
		
		JTextField txtLastPayday = new JTextField();
		txtLastPayday.setText(dateFormatter.format(FM.GetIncome().LastPayday));
		txtLastPayday.setColumns(10);
		txtLastPayday.setBounds(234, 126, 130, 26);
		contentPane.add(txtLastPayday);
		
		JLabel lblNewLabel_1 = new JLabel("DD/MM/YYYY");
		lblNewLabel_1.setFont(new Font("Dialog", Font.PLAIN, 10));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(244, 152, 109, 16);
		contentPane.add(lblNewLabel_1);
		
		JButton btnAlterIncome = new JButton("Update Income");
		btnAlterIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Update Income.csv
				try (PrintWriter writer = new PrintWriter(new File("Income.csv"))) {
					StringBuilder sb = new StringBuilder();
					sb.append("Salary,");
					sb.append("Last Payday,");
					sb.append("Months Between Wages,");
					sb.append("Weeks Between Wages,");
					sb.append("\n");

					sb.append(txtNewSalary.getText() + ",");
					sb.append(txtLastPayday.getText() + ",");
					sb.append("1,");
					sb.append(" ,");
					
					writer.write(sb.toString());
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				System.out.println("Income.csv updated\n");
				System.out.println("Your new salary is £" + df.format(FM.GetIncome().Salary) + " and was last paid on " + 
										dateFormatter.format(FM.GetIncome().LastPayday) + ".");
				System.out.println("Your next wage will be due on " + dateFormatter.format(FM.GetIncome().NextPayday) + ".");
				
				try {
					FM.UpdateIncome();
				} 
				catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				CloseJframe();
			}
		});
		btnAlterIncome.setBounds(159, 219, 152, 29);
		contentPane.add(btnAlterIncome);

	}
	
	void CloseJframe(){
	    super.dispose();
	}
}