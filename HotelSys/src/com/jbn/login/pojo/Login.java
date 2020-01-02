package com.jbn.login.pojo;

/**
 * Login对象对应数据库中的login表，主键为authId，设置为自动增加。
 */

public class Login {
    private int loginId;            //用户ID
    //用户ID一般是自动生成的
    private String loginName;       //用户登录名

    private String loginPwd;        //用户密码

    private String loginNickName;   //用户昵称

    private int loginAdmin;         //用户权限

    public Login() {
        super();
        loginAdmin = -1;
    }
    public Login(String loginName){
        this.loginName=loginName;
    }
    public Login(String loginName,String loginPwd) {
        this.loginName=loginName;
        this.loginPwd=loginPwd;
    }
    public Login(String loginName, String loginPwd, String loginNickName) {
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.loginNickName = loginNickName;
    }

    public Login(int loginId, String loginName, String loginPwd, String loginNickName, int loginAdmin) {
        this.loginId = loginId;
        this.loginName = loginName;
        this.loginPwd = loginPwd;
        this.loginNickName = loginNickName;
        this.loginAdmin = loginAdmin;
    }
    @Override
    public String toString() {
        return "Login{" +
                "loginId=" + loginId +
                ", loginName='" + loginName + '\'' +
                ", loginPwd='" + loginPwd + '\'' +
                ", loginNickName='" + loginNickName + '\'' +
                ", loginAdmin=" + loginAdmin +
                '}';
    }

    public int getLoginId() {
        return loginId;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public String getLoginNickName() {
        return loginNickName;
    }

    public int getLoginAdmin() {
        return loginAdmin;
    }

    public void setLoginId(int loginId) {
        this.loginId = loginId;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public void setLoginNickName(String loginNickName) {
        this.loginNickName = loginNickName;
    }

    public void setLoginAdmin(int loginAdmin) {
        this.loginAdmin = loginAdmin;
    }
}
