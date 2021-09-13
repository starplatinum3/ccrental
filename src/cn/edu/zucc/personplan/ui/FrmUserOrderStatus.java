/**
 * @Author: SevDaisy十七散人
 * @Date: 2020-07-05 16:29:00
 */
package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.svo.OrderSvo;
import cn.edu.zucc.personplan.tvo.OrderTvo;
import cn.edu.zucc.personplan.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrmUserOrderStatus extends JFrame implements ActionListener {
    private static final long serialVersionUID = -990815448731736703L;
    //  网点 两个
    NetInfo currentNetInfo = null;
    private Object[] titlesNetInfo = NetInfo.tableTitles;
    private Object[][] tblDataNetInfo;
    DefaultTableModel tabModelNetInfo = new DefaultTableModel();
    //    private JTable dataTableNetInfo = new JTable(tabModelNetInfo);
    TblData<NetInfo> netInfoTblData;

    List<NetInfo> allNetInfo = null;

//  user stat

    //  车
//    这些数据 这里是需要的 ，但是好像也不是
    CarInfo currentCarInfo = null;
    private Object[] titlesCarInfo = CarInfo.tableTitles;
    private Object[][] tblDataCarInfo;
    DefaultTableModel tabModelCarInfo = new DefaultTableModel();
    //    private JTable dataTableCarInfo ;
//    private TblData<CarInfo> dataTableCarInfo;
    private TblDataDo<CarInfo> dataTableCarInfo;
    //    private JTable dataTableCarInfo = new JTable(tabModelCarInfo);
    List<CarInfo> allCarInfo = null;
    FrmAdminCarType frmAdminCarType = new FrmAdminCarType();

    //  优惠券
    Coupon currentCoupon = null;
    private Object[] titlesCoupon = Coupon.tableTitles;
    private Object[][] tblDataCoupon;
    DefaultTableModel tabModelCoupon = new DefaultTableModel();
    //    private JTable dataTableCoupon = new JTable(tabModelCoupon);
    private TblData<Coupon> dataTableCoupon;
    List<Coupon> allCoupon = null;

    //    限时促销
    Promotion currentPromotion = null;
    private Object[] titlesPromotion = Promotion.tableTitles;
    private Object[][] tblDataPromotion;
    DefaultTableModel tabModelPromotion = new DefaultTableModel();
    //    private JTable dataTablePromotion = new JTable(tabModelPromotion);
    private TblData<Promotion> dataTablePromotion;
    List<Promotion> allPromotion = null;

    TblData<NetInfo> netOutInfoTblData;

    TblData<CarType> tblCarType;
    TblData<Scrap> tblScrap = new TblData<>(Scrap.tableTitles, CcCarUtil.scrapDao);
    TblDataDo<OrderSvo> tblOrderSvo = new TblDataDo<>(OrderTvo.tableTitles, CcCarUtil.orderSvoDao);
//    TblDataDo<OrderSvo> tblOrderSvo = new TblDataDo<>(OrderTvo.tableTitles, CcCarUtil.orderSvoDao);

//    好像不需要是 类里面定义的？ 还是需要的
//    private TblData<NetInfo>  netOutInfoTblData;

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

    private int OrderDetailIndex;

    // Panel 布局
    private JPanel mdpTblPanel = new JPanel();
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
    private JButton btnUpdate = new JButton("更新");
    private JButton btnScrap = new JButton("报废");
    private JButton btnOrder = new JButton("查看订单");
    private JButton btnCat = new JButton("查看车大类");
    private JButton btnCarType = new JButton("查看车类别");
    private JButton btnNetInfo = new JButton("管理网点信息");
    private JButton btnCarInfo = new JButton("管理车子信息");
    JButton btnStatusWrong = new JButton("状态有误");
    TblOrder order = new TblOrder();


    private JLabel labelGetTime = new JLabel("edtGetTime：");
    private JLabel labelRetTime = new JLabel("edtRetTime：");
    //    private JLabel labelRentalDur = new JLabel("edtRentalDur：");

    private JTextField edtGetTime = new JTextField(20);
    private JTextField edtRetTime = new JTextField(20);
    //    借的时间
    private JTextField edtRentalDur = new JTextField(20);
    private JTextField edtRemark = new JTextField(20);
//    private JTextField edtGetTime = new JTextField(20);
//    raw_price  算出来的

    //    另外一个界面的 时候 只要 复制 然后 改名字
//    不能扔东西
//    actionPerformed 怎么抛出异常

    void doAdd() {
        //            tbl 放在list 里面 ，也不知道是哪个
//            map  也还是要取出来 ，好像没什么用处

        System.out.println("add");
        NetInfo netInfo = netInfoTblData.current;
        System.out.println("netInfo");
        System.out.println(netInfo);
//            可以能拿到
//            没有显示
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

        Coupon coupon = dataTableCoupon.current;
        Promotion promotion = dataTablePromotion.current;
//            coupon.getDeducAmou();
//          price =  rentalDur*unitPrice - 优惠券
//            price  * 折扣
//            BigDecimal unitPrice = BigDecimal.ONE;
//            if()
//            BigDecimal price=
        // TODO: 2021/9/3     BigDecimal price =
        BigDecimal rawPrice = unitPrice.multiply(BigDecimal.valueOf(rentalDur));

        BigDecimal finalPrice = rawPrice.
                subtract(coupon.getDeducAmou());
//            促销数量 要是 0  也是不显示
//            打折  写着 7折 ，再除以 10
        finalPrice = finalPrice.multiply(promotion.getDiscount()).divide(BigDecimal.valueOf(10));
//            divide bigd
//                    再打折
//            订单完成后，根据车型、租车时长，自动生成结算金额


//          根据 car id  知道车型号, 然后知道了
//            rental_dur 应该是天数把
//            两个时间 相差几天

//netInfo
//NetInfo{netId=1, netName='1', city='1', addr='1', phone='1'}
//
//            try {
////                Integer couponId = dataTableCoupon.current.getCouponId();
////                dataTable
////                Integer couponId = dataTableCoupon.current.getCouponId();
//
////                get_time 表单
//
//
//
//
        TblOrder order = new TblOrder(null,
                promotion.getPromId(),
                netInfoTblData.current.getNetId(),
                User.currentLoginUser.getUserId(),
                dataTableCarInfo.current.getCarId(),
                netOutInfoTblData.current.getNetId(),
                dataTableCoupon.current.getCouponId(),
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


//            car_type  有个 price
////
//
//                // 只是一个商品吗
////                Bus.orderInfoManager.add_1(this.currentProduct);
////                this.reloadOrderProjectsable();
////                if (this.allOrderProjects.size() > this.OrderDetailIndex
////                        && this.allOrderProjects.get(this.OrderDetailIndex).getOpCnt() >= 1)
////                    this.dataTableOrderProjects.setRowSelectionInterval(
////                            this.OrderDetailIndex, this.OrderDetailIndex);
//
//            } catch (BusinessException e1) {
//                JOptionPane.showMessageDialog(null, "添加商品到订单失败\n" + e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//                e1.printStackTrace();
//            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.exit) {
//            dataTableCarInfo.current.setCarStatus();
//            可以拿到的


//            try {
//                this.reloadOrderProjectsable();
//                // System.out.println("OrderID is " + Link.currentOrder.getOrderID());
//                System.out.println("InfoItem count is " + allOrderProjects.size());
//                // if (allOrderProjects.size() != 0)
//                Bus.orderManager.removeByOrderID(Bus.currentOrder.getOrderID());
//                // System.exit(0);
//                this.setVisible(false);
//                // } catch (DbException e1) {
//            } catch (Exception e1) {
//                JOptionPane.showMessageDialog(null, "删除已创建的订单失败\n" + e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//                e1.printStackTrace();
//            }
        } else if (e.getSource() == this.add) {
            doAdd();
        } else if (e.getSource() == this.unadd) {
//            try {
//                Bus.orderInfoManager.unadd_1(this.currentProduct);
//                this.reloadOrderProjectsable();
//                if (this.allOrderProjects.size() > this.OrderDetailIndex
//                        && this.allOrderProjects.get(this.OrderDetailIndex).getOpCnt() >= 1)
//                    this.dataTableOrderProjects.setRowSelectionInterval(this.OrderDetailIndex, this.OrderDetailIndex);
//            } catch (Exception e1) {
//                JOptionPane.showMessageDialog(null, "从订单移除商品失败\n" + e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//                e1.printStackTrace();
//            }
        } else if (e.getSource() == this.done) {
//            new FrmUserAddress();
            // FrmUserAddress FUserAddress = new FrmUserAddress();
//            System.out.println("您的订单：" + Bus.currentOrder);
            // this.setVisible(false);
//            完成订单 应该是管理员的 事情
        } else if (e.getSource() == this.btnUpdate) {
            System.out.println("更新");

            try {
//                UiUtil.updateBatch(this.netOutInfoTblData, CcCarUtil.netInfoDao);
                UiUtil.updateBatch(tblCarType);
                UiUtil.updateBatch(netInfoTblData);
                UiUtil.updateBatch(dataTableCarInfo);
                UiUtil.updateBatch(dataTableCoupon);
                // TODO: 2021/9/6  dataTableCoupon
//                UiUtil.updateBatch(netInfoTblData);
//                UiUtil.updateBatch(netOutInfoTblData);
            } catch (SQLException | BaseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "更新失败\n" + ex.getMessage(),
                        "错误", JOptionPane.ERROR_MESSAGE);
            }


        } else if (e.getSource() == this.btnScrap) {
            try {
                btnScrapEvent();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == this.btnOrder) {
            btnOrderEvent();
        } else if (e.getSource() == this.btnCat) {
            frmAdminCarCat.setVisible(true);
        } else if (e.getSource() == this.btnCarType) {
//            frmAdminCarCat.setVisible(true);
            frmAdminCarType.setVisible(true);
        } else if (e.getSource() == this.btnNetInfo) {
//            frmAdminCarCat.setVisible(true);
            frmAdminNetInfo.setVisible(true);
        } else if (e.getSource() == this.btnCarInfo) {
//            frmAdminCarCat.setVisible(true);
            frmAdminCarInfo.setVisible(true);
        } else if (e.getSource() == this.btnStatusWrong) {
//            frmAdminCarCat.setVisible(true);
//            frmAdminCarInfo.setVisible(true);
            UiUtil.showInfo("向管理员发送错误信息");
        }
    }

    FrmAdminCarCat frmAdminCarCat = new FrmAdminCarCat();
    FrmAdminNetInfo frmAdminNetInfo = new FrmAdminNetInfo();
    FrmAdminCarInfo frmAdminCarInfo = new FrmAdminCarInfo();

    void btnScrapEvent() throws SQLException {
        //            tblScrap.all

//            Connection connection=null;
//            try {
//                 connection=DBUtil.getConnection();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null,
//                        "数据库连接失败\n" + ex.getMessage(),
//                        "错误", JOptionPane.ERROR_MESSAGE);
//            }
        Connection connection = UiUtil.getConnection();
        connection.setAutoCommit(false);
        CarInfo currentCarInfo = dataTableCarInfo.current;
        Integer carId = currentCarInfo.getCarId();
        Integer empId = CcCarUtil.currentEmp.getEmpId();
        String remarkText = edtRemark.getText();
        Scrap scrap = new Scrap(null, carId, empId, new Date(), remarkText);
//        tblScrap.all.add(scrap);
//        tblScrap.reloadTableByThisList();

        currentCarInfo.setCarStatus(3);
        try {
            JdbcUtil.update(connection, currentCarInfo, currentCarInfo.getCarId(),
                    "car_id");
            JdbcUtil.insert(connection, scrap);
//            Integer netId = CcCarUtil.currentEmp.getNetId();
//            Scrap scrap1 = new Scrap();
//            scrap1.setEmpId();
//            这是管理员 是全部的
            tblScrap.reloadDataAndRepaint(new Scrap());
            dataTableCarInfo.reloadDataAndRepaint(true, new CarInfo());
            connection.commit();
            UiUtil.showInfo("报废成功");
        } catch (DbException ex) {
            ex.printStackTrace();
            UiUtil.showError("插入失败 或者 更新失败\n" + ex.getMessage());
            connection.rollback();
        } catch (BaseException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(connection);

        }

//            try {
//                JdbcUtil.insert(connection, scrap);
//            } catch (DbException ex) {
//                ex.printStackTrace();
////                JOptionPane.showMessageDialog(null, "插入失败\n" + ex.getMessage(),
////                        "错误", JOptionPane.ERROR_MESSAGE);
//                UiUtil.showError("插入失败\n" + ex.getMessage());
//            }
    }

//    管理员可以查询客户的消费情况（订单数，总金额以及每个订单的明细信息）；
//    管理员可以根据网点、车型信息查询车辆租用信息（总数量，总金额等）


    JLabel lblOrderNumTitle = new JLabel("订单数");
    JLabel lblMoneyTitle = new JLabel("总金额");

    JLabel lblOrderNum = new JLabel("");
    JLabel lblMoney = new JLabel("");


    FrmOrderAdmin frmOrderAdmin = new FrmOrderAdmin("订单信息", tblOrderSvo);

    void reloadTblOrderSvo() {
        //        List<OrderSvo> orderSvos = CcCarUtil.orderSvoDao.loadAll();
        try {
//            tblOrderSvo.reloadTable(true);
//            全部查出来
            List<OrderSvo> by = JdbcUtil.getBy(new OrderSvo());
            tblOrderSvo.all = by;
//            tblOrderSvo.reloadTableBy(true,new OrderSvo());
            tblOrderSvo.reloadTableNoSearch(true);
//            tblOrderSvo.reloadTable(true);
        } catch (BaseException | SQLException e) {
            e.printStackTrace();
            UiUtil.showError("显示订单失败  " + e.getMessage());
        }
    }


    void btnOrderEvent() {
        reloadTblOrderSvo();
//        UiUtil.showTable("订单",tblOrderSvo,10,10);

//        JFrame frame = new JFrame("订单");
//        frame.getContentPane().add(new JScrollPane(tblOrderSvo));
//        frame.setSize(1400, 400);
//        frame.setLocation(10, 10);//在屏幕中设置显示的位置
//      300 大概 中间  上下

//        x+=410;
//        frame.setVisible(true);
//        没有 reloadTableByThisList 就出事
//        tblOrderSvo.reloadTableByThisList();
        frmOrderAdmin.setVisible(true);

    }

    void updateNetOutInfo() {


//        可以改成 以下
//        try {
//            UiUtil.updateBatch(this.netOutInfoTblData,  CcCarUtil.netInfoDao);
//        } catch (SQLException | BaseException e) {
//            e.printStackTrace();
//            UiUtil.showError("更新失败"+e.getMessage());
//        }
//        可以改成  以上

//        this.dataTableCarInfo.updateBatch();

//            this.netInfoTblData.get

//            List<List<Object>> tableVals = UiUtil.getTableVals(this.netInfoTblData);
//            List<List<Object>> tableVals = UiUtil.getTableVals( this.netInfoTblData.tabModel);
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "创建连接失败\n" + ex.getMessage(),
                    "错误", JOptionPane.ERROR_MESSAGE);
        }


        List<List<String>> tableVals = UiUtil.getTableVals(this.netOutInfoTblData.tabModel);
