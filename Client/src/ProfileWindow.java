import Base.*;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import static java.security.AccessController.getContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.security.auth.callback.Callback;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;


public class ProfileWindow extends UnicastRemoteObject implements ActionListener,ClientInterface{

	//-----------------------------------Variables for screen dimensions and drawing content----------------------------------------------//
	static ServiceInterface serviceInstance;
        private JFrame frame;
	private int screenWidth;
	private int screenHeight;
	private int currentScreen;
        int leftMargin;
        int topMargin;
	private int x_im = 10;
	private int y_im = 10;
	private JTextArea textArea_Enter;
	private JScrollPane scrollPaneIM;
	
	//--------------------------------------Variables for 	
	private JLayeredPane MainlayeredPane;
	private JLayeredPane ProfilelayeredPane;
	private JLayeredPane SearchlayeredPane;
	
	private JPanel panelSearchResults;
	private JPanel panelProfileMain;
        
	private Calendar cal = Calendar.getInstance();
        private HashMap prefMap;
        private ArrayList<User> onlineUsers = new ArrayList<User>();
	
	private User currentUser; //= new User("gareth", "twat", "male", 1, 1, 34, cal, "Leiden",prefMap);
	private User testUser_01 = new User("Kylie", "Minogue", "Female", 1, 1, 34, cal, "Leiden",prefMap,"Male");
	//private User testUser_02 = new User("jaimie", "twat", "male", 1, 1, 34, cal, "Leiden", prefMap,"Male");
	//private User testUser_03 = new User("todd", "twat", "male", 1, 1, 34, cal, "Leiden", prefMap,"Both");
	
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
        
        private JComboBox<?> panelMainBlind_CB1;
        
        private ArrayList<String> sports1 = new ArrayList<String>();
	private ArrayList<String> music1 = new ArrayList<String>();
	private ArrayList<String> films1 = new ArrayList<String>();
        
	private String[] comboBoxSearch = {"Firstname","Surname","Sport","Music","Film"};
	private String[] comboBoxBlind = {"Age","Location"};
	
	//----------------------------------Variables for setting the current profile being shown ------------------------------------------------------------//
	
