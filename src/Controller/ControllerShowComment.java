package Controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import VIEW.ViewComment;

public class ControllerShowComment implements WindowListener{
	ViewComment viewComment;
	Timer timer;
	
	public ControllerShowComment(ViewComment viewComment) {
		super();
		this.viewComment = viewComment;
		this.timer = new Timer();
        startTimer();
	}

	private void startTimer() {
		// TODO Auto-generated method stub
		timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // Call your windowOpened logic here
                windowOpenedLogic();
            }
        }, 0, 4000);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		try {
			Socket socket = new Socket("192.168.1.7", 12345);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF("ShowComment");
			out.writeUTF(this.viewComment.lbIDFile.getText());
			
			int length = in.readInt();
			this.viewComment.ClearLabel();
			for(int i = 0; i < length; i++) {
				String Account = in.readUTF();
				if((Account.equals(this.viewComment.lbAccount.getText()))){
					this.viewComment.addNewLabelWithColor(in.readUTF());
				}
				else this.viewComment.addNewLabel(in.readUTF());
			}
			
		} catch (Exception e2) {
			// TODO: handle exception
			e2.printStackTrace();
		}
		
	}
	
	private void windowOpenedLogic() {
        try {
            Socket socket = new Socket("192.168.1.7", 12345);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            out.writeUTF("ShowComment");
            out.writeUTF(this.viewComment.lbIDFile.getText());

            int length = in.readInt();
            this.viewComment.ClearLabel();
            for (int i = 0; i < length; i++) {
                String Account = in.readUTF();
                if ((Account.equals(this.viewComment.lbAccount.getText()))) {
                    this.viewComment.addNewLabelWithColor(in.readUTF());
                } else this.viewComment.addNewLabel(in.readUTF());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
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
