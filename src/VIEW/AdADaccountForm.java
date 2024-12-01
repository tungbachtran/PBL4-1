package VIEW;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.ControllerAdADaccountForm;
import javax.swing.SwingConstants;
import java.awt.Color;


public class AdADaccountForm extends JFrame {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdADaccountForm frame = new AdADaccountForm();
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
	public AdADaccountForm() {
		setTitle("Đăng kí");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 630, 549);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên đăng nhập :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(41, 92, 123, 21);
		contentPane.add(lblNewLabel);
		
		ActionListener actionListener = new ControllerAdADaccountForm(this);
		
	    btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(actionListener);
		btnNewButton.setBounds(424, 429, 109, 36);
		contentPane.add(btnNewButton);
		
		txtAccount = new JTextField();
		txtAccount.setBounds(246, 93, 287, 24);
		contentPane.add(txtAccount);
		txtAccount.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu :");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMtKhu.setBounds(41, 288, 123, 21);
		contentPane.add(lblMtKhu);
		
		
		
		btnHy = new JButton("Cancel");
		btnHy.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnHy.setBounds(84, 429, 109, 36);
		btnHy.addActionListener(actionListener);
		contentPane.add(btnHy);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(246, 289, 287, 24);
		contentPane.add(passwordField);
		
		JLabel lblngDngQun = new JLabel("Tạo tài khoản Admin");
		lblngDngQun.setHorizontalAlignment(SwingConstants.CENTER);
		lblngDngQun.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblngDngQun.setBounds(156, 11, 310, 74);
		contentPane.add(lblngDngQun);
		
		JLabel lblXcNhnMt = new JLabel("Xác nhận mật khẩu :");
		lblXcNhnMt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblXcNhnMt.setBounds(41, 335, 156, 21);
		contentPane.add(lblXcNhnMt);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(246, 336, 287, 24);
		contentPane.add(passwordField_1);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(41, 149, 123, 21);
		contentPane.add(lblEmail);
		
		lblSdt = new JLabel("SDT: ");
		lblSdt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSdt.setBounds(41, 200, 123, 21);
		contentPane.add(lblSdt);
		
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(246, 152, 287, 24);
		contentPane.add(txtEmail);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(246, 203, 287, 24);
		contentPane.add(txtPhone);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(41, 241, 123, 21);
		contentPane.add(lblUsername);
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(246, 244, 287, 24);
		contentPane.add(txtUserName);
	}
}
