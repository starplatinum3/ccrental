package cn.edu.zucc.personplan.tvo;

import cn.edu.zucc.personplan.model.User;

import java.util.Date;

public class UserTvo implements Tvo{

    public static final String[] tableTitles = { "id", "名字", "性别",
            "密码","手机","邮箱","城市","注册时间"};
    public static final String[] sexLst = { "", "男", "女",};
    public static User currentLoginUser = null;

    private Integer userId;

    /**
     * user_name
     */
    private String userName;

    /**
     * sex
     */
    private String  sex;

    /**
     * password
     */
    private String password;

    /**
     * phone
     */
    private String phone;

    /**
     * mailbox
     */
    private String mailbox;

    /**
     * city
     */
    private String city;

    /**
     * reg_time
     */
    private Date regTime;

    public UserTvo(Integer userId, String userName, String sex, String password, String phone, String mailbox, String city, Date regTime) {
        this.userId = userId;
        this.userName = userName;
        this.sex = sex;
        this.password = password;
        this.phone = phone;
        this.mailbox = mailbox;
        this.city = city;
        this.regTime = regTime;
    }

    public static String[] getTableTitles() {
        return tableTitles;
    }

    public static String[] getSexLst() {
        return sexLst;
    }

    public static User getCurrentLoginUser() {
        return currentLoginUser;
    }

    public static void setCurrentLoginUser(User currentLoginUser) {
        UserTvo.currentLoginUser = currentLoginUser;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return "UserTvo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sex='" + sex + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", mailbox='" + mailbox + '\'' +
                ", city='" + city + '\'' +
                ", regTime=" + regTime +
                '}';
    }
}
