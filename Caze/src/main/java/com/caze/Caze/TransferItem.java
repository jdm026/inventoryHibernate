package com.caze.Caze;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;


/*
 *  Update Ideas:
 *  - Change reenter store with enter key and make other fields void
 *
 */
public class TransferItem extends JFrame{

	private static final long serialVersionUID = 1L;
	private static App manager;
	private static Manager managerIn;
	private static Item itemSearch;
//	private static JTextField storeField;
	private static JTextField itemField;
	private static JTextField amtField;
	private static JPanel northPanel;
	private static JPanel southPanel;
	private static JPanel centerPanel;
	private static JPanel eastPanel;
	private static JPanel westPanel;
	private static JPanel southBtnPanel;
	private static JPanel westBtnPanel;
	private static JScrollPane scroll;
	private static JLabel lblText;
	private static JLabel lblCount;
	private static JLabel lblRetail;
	
	private static List<Item> lstItems;
	private static List<Integer> lstAdj;
	private static Timer timer;
	
	
	private static int count;
	private static int check;
	
	private static int glbItemCount;
	private static double glbRetail;
	private static int glbStore;
	
	public TransferItem(String name) {
		super(name);
	}
	
	public static void main(String[] args)
	{
		App appman = new App();
		appman.setup();
		
		managerIn = appman.searchManager(1);
		
		addView(appman, managerIn);
		
	}
	
