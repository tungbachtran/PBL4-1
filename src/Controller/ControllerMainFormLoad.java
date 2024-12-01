package Controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

import VIEW.MainForm;

public class ControllerMainFormLoad implements WindowListener{
	MainForm mainForm;
	Timer timer;
	public ControllerMainFormLoad(MainForm mainForm) {
		super();
		this.mainForm = mainForm;
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
        }, 2000, 5000);
	}
	
	private void windowOpenedLogic() {
        try {
            Socket socket = new Socket("192.168.1.7", 12345);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            out.writeUTF("LoadBtUser");
            out.writeUTF(this.mainForm.lblAccount.getText());

            int length = in.readInt();
            
            this.mainForm.BTN_Response.setText("Phản hồi từ admin(" + length + ")");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
