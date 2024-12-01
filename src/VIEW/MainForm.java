package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import Controller.ControllerDownload;
import Controller.ControllerTable;
import Controller.ControllerMainForm;
import Controller.ControllerMainFormLoad;
import Controller.ControllerTable;
import javax.swing.ImageIcon;

public class MainForm extends JFrame {

	public JPanel contentPane;
	public JLabel lbLink;
	public JButton btnNewButton;
	public JLabel lbLink2;
	public JPanel panel_1;
	private ArrayList<JLabel> labels = new ArrayList<>();
	public JLabel lblNewLabel;
	public JComboBox comboBox;
	public JComboBox CBMonhoc;
	public JScrollPane scrollPane;
	public JPanel panelInScrollJPanel;
	public JTextField textField;
	public JLabel lblUsername;
	public JLabel lblAccount;
	public DefaultTableModel model;
	public JTable table;
	public JButton BTN_Response;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm frame = new MainForm();
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
	// tạo ra Jlabel chứa tên file để tải
	
	
	

	
	
    
	public void addTable(Object[] rowdata) {
		//keepLabel(panel_1,lblNewLabel);
		
		model.addRow(rowdata);
        //ListSelectionListener listSelectionListener = new ControllerTable(this);
        //table.getColumnModel().getColumn(0).setCellRenderer(new CustomTableCellRenderer()); 
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        MouseListener[] mouseListeners = table.getMouseListeners();
        for (MouseListener listener : mouseListeners) {
            table.removeMouseListener(listener);
        }
        MouseListener mouseListener = new ControllerTable(this);
        table.addMouseListener(mouseListener);
	}
	
      
         
    
	
	 public static void keepLabel(JPanel panel, JLabel labelToKeep) {
	        Component[] components = panel.getComponents();

	        for (Component component : components) {
	            // Kiểm tra nếu thành phần không phải là JLabel hoặc không phải là label bạn muốn giữ lại
	            if (!(component instanceof JLabel) || component != labelToKeep) {
	                // Loại bỏ thành phần khác
	                panel.remove(component);
	            }
	        }

	        
	    }
	 
	 public void ClearLabel() {
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
		 model.setRowCount(0);
	 }
	 
