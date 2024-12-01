package Controller;


import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import VIEW.QLFilesForm;

public class ControllerLoadQLFilesForm implements WindowListener{
	
	QLFilesForm viewForm;
	Timer timer;
	/*
	Socket socket; 
	DataInputStream in;
	DataOutputStream out;
	*/
	
	
	public ControllerLoadQLFilesForm(QLFilesForm viewForm) {
		super();
		
		this.viewForm = viewForm;
		
		/*
		try {
			socket = new Socket("localhost", 12345);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		startTimer();
		}catch (Exception e) {
			// TODO: handle exception
		}
		*/
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
		try {
			Socket socket = new Socket("192.168.1.7", 12345);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			out.writeUTF("GetAllFile");
			
			int rowcount = in.readInt();
			this.viewForm.ClearLabel();
			for(int i = 0; i < rowcount;i++) {
				
				
	                Object[] request = {in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF()};
	                this.viewForm.addTable(request);
	                
	            } 
			
		}catch (Exception e1) {
			// TODO: handle exception
		}
		
	}
	
	private void starttime() {
		timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            	deleteTable();
                // Call your windowOpened logic here
            	WindowsOpenedLogic();
            }
        }, 0, 2000);
	}
	
	public void deleteTable() {
		this.viewForm.ClearLabel();
	}
	
	public void WindowsOpenedLogic() {
		/*
		try {
			Socket socket = new Socket("localhost", 12345);
			DataInputStream in = new DataInputStream(socket.getInputStream());
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
			out.writeUTF("GetAllFile");
			
			int rowcount = in.readInt();
			
			for(int i = 0; i < rowcount;i++) {
				
				if (in.available() > 0) { // Kiểm tra xem có dữ liệu để đọc không
	                Object[] request = {in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF()};
	                this.viewForm.addTable(request);
	                this.viewForm.DeleteTable(request);
	            } else {
	                break; // Thoát vòng lặp nếu không có dữ liệu để đọc
	            }
			}
		}catch (Exception e1) {
			// TODO: handle exception
		}
		*/
		
		    try {
		        Socket socket = new Socket("localhost", 12345);
		        DataInputStream in = new DataInputStream(socket.getInputStream());
		        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		        
		        out.writeUTF("GetAllFile");
		        
		        int rowcount = in.readInt();
		        
		        // Lưu trữ dữ liệu cũ trước khi thêm dữ liệu mới vào
		        List<Object[]> oldData = new ArrayList<>();
		        for (int i = 0; i < viewForm.table.getRowCount(); i++) {
		            Object[] rowData = new Object[7];
		            for (int j = 0; j < viewForm.table.getColumnCount(); j++) {
		                rowData[j] = viewForm.table.getValueAt(i, j);
		            }
		            oldData.add(rowData);
		        }
		        
		        for (int i = 0; i < rowcount; i++) {
		            Object[] request = {in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF()};
		            
		            // Kiểm tra xem dữ liệu mới đã tồn tại trong dữ liệu cũ chưa
		            if (!oldData.contains(request)) {
		                // Thêm dữ liệu mới vào bảng
		                viewForm.addTable(request);
		            }
		        }
		    } catch (Exception e1) {
		        // Xử lý ngoại lệ
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
