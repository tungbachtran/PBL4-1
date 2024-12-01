package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.ControllerReportUser;

import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.TextArea;
import java.awt.Button;
import java.awt.Label;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ReportUser extends JFrame {

	private JPanel contentPane;
	public Label lbIDFile;
	public Label lbAccout;
	public JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportUser frame = new ReportUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ReportUser() {
		ActionListener actionListener = new ControllerReportUser(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 673, 405);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lbIDFile = new Label("New label");
		lbIDFile.setBounds(238, 320, 59, 21);
		lbIDFile.setVisible(false);
		contentPane.add(lbIDFile);
		
		lbAccout = new Label("New label");
		lbAccout.setBounds(365, 320, 59, 21);
		lbAccout.setVisible(false);
		contentPane.add(lbAccout);
		
		JButton BTN_Cancel = new JButton("Cancel");
		BTN_Cancel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		BTN_Cancel.setBounds(89, 305, 113, 36);
		BTN_Cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
				
			}
		});
		contentPane.add(BTN_Cancel);
		
		JButton BTN_OK = new JButton("OK");
		BTN_OK.setFont(new Font("Tahoma", Font.PLAIN, 17));
		ActionListener[] actionListeners = BTN_OK.getActionListeners();
        for( ActionListener listener : actionListeners ) {
        	BTN_OK.removeActionListener(listener);
        }
        BTN_OK.addActionListener(actionListener);
		BTN_OK.setBounds(465, 305, 113, 36);
		contentPane.add(BTN_OK);
		
		JLabel lblNewLabel = new JLabel("Nhập nội dung báo cáo của bạn :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblNewLabel.setBounds(20, 44, 268, 23);
		contentPane.add(lblNewLabel);
		
		textArea = new JTextArea();
		textArea.setBounds(10, 78, 639, 203);
		contentPane.add(textArea);
	}
}
