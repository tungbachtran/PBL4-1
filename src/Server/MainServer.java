package Server;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.invoke.StringConcatFactory;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.plaf.InternalFrameUI;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.Driver;
import com.mysql.cj.xdevapi.PreparableStatement;

import Class.Files;
import library.AES256;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class MainServer {
	
	public MainServer() {
		
		try {
		ServerSocket server = new ServerSocket(12345);
		
		while(true) {
			Socket socket = server.accept()	;
			
			Thread clientThread = new Thread(new ClientHandlers(socket));
			clientThread.start();
			
		}
		}catch(Exception e1) {
			System.out.println("111");
		}
	}
	
	class ClientHandlers implements Runnable{
		private Socket socket;
		
		
		public ClientHandlers(Socket socket) {
			this.socket = socket;
		}
		

		@Override
		public void run() {
			Files files = new Files();
			try {
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				String request = in.readUTF();
				String urString = "jdbc:mySQL://localhost:3306/documents";
				String Username = "root";
				String password = "";
				Connection cnnConnection = DriverManager.getConnection(urString,Username,password);


				if(request.equals("Upfile")) {
					
					String namePoster = in.readUTF();
					String Class = in.readUTF();
					String NameDocument = in.readUTF();
					String Object = in.readUTF();
					String NameFile = in.readUTF();
					String Account = in.readUTF();
					
					String queString = "select * from fileinfor WHERE NameDocument = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(queString);
					statement.setString(1, NameDocument);
					ResultSet resultSet = statement.executeQuery();
					
					String queString2 = "select * from fileinfor WHERE NameFile = ?";
					PreparedStatement statement2 = cnnConnection.prepareStatement(queString2);
					statement2.setString(1, NameFile);
					ResultSet resultSet2 = statement2.executeQuery();
					
					int i = 0;
					int k = 0;
					while(resultSet.next()) {
						i = 1;
					}
					while(resultSet2.next()) {
						k = 1;
					}
					if(i == 1 && k == 0) {
						out.writeUTF("NameDocument");
					}else if(i == 0 && k == 1) {
						out.writeUTF("NameFile");
					}else if( i == 1 && k == 1) {
						out.writeUTF("DocumentFile");
					}else {
						out.writeUTF("OK");
			            //String folderPath = "C:\\Users\\FPT\\eclipse-workspace\\PBL004\\Lớp 12";
			            String folderPath = "D:\\Documents\\Java\\PBL4\\" + Class + "\\" + Object;
			            
			            File folder = new File(folderPath);
			            if (!folder.exists()) {
			            	
			                folder.mkdirs();  
			            }
			            
			            byte[] buffer = new byte[4096];
		                int bytesRead;
		                
		                
		                
		                // Tạo FileOutputStream với đường dẫn đến file trong thư mục đã chỉ định
		                
		               
		                
		                
		                FileOutputStream fileOutputStream = new FileOutputStream(folderPath +"\\"+ NameFile);
		                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
		                int c = 0;
		               
		                Long length = in.readLong();
		               
		                while (length > 0 && (bytesRead = in.read(buffer, 0, (int) Math.min(buffer.length, length))) != -1) {
		                    bufferedOutputStream.write(buffer, 0, bytesRead);
		                    length -= bytesRead;
		                }
		                bufferedOutputStream.close();
		                fileOutputStream.close();
		                
		            
		                
		                String query = "INSERT INTO fileinfor (NamePoster, Class, NameDocument, Object, NameFile, Account,Status) VALUES (?, ?, ?, ?, ?, ?, ?)";
						PreparedStatement preparedStatement = cnnConnection.prepareStatement(query);
						preparedStatement.setString(1, namePoster);
			            preparedStatement.setString(2, Class);
			            preparedStatement.setString(3, NameDocument);
			            preparedStatement.setString(4, Object);
			            preparedStatement.setString(5, NameFile);
			            preparedStatement.setString(6, Account);
			            preparedStatement.setString(7, "Unlocked");
			            int rowsAffected = preparedStatement.executeUpdate();
			            if(rowsAffected > 0) {
			            	out.writeUTF("OK");
			            }
			            
			            //fileOutStream.close();    
		                //bufferedOutputStream.close();
			            preparedStatement.close();
					}
		            
					
				}else if(request.startsWith("Login")) {
					String secretKey = "MySecretKey";
				    String salt = "MySalt";
					String Account = in.readUTF();
					String password1 = in.readUTF();
					
					String query = "SELECT * FROM account WHERE Account = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, Account);
					
					
					ResultSet resultSet = statement.executeQuery();
					if(resultSet.next()) {
						String ecrytpPass = resultSet.getString("Password");
						String decryptedString = AES256.decrypt(ecrytpPass, secretKey, salt);
						if(password1.equals(decryptedString)) {
							out.writeUTF("OK");
							if(resultSet.getString("Type").equals("admin")) out.writeUTF("admin");
							else out.writeUTF("user");
						out.writeUTF(resultSet.getString("Username"));
						}else out.writeUTF("No");
					}else out.writeUTF("No");
				}else if(request.equals("Register")) {	
					String secretKey = "MySecretKey";
				    String salt = "MySalt";
					String query = "INSERT INTO account(Account,Password,Email,SDT,Username,Type) VALUES(?,?,?,?,?,?)";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					
					String Account = in.readUTF();
					String passwordString = in.readUTF();
					String Email = in.readUTF();
					String SDT = in.readUTF();
					String userName = in.readUTF();
					String EncryptPass = AES256.encrypt(passwordString, secretKey, salt);
					
					statement.setString(1, Account);
					statement.setString(2, EncryptPass);
					statement.setString(3, Email);
					statement.setString(4, SDT);
					statement.setString(5, userName);
					statement.setString(6, "user");
					statement.executeUpdate();
					out.writeUTF("PASS");		
					
				}else if(request.equals("FileClass")) {
					
					String Class = in.readUTF();
					String Object = in.readUTF();
					
					
					
					
					// chọn tên file thuộc lớp đã truyền
					String query = "SELECT * from fileinfor where Class = ? And Object = ? And Status = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, Class);
					statement.setString(2, Object);
					statement.setString(3, "Unlocked");
					ResultSet resultSet = statement.executeQuery();
					
					
					// đếm tên file thuộc lớp đã truyền
					String query2 = "SELECT * from fileinfor where Class = ? And Object = ? And Status = ?";
					PreparedStatement statement2 = cnnConnection.prepareStatement(query2);
					statement2.setString(1, Class);
					statement2.setString(2, Object);
					statement2.setString(3, "Unlocked");
					ResultSet resultSet2 = statement2.executeQuery();
					
					int rowCount = 0;
					
					
		            while (resultSet2.next()) {
		                rowCount++;
		                
		            }
		            
		           
		            // truyền số dòng về client
		            out.writeInt(rowCount);
		           
		            //truyền tên file về client
		            int i = 0;
		            //resultSet.beforeFirst();
		            while (resultSet.next()) {
		            	//System.out.println(i++);
		            	String NameDocument = resultSet.getString("NameDocument");	
						out.writeUTF(NameDocument);	
						out.writeUTF(resultSet.getString("Class"));
						out.writeUTF(resultSet.getString("NamePoster"));
						out.writeUTF(resultSet.getString("Object"));
						out.writeUTF(resultSet.getString("NameFile"));
		            
				}
		            
				}else if(request.equals("DownloadFile")) {
					
					
					String NameDocument = in.readUTF();
					String query = "Select * from fileinfor where NameDocument = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, NameDocument);
					ResultSet resultSet = statement.executeQuery();
					while(resultSet.next()) {
						String nameFile = resultSet.getString("NameFile");
						String object = resultSet.getString("Object");
						String Class = resultSet.getString("Class");
								
					String folderPath = "D:\\Documents\\Java\\PBL4\\"+ Class +"\\" + object + "\\" + nameFile;
					System.out.println(folderPath);
					File file = new File(folderPath);
					FileInputStream fileInputStream = new FileInputStream(file);
					BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
					
					 byte[] buffer = new byte[4096];
	                 int bytesRead;
	                 
	                 //Gửi độ dài của file
	                 out.writeLong(file.length());
	                 out.writeUTF(nameFile);
	                 while ((bytesRead = bufferedInputStream.read(buffer)) != -1) {
	                	 //Gửi nội dung
	                     out.write(buffer, 0, bytesRead);
	                 }
	                 out.flush();
	                 bufferedInputStream.close();
	                 fileInputStream.close();
	                 
					}
				}else if(request.equals("Search")) {
					
					String nameDocument = in.readUTF();
					String query = "Select * from fileinfor where NameDocument LIKE ? And Status = ?";
					String nameDocumentPattern = "%" + nameDocument + "%";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					PreparedStatement statement2 = cnnConnection.prepareStatement(query);
					statement.setString(1, nameDocumentPattern);
					statement.setString(2, "Unlocked");
					statement2.setString(1, nameDocumentPattern);
					statement2.setString(2, "Unlocked");
					ResultSet resultSet = statement.executeQuery();
					ResultSet resultSet2 = statement2.executeQuery();
					
					int i = 0;
					
					while (resultSet2.next()) {
						i++;
					}
					out.writeInt(i);
					
					while(resultSet.next()) {
					
						String NameDocument = resultSet.getString("NameDocument");	
						out.writeUTF(NameDocument);	
						out.writeUTF(resultSet.getString("Class"));
						out.writeUTF(resultSet.getString("NamePoster"));
						out.writeUTF(resultSet.getString("Object"));
						out.writeUTF(resultSet.getString("NameFile"));
					}
				}else if(request.equals("Forget")) {
					String secretKey = "MySecretKey";
				    String salt = "MySalt";
					String login = in.readUTF();
					String email = in.readUTF();
					String sdt = in.readUTF();
					String userName = in.readUTF();
					String passNew = in.readUTF();
					String encrytpPass = AES256.encrypt(passNew, secretKey, salt);
					String query = "UPDATE account SET Password = ? WHERE Account = ? AND Email = ? AND SDT = ? AND Username = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, encrytpPass);
					statement.setString(2, login);
					statement.setString(3, email);
					statement.setString(4, sdt);
					statement.setString(5, userName);
					int rowaffect = statement.executeUpdate();
					
					if( rowaffect > 0) {
						out.writeUTF("YES");
					}
				}else if(request.equals("ShowComment")) {
					String Idfile = in.readUTF();
					String query = "Select * from cminfor where IdFile = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, Idfile);
					PreparedStatement statement2 = cnnConnection.prepareStatement(query);
					statement2.setString(1, Idfile);
					ResultSet resultSet1 = statement.executeQuery();
					ResultSet resultSet2 = statement2.executeQuery();
					int i = 0;
					while(resultSet1.next()) {
						i++;
					}
					out.writeInt(i);
					while(resultSet2.next()) {
						out.writeUTF(resultSet2.getString("Account"));
						out.writeUTF(resultSet2.getString("UserName") +
					    ": " +resultSet2.getString("Cmtext")+ 
					    "    Vào lúc " + resultSet2.getString("Date"));
					}
					
				}else if (request.equals("ShowInfor")) {
				    try {
				    	System.out.println("heloo");
				        // Đọc tài khoản từ client
				        String account = in.readUTF();

				        // Truy vấn thông tin từ database
				        String query = "SELECT Email, SDT, UserName FROM account WHERE Account = ?";
				        PreparedStatement preparedStatement = cnnConnection.prepareStatement(query);
				        preparedStatement.setString(1, account);
				        ResultSet resultSet = preparedStatement.executeQuery();

				        if (resultSet.next()) {
				            // Lấy thông tin từ ResultSet
				            String email = resultSet.getString("Email");
				            String phone = resultSet.getString("SDT");
				            String userName = resultSet.getString("UserName");

				            // Gửi thông tin về client
				            out.writeUTF(email);
				            out.writeUTF(phone);
				            out.writeUTF(userName);
				        } else {
				            // Trường hợp không tìm thấy tài khoản
				            out.writeUTF("null");
				            out.writeUTF("null");
				            out.writeUTF("null");
				        }

				        preparedStatement.close();
				    } catch (Exception e) {
				        e.printStackTrace();
				        // Gửi lỗi về client nếu xảy ra lỗi
				        out.writeUTF("error");
				        out.writeUTF("error");
				        out.writeUTF("error");
				    }
				}
				else if(request.equals("GetIDFile")) {
					String query = "Select * from fileinfor where NameDocument = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					String nameDocument = in.readUTF();
					statement.setString(1, nameDocument);
					ResultSet resultSet = statement.executeQuery();
					
					while(resultSet.next()) {
						out.writeUTF(resultSet.getString("Id"));
					}
				}else if(request.equals("UpComment")) {
					String Idfile = in.readUTF();
					String query = "Insert into cminfor(IdFile,UserName,Account,CmText,Date) Value(?,?,?,?,?)";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, Idfile);
					statement.setString(2, in.readUTF());
					statement.setString(3, in.readUTF());
					statement.setString(4, in.readUTF());
					 SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
					    SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

					    // Đọc giá trị ngày giờ từ luồng
					    String inputDate = in.readUTF(); // Ví dụ: "18/11/2024 00:08:00"

					    // Chuyển đổi sang định dạng MySQL
					    Date parsedDate = inputFormat.parse(inputDate);
					    String formattedDate = outputFormat.format(parsedDate);

					    // Truyền giá trị đã định dạng vào statement
					    statement.setString(5, formattedDate);

					    // Thực thi câu lệnh
					    statement.executeUpdate();
					
					String query2 = "Select * from cminfor where IdFile = ?";
					PreparedStatement statement1 = cnnConnection.prepareStatement(query2);
					statement1.setString(1,Idfile);
					PreparedStatement statement2 = cnnConnection.prepareStatement(query2);
					statement2.setString(1,Idfile);
					ResultSet resultSet1 = statement1.executeQuery();
					ResultSet resultSet2 = statement2.executeQuery();
					
					int i = 0;
					while(resultSet1.next()) {
						i++;
					}
					out.writeInt(i);
					while(resultSet2.next()) {
						out.writeUTF(resultSet2.getString("Account"));
						out.writeUTF(resultSet2.getString("UserName") +
					    ": " +resultSet2.getString("Cmtext")+ 
					    "    Vào lúc " + resultSet2.getString("Date"));
					}
				}else if(request.equals("MyFile")) {
					String account = in.readUTF();
					String query = "Select * from fileinfor where Account = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, account);
					PreparedStatement statement2 = cnnConnection.prepareStatement(query);
					statement2.setString(1, account);
					ResultSet resultSet = statement.executeQuery();
					ResultSet resultSet2 = statement2.executeQuery();
					
					int i = 0;
					
					while (resultSet2.next()) {
						i++;
					}
					out.writeInt(i);
					
					while(resultSet.next()) {
					
						String NameDocument = resultSet.getString("NameDocument");	
						out.writeUTF(NameDocument);	
						out.writeUTF(resultSet.getString("Class"));
						out.writeUTF(resultSet.getString("NamePoster"));
						out.writeUTF(resultSet.getString("Object"));
						out.writeUTF(resultSet.getString("NameFile"));
					}
					
				}else if(request.equals("DeleteComment")) {
					String Account = in.readUTF();
					String username = in.readUTF();
					String content = in.readUTF();
					String date = in.readUTF();
					String idFile = in.readUTF();
 					String query = "Delete From cminfor where Account = ? AND Username = ? AND CmText = ? AND Date = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, Account);
					statement.setString(2, username);
					statement.setString(3, content);
					statement.setString(4, date);
					
					statement.executeUpdate();
					
					out.writeUTF("OK");
					
					String query2 = "SELECT * From cminfor where IdFile = ?";
					PreparedStatement statement2 = cnnConnection.prepareStatement(query2);
					statement2.setString(1, idFile);
					PreparedStatement statement3 = cnnConnection.prepareStatement(query2);
					statement3.setString(1, idFile);
					ResultSet resultSet = statement2.executeQuery();
					ResultSet resultSet2 = statement3.executeQuery();
					int i = 0;
					while(resultSet.next()) {
						i++;
					}
					out.writeInt(i);
					while(resultSet2.next()) {
						out.writeUTF(resultSet2.getString("Account"));
						out.writeUTF(resultSet2.getString("UserName") +
					    ": " +resultSet2.getString("Cmtext")+ 
					    "    Vào lúc " + resultSet2.getString("Date"));
					}
					
				}else if(request.equals("GetAllFile")) {
					String query = "Select * from fileinfor";
					PreparedStatement statement = cnnConnection.prepareStatement(query);					
					PreparedStatement statement2 = cnnConnection.prepareStatement(query);
					ResultSet resultSet = statement.executeQuery();
					ResultSet resultSet2 = statement2.executeQuery();
					
					int i = 0;
					
					while (resultSet2.next()) {
						i++;
					}
					out.writeInt(i);
					
					while(resultSet.next()) {
					
						String NameDocument = resultSet.getString("NameDocument");	
						out.writeUTF(NameDocument);	
						out.writeUTF(resultSet.getString("Id"));				
						out.writeUTF(resultSet.getString("NamePoster"));
						out.writeUTF(resultSet.getString("Class"));
						out.writeUTF(resultSet.getString("Object"));
						out.writeUTF(resultSet.getString("NameFile"));
						out.writeUTF(resultSet.getString("Status"));
					}
				}else if(request.equals("GetNameDocumentById")) {
					String idString = in.readUTF();
					String query = "select NameDocument from fileinfor where Id = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, idString);
					ResultSet resultSet = statement.executeQuery();
					
					while(resultSet.next()) {
						out.writeUTF(resultSet.getString("NameDocument"));
						
					}
					
					String response = in.readUTF();
					if(response.equals("OK")) {
						String query1 = "Delete from fileinfor where Id = ?";
						PreparedStatement statement1 = cnnConnection.prepareStatement(query1);
						statement1.setString(1, idString);
						statement1.executeUpdate();
						
						
						
						String query3 = "Select * from fileinfor";
						PreparedStatement statement3 = cnnConnection.prepareStatement(query3);					
						PreparedStatement statement4 = cnnConnection.prepareStatement(query3);
						ResultSet resultSet1 = statement3.executeQuery();
						ResultSet resultSet2 = statement4.executeQuery();
						
						int i = 0;
						
						while (resultSet2.next()) {
							i++;
						}
						out.writeInt(i);
						
						while(resultSet1.next()) {
						
							String NameDocument = resultSet1.getString("NameDocument");	
							out.writeUTF(NameDocument);	
							out.writeUTF(resultSet1.getString("Id"));				
							out.writeUTF(resultSet1.getString("NamePoster"));
							out.writeUTF(resultSet1.getString("Class"));
							out.writeUTF(resultSet1.getString("Object"));
							out.writeUTF(resultSet1.getString("NameFile"));
							out.writeUTF(resultSet1.getString("Status"));
						}
						
					}
					
				}else if(request.equals("LockFile")) {
					String idString = in.readUTF();
					String query = "select * from fileinfor where Id = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, idString);
					ResultSet resultSet = statement.executeQuery();
					
					while(resultSet.next()) {
						out.writeUTF(resultSet.getString("NameDocument"));
						out.writeUTF(resultSet.getString("Status"));
						
					}
					String response = in.readUTF();
					if(response.equals("OK")) {
						String query2 = "UPDATE fileinfor SET Status = ? WHERE Id = ?";
						PreparedStatement statement2 = cnnConnection.prepareStatement(query2);
						statement2.setString(1, "Locked");
						statement2.setString(2, idString);
						statement2.executeUpdate();
						
						String query3 = "Select * from fileinfor";
						PreparedStatement statement3 = cnnConnection.prepareStatement(query3);					
						PreparedStatement statement4 = cnnConnection.prepareStatement(query3);
						ResultSet resultSet1 = statement3.executeQuery();
						ResultSet resultSet2 = statement4.executeQuery();
						
						int i = 0;
						
						while (resultSet2.next()) {
							i++;
						}
						out.writeInt(i);
						
						while(resultSet1.next()) {
						
							String NameDocument = resultSet1.getString("NameDocument");	
							out.writeUTF(NameDocument);	
							out.writeUTF(resultSet1.getString("Id"));				
							out.writeUTF(resultSet1.getString("NamePoster"));
							out.writeUTF(resultSet1.getString("Class"));
							out.writeUTF(resultSet1.getString("Object"));
							out.writeUTF(resultSet1.getString("NameFile"));
							out.writeUTF(resultSet1.getString("Status"));
						}
						
					}
					
				}else if(request.equals("UnLockFile")){
					String idString = in.readUTF();
					String query = "select * from fileinfor where Id = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, idString);
					ResultSet resultSet = statement.executeQuery();
					
					while(resultSet.next()) {
						out.writeUTF(resultSet.getString("NameDocument"));
						out.writeUTF(resultSet.getString("Status"));
						
					}
					String response = in.readUTF();
					if(response.equals("OK")) {
						String query2 = "UPDATE fileinfor SET Status = ? WHERE Id = ?";
						PreparedStatement statement2 = cnnConnection.prepareStatement(query2);
						statement2.setString(1, "Unlocked");
						statement2.setString(2, idString);
						statement2.executeUpdate();
						
						String query3 = "Select * from fileinfor";
						PreparedStatement statement3 = cnnConnection.prepareStatement(query3);					
						PreparedStatement statement4 = cnnConnection.prepareStatement(query3);
						ResultSet resultSet1 = statement3.executeQuery();
						ResultSet resultSet2 = statement4.executeQuery();
						
						int i = 0;
						
						while (resultSet2.next()) {
							i++;
						}
						out.writeInt(i);
						
						while(resultSet1.next()) {
						
							String NameDocument = resultSet1.getString("NameDocument");	
							out.writeUTF(NameDocument);	
							out.writeUTF(resultSet1.getString("Id"));				
							out.writeUTF(resultSet1.getString("NamePoster"));
							out.writeUTF(resultSet1.getString("Class"));
							out.writeUTF(resultSet1.getString("Object"));
							out.writeUTF(resultSet1.getString("NameFile"));
							out.writeUTF(resultSet1.getString("Status"));
						}
					}
					
				}else if(request.equals("LoadReport")) {
					String query = "Select * from report where Status = ?";
					PreparedStatement statement1 = cnnConnection.prepareStatement(query);					
					PreparedStatement statement2 = cnnConnection.prepareStatement(query);
					statement1.setString(1, "No");
					statement2.setString(1, "No");
					ResultSet resultSet1 = statement1.executeQuery();
					ResultSet resultSet2 = statement2.executeQuery();
					
					int i = 0;
					
					while (resultSet2.next()) {
						i++;
					}
					out.writeInt(i);
					
					while(resultSet1.next()) {
						out.writeUTF(resultSet1.getString("IDReport"));
						out.writeUTF(resultSet1.getString("NameDocument"));
						out.writeUTF(resultSet1.getString("Account"));
						out.writeUTF(resultSet1.getString("Username"));
						out.writeUTF(resultSet1.getString("ReportContent"));
					}
				}else if(request.equals("PostReport")) {
					String Account = in.readUTF();
					String IDFile = in.readUTF();
					String content = in.readUTF();
					String query = "Select * from fileinfor where ID = ?";
					System.out.println("check server: " + Account + " - " + IDFile + " - " + content);
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, IDFile);
					ResultSet resultSet = statement.executeQuery();
					String nameDocument = null;
					String UserName = null;
					while(resultSet.next()) {
						nameDocument = resultSet.getString("NameDocument");
						UserName = resultSet.getString("NamePoster");
					}
					
					String query2 = "Insert into report(NameDocument,Account,UserName,ReportContent, Response, Status, Seen) Value(?,?,?,?,?,?,?)";
					PreparedStatement statement2 = cnnConnection.prepareStatement(query2);
					statement2.setString(1, nameDocument);
					statement2.setString(2, Account);
					statement2.setString(3, UserName);
					statement2.setString(4, content);
					statement2.setString(5, "No");
					statement2.setString(6, "No");
					statement2.setString(7, "No");
					
					statement2.executeUpdate();
					
					out.writeUTF("OK");
					
				}else if(request.equals("ResponseFromAdmin")) {
					String IdResponse = in.readUTF(); 
					String Response = in.readUTF();
					
					String query = "UPDATE report SET Response = ?,Status = ? Where IDReport = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, Response);
					statement.setString(2, "Done");
					statement.setString(3, IdResponse);
					
					statement.executeUpdate();
					
					out.writeUTF("OK");
					
					String query2 = "Select * from report where Status = ?";
					PreparedStatement statement1 = cnnConnection.prepareStatement(query2);					
					PreparedStatement statement2 = cnnConnection.prepareStatement(query2);
					statement1.setString(1, "No");
					statement2.setString(1, "No");
					ResultSet resultSet1 = statement1.executeQuery();
					ResultSet resultSet2 = statement2.executeQuery();
					
					int i = 0;
					
					while (resultSet2.next()) {
						i++;
					}
					out.writeInt(i);
					
					while(resultSet1.next()) {
						out.writeUTF(resultSet1.getString("IDReport"));
						out.writeUTF(resultSet1.getString("NameDocument"));
						out.writeUTF(resultSet1.getString("Account"));
						out.writeUTF(resultSet1.getString("Username"));
						out.writeUTF(resultSet1.getString("ReportContent"));
					}
				}else if(request.equals("GetResponse")) {
					String Account = in.readUTF();
					String query1 = "Update report SET SEEN = ? Where Account = ? And Status = ?";
					PreparedStatement statement3 = cnnConnection.prepareStatement(query1);
					statement3.setString(1, "OK");
					statement3.setString(2, Account);
					statement3.setString(3, "Done");
					
					statement3.executeUpdate();
					
					String query = "Select * from report where Account = ? And Status = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, Account);
					statement.setString(2, "Done");
					
					PreparedStatement statement2 = cnnConnection.prepareStatement(query);
					statement2.setString(1, Account);
					statement2.setString(2, "Done");
					ResultSet resultSet = statement.executeQuery();
					ResultSet resultSet2 = statement2.executeQuery();
					
					int i = 0;
					while(resultSet.next()) {
						i++;
					}
					out.writeInt(i);
					
					while(resultSet2.next()) {
						out.writeUTF("Admin đã phản hồi : " + resultSet2.getString("Response") + " |||Từ tài liệu: " 
					+ resultSet2.getString("NameDocument") + " |||Nội dung là : " + resultSet2.getString("ReportContent"));
					}
				}else if(request.equals("LoadBtUser")) {
					String Account = in.readUTF();
					String query = "SELECT COUNT(*) AS total FROM report WHERE Account = ? AND Status = ? AND Seen = ?";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, Account);
					statement.setString(2, "Done");
					statement.setString(3, "No");
					
					ResultSet resultSet = statement.executeQuery();
					
					while(resultSet.next()) {
						out.writeInt(resultSet.getInt("total"));
					}
				}else if(request.equals("LoadBtAdmin")) {
					
					String query = "SELECT COUNT(*) AS total FROM report WHERE Status = ? ";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, "No");
					
					
					ResultSet resultSet = statement.executeQuery();
					
					while(resultSet.next()) {
						out.writeInt(resultSet.getInt("total"));
					}
				}else if(request.equals("ShowAccount")) {
					// chọn tên file thuộc lớp đã truyền
					String query = "SELECT * from account";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					ResultSet resultSet = statement.executeQuery();
					
					
					// đếm tên file thuộc lớp đã truyền
					String query2 = "SELECT * from account";
					PreparedStatement statement2 = cnnConnection.prepareStatement(query2);
					ResultSet resultSet2 = statement2.executeQuery();
					
					int rowCount = 0;
					
					
		            while (resultSet2.next()) {
		                rowCount++;
		                
		            }
		            
		           
		            // truyền số dòng về client
		            out.writeInt(rowCount);
		           
		            //truyền tên file về client
		            int i = 0;
		            //resultSet.beforeFirst();
		            while (resultSet.next()) {
		            	//System.out.println(i++);
		            	String Account = resultSet.getString("Account");	
						out.writeUTF(Account);	
						out.writeUTF(resultSet.getString("UserName"));
						out.writeUTF(resultSet.getString("Type"));
		            
		            }
				}else if(request.equals("RegisterADMIN")) {	
					String secretKey = "MySecretKey";
				    String salt = "MySalt";
					String query = "INSERT INTO account(Account,Password,Email,SDT,Username,Type) VALUES(?,?,?,?,?,?)";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					
					String Account = in.readUTF();
					String passwordString = in.readUTF();
					String Email = in.readUTF();
					String SDT = in.readUTF();
					String userName = in.readUTF();
					String EncryptPass = AES256.encrypt(passwordString, secretKey, salt);
					
					statement.setString(1, Account);
					statement.setString(2, EncryptPass);
					statement.setString(3, Email);
					statement.setString(4, SDT);
					statement.setString(5, userName);
					statement.setString(6, "admin");
					statement.executeUpdate();
					out.writeUTF("PASSAD");		
					
				}else if(request.equals("DeleteAccount")) {	
					String UserName = in.readUTF();
 					String query = "Delete From account where Account = ? ";
					PreparedStatement statement = cnnConnection.prepareStatement(query);
					statement.setString(1, UserName);
					statement.executeUpdate();
					out.writeUTF("OK");	
				}
				
				cnnConnection.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			
		
		}
	}
	
	public static void main(String[] args) {
		new MainServer();
	}
}
	


