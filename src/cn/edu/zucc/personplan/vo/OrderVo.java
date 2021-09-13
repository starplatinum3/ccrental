package cn.edu.zucc.personplan.vo;

import cn.edu.zucc.personplan.model.TblOrder;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderVo {

    //    l? 商品订单表（至少包括订单编号，用户编号，提车网点 out，，
//    车辆编号，提车时间 out ，还车网点 in ，还车时间，租车时长（不到一天，按一天计），
//    原始金额，结算金额，使用优惠券编号，订单状态（下单，提车，还车，取消等）等）
    public static final String[] tableTitles = { "id", "促销id", "还车网点",
            "用户id","车id","提车网点","优惠券","提车时间","还车时间","借的时间","原来价钱","最终价钱","订单状态"};

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
    private String status;


    public OrderVo() {
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

//    l? 商品订单表（至少包括订单编号，用户编号，提车网点，，车辆编号，提车时间，还车网点，还车时间，租车时长（不到一天，按一天计），
//    原始金额，结算金额，使用优惠券编号，订单状态（下单，提车，还车，取消等）等）

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;

    }

    static void register(){

        ConvertUtils.register((clazz, value) -> {
            //将String转化为date
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            Date parse=null;
            try {
//parse()返回的是一个Date类型数据，format返回的是一个StringBuffer类型的数据

                parse =format.parse(value.toString());
            } catch (ParseException e) {

                e.printStackTrace();
            }
            return parse;
        }, Date.class);
//————————————————
//        版权声明：本文为CSDN博主「fulisha_la」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/fulishafulisha/article/details/82013558

//        BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
//        BeanUtils. set

    }
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        OrderVo orderVo = new OrderVo();
        TblOrder tblOrder = new TblOrder();
        tblOrder.setStatus(1);
        tblOrder.setCarId(2);

        register();
        BeanUtils.copyProperties(orderVo,tblOrder);
        System.out.println("orderVo");
        System.out.println(orderVo);


//
    }

    public OrderVo(Integer orderId, Integer promId, Integer netInId, Integer userId, Integer carId, Integer netOutId
            , Integer couponId, Date getTime, Date retTime, Long rentalDur, BigDecimal rawPrice, BigDecimal finalPrice,
                   String status) {
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
}
