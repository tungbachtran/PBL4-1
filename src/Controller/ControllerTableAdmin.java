package Controller;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import VIEW.QLFilesForm;

public class ControllerTableAdmin implements MouseListener{
	
	QLFilesForm view;
	
	
	
	public ControllerTableAdmin(QLFilesForm view) {
		super();
		this.view = view;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket("192.168.1.7",12345);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			int column = this.view.table.getColumnModel().getColumnIndexAtX(e.getX());
            int row = e.getY() / this.view.table.getRowHeight();
            if (column == 0 && row < this.view.table.getRowCount()) {
            	Object value = this.view.table.getValueAt(row, column);
            	out.writeUTF("DownloadFile");
            	out.writeUTF(value.toString());
            	
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
				JOptionPane.showMessageDialog(view, "Tài liệu của bạn đang được tạm lưu ở " + "C:\\TempFile\\" + nameFile);
            }
		} catch (Exception e2) {
			// TODO: handle exception
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