//            System.out.println("tableVals");
//            System.out.println(tableVals);
        int idx = 0;
        List<NetInfo> list = this.netInfoTblData.all;
//            System.out.println("list");
//            System.out.println(list);
        for (List<String> tableVal : tableVals) {
            NetInfo netInfo = new NetInfo(tableVal);
//                System.out.println("netInfo");
//                System.out.println(netInfo);
            list.set(idx++, netInfo);
//            直接 构建新的 的列表  因为 监听的 是 类里面的 所以没事
            try {
                CcCarUtil.netInfoDao.update(connection, netInfo);
            } catch (BaseException | SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "更新失败\n" + ex.getMessage(),
                        "错误", JOptionPane.ERROR_MESSAGE);
            }
//                NetInfo netInfo = this.netInfoTblData.all.get(idx++);

//            new NetInfo(tableVal);
        }
        System.out.println("this.netInfoTblData.all");
        System.out.println(this.netInfoTblData.all);
//            System.out.println(list);


    }

    //    String[] titles = new String[]{"车的类型", "网点", "车子信息", "优惠券", "限时优惠", "报废"};
    String[] titles = new String[]{"车子信息", "报废"};

    public FrmUserOrderStatus() throws BaseException, SQLException {

//            Bus.fUserGo = this;
        this.setTitle("您的订单信息");
        this.setSize(1700, 1000);
        this.setLocationRelativeTo(null);
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
        mdpTblPanel.setLayout(new GridLayout(1, titles.length));// 如果这里不设置的话，商品信息表将无法展示
//            mdpTblPanel.setLayout(new FlowLayout());// 如果这里不设置的话，商品信息表将无法展示
//            小了 就不显示
//            TextField textField = new TextField("1");
//            textField.setSize(10,10);
//            mdpTblPanel.add(textField);
////            设置 size 好像没用处
//            mdpTblPanel.add(new TextField("1"));
//            mdpTblPanel.add(new TextField("1"));
//            mdpTblPanel.add(new TextField("1"));
//            mdpTblPanel.add(new TextField("1"));
//            mdpTblPanel.add(new TextField("1"));

//            没有用处啊
//            GridLayout 有 scroll
//            mdpTblPanel.setLayout(new ScrollPaneLayout());// 如果这里不设置的话，商品信息表将无法展示
//            这个不显示啊
//            没有layout 不行啊
        Container contentPane = this.getContentPane();

//            JScrollPane jScrollPane=new JScrollPane();
//            jScrollPane.setLayout(new ScrollPaneLayout());
//            jScrollPane.add(this.mdpTblPanel);
//            放在 这里会显示不出来
//            pnlTitles.setLayout(new GridLayout(1,6));
//            pnlTitles.add(new JTextField("1"));
//            contentPane.add(pnlTitles,BorderLayout.NORTH);
        contentPane.add(this.mdpTblPanel, BorderLayout.CENTER);
//            contentPane.add(jScrollPane, BorderLayout.NORTH);
//            contentPane.add(this.mdpTblPanel, BorderLayout.NORTH);
//        contentPane.add(this.orderInfoTablePanel, BorderLayout.CENTER);
//            this.getContentPane().add(this.orderInfoTablePanel, BorderLayout.CENTER);
//            this.getContentPane().add(this.toolBarPanel, BorderLayout.SOUTH);
//        contentPane.add(this.toolBarPanel, BorderLayout.SOUTH);
//        contentPane.add(this.inputPanel, BorderLayout.EAST);
//            展示了


        setBottom(contentPane);

        List<JButton> btnList = new ArrayList<>();
//        btnList.add(this.exit);
//        btnList.add(this.add);
////        btnList.add(this.unadd);
////        btnList.add(this.done);
//        btnList.add(this.btnUpdate);
//        btnList.add(this.btnScrap);
//        btnList.add(this.btnOrder);
//        btnList.add(this.btnCat);
//        btnList.add(this.btnCarType);
//        btnList.add(this.btnNetInfo);
//        btnList.add(this.btnCarInfo);
        btnList.add(this.btnStatusWrong);
        Color colorRed = new Color(254, 67, 101);
        Color colorPurple = new Color(172, 3, 220);
        add.setBackground(new Color(254, 67, 101));//设置背景色
        btnUpdate.setBackground(new Color(254, 67, 101));//设置背景色
        btnScrap.setBackground(colorRed);//设置背景色
//        btnScrap.setBackground(new Color(252, 157, 154));//设置背景色
//        btnScrap.setBackground(new Color(252, 157, 154));//设置背景色
//        btnScrap.setBackground(new Color(252, 157, 154));//设置背景色
        btnOrder.setBackground(colorRed);//设置背景色
//        new Color(249, 205, 173)
//        btnOrder.setBackground(new Color(249, 205, 173));//设置背景色
        btnCat.setBackground(colorRed);//设置背景色
        btnCarType.setBackground(colorRed);//设置背景色
        btnNetInfo.setBackground(colorRed);//设置背景色
        btnCarInfo.setBackground(colorRed);//设置背景色
        btnStatusWrong.setBackground(colorRed);//设置背景色
//        深红色：R=254 G=67 B=101
//        浅红色：R=252 G=157 B=154
//        浅黄色：R=249 G=205 B=173
//        浅青色：R=200 G=200 B=169
//        淡青色：R=131 G=175 B=155

//        https://www.icoa.cn/a/512.html


        // toolBarPanel 设置
        toolBarPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
//        toolBarPanel.add(this.exit);
//        toolBarPanel.add(this.add);
//        toolBarPanel.add(this.unadd);
//        toolBarPanel.add(this.done);
//        toolBarPanel.add(this.btnUpdate);
//        toolBarPanel.add(this.btnScrap);
//swing border

        for (JButton jButton : btnList) {
            toolBarPanel.add(jButton);
            jButton.addActionListener(this);
//            this.edtGetTime.getPreferredSize()
            //设置按钮样式
//            jButton.setPreferredSize(new Dimension(100, 40));//设置大小
//            jButton.setBorder();
            jButton.setForeground(Color.WHITE);//设置前景色
            jButton.setFont(new Font("微软雅黑", Font.BOLD, 13)); //设置字体样式

        }


//        inputPanel.add(labelGetTime);
//        inputPanel.add(edtGetTime);
//
//        inputPanel.add(labelRetTime);
//        inputPanel.add(edtRetTime);


//        JLabel labelRentalDur = new JLabel("edtRentalDur：");
//        inputPanel.add(labelRentalDur);
//        inputPanel.add(edtRentalDur);

//        inputPanel.add(new JLabel("报废汽车备注信息："));
//        inputPanel.add(edtRemark);

//        进出的 网点 都要列出来吗 这样比较方便 感觉

//        借的 时长  也是 算出来的吧
//

//        选择 的时间不一样  促销信息不一样 ？
//        inputPanel.add(edtRetTime);
//        inputPanel.add(edtRentalDur);
//
//        inputPanel.add(edtRetTime);
//        inputPanel.add(edtRentalDur);

//        inputPanel.add(edtGetTime);

//        List<TblData> tblDataList = new ArrayList<>();
        List<JTable> tblDataList = new ArrayList<>();


//        mqp  2021年9月3日08:59:56
//      界面怎么没了
//        System.out.println("================");
//        System.out.println("allNetInfo");
//        allNetInfo = CcCarUtil.netInfoDao.getBy(new NetInfo());
//        需要加载吗 不是reload了吗
//      TblData<NetInfo> netInfoTblData = new TblData<>(NetInfo.tableTitles,
//              tblDataNetInfo, tabModelNetInfo, allNetInfo,CcCarUtil.netInfoDao);

//      是因为model 不是这里的吗


//        会 很挤 可能需要 放到下一排,或者有些字段不要了
//        this.netInfoTblData = new TblData<>(NetInfo.tableTitles,
//                tblDataNetInfo, tabModelNetInfo, allNetInfo, CcCarUtil.netInfoDao);
//        this.netInfoTblData = new TblData<>(NetInfo.tableTitles,
//                allNetInfo, CcCarUtil.netInfoDao);
////        因为没有 reload
//        this.dataTableCarInfo = new TblData<>(CarInfo.tableTitles,
//                 allCarInfo, CcCarUtil.carInfoDao);
////        this.dataTableCoupon = new TblData<>(Coupon.tableTitles,
////                tblDataCoupon, tabModelCoupon, allCoupon, CcCarUtil.couponDao);
//        this.dataTableCoupon = new TblData<>(Coupon.tableTitles,
//                allCoupon, CcCarUtil.couponDao);
////        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
////                tblDataPromotion, tabModelPromotion, allPromotion, CcCarUtil.promotionDao);
//        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
//             allPromotion, CcCarUtil.promotionDao);
//        TblData<NetInfo>  netOutInfoTblData;
        this.tblCarType = new TblData<>(CarType.tableTitles,
                CcCarUtil.carTypeDao);


        this.netInfoTblData = new TblData<>(NetInfo.tableTitles,
                CcCarUtil.netInfoDao);
//        this.netOutInfoTblData = new TblData<>(NetInfo.tableTitles,
//                CcCarUtil.netInfoDao);
//        因为没有 reload
        this.dataTableCarInfo = new TblDataDo<>(CarInfo.tableTitles,
                CcCarUtil.carInfoDao);
//        this.dataTableCoupon = new TblData<>(Coupon.tableTitles,
//                tblDataCoupon, tabModelCoupon, allCoupon, CcCarUtil.couponDao);
        this.dataTableCoupon = new TblData<>(Coupon.tableTitles,
                CcCarUtil.couponDao);
//        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
//                tblDataPromotion, tabModelPromotion, allPromotion, CcCarUtil.promotionDao);
        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
                CcCarUtil.promotionDao);

