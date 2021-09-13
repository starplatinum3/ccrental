package cn.edu.zucc.personplan.tvo;

public class CarInfoTvo implements Tvo {
    public static final String[] tableTitles = {"序号", "网点id", "车型id", "车牌", "汽车状态"};

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
    private String  carStatus;

    public CarInfoTvo(Integer carId, Integer netId, Integer typeId, String license, String carStatus) {
        this.carId = carId;
        this.netId = netId;
        this.typeId = typeId;
        this.license = license;
        this.carStatus = carStatus;
    }

    public static String[] getTableTitles() {
        return tableTitles;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getNetId() {
        return netId;
    }

    public void setNetId(Integer netId) {
        this.netId = netId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }
}
