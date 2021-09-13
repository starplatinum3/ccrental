package cn.edu.zucc.personplan.model;

import cn.edu.zucc.personplan.tvo.Tvo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/**
 * @description net_info
 * @author zhengkai.blog.csdn.net
 * @date 2021-09-02
 */
public class NetInfo implements Serializable, WithTitles, CanSetVals, Do {

    private static final long serialVersionUID = 1L;

    public static final String[] tableTitles = { "序号", "网点名称", "城市", "地址","手机" };

    Integer netId;


    /**
    * net_name
    */
    private String netName;

    /**
    * city
    */
    private String city;

    /**
    * addr
    */
    private String addr;

    /**
    * phone
    */
    private String phone;


    public NetInfo() {
    }


    public Integer getNetId() {
        return netId;
    }

    public void setNetId(Integer netId) {
        this.netId = netId;
    }



    public String getNetName() {
        return netName;
    }

    public void setNetName(String netName) {
        this.netName = netName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String[] getTitles() {
        return tableTitles;
    }

    @Override
    public String toString() {
        return "NetInfo{" +
                "netId=" + netId +
                ", netName='" + netName + '\'' +
                ", city='" + city + '\'' +
                ", addr='" + addr + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public NetInfo(Integer netId, String netName, String city, String addr, String phone) {
        this.netId = netId;
        this.netName = netName;
        this.city = city;
        this.addr = addr;
        this.phone = phone;
    }

    public  NetInfo(List<String> rowVals){
//      carId= (Integer) rowVals.get(0);
//        this.carId =(Integer) rowVals.get(0);
//        this.typeId =(Integer) rowVals.get(1);
//        this.netId = (Integer) rowVals.get(2);
//        this.license = (String) rowVals.get(3);
//        this.carStatus =(Integer) rowVals.get(4);

//        this.netId =(Integer) rowVals.get(0);
        this.netId =Integer.valueOf( rowVals.get(0));
//        String ss="1";

//        传过来的 都是 stre
        this.netName = rowVals.get(1);
        this.city = rowVals.get(2);
        this.addr = rowVals.get(3);
        this.phone =  rowVals.get(4);
    }

    @Override
    public void setVals(List<String> rowVals) {
//        不需要 id
//        但是 更新的时候 需要 ,插入不需要
        try{
            this.netId =Integer.valueOf( rowVals.get(0));

        }catch (NumberFormatException e){
            this.netId=null;
        }

//        String ss="1";

//        传过来的 都是 stre
        this.netName = rowVals.get(1);
        this.city = rowVals.get(2);
        this.addr = rowVals.get(3);
        this.phone =  rowVals.get(4);
    }

    @Override
    public Tvo toTvo() {
        return null;
    }
}