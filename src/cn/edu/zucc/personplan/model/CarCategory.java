package cn.edu.zucc.personplan.model;

import cn.edu.zucc.personplan.tvo.CarCategoryTvo;
import cn.edu.zucc.personplan.tvo.CarInfoTvo;
import cn.edu.zucc.personplan.tvo.Tvo;

import java.io.Serializable;
import java.util.List;

/**
 * @author zhengkai.blog.csdn.net
 * @description car_category
 * @date 2021-09-04
 */
public class CarCategory implements Serializable, CanSetVals, Do {

    private static final long serialVersionUID = 1L;

    public static final String[] tableTitles = {"序号", "类型名字", "描述"};

    private Integer catId;

    /**
     * cat_name
     */
    private String catName;

    /**
     * cat_descrip
     */
    private String catDescrip;


    public CarCategory() {
    }

    public Integer getCatId() {
        return catId;
    }

    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDescrip() {
        return catDescrip;
    }

    public void setCatDescrip(String catDescrip) {
        this.catDescrip = catDescrip;
    }

    public CarCategory(Integer catId, String catName, String catDescrip) {
        this.catId = catId;
        this.catName = catName;
        this.catDescrip = catDescrip;
    }

    @Override
    public void setVals(List<String> rowVals) {
//        this.carId = Integer.parseInt(rowVals.get(0));
//        this.netId = Integer.parseInt(rowVals.get(1));
//        this.typeId = Integer.parseInt(rowVals.get(2));
//        this.license = rowVals.get(3);
//        this.carStatus = Integer.parseInt(rowVals.get(4));

//        this.catId = Integer.parseInt(rowVals.get(0));
//        this.catName = rowVals.get(1);
//        this.catDescrip = rowVals.get(2);

//        this.catId = Integer.parseInt(rowVals.get(0));
        this.catName = rowVals.get(0);
        this.catDescrip = rowVals.get(1);
//        对应 tvo
    }

    @Override
    public String toString() {
        return "CarCategory{" +
                "catId=" + catId +
                ", catName='" + catName + '\'' +
                ", catDescrip='" + catDescrip + '\'' +
                '}';
    }

    @Override
    public Tvo toTvo() {
//        return null;

        return new CarCategoryTvo(
                catName,
                catDescrip);
    }
}