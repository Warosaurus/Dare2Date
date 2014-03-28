import Base.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public class ProfileWindow implements ActionListener{

	//-----------------------------------Variables for screen dimensions and drawing content----------------------------------------------//
	private JFrame frame;
	private int screenWidth;
	private int screenHeight;
	private int currentScreen;
	//--------------------------------------Variables for 	
	private JLayeredPane MainlayeredPane;
	private JLayeredPane ProfilelayeredPane;
	private JLayeredPane SearchlayeredPane;
	
	private JPanel panelSearchResults;
	private JPanel panelProfileMain;
	
	private Calendar cal = Calendar.getInstance();
	
	private User currentUser = new User("gareth", "twat", "male", 1, 1, 34, cal, "Leiden");
	private User testUser_01 = new User("gazza", "twat", "male", 1, 1, 34, cal, "Leiden");
	private User testUser_02 = new User("jaimie", "twat", "male", 1, 1, 34, cal, "Leiden");
	private User testUser_03 = new User("todd", "twat", "male", 1, 1, 34, cal, "Leiden");
	
	private User[] userArray = {currentUser,testUser_01,testUser_02,testUser_03};
	
	private JButton btnpanelProfileTitle_Home;
	private String[] comboBoxSettings = {"Account Settings","Edit Personal Details","Edit Preferences","Edit Account Details","View Profile"};
	private JComboBox<?> comboBoxpanelTitle_Settings;
	
	//---------------------------------Variables for the main search panel-----------------------------------------------------
	
	private JTextField panelMainSearch_Criteria1;
	private JTextField panelMainSearch_Criteria2;
	private JTextField panelMainSearch_Criteria3;
	private JTextField panelMainSearch_Criteria4;
	private JTextField panelMainSearch_Criteria5;
	
	private JComboBox<?> panelMainSearch_CB1;
	private JComboBox<?> panelMainSearch_CB2;
	private JComboBox<?> panelMainSearch_CB3;
	private JComboBox<?> panelMainSearch_CB4;
	private JComboBox<?> panelMainSearch_CB5;
	
	private String[] comboBoxSearch = {"Firstname","Surname","Sport","Music","Film"};
	private String[] comboBoxBlind = {"Age","Location"};
	
	//----------------------------------Variables for setting the current profile being shown ------------------------------------------------------------//
	
	private String firstname = "";
	private String surname = "";
	private String gender = "";
	private int age = 0;
	private String location = "";
	private String sex_Pref = "";
	private String films = "";
	private String sports = "";
	private JTextField txtpanelMainInstantM_Message;
	private JTable table;
	
	
	private String[] columnNames = {"Firstname", "Surname", "Age", "Gender", "Location"};
	private User[] userTests = {testUser_01,testUser_02,testUser_03};
	private Object[][] test;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfileWindow window = new ProfileWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ProfileWindow() {
		initialize();
		frame.setVisible(true);
		currentScreen = 1;
	}
	
	public ProfileWindow(User user){
		
		initialize();
		frame.setVisible(true);
		
		//currentUser = user;
		currentScreen = 1;
		
		if(user.getLevel() == 1){
			panelMainSearch_Criteria2.setEnabled(false);
			panelMainSearch_Criteria3.setEnabled(false);
			panelMainSearch_Criteria4.setEnabled(false);
			panelMainSearch_Criteria5.setEnabled(false);
			
			panelMainSearch_CB2.setEnabled(false);
			panelMainSearch_CB3.setEnabled(false);
			panelMainSearch_CB4.setEnabled(false);
			panelMainSearch_CB5.setEnabled(false);
			
			//comboBoxpanelTitle_Settings.setEnabled(false);
			//comboBoxpanelTitle_Settings.setFocusable(false);
			//comboBoxpanelTitle_Settings.setVisible(false);
			
		}
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		setScreenMargins();
		frame.setBounds(0, 0, 918, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
	
		onDrawPanelMain();
		
		onDrawPanelProfile();
		
		onDrawPanelSearchResults();
	}
	
	public class MyButtonListener implements ActionListener{
		
		int number;
		
		public MyButtonListener(int number){
			this.number = number;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			setProfile(userArray[this.number+1]);
			//System.out.print(String.valueOf(number));
			changePanels(SearchlayeredPane,ProfilelayeredPane);
			
		}
	}
	
	public class MySearchListener implements ActionListener{
		
		
		
		public MySearchListener(){
			
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			int choice1 = panelMainSearch_CB1.getSelectedIndex();
//			int choice2 = panelMainSearch_CB2.getSelectedIndex();
//			int choice3 = panelMainSearch_CB3.getSelectedIndex();
//			int choice4 = panelMainSearch_CB4.getSelectedIndex();
//			int choice5 = panelMainSearch_CB5.getSelectedIndex();
			
			if(panelMainSearch_Criteria1 != null&&((choice1 == 1)||(choice1 == 0))){
				
                       public void nameSearch ("gareth"){
                        try {
                            //Create a reference to the service interface at the location.
                            ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://192.0.0.102/DateServer");
                            //Create a response object
                            Response res = new Response();
                            //Invoke server SignUp method
                            res = service.nameSearch(panelMainSearch_Criteria1.getText());
                            //Test response
                            if (res.getError() != null) {
                                    System.out.println(res.getError());
                                    System.out.println("There was an error.");
                            }
                            else {
                                    System.out.println("Everything went okay.");
                                    System.out.println(res.getResponse());
                            }
                        } catch (NotBoundException ex) {
                                System.out.println(ex);
                        } catch (MalformedURLException ex) {
                                System.out.println(ex);
                        } catch (RemoteException ex) {
                                System.out.println(ex);
                    }
            
                }
		
	}
	
	public void onDrawPanelTitle(JLayeredPane pane){
		
		JPanel panelProfileTitle = new JPanel();
		panelProfileTitle.setBackground(new Color(250, 235, 215));
		panelProfileTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelProfileTitle.setBounds(0, 0, 902, 99);
		pane.add(panelProfileTitle);
		panelProfileTitle.setLayout(null);
		
		JLabel lblpanelTitle_Dare2Date = new JLabel("Dare2Date");
		lblpanelTitle_Dare2Date.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 50));
		lblpanelTitle_Dare2Date.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelTitle_Dare2Date.setBounds(201, 20, 500, 60);
		panelProfileTitle.add(lblpanelTitle_Dare2Date);
		
		comboBoxpanelTitle_Settings = new JComboBox<Object>(comboBoxSettings);
		comboBoxpanelTitle_Settings.setBounds(740, 54, 140, 25);
		comboBoxpanelTitle_Settings.addActionListener(this);
		panelProfileTitle.add(comboBoxpanelTitle_Settings);
		
		JButton btnpanelProfileTitle_Home = new JButton("HOME");
		btnpanelProfileTitle_Home.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
					changePanels(ProfilelayeredPane,MainlayeredPane);
                                        changePanels(SearchlayeredPane,MainlayeredPane);
                                        panelSearchResults.removeAll();
                                        panelSearchResults.revalidate();
				
			}
		});
		btnpanelProfileTitle_Home.setBounds(20, 20, 80, 23);
		btnpanelProfileTitle_Home.setVisible(true);
		panelProfileTitle.add(btnpanelProfileTitle_Home);
		
		JButton btnpanelProfileTitle_LogOut = new JButton("Log Out");
		btnpanelProfileTitle_LogOut.setBounds(740, 20, 140, 25);
		panelProfileTitle.add(btnpanelProfileTitle_LogOut);
	
	}
	
	public void onDrawPanelMain(){
		
		MainlayeredPane = new JLayeredPane();
		frame.getContentPane().add(MainlayeredPane, "name_93516581179371");
		
		onDrawPanelTitle(MainlayeredPane);
		
		onDrawPanelMain_Search(MainlayeredPane);
		
		onDrawPanelMain_InstantM(MainlayeredPane);
		
		onDrawPanelMain_Blind(MainlayeredPane);
		
		onDrawPanelMain_VipSearch(MainlayeredPane);
		
		onDrawPanelMain_Inbox(MainlayeredPane);
	}
	
	public void onDrawPanelMain_Search(JLayeredPane pane){
		
		JPanel panelMainSearch = new JPanel();
		panelMainSearch.setBackground(new Color(100, 149, 237));
		panelMainSearch.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMainSearch.setBounds(0, 100, 450, 260);
		pane.add(panelMainSearch);
		panelMainSearch.setLayout(null);
		
		JLabel lblpanelMainSearch_Title = new JLabel("Search Profiles");
		lblpanelMainSearch_Title.setFont(new Font("Verdana", Font.BOLD, 15));
		lblpanelMainSearch_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelMainSearch_Title.setBounds(10, 10, 430, 30);
		panelMainSearch.add(lblpanelMainSearch_Title);
		
		panelMainSearch_CB1 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB1.setBounds(20, 50, 150, 20);
		
		panelMainSearch.add(panelMainSearch_CB1);
		
		panelMainSearch_CB2 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB2.setBounds(20, 80, 150, 20);
		//panelMainSearch_CB2.addActionListener(this);
		panelMainSearch.add(panelMainSearch_CB2);
		
		panelMainSearch_CB3 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB3.setBounds(20, 110, 150, 20);
		//panelMainSearch_CB3.addActionListener(this);
		panelMainSearch.add(panelMainSearch_CB3);
		
		panelMainSearch_CB4 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB4.setBounds(20, 140, 150, 20);
		//panelMainSearch_CB4.addActionListener(this);
		panelMainSearch.add(panelMainSearch_CB4);
		
		panelMainSearch_CB5 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB5.setBounds(20, 170, 150, 20);
		//panelMainSearch_CB5.addActionListener(this);
		panelMainSearch.add(panelMainSearch_CB5);
		
		panelMainSearch_Criteria1 = new JTextField();
		panelMainSearch_Criteria1.setBounds(270, 50, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria1);
		panelMainSearch_Criteria1.setColumns(10);
		
		panelMainSearch_Criteria2 = new JTextField();
		panelMainSearch_Criteria2.setBounds(270, 80, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria2);
		panelMainSearch_Criteria2.setColumns(10);
		
		panelMainSearch_Criteria3 = new JTextField();
		panelMainSearch_Criteria3.setBounds(270, 110, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria3);
		panelMainSearch_Criteria3.setColumns(10);
		
		panelMainSearch_Criteria4 = new JTextField();
		panelMainSearch_Criteria4.setBounds(270, 140, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria4);
		panelMainSearch_Criteria4.setColumns(10);
		
		panelMainSearch_Criteria5 = new JTextField();
		panelMainSearch_Criteria5.setBounds(270, 170, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria5);
		panelMainSearch_Criteria5.setColumns(10);
		
		JButton btnpanelMainSearch_Match = new JButton("Search");
		btnpanelMainSearch_Match.addActionListener(new MySearchListener());
		btnpanelMainSearch_Match.setBounds(150, 219, 150, 30);
		panelMainSearch.add(btnpanelMainSearch_Match);
	}
	
	public void onDrawPanelMain_InstantM(JLayeredPane pane){
		
		JPanel panelMainInstantM = new JPanel();
		panelMainInstantM.setBackground(new Color(65, 105, 225));
		panelMainInstantM.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMainInstantM.setBounds(452, 100, 450, 260);
		pane.add(panelMainInstantM);
		panelMainInstantM.setLayout(null);
		
		txtpanelMainInstantM_Message = new LimitField();
		txtpanelMainInstantM_Message.setText("Enter text here.....");
		txtpanelMainInstantM_Message.setBounds(10, 200, 340, 50);
		panelMainInstantM.add(txtpanelMainInstantM_Message);
		txtpanelMainInstantM_Message.setColumns(10);
		
		JButton btnpanelMainInstantM_Send = new JButton("SEND");
		btnpanelMainInstantM_Send.setBounds(360, 200, 80, 50);
		panelMainInstantM.add(btnpanelMainInstantM_Send);
		
		JLabel lblpanelMainInstantM_Title = new JLabel("Instant Messenger");
		lblpanelMainInstantM_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelMainInstantM_Title.setFont(new Font("Verdana", Font.BOLD, 15));
		lblpanelMainInstantM_Title.setBounds(10, 10, 430, 30);
		panelMainInstantM.add(lblpanelMainInstantM_Title);
		
		
		
		JTextArea textArea = new JTextArea(4,10);
		textArea.setBounds(112, 78, 103, 61);
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		panelMainInstantM.add(textArea);
	}
	
	public void onDrawPanelMain_Blind(JLayeredPane pane){
		
		JPanel panelMainBlind = new JPanel();
		panelMainBlind.setBackground(new Color(65, 105, 225));
		panelMainBlind.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMainBlind.setBounds(0, 362, 300, 200);
		pane.add(panelMainBlind);
		panelMainBlind.setLayout(null);
		
		JLabel lblpanelMainBlind_Title = new JLabel("Blind Match");
		lblpanelMainBlind_Title.setFont(new Font("Verdana", Font.BOLD, 15));
		lblpanelMainBlind_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelMainBlind_Title.setBounds(10, 11, 280, 40);
		panelMainBlind.add(lblpanelMainBlind_Title);
		
		JComboBox<?> panelMainBlind_CB1 = new JComboBox<Object>(comboBoxBlind);
		panelMainBlind_CB1.setBounds(69, 70, 155, 40);
		panelMainBlind.add(panelMainBlind_CB1);
		
		JButton btnpanelMainBlind_Match = new JButton("MATCH");
		btnpanelMainBlind_Match.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				changePanels(MainlayeredPane,SearchlayeredPane);
				onDrawSearchResults(panelSearchResults,userTests);
			}
		});
		btnpanelMainBlind_Match.setBounds(90, 139, 110, 50);
		panelMainBlind.add(btnpanelMainBlind_Match);
	}

	public void onDrawPanelMain_VipSearch(JLayeredPane pane){

		
		JPanel panelMainVipSearch = new JPanel();
		panelMainVipSearch.setBackground(new Color(0, 0, 139));
		panelMainVipSearch.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMainVipSearch.setBounds(301, 362, 300, 200);
		pane.add(panelMainVipSearch);
		panelMainVipSearch.setLayout(null);
	}
	
	public void onDrawPanelMain_Inbox(JLayeredPane pane){
		
		JPanel panelMainInbox = new JPanel();
		panelMainInbox.setBackground(new Color(0, 0, 255));
		panelMainInbox.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMainInbox.setBounds(602, 362, 300, 200);
		pane.add(panelMainInbox);
		panelMainInbox.setLayout(null);
	}
	
	public void onDrawPanelProfile(){
		
		ProfilelayeredPane = new JLayeredPane();
		frame.getContentPane().add(ProfilelayeredPane, "name_102424516773931");
		
		onDrawPanelTitle(ProfilelayeredPane);
		
		panelProfileMain = new JPanel();
		panelProfileMain.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelProfileMain.setBounds(0, 100, 902, 460);
		ProfilelayeredPane.add(panelProfileMain);
		panelProfileMain.setLayout(null);
	}

	public void onDrawPanelSearchResults(){
		
		SearchlayeredPane = new JLayeredPane();
		frame.getContentPane().add(SearchlayeredPane, "name_201721837465502");
		
		onDrawPanelTitle(SearchlayeredPane);
		
		panelSearchResults = new JPanel();
		panelSearchResults.setBounds(10, 100, 902, 462);
		SearchlayeredPane.add(panelSearchResults);
		panelSearchResults.setLayout(null);
		
		
		
		//onDrawSearchResults();
			
			
		
		/*panelSearchResults.setVisible(false);
		
		table = new JTable(setTableData(userTests),columnNames);
		table.setBounds(100, 50, 702, 96);
		panelSearchResults.add(table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		scrollPane.setBounds(100, 247, 719, -103);
		SearchlayeredPane.add(scrollPane);
		
		///JScrollPane scrollPane = new JScrollPane(table);
		//table.setFillsViewportHeight(true);*/
		
		
	}
	
	public void setScreenMargins(){
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		screenHeight = (int)screen.getHeight();
		screenWidth = (int)screen.getWidth(); 
		
	}
	
	public void changePanels(JLayeredPane panel1,JLayeredPane panel2){
		
		panel1.setVisible(false);
		panel2.setVisible(true);
	}
	
	public void setProfile(User user){
		
		firstname = user.getFName();
		surname = user.getLName();
		gender = user.getGender();
		age = user.getAge();
		location = user.getLocation();
		onDrawProfile();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JComboBox<?> cb = (JComboBox<?>) e.getSource();
		
		int choice = cb.getSelectedIndex();
		
		if((choice == 1)||(choice == 2)||(choice == 3)){
			
			new FormDetailsWindow(currentUser,choice);
			cb.setSelectedIndex(0);
		}
		else if(choice == 4){
			
			setProfile(currentUser);
			currentScreen = 2;
			changePanels(MainlayeredPane,ProfilelayeredPane);
			cb.setSelectedIndex(0);
		}
		
		
	}
	
