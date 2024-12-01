package Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPasswordField;

public class ControllerTogglePasswordHandler implements MouseListener {

    private final JPasswordField passwordField;

    public ControllerTogglePasswordHandler(JPasswordField passwordField) {
        this.passwordField = passwordField;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        togglePasswordVisibility();
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

    private void togglePasswordVisibility() {
        char echoChar = (passwordField.getEchoChar() == 0) ? '\u2022' : (char) 0;
        passwordField.setEchoChar(echoChar);
    }
}
