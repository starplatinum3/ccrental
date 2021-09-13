/**
 * @Author: SevDaisy十七散人
 * @Date: 2020-07-05 16:29:00
 */
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
import java.util.Date;
import java.util.List;

import javax.swing.*;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.test.DisplayImageIcon;
import cn.edu.zucc.personplan.util.*;
//import zyy.model.BeanMerchant;
//import zyy.model.BeanOrderProjects;
//import zyy.model.BeanProduct;
//import zyy.model.BeanProduct_Dir;
//import zyy.util.BaseException;
//import zyy.util.Bus;
//import zyy.util.BusinessException;

public class FrmOrder extends JFrame implements ActionListener {
    //  网点 两个
//    NetInfo currentNetInfo = null;
//    private Object[] titlesNetInfo = NetInfo.tableTitles;
//    private Object[][] tblDataNetInfo;
//    DefaultTableModel tabModelNetInfo = new DefaultTableModel();
    //    private JTable dataTableNetInfo = new JTable(tabModelNetInfo);
    TblData<NetInfo> netInfoTblData;
    TblData<NetInfo> tddNetGet;

//    JTextField
//    List<NetInfo> allNetInfo = null;

//  user stat

    //  车
//    这些数据 这里是需要的 ，但是好像也不是
//    CarInfo currentCarInfo = null;
//    private Object[] titlesCarInfo = CarInfo.tableTitles;
//    private Object[][] tblDataCarInfo;
//    DefaultTableModel tabModelCarInfo = new DefaultTableModel();
    //    private JTable dataTableCarInfo ;
//    private TblData<CarInfo> dataTableCarInfo;
    public TblDataDo<CarInfo> dataTableCarInfo;
    //    private JTable dataTableCarInfo = new JTable(tabModelCarInfo);
//    List<CarInfo> allCarInfo = null;


    //  优惠券
//    Coupon currentCoupon = null;
//    private Object[] titlesCoupon = Coupon.tableTitles;
//    private Object[][] tblDataCoupon;
//    DefaultTableModel tabModelCoupon = new DefaultTableModel();
    //    private JTable dataTableCoupon = new JTable(tabModelCoupon);
//     TblData<Coupon> dataTableCouponUser;
    TblData<Coupon> dataTableCouponUser;
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
    //    private JButton btnPromotionSearch = new JButton("查询限时优惠");
    private JButton btnPromotionSearch = new JButton("查询优惠信息");
    private JButton btnAddNewRow = new JButton("增加新的一行");
    private JButton btnGetCoupon = new JButton("领取优惠券");
    private JButton btnRefresh = new JButton("刷新");
    private JButton btnUserOrder = new JButton("打开您的订单信息");
    private JButton btnPic = new JButton("查看车型图片");
    private JButton btnSearch = new JButton("搜索");
    TblOrder order = new TblOrder();


    private JLabel labelGetTime = new JLabel("取车时间：");
    private JLabel labelRetTime = new JLabel("还车时间：");
    private JLabel labelRentalDur = new JLabel("edtRentalDur：");
    private JLabel lblCity = new JLabel("提车城市：");
    private JLabel lblCityRet = new JLabel("还车城市：");
    private JLabel lblAddr = new JLabel("提车地址：");
    private JLabel lblAddrRet = new JLabel("还车地址：");

//    public JTextField edtGetTime = new JTextField(20);
//    private JTextField edtRetTime = new JTextField(20);
//    //    借的时间
//    private JTextField edtRentalDur = new JTextField(20);
//    private JTextField edtCity = new JTextField(20);
//    private JTextField edtCityRet = new JTextField(20);
//    private JTextField edtAddr = new JTextField(20);
//    private JTextField edtAddrRet = new JTextField(20);


    public JTextField edtGetTime = new JTextField(14);
    private JTextField edtRetTime = new JTextField(14);
    //    借的时间
    private JTextField edtRentalDur = new JTextField(14);
    private JTextField edtCity = new JTextField(14);
    private JTextField edtCityRet = new JTextField(14);
    private JTextField edtAddr = new JTextField(14);
    private JTextField edtAddrRet = new JTextField(14);


//    private JTextField edtGetTime = new JTextField(20);
//    raw_price  算出来的

