import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class FormDetailsWindow extends JFrame implements ItemListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4227172725150558754L;
	
	//--------------------variables : personal details page----------------------------------//
	
	private JPanel personal;
	private JPanel preferences;
	private JPanel account;
	private JPanel confirm;
	
	private JCheckBox chckbxPersonal_Male = new JCheckBox("Male");
	private JCheckBox chckbxPersonal_Female = new JCheckBox("Female");
	
	private Validate validate = new Validate();
	SimpleDateFormat formatCal = new SimpleDateFormat("dd-MM-yyyy");
	
	JLabel lblFirstnameerror;
	JLabel lblSurnameerror;
	JLabel lblDOBerror;
	JLabel lblTownerror;
	JLabel lblGendererror;
	JLabel lblthisFieldMust;
	
	JLabel lblEmailerror;
	JLabel lblPassworderror;
	JLabel lblAccounterror;
	
	private JFrame frame;
	private JTextField txtPersonal_Firstname;
	private JTextField txtPersonal_Surname;
	private JTextField txtPersonal_Day;
	private JTextField txtPersonal_Month;
	private JTextField txtPersonal_Year;
	private JLabel lblPreferences_Education;
	private JLabel lblPreferences_Sports;
	private JTextField txtPersonal_Town;
	private JTextField txtPrefernces_Film;
	private JTextField txtPreferences_Music;
	private JTextField txtPreferences_Education;
	private JTextField txtPreferences_Sport;
	private JTextField txtAccount_Email;
	private JTextField txtAccount_Password;
	private JTextField txtAccount_AccountNumber;
	
	//-----------------------------------Variables for confirm panel section---------------------------//
	private JLabel lblFinal_name;
	private JLabel lblFinal_DOB;
	private JLabel lblFinal_Gender;
	private JLabel lblFinal_Location;
	
	private JLabel lblFinal_Email;
	private JLabel lblFinal_Password;
	private JLabel lblFinal_AccountNumber;
	
	private String firstName = "";
	private String surName = "";
	private String day = "";
	private String month = "";
	private String year = "";
	private Calendar dob;
	private int age;
	private String gender = "";
	private String location = "";
	
	private ArrayList<String> sports = new ArrayList<String>();
	private ArrayList<String> music = new ArrayList<String>();
	private ArrayList<String> films = new ArrayList<String>();	
	
 	private String email = "";
	private String password = "";
	private String accountNumber = "";
	
	private JLabel test_pref;

	
	public FormDetailsWindow(){
		
		initialize();
		frame.setVisible(true);
	}
	
	public FormDetailsWindow(User currentUser,int number){
		
		onDrawFrame();
		frame.setVisible(true);
		
		if(number == 1){
			onDrawPanelPersonal();
		}
		else if(number == 2){
			onDrawPanelPreferences();
		}
		else if(number == 3){
			onDrawPanelAccount();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		onDrawFrame();
		
		onDrawPanelPersonal();
		
		onDrawPanelPreferences();
		
		onDrawPanelAccount();
		
	}
	
	public void onDrawFrame(){
		
		frame = new JFrame();
		frame.setBounds(100, 100, 820, 520);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
	}
	//-----------------------------------Draws the panel for personal details---------------------------------------------------//]
	public void onDrawPanelPersonal(){
		
		personal = new JPanel();
		frame.getContentPane().add(personal, "name_212104587205202");
		personal.setVisible(true);
		personal.setLayout(null);
		
		JLabel lblDaredate = new JLabel("Dare2Date");
		lblDaredate.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 50));
		lblDaredate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDaredate.setBounds(150, 25, 500, 78);
		personal.add(lblDaredate);
		
		JLabel lblPersonal_Firstname = new JLabel("*Firstname :");
		lblPersonal_Firstname.setBounds(150, 150, 90, 20);
		personal.add(lblPersonal_Firstname);
		
		JLabel lblPersonal_Surname = new JLabel("*Surname :");
		lblPersonal_Surname.setBounds(150, 180, 90, 20);
		personal.add(lblPersonal_Surname);
		
		JLabel lblPersonal_DOB = new JLabel("*Date Of Birth :");
		lblPersonal_DOB.setBounds(150, 210, 90, 20);
		personal.add(lblPersonal_DOB);
		
		txtPersonal_Firstname = new JTextField();
		txtPersonal_Firstname.setToolTipText("First name : minimum of 2 characters and max length of 24 characters");
		txtPersonal_Firstname.setBounds(270, 150, 120, 20);
		personal.add(txtPersonal_Firstname);
		txtPersonal_Firstname.setColumns(10);
		
		txtPersonal_Surname = new JTextField();
		txtPersonal_Surname.setToolTipText("Surname : minimum of 2 characters and max length of 24 characters");
		txtPersonal_Surname.setBounds(270, 180, 120, 20);
		personal.add(txtPersonal_Surname);
		txtPersonal_Surname.setColumns(10);
		
		txtPersonal_Day = new JTextField();
		txtPersonal_Day.setToolTipText("Day : must contain a one/two digit number between 1-31");
		txtPersonal_Day.setHorizontalAlignment(SwingConstants.CENTER);
		txtPersonal_Day.setText("DD");
		//txtPersonal_Day.setInputVerifier( verifier_number );
		txtPersonal_Day.setBounds(270, 210, 30, 20);
		personal.add(txtPersonal_Day);
		txtPersonal_Day.setColumns(10);
		
		txtPersonal_Month = new JTextField();
		txtPersonal_Month.setToolTipText("Month : must contain a one/two digit number between 1-12");
		txtPersonal_Month.setHorizontalAlignment(SwingConstants.CENTER);
		txtPersonal_Month.setText("MM");
		//txtPersonal_Month.setInputVerifier( verifier_number );
		txtPersonal_Month.setBounds(310, 210, 30, 20);
		personal.add(txtPersonal_Month);
		txtPersonal_Month.setColumns(10);
		
		txtPersonal_Year = new JTextField();
		txtPersonal_Year.setToolTipText("Year");
		txtPersonal_Year.setHorizontalAlignment(SwingConstants.CENTER);
		txtPersonal_Year.setText("YYYY");
		//txtPersonal_Year.setInputVerifier( verifier_number );
		txtPersonal_Year.setBounds(350, 210, 40, 20);
		personal.add(txtPersonal_Year);
		txtPersonal_Year.setColumns(10);
		
		JLabel lblPersonal_Gender = new JLabel("*Gender :");
		lblPersonal_Gender.setBounds(150, 240, 90, 20);
		personal.add(lblPersonal_Gender);
		
		chckbxPersonal_Male.setBounds(270, 237, 59, 24);
		chckbxPersonal_Male.addItemListener(this);
		chckbxPersonal_Male.setSelected(false);
		personal.add(chckbxPersonal_Male);
		
		chckbxPersonal_Female.setBounds(331, 237, 80, 24);
		chckbxPersonal_Female.addItemListener(this);
		chckbxPersonal_Female.setSelected(false);
		personal.add(chckbxPersonal_Female);
		
		JLabel lblPersonal_Location = new JLabel("*Town/City :");
		lblPersonal_Location.setBounds(150, 270, 90, 20);
		personal.add(lblPersonal_Location);
		
		txtPersonal_Town = new JTextField();
		txtPersonal_Town.setToolTipText("Town or City : must contain a valid town or city maximum of 24 characters");
		txtPersonal_Town.setBounds(270, 268, 120, 21);
		personal.add(txtPersonal_Town);
		txtPersonal_Town.setColumns(10);
		
		JLabel lblthisFieldMust = new JLabel("*These field's must be filled in.");
		lblthisFieldMust.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 10));
		lblthisFieldMust.setForeground(new Color(0, 0, 0));
		lblthisFieldMust.setBounds(150, 300, 240, 20);
		personal.add(lblthisFieldMust);
		
		lblFirstnameerror = new JLabel("*The firstname entered was invalid ");
		lblFirstnameerror.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblFirstnameerror.setForeground(Color.RED);
		lblFirstnameerror.setBounds(460, 150, 275, 20);
		lblFirstnameerror.setVisible(false);
		personal.add(lblFirstnameerror);
		
		lblSurnameerror = new JLabel("*The Surname Entered was invalid");
		lblSurnameerror.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblSurnameerror.setForeground(Color.RED);
		lblSurnameerror.setBounds(460, 180, 275, 20);
		lblSurnameerror.setVisible(false);
		personal.add(lblSurnameerror);
		
		lblDOBerror = new JLabel("*The Date of Birth entered was invalid");
		lblDOBerror.setForeground(Color.RED);
		lblDOBerror.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblDOBerror.setBounds(460, 210, 275, 20);
		lblDOBerror.setVisible(false);
		personal.add(lblDOBerror);
		
		lblGendererror = new JLabel("*No Gender was selected");
		lblGendererror.setForeground(Color.RED);
		lblGendererror.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblGendererror.setBounds(460, 240, 275, 20);
		lblGendererror.setVisible(false);
		personal.add(lblGendererror);
		
		lblTownerror = new JLabel("*The Location Entered was invalid");
		lblTownerror.setForeground(Color.RED);
		lblTownerror.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblTownerror.setBounds(460, 270, 275, 20);
		lblTownerror.setVisible(false);
		personal.add(lblTownerror);
		
		JButton btnNextPers = new JButton("Next");
		btnNextPers.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				
				setPersonal(txtPersonal_Firstname.getText(), txtPersonal_Surname.getText(), txtPersonal_Day.getText(), txtPersonal_Month.getText(),
						txtPersonal_Year.getText(), txtPersonal_Town.getText());
				
				boolean go = validate.onValidate(firstName, surName, day, month, year, gender, location, lblFirstnameerror,
						lblSurnameerror, lblDOBerror, lblGendererror, lblTownerror);
				
				if(go){
					dob = validate.getCalendar();
					age = validate.getAge();
					onClickNext(personal,preferences);
				}
			}
		});
		
		btnNextPers.setBounds(484, 350, 90, 24);
		personal.add(btnNextPers);
		
		JButton btnCancelPers = new JButton("Cancel");
		btnCancelPers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelWindow();
			}
		});
		
		btnCancelPers.setBounds(584, 350, 90, 24);
		personal.add(btnCancelPers);
		
		onDrawPictures(personal);
	}
	
	
	public void onDrawPanelPreferences(){
		
		preferences = new JPanel();
		frame.getContentPane().add(preferences, "name_212123375373559");
		preferences.setLayout(null);
		
		JLabel lblDaredate2 = new JLabel("Dare2Date");
		lblDaredate2.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 50));
		lblDaredate2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDaredate2.setBounds(150, 25, 500, 78);
		preferences.add(lblDaredate2);
		
		lblPreferences_Education = new JLabel("Education :");
		lblPreferences_Education.setBounds(150, 240, 100, 20);
		preferences.add(lblPreferences_Education);
		
		lblPreferences_Sports = new JLabel("Sports :");
		lblPreferences_Sports.setBounds(150, 270, 80, 20);
		preferences.add(lblPreferences_Sports);
		
		JLabel lblPreferences_SexualPreference = new JLabel("Sexual Preference :");
		lblPreferences_SexualPreference.setBounds(150, 150, 100, 20);
		preferences.add(lblPreferences_SexualPreference);
		
		JLabel lblPreferences_Films = new JLabel("Films :");
		lblPreferences_Films.setBounds(150, 180, 100, 20);
		preferences.add(lblPreferences_Films);
		
		JLabel lblPreferences_Music = new JLabel("Music :");
		lblPreferences_Music.setBounds(150, 210, 100, 20);
		preferences.add(lblPreferences_Music);
		
		JCheckBox chckbxPreferences_Male = new JCheckBox("Male");
		chckbxPreferences_Male.setBounds(283, 150, 60, 23);
		preferences.add(chckbxPreferences_Male);
		
		JCheckBox chckbxPreferences_Female = new JCheckBox("Female");
		chckbxPreferences_Female.setBounds(345, 150, 80, 23);
		preferences.add(chckbxPreferences_Female);
		
		JCheckBox chckbxPreferences_Both = new JCheckBox("Both");
		chckbxPreferences_Both.setBounds(425, 150, 80, 23);
		preferences.add(chckbxPreferences_Both);
		
		txtPrefernces_Film = new JTextField();
		txtPrefernces_Film.setBounds(283, 180, 367, 20);
		preferences.add(txtPrefernces_Film);
		txtPrefernces_Film.setColumns(10);
		
		txtPreferences_Music = new JTextField();
		txtPreferences_Music.setBounds(283, 210, 367, 20);
		preferences.add(txtPreferences_Music);
		txtPreferences_Music.setColumns(10);
		
		txtPreferences_Education = new JTextField();
		txtPreferences_Education.setText("");
		txtPreferences_Education.setBounds(283, 240, 367, 20);
		preferences.add(txtPreferences_Education);
		txtPreferences_Education.setColumns(10);
		
		txtPreferences_Sport = new JTextField();
		txtPreferences_Sport.setBounds(283, 270, 367, 20);
		preferences.add(txtPreferences_Sport);
		txtPreferences_Sport.setColumns(10);
		
		JButton btnBackPref = new JButton("Back");
		btnBackPref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				onClickNext(preferences,personal);
			}
		});
		btnBackPref.setBounds(384, 350, 90, 24);
		preferences.add(btnBackPref);
		
		JButton btnNextPref = new JButton("Next");
		btnNextPref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setPreferences(txtPreferences_Sport.getText());
				//onClickNext(preferences,account);
			}
		});
		btnNextPref.setBounds(484, 350, 90, 24);
		preferences.add(btnNextPref);
		
		JButton btnCancelPref = new JButton("Cancel");
		btnCancelPref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelWindow();
			}
		});
		btnCancelPref.setBounds(584, 350, 90, 24);
		preferences.add(btnCancelPref);
		
		onDrawPictures(preferences);
		
		test_pref = new JLabel("New label");
		test_pref.setBounds(33, 107, 744, 36);
		preferences.add(test_pref);
	}
	
	
	public void onDrawPanelAccount(){
		
		account = new JPanel();
		frame.getContentPane().add(account, "name_212126368410378");
		account.setLayout(null);
		
		JLabel lblDaredate3 = new JLabel("Dare2Date");
		lblDaredate3.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 50));
		lblDaredate3.setHorizontalAlignment(SwingConstants.CENTER);
		lblDaredate3.setBounds(150, 25, 500, 78);
		account.add(lblDaredate3);
		
		JLabel lblAccount_Email = new JLabel("*E-mail :");
		lblAccount_Email.setBounds(110, 150, 130, 20);
		account.add(lblAccount_Email);
		
		JLabel lblAccount_Password = new JLabel("*Password :");
		lblAccount_Password.setBounds(110, 180, 130, 20);
		account.add(lblAccount_Password);
		
		JLabel lblAccount_AccountNumber = new JLabel("*Account number :");
		lblAccount_AccountNumber.setBounds(110, 210, 130, 20);
		account.add(lblAccount_AccountNumber);
		
		txtAccount_Email = new JTextField();
		txtAccount_Email.setToolTipText("e-mail");
		txtAccount_Email.setBounds(250, 150, 200, 20);
		account.add(txtAccount_Email);
		txtAccount_Email.setColumns(10);
		
		txtAccount_Password = new JPasswordField();
		txtAccount_Password.setBounds(250, 180, 200, 20);
		account.add(txtAccount_Password);
		txtAccount_Password.setColumns(10);
		
		txtAccount_AccountNumber = new JTextField();
		txtAccount_AccountNumber.setBounds(250, 210, 200, 20);
		account.add(txtAccount_AccountNumber);
		txtAccount_AccountNumber.setColumns(10);
		
		JLabel thisFieldMust_01 = new JLabel("*These field's must be filled in.");
		thisFieldMust_01.setForeground(Color.BLACK);
		thisFieldMust_01.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 10));
		thisFieldMust_01.setBounds(110, 240, 240, 20);
		account.add(thisFieldMust_01);
		
		lblEmailerror = new JLabel("*The E-mail entered is invalid.");
		lblEmailerror.setForeground(Color.RED);
		lblEmailerror.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblEmailerror.setVisible(false);
		lblEmailerror.setBounds(485, 150, 240, 20);
		account.add(lblEmailerror);
		
		lblPassworderror = new JLabel("*The Password entered is invalid.");
		lblPassworderror.setForeground(Color.RED);
		lblPassworderror.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblPassworderror.setVisible(false);
		lblPassworderror.setBounds(485, 180, 240, 20);
		account.add(lblPassworderror);
		
		lblAccounterror = new JLabel("*The Account number entered is invalid.");
		lblAccounterror.setForeground(Color.RED);
		lblAccounterror.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblAccounterror.setVisible(false);
		lblAccounterror.setBounds(485, 210, 280, 20);
		account.add(lblAccounterror);
		
		JButton btnNextAcc = new JButton("Next");
		btnNextAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				setAccount(txtAccount_Email.getText(), txtAccount_Password.getText(), txtAccount_AccountNumber.getText());
				
				boolean go = validate.onValidate(email, password, accountNumber, lblEmailerror, lblPassworderror, lblAccounterror);
				
				if(go){
					onDrawPanelConfirm();
					onClickNext(account,confirm);
				}
			}
		});
		btnNextAcc.setBounds(484, 350, 90, 24);
		account.add(btnNextAcc);
		
		JButton btnCancelAcc = new JButton("Cancel");
		btnCancelAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelWindow();
			}
		});
		btnCancelAcc.setBounds(584, 350, 90, 24);
		account.add(btnCancelAcc);
		
		JButton btnBackAcc = new JButton("Back");
		btnBackAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				onClickNext(account,preferences);
			}
		});
		btnBackAcc.setBounds(384, 350, 90, 24);
		account.add(btnBackAcc);
		
		onDrawPictures(account);
	}
	
	
	public void onDrawPanelConfirm(){
		
		confirm = new JPanel();
		frame.getContentPane().add(confirm, "name_212129749305905");
		confirm.setLayout(null);
		
		JLabel lblDare2Date4 = new JLabel("Dare2Date");
		lblDare2Date4.setHorizontalAlignment(SwingConstants.CENTER);
		lblDare2Date4.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 50));
		lblDare2Date4.setBounds(150, 25, 500, 78);
		confirm.add(lblDare2Date4);
		
		JButton btnNextCon = new JButton("Confirm");
		btnNextCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				onConfirm();
			}
		});
		btnNextCon.setBounds(484, 350, 90, 24);
		confirm.add(btnNextCon);
		
		JButton btnBackCon = new JButton("Back");
		btnBackCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				onClickNext(confirm,account);
			}
		});
		btnBackCon.setBounds(385, 350, 90, 24);
		confirm.add(btnBackCon);
		
		JButton btnCancelCon = new JButton("Cancel");
		btnCancelCon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cancelWindow();
			}
		});
		btnCancelCon.setBounds(584, 350, 90, 24);
		confirm.add(btnCancelCon);
		
		JLabel lblConfirm_Name = new JLabel("Name :");
		lblConfirm_Name.setBounds(120, 150, 80, 20);
		confirm.add(lblConfirm_Name);
		
		JLabel lbConfirm_DOB = new JLabel("Date of Birth :");
		lbConfirm_DOB.setBounds(120, 180, 80, 20);
		confirm.add(lbConfirm_DOB);
		
		JLabel lblConfirm_Gender = new JLabel("Gender :");
		lblConfirm_Gender.setBounds(120, 210, 80, 20);
		confirm.add(lblConfirm_Gender);
		
		JLabel lblConfirm_Town = new JLabel("Town/City :");
		lblConfirm_Town.setBounds(120, 241, 80, 20);
		confirm.add(lblConfirm_Town);
		
		lblFinal_name = new JLabel(firstName+surName);
		lblFinal_name.setEnabled(false);
		lblFinal_name.setBackground(new Color(192, 192, 192));
		lblFinal_name.setForeground(Color.BLACK);
		lblFinal_name.setBounds(240, 150, 120, 20);
		confirm.add(lblFinal_name);
		
		lblFinal_DOB = new JLabel(formatCal.format(dob.getTime()));
		lblFinal_DOB.setEnabled(false);
		lblFinal_DOB.setBounds(240, 180, 120, 20);
		confirm.add(lblFinal_DOB);
		
		lblFinal_Gender = new JLabel(gender);
		lblFinal_Gender.setEnabled(false);
		lblFinal_Gender.setBounds(240, 210, 120, 20);
		confirm.add(lblFinal_Gender);
		
		lblFinal_Location = new JLabel(location);
		lblFinal_Location.setEnabled(false);
		lblFinal_Location.setBounds(240, 240, 120, 20);
		confirm.add(lblFinal_Location);
		
		JLabel lblConfirm_Email = new JLabel("E-mail :");
		lblConfirm_Email.setBounds(410, 150, 100, 20);
		confirm.add(lblConfirm_Email);
		
		JLabel lblConfirm_Password = new JLabel("Password :");
		lblConfirm_Password.setBounds(410, 180, 100, 20);
		confirm.add(lblConfirm_Password);
		
		JLabel lblConfirm_AccountNumber = new JLabel("Account Number :");
		lblConfirm_AccountNumber.setBounds(410, 210, 100, 20);
		confirm.add(lblConfirm_AccountNumber);
		
		JLabel lblFinal_Email = new JLabel(email);
		lblFinal_Email.setEnabled(false);
		lblFinal_Email.setBounds(550, 150, 170, 20);
		confirm.add(lblFinal_Email);
		
		JLabel lblFinal_Password = new JLabel(password);
		lblFinal_Password.setEnabled(false);
		lblFinal_Password.setBounds(550, 180, 170, 20);
		confirm.add(lblFinal_Password);
		
		JLabel lblFinal_AccountNumber = new JLabel(accountNumber);
		lblFinal_AccountNumber.setEnabled(false);
		lblFinal_AccountNumber.setBounds(550, 210, 170, 20);
		confirm.add(lblFinal_AccountNumber);
		
		JButton btnEditPersonalDetails = new JButton("Edit Personal Details..");
		btnEditPersonalDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickNext(confirm,personal);
			}
		});
		btnEditPersonalDetails.setBounds(139, 310, 165, 25);
		confirm.add(btnEditPersonalDetails);
		
		JButton btnEditAccountDetails = new JButton("Edit Account Details..");
		btnEditAccountDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClickNext(confirm,account);
			}
		});
		btnEditAccountDetails.setBounds(435, 310, 165, 25);
		confirm.add(btnEditAccountDetails);
		
		onDrawPictures(confirm);
	}
	
	public void onDrawPictures(JPanel pane){
		
		JLabel pic_1 = new JLabel("");
		pic_1.setIcon(new ImageIcon(FormDetailsWindow.class.getResource("/myImages/pic1.jpg")));
		pic_1.setBounds(10, 382, 82, 90);
		pane.add(pic_1);
		
		JLabel pic_2 = new JLabel("");
		pic_2.setIcon(new ImageIcon(FormDetailsWindow.class.getResource("/myImages/pic2.jpg")));
		pic_2.setBounds(98, 382, 82, 90);
		pane.add(pic_2);
		
		JLabel pic_3 = new JLabel("");
		pic_3.setIcon(new ImageIcon(FormDetailsWindow.class.getResource("/myImages/pic3.jpg")));
		pic_3.setBounds(186, 382, 82, 90);
		pane.add(pic_3);
		
		JLabel pic_4 = new JLabel("");
		pic_4.setIcon(new ImageIcon(FormDetailsWindow.class.getResource("/myImages/pic4.jpg")));
		pic_4.setBounds(274, 382, 82, 90);
		pane.add(pic_4);
		
		JLabel pic_5 = new JLabel("");
		pic_5.setIcon(new ImageIcon(FormDetailsWindow.class.getResource("/myImages/pic5.jpg")));
		pic_5.setBounds(362, 382, 82, 90);
		pane.add(pic_5);
		
		JLabel pic_6 = new JLabel("");
		pic_6.setIcon(new ImageIcon(FormDetailsWindow.class.getResource("/myImages/pic6.jpg")));
		pic_6.setBounds(450, 382, 82, 90);
		pane.add(pic_6);
		
		JLabel pic_7 = new JLabel("");
		pic_7.setIcon(new ImageIcon(FormDetailsWindow.class.getResource("/myImages/pic7.jpg")));
		pic_7.setBounds(538, 382, 82, 90);
		pane.add(pic_7);
		
		JLabel pic_8 = new JLabel("");
		pic_8.setIcon(new ImageIcon(FormDetailsWindow.class.getResource("/myImages/pic8.jpg")));
		pic_8.setBounds(626, 382, 82, 90);
		pane.add(pic_8);
		
		JLabel pic_9 = new JLabel("");
		pic_9.setIcon(new ImageIcon(FormDetailsWindow.class.getResource("/myImages/pic9.jpg")));
		pic_9.setBounds(712, 382, 82, 90);
		pane.add(pic_9);
	}


	//takes in two panels making the first one invisible and showing the second panel
	public void onClickNext(JPanel panel1,JPanel panel2){
		
		panel1.setVisible(false);
		panel2.setVisible(true);
	}
	
	//Method to be called that will close the current window
	private void cancelWindow(){
		
		frame.dispose();
	}

	//makes sure that only one checkbox can be ticked at any given time
	public void itemStateChanged(ItemEvent e) {
		
		Object source = e.getItemSelectable();
		
		if (source == chckbxPersonal_Male){
			chckbxPersonal_Female.setSelected(false);
			gender = "Male";
		}
		else if (source == chckbxPersonal_Female){
			chckbxPersonal_Male.setSelected(false);
			gender = "Female";
		}
		
	}
	
	public void setPersonal(String first, String sur, String da, String mon, String yea, String town){
	
		firstName = first;
		surName = sur;
		day = da;
		month = mon;
		year = yea;
		location = town;
		
		
	}
	
	public void setAccount(String e, String p, String a_num){
		
		email = e;
		password = p;
		accountNumber = a_num;
	}
	
	public void setPreferences(String sport){
		
		String[] new_sports = sport.split(",");
		
		for(int i=0;i<new_sports.length;i++){
			
			sports.add(new_sports[i]);
		}
		
		sports.remove("football");
		
		test_pref.setText(sports.toString());
		
	}
	
	public void setConfirm(){
		
		
	}
	
	//puts the users details into a SignUp class
	public void onConfirm(){
		
		SignUp signUp = new SignUp(txtPersonal_Firstname.getText(), txtPersonal_Surname.getText(), dob, age, gender, txtPersonal_Town.getText(), txtAccount_Email.getText(),	
				txtAccount_Password.getText(), txtAccount_AccountNumber.getText());
		
		onSignUp(signUp);
	}
	
	
	public void onSignUp (SignUp user) {
		try {
			//Create a reference to the service interface at the location.
			ServiceInterface service = (ServiceInterface) Naming.lookup("rmi://127.0.0.1/DateServer");
			//Create a response object
			OnResponse res;
			//Invoke server SignUp method
			res = service.SignUp(user);
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
