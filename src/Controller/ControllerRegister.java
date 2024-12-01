package Controller;

import java.awt.event.ActionEvent;
import VIEW.RegisterForm;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.channels.NonReadableChannelException;

import javax.swing.JOptionPane;

public class ControllerRegister implements ActionListener{
	
	RegisterForm registerForm;
	
	

	public ControllerRegister(RegisterForm registerForm) {
		
		this.registerForm = registerForm;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("OK")) {
			try {
				Socket socket = new Socket("192.168.1.7",12345);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				if(this.registerForm.txtAccount.getText().equals("")
						||this.registerForm.txtEmail.getText().equals("")
						|| this.registerForm.txtPhone.getText().equals("")
						|| this.registerForm.passwordField.getPassword().equals(" ")
						|| this.registerForm.txtUserName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(this.registerForm, "Bạn chưa nhập đủ thông tin");
				}else {
				
				char[] protectedpass1 = this.registerForm.passwordField.getPassword();
				String pass1 = new String(protectedpass1);
				
				char[] protectedpass2 = this.registerForm.passwordField_1.getPassword();
				String pass2 = new String(protectedpass2);
				
				if(pass1.equals(pass2)) {
					out.writeUTF("Register");
					out.writeUTF(this.registerForm.txtAccount.getText());
					out.writeUTF(pass2);
					out.writeUTF(this.registerForm.txtEmail.getText());
					out.writeUTF(this.registerForm.txtPhone.getText());
					out.writeUTF(this.registerForm.txtUserName.getText());
					
					String response = in.readUTF();
					if(response.equals("No")) {
						JOptionPane.showMessageDialog(registerForm, "Tên Tài khoản bị trùng, xin hãy đặt lại !!!!");
					}else if(response.equals("Ok")){
						String res = in.readUTF();
						System.out.println(res);
						if(res.equals("PASS")) {
							JOptionPane.showMessageDialog(this.registerForm, "Đăng kí thành công");
							this.registerForm.dispose();
					}
					
				}
				
				}else {JOptionPane.showMessageDialog(this.registerForm, "Mật khẩu không trùng");}				
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}else registerForm.dispose();
	}
	

}
