
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

public class FrmGetCoupon extends JFrame implements ActionListener {
    private static final long serialVersionUID = -990815448731736703L;
    //  网点 两个
//    NetInfo currentNetInfo = null;
//    private Object[] titlesNetInfo = NetInfo.tableTitles;
//    private Object[][] tblDataNetInfo;
//    DefaultTableModel tabModelNetInfo = new DefaultTableModel();
    //    private JTable dataTableNetInfo = new JTable(tabModelNetInfo);
    TblData<NetInfo> netInfoTblData;
    TblData<Coupon> couponTblData;

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


    //    一开始 不加载
    public void loadNowCouponNetHave() {
        Date date = new Date();
//        TimeUtil.dato/
        String nowTimeStr = DateUtil.dateToString(date, TimeUtil.fmtYmd);
//            String edtGetTimeText = edt.getText();
        String addSql = String.format(" and '%s' >=  start_time  and '%s' <= end_time "
                , nowTimeStr, nowTimeStr);
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
//            Promotion promotion = new Promotion();
////                限时促销的车型，必须在特定的网点，特定的车型，及特定的时间段才能享受
//            promotion.setNetId(dataTableCarInfo.current.getNetId());
//            promotion.setTypeId(dataTableCarInfo.current.getTypeId());
////                List<Promotion> by = JdbcUtil.getBy(new Promotion(), addSql);
//            List<Promotion> by = JdbcUtil.getBy(connection,promotion, addSql);
//            dataTablePromotion.all = by;
//            dataTablePromotion.reloadTableNoSearch();

            Coupon coupon = new Coupon();
//            这里的 这个 不是 order那边的 这里是空的
            coupon.setNetId(dataTableCarInfo.current.getNetId());
            coupon.setStatus(1);
//            没有领取的
//            String  name;
//
//          int intVal=  Integer.valueOf(name);
//            List<Coupon> coupons = JdbcUtil.getBy(connection,coupon, addSql);
            List<Coupon> coupons = JdbcUtil.getByAddSql(connection, coupon, addSql);
//          frmGetCoupon.  dataTableCoupon.all = coupons;
//            为什么 这里要 加载。。 哦哦 优惠券 还有时间的。。。
//            这 需要 加载吗
            this.couponTblData.all = coupons;
//            查询没有领取的
//            frmGetCoupon.   dataTableCoupon.reloadTableNoSearch();
            this.couponTblData.reloadTableNoSearch();
        } catch (SQLException | BaseException ex) {
            ex.printStackTrace();
            UiUtil.showError(ex.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    //  优惠券
//    Coupon currentCoupon = null;
//    private Object[] titlesCoupon = Coupon.tableTitles;
//    private Object[][] tblDataCoupon;
//    DefaultTableModel tabModelCoupon = new DefaultTableModel();
    //    private JTable dataTableCoupon = new JTable(tabModelCoupon);
//    private TblData<Coupon> dataTableCoupon;
    public TblData<Coupon> dataTableCoupon;
    //    这里还没有new 出来
//    List<Coupon> allCoupon = null;
//
//    //    限时促销
//    Promotion currentPromotion = null;
//    private Object[] titlesPromotion = Promotion.tableTitles;
//    private Object[][] tblDataPromotion;
//    DefaultTableModel tabModelPromotion = new DefaultTableModel();
    //    private JTable dataTablePromotion = new JTable(tabModelPromotion);
//    private TblData<Promotion> dataTablePromotion;
    public TblData<Promotion> dataTablePromotion;
//    List<Promotion> allPromotion = null;

    TblData<NetInfo> tblNetBack;

    TblData<TblOrder> tblOrder = new TblData<>(TblOrder.tableTitles,
            CcCarUtil.tblOrderDao);

    TblData<User> tblUser = new TblData<>(User.tableTitles,
            CcCarUtil.userDao);

//    好像不需要是 类里面定义的？ 还是需要的
//    private TblData<NetInfo>  tblNetBack;

//
//  // 商家
//  private BeanMerchant currentMerchant = null;
//  private Object tblMerchantTitle[] = BeanMerchant.tableTitles;
//  private Object tblMerchantData[][];
//  DefaultTableModel tabMerchantModel = new DefaultTableModel();
//  private JTable dataTableMerchant = new JTable(tabMerchantModel);
//  List<BeanMerchant> allMerchant = null;
//  // 商品目录
//  private BeanProduct_Dir currentProduct_Dir = null;
//  private Object tblProduct_DirTitle[] = BeanProduct_Dir.tableTitles;
//  private Object tblProduct_DirData[][];
//  DefaultTableModel tabProduct_DirModel = new DefaultTableModel();
//  private JTable dataTableProduct_Dir = new JTable(tabProduct_DirModel);
//  List<BeanProduct_Dir> allProduct_Dir = null;
//  // 商品信息
//  private BeanProduct currentProduct = null;
//  private Object tblProductTitles[] = BeanProduct.tableTitles;
//  private Object tblProductData[][];
//  DefaultTableModel tabProductModel = new DefaultTableModel();
//  private JTable dataTableProduct = new JTable(tabProductModel);
//  List<BeanProduct> allProduct = null;
//  // 订单的商品详情信息
//  // private BeanOrderProjects currentOrderProjects = null;
//  private Object tblOrderProjectsTitles[] = BeanOrderProjects.tableTitles;
//  private Object tblOrderProjectsData[][];
//  DefaultTableModel tabOrderProjectsModel = new DefaultTableModel();
//  private JTable dataTableOrderProjects = new JTable(tabOrderProjectsModel);
//  List<BeanOrderProjects> allOrderProjects = null;

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
    private JButton btnAddNewRow = new JButton("增加新的一行");
    private JButton btnGetCoupon = new JButton("领取优惠券");
    public JButton btnGet = new JButton("领取优惠券");
    TblOrder order = new TblOrder();


    private JLabel labelGetTime = new JLabel("edtGetTime：");
    private JLabel labelRetTime = new JLabel("edtRetTime：");
    private JLabel labelRentalDur = new JLabel("edtRentalDur：");

    private JTextField edtGetTime = new JTextField(20);
    private JTextField edtRetTime = new JTextField(20);
    //    借的时间
    private JTextField edtRentalDur = new JTextField(20);
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
                finalPrice, 1

        );
//        1 是下单状态
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
            btnPromotionSearchEvent();
        } else if (e.getSource() == this.btnGetCoupon) {
            btnGetCouponEvent();
        } else if (e.getSource() == this.btnGet) {
            try {
                btnGetEvent();

            } catch (SQLException | BaseException ex) {
                ex.printStackTrace();
            }
        }

//        else if (e.getSource() == this.btnAddNewRow) {
//
//            dataTableCoupon.addEmptyRow();
//
//        }
    }

