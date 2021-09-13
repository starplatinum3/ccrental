package cn.edu.zucc.personplan.svo;

//import com.sun.org.apache.bcel.internal.generic.DADD;

import cn.edu.zucc.personplan.model.Do;
import cn.edu.zucc.personplan.model.TblOrder;
import cn.edu.zucc.personplan.tvo.OrderTvo;
import cn.edu.zucc.personplan.tvo.Tvo;

import java.math.BigDecimal;
import java.util.Date;

// sql 的 视图 对应
// 自己造的 名字 sql view object
//data obj
public class OrderSvo implements Do {

    public static final String[] tableTitles = {"序号", "促销id", "返还网点id", "用户id", "备注"};

    Integer orderId;
    Integer promId;
    Integer retNetId;

    Integer userId;
    Integer carId;
    Integer getNetId;
    Integer couponId;
    Date getTime;
    Date retTime;
    Long rentalDur;
    BigDecimal rawPrice;
    BigDecimal finalPrice;
    Integer status;
    BigDecimal discount;
    String userName;
    String brand;
    String typeName;
    BigDecimal deducAmou;
    String license;
    String userPhone;
    String mailbox;
    String getNetName;
    String retNetName;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public Integer getRetNetId() {
        return retNetId;
    }

    public void setRetNetId(Integer retNetId) {
        this.retNetId = retNetId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getGetNetId() {
        return getNetId;
    }

    public void setGetNetId(Integer getNetId) {
        this.getNetId = getNetId;
    }

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BigDecimal getDeducAmou() {
        return deducAmou;
    }

    public void setDeducAmou(BigDecimal deducAmou) {
        this.deducAmou = deducAmou;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String user_phone) {
        this.userPhone = user_phone;
    }

    public String getMailbox() {
        return mailbox;
    }

    public void setMailbox(String mailbox) {
        this.mailbox = mailbox;
    }

    public String getGetNetName() {
        return getNetName;
    }

    public void setGetNetName(String getNetName) {
        this.getNetName = getNetName;
    }

    public String getRetNetName() {
        return retNetName;
    }

    public void setRetNetName(String retNetName) {
        this.retNetName = retNetName;
    }

    public OrderSvo() {

    }

    public OrderSvo(Integer orderId, Integer promId, Integer retNetId, Integer userId, Integer carId, Integer getNetId, Integer couponId, Date getTime, Date retTime, Long rentalDur, BigDecimal rawPrice, BigDecimal finalPrice, Integer status, BigDecimal discount, String userName, String brand, String typeName, BigDecimal deducAmou, String license, String userPhone, String mailbox, String getNetName, String retNetName) {
        this.orderId = orderId;
        this.promId = promId;
        this.retNetId = retNetId;
        this.userId = userId;
        this.carId = carId;
        this.getNetId = getNetId;
        this.couponId = couponId;
        this.getTime = getTime;
        this.retTime = retTime;
        this.rentalDur = rentalDur;
        this.rawPrice = rawPrice;
        this.finalPrice = finalPrice;
        this.status = status;
        this.discount = discount;
        this.userName = userName;
        this.brand = brand;
        this.typeName = typeName;
        this.deducAmou = deducAmou;
        this.license = license;
        this.userPhone = userPhone;
        this.mailbox = mailbox;
        this.getNetName = getNetName;
        this.retNetName = retNetName;
    }

    @Override
    public String toString() {
        return "OrderSvo{" +
                "orderId=" + orderId +
                ", promId=" + promId +
                ", retNetId=" + retNetId +
                ", userId=" + userId +
                ", carId=" + carId +
                ", getNetId=" + getNetId +
                ", couponId=" + couponId +
                ", getTime=" + getTime +
                ", retTime=" + retTime +
                ", rentalDur=" + rentalDur +
                ", rawPrice=" + rawPrice +
                ", finalPrice=" + finalPrice +
                ", status=" + status +
                ", discount=" + discount +
                ", userName='" + userName + '\'' +
                ", brand='" + brand + '\'' +
                ", typeName='" + typeName + '\'' +
                ", deducAmou=" + deducAmou +
                ", license='" + license + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", mailbox='" + mailbox + '\'' +
                ", getNetName='" + getNetName + '\'' +
                ", retNetName='" + retNetName + '\'' +
                '}';
    }

    public static String[] statusLst = {"", "已下单", "已提车", "已还车", "已取消"};

    //    订单状态（下单，提车，还车，取消等）
    public OrderTvo toTvo() {


        return new OrderTvo(this.userName,
                this.getNetName,
                this.retNetName,
                this.getTime,
                this.retTime,
                this.rentalDur,
                this.rawPrice,
                this.finalPrice,
//                this.status
                statusLst[this.status],
                this.discount,
                this.brand,
                this.typeName,
                this.deducAmou,
                this.license,
                this.userPhone,
                this.mailbox);
    }


    //    netInId  还车
    public TblOrder toEntity() {
        return new TblOrder(
                orderId,
                promId,
                retNetId,
                userId,
                carId,
                getNetId,
                couponId,
                getTime,
                retTime,
                rentalDur,
                rawPrice,
                finalPrice,
                status
        );
    }
}
