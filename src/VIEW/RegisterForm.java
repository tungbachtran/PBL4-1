package VIEW;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.ControllerLogin;
import Controller.ControllerRegister;
import Controller.ControllerTogglePasswordHandler;

import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class RegisterForm extends JFrame {

	private JPanel contentPane;
	public JTextField txtAccount;
	public JPasswordField passwordField;
	public JButton btnHy;
	public JButton btnNewButton;
	public JPasswordField passwordField_1;
	
	public JLabel lblEmail;
	public JLabel lblSdt;
	public JTextField txtEmail;
	public JTextField txtPhone;
	public JTextField txtUserName;
	public JLabel ShowPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterForm frame = new RegisterForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterForm() {
		setTitle("Đăng kí");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 674, 540);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên đăng nhập :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(89, 103, 123, 21);
		contentPane.add(lblNewLabel);
		
		ActionListener actionListener = new ControllerRegister(this);
		
	    btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(actionListener);
		btnNewButton.setBounds(472, 440, 109, 36);
		contentPane.add(btnNewButton);
		
		txtAccount = new JTextField();
		txtAccount.setBounds(294, 104, 287, 24);
		contentPane.add(txtAccount);
		txtAccount.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu :");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMtKhu.setBounds(89, 299, 123, 21);
		contentPane.add(lblMtKhu);
		
		
		
		btnHy = new JButton("Cancel");
		btnHy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnHy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHy.setBounds(132, 440, 109, 36);
		contentPane.add(btnHy);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(294, 300, 287, 24);
		contentPane.add(passwordField);
		
		JLabel lblngDngQun = new JLabel("Đăng kí tài khoản mới");
		lblngDngQun.setHorizontalAlignment(SwingConstants.CENTER);
		lblngDngQun.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblngDngQun.setBounds(172, 11, 310, 74);
		contentPane.add(lblngDngQun);
		
		JLabel lblXcNhnMt = new JLabel("Xác nhận mật khẩu :");
		lblXcNhnMt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblXcNhnMt.setBounds(89, 346, 156, 21);
		contentPane.add(lblXcNhnMt);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(294, 347, 287, 24);
		contentPane.add(passwordField_1);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(89, 160, 123, 21);
		contentPane.add(lblEmail);
		
		lblSdt = new JLabel("SDT: ");
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSdt.setBounds(89, 211, 123, 21);
		contentPane.add(lblSdt);
		
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(294, 163, 287, 24);
		contentPane.add(txtEmail);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(294, 214, 287, 24);
		contentPane.add(txtPhone);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(89, 252, 123, 21);
		contentPane.add(lblUsername);
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(294, 255, 287, 24);
		contentPane.add(txtUserName);
		
		JLabel ShowPass = new JLabel("New label");
		ShowPass.setIcon(new ImageIcon(RegisterForm.class.getResource("/Icon/eye.jpg")));
		ShowPass.setBounds(592, 302, 33, 21);
		contentPane.add(ShowPass);
		ShowPass.addMouseListener(new ControllerTogglePasswordHandler(passwordField));
	}
}
