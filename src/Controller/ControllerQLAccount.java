package Controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import VIEW.AdADaccountForm;
import VIEW.QLTaikhoanForm;

public class ControllerQLAccount implements ActionListener{
	QLTaikhoanForm QLTaikhoanForm;

	public ControllerQLAccount(QLTaikhoanForm QLTaikhoanForm) {
		super();
		this.QLTaikhoanForm = QLTaikhoanForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		try {
			Socket socket = new Socket("192.168.1.7",12345);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			if(command.equals("Show")) {
				this.QLTaikhoanForm.ClearLabel();
				out.writeUTF("ShowAccount");
				int rowcount = in.readInt();
				
			for (int i = 0; i < rowcount; i++) {        
				Object[] request = {in.readUTF(),in.readUTF(),in.readUTF()};
				this.QLTaikhoanForm.addTable(request);}
			}else if(command.equals("Add ADMIN")) {
				AdADaccountForm ad = new AdADaccountForm();
				ad.setVisible(true);
			}else if(command.equals("Cancel")) {
				this.QLTaikhoanForm.dispose();
			}else if(command.equals("Delete")) {
				String text = this.QLTaikhoanForm.UserNameText.getText();
				if (text.isEmpty()) { 
					JOptionPane.showMessageDialog(this.QLTaikhoanForm, "Vui lòng nhập UserName cần thực hiện!!!");
		        } else {
		        // Hiển thị hộp thoại xác nhận
		        int choice = JOptionPane.showConfirmDialog(this.QLTaikhoanForm,
		                "Bạn có chắc chắn muốn xóa \"" + text + "\" không?",
		                "Xác Nhận Xóa",
		                JOptionPane.YES_NO_OPTION);

		        // Xử lý lựa chọn của người dùng
		        if (choice == JOptionPane.YES_OPTION) {
		        	out.writeUTF("DeleteAccount");
					out.writeUTF(this.QLTaikhoanForm.UserNameText.getText());
					if(in.readUTF().equals("OK")) {
		    			JOptionPane.showMessageDialog(this.QLTaikhoanForm, "Xóa tài khoản thành công");
						}else {
							JOptionPane.showMessageDialog(QLTaikhoanForm, "Tài khoản này không tồn tại !!!");
						}
		        	}
		        }
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
}
	

