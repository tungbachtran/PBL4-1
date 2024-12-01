package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import VIEW.AdminReportForm;

public class ControllerAdminReportForm implements ActionListener{
	
	AdminReportForm adminReportForm;
	
	public ControllerAdminReportForm(AdminReportForm adminReportForm) {
		super();
		this.adminReportForm = adminReportForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if(command.equals("Post")) {
			if(this.adminReportForm.IDTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(adminReportForm, "Bạn chưa nhập IdReport");
			}else {
				try {
					if(this.adminReportForm.CmField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(adminReportForm, "Bạn chưa nhập phản hồi!");
					}else {
						Socket socket = new Socket("192.168.1.7",12345);
						DataOutputStream out = new DataOutputStream(socket.getOutputStream());
						DataInputStream in = new DataInputStream(socket.getInputStream());
						out.writeUTF("ResponseFromAdmin");
						
						out.writeUTF(this.adminReportForm.IDTextField.getText());
						out.writeUTF(this.adminReportForm.CmField.getText());
						
						if(in.readUTF().equals("OK")) {
							int rowcount = in.readInt();
							this.adminReportForm.ClearTable();
							for(int i = 0; i < rowcount;i++) {	
					            Object[] request = {in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF()};
					            this.adminReportForm.addTable(request);
					                
					         } 
							JOptionPane.showMessageDialog(adminReportForm, "Phản hồi thành công!");
						}
					}
					
					//out.writeUTF(this.adminReportForm.);
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
			
		}
		
	}

}
