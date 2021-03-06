/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Client;

import Base.*;

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
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.DefaultStyledDocument;

public class MainWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1855570096724948748L;
	private JFrame frame;
	private JTextField txtEmail;
	private JPasswordField pwdPassword;
	private DefaultStyledDocument doc = new DefaultStyledDocument();
        private String finalPassword;
        private String finalEmail;
        private User user;
        private int screenWidth;
	private int screenHeight;
        private String serviceip = "rmi://127.0.0.1/DateServer";
	

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
                frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
                setScreenMargins();
                int leftMargin = (screenWidth-820)/2;
                int topMargin = (screenHeight-520)/5;
		frame.getContentPane().setBackground(new Color(243, 118, 118));;
		frame.setBounds(leftMargin, topMargin, 820, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDaredate = new JLabel("Dare2Date");
		lblDaredate.setFont(new Font("Verdana", Font.BOLD | Font.ITALIC, 50));
		lblDaredate.setHorizontalAlignment(SwingConstants.CENTER);
		lblDaredate.setBounds(150, 25, 500, 78);
		frame.getContentPane().add(lblDaredate);
		
		JLabel lblNewLabel = new JLabel("<html>Dare2Date is a dating application designed by profesionals for rofessionals that want to meet other people in the local area with the same intrests and hobbies. There are various"
                        + "features including a chat service to im any users that are online and a complex match for all those V.I.P. users, so sign up and sign in and enjoy.</html>");
		lblNewLabel.setBounds(150, 130, 500, 120);
		lblNewLabel.setAutoscrolls(true);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("E-mail :");
		lblUsername.setBounds(150, 281, 70, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setBounds(150, 306, 70, 14);
		frame.getContentPane().add(lblPassword);
		
		txtEmail = new JTextField();
		txtEmail.setToolTipText("E-mail");
		txtEmail.setBounds(230, 281, 185, 20);
		frame.getContentPane().add(txtEmail);
		txtEmail.setColumns(10);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                            
                            
                            setLogin();
                            signIn();
                            
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
				
                            try {
                                new ProfileWindow();
                            } catch (RemoteException ex) {
                                Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                            }
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
		pwdPassword.setBounds(230, 303, 185, 17);
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
		
		try {
			//Create a reference to the service interface at the location.
			ServiceInterface service = (ServiceInterface) Naming.lookup(serviceip);
			//Create a response object
			Response res = new Response();
			//Invoke server SignUp method
                        if((!finalEmail.equals(""))&&(!finalPassword.equals("")))
                            res = service.Login(finalEmail,finalPassword);
                        else{
                            JOptionPane.showMessageDialog(null, "either no E-mail or no Password was entered please try again", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
			//Test response
			if (res.getError() != null) {
                                JOptionPane.showMessageDialog(null,res.getError() , "Error", JOptionPane.ERROR_MESSAGE);
				System.out.println("There was an error.");
			} else {
				System.out.println("Everything went okay.");
				System.out.println(res.getResponse());
                                user = (User)res.getResponse();
                                new ProfileWindow(user,1);
                                cancelWindow();
			}
		} catch (NotBoundException ex) {
			System.out.println(ex);
		} catch (MalformedURLException ex) {
			System.out.println(ex);
		} catch (RemoteException ex) {
			System.out.println(ex);
		}
               
	}
        
        public void setLogin(){
                finalPassword = String.valueOf(pwdPassword.getPassword());
                finalEmail = txtEmail.getText();
        }
	
	//Method to be called that will close the current window
	private void cancelWindow(){
			
		frame.dispose();
	}
        
        public void setScreenMargins(){
		
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		screenHeight = (int)screen.getHeight();
		screenWidth = (int)screen.getWidth(); 
		
	}
}
