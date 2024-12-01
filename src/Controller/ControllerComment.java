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

import VIEW.ViewComment;

public class ControllerComment implements ActionListener{
	ViewComment viewComment;

	public ControllerComment(ViewComment viewComment) {
		super();
		this.viewComment = viewComment;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		try {
			Socket socket = new Socket("192.168.1.7",12345);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			if(command.equals("Download")) {
				
				String labelTextString = this.viewComment.hiddenlabel.getText();
				int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn tải tài liệu không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int saveOption = fileChooser.showSaveDialog(null);
					if (saveOption == JFileChooser.APPROVE_OPTION) {
						String filePath = fileChooser.getSelectedFile().getAbsolutePath();
						
						
						out.writeUTF("DownloadFile");
						out.writeUTF(labelTextString);
						
						long Length = in.readLong();
						String nameFile = in.readUTF();
						int bytesRead;
						byte[] buffer = new byte[4096];						
						File file = new File(filePath + "\\" + nameFile);
						FileOutputStream fileOutputStream = new FileOutputStream(file);
						BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
						while (Length > 0 && (bytesRead = in.read(buffer, 0, (int) Math.min(buffer.length, Length))) != -1) {
							bufferedOutputStream.write(buffer, 0, bytesRead);
							Length -= bytesRead;
						}
						bufferedOutputStream.close();
						fileOutputStream.close();
						
						JOptionPane.showMessageDialog(viewComment, "Tải File thành công !!!");
						}
				}
			}else if(command.equals("Preview")) {
				out.writeUTF("DownloadFile");
				out.writeUTF(this.viewComment.hiddenlabel.getText());
				
				long Length = in.readLong();
				String nameFile = in.readUTF();
				int bytesRead;
				byte[] buffer = new byte[4096];						
				File file1 = new File("C:\\TempFile");
				if(!file1.exists()) {
					file1.mkdir();
				}
				File file = new File("C:\\TempFile\\" + nameFile);
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
				while (Length > 0 && (bytesRead = in.read(buffer, 0, (int) Math.min(buffer.length, Length))) != -1) {
					bufferedOutputStream.write(buffer, 0, bytesRead);
					Length -= bytesRead;
				}
				bufferedOutputStream.close();
				fileOutputStream.close();
				Desktop.getDesktop().open(file);
				JOptionPane.showMessageDialog(viewComment, "Tài liệu của bạn đang được tạm lưu ở " + "C:\\TempFile\\" + nameFile);
			}else if(command.equals("Post")) {
				if(this.viewComment.CmField.getText().isEmpty()) {
					JOptionPane.showMessageDialog(this.viewComment, "Bạn chưa nhập nội dung bình luận");
				}
				else {
					LocalDateTime currentDateTime = LocalDateTime.now();

			        // Định dạng theo mẫu tương tự SimpleDateFormat
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");	        
			        String formattedDateTime = currentDateTime.format(formatter);
			        
			        out.writeUTF("UpComment");
			        out.writeUTF(this.viewComment.lbIDFile.getText());
			        out.writeUTF(this.viewComment.lblhiddenAcc.getText());
			        out.writeUTF(this.viewComment.lbAccount.getText());
			        out.writeUTF(this.viewComment.CmField.getText());
			        out.writeUTF(formattedDateTime);
			        this.viewComment.CmField.setText("");
			        
			        int length = in.readInt();
					this.viewComment.ClearLabel();
					for(int i = 0; i < length; i++) {
						String Account = in.readUTF();
						if(Account.equals(this.viewComment.lbAccount.getText())) {
							this.viewComment.addNewLabelWithColor(in.readUTF());
						}else this.viewComment.addNewLabel(in.readUTF());
						
					}
				}
			}else if(command.equals("Cancel")) {
				this.viewComment.dispose();
			}
			
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
}
	