//        tblDataList.add(this.tblCarType);

//        tblDataList.add(netInfoTblData);
//        tblDataList.add(dataTableCarInfo);

//        tblDataList.add(netOutInfoTblData);
//        tblDataList.add(dataTableCoupon);
//        tblDataList.add(dataTablePromotion);
//        tblDataList.add(tblScrap);
        tblDataList.add(tblOrderSvo);
//        数据貌似没有写入  难道因为泛型？

//        pnlTitles.setLayout(new GridLayout(1,6));
//            pnlTitles.add(new JTextField("1"));
//            contentPane.add(pnlTitles,BorderLayout.NORTH);
//        this.dataTableCarInfo.

        int x = 0;
        int y = 0;
        int titleIdx = 0;

        for (JTable tblData : tblDataList) {

//            grid  占据 比例
//            JScrollPane jScrollPane = new JScrollPane(tblData);
//            JPanel panel=new JPanel(new GridBagLayout());
//            new JLabel("1");
//            jScrollPane.setSize(100,100);
//            没用
//            PnlTblWithTitle pnlTblWithTitle = new PnlTblWithTitle("1", tblData);
////            不能显示了
//            this.mdpTblPanel.add(pnlTblWithTitle);
//            this.mdpTblPanel.add(jScrollPane);

//            JPanel p = new JPanel();
////            JPanel padding
////            JPanel 内部 自适应
//
//            final JLabel l = new JLabel("暂时未选中条目");
//
//            p.add(l);

//            tblData.setSize(40,40);
//            jScrollPane.setSize(50,50);
////            setSize 也没什么用
//            JPanel pnlBig = new JPanel();
////            JFrame pnlBig = new JFrame();
////            Exception in thread "main" java.lang.IllegalArgumentException: adding a window to a container
////            Container pnlBig = new Container();
//            //            不行的
//
//            pnlBig.setBorder(new EmptyBorder(2, 2, 2, 2));
//            pnlBig.setSize(50,50);
//
////            JScrollPane pnlBig = new JScrollPane();
////            放在 sc 就没了
////            放在 pane 显示的不太好
//            pnlBig.add(p);
//            pnlBig.add(jScrollPane);

            JPanel panel = UiUtil.borderTable(titles[titleIdx++], tblData);

//            this.mdpTblPanel.add(jScrollPane);
//            this.mdpTblPanel.add(west);
            this.mdpTblPanel.add(panel);


//            JScrollPane jScrollPane = new JScrollPane(tblData);
//
//            this.mdpTblPanel.add(jScrollPane);

//            PnlTblWithTitle pnlTblWithTitle=new PnlTblWithTitle("1",tblData);
//            没用

//            showFrame(titles[titleIdx], jScrollPane, x);
//            x += 410;
//            titleIdx++;
//            JFrame frame = new JFrame("1");
//            frame.getContentPane().add(jScrollPane);
//            frame.setSize(400,500);
//            frame.setLocation(x,300);//在屏幕中设置显示的位置
//
//            x+=410;
//            frame.setVisible(true);
//            this.mdpTblPanel.add(pnlBig);
//            this.mdpTblPanel.add(pnlTblWithTitle);
//            this.mdpTblPanel.add(jScrollPane);
//            tblData.reloadTable();
        }
