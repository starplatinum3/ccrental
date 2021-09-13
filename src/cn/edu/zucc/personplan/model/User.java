package cn.edu.zucc.personplan.model;

import cn.edu.zucc.personplan.tvo.Tvo;
import cn.edu.zucc.personplan.tvo.UserTvo;
import cn.edu.zucc.personplan.util.ListUtil;
import cn.edu.zucc.personplan.util.TimeUtil;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author zhengkai.blog.csdn.net
 * @description user
 * @date 2021-09-02
 */
public class User implements Serializable, CanSetVals, Do {

    public static final String[] tableTitles = {"id", "名字", "性别",
            "密码", "手机", "邮箱", "城市", "注册时间"};

    public static User currentLoginUser = null;
    private static final long serialVersionUID = 1L;

    private Integer userId;

    /**
     * user_name
     */
    private String userName;

    /**
     * sex
     */
    private Integer sex;

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


    public User() {
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
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

    public User(Integer userId, String userName, Integer sex, String password, String phone, String mailbox, String city, Date regTime) {
        this.userId = userId;
        this.userName = userName;
        this.sex = sex;
        this.password = password;
        this.phone = phone;
        this.mailbox = mailbox;
        this.city = city;
        this.regTime = regTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", sex=" + sex +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", mailbox='" + mailbox + '\'' +
                ", city='" + city + '\'' +
                ", regTime=" + regTime +
                '}';
    }

    @Override
    public void setVals(List<String> rowVals) {
//        this.carId = Integer.parseInt(rowVals.get(0));
//        this.netId = Integer.parseInt(rowVals.get(1));
//        this.typeId = Integer.parseInt(rowVals.get(2));
//        this.license = rowVals.get(3);
//        this.carStatus = Integer.parseInt(rowVals.get(4));

        this.userId = Integer.parseInt(rowVals.get(0));
        this.userName = rowVals.get(1);
//        this.sex = Integer.parseInt(rowVals.get(2));
        this.sex =   ListUtil.index(UserTvo.sexLst,rowVals.get(2));
        this.password = rowVals.get(3);
        this.phone = rowVals.get(4);
        this.mailbox = rowVals.get(5);
        this.city = rowVals.get(6);
        this.regTime = TimeUtil.stringtoDate(rowVals.get(7), TimeUtil.fmtYmd);

    }

    @Override
    public Tvo toTvo() {
        return new UserTvo(
                this.userId,
                this.userName,
                UserTvo.sexLst[this.sex],
                this.password,
                this.phone,
                this.mailbox,
                this.city,
                this.regTime
        );
    }
}