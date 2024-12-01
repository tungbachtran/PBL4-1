package VIEW;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import Controller.ControllerSeeInfor;

import javax.swing.SwingConstants;
import java.awt.Color;

public class FormSeeInfor extends JFrame {

	private JPanel contentPane;
	public JTextField txtAccount;
	public JButton btnHy;
	public JButton btnNewButton;
	
	public JLabel lblEmail;
	public JLabel lblSdt;
	public JTextField txtEmail;
	public JTextField txtPhone;
	public JTextField txtUserName;
	public JButton BTN_OK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormSeeInfor frame = new FormSeeInfor();
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
	public FormSeeInfor() {
		setTitle("Xem thông tin");
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
		
	
		
		txtAccount = new JTextField();
		txtAccount.setBounds(222, 104, 287, 24);
		txtAccount.setEditable(false);
		contentPane.add(txtAccount);
		txtAccount.setColumns(10);
		
		
		
		btnHy = new JButton("Hủy");
		btnHy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHy.setBounds(141, 404, 109, 36);
		contentPane.add(btnHy);
		btnHy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		JLabel lblngDngQun = new JLabel("Xem thông tin cá nhân");
		lblngDngQun.setHorizontalAlignment(SwingConstants.CENTER);
		lblngDngQun.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblngDngQun.setBounds(172, 11, 310, 74);
		contentPane.add(lblngDngQun);
		
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
		txtEmail.setBounds(222, 161, 287, 24);
		txtEmail.setEditable(false);
		contentPane.add(txtEmail);
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		txtPhone.setBounds(222, 212, 287, 24);
		txtPhone.setEditable(false);
		contentPane.add(txtPhone);
		
		JLabel lblUsername = new JLabel("UserName");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(89, 271, 123, 21);
		contentPane.add(lblUsername);
		
		txtUserName = new JTextField();
		txtUserName.setColumns(10);
		txtUserName.setBounds(222, 272, 287, 24);
		
		txtUserName.setEditable(false);
		contentPane.add(txtUserName);
		
		ActionListener actionListener = new ControllerSeeInfor(this);
		
		BTN_OK = new JButton("OK");
		BTN_OK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BTN_OK.setBounds(279, 337, 109, 36);
		contentPane.add(BTN_OK);
		BTN_OK.setVisible(false);
		ActionListener []actionListeners = BTN_OK.getActionListeners();
		for (ActionListener listener : actionListeners) {
            BTN_OK.removeActionListener(listener);
        }
		BTN_OK.addActionListener(actionListener);
		
		
		
	    btnNewButton = new JButton("Sửa thông tin");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(actionListener);
		btnNewButton.setBounds(451, 404, 149, 36);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtEmail.setEditable(true);
				txtPhone.setEditable(true);
				txtUserName.setEditable(true);
				BTN_OK.setVisible(true);
			}
		});
		
	}
}
