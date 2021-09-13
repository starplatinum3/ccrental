package cn.edu.zucc.personplan.tvo;

import java.math.BigDecimal;
import java.util.Date;

public class PromotionTvo implements Tvo {

    public static final String[] tableTitles = {
            "折扣","促销数量","开始时间","结束时间" };

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
        return "PromotionTvo{" +
                "discount=" + discount +
                ", promQuantity=" + promQuantity +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }

    public PromotionTvo(BigDecimal discount, Integer promQuantity, Date startTime, Date endTime) {
        this.discount = discount;
        this.promQuantity = promQuantity;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
