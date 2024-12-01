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
import Controller.ControllerLoginLabel;
import Controller.ControllerTogglePasswordHandler;

import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {

	
	private JPanel contentPane;
	public JTextField txtLogin;
	public JPasswordField passwordField;
	public JButton btnHy;
	public JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private JLabel ShowPass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
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
	public LoginForm() {
		setTitle("Đăng nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 827, 598);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên đăng nhập");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(84, 270, 123, 21);
		contentPane.add(lblNewLabel);
		
		ActionListener actionListener = new ControllerLogin(this);
		
	    btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(actionListener);
		btnNewButton.setBounds(598, 454, 109, 36);
		contentPane.add(btnNewButton);
		
		txtLogin = new JTextField();
		txtLogin.setBounds(335, 271, 372, 24);
		contentPane.add(txtLogin);
		txtLogin.setColumns(10);
		
		JLabel lblMtKhu = new JLabel("Mật khẩu");
		lblMtKhu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMtKhu.setBounds(84, 314, 123, 21);
		contentPane.add(lblMtKhu);
		
		
		
		btnHy = new JButton("Cancel");
		btnHy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnHy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnHy.setBounds(138, 454, 109, 36);
		contentPane.add(btnHy);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(335, 315, 372, 24);
		contentPane.add(passwordField);
		MouseListener mouseListener = new ControllerLoginLabel();
		
		JLabel lblChaCTi = new JLabel("Chưa có tài khoản, Đăng kí ngay");
		lblChaCTi.addMouseListener(mouseListener);
		lblChaCTi.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblChaCTi.setBounds(84, 373, 273, 21);
		contentPane.add(lblChaCTi);
		
		JLabel lblngDngQun = new JLabel("");
		lblngDngQun.setIcon(new ImageIcon(LoginForm.class.getResource("/Icon/avatar.png")));
		lblngDngQun.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblngDngQun.setBounds(329, 39, 140, 153);
		contentPane.add(lblngDngQun);
		
		JLabel lblForgotPass = new JLabel("Quên mật khẩu ");
		lblForgotPass.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblForgotPass.setBounds(84, 405, 140, 30);
		lblForgotPass.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ForgetPassForm forgetPassForm = new ForgetPassForm();
				forgetPassForm.setDefaultCloseOperation(3);
				forgetPassForm.setVisible(true);			
			}
		});
		contentPane.add(lblForgotPass);
		
		lblNewLabel_1 = new JLabel("Files Manager");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel_1.setBounds(297, 199, 207, 48);
		contentPane.add(lblNewLabel_1);
		
		ShowPass = new JLabel("New label");
		ShowPass.setIcon(new ImageIcon(LoginForm.class.getResource("/Icon/eye.jpg")));
		ShowPass.setBounds(717, 319, 33, 21);
		contentPane.add(ShowPass);
		ShowPass.addMouseListener(new ControllerTogglePasswordHandler(passwordField));
	}
}
