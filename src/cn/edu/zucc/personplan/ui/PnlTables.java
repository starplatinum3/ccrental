package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.dao.ItDao;
import cn.edu.zucc.personplan.model.NetInfo;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BeanUtil;
import cn.edu.zucc.personplan.util.UiUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.List;

public class PnlTables extends JPanel implements ActionListener {
    DefaultTableModel tabNetInfoModel = new DefaultTableModel();
    private JTable dataTableNetInfo = new JTable(tabNetInfoModel);

    DefaultTableModel tabCouponModel = new DefaultTableModel();
    private JTable dataTableCoupon = new JTable(tabCouponModel);

//    每个 table的数据 都要 点了之后 获得, 这样数据要在最大的 frm里
//    引用 反正都可以的吧. 点了 按钮,就 set,需要传入一个对象,难道又要泛型
//    set 的那个 函数没有 啊,泛型也没用.这样只能每个都写?
    List<PnlTableBtn> pnlTableBtns;

//    需要多个  tbl btn
    PnlTables(){
        super(new GridLayout(1, 3));

//        new JScrollPane(this.dataTableNetInfo)
//        需要 这个才有 title
//        而且只有最后一个更新了 其他都是空的
//        this.add( new JScrollPane(dataTableNetInfo));
//        this.add( new JScrollPane(dataTableNetInfo));
//        JScrollPane jScrollPane = new JScrollPane(dataTableNetInfo);

        JButton add = new JButton("add");
        PnlTableBtn pnlTableBtn = new PnlTableBtn(dataTableNetInfo, add);

//        jScrollPane.add(dataTableNetInfo);
//        不能是add
//        jScrollPane.add(new JButton("add"));
//        添加一个 按钮是有问题的
//        this.add( new JScrollPane(dataTableNetInfo));
        this.add(pnlTableBtn);
//        this.add(jScrollPane);
        this.add( new JScrollPane(dataTableCoupon));
//        数据 存在 哪里 this?
//        数据呢 需要 reload嘛
//        this.add( new JButton("add"));
//        this.add(dataTableNetInfo);
//        this.add(dataTableNetInfo);
//        this.add(dataTableCoupon);
        reloadNetInfoTable();
        this.setVisible(true);
    }

    public PnlTables(List<PnlTableBtn> pnlTableBtns) {
        this.pnlTableBtns = pnlTableBtns;
//        super(new GridLayout(3,1));
        this.setLayout(new GridLayout(pnlTableBtns.size(),1));
        pnlTableBtns.forEach(this::add);
//        this.add(pnlTableBtn);
    }

    @Deprecated
    //    可以用
    private void reloadNetInfoTable()  {//这是测试数据，需要用实际数替换


        //            废弃了  2021年9月3日09:29:07
//            UiUtil.reloadTable( NetInfo.tableTitles,
//                    tabNetInfoModel ,this.dataTableNetInfo,
//                   CcCarUtil.netInfoDao,new NetInfo(),);


        //			一个list
//            allPlan = PersonPlanUtil.planManager.loadAll();


//            NetInfo netInfo = new NetInfo();
////            netInfo.setNetId(1);
//            List<NetInfo> by = CcCarUtil.netInfoDao.getBy(netInfo);
//          UiUtil. reloadTable(by,  NetInfo.tableTitles,
//                    tabNetInfoModel ,this.dataTableNetInfo) ;


//            System.out.println("by");
//            System.out.println(by);
//            全部 拿出来
//			System.out.println("allPlan");
//			System.out.println(allPlan);


//            Object[][] tblData = BeanUtil.getTblData(by, NetInfo.tableTitles.length);
//            tabNetInfoModel.setDataVector(tblData, NetInfo.tableTitles);
//            this.dataTableNetInfo.validate();
//            this.dataTableNetInfo.repaint();

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
//    private void reloadNetInfoTable() {//这是测试数据，需要用实际数替换
//
//        try {
////			一个list
////            allPlan = PersonPlanUtil.planManager.loadAll();
//            NetInfo netInfo = new NetInfo();
////            netInfo.setNetId(1);
//            List<NetInfo> by = CcCarUtil.netInfoDao.getBy(netInfo);
//
//            reloadTable(by,  NetInfo.tableTitles,
//                    tabNetInfoModel ,this.dataTableNetInfo) ;
//
//
////            System.out.println("by");
////            System.out.println(by);
////            全部 拿出来
////			System.out.println("allPlan");
////			System.out.println(allPlan);
//
//
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
//以上是备份



//    public static<T> void reloadTable(T t, ) {//这是测试数据，需要用实际数替换
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

// awt 按一下 ，按钮 选择要添加的 东西，我保存在哪里，列表？
//    就设置一个类 保存好了。 就是一个 网点 需要 id 什么的，每次按一个按钮，他的id什么的就可以获得，然后set
//    全部set玩了 可以插入
    public static<T> void reloadTable(List<T> list,String [] tableTitles,
                                      DefaultTableModel model, JTable table) {
        //这是测试数据，需要用实际数替换
        try {
//			一个list
//            allPlan = PersonPlanUtil.planManager.loadAll();
//            NetInfo netInfo = new NetInfo();
////            netInfo.setNetId(1);
//            List<NetInfo> by = CcCarUtil.netInfoDao.getBy(netInfo);
//            System.out.println("by");
//            System.out.println(by);
//            全部 拿出来
//			System.out.println("allPlan");
//			System.out.println(allPlan);
//            Object[][] tblData = BeanUtil.getTblData(list, NetInfo.tableTitles.length);
            Object[][] tblData = BeanUtil.getTblData(list, tableTitles.length);
            model.setDataVector(tblData, tableTitles);
//            System.out.println("tableTitles");
//            System.out.println(Arrays.toString(tableTitles));
            table.validate();
            table.repaint();

        } catch (IllegalAccessException e) {
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


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    void test3(){
        
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Demo buttons");
        frame.add(new PnlTables());
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
//        frame.dis
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//
//        SwingUtilities.invokeLater(PnlTables::new);
    }
}
