package cn.edu.zucc.personplan.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.util.*;
import com.mchange.v1.util.UIterator;
import org.apache.commons.beanutils.BeanUtils;
import sun.management.jdp.JdpController;

public class FrmProm extends JFrame implements ActionListener {
    private static final long serialVersionUID = -990815448731736703L;
    //  网点 两个
//    NetInfo currentNetInfo = null;
//    private Object[] titlesNetInfo = NetInfo.tableTitles;
//    private Object[][] tblDataNetInfo;
//    DefaultTableModel tabModelNetInfo = new DefaultTableModel();
    //    private JTable dataTableNetInfo = new JTable(tabModelNetInfo);
    TblData<NetInfo> netInfoTblData;

//    List<NetInfo> allNetInfo = null;

//  user stat

    //  车
//    这些数据 这里是需要的 ，但是好像也不是
//    CarInfo currentCarInfo = null;
//    private Object[] titlesCarInfo = CarInfo.tableTitles;
//    private Object[][] tblDataCarInfo;
//    DefaultTableModel tabModelCarInfo = new DefaultTableModel();
    //    private JTable dataTableCarInfo ;
    private TblData<CarInfo> dataTableCarInfo;
    //    private JTable dataTableCarInfo = new JTable(tabModelCarInfo);
//    List<CarInfo> allCarInfo = null;


    //  优惠券
//    Coupon currentCoupon = null;
//    private Object[] titlesCoupon = Coupon.tableTitles;
//    private Object[][] tblDataCoupon;
//    DefaultTableModel tabModelCoupon = new DefaultTableModel();
    //    private JTable dataTableCoupon = new JTable(tabModelCoupon);
    private TblData<Coupon> dataTableCoupon;
    //    这里还没有new 出来
//    List<Coupon> allCoupon = null;
//
//    //    限时促销
//    Promotion currentPromotion = null;
//    private Object[] titlesPromotion = Promotion.tableTitles;
//    private Object[][] tblDataPromotion;
//    DefaultTableModel tabModelPromotion = new DefaultTableModel();
    //    private JTable dataTablePromotion = new JTable(tabModelPromotion);
    private TblData<Promotion> dataTablePromotion;
    private TblData<CarCategory> dataTableCarCategory;
//    List<Promotion> allPromotion = null;

    TblData<NetInfo> tblNetBack;

    TblData<TblOrder> tblOrder = new TblData<>(TblOrder.tableTitles,
            CcCarUtil.tblOrderDao);

    TblData<User> tblUser = new TblData<>(User.tableTitles,
            CcCarUtil.userDao);

    TblData<CarType> tblCarType = new TblData<>(CarType.tableTitles,
            CcCarUtil.carTypeDao);

    JFrame frmTableCoupon = new JFrame("优惠券");
    private int OrderDetailIndex;

    // Panel 布局
    private JPanel mdpTblPanel = new JPanel();
    //    private JPanel pnlTitles = new JPanel();
    private JPanel pnlTitles = new JPanel();
    //    private JScrollPane mdpTblPanel = new JScrollPane();
    private JPanel orderInfoTablePanel = new JPanel();
    private JPanel toolBarPanel = new JPanel();
    private JPanel inputPanel = new JPanel();

    private JButton exit = new JButton("放弃订单");
    //    private JButton add = new JButton("购入");
//    private JButton add = new JButton("点单");
    private JButton add = new JButton("发起订单");
    private JButton unadd = new JButton("减少");
    //    private JButton done = new JButton("结算");
    private JButton done = new JButton("订单完成");
    private JButton btnUserInfo = new JButton("个人信息");
    private JButton btnOrder = new JButton("订单信息");
    private JButton btnPromotionSearch = new JButton("查询限时优惠");
    //    private JButton btnAddNewRow = new JButton("增加新的一行");
    private JButton btnAddNewPromo = new JButton("新增优惠券");
    private JButton btnAddNewPromoComplete = new JButton("提交限时优惠");
    TblOrder order = new TblOrder();


    private JLabel labelGetTime = new JLabel("edtGetTime：");
    private JLabel labelRetTime = new JLabel("edtRetTime：");
    private JLabel labelRentalDur = new JLabel("edtRentalDur：");

