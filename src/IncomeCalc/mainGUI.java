package IncomeCalc;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class mainGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainGUI window = new mainGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				//As the program launches, check if cash.xml exists, and if not, create it
				//Do the same with bills.xml
				File cashXML = new File (System.getProperty("user.dir") + "/cash.xml");
				File billsXML = new File (System.getProperty("user.dir") + "/bills.xml");
				
				
				if(cashXML.exists() == false)
				{
					//Create cash.xml template
					try {
						TransformerFactory transformerFactory = TransformerFactory.newInstance();
						Transformer transformer = transformerFactory.newTransformer();
						transformer.setOutputProperty(OutputKeys.INDENT, "yes");
						transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
						
						DOMSource source = new DOMSource(CreateCashXML());
						
						StreamResult result = new StreamResult(cashXML);
						
						transformer.transform(source, result);
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(cashXML.exists() == false)
				{
					//Create bills.xml template
					CreateBillsXML();
				}
			}
			
			
			private Document CreateCashXML() throws Exception {
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				
				Document doc = docBuilder.newDocument();
				
					Element cash = doc.createElement("Cash");
						Element notes = doc.createElement("Notes");
							
							Element hundreds = doc.createElement("HundredPound");
									Element quantityHundred = doc.createElement("Quantity");
										quantityHundred.setTextContent("0");
									Element totalHundred = doc.createElement("Total");
										totalHundred.setTextContent("0");
										
									hundreds.appendChild(quantityHundred);
									hundreds.appendChild(totalHundred);
						
							Element fifties = doc.createElement("FiftyPound");
									Element quantityFifty = doc.createElement("Quantity");
										quantityFifty.setTextContent("0");
									Element totalFifty = doc.createElement("Total");
										totalFifty.setTextContent("0");
										
									fifties.appendChild(quantityFifty);
									fifties.appendChild(totalFifty);
									
							Element twenties = doc.createElement("TwentyPound");
									Element quantityTwenty = doc.createElement("Quantity");
										quantityTwenty.setTextContent("0");
									Element totalTwenty = doc.createElement("Total");
										totalTwenty.setTextContent("0");
										
									twenties.appendChild(quantityTwenty);
									twenties.appendChild(totalTwenty);
										
							Element tenners = doc.createElement("TenPound");
									Element quantityTenner = doc.createElement("Quantity");
										quantityTenner.setTextContent("0");
									Element totalTenner = doc.createElement("Total");
										totalTenner.setTextContent("0");
										
									tenners.appendChild(quantityTenner);
									tenners.appendChild(totalTenner);
										
							Element fivers = doc.createElement("FivePound");
									Element quantityFiver = doc.createElement("Quantity");
										quantityFiver.setTextContent("0");
									Element totalFiver = doc.createElement("Total");
										totalFiver.setTextContent("0");
										
									fivers.appendChild(quantityFiver);
									fivers.appendChild(totalFiver);
									
						notes.appendChild(hundreds);
						notes.appendChild(fifties);
						notes.appendChild(twenties);
						notes.appendChild(tenners);
						notes.appendChild(fivers);
						
						Element change = doc.createElement("Change");
						
							Element twoPounds = doc.createElement("TwoPound");
								Element quantityTwoPounds = doc.createElement("Quantity");
									quantityTwoPounds.setTextContent("0");
								Element totalTwoPounds = doc.createElement("Total");
									totalTwoPounds.setTextContent("0");
									
								twoPounds.appendChild(quantityTwoPounds);
								twoPounds.appendChild(totalTwoPounds);
							
							Element pounds = doc.createElement("OnePound");
								Element quantityPounds = doc.createElement("Quantity");
									quantityPounds.setTextContent("0");
								Element totalPounds = doc.createElement("Total");
									totalPounds.setTextContent("0");
									
								pounds.appendChild(quantityPounds);
								pounds.appendChild(totalPounds);
								
							Element fiftyP = doc.createElement("FiftyPence");
								Element quantityFiftyP = doc.createElement("Quantity");
									quantityFiftyP.setTextContent("0");
								Element totalFiftyP = doc.createElement("Total");
									totalFiftyP.setTextContent("0");
									
								fiftyP.appendChild(quantityFiftyP);
								fiftyP.appendChild(totalFiftyP);
									
							Element twentyP = doc.createElement("TwentyPence");
								Element quantityTwentyP = doc.createElement("Quantity");
									quantityTwentyP.setTextContent("0");
								Element totalTwentyP = doc.createElement("Total");
									totalTwentyP.setTextContent("0");
									
								twentyP.appendChild(quantityTwentyP);
								twentyP.appendChild(totalTwentyP);
										
							Element tenP = doc.createElement("TenPence");
								Element quantityTenP = doc.createElement("Quantity");
									quantityTenP.setTextContent("0");
								Element totalTenP = doc.createElement("Total");
									totalTenP.setTextContent("0");
									
								tenP.appendChild(quantityTenP);
								tenP.appendChild(totalTenP);
											
							Element fiveP = doc.createElement("FivePence");
								Element quantityFiveP = doc.createElement("Quantity");
									quantityFiveP.setTextContent("0");
								Element totalFiveP = doc.createElement("Total");
									totalFiveP.setTextContent("0");
									
								fiveP.appendChild(quantityFiveP);
								fiveP.appendChild(totalFiveP);
												
							Element twoP = doc.createElement("TwoPence");
								Element quantityTwoP = doc.createElement("Quantity");
									quantityTwoP.setTextContent("0");
								Element totalTwoP = doc.createElement("Total");
									totalTwoP.setTextContent("0");
									
								twoP.appendChild(quantityTwoP);
								twoP.appendChild(totalTwoP);
								
							Element Pennies = doc.createElement("OnePence");
								Element quantityPennies = doc.createElement("Quantity");
									quantityPennies.setTextContent("0");
								Element totalPennies = doc.createElement("Total");
									totalPennies.setTextContent("0");
									
								Pennies.appendChild(quantityPennies);
								Pennies.appendChild(totalPennies);
							

						change.appendChild(twoPounds);
						change.appendChild(pounds);
						change.appendChild(fiftyP);
						change.appendChild(twentyP);
						change.appendChild(tenP);
						change.appendChild(fiveP);
						change.appendChild(twoP);
						change.appendChild(Pennies);
						
						Element totalCash = doc.createElement("TotalCash");
						totalCash.setTextContent("0");
						
				doc.appendChild(cash);
				cash.appendChild(notes);
				cash.appendChild(change);
				cash.appendChild(totalCash);
				
				return doc;
			}
			
			private void CreateBillsXML() {
				// TODO Auto-generated method stub
				
			}

			
		});
	}

	/**
	 * Create the application.
	 */
	public mainGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCalculateIncome = new JButton("Calculate Income");
		btnCalculateIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Calculate Income
			}
		});
		btnCalculateIncome.setBounds(66, 23, 152, 29);
		frame.getContentPane().add(btnCalculateIncome);
		
		JButton btnEditBills = new JButton("Edit Bills");
		btnEditBills.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Edit bills
				
			}
		});
		btnEditBills.setBounds(66, 51, 152, 29);
		frame.getContentPane().add(btnEditBills);
		
		JButton btnEditSalary = new JButton("Edit Salary");
		btnEditSalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Edit salary
				
			}
		});
		btnEditSalary.setBounds(66, 81, 152, 29);
		frame.getContentPane().add(btnEditSalary);
		
		JButton btnDepositShrapnel = new JButton("Deposit Shrapnel");
		btnDepositShrapnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				DepositGUI DepositWindow = new DepositGUI();
				DepositWindow.setVisible(true);
				
			}
		});
		btnDepositShrapnel.setBounds(248, 23, 152, 29);
		frame.getContentPane().add(btnDepositShrapnel);
		
		JButton btnWithdrawShrapnel = new JButton("Withdraw Shrapnel");
		btnWithdrawShrapnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Open Withdraw window
				WithdrawGUI WithdrawWindow = new WithdrawGUI();
				WithdrawWindow.setVisible(true);
				
			}
		});
		btnWithdrawShrapnel.setBounds(248, 53, 152, 29);
		frame.getContentPane().add(btnWithdrawShrapnel);
		
		JButton btnViewShrapnel = new JButton("View Shrapnel");
		btnViewShrapnel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//Read cash.xml
				DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
				
				try {
					DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();					
					Document doc = docBuilder.parse(System.getProperty("user.dir") + "/cash.xml");

					
					NodeList Totals = doc.getElementsByTagName("Total");
					
					
					System.out.println("\n\nYour Current cash situation is as follows:");
					
					System.out.println("\t£100 notes:\t" + Totals.item(0).getTextContent());
					System.out.println("\t£50 notes:\t" + Totals.item(1).getTextContent());
					System.out.println("\t£20 notes:\t" + Totals.item(2).getTextContent());
					System.out.println("\t£10 notes:\t" + Totals.item(3).getTextContent());
					System.out.println("\t£5 notes:\t" + Totals.item(4).getTextContent());
					System.out.println("\t£2 coins:\t" + Totals.item(5).getTextContent());
					System.out.println("\t£1 coins:\t" + Totals.item(6).getTextContent());
					System.out.println("\t50 pences:\t" + Totals.item(7).getTextContent());
					System.out.println("\t20 pences:\t" + Totals.item(8).getTextContent());
					System.out.println("\t10 pences:\t" + Totals.item(9).getTextContent());
					System.out.println("\t5 pences:\t" + Totals.item(10).getTextContent());
					System.out.println("\t2 pences:\t" + Totals.item(11).getTextContent());
					System.out.println("\t1 pences:\t" + Totals.item(12).getTextContent());
					
					DecimalFormat df = new DecimalFormat("#.##");	//Fix decimal formatting first
					double totalCash = Double.parseDouble(doc.getElementsByTagName("TotalCash").item(0).getTextContent());
					System.out.println("\n\tTotal:\t£" + df.format(totalCash));
					System.out.println();
				}
				catch(ParserConfigurationException | SAXException | IOException Ex)
				{
					Ex.printStackTrace();
				}
			}
		});
		btnViewShrapnel.setBounds(248, 81, 152, 29);
		frame.getContentPane().add(btnViewShrapnel);
	}
}