//        tblScrap.reloadDataAndRepaint(new Scrap());
//        dataTableCarInfo.reloadDataAndRepaint(true, new CarInfo());
        OrderSvo orderSvo = new OrderSvo();
        orderSvo.setUserId(User.currentLoginUser.getUserId());
        tblOrderSvo.reloadDataAndRepaint(true,orderSvo);
//        netInfoListen();
//        this.netInfoTblData.addMouseListener();
//        FrmAdmin that=this;

//        System.out.println(netInfoTblData.current);
//        可以拿到的
//        这里写成 车子的了
//      点了 一下马上跳没了 jtable，jtable 选中行 一直选中
//        System.out.println("NetInfo.tableTitles");
//        System.out.println(Arrays.toString(NetInfo.tableTitles));
//      NetInfo.tableTitles
//              [???, ????????, ????, ???, ???]
//        this.mdpTblPanel.add(new JScrollPane(netInfoTblData), BorderLayout.WEST);
//        this.mdpTblPanel.add(new JScrollPane(netInfoTblData));
//        netInfoTblData.reloadTable();
////        netInfoTblData.validate();
////        netInfoTblData.repaint();
////        显示 是需要的
////        在里面 加载了
//
//
////        car
//        System.out.println("================");
//        System.out.println("car");
//        allCarInfo = CcCarUtil.carInfoDao.getBy(new CarInfo());
//
//        // TODO: 2021/9/3    this.tblDataCarInfo
//        this.dataTableCarInfo = new TblData<>(CarInfo.tableTitles,
//                this.tblDataCarInfo, tabModelCarInfo, allCarInfo, CcCarUtil.carInfoDao);
////      点了 一下马上跳没了 jtable，jtable 选中行 一直选中
//        System.out.println("CarInfo.tableTitles");
//        System.out.println(Arrays.toString(CarInfo.tableTitles));
////      NetInfo.tableTitles
////              [???, ????????, ????, ???, ???]
////        this.mdpTblPanel.add(new JScrollPane(dataTableCarInfo), BorderLayout.CENTER);
//        this.mdpTblPanel.add(new JScrollPane(dataTableCarInfo));
//        dataTableCarInfo.reloadTable();
////以上
//
////        下面 oupon
//        System.out.println("================");
//        System.out.println("coupon");
//        allCoupon = CcCarUtil.couponDao.getBy(new Coupon());
//
//        this.dataTableCoupon = new TblData<>(Coupon.tableTitles,
//                tblDataCoupon, tabModelCoupon, allCoupon, CcCarUtil.couponDao);
////      点了 一下马上跳没了 jtable，jtable 选中行 一直选中
////        System.out.println("NetInfo.tableTitles");
////        System.out.println(Arrays.toString(NetInfo.tableTitles));
////      NetInfo.tableTitles
////              [???, ????????, ????, ???, ???]
////        this.mdpTblPanel.add(new JScrollPane(dataTableCoupon), BorderLayout.EAST);
//        this.mdpTblPanel.add(new JScrollPane(dataTableCoupon));
//        dataTableCoupon.reloadTable();
//
//
//
////        下面 促销
//        System.out.println("================");
//        System.out.println("allPromotion");
//        allPromotion = CcCarUtil.promotionDao.getBy(new Promotion());
////Promotion
//        this.dataTablePromotion = new TblData<>(Promotion.tableTitles,
//                tblDataPromotion, tabModelPromotion, allPromotion, CcCarUtil.promotionDao);
////      点了 一下马上跳没了 jtable，jtable 选中行 一直选中
////        System.out.println("NetInfo.tableTitles");
////        System.out.println(Arrays.toString(NetInfo.tableTitles));
////      NetInfo.tableTitles
////              [???, ????????, ????, ???, ???]
////        三个 是不是要放在 grid
////        java  并排四个 layout
////        this.mdpTblPanel.add(new JScrollPane(dataTablePromotion), BorderLayout.EAST);
//        this.mdpTblPanel.add(new JScrollPane(dataTablePromotion));
//        dataTablePromotion.reloadTable();


        // 初始化选择
