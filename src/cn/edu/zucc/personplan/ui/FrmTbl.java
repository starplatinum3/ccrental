package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.model.User;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.UiUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class FrmTbl<T> extends JFrame implements ActionListener {

    TblData<T> tblData;
    JButton btnUpdate = new JButton("修改信息");


//    传入的btn 不是 这里的 好像没用
    public FrmTbl(String title, TblData<T> tblData, java.util.List<JButton> buttonList) {
        super(title);
//        Connection connection;
//        connection.setAutoCommit(false);
        this.tblData = tblData;
//        JFrame frame = new JFrame("用户信息");
        Container contentPane = getContentPane();
        contentPane.add(new JScrollPane(tblData), BorderLayout.CENTER);
//        , BorderLayout.SOUTH
        JPanel pnlBtns = new JPanel();
        for (JButton jButton : buttonList) {
            pnlBtns.add(jButton);
            jButton.addActionListener(this);
        }
//        pnlBtns.add(btnUpdate);
////        toolBarPanel.add(jButton);
//        btnUpdate.addActionListener(this);
        contentPane.add(pnlBtns, BorderLayout.SOUTH);
        setSize(1400, 500);
        setLocation(20, 10);//在屏幕中设置显示的位置
//        frame.getContentPane().add(new JScrollPane(tblUser));
//        frame.setSize(1400, 500);
//        frame.setLocation(20, 10);//在屏幕中设置显示的位置

//        tblUser.reloadTable();
//        tblUser.reloadTable();
//        x+=410;
//        frame.setVisible(true);
    }

    //    客户可以修改自己的信息；
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnUpdate) {
            btnUpdateEvent();

        }
    }

//    用户可以选购车辆并生成订单；客户可以修改自己的信息；
//    客户可以根据车辆、网点等条件，查询到相关信息；客户可以查看订单情况，并确认其状态。

    void btnUpdateEvent() {
//        tblData.updateBatch(TblData<T> dataTbl, ItDao<T> dao);


//        try {
//            UiUtil.updateBatch(tblData, tblData.dao);
//        } catch (SQLException | BaseException ex) {
//            ex.printStackTrace();
//            UiUtil.showError(ex.getMessage());
//        }
    }
}
