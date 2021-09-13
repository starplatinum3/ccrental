package cn.edu.zucc.personplan.test;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.TblOrder;
import cn.edu.zucc.personplan.ui.TblData;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import java.sql.SQLException;

public class Test2 {
    private JPanel panel1;
    private JTable table1;

    //    private JTable table2;
//    TblData<TblOrder> tblOrder = new TblData<>(TblOrder.tableTitles,
//            CcCarUtil.tblOrderDao);
    private TblData<TblOrder> tblOrder;
    public Test2() throws BaseException, SQLException {
//        table1=tblOrder;
//        table1=tblOrder;
//        tblOrder=new
        tblOrder.reloadTable();


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public void setData(BeanPlan data) {
    }

    public void getData(BeanPlan data) {
    }

    public boolean isModified(BeanPlan data) {
        return false;
    }

    public static void main(String[] args) throws BaseException, SQLException {
        JFrame frame = new JFrame("Test2");
        frame.setContentPane(new Test2().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    //    public static void main(String[] args) throws BaseException, SQLException {
//
////        this.table1=this.tblOrder;
//        JFrame frame = new JFrame("Test2");
//        frame.setContentPane(new Test2().panel1);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.pack();
//        frame.setVisible(true);
//
////        frame.   getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, 370,
////                250));
//    }
}
