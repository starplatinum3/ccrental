package cn.edu.zucc.personplan.model;

import cn.edu.zucc.personplan.tvo.CarInfoTvo;
import cn.edu.zucc.personplan.tvo.OrderTvo;
import cn.edu.zucc.personplan.tvo.Tvo;

import java.io.Serializable;
import java.util.*;

/**
 * @author mqp
 * @description car_info
 * @date 2021-09-02
 */
public class CarInfo implements Serializable, CanSetVals, Do {

//    这里是要翻译 状态的

    public static final String[] tableTitles = {"序号", "网点id", "车型id", "车牌", "汽车状态"};
    private static final long serialVersionUID = 1L;
    //    当前状态（空闲，在途，修理报废等）
    public static final String[] carStatusLst = {"", "空闲", "在途", "报废"};

    /**
     * cat_id
     */
    private Integer carId;
//    private Integer catId;
//    private Integer catId;

    /**
     * order_id
     */
//    private Integer orderId;

    /**
     * net_id
     */
    private Integer netId;


    /**
     * type_id
     */
    private Integer typeId;


    /**
     * license
     */
    private String license;

    /**
     * car_status
     */
    private Integer carStatus;


    public CarInfo() {
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

//    public Integer getCatId() {
//        return catId;
//    }
//
//    public void setCatId(Integer catId) {
//        this.catId = catId;
//    }

//    public Integer getOrderId() {
//        return orderId;
//    }
//
//    public void setOrderId(Integer orderId) {
//        this.orderId = orderId;
//    }

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

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Integer getCarStatus() {
        return carStatus;
    }

//    public static final String[] carStatusLst = {"", "空闲", "在途", "报废"};
    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "carId=" + carId +
                ", typeId=" + typeId +
                ", netId=" + netId +
                ", license='" + license + '\'' +
                ", carStatus=" + carStatus +
                '}';
    }

    public CarInfo(List<String> rowVals) {
//      carId= (Integer) rowVals.get(0);
//        this.carId =(Integer) rowVals.get(0);
//        this.typeId =(Integer) rowVals.get(1);
//        this.netId = (Integer) rowVals.get(2);
//        this.license = (String) rowVals.get(3);
//        this.carStatus =(Integer) rowVals.get(4);

//        this.carId =(Integer) rowVals.get(0);
//        this.netId =(Integer) rowVals.get(0);
//        this.typeId = (Integer) rowVals.get(0);
//        this.license = (String) rowVals.get(0);
//        this.carStatus = (Integer) rowVals.get(0);

        this.carId = Integer.parseInt(rowVals.get(0));
        this.netId = Integer.parseInt(rowVals.get(1));
        this.typeId = Integer.parseInt(rowVals.get(2));
        this.license = rowVals.get(3);
        this.carStatus = Integer.parseInt(rowVals.get(4));

    }

//    public CarInfo(Integer carId, Integer typeId, Integer netId, String license, Integer carStatus) {
//        this.carId = carId;
//        this.typeId = typeId;
//        this.netId = netId;
//        this.license = license;
//        this.carStatus = carStatus;
//    }


    public CarInfo(Integer carId, Integer netId, Integer typeId, String license, Integer carStatus) {
        this.carId = carId;
        this.netId = netId;
        this.typeId = typeId;
        this.license = license;
        this.carStatus = carStatus;
    }

    //    更新 是需要 id的 ，但是 如果他填了一个id 怎么办
//    id 应该是永远不需要的
    @Override
    public void setVals(List<String> rowVals) {
//        try{
//            this.carId = Integer.parseInt(rowVals.get(0));
//
//        }catch (NumberFormatException e){
//            this.carId = null;
//        }
        this.netId = Integer.parseInt(rowVals.get(1));
        this.typeId = Integer.parseInt(rowVals.get(2));
        this.license = rowVals.get(3);
//        this.carStatus = Integer.parseInt(rowVals.get(4));
        this.carStatus =  index(carStatusLst,rowVals.get(4));
//        carStatusLst.
//        Arrays
//        Collections.in
//        List<Object> list = new ArrayList<>(carStatusLst);
//        index(carStatus,)
    }

    int index(String[] arr, String val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(val)) {
                return i;
            }
        }
        return -1;
    }


    @Override
    public Tvo toTvo() {
//        return null;

        return new CarInfoTvo(
                this.carId,
                this.netId,
                this.typeId,
                this.license,
                carStatusLst[this.carStatus]);
//        如果是管理员 需要知道他是不是空闲的
//        但是 用户就不需要知道 ，一定是空闲的 ，这样管理员和用户还不一样
//        不过id 应该是不要的
    }
}