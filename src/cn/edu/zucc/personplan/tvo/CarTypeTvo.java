package cn.edu.zucc.personplan.tvo;

import java.math.BigDecimal;

public class CarTypeTvo implements Tvo {

    /**
     * brand
     */
    private String brand;


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
//    private Integer gear;
    private String  gear;

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


//    public static final String[] tableTitles = { "类型名字", "描述"};


//    public static final String[] tableTitles = { "牌子", "品牌名字", "排量", "排挡", "座位数", "价格",
//            "图片"};

    public static final String[] tableTitles = { "品牌", "类型名", "排量", "排挡", "座位数", "价格",
            "图片"};

    public CarTypeTvo(String brand, String typeName, BigDecimal displacement, String  gear, Integer seatNum,
                      BigDecimal price, String pic) {
        this.brand = brand;
        this.typeName = typeName;
        this.displacement = displacement;
        this.gear = gear;
        this.seatNum = seatNum;
        this.price = price;
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "CarTypeTvo{" +
                "brand='" + brand + '\'' +
                ", typeName='" + typeName + '\'' +
                ", displacement=" + displacement +
                ", gear=" + gear +
                ", seatNum=" + seatNum +
                ", price=" + price +
                ", pic='" + pic + '\'' +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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

    public String  getGear() {
        return gear;
    }

    public void setGear(String gear) {
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
}