//    void btnGetEvent() throws SQLException, DbException {
////        private  拿不到
//// 只能数据 在 那边，这里
////        不行的
////        FrmOrder.this.dataTableCoupon.current;
////        FrmOrder.this.couponGet();
//        couponTblData.current.setStatus(2);
////        不用设置 状态吧
////    todo    Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
//        JdbcUtil.update(couponTblData.current, couponTblData.current.getCouponId(),
//                "coupon_id");
////        状态（未被领取1，已经被领取2，已经使用3）
//    }

    //    这样的话 ，优惠券 就和 数据库 无关 只是 内存操作
//    一个 搜索 就全没了 。 就当 有 用户 id 吧，不然太难了
//    就算 知道 是有用户的 ，他的 状态也是需要的吧，不然用户可以无限使用了
    void btnGetEvent() throws SQLException, BaseException {
//        private  拿不到
// 只能数据 在 那边，这里
//        不行的
//        FrmOrder.this.dataTableCoupon.current;
//        FrmOrder.this.couponGet();
        if (couponTblData.current == null) {
            UiUtil.showError("请选择优惠券");
            return;
        }
        couponTblData.current.setStatus(2);
//        状态（未被领取1，已经被领取2，已经使用3）
        couponTblData.current.setUserId(User.currentLoginUser.getUserId());
//        不用设置 状态吧
//    todo    Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
        JdbcUtil.update(couponTblData.current, couponTblData.current.getCouponId(),
                "coupon_id");

        loadData(netId, timeStr);
        father.loadCouponByTimeStr(timeStr);
//        tdCouponFather.reloadDataAndRepaint();

//        父亲 那边 就不load了
//        Coupon coupon = new Coupon();
//        coupon.setUserId(User.currentLoginUser.getUserId());
//        coupon.setStatus(2);
//        需要根据 时间 的
//        tdCouponFather.reloadDataAndRepaint(coupon);
//        状态（未被领取1，已经被领取2，已经使用3）
    }

    //    两个表  自己的优惠券 和 所有优惠券
    void btnGetCouponEvent() {
//        现在的优惠券  领取
//        如果他一个人 把所有优惠券领取了呢？
//        每个人有属于他自己的优惠券
    }

    //    一开始 不加载
    void loadNowCoupon() {
        Date date = new Date();
//        TimeUtil.dato/
        String nowTimeStr = DateUtil.dateToString(date, TimeUtil.fmtYmd);
//            String edtGetTimeText = edt.getText();
        String addSql = String.format(" and '%s' >=  start_time  and '%s' <= end_time "
                , nowTimeStr, nowTimeStr);
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
//            Promotion promotion = new Promotion();
////                限时促销的车型，必须在特定的网点，特定的车型，及特定的时间段才能享受
//            promotion.setNetId(dataTableCarInfo.current.getNetId());
//            promotion.setTypeId(dataTableCarInfo.current.getTypeId());
////                List<Promotion> by = JdbcUtil.getBy(new Promotion(), addSql);
//            List<Promotion> by = JdbcUtil.getBy(connection,promotion, addSql);
//            dataTablePromotion.all = by;
//            dataTablePromotion.reloadTableNoSearch();

            Coupon coupon = new Coupon();
//            这里的 这个 不是 order那边的 这里是空的
            coupon.setNetId(dataTableCarInfo.current.getNetId());
            coupon.setStatus(1);
//            没有领取的
//            List<Coupon> coupons = JdbcUtil.getBy(connection,coupon, addSql);
            List<Coupon> coupons = JdbcUtil.getByAddSql(connection, coupon, addSql);
            dataTableCoupon.all = coupons;
//            查询没有领取的
            dataTableCoupon.reloadTableNoSearch();
        } catch (SQLException | BaseException ex) {
            ex.printStackTrace();
            UiUtil.showError(ex.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    //    促销数量要大于0
//    一开始没有选车 还不要 load
    void loadNowPromByTimeStr(String timeStr) {

//            String edtGetTimeText = edt.getText();
        String addSql = String.format(" and '%s' >=  start_time  and '%s' <= end_time  and prom_quantity >0   "
                , timeStr, timeStr);
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            Promotion promotion = new Promotion();
//                限时促销的车型，必须在特定的网点，特定的车型，及特定的时间段才能享受
            promotion.setNetId(dataTableCarInfo.current.getNetId());
            promotion.setTypeId(dataTableCarInfo.current.getTypeId());
//                List<Promotion> by = JdbcUtil.getBy(new Promotion(), addSql);
            List<Promotion> by = JdbcUtil.getBy(connection, promotion, addSql);
            dataTablePromotion.all = by;
            dataTablePromotion.reloadTableNoSearch();

//            Coupon coupon=new Coupon();
//            coupon.setNetId(dataTableCarInfo.current.getNetId());
//            coupon.setStatus(1);
////            没有领取的
//            List<Coupon> coupons = JdbcUtil.getBy(connection,coupon, addSql);
//            dataTableCoupon.all = coupons;
//            dataTableCoupon.reloadTableNoSearch();
        } catch (SQLException | BaseException ex) {
            ex.printStackTrace();
            UiUtil.showError(ex.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
        }
    }


    void loadNowProm() {

//        String edtGetTimeText = edtGetTime.getText();
////            如果 全部列出来 都可以用了
//        Date date = TimeUtil.stringtoDate(edtGetTimeText, TimeUtil.fmtYmd);
//        if (date == null) {
//            UiUtil.showError("日期格式异常");
//            return;
//        }
        Date date = new Date();
//        TimeUtil.dato/
        String edtGetTimeText = DateUtil.dateToString(date, TimeUtil.fmtYmd);
        loadNowPromByTimeStr(edtGetTimeText);


////            String edtGetTimeText = edt.getText();
//        String addSql = String.format(" and '%s' >=  start_time  and '%s' <= end_time "
//                , edtGetTimeText, edtGetTimeText);
//        try {
//            Promotion promotion = new Promotion();
////                限时促销的车型，必须在特定的网点，特定的车型，及特定的时间段才能享受
//            promotion.setNetId(dataTableCarInfo.current.getNetId());
//            promotion.setTypeId(dataTableCarInfo.current.getTypeId());
////                List<Promotion> by = JdbcUtil.getBy(new Promotion(), addSql);
//            List<Promotion> by = JdbcUtil.getBy(promotion, addSql);
//            dataTablePromotion.all = by;
//            dataTablePromotion.reloadTableNoSearch();
//        } catch (SQLException | BaseException ex) {
//            ex.printStackTrace();
//            UiUtil.showError(ex.getMessage());
//        }
    }

    void btnPromotionSearchEvent() {

        String edtGetTimeText = edtGetTime.getText();
//            如果 全部列出来 都可以用了
        Date date = TimeUtil.stringtoDate(edtGetTimeText, TimeUtil.fmtYmd);
        if (date == null) {
            UiUtil.showError("日期格式异常");
            return;
        }

        loadNowPromByTimeStr(edtGetTimeText);

//
////            String edtGetTimeText = edt.getText();
//        String addSql = String.format(" and '%s' >=  start_time  and '%s' <= end_time "
//                , edtGetTimeText, edtGetTimeText);
//        try {
//            Promotion promotion = new Promotion();
////                限时促销的车型，必须在特定的网点，特定的车型，及特定的时间段才能享受
//            promotion.setNetId(dataTableCarInfo.current.getNetId());
//            promotion.setTypeId(dataTableCarInfo.current.getTypeId());
////                List<Promotion> by = JdbcUtil.getBy(new Promotion(), addSql);
//            List<Promotion> by = JdbcUtil.getBy(promotion, addSql);
//            dataTablePromotion.all = by;
//            dataTablePromotion.reloadTableNoSearch();
//        } catch (SQLException | BaseException ex) {
//            ex.printStackTrace();
//            UiUtil.showError(ex.getMessage());
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

    public FrmGetCoupon() throws BaseException, SQLException {
//            Bus.fUserGo = this;
        this.setTitle("领取优惠券");
//            this.setSize(1318, 838);
//        this.setSize(1800, 1000);
        this.setSize(400, 700);
        this.setLocationRelativeTo(null);
//        this.setLocation(10, 400);
//        中间左右  400
//        this.setLocation(10, 800);
//        this.setLocation(10, 10);
        this.setLocation(600, 10);
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

//        JPanel pnlBottom = new JPanel(new BorderLayout());
//
//        pnlBottom.add(this.toolBarPanel, BorderLayout.NORTH);
//
//        pnlBottom.add(this.inputPanel, BorderLayout.SOUTH);
//
//        contentPane.add(pnlBottom, BorderLayout.SOUTH);

        setBottom(contentPane);

//        List<JButton> btnList = new ArrayList<>();
//        btnList.add(this.exit);
//        btnList.add(this.add);
//        btnList.add(this.unadd);
//        btnList.add(this.done);
//        btnList.add(this.btnUserInfo);
//        btnList.add(this.btnOrder);
//        btnList.add(this.btnPromotionSearch);
//        btnList.add(this.btnAddNewRow);
//        btnList.add(this.btnScrap);


        // toolBarPanel 设置
//        toolBarPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        toolBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//        for (JButton jButton : btnList) {
//            toolBarPanel.add(jButton);
//            jButton.addActionListener(this);
////            this.edtGetTime.getPreferredSize()
//        }


        setUpButtons();

//        inputPanel.add(labelGetTime);
//        inputPanel.add(edtGetTime);
//
//        inputPanel.add(labelRetTime);
//        inputPanel.add(edtRetTime);


//        inputPanel.add(labelRentalDur);
//        inputPanel.add(edtRentalDur);


        setUpTables();
        loadData();


//        List<TblData> tblDataList = new ArrayList<>();
//
//
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
////        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
////                tblDataPromotion, tabModelPromotion, allPromotion, CcCarUtil.promotionDao);
//        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
//                CcCarUtil.promotionDao);
//
//
////        tblDataList.add(netInfoTblData);
//        tblDataList.add(dataTableCarInfo);
//
//        tblDataList.add(tblNetBack);
//        tblDataList.add(dataTableCoupon);
//        tblDataList.add(dataTablePromotion);
//
////        dataTableCoupon.setVisible(false);
////        dataTablePromotion.setVisible(false);
////        数据貌似没有写入  难道因为泛型？
//        // TODO: 2021/9/5  dataTablePromotion 一开始 不显示
//
////        pnlTitles.setLayout(new GridLayout(1,6));
////            pnlTitles.add(new JTextField("1"));
////            contentPane.add(pnlTitles,BorderLayout.NORTH);
//
//
//        int x = 0;
//        int y = 0;
//        int titleIdx = 0;
//        String[] titles = new String[]{"车", "还车网点", "优惠券", "限时优惠"};
////        String[] titles = new String[]{"车", "还车网点", "限时优惠"};
//        for (TblData tblData : tblDataList) {
//
//
//            JPanel panel = borderTable(titles[titleIdx++], tblData);
//
////            this.mdpTblPanel.add(jScrollPane);
////            this.mdpTblPanel.add(west);
//            this.mdpTblPanel.add(panel);
//
//        }
//        dataTableCarInfo.reloadTable();
////        ca.reloadTable();
////        netInfoTblData.reloadTable();
//        tblNetBack.reloadTable();
////        dataTableCoupon.reloadTable();


//        init();
//
//        jScrollPane1.setViewportView(this.netInfoTblData);
//        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 320, 270));


//        getContentPane().add(jScrollPane1,
//                new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 320, 270));

//        优惠券 一开始 不用 加载

        addListenerCar();

//
//        this.dataTableCarInfo.addMouseListener(new MouseAdapter() {
//
//            //            mouseClicked 怎么获取外面的数据
////            这是引用 只要弄到一次 以后都可以了
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//                TblData<CarInfo> tableCarInfo = FrmOrder.this.dataTableCarInfo;
//                int i = tableCarInfo.getSelectedRow();
////                这个知道是自己吗
//                if (i < 0) {
//                    return;
//                }
//                TblData<Coupon> tableCoupon = FrmOrder.this.dataTableCoupon;
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

//        setUpFrmTableCoupon();
        setUpFrmOrder();
    }

    TblData<Coupon> tdCouponFather;
    String timeStr;
    Integer netId;

    void setUp(Integer netId, TblData<Coupon> tdCouponFather,
               String timeStr) throws BaseException, SQLException {
        //            Bus.fUserGo = this;
        this.setTitle("领取优惠券");
//            this.setSize(1318, 838);
        this.tdCouponFather = tdCouponFather;
        this.netId = netId;
        this.timeStr = timeStr;
//        this.setSize(1800, 1000);
//        this.setSize(400, 700);
        this.setSize(800, 700);
        this.setLocationRelativeTo(null);
//        this.setLocation(10, 400);
//        中间左右  400
//        this.setLocation(10, 800);
//        this.setLocation(10, 10);
        this.setLocation(600, 10);
        // this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);

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

//        JPanel pnlBottom = new JPanel(new BorderLayout());
//
//        pnlBottom.add(this.toolBarPanel, BorderLayout.NORTH);
//
//        pnlBottom.add(this.inputPanel, BorderLayout.SOUTH);
//
//        contentPane.add(pnlBottom, BorderLayout.SOUTH);

        setBottom(contentPane);

//        List<JButton> btnList = new ArrayList<>();
//        btnList.add(this.exit);
//        btnList.add(this.add);
//        btnList.add(this.unadd);
//        btnList.add(this.done);
//        btnList.add(this.btnUserInfo);
//        btnList.add(this.btnOrder);
//        btnList.add(this.btnPromotionSearch);
//        btnList.add(this.btnAddNewRow);
//        btnList.add(this.btnScrap);


        // toolBarPanel 设置
//        toolBarPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        toolBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//
//        for (JButton jButton : btnList) {
//            toolBarPanel.add(jButton);
//            jButton.addActionListener(this);
////            this.edtGetTime.getPreferredSize()
//        }


        setUpButtons();

//        inputPanel.add(labelGetTime);
//        inputPanel.add(edtGetTime);
//
//        inputPanel.add(labelRetTime);
//        inputPanel.add(edtRetTime);


//        inputPanel.add(labelRentalDur);
//        inputPanel.add(edtRentalDur);


        setUpTables();
//        loadData();
//        loadData(netId);
        loadData(netId, timeStr);


//        List<TblData> tblDataList = new ArrayList<>();
//
//
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
////        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
////                tblDataPromotion, tabModelPromotion, allPromotion, CcCarUtil.promotionDao);
//        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
//                CcCarUtil.promotionDao);
//
//
////        tblDataList.add(netInfoTblData);
//        tblDataList.add(dataTableCarInfo);
//
//        tblDataList.add(tblNetBack);
//        tblDataList.add(dataTableCoupon);
//        tblDataList.add(dataTablePromotion);
//
////        dataTableCoupon.setVisible(false);
////        dataTablePromotion.setVisible(false);
////        数据貌似没有写入  难道因为泛型？
//        // TODO: 2021/9/5  dataTablePromotion 一开始 不显示
//
////        pnlTitles.setLayout(new GridLayout(1,6));
////            pnlTitles.add(new JTextField("1"));
////            contentPane.add(pnlTitles,BorderLayout.NORTH);
//
//
//        int x = 0;
//        int y = 0;
//        int titleIdx = 0;
//        String[] titles = new String[]{"车", "还车网点", "优惠券", "限时优惠"};
////        String[] titles = new String[]{"车", "还车网点", "限时优惠"};
//        for (TblData tblData : tblDataList) {
//
//
//            JPanel panel = borderTable(titles[titleIdx++], tblData);
//
////            this.mdpTblPanel.add(jScrollPane);
////            this.mdpTblPanel.add(west);
//            this.mdpTblPanel.add(panel);
//
//        }
//        dataTableCarInfo.reloadTable();
////        ca.reloadTable();
////        netInfoTblData.reloadTable();
//        tblNetBack.reloadTable();
////        dataTableCoupon.reloadTable();


//        init();
//
//        jScrollPane1.setViewportView(this.netInfoTblData);
//        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 320, 270));


//        getContentPane().add(jScrollPane1,
//                new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 50, 320, 270));

//        优惠券 一开始 不用 加载

        addListenerCar();

        setUpFrmOrder();
    }

    public FrmGetCoupon(Integer netId, TblData<Coupon> tdCouponFather,
                        String timeStr) throws BaseException,
            SQLException {
        setUp(netId, tdCouponFather, timeStr);
    }

    FrmOrder father;

    public FrmGetCoupon(FrmOrder father) throws BaseException,
            SQLException {
//            Bus.fUserGo = this;
        this(father.dataTableCarInfo.current.getNetId(),
                father.dataTableCouponUser, father.edtGetTime.getText());
        this.father = father;

    }


    void setBottom(Container contentPane) {
        JPanel pnlBottom = new JPanel(new BorderLayout());

        pnlBottom.add(this.toolBarPanel, BorderLayout.NORTH);

        pnlBottom.add(this.inputPanel, BorderLayout.SOUTH);

        contentPane.add(pnlBottom, BorderLayout.SOUTH);
    }

    //    点 车子 显示 这个网点的 优惠券， 不需要了，emp 已经只能是自己网点
    void addListenerCar() {

        this.dataTableCarInfo.addMouseListener(new MouseAdapter() {

            //            mouseClicked 怎么获取外面的数据
//            这是引用 只要弄到一次 以后都可以了
            @Override
            public void mouseClicked(MouseEvent e) {

                TblData<CarInfo> tableCarInfo = FrmGetCoupon.this.dataTableCarInfo;
                int i = tableCarInfo.getSelectedRow();
//                这个知道是自己吗
                if (i < 0) {
                    return;
                }


                loadNowCoupon();
                loadNowProm();
//                TblData<Coupon> tableCoupon = FrmGetCoupon.this.dataTableCoupon;
//                Coupon coupon = new Coupon();
//                Integer currentNetId = tableCarInfo.current.getNetId();
////                coupon.setNetId(tableCarInfo.current.getNetId());
//                coupon.setNetId(currentNetId);
//                Connection connection=null;
//                try {
//                    connection = DBUtil.getConnection();
//                    tableCoupon.all = JdbcUtil.getBy(connection,coupon);
//                    tableCoupon.reloadTableByThisList();
//
//                    Promotion promotion = new Promotion();
//                    promotion.setNetId(currentNetId);
////                    dataTablePromotion.all = JdbcUtil.getBy(coupon);
//                    dataTablePromotion.all = JdbcUtil.getBy(connection,promotion);
//                    dataTablePromotion.reloadTableByThisList();

//                    showFrame("优惠券",new ScrollPane( tableCoupon), 410 * 3);

//                    showFrame("优惠券", tableCoupon, 410 * 3);
//                    showTable("优惠券", tableCoupon, 410 * 3);

//                    JFrame frame = new JFrame("优惠券");
//                    frmTableCoupon= new JFrame("优惠券");
                // TODO: 2021/9/5 cop
//                    frmTableCoupon.
//                    frmTableCoupon.setVisible(true);
//                    FrmOrder.this.dataTableCoupon.setVisible(true);


//                    loadNowCoupon();
//                    loadNowProm();

//                }

//                catch (SQLException ex) {
//                    ex.printStackTrace();
//                    UiUtil.showError("获取优惠券失败 " + ex.getMessage());
//                }

//                finally {
//                    DBUtil.closeConnection(connection);
//                }
//                这样可以的
//                tableCarInfo.current = tableCarInfo.all.get(i);


            }
        });
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
////        btnList.add(this.btnAddNewPromo);
//        btnList.add(this.btnAddNewRow);
//        btnList.add(this.btnGetCoupon);
        btnList.add(this.btnGet);
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


        this.netInfoTblData = new TblData<>(NetInfo.tableTitles,
                CcCarUtil.netInfoDao);
//        因为这个 确实没了


        this.tblNetBack = new TblData<>(NetInfo.tableTitles,
                CcCarUtil.netInfoDao);
//        因为没有 reload
        this.dataTableCarInfo = new TblData<>(CarInfo.tableTitles,
                CcCarUtil.carInfoDao);
//        this.dataTableCoupon = new TblData<>(Coupon.tableTitles,
//                tblDataCoupon, tabModelCoupon, allCoupon, CcCarUtil.couponDao);
        this.dataTableCoupon = new TblData<>(Coupon.tableTitles,
                CcCarUtil.couponDao);
//        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
//                tblDataPromotion, tabModelPromotion, allPromotion, CcCarUtil.promotionDao);
        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
                CcCarUtil.promotionDao);
//        网点优惠券
        couponTblData = new TblData<>(Coupon.tableTitles,
                CcCarUtil.couponDao);

//        tblDataList.add(netInfoTblData);
//        tblDataList.add(dataTableCarInfo);
//
//        tblDataList.add(tblNetBack);
//        tblDataList.add(dataTableCoupon);
//        tblDataList.add(dataTablePromotion);
        tblDataList.add(couponTblData);

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
//        String[] titles = new String[]{"车", "还车网点", "优惠券", "限时优惠"};
        String[] titles = new String[]{"优惠券"};
//        String[] titles = new String[]{"车", "还车网点", "限时优惠"};
        for (TblData tblData : tblDataList) {


            JPanel panel = borderTable(titles[titleIdx++], tblData);

//            this.mdpTblPanel.add(jScrollPane);
//            this.mdpTblPanel.add(west);
            this.mdpTblPanel.add(panel);

        }
    }

    void loadDataEmp() throws SQLException, BaseException {

        Connection connection = DBUtil.getConnection();
        Integer currentEmpNetId = CcCarUtil.currentEmp.getNetId();
        CarInfo carInfo = new CarInfo();
        carInfo.setNetId(currentEmpNetId);
        dataTableCarInfo.all = JdbcUtil.getBy(connection, carInfo);
        dataTableCarInfo.reloadTableNoSearch();
//        dataTableCarInfo.all=
//        dataTableCarInfo.reloadTable();
//        ca.reloadTable();
//        netInfoTblData.reloadTable();
//        tblNetBack.reloadTable();
//        dataTableCoupon.reloadTable();
//        员工 可以看所有优惠券吗，也只是自己网点的
        Promotion promotion = new Promotion();
        promotion.setNetId(CcCarUtil.currentEmp.getNetId());
        dataTablePromotion.all = JdbcUtil.getBy(connection, promotion);
        dataTablePromotion.reloadTableNoSearch();

        DBUtil.closeConnection(connection);
    }

    //    这是用户，可以看 网点的优惠券
    void loadData() throws BaseException, SQLException {

//        Connection connection = DBUtil.getConnection();
//        Integer currentEmpNetId = CcCarUtil.currentEmp.getNetId();
//        CarInfo carInfo = new CarInfo();
//        carInfo.setNetId(currentEmpNetId);
//        dataTableCarInfo.all = JdbcUtil.getBy(connection, carInfo);
//        dataTableCarInfo.reloadTableNoSearch();
//
//        Promotion promotion = new Promotion();
//        promotion.setNetId(CcCarUtil.currentEmp.getNetId());
//        dataTablePromotion.all = JdbcUtil.getBy(connection, promotion);
//        dataTablePromotion.reloadTableNoSearch();
//
//        DBUtil.closeConnection(connection);
//


//        dataTableCarInfo.all=
//        dataTableCarInfo.reloadTable();
////        ca.reloadTable();
////        netInfoTblData.reloadTable();
//        tblNetBack.reloadTable();
//        dataTableCoupon.reloadTable();
//        需要 net id ，user id （不是这个 user的 ，还有状态
//        Coupon coupon = new Coupon();
//        dataTableCoupon.reloadTable();
//        Coupon coupon = new Coupon();
//        coupon.setNetId();
//        loadNowCoupon();
//        领取优惠券的 界面 一开始也不加载吗
//        一开始没有选择 车 不要 load
        // TODO: 2021/9/7 显示自己的优惠券 领取网点优惠券
//        loadNowProm();
//        员工 可以看所有优惠券吗，也只是自己网点的


    }


    void loadData(Integer netId, String timeStr) throws BaseException, SQLException {


//        dataTableCarInfo.all=
//        dataTableCarInfo.reloadTable();
////        ca.reloadTable();
////        netInfoTblData.reloadTable();
//        tblNetBack.reloadTable();
//        dataTableCoupon.reloadTable();
        Coupon coupon = new Coupon();
        coupon.setNetId(netId);
        coupon.setStatus(1);
//        coupon.setUserId(0);
//        但是 必须要 userid 是null的 ，就0 吧
//        这样 需要 new 出来一个 frm
//        如果已经是这个 user 的 也不 加载了
//        新增 userid。，状态（未被领取1，已经被领取2，已经使用3）

        String addSql = String.format(" and '%s' >=  start_time  and '%s' <= end_time "
                , timeStr, timeStr);
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
//            Promotion promotion = new Promotion();
////                限时促销的车型，必须在特定的网点，特定的车型，及特定的时间段才能享受
//            promotion.setNetId(dataTableCarInfo.current.getNetId());
//            promotion.setTypeId(dataTableCarInfo.current.getTypeId());
////                List<Promotion> by = JdbcUtil.getBy(new Promotion(), addSql);
//            List<Promotion> by = JdbcUtil.getBy(connection,promotion, addSql);
//            dataTablePromotion.all = by;
//            dataTablePromotion.reloadTableNoSearch();

//            Coupon coupon = new Coupon();
//            coupon.setNetId(dataTableCarInfo.current.getNetId());
//            coupon.setStatus(1);
//            coupon.setStatus(2);
//            已经领取的  这个人的
//            List<Coupon> coupons = JdbcUtil.getBy(connection,coupon, addSql);
            List<Coupon> coupons = JdbcUtil.getByAddSql(connection, coupon, addSql);
            couponTblData.all = coupons;
            couponTblData.reloadTableNoSearch();
        } catch (SQLException | BaseException ex) {
            ex.printStackTrace();
            UiUtil.showError(ex.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
        }


        couponTblData.reloadDataAndRepaint(coupon);
//        loadNowCoupon();
//        领取优惠券的 界面 一开始也不加载吗
//        一开始没有选择 车 不要 load
        // TODO: 2021/9/7 显示自己的优惠券 领取网点优惠券
//        loadNowProm();
//        员工 可以看所有优惠券吗，也只是自己网点的


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