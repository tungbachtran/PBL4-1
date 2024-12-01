package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.net.Socket;

import javax.swing.JOptionPane;

import VIEW.QLFilesForm;

public class ControllerQLFilesForm implements ActionListener{
	
	QLFilesForm viewFilesForm;
	
	
	public ControllerQLFilesForm(QLFilesForm viewFilesForm) {
		super();
		this.viewFilesForm = viewFilesForm;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String command = e.getActionCommand();
		if(this.viewFilesForm.IdText.getText().isEmpty()) {
			JOptionPane.showMessageDialog(viewFilesForm, "Bạn chưa nhập ID của file");
			
		}else {
			try {
				Socket socket = new Socket("192.168.1.7",12345);
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				
				if(command.equals("Delete")) {
					out.writeUTF("GetNameDocumentById");
					out.writeUTF(this.viewFilesForm.IdText.getText());
					String nameDocument = in.readUTF();
					int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa tài liệu " + nameDocument + " không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
					if(option == JOptionPane.YES_OPTION) {
						
						out.writeUTF("OK");
					
						int rowcount = in.readInt();
						this.viewFilesForm.ClearLabel();
						for(int i = 0; i < rowcount;i++) {
							//String nameDocument = in.readUTF();
							//this.mainForm.addNewLabel(nameDocument);
							Object[] request = {in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF()};
							this.viewFilesForm.addTable(request);
						}
						JOptionPane.showMessageDialog(viewFilesForm, "Xóa file " + nameDocument + " thành công");
					}	
				}else if(command.equals("Lock")) {
					out.writeUTF("LockFile");
					out.writeUTF(this.viewFilesForm.IdText.getText());
					String nameDocument = in.readUTF();
					String Status = in.readUTF();
					if(Status.equals("Locked")) {
						JOptionPane.showMessageDialog(viewFilesForm, "File " + nameDocument + " đã bị khóa, không thể khóa file này");
					}else {
						int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn khóa tài liệu " + nameDocument + " không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
						if(option == JOptionPane.YES_OPTION) {
							
							out.writeUTF("OK");
							
							int rowcount = in.readInt();
							this.viewFilesForm.ClearLabel();
							for(int i = 0; i < rowcount;i++) {
								//String nameDocument = in.readUTF();
								//this.mainForm.addNewLabel(nameDocument);
								Object[] request = {in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF()};
								this.viewFilesForm.addTable(request);
							}
							JOptionPane.showMessageDialog(viewFilesForm, "Khóa file " + nameDocument + " thành công");
						
						}
					}
					
				}else if(command.equals("UnLock")) {
					out.writeUTF("UnLockFile");
					out.writeUTF(this.viewFilesForm.IdText.getText());
					String nameDocument = in.readUTF();
					String Status = in.readUTF();
					if(Status.equals("Unlocked")) {
						JOptionPane.showMessageDialog(viewFilesForm, "File " + nameDocument + " không khóa, bạn không thể mở khóa file này");
					}else {
						int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn mở khóa tài liệu " + nameDocument + " không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
						if(option == JOptionPane.YES_OPTION) {
							
							out.writeUTF("OK");
							
							int rowcount = in.readInt();
							this.viewFilesForm.ClearLabel();
							for(int i = 0; i < rowcount;i++) {
								//String nameDocument = in.readUTF();
								//this.mainForm.addNewLabel(nameDocument);
								Object[] request = {in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF()};
								this.viewFilesForm.addTable(request);
							}
							JOptionPane.showMessageDialog(viewFilesForm, "Mở khóa file " + nameDocument + " thành công");
						
						}
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
	}
	
}