	/*
	public MainForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 835, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(128, 255, 255));
		contentPane.add(panel, BorderLayout.NORTH);
		
		ActionListener actionListener = new ControllerMainForm(this);
		
		JButton btnNewButton_3 = new JButton("Refresh");
		btnNewButton_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		//btnNewButton_3.addActionListener(actionListener);
		panel.add(btnNewButton_3);
		
	    btnNewButton = new JButton("Lớp 12");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(actionListener);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Lớp 11");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_1.addActionListener(actionListener);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Lớp 10");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.setName("btl10");
		btnNewButton_2.addActionListener(actionListener);
		panel.add(btnNewButton_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Lớp 9 ", "Lớp 8", "Lớp 7", "Lớp 6", "Lớp 5", "Lớp 4", "Lớp 3", "Lớp 2", "Lớp 1"}));
		comboBox.setName("cbclass");
		comboBox.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		comboBox.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		comboBox.setToolTipText("");
		panel.add(comboBox);
		
		JButton btnUpfile = new JButton("Upfile");
		btnUpfile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpfile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FormPostFile formPostFile = new FormPostFile();
				formPostFile.setDefaultCloseOperation(3);
				formPostFile.setVisible(true);				
				
			}
		});
		panel.add(btnUpfile);
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		lblNewLabel = new JLabel("Các files được tải lên gần đây:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_1.add(lblNewLabel);
		
		
	    lbLink = new JLabel("lblink");
		lbLink.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lbLink);
		
		lbLink2 = new JLabel("lblink2");
		lbLink2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_1.add(lbLink2);
		
		
		
	}
	
	*/
	 
	 
	 public MainForm() {
		 	WindowListener windowListener = new ControllerMainFormLoad(this);
		 	this.addWindowListener(windowListener);
		 	ActionListener actionListener = new ControllerMainForm(this);
		 	panel_1 = new JPanel();
			setTitle("Trang chủ - Ứng dung quản lý tài liệu");
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setBounds(100, 100, 1250, 800);
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

			setContentPane(contentPane);
			contentPane.setLayout(new BorderLayout(0, 0));
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 255));
			contentPane.add(panel, BorderLayout.NORTH);
			
			textField = new JTextField();
			textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
			panel.add(textField);
			textField.setColumns(25);
			
			JButton BtTimkiem = new JButton("Search");
			panel.add(BtTimkiem);
			BtTimkiem.setMaximumSize(new Dimension(120, 30));
			BtTimkiem.setFont(new Font("Tahoma", Font.PLAIN, 16));
			BtTimkiem.addActionListener(actionListener);
			
			Component horizontalSpace_1 = Box.createRigidArea(new Dimension(50, 0));
			panel.add(horizontalSpace_1);
			
			lblUsername = new JLabel("New label");
			lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
			panel.add(lblUsername);
			
			JButton BTN_MyFilePost = new JButton("My File");
			BTN_MyFilePost.setFont(new Font("Tahoma", Font.PLAIN, 16));
			BTN_MyFilePost.addActionListener(actionListener);
			
			Component horizontalSpace_1_1 = Box.createRigidArea(new Dimension(50, 0));
			panel.add(horizontalSpace_1_1);
			panel.add(BTN_MyFilePost);
			
			JButton BTN_MyFilePost_1 = new JButton("MyInfor");
			BTN_MyFilePost_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
			panel.add(BTN_MyFilePost_1);
			ActionListener []actionListeners = BTN_MyFilePost_1.getActionListeners();
			for (ActionListener listener : actionListeners) {
				BTN_MyFilePost_1.removeActionListener(listener);
	        }
			BTN_MyFilePost_1.addActionListener(actionListener);
			
			lblAccount = new JLabel("New label");
			lblAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));
			panel.add(lblAccount);
			
			JButton btnNewButton_1 = new JButton("Log out");
			btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 16));
			panel.add(btnNewButton_1);
			btnNewButton_1.addActionListener(actionListener);
			lblAccount.setVisible(false);
			
			panel_1 = new JPanel();
			panel_1.setBackground(new Color(255, 255, 255));
			contentPane.add(panel_1, BorderLayout.CENTER);
			panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
			
			model = new DefaultTableModel() {
				public boolean isCellEditable(int row, int column) {
	                return false; // Set cells non-editable
	            }
			};
	        table = new JTable(model);
	        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	        model.addColumn("NameDocument");
	        model.addColumn("Class");
	        model.addColumn("NamePoster");
	        model.addColumn("Object");
	        model.addColumn("NameFile");
	        table.getColumnModel().getColumn(0).setPreferredWidth(300);
	        table.getColumnModel().getColumn(1).setPreferredWidth(150);
	        table.getColumnModel().getColumn(2).setPreferredWidth(200);
	        table.getColumnModel().getColumn(3).setPreferredWidth(200);
	        table.getColumnModel().getColumn(4).setPreferredWidth(300);
	        
			
			
			
			scrollPane = new JScrollPane(table);
			scrollPane.setBackground(new Color(255, 255, 255));
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			panel_1.add(scrollPane);
			
			
			
			JPanel panel_2 = new JPanel();
			panel_2.setBackground(new Color(255, 255, 255));
			contentPane.add(panel_2, BorderLayout.WEST);
			panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
			
			JPanel panel_3 = new JPanel();
			panel_3.setBackground(new Color(255, 255, 255));
			panel_2.add(panel_3);
			panel_3.setLayout(null);
			
			JLabel lblNewLabel_3 = new JLabel("");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setIcon(new ImageIcon(MainForm.class.getResource("/Icon/avatar.png")));
			lblNewLabel_3.setBounds(10, 11, 175, 170);
			panel_3.add(lblNewLabel_3);
			
			Component horizontalSpace_1_1_1 = Box.createRigidArea(new Dimension(50, 0));
			horizontalSpace_1_1_1.setBounds(79, 88, 50, 12);
			panel_3.add(horizontalSpace_1_1_1);
			
			JLabel lblNewLabel_6 = new JLabel("       Files Manager ");
			lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
			panel_2.add(lblNewLabel_6);
			panel_2.add(Box.createVerticalStrut(10));
			
			Component verticalStrut = Box.createVerticalStrut(10);
			panel_2.add(verticalStrut);
			
			JLabel lblNewLabel_1 = new JLabel("        Bộ lọc tìm kiếm:");
			lblNewLabel_1.setAlignmentY(Component.TOP_ALIGNMENT);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
			panel_2.add(lblNewLabel_1);
			panel_2.add(Box.createVerticalStrut(10));
			
			JLabel lblNewLabel_5 = new JLabel(" Lớp:");
			lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 16));
			panel_2.add(lblNewLabel_5);
			
			Component verticalStrut_2 = Box.createVerticalStrut(10);
			panel_2.add(verticalStrut_2);
			
			comboBox = new JComboBox();
			comboBox.setPreferredSize(new Dimension(200, 30));
			comboBox.setMaximumSize(new Dimension(200, 30));
			comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"Lớp 12", "Lớp 11", "Lớp 10", "Lớp 9", "Lớp 8", "Lớp 7", "Lớp 6", "Lớp 5", "Lớp 4", "Lớp 3", "Lớp 2", "Lớp 1"}));
			comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
			panel_2.add(comboBox);
			
			Component verticalStrut_3 = Box.createVerticalStrut(10);
			panel_2.add(verticalStrut_3);
			
			JLabel lblNewLabel_2 = new JLabel(" Môn học:");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 16));
			panel_2.add(lblNewLabel_2);
			panel_2.add(Box.createVerticalStrut(10));
			
			CBMonhoc = new JComboBox();
			CBMonhoc.setPreferredSize(new Dimension(200, 30));
			CBMonhoc.setAlignmentX(Component.LEFT_ALIGNMENT);
			CBMonhoc.setMaximumSize(new Dimension(200, 30));
			CBMonhoc.setFont(new Font("Tahoma", Font.PLAIN, 16));
			CBMonhoc.setModel(new DefaultComboBoxModel(new String[] {"Toán", "Vật Lý", "Hóa Học", "Sinh Học", "Ngữ Văn", "Lịch Sử", "Địa Lý", "Giáo Dục Kinh Tế & Pháp Luật", "Công Nghệ", "Tin Học"}));
			panel_2.add(CBMonhoc);
			panel_2.add(Box.createVerticalStrut(10));
			
			Component verticalStrut_6 = Box.createVerticalStrut(10);
			panel_2.add(verticalStrut_6);
			panel_2.add(Box.createVerticalStrut(10));
			
			
			
			JButton BtXemTL = new JButton("Show");
			BtXemTL.setPreferredSize(new Dimension(200, 30));
			BtXemTL.setMaximumSize(new Dimension(200, 30));
			BtXemTL.setFont(new Font("Tahoma", Font.PLAIN, 16));
			BtXemTL.addActionListener(actionListener);
			panel_2.add(BtXemTL);
			
			Component verticalStrut_1 = Box.createVerticalStrut(10);
			panel_2.add(verticalStrut_1);
			
			JButton BtThemTL = new JButton("Add File");
			BtThemTL.setPreferredSize(new Dimension(200, 30));
			BtThemTL.setMaximumSize(new Dimension(200, 30));
			BtThemTL.setFont(new Font("Tahoma", Font.PLAIN, 16));
			BtThemTL.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					FormPostFile formPostFile = new FormPostFile();
					formPostFile.setDefaultCloseOperation(3);
					formPostFile.setVisible(true);	
					formPostFile.JTFName.setText(lblUsername.getText());
					formPostFile.lblAccount.setText(lblAccount.getText());
					formPostFile.JTFName.setEnabled(false);
					
				}
			});
			panel_2.add(BtThemTL);
			
			BTN_Response = new JButton("Admin's Report");
			BTN_Response.setPreferredSize(new Dimension(200, 30));
			BTN_Response.setMaximumSize(new Dimension(200, 30));
			BTN_Response.setFont(new Font("Tahoma", Font.PLAIN, 16));
			BTN_Response.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					FormViewRespone formViewRespone = new FormViewRespone();
					formViewRespone.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					formViewRespone.setVisible(true);
					formViewRespone.lbAccount.setText(lblAccount.getText());
				}
			});
			Component verticalStrut_5 = Box.createVerticalStrut(10);
			panel_2.add(verticalStrut_5);
			panel_2.add(BTN_Response);
			Component verticalStrut_4 = Box.createVerticalStrut(190);
			panel_2.add(verticalStrut_4);
			
			
	        

		}
	 
	
	 static class CustomTableCellRenderer extends DefaultTableCellRenderer {
	        private JLabel label;
	        
	        
	        public CustomTableCellRenderer() {
	        	label = new JLabel();
	            
	            
	        }
	        public void setMouseListener(MouseListener listener) {
	            label.addMouseListener(listener);
	        }
	      

	        @Override
	        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	            if (column == 0) { // Chỉ áp dụng cho cột NameDocument
	            	
	                label.setText(value != null ? value.toString() : "");
	                label.setFont(new Font("Tahoma", Font.PLAIN, 16));
	                label.setForeground(Color.BLUE);  
	                label.setHorizontalAlignment(SwingConstants.CENTER);
	                return label;
	            } else {
	                Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	                if (cellComponent instanceof JLabel) {
	                    JLabel label = (JLabel) cellComponent;
	                    label.setHorizontalAlignment(SwingConstants.CENTER);
	                }
	                
	                return cellComponent;
	            }
	        }
	    }
}