	private String firstname = "";
	private String surname = "";
	private String gender = "";
	private int age = 0;
	private String location = "";
	private String sexPref = "";
	private String films = "";
	private String sports = "";
        private String music = "";
	private JTextField txtpanelMainInstantM_Message;
	private JTable table;
	
	
	private String[] columnNames = {"Firstname", "Surname", "Age", "Gender", "Location"};
	private JPanel panelMainInstantM_Output;
	private JPanel panel;
	private JLabel lblHello;
	private JLabel lblWorld;
	private JList<String> list;
	DefaultListModel<String> listModel = new DefaultListModel<String>();
	//private User[] userTests = {testUser_01,testUser_02,testUser_03};
	/**
	 * Launch the application.
        * @param args
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
	public ProfileWindow() throws RemoteException{
		initialize();
		frame.setVisible(true);
		currentScreen = 1;
	}
	
	public ProfileWindow(User user, int number) throws RemoteException{
		
            if(number == 2){
                    
                currentUser = user;
		initialize();
		frame.setVisible(true);
		
            }
            
            else if(number == 1){
                
                //initialize();
		//frame.setVisible(true);
                currentUser = user;
                setRmiClient();
                
            }
            
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		setScreenMargins();
                leftMargin = (screenWidth-918)/2;
                topMargin = (screenHeight-600)/5;
                
		frame.setBounds(0, 0, screenWidth, screenHeight);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
	
		onDrawPanelMain();
		
		onDrawPanelProfile();
		
		onDrawPanelSearchResults();
        }
	
        
        public class MyButtonListener implements ActionListener{
		
		User user;
		
		public MyButtonListener(User user){
			this.user = user;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			setProfile(this.user);
			changePanels(SearchlayeredPane,ProfilelayeredPane);
			
		}
	}
	
	public class MySearchListener implements ActionListener{
		
		int number;
		
		public MySearchListener(int number){
                    this.number = number;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
                    
                    if(number == 0){
			int choice1 = panelMainSearch_CB1.getSelectedIndex();
//			int choice2 = panelMainSearch_CB2.getSelectedIndex();
//			int choice3 = panelMainSearch_CB3.getSelectedIndex();
//			int choice4 = panelMainSearch_CB4.getSelectedIndex();
//			int choice5 = panelMainSearch_CB5.getSelectedIndex();
			
			if(panelMainSearch_Criteria1 != null&&((choice1 == 1)||(choice1 == 0))){
				nameSearch(panelMainSearch_Criteria1.getText());
				
			}
                    }
                    if (number == 1){
                        int choice = panelMainBlind_CB1.getSelectedIndex();
                        
                        
                            try {
                            //Create a reference to the service interface at the location.
                            ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://127.0.0.1/DateServer");
                            //Create a response object
                            Response res = new Response();
                            if(choice == 0){
                                //Invoke server blindAgeMatch method
                               // res = service.blindAgeMatch(currentUser,"male");
                            }
                            if(choice == 1){
                                //Invoke server blindAgeMatch method
                               // res = service.blindLocationMatch(currentUser,"female");
                            }
                                //Test response
                                if (res.getError() != null) {
                                        System.out.println(res.getError());
                                        System.out.println("There was an error.");
                                }
                                else {
                                        System.out.println("Everything went okay.");
                                        System.out.println(res.getResponse());
                                        ArrayList<User> usersFound = (ArrayList<User>)res.getResponse();
                                        if(usersFound != null){
                                            usersFound = (ArrayList<User>) res.getResponse();
                                            User[] userArray = new User[usersFound.size()];
                                            onDrawSearchResults(panelSearchResults,userArray);
                                        }
                                        else
                                            noResults();
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
	}
	
	public void onDrawPanelTitle(JLayeredPane pane){
		
		JPanel panelProfileTitle = new JPanel();
		panelProfileTitle.setBackground(new Color(242, 62, 62));
		panelProfileTitle.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelProfileTitle.setBounds(0+leftMargin, 0+topMargin, 902, 99);
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
                btnpanelProfileTitle_LogOut.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        LogOff();
                    }
                });
		panelProfileTitle.add(btnpanelProfileTitle_LogOut);
	
	}
	
	public void onDrawPanelMain(){
		
		MainlayeredPane = new JLayeredPane();
                MainlayeredPane.setOpaque(true);
                MainlayeredPane.setBackground(new Color(171,37,37));
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
		panelMainSearch.setBackground(new Color(255, 216, 216));
		panelMainSearch.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelMainSearch.setBounds(0+leftMargin, 100+topMargin, 360, 260);
		pane.add(panelMainSearch);
		panelMainSearch.setLayout(null);
		
		JLabel lblpanelMainSearch_Title = new JLabel("Search Profiles");
		lblpanelMainSearch_Title.setFont(new Font("Verdana", Font.BOLD, 15));
		lblpanelMainSearch_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelMainSearch_Title.setBounds(10, 10, 340, 30);
		panelMainSearch.add(lblpanelMainSearch_Title);
		
		panelMainSearch_CB1 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB1.setBounds(20, 50, 120, 20);
		
		panelMainSearch.add(panelMainSearch_CB1);
		
		panelMainSearch_CB2 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB2.setBounds(20, 80, 120, 20);
		//panelMainSearch_CB2.addActionListener(this);
		panelMainSearch.add(panelMainSearch_CB2);
		
		panelMainSearch_CB3 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB3.setBounds(20, 110, 120, 20);
		//panelMainSearch_CB3.addActionListener(this);
		panelMainSearch.add(panelMainSearch_CB3);
		
		panelMainSearch_CB4 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB4.setBounds(20, 140, 120, 20);
		//panelMainSearch_CB4.addActionListener(this);
		panelMainSearch.add(panelMainSearch_CB4);
		
		panelMainSearch_CB5 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB5.setBounds(20, 170, 120, 20);
		//panelMainSearch_CB5.addActionListener(this);
		panelMainSearch.add(panelMainSearch_CB5);
		
		panelMainSearch_Criteria1 = new JTextField();
		panelMainSearch_Criteria1.setBounds(190, 50, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria1);
		panelMainSearch_Criteria1.setColumns(10);
		
		panelMainSearch_Criteria2 = new JTextField();
		panelMainSearch_Criteria2.setBounds(190, 80, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria2);
		panelMainSearch_Criteria2.setColumns(10);
		
		panelMainSearch_Criteria3 = new JTextField();
		panelMainSearch_Criteria3.setBounds(190, 110, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria3);
		panelMainSearch_Criteria3.setColumns(10);
		
		panelMainSearch_Criteria4 = new JTextField();
		panelMainSearch_Criteria4.setBounds(190, 140, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria4);
		panelMainSearch_Criteria4.setColumns(10);
		
		panelMainSearch_Criteria5 = new JTextField();
		panelMainSearch_Criteria5.setBounds(190, 170, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria5);
		panelMainSearch_Criteria5.setColumns(10);
		
		JButton btnpanelMainSearch_Match = new JButton("Search");
		btnpanelMainSearch_Match.addActionListener(new MySearchListener(0));
		btnpanelMainSearch_Match.setBounds(100, 219, 150, 30);
		panelMainSearch.add(btnpanelMainSearch_Match);
	}
	
	public void onDrawPanelMain_InstantM(JLayeredPane pane){
		
		
		JPanel panelMainInstantM = new JPanel();
		panelMainInstantM.setBackground(new Color(255, 216, 216));
		panelMainInstantM.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelMainInstantM.setBounds(361+leftMargin, 100+topMargin, 541, 260);
		pane.add(panelMainInstantM);
		panelMainInstantM.setLayout(null);
		
		
		panelMainInstantM_Output = new JPanel();
		panelMainInstantM_Output.setBounds(10, 50, 430, 150);
		panelMainInstantM_Output.setVisible(true);
		panelMainInstantM_Output.setPreferredSize(new Dimension(430,140));
		panelMainInstantM_Output.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		//panelMainInstantM.add(panelMainInstantM_Output);
		panelMainInstantM_Output.setLayout(null);
		
		textArea_Enter = new JTextArea(2,10);
		textArea_Enter.setDocument(new LimitDocument(80));
                textArea_Enter.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea_Enter.setBounds(10, 210, 300, 40);
		textArea_Enter.setWrapStyleWord(true);
		textArea_Enter.setLineWrap(true);
		panelMainInstantM.add(textArea_Enter);
		
		/*lblWorld = new JLabel("world");
		lblWorld.setBounds(300, 40, 120, 20);
		panelMainInstantM_Output.add(lblWorld);*/
		