//            this.reloadMerchantTable();
//            this.currentMerchant = this.allMerchant.get(0);
//            this.reloadProduct_DirTable();
//            this.currentProduct_Dir = this.allProduct_Dir.get(0);
//            this.reloadProductTable();
//            this.currentProduct = this.allProduct.get(0);
//            this.reloadOrderProjectsable();

    }


    void netInfoListen(){
        this.netInfoTblData.addMouseListener(new MouseAdapter() {

            //            mouseClicked 怎么获取外面的数据
//            这是引用 只要弄到一次 以后都可以了
            @Override
            public void mouseClicked(MouseEvent e) {
//          FrmOrder.没有 不行
//                因为这个吗
//                int i = TblData.this.getSelectedRow();
                TblData<NetInfo> tbl = FrmUserOrderStatus.this.netInfoTblData;
//                int i = FrmAdmin.this.netInfoTblData.getSelectedRow();
                int i = tbl.getSelectedRow();
//                这个知道是自己吗
                if (i < 0) {
                    return;
                }
//                这个不是 this的 all？
//                this.
//                这里的this 不一样了

//                current = all.get(i);
//                current = (T) that.all.get(i);
//                这里要 引用一个 不然 用不了 this
//                FrmAdmin.this.currentNetInfo = FrmAdmin.this.allNetInfo.get(i);
//              TblData<NetInfo> tbl=  FrmAdmin.this.netInfoTblData;
//                tbl.current = FrmAdmin.this.netInfoTblData.all.get(i);
                tbl.current = tbl.all.get(i);
//                NetInfo currentNetInfo = FrmAdmin.this.currentNetInfo;
//                NetInfo currentNetInfo = FrmAdmin.this.currentNetInfo;
//                Integer netId = currentNetInfo.getNetId();
                Integer netId = tbl.current.getNetId();
                CarInfo carInfo = new CarInfo();
                carInfo.setNetId(netId);
                TblDataDo<CarInfo> tblCarInfo = FrmUserOrderStatus.this.dataTableCarInfo;
                try {
                    List<CarInfo> by = CcCarUtil.carInfoDao.getBy(carInfo);
//                    这个网点的
//                    FrmAdmin.this.allCarInfo = by;
//                    FrmAdmin.this.dataTableCarInfo.all=by;
                    tblCarInfo.all = by;
//                    tbl.all=by;
//                    让他 根据list 重新加载
                } catch (BaseException | SQLException ex) {
                    ex.printStackTrace();
                }

//                FrmAdmin.this.netInfoTblData. reloadTableByThisList()  ;
//                FrmAdmin.this.dataTableCarInfo.reloadTableByThisList();
                tblCarInfo.reloadTableByThisList();
//                Object o = that.all.get(i);
//                current= (T) o;
//                try {
//                    reloadTable();
////                    是这个 reload的问题吧 。。
//                } catch (BaseException ex) {
//                    ex.printStackTrace();
//                }

//                System.out.println("all.size is " + all.size());
//                System.out.println(all);
                System.out.println("FrmAdmin.this.  currentNetInfo");
                System.out.println(FrmUserOrderStatus.this.currentNetInfo);

//                TblData.this.reloadProduct_DirTable();
                // 每次刷新都顺便增加默认选择
//                TblData.this.currentProduct_Dir = TblData.this.allProduct_Dir.get(0);
//                TblData.this.reloadProductTable();
//                TblData.this.currentProduct = TblData.this.allProduct.get(0);
//                {
//                    // add for 商品分类
//
//                    TblData.this.currentNetInfo = TblData.this.allNetInfo.get(i);
//                    TblData.this.reloadProduct_DirTable();
//                    // 每次刷新都顺便增加默认选择
////                    这有多余的 那就不是 可以模板的了
//                    TblData.this.currentProduct_Dir = TblData.this.allProduct_Dir.get(0);
//                    TblData.this.reloadProductTable();
//                    TblData.this.currentProduct = TblData.this.allProduct.get(0);
//                }
//                {
//                    // Debug OK
//                    System.out.println("allMerchant.size is " + this.allMerchant.size());
//                    System.out.println(this.currentMerchant);
//                }
            }
        });


    }

    void setBottom(Container contentPane) {
        JPanel pnlBottom = new JPanel(new BorderLayout());

        pnlBottom.add(this.toolBarPanel, BorderLayout.SOUTH);

        pnlBottom.add(this.inputPanel, BorderLayout.NORTH);

        contentPane.add(pnlBottom, BorderLayout.SOUTH);
    }

    void showFrame(String title, Component component, int x) {
        JFrame frame = new JFrame(title);
        frame.getContentPane().add(component);
        frame.setSize(400, 500);
        frame.setLocation(x, 300);//在屏幕中设置显示的位置

//        x+=410;
        frame.setVisible(true);
    }

    void test1() throws BaseException, SQLException {
        FrmOrder frmOrder = new FrmOrder();
//    文字是 口
//    convert  gbk 之后 可以了
        frmOrder.setVisible(true);
    }

    static void test2() throws DbException, SQLException, IllegalAccessException {
        NetInfo netInfo = new NetInfo();
        netInfo.setNetId(2);

        User user = new User();
        user.setUserId(2);
//      List<NetInfo> by = JdbcUtil.getBy(netInfo);
//      System.out.println("by");
//      System.out.println(by);

//        JdbcUtil.delete(netInfo);
        JdbcUtil.delete(user);
//        可以删除
//      JdbcUtil.insert();
    }


    public static void main(String[] args) throws BaseException, SQLException, IllegalAccessException {
        test2();
//        by
//                [NetInfo{netId=1, netName='1', city='1', addr='1', phone='1'}]
    }
}