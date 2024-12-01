package VIEW;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Controller.ControllerComment;
import Controller.ControllerDeleteComment;
import Controller.ControllerShowComment;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class ViewComment extends JFrame {

    public JPanel contentPane;
    public JTextField CmField;
    public JLabel lblhiddenAcc;
    public JLabel hiddenlabel;
    public JPanel panelInScrollJPanel;
    public ScrollPane scrollPane;
    public JLabel lbIDFile;
    private ArrayList<JLabel> labels = new ArrayList<>();
    public JLabel lbAccount;
    public JButton BTN_Report;
    

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	
                ViewComment frame = new ViewComment();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

   
    public ViewComment() {
        setTitle("Comment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 928, 645);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        createSouthPanel();
        createCenterPanel();
        WindowListener windowListener = new ControllerShowComment(this);
        this.addWindowListener(windowListener);
    }

    public void createSouthPanel() {
    	ActionListener actionListener = new ControllerComment(this);
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panel_2 = new JPanel();
        panel.add(panel_2, BorderLayout.NORTH);
        panel_2.setLayout(new BorderLayout(0, 0));

        CmField = new JTextField();
        CmField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        CmField.setPreferredSize(new Dimension(400, 100));
        panel_2.add(CmField);
        CmField.setColumns(10);

        JButton CmButton = new JButton("Post");
        CmButton.setPreferredSize(new Dimension(130, 35));
        panel_2.add(CmButton, BorderLayout.EAST);
        CmButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        CmButton.addActionListener(actionListener);

        JLabel lblNewLabel = new JLabel("Vui lòng bình luận dưới 50 từ");
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
        panel_2.add(lblNewLabel, BorderLayout.NORTH);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel.add(panel_1, BorderLayout.SOUTH);
        
        hiddenlabel = new JLabel("New label");
        hiddenlabel.setVisible(false);
        
        
        
        lbAccount = new JLabel("New label");
        lbAccount.setVisible(false);
        panel_1.add(lbAccount);
        
        
        lbIDFile = new JLabel("New label");
        lbIDFile.setVisible(false);
        panel_1.add(lbIDFile);
        
        lblhiddenAcc = new JLabel("New label");
        lblhiddenAcc.setVisible(false);
        panel_1.add(lblhiddenAcc);
        panel_1.add(hiddenlabel);
        
        JButton btnHy = new JButton("Cancel");
        btnHy.setPreferredSize(new Dimension(130, 35));
        btnHy.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnHy.addActionListener(actionListener);
        panel_1.add(btnHy);

        JButton ShowButton = new JButton("Preview");
        panel_1.add(ShowButton);
        ShowButton.setPreferredSize(new Dimension(130, 35));
        ShowButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ShowButton.addActionListener(actionListener);
        
        BTN_Report = new JButton("Report");
        BTN_Report.setPreferredSize(new Dimension(130, 35));
        BTN_Report.setFont(new Font("Tahoma", Font.PLAIN, 16));
        BTN_Report.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ReportUser reportUser = new ReportUser();
				reportUser.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				reportUser.setVisible(true);
				reportUser.lbAccout.setText(lbAccount.getText());
				reportUser.lbIDFile.setText(lbIDFile.getText());
				
			}
		});
        panel_1.add(BTN_Report);

        JButton DownLButton = new JButton("Download");
        panel_1.add(DownLButton);
        DownLButton.setPreferredSize(new Dimension(130, 35));
        DownLButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        DownLButton.addActionListener(actionListener);

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
    public void addNewLabelWithColor(String labelText) {
		//keepLabel(panel_1,lblNewLabel);
		
        JLabel newLabel = new JLabel(labelText);
        newLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        newLabel.setForeground(Color.BLUE);      
        labels.add(newLabel);
        MouseListener mouseListener = new ControllerDeleteComment(this);
        newLabel.addMouseListener(mouseListener);
        panelInScrollJPanel.add(newLabel); // Thêm JLabel vào JPanel trong JScrollPane
        panelInScrollJPanel.revalidate(); // Cập nhật giao diện của JPanel trong JScrollPane
        panelInScrollJPanel.repaint(); // Vẽ lại JPanel trong JScrollPane nếu cần thiết
    }
    public void ClearLabel() {
    	panelInScrollJPanel.removeAll();
    }
}
