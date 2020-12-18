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

/* This class displays the login menu for employees
*/
public class ManagerView extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final App appManager = new App();
	private static JPanel optionList;
	private static JPanel managerDatePanel;
	private static JPanel buttonList;
	private static Manager managerIn;
	private static Timer timer;
	
	// Main method to execute program
	public static void main(String[] args)
	{
		// GUI Style
		FlatLightLaf.install();
    	UIManager.put("Button.arc", 50);
    	UIManager.put("TextComponent.arc", 25);
    	
    	// Schedule job for event
		EventQueue.invokeLater(() ->
		{
			// Initializes database connection
			appManager.setup();
			
			// Displays GUI
			createGUI();
		});
		
	}
	
	// No-arg Constructor
	public ManagerView()
	{
		
	}
	
	// Constructor
	public ManagerView(String name)
	{
		super(name);
	}
	
	// Displays login frame
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
	
	// Adds component to frame instance
	private void addComponentsToPane()
	{
		
		// Timer function used to display welcoming message before
		// displaying the manager home screen
		timer.stop();
		remove(managerDatePanel);
		remove(optionList);
		remove(buttonList);
		
		// Initialize panels
		buttonList = new JPanel(new GridBagLayout());
		optionList = new JPanel();
		managerDatePanel = new JPanel(new GridBagLayout());
		
		// Buttons displayed in home screen
		JButton btnInventoryManagement = new JButton("Inventory Management");
		JButton btnInventoryReports= new JButton("Inventory Reports");
		JButton btnStoreInfo = new JButton("Store Information");
		
		// Add buttons to buttonList panel
		GridBagConstraints gbc = new GridBagConstraints();
		buttonList.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(6, 6, 6, 6);
		buttonList.add(btnInventoryManagement, gbc);
		
		gbc.gridy = 1;
		buttonList.add(btnInventoryReports, gbc);
		
		gbc.gridy = 2;
		buttonList.add(btnStoreInfo, gbc);
		
		// Add buttonList to frame
		getContentPane().add(buttonList, BorderLayout.WEST);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(6, 6, 6, 50);
		
		// Initialize labels for manager and date
    	JLabel lblDate = new JLabel();
    	JLabel lblId = new JLabel();
    	
    	// Display manager and date across the top border
    	lblDate.setText(LocalDate.now().getMonth().getValue() + " / " + LocalDate.now().getDayOfMonth() + " / " + LocalDate.now().getYear());
    	lblId.setText("Manager Menu - " +managerIn.getLast().toString());
    	
    	managerDatePanel.add(lblId, gbc);
    	gbc.gridx++;
    	gbc.insets = new Insets(6, 40, 6, 40);
    	managerDatePanel.add(new JLabel(""), gbc);
    	gbc.gridx++;
    	managerDatePanel.add(new JLabel(""), gbc);
    	gbc.gridx++;
    	gbc.insets = new Insets(6, 50, 6, 6);
    	managerDatePanel.add(lblDate, gbc);
    	getContentPane().add(managerDatePanel, BorderLayout.NORTH);
		repaint();
		revalidate();
		getContentPane().add(optionList, BorderLayout.CENTER);
		
		// Button event to display inventory management options
		btnInventoryManagement.addActionListener(e -> {

				remove(optionList);
				repaint();
				revalidate();
				optionList = invMgmt();
				getContentPane().add(optionList, BorderLayout.CENTER);
				setVisible(true);
				
			});
		
		// Button event to display inventory report options
		btnInventoryReports.addActionListener(e -> {
			
			remove(optionList);
			repaint();
			revalidate();
			optionList = invReport();
			getContentPane().add(optionList, BorderLayout.CENTER);
			setVisible(true);
			
		});
		
		// Button event to display store information
		btnStoreInfo.addActionListener(e -> {
			
			
		});
		
	}
		
	// Panel to display inventory management options
	protected JPanel invMgmt()
	{
		// Initialize optionList panel
		optionList = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Button to display inventory management options
		JLabel lblSearch = new JLabel("Search Item");
		JLabel lblTransf = new JLabel("Transfer Item");
		JLabel lblAdd = new JLabel("Add Item Count");
		JLabel lblSub = new JLabel("Sub Item Count");
		
		// Add buttons to optionList panel
		JLabel lblText = new JLabel();
		lblText.setText("<html><b><i>Click To Select Option: </i></b></html>");
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 0);
		optionList.add(lblText, gbc);
		
		gbc.gridy++;
		gbc.insets = new Insets(6, 3, 6, 3);
		
		optionList.add(lblTransf, gbc);
		
		gbc.gridy++;
		optionList.add(lblSearch, gbc);
		
		gbc.gridy++;
		optionList.add(lblAdd, gbc);
		
		gbc.gridy++;
		optionList.add(lblSub, gbc);
		
		setSize(new Dimension(500, 500));
		
		// Search button listener
		// Call the addView method from SearchItem class
		// to display popup window for search function
		lblSearch.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e)
			{
				if (e.getID() == MouseEvent.MOUSE_CLICKED)
				{
					lblSearch.setText("<html><body style=\"color:red\">Search Item</body></html>");
					lblTransf.setText("<html><body style=\"color:black\">Transfer Item</body></html>");
					lblAdd.setText("<html><body style=\"color:black\">Add Item Count</body></html>");
					lblSub.setText("<html><body style=\"color:black\">Sub Item Count</body></html>");
					SearchItem.addView(appManager);
					
				}
			}
		});
		
		// Transfer button listener
		// Call the addView method from TransferItem class
		// to display popup window for transfer function
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
		
		// Add item button listener
		// Call the addView method from AddItemCount class
		// to display popup window for adding item function
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
		
		// Subtract item button listener
		// Call the addView method from SubtractItemCount class
		// to display popup window for subtracting item function
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
		
		return optionList;
		
	}
	
	// Panel to display inventory report options
	private JPanel invReport()
	{
		// Initialize optionList
		optionList = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Button to display inventory report options
		JLabel lblAdj = new JLabel("Item Adjustments");
		JLabel lblSale = new JLabel("Sales");
		JLabel lblOrd = new JLabel("Orders");
		
		// Add buttons to optionList panel
		JLabel lblText = new JLabel();
		lblText.setText("<html><b><i>Click To Select Option: </i></b></html>");
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 10, 0);
		optionList.add(lblText, gbc);
		
		gbc.gridy++;
		gbc.insets = new Insets(6, 3, 6, 3);
		
		optionList.add(lblAdj, gbc);
		
		gbc.gridy++;
		optionList.add(lblSale, gbc);
		
		gbc.gridy++;
		optionList.add(lblOrd, gbc);
		
		setSize(new Dimension(500, 500));
		
		lblAdj.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent e)
			{
				if (e.getID() == MouseEvent.MOUSE_CLICKED)
				{
					lblAdj.setText("<html><body style=\"color:red\">Item Adjustments</body></html>");
					lblSale.setText("<html><body style=\"color:black\">Sales</body></html>");
					lblOrd.setText("<html><body style=\"color:black\">Orders</body></html>");
// ~~~~~~~~~~~~~~~~ Create class to display item adjustment reports ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
					
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
				}
			}
			
		});
		
		return optionList;
		
	}

	// Initial login screen
	private void logIn()
	{
		// Initialize panel variables
		managerDatePanel = new JPanel(new GridBagLayout());
		optionList = new JPanel();
		buttonList = new JPanel();
		GridBagConstraints gbc = new GridBagConstraints();
		
		// Declare and initialize login field and hide input
		// Add field to panel
		JPasswordField tfLogIn = new JPasswordField(13);
		tfLogIn.setEchoChar('*');
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(3, 3, 3, 3);
		
		// Declare and initialize label and text va
		JLabel lblText = new JLabel("Enter Password: ");
		managerDatePanel.add(lblText, gbc);
		
		gbc.gridx = 1;
		managerDatePanel.add(tfLogIn, gbc);
		
		JLabel txt = new JLabel();
    	txt.setText("");
    	gbc.gridy = 1;
    	managerDatePanel.add(txt, gbc);
    	
    	// Add panels to frame
		getContentPane().add(managerDatePanel, BorderLayout.NORTH);
		getContentPane().add(optionList, BorderLayout.CENTER);
		getContentPane().add(buttonList, BorderLayout.WEST);
		
		// Timer instance to handle displaying welcoming message
		timer = new Timer(2000, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				addComponentsToPane();
			}
			
		});
		
		// Handle login credentials
		tfLogIn.addKeyListener( new KeyListener() {
		
			 public void keyTyped(KeyEvent e) {
				   
			 }
			    
			 /** Handle the key pressed event from the text field. */
			 public void keyPressed(KeyEvent e) {
			        
			 }
			    
		    /** Handle the key released event from the text field. */
		    public void keyReleased(KeyEvent e) {
		     
		    	// Standard password for this program has length 5
		    	// If statement to run once the length requirement is
		    	// met in the textfield
		    	if (tfLogIn.getPassword().length == 5)
		    	{
		    		char[] pass = tfLogIn.getPassword();
		    		StringBuilder check = new StringBuilder();
		    		
		    		for (int i = 0; i < pass.length; i++)
		    			check.append(pass[i]);
		    		
		    		// Try statement to check if manager can be initialized
		    		// Catch if no manager was found, display message to console,
		    		// and clear text field
		    		try
		    		{
		   
		    			managerIn = (Manager)appManager.searchManager(check.toString());
		    			
		    			System.out.println("Welcome " + managerIn.getLast());
		    		
		    			// Calendar to check time of login
		    			Calendar calendar = Calendar.getInstance();
		    			@SuppressWarnings("deprecation")
						int timeCheck = (calendar.getTime().getHours());
		    			
		    			// Conditoinal for morning/afternoon message
		    			if (timeCheck > 12)
		    				txt.setText("Good Afterrnoon " + managerIn.getFirst()
		    							+ " " + managerIn.getLast().charAt(0) + ".");
		    			else
		    				txt.setText("Good Morning " + managerIn.getFirst()
							+ " " + managerIn.getLast().charAt(0) + ".");
		    			
		    			gbc.gridy = 1;
		    			managerDatePanel.add(txt, gbc);
		    		
		    			getContentPane().add(managerDatePanel, BorderLayout.NORTH);
		   				
		    			timer.start();
		    			
		    		}
		    		catch(NullPointerException ex)
		    		{
		    			tfLogIn.setText("");
		    			
		    			txt.setText("<html><body style=\"color:red\">Invalid Password</body></html>");
		    			gbc.gridy = 2;
		    			gbc.insets = new Insets(6, 0, 0, 0);
		    			managerDatePanel.add(txt, gbc);
		    			
		    			getContentPane().add(managerDatePanel, BorderLayout.NORTH);
		    			setVisible(true);
		    			
		    			tfLogIn.requestFocusInWindow();
		    			
		    		}
		    	}
		    }
		});
	}
}
















