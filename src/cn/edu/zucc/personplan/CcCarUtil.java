package cn.edu.zucc.personplan;

import cn.edu.zucc.personplan.common.LoginType;
import cn.edu.zucc.personplan.comtrol.example.ExamplePlanManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleStepManager;
import cn.edu.zucc.personplan.comtrol.example.ExampleUserManager;
import cn.edu.zucc.personplan.dao.*;
import cn.edu.zucc.personplan.itf.*;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.svo.OrderSvo;
import cn.edu.zucc.personplan.tvo.CarInfoTvo;
import cn.edu.zucc.personplan.ui.TblData;
import cn.edu.zucc.personplan.ui.TblDataDo;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class CcCarUtil {
    //    public static INetInfoDao netInfoDao=new NetInfoDao();//需要换成自行设计的实现类
    public static ItDao<NetInfo> netInfoDao = new NetInfoDao();//需要换成自行设计的实现类
    public static IUserDao userDaoCustom = new UserDao();//需要换成自行设计的实现类
//    public static IStepManager stepManager=new ExampleStepManager();//需要换成自行设计的实现类
//    public static IUserManager userManager=new ExampleUserManager();//需要换成自行设计的实现类

    // Tool
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static   boolean login=false;
    public static Emp currentEmp;
    public static ItDao<Emp> empDao = new EmpDao();
    public static IEmpDao empDaoC = new EmpDao();
    public static ItDao<CarInfo> carInfoDao = new CarInfoDao();
    public static ItDao<Coupon> couponDao = new CouponDao();
    public static ItDao<Promotion> promotionDao = new PromotionDao();
    public static ItDao<CarType> carTypeDao = new CarTypeDao();
    public static ItDao<TblOrder> tblOrderDao = new TblOrderDao();
    public static ItDao<Scrap> scrapDao = new ScrapDao();
    public static ItDao<User> userDao = new UserDao();
    public static ItDao<OrderSvo> orderSvoDao = new OrderSvoDao();
    public static ItDao<CarCategory> carCategoryDao = new CarCategoryDao();
    public static ItDao<Allocation> allocationDao = new AllocationDao();
//    public static ItDao<Allocation> allocationDao = new AllocationDao();

    public static String  imgPath ="img";

//    可以放在 总线 吗，每张表 好像需要的 不一样 这样会一起变的
//    public static TblDataDo<CarInfo> tddCarInfo;
//
//    static {
//        try {
//            tddCarInfo = new TblDataDo<>(CarInfoTvo.tableTitles, CcCarUtil.carInfoDao);
//        } catch (BaseException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

//    他有特殊功能 怎么办 不能是it
    // Manager
//    public static UsersManager userManager = new UsersManager();
//    public static RiderManager riderManager = new RiderManager();
//    public static AdminManager adminManager = new AdminManager();
//    public static MerchantManager merchantManager = new MerchantManager();
//    public static OrderManager orderManager = new OrderManager();
//    public static OrderProjectsManager orderInfoManager = new OrderProjectsManager();
//    public static Product_DirManager Product_DirManager = new Product_DirManager();
//    public static ProductManager ProductManager = new ProductManager();
//    public static AddrManager addrManager = new AddrManager();

    // Current Var
    // public static String LoginAccountType = "用户";
    // public static String LoginAccountType = "骑手";
//    public static String LoginAccountType = "管理员";
//    public static LoginType LoginAccountType = LoginType.ADMIN;
    public static LoginType LoginAccountType =LoginType.USER;
    public static TblOrder currentOrder;
//    public static BeanAddress currentAddress;
//    public static BeanUsers currentUser;
//    public static BeanRider currentRider;
//    public static BeanAdministrator currentAdministrator;
//    public static BeanViewOrder currentVOrder;
//    public static BeanAdminUserinfo curVAdminUser;
//    public static BeanRider curAdminRider;
//    public static String VIPtime;

    // Frame/Dialog
//    public static FrmUserGo fUserGo;
//    public static FrmUserOrders fUserOrders;
//    public static FrmUserAddress fuUserAddress;
//    public static FrmAdminUser fAdminUser;
//    public static FrmAdminRider fAdminRider;
//    public static FrmAdminMerchant fAdminMerchant;
//    public static FrmNewMerchant fNewMerchant;
//    public static FrmNewPD fNewPD;
//    public static FrmUserInfo fUserInfo;
//    public static FrmRiderInfo fRiderInfo;

    // Tool Method
    public static void jButtonZYYStyle(JButton jb) {
        jb.setPreferredSize(new Dimension(85, 50));
        jb.setFont(new java.awt.Font("STKaiti Regular", java.awt.Font.BOLD, 17));
        // start.setContentAreaFilled(false);
        // start.setBorderPainted(false);
    }

    public static String infoVal(String x) {
        return x == null ? "         " : x;
    }
}
