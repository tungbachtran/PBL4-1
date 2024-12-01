package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import VIEW.RegisterForm;

public class ControllerLoginLabel implements MouseListener{
	

	@Override
	public void mouseClicked(MouseEvent e) {
		JFrame frame = new RegisterForm();
		frame.setDefaultCloseOperation(3);
		frame.setVisible(true);
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
