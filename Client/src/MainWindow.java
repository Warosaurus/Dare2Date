import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.RootPaneContainer;
import javax.swing.SwingConstants;


public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1855570096724948748L;
	private JFrame frame;
	private JTextField txtEmail;
	private JPasswordField pwdPassword;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
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
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(250, 240, 230));
		frame.setBounds(100, 100, 820, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDaredate = new JLabel("Dare2Date");
		lblDaredate.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 50));
		lblDaredate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDaredate.setBounds(150, 25, 500, 78);
		frame.getContentPane().add(lblDaredate);
		
		JLabel lblNewLabel = new JLabel("<html>Dare2Date is a dating application designed by young profesionals for young professionals that want to meetother people in the loacal area with the same intrests and hobbies........MORE CONTENT NEEDED</html>");
		lblNewLabel.setBounds(150, 130, 500, 140);
		lblNewLabel.setAutoscrolls(true);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("E-mail :");
		lblUsername.setBounds(150, 281, 96, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(150, 306, 96, 14);
		frame.getContentPane().add(lblPassword);
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("E-mail");
		txtEmail.setHorizontalAlignment(SwingConstants.CENTER);
		txtEmail.setBounds(255, 281, 160, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(150, 331, 107, 23);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblOr = new JLabel("Or");
		lblOr.setBounds(274, 335, 20, 14);
		frame.getContentPane().add(lblOr);
		
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				signUp();
			}
		});
		btnNewButton_1.setBounds(304, 331, 111, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Continue As\r\n Free User");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				signIn();
				cancelWindow();
			}
		});
		btnNewButton_2.setBounds(490, 281, 160, 73);
		frame.getContentPane().add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("Or");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(441, 303, 20, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		pwdPassword = new JPasswordField();
		pwdPassword.setToolTipText("Password");
		pwdPassword.setBounds(255, 303, 160, 17);
		frame.getContentPane().add(pwdPassword);
		
		onDrawPictures();
		
		}
	
	
	public void onDrawPictures(){
		
		JLabel pic_1 = new JLabel("");
		pic_1.setIcon(new ImageIcon(MainWindow.class.getResource("/myImages/pic1.jpg")));
		pic_1.setBounds(10, 382, 82, 90);
		frame.getContentPane().add(pic_1);
		
		JLabel pic_2 = new JLabel("");
		pic_2.setIcon(new ImageIcon(MainWindow.class.getResource("/myImages/pic2.jpg")));
		pic_2.setBounds(98, 382, 82, 90);
		frame.getContentPane().add(pic_2);
		
		JLabel pic_3 = new JLabel("");
		pic_3.setIcon(new ImageIcon(MainWindow.class.getResource("/myImages/pic3.jpg")));
		pic_3.setBounds(186, 382, 82, 90);
		frame.getContentPane().add(pic_3);
		
		JLabel pic_4 = new JLabel("");
		pic_4.setIcon(new ImageIcon(MainWindow.class.getResource("/myImages/pic4.jpg")));
		pic_4.setBounds(274, 382, 82, 90);
		frame.getContentPane().add(pic_4);
		
		JLabel pic_5 = new JLabel("");
		pic_5.setIcon(new ImageIcon(MainWindow.class.getResource("/myImages/pic5.jpg")));
		pic_5.setBounds(362, 382, 82, 90);
		frame.getContentPane().add(pic_5);
		
		JLabel pic_6 = new JLabel("");
		pic_6.setIcon(new ImageIcon(MainWindow.class.getResource("/myImages/pic6.jpg")));
		pic_6.setBounds(450, 382, 82, 90);
		frame.getContentPane().add(pic_6);
		
		JLabel pic_7 = new JLabel("");
		pic_7.setIcon(new ImageIcon(MainWindow.class.getResource("/myImages/pic7.jpg")));
		pic_7.setBounds(538, 382, 82, 90);
		frame.getContentPane().add(pic_7);
		
		JLabel pic_8 = new JLabel("");
		pic_8.setIcon(new ImageIcon(MainWindow.class.getResource("/myImages/pic8.jpg")));
		pic_8.setBounds(626, 382, 82, 90);
		frame.getContentPane().add(pic_8);
		
		JLabel pic_9 = new JLabel("");
		pic_9.setIcon(new ImageIcon(MainWindow.class.getResource("/myImages/pic9.jpg")));
		pic_9.setBounds(712, 382, 82, 90);
		frame.getContentPane().add(pic_9);
	}
	
	public void signUp(){
		
		new FormDetailsWindow();
	}
	
	public void signIn(){
		
		User user = new User();
		user.setLevel(1);
		
		new ProfileWindow(user);
	}
	
	//Method to be called that will close the current window
	private void cancelWindow(){
			
		frame.dispose();
	}
}
