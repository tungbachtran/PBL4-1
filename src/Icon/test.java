//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.io.*;
//import java.net.*;
//
//public class ClientGUI extends JFrame {
//    private JTextField textField;
//    private JTextArea textArea;
//
//    public ClientGUI() {
//        // Khởi tạo giao diện
//        setTitle("Client");
//        setLayout(new BorderLayout());
//
//        textField = new JTextField(20);
//        JButton sendButton = new JButton("Gửi");
//        textArea = new JTextArea(10, 30);
//        textArea.setEditable(false);
//
//        sendButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String message = textField.getText();
//                textField.setText("");
//                sendMessageToServer(message);
//            }
//        });
//
//        JPanel panel = new JPanel();
//        panel.add(textField);
//        panel.add(sendButton);
//
//        add(panel, BorderLayout.NORTH);
//        add(new JScrollPane(textArea), BorderLayout.CENTER);
//        pack();
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setVisible(true);
//    }
//
//    private void sendMessageToServer(String message) {
//        try (Socket socket = new Socket("localhost", 12345);
//             OutputStream outputStream = socket.getOutputStream();
//             InputStream inputStream = socket.getInputStream();
//             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//             PrintWriter writer = new PrintWriter(outputStream, true)) {
//
//            // Gửi thông điệp đến server
//            writer.println(message);
//
//            // Nhận phản hồi từ server
//            String response = reader.readLine();
//            textArea.append("Server: " + response + "\n");
//
//        } catch (IOException e) {
//            textArea.append("Lỗi kết nối đến server\n");
//        }
//    }
//
//    public static void main(String[] args) {
//        new ClientGUI();
//    }
//}
