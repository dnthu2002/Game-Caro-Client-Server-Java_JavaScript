package controller;

import dao.UserDAO;

public class Room {
	private int ID;
    private ServerThread user1;
    private ServerThread user2;
    private String password;
    private UserDAO userDAO;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public ServerThread getUser1() {
		return user1;
	}
	public void setUser1(ServerThread user1) {
		this.user1 = user1;
	}
	public ServerThread getUser2() {
		return user2;
	}
	public void setUser2(ServerThread user2) {
		this.user2 = user2;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserDAO getUserDAO() {
		return userDAO;
	}
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
    
    public Room(ServerThread user1) {
    	System.out.println("Tạo phòng thành công, ID là :"+Server.ID_room);
    	this.password = " ";
    	this.ID = Server.ID_room++;
    	userDAO = new UserDAO();
    	this.user1 = user1;
    	this.user2 = null;
    }
    
    public int getNumberOfUser(){
    	return user2 == null?1:2;
    }

    
    public void boardCast(String message) {
    	try {
    		user1.write(message);
    		user2.write(message);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
    // Competitor = đối thủ
    public int getCompetitorID(int ID_ClientNumber) {
    	if(user1.getClientNumber()==ID_ClientNumber)
    		return user2.getUser().getID();
    	return user1.getUser().getID();
    	
    }
    
    public ServerThread getCompetitor(int ID_ClientNumber) {
        if(user1.getClientNumber()==ID_ClientNumber)
            return user2;
        return user1;
    }
    
    public void serUsersToPlaying() {
    	userDAO.updateToPlaying(user1.getUser().getID());
    	if(user2 != null) {
    		userDAO.updateToPlaying(user2.getUser().getID());
    	}
    }
    public void setUsersToNotPlaying(){
        userDAO.updateToNotPlaying(user1.getUser().getID());
        if(user2!=null){
            userDAO.updateToNotPlaying(user2.getUser().getID());
        }
    }
    
    public void increaseNumberOfGame(){
        userDAO.addGame(user1.getUser().getID());
        userDAO.addGame(user2.getUser().getID());
    }
    
    public void increaseNumberOfDraw(){
        userDAO.addDrawGame(user1.getUser().getID());
        userDAO.addDrawGame(user2.getUser().getID());
    }
    
    public void decreaseNumberOfGame(){
        userDAO.decreaseGame(user1.getUser().getID());
        userDAO.decreaseGame(user2.getUser().getID());
    }

}
