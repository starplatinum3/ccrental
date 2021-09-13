package cn.edu.zucc.personplan.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.common.LoginType;
import cn.edu.zucc.personplan.dao.ItDao;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.BeanStep;
import cn.edu.zucc.personplan.model.NetInfo;
import cn.edu.zucc.personplan.model.User;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BeanUtil;
import cn.edu.zucc.personplan.util.UiUtil;
import javafx.print.PageLayout;
import org.omg.CORBA.FREE_MEM;


public class FrmMain extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private JMenuBar menubar = new JMenuBar();

    private JMenu menu_plan = new JMenu("计划管理");
    private JMenu menu_step = new JMenu("步骤管理");
    private JMenu menu_static = new JMenu("查询统计");
    private JMenu menu_more = new JMenu("更多");

    private JMenuItem menuItem_AddPlan = new JMenuItem("新建计划");
    private JMenuItem menuItem_DeletePlan = new JMenuItem("删除计划");
    private JMenuItem menuItem_AddStep = new JMenuItem("添加步骤");
    private JMenuItem menuItem_DeleteStep = new JMenuItem("删除步骤");
    private JMenuItem menuItem_startStep = new JMenuItem("开始步骤");
    private JMenuItem menuItem_finishStep = new JMenuItem("结束步骤");
    private JMenuItem menuItem_moveUpStep = new JMenuItem("步骤上移");
    private JMenuItem menuItem_moveDownStep = new JMenuItem("步骤下移");

    private JMenuItem menuItem_modifyPwd = new JMenuItem("密码修改");

    private JMenuItem menuItem_static1 = new JMenuItem("统计1");


    //	优惠券由网点的工作人员添加，且该工作人员只能添加他所属网点的优惠券
    private Button btnAddCoupon = new Button("添加优惠券");
    private Button btnListNetInfo = new Button("展示网点信息");

    private FrmLogin dlgLogin = null;
    private JPanel statusBar = new JPanel();

    private Object tblPlanTitle[] = BeanPlan.tableTitles;
    private Object tblPlanData[][];
    DefaultTableModel tabPlanModel = new DefaultTableModel();
    private JTable dataTablePlan = new JTable(tabPlanModel);

    DefaultTableModel tabNetInfoModel = new DefaultTableModel();
    private JTable dataTableNetInfo = new JTable(tabNetInfoModel);

    DefaultTableModel tabCouponModel = new DefaultTableModel();
    private JTable dataTableCoupon = new JTable(tabCouponModel);


    private Object tblStepTitle[] = BeanStep.tblStepTitle;
    private Object tblStepData[][];
    DefaultTableModel tabStepModel = new DefaultTableModel();
    private JTable dataTableStep = new JTable(tabStepModel);

    private BeanPlan curPlan = null;
    List<BeanPlan> allPlan = null;
//    全是 get  就一个地方 set 了，那应该不需要 写到类里面吧

    List<BeanStep> planSteps = null;

    private void reloadPlanTable() {//这是测试数据，需要用实际数替换
        try {
            allPlan = PersonPlanUtil.planManager.loadAll();
//			System.out.println("allPlan");
//			System.out.println(allPlan);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblPlanData = new Object[allPlan.size()][BeanPlan.tableTitles.length];
        for (int i = 0; i < allPlan.size(); i++) {
            for (int j = 0; j < BeanPlan.tableTitles.length; j++)
                tblPlanData[i][j] = allPlan.get(i).getCell(j);
        }
//		tblPlanTitle obj 列表
        tabPlanModel.setDataVector(tblPlanData, tblPlanTitle);
        this.dataTablePlan.validate();
        this.dataTablePlan.repaint();
    }

    //	所有类型的值 转化为 string java
    public static List<String> getVals(Object object) throws IllegalAccessException {
//		Object st = new String();
        List<String> vals = new ArrayList<>();
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object o = field.get(object);

            vals.add(String.valueOf(o));
        }
        return vals;
    }


    /**
     * list 里面有 一堆 obj
     * todo
     *
     * @param dataList
     * @param titles
     * @param <T>
     */