		/*txtpanelMainInstantM_Message = new LimitField();
		txtpanelMainInstantM_Message.setText("Enter text here.....");
		txtpanelMainInstantM_Message.setBounds(10, 200, 340, 50);
		panelMainInstantM.add(txtpanelMainInstantM_Message);
		txtpanelMainInstantM_Message.setColumns(10);*/
		
		class SendListener implements ActionListener{
			
			String mess;
			JTextArea jt;
			
			public SendListener(JTextArea message){
				super();
				this.jt = message;
				
			}
			
			public void actionPerformed(ActionEvent e) {
				
//				JTextArea textArea = new JTextArea(2,10);
//				textArea.setText(this.message);
//				textArea.setBounds(10, 210, 0, 0);
//				textArea.setWrapStyleWord(true);
//				textArea.setLineWrap(true);
//				panelMainInstantM_Output.add(textArea);
				//panelMainInstantM_Output.updateUI();
				
                               
                            
				this.mess = this.jt.getText();
                                
                                int check = list.getSelectedIndex();
                                
                                User user = onlineUsers.get(check);
                                
                                Mail mail = new Mail(currentUser,user,this.mess);  	
                                
                                sendMail(mail);
                                
                                receiveMail(mail);
				//testMail(mail);
				
			}
			
		}
		
		JButton btnpanelMainInstantM_Send = new JButton("SEND");
		btnpanelMainInstantM_Send.addActionListener(new SendListener(textArea_Enter));
		btnpanelMainInstantM_Send.setBounds(320, 210, 100, 40);
		panelMainInstantM.add(btnpanelMainInstantM_Send);
		
