package com.caze.Caze;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SearchView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel northPanel;
	private JPanel centerPanel;
	private JTextField searchField;
	private static App appManager;
	
	public SearchView(String name)
	{
		super(name);
	}
	
	protected static void searchView(App manager)
	{
		
		EventQueue.invokeLater(() ->
		{
			
				appManager = manager;
				
		/*		
				var frame = new SearchView();
				frame.setTitle("Search Item");
				frame.addToPanel(manager);
		//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
				frame.setSize(400, 400);
				frame.setResizable(false);
				
				frame.addToPanel(manager);
				
				frame.addWindowListener(new WindowAdapter(){

				     @Override
				    public void windowClosing(WindowEvent e){
				        frame.dispose();
				    }

				});
		*/		
			
			
		});
		
		
	}
	
	private static void createGUI() {
		
		// Create and set up window
		SearchView frame = new SearchView("");
		
		frame.addToPanel(appManager);
		
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
	
	private void addToPanel(App manager)
	{
		JLabel lblSearch = new JLabel("Enter item: ");
		
		JButton searchButton = new JButton("Search");
		
		northPanel = new JPanel(new GridBagLayout());
		centerPanel = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		
		northPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(6, 6, 1, 3);
		northPanel.add(lblSearch, gbc);
		
		searchField = new JTextField(13);
		gbc.gridx++;
		gbc.insets = new Insets(6, 6, 3, 3);
		northPanel.add(searchField, gbc);
		
		gbc.gridx++;
		northPanel.add(searchButton, gbc);
		
	//	getContentPane().add(northPanel, BorderLayout.NORTH);
		
	//	center("N/A", "N/A", "N/A",
	//			null, "N/A", "N/A");
		
		searchButton.addActionListener(event ->
		{
			//remove(centerPanel);
			if (isDigit(searchField.getText()))
			{
				System.out.println(searchField.getText().toString());
				Item item = appManager.searchItem(Integer.parseInt(searchField.getText().toString()));
				if (item == null)
				{
					
					item = appManager.searchPart(searchField.getText());
					
					if (item == null)
					{
						
						item = appManager.searchAlt(searchField.getText());
						
						if (item == null)
						{
							
							Plano plano = new Plano();
							plano.setPlanoid("N/A");
							
							center("N/A", "N/A", "N/A",
									null, "N/A", "N/A");
						}
						else
							center(Integer.toString(item.getInum()), item.getPart(), item.getAlt(),
									item.getPlano(), Integer.toString(item.getSeq()), Integer.toString(item.getQoh()));
					}
					else
						center(Integer.toString(item.getInum()), item.getPart(), item.getAlt(),
								item.getPlano(), Integer.toString(item.getSeq()), Integer.toString(item.getQoh()));
				}
				else
					center(Integer.toString(item.getInum()), item.getPart(), item.getAlt(),
							item.getPlano(), Integer.toString(item.getSeq()), Integer.toString(item.getQoh()));
		
			}
			else
			{
				
				Item item = appManager.searchPart(searchField.getText().toUpperCase());
				
				if (item == null)
				{
					item = appManager.searchAlt(searchField.getText().toUpperCase());
					
					if (item == null)
					{
						
						center("N/A", "N/A", "N/A",
								null, "N/A", "N/A");
					}
					else
						center(Integer.toString(item.getInum()), item.getPart(), item.getAlt(),
							item.getPlano(), Integer.toString(item.getSeq()), Integer.toString(item.getQoh()));
				
					
				}
				else
				{
					center(Integer.toString(item.getInum()), item.getPart(), item.getAlt(),
						item.getPlano(), Integer.toString(item.getSeq()), Integer.toString(item.getQoh()));
				}
				
				
			}
			
			
		});

		
	}
	
	protected boolean isDigit(String check)
	{
				
		for (int i = 0; i < check.length(); i++)
		{
		
			if(!Character.isDigit(check.charAt(i)))
			{
				
				return false;
			}
			
		}
		
		return true;
	}
	
	protected void north()
	{
		northPanel = new JPanel(new GridLayout());
	}
	
	protected void center(String inum, String part, String alt,
			Plano plano, String seq, String qoh)
	{
		
	
		//centerPanel = new JPanel();
		centerPanel = new JPanel(new GridLayout(6, 0, 5, 10));
		GridBagConstraints gbc = new GridBagConstraints();
		centerPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JLabel labelINum = new JLabel("Item:\t" + inum);
		JLabel labelPart = new JLabel("Part:\t" + part);
		JLabel labelAlt = new JLabel("Alt:\t\t" + alt);
		
		JLabel labelPlano;
		
		if (plano == null)
			labelPlano = new JLabel("Plan:N/A");
		else
			labelPlano = new JLabel("Plan:\t" + plano.toString());
		
		
		JLabel labelSeq = new JLabel("Seq:\t\t" + seq);
		JLabel labelQoh = new JLabel("Qoh:\t" + qoh);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(3, 3, 3, 3);
		centerPanel.add(labelINum, gbc);
		
		gbc.gridy++;
		centerPanel.add(labelPart, gbc);
		
		gbc.gridy++;
		centerPanel.add(labelAlt, gbc);
		
		gbc.gridy++;
		centerPanel.add(labelPlano, gbc);
		
		gbc.gridy++;
		centerPanel.add(labelSeq, gbc);
		
		gbc.gridy++;
		centerPanel.add(labelQoh, gbc);
		
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		pack();
		
	}
	

}
/*
class ViewFrame extends JFrame
{
	/**
	 * 
	 
	private static final long serialVersionUID = 1L;
	private JButton searchButton;
	private JTextField searchField;
	private JPanel searchPanel;
	private JPanel centerPanel;
	
	
	
		
	public ViewFrame(final App manager)
	{
		
		centerPanel = new JPanel();
		searchField = new JTextField(10);
		searchButton = new JButton("Search");
		searchPanel = new JPanel();
		
		
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		
		add(searchPanel, BorderLayout.NORTH);
		
		
		
		
		setSize(new Dimension(500, 500));
		
		setVisible(true);
		
		searchButton.addActionListener(event ->
		{
			
			if (isDigit(searchField.getText()))
			{
				
				Item item = manager.searchItem(Integer.parseInt(searchField.getText()));
				if (item == null)
				{
					
					item = manager.searchPart(searchField.getText());
					
					if (item == null)
					{
						
						item = manager.searchAlt(searchField.getText());
						
						if (item == null)
						{
							
							Plano plano = new Plano();
							plano.setPlanoid("N/A");
							
							centerPanel = gridLayout("N/A", "N/A", "N/A",
									null, "N/A", "N/A");
						}
						else
							centerPanel = gridLayout(Integer.toString(item.getInum()), item.getPart(), item.getAlt(),
									item.getPlano(), Integer.toString(item.getSeq()), Integer.toString(item.getQoh()));
					}
					else
						centerPanel = gridLayout(Integer.toString(item.getInum()), item.getPart(), item.getAlt(),
								item.getPlano(), Integer.toString(item.getSeq()), Integer.toString(item.getQoh()));
				}
				else
					centerPanel = gridLayout(Integer.toString(item.getInum()), item.getPart(), item.getAlt(),
							item.getPlano(), Integer.toString(item.getSeq()), Integer.toString(item.getQoh()));
		
			}
			else
			{
				
				Item item = manager.searchPart(searchField.getText().toUpperCase());
				
				if (item == null)
				{
					item = manager.searchAlt(searchField.getText().toUpperCase());
					
					if (item == null)
					{
						
						centerPanel = gridLayout("N/A", "N/A", "N/A",
								null, "N/A", "N/A");
					}
					else
						centerPanel = gridLayout(Integer.toString(item.getInum()), item.getPart(), item.getAlt(),
							item.getPlano(), Integer.toString(item.getSeq()), Integer.toString(item.getQoh()));
				
					
				}
				else
				{
					centerPanel = gridLayout(Integer.toString(item.getInum()), item.getPart(), item.getAlt(),
						item.getPlano(), Integer.toString(item.getSeq()), Integer.toString(item.getQoh()));
				}
			}
			
			repaint();
			revalidate();
			add(centerPanel, BorderLayout.SOUTH);
			setLayout(new FlowLayout());
			
			setSize(new Dimension(500, 500));
			
			setVisible(true);
			
		});
		
		addWindowListener(new WindowAdapter(){

		     @Override
		    public void windowClosing(WindowEvent e){
		        ViewFrame.this.dispose();
		    }

		});
		
	}
		
	
	protected boolean isDigit(String check)
	{
				
		for (int i = 0; i < check.length(); i++)
		{
		
			if(!Character.isDigit(check.charAt(i)))
			{
				
				return false;
			}
			
		}
		
		return true;
	}
	
	protected JPanel gridLayout(String inum, String part, String alt,
			Plano plano, String seq, String qoh)
	{
		
	
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(6, 0, 5, 10));
		
		JLabel labelINum = new JLabel("Item:\t" + inum);
		JLabel labelPart = new JLabel("Part:\t" + part);
		JLabel labelAlt = new JLabel("Alt:\t\t" + alt);
		
		JLabel labelPlano = new JLabel("Plan:\t" + plano);
		
		
		JLabel labelSeq = new JLabel("Seq:\t\t" + seq);
		JLabel labelQoh = new JLabel("Qoh:\t" + qoh);
		
		
		centerPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		centerPanel.add(labelINum);
		centerPanel.add(labelPart);
		centerPanel.add(labelAlt);
		centerPanel.add(labelPlano);
		centerPanel.add(labelSeq);
		centerPanel.add(labelQoh);
		
		
		
		return centerPanel;
		
	}
	
	
}

*/