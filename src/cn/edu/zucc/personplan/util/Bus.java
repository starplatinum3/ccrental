//package zyy.util;
//package en.edu.
package cn.edu.zucc.personplan.util;
import cn.edu.zucc.personplan.model.TblOrder;

import java.awt.Dimension;
import java.text.SimpleDateFormat;

import javax.swing.JButton;

//import zyy.control.AddrManager;
//import zyy.control.AdminManager;
//import zyy.control.MerchantManager;
//import zyy.control.OrderManager;
//import zyy.control.OrderProjectsManager;
//import zyy.control.ProductManager;
//import zyy.control.Product_DirManager;
//import zyy.control.RiderManager;
//import zyy.control.UsersManager;
//import zyy.model.BeanAddress;
//import zyy.model.BeanAdminUserinfo;
//import zyy.model.BeanAdministrator;
//import zyy.model.BeanOrders;
//import zyy.model.BeanRider;
//import zyy.model.BeanUsers;
//import zyy.model.BeanViewOrder;
//import zyy.ui.FrmAdminMerchant;
//import zyy.ui.FrmAdminRider;
//import zyy.ui.FrmAdminUser;
//import zyy.ui.FrmNewMerchant;
//import zyy.ui.FrmNewPD;
//import zyy.ui.FrmRiderInfo;
//import zyy.ui.FrmUserAddress;
//import zyy.ui.FrmUserGo;
//import zyy.ui.FrmUserInfo;
//import zyy.ui.FrmUserOrders;

/**
 * @Author: SevDaisy十七散人
 * @Date: 2020-07-04 09:17:05
 */
public class Bus {
    // Tool
    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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
    public static String LoginAccountType = "管理员";
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