		JLabel lblpanelMainInstantM_Title = new JLabel("Instant Messenger");
		lblpanelMainInstantM_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelMainInstantM_Title.setFont(new Font("Verdana", Font.BOLD, 15));
		lblpanelMainInstantM_Title.setBounds(10, 10, 430, 30);
		panelMainInstantM.add(lblpanelMainInstantM_Title);
		
		scrollPaneIM = new JScrollPane();
		scrollPaneIM.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneIM.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		scrollPaneIM.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneIM.setViewportView(panelMainInstantM_Output);
		scrollPaneIM.setBounds(10, 50, 430, 150);
		panelMainInstantM.add(scrollPaneIM);
		
		list = new JList(listModel);
		list.setVisibleRowCount(3);
		list.setBounds(450, 50, 80, 150);
                list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panelMainInstantM.add(list);
		
		JButton btnNewButton_1 = new JButton("REFRESH");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				updateList();
			}
		});
		btnNewButton_1.setBounds(430, 210, 100, 40);
		panelMainInstantM.add(btnNewButton_1);
		
		
		
	}
	
	public void onDrawPanelMain_Blind(JLayeredPane pane){
		
		JPanel panelMainBlind = new JPanel();
		panelMainBlind.setBackground(new Color(255, 232, 232));
		panelMainBlind.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelMainBlind.setBounds(0+leftMargin, 362+topMargin, 300, 200);
		pane.add(panelMainBlind);
		panelMainBlind.setLayout(null);
		
		JLabel lblpanelMainBlind_Title = new JLabel("Blind Match");
		lblpanelMainBlind_Title.setFont(new Font("Verdana", Font.BOLD, 15));
		lblpanelMainBlind_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelMainBlind_Title.setBounds(10, 11, 280, 40);
		panelMainBlind.add(lblpanelMainBlind_Title);
		
		panelMainBlind_CB1 = new JComboBox<Object>(comboBoxBlind);
		panelMainBlind_CB1.setBounds(69, 70, 155, 40);
		panelMainBlind.add(panelMainBlind_CB1);
		
		JButton btnpanelMainBlind_Match = new JButton("MATCH");
		btnpanelMainBlind_Match.addActionListener(new MySearchListener(1));
		btnpanelMainBlind_Match.setBounds(90, 139, 110, 50);
		panelMainBlind.add(btnpanelMainBlind_Match);
	}

	public void onDrawPanelMain_VipSearch(JLayeredPane pane){

		
		JPanel panelMainVipSearch = new JPanel();
		panelMainVipSearch.setBackground(new Color(255, 232, 232));
		panelMainVipSearch.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelMainVipSearch.setBounds(301+leftMargin, 362+topMargin, 300, 200);
		pane.add(panelMainVipSearch);
		panelMainVipSearch.setLayout(null);
                
                JLabel panelMainVipSearch_label = new JLabel("V.I.P. SEARCH");
                panelMainVipSearch_label.setBounds(90, 50, 120, 50);
                panelMainVipSearch_label.setFont(new Font("Verdana", Font.BOLD, 15));
                panelMainVipSearch.add(panelMainVipSearch_label);
                
                JButton panelMainVipSearch_button = new JButton("FULL MATCH");
                panelMainVipSearch_button.setBounds(80, 120, 140, 50);
                panelMainVipSearch.add(panelMainVipSearch_button);
                
                
	}
	
	public void onDrawPanelMain_Inbox(JLayeredPane pane){
		
		JPanel panelMainInbox = new JPanel();
		panelMainInbox.setBackground(new Color(255, 232, 232));
		panelMainInbox.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelMainInbox.setBounds(602+leftMargin, 362+topMargin, 300, 200);
		pane.add(panelMainInbox);
		panelMainInbox.setLayout(null);
	}
	
	public void onDrawPanelProfile(){
		
		ProfilelayeredPane = new JLayeredPane();
		frame.getContentPane().add(ProfilelayeredPane, "name_102424516773931");
                ProfilelayeredPane.setBackground(new Color(212,54,54));
                ProfilelayeredPane.setOpaque(true);
		
		onDrawPanelTitle(ProfilelayeredPane);
		
		panelProfileMain = new JPanel();
		panelProfileMain.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panelProfileMain.setBounds(0+leftMargin, 100+topMargin, 902, 460);
		ProfilelayeredPane.add(panelProfileMain);
		panelProfileMain.setLayout(null);
	}

	public void onDrawPanelSearchResults(){
		
		SearchlayeredPane = new JLayeredPane();
		frame.getContentPane().add(SearchlayeredPane, "name_201721837465502");
                SearchlayeredPane.setBackground(new Color(212,54,54));
                SearchlayeredPane.setOpaque(true);
		
		onDrawPanelTitle(SearchlayeredPane);
		
		panelSearchResults = new JPanel();
		panelSearchResults.setBounds(leftMargin, 100+topMargin, 902, 462);
                panelSearchResults.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
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
                sexPref = user.getSexPref();
                try{
                System.out.println(user.getPreferencesMap().size());}
                catch(NullPointerException e){
                    if(user.getPreferencesMap()== null)
                        System.out.print("false");
                    else
                        System.out.print("true");
                }
                films = user.getPreferencesMap().get("films").toString();
                films = films.replace("[","");
                films = films.replace("]","");
                music = user.getPreferencesMap().get("music").toString();
                music = music.replace("[","");
                music = music.replace("]","");
                sports = user.getPreferencesMap().get("sport").toString();
                sports = sports.replace("[","");
                sports = sports.replace("]","");
		onDrawProfile();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JComboBox<String> cb = (JComboBox<String>) e.getSource();
		
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
	
    /**
     *
     * @param pane
     * @param users
     */
    	
public void onDrawSearchResults(JPanel pane,User[] users){
		
		
                changePanels(MainlayeredPane,SearchlayeredPane);
    
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
			btnNewButton.addActionListener( new MyButtonListener(users[i]));
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
		
		JLabel lblpanelProfileMain_SexPref = new JLabel(sexPref);
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

	public void nameSearch(String keyword){
            try {
	            //Create a reference to the service interface at the location.
	            ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://127.0.0.1/DateServer");
	            //Create a response object
	            Response res = new Response();
	            //Invoke server SignUp method
	            res = service.nameSearch(keyword);
	            //Test response
	            if (res.getError() != null) {
	                    System.out.println(res.getError());
	                    System.out.println("There was an error.");
	            }
	            else {
	                    System.out.println("Everything went okay.");
	                    ArrayList<User> usersFound = new ArrayList<User>();
	                    usersFound = (ArrayList<User>) res.getResponse();
	                    User[] userArray = new User[usersFound.size()];
	                    usersFound.toArray(userArray);
	                    
	                    onDrawSearchResults(panelSearchResults,userArray);
	                    
            }
            } catch (NotBoundException ex) {
                    System.out.println(ex);
            } catch (MalformedURLException ex) {
                    System.out.println(ex);
            } catch (RemoteException ex) {
                    System.out.println(ex);
            }

        }
        
        public void noResults(){
            
            JOptionPane.showMessageDialog(null, "No matches were found, please check Account Settings>>Edit preferences and make sure details are entered", "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        
        public void LogOff(){
            
            try{
                 ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://127.0.0.1/DateServer");
                 
                 Response res = new Response();
                 
                 res = service.Logoff(currentUser.getUserid());
                 
                 if((boolean)res.getResponse()){
                     new MainWindow();
                     frame.dispose();
                 }
                 else
                     return;
            }
            catch(Exception e){
                
            }
        }
        
        public void sendMail(Mail mail){
        	
            try{
                ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://127.0.0.1/DateServer");
                
                Response res = new Response();
                
                service.sendMail(mail);
             
            }
            catch (NotBoundException ex) {
                    System.out.println(ex);
            } 
            catch (MalformedURLException ex) {
                    System.out.println(ex);
            } 
            catch (RemoteException ex) {
                    System.out.println(ex);
            }
            
        }
        
        public void refreshList(ArrayList<User> usersOnline){
        	
        	onlineUsers.clear();
                listModel.clear();
                
                onlineUsers = (ArrayList<User>) usersOnline.clone();
                
        	try{
	            User[] userArray = new User[usersOnline.size()];
	            usersOnline.toArray(userArray);
	        	
	        	for(int i = 0;i < userArray.length;i++){
	        		
	        		listModel.addElement(userArray[i].getFName());
	        	}
                        //System.out.println(userArray[1].getFName());
	        	
        	}
        	catch (NullPointerException excep){
        		System.out.print("no users online");
                        System.out.println("\nuser list works yeah!");
        		return;
        	}
        	/*finally{
                        list.repaint();
                        list.revalidate();
        		list.validate();
                        
        	}*/
    		//listModel.addElement("Jane Doe");
    		//listModel.addElement("John Smith");
    		//listModel.addElement("Kathy Green");
        	
        }
        
        public void updateList(){
            
            try{
                ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://127.0.0.1/DateServer");
                
                Response res = new Response();
                
                res = service.getOnlineUsers(currentUser);
                
                if (res.getError() != null) {
	                    System.out.println(res.getError());
	                    System.out.println("There was an error.");
	        }
	        else {
	                    System.out.println("Everything went okay.");
                            ArrayList<User> arr = (ArrayList<User>) res.getResponse();
                            refreshList(arr);
                            
	                    
                }
            }
            catch (NotBoundException ex) {
                    System.out.println(ex);
            } 
            catch (MalformedURLException ex) {
                    System.out.println(ex);
            } 
            catch (RemoteException ex) {
                    System.out.println(ex);
            }
        }
        
        public void setRmiClient(){
            
            try{


                Response res = new Response();
                


                ServerSocket port = new ServerSocket(0);
            
                LocateRegistry.createRegistry(port.getLocalPort()+1);

                
                ClientInterface client = new ProfileWindow(currentUser,2);
                
                Naming.rebind("DateClient"+currentUser.getUserid(), client);
                


                String ip = "rmi://"+InetAddress.getLocalHost().getHostAddress()+"/DateClient"+currentUser.getUserid();

                //String ip = InetAddress.getLocalHost().getHostAddress();

                
                ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://127.0.0.1/DateServer");
                
                res = service.setClientRmi(ip, currentUser);

                
                


                //String url = "rmi://"+InetAddress.getLocalHost().getHostAddress()+"/DateClient";

                
            }
            catch(IOException exp){
                
            }

            catch(NotBoundException exp){
                
            }
        }
        
        public void receiveMail(Mail mail){
            
            JLabel label = new JLabel();
            label.setOpaque(true);
            JTextArea textArea = new JTextArea(mail.getContent());
            textArea.setWrapStyleWord(true);
            textArea.setLineWrap(true);
            if(mail.getSender().getUserid() != currentUser.getUserid()){
                x_im = 120;
                if(mail.getSender().getGender() == "Female")
                    label.setBackground(Color.PINK);
                else
                    label.setBackground(Color.BLUE);
                label.setText("From : "+mail.getSender().getFName());
            }
            else{
		x_im = 40;
		label.setBackground(Color.GREEN);
		label.setText("To : "+mail.getReciever().getFName());
	    }
				
            if(mail.getContent().length() <= 50){
		label.setBounds(x_im, y_im, 100, 20);
		textArea.setBounds(x_im, y_im+20, 300, 20);
		y_im = y_im+45;
            }
				
            else if(mail.getContent().length() > 50){
					
                label.setBounds(x_im, y_im, 100, 20);
					
                textArea.setBounds(x_im, y_im+20, 300, 40);
					
                y_im = y_im+65;
            }
				
            label.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
				
            textArea.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
	
            textArea.setVisible(true);
	
            textArea.setOpaque(true);
	
            if(y_im > 140)
                    panelMainInstantM_Output.setPreferredSize(new Dimension(430,y_im+65));
				

            textArea_Enter.setText("");
	
            panelMainInstantM_Output.add(label);
	
            panelMainInstantM_Output.add(textArea);
				

	
            panelMainInstantM_Output.repaint();
	
            panelMainInstantM_Output.validate();
	
            panelMainInstantM_Output.revalidate();
				
            panelMainInstantM_Output.scrollRectToVisible(new Rectangle(x_im,y_im,430,140));
                                



				panelMainInstantM_Output.repaint();
				panelMainInstantM_Output.validate();
				panelMainInstantM_Output.revalidate();
				panelMainInstantM_Output.scrollRectToVisible(new Rectangle(x_im,y_im,430,140));

        }
        
         @Override
        public void updateUsers(ArrayList<User> users) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
