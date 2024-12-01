package VIEW;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.ControllerPostFile;
import javax.swing.SwingConstants;

public class FormPostFile extends JFrame {

	
	private JPanel contentPane;
	public JTextField JTFName;
	private JLabel lblTiLiuLp;
	public JComboBox comboBox;
	private JLabel lblTiTiLiu;
	public JTextField textField;
	private JButton btnUpload;
	private JButton btnUpload_1;
	private JButton btnCancel;
	private JLabel lblTnTiLiu;
	public JTextField txtNameDoc;
	public JLabel lblNewLabel_1;
	private JLabel lblMnHc;
	public JComboBox comboBox_1;
	public JLabel lblAccount;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormPostFile frame = new FormPostFile();
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
	
	public void AddLabel(String name) {
		
	}
	
	public FormPostFile() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 621, 610);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tên người dùng : ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(57, 111, 141, 40);
		contentPane.add(lblNewLabel);
		
		JTFName = new JTextField();
		JTFName.setSelectionColor(Color.BLACK);
		JTFName.setBounds(205, 110, 234, 36);
		contentPane.add(JTFName);
		JTFName.setColumns(10);
		
		lblTiLiuLp = new JLabel("Tài liệu lớp :");
		lblTiLiuLp.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTiLiuLp.setBounds(57, 189, 141, 40);
		contentPane.add(lblTiLiuLp);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Lớp 12", "Lớp 11", "Lớp 10", "Lớp 9", "Lớp 8", "Lớp 7", "Lớp 6"}));
		comboBox.setBounds(207, 194, 141, 31);
		contentPane.add(comboBox);
		
		lblTiTiLiu = new JLabel("Đường dẫn: ");
		lblTiTiLiu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTiTiLiu.setBounds(57, 377, 141, 40);
		contentPane.add(lblTiTiLiu);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(205, 382, 234, 36);
		contentPane.add(textField);
		
		ActionListener action = new ControllerPostFile(this);
		
		btnUpload = new JButton("Link");
		btnUpload.addActionListener(action);
		btnUpload.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpload.setBounds(459, 377, 123, 40);
		contentPane.add(btnUpload);
		
		btnUpload_1 = new JButton("Upfile");
		btnUpload_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnUpload_1.addActionListener(action);
		btnUpload_1.setBounds(116, 477, 123, 40);
		contentPane.add(btnUpload_1);
		
		btnCancel = new JButton("Hủy");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancel.addActionListener(action);
		btnCancel.setBounds(354, 477, 123, 40);
		contentPane.add(btnCancel);
		
		lblTnTiLiu = new JLabel("Tên tài liệu : ");
		lblTnTiLiu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTnTiLiu.setBounds(57, 257, 141, 40);
		contentPane.add(lblTnTiLiu);
		
		txtNameDoc = new JTextField();
		txtNameDoc.setColumns(10);
		txtNameDoc.setBounds(205, 262, 234, 36);
		contentPane.add(txtNameDoc);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setForeground(new Color(255, 0, 0));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(57, 411, 515, 50);
		contentPane.add(lblNewLabel_1);
		
		lblMnHc = new JLabel("Môn học");
		lblMnHc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMnHc.setBounds(57, 321, 141, 40);
		contentPane.add(lblMnHc);
		
		comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Toán", "Vật Lý", "Hóa Học", "Sinh Học", "Ngữ Văn", "Lịch Sử", "Địa Lý", "Giáo Dục Kinh Tế & Pháp Luật", "Công Nghệ", "Tin Học"}));
		comboBox_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		comboBox_1.setBounds(207, 330, 141, 31);
		contentPane.add(comboBox_1);
		
		lblAccount = new JLabel("");
		lblAccount.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAccount.setBounds(57, 148, 141, 40);
		lblAccount.setVisible(false);
		contentPane.add(lblAccount);
		
		JLabel lblNewLabel_2 = new JLabel("Add File");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_2.setBounds(183, 30, 243, 70);
		contentPane.add(lblNewLabel_2);
	}
}
