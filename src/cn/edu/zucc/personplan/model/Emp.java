package cn.edu.zucc.personplan.model;

import java.io.Serializable;
import java.util.List;

/**
 * @description emp
 * @author zhengkai.blog.csdn.net
 * @date 2021-09-03
 */
public class Emp implements Serializable ,CanSetVals{

    public static final String[] tableTitles = { "员工id", "网点id", "名字", "密码" };
    private static final long serialVersionUID = 1L;

    Integer empId;


    /**
    * net_id
    */
    private Integer netId;

    /**
    * emp_name
    */
    private String empName;

    /**
    * password
    */
    private String password;


    public Emp() {
    }


    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }



    public Integer getNetId() {
        return netId;
    }

    public void setNetId(Integer netId) {
        this.netId = netId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empId=" + empId +
                ", netId=" + netId +
                ", empName='" + empName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Emp(Integer empId, Integer netId, String empName, String password) {
        this.empId = empId;
        this.netId = netId;
        this.empName = empName;
        this.password = password;
    }

    @Override
    public void setVals(List<String> rowVals) {
//        this.catName = rowVals.get(0);
//        this.catDescrip = rowVals.get(1);

//        this.empId = empId;
//        this.netId = netId;
        this.empName =rowVals.get(2);
        this.password = rowVals.get(3);
    }
}