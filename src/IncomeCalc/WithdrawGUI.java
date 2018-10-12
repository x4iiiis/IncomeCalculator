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
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

public class WithdrawGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtDeposit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WithdrawGUI frame = new WithdrawGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WithdrawGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JLabel lblHowManyOf = new JLabel("How many of each coin or note do you wish to deposit?");
		lblHowManyOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblHowManyOf.setBounds(6, 6, 438, 16);
		contentPane.add(lblHowManyOf);
		
		txtDeposit = new JTextField();
		txtDeposit.setBounds(109, 270, 130, 26);
		contentPane.add(txtDeposit);
		txtDeposit.setColumns(10);
		
		
		
		JRadioButton radio100Pound = new JRadioButton("£100");
		radio100Pound.setVisible(false);
		radio100Pound.setBounds(180, 64, 69, 23);
		contentPane.add(radio100Pound);
		
		JRadioButton radio50Pound = new JRadioButton("£50");
		radio50Pound.setVisible(false);
		radio50Pound.setBounds(180, 87, 141, 23);
		contentPane.add(radio50Pound);
		
		JRadioButton radio20Pound = new JRadioButton("£20");
		radio20Pound.setVisible(false);
		radio20Pound.setBounds(180, 111, 141, 23);
		contentPane.add(radio20Pound);
		
		JRadioButton radio10Pound = new JRadioButton("£10");
		radio10Pound.setVisible(false);
		radio10Pound.setBounds(180, 134, 141, 23);
		contentPane.add(radio10Pound);
		
		JRadioButton radio5Pound = new JRadioButton("£5");
		radio5Pound.setVisible(false);
		radio5Pound.setBounds(180, 157, 141, 23);
		contentPane.add(radio5Pound);
		
		JRadioButton radio5Pence = new JRadioButton("5 pence");
		radio5Pence.setVisible(false);
		radio5Pence.setBounds(180, 179, 141, 23);
		contentPane.add(radio5Pence);
		
		JRadioButton radio2Pence = new JRadioButton("2 pence");
		radio2Pence.setVisible(false);
		radio2Pence.setBounds(180, 203, 141, 23);
		contentPane.add(radio2Pence);
		
		JRadioButton radio1Pence = new JRadioButton("1 pence");
		radio1Pence.setVisible(false);
		radio1Pence.setBounds(180, 224, 141, 23);
		contentPane.add(radio1Pence);
		
		JRadioButton radio2Pound = new JRadioButton("£2");
		radio2Pound.setVisible(false);
		radio2Pound.setBounds(180, 64, 69, 23);
		contentPane.add(radio2Pound);
		
		JRadioButton radio1Pound = new JRadioButton("£1");
		radio1Pound.setVisible(false);
		radio1Pound.setBounds(180, 87, 141, 23);
		contentPane.add(radio1Pound);
		
		JRadioButton radio50Pence = new JRadioButton("50 pence");
		radio50Pence.setVisible(false);
		radio50Pence.setBounds(180, 111, 141, 23);
		contentPane.add(radio50Pence);
		
		JRadioButton radio20Pence = new JRadioButton("20 pence");
		radio20Pence.setVisible(false);
		radio20Pence.setBounds(180, 134, 141, 23);
		contentPane.add(radio20Pence);
		
		JRadioButton radio10Pence = new JRadioButton("10 pence");
		radio10Pence.setVisible(false);
		radio10Pence.setBounds(180, 157, 141, 23);
		contentPane.add(radio10Pence);
		
		JRadioButton radioNotes = new JRadioButton("Notes");
		radioNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				radio100Pound.setVisible(true);
				radio50Pound.setVisible(true);
				radio20Pound.setVisible(true);
				radio10Pound.setVisible(true);
				radio5Pound.setVisible(true);

				radio2Pound.setVisible(false);
				radio1Pound.setVisible(false);
				radio50Pence.setVisible(false);
				radio20Pence.setVisible(false);
				radio10Pence.setVisible(false);
				radio5Pence.setVisible(false);
				radio2Pence.setVisible(false);
				radio1Pence.setVisible(false);
				
				radio100Pound.setSelected(false);
				radio50Pound.setSelected(false);
				radio20Pound.setSelected(false);
				radio10Pound.setSelected(false);
				radio5Pound.setSelected(false);
				radio2Pound.setSelected(false);
				radio1Pound.setSelected(false);
				radio50Pence.setSelected(false);
				radio20Pence.setSelected(false);
				radio10Pence.setSelected(false);
				radio5Pence.setSelected(false);
				radio2Pence.setSelected(false);
				radio1Pence.setSelected(false);
			}
		});
		radioNotes.setBounds(140, 29, 69, 23);
		contentPane.add(radioNotes);
		
		JRadioButton radioCoins = new JRadioButton("Coins");
		radioCoins.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				radio100Pound.setVisible(false);
				radio50Pound.setVisible(false);
				radio20Pound.setVisible(false);
				radio10Pound.setVisible(false);
				radio5Pound.setVisible(false);

				radio2Pound.setVisible(true);
				radio1Pound.setVisible(true);
				radio50Pence.setVisible(true);
				radio20Pence.setVisible(true);
				radio10Pence.setVisible(true);
				radio5Pence.setVisible(true);
				radio2Pence.setVisible(true);
				radio1Pence.setVisible(true);
				
				radio100Pound.setSelected(false);
				radio50Pound.setSelected(false);
				radio20Pound.setSelected(false);
				radio10Pound.setSelected(false);
				radio5Pound.setSelected(false);
				radio2Pound.setSelected(false);
				radio1Pound.setSelected(false);
				radio50Pence.setSelected(false);
				radio20Pence.setSelected(false);
				radio10Pence.setSelected(false);
				radio5Pence.setSelected(false);
				radio2Pence.setSelected(false);
				radio1Pence.setSelected(false);
			}
		});
		radioCoins.setBounds(207, 29, 141, 23);
		contentPane.add(radioCoins);
		
		ButtonGroup NotesOrCoins = new ButtonGroup();
		NotesOrCoins.add(radioNotes);
		NotesOrCoins.add(radioCoins);
		
		ButtonGroup Notes = new ButtonGroup();
		Notes.add(radio100Pound);
		Notes.add(radio50Pound);
		Notes.add(radio20Pound);
		Notes.add(radio10Pound);
		Notes.add(radio5Pound);
		
		ButtonGroup Coins = new ButtonGroup();
		Coins.add(radio2Pound);
		Coins.add(radio1Pound);
		Coins.add(radio50Pence);
		Coins.add(radio20Pence);
		Coins.add(radio10Pence);
		Coins.add(radio5Pence);
		Coins.add(radio2Pence);
		Coins.add(radio1Pence);
		
		JLabel lblNewLabel = new JLabel("How many?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(112, 253, 127, 16);
		contentPane.add(lblNewLabel);
		
		JButton btnWithdraw = new JButton("Withdraw");
		btnWithdraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//The xml file object
				File cashXML = new File (System.getProperty("user.dir") + "/cash.xml");
				
				//Read cash.xml
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				
				try {
					DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();					
					Document doc = docBuilder.parse(cashXML);

					
					NodeList XMLTotals = doc.getElementsByTagName("Total");	//This will be useful later but what we actually need are quantities, not totals!
					NodeList XMLQuantities = doc.getElementsByTagName("Quantity");	//Better
					int[] intQuantities = new int[XMLQuantities.getLength()];
					
					for(int i = 0; i < XMLQuantities.getLength(); i++) {
						intQuantities[i] = Integer.parseInt(XMLQuantities.item(i).getTextContent());
					}
				
					// Code for depositing notes
					if(radioNotes.isSelected()) {
					
						// Check that a valid input has been given
						try {
							int Amount = Integer.parseInt(txtDeposit.getText());
							
							//Check which note is being deposited
							if(radio100Pound.isSelected()) {
								intQuantities[0] -= Amount;
							}
							if(radio50Pound.isSelected()) {
								intQuantities[1] -= Amount;
							}
							if(radio20Pound.isSelected()) {
								intQuantities[2] -= Amount;
							}
							if(radio10Pound.isSelected()) {
								intQuantities[3] -= Amount;
							}
							if(radio5Pound.isSelected()) {
								intQuantities[4] -= Amount;
							}
						}
						
						catch (Exception InvalidInput) {
							JOptionPane.showMessageDialog(null, "Please input a valid numerical value for the amount of your chosen note");
						}
					}
							
					// code for depositing coins
					if(radioCoins.isSelected()) {
						
						// Check that a valid input has been given
						try {
							double Amount = Double.parseDouble(txtDeposit.getText());
						
							//Check which coin is being deposited
							if(radio2Pound.isSelected()) {
								intQuantities[5] -= Amount;
							}
							if(radio1Pound.isSelected()) {
								intQuantities[6] -= Amount;
							}
							if(radio50Pence.isSelected()) {
								intQuantities[7] -= Amount;
							}
							if(radio20Pence.isSelected()) {
								intQuantities[8] -= Amount;
							}
							if(radio10Pence.isSelected()) {
								intQuantities[9] -= Amount;
							}
							if(radio5Pence.isSelected()) {
								intQuantities[10] -= Amount;
							}
							if(radio2Pence.isSelected()) {
								intQuantities[11] -= Amount;
							}
							if(radio1Pence.isSelected()) {
								intQuantities[12] -= Amount;
							}
						}
						catch (Exception InvalidInput) {
							JOptionPane.showMessageDialog(null, "Please input a valid numerical value for the amount of your chosen note or coin");
						}
					}

					//Rewrite the XML file based on the deposit input
					//Update quantities based on input
					XMLQuantities.item(0).setTextContent(String.valueOf(intQuantities[0]));
					XMLQuantities.item(1).setTextContent(String.valueOf(intQuantities[1]));
					XMLQuantities.item(2).setTextContent(String.valueOf(intQuantities[2]));
					XMLQuantities.item(3).setTextContent(String.valueOf(intQuantities[3]));
					XMLQuantities.item(4).setTextContent(String.valueOf(intQuantities[4]));
					XMLQuantities.item(5).setTextContent(String.valueOf(intQuantities[5]));
					XMLQuantities.item(6).setTextContent(String.valueOf(intQuantities[6]));
					XMLQuantities.item(7).setTextContent(String.valueOf(intQuantities[7]));
					XMLQuantities.item(8).setTextContent(String.valueOf(intQuantities[8]));
					XMLQuantities.item(9).setTextContent(String.valueOf(intQuantities[9]));
					XMLQuantities.item(10).setTextContent(String.valueOf(intQuantities[10]));
					XMLQuantities.item(11).setTextContent(String.valueOf(intQuantities[11]));
					XMLQuantities.item(12).setTextContent(String.valueOf(intQuantities[12]));
					
					//Update totals based on quantities
					DecimalFormat df = new DecimalFormat("#.##");	//Fix decimal formatting first
					XMLTotals.item(0).setTextContent(String.valueOf(df.format(intQuantities[0] * 100)));
					XMLTotals.item(1).setTextContent(String.valueOf(df.format(intQuantities[1] * 50)));
					XMLTotals.item(2).setTextContent(String.valueOf(df.format(intQuantities[2] * 20)));
					XMLTotals.item(3).setTextContent(String.valueOf(df.format(intQuantities[3] * 10)));
					XMLTotals.item(4).setTextContent(String.valueOf(df.format(intQuantities[4] * 5)));
					XMLTotals.item(5).setTextContent(String.valueOf(df.format(intQuantities[5] * 2)));
					XMLTotals.item(6).setTextContent(String.valueOf(df.format(intQuantities[6] * 1)));
					XMLTotals.item(7).setTextContent(String.valueOf(df.format(intQuantities[7] * 0.5)));
					XMLTotals.item(8).setTextContent(String.valueOf(df.format(intQuantities[8] * 0.2)));
					XMLTotals.item(9).setTextContent(String.valueOf(df.format(intQuantities[9] * 0.1)));
					XMLTotals.item(10).setTextContent(String.valueOf(df.format(intQuantities[10] * 0.05)));
					XMLTotals.item(11).setTextContent(String.valueOf(df.format(intQuantities[11] * 0.02)));
					XMLTotals.item(12).setTextContent(String.valueOf(df.format(intQuantities[12] * 0.01)));
					
					//Update total cash tally based on totals
					NodeList CashTotal = doc.getElementsByTagName("TotalCash");
					double TotalCash = 0;
					for (int i = 0; i < XMLTotals.getLength(); i++) {
						TotalCash += Double.parseDouble(XMLTotals.item(i).getTextContent());
					}
					CashTotal.item(0).setTextContent(String.valueOf(df.format(TotalCash)));

					//Push changes to the cash.xml
					TransformerFactory transformerFactory = TransformerFactory.newInstance();
					Transformer transformer = transformerFactory.newTransformer();
					DOMSource source = new DOMSource(doc);
					StreamResult result = new StreamResult(cashXML);
					transformer.transform(source, result);

					System.out.println("Done");
					
					//sysout X Y's deposited 
					
					txtDeposit.setText(null);

				}
				catch(Exception Except) {
					Except.printStackTrace();
				}
			}
		});
		btnWithdraw.setBounds(246, 270, 117, 29);
		contentPane.add(btnWithdraw);
	}
}