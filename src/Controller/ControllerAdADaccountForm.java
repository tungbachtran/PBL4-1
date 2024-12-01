package Controller;

import java.awt.event.ActionEvent;

import VIEW.AdADaccountForm;

import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.nio.channels.NonReadableChannelException;

import javax.swing.JOptionPane;

public class ControllerAdADaccountForm implements ActionListener{
	
	AdADaccountForm AdADaccountForm;
	
	

	public ControllerAdADaccountForm(AdADaccountForm AdADaccountForm) {
		
		this.AdADaccountForm = AdADaccountForm;
	}



	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("OK")) {
			try {
				Socket socket = new Socket("192.168.1.7",12345);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				if(this.AdADaccountForm.txtAccount.getText().equals("")
						||this.AdADaccountForm.txtEmail.getText().equals("")
						|| this.AdADaccountForm.txtPhone.getText().equals("")
						|| this.AdADaccountForm.passwordField.getPassword().equals(" ")
						|| this.AdADaccountForm.txtUserName.getText().equals(""))
				{
					JOptionPane.showMessageDialog(this.AdADaccountForm, "Bạn chưa nhập đủ thông tin");
				}else {
				
				char[] protectedpass1 = this.AdADaccountForm.passwordField.getPassword();
				String pass1 = new String(protectedpass1);
				
				char[] protectedpass2 = this.AdADaccountForm.passwordField_1.getPassword();
				String pass2 = new String(protectedpass2);
				
				if(pass1.equals(pass2)) {
					out.writeUTF("RegisterADMIN");
					out.writeUTF(this.AdADaccountForm.txtAccount.getText());
					out.writeUTF(pass2);
					out.writeUTF(this.AdADaccountForm.txtEmail.getText());
					out.writeUTF(this.AdADaccountForm.txtPhone.getText());
					out.writeUTF(this.AdADaccountForm.txtUserName.getText());
					
					String response = in.readUTF();
					if(response.equals("No")) {
						JOptionPane.showMessageDialog(AdADaccountForm, "Tên Tài khoản bị trùng, xin hãy đặt lại !!!!");
					}else if(response.equals("Ok")){
						String res = in.readUTF();
						System.out.println(res);
						if(res.equals("PASSAD")) {
							JOptionPane.showMessageDialog(this.AdADaccountForm, "Đăng kí thành công");
							this.AdADaccountForm.dispose();
					}
					
				}
				
				}else {JOptionPane.showMessageDialog(this.AdADaccountForm, "Mật khẩu không trùng");}
				
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}else if(command.equals("Cancel")) {
			AdADaccountForm.dispose();
		}else AdADaccountForm.dispose();
	}
	

}
