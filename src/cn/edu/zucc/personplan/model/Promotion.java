package cn.edu.zucc.personplan.model;

import cn.edu.zucc.personplan.tvo.CarCategoryTvo;
import cn.edu.zucc.personplan.tvo.PromotionTvo;
import cn.edu.zucc.personplan.tvo.Tvo;
import cn.edu.zucc.personplan.util.TimeUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 促销
 *
 * @author zhengkai.blog.csdn.net
 * @description promotion
 * @date 2021-09-03
 */
public class Promotion implements Serializable, CanSetVals, Do {

    public boolean useOne() {
        if (promQuantity <= 0) {
            return false;
        }
        promQuantity--;
        return true;
    }

    public static final String[] tableTitles = {"促销id", "车型id", "网点id",
            "折扣", "促销数量", "开始时间", "结束时间"};

    private static final long serialVersionUID = 1L;

    private Integer promId;

    public Integer getPromId() {
        return promId;
    }

    public void setPromId(Integer promId) {
        this.promId = promId;
    }

    /**
     * type_id
     */
    private Integer typeId;

    /**
     * net_id
     */
    private Integer netId;

    /**
     * discount
     */
    private BigDecimal discount;

    /**
     * prom_quantity
     */
    private Integer promQuantity;

    /**
     * start_time
     */
    private Date startTime;

    /**
     * end_time
     */
    private Date endTime;


    public Promotion() {
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getNetId() {
        return netId;
    }

    public void setNetId(Integer netId) {
        this.netId = netId;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Integer getPromQuantity() {
        return promQuantity;
    }

    public void setPromQuantity(Integer promQuantity) {
        this.promQuantity = promQuantity;
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


    @Override
    public String toString() {
        return "Promotion{" +
                "promId=" + promId +
                ", typeId=" + typeId +
                ", netId=" + netId +
                ", discount=" + discount +
                ", promQuantity=" + promQuantity +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public Promotion(Integer promId, Integer typeId, Integer netId, BigDecimal discount, Integer promQuantity, Date startTime, Date endTime) {
        this.promId = promId;
        this.typeId = typeId;
        this.netId = netId;
        this.discount = discount;
        this.promQuantity = promQuantity;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public void setVals(List<String> rowVals) {
//        this.netId =Integer.valueOf( rowVals.get(0));
////        String ss="1";
//
////        传过来的 都是 stre
//        this.netName = rowVals.get(1);
//        this.city = rowVals.get(2);
//        this.addr = rowVals.get(3);
//        this.phone =  rowVals.get(4);


//        this.promId = Integer.valueOf(rowVals.get(0));
//        this.typeId = Integer.valueOf(rowVals.get(1));
//        this.netId = Integer.valueOf(rowVals.get(2));
////        this.discount =BigDecimal.valueOf(rowVals.get(3));
//        this.discount = new BigDecimal(rowVals.get(3));
//        this.promQuantity = Integer.valueOf(rowVals.get(4));
////        String  f
//        this.startTime = TimeUtil.stringtoDate(rowVals.get(5), TimeUtil.fmtYmd);
//        this.endTime = TimeUtil.stringtoDate(rowVals.get(6), TimeUtil.fmtYmd);



//        this.promId = Integer.valueOf(rowVals.get(0));
//        this.typeId = Integer.valueOf(rowVals.get(1));
//        this.netId = Integer.valueOf(rowVals.get(2));
//        this.discount =BigDecimal.valueOf(rowVals.get(3));
        this.discount = new BigDecimal(rowVals.get(0));
        this.promQuantity = Integer.valueOf(rowVals.get(1));
//        String  f
        this.startTime = TimeUtil.stringtoDate(rowVals.get(2), TimeUtil.fmtYmd);
        this.endTime = TimeUtil.stringtoDate(rowVals.get(3), TimeUtil.fmtYmd);
    }

    @Override
    public Tvo toTvo() {
        return new PromotionTvo(
                this.discount,
                this.promQuantity,
                this.startTime,
                this.endTime);
    }
}