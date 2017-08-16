package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import rules.MainController;

public class LoginFrame extends JFrame {
	private MainController mainController;
	JButton login_button,sign_up;
    JLabel usernamelabel, password_label;
    JTextField usernamefield;
    JPasswordField password_field;
    JPanel panel;
    	
	public LoginFrame(MainController mainController){
		this.mainController = mainController;
		initSwingComponents();
	}
	public LoginFrame() {
		initSwingComponents();
	}
	private void initSwingComponents() {
		setTitle("Musify");
        setBounds(100, 100, 900, 590);
        setLayout(null);
        setResizable(false);

        JLabel background = new JLabel(new ImageIcon("images/back.jpg"));
        setContentPane(background);
        
        usernamelabel = new JLabel("Username : ");
        usernamelabel.setBounds(400, 60, 210, 30);
        
        usernamefield = new JTextField();
        usernamefield.setBounds(400, 90, 210, 30);
        
        password_label = new JLabel("Password : ");
        password_label.setBounds(400, 120, 210, 30);
        
        password_field = new JPasswordField();
        password_field.setBounds(400, 150, 210, 30);
        
        login_button = new JButton("Login");
        login_button.setBounds(400, 200, 100, 30);
        sign_up = new JButton("Sign up");
        sign_up.setBounds(510, 200, 100, 30);
        login_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					System.out.println("Login requested");
					mainController.login(usernamefield.getText(),password_field.getText());
				} catch (SQLException e) {
					// TODO Show error dialog
					e.printStackTrace();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
        sign_up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainController.register();
				setVisible(false);
			}
		});
        add(usernamelabel);
        add(usernamefield);
        add(password_label);
        add(password_field);
        add(login_button);
        add(sign_up);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().setDefaultButton(login_button);
	}
	public static void main(String args[]) {
		new LoginFrame();
	}
}
