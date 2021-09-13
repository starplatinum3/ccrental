package cn.edu.zucc.personplan.model;

import cn.edu.zucc.personplan.tvo.CarCategoryTvo;
import cn.edu.zucc.personplan.tvo.CarTypeTvo;
import cn.edu.zucc.personplan.tvo.Tvo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author zhengkai.blog.csdn.net
 * @description car_type
 * @date 2021-09-03
 */
public class CarType implements Serializable, CanSetVals, Do {

    public static final String[] tableTitles = {"序号", "牌子", "类型id", "品牌名字", "排量", "排挡", "座位数", "价格",
            "图片"};

    //    l? 车型信息（至少包括：车型编号，车型名称（如：朗逸，帕萨特，雷凌等），
//    品牌（如：大众，丰田，雪佛兰等），排量，排挡（自动挡，手动挡），座位数，价格，图片等信息） （加上 类别编号）
    private static final long serialVersionUID = 1L;

    private Integer typeId;


    /**
     * brand
     */
    private String brand;

    /**
     * cat_id
     */
    private Integer catId;

    /**
     * type_name
     */
    private String typeName;

    /**
     * displacement
     */
    private BigDecimal displacement;

    /**
     * gear
     */
    private Integer gear;

    /**
     * seat_num
     */
    private Integer seatNum;

    /**
     * price
     */
    private BigDecimal price;

    /**
     * pic
     */
    private String pic;


    public CarType() {
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }


    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BigDecimal getDisplacement() {
        return displacement;
    }

    public void setDisplacement(BigDecimal displacement) {
        this.displacement = displacement;
    }

    public Integer getGear() {
        return gear;
    }

    public void setGear(Integer gear) {
        this.gear = gear;
    }

    public Integer getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(Integer seatNum) {
        this.seatNum = seatNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "CarType{" +
                "typeId=" + typeId +
                ", brand='" + brand + '\'' +
                ", catId=" + catId +
                ", typeName='" + typeName + '\'' +
                ", displacement=" + displacement +
                ", gear=" + gear +
                ", seatNum=" + seatNum +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                '}';
    }

    public CarType(Integer typeId, String brand, Integer catId, String typeName, BigDecimal displacement, Integer gear, Integer seatNum, BigDecimal price, String pic) {
        this.typeId = typeId;
        this.brand = brand;
        this.catId = catId;
        this.typeName = typeName;
        this.displacement = displacement;
        this.gear = gear;
        this.seatNum = seatNum;
        this.price = price;
        this.pic = pic;
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


//        this.typeId = Integer.valueOf(rowVals.get(0));
//        this.brand = rowVals.get(1);
//        this.catId = Integer.valueOf(rowVals.get(2));
//        this.typeName = rowVals.get(3);
//        this.displacement = new BigDecimal(rowVals.get(4));
//        this.gear = Integer.valueOf(rowVals.get(5));
//        this.seatNum = Integer.valueOf(rowVals.get(6));
//        this.price = new BigDecimal(rowVals.get(7));
//        this.pic = rowVals.get(8);



//        this.typeId = Integer.valueOf(rowVals.get(0));
        this.brand = rowVals.get(0);
//        this.catId = Integer.valueOf(rowVals.get(2));
        this.typeName = rowVals.get(1);
        this.displacement = new BigDecimal(rowVals.get(2));
        this.gear = Integer.valueOf(rowVals.get(3));
        this.seatNum = Integer.valueOf(rowVals.get(4));
        this.price = new BigDecimal(rowVals.get(5));
        this.pic = rowVals.get(6);
    }

    public static final String[] gearLst = {"", "自动挡", "手动挡", "品牌名字", "排量", "排挡", "座位数", "价格",
            "图片"};

    @Override
    public Tvo toTvo() {
        return new CarTypeTvo(
                brand,
                typeName,
                displacement,
//                gear,
                gearLst[gear],
                seatNum,
                price,
                pic);
    }
}