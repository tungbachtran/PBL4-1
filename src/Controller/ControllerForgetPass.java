package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import com.mysql.cj.x.protobuf.MysqlxSession.AuthenticateContinueOrBuilder;

import VIEW.ForgetPassForm;

public class ControllerForgetPass implements ActionListener {
	
	ForgetPassForm forgetpassForm;
	
	public ControllerForgetPass(ForgetPassForm forgetpassForm) {
		this.forgetpassForm = forgetpassForm;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("OK")) {
			try {
				Socket socket = new Socket("192.168.1.7",12345);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				
				char[] protectedpass1 = this.forgetpassForm.CFpasswordField.getPassword();
				String pass1 = new String(protectedpass1);
				
				char[] protectedpass2 = this.forgetpassForm.CFpasswordField_1.getPassword();
				String pass2 = new String(protectedpass2);
				
				if(pass1.equals(pass2)) {
					out.writeUTF("Forget");	
					out.writeUTF(this.forgetpassForm.txtLogin.getText());
					out.writeUTF(this.forgetpassForm.EmailFieldChangeF.getText());
					out.writeUTF(this.forgetpassForm.sdtFieldChangeF.getText());
					out.writeUTF(this.forgetpassForm.textField.getText());
					out.writeUTF(pass2);
					
					String response = in.readUTF();
					if(response.equals("YES")) {
						JOptionPane.showMessageDialog(this.forgetpassForm, "Cập nhật thành công vui lòng đăng nhập lại!!!");
						this.forgetpassForm.dispose();
					}
					else JOptionPane.showMessageDialog(this.forgetpassForm, "Cập nhật thất bại");
				}else {
					JOptionPane.showMessageDialog(this.forgetpassForm, "Mật khẩu không trùng");
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}else this.forgetpassForm.dispose();
		
	}

/*
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
			try {
				Socket socket = new Socket("localhost",12345);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				
				char[] protectedpass1 = this.forgetpassForm.CFpasswordField.getPassword();
				String pass1 = new String(protectedpass1);
				
				char[] protectedpass2 = this.forgetpassForm.CFpasswordField_1.getPassword();
				String pass2 = new String(protectedpass2);
				
				if(pass1.equals(pass2)) {
					out.writeUTF("Forget");	
					out.writeUTF(this.forgetpassForm.txtLogin.getText());
					out.writeUTF(this.forgetpassForm.EmailFieldChangeF.getText());
					out.writeUTF(this.forgetpassForm.sdtFieldChangeF.getText());
					out.writeUTF(this.forgetpassForm.textField.getText());
					out.writeUTF(pass2);
					
					String response = in.readUTF();
					if(response.equals("YES")) {
						JOptionPane.showMessageDialog(this.forgetpassForm, "Cập nhật thành công vui lòng đăng nhập lại!!!");
						this.forgetpassForm.dispose();
					}
					else JOptionPane.showMessageDialog(this.forgetpassForm, "Cập nhật thất bại");
				}else {
					JOptionPane.showMessageDialog(this.forgetpassForm, "Mật khẩu không trùng");
				}
				
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	*/
}

