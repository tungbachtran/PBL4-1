package VIEW;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import Controller.ControllerAdminReportForm;
import Controller.ControllerComment;
import Controller.ControllerDeleteComment;
import Controller.ControllerReportContent;
import Controller.ControllerReportFormLoad;
import Controller.ControllerShowComment;
import Controller.ControllerTable;
import VIEW.MainForm.CustomTableCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class AdminReportForm extends JFrame {

    public JPanel contentPane;
    public JTextField CmField;
    public JPanel panelInScrollJPanel;
    public ScrollPane scrollPane;
    private ArrayList<JLabel> labels = new ArrayList<>();
    public JTable table;
    public DefaultTableModel model;
    public TextField IDTextField;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
            	
                AdminReportForm frame = new AdminReportForm();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(3);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    public void addTable(Object[] rowdata) {
		//keepLabel(panel_1,lblNewLabel);
		
		model.addRow(rowdata);
        //ListSelectionListener listSelectionListener = new ControllerTable(this);
        table.getColumnModel().getColumn(0).setCellRenderer(new CustomTableCellRenderer()); 
        table.setDefaultRenderer(Object.class, new CustomTableCellRenderer());
        MouseListener[] mouseListeners = table.getMouseListeners();
        for (MouseListener listener : mouseListeners) {
            table.removeMouseListener(listener);
        }
        MouseListener mouseListener = new ControllerReportContent(this);
        table.addMouseListener(mouseListener);
	}
    
    public void ClearTable() {
		 DefaultTableModel model = (DefaultTableModel) table.getModel();
		 model.setRowCount(0);
	 }

   
    public AdminReportForm() {
        setTitle("Comment");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 928, 645);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        createSouthPanel();
        createCenterPanel();
        WindowListener windowListener = new ControllerReportFormLoad(this);
        this.addWindowListener(windowListener);
    }

    public void createSouthPanel() {
    	ActionListener actionListener = new ControllerAdminReportForm(this);
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.SOUTH);
        panel.setLayout(new BorderLayout(0, 0));

        JPanel panel_2 = new JPanel();
        panel.add(panel_2, BorderLayout.NORTH);
        panel_2.setLayout(new BorderLayout(0, 0));

        CmField = new JTextField();
        CmField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        CmField.setPreferredSize(new Dimension(400, 100));
        panel_2.add(CmField, BorderLayout.CENTER);
        CmField.setColumns(10);

        JButton CmButton = new JButton("Post");
        CmButton.setPreferredSize(new Dimension(130, 35));
        panel_2.add(CmButton, BorderLayout.EAST);
        CmButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        ActionListener [] actionListeners = CmButton.getActionListeners();
        for (ActionListener listener : actionListeners) {
            CmButton.removeActionListener(listener);
        }
        CmButton.addActionListener(actionListener);

        JLabel lblNewLabel = new JLabel("Nhập phản hồi của bạn");
        lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 12));
        panel_2.add(lblNewLabel, BorderLayout.NORTH);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(255, 255, 255));
        panel.add(panel_1, BorderLayout.SOUTH);
        
        JLabel lblNewLabel_1 = new JLabel("Nhập ID mà bạn muốn phản hồi: ");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        panel_1.add(lblNewLabel_1);
        
        IDTextField = new TextField();
        IDTextField.setColumns(5);
        panel_1.add(IDTextField);
        
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
        //DownLButton.addActionListener(actionListener);

        JPanel panel_3 = new JPanel();
        panel.add(panel_3, BorderLayout.WEST);
        panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.X_AXIS));
    }

    private void createCenterPanel() {
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
        model.addColumn("IDReport");
        model.addColumn("NameDocument");
        model.addColumn("Account");
        model.addColumn("UserName");
        model.addColumn("ReportContent");
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        table.getColumnModel().getColumn(4).setPreferredWidth(400);
		
        JScrollPane scrollPane = new JScrollPane(table);
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
        //MouseListener mouseListener = new ControllerDeleteComment(this);
        //newLabel.addMouseListener(mouseListener);
        panelInScrollJPanel.add(newLabel); // Thêm JLabel vào JPanel trong JScrollPane
        panelInScrollJPanel.revalidate(); // Cập nhật giao diện của JPanel trong JScrollPane
        panelInScrollJPanel.repaint(); // Vẽ lại JPanel trong JScrollPane nếu cần thiết
    }
    public void ClearLabel() {
    	panelInScrollJPanel.removeAll();
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
            
        	 if (column == 4) { // Chỉ áp dụng cho cột ReportContent
	            	
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
    /*
    public class MultiLineTableCellRenderer extends JTextArea implements TableCellRenderer {

        public MultiLineTableCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
            setRows(2); // Số dòng tối đa để hiển thị trong một ô
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
                setAlignmentX(CENTER_ALIGNMENT); // Căn giữa theo trục X
                setAlignmentY(CENTER_ALIGNMENT);
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }
    */
}
