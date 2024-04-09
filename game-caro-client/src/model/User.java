package model;

public class User {
	private int ID;
	private String username;
	private String password;
	private String nickname;
	private String avatar;
	private int numberOfGame;
	private int numberOfWin;
	private int numberOfDraw;
	private boolean isOnline;
	private boolean isPlaying;
	private int rank;

	public User(int iD, String username, String password, String nickname, String avatar, int numberOfGame,
			int numberOfWin, int numberOfDraw, int rank) {
		this.ID = iD;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.avatar = avatar;
		this.numberOfGame = numberOfGame;
		this.numberOfWin = numberOfWin;
		this.numberOfDraw = numberOfDraw;
		this.rank = rank;
	}

	public User(int iD, String username, String password, String nickname, String avatar, int numberOfGame,
			int numberOfWin, int numberOfDraw, boolean isOnline, boolean isPlaying) {

		this.ID = iD;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.avatar = avatar;
		this.numberOfGame = numberOfGame;
		this.numberOfWin = numberOfWin;
		this.numberOfDraw = numberOfDraw;
		this.isOnline = isOnline;
		this.isPlaying = isPlaying;
	}

	public User(int iD, String username, String password, String nickname, String avatar, int numberOfGame,
			int numberOfWin, int numberOfDraw) {
		this.ID = iD;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.avatar = avatar;
		this.numberOfGame = numberOfGame;
		this.numberOfWin = numberOfWin;
		this.numberOfDraw = numberOfDraw;
	}

	public User(int iD, String nickname) {
		this.ID = iD;
		this.nickname = nickname;
	}

	public User(int iD, String nickname, boolean isOnline, boolean isPlaying) {
		ID = iD;
		this.nickname = nickname;
		this.isOnline = isOnline;
		this.isPlaying = isPlaying;
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public User(String username, String password, String nickname, String avatar) {
		this.username = username;
		this.password = password;
		this.nickname = nickname;
		this.avatar = avatar;
	}

	public User(int iD, String nickname, int numberOfGame, int numberOfDraw) {
		ID = iD;
		this.nickname = nickname;
		this.numberOfGame = numberOfGame;
		this.numberOfDraw = numberOfDraw;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public int getNumberOfGame() {
		return numberOfGame;
	}

	public void setNumberOfGame(int numberOfGame) {
		this.numberOfGame = numberOfGame;
	}

	public int getNumberOfWin() {
		return numberOfWin;
	}

	public void setNumberOfWin(int numberOfWin) {
		this.numberOfWin = numberOfWin;
	}

	public int getNumberOfDraw() {
		return numberOfDraw;
	}

	public void setNumberOfDraw(int numberOfDraw) {
		this.numberOfDraw = numberOfDraw;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	public boolean isPlaying() {
		return isPlaying;
	}

	public void setPlaying(boolean isPlaying) {
		this.isPlaying = isPlaying;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
    public boolean isIsOnline() {
        return isOnline;
    }
    

    public boolean isIsPlaying() {
        return isPlaying;
    }
	

}
