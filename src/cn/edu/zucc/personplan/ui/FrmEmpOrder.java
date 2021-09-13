package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.Allocation;
import cn.edu.zucc.personplan.model.CarInfo;
import cn.edu.zucc.personplan.model.Emp;
import cn.edu.zucc.personplan.model.TblOrder;
import cn.edu.zucc.personplan.svo.OrderSvo;
import cn.edu.zucc.personplan.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrmEmpOrder extends JFrame implements ActionListener {

    public FrmEmpOrder() {

    }

    public static void main(String[] args) {
        FrmEmpOrder frmOrderAdmin = new FrmEmpOrder();
    }

    TblDataDo<OrderSvo> tblOrderSvo;
    //    TblData<User> tblData;
    JButton btnUpdate = new JButton("修改信息");
    JButton btnSearch = new JButton("搜索");
    JButton btnGetCarComplete = new JButton("已提车");
    JButton btnRetCarComplete = new JButton("已还车");
    JButton btnCancelOrder = new JButton("取消订单");
    JButton btnDel = new JButton("删除订单");
    // TODO: 2021/9/6  btnOrderComplete

    JLabel lblOrderNumTitle = new JLabel("订单数");
    JLabel lblMoneyTitle = new JLabel("总金额");

    JLabel lblOrderNum = new JLabel("");
    JLabel lblMoney = new JLabel("");

    //    JLabel lblGetNetTitle = new JLabel("提取网点");
    JLabel lblGetNetTitle = new JLabel("提车网点");
    JLabel lblRetNetTitle = new JLabel("返还网点");
    JLabel lblCarTyeTitle = new JLabel("车型信息");
    JLabel lblUsername = new JLabel("用户名");

    JTextField edtGetNet = new JTextField(20);
    JTextField edtRetNet = new JTextField(20);
    JTextField edtCarTye = new JTextField(20);
    JTextField edtUsername = new JTextField(20);
    JPanel pnlBtns = new JPanel();
    JPanel pnlInputs = new JPanel();

    void putButtons() {
        List<JButton> buttons = new ArrayList<>();
        pnlBtns.setLayout(new FlowLayout(FlowLayout.CENTER));
//        buttons.add(btnUpdate);
        buttons.add(btnSearch);
        if (get) {
            buttons.add(btnGetCarComplete);

        } else {
            buttons.add(btnRetCarComplete);

        }
        buttons.add(btnCancelOrder);
        buttons.add(btnDel);

        for (JButton button : buttons) {
            pnlBtns.add(button);
            button.addActionListener(this);
        }

//        btnUpdate.addActionListener(this);
//        btnSearch.addActionListener(this);
    }

    public FrmEmpOrder(String title, TblDataDo<OrderSvo> tblOrderSvo) {
        super(title);
        this.tblOrderSvo = tblOrderSvo;
//        JFrame frame = new JFrame("用户信息");
        Container contentPane = getContentPane();
        contentPane.add(new JScrollPane(this.tblOrderSvo), BorderLayout.CENTER);
//        , BorderLayout.SOUTH

//        pnlBtns.add(btnUpdate);
//        pnlInputs.add()

        JPanel pnlBottom = new JPanel(new BorderLayout());
        pnlBottom.add(pnlInputs, BorderLayout.CENTER);
        pnlBottom.add(pnlBtns, BorderLayout.SOUTH);
        pnlInputs.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlInputs.add(lblOrderNumTitle);
        pnlInputs.add(lblOrderNum);
        pnlInputs.add(lblMoneyTitle);
        pnlInputs.add(lblMoney);


        pnlInputs.add(lblGetNetTitle);
        pnlInputs.add(edtGetNet);
        pnlInputs.add(lblRetNetTitle);
        pnlInputs.add(edtRetNet);
        pnlInputs.add(lblCarTyeTitle);
//        pnlInputs.add(lblMoney);
        pnlInputs.add(edtCarTye);
        pnlInputs.add(lblUsername);
        pnlInputs.add(edtUsername);


//        pnlBtns.add(lblOrderNumTitle);
//        pnlBtns.add(lblOrderNum);
//        pnlBtns.add(lblMoneyTitle);
//        pnlBtns.add(lblMoney);
//
//
//        pnlBtns.add(lblGetNetTitle);
//        pnlBtns.add(edtGetNet);
//        pnlBtns.add(lblRetNetTitle);
//        pnlBtns.add(edtRetNet);
//        pnlBtns.add(lblCarTyeTitle);
////        pnlBtns.add(lblMoney);
//        pnlBtns.add(edtCarTye);
//        pnlBtns.add(lblUsername);
//        pnlBtns.add(edtUsername);
//        pnlBtns.add(btnSearch);
//        pnlBtns.add(btnGetCarComplete);
//        pnlBtns.add(btnGetCarComplete);


//        toolBarPanel.add(jButton);
//        btnUpdate.addActionListener(this);
//        btnSearch.addActionListener(this);
//        contentPane.add(pnlBtns, BorderLayout.SOUTH);
        contentPane.add(pnlBottom, BorderLayout.SOUTH);
//        setSize(1400, 500);
        setSize(1400, 700);
        setLocation(20, 10);//在屏幕中设置显示的位置
//        frame.getContentPane().add(new JScrollPane(tblUser));
//        frame.setSize(1400, 500);
//        frame.setLocation(20, 10);//在屏幕中设置显示的位置

//        tblUser.reloadTable();
//        tblUser.reloadTable();
//        x+=410;
//        frame.setVisible(true);

//        lblOrderNum.setText();
//        btnSearchEvent();
        // TODO: 2021/9/12   btnSearchEvent
//        reloadNums();


    }


    boolean get;

    public FrmEmpOrder(String title, TblDataDo<OrderSvo> tblOrderSvo, boolean get) {
//        super(title);
        this(title, tblOrderSvo);
        this.get = get;
        putButtons();

        btnSearchEvent();
        reloadNums();

//        this.tblOrderSvo = tblOrderSvo;
////        JFrame frame = new JFrame("用户信息");
//        Container contentPane = getContentPane();
//        contentPane.add(new JScrollPane(this.tblOrderSvo), BorderLayout.CENTER);
////        , BorderLayout.SOUTH
//
////        pnlBtns.add(btnUpdate);
////        pnlInputs.add()
//
//        JPanel pnlBottom = new JPanel(new BorderLayout());
//        pnlBottom.add(pnlInputs, BorderLayout.CENTER);
//        pnlBottom.add(pnlBtns, BorderLayout.SOUTH);
//        pnlInputs.setLayout(new FlowLayout(FlowLayout.CENTER));
//        pnlInputs.add(lblOrderNumTitle);
//        pnlInputs.add(lblOrderNum);
//        pnlInputs.add(lblMoneyTitle);
//        pnlInputs.add(lblMoney);
//
//
//        pnlInputs.add(lblGetNetTitle);
//        pnlInputs.add(edtGetNet);
//        pnlInputs.add(lblRetNetTitle);
//        pnlInputs.add(edtRetNet);
//        pnlInputs.add(lblCarTyeTitle);
////        pnlInputs.add(lblMoney);
//        pnlInputs.add(edtCarTye);
//        pnlInputs.add(lblUsername);
//        pnlInputs.add(edtUsername);
//
//
////        pnlBtns.add(lblOrderNumTitle);
////        pnlBtns.add(lblOrderNum);
////        pnlBtns.add(lblMoneyTitle);
////        pnlBtns.add(lblMoney);
////
////
////        pnlBtns.add(lblGetNetTitle);
////        pnlBtns.add(edtGetNet);
////        pnlBtns.add(lblRetNetTitle);
////        pnlBtns.add(edtRetNet);
////        pnlBtns.add(lblCarTyeTitle);
//////        pnlBtns.add(lblMoney);
////        pnlBtns.add(edtCarTye);
////        pnlBtns.add(lblUsername);
////        pnlBtns.add(edtUsername);
////        pnlBtns.add(btnSearch);
////        pnlBtns.add(btnGetCarComplete);
////        pnlBtns.add(btnGetCarComplete);
//
//        putButtons();
//
////        toolBarPanel.add(jButton);
////        btnUpdate.addActionListener(this);
////        btnSearch.addActionListener(this);
////        contentPane.add(pnlBtns, BorderLayout.SOUTH);
//        contentPane.add(pnlBottom, BorderLayout.SOUTH);
////        setSize(1400, 500);
//        setSize(1400, 700);
//        setLocation(20, 10);//在屏幕中设置显示的位置
////        frame.getContentPane().add(new JScrollPane(tblUser));
////        frame.setSize(1400, 500);
////        frame.setLocation(20, 10);//在屏幕中设置显示的位置
//
////        tblUser.reloadTable();
////        tblUser.reloadTable();
////        x+=410;
////        frame.setVisible(true);
//
////        lblOrderNum.setText();
//        btnSearchEvent();
//        // TODO: 2021/9/12   btnSearchEvent
//        reloadNums();


    }

    private void reloadNums() {
        int size = tblOrderSvo.all.size();
        lblOrderNum.setText(String.valueOf(size));
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderSvo orderSvo : tblOrderSvo.all) {
            sum = sum.add(orderSvo.getFinalPrice());
        }
//        tblOrderSvo.all.forEach(o->sum=sum.add(o.getFinalPrice()));
        lblMoney.setText(String.valueOf(sum));
//        stream()
//        tblOrderSvo.lis
    }

    //    客户可以修改自己的信息；
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnUpdate) {
            btnUpdateEvent();

        } else if (e.getSource() == this.btnSearch) {
//            try {
//                btnSearchEvent();
//            } catch (DbException | SQLException ex) {
//                ex.printStackTrace();
//            }

            btnSearchEvent();

        } else if (e.getSource() == this.btnRetCarComplete) {

            try {
                btnRetCarCompleteEvent();
            } catch (SQLException | BaseException ex) {
                ex.printStackTrace();
                UiUtil.showError(ex.getMessage());
            }
        } else if (e.getSource() == this.btnGetCarComplete) {

            try {
//                btnRetCarCompleteEvent();
                btnGetCarCompleteEvent();
            } catch (SQLException | BaseException ex) {
                ex.printStackTrace();
                UiUtil.showError(ex.getMessage());
            }
        } else if (e.getSource() == this.btnCancelOrder) {

            try {
//                btnRetCarCompleteEvent();
                btnCancelOrderEvent();
            } catch (SQLException | BaseException ex) {
                ex.printStackTrace();
                UiUtil.showError(ex.getMessage());
            }
        } else if (e.getSource() == this.btnDel) {
            del();

        }
    }

    // TODO: 2021/9/10  del() {
    void del() {
        if (tblOrderSvo.current == null) {
            UiUtil.showError("没有选择订单");
            return;
        }
//        public static String[] statusLst = {"", "已下单", "已提车", "已还车", "已取消"};
        Integer status = tblOrderSvo.current.getStatus();
//        if(status>=2&&status<=3){
//
//        }
//        BeanPlan beanPlan = new BeanPlan();
//        beanPlan.getPlan_order();

        if (status == 2) {
            UiUtil.showError("已经提车  不能删除");
            return;
        }
        if (status == 3) {
            UiUtil.showError("已经还车  不能删除");
            return;
        }
        try {
            JdbcUtil.delete(tblOrderSvo.current.toEntity());
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (DbException ex) {
            ex.printStackTrace();
        }

    }

    //    订单状态（下单，提车，还车，取消等）
    void btnRetCarCompleteEvent() throws SQLException, BaseException {
//        OrderSvo orderSvo=   tblOrderSvo.current;
        if (tblOrderSvo.current == null) {
            UiUtil.showError("没有选择订单");
            return;
        }
//        public static String[] statusLst = {"", "已下单", "已提车", "已还车", "已取消"};
        if (tblOrderSvo.current.getStatus() == 1) {
            UiUtil.showError("还没有提车，不能还车");
            return;
        }


        if (tblOrderSvo.current.getStatus() == 3) {
            UiUtil.showError("已经还过车了 不能再还车");
            return;
        }
        if (tblOrderSvo.current.getStatus() == 4) {
            UiUtil.showError("订单已取消 不能还车");
            return;
        }
        Connection connection = DBUtil.getConnection();
        connection.setAutoCommit(false);

        try {
            //        还车，
            tblOrderSvo.current.setStatus(3);
//        TblOrder tblOrder = new TblOrder();
            TblOrder tblOrder = tblOrderSvo.current.toEntity();
            JdbcUtil.update(connection, tblOrder, tblOrder.getOrderId(), "order_id");
            tblOrderSvo.reloadTableNoSearch(true);

//        产生调拨单

//        JdbcUtil.getBy()
            Integer carId = tblOrderSvo.current.getCarId();
            Integer getNetId = tblOrderSvo.current.getGetNetId();
            Integer retNetId = tblOrderSvo.current.getRetNetId();

            Allocation allocation = new Allocation(
                    new Date(),
                    null,
                    carId,
                    retNetId,
                    getNetId
            );
            JdbcUtil.insert(connection, allocation);
            CarInfo carInfo = new CarInfo();
            carInfo.setCarId(carId);
            List<CarInfo> by = JdbcUtil.getBy(carInfo);
            if (by.size() == 0) {
                UiUtil.showError("没有这辆车的信息");
                connection.rollback();
                return;
            }

            CarInfo carInfo1 = by.get(0);
            carInfo1.setNetId(retNetId);
            carInfo1.setCarStatus(1);
            //    public static final String[] carStatusLst = {"", "空闲", "在途", "报废"};

            JdbcUtil.update(connection, carInfo1, carInfo1.getCarId(), "car_id");


            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            UiUtil.showError(e.getMessage());
            connection.rollback();
        }


        DBUtil.closeConnection(connection);


    }

    //    订单状态（下单，提车，还车，取消等）
    void btnGetCarCompleteEvent() throws SQLException, BaseException {
//        OrderSvo orderSvo=   tblOrderSvo.current;
        if (tblOrderSvo.current == null) {
            UiUtil.showError("没有选择订单");
            return;
        }

//        ，订单状态（下单，提车，还车，取消等）
        if (tblOrderSvo.current.getStatus() == 3) {
            UiUtil.showError("已经还车了 不能再提车");
            return;
        }

        if (tblOrderSvo.current.getStatus() == 4) {
            UiUtil.showError("订单已经取消 不能再提车");
            return;
        }

        if (tblOrderSvo.current.getStatus() == 2) {
//            UiUtil.showError("订单已经取消 不能再提车");
//            已经是 已经提车的状态 不用更改
            return;
        }


//        取车，
        tblOrderSvo.current.setStatus(2);
//        TblOrder tblOrder = new TblOrder();
        TblOrder tblOrder = tblOrderSvo.current.toEntity();
        JdbcUtil.update(tblOrder, tblOrder.getOrderId(), "order_id");
        tblOrderSvo.reloadTableNoSearch(true);
    }

    //    订单状态（下单，提车，还车，取消等）
    void btnCancelOrderEvent() throws SQLException, BaseException {
//        OrderSvo orderSvo=   tblOrderSvo.current;
        if (tblOrderSvo.current == null) {
            UiUtil.showError("没有选择订单");
            return;
        }
//        取车，
        tblOrderSvo.current.setStatus(4);
//        TblOrder tblOrder = new TblOrder();
        TblOrder tblOrder = tblOrderSvo.current.toEntity();
        JdbcUtil.update(tblOrder, tblOrder.getOrderId(), "order_id");
        tblOrderSvo.reloadTableNoSearch(true);
    }

    void btnSearchEvent(boolean get) {
        String getNetText = edtGetNet.getText();
        String retNetText = edtRetNet.getText();
        String carTyeText = edtCarTye.getText();
        String usernameText = edtUsername.getText();
//        String text = edtGetNet.getText();
        OrderSvo orderSvo = new OrderSvo();
        if (!getNetText.equals("")) {
            orderSvo.setGetNetName(getNetText);
        }

        if (!retNetText.equals("")) {
            orderSvo.setRetNetName(retNetText);
        }
        if (!carTyeText.equals("")) {
            orderSvo.setTypeName(carTyeText);
        }

        if (!usernameText.equals("")) {
            orderSvo.setUserName(usernameText);
        }
//        orderSvo.setGetNetId(Emp.);
        if (CcCarUtil.currentEmp == null) {
            UiUtil.showError("你不是管理员");
            return;
        }
//        CcCarUtil.currentEmp.getNetId()
        if (get) {
            orderSvo.setGetNetId(CcCarUtil.currentEmp.getNetId());

        } else {
            orderSvo.setRetNetId(CcCarUtil.currentEmp.getNetId());

        }

//        orderSvo.setTypeName(carTyeText);

        try {
            List<OrderSvo> by = JdbcUtil.getBy(orderSvo);
            tblOrderSvo.all = by;
//            tblOrderSvo.reloadTableBy(true,new OrderSvo());
//            tblOrderSvo.reloadTableBy(true,orderSvo);
            tblOrderSvo.reloadTableNoSearch(true);
        } catch (SQLException | BaseException ex) {
            ex.printStackTrace();
            UiUtil.showError(ex.getMessage());
        }
        reloadNums();


//        try{
//            List<OrderSvo> by = JdbcUtil.getBy(orderSvo);
//
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
    }

    void btnSearchEvent() {
        btnSearchEvent(get);
//        String getNetText = edtGetNet.getText();
//        String retNetText = edtRetNet.getText();
//        String carTyeText = edtCarTye.getText();
//        String usernameText = edtUsername.getText();
////        String text = edtGetNet.getText();
//        OrderSvo orderSvo = new OrderSvo();
//        if (!getNetText.equals("")) {
//            orderSvo.setGetNetName(getNetText);
//        }
//
//        if (!retNetText.equals("")) {
//            orderSvo.setRetNetName(retNetText);
//        }
//        if (!carTyeText.equals("")) {
//            orderSvo.setTypeName(carTyeText);
//        }
//
//        if (!usernameText.equals("")) {
//            orderSvo.setUserName(usernameText);
//        }
////        orderSvo.setGetNetId(Emp.);
//        if (CcCarUtil.currentEmp == null) {
//            UiUtil.showError("你不是管理员");
//            return;
//        }
////        CcCarUtil.currentEmp.getNetId()
//        orderSvo.setGetNetId(CcCarUtil.currentEmp.getNetId());
//
////        orderSvo.setTypeName(carTyeText);
//
//        try {
//            List<OrderSvo> by = JdbcUtil.getBy(orderSvo);
//            tblOrderSvo.all = by;
////            tblOrderSvo.reloadTableBy(true,new OrderSvo());
////            tblOrderSvo.reloadTableBy(true,orderSvo);
//            tblOrderSvo.reloadTableNoSearch(true);
//        } catch (SQLException | BaseException ex) {
//            ex.printStackTrace();
//            UiUtil.showError(ex.getMessage());
//        }
//        reloadNums();


//        try{
//            List<OrderSvo> by = JdbcUtil.getBy(orderSvo);
//
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }
    }

    void reloadTblOrderSvo() {
        reloadTblOrderSvo(true);
        //        List<OrderSvo> orderSvos = CcCarUtil.orderSvoDao.loadAll();
//        if (CcCarUtil.currentEmp == null) {
//            UiUtil.showError("你不是管理员");
//            return;
//        }
//
//        OrderSvo orderSvo = new OrderSvo();
////        CcCarUtil.currentEmp.getNetId()
//        orderSvo.setGetNetId(CcCarUtil.currentEmp.getNetId());
//
//        try {
////            tblOrderSvo.reloadTable(true);
////            全部查出来
//            List<OrderSvo> by = JdbcUtil.getBy(new OrderSvo());
//            tblOrderSvo.all = by;
////            tblOrderSvo.reloadTableBy(true,new OrderSvo());
//            tblOrderSvo.reloadTableNoSearch(true);
////            tblOrderSvo.reloadTable(true);
//        } catch (BaseException | SQLException e) {
//            e.printStackTrace();
//            UiUtil.showError("显示订单失败  " + e.getMessage());
//        }
    }


    void reloadTblOrderSvo(boolean get) {
        //        List<OrderSvo> orderSvos = CcCarUtil.orderSvoDao.loadAll();
        if (CcCarUtil.currentEmp == null) {
            UiUtil.showError("你不是管理员");
            return;
        }

        OrderSvo orderSvo = new OrderSvo();
//        CcCarUtil.currentEmp.getNetId()
        if (get) {
            orderSvo.setGetNetId(CcCarUtil.currentEmp.getNetId());

        } else {
            orderSvo.setRetNetId(CcCarUtil.currentEmp.getNetId());
        }

        try {
//            tblOrderSvo.reloadTable(true);
//            全部查出来
//            List<OrderSvo> by = JdbcUtil.getBy(new OrderSvo());
            List<OrderSvo> by = JdbcUtil.getBy(orderSvo);
            tblOrderSvo.all = by;
//            tblOrderSvo.reloadTableBy(true,new OrderSvo());
            tblOrderSvo.reloadTableNoSearch(true);
//            tblOrderSvo.reloadTable(true);
        } catch (BaseException | SQLException e) {
            e.printStackTrace();
            UiUtil.showError("显示订单失败  " + e.getMessage());
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
}
