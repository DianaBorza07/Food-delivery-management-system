package bussinesLayer;

import java.io.Serializable;

public  class User implements Serializable {
    private int userID;
    private int userType; // 1-Administrator 2-Client 3-Employee
    private String username;
    private String password;

    public User(int userID,int userType, String username, String password) {
        this.userID = userID;
        this.userType = userType;
        this.username = username;
        this.password = password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
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
}
