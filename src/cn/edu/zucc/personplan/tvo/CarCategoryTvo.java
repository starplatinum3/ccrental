package cn.edu.zucc.personplan.tvo;

public class CarCategoryTvo implements Tvo {

    public static final String[] tableTitles = { "类型名字", "描述"};


    /**
     * cat_name
     */
    private String catName;

    /**
     * cat_descrip
     */
    private String catDescrip;

    public CarCategoryTvo(String catName, String catDescrip) {
        this.catName = catName;
        this.catDescrip = catDescrip;
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
}
