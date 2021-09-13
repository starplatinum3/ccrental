package cn.edu.zucc.personplan.model;

import cn.edu.zucc.personplan.util.TimeUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhengkai.blog.csdn.net
 * @description coupon
 * @date 2021-09-03
 */
public class Coupon implements Serializable, CanSetVals {

    public boolean use() {
//        状态（未被领取1，已经被领取2，已经使用3）
        if (status != 2) {
            return false;
        }
        status = 3;
        return true;
    }

    public static final String[] tableTitles = {"优惠券序号", "内容", "减免金额",
            "开始时间", "结束时间", "用户id", "网点id"};

    private static final long serialVersionUID = 1L;


    private Integer couponId;

    public Integer getCouponId() {
        return couponId;
    }

    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }

    /**
     * content
     */
    private String content;

    /**
     * deduc_amou
     */
    private BigDecimal deducAmou;

    /**
     * start_time
     */
    private Date startTime;

    /**
     * end_time
     */
    private Date endTime;

    /**
     * user_id
     */
    private Integer userId;

    private Integer netId;

    private Integer status;


    public Coupon() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BigDecimal getDeducAmou() {
        return deducAmou;
    }

    public void setDeducAmou(BigDecimal deducAmou) {
        this.deducAmou = deducAmou;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNetId() {
        return netId;
    }

    public void setNetId(Integer netId) {
        this.netId = netId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "couponId=" + couponId +
                ", content='" + content + '\'' +
                ", deducAmou=" + deducAmou +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", userId=" + userId +
                ", netId=" + netId +
                ", status=" + status +
                '}';
    }

    public Coupon(Integer couponId, String content, BigDecimal deducAmou, Date startTime, Date endTime, Integer userId, Integer netId) {
        this.couponId = couponId;
        this.content = content;
        this.deducAmou = deducAmou;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
        this.netId = netId;
    }

    public Coupon(Integer couponId, String content, BigDecimal deducAmou, Date startTime, Date endTime, Integer userId, Integer netId, Integer status) {
        this.couponId = couponId;
        this.content = content;
        this.deducAmou = deducAmou;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
        this.netId = netId;
        this.status = status;
    }

    @Override
    public void setVals(List<String> rowVals) {
//        this.carId = Integer.parseInt(rowVals.get(0));
//        this.netId = Integer.parseInt(rowVals.get(1));
//        this.typeId = Integer.parseInt(rowVals.get(2));
//        this.license = rowVals.get(3);
//        this.carStatus = Integer.parseInt(rowVals.get(4));


        this.couponId = Integer.parseInt(rowVals.get(0));
        this.content = rowVals.get(1);
        this.deducAmou = new BigDecimal(rowVals.get(2));
        this.startTime = TimeUtil.stringtoDate(rowVals.get(3), TimeUtil.fmtYmd);
        this.endTime = TimeUtil.stringtoDate(rowVals.get(4), TimeUtil.fmtYmd);
        this.userId = Integer.parseInt(rowVals.get(5));
        this.netId = Integer.parseInt(rowVals.get(6));
        this.status = Integer.parseInt(rowVals.get(6));
    }
}