package VIEW;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;

import Controller.ControllerComment;
import Controller.ControllerQLAccount;
import Controller.ControllerShowComment;
import Controller.ControllerTable;
import VIEW.MainForm.CustomTableCellRenderer;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class QLTaikhoanForm extends JFrame {

	public JPanel contentPane;
	public JTable table;
	public JButton AddADBt;
	public JButton ExitBt;
	public JButton ShowBt;
	public DefaultTableModel model;
	public JTextField UserNameText;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLTaikhoanForm frame = new QLTaikhoanForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void addTable(Object[] rowdata) {
		//keepLabel(panel_1,lblNewLabel);
		
		model.addRow(rowdata);
        //ListSelectionListener listSelectionListener = new ControllerTable(this);
        //table.getColumnModel().getColumn(0).setCellRenderer(new CustomTableCellRenderer()); 
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        //MouseListener[] mouseListeners = table.getMouseListeners();
        //for (MouseListener listener : mouseListeners) {
           // table.removeMouseListener(listener);
        //}
        //MouseListener mouseListener = new ControllerTable(this);
        //table.addMouseListener(mouseListener);
	}
	
	 public void ClearLabel() {
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
		 model.setRowCount(0);
	 }

	/**
	 * Create the frame.
	 */
	public QLTaikhoanForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1238, 831);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		ActionListener actionlistener = new ControllerQLAccount(this);
        
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_3, BorderLayout.WEST);
		
		JButton ExitBt_1 = new JButton("Cancel");
		ExitBt_1.setPreferredSize(new Dimension(90, 30));
		panel_3.add(ExitBt_1);
		ExitBt_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ExitBt_1.addActionListener(actionlistener);
		
		JButton ShowBt_1 = new JButton("Show");
		panel_3.add(ShowBt_1);
		ShowBt_1.setPreferredSize(new Dimension(80, 30));
		ShowBt_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		ShowBt_1.addActionListener(actionlistener);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_4, BorderLayout.EAST);
		
		JButton AddADBt_1 = new JButton("Add ADMIN");
		AddADBt_1.setBackground(new Color(255, 255, 255));
		panel_4.add(AddADBt_1);
		AddADBt_1.setPreferredSize(new Dimension(150, 30));
		AddADBt_1.addActionListener(actionlistener);
		AddADBt_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 255, 255));
		panel_1.add(panel_5, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("Nhập Account cần xóa:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(lblNewLabel);
		
		UserNameText = new JTextField();
		UserNameText.setPreferredSize(new Dimension(150, 30));
		UserNameText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_5.add(UserNameText);
		UserNameText.setColumns(10);
		
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setPreferredSize(new Dimension(150, 30));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(actionlistener);
		panel_5.add(btnNewButton);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new BorderLayout(0, 0));
	
		
		model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
                return false; // Set cells non-editable
            }
		};
		
		table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model.addColumn("Account");
        model.addColumn("UserName");
        model.addColumn("Type");
        table.getColumnModel().getColumn(0).setPreferredWidth(400);
        table.getColumnModel().getColumn(1).setPreferredWidth(400);
        table.getColumnModel().getColumn(2).setPreferredWidth(400);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		panel_2.add(scrollPane, BorderLayout.CENTER);
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