//	public void onDrawSearchResults(JLayeredPane pane,User[] users){
//		
//		int x = 0;
//		
//		JLabel[] label_array_first = new JLabel[users.length];
//		JLabel[] label_array_sur = new JLabel[users.length];
//		JLabel[] label_array_age = new JLabel[users.length];
//		JLabel[] label_array_gender = new JLabel[users.length];
//		JLabel[] label_array_loc = new JLabel[users.length];
//		
//		
//		for(int i = 0;i < users.length;i++){
//			
//			label_array_first[i] = new JLabel(users[i].getFName());
//			label_array_first[i].setBounds(100, 100+x, 120, 20);
//			label_array_first[i].setOpaque(true);
//			pane.add(label_array_first[i]);
//			
//			label_array_sur[i] = new JLabel(users[i].getLName());
//			label_array_sur[i].setBounds(220, 100+x, 120, 20);
//			label_array_sur[i].setOpaque(true);
//			pane.add(label_array_sur[i]);
//			
//			label_array_age[i] = new JLabel(String.valueOf(users[i].getAge()));
//			label_array_age[i].setBounds(340, 100+x, 70, 20);
//			label_array_age[i].setOpaque(true);
//			pane.add(label_array_age[i]);
//			
//			label_array_gender[i] = new JLabel(users[i].getGender());
//			label_array_gender[i].setBounds(410, 100+x, 90, 20);
//			label_array_gender[i].setOpaque(true);
//			pane.add(label_array_gender[i]);
//			
//			label_array_loc[i] = new JLabel(users[i].getLocation());
//			label_array_loc[i].setBounds(500, 100+x, 120, 20);
//			label_array_loc[i].setOpaque(true);
//			pane.add(label_array_loc[i]);
//			
////			JButton btnNewButton = new JButton("View Profile");
////			btnNewButton.addActionListener(new ActionListener() {
////				public void actionPerformed(ActionEvent arg0) {
////					
////					
////					changePanels(SearchlayeredPane,ProfilelayeredPane);
////					
////				}
////			});
////			btnNewButton.setBounds(620, 100+x, 110, 20);
////			pane.add(btnNewButton);
//			
//			x = x+20;
//		}
//	}
	
