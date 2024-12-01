package VIEW;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import Controller.ControllerForgetPass;
import javax.swing.SwingConstants;
import java.awt.Color;

public class ForgetPassForm extends JFrame {

	private JPanel contentPane;
	public JTextField txtLogin;
	public JButton btnHy;
	public JButton btnNewButton;
	public JTextField EmailFieldChangeF;
	public JTextField sdtFieldChangeF;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	public JPasswordField CFpasswordField;
	public JPasswordField CFpasswordField_1;
	public JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForgetPassForm frame = new ForgetPassForm();
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
	public ForgetPassForm() {
		setTitle("Quên mật khẩu!!!");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 513);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên đăng nhập :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(61, 147, 123, 21);
		contentPane.add(lblNewLabel);
		
		ActionListener actionListener = new ControllerForgetPass(this);
		
	    btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(actionListener);
		btnNewButton.setBounds(427, 377, 109, 36);
		contentPane.add(btnNewButton);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(266, 148, 287, 24);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("Email :");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMtKhu.setBounds(61, 178, 123, 21);
		contentPane.add(lblMtKhu);
		
		
		
		btnHy = new JButton("Hủy");
		btnHy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHy.setBounds(108, 377, 109, 36);
		contentPane.add(btnHy);
		btnHy.addActionListener(actionListener);
	
		
		JLabel lblngDngQun = new JLabel("Thông tin người dùng quên mật khẩu");
		lblngDngQun.setHorizontalAlignment(SwingConstants.CENTER);
		lblngDngQun.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblngDngQun.setBounds(108, 34, 392, 74);
		contentPane.add(lblngDngQun);
		
		JLabel lblXcNhnMt = new JLabel("SĐT :");
		lblXcNhnMt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblXcNhnMt.setBounds(61, 215, 156, 21);
		contentPane.add(lblXcNhnMt);
		
		EmailFieldChangeF = new JTextField();
		EmailFieldChangeF.setBounds(266, 182, 287, 24);
		contentPane.add(EmailFieldChangeF);
		EmailFieldChangeF.setColumns(10);
		
		sdtFieldChangeF = new JTextField();
		sdtFieldChangeF.setBounds(266, 216, 287, 24);
		contentPane.add(sdtFieldChangeF);
		sdtFieldChangeF.setColumns(10);
		
		lblNewLabel_1 = new JLabel("Mật khẩu mới:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(61, 280, 123, 21);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Nhập lại mật khẩu mới:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(61, 316, 185, 21);
		contentPane.add(lblNewLabel_2);
		
		CFpasswordField = new JPasswordField();
		CFpasswordField.setBounds(266, 281, 287, 24);
		contentPane.add(CFpasswordField);
		
		CFpasswordField_1 = new JPasswordField();
		CFpasswordField_1.setBounds(266, 317, 287, 24);
		contentPane.add(CFpasswordField_1);
		
		JLabel lbUser = new JLabel("UserName");
		lbUser.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbUser.setBounds(61, 246, 156, 21);
		contentPane.add(lbUser);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(266, 247, 287, 24);
		contentPane.add(textField);
	}
}
