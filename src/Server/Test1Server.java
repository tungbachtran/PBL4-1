package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import Server.MainServer.ClientHandlers;

public class Test1Server {
public Test1Server() {
		
		try {
		ServerSocket server = new ServerSocket(12347);
		
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
			try {
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());
				DataInputStream in = new DataInputStream(socket.getInputStream());
				String request = in.readUTF();
				if(request.equals("Upfile")) {
					int NameFileLength = in.readInt();
					System.out.println("Vào đây rồi");
					if(NameFileLength > 0) {
						byte [] filenameByte = new byte[NameFileLength];
						in.readFully(filenameByte , 0 , filenameByte.length);
						String fileName = new String(filenameByte);					
						byte[] fileNametoByte = fileName.getBytes();						
						
						System.out.println(fileName);
						
					}
					/*
					System.out.println("Đã xuống đây");
					int ContentFileLength = in.readInt();
					if(ContentFileLength > 0) {
						byte[] contentfileByte = new byte[ContentFileLength];
						in.readFully(contentfileByte, 0 , contentfileByte.length);
						String contentFile = new String(contentfileByte);
						byte[] contentFiletoByte = contentFile.getBytes();
						System.out.println("rứa");
						out.writeInt(ContentFileLength);
						out.write(contentFiletoByte);
					}
					*/
					
			
					
				}else if(request.startsWith("Login ")) {
					String []parts = request.split(" ");
					String Account = parts[1];
					String password = parts[2];
					
					if(Account.equals("Test") && password.equals("123")) {
						out.writeUTF("OK");
					}
					else out.writeUTF("Sai rồi");
				}
					
				socket.close();				
				
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			
		
		}
	}
	
	public static void main(String[] args) {
		new Test1Server();
	}

}