//    static <T> void d(List<T> dataList, String[] titles) {
//        Object[][] tblData = new Object[dataList.size()][titles.length];
//        for (int i = 0; i < dataList.size(); i++) {
//
////        	一行数据
//            for (int j = 0; j < titles.length; j++)
//                tblData[i][j] = dataList.get(i).getCell(j);
//            //            里面是 str
//
//        }
////        要把 一个实体 变成 一行
////		tblPlanTitle obj 列表
//        tabPlanModel.setDataVector(tblPlanData, tblPlanTitle);
//    }

    /**
     * 要用吗
     *
     * @param itDao
     * @param <T>
     * @return
     */
    private static <T> Object[][] reloadTable(ItDao<T> itDao) {
        List<T> list = null;
        try {
//			一个list
            list = itDao.loadAll();
//			System.out.println("allPlan");
//			System.out.println(allPlan);
            Object[][] tblData = BeanUtil.getTblData(list, BeanPlan.tableTitles.length);
            return tblData;
            //		tblPlanTitle obj 列表
//            tabPlanModel.setDataVector(tblPlanData, tblPlanTitle);
//            this.dataTablePlan.validate();
//            this.dataTablePlan.repaint();
        } catch (IllegalAccessException | BaseException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "错误", JOptionPane.ERROR_MESSAGE);
//            return;
        }


//        tblPlanData = new Object[allPlan.size()][BeanPlan.tableTitles.length];
//        for (int i = 0; i < allPlan.size(); i++) {
//            for (int j = 0; j < BeanPlan.tableTitles.length; j++)
//                tblPlanData[i][j] = allPlan.get(i).getCell(j);
////            里面是 str
//        }
        return null;

    }
//    netInfoTitles=

    //    可以用
    private void reloadNetInfoTable() {//这是测试数据，需要用实际数替换

        try {
//			一个list
//            allPlan = PersonPlanUtil.planManager.loadAll();
            NetInfo netInfo = new NetInfo();
//            netInfo.setNetId(1);
            List<NetInfo> by = CcCarUtil.netInfoDao.getBy(netInfo);

            UiUtil.reloadTable(by, NetInfo.tableTitles,
                    tabNetInfoModel, this.dataTableNetInfo);


//            System.out.println("by");
//            System.out.println(by);
//            全部 拿出来
//			System.out.println("allPlan");
//			System.out.println(allPlan);


//            Object[][] tblData = BeanUtil.getTblData(by, NetInfo.tableTitles.length);
//            tabNetInfoModel.setDataVector(tblData, NetInfo.tableTitles);
//            this.dataTableNetInfo.validate();
//            this.dataTableNetInfo.repaint();

        } catch (BaseException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//            return;
        }

//        BeanUtil.getTblData(allPlan,BeanPlan.tableTitles.length);
//        tblPlanData = new Object[allPlan.size()][BeanPlan.tableTitles.length];
//        for (int i = 0; i < allPlan.size(); i++) {
//            for (int j = 0; j < BeanPlan.tableTitles.length; j++)
//                tblPlanData[i][j] = allPlan.get(i).getCell(j);
////            里面是 str
//        }
////		tblPlanTitle obj 列表
//        tabPlanModel.setDataVector(tblPlanData, tblPlanTitle);
//        this.dataTablePlan.validate();
//        this.dataTablePlan.repaint();
    }


//    备份
//
//    private void reloadNetInfoTable() {//这是测试数据，需要用实际数替换
//        try {
////			一个list
////            allPlan = PersonPlanUtil.planManager.loadAll();
//            NetInfo netInfo = new NetInfo();
////            netInfo.setNetId(1);
//            List<NetInfo> by = CcCarUtil.netInfoDao.getBy(netInfo);
////            System.out.println("by");
////            System.out.println(by);
////            全部 拿出来
////			System.out.println("allPlan");
////			System.out.println(allPlan);
//            Object[][] tblData = BeanUtil.getTblData(by, NetInfo.tableTitles.length);
//            tabNetInfoModel.setDataVector(tblData, NetInfo.tableTitles);
//            this.dataTableNetInfo.validate();
//            this.dataTableNetInfo.repaint();
//
//        } catch (BaseException | IllegalAccessException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
////            return;
//        }
//
////        BeanUtil.getTblData(allPlan,BeanPlan.tableTitles.length);
////        tblPlanData = new Object[allPlan.size()][BeanPlan.tableTitles.length];
////        for (int i = 0; i < allPlan.size(); i++) {
////            for (int j = 0; j < BeanPlan.tableTitles.length; j++)
////                tblPlanData[i][j] = allPlan.get(i).getCell(j);
//////            里面是 str
////        }
//////		tblPlanTitle obj 列表
////        tabPlanModel.setDataVector(tblPlanData, tblPlanTitle);
////        this.dataTablePlan.validate();
////        this.dataTablePlan.repaint();
//    }

