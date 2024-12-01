package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import VIEW.AdminForm;
import VIEW.MainForm;
import VIEW.ViewComment;

public class ControllerTable implements MouseListener{

	MainForm mainForm;
	AdminForm adminForm;

	public ControllerTable(MainForm mainForm) {
		
		this.mainForm = mainForm;
		this.adminForm = null;
	}
	

	public ControllerTable(AdminForm adminForm) {
		
		this.adminForm = adminForm;
		this.mainForm = null;
	}




	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket("192.168.1.7",12345);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			if(this.adminForm == null) {
				int column = this.mainForm.table.getColumnModel().getColumnIndexAtX(e.getX());
	            int row = e.getY() / this.mainForm.table.getRowHeight();
	            if (column == 0 && row < this.mainForm.table.getRowCount()) {
	            	Object value = this.mainForm.table.getValueAt(row, column);
		            out.writeUTF("GetIDFile");
		            out.writeUTF(value.toString());
		            String Id = in.readUTF();
	   				ViewComment viewComment = new ViewComment();
	   				viewComment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   				viewComment.setVisible(true);
	   				viewComment.lblhiddenAcc.setText(this.mainForm.lblUsername.getText());
    				viewComment.hiddenlabel.setText(value.toString());
	    				//viewComment.lblhiddenAcc.setText(this.mainForm.);
	    			viewComment.lbIDFile.setText(Id);
	    			viewComment.lbAccount.setText(this.mainForm.lblAccount.getText());
	            }
	            
			}else {
				int column = this.adminForm.table.getColumnModel().getColumnIndexAtX(e.getX());
	            int row = e.getY() / this.adminForm.table.getRowHeight();
	            if (column == 0 && row < this.adminForm.table.getRowCount()) {
	            	Object value = this.adminForm.table.getValueAt(row, column);
		            out.writeUTF("GetIDFile");
		            out.writeUTF(value.toString());
		            String Id = in.readUTF();
	   				ViewComment viewComment = new ViewComment();
	   				viewComment.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   				viewComment.setVisible(true);
	   				viewComment.lblhiddenAcc.setText(this.adminForm.lblUserName.getText());
    				viewComment.hiddenlabel.setText(value.toString());
	    				//viewComment.lblhiddenAcc.setText(this.mainForm.);
	    			viewComment.lbIDFile.setText(Id);
	    			viewComment.lbAccount.setText(this.adminForm.lblAccount.getText());
	    			viewComment.BTN_Report.setVisible(false);
	            }
			}
						/*
			// TODO Auto-generated method stub
		    // Kiểm tra xem có phải lựa chọn của cột không
			int column = this.mainForm.table.getColumnModel().getColumnIndexAtX(e.getX());
            int row = e.getY() / this.mainForm.table.getRowHeight();
            
            // Kiểm tra nếu click vào ô đầu tiên
            if (column == 0 && (row < this.mainForm.table.getRowCount()|| row < this.adminForm.table.getRowCount())) {
            	Object value = this.mainForm.table.getValueAt(row, column);
	            out.writeUTF("GetIDFile");
	            out.writeUTF(value.toString());
	            if(this.adminForm == null) {
	    			String Id = in.readUTF();
	   				ViewComment viewComment = new ViewComment();
	   				viewComment.setDefaultCloseOperation(3);
	   				viewComment.setVisible(true);
	   				viewComment.lblhiddenAcc.setText(this.mainForm.lblUsername.getText());
    				viewComment.hiddenlabel.setText(value.toString());
	    				//viewComment.lblhiddenAcc.setText(this.mainForm.);
	    			viewComment.lbIDFile.setText(Id);
	    			viewComment.lbAccount.setText(this.mainForm.lblAccount.getText());
	    		}else if(this.mainForm == null){
	    			String Id = in.readUTF();
	    			ViewComment viewComment = new ViewComment();
	    			viewComment.setDefaultCloseOperation(3);
	   				viewComment.setVisible(true);
	   				viewComment.lblhiddenAcc.setText(this.adminForm.lblUserName.getText());
	   				viewComment.hiddenlabel.setText(value.toString());
	   				//viewComment.lblhiddenAcc.setText(this.mainForm.);
    				viewComment.lbIDFile.setText(Id);
    				viewComment.lbAccount.setText(this.adminForm.lblAccount.getText());
	    		} else JOptionPane.showMessageDialog(adminForm, "hehe");
	    		
	            
	        }
	        */
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


