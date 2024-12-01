package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;


import javax.swing.JOptionPane;

import VIEW.FormSeeInfor;

public class ControllerSeeInfor implements ActionListener{

	FormSeeInfor formSeeInfor;
	
	
	
	public ControllerSeeInfor(FormSeeInfor formSeeInfor) {
		super();
		this.formSeeInfor = formSeeInfor;
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String command = e.getActionCommand();
		if(command.equals("OK")) {
			if(this.formSeeInfor.txtPhone.getText().isEmpty()||
			   this.formSeeInfor.txtEmail.getText().isEmpty()||
			   this.formSeeInfor.txtUserName.getText().isEmpty()
			   ) {
				JOptionPane.showMessageDialog(formSeeInfor, "Bạn chưa nhập đầy đủ thông tin !!!");
			}
			else {
				try {
				    Socket socket = new Socket("192.168.1.7", 12345);
				    DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				    DataInputStream in = new DataInputStream(socket.getInputStream());
				    
				    out.writeUTF("UpdateInfor");
				    out.writeUTF(this.formSeeInfor.txtAccount.getText());
				    out.writeUTF(this.formSeeInfor.txtEmail.getText());
				    out.writeUTF(this.formSeeInfor.txtPhone.getText());
				    out.writeUTF(this.formSeeInfor.txtUserName.getText());
				    
				    JOptionPane.showMessageDialog(formSeeInfor, "Đổi thông tin thành công !!");
				    
				    // Vô hiệu hóa chỉnh sửa sau khi thành công
				    this.formSeeInfor.txtPhone.setEditable(false);
				    this.formSeeInfor.txtEmail.setEditable(false);
				    this.formSeeInfor.txtUserName.setEditable(false);
				    this.formSeeInfor.BTN_OK.setVisible(false);
				    
				    // Đóng kết nối
				    in.close();
				    out.close();
				    socket.close();
				    
				} catch (Exception ex) {
				    JOptionPane.showMessageDialog(formSeeInfor, "Lỗi khi kết nối tới server: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}

				
			}
		}
		
		
	}

}
