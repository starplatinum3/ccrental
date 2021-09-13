package cn.edu.zucc.personplan.dao;

import cn.edu.zucc.personplan.common.LoginType;
import cn.edu.zucc.personplan.itf.IEmpDao;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.Emp;
import cn.edu.zucc.personplan.model.NetInfo;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpDao implements ItDao<Emp>, IEmpDao {


    @Override
    public List<Emp> getBy(Connection conn, Emp obj) throws BaseException {
        return null;
    }

    @Override
    public void insert(Connection conn, Emp obj) throws DbException {

    }

    @Override
    public void update(Connection conn, Emp obj) throws DbException, BaseException {

    }

    @Override
    public void delete(Connection conn, Emp obj) throws DbException {

    }

    @Override
    public List<Emp> loadAll() throws BaseException {
        List<Emp> by = getBy(new Emp());
        return by;
    }

    public static String q(Object object) {
        return " '" + object + "' ";
    }


    @Override
//    emp 和 user 不一样 那么现在登录的 那个人 是谁 要怎么知道
//    emp 是没有 user id 概念 所以 之间是没有关系的
//    需要判断 是不是 admin
    public Emp login(String username, String pwd, LoginType loginType)
            throws BusinessException, DbException {
// TODO Auto-generated method stub
//        BeanUser user = null;
        if (pwd == null) {
            throw new BusinessException("密码为空");
        }

        Connection conn = null;

        try {
            conn = DBUtil.getConnection();

            Emp emp = new Emp();
            emp.setEmpName(username);
            emp.setPassword(pwd);
            List<Emp> by = getBy(emp);
            if (by.size() == 0) {
//                没有这个人
                throw new BusinessException("用户不存在或者密码错误");
            }
            Emp emp1 = by.get(0);
            if (emp1.getNetId() == null) {
//                是管理员
                if (loginType == LoginType.EMP) {
                    throw new BusinessException("你不是员工");
                }
                return emp1;
            }
//            现在不是管理员了
            if (loginType == LoginType.ADMIN) {
                throw new BusinessException("你不是管理员");
            }
//            正常 员工登陆
            return emp1;
//            return by.get(0);

//            String sql = "select register_time from tbl_user where user_id = ? and user_pwd = ?";
//            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//            pst.setString(1, userid);
//            pst.setString(2, pwd);
//            java.sql.ResultSet rs = pst.executeQuery();
//            if (rs.next()) {
//                user = new BeanUser();
//                user.setUser_id(userid);
//                user.setRegister_time(rs.getTimestamp(1));
//                return user;
//            } else {
//                throw new BusinessException("用户不存在或者密码错误");
//            }
        } catch (SQLException | BaseException ex) {
            throw new DbException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //    @Override
    public List<Emp> getBy(Emp obj) throws BaseException {

        Connection conn = null;
//        Emp obj=(Emp)o;
        try {
            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
//            int step_order = 0;// 需要计算
            String sqlInsert = "select * from emp ";

            Integer empId = obj.getEmpId();
            boolean first = true;
            if (empId != null) {
                if (first) {
                    sqlInsert += "where emp_id = " + q(empId);
                    first = false;
                } else {
                    sqlInsert += "and emp_id = " + q(empId);
                }

            }


            Integer netId = obj.getNetId();
//            boolean first = true;
            if (netId != null) {
                if (first) {
                    sqlInsert += "where net_id = " + q(netId);
                    first = false;
                } else {
                    sqlInsert += "and net_id = " + q(netId);
                }

            }
            String empName = obj.getEmpName();
            if (empName != null) {
                if (first) {
                    sqlInsert += "where emp_name = " + q(empName);
                    first = false;
                } else {
                    sqlInsert += "and emp_name = " + q(empName);
                }

            }

            String password = obj.getPassword();
//			boolean first=true;
            if (password != null) {
                if (first) {
                    sqlInsert += "where password = " + q(password);
                    first = false;
                } else {
                    sqlInsert += "and password = " + q(password);
                }

            }

//            String stepName = obj.getAddr();
//            if (stepName != null) {
//                if (first) {
//                    sqlInsert += "where step_name = " + q(stepName);
//                    first = false;
//                } else {
//                    sqlInsert += "and step_name = " + q(stepName);
//                }
//
//            }
//
//            String phone = obj.getPhone();
//            if (phone != null) {
//                if (first) {
//                    sqlInsert += "where phone = " + q(phone);
//                    first = false;
//                } else {
//                    sqlInsert += "and phone = " + q(phone);
//                }
//
//            }

            System.out.println("sqlInsert");
            System.out.println(sqlInsert);

            PreparedStatement pstByPlanName = conn.prepareStatement(sqlInsert);
//			pstByPlanName.setInt(1, stepId);

            ResultSet resultSet = pstByPlanName.executeQuery();
//			pstByPlanName.setString(2, name);
//			ResultSet resultSetByPlanName = pstByPlanName.executeQuery();
//			System.out.println("resultSetByPlanName");
//			if (resultSetByPlanName.next()) {
//				resultSetByPlanName.close();
//				pstByPlanName.close();
//				throw new BusinessException("同名步骤已经存在");
//			}
//			resultSetByPlanName.close();
//			pstByPlanName.close();

            List<Emp> list = new ArrayList<>();
            while (resultSet.next()) {
//                NetInfo lstObj = new NetInfo();
                Emp lstObj = new Emp();
                lstObj.setEmpId(resultSet.getInt(1));
                lstObj.setNetId((Integer) resultSet.getObject(2));
//                这里 set int 有问题的啊 null 会变成 0
                lstObj.setEmpName(resultSet.getString(3));
                lstObj.setPassword(resultSet.getString(4));
//                lstObj.setAddr(resultSet.getString(4));
//                lstObj.setPhone(resultSet.getString(5));
//                lstObj.setPlanEndTime(resultSet.getTimestamp(6));
//                lstObj.setRealBeginTime(resultSet.getTimestamp(7));
//                lstObj.setRealEndTime(resultSet.getTimestamp(8));
                list.add(lstObj);
//				resultSet.get
//				throw new BusinessException("没找到");
//				return stepDa;
            }
            return list;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DbException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

//        return null;
    }
}
