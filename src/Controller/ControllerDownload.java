package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import VIEW.AdminForm;
import VIEW.MainForm;

public class ControllerDownload implements MouseListener{
	
	MainForm mainForm;
	AdminForm adminForm;
	public ControllerDownload(MainForm mainForm) {
		// TODO Auto-generated constructor stub
		this.mainForm = mainForm;
	}
	
	public ControllerDownload(AdminForm adminForm) {
		super();
		this.adminForm = adminForm;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		String labelTextString = ((JLabel)e.getSource()).getText();
			try {
				int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn tải tài liệu không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
				if(option == JOptionPane.YES_OPTION) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int saveOption = fileChooser.showSaveDialog(null);
					if (saveOption == JFileChooser.APPROVE_OPTION) {
						String filePath = fileChooser.getSelectedFile().getAbsolutePath();
						
						Socket socket = new Socket("192.168.1.7",12345);
						DataOutputStream out = new DataOutputStream(socket.getOutputStream());
						DataInputStream in = new DataInputStream(socket.getInputStream());
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
						
						}
				}
				
			}catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
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

}
