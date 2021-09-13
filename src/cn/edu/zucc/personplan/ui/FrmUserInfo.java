package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.model.CanSetVals;
import cn.edu.zucc.personplan.model.User;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.UiUtil;
import com.mchange.v1.util.UIterator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

//可以用
public class FrmUserInfo extends JFrame implements ActionListener {

    TblData<User> tblData;
    JButton btnUpdate = new JButton("修改信息");


    public FrmUserInfo(String title, TblData<User> tblData) {
        super(title);
        this.tblData = tblData;
//        JFrame frame = new JFrame("用户信息");
        Container contentPane = getContentPane();
        contentPane.add(new JScrollPane(tblData), BorderLayout.CENTER);
//        , BorderLayout.SOUTH
        JPanel pnlBtns = new JPanel();
        pnlBtns.add(btnUpdate);
//        toolBarPanel.add(jButton);
        btnUpdate.addActionListener(this);
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


        try {
            UiUtil.updateBatch(tblData, tblData.dao);
            UiUtil.showInfo("成功更新");
        } catch (SQLException | BaseException ex) {
            ex.printStackTrace();
            UiUtil.showError(ex.getMessage());
        }
    }
}
