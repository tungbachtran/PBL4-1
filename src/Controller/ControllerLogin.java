package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;

import javax.swing.JOptionPane;

import VIEW.AdminForm;
import VIEW.LoginForm;
import VIEW.MainForm;

public class ControllerLogin implements ActionListener{
	
	
	LoginForm loginForm;
	AdminForm adminForm;
	
	public ControllerLogin(LoginForm loginForm) {
		this.loginForm = loginForm;
	}
	

	public ControllerLogin(AdminForm adminForm) {
		super();
		this.adminForm = adminForm;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("OK")) {
			try {
				Socket socket = new Socket("192.168.1.7",12345);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				
				
				
				char[] protectedpass = this.loginForm.passwordField.getPassword();
				String password = new String(protectedpass);
				//String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt())
				out.writeUTF("Login");
				out.writeUTF(this.loginForm.txtLogin.getText());
				out.writeUTF(password);
				
				String requestfromserver = in.readUTF();
				
				
				if(requestfromserver.equals("OK")) {
					JOptionPane.showMessageDialog(this.loginForm, "Đăng nhập thành công");
					if((in.readUTF()).equals("user")) {
						MainForm mainForm = new MainForm();
						mainForm.lblUsername.setText(in.readUTF());
						mainForm.setDefaultCloseOperation(3);
						mainForm.setVisible(true);
						mainForm.lblAccount.setText(this.loginForm.txtLogin.getText());
						loginForm.dispose();
					}else {
						AdminForm adminForm = new AdminForm();
						adminForm.lblUserName.setText(in.readUTF());
						adminForm.setDefaultCloseOperation(3);
						adminForm.setVisible(true);
						adminForm.lblAccount.setText(this.loginForm.txtLogin.getText());
						loginForm.dispose();
					}
				}else if(requestfromserver.equals("No")) {
					JOptionPane.showMessageDialog(this.loginForm, "Đăng nhập thất bại");
				}
				
				
				in.close();
				out.close();
				socket.close();
				
				
			}catch(Exception e23) {
				System.out.println("sai day ne");;
			}
		}
		
	}
	
	

}
