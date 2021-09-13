package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.User;
import cn.edu.zucc.personplan.tvo.UserTvo;
import cn.edu.zucc.personplan.util.*;
import org.hibernate.boot.model.relational.QualifiedSequenceName;

public class FrmRegister extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("注册");
    private JButton btnCancel = new JButton("取消");

    private JLabel labelUser = new JLabel("用户：");
    private JLabel labelUsername = new JLabel("用户名：");
    private JLabel labelPwd = new JLabel("请输入密码：");
    private JLabel labelPwd2 = new JLabel("请再次输入密码：");
    private JTextField edtUserId = new JTextField(20);
    private JTextField edtUsername = new JTextField(20);
    private JPasswordField edtPwd = new JPasswordField(20);
    private JPasswordField edtPwd2 = new JPasswordField(20);

    private JLabel lblSex = new JLabel("性别");
    private JLabel lblPhone = new JLabel("手机");
    private JLabel lblMailbox = new JLabel("邮箱");
    private JLabel lblCity = new JLabel("城市");

    private JTextField edtSex = new JTextField(20);
    private JTextField edtPhone = new JTextField(20);
    private JTextField edtMailbox = new JTextField(20);
    private JTextField edtCity = new JTextField(20);

    public FrmRegister(Dialog f, String s, boolean b) {
//	public FrmRegister(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
//        workPane.add(labelUser);
//        workPane.add(edtUserId);

        workPane.add(labelUsername);
        workPane.add(edtUsername);

        workPane.add(labelPwd);
        workPane.add(edtPwd);
        workPane.add(labelPwd2);
        workPane.add(edtPwd2);

        workPane.add(lblSex);
        workPane.add(edtSex);
        workPane.add(lblCity);
        workPane.add(edtCity);

        workPane.add(lblMailbox);
        workPane.add(edtMailbox);
        workPane.add(lblPhone);
        workPane.add(edtPhone);


        this.getContentPane().add(workPane, BorderLayout.CENTER);
//        this.setSize(200, 800);
//        this.setSize(220, 800);
//        this.setSize(250, 800);
        this.setSize(270, 800);
        this.btnCancel.addActionListener(this);
        this.btnOk.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnCancel)
            this.setVisible(false);
        else if (e.getSource() == this.btnOk) {
//            String userid = this.edtUserId.getText();
            String usernameText = this.edtUsername.getText();
            String pwd1 = new String(this.edtPwd.getPassword());
            String pwd2 = new String(this.edtPwd2.getPassword());
            try {
//				BeanUser user=PersonPlanUtil.userManager.reg(userid,pwd1,pwd2);
//                if (userid == null || "".equals(userid))
                if (usernameText == null || "".equals(usernameText))
                    throw new BusinessException("账号不能为空");
//				if(!pwd1.equals(pwd2)){
//					throw new BusinessException("两次密码不同");
//				}
                if (pwd1 == null || "".equals(pwd1))
                    throw new BusinessException("密码不能为空");
                if (!pwd1.equals(pwd2))
                    throw new BusinessException("两次输入的密码密码不一样");

                User user1 = new User();
                user1.setUserName(usernameText);

                Connection conn = null;
                try {
                    conn = DBUtil.getConnection();

                    List<User> by = JdbcUtil.getBy(conn, user1);
                    if (by.size() >= 1) {
                        throw new BusinessException("用户已存在");
                    }

                    User user2 = new User();
                    user2.setUserName(usernameText);
                    user2.setPassword(pwd1);
                    String cityText = edtCity.getText();
                    String mailboxText = edtMailbox.getText();
                    String phoneText = edtPhone.getText();
                    String sexText = edtSex.getText();
//                    String cityText1 = edtCity.getText();
                    if (StringUtils.isNone(mailboxText) || StringUtils.isEmail(mailboxText)) {
                        user2.setMailbox(mailboxText);
                    }
                    if (StringUtils.isNone(phoneText) || StringUtils.isPhone(phoneText)) {
                        user2.setPhone(phoneText);
                    }

                    int index = ListUtil.index(UserTvo.sexLst, sexText);
                    if (index == -1) {
                        user2.setSex(0);
                    } else {
                        user2.setSex(index);
                    }

                    user2.setRegTime(new Date());
                    user2.setCity(cityText);


                    JdbcUtil.insert(conn, user2);

//                    String sql = "select user_id from tbl_user where user_id=?";
//                    PreparedStatement pst = conn.prepareStatement(sql);
//                    pst.setString(1, userid);
////			pst.setString(2, pwd);
//                    ResultSet rs = pst.executeQuery();
//                    if (rs.next()) {
//                        rs.close();
//                        pst.close();
//                        throw new BusinessException("用户已存在");
//
//                    }
//                    rs.close();
//                    pst.close();
//                    sql = "insert into tbl_user(user_id,user_pwd,register_time) values(?,?,?)";
//                    pst = conn.prepareStatement(sql);
//                    pst.setString(1, userid);
//                    pst.setString(2, pwd);
//                    pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
//                    pst.execute();
//                    BeanUser user = new BeanUser();
//                    user.setUser_id(userid);
//                    user.setRegister_time(new Date());
//                    return user;
                } catch (SQLException ex) {
                    // TODO: handle exception
                    throw new DbException(ex);
                } finally {
                    DBUtil.closeConnection(conn);

                }
                UiUtil.showInfo("注册成功  用户名: " + usernameText);
                this.setVisible(false);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(),
                        "错误", JOptionPane.ERROR_MESSAGE);

            }

        }


    }


}
