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
import java.util.Calendar;

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
	
	private JCheckBox chckbxMale = new JCheckBox("Male");
	private JCheckBox chckbxFemale = new JCheckBox("Female");
	private JLabel lblFirstNameerror;
	private JLabel lblSurnameerror;
	private JLabel lblDOBerror;
	private JLabel lblTownerror;
	private JLabel lblGendererror;
	private JLabel lblCountryerror;
	private JLabel lblthisFieldMust;
	private String gender;
	private Calendar age;
	
	
	private JFrame frame;
	private JPanel personal;
	private JPanel preferences;
	private JPanel account;
	private JPanel confirm;
	private JTextField firstName;
	private JTextField surName;
	private JTextField dobDay;
	private JTextField dobMonth;
	private JTextField dobYear;
	private CheckEntered checked = new CheckEntered();
	private JLabel lblEducation;
	private JLabel lblSports;
	private JLabel lblAboutYou;
	private JTextField town_city;
	private JTextField country;
	private JTextField films;
	private JTextField music;
	private JTextField education;
	private JTextField sports;
	private JTextField about_you;
	private JTextField e_mail;
	private JTextField password;
	private JTextField acc_name;
	private JTextField acc_number;
	private JTextField card_number;
	
	//-----------------------------------Variables for confirm panel section---------------------------//
	private JLabel lblFullnamecon;
	private JLabel lblDobcon;
	private JLabel lblGendercon;
	private JLabel lblTowncitycon;
	private JLabel lblCountrycon;
	
	

	
	public FormDetailsWindow() {
		
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 820, 520);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
			
			//----------------------------THE PANEL USED FOR ALL PERSONAL DETAILS ENTERED-----------------------------------------//
			
			personal = new JPanel();
			frame.getContentPane().add(personal, "name_212104587205202");
			personal.setVisible(true);
			personal.setLayout(null);
			
			JLabel lblDaredate = new JLabel("Dare2Date");
			lblDaredate.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 50));
			lblDaredate.setHorizontalAlignment(SwingConstants.CENTER);
			lblDaredate.setBounds(150, 25, 500, 78);
			personal.add(lblDaredate);
			
			JLabel lblFirstname = new JLabel("*Firstname :");
			lblFirstname.setBounds(150, 150, 90, 20);
			personal.add(lblFirstname);
			
			JLabel lblSurname = new JLabel("*Surname :");
			lblSurname.setBounds(150, 180, 90, 20);
			personal.add(lblSurname);
			
			JLabel lblDateOfBirth = new JLabel("*Date Of Birth :");
			lblDateOfBirth.setBounds(150, 210, 90, 20);
			personal.add(lblDateOfBirth);
			
			firstName = new JTextField();
			firstName.setToolTipText("First name : minimum of 2 characters and max length of 24 characters");
			firstName.setBounds(270, 150, 120, 20);
			personal.add(firstName);
			firstName.setColumns(10);
			
			surName = new JTextField();
			surName.setToolTipText("Surname : minimum of 2 characters and max length of 24 characters");
			surName.setText("");
			surName.setBounds(270, 180, 120, 20);
			personal.add(surName);
			surName.setColumns(10);
			
			dobDay = new JTextField();
			dobDay.setToolTipText("Day : must contain a one/two digit number between 1-31");
			dobDay.setHorizontalAlignment(SwingConstants.CENTER);
			dobDay.setText("2");
			dobDay.setBounds(270, 210, 30, 20);
			personal.add(dobDay);
			dobDay.setColumns(10);
			
			dobMonth = new JTextField();
			dobMonth.setToolTipText("Month : must contain a one/two digit number between 1-12");
			dobMonth.setHorizontalAlignment(SwingConstants.CENTER);
			dobMonth.setText("04");
			dobMonth.setBounds(310, 210, 30, 20);
			personal.add(dobMonth);
			dobMonth.setColumns(10);
			
			dobYear = new JTextField();
			dobYear.setToolTipText("Year");
			dobYear.setHorizontalAlignment(SwingConstants.CENTER);
			dobYear.setText("1985");
			dobYear.setBounds(350, 210, 40, 20);
			personal.add(dobYear);
			dobYear.setColumns(10);
			
			JButton btnNextPers = new JButton("Next");
			btnNextPers.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent arg0){
					if(validatePersonal())
						onClickNext(personal,preferences);
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
			
			JLabel lblGender = new JLabel("*Gender :");
			lblGender.setBounds(150, 240, 90, 20);
			personal.add(lblGender);
			
			chckbxMale.setBounds(270, 237, 59, 24);
			chckbxMale.addItemListener(this);
			chckbxMale.setSelected(false);
			personal.add(chckbxMale);
			
			chckbxFemale.setBounds(331, 237, 80, 24);
			chckbxFemale.addItemListener(this);
			chckbxFemale.setSelected(false);
			personal.add(chckbxFemale);
			
			JLabel lblTowncity = new JLabel("*Town/City :");
			lblTowncity.setBounds(150, 270, 90, 20);
			personal.add(lblTowncity);
			
			town_city = new JTextField();
			town_city.setToolTipText("Town or City : must contain a valid town or city maximum of 24 characters");
			town_city.setBounds(270, 268, 120, 21);
			personal.add(town_city);
			town_city.setColumns(10);
			
			JLabel lblCountry = new JLabel("*Country :");
			lblCountry.setBounds(150, 300, 90, 20);
			personal.add(lblCountry);
			
			country = new JTextField();
			country.setToolTipText("Country : must be a valid country with a maximum of 24 characters");
			country.setBounds(270, 300, 120, 20);
			personal.add(country);
			country.setColumns(10);
			
			lblthisFieldMust = new JLabel("*These field's must be filled in.");
			lblthisFieldMust.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			lblthisFieldMust.setForeground(new Color(0, 0, 0));
			lblthisFieldMust.setBounds(150, 340, 240, 20);
			personal.add(lblthisFieldMust);
			
			lblFirstNameerror = new JLabel("*The firstname entered was invalid ");
			lblFirstNameerror.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblFirstNameerror.setForeground(Color.RED);
			lblFirstNameerror.setBounds(150, 391, 275, 20);
			lblFirstNameerror.setVisible(false);
			personal.add(lblFirstNameerror);
			
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
			
			lblTownerror = new JLabel("*The Town/City Entered was invalid");
			lblTownerror.setForeground(Color.RED);
			lblTownerror.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblTownerror.setBounds(460, 270, 275, 20);
			lblTownerror.setVisible(false);
			personal.add(lblTownerror);
			
			lblCountryerror = new JLabel("*The Country Entered was invalid");
			lblCountryerror.setForeground(Color.RED);
			lblCountryerror.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblCountryerror.setBounds(460, 300, 275, 20);
			lblCountryerror.setVisible(false);
			personal.add(lblCountryerror);
		
		
				//-------------------------------------------THE PANEL USED FOR ALL PREFERENCES DETAILS-----------------------------------//
		
				preferences = new JPanel();
				frame.getContentPane().add(preferences, "name_212123375373559");
				preferences.setLayout(null);
				
				JLabel lblDaredate2 = new JLabel("Dare2Date");
				lblDaredate2.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 50));
				lblDaredate2.setHorizontalAlignment(SwingConstants.CENTER);
				lblDaredate2.setBounds(150, 25, 500, 78);
				preferences.add(lblDaredate2);
				
				lblEducation = new JLabel("Education :");
				lblEducation.setBounds(150, 240, 100, 20);
				preferences.add(lblEducation);
				
				lblSports = new JLabel("Sports :");
				lblSports.setBounds(150, 270, 80, 20);
				preferences.add(lblSports);
				
				lblAboutYou = new JLabel("About you :");
				lblAboutYou.setBounds(150, 300, 80, 20);
				preferences.add(lblAboutYou);
				
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
						
						onClickNext(preferences,account);
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
				
				JLabel lblSexualPreference = new JLabel("Sexual Preference :");
				lblSexualPreference.setBounds(150, 150, 100, 20);
				preferences.add(lblSexualPreference);
				
				JLabel lblFilms = new JLabel("Films :");
				lblFilms.setBounds(150, 180, 100, 20);
				preferences.add(lblFilms);
				
				JLabel lblMusic = new JLabel("Music :");
				lblMusic.setBounds(150, 210, 100, 20);
				preferences.add(lblMusic);
				
				JCheckBox chckbxMale_1 = new JCheckBox("Male");
				chckbxMale_1.setBounds(283, 150, 60, 23);
				preferences.add(chckbxMale_1);
				
				JCheckBox chckbxFemale_1 = new JCheckBox("Female");
				chckbxFemale_1.setBounds(345, 150, 80, 23);
				preferences.add(chckbxFemale_1);
				
				JCheckBox chckbxBoth = new JCheckBox("Both");
				chckbxBoth.setBounds(425, 150, 80, 23);
				preferences.add(chckbxBoth);
				
				films = new JTextField();
				films.setBounds(283, 180, 291, 20);
				preferences.add(films);
				films.setColumns(10);
				
				music = new JTextField();
				music.setBounds(283, 210, 291, 20);
				preferences.add(music);
				music.setColumns(10);
				
				education = new JTextField();
				education.setText("");
				education.setBounds(283, 240, 291, 20);
				preferences.add(education);
				education.setColumns(10);
				
				sports = new JTextField();
				sports.setBounds(283, 270, 291, 20);
				preferences.add(sports);
				sports.setColumns(10);
				
				about_you = new JTextField();
				about_you.setBounds(283, 300, 291, 20);
				preferences.add(about_you);
				about_you.setColumns(10);
		
		
			//---------------------------------------------THE PANEL USED FOR ALL ACCOUNT DETAILS ADDED------------------------------------//	
				
			account = new JPanel();
			frame.getContentPane().add(account, "name_212126368410378");
			account.setLayout(null);
			
			JLabel lblDaredate3 = new JLabel("Dare2Date");
			lblDaredate3.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 50));
			lblDaredate3.setHorizontalAlignment(SwingConstants.CENTER);
			lblDaredate3.setBounds(150, 25, 500, 78);
			account.add(lblDaredate3);
			
			JLabel lblEmail = new JLabel("E-mail :");
			lblEmail.setBounds(150, 150, 100, 20);
			account.add(lblEmail);
			
			JLabel lblpassword = new JLabel("Password :");
			lblpassword.setBounds(150, 180, 100, 20);
			account.add(lblpassword);
			
			JLabel lblAccountName = new JLabel("Account name :");
			lblAccountName.setBounds(150, 210, 100, 20);
			account.add(lblAccountName);
			
			JLabel lblAccountNumber = new JLabel("Account number :");
			lblAccountNumber.setBounds(150, 240, 100, 20);
			account.add(lblAccountNumber);
			
			JLabel lblCardNumber = new JLabel("Card number :");
			lblCardNumber.setBounds(150, 270, 100, 20);
			account.add(lblCardNumber);
			
			JButton btnNextAcc = new JButton("Next");
			btnNextAcc.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					onClickNext(account,confirm);
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
			
			e_mail = new JTextField();
			e_mail.setToolTipText("e-mail");
			e_mail.setBounds(270, 150, 200, 20);
			account.add(e_mail);
			e_mail.setColumns(10);
			
			password = new JPasswordField();
			password.setBounds(270, 180, 200, 20);
			account.add(password);
			password.setColumns(10);
			
			acc_name = new JTextField();
			acc_name.setBounds(270, 210, 200, 20);
			account.add(acc_name);
			acc_name.setColumns(10);
			
			acc_number = new JTextField();
			acc_number.setBounds(270, 240, 200, 20);
			account.add(acc_number);
			acc_number.setColumns(10);
			
			card_number = new JTextField();
			card_number.setBounds(270, 270, 70, 20);
			account.add(card_number);
			card_number.setColumns(10);
			
			JLabel thisFieldMust_01 = new JLabel("*These field's must be filled in.");
			thisFieldMust_01.setForeground(Color.BLACK);
			thisFieldMust_01.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			thisFieldMust_01.setBounds(150, 310, 240, 20);
			account.add(thisFieldMust_01);
		
			
				//-------------------------------------THE PANEL USED FOR ALL OF THE CONFIRMED DETAILS THAT HAVE BEEN ENTERED.---------------------//
			
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
				
				JLabel lblName = new JLabel("Name :");
				lblName.setBounds(120, 150, 80, 20);
				confirm.add(lblName);
				
				JLabel lblDateOfBirth_1 = new JLabel("Date of Birth :");
				lblDateOfBirth_1.setBounds(120, 180, 80, 20);
				confirm.add(lblDateOfBirth_1);
				
				JLabel lblGender_1 = new JLabel("Gender :");
				lblGender_1.setBounds(120, 210, 80, 20);
				confirm.add(lblGender_1);
				
				JLabel lblTowncity_1 = new JLabel("Town/City :");
				lblTowncity_1.setBounds(120, 241, 80, 20);
				confirm.add(lblTowncity_1);
				
				JLabel lblCountry_1 = new JLabel("Country :");
				lblCountry_1.setBounds(120, 270, 80, 20);
				confirm.add(lblCountry_1);
				
				lblFullnamecon = new JLabel("");
				lblFullnamecon.setEnabled(false);
				lblFullnamecon.setBackground(new Color(192, 192, 192));
				lblFullnamecon.setForeground(Color.BLACK);
				lblFullnamecon.setBounds(240, 150, 120, 20);
				confirm.add(lblFullnamecon);
				
				lblDobcon = new JLabel("");
				lblDobcon.setEnabled(false);
				lblDobcon.setBounds(240, 180, 120, 20);
				confirm.add(lblDobcon);
				
				lblGendercon = new JLabel("");
				lblGendercon.setEnabled(false);
				lblGendercon.setBounds(240, 210, 120, 20);
				confirm.add(lblGendercon);
				
				lblTowncitycon = new JLabel("");
				lblTowncitycon.setEnabled(false);
				lblTowncitycon.setBounds(240, 240, 120, 20);
				confirm.add(lblTowncitycon);
				
				lblCountrycon = new JLabel("");
				lblCountrycon.setEnabled(false);
				lblCountrycon.setBounds(240, 270, 120, 20);
				confirm.add(lblCountrycon);
				
				JLabel lblEmail_1 = new JLabel("E-mail :");
				lblEmail_1.setBounds(410, 150, 90, 20);
				confirm.add(lblEmail_1);
				
				JLabel lblPassword_1 = new JLabel("Password :");
				lblPassword_1.setBounds(410, 180, 90, 20);
				confirm.add(lblPassword_1);
				
				JLabel lblAccountName_1 = new JLabel("Account Name :");
				lblAccountName_1.setBounds(410, 210, 90, 20);
				confirm.add(lblAccountName_1);
				
				JLabel lblAccountNum_1 = new JLabel("Account Number :");
				lblAccountNum_1.setBounds(410, 240, 90, 20);
				confirm.add(lblAccountNum_1);
				
				JLabel lblCardNumber_1 = new JLabel("Card Number :");
				lblCardNumber_1.setBounds(410, 270, 90, 20);
				confirm.add(lblCardNumber_1);
				
				JLabel lblEmailcon = new JLabel("E-mailcon");
				lblEmailcon.setEnabled(false);
				lblEmailcon.setBounds(550, 150, 170, 20);
				confirm.add(lblEmailcon);
				
				JLabel lblPasswordcon = new JLabel("Passwordcon");
				lblPasswordcon.setEnabled(false);
				lblPasswordcon.setBounds(550, 180, 170, 20);
				confirm.add(lblPasswordcon);
				
				JLabel AccountNamecon = new JLabel("New label");
				AccountNamecon.setEnabled(false);
				AccountNamecon.setBounds(550, 210, 170, 20);
				confirm.add(AccountNamecon);
				
				JLabel lblAccountnumbercon = new JLabel("AccountNumbercon");
				lblAccountnumbercon.setEnabled(false);
				lblAccountnumbercon.setBounds(550, 240, 170, 20);
				confirm.add(lblAccountnumbercon);
				
				JLabel lblCardNumbercon = new JLabel("New label");
				lblCardNumbercon.setEnabled(false);
				lblCardNumbercon.setBounds(550, 270, 170, 20);
				confirm.add(lblCardNumbercon);
				
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
		
		
	}
	
	
	//takes in two panels making the first one invisible and showing the second panel
	public void onClickNext(JPanel panel1,JPanel panel2){
		
		panel1.setVisible(false);
		panel2.setVisible(true);
	}
	
	
	//validates all of the personal details when the next button is pressed
	public boolean validatePersonal(){
		
		boolean go = true;
		int day = 0;
		int month = 0;
		int year = 0;
		
		SimpleDateFormat formatCal = new SimpleDateFormat("dd-MM-yyyy");
				
		try{
			day = Integer.parseInt(dobDay.getText());
			month = Integer.parseInt(dobMonth.getText());
			year = Integer.parseInt(dobYear.getText());
			lblDOBerror.setVisible(false);
		}
		catch(NumberFormatException e){
			lblDOBerror.setVisible(true);
			return false;
		}
		
		Calendar checkAge = Calendar.getInstance();
		checkAge.clear();
		checkAge.set(year, month-1, day);
		
		boolean firstname = checked.check(firstName.getText());
		boolean surname = checked.check(surName.getText());
		boolean age = checked.check(checkAge);
		boolean Gender = (chckbxFemale.getSelectedObjects() == null)&&(chckbxMale.getSelectedObjects() == null);
		boolean town = checked.check(town_city.getText());
		boolean Country = checked.check(country.getText());
		
		String DOB = formatCal.format(checkAge.getTime());
		
		if(!firstname||!surname||!age||Gender||!town||!Country){
			go = false;
			lblthisFieldMust.setForeground(Color.RED);
		}
		/*else if(!surname){
			go = false;
			lblSurnameerror.setVisible(true);
		}
		else if(!age){
			go = false;
			lblDOBerror.setVisible(true);
		}
		else if(Gender){
			go = false;
			lblGendererror.setVisible(true);
		}
		else if(!town){
			go = false;
			lblTownerror.setVisible(true);
		}
		else if(!Country){
			go = false;
			lblCountryerror.setVisible(true);
		}*/
		else{
			lblFullnamecon.setText(firstName.getText()+" "+surName.getText());
			lblDobcon.setText(DOB);
			lblGendercon.setText(gender);
			lblTowncitycon.setText(town_city.getText());
			lblCountrycon.setText(country.getText());
			
			lblthisFieldMust.setForeground(new Color(0, 0, 0));
			
			/*lblFirstNameerror.setVisible(false);
			lblSurnameerror.setVisible(false);
			lblDOBerror.setVisible(false);
			lblGendererror.setVisible(false);
			lblTownerror.setVisible(false);
			lblCountryerror.setVisible(false);*/
		}
		
		return go;
	}
	
	//Method to be called that will close the current window
	private void cancelWindow(){
		
		frame.dispose();
	}

	//makes sure that only one checkbox can be ticked at any given time
	public void itemStateChanged(ItemEvent e) {
		
		Object source = e.getItemSelectable();
		
		if (source == chckbxMale){
			chckbxFemale.setSelected(false);
			gender = "Male";
		}
		else if (source == chckbxFemale){
			chckbxMale.setSelected(false);
			gender = "Female";
		}
		
	}
	
	//puts the users details into a SignUp class
	public void onConfirm(){
		
		SignUp signUp = new SignUp(firstName.getText(), surName.getText(), age, gender, town_city.getText(), e_mail.getText(),	
				password.getText(), acc_name.getText(), acc_number.getText(), card_number.getText());
		
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