    private JTextField edtGetTime = new JTextField(20);
    private JTextField edtRetTime = new JTextField(20);
    //    借的时间
    private JTextField edtRentalDur = new JTextField(20);


    private JLabel labelDiscount = new JLabel("折扣：");
    private JLabel labelPromQuantity = new JLabel("数量：");
    private JLabel labelStartTime = new JLabel("开始时间：");
    private JLabel labelEndTime = new JLabel("结束时间：");


    private JTextField edtDiscount = new JTextField(20);
    private JTextField edtPromQuantity = new JTextField(20);
    private JTextField edtStartTime = new JTextField(20);
    private JTextField edtEndTime = new JTextField(20);


//     this.discount = discount;
//        this.promQuantity = promQuantity;
//        this.startTime = startTime;
//        this.endTime = endTime;
//

//    public FrmEmp() throws BaseException, SQLException {
//    }
//    private JTextField edtGetTime = new JTextField(20);
//    raw_price  算出来的

    //    另外一个界面的 时候 只要 复制 然后 改名字
//    不能扔东西
//    actionPerformed 怎么抛出异常

    void doAdd() {
        //            tbl 放在list 里面 ，也不知道是哪个
//            map  也还是要取出来 ，好像没什么用处

        System.out.println("add");
//        NetInfo netInfo = netInfoTblData.current;
//        NetInfo netInfo = netInfoTblData.current;
//        System.out.println("netInfo");
//        System.out.println(netInfo);
//            可以能拿到
//            没有显示
        CarInfo carInfo = dataTableCarInfo.current;

        String getTimeText = edtGetTime.getText();
        String retTimeText = edtRetTime.getText();
        String rentalDurText = edtRentalDur.getText();
//            String getTimeText = edtGetTime.getText();
        if (getTimeText == null || retTimeText == null ||
                getTimeText.equals("") || retTimeText.equals("")) {
            JOptionPane.showMessageDialog(null,
                    "时间不能为空", "错误", JOptionPane.ERROR_MESSAGE);
            return;

//                try {
//                    throw new BusinessException("时间不能为空");
//                } catch (BusinessException ex) {
//                    ex.printStackTrace();
//                }
//                Dialog 时间不能为空 = new Dialog("时间不能为空");
        }
        System.out.println("getTimeText");
        System.out.println(getTimeText);
        System.out.println("retTimeText");
        System.out.println(retTimeText);
        System.out.println("rentalDurText");
        System.out.println(rentalDurText);
//            Date date = new Date(getTimeText);
//            Date date = Date.
//            String format="yyyy-MM-dd hh:mm:ss";
        String format = "yyyy-MM-dd";
        Date getTimeDate = TimeUtil.stringtoDate(getTimeText, format);
        Date retTimeDate = TimeUtil.stringtoDate(retTimeText, format);

//            getTimeDate.
//            TimeUtil.
//            时间 相差毫秒
//            getTimeDate.getTime()-
//          Long  rentalDur= retTimeDate.getTime()- getTimeDate.getTime();
//            相差的天数
//            Long rentalDur = TimeUtil.daysBetween(getTimeDate, retTimeDate);
        long rentalDur = TimeUtil.daysBetween(getTimeDate, retTimeDate);
//            dataTableProm.current.prom()
//            Integer carId = this.dataTableCarInfo.current.getCarId();
        Integer typeId = this.dataTableCarInfo.current.getTypeId();
        CarType carType = new CarType();
//            carType.setCatId(carId);
        carType.setTypeId(typeId);
        System.out.println("carType");
        System.out.println(carType);
//            carTypeDao


        BigDecimal unitPrice;
        try {
            List<CarType> byCarId = CcCarUtil.carTypeDao.getBy(carType);
            if (byCarId == null) {
                JOptionPane.showMessageDialog(null,
                        "没有搜索到这种车的车型",
                        "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            System.out.println("byCarId");
            System.out.println(byCarId);
            if (byCarId.size() <= 0) {
//                    没有搜索到 车型的 price
                JOptionPane.showMessageDialog(null,
                        "没有搜索到 车型的 price", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            CarType carType1 = byCarId.get(0);
            unitPrice = carType1.getPrice();

        } catch (BaseException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }
        BigDecimal rawPrice = unitPrice.multiply(BigDecimal.valueOf(rentalDur));

        Coupon coupon = dataTableCoupon.current;

//        BigDecimal finalPrice = rawPrice.subtract(coupon.getDeducAmou());
        BigDecimal finalPrice;
        if (coupon == null) {
//            UiUtil.showError("没有选择优惠券");
            finalPrice = rawPrice;
//            return;
        } else {
            finalPrice = rawPrice.subtract(coupon.getDeducAmou());

            Integer netId1 = coupon.getNetId();
            if (!carInfo.getNetId().equals(netId1)) {
                UiUtil.showError("该优惠券不能在该网点使用");
                return;
            }
        }
        Promotion promotion = dataTablePromotion.current;
        if (promotion != null) {
            finalPrice = finalPrice.multiply(promotion.getDiscount()).divide(BigDecimal.valueOf(10));

            Integer netId = promotion.getNetId();

            if (!carInfo.getNetId().equals(netId)) {
                UiUtil.showError("此种限时优惠不能在该网点使用");
                return;
            }
        }


//            coupon.getDeducAmou();
//          price =  rentalDur*unitPrice - 优惠券
//            price  * 折扣
//            BigDecimal unitPrice = BigDecimal.ONE;
//            if()
//            BigDecimal price=
        // TODO: 2021/9/3     BigDecimal price =

//        coupon 可能是null
//        BigDecimal finalPrice = rawPrice.
//                subtract(coupon.getDeducAmou());
//            促销数量 要是 0  也是不显示
//            打折  写着 7折 ，再除以 10

//            divide bigd
//                    再打折
//            订单完成后，根据车型、租车时长，自动生成结算金额


//          根据 car id  知道车型号, 然后知道了
//            rental_dur 应该是天数把
//            两个时间 相差几天
//        promotion.getPromId(),


//        netInfo.getNetId()
//        CarInfo   curCar=  dataTableCarInfo.current;
//        curCar.getNetId();


        Integer couponId = coupon == null ? null : coupon.getCouponId();
        TblOrder order = new TblOrder(null,
                promotion == null ? null : promotion.getPromId(),
                carInfo.getNetId(),
                User.currentLoginUser.getUserId(),
                carInfo.getCarId(),
                tblNetBack.current.getNetId(),
//                dataTableCoupon.current.getCouponId(),
                couponId,
                getTimeDate,
                retTimeDate,
                rentalDur,
                rawPrice,
                finalPrice, 0

        );
        try {
            Connection connection = DBUtil.getConnection();
            CcCarUtil.tblOrderDao.insert(connection, order);
        } catch (SQLException | DbException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }


    }


    // Variables declaration - do not modify
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration


    /**
     * 全部订单
     *
     * @throws BaseException
     * @throws SQLException
     */
    void showOrder() throws BaseException, SQLException {
//        tblOrder.validate();
//        tbl
        JFrame frame = new JFrame("订单");
        frame.getContentPane().add(new JScrollPane(tblOrder));
        frame.setSize(1400, 500);
        frame.setLocation(20, 10);//在屏幕中设置显示的位置

        tblOrder.reloadTable();
//        x+=410;
        frame.setVisible(true);
    }

    JFrame frmOrder = new JFrame("订单");

    void showOrder(boolean self) throws BaseException, SQLException {
//        tblOrder.validate();
//        tbl
        JFrame frame = new JFrame("订单");
        frame.getContentPane().add(new JScrollPane(tblOrder));
        frame.setSize(1400, 500);
        frame.setLocation(20, 10);//在屏幕中设置显示的位置

        if (self) {
            TblOrder tblOrderEntity = new TblOrder();
            tblOrderEntity.setUserId(User.currentLoginUser.getUserId());
            this.tblOrder.all = JdbcUtil.getBy(tblOrderEntity);
            tblOrder.reloadTableByThisList();
        } else {
            tblOrder.reloadTable();
        }

//        x+=410;
        frame.setVisible(true);
    }

    void setUpFrmOrder() {
//        JFrame frame = new JFrame("订单");
        frmOrder.getContentPane().add(new JScrollPane(tblOrder));
        frmOrder.setSize(1400, 500);
        frmOrder.setLocation(20, 10);//在屏幕中设置显示的位置

    }

    FrmUserInfo frmUserInfo = new FrmUserInfo("用户信息", tblUser);

    void showUser() {
//        tblOrder.validate();
//        tbl
        JFrame frame = new JFrame("用户信息");
        frame.getContentPane().add(new JScrollPane(tblUser));
        frame.setSize(1400, 500);
        frame.setLocation(20, 10);//在屏幕中设置显示的位置

//        tblUser.reloadTable();
//        tblUser.reloadTable();
//        x+=410;
        frame.setVisible(true);
    }


    void init() {
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.exit) {
//            dataTableCarInfo.current.setCarStatus();
//
        } else if (e.getSource() == this.add) {
            doAdd();
            try {
                showOrder();
            } catch (BaseException | SQLException ex) {
                ex.printStackTrace();
                UiUtil.showError("展示不了订单" + ex.getMessage());
            }
        } else if (e.getSource() == this.unadd) {

        } else if (e.getSource() == this.done) {
//            new FrmUserAddress();
            // FrmUserAddress FUserAddress = new FrmUserAddress();
//            System.out.println("您的订单：" + Bus.currentOrder);
            // this.setVisible(false);
//            完成订单 应该是管理员的 事情

        } else if (e.getSource() == this.btnUserInfo) {
            btnUserInfoEvent();
        } else if (e.getSource() == this.btnOrder) {

            try {
//                showOrder(true);


                TblOrder tblOrderEntity = new TblOrder();
                tblOrderEntity.setUserId(User.currentLoginUser.getUserId());
                this.tblOrder.all = JdbcUtil.getBy(tblOrderEntity);
                tblOrder.reloadTableByThisList();
                frmOrder.setVisible(true);
//                dataTablePromotion.
                // TODO: 2021/9/5 为什么不行 不显示
//                frmUserOrder.setVisible(true);

            } catch (BaseException | SQLException ex) {
                ex.printStackTrace();
                UiUtil.showError("展示订单失败 " + ex.getMessage());
            }


        } else if (e.getSource() == this.btnPromotionSearch) {

            String edtGetTimeText = edtGetTime.getText();
//            如果 全部列出来 都可以用了
            Date date = TimeUtil.stringtoDate(edtGetTimeText, TimeUtil.fmtYmd);
            if (date == null) {
                UiUtil.showError("日期格式异常");
                return;
            }
//            String edtGetTimeText = edt.getText();
            String addSql = String.format(" and '%s' >=  start_time  and '%s' <= end_time "
                    , edtGetTimeText, edtGetTimeText);
            try {
                Promotion promotion = new Promotion();
//                限时促销的车型，必须在特定的网点，特定的车型，及特定的时间段才能享受
                promotion.setNetId(dataTableCarInfo.current.getNetId());
                promotion.setTypeId(dataTableCarInfo.current.getTypeId());
//                List<Promotion> by = JdbcUtil.getBy(new Promotion(), addSql);
                List<Promotion> by = JdbcUtil.getBy(promotion, addSql);
                dataTablePromotion.all = by;
                dataTablePromotion.reloadTableNoSearch();
            } catch (SQLException | BaseException ex) {
                ex.printStackTrace();
                UiUtil.showError(ex.getMessage());
            }

        }

//        else if (e.getSource() == this.btnAddNewRow) {
//
//            dataTableCoupon.addEmptyRow();
//
//        }

        else if (e.getSource() == this.btnAddNewPromo) {

            dataTablePromotion.addEmptyRow();

        } else if (e.getSource() == this.btnAddNewPromoComplete) {
            btnAddNewPromoCompleteEvent();

//            try {
//                btnAddNewPromoCompleteEvent();
//            } catch (DbException | SQLException ex) {
//                ex.printStackTrace();
//            }
        }
    }


    void btnAddNewPromoCompleteEvent()  {

//            btnAddNewPromoComplete.addEmptyRow();
//            dataTablePromotion.

        String discountText = edtDiscount.getText();
        String promQuantityText = edtPromQuantity.getText();
        String startTimeText = edtStartTime.getText();
        String endTimeText = edtEndTime.getText();

//            if(!StringUtils.checkTimeStr(startTimeText)){
//              return;
//            }
//            if(!StringUtils.checkTimeStr(endTimeText)){
//                return;
//            }

        Date dateStart = TimeUtil.stringtoDate(startTimeText, TimeUtil.fmtYmd);
        if (dateStart == null) {
            UiUtil.showError("填入开始时间有误");
            return;
        }
        Date dateEnd = TimeUtil.stringtoDate(endTimeText, TimeUtil.fmtYmd);
        if (dateEnd == null) {
            UiUtil.showError("填入结束时间有误");
            return;
        }
        if ( StringUtils.isNone(promQuantityText)) {
            UiUtil.showError("请输入限时优惠数量");
            return;
        }

        if (StringUtils.isNone(discountText)) {
            UiUtil.showError("请输入限时优惠折扣");
            return;
        }

        BigDecimal discountDeci = new BigDecimal(discountText);
        if(discountDeci.compareTo(BigDecimal.ZERO)<0||
                discountDeci.compareTo(new BigDecimal("10"))>0){
            UiUtil.showError("折扣只能从0到10折");
            return;
        }

        Integer promInt;
        try{
             promInt = Integer.valueOf(promQuantityText);

        }catch (Exception e){
            UiUtil.showError("折扣只能是整数");
            return;
        }
        if(promInt<=0){
            UiUtil.showError("数量不能少于1");
            return;
        }
        if(tblCarType.current==null){
            UiUtil.showError("请选择车型");
            return;
        }
//        折扣是 1 到10
//          限时促销信息（至少包括促销编号，网点编号，车辆型号，促销折扣，促销数量，起始日期，结束日期等信息
//            antlr.StringUtils.st

//            com.mysql.jdbc.StringUtils.

//            StringUtils.checkTimeStr(endTimeText);
        Promotion promotion = new Promotion(null, tblCarType.current.getTypeId(),
                CcCarUtil.currentEmp.getNetId(), discountDeci,
                promInt, dateStart, dateEnd);
        try {
//                JdbcUtil.update(promotion,promotion.getPromId(),"prom_id");

            JdbcUtil.insert(promotion);
        } catch (SQLException | DbException ex) {
            ex.printStackTrace();
            UiUtil.showError("更新失败  " + ex.getMessage());
        }

//            try {
////                dataTablePromotion.updateBatch(dataTablePromotion,dataTablePromotion.dao);
//                UiUtil.updateBatch(dataTablePromotion);
//            } catch (SQLException | BaseException ex) {
//                ex.printStackTrace();
//                UiUtil.showError("优惠券上传失败  "+ex.getMessage());
//            }

        dataTablePromotionReload();
//        Promotion promotion1 = new Promotion();
//        promotion1.setNetId(  CcCarUtil.currentEmp.getNetId());
//
//        try {
////            btnAddNewPromoCompleteEvent();
//            dataTablePromotion.all=JdbcUtil.getBy(promotion1);
//            dataTablePromotion.reloadTableNoSearch();
//        } catch (SQLException | BaseException ex) {
//            ex.printStackTrace();
//            UiUtil.showError("查询失败 "+ex.getMessage());
//        }


    }

    void btnUserInfoEvent() {
        try {
//                密码是不是不要显示
            List<User> by = JdbcUtil.getBy(User.currentLoginUser);
            tblUser.all = by;


        } catch (DbException | SQLException ex) {
            ex.printStackTrace();
            UiUtil.showError("查找失败" + ex.getMessage());
        }
        tblUser.reloadTableByThisList();
//            showUser();
        frmUserInfo.setVisible(true);


//            showTable();
    }

    FrmUserOrder frmUserOrder = new FrmUserOrder("用户查看订单", tblOrder);
//    FrmUserOrder frmUserOrder=new FrmUserOrder("用户查看订单",orde);

    JFrame frmPromotion = new JFrame("促销信息");

    public FrmProm() throws BaseException, SQLException {
//            Bus.fUserGo = this;
        this.setTitle("限时优惠管理界面");
//            this.setSize(1318, 838);
        this.setSize(1318, 1000);
        this.setLocationRelativeTo(null);
//        this.setLocation(10, 400);
//        中间左右  400
        this.setLocation(10, 10);
        // this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // 这里可以加一个确认(Yes/No)的JoptionPane，来设置关闭窗口是否要删除订单。但是我懒
                try {
//                        Bus.orderManager.removeByOrderID(Bus.currentOrder.getOrderID());
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "删除已创建的订单失败" + e1.getMessage(),
                            "错误", JOptionPane.ERROR_MESSAGE);
                }

//          System.exit(0);
//          不要关掉
            }
        });


//            this.setVisible(true);
        // body 布局
//            mdpTblPanel.setLayout(new BorderLayout());// 如果这里不设置的话，商品信息表将无法展示
//            mdpTblPanel.setLayout(new GridLayout(3,3));// 如果这里不设置的话，商品信息表将无法展示
//            mdpTblPanel.setLayout(new GridLayout(1, 6));// 如果这里不设置的话，商品信息表将无法展示
        mdpTblPanel.setLayout(new GridLayout(1, 6));// 如果这里不设置的话，商品信息表将无法展示
//
        Container contentPane = this.getContentPane();

