package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.common.LoginType;
import cn.edu.zucc.personplan.model.User;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.UiUtil;

//JFrame
//JDialog 会消失
//不是 JDialog 就不会接下去
public class FrmLogin extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnLogin = new JButton("登陆");
    private JButton btnCancel = new JButton("退出");
    private JButton btnRegister = new JButton("注册");

    private JLabel labelUser = new JLabel("用户：");
    private JLabel labelPwd = new JLabel("密码：");
    private JTextField edtUserId = new JTextField(20);
    private JPasswordField edtPwd = new JPasswordField(20);
//awt 填入 时间
//    JTime

    // ui 单选项
    private JPanel jrPane = new JPanel();
    private JRadioButton jrUser = new JRadioButton("用户", true);
    private JRadioButton jrEmp = new JRadioButton("员工");
    private JRadioButton jrRoot = new JRadioButton("管理员");
    private ButtonGroup btnG = new ButtonGroup();
    // btnG.add(jrUser); btnG.add(jrRoot); // 绑定n个单选为n选一


    public FrmLogin(Frame f, String s, boolean b) {
//    public FrmLogin( String title) {
        super(f, s, b);
//        super(title);
//        JDialog
//        JFrame
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(this.btnRegister);
        toolBar.add(btnLogin);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelUser);
        workPane.add(edtUserId);
        workPane.add(labelPwd);
        workPane.add(edtPwd);

        this.getContentPane().add(workPane, BorderLayout.CENTER);


//        btnLogin.addActionListener(this);
//        btnCancel.addActionListener(this);
//        this.btnRegister.addActionListener(this);
//        不能监听两次
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
//		this.addWindowListener(( WindowAdapter) windowClosing(e)->{
//			System.exit(0);
//
//		});
//		没有这个写法

        // 单选框 中 加入
        jrPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        jrPane.add(jrUser);
        jrPane.add(jrEmp);
        jrPane.add(jrRoot);
        // 绑定 n 个单选按钮为同一个 ButtonGroup 则 n个单选项中同时最多只有一个会被选中
        btnG.add(jrUser);
        btnG.add(jrEmp);
        btnG.add(jrRoot);
//		没有显示

        this.workPane.add(jrPane, BorderLayout.SOUTH);


//		居中写在 后面 也没有用处
//			this.setSize(320, 140);
        this.setSize(312, 175);
//			因为 太短了 没有显示。。

        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);
//		括号了也没用

        // 事件监听管理
        // 按钮事件
        this.btnLogin.addActionListener(this);
        this.btnCancel.addActionListener(this);
        this.btnRegister.addActionListener(this);
        this.jrUser.addActionListener(this);
        this.jrEmp.addActionListener(this);
        this.jrRoot.addActionListener(this);

        this.validate();

    }

    void login() throws BaseException {
//        String userid = this.edtUserId.getText();
        String username = this.edtUserId.getText();
        String pwd = new String(this.edtPwd.getPassword());
        if (CcCarUtil.LoginAccountType.equals(LoginType.USER)) {

            User.currentLoginUser = CcCarUtil.userDaoCustom.login(username, pwd);
//            try {
////				BeanUser.currentLoginUser= PersonPlanUtil.userManager.login(userid, pwd);
//                User.currentLoginUser = CcCarUtil.userDaoCustom.login(username, pwd);
////				BeanUser.currentLoginUser= PersonPlanUtil.userManager.login(userid, pwd);
//                CcCarUtil.login=true;
//            } catch (BaseException e1) {
//                JOptionPane.showMessageDialog(null, e1.getMessage(),
//                        "错误", JOptionPane.ERROR_MESSAGE);
////                return;
//            }
        } else if (CcCarUtil.LoginAccountType.equals(LoginType.ADMIN)) {
            CcCarUtil.currentEmp = CcCarUtil.empDaoC.login(username,
                    pwd, CcCarUtil.LoginAccountType);
//            try {
////				BeanUser.currentLoginUser= PersonPlanUtil.userManager.login(userid, pwd);
////                User.currentLoginUser = CcCarUtil.userDao.login(username, pwd);
//                CcCarUtil.currentEmp = CcCarUtil.empDaoC.login(username,
//                        pwd,CcCarUtil.LoginAccountType );
////				BeanUser.currentLoginUser= PersonPlanUtil.userManager.login(userid, pwd);
//            } catch (BaseException e1) {
//                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
////                return;
//            }
        } else if (CcCarUtil.LoginAccountType.equals(LoginType.EMP)) {
            CcCarUtil.currentEmp = CcCarUtil.empDaoC.login(username,
                    pwd, CcCarUtil.LoginAccountType);
//            try {
//
////				BeanUser.currentLoginUser= PersonPlanUtil.userManager.login(userid, pwd);
//                CcCarUtil.currentEmp = CcCarUtil.empDaoC.login(username,
//                        pwd,CcCarUtil.LoginAccountType );
////                他们之间的工作没有 相交  所以两个dao 可以吧
////                这个接口 是不行的
////				BeanUser.currentLoginUser= PersonPlanUtil.userManager.login(userid, pwd);
//            } catch (BaseException e1) {
//                JOptionPane.showMessageDialog(null, e1.getMessage(),
//                        "错误", JOptionPane.ERROR_MESSAGE);
////                return;
//            }
        }

    }

    /**
     * 不属于任何网点的工作人员被认为是系统管理员
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnLogin) {
            try {
                login();
                CcCarUtil.login = true;
                this.setVisible(false);
            } catch (BaseException ex) {
                ex.printStackTrace();
                UiUtil.showError("登录失败  " + ex.getMessage());
//                this.setVisible(true);
            }


        } else if (e.getSource() == this.btnCancel) {
            System.exit(0);
        } else if (e.getSource() == this.btnRegister) {
            FrmRegister dlg = new FrmRegister(this, "注册", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.jrUser) {
//			CcCarUtil.LoginAccountType = "用户";
            CcCarUtil.LoginAccountType = LoginType.USER;
        } else if (e.getSource() == this.jrEmp) {
            CcCarUtil.LoginAccountType = LoginType.EMP;

        } else if (e.getSource() == this.jrRoot) {
//			CcCarUtil.LoginAccountType = "管理员";
//            jrRoot.isSelected()
            CcCarUtil.LoginAccountType = LoginType.ADMIN;

        }
    }

}