//    以上备份

    private void reloadCouponTable() {//这是测试数据，需要用实际数替换
        try {
//			一个list
            allPlan = PersonPlanUtil.planManager.loadAll();
//			System.out.println("allPlan");
//			System.out.println(allPlan);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

//        BeanUtil.getTblData(allPlan,BeanPlan.tableTitles.length);
        tblPlanData = new Object[allPlan.size()][BeanPlan.tableTitles.length];
        for (int i = 0; i < allPlan.size(); i++) {
            for (int j = 0; j < BeanPlan.tableTitles.length; j++)
                tblPlanData[i][j] = allPlan.get(i).getCell(j);
//            里面是 str
        }
//        dataTableNetInfo

//		tblPlanTitle obj 列表
        tabPlanModel.setDataVector(tblPlanData, tblPlanTitle);
        this.dataTablePlan.validate();
        this.dataTablePlan.repaint();
    }


    private void reloadPlanStepTabel(int planIdx) {
        if (planIdx < 0) return;
        curPlan = allPlan.get(planIdx);
        System.out.println("planIdx");
        System.out.println(planIdx);
        System.out.println("curPlan");
        System.out.println(curPlan);
        try {
//			某个plan 的steps 
            planSteps = PersonPlanUtil.stepManager.loadSteps(curPlan);
            System.out.println("planSteps");
            System.out.println(planSteps);
        } catch (BaseException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tblStepData = new Object[planSteps.size()][BeanStep.tblStepTitle.length];
        for (int i = 0; i < planSteps.size(); i++) {
            for (int j = 0; j < BeanStep.tblStepTitle.length; j++)
                tblStepData[i][j] = planSteps.get(i).getCell(j);
        }

        tabStepModel.setDataVector(tblStepData, tblStepTitle);
        this.dataTableStep.validate();
        this.dataTableStep.repaint();
    }

    JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

//    JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

    //      密码不匹配也会进来  ，反正 演示的时候一定对的 先不管
    void showMain() throws BaseException, SQLException {
        String welStr = "";
//      如果没有 角色的话 就不展示了
        if (CcCarUtil.LoginAccountType == LoginType.ADMIN) {

            // System.out.println("succeed 1");
            welStr = "管理员 " + CcCarUtil.currentEmp.getEmpName() + "，你好，跑路有风险，删库需谨慎！";
            showFrmAdmin();

//                Bus.jButtonZYYStyle(adminUser);
//                Bus.jButtonZYYStyle(adminRider);
//                Bus.jButtonZYYStyle(adminMerchant);
//                Bus.jButtonZYYStyle(adminCoupon);
//                Bus.jButtonZYYStyle(adminFull_Reduction);
//                toolBar.add(adminUser);
//                toolBar.add(adminRider);
//                toolBar.add(adminMerchant);
//                toolBar.add(adminCoupon);
//                toolBar.add(adminFull_Reduction);
//                this.adminCoupon.setFont(new java.awt.Font("STKaiti Regular", java.awt.Font.BOLD, 15));
//                this.setSize(500, 155);

        } else if (CcCarUtil.LoginAccountType == LoginType.EMP) {
            welStr = "员工 " + CcCarUtil.currentEmp.getEmpName() + "，你好";
//                Bus.jButtonZYYStyle(riderHub);
//                Bus.jButtonZYYStyle(riderInfo);
//                Bus.jButtonZYYStyle(riderWork);
//                toolBar.add(riderHub);
//                toolBar.add(riderInfo);
//                toolBar.add(riderWork);
            FrmEmp frmEmp = new FrmEmp();

            frmEmp.setVisible(true);

        } else if (CcCarUtil.LoginAccountType == LoginType.USER) {

            welStr = "用户 " + User.currentLoginUser.getUserName() + "，你好，欢迎你的使用";


            FrmOrder frmOrder = new FrmOrder();

            frmOrder.setVisible(true);


            //            Bus.jButtonZYYStyle(userGo);
//            Bus.jButtonZYYStyle(userOrders);
//            Bus.jButtonZYYStyle(userInfo);
//            toolBar.add(userGo);
////          点单
//            toolBar.add(userOrders);
//            toolBar.add(userInfo);


        }

    }

    void loadMenu() {
        //菜单
        this.menu_plan.add(this.menuItem_AddPlan);
        this.menuItem_AddPlan.addActionListener(this);
        this.menu_plan.add(this.menuItem_DeletePlan);
        this.menuItem_DeletePlan.addActionListener(this);
        this.menu_step.add(this.menuItem_AddStep);
        this.menuItem_AddStep.addActionListener(this);
        this.menu_step.add(this.menuItem_DeleteStep);
        this.menuItem_DeleteStep.addActionListener(this);
        this.menu_step.add(this.menuItem_startStep);
        this.menuItem_startStep.addActionListener(this);
        this.menu_step.add(this.menuItem_finishStep);
        this.menuItem_finishStep.addActionListener(this);
        this.menu_step.add(this.menuItem_moveUpStep);
        this.menuItem_moveUpStep.addActionListener(this);
        this.menu_step.add(this.menuItem_moveDownStep);
        this.menuItem_moveDownStep.addActionListener(this);
        this.menu_static.add(this.menuItem_static1);
        this.menuItem_static1.addActionListener(this);
        this.menu_more.add(this.menuItem_modifyPwd);
        this.menuItem_modifyPwd.addActionListener(this);

        menubar.add(menu_plan);
        menubar.add(menu_step);
        menubar.add(menu_static);
        menubar.add(menu_more);
        this.setJMenuBar(menubar);
    }

    void showFrmAdmin() throws BaseException, SQLException {


        FrmAdmin frmAdmin = new FrmAdmin();
//        frmAdmin.setLocation(40, 800);
//        frmAdmin.setSize(1500, 100);
        frmAdmin.setVisible(true);

    }

    void showFrmOrder() throws BaseException, SQLException {

        FrmOrder frmOrder = new FrmOrder();
        frmOrder.setLocation(40, 800);
        frmOrder.setSize(1500, 100);
        frmOrder.setVisible(true);

    }


    public FrmMain() throws BaseException, SQLException {

        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.setTitle("个人计划管理系统");
//        try{
//            dlgLogin = new FrmLogin(this, "登陆", true);
//            dlgLogin.setVisible(true);
//        }
        dlgLogin = new FrmLogin(this, "登陆", true);
//        dlgLogin = new FrmLogin("登陆");
        dlgLogin.setVisible(true);

//        没有人登陆
        if (CcCarUtil.LoginAccountType == null) {
            return;
        }
        if(CcCarUtil.login==false){
            return;
        }
        loadMenu();
        showMain();

//        showFrmOrder();

        //菜单
//        this.menu_plan.add(this.menuItem_AddPlan);
//        this.menuItem_AddPlan.addActionListener(this);
//        this.menu_plan.add(this.menuItem_DeletePlan);
//        this.menuItem_DeletePlan.addActionListener(this);
//        this.menu_step.add(this.menuItem_AddStep);
//        this.menuItem_AddStep.addActionListener(this);
//        this.menu_step.add(this.menuItem_DeleteStep);
//        this.menuItem_DeleteStep.addActionListener(this);
//        this.menu_step.add(this.menuItem_startStep);
//        this.menuItem_startStep.addActionListener(this);
//        this.menu_step.add(this.menuItem_finishStep);
//        this.menuItem_finishStep.addActionListener(this);
//        this.menu_step.add(this.menuItem_moveUpStep);
//        this.menuItem_moveUpStep.addActionListener(this);
//        this.menu_step.add(this.menuItem_moveDownStep);
//        this.menuItem_moveDownStep.addActionListener(this);
//        this.menu_static.add(this.menuItem_static1);
//        this.menuItem_static1.addActionListener(this);
//        this.menu_more.add(this.menuItem_modifyPwd);
//        this.menuItem_modifyPwd.addActionListener(this);
//
//        menubar.add(menu_plan);
//        menubar.add(menu_step);
//        menubar.add(menu_static);
//        menubar.add(menu_more);
//        this.setJMenuBar(menubar);
        Container contentPane = this.getContentPane();

        contentPane.add(new JScrollPane(this.dataTablePlan), BorderLayout.WEST);
//        this.getContentPane().add(new JScrollPane(this.dataTablePlan), BorderLayout.WEST);
        this.dataTablePlan.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                int i = FrmMain.this.dataTablePlan.getSelectedRow();
                if (i < 0) {
                    return;
                }
//                加载 步骤那边的
//                PnlTables.this.tabNetInfoModel
//                可以拿到别人的 东西嘛
                FrmMain.this.reloadPlanStepTabel(i);
            }

        });

