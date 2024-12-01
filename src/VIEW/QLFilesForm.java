package VIEW;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Controller.ControllerAdminForm;
import Controller.ControllerLoadQLFilesForm;
import Controller.ControllerQLFilesForm;
import Controller.ControllerTable;
import Controller.ControllerTableAdmin;
import VIEW.MainForm.CustomTableCellRenderer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.util.Arrays;
import java.awt.Dimension;

public class QLFilesForm extends JFrame {
    private JPanel contentPane;
    public JTextField IdText;
    public JTable table;
    private JButton DeleteBt;
    private JButton LockBt;
    private JButton UnLockBt;
    public DefaultTableModel model;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    QLFilesForm frame = new QLFilesForm();
                    
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
        MouseListener[] mouseListeners = table.getMouseListeners();
        for (MouseListener listener : mouseListeners) {
            table.removeMouseListener(listener);
        }
        MouseListener mouseListener = new ControllerTableAdmin(this);
        table.addMouseListener(mouseListener);
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
    
    public void ClearLabel() {
    	 model = (DefaultTableModel) table.getModel();
		 model.setRowCount(0);
	 }
    
    public void DeleteTable(Object[] Data) {
    	 model = (DefaultTableModel) table.getModel();

    	// Dữ liệu mới cần thêm vào JTable
    	  /* Dữ liệu mới */;

    	// Kiểm tra xem dữ liệu mới đã tồn tại trong bảng chưa
    	boolean isDuplicate = false;
    	for (int row = 0; row < model.getRowCount(); row++) {
    	    Object[] rowData = new Object[model.getColumnCount()];
    	    for (int col = 0; col < model.getColumnCount(); col++) {
    	        rowData[col] = model.getValueAt(row, col);
    	    }

    	    // So sánh dữ liệu mới với từng hàng trong bảng
    	    if (Arrays.equals(rowData, Data)) {
    	        isDuplicate = true;
    	        break; // Thoát vòng lặp nếu tìm thấy bản sao
    	    }
    	}

    	// Nếu không phải là bản sao, thêm dữ liệu vào bảng
    	if (!isDuplicate) {
    	    model.addRow(Data); // Thêm dữ liệu mới vào JTable
    	}

    }
    


    /**
     * Create the frame.
     */
    
    public void LoadForm() {
    	
    	WindowListener windowListener = new ControllerLoadQLFilesForm(this);
    	this.addWindowListener(windowListener);
    }
    
    public QLFilesForm() {
        setTitle("Quản lý Files");
        
        WindowListener windowListener = new ControllerLoadQLFilesForm(this);
    	this.addWindowListener(windowListener);
    	
        ActionListener actionListener = new ControllerQLFilesForm(this);
        this.LoadForm();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 925, 725);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setBackground(new Color(255, 255, 255));
        contentPane.add(panel, BorderLayout.SOUTH);

        JLabel lblNewLabel = new JLabel("Nhập IDFile:");
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 16));
        panel.add(lblNewLabel);

        IdText = new JTextField();
        IdText.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel.add(IdText);
        IdText.setColumns(10);

        DeleteBt = new JButton("Delete");
        DeleteBt.setPreferredSize(new Dimension(90, 23));
        DeleteBt.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ActionListener[] actionListeners = DeleteBt.getActionListeners();
        for( ActionListener listener : actionListeners ) {
        	DeleteBt.removeActionListener(listener);
        }
        DeleteBt.addActionListener(actionListener);
        panel.add(DeleteBt);

        LockBt = new JButton("Lock");
        LockBt.setPreferredSize(new Dimension(90, 23));
        LockBt.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ActionListener[] actionListeners2 = LockBt.getActionListeners();
        for( ActionListener listener : actionListeners2 ) {
        	LockBt.removeActionListener(listener);
        }
        LockBt.addActionListener(actionListener);
        panel.add(LockBt);
        
        UnLockBt = new JButton("UnLock");
        UnLockBt.setPreferredSize(new Dimension(90, 23));
        UnLockBt.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ActionListener[] actionListeners3 = UnLockBt.getActionListeners();
        for( ActionListener listener : actionListeners3 ) {
        	UnLockBt.removeActionListener(listener);
        }
        UnLockBt.addActionListener(actionListener);
        panel.add(UnLockBt);

        
        
        model = new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
                return false; // Set cells non-editable
            }
		};
        table = new JTable(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        model.addColumn("NameDocument");
        model.addColumn("IdFile");
        model.addColumn("NamePoster");
        model.addColumn("Class");
        model.addColumn("Object");
        model.addColumn("NameFile");
        model.addColumn("Status");
        table.getColumnModel().getColumn(0).setPreferredWidth(200);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(300);
        table.getColumnModel().getColumn(6).setPreferredWidth(100);
        
		
		
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBackground(new Color(255, 255, 255));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        contentPane.add(scrollPane, BorderLayout.CENTER);
		
       

        

    }
    
    
}
