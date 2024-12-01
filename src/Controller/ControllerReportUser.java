package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import VIEW.ReportUser;

public class ControllerReportUser implements ActionListener{
	
	ReportUser reportUser;
	
	public ControllerReportUser(ReportUser reportUser) {
		super();
		this.reportUser = reportUser;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			String command = e.getActionCommand();
			if(command.equals("OK")) {
				Socket socket = new Socket("192.168.1.7", 12345);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				if(this.reportUser.textArea.getText().isEmpty()) JOptionPane.showMessageDialog(reportUser, "Bạn chưa nhập nội dung báo cáo!");
				else {
					out.writeUTF("PostReport");
					out.writeUTF(this.reportUser.lbAccout.getText());
					out.writeUTF(this.reportUser.lbIDFile.getText());
					out.writeUTF(this.reportUser.textArea.getText());
					if(in.readUTF().equals("OK")) {
						JOptionPane.showMessageDialog(reportUser, "Báo cáo của bạn đã được ghi nhận, xin hãy chờ phản hồi !");
						this.reportUser.dispose();
					}
				}
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
	}
	
}