//		Container 是不是会填满的啊
//        contentPane.add(new JScrollPane(this.dataTableStep), BorderLayout.CENTER);
//        需要包装一个这个吗
        contentPane.add(new JScrollPane(this.dataTableNetInfo), BorderLayout.CENTER);
//		右边的中间
//		this.setSize(320, 180);
//		awt 按钮 设置了 size 没有用处啊
        this.btnAddCoupon.setSize(100, 100);
        this.btnAddCoupon.addActionListener(this);
        this.btnListNetInfo.addActionListener(this);
        this.buttonPanel.add(this.btnAddCoupon);
        this.buttonPanel.add(this.btnListNetInfo);
//		contentPane.add(this.buttonPanel);
//		this.add(this.buttonPanel,BorderLayout.NORTH);
        contentPane.add(this.buttonPanel, BorderLayout.NORTH);
//		contentPane.add(this.btnAddCoupon);
//        不load了
//        this.reloadPlanTable();
        //状态栏
//        reloadNetInfoTable();
//        这是 main 的 有关系吗
        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel("您好!");//修改成   您好！+登陆用户名
        statusBar.add(label);
//	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
        contentPane.add(statusBar, BorderLayout.SOUTH);
//		左边的 下面
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


//        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.menuItem_AddPlan) {
            FrmAddPlan dlg = new FrmAddPlan(this, "添加计划", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_DeletePlan) {
            if (this.curPlan == null) {
                JOptionPane.showMessageDialog(null, "请选择计划", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.planManager.deletePlan(this.curPlan);
                reloadPlanTable();
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (e.getSource() == this.menuItem_AddStep) {
            FrmAddStep dlg = new FrmAddStep(this, "添加步骤", true);
            dlg.plan = curPlan;
            dlg.setVisible(true);
        } else if (e.getSource() == this.menuItem_DeleteStep) {
            int i = FrmMain.this.dataTableStep.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.stepManager.deleteStep(this.planSteps.get(i));
                reloadPlanStepTabel(i);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (e.getSource() == this.menuItem_startStep) {
            int i = FrmMain.this.dataTableStep.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.stepManager.startStep(this.planSteps.get(i));
                reloadPlanStepTabel(i);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (e.getSource() == this.menuItem_finishStep) {
            int i = FrmMain.this.dataTableStep.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.stepManager.finishStep(this.planSteps.get(i));
                reloadPlanStepTabel(i);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (e.getSource() == this.menuItem_moveUpStep) {
            int i = FrmMain.this.dataTableStep.getSelectedRow();
//			这是 step 那边的行数
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
//				System.out.println("i");
//				System.out.println(i);
                PersonPlanUtil.stepManager.moveUp(this.planSteps.get(i));
//				System.out.println("move up 之后的 i");
                int selTblPlanRow = this.dataTablePlan.getSelectedRow();
//				System.out.println("selTblPlanRow");
//				System.out.println(selTblPlanRow);
//				reloadPlanStepTabel(i-1);
                reloadPlanStepTabel(selTblPlanRow);
//				reloadPlanTable();
//				修改了 可以刷新，只有 move up 这里
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (e.getSource() == this.menuItem_moveDownStep) {
            int i = FrmMain.this.dataTableStep.getSelectedRow();
            if (i < 0) {
                JOptionPane.showMessageDialog(null, "请选择步骤", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                PersonPlanUtil.stepManager.moveDown(this.planSteps.get(i));
                reloadPlanStepTabel(i);
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else if (e.getSource() == this.menuItem_static1) {

        } else if (e.getSource() == this.menuItem_modifyPwd) {
            FrmModifyPwd dlg = new FrmModifyPwd(this, "密码修改", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.btnListNetInfo) {
//            FrmModifyPwd dlg = new FrmModifyPwd(this, "密码修改", true);
//            dlg.setVisible(true);
            System.out.println("reloadNetInfoTable");
            reloadNetInfoTable();
        }
    }

    void startGui() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception e) {
            e.printStackTrace();

        }

//        SwingUtilities.invokeLater(FrmMain::new);
    }

    public static void main(String[] args) throws IllegalAccessException {
//        TblData<NetInfo> netInfoTblData = new TblData<>(NetInfo.tableTitles,
//                tblDataNetInfo, tabModelNetInfo, allNetInfo);


    }
}
