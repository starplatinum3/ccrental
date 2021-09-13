package cn.edu.zucc.personplan.tvo;

//import com.sun.org.apache.bcel.internal.generic.DADD;

import java.math.BigDecimal;
import java.util.Date;

// sql 的 视图 对应
// 自己造的 名字 sql view object
public class OrderLittleTvo implements Tvo{

//    public static final String[] tableTitles = {"用户名", "提取网点", "返还网点", "提车时间", "还车时间",
//    "借车时长(天)","原价","减免之后的价格","状态","折扣","车品牌","车型","减免金额","车牌","电话","邮箱"};

    public static final String[] tableTitles = { "提车时间", "还车时间",
            "借车时长(天)","原价","减免之后的价格","状态"};
    public static final String[] statusLst = { "下单", "提车",
            "还车","取消","减免之后的价格","状态"};
//    订单状态（下单，提车，还车，取消等）
    //    Integer orderId;
//    Integer promId;
//    Integer retNetId;
//
//    Integer userId;
//    Integer carId;
//    Integer getNetId;
//    Integer couponId;
//    String userName;
//    String getNetName;
//    String retNetName;


    Date getTime;
    Date retTime;
    Long rentalDur;
    BigDecimal rawPrice;
    BigDecimal finalPrice;
//    Integer status;
    String  status;


//    BigDecimal discount;
//
//    String brand;
//    String typeName;
//    BigDecimal deducAmou;
//    String license;
//    String userPhone;
//    String mailbox;



    public Date getGetTime() {
        return getTime;
    }

    public void setGetTime(Date getTime) {
        this.getTime = getTime;
    }

    public Date getRetTime() {
        return retTime;
    }

    public void setRetTime(Date retTime) {
        this.retTime = retTime;
    }

    public Long getRentalDur() {
        return rentalDur;
    }

    public void setRentalDur(Long rentalDur) {
        this.rentalDur = rentalDur;
    }

    public BigDecimal getRawPrice() {
        return rawPrice;
    }

    public void setRawPrice(BigDecimal rawPrice) {
        this.rawPrice = rawPrice;
    }

    public BigDecimal getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(BigDecimal finalPrice) {
        this.finalPrice = finalPrice;
    }

    public String  getStatus() {
        return status;
    }

    public void setStatus(String  status) {
        this.status = status;
    }
//
//    public BigDecimal getDiscount() {
//        return discount;
//    }
//
//    public void setDiscount(BigDecimal discount) {
//        this.discount = discount;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getBrand() {
//        return brand;
//    }
//
//    public void setBrand(String brand) {
//        this.brand = brand;
//    }
//
//    public String getTypeName() {
//        return typeName;
//    }
//
//    public void setTypeName(String typeName) {
//        this.typeName = typeName;
//    }
//
//    public BigDecimal getDeducAmou() {
//        return deducAmou;
//    }
//
//    public void setDeducAmou(BigDecimal deducAmou) {
//        this.deducAmou = deducAmou;
//    }
//
//    public String getLicense() {
//        return license;
//    }
//
//    public void setLicense(String license) {
//        this.license = license;
//    }
//
//    public String getUserPhone() {
//        return userPhone;
//    }
//
//    public void setUserPhone(String user_phone) {
//        this.userPhone = user_phone;
//    }
//
//    public String getMailbox() {
//        return mailbox;
//    }
//
//    public void setMailbox(String mailbox) {
//        this.mailbox = mailbox;
//    }
//
//    public String getGetNetName() {
//        return getNetName;
//    }
//
//    public void setGetNetName(String getNetName) {
//        this.getNetName = getNetName;
//    }
//
//    public String getRetNetName() {
//        return retNetName;
//    }
//
//    public void setRetNetName(String retNetName) {
//        this.retNetName = retNetName;
//    }

    public OrderLittleTvo() {

    }

//    public OrderLittleTvo(String userName, String getNetName, String retNetName,
//                          Date getTime, Date retTime, Long rentalDur,
//                          BigDecimal rawPrice, BigDecimal finalPrice,
//                          String  status, BigDecimal discount, String brand, String typeName, BigDecimal deducAmou,
//                          String license, String userPhone, String mailbox) {
//        this.userName = userName;
//        this.getNetName = getNetName;
//        this.retNetName = retNetName;
//        this.getTime = getTime;
//        this.retTime = retTime;
//        this.rentalDur = rentalDur;
//        this.rawPrice = rawPrice;
//        this.finalPrice = finalPrice;
//        this.status = status;
//        this.discount = discount;
//        this.brand = brand;
//        this.typeName = typeName;
//        this.deducAmou = deducAmou;
//        this.license = license;
//        this.userPhone = userPhone;
//        this.mailbox = mailbox;
//    }
//    订单状态（下单，提车，还车，取消等）

    public OrderLittleTvo(Date getTime, Date retTime, Long rentalDur, BigDecimal rawPrice, BigDecimal finalPrice, String status) {
        this.getTime = getTime;
        this.retTime = retTime;
        this.rentalDur = rentalDur;
        this.rawPrice = rawPrice;
        this.finalPrice = finalPrice;
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderLittleTvo{" +
                "getTime=" + getTime +
                ", retTime=" + retTime +
                ", rentalDur=" + rentalDur +
                ", rawPrice=" + rawPrice +
                ", finalPrice=" + finalPrice +
                ", status='" + status + '\'' +
                '}';
    }
}
