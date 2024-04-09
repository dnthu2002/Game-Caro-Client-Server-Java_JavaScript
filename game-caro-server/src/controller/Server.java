package controller;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import view.Admin;

public class Server {
	public static volatile ServerThreadBus serverThreadBus;
	public static Socket socketOfServer;
	public static int ID_room;
	public static volatile Admin admin;
	
	public static void main(String [] args) {
		ServerSocket listener = null;
		serverThreadBus = new ServerThreadBus();
		System.out.println("Server is waiting to accept user ...");
		int clientNumber = 0;
		ID_room = 100;
		
		try {
			listener = new ServerSocket(2408);
		} catch (Exception e) {
			System.out.println(e);
			System.exit(1);
		}
		 ThreadPoolExecutor executor = new ThreadPoolExecutor(
				 10,
				 100,
				 10,
				 TimeUnit.SECONDS,
	             new ArrayBlockingQueue<>(8));
		admin = new Admin();
		admin.run();
		
		try {
			while (true) {
				// Chấp nhận một yêu cầu kết nối từ phía Client.
                // Đồng thời nhận được một đối tượng Socket tại server.
                socketOfServer = listener.accept();
                System.out.println(socketOfServer.getInetAddress().getHostAddress());
                ServerThread serverThread = new ServerThread(socketOfServer, clientNumber++);
                serverThreadBus.add(serverThread);
                System.out.println("Số thread đang chạy là :"+serverThreadBus.getLength());
                executor.execute(serverThread);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				listener.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
