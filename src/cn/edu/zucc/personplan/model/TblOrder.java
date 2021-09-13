package cn.edu.zucc.personplan.model;

import cn.edu.zucc.personplan.tvo.CarCategoryTvo;
import cn.edu.zucc.personplan.tvo.OrderLittleTvo;
import cn.edu.zucc.personplan.tvo.Tvo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description tbl_order
 * @author mqp
 * @date 2021-09-02
 */
//public class TblOrder implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    Integer orderId;
//
//    public Integer getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Integer orderId) {
//        this.orderId = orderId;
//    }
//
//    /**
//    * net_id
//    */
//    private Integer netId;
//
//    /**
//    * user_id
//    */
//    private Integer userId;
//
//    /**
//    * car_id
//    */
//    private Integer carId;
//
//    /**
//    * net_net_id
//    */
//    private Integer netNetId;
//
//    /**
//    * coupon_id
//    */
//    private Integer couponId;
//
//    /**
//    * get_time
//    */
//    private Date getTime;
//
//    /**
//    * ret_time
//    */
//    private Date retTime;
//
//    /**
//    * rental_dur
//    */
//    private Integer rentalDur;
//
//    /**
//    * raw_price
//    */
//    private BigDecimal rawPrice;
//
//    /**
//    * final_price
//    */
//    private BigDecimal finalPrice;
//
//    /**
//    * status
//    */
//    private Integer status;
//
//
//    public TblOrder() {
//    }
//
//    public Integer getNetId() {
//        return netId;
//    }
//
//    public void setNetId(Integer netId) {
//        this.netId = netId;
//    }
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public Integer getCarId() {
//        return carId;
//    }
//
//    public void setCarId(Integer carId) {
//        this.carId = carId;
//    }
//
//    public Integer getNetNetId() {
//        return netNetId;
//    }
//
//    public void setNetNetId(Integer netNetId) {
//        this.netNetId = netNetId;
//    }
//
//    public Integer getCouponId() {
//        return couponId;
//    }
//
//    public void setCouponId(Integer couponId) {
//        this.couponId = couponId;
//    }
//
//    public Date getGetTime() {
//        return getTime;
//    }
//
//    public void setGetTime(Date getTime) {
//        this.getTime = getTime;
//    }
//
//    public Date getRetTime() {
//        return retTime;
//    }
//
//    public void setRetTime(Date retTime) {
//        this.retTime = retTime;
//    }
//
//    public Integer getRentalDur() {
//        return rentalDur;
//    }
//
//    public void setRentalDur(Integer rentalDur) {
//        this.rentalDur = rentalDur;
//    }
//
//    public BigDecimal getRawPrice() {
//        return rawPrice;
//    }
//
//    public void setRawPrice(BigDecimal rawPrice) {
//        this.rawPrice = rawPrice;
//    }
//
//    public BigDecimal getFinalPrice() {
//        return finalPrice;
//    }
//
//    public void setFinalPrice(BigDecimal finalPrice) {
//        this.finalPrice = finalPrice;
//    }
//
//    public Integer getStatus() {
//        return status;
//    }
//
//    public void setStatus(Integer status) {
//        this.status = status;
//    }
//
//    public TblOrder(Integer netId, Integer userId, Integer carId,
//                    Integer netNetId, Integer couponId, Date getTime,
//                    Date retTime, Integer rentalDur, BigDecimal rawPrice,
//                    BigDecimal finalPrice, Integer status) {
//        this.netId = netId;
//        this.userId = userId;
//        this.carId = carId;
//        this.netNetId = netNetId;
//        this.couponId = couponId;
//        this.getTime = getTime;
//        this.retTime = retTime;
//        this.rentalDur = rentalDur;
//        this.rawPrice = rawPrice;
//        this.finalPrice = finalPrice;
//        this.status = status;
//    }
//
//    public TblOrder(Integer orderId, Integer netId, Integer userId, Integer carId, Integer netNetId, Integer couponId, Date getTime, Date retTime, Integer rentalDur, BigDecimal rawPrice, BigDecimal finalPrice, Integer status) {
//        this.orderId = orderId;
//        this.netId = netId;
//        this.userId = userId;
//        this.carId = carId;
//        this.netNetId = netNetId;
//        this.couponId = couponId;
//        this.getTime = getTime;
//        this.retTime = retTime;
//        this.rentalDur = rentalDur;
//        this.rawPrice = rawPrice;
//        this.finalPrice = finalPrice;
//        this.status = status;
//    }
//
//    @Override
//    public String toString() {
//        return "TblOrder{" +
//                "netId=" + netId +
//                ", userId=" + userId +
//                ", carId=" + carId +
//                ", netNetId=" + netNetId +
//                ", couponId=" + couponId +
//                ", getTime=" + getTime +
//                ", retTime=" + retTime +
//                ", rentalDur=" + rentalDur +
//                ", rawPrice=" + rawPrice +
//                ", finalPrice=" + finalPrice +
//                ", status=" + status +
//                '}';
//    }
//}

/**
 * @author zhengkai.blog.csdn.net
 * @description tbl_order
 * @date 2021-09-03
 */
public class TblOrder implements Serializable, Do {

    //    l? 商品订单表（至少包括订单编号，用户编号，提车网点 out，，
//    车辆编号，提车时间 out ，还车网点 in ，还车时间，租车时长（不到一天，按一天计），
//    原始金额，结算金额，使用优惠券编号，订单状态（下单，提车，还车，取消等）等）
    public static final String[] tableTitles = {"id", "促销id", "还车网点",
            "用户id", "车id", "提车网点", "优惠券", "提车时间", "还车时间", "借的时间",
            "原来价钱", "最终价钱", "订单状态"};

    private static final long serialVersionUID = 1L;


    private Integer orderId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }


    /**
     * prom_id
     */
    private Integer promId;

    /**
     * net_in_id
     * ，还车网点 in
     */
    private Integer netInId;

    /**
     * user_id
     */
    private Integer userId;

    /**
     * car_id
     */
    private Integer carId;

    /**
     * net_out_id、
     * ，提车网点 out，
     */
    private Integer netOutId;

    /**
     * coupon_id
     */
    private Integer couponId;

    /**
     * get_time
     */
    private Date getTime;

    /**
     * ret_time
     */
    private Date retTime;

    /**
     * rental_dur
     */
//    private Integer rentalDur;
    private Long rentalDur;

    /**
     * raw_price
     */
    private BigDecimal rawPrice;

    /**
     * final_price
     */
    private BigDecimal finalPrice;

    /**
     * status
     */
    private Integer status;


    public TblOrder() {
    }

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    public Integer getNetInId() {
        return netInId;
    }

    public void setNetInId(Integer netInId) {
        this.netInId = netInId;
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

    public Integer getNetOutId() {
        return netOutId;
    }

    public void setNetOutId(Integer netOutId) {
        this.netOutId = netOutId;
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

//    public Integer getRentalDur() {
//        return rentalDur;
//    }
//
//    public void setRentalDur(Integer rentalDur) {
//        this.rentalDur = rentalDur;
//    }

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


    public TblOrder(Integer orderId, Integer promId, Integer netInId, Integer userId, Integer carId, Integer netOutId
            , Integer couponId, Date getTime, Date retTime, Long rentalDur, BigDecimal rawPrice, BigDecimal finalPrice,
                    Integer status) {
        this.orderId = orderId;
        this.promId = promId;
        this.netInId = netInId;
        this.userId = userId;
        this.carId = carId;
        this.netOutId = netOutId;
        this.couponId = couponId;
        this.getTime = getTime;
        this.retTime = retTime;
        this.rentalDur = rentalDur;
        this.rawPrice = rawPrice;
        this.finalPrice = finalPrice;
        this.status = status;
    }

    @Override
    public String toString() {
        return "TblOrder{" +
                "orderId=" + orderId +
                ", promId=" + promId +
                ", netInId=" + netInId +
                ", userId=" + userId +
                ", carId=" + carId +
                ", netOutId=" + netOutId +
                ", couponId=" + couponId +
                ", getTime=" + getTime +
                ", retTime=" + retTime +
                ", rentalDur=" + rentalDur +
                ", rawPrice=" + rawPrice +
                ", finalPrice=" + finalPrice +
                ", status=" + status +
                '}';
    }

    //    订单状态（下单，提车，还车，取消等）
    @Override
    public Tvo toTvo() {
        return new OrderLittleTvo(
                this.getTime,
                this.retTime,
                this.rentalDur,
                this.rawPrice,
                this.finalPrice,
//        this.status ,
                OrderLittleTvo.statusLst[this.status - 1]
        );
    }

//  TblOrder   toEntity(){
//     new TblOrder()
//    }
}