    //    另外一个界面的 时候 只要 复制 然后 改名字
//    不能扔东西
//    actionPerformed 怎么抛出异常

    //    FrmGetCoupon frmGetCoupon ;
//    FrmGetCoupon  frmGetCoupon=new FrmGetCoupon();
    FrmGetCoupon frmGetCoupon;

    //    FrmGetCoupon frmGetCoupon = new FrmGetCoupon();
//    FrmGetCoupon()
    void couponGet() {
//        按了 按钮， 数据传过来

    }

    //    swing  form 之间传递数据
//    这边数据新增 优惠券传过来
//    那边更改了 状态 这里重新加载
    @Deprecated
    void couponGetListen() {
        frmGetCoupon.btnGet.addActionListener(this);
//        frmGetCoupon.couponTblData.current
//      new   JScrollPane(new JTable())
    }

    void doAdd() throws SQLException, BaseException {
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
//        车当前状态（空闲，在途，修理报废等）
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
        if (getTimeDate == null) {
            UiUtil.showError("提车时间格式不对 请填写 年-月-日");
            return;
        }
        if (retTimeDate == null) {
            UiUtil.showError("还车时间格式不对 请填写 年-月-日");
            return;
        }

        if (getTimeDate.compareTo(retTimeDate) > 0) {
            UiUtil.showError("提车时间不能比还车时间晚");
            return;
        }
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
        if (dataTableCarInfo.current == null) {
            UiUtil.showError("请选择车");
            return;
        }
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

        Coupon coupon = dataTableCouponUser.current;

//        BigDecimal finalPrice = rawPrice.subtract(coupon.getDeducAmou());
        BigDecimal finalPrice;
        if (coupon == null) {
//            UiUtil.showError("没有选择优惠券");
            finalPrice = rawPrice;
//            return;
        } else {
            finalPrice = rawPrice.subtract(coupon.getDeducAmou());

//            coupon.use()
            if (coupon.getStatus() == 1) {
                UiUtil.showError("您还没有领取优惠券");
                return;
            }
            if (coupon.getStatus() == 3) {
                UiUtil.showError("该优惠券已经被使用");
                return;
            }
            coupon.use();
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

            if (!promotion.useOne()) {
                UiUtil.showError("限时优惠数量不够了");
                return;
            }

        }
        //        车当前状态（空闲，在途，修理 报废等）

        if (carInfo.getCarStatus() != 1) {
//            不是空闲的
            UiUtil.showError("车不是空闲的");
            return;
        }
        if (tblNetBack.current == null) {
            UiUtil.showError("请选择还车网点");
            return;
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


        if(finalPrice.compareTo(BigDecimal.ZERO)<0){
            finalPrice=BigDecimal.ZERO;
        }
        Integer couponId = coupon == null ? null : coupon.getCouponId();
        TblOrder order = new TblOrder(null,
                promotion == null ? null : promotion.getPromId(),
//                carInfo.getNetId(),
                tblNetBack.current.getNetId(),
                User.currentLoginUser.getUserId(),
                carInfo.getCarId(),
//                tblNetBack.current.getNetId(),
                carInfo.getNetId(),
//                dataTableCoupon.current.getCouponId(),
                couponId,
                getTimeDate,
                retTimeDate,
                rentalDur,
                rawPrice,
                finalPrice, 1

        );
//        1 是下单状态
//        订单玩了 之后 优惠券 要变得 没有选中 状态
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
            CcCarUtil.tblOrderDao.insert(connection, order);
//            promotion.setPromQuantity();
            if (promotion != null) {
                JdbcUtil.update(connection, promotion,
                        promotion.getPromId(), "prom_id");
            }
            if (coupon != null) {
                JdbcUtil.update(connection, coupon, couponId, "coupon_id");
            }
            carInfo.setCarStatus(2);
            JdbcUtil.update(connection, carInfo, carInfo.getCarId(), "car_id");


            connection.commit();
            //        展示订单
            dataTableCouponUser.current = null;
            dataTablePromotion.current = null;
            loadNowPromByTimeStr(edtGetTime.getText());
            loadCouponByTimeStr(edtGetTime.getText());
            reloadCar();
//            showOrder();
            frmUserOrderStatus = new FrmUserOrderStatus();
            frmUserOrderStatus.setVisible(true);
//            frmUserOrder.setVisible(true);


        } catch (SQLException | DbException ex) {
            ex.printStackTrace();
            if (connection != null) {
                connection.rollback();
            }
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        } finally {
            DBUtil.closeConnection(connection);
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

    FrmUserInfo frmUserInfo;

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
            try {
                doAdd();
//                showOrder();
            } catch (SQLException | BaseException ex) {
                ex.printStackTrace();
            }
//            try {
//                showOrder();
//            } catch (BaseException | SQLException ex) {
//                ex.printStackTrace();
//                UiUtil.showError("展示不了订单" + ex.getMessage());
//            }
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
                frmUserOrder = new FrmUserOrder("用户查看订单", tblOrder);
            } catch (BaseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            frmUserOrder.setVisible(true);


//            try {
////                showOrder(true);
//
//
////                TblOrder tblOrderEntity = new TblOrder();
////                tblOrderEntity.setUserId(User.currentLoginUser.getUserId());
////                this.tblOrder.all = JdbcUtil.getBy(tblOrderEntity);
////                tblOrder.reloadTableByThisList();
////                frmOrder.setVisible(true);
////                dataTablePromotion.
//                // TODO: 2021/9/5 为什么不行 不显示
////                frmUserOrder.setVisible(true);
//
//                frmUserOrder.setVisible(true);
//
//            } catch (BaseException | SQLException ex) {
//                ex.printStackTrace();
//                UiUtil.showError("展示订单失败 " + ex.getMessage());
//            }


        } else if (e.getSource() == this.btnPromotionSearch) {
            btnPromotionSearchEvent();
        } else if (e.getSource() == this.btnGetCoupon) {

            try {
                btnRaiseCouponFrmEvent();
            } catch (BaseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
//        else if (e.getSource() == this.frmGetCoupon.btnGet) {
//            System.out.println("this.frmGetCoupon.btnGet");
//
//            btnGetCouponEvent();
//
//
//        }
        else if (e.getSource() == this.btnRefresh) {
//            System.out.println("this.frmGetCoupon.btnGet");
//            btnGetCouponEvent();
            try {
//                loadData();
                CarInfo carInfo = new CarInfo();
                carInfo.setCarStatus(1);
//        dataTableCarInfo.all = JdbcUtil.getBy(carInfo);
////        dataTableCarInfo.reloadTable();
////        dataTableCarInfo.reloadTableNoSearch();
//        dataTableCarInfo.reloadTableNoSearch(true);
                dataTableCarInfo.reloadDataAndRepaint(true, carInfo);
//        ca.reloadTable();
//        netInfoTblData.reloadTable();
                tblNetBack.reloadTable();
            } catch (BaseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == this.btnUserOrder) {
//            System.out.println("this.frmGetCoupon.btnGet");
//            btnGetCouponEvent();
            try {
//                frmUserOrder = new FrmUserOrder("用户查看订单", tblOrder);
                frmUserOrderStatus = new FrmUserOrderStatus();
                frmUserOrderStatus.setVisible(true);
            } catch (BaseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


        } else if (e.getSource() == this.btnPic) {
            lookPic();
        } else if (e.getSource() == this.btnSearch) {
            search();
        }

//        else if (e.getSource() == this.btnAddNewRow) {
//
//            dataTableCoupon.addEmptyRow();
//
//        }
    }

    void search() {
        //            if(tddNetGet.current==null){
//                UiUtil.showError("请选择网点");
//            }
        String cityText = edtCity.getText();
        String cityRetText = edtCityRet.getText();
        String addrText = edtAddr.getText();
//        String addrText = edtAddr.getText();
        String addrRetText = edtAddrRet.getText();
        NetInfo netInfo = new NetInfo();
        netInfo.setCity(cityText);
        netInfo.setAddr(addrText);

        NetInfo netInfoRet = new NetInfo();
        netInfoRet.setCity(cityRetText);
        netInfoRet.setAddr(addrRetText);
//            netInfo.setCity(cityText);
//        new FlowLayout(FlowLayout.RIGHT)
        try {
//                tddNetGet.reloadDataAndRepaint(netInfo);
            tddNetGet.reloadDataLikeAndRepaint(new NetInfo(), netInfo);
//            netInfoTblData.reloadDataLikeAndRepaint(new NetInfo(), netInfoRet);
            tblNetBack.reloadDataLikeAndRepaint(new NetInfo(), netInfoRet);
        } catch (BaseException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void lookPic() {

//            frmUserOrderStatus.setVisible(true);
        if (dataTableCarInfo.current == null) {
            UiUtil.showError("请选择车子");
            return;
        }

        Integer typeId = dataTableCarInfo.current.getTypeId();
        CarType carType = new CarType();
        carType.setTypeId(typeId);
        try {
            List<CarType> by = JdbcUtil.getBy(carType);
            if (by.size() <= 0) {
                UiUtil.showError("没有这种车型");
                return;
            }
            DisplayImageIcon displayImageIcon = new DisplayImageIcon(by.get(0).getPic());
            displayImageIcon.setVisible(true);
        } catch (DbException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


//   new  DisplayImageIcon();

    void btnRaiseCouponFrmEvent() throws BaseException, SQLException {
//        new FrmGetCoupon()
//        frmGetCoupon=new FrmGetCoupon(dataTableCarInfo.current.getNetId());
//        这个网点的
        if (dataTableCarInfo.current == null) {
            UiUtil.showError("请选择车");
            return;
        }
//        frmGetCoupon=new FrmGetCoupon(dataTableCarInfo.current.getNetId(),
//                dataTableCouponUser,edtGetTime.getText());

        frmGetCoupon = new FrmGetCoupon(this);

        frmGetCoupon.setVisible(true);
    }

    // TODO: 2021/9/10   dataTableCarInfo reload
    //    两个表  自己的优惠券 和 所有优惠券
//    这个应该可以不监听了  儿子 自己监听了
    @Deprecated
    void btnGetCouponEvent() {
//        现在的优惠券  领取
//        如果他一个人 把所有优惠券领取了呢？
//        每个人有属于他自己的优惠券
//        couponGet();
//        loadNowProm();

//        这里必然是 状态 1的

//            状态（未被领取1，已经被领取2，已经使用3）
//            frmGetCoupon.  couponTblData.current.setStatus(2);
        frmGetCoupon.couponTblData.current.setStatus(2);
        frmGetCoupon.couponTblData.current.setUserId(User.currentLoginUser.getUserId());
//            这是她 获得了 优惠券
//    todo    Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException
        try {
            JdbcUtil.update(frmGetCoupon.couponTblData.current,
                    frmGetCoupon.couponTblData.current.getCouponId(),
                    "coupon_id");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (DbException ex) {
            ex.printStackTrace();
        }


//        loadNowCoupon();

        loadNowPromByTimeStr(edtGetTime.getText());
    }


    //    一开始 不加载
    void loadNowCouponNetHave() {
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
//          frmGetCoupon.  dataTableCoupon.all = coupons;
//            为什么 这里要 加载。。 哦哦 优惠券 还有时间的。。。
//            这 需要 加载吗
            frmGetCoupon.couponTblData.all = coupons;
//            查询没有领取的
//            frmGetCoupon.   dataTableCoupon.reloadTableNoSearch();
            frmGetCoupon.couponTblData.reloadTableNoSearch();
        } catch (SQLException | BaseException ex) {
            ex.printStackTrace();
            UiUtil.showError(ex.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
        }
    }


    void loadCouponByTimeStr(String timeStr) {
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

            Coupon coupon = new Coupon();
            coupon.setNetId(dataTableCarInfo.current.getNetId());
//            coupon.setStatus(1);
            coupon.setStatus(2);
//            已经领取的  这个人的
//            List<Coupon> coupons = JdbcUtil.getBy(connection,coupon, addSql);
            List<Coupon> coupons = JdbcUtil.getByAddSql(connection, coupon, addSql);
            dataTableCouponUser.all = coupons;
            dataTableCouponUser.reloadTableNoSearch();
        } catch (SQLException | BaseException ex) {
            ex.printStackTrace();
            UiUtil.showError(ex.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
        }
    }

    //    用户 有的
    void loadNowCoupon() {
        Date date = new Date();
//        TimeUtil.dato/
        String nowTimeStr = DateUtil.dateToString(date, TimeUtil.fmtYmd);
        loadCouponByTimeStr(nowTimeStr);
//            String edtGetTimeText = edt.getText();
//        String addSql = String.format(" and '%s' >=  start_time  and '%s' <= end_time "
//                , nowTimeStr, nowTimeStr);
//        Connection connection = null;
//        try {
//            connection = DBUtil.getConnection();
////            Promotion promotion = new Promotion();
//////                限时促销的车型，必须在特定的网点，特定的车型，及特定的时间段才能享受
////            promotion.setNetId(dataTableCarInfo.current.getNetId());
////            promotion.setTypeId(dataTableCarInfo.current.getTypeId());
//////                List<Promotion> by = JdbcUtil.getBy(new Promotion(), addSql);
////            List<Promotion> by = JdbcUtil.getBy(connection,promotion, addSql);
////            dataTablePromotion.all = by;
////            dataTablePromotion.reloadTableNoSearch();
//
//            Coupon coupon = new Coupon();
//            coupon.setNetId(dataTableCarInfo.current.getNetId());
////            coupon.setStatus(1);
//            coupon.setStatus(2);
////            已经领取的  这个人的
////            List<Coupon> coupons = JdbcUtil.getBy(connection,coupon, addSql);
//            List<Coupon> coupons = JdbcUtil.getByAddSql(connection, coupon, addSql);
//            dataTableCouponUser.all = coupons;
//            dataTableCouponUser.reloadTableNoSearch();
//        } catch (SQLException | BaseException ex) {
//            ex.printStackTrace();
//            UiUtil.showError(ex.getMessage());
//        } finally {
//            DBUtil.closeConnection(connection);
//        }

//        在这里加载吗
//        frmGetCoupon.loadNowCoupon();
//        loadNowCouponNetHave();
    }

    //    促销数量要大于0
    void loadNowPromByTimeStr(String timeStr) {
        if (dataTableCarInfo.current == null) {
            UiUtil.showError("请选择车辆");
            return;
        }
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
//            List<Promotion> by = JdbcUtil.getBy(connection,promotion, addSql);
            List<Promotion> by = JdbcUtil.getByAddSql(connection, promotion, addSql);
            dataTablePromotion.all = by;
            dataTablePromotion.reloadTableNoSearch();

//            当前的 优惠信息 可能会变
            dataTablePromotion.current = null;

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


    public void loadUserCouponByTimeStr(String timeStr) {

        if (dataTableCarInfo.current == null) {
            UiUtil.showError("请选择车辆");
            return;
        }
//            String edtGetTimeText = edt.getText();
        String addSql = String.format(" and '%s' >=  start_time  and '%s' <= end_time   "
                , timeStr, timeStr);
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
//            Promotion promotion = new Promotion();
////                限时促销的车型，必须在特定的网点，特定的车型，及特定的时间段才能享受
//            promotion.setNetId(dataTableCarInfo.current.getNetId());
//            promotion.setTypeId(dataTableCarInfo.current.getTypeId());
//
//            //                List<Promotion> by = JdbcUtil.getBy(new Promotion(), addSql);
////            List<Promotion> by = JdbcUtil.getBy(connection,promotion, addSql);
//            List<Promotion> by = JdbcUtil.getByAddSql(connection,promotion, addSql);
//            dataTablePromotion.all = by;
//            dataTablePromotion.reloadTableNoSearch();
//

            Coupon coupon = new Coupon();
//                限时促销的车型，必须在特定的网点，特定的车型，及特定的时间段才能享受
            coupon.setNetId(dataTableCarInfo.current.getNetId());
//            coupon.setTypeId(dataTableCarInfo.current.getTypeId());
            coupon.setStatus(2);
            coupon.setUserId(User.currentLoginUser.getUserId());
//            状态（未被领取1，已经被领取2，已经使用3）
            List<Coupon> by = JdbcUtil.getByAddSql(connection, coupon, addSql);
//            dataTableCouponUser.reloadDataAndRepaint(coupon);
            dataTableCouponUser.all = by;
            dataTableCouponUser.reloadTableNoSearch();
            dataTableCouponUser.current = null;
//            防止 刷优惠券

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

    //    优惠券  和 限时优惠
    void btnPromotionSearchEvent() {

        String edtGetTimeText = edtGetTime.getText();
//            如果 全部列出来 都可以用了
        Date date = TimeUtil.stringtoDate(edtGetTimeText, TimeUtil.fmtYmd);
        if (date == null) {
            UiUtil.showError("日期格式异常");
            return;
        }
        String nowDateYmdStr = DateUtil.dateToString(new Date(), TimeUtil.fmtYmd);
        Date nowDateYmd = TimeUtil.stringtoDate(
                nowDateYmdStr,
                TimeUtil.fmtYmd);
//        提车 的日期 比现在晚，不行
        if (date.compareTo(nowDateYmd) < 0) {
            UiUtil.showError("提车 的日期 比现在早，不行");
            return;
        }

        loadNowPromByTimeStr(edtGetTimeText);
        loadUserCouponByTimeStr(edtGetTimeText);
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
            // TODO: 2021/9/10  User.currentLoginUser.ge
            User user = new User();
            user.setUserId(User.currentLoginUser.getUserId());
//            List<User> by = JdbcUtil.getBy(User.currentLoginUser);
            List<User> by = JdbcUtil.getBy(user);
            if (by.size() <= 0) {
                UiUtil.showError("没有找到用户");
                return;
            }
            if (by.size() > 1) {
                UiUtil.showError("用户id重复 有问题 ，请联系管理员");
                return;
            }
            tblUser.all = by;

            User.currentLoginUser = by.get(0);

        } catch (DbException | SQLException ex) {
            ex.printStackTrace();
            UiUtil.showError("查找失败" + ex.getMessage());
        }
        tblUser.reloadTableByThisList();
//            showUser();
        frmUserInfo = new FrmUserInfo("用户信息", tblUser);
        frmUserInfo.setVisible(true);


//            showTable();
    }

    FrmUserOrderStatus frmUserOrderStatus;

    FrmUserOrder frmUserOrder;
//    FrmUserOrder frmUserOrder=new FrmUserOrder("用户查看订单",orde);

    JFrame frmPromotion = new JFrame("促销信息");

    public FrmOrder() throws BaseException, SQLException {
//            Bus.fUserGo = this;
        this.setTitle("租车下单");
//            this.setSize(1318, 838);
        this.setSize(1800, 1000);
        this.setLocationRelativeTo(null);
//        this.setLocation(10, 400);
//        中间左右  400
//        this.setLocation(10, 800);
        this.setLocation(10, 10);
        this.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
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
//        mdpTblPanel.setLayout(new GridLayout(1, 6));// 如果这里不设置的话，商品信息表将无法展示
        mdpTblPanel.setLayout(new GridLayout(1, titles.length));// 如果这里不设置的话，商品信息表将无法展示
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

        inputPanel.add(labelGetTime);
        inputPanel.add(edtGetTime);

        inputPanel.add(labelRetTime);
        inputPanel.add(edtRetTime);

        inputPanel.add(lblCity);
        inputPanel.add(edtCity);
        inputPanel.add(lblCityRet);
        inputPanel.add(edtCityRet);

        inputPanel.add(lblAddr);
        inputPanel.add(edtAddr);
        inputPanel.add(lblAddrRet);
        inputPanel.add(edtAddrRet);


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

//        frmGetCoupon=new FrmGetCoupon(dataTableCarInfo.current.getNetId());
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

//                TblData<CarInfo> tableCarInfo = FrmOrder.this.dataTableCarInfo;
                TblDataDo<CarInfo> tableCarInfo = FrmOrder.this.dataTableCarInfo;
                int i = tableCarInfo.getSelectedRow();
//                这个知道是自己吗
                if (i < 0) {
                    return;
                }
                dataTableCarInfo.current = dataTableCarInfo.all.get(i);
//                这个应该是 拿优惠券的时候 加载吧
                loadNowProm();
                loadNowCoupon();
//                load1(tableCarInfo);
//                cur
                // TODO: 2021/9/7 curr

            }
        });
    }

    @Deprecated
    void load1(TblData<CarInfo> tableCarInfo) {
//        TblData<CarInfo> tableCarInfo = FrmOrder.this.dataTableCarInfo;
        TblData<Coupon> tableCoupon = FrmOrder.this.dataTableCouponUser;
        Coupon coupon = new Coupon();
        Integer currentNetId = tableCarInfo.current.getNetId();
//                coupon.setNetId(tableCarInfo.current.getNetId());
        coupon.setNetId(currentNetId);
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            tableCoupon.all = JdbcUtil.getBy(connection, coupon);
            tableCoupon.reloadTableByThisList();

            Promotion promotion = new Promotion();
            promotion.setNetId(currentNetId);
//                    需要现在的优惠
//                    dataTablePromotion.all = JdbcUtil.getBy(coupon);
            dataTablePromotion.all = JdbcUtil.getBy(connection, promotion);
            dataTablePromotion.reloadTableByThisList();
//                    showFrame("优惠券",new ScrollPane( tableCoupon), 410 * 3);

//                    showFrame("优惠券", tableCoupon, 410 * 3);
//                    showTable("优惠券", tableCoupon, 410 * 3);

//                    JFrame frame = new JFrame("优惠券");
//                    frmTableCoupon= new JFrame("优惠券");
            // TODO: 2021/9/5 cop
//                    frmTableCoupon.
//                    frmTableCoupon.setVisible(true);
//                    FrmOrder.this.dataTableCoupon.setVisible(true);


        } catch (DbException | SQLException ex) {
            ex.printStackTrace();
            UiUtil.showError("获取优惠券失败 " + ex.getMessage());
        } finally {
            DBUtil.closeConnection(connection);
        }
//                这样可以的
//                tableCarInfo.current = tableCarInfo.all.get(i);


    }

    void setUpButtons() {


        List<JButton> btnList = new ArrayList<>();
//        btnList.add(this.exit);
        btnList.add(this.add);
//        btnList.add(this.unadd);
//        btnList.add(this.done);
        btnList.add(this.btnUserInfo);
//        btnList.add(this.btnOrder);
        btnList.add(this.btnPromotionSearch);
//        btnList.add(this.btnAddNewPromo);
        btnList.add(this.btnAddNewRow);
        btnList.add(this.btnGetCoupon);
        btnList.add(this.btnRefresh);
        btnList.add(this.btnUserOrder);
        btnList.add(this.btnPic);
        btnList.add(this.btnSearch);
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

    //    String[] titles = new String[]{"车", "还车网点", "优惠券", "限时优惠"};
    String[] titles = new String[]{"提车网点", "车", "还车网点", "优惠券", "限时优惠"};

    void setUpTables() throws BaseException, SQLException {

//        List<TblData> tblDataList = new ArrayList<>();
        List<JTable> tblDataList = new ArrayList<>();


        this.netInfoTblData = new TblData<>(NetInfo.tableTitles,
                CcCarUtil.netInfoDao);
//        因为这个 确实没了
//        tddNetGet
        this.tddNetGet = new TblData<>(NetInfo.tableTitles,
                CcCarUtil.netInfoDao);
        this.tblNetBack = new TblData<>(NetInfo.tableTitles,
                CcCarUtil.netInfoDao);
//        因为没有 reload
        this.dataTableCarInfo = new TblDataDo<>(CarInfo.tableTitles,
                CcCarUtil.carInfoDao);
//        this.dataTableCoupon = new TblData<>(Coupon.tableTitles,
//                tblDataCoupon, tabModelCoupon, allCoupon, CcCarUtil.couponDao);
        this.dataTableCouponUser = new TblData<>(Coupon.tableTitles,
                CcCarUtil.couponDao);
//        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
//                tblDataPromotion, tabModelPromotion, allPromotion, CcCarUtil.promotionDao);
        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
                CcCarUtil.promotionDao);


        tblDataList.add(tddNetGet);
        tblDataList.add(dataTableCarInfo);

        tblDataList.add(tblNetBack);
        tblDataList.add(dataTableCouponUser);
        tblDataList.add(dataTablePromotion);

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

//        String[] titles = new String[]{"车", "还车网点", "限时优惠"};
//        for (TblData tblData : tblDataList) {
        for (JTable tblData : tblDataList) {


            JPanel panel = borderTable(titles[titleIdx++], tblData);

//            this.mdpTblPanel.add(jScrollPane);
//            this.mdpTblPanel.add(west);
            this.mdpTblPanel.add(panel);

        }
        tddNetGet.reloadTable();

        tddNetGet.addMouseListener(new MouseAdapter() {

            //            mouseClicked 怎么获取外面的数据
//            这是引用 只要弄到一次 以后都可以了
            @Override
            public void mouseClicked(MouseEvent e) {

                reloadCar();
//                if (tddNetGet.current == null) {
//                    return;
//                }
//
//                CarInfo carInfo1 = new CarInfo();
//                carInfo1.setNetId(tddNetGet.current.getNetId());
//                carInfo1.setCarStatus(1);
////                carInfo1.setTypeId(tddNetGet.current.getTypeId());
//                try {
//                    dataTableCarInfo.reloadDataAndRepaint(true, carInfo1);
////                    Promotion promotion = new Promotion();
////                    promotion.setTypeId(carTypeTblDataDo.current.getTypeId());
////                    tddPromo.reloadDataAndRepaint(true, promotion);
//                } catch (BaseException ex) {
//                    ex.printStackTrace();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }

//                carTypeTblDataDo.current

            }
        });

    }

    void reloadCar() {
        if (tddNetGet.current == null) {
            return;
        }

        CarInfo carInfo1 = new CarInfo();
        carInfo1.setNetId(tddNetGet.current.getNetId());
        carInfo1.setCarStatus(1);
//                carInfo1.setTypeId(tddNetGet.current.getTypeId());
        try {
            dataTableCarInfo.reloadDataAndRepaint(true, carInfo1);
//                    Promotion promotion = new Promotion();
//                    promotion.setTypeId(carTypeTblDataDo.current.getTypeId());
//                    tddPromo.reloadDataAndRepaint(true, promotion);
        } catch (BaseException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    void loadDataEmp() throws SQLException, BaseException {

        Connection connection = DBUtil.getConnection();
        Integer currentEmpNetId = CcCarUtil.currentEmp.getNetId();
        CarInfo carInfo = new CarInfo();
        carInfo.setNetId(currentEmpNetId);
        dataTableCarInfo.all = JdbcUtil.getBy(connection, carInfo);
        dataTableCarInfo.reloadTableNoSearch(true);
//        dataTableCarInfo.reloadTableNoSearch();
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


        CarInfo carInfo = new CarInfo();
        carInfo.setCarStatus(1);
//        dataTableCarInfo.all = JdbcUtil.getBy(carInfo);
////        dataTableCarInfo.reloadTable();
////        dataTableCarInfo.reloadTableNoSearch();
//        dataTableCarInfo.reloadTableNoSearch(true);
        dataTableCarInfo.reloadDataAndRepaint(true, carInfo);
//        ca.reloadTable();
//        netInfoTblData.reloadTable();
        tblNetBack.reloadTable();
//        dataTableCoupon.reloadTable();
        // TODO: 2021/9/7 显示自己的优惠券 领取网点优惠券
//        loadNowProm();
//        员工 可以看所有优惠券吗，也只是自己网点的

        String dateToString = DateUtil.dateToString(new Date(), DateUtil.LONG_DATE_FORMAT);
        edtGetTime.setText(dateToString);
//        edtGetTime.setColumns();
//        FrmGetCoupon  frmGetCoupon=new FrmGetCoupon();

    }


    void setUpFrmTableCoupon() {
        frmTableCoupon.getContentPane().add(new JScrollPane(dataTableCouponUser));
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