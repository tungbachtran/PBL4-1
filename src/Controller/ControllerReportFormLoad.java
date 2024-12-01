package Controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import VIEW.AdminReportForm;
import VIEW.QLFilesForm;

public class ControllerReportFormLoad implements WindowListener{
	
	AdminReportForm adminReportForm;
	
	
	public ControllerReportFormLoad(AdminReportForm adminReportForm) {
		super();
		this.adminReportForm = adminReportForm;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket("192.168.1.7", 12345);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			out.writeUTF("LoadReport");
			
			int rowcount = in.readInt();
			this.adminReportForm.ClearTable();
			for(int i = 0; i < rowcount;i++) {	
	            Object[] request = {in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF()};
	            this.adminReportForm.addTable(request);
	                
	         } 
		} catch (Exception e2) {
			// TODO: handle exception
		}
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