public void onDrawSearchResults(JPanel pane,User[] users){
		
		
		JLabel lblpanelSearchResults_FirstNameTag = new JLabel("Firstname");
		lblpanelSearchResults_FirstNameTag.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblpanelSearchResults_FirstNameTag.setFont(new Font("Verdana", Font.BOLD, 12));
		lblpanelSearchResults_FirstNameTag.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelSearchResults_FirstNameTag.setBounds(100, 50, 120, 20);
		panelSearchResults.add(lblpanelSearchResults_FirstNameTag);
		
		JLabel lblpanelSearchResults_AgeTag = new JLabel("Age");
		lblpanelSearchResults_AgeTag.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblpanelSearchResults_AgeTag.setFont(new Font("Verdana", Font.BOLD, 12));
		lblpanelSearchResults_AgeTag.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelSearchResults_AgeTag.setBounds(340, 50, 70, 20);
		panelSearchResults.add(lblpanelSearchResults_AgeTag);
		
		JLabel lblpanelSearchResults_GenderTag = new JLabel("Gender");
		lblpanelSearchResults_GenderTag.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelSearchResults_GenderTag.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblpanelSearchResults_GenderTag.setFont(new Font("Verdana", Font.BOLD, 12));
		lblpanelSearchResults_GenderTag.setBounds(410, 50, 90, 20);
		panelSearchResults.add(lblpanelSearchResults_GenderTag);
		
		JLabel lblpanelSearchResults_LocTag = new JLabel("Location");
		lblpanelSearchResults_LocTag.setFont(new Font("Verdana", Font.BOLD, 12));
		lblpanelSearchResults_LocTag.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblpanelSearchResults_LocTag.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelSearchResults_LocTag.setBounds(500, 50, 120, 20);
		panelSearchResults.add(lblpanelSearchResults_LocTag);
		
		JLabel lblpanelSearchResults_ProfTag = new JLabel("Profile");
		lblpanelSearchResults_ProfTag.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelSearchResults_ProfTag.setFont(new Font("Verdana", Font.BOLD, 12));
		lblpanelSearchResults_ProfTag.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblpanelSearchResults_ProfTag.setBounds(620, 50, 110, 20);
		panelSearchResults.add(lblpanelSearchResults_ProfTag);
		
		JLabel lblpanelSearchResults_SurNameTag = new JLabel("Surname");
		lblpanelSearchResults_SurNameTag.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelSearchResults_SurNameTag.setFont(new Font("Verdana", Font.BOLD, 12));
		lblpanelSearchResults_SurNameTag.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lblpanelSearchResults_SurNameTag.setBounds(220, 50, 120, 20);
		panelSearchResults.add(lblpanelSearchResults_SurNameTag);
//		JLabel[] label_array_first = new JLabel[users.length];
//		JLabel[] label_array_sur = new JLabel[users.length];
//		JLabel[] label_array_age = new JLabel[users.length];
//		JLabel[] label_array_gender = new JLabel[users.length];
//		JLabel[] label_array_loc = new JLabel[users.length];
		int x = 0;
		
		for(int i = 0;i < users.length;i++){
			
			JLabel label_10 = new JLabel(users[i].getFName());
			label_10.setBounds(100, 100+x, 120, 20);
			label_10.setOpaque(true);
			label_10.setBackground(Color.WHITE);
			label_10.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pane.add(label_10);
			
			JLabel label_11 = new JLabel(users[i].getLName());
			label_11.setBounds(220, 100+x, 120, 20);
			label_11.setOpaque(true);
			label_11.setBackground(Color.WHITE);
			label_11.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pane.add(label_11);
			
			JLabel label_12 = new JLabel(String.valueOf(users[i].getAge()));
			label_12.setBounds(340, 100+x, 70, 20);
			label_12.setOpaque(true);
			label_12.setBackground(Color.WHITE);
			label_12.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pane.add(label_12);
			
			JLabel label_13 = new JLabel(users[i].getGender());
			label_13.setBounds(410, 100+x, 90, 20);
			label_13.setOpaque(true);
			label_13.setBackground(Color.WHITE);
			label_13.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pane.add(label_13);
			
			JLabel label_14 = new JLabel(users[i].getLocation());
			label_14.setBounds(500, 100+x, 120, 20);
			label_14.setOpaque(true);
			label_14.setBackground(Color.WHITE);
			label_14.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			pane.add(label_14);
			
			JButton btnNewButton = new JButton("View Profile");
			btnNewButton.addActionListener( new MyButtonListener(i));
			btnNewButton.setBounds(620, 100+x, 110, 20);
			pane.add(btnNewButton);
			
			x = x+20;
		}
	}

	public void onDrawProfile(){
		
		panelProfileMain.removeAll();
		panelProfileMain.revalidate();
		
		JLabel lblpanelProfileMain_Picture = new JLabel("PIC");
		lblpanelProfileMain_Picture.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelProfileMain_Picture.setBounds(100, 60, 100, 100);
		lblpanelProfileMain_Picture.setOpaque(true);
		lblpanelProfileMain_Picture.setBackground(Color.WHITE);
		panelProfileMain.add(lblpanelProfileMain_Picture);
		
		JLabel lblpanelProfileMain_NameTag = new JLabel("Name :");
		lblpanelProfileMain_NameTag.setBounds(300, 60, 130, 20);
		panelProfileMain.add(lblpanelProfileMain_NameTag);
		
		JLabel lblpanelProfileMain_AgeTag = new JLabel("Age :");
		lblpanelProfileMain_AgeTag.setBounds(300, 140, 130, 20);
		panelProfileMain.add(lblpanelProfileMain_AgeTag);
		
		JLabel lblpanelProfileMain_GenderTag = new JLabel("Gender :");
		lblpanelProfileMain_GenderTag.setBounds(300, 100, 130, 20);
		panelProfileMain.add(lblpanelProfileMain_GenderTag);
		
		JLabel lblpanelProfileMain_LocationTag = new JLabel("Location :");
		lblpanelProfileMain_LocationTag.setBounds(300, 180, 130, 20);
		panelProfileMain.add(lblpanelProfileMain_LocationTag);
		
		JLabel lblpanelProfileMain_SexPrefTag = new JLabel("Sexual Preference :");
		lblpanelProfileMain_SexPrefTag.setBounds(300, 220, 130, 20);
		panelProfileMain.add(lblpanelProfileMain_SexPrefTag);
		
		JLabel lblpanelProfileMain_SportTag = new JLabel("Sports :");
		lblpanelProfileMain_SportTag.setBounds(300, 260, 130, 20);
		panelProfileMain.add(lblpanelProfileMain_SportTag);
		
		JLabel lblpanelProfileMain_FilmTag = new JLabel("Films : ");
		lblpanelProfileMain_FilmTag.setBounds(300, 300, 130, 20);
		panelProfileMain.add(lblpanelProfileMain_FilmTag);
		
		JLabel lblpanelProfileMain_Name = new JLabel(firstname+" "+surname);
		lblpanelProfileMain_Name.setBackground(Color.WHITE);
		lblpanelProfileMain_Name.setOpaque(true);
		lblpanelProfileMain_Name.setBounds(480, 60, 240, 20);
		panelProfileMain.add(lblpanelProfileMain_Name);
		
		JLabel lblpanelProfileMain_Gender = new JLabel(gender);
		lblpanelProfileMain_Gender.setBackground(Color.WHITE);
		lblpanelProfileMain_Gender.setBounds(480, 100, 240, 20);
		lblpanelProfileMain_Gender.setOpaque(true);
		panelProfileMain.add(lblpanelProfileMain_Gender);
		
		JLabel lblpanelProfileMain_Age = new JLabel(String.valueOf(age));
		lblpanelProfileMain_Age.setBackground(Color.WHITE);
		lblpanelProfileMain_Age.setBounds(480, 140, 240, 20);
		lblpanelProfileMain_Age.setOpaque(true);
		panelProfileMain.add(lblpanelProfileMain_Age);
		
		JLabel lblpanelProfileMain_Location = new JLabel(location);
		lblpanelProfileMain_Location.setBackground(Color.WHITE);
		lblpanelProfileMain_Location.setBounds(480, 180, 240, 20);
		lblpanelProfileMain_Location.setOpaque(true);
		panelProfileMain.add(lblpanelProfileMain_Location);
		
		JLabel lblpanelProfileMain_SexPref = new JLabel(sex_Pref);
		lblpanelProfileMain_SexPref.setBackground(Color.WHITE);
		lblpanelProfileMain_SexPref.setBounds(480, 220, 240, 20);
		lblpanelProfileMain_SexPref.setOpaque(true);
		panelProfileMain.add(lblpanelProfileMain_SexPref);
		
		JLabel lblpanelProfileMain_Sports = new JLabel(sports);
		lblpanelProfileMain_Sports.setBackground(Color.WHITE);
		lblpanelProfileMain_Sports.setBounds(480, 260, 240, 20);
		lblpanelProfileMain_Sports.setOpaque(true);
		panelProfileMain.add(lblpanelProfileMain_Sports);
		
		JLabel lblpanelProfileMain_Films = new JLabel(films);
		lblpanelProfileMain_Films.setBackground(Color.WHITE);
		lblpanelProfileMain_Films.setBounds(480, 300, 240, 20);
		lblpanelProfileMain_Films.setOpaque(true);
		panelProfileMain.add(lblpanelProfileMain_Films);
	}

}
