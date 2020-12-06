package com.caze.Caze;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class AddItemCount extends JFrame {
    
	private static final long serialVersionUID = 1L;
	private static App manager;
	private static JTextField itemArea;
    private static JTextField qohArea;
    private static JPanel itemSearchPanel;
    private static JPanel southPanel;
    private static JPanel northPanel;
    private static JPanel itemPanel;
    private static JScrollPane scroll;
    private static Manager managerIn;
    
    
    public AddItemCount(String name) {
        super(name);
    }
    
    public static void main(String[] args) {
    	
    	// Bubble type look and feel
    	FlatLightLaf.install();
    	
    	UIManager.put("Button.arc", 50);
    	UIManager.put("TextComponent.arc", 25);
    	
        //Schedule job for event dispatch thread
        //creating GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	manager = new App();
            	manager.setup();
            	createGUI();
            	
                
            }
        });
    	
    }
    
    protected static void addView(App mgmt, Manager mgmtIn) {
    
    	javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	manager = mgmt;
            	managerIn = mgmtIn;
            	createGUI();
            	
            }
        });
    	
    }
    
    /**
     * Create and display GUI.
     */
    private static void createGUI() {
    	
        //Create and set up window.
        AddItemCount frame = new AddItemCount("Add Inventory");
        
        //Set up content pane.
        frame.addComponentsToPane();
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        
        frame.addWindowListener(new WindowAdapter(){

		     @Override
		    public void windowClosing(WindowEvent e){
		        frame.dispose();
		    }

		});
    }
    
    /**
     * Add initial components to pane
     */
    private void addComponentsToPane() {
        
    	JLabel lblItem;
    	JLabel lblQoh;
    	JLabel lblText;
    	JButton btnSearch;
        JButton btnEnter;
        
        // Create panel for item search features
        itemSearchPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        
        itemSearchPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
        // Add objects to panel
		lblItem = new JLabel("Item: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(6, 6, 1, 3);
		gbc.anchor = GridBagConstraints.WEST;
		itemSearchPanel.add(lblItem, gbc);
		
		
		itemArea = new JTextField(13);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.insets = new Insets(6, 6, 1, 3);
		itemSearchPanel.add(itemArea, gbc);
		
		
		lblQoh = new JLabel("Qoh: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.ipadx = 0;
		gbc.insets = new Insets(6, 6, 3, 3);
		itemSearchPanel.add(lblQoh, gbc);

		
		qohArea = new JTextField(13);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.ipadx = 0;
		gbc.insets = new Insets(6, 6, 3, 3);
		itemSearchPanel.add(qohArea, gbc);
		
		
		// Handles item area key event
		itemArea.addKeyListener(new KeyListener(){

		    /** Handle the key typed event from the text field. */
		    public void keyTyped(KeyEvent e) {
		   
		    }
		    
		    /** Handle the key pressed event from the text field. */
		    public void keyPressed(KeyEvent e) {
		        
		    }
		    
		    /** Handle the key released event from the text field. */
		    public void keyReleased(KeyEvent e) {
		       
		    	qohArea.setText(null);
		    	
		    	int id = e.getKeyCode();
		    	
		    	if (id == KeyEvent.VK_ENTER)
		    	{
		    		displaySearch();
		    		qohArea.requestFocusInWindow();
		    	}
		    }
		});
		
		// Handles qoh text field key event
		qohArea.addKeyListener(new KeyListener(){

			
			
		    /** Handle the key typed event from qoh text field */
		    public void keyTyped(KeyEvent e) {
		    }
		    
		    /** Handle the key pressed event from qoh text field. */
		    public void keyPressed(KeyEvent e) {
		        
		    }
		    
		    /** Handle the key released event from the qoh field. */
		    public void keyReleased(KeyEvent e) {
		        
		    	int id = e.getKeyCode();
		    	
		    	if (id == KeyEvent.VK_ENTER)
		    	{
			    	try
		    		{
			    		if(isDigit(qohArea.getText()))
			    			addItem(manager.search(itemArea.getText()));
		    		}
			    	catch(NullPointerException ex)
			    	{
			    	
			    	}
		    	}
		    }
			
		});
	
		JPanel buttonPanel = new JPanel();
		btnSearch = new JButton("Search");
		btnEnter = new JButton("Enter");
		buttonPanel.add(btnSearch);
		buttonPanel.add(btnEnter);
		
		// Handle button action events
		btnSearch.addActionListener(e -> displaySearch());
		btnEnter.addActionListener(e -> 
		{
			try 
			{
				if(isDigit(qohArea.getText()) && !itemArea.getText().isEmpty())
					addItem(manager.search(itemArea.getText()));
			}
			catch(NumberFormatException ex)
			{
				ex.printStackTrace();
			}
		});
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 6, 3, 0);
		itemSearchPanel.add(buttonPanel, gbc);
		
		lblText = new JLabel();
		lblText.setText("<html><body style=\"color:red\">Enter Item Number <br> Then Quantity To Add</body></html>");
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		gbc.gridy = 4;
		gbc.gridwidth = 2;
		gbc.ipadx = 0;
		gbc.insets = new Insets(3, 3, 0, 0);
		itemSearchPanel.add(lblText, gbc);
		
		// Add objects to pane
        getContentPane().add(itemSearchPanel, BorderLayout.WEST);
        center(null);
        south();
        north();
        pack();
        setPreferredSize(new Dimension(850, 500));

    }
    
    /** 
     * Fill south border layout with item description
     */
    private void center(Item item)
	{
		
    	// Create panel
		southPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		southPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		// Create and update labels
		JLabel lblINum;
		JLabel lblPart;
		JLabel lblAlt;
		JLabel lblDesc;
		JLabel lblRetail;
		JLabel lblQoh;
		JLabel lblLasts;
		
		JLabel lblI = new JLabel("Item: ");
		JLabel lblP = new JLabel("Part: ");
		JLabel lblA = new JLabel("Alt: ");
		JLabel lblD = new JLabel("Desc: ");
		JLabel lblR = new JLabel("Sale: ");
		JLabel lblQ = new JLabel("Qoh: ");
		JLabel lblL = new JLabel("Last Sale: ");
		
		if (item == null)
		{
			lblINum = new JLabel("-");
			lblPart = new JLabel("-");
			lblAlt = new JLabel("-");
			lblDesc = new JLabel("-");
			lblRetail = new JLabel("-");
			lblQoh = new JLabel("-");
			lblLasts = new JLabel("-");
			
		}
		else
		{
			lblINum = new JLabel(item.getInum().toString());
			lblPart = new JLabel(item.getPart());
			lblAlt = new JLabel(item.getAlt());
			lblDesc = new JLabel(item.getIdesc());
			lblRetail = new JLabel("$" + item.getRetail());
			lblQoh = new JLabel(item.getQoh().toString());
			if (item.getLasts() == null)
				lblLasts = new JLabel("-");
			else
				lblLasts = new JLabel("" + item.getLasts());	
		}
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(3, 3, 3, 3);
		southPanel.add(lblINum, gbc);
		gbc.gridy = 1;
		gbc.insets = new Insets(3, 3, 3, 3);
		southPanel.add(lblPart, gbc);
		gbc.gridy++;
		southPanel.add(lblAlt, gbc);
		gbc.gridy++;
		southPanel.add(lblDesc, gbc);
		gbc.gridy++;
		southPanel.add(lblRetail, gbc);
		gbc.gridy++;
		southPanel.add(lblQoh, gbc);
		gbc.gridy++;
		southPanel.add(lblLasts, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(3, 3, 3, 3);
		southPanel.add(lblI, gbc);
		gbc.gridy = 1;
		gbc.insets = new Insets(3, 3, 3, 3);
		southPanel.add(lblP, gbc);
		gbc.gridy++;
		southPanel.add(lblA, gbc);
		gbc.gridy++;
		southPanel.add(lblD, gbc);
		gbc.gridy++;
		southPanel.add(lblR, gbc);
		gbc.gridy++;
		southPanel.add(lblQ, gbc);
		gbc.gridy++;
		southPanel.add(lblL, gbc);
		
		getContentPane().add(southPanel, BorderLayout.CENTER);
		pack();
		
	}
    
    /**
     * Displays description of items to 
     * be adjusted and the total adj
     * 
     */
    private void south()
    {

    	scroll = new JScrollPane();
    	GridLayout grid = new GridLayout(0, 6, 5, 0);
    
    	itemPanel = new JPanel(grid);
    	
    	itemPanel.add(new JLabel("Qty"));
    	itemPanel.add(new JLabel("Item"));
    	itemPanel.add(new JLabel("Part"));
    	itemPanel.add(new JLabel("Desc"));
    	itemPanel.add(new JLabel("Old"));
    	itemPanel.add(new JLabel("New"));
    	
   
    	
    	scroll.setPreferredSize(new Dimension(100, 100));
    	scroll.setViewportView(itemPanel);
    
    	getContentPane().add(scroll, BorderLayout.SOUTH);
    	pack();
    	
    	
    }
    
    /*
     * 
     */
    private void north()
    {
    	
    	northPanel = new JPanel(new GridLayout(0, 4, 10, 2));
    	JLabel lblDate = new JLabel();
    	JLabel lblId = new JLabel();
    	
    	lblDate.setText("                        " +LocalDate.now().getMonth().getValue() + " / " + LocalDate.now().getDayOfMonth() + " / " + LocalDate.now().getYear());
    	lblId.setText("   Item Adjustment - Add Count");
    	
    	northPanel.add(lblId);
    	northPanel.add(new JLabel());
    	northPanel.add(new JLabel());
    	northPanel.add(lblDate);
    	
    	getContentPane().add(northPanel, BorderLayout.NORTH);
    	pack();
    	
    	
    }
    
    /**
     * Add item to adjustment list
     */
    private void addItem(Item item)
    {
    	Integer oldQoh = item.getQoh();
    	
    	// Check if text in Qoh text field is integer
    	// greater than zero
    	if(isDigit(qohArea.getText()))
    	{
    		
    		int subInt = Integer.parseInt(qohArea.getText());
    		if (subInt > 0)
    		{	
    			// Update database
    			manager.addQoh(managerIn, item, Integer.parseInt(qohArea.getText()));
    		}
    	}
    	
    	
    	// Add components to item panel
    	itemPanel.add(new JLabel(qohArea.getText()));
    	itemPanel.add(new JLabel(item.getInum().toString()));
    	itemPanel.add(new JLabel(item.getPart().toString()));
    	itemPanel.add(new JLabel(item.getIdesc().toString()));
    	itemPanel.add(new JLabel(oldQoh.toString()));
    	itemPanel.add(new JLabel(item.getQoh().toString()));
    	
    	pack();
    	
    }
    
    
    /**
     * Displays updated item in the south border layout
     */
    private void displaySearch()
	{
    	// Return to caller if item field is empty
    	if (itemArea.getText().isEmpty())
			return;
    	
    	// Check to search for item number,
    	// part number, or alternate number
		if (isDigit(itemArea.getText()))
		{
			
			Item item = manager.searchItem(Integer.parseInt(itemArea.getText()));
			if (item == null)
			{
				item = manager.searchPart(itemArea.getText());
				
				if (item == null)
				{
					item = manager.searchAlt(itemArea.getText());
					
					if (item == null)
					{
						remove(southPanel);
						center(null);
					}
					else
					{
						remove(southPanel);
						center(item);
					}		 
				}
				else
				{
					remove(southPanel);
					center(item);	
				}	
			}
			else
			{
				remove(southPanel);
				center(item);
			}
	
		}
		else
		{
			Item item = manager.searchPart(itemArea.getText().toUpperCase());
			
			if (item == null)
			{
				item = manager.searchAlt(itemArea.getText().toUpperCase());
				
				if (item == null)
				{
					remove(southPanel);
					center(null);
				}
				else
				{
					remove(southPanel);
					center(item);
				}
				
			}
			else
			{
				remove(southPanel); 
				center(item);
			}
		}
		
	}
    
    // Check to see if a String is an integer
    private boolean isDigit(String check)
	{
		for (int i = 0; i < check.length(); i++)
			if(!Character.isDigit(check.charAt(i)))
				return false;	
		
		return true;
	}
   
}



