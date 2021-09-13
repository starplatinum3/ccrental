package cn.edu.zucc.personplan.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @description scrap
 * @author  mqp
 * @date 2021-09-04
 */
public class Scrap implements Serializable {

    public static final String[] tableTitles = { "序号", "车id", "员工id", "操作时间","备注" };


    private static final long serialVersionUID = 1L;

    private  Integer crapId;

    /**
    * car_id
    */
    private Integer carId;

    /**
    * emp_id
    */
    private Integer empId;

    /**
    * op_time
    */
    private Date opTime;

    /**
    * remark
    */
    private String remark;


    public Scrap() {
    }

    public Integer getCrapId() {
        return crapId;
    }

    public void setCrapId(Integer crapId) {
        this.crapId = crapId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Scrap(Integer crapId, Integer carId, Integer empId, Date opTime, String remark) {
        this.crapId = crapId;
        this.carId = carId;
        this.empId = empId;
        this.opTime = opTime;
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Scrap{" +
                "crapId=" + crapId +
                ", carId=" + carId +
                ", empId=" + empId +
                ", opTime=" + opTime +
                ", remark='" + remark + '\'' +
                '}';
    }
}