        contentPane.add(this.mdpTblPanel, BorderLayout.CENTER);
//            contentPane.add(jScrollPane, BorderLayout.NORTH);
//            contentPane.add(this.mdpTblPanel, BorderLayout.NORTH);
        setBottom(contentPane);


        setUpButtons();
        setUpInputPanel();

        setUpTables();
        loadData();

//        init();
//
//        jScrollPane1.setViewportView(this.netInfoTblData);
//        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 320, 270));


//        getContentPane().add(jScrollPane1,
//                new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 320, 270));

//        优惠券 一开始 不用 加载


//        setUpFrmTableCoupon();
//        setUpFrmOrder();

        addListenerCarType();
    }


    void setUpInputPanel() {
        inputPanel.add(labelDiscount);
        inputPanel.add(edtDiscount);

        inputPanel.add(labelPromQuantity);
        inputPanel.add(edtPromQuantity);


        inputPanel.add(labelStartTime);
        inputPanel.add(edtStartTime);

        inputPanel.add(labelEndTime);
        inputPanel.add(edtEndTime);

    }

    void setBottom(Container contentPane) {
        JPanel pnlBottom = new JPanel(new BorderLayout());

        pnlBottom.add(this.toolBarPanel, BorderLayout.NORTH);

        pnlBottom.add(this.inputPanel, BorderLayout.SOUTH);

        contentPane.add(pnlBottom, BorderLayout.SOUTH);
    }

    //    点 车子 显示 这个网点的 优惠券， 不需要了，emp 已经只能是自己网点
    void addListenerCarType() {


        this.tblCarType.addMouseListener(new MouseAdapter() {

            //            mouseClicked 怎么获取外面的数据
//            这是引用 只要弄到一次 以后都可以了
            @Override
            public void mouseClicked(MouseEvent e) {

//                TblData<CarInfo> tableCarInfo = FrmProm.this.dataTableCarInfo;
                TblData<CarType>carTypeTblData=     FrmProm.this.tblCarType;
//                int i = tableCarInfo.getSelectedRow();
                int i = carTypeTblData.getSelectedRow();
//                这个知道是自己吗
                if (i < 0) {
                    return;
                }
//                tableCarInfo.current.getCarId()
                CarCategory carCategory = new CarCategory();
                carCategory.setCatId(carTypeTblData.current.getCatId());
                try {
                    dataTableCarCategory.all= JdbcUtil.getBy(carCategory);
                    dataTableCarCategory.reloadTableNoSearch();
                } catch (DbException | SQLException ex) {
                    ex.printStackTrace();
                } catch (BaseException ex) {
                    ex.printStackTrace();
                }


//                TblData<Coupon> tableCoupon = FrmProm.this.dataTableCoupon;
//                Coupon coupon = new Coupon();
//                coupon.setNetId(tableCarInfo.current.getNetId());
//                try {
//                    tableCoupon.all = JdbcUtil.getBy(coupon);
//                    tableCoupon.reloadTableByThisList();
//
//
//                } catch (DbException | SQLException ex) {
//                    ex.printStackTrace();
//                    UiUtil.showError("获取优惠券失败 " + ex.getMessage());
//                }

//                这样可以的
//                tableCarInfo.current = tableCarInfo.all.get(i);


            }
        });

//        this.dataTableCarInfo.addMouseListener(new MouseAdapter() {
//
//            //            mouseClicked 怎么获取外面的数据
////            这是引用 只要弄到一次 以后都可以了
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//                TblData<CarInfo> tableCarInfo = FrmProm.this.dataTableCarInfo;
//                int i = tableCarInfo.getSelectedRow();
////                这个知道是自己吗
//                if (i < 0) {
//                    return;
//                }
////                tableCarInfo.current.getCarId()
//
//
//
//                TblData<Coupon> tableCoupon = FrmProm.this.dataTableCoupon;
//                Coupon coupon = new Coupon();
//                coupon.setNetId(tableCarInfo.current.getNetId());
//                try {
//                    tableCoupon.all = JdbcUtil.getBy(coupon);
//                    tableCoupon.reloadTableByThisList();
////                    showFrame("优惠券",new ScrollPane( tableCoupon), 410 * 3);
//
////                    showFrame("优惠券", tableCoupon, 410 * 3);
////                    showTable("优惠券", tableCoupon, 410 * 3);
//
////                    JFrame frame = new JFrame("优惠券");
////                    frmTableCoupon= new JFrame("优惠券");
//                    // TODO: 2021/9/5 cop
////                    frmTableCoupon.
////                    frmTableCoupon.setVisible(true);
////                    FrmOrder.this.dataTableCoupon.setVisible(true);
//
//
//                } catch (DbException | SQLException ex) {
//                    ex.printStackTrace();
//                    UiUtil.showError("获取优惠券失败 " + ex.getMessage());
//                }
////                这样可以的
////                tableCarInfo.current = tableCarInfo.all.get(i);
//
//
//            }
//        });
    }

    void setUpButtons() {


        List<JButton> btnList = new ArrayList<>();
//        btnList.add(this.exit);
//        btnList.add(this.add);
//        btnList.add(this.unadd);
//        btnList.add(this.done);
//        btnList.add(this.btnUserInfo);
//        btnList.add(this.btnOrder);
//        btnList.add(this.btnPromotionSearch);
//        btnList.add(this.btnAddNewPromo);
        btnList.add(this.btnAddNewPromoComplete);
//        btnList.add(this.btnAddNewRow);
//        btnList.add(this.btnScrap);


        // toolBarPanel 设置
//        toolBarPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        for (JButton jButton : btnList) {
            toolBarPanel.add(jButton);
            jButton.addActionListener(this);
//            this.edtGetTime.getPreferredSize()
        }
    }

    void setUpTables() throws BaseException, SQLException {

        List<TblData> tblDataList = new ArrayList<>();


//        this.netInfoTblData = new TblData<>(NetInfo.tableTitles,
//                CcCarUtil.netInfoDao);
////        因为这个 确实没了
//
//
//        this.tblNetBack = new TblData<>(NetInfo.tableTitles,
//                CcCarUtil.netInfoDao);
////        因为没有 reload
//        this.dataTableCarInfo = new TblData<>(CarInfo.tableTitles,
//                CcCarUtil.carInfoDao);
////        this.dataTableCoupon = new TblData<>(Coupon.tableTitles,
////                tblDataCoupon, tabModelCoupon, allCoupon, CcCarUtil.couponDao);
//        this.dataTableCoupon = new TblData<>(Coupon.tableTitles,
//                CcCarUtil.couponDao);
//        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
//                tblDataPromotion, tabModelPromotion, allPromotion, CcCarUtil.promotionDao);
        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
                CcCarUtil.promotionDao);

        this.dataTableCarCategory = new TblData<>(CarCategory.tableTitles,
                CcCarUtil.carCategoryDao);
