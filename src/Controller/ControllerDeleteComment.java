package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.mysql.cj.callback.UsernameCallback;

import VIEW.AdminForm;
import VIEW.MainForm;
import VIEW.ViewComment;

public class ControllerDeleteComment implements MouseListener{
	
	ViewComment viewComment;
	
	
	public ControllerDeleteComment(ViewComment viewComment) {
		super();
		this.viewComment = viewComment;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		try {
			int option = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa bình luận này không ?", "Xác nhận", JOptionPane.YES_NO_OPTION);
			if(option == JOptionPane.YES_OPTION) {
				Socket socket = new Socket("192.168.1.7",12345);
				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				
				String comment = ((JLabel)e.getSource()).getText();
				out.writeUTF("DeleteComment");
				out.writeUTF(this.viewComment.lbAccount.getText());
				int firstColonIndex = comment.indexOf(":");
				if (firstColonIndex != -1) {
				    // Cắt chuỗi để lấy Name
				    String Username = comment.substring(0, firstColonIndex).trim();
				    String otherString = comment.substring(firstColonIndex + 2);
				    out.writeUTF(Username);
				    
				    String []split = otherString.split("\\s{3,}");
				    if(split.length > 0) {
				    	String content = split[0];
				    	out.writeUTF(content);
				    	String other = split[1];
				    	int indexOfdate = other.indexOf("c");
				    	if(indexOfdate !=-1) {
				    		String date = other.substring(indexOfdate + 2);
				    		out.writeUTF(date);
				    		out.writeUTF(this.viewComment.lbIDFile.getText());
				    		if(in.readUTF().equals("OK")) {
				    			JOptionPane.showMessageDialog(viewComment, "Xóa bình luận thành công");
				    			int length = in.readInt();
				    			this.viewComment.ClearLabel();
				    			for(int i = 0; i < length; i++) {
				    				String Account = in.readUTF();
				    				if((Account.equals(this.viewComment.lbAccount.getText()))){
				    					this.viewComment.addNewLabelWithColor(in.readUTF());
				    				}
				    				else this.viewComment.addNewLabel(in.readUTF());
				    			}
				    		}
				    	}
				    }
				}		
				
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
