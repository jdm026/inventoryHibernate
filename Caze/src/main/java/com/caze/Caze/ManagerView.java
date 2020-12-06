package com.caze.Caze;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.Timer;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class ManagerView extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final App appManager = new App();
	private static JPanel centerPanel;
	private static JPanel northPanel;
	private static JPanel southPanel;
	private static JPanel westPanel;
	private static Manager managerIn;
	private static Timer timer;
	
	public static void main(String[] args)
	{
		
		FlatLightLaf.install();
    	
    	UIManager.put("Button.arc", 50);
    	UIManager.put("TextComponent.arc", 25);
    	
    	
		EventQueue.invokeLater(() ->
		{
			appManager.setup();
			createGUI();
		});
		
	}
	
	public ManagerView(String name)
	{
		super(name);
	}
	
	private static void createGUI()
	{
		// Create and set up window
		ManagerView frame = new ManagerView("Manager View");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.logIn();
		
		//Display the window
		frame.setLocation(300, 100);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(new Dimension(500, 500));
		frame.setResizable(false);
			
	}
	
	private void addComponentsToPane()
	{
		
		timer.stop();
		remove(northPanel);
		remove(centerPanel);
		remove(westPanel);
		
		JButton btnInventoryManagement = new JButton("Inventory Management");
		JButton btnInventoryReports= new JButton("Inventory Reports");
		JButton btnStoreInfo = new JButton("Store Information");
		
		westPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		westPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(6, 6, 6, 6);
		westPanel.add(btnInventoryManagement, gbc);
		
		gbc.gridy = 1;
		westPanel.add(btnInventoryReports, gbc);
		
		gbc.gridy = 2;
		westPanel.add(btnStoreInfo, gbc);
		
		getContentPane().add(westPanel, BorderLayout.WEST);
		
		centerPanel = new JPanel();
		southPanel = new JPanel();
		
		northPanel = new JPanel(new GridBagLayout());
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(6, 6, 6, 50);
		
		
    	JLabel lblDate = new JLabel();
    	JLabel lblId = new JLabel();
    	
    	lblDate.setText(LocalDate.now().getMonth().getValue() + " / " + LocalDate.now().getDayOfMonth() + " / " + LocalDate.now().getYear());
    	lblId.setText("Manager Menu - " +managerIn.getLast().toString());
    	
    	northPanel.add(lblId, gbc);
    	gbc.gridx++;
    	gbc.insets = new Insets(6, 40, 6, 40);
    	northPanel.add(new JLabel(""), gbc);
    	gbc.gridx++;
    	northPanel.add(new JLabel(""), gbc);
    	gbc.gridx++;
    	gbc.insets = new Insets(6, 50, 6, 6);
    	northPanel.add(lblDate, gbc);
    	getContentPane().add(northPanel, BorderLayout.NORTH);
		repaint();
		revalidate();
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		
		getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		
		btnInventoryManagement.addActionListener(e -> {

				remove(centerPanel);
				repaint();
				revalidate();
				centerPanel = invMgmt();
				getContentPane().add(centerPanel, BorderLayout.CENTER);
				setVisible(true);
				
			});
		
		btnInventoryReports.addActionListener(e -> {
			
			remove(centerPanel);
			repaint();
			revalidate();
			centerPanel = invReport();
			getContentPane().add(centerPanel, BorderLayout.CENTER);
			setVisible(true);
			
		});
		
		btnStoreInfo.addActionListener(e -> {
			
			
		});
		
	}
		
	protected JPanel invMgmt()
	{
		centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel lblSearch = new JLabel("Search Item");
		JLabel lblTransf = new JLabel("Transfer Item");
		JLabel lblAdd = new JLabel("Add Item Count");
		JLabel lblSub = new JLabel("Sub Item Count");
		
		JLabel lblText = new JLabel();
		lblText.setText("<html><b><i>Click To Select Option: </i></b></html>");
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 0);
		centerPanel.add(lblText, gbc);
		
		gbc.gridy++;
		gbc.insets = new Insets(6, 3, 6, 3);
		
		centerPanel.add(lblTransf, gbc);
		
		gbc.gridy++;
		centerPanel.add(lblSearch, gbc);
		
		gbc.gridy++;
		centerPanel.add(lblAdd, gbc);
		
		gbc.gridy++;
		centerPanel.add(lblSub, gbc);
		
		setSize(new Dimension(500, 500));
		
		lblSearch.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e)
			{
				if (e.getID() == MouseEvent.MOUSE_CLICKED)
				{
					lblSearch.setText("<html><body style=\"color:red\">Search Item</body></html>");
					lblTransf.setText("<html><body style=\"color:black\">Transfer Item</body></html>");
					lblAdd.setText("<html><body style=\"color:black\">Add Item Count</body></html>");
					lblSub.setText("<html><body style=\"color:black\">Sub Item Count</body></html>");
				//	SearchView.searchView(appManager);
					SearchItem.addView(appManager);
					
				}
			}
		});
		
		lblTransf.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e)
			{
				if (e.getID() == MouseEvent.MOUSE_CLICKED)
				{
					lblSearch.setText("<html><body style=\"color:black\">Search Item</body></html>");
					lblTransf.setText("<html><body style=\"color:red\">Transfer Item</body></html>");
					lblAdd.setText("<html><body style=\"color:black\">Add Item Count</body></html>");
					lblSub.setText("<html><body style=\"color:black\">Sub Item Count</body></html>");
					TransferItem.addView(appManager, managerIn);
					
					
				}
			}
		});
		
		lblAdd.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e)
			{
				if (e.getID() == MouseEvent.MOUSE_CLICKED)
				{
					lblSearch.setText("<html><body style=\"color:black\">Search Item</body></html>");
					lblTransf.setText("<html><body style=\"color:black\">Transfer Item</body></html>");
					lblAdd.setText("<html><body style=\"color:red\">Add Item Count</body></html>");
					lblSub.setText("<html><body style=\"color:black\">Sub Item Count</body></html>");
					AddItemCount.addView(appManager, managerIn);
				}
			}
			
		});
		
		
		
		lblSub.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e)
			{
				if (e.getID() == MouseEvent.MOUSE_CLICKED)
				{
					lblSearch.setText("<html><body style=\"color:black\">Search Item</body></html>");
					lblTransf.setText("<html><body style=\"color:black\">Transfer Item</body></html>");
					lblAdd.setText("<html><body style=\"color:black\">Add Item Count</body></html>");
					lblSub.setText("<html><body style=\"color:red\">Sub Item Count</body></html>");
					SubtractItemCount.subView(appManager, managerIn);
				}
			}
			
		});
		
		centerPanel.addKeyListener(new KeyListener() {
			
			public void keyTyped(KeyEvent e) {
				   
			 }
			    
			 /** Handle the key pressed event from the text field. */
			 public void keyPressed(KeyEvent e) {
			        
			 }
			    
		    /** Handle the key released event from the text field. */
		    public void keyReleased(KeyEvent e) {
		    	
		    	if (e.getID() == KeyEvent.VK_A)
				{
					lblSearch.setText("<html><body style=\"color:red\">Search Item</body></html>");
					lblAdd.setText("<html><body style=\"color:black\">Add Item Count</body></html>");
					lblSub.setText("<html><body style=\"color:black\">Sub Item Count</body></html>");
					SearchItem.addView(appManager);
					
				}
		    	
			}
		});
		
		return centerPanel;
		
	}
	
	
	private JPanel invReport()
	{
		centerPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel lblAdj = new JLabel("Item Adjustments");
		JLabel lblSale = new JLabel("Sales");
		JLabel lblOrd = new JLabel("Orders");
		
		JLabel lblText = new JLabel();
		lblText.setText("<html><b><i>Click To Select Option: </i></b></html>");
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 0);
		centerPanel.add(lblText, gbc);
		
		gbc.gridy++;
		gbc.insets = new Insets(6, 3, 6, 3);
		
		centerPanel.add(lblAdj, gbc);
		
		gbc.gridy++;
		centerPanel.add(lblSale, gbc);
		
		gbc.gridy++;
		centerPanel.add(lblOrd, gbc);
		
		setSize(new Dimension(500, 500));
		
		lblAdj.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e)
			{
				if (e.getID() == MouseEvent.MOUSE_CLICKED)
				{
					lblAdj.setText("<html><body style=\"color:red\">Item Adjustments</body></html>");
					lblSale.setText("<html><body style=\"color:black\">Sales</body></html>");
					lblOrd.setText("<html><body style=\"color:black\">Orders</body></html>");
				//	SearchView.searchView();
					
				}
			}
		});
		
		lblSale.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e)
			{
				if (e.getID() == MouseEvent.MOUSE_CLICKED)
				{
					lblAdj.setText("<html><body style=\"color:black\">Item Adjustments</body></html>");
					lblSale.setText("<html><body style=\"color:red\">Sales</body></html>");
					lblOrd.setText("<html><body style=\"color:black\">Orders</body></html>");
					
					
				}
			}
			
		});
		
		
		
		lblOrd.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e)
			{
				if (e.getID() == MouseEvent.MOUSE_CLICKED)
				{
					lblAdj.setText("<html><body style=\"color:black\">Item Adjustments</body></html>");
					lblSale.setText("<html><body style=\"color:black\">Sales</body></html>");
					lblOrd.setText("<html><body style=\"color:red\">Orders</body></html>");
				//	SubtractItemCount.subView();
				}
			}
			
		});
		
		return centerPanel;
		
	}

	private void logIn()
	{
		northPanel = new JPanel(new GridBagLayout());
		centerPanel = new JPanel();
		westPanel = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		
		
		JPasswordField tfLogIn = new JPasswordField(13);
		
		tfLogIn.setEchoChar('*');
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(3, 3, 3, 3);
		JLabel lblText = new JLabel("Enter Password: ");
		northPanel.add(lblText, gbc);
		
	//	gbc.insets = new Insets(0, 0, 0, 0);
		gbc.gridx = 1;
		northPanel.add(tfLogIn, gbc);
		
		JLabel txt = new JLabel();
    	txt.setText("");
    	gbc.gridy = 1;
    	northPanel.add(txt, gbc);
    	
    	
		getContentPane().add(northPanel, BorderLayout.NORTH);
		getContentPane().add(centerPanel, BorderLayout.CENTER);
		getContentPane().add(westPanel, BorderLayout.WEST);
		
		timer = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				addComponentsToPane();
			}
			
		});
		
		tfLogIn.addKeyListener( new KeyListener() {
		
			 public void keyTyped(KeyEvent e) {
				   
			 }
			    
			 /** Handle the key pressed event from the text field. */
			 public void keyPressed(KeyEvent e) {
			        
			 }
			    
		    /** Handle the key released event from the text field. */
		    public void keyReleased(KeyEvent e) {
		     
		    	
		    	if (tfLogIn.getPassword().length == 5)
		    	{
		    		char[] pass = tfLogIn.getPassword();
		    		StringBuilder check = new StringBuilder();
		    		
		    		for (int i = 0; i < pass.length; i++)
		    			check.append(pass[i]);
		    		
		    		try
		    		{
		   
		    			managerIn = (Manager)appManager.searchManager(check.toString());
		    			
		    			System.out.println("Welcome " + managerIn.getLast());
		    		
		    			// Calendar to check time of login
		    			Calendar calendar = Calendar.getInstance();
		    			int timeCheck = (calendar.getTime().getHours());
		    			
		    			if (timeCheck > 12)
		    				txt.setText("Good Afterrnoon " + managerIn.getFirst()
		    							+ " " + managerIn.getLast().charAt(0) + ".");
		    			else
		    				txt.setText("Good Morning " + managerIn.getFirst()
							+ " " + managerIn.getLast().charAt(0) + ".");
		    			
		    			gbc.gridy = 1;
		    			northPanel.add(txt, gbc);
		    		
		    			getContentPane().add(northPanel, BorderLayout.NORTH);
		   				
		    			timer.start();
		    			
		    		}
		    		catch(NullPointerException ex)
		    		{
		    			tfLogIn.setText("");
		    			
		    			txt.setText("<html><body style=\"color:red\">Invalid Password</body></html>");
		    			gbc.gridy = 2;
		    			gbc.insets = new Insets(6, 0, 0, 0);
		    			northPanel.add(txt, gbc);
		    			
		    			getContentPane().add(northPanel, BorderLayout.NORTH);
		    			setVisible(true);
		    			
		    			tfLogIn.requestFocusInWindow();
		    			
		    		}
		    	}
		    }
		});
	}
}
