	protected static void addView(App mgmt, Manager mgmtIn) {
		
		lstItems = new ArrayList<Item>();
		lstAdj = new ArrayList<Integer>();
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() { 
			
			public void run() {
				manager = mgmt;
				managerIn = mgmtIn;
				store();
			}
			
		});
	}
	
	private static void store()
	{
		
		TransferItem frame = new TransferItem("Enter Store Number");
		JPanel panel = new JPanel(new GridLayout(0, 2, 3, 3));
		JLabel store1 = new JLabel("Enter Store: ");
		JLabel store2 = new JLabel("Re-Enter Store: ");
		JTextField tfs1 = new JTextField(13);
		JTextField tfs2 = new JTextField(13);
		
		tfs2.setEditable(false);
		
		
		
		panel.add(store1);
		panel.add(tfs1);
		panel.add(store2);
		panel.add(tfs2);
		
		
		tfs1.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				   
			 }
			    
			 /** Handle the key pressed event from the text field. */
			 public void keyPressed(KeyEvent e) {
			        
			 }
			    
		    /** Handle the key released event from the text field. */
		    public void keyReleased(KeyEvent e) {
		     
		    	 
		    	if ( tfs1.getText().length() == 4 && isDigit(tfs1.getText()))
		    	{
		    		tfs1.setEditable(false);
		    		tfs2.setEditable(true);
		    		tfs2.requestFocusInWindow();
		    		
		    	}
		    	else if(tfs1.getText().length() > 4)
		    	{
		    		tfs1.setText(null);
		    	}
		    }
		    
		});
		
		tfs2.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent k) {
				
			}
			
			public void keyPressed(KeyEvent k) {
				
			}
			
			public void keyReleased(KeyEvent k) {
				
				if (tfs2.getText().equals(tfs1.getText()))
	    		{
	    			glbStore = Integer.parseInt(tfs2.getText());
					
					lblText = new JLabel("| " + glbStore);
					createGUI();
	    			frame.dispose();
	    		}
				else if (tfs2.getText().length() == 4)
				{
					tfs1.setText(null);
					tfs1.setEditable(true);
					tfs1.requestFocusInWindow();
					tfs2.setText(null);
					tfs2.setEditable(false);
					
				}
				
			}
			
		});
		
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setLocation(550, 300);
		
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
			
		});
	}
	
	/*
	 * Create and display GUI
	 */
	private static void createGUI() {
		
		// Create and set up window
		TransferItem frame = new TransferItem("Transfer Item");
		
		// Set up content pane
		frame.addComponentsToPane();
		
		//Display the window
		frame.setPreferredSize(new Dimension(1200, 400));
		frame.pack();
		frame.setVisible(true);
		
		frame.setResizable(false);
		
		frame.addWindowListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
			
		});
		
	}

	/*
	 * Add initial components to pane
	 */
	private void addComponentsToPane() {
		
		north();
		south();
		center(null);
		west();
		east();
		
	//	setPreferredSize(new Dimension(850, 500));
		
	}
	
	private void center(Item item) {
		
		
		centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		centerPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JLabel lblINum;
		JLabel lblPart;
		JLabel lblAlt;
		JLabel lblDesc;
		JLabel lblRetail;
		JLabel lblQoh;
		
		JLabel lblI = new JLabel("Item: ");
		JLabel lblP = new JLabel("Part: ");
		JLabel lblA = new JLabel("Alt: ");
		JLabel lblD = new JLabel("Desc: ");
		JLabel lblR = new JLabel("Sale: ");
		JLabel lblQ = new JLabel("Qoh: ");
		

		if (item == null)
		{
			lblINum = new JLabel("-");
			lblPart = new JLabel("-");
			lblAlt = new JLabel("-");
			lblDesc = new JLabel("-");
			lblRetail = new JLabel("-");
			lblQoh = new JLabel("-");
			
		}
		else
		{
			lblINum = new JLabel(item.getInum().toString());
			lblPart = new JLabel(item.getPart());
			lblAlt = new JLabel(item.getAlt());
			lblDesc = new JLabel(item.getIdesc());
			lblRetail = new JLabel("$" + item.getRetail());
			lblQoh = new JLabel(item.getQoh().toString());
			
		}
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(3, 3, 3, 3);
		centerPanel.add(lblINum, gbc);
		gbc.gridy = 1;
		gbc.insets = new Insets(3, 3, 3, 3);
		centerPanel.add(lblPart, gbc);
		gbc.gridy++;
		centerPanel.add(lblAlt, gbc);
		gbc.gridy++;
		centerPanel.add(lblDesc, gbc);
		gbc.gridy++;
		centerPanel.add(lblRetail, gbc);
		gbc.gridy++;
		centerPanel.add(lblQoh, gbc);
		gbc.gridy++;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(3, 3, 3, 3);
		centerPanel.add(lblI, gbc);
		gbc.gridy = 1;
		gbc.insets = new Insets(3, 3, 3, 3);
		centerPanel.add(lblP, gbc);
		gbc.gridy++;
		centerPanel.add(lblA, gbc);
		gbc.gridy++;
		centerPanel.add(lblD, gbc);
		gbc.gridy++;
		centerPanel.add(lblR, gbc);
		gbc.gridy++;
		centerPanel.add(lblQ, gbc);
		
		centerPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		
		
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		pack();
	
		
	}
	
	private void south()
	{
		
		scroll = new JScrollPane();
    	GridLayout grid = new GridLayout(0, 5, 1, 0);
    	southPanel = new JPanel(grid);
    	southBtnPanel = new JPanel(new GridLayout(2, 2, 1, 1));
    	
    	lblCount = new JLabel("-");
    	lblRetail = new JLabel("-");
    	
    	JLabel lblMgmt = new JLabel("| " + managerIn.getFirst() + " " + managerIn.getLast().charAt(0) + ".");
    	JLabel lblDate = new JLabel("|" + LocalDate.now().getMonth().getValue() + "/" + LocalDate.now().getDayOfMonth());
    	southPanel.add(new JLabel("| Store:"));
    	southPanel.add(new JLabel("| Date:"));
    	southPanel.add(new JLabel("| Item Count:"));
    	southPanel.add(new JLabel("| Retail:"));
    	southPanel.add(new JLabel("| Transferred:"));
    	southPanel.add(lblText);
    	southPanel.add(lblDate);
    	southPanel.add(lblCount);
    	southPanel.add(lblRetail);
    	southPanel.add(lblMgmt);
    	
    	southPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
    	
    	JButton btnSubmit = new JButton("Submit Transfer");
    	JButton btnClear = new JButton("Clear");
    	JButton btnCancel = new JButton("Cancel");
    	JButton btnConfirm = new JButton("<html><body style=\"color:red\">Confirm Transfer</body></html>");
    	
    	btnConfirm.setVisible(false);
    	btnCancel.setVisible(false);
    	
    	southBtnPanel.add(btnSubmit);
    	southBtnPanel.add(btnClear);
    	southBtnPanel.add(btnConfirm);
    	southBtnPanel.add(btnCancel);
    	
    	btnClear.addActionListener(e -> {
    		
    		System.out.println("Clear Clicked");
    		
    	});
    	
    	btnSubmit.addActionListener(e -> {
    		
    		btnConfirm.setVisible(true);
    		btnCancel.setVisible(true);
    		btnSubmit.setVisible(false);
    		btnClear.setVisible(false);
    		pack();
    		
    		lblText.setText("<html><body style=\"color:red\">" + lblText.getText() + "</body></html>");
    		lblDate.setText("<html><body style=\"color:red\">" + lblDate.getText() + "</body></html>");
    		lblCount.setText("<html><body style=\"color:red\">" + lblCount.getText() + "</body></html>");
    		lblRetail.setText("<html><body style=\"color:red\">" + lblRetail.getText() + "</body></html>");
    		lblMgmt.setText("<html><body style=\"color:red\">" + lblMgmt.getText() + "</body></html>");
    		pack();
    		
    	});
    	
    	btnCancel.addActionListener(e -> {
    	
    		btnConfirm.setVisible(false);
    		btnCancel.setVisible(false);
    		btnSubmit.setVisible(true);
    		btnClear.setVisible(true);
    		
    		pack();
    		lblText.setText("<html><body style=\"color:black\">" + lblText.getText() + "</body></html>");
    		lblDate.setText("<html><body style=\"color:black\">" + lblDate.getText() + "</body></html>");
    		lblCount.setText("<html><body style=\"color:black\">" + lblCount.getText() + "</body></html>");
    		lblRetail.setText("<html><body style=\"color:black\">" + lblRetail.getText() + "</body></html>");
    		lblMgmt.setText("<html><body style=\"color:black\">" + lblMgmt.getText() + "</body></html>");
    		pack();
    		
    	});
    	
    	timer = new Timer(3000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				var temp = new JFrame();
				temp.setLocation(300, 100);
				temp.pack();
				temp.setVisible(true);
				temp.setSize(50, 50);
				
				var tempPanelTop = new JPanel();
				var tempPanelBot = new JPanel(new GridLayout(0, 2, 3, 3));
				var lblMgmt = new JLabel(managerIn.getFirst() + " " + managerIn.getLast().charAt(0) + " Transferred");
				
				tempPanelTop.add(lblMgmt);
				
				var lblC = new JLabel("Count: ");
				Integer count = glbItemCount;
				var lblCount = new JLabel(count.toString());
				tempPanelBot.add(lblC);
				tempPanelBot.add(lblCount);
				
				var lblS = new JLabel("Store: ");
				Integer store = glbStore;
				var lblStore = new JLabel(store.toString());
				tempPanelBot.add(lblS);
				tempPanelBot.add(lblStore);
				
				var lblD = new JLabel("Date: ");
				var lblDate = new JLabel(LocalDate.now().getMonth().getValue() + " / " + LocalDate.now().getDayOfMonth() + " / " + LocalDate.now().getYear());
				tempPanelBot.add(lblD);
				tempPanelBot.add(lblDate);
				
				JLabel lblT = new JLabel("Time: ");
				JLabel lblTime = new JLabel(LocalTime.now().toString());
				tempPanelBot.add(lblT);
				tempPanelBot.add(lblTime);
				
				
				
			}
			
		});
    	
    	btnConfirm.addActionListener(e -> {
    		System.out.println("Confirm Clicked");
    		try {
				manager.transfer(lstItems, lstAdj, managerIn, glbStore);
				
				timer.start();
				dispose();
				
				timer.stop();
				
				
			} catch (ParseException e1) {
				
				e1.printStackTrace();
			}
    	});
    	
    	
    	southBtnPanel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
    	
    	
    	
    	JPanel panel = new JPanel(new GridLayout(1, 2, 1, 1));
    	
