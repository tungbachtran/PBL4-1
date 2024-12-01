package VIEW;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.ControllerComment;
import Controller.ControllerDeleteComment;
import Controller.ControllerLoadRespone;
import Controller.ControllerShowComment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class FormViewRespone extends JFrame {

    public JPanel contentPane;
    public JPanel panelInScrollJPanel;
    public ScrollPane scrollPane;
    private ArrayList<JLabel> labels = new ArrayList<>();
    public JLabel lbAccount;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	
                FormViewRespone frame = new FormViewRespone();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

   
    public FormViewRespone() {
        setTitle("Comment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 928, 645);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        createSouthPanel();
        createCenterPanel();
        WindowListener windowListener = new ControllerLoadRespone(this);
        this.addWindowListener(windowListener);
    }

    public void createSouthPanel() {
    	//ActionListener actionListener = new ControllerComment(this);
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panel_2 = new JPanel();
        panel.add(panel_2, BorderLayout.NORTH);
        panel_2.setLayout(new BorderLayout(0, 0));

        JPanel panel_1 = new JPanel();
        panel.add(panel_1, BorderLayout.SOUTH);
        
        
        
        lbAccount = new JLabel("New label");
        lbAccount.setVisible(false);
        panel_1.add(lbAccount);
        
        JButton btnHy = new JButton("Cancel");
        btnHy.setPreferredSize(new Dimension(130, 35));
        btnHy.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnHy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
        panel_1.add(btnHy);
        
        JButton BTN_Contact = new JButton("Contact to Ad");
        BTN_Contact.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ReportUser reportUser = new ReportUser();
        		reportUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        		reportUser.setVisible(true);
        		reportUser.lbAccout.setText(lbAccount.getText());
        		reportUser.lbIDFile.setText("No");
        	}
        });
        BTN_Contact.setPreferredSize(new Dimension(160, 35));
        BTN_Contact.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_1.add(BTN_Contact);

        JPanel panel_3 = new JPanel();
        panel.add(panel_3, BorderLayout.WEST);
        panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
    }

    private void createCenterPanel() {
    	panelInScrollJPanel = new JPanel();
		panelInScrollJPanel.setBackground(new Color(255, 255, 255));
		panelInScrollJPanel.setLayout(new BoxLayout(panelInScrollJPanel, BoxLayout.Y_AXIS));
		
        JScrollPane scrollPane = new JScrollPane(panelInScrollJPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPane, BorderLayout.CENTER);
    }
    
    public void addNewLabel(String labelText) {
		//keepLabel(panel_1,lblNewLabel);
		
        JLabel newLabel = new JLabel(labelText);
        newLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        newLabel.setBackground(Color.BLACK);      
        labels.add(newLabel); 
        panelInScrollJPanel.add(newLabel); // Thêm JLabel vào JPanel trong JScrollPane
        panelInScrollJPanel.revalidate(); // Cập nhật giao diện của JPanel trong JScrollPane
        panelInScrollJPanel.repaint(); // Vẽ lại JPanel trong JScrollPane nếu cần thiết
    }
    /*
    public void addNewLabelWithColor(String labelText) {
		//keepLabel(panel_1,lblNewLabel);
		
        JLabel newLabel = new JLabel(labelText);
        newLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        newLabel.setForeground(Color.BLUE);      
        labels.add(newLabel);
        //MouseListener mouseListener = new ControllerDeleteComment(this);
        //newLabel.addMouseListener(mouseListener);
        panelInScrollJPanel.add(newLabel); // Thêm JLabel vào JPanel trong JScrollPane
        panelInScrollJPanel.revalidate(); // Cập nhật giao diện của JPanel trong JScrollPane
        panelInScrollJPanel.repaint(); // Vẽ lại JPanel trong JScrollPane nếu cần thiết
    }
    */
    public void ClearLabel() {
    	panelInScrollJPanel.removeAll();
    }
}
