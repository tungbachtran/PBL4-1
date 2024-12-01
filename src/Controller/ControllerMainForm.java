package Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Class.MyFile;
import VIEW.FormSeeInfor;
import VIEW.LoginForm;
import VIEW.MainForm;

public class ControllerMainForm implements ActionListener{
	MainForm mainForm;
	ArrayList<MyFile> myFiles = new ArrayList<MyFile>();
	String[] tempStrings = new String[5];

	public ControllerMainForm(MainForm mainForm) {
		
		this.mainForm = mainForm;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		try {
			Socket socket = new Socket("192.168.1.7", 12345);
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			DataInputStream in = new DataInputStream(socket.getInputStream());
			/*
			if(command.equals("Lớp 12")) {
				out.writeUTF("FileClass");
				out.writeUTF("Lớp 12");
			}
			*/
			if(command.equals("Show")) {
				this.mainForm.ClearLabel();
				out.writeUTF("FileClass");
				out.writeUTF(this.mainForm.comboBox.getSelectedItem().toString());
				out.writeUTF(this.mainForm.CBMonhoc.getSelectedItem().toString());
			
				
				int rowcount = in.readInt();
			
				this.mainForm.ClearLabel();
			for (int i = 0; i < rowcount; i++) {        
				Object[] request = {in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF()};
				this.mainForm.addTable(request);		        
		    }
		}else if(command.equals("Search")){
			if(this.mainForm.textField.getText().trim().isEmpty()) {
				JOptionPane.showMessageDialog(mainForm, "Bạn chưa nhập file cần tìm kiếm");
			}else {
				out.writeUTF("Search");
				out.writeUTF(this.mainForm.textField.getText());
				
				int rowcount = in.readInt();
				this.mainForm.ClearLabel();
				for(int i = 0; i < rowcount;i++) {
					//String nameDocument = in.readUTF();
					//this.mainForm.addNewLabel(nameDocument);
					Object[] request = {in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF()};
					this.mainForm.addTable(request);
				}
			}
		}else if(command.equals("Log out")) {
			LoginForm loginForm = new LoginForm();
			loginForm.setDefaultCloseOperation(3);
			loginForm.setVisible(true);
			this.mainForm.dispose();
			
		}else if(command.equals("MyInfor")) {
			FormSeeInfor formSeeInfor = new FormSeeInfor();
			formSeeInfor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			formSeeInfor.setVisible(true);
			formSeeInfor.txtAccount.setText(this.mainForm.lblAccount.getText());
			out.writeUTF("ShowInfor");
			out.writeUTF(this.mainForm.lblAccount.getText());
			
			formSeeInfor.txtEmail.setText(in.readUTF());
			formSeeInfor.txtPhone.setText(in.readUTF());
			formSeeInfor.txtUserName.setText(in.readUTF());
			}
			
		else {
			out.writeUTF("MyFile");
			out.writeUTF(this.mainForm.lblAccount.getText());
			
			int rowcount = in.readInt();
			this.mainForm.ClearLabel();
			for(int i = 0; i < rowcount;i++) {
				//String nameDocument = in.readUTF();
				//this.mainForm.addNewLabel(nameDocument);
				Object[] request = {in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF(),in.readUTF()};
				this.mainForm.addTable(request);
			}
			
		}
		

			
		}catch (Exception e2) {
			e2.printStackTrace();
			System.out.println("error: " + e2);
		}
		
			
		}
	
	

}
