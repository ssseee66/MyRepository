package com.smart.domain;

public class User {
    private int user_id;
    private String userName;
    private String password;
    private String user_memo;
    private boolean user_type;
    private String userType;
    public void setUserType(boolean user_type) {
        if (user_type)
            userType = "管理员";
        else
            userType = "普通用户";
    }
    public String getUserType() {
        return userType;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_memo() {
        return user_memo;
    }

    public void setUser_memo(String user_memo) {
        this.user_memo = user_memo;
    }

    public boolean getUser_type() {
        return user_type;
    }

    public void setUser_type(boolean user_type) {
        this.user_type = user_type;
    }
}