//    	
//    	scroll.setPreferredSize(new Dimension(100, 100));
//    	scroll.setViewportView(southPanel);
//		scroll.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
//    	
		panel.add(southPanel);
		panel.add(southBtnPanel);
		panel.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		
		
		
		getContentPane().add(panel, BorderLayout.SOUTH);
    	pack();
    
		
		
	}
	
	private static void transferPopUp() {
		
		JFrame frame = new JFrame();
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		
		
	}
	
	private void west() {
		
		
		JLabel lblText;
		JLabel lblItem;
		JLabel lblAmt;
		
		// Create panel for item transfer fields
		westPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		westPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		// Add object to panel
		
		
		
		lblItem = new JLabel("Item: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(6, 6, 3, 3);
		gbc.anchor = GridBagConstraints.WEST;
		westPanel.add(lblItem, gbc);
		
		lblAmt = new JLabel("Quantity: ");
		gbc.gridy++;
		westPanel.add(lblAmt, gbc);
		
        lblText = new JLabel();
		lblText.setText("<html><body style=\"color:red\">Enter Item Number <br> Then Quantity</body></html>");
		gbc.gridx = 1;
		gbc.gridy = 0;
		
		westPanel.add(lblText, gbc);
		
		itemField = new JTextField(13);
		gbc.gridy++;
		westPanel.add(itemField, gbc);
		
		amtField = new JTextField(13);
		gbc.gridy++;
		westPanel.add(amtField, gbc);
		
		westBtnPanel = new JPanel(new GridLayout(1, 2, 0, 0));
		gbc.gridy++;
		westPanel.add(westBtnPanel, gbc);
		Border blackLine = BorderFactory.createDashedBorder(Color.BLACK);//.createLineBorder(Color.BLACK, 1);
		
		westPanel.setBorder(blackLine);
		
		itemField.addKeyListener(new KeyListener(){
		
		    /** Handle the key typed event from the text field. */
		    public void keyTyped(KeyEvent e) {
		   
		    }
		    
		    /** Handle the key pressed event from the text field. */
		    public void keyPressed(KeyEvent e) {
		        
		    }
		    
		    /** Handle the key released event from the text field. */
		    public void keyReleased(KeyEvent e) {
		    		
		    		amtField.setEditable(false);
		    	
		    		int id = e.getKeyCode();
		    	
		    		if (id == KeyEvent.VK_ENTER)
		    		{
			    	//	itemSearch = manager.search(itemField.getText());
			    	/*	
			    		if(!itemSearch.equals(null))
			    		{
			    			remove(centerPanel);
			    			center(itemSearch);
			    			amtField.setEditable(true);
			    			amtField.requestFocusInWindow();
			    		}
			    		else
			    		{
			    			itemField.setText(null);
			    			itemField.requestFocusInWindow();
			    		}
			    	*/	
			    		try
			    		{
			    			itemSearch = manager.search(itemField.getText());
			    			System.out.println("Item: " + itemSearch.getInum() + "\nPart: " + itemSearch.getPart());
			    			remove(centerPanel);
			    			center(itemSearch);
			    			amtField.setEditable(true);
			    			amtField.requestFocusInWindow();
			    			
		    			}
		    			catch(NullPointerException ex)
		    			{
		    				itemField.setText(null);
		    				itemField.requestFocusInWindow();
		    				center(null);
		    				
		    			}
		    			
		    		}
		    	}
			});
		
		
		amtField.addKeyListener(new KeyListener() {
			
			
			/** Handle the key typed event from the text field. */
		    public void keyTyped(KeyEvent e) {
		   
		    }
		    
		    /** Handle the key pressed event from the text field. */
		    public void keyPressed(KeyEvent e) {
		        
		    }
		    
		    /** Handle the key released event from the text field. */
		    public void keyReleased(KeyEvent e) {
		    		
		    		
		    		int id = e.getKeyCode();
		    	
		    		if (id == KeyEvent.VK_ENTER)
		    		{
		    			
		    			if (isDigit(amtField.getText()) && !amtField.getText().isEmpty())
		    			{
		    				itemField.setEditable(false);
		    				System.out.println("Check Count: " + checkCount(Integer.parseInt(amtField.getText())));
		    			}
		    			
		    			
		    		}
		    	}
		});
		
		getContentPane().add(westPanel, BorderLayout.WEST);
		pack();
		
		
	}
	private void east() {
		
		scroll = new JScrollPane();
		scroll.setBorder(BorderFactory.createDashedBorder(Color.BLACK));
		eastPanel = new JPanel(new GridLayout(0, 6, 0, 0));
		
		eastPanel.add(new JLabel("| Qty "));
    	eastPanel.add(new JLabel("| Item "));
    	eastPanel.add(new JLabel("| Part"));
    	eastPanel.add(new JLabel("| Desc"));
    	eastPanel.add(new JLabel("| Old"));
    	eastPanel.add(new JLabel("| New |"));
    	
		scroll.setPreferredSize(new Dimension(500, 50));
		scroll.setViewportView(eastPanel);
		
		getContentPane().add(scroll, BorderLayout.EAST);
		
	}
	
	private void north() {
		
		northPanel = new JPanel(new GridLayout(0, 6, 2, 2));
    	JLabel lblDate = new JLabel();
    	JLabel lblId = new JLabel();
    	
    	lblDate.setText("\t\t\t       " + LocalDate.now().getMonth().getValue() + " / " + LocalDate.now().getDayOfMonth() + " / " + LocalDate.now().getYear());
    	lblId.setText("   Transfer Item");
    	
    	northPanel.add(lblId);
    	northPanel.add(new JLabel("      "));
    	northPanel.add(new JLabel("      "));
    	northPanel.add(new JLabel("      "));
    	northPanel.add(new JLabel("      "));
    	northPanel.add(lblDate);
    	
    	getContentPane().add(northPanel, BorderLayout.NORTH);
    	pack();
		
	}
	
	private int checkCount(int a)
	{
		switch(count)
		{
		case 0: count++;
				check = a;
				amtField.selectAll();
				westBtnPanel.removeAll();
				westBtnPanel.add(new JLabel("<html><body style=\"color:red\">Confirm Quantity</body></html>"));
				pack();
				return 0;
				//break;
			
		case 1:	if (check == a)
				{
					count--;
					westBtnPanel.removeAll();
					itemField.setEditable(true);
					itemField.requestFocusInWindow();
					itemField.selectAll();
					amtField.setText(null);
					lstItems.add(itemSearch);
					lstAdj.add(check);
					glbItemCount += check;
					glbRetail += (check * itemSearch.getRetail().doubleValue());
					
					lblCount.setText("| " + glbItemCount);
					lblRetail.setText("| $" + glbRetail);
					
					int nqoh = itemSearch.getQoh() - check;
					eastPanel.add(new JLabel(Integer.toString(check)));
			    	eastPanel.add(new JLabel(itemSearch.getInum().toString()));
			    	eastPanel.add(new JLabel(itemSearch.getPart().toString()));
			    	eastPanel.add(new JLabel(itemSearch.getIdesc().toString()));
			    	eastPanel.add(new JLabel(itemSearch.getQoh().toString()));
			    	eastPanel.add(new JLabel(Integer.toString(nqoh)));
			    	
					pack();
					return check;
				}
				else {
					count--;
					westBtnPanel.removeAll();
					westBtnPanel.add(new JLabel("<html><body style=\"color:red\">Quantites Do Not Match</body></html>"));
					pack();
					amtField.setText(null);
					amtField.requestFocus();
					itemField.selectAll();
					return -1;
				}
		default: return -1;
		}
	}
	
	private void displaySearch(Item item)
	{
		
	}
	
	private void transferItem(Item item, int store)
	{
		
	}
	
	 // Check to see if a String is an integer
    private static boolean isDigit(String check)
	{
		for (int i = 0; i < check.length(); i++)
			if(!Character.isDigit(check.charAt(i)))
				return false;	
		
		return true;
	}
    
    
}
