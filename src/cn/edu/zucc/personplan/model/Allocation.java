package cn.edu.zucc.personplan.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description allocation
 * @author zhengkai.blog.csdn.net
 * @date 2021-09-06
 */
public class Allocation implements Serializable {

    public static final String[] tableTitles = { "调度时间", "id", "车子id", "进入网点id","出来网点id" };


    private static final long serialVersionUID = 1L;

    /**
    * alloc_time
    */
    private Date allocTime;

    /**
    * alloc_id
    */
    private Integer allocId;

    /**
     * car_id
     */
    private Integer carId;

    /**
    * net_in_id
    */
    private Integer netInId;

    /**
    * net_out_id
    */
    private Integer netOutId;


    public Allocation() {
    }

    public Date getAllocTime() {
        return allocTime;
    }

    public void setAllocTime(Date allocTime) {
        this.allocTime = allocTime;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getNetInId() {
        return netInId;
    }

    public void setNetInId(Integer netInId) {
        this.netInId = netInId;
    }

    public Integer getNetOutId() {
        return netOutId;
    }

    public void setNetOutId(Integer netOutId) {
        this.netOutId = netOutId;
    }

    public Integer getAllocId() {
        return allocId;
    }

    public void setAllocId(Integer allocId) {
        this.allocId = allocId;
    }

    public Allocation(Date allocTime, Integer allocId, Integer carId, Integer netInId, Integer netOutId) {
        this.allocTime = allocTime;
        this.allocId = allocId;
        this.carId = carId;
        this.netInId = netInId;
        this.netOutId = netOutId;
    }

    @Override
    public String toString() {
        return "Allocation{" +
                "allocTime=" + allocTime +
                ", allocId=" + allocId +
                ", carId=" + carId +
                ", netInId=" + netInId +
                ", netOutId=" + netOutId +
                '}';
    }
}