//        tblDataList.add(netInfoTblData);
//        tblDataList.add(dataTableCarInfo);
//
////        tblDataList.add(tblNetBack);
//        tblDataList.add(dataTableCoupon);
        tblDataList.add(dataTablePromotion);
        tblDataList.add(tblCarType);
        tblDataList.add(dataTableCarCategory);

//        dataTableCoupon.setVisible(false);
//        dataTablePromotion.setVisible(false);
//        数据貌似没有写入  难道因为泛型？
        // TODO: 2021/9/5  dataTablePromotion 一开始 不显示

//        pnlTitles.setLayout(new GridLayout(1,6));
//            pnlTitles.add(new JTextField("1"));
//            contentPane.add(pnlTitles,BorderLayout.NORTH);


        int x = 0;
        int y = 0;
        int titleIdx = 0;
//        String[] titles = new String[]{"优惠券", "车型","车总类"};
        String[] titles = new String[]{"限时优惠", "车型","车总类"};
//        String[] titles = new String[]{"车", "还车网点", "限时优惠"};
        for (TblData tblData : tblDataList) {


            JPanel panel = borderTable(titles[titleIdx++], tblData);

//            this.mdpTblPanel.add(jScrollPane);
//            this.mdpTblPanel.add(west);
            this.mdpTblPanel.add(panel);

        }
    }

    void dataTablePromotionReload(){
        Promotion promotion1 = new Promotion();
        promotion1.setNetId(  CcCarUtil.currentEmp.getNetId());

        try {
//            btnAddNewPromoCompleteEvent();
            dataTablePromotion.all=JdbcUtil.getBy(promotion1);
            dataTablePromotion.reloadTableNoSearch();
        } catch (SQLException | BaseException ex) {
            ex.printStackTrace();
            UiUtil.showError("查询失败 "+ex.getMessage());
        }

    }
    void loadData() throws BaseException, SQLException {

//        Connection connection = DBUtil.getConnection();
//        Integer currentEmpNetId = CcCarUtil.currentEmp.getNetId();
//        CarInfo carInfo = new CarInfo();
//        carInfo.setNetId(currentEmpNetId);
//        dataTableCarInfo.all = JdbcUtil.getBy(connection, carInfo);
//        dataTableCarInfo.reloadTableNoSearch();
        dataTablePromotionReload();
        tblCarType.reloadTable();

//        dataTableCarInfo.all=
//        dataTableCarInfo.reloadTable();
//        ca.reloadTable();
//        netInfoTblData.reloadTable();
//        tblNetBack.reloadTable();
//        dataTableCoupon.reloadTable();
//        员工 可以看所有优惠券吗，也只是自己网点的


//        Promotion promotion = new Promotion();
//        promotion.setNetId(CcCarUtil.currentEmp.getNetId());
//        dataTablePromotion.all = JdbcUtil.getBy(connection, promotion);
//        dataTablePromotion.reloadTableNoSearch();

//        DBUtil.closeConnection(connection);

    }

    void setUpFrmTableCoupon() {
        frmTableCoupon.getContentPane().add(new JScrollPane(dataTableCoupon));
        frmTableCoupon.setSize(400, 500);
        frmTableCoupon.setLocation(410 * 3, 300);//在屏幕中设置显示的位置

    }


    JPanel borderTable(String title, JTable table) {
        JPanel border = new JPanel(new BorderLayout());
        border.setBorder(BorderFactory.createTitledBorder(title));
        border.add(new JScrollPane(table));
        border.setPreferredSize(new Dimension(256, 0));
        return border;
    }

    void showFrame(String title, Component component, int x) {
        JFrame frame = new JFrame(title);
        frame.getContentPane().add(component);
        frame.setSize(400, 500);
        frame.setLocation(x, 300);//在屏幕中设置显示的位置

//        UiUtil
//        x+=410;
        frame.setVisible(true);
    }

    void showTable(String title, Component component, int x) {
        showFrame(title, new JScrollPane(component), x);

    }


    public static void main(String[] args) throws BaseException, SQLException {
        FrmOrder frmOrder = new FrmOrder();
//    文字是 口
//    convert  gbk 之后 可以了
        frmOrder.setVisible(true);
    }
}