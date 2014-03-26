import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;


public class ProfileWindow implements ActionListener{

	//-----------------------------------Variables for screen dimensions and drawing content----------------------------------------------//
	private JFrame frame;
	private int screenWidth;
	private int screenHeight;
	//--------------------------------------Variables for 	
	private JLayeredPane MainlayeredPane;
	private JLayeredPane ProfilelayeredPane;
	
	private User currentUser = new User();
	
	private JButton btnpanelProfileTitle_Home;
	
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
	private String[] comboBoxSettings = {"Account Settings","Edit Personal Details","Edit Preferences","Edit Account Details","View Profile"};
	
	//----------------------------------Variables for setting the current profile being shown ------------------------------------------------------------//
	
	private String firstname = "";
	private String surname = "";
	private String gender = "";
	private int age = 0;
	private String location = "";
	private String sex_Pref = "";
	private String films = "";
	private String sports = "";
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
	}
	
	public ProfileWindow(User user){
		
		initialize();
		frame.setVisible(true);
		
		
		//if(user.getLevel() == 1){
			panelMainSearch_Criteria2.setVisible(false);
			panelMainSearch_Criteria3.setVisible(false);
			panelMainSearch_Criteria4.setVisible(false);
			panelMainSearch_Criteria5.setVisible(false);
			
			panelMainSearch_CB2.setVisible(false);
			panelMainSearch_CB3.setVisible(false);
			panelMainSearch_CB4.setVisible(false);
			panelMainSearch_CB5.setEnabled(false);
		//}
		
		
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
	}
	
	public void onDrawPanelTitle(JLayeredPane pane){
		
		JPanel panelProfileTitle = new JPanel();
		panelProfileTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelProfileTitle.setBounds(0, 0, 902, 100);
		pane.add(panelProfileTitle);
		panelProfileTitle.setLayout(null);
		
		JLabel lblpanelTitle_Dare2Date = new JLabel("Dare2Date");
		lblpanelTitle_Dare2Date.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 50));
		lblpanelTitle_Dare2Date.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelTitle_Dare2Date.setBounds(201, 20, 500, 60);
		panelProfileTitle.add(lblpanelTitle_Dare2Date);
		
		JComboBox<?> comboBoxpanelTitle_Settings = new JComboBox<Object>(comboBoxSettings);
		comboBoxpanelTitle_Settings.setBounds(740, 54, 140, 25);
		comboBoxpanelTitle_Settings.addActionListener(this);
		panelProfileTitle.add(comboBoxpanelTitle_Settings);
		
		btnpanelProfileTitle_Home = new JButton("HOME");
		btnpanelProfileTitle_Home.setBounds(20, 20, 80, 23);
		btnpanelProfileTitle_Home.setVisible(false);
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
		panelMainSearch.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMainSearch.setBounds(0, 100, 450, 260);
		pane.add(panelMainSearch);
		panelMainSearch.setLayout(null);
		
		JLabel lblpanelMainSearch_Title = new JLabel("Search Profiles");
		lblpanelMainSearch_Title.setFont(new Font("Verdana", Font.BOLD, 15));
		lblpanelMainSearch_Title.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelMainSearch_Title.setBounds(10, 10, 430, 40);
		panelMainSearch.add(lblpanelMainSearch_Title);
		
		panelMainSearch_CB1 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB1.setBounds(20, 60, 150, 20);
		panelMainSearch.add(panelMainSearch_CB1);
		
		panelMainSearch_CB2 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB2.setBounds(20, 95, 150, 20);
		panelMainSearch.add(panelMainSearch_CB2);
		
		panelMainSearch_CB3 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB3.setBounds(20, 130, 150, 20);
		panelMainSearch.add(panelMainSearch_CB3);
		
		panelMainSearch_CB4 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB4.setBounds(20, 165, 150, 20);
		panelMainSearch.add(panelMainSearch_CB4);
		
		panelMainSearch_CB5 = new JComboBox<Object>(comboBoxSearch);
		panelMainSearch_CB5.setBounds(20, 200, 150, 20);
		panelMainSearch.add(panelMainSearch_CB5);
		
		panelMainSearch_Criteria1 = new JTextField();
		panelMainSearch_Criteria1.setBounds(230, 60, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria1);
		panelMainSearch_Criteria1.setColumns(10);
		
		panelMainSearch_Criteria2 = new JTextField();
		panelMainSearch_Criteria2.setBounds(230, 95, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria2);
		panelMainSearch_Criteria2.setColumns(10);
		
		panelMainSearch_Criteria3 = new JTextField();
		panelMainSearch_Criteria3.setBounds(230, 130, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria3);
		panelMainSearch_Criteria3.setColumns(10);
		
		panelMainSearch_Criteria4 = new JTextField();
		panelMainSearch_Criteria4.setBounds(230, 165, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria4);
		panelMainSearch_Criteria4.setColumns(10);
		
		panelMainSearch_Criteria5 = new JTextField();
		panelMainSearch_Criteria5.setBounds(230, 200, 150, 20);
		panelMainSearch.add(panelMainSearch_Criteria5);
		panelMainSearch_Criteria5.setColumns(10);
		
		JButton btnpanelMainSearch_Match = new JButton("Search");
		btnpanelMainSearch_Match.setBounds(230, 231, 150, 18);
		panelMainSearch.add(btnpanelMainSearch_Match);
	}
	
	public void onDrawPanelMain_InstantM(JLayeredPane pane){
		
		JPanel panelMainInstantM = new JPanel();
		panelMainInstantM.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMainInstantM.setBounds(452, 100, 450, 260);
		pane.add(panelMainInstantM);
		panelMainInstantM.setLayout(null);
	}
	
	public void onDrawPanelMain_Blind(JLayeredPane pane){
		
		JPanel panelMainBlind = new JPanel();
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
		btnpanelMainBlind_Match.setBounds(90, 139, 110, 50);
		panelMainBlind.add(btnpanelMainBlind_Match);
	}

	public void onDrawPanelMain_VipSearch(JLayeredPane pane){

		
		JPanel panelMainVipSearch = new JPanel();
		panelMainVipSearch.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMainVipSearch.setBounds(301, 362, 300, 200);
		pane.add(panelMainVipSearch);
		panelMainVipSearch.setLayout(null);
	}
	
	public void onDrawPanelMain_Inbox(JLayeredPane pane){
		
		JPanel panelMainInbox = new JPanel();
		panelMainInbox.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelMainInbox.setBounds(602, 362, 300, 200);
		pane.add(panelMainInbox);
		panelMainInbox.setLayout(null);
	}
	
	public void onDrawPanelProfile(){
		
		ProfilelayeredPane = new JLayeredPane();
		frame.getContentPane().add(ProfilelayeredPane, "name_102424516773931");
		
		onDrawPanelTitle(ProfilelayeredPane);
		
		JPanel panelProfileMain = new JPanel();
		panelProfileMain.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelProfileMain.setBounds(0, 100, 902, 460);
		ProfilelayeredPane.add(panelProfileMain);
		panelProfileMain.setLayout(null);
		
		JLabel lblpanelProfileMain_Picture = new JLabel("PIC");
		lblpanelProfileMain_Picture.setHorizontalAlignment(SwingConstants.CENTER);
		lblpanelProfileMain_Picture.setBounds(100, 60, 100, 100);
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
		
		JLabel lblpanelProfileMain_Name = new JLabel(firstname+surname);
		lblpanelProfileMain_Name.setBackground(new Color(255, 255, 255));
		lblpanelProfileMain_Name.setBounds(480, 60, 240, 20);
		panelProfileMain.add(lblpanelProfileMain_Name);
		
		JLabel lblpanelProfileMain_Gender = new JLabel(gender);
		lblpanelProfileMain_Gender.setBounds(480, 100, 240, 20);
		panelProfileMain.add(lblpanelProfileMain_Gender);
		
		JLabel lblpanelProfileMain_Age = new JLabel(String.valueOf(age));
		lblpanelProfileMain_Age.setBounds(480, 140, 240, 20);
		panelProfileMain.add(lblpanelProfileMain_Age);
		
		JLabel lblpanelProfileMain_Location = new JLabel(location);
		lblpanelProfileMain_Location.setBounds(480, 180, 240, 20);
		panelProfileMain.add(lblpanelProfileMain_Location);
		
		JLabel lblpanelProfileMain_SexPref = new JLabel(sex_Pref);
		lblpanelProfileMain_SexPref.setBounds(480, 220, 240, 20);
		panelProfileMain.add(lblpanelProfileMain_SexPref);
		
		JLabel lblpanelProfileMain_Sports = new JLabel(sports);
		lblpanelProfileMain_Sports.setBounds(480, 260, 240, 20);
		panelProfileMain.add(lblpanelProfileMain_Sports);
		
		JLabel lblpanelProfileMain_Films = new JLabel(films);
		lblpanelProfileMain_Films.setBounds(480, 300, 240, 20);
		panelProfileMain.add(lblpanelProfileMain_Films);
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
			btnpanelProfileTitle_Home.setVisible(true);
			changePanels(MainlayeredPane,ProfilelayeredPane);
			cb.setSelectedIndex(0);
		}
		
		
	}
}
