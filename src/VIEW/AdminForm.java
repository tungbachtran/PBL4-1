package VIEW;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerAdminForm;
import Controller.ControllerAdminFormLoad;
import Controller.ControllerDownload;
import Controller.ControllerTable;
import VIEW.MainForm.CustomTableCellRenderer;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JComboBox;
import javax.swing.JList;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class AdminForm extends JFrame {

	private JPanel contentPane;
	public JScrollPane scrollPane;
	public JPanel panelInScrollJPanel;
	private ArrayList<JLabel> labels = new ArrayList<>();
	public JComboBox comboBox;
	public JComboBox CBMonhoc;
	public JTextField textField;
	public JLabel lblUserName;
	public JLabel lblAccount;
	public DefaultTableModel model;
	public JTable table;
	public JButton BTN_Request;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminForm frame = new AdminForm();
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
	
	
	public void addTable(Object[] rowdata) {
		//keepLabel(panel_1,lblNewLabel);
		
		model.addRow(rowdata);
        //ListSelectionListener listSelectionListener = new ControllerTable(this);
		table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        MouseListener[] mouseListeners = table.getMouseListeners();
        for (MouseListener listener : mouseListeners) {
            table.removeMouseListener(listener);
        }
        MouseListener mouseListener = new ControllerTable(this);
        table.addMouseListener(mouseListener);
	}
	
	public void ClearLabel() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
	 }
	public AdminForm() {
		WindowListener windowListener = new ControllerAdminFormLoad(this);
		this.addWindowListener(windowListener);
		ActionListener actionListener = new ControllerAdminForm(this);
		setTitle("ADMIN - Ứng dụng lưu trữ tài liệu học tập");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1260, 901);
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
		
		JButton btnNewButton = new JButton("Log out");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(actionListener);
		
		JButton BTN_MyFile = new JButton("My Files");
		BTN_MyFile.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BTN_MyFile.addActionListener(actionListener);
		
		Component horizontalSpace_1 = Box.createRigidArea(new Dimension(100, 0));
		panel.add(horizontalSpace_1);
		panel.add(BTN_MyFile);
		
		JButton BTN_MyFile_1 = new JButton("My Infor");
		BTN_MyFile_1.setMinimumSize(new Dimension(90, 21));
		BTN_MyFile_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ActionListener []actionListeners = BTN_MyFile_1.getActionListeners();
		for (ActionListener listener : actionListeners) {
			BTN_MyFile_1.removeActionListener(listener);
        }
		BTN_MyFile_1.addActionListener(actionListener);
		
		panel.add(BTN_MyFile_1);
		panel.add(btnNewButton);
		
		
		lblAccount = new JLabel("New label");
		lblAccount.setVisible(false);
		panel.add(lblAccount);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		panelInScrollJPanel = new JPanel();
		panelInScrollJPanel.setBackground(new Color(255, 255, 255));
		panelInScrollJPanel.setLayout(new BoxLayout(panelInScrollJPanel, BoxLayout.Y_AXIS));
		
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
        table.getColumnModel().getColumn(4).setPreferredWidth(350);
		scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel_1.add(scrollPane);
		
		lblUserName = new JLabel("New label   ");
		panel_1.add(lblUserName);
		lblUserName.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblUserName.setVisible(true);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		panel_1.add(scrollPane);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_2, BorderLayout.WEST);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setMinimumSize(new Dimension(10, 3));
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(28, 11, 140, 140);
		panel_3.add(lblNewLabel_3);
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setIcon(new ImageIcon(AdminForm.class.getResource("/Icon/avatar.png")));
		
		JLabel lblNewLabel_4 = new JLabel("      Files Manager ");
		lblNewLabel_4.setPreferredSize(new Dimension(200, 23));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		panel_2.add(lblNewLabel_4);
		panel_2.add(Box.createVerticalStrut(10));
		
		Component verticalStrut = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut);
		
		JLabel lblNewLabel_1 = new JLabel("        Bộ lọc tìm kiếm:");
		lblNewLabel_1.setAlignmentY(Component.TOP_ALIGNMENT);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.ITALIC, 16));
		panel_2.add(lblNewLabel_1);
		panel_2.add(Box.createVerticalStrut(10));
		
		JLabel lblNewLabel_5 = new JLabel("  Lớp:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 16));
		panel_2.add(lblNewLabel_5);
		
		Component verticalStrut_2 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_2);
		
		comboBox = new JComboBox();
		comboBox.setPreferredSize(new Dimension(61, 23));
		comboBox.setMaximumSize(new Dimension(200, 30));
		comboBox.setAlignmentX(Component.LEFT_ALIGNMENT);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Lớp 12", "Lớp 11", "Lớp 10", "Lớp 9", "Lớp 8", "Lớp 7", "Lớp 6", "Lớp 5", "Lớp 4", "Lớp 3", "Lớp 2", "Lớp 1"}));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(comboBox);
		
		Component verticalStrut_3 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_3);
		
		JLabel lblNewLabel_2 = new JLabel("  Môn học:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 16));
		panel_2.add(lblNewLabel_2);
		panel_2.add(Box.createVerticalStrut(10));
		
		CBMonhoc = new JComboBox();
		CBMonhoc.setPreferredSize(new Dimension(61, 23));
		CBMonhoc.setAlignmentX(Component.LEFT_ALIGNMENT);
		CBMonhoc.setMaximumSize(new Dimension(200, 30));
		CBMonhoc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		CBMonhoc.setModel(new DefaultComboBoxModel(new String[] {"Toán", "Vật Lý", "Hóa Học", "Sinh Học", "Ngữ Văn", "Lịch Sử", "Địa Lý", "Giáo Dục Kinh Tế & Pháp Luật", "Công Nghệ", "Tin Học"}));
		panel_2.add(CBMonhoc);
		panel_2.add(Box.createVerticalStrut(10));
		panel_2.add(Box.createVerticalStrut(10));
		panel_2.add(Box.createVerticalStrut(10));
		
		
		
		JButton BtXemTL = new JButton("Show");
		BtXemTL.setMaximumSize(new Dimension(200, 30));
		BtXemTL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BtXemTL.addActionListener(actionListener);
		panel_2.add(BtXemTL);
		Component verticalStrut_8 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_8);
		
		JButton BtThemTL = new JButton("Add File");
		BtThemTL.setMaximumSize(new Dimension(200, 30));
		BtThemTL.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BtThemTL.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				FormPostFile formPostFile = new FormPostFile();
				formPostFile.setDefaultCloseOperation(3);
				formPostFile.setVisible(true);	
				formPostFile.JTFName.setText(lblUserName.getText());
				formPostFile.JTFName.setEditable(false);
				
			}
		});
		panel_2.add(BtThemTL);
		
		Component verticalStrut_1 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_1);
		
		JLabel lblNewLabel_6 = new JLabel("  ADMIN:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 16));
		panel_2.add(lblNewLabel_6);
		
		Component verticalStrut_4 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_4);
		
		JButton btnNewButton_1 = new JButton("User's Account");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QLTaikhoanForm qltaikhoanform = new QLTaikhoanForm();
				qltaikhoanform.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				qltaikhoanform.setVisible(true);
			}
		});
		btnNewButton_1.setMaximumSize(new Dimension(200, 30));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_2.add(btnNewButton_1);
		
		Component verticalStrut_5 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_5);
		
		JButton btnNewButton_2 = new JButton("User's Files");
		btnNewButton_2.setMaximumSize(new Dimension(200, 30));
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				QLFilesForm qlFilesForm = new QLFilesForm();
				qlFilesForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				qlFilesForm.setVisible(true);
			}
		});
		panel_2.add(btnNewButton_2);
		
		Component verticalStrut_6 = Box.createVerticalStrut(10);
		panel_2.add(verticalStrut_6);
		
		BTN_Request = new JButton("User's Report");
		BTN_Request.setMaximumSize(new Dimension(200, 30));
		BTN_Request.setFont(new Font("Tahoma", Font.PLAIN, 16));
		BTN_Request.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AdminReportForm adminReportForm = new AdminReportForm();
				adminReportForm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				adminReportForm.setVisible(true);
				
			}
		});
		panel_2.add(BTN_Request);
		
		Component verticalStrut_7_4 = Box.createVerticalStrut(180);
		panel_2.add(verticalStrut_7_4);

		

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

