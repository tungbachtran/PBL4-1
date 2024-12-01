package Controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JFileChooser;
import VIEW.FormPostFile;

public class ControllerPostFile implements ActionListener{
	FormPostFile formPostFile;
	final File[] filetoSend = new File[1];
	
	public ControllerPostFile(FormPostFile formPostFile) {
		this.formPostFile = formPostFile;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		JFileChooser fc = new JFileChooser();
		
		if(command.equals("Link")) {
			int returnvalue = fc.showOpenDialog(this.formPostFile);
			if(returnvalue == JFileChooser.APPROVE_OPTION) {
				filetoSend[0] = fc.getSelectedFile();
				this.formPostFile.textField.setText(filetoSend[0].getName());
				
			}
		}else if(command.equals("Upfile")) {
			if(this.formPostFile.textField.getText().trim().isEmpty() ||
		     	this.formPostFile.txtNameDoc.getText().trim().isEmpty() ||
		     	this.formPostFile.JTFName.getText().trim().isEmpty()
					) {
				this.formPostFile.lblNewLabel_1.setText("Bạn chưa nhập hết nội dung");
					
			}else {
				try {
					Socket socket = new Socket("192.168.1.7",12345);
					DataOutputStream out = new DataOutputStream(socket.getOutputStream());
					DataInputStream in = new DataInputStream(socket.getInputStream());
					
					out.writeUTF("Upfile");
					
					
					
					String fileString = this.formPostFile.JTFName.getText();
					out.writeUTF(fileString);
					out.writeUTF((String)this.formPostFile.comboBox.getSelectedItem());
					out.writeUTF(this.formPostFile.txtNameDoc.getText());
					out.writeUTF((String)this.formPostFile.comboBox_1.getSelectedItem());
					out.writeUTF(this.formPostFile.textField.getText());
					out.writeUTF(this.formPostFile.lblAccount.getText());
					
					String k = in.readUTF();
					if(k.equals("OK")) {	
						 File fileToSend = new File(filetoSend[0].getAbsolutePath());
		                 FileInputStream fileInputStream = new FileInputStream(fileToSend);
		                 BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
		                 byte[] buffer = new byte[4096];
		                 int bytesRead;
		                 int i = 0;
		                 out.writeLong(fileToSend.length());
		                 while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
		                     out.write(buffer, 0, bytesRead);
		                 }
		                 bufferedInputStream.close();
		                 fileInputStream.close();
			            System.out.println("Kết thúc");
			            out.flush();
			            String requestString = in.readUTF();
						
						if(requestString.equals("OK")) {
							this.formPostFile.lblNewLabel_1.setText("Gửi file thành công ");
						}else this.formPostFile.lblNewLabel_1.setText("Gửi file thất bại");
						
					}else if(k.equals("NameFile")) {
						this.formPostFile.lblNewLabel_1.setText("Tên File đã tồn tại, xin vui lòng đổi lại");
					}else if(k.equals("NameDocument")) {
						this.formPostFile.lblNewLabel_1.setText("Tên Document đã tồn tại, xin vui lòng đổi lại");
					}else if(k.equals("DocumentFile")) {
						this.formPostFile.lblNewLabel_1.setText("Tên File và Document đã tồn tại, xin vui lòng đổi lại ");
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			
			}
		}else if (command.equals("Hủy")) {
			formPostFile.dispose();
		}
	}
	
	

}
