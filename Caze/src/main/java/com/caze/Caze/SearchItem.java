package com.caze.Caze;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SearchItem extends JFrame {

	private static final long serialVersionUID = 1L;
	private static App appManager;
	private static JTextField searchField;
	private static JPanel northPanel;
	private static JPanel centerPanel;
	private static JPanel itemPanel;
	
	public SearchItem(String name) {
		super(name);
	}
	
	public static void main(String[] args) {
		
		createGui();
		
	}
	
	public static void addView(App mgmt) {
		SwingUtilities.invokeLater(new Runnable() {
			
			public void run() {
				appManager = mgmt;
				createGui();
			}
			
		});
	}
	
	private static void createGui() {
		
		SearchItem frame = new SearchItem("Search Item");
		
		frame.addComponentsToPane();
		
		frame.pack();
		frame.setResizable(false);
		frame.setPreferredSize(new Dimension(400, 400));
		frame.setVisible(true);
		
		frame.addWindowFocusListener(new WindowAdapter() {
			
			@Override
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});
	}
	
	private void addComponentsToPane() {
		
		north();
		center(null);
		pack();
		setPreferredSize(new Dimension(400, 400));
		
	}
	
	private void center(Item item)
	{
		centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		centerPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JLabel lblINum, lblPart, lblAlt, lblPlano, 
			   lblSeq, lblDesc,lblQoh, lblRetail,
			   lblI, lblP, lblA, lblPl,
			   lblS, lblD, lblQ, lblR;
		
		lblI = new JLabel("Item: ");
		lblP = new JLabel("Part: ");
		lblA = new JLabel("Alt: ");
		lblPl = new JLabel("Plan: ");
		lblS = new JLabel("Seq: ");
		lblD = new JLabel("Desc: ");
		lblQ = new JLabel("Qoh: ");
		lblR = new JLabel("Sale: ");
		
		
		if (item == null)
		{
			lblINum = new JLabel("N/A");
			lblPart = new JLabel("N/A");
			lblAlt = new JLabel("N/A");
			lblPlano = new JLabel("N/A");
			lblSeq = new JLabel("N/A");
			lblDesc = new JLabel("N/A");
			lblQoh = new JLabel("N/A");
			lblRetail = new JLabel("N/A");
			
		}
		else
		{
			lblINum = new JLabel(item.getInum().toString());
			lblPart = new JLabel(item.getPart().toString());
			lblAlt = new JLabel(item.getAlt().toString());
			lblPlano = new JLabel(item.getPlano().getPlanoid().toString());
			lblSeq = new JLabel(item.getSeq().toString());
			lblDesc = new JLabel(item.getIdesc().toString());
			lblQoh = new JLabel(item.getQoh().toString());
			lblRetail = new JLabel("$" + item.getRetail());	
		}
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.insets = new Insets(3, 3, 3, 3);
		centerPanel.add(lblINum, gbc);
		gbc.gridy++;
		centerPanel.add(lblPart, gbc);
		gbc.gridy++;
		centerPanel.add(lblAlt, gbc);
		gbc.gridy++;
		centerPanel.add(lblPlano, gbc);
		gbc.gridy++;
		centerPanel.add(lblSeq, gbc);
		gbc.gridy++;
		centerPanel.add(lblDesc, gbc);
		gbc.gridy++;
		centerPanel.add(lblQoh, gbc);
		gbc.gridy++;
		centerPanel.add(lblRetail, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(3, 3, 3, 3);
		centerPanel.add(lblI, gbc);
		gbc.gridy++;
		centerPanel.add(lblP, gbc);
		gbc.gridy++;
		centerPanel.add(lblA, gbc);
		gbc.gridy++;
		centerPanel.add(lblPl, gbc);
		gbc.gridy++;
		centerPanel.add(lblS, gbc);
		gbc.gridy++;
		centerPanel.add(lblD, gbc);
		gbc.gridy++;
		centerPanel.add(lblQ, gbc);
		gbc.gridy++;
		centerPanel.add(lblR, gbc);
		
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		pack();
		
	}
	
	private void north()
	{
		JLabel lblEnter;
		JButton btnSearch;
		
		itemPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		itemPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	
		lblEnter = new JLabel("Enter item: ");
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(3, 3, 3, 3);
		itemPanel.add(lblEnter, gbc);
		
		searchField = new JTextField(13);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 1;
		itemPanel.add(searchField, gbc);
		
		searchField.addKeyListener(new KeyListener() {
			
			/** Handle the key typed event from qoh text field */
		    public void keyTyped(KeyEvent e) {
		   //   displaySearch(e);
		   //   System.out.println("Key Typed");
		    }
		    
		    /** Handle the key pressed event from qoh text field. */
		    public void keyPressed(KeyEvent e) {
		    //  displaySearch(e);
		        
		    }
		    
		    /** Handle the key released event from the qoh field. */
		    public void keyReleased(KeyEvent e) {
		    
		    	if (e.getKeyCode() == KeyEvent.VK_ENTER)
		    	{
		    		displaySearch();
					searchField.requestFocusInWindow();
					searchField.selectAll();
		    	}
		    }
		});
		
		JPanel btnPanel = new JPanel();
		btnSearch = new JButton("Search");
		btnPanel.add(btnSearch);
		
		btnSearch.addActionListener(e -> {
			
			displaySearch();
			searchField.requestFocusInWindow();
			searchField.selectAll();
	});
	
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 2;
		itemPanel.add(btnPanel, gbc);
		
		getContentPane().add(itemPanel, BorderLayout.NORTH);
		pack();
		
	}
	
	private void displaySearch()
	{
    	// Return to caller if item field is empty
    	if (searchField.getText().isEmpty())
			return;
    	
    	// Check to search for item number,
    	// part number, or alternate number
		if (isDigit(searchField.getText()))
		{
			
			Item item = appManager.searchItem(Integer.parseInt(searchField.getText()));
			if (item == null)
			{
				item = appManager.searchPart(searchField.getText());
				
				if (item == null)
				{
					item = appManager.searchAlt(searchField.getText());
					
					if (item == null)
					{
						remove(centerPanel);
						center(null);
					}
					else
					{
						remove(centerPanel);
						center(item);
					}		 
				}
				else
				{
					remove(centerPanel);
					center(item);	
				}	
			}
			else
			{
				remove(centerPanel);
				center(item);
			}
	
		}
		else
		{
			Item item = appManager.searchPart(searchField.getText().toUpperCase());
			
			if (item == null)
			{
				item = appManager.searchAlt(searchField.getText().toUpperCase());
				
				if (item == null)
				{
					remove(centerPanel);
					center(null);
				}
				else
				{
					remove(centerPanel);
					center(item);
				}
				
			}
			else
			{
				remove(centerPanel); 
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
