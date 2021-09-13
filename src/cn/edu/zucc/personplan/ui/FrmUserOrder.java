package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.TblOrder;
import cn.edu.zucc.personplan.model.User;
import cn.edu.zucc.personplan.svo.OrderSvo;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.JdbcUtil;
import cn.edu.zucc.personplan.util.UiUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class FrmUserOrder extends JFrame implements ActionListener {

    // TODO: 2021/9/8  FrmUserOrder

    //    TblDataDo<OrderSvo> tblOrderSvo;
    //    TblData<User> tblData;
    JButton btnUpdate = new JButton("修改信息");

    JLabel lblOrderNumTitle = new JLabel("订单数");
    JLabel lblMoneyTitle = new JLabel("总金额");

    JLabel lblOrderNum = new JLabel("");
    JLabel lblMoney = new JLabel("");
    TblDataDo<TblOrder> tblOrder = new TblDataDo<>(TblOrder.tableTitles,
            CcCarUtil.tblOrderDao);

    public FrmUserOrder() throws BaseException, SQLException {
//        super(title);
        setTitle("用户信息");
//        this.tblOrder = tblOrder;

//        JFrame frame = new JFrame("用户信息");
        Container contentPane = getContentPane();
        contentPane.add(new JScrollPane(this.tblOrder), BorderLayout.CENTER);
//        , BorderLayout.SOUTH
        JPanel pnlBtns = new JPanel();
        pnlBtns.add(btnStatusWrong);
//        pnlBtns.add(lblOrderNumTitle);
//        pnlBtns.add(lblMoneyTitle);
//        pnlBtns.add(lblOrderNum);
//        pnlBtns.add(lblMoney);
//        toolBarPanel.add(jButton);
        btnUpdate.addActionListener(this);
        contentPane.add(pnlBtns, BorderLayout.SOUTH);
        setSize(1400, 500);
        setLocation(20, 10);//在屏幕中设置显示的位置
        TblOrder tblOrder = new TblOrder();
        tblOrder.setUserId(User.currentLoginUser.getUserId());
        this.tblOrder.reloadDataAndRepaint(true,tblOrder);

//        frame.getContentPane().add(new JScrollPane(tblUser));
//        frame.setSize(1400, 500);
//        frame.setLocation(20, 10);//在屏幕中设置显示的位置

//        tblUser.reloadTable();
//        tblUser.reloadTable();
//        x+=410;
//        frame.setVisible(true);


//        Container contentPane = getContentPane();
//        getContentPane().add(new JScrollPane(tblOrder),BorderLayout.CENTER);
//        setSize(1400, 500);
//        setLocation(20, 10);//在屏幕中设置显示的位置
//
//        JPanel pnlBtns=new JPanel();
//        pnlBtns.add(btnStatusWrong);
////        toolBarPanel.add(jButton);
//        btnStatusWrong.addActionListener(this);
    }



    public FrmUserOrder(String title, TblData<TblOrder> tblOrder) throws BaseException, SQLException {
        super(title);
//        this.tblOrder = tblOrder;
//        JFrame frame = new JFrame("用户信息");
        Container contentPane = getContentPane();
        contentPane.add(new JScrollPane(this.tblOrder), BorderLayout.CENTER);
//        , BorderLayout.SOUTH
        JPanel pnlBtns = new JPanel();
        pnlBtns.add(btnStatusWrong);
//        pnlBtns.add(lblOrderNumTitle);
//        pnlBtns.add(lblMoneyTitle);
//        pnlBtns.add(lblOrderNum);
//        pnlBtns.add(lblMoney);
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


//        Container contentPane = getContentPane();
//        getContentPane().add(new JScrollPane(tblOrder),BorderLayout.CENTER);
//        setSize(1400, 500);
//        setLocation(20, 10);//在屏幕中设置显示的位置
//
//        JPanel pnlBtns=new JPanel();
//        pnlBtns.add(btnStatusWrong);
////        toolBarPanel.add(jButton);
//        btnStatusWrong.addActionListener(this);
    }

    //    客户可以修改自己的信息；
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnStatusWrong) {
            btnStatusWrongEvent();
        }
    }

//    用户可以选购车辆并生成订单；客户可以修改自己的信息；
//    客户可以根据车辆、网点等条件，查询到相关信息；客户可以查看订单情况，并确认其状态。

    void btnUpdateEvent() {
//        tblData.updateBatch(TblData<T> dataTbl, ItDao<T> dao);


//        try {
//            UiUtil.updateBatch(this.tblOrderSvo, this.tblOrderSvo.dao);
//        } catch (SQLException | BaseException ex) {
//            ex.printStackTrace();
//            UiUtil.showError(ex.getMessage());
//        }
    }


    JButton btnStatusWrong = new JButton("状态有误");


    void btnStatusWrongEvent() {
//        System.out.println("发送错误信息");
//        UiUtil.showError();
        JOptionPane.showMessageDialog(null, "发送错误信息",
                "信息", JOptionPane.ERROR_MESSAGE);
    }

//    TblData<TblOrder> tblOrder;

    //            = new TblData<>(TblOrder.tableTitles,
//            CcCarUtil.tblOrderDao);
    void setUp(TblData<TblOrder> tblOrder) throws DbException, SQLException {
        Container contentPane = getContentPane();
        getContentPane().add(new JScrollPane(tblOrder), BorderLayout.CENTER);
        setSize(1400, 500);
        setLocation(20, 10);//在屏幕中设置显示的位置

        JPanel pnlBtns = new JPanel();
        pnlBtns.add(btnStatusWrong);
//        toolBarPanel.add(jButton);
        btnStatusWrong.addActionListener(this);
//        contentPane.add(new JScrollPane(tblData), BorderLayout.CENTER);

//        toolBarPanel.add(jButton);
//        jButton.addActionListener(this);

//        this.tblData = tblData;
//        JFrame frame = new JFrame("用户信息");
//        Container contentPane = getContentPane();
//        contentPane.add(new JScrollPane(tblData), BorderLayout.CENTER);
//        , BorderLayout.SOUTH
//        JPanel pnlBtns=new JPanel();
//        pnlBtns.add(btnUpdate);
//        toolBarPanel.add(jButton);
//        btnUpdate.addActionListener(this);
//        contentPane.add(pnlBtns, BorderLayout.SOUTH);
//        setSize(1400, 500);
//        setLocation(20, 10);//在屏幕中设置显示的位置

//        TblOrder tblOrderEntity = new TblOrder();
//        tblOrderEntity.setUserId(User.currentLoginUser.getUserId());
//        this.tblOrder.all = JdbcUtil.getBy(tblOrderEntity);
//        tblOrder.reloadTableByThisList();
    }

//    public FrmUserOrder(String title,TblData<TblOrder> tblOrder)
//            throws HeadlessException, DbException, SQLException {
//        super(title);
//        this.tblOrder = tblOrder;
//        setUp(tblOrder);
//    }
}
