package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import VIEW.AdminReportForm;

public class ControllerReportContent implements MouseListener{
	AdminReportForm adminReportForm;
	
	public ControllerReportContent(AdminReportForm adminReportForm) {
		super();
		this.adminReportForm = adminReportForm;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
			int column = this.adminReportForm.table.getColumnModel().getColumnIndexAtX(e.getX());
            int row = e.getY() / this.adminReportForm.table.getRowHeight();
            if (column == 4 && row < this.adminReportForm.table.getRowCount()) {
            	Object value = this.adminReportForm.table.getValueAt(row, column);
            	JOptionPane.showMessageDialog(adminReportForm, "Nội dung của góp ý là: " + value);
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
