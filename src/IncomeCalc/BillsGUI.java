package IncomeCalc;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.GridLayout;

public class BillsGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtBillName;
	private JTextField txtAmount;
	private JTextField txtMonthsBetween;
	private JTextField txtLastPayment;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BillsGUI frame = new BillsGUI();
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
	public BillsGUI() throws FileNotFoundException {
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
		
		
		
		
		JLabel lblNewLabel = new JLabel("Bill");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(54, 23, 50, 42);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewSalary = new JLabel("Amount");
		lblNewSalary.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewSalary.setBounds(144, 23, 73, 42);
		contentPane.add(lblNewSalary);
		
		JLabel lblLastPayday = new JLabel("<html><center>Months Between<br>Payments</center><html>");
		lblLastPayday.setVerticalAlignment(SwingConstants.TOP);
		lblLastPayday.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastPayday.setBounds(229, 23, 105, 42);
		contentPane.add(lblLastPayday);
		
		
		
		JLabel lblLastPayment = new JLabel("Last Payment ");
		lblLastPayment.setHorizontalAlignment(SwingConstants.CENTER);
		lblLastPayment.setBounds(335, 23, 99, 42);
		contentPane.add(lblLastPayment);
		
		/////////////
		JPanel panel = new JPanel();
		/////////////
		JTextField[] Names = new JTextField[FM.getBillList().size()];
		JTextField[] Amounts = new JTextField[FM.getBillList().size()];
		JTextField[] MonthsBetween = new JTextField[FM.getBillList().size()];
		JTextField[] LastPaydays = new JTextField[FM.getBillList().size()];
			
		for (int i = 0; i < FM.getBillList().size(); i++)
		{
			Names[i] = new JTextField(FM.getBillList().get(i).billName);
			Amounts[i] = new JTextField(df.format(FM.getBillList().get(i).billAmount));
			MonthsBetween[i] = new JTextField(Integer.toString(FM.getBillList().get(i).monthsBetweenPayments));
			LastPaydays[i] = new JTextField(dateFormatter.format(FM.getBillList().get(i).lastPayment));
		}
		
		int y = 61;
		for (int i = 0; i < Names.length; i++)
		{
			Names[i].setFont(new Font("Lucida Grande", Font.PLAIN, 7));
			Names[i].setBounds(160, y, 124, 26);
			panel.add(Names[i]);
			Names[i].setColumns(10);
			
			
			Amounts[i].setFont(new Font("Lucida Grande", Font.PLAIN, 7));
			Amounts[i].setBounds(144, y, 80, 26);
			panel.add(Amounts[i]);
			Amounts[i].setColumns(10);
			
			MonthsBetween[i].setFont(new Font("Lucida Grande", Font.PLAIN, 7));
			MonthsBetween[i].setBounds(252, y, 59, 26);
			panel.add(MonthsBetween[i]);
			MonthsBetween[i].setColumns(10);
			
			LastPaydays[i].setFont(new Font("Lucida Grande", Font.PLAIN, 7));
			LastPaydays[i].setBounds(335, y, 99, 26);
			panel.add(LastPaydays[i]);
			LastPaydays[i].setColumns(10);
			
			panel.validate();
			y+=25;
					
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(16, 61, 428, 300);
		contentPane.add(scrollPane);
		
		scrollPane.setViewportView(panel);
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setAutoscrolls(true);
		scrollPane.validate();
		
		txtBillName = new JTextField();
		txtBillName.setFont(new Font("Lucida Grande", Font.PLAIN, 7));
		txtBillName.setColumns(10);
		txtBillName.setBounds(26, 365, 124, 26);
		getContentPane().add(txtBillName);
		
		txtAmount = new JTextField();
		txtAmount.setFont(new Font("Lucida Grande", Font.PLAIN, 7));
		txtAmount.setColumns(10);
		txtAmount.setBounds(162, 365, 80, 26);
		getContentPane().add(txtAmount);
		
		txtMonthsBetween = new JTextField();
		txtMonthsBetween.setFont(new Font("Lucida Grande", Font.PLAIN, 7));
		txtMonthsBetween.setColumns(10);
		txtMonthsBetween.setBounds(253, 365, 59, 26);
		getContentPane().add(txtMonthsBetween);
		
		txtLastPayment = new JTextField();
		txtLastPayment.setFont(new Font("Lucida Grande", Font.PLAIN, 7));
		txtLastPayment.setColumns(10);
		txtLastPayment.setBounds(324, 365, 99, 26);
		getContentPane().add(txtLastPayment);
		
		panel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{txtBillName, txtAmount, txtMonthsBetween, txtLastPayment}));
		
		
		JButton btnUpdateBills = new JButton("Update Bills");
		btnUpdateBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Update BillsPrototype.csv
				try (PrintWriter writer = new PrintWriter(new File("BillsPrototype.csv"))) {
					StringBuilder sb = new StringBuilder();
			
					sb.append("Bill Name,");
					sb.append("Amount,");
					sb.append("Months Between Payments,");
					sb.append("Date of Last Payment,");
					sb.append("\n");
					sb.append(",");
					sb.append(",");
					sb.append("^,");
					sb.append("\n");
					sb.append(",");
					sb.append(",");
					sb.append("|____  option in gui for months/years; bi-annual will auto calculate to 24 months etc,");
					sb.append("\n");
					sb.append("\n");

					for(int i = 0; i < Names.length; i++)
					{
						sb.append(Names[i].getText() + ",");
						if(Names[i].getText().toString().equals("Heroku"))
						{
							//Reverse the Heroku conversion back to USD
							sb.append("$" + df.format(Double.parseDouble(Amounts[i].getText()) / CurrencyConverter.GetExchangeRate()) + ",");
						}
						else
						{
							sb.append(Amounts[i].getText() + ",");
						}
						sb.append(MonthsBetween[i].getText() + ",");
						sb.append(LastPaydays[i].getText() + "\n");
					}
					
					
					if(!(txtBillName.getText().isEmpty() || txtAmount.getText().isEmpty() || txtMonthsBetween.getText().isEmpty() || txtLastPayment.getText().isEmpty()))
					{
						//Validate and add new bill
						try {
							
							sb.append(txtBillName.getText() + ",");
							
							try{
								sb.append(df.format(Double.parseDouble(txtAmount.getText())) + ",");
							}
							catch (Exception AmountFail) {
								
								throw new IllegalArgumentException("Invalid Amount");
							}
							
							try{
								sb.append(Integer.parseInt(txtMonthsBetween.getText()) + ","); 
							}
							catch (Exception MonthsFail) {
								
								throw new IllegalArgumentException("Invalid Input for Number of Months");
							}
							
							try{
								sb.append(txtLastPayment.getText() + "\n");
							}
							catch (Exception DateFail) {
								
								throw new IllegalArgumentException("Invalid Payment Date");
							}
								
								writer.write(sb.toString());
						}
						catch(Exception ValidationError) {
							JOptionPane.showMessageDialog(null, ValidationError.getMessage(), "InfoBox: " + "Validation", JOptionPane.INFORMATION_MESSAGE);
						}
					}
					else
					{
						System.out.println("Empty field(s) brah");
					}
					
				} 
				catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				
				System.out.println("BillsPrototype.csv updated\n");
				
				try {
					FM.UpdateBills();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

				txtBillName.setText(null);
				txtAmount.setText(null);
				txtMonthsBetween.setText(null);
				txtLastPayment.setText(null);
				
				//CloseJframe();
			}
		});
		btnUpdateBills.setBounds(285, 400, 152, 29);
		contentPane.add(btnUpdateBills);
		
		setBounds(100, 100, 450, 460);
	}
	

	void CloseJframe(){
	    super.dispose();
	}
}