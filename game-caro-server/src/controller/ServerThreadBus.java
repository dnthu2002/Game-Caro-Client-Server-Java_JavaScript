package controller;

import java.util.ArrayList;
import java.util.List;

public class ServerThreadBus {
	private List<ServerThread> listServerThreads;
	public List<ServerThread> getListServerThreads(){
		return listServerThreads;
	}
	public ServerThreadBus() {
		listServerThreads  = new ArrayList<>();
	}
	public void add(ServerThread serverThread) {
		listServerThreads.add(serverThread);
	}
	public void mutilCastSend(String message) {
		for(ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			try {
				serverThread.write(message);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
	}
	public void boardCast(int id, String message) {
		for(ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			if(serverThread.getClientNumber()==id) {
				continue;
			} else {
				try {
					serverThread.write(message);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
	}
	public int getLength() {
		return listServerThreads.size();
	}
	public void sendMessageToUserID(int id, String message) {
		for(ServerThread serverThread : Server.serverThreadBus.getListServerThreads()) {
			try {
				serverThread.write(message);
				break;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
	public ServerThread getServerThreadByUserID(int ID) {
		for(int i =0; i<Server.serverThreadBus.getLength();i++) {
			if(Server.serverThreadBus.getListServerThreads().get(i).getUser().getID()==ID) {
				return Server.serverThreadBus.listServerThreads.get(i);
			}
		}
		return null;
	}
	public void remove(int id) {
		for(int i=0; i<Server.serverThreadBus.getLength(); i++) {
			if(Server.serverThreadBus.getListServerThreads().get(i).getClientNumber()==id) {
				Server.serverThreadBus.listServerThreads.remove(i);
			}
		}
	}
}
