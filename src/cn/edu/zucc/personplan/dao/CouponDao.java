package cn.edu.zucc.personplan.dao;

import cn.edu.zucc.personplan.model.CarInfo;
import cn.edu.zucc.personplan.model.Coupon;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponDao implements ItDao<Coupon> {
//    @Override
    public List<Coupon> getBy(Connection conn, Coupon obj) throws BaseException {
        return null;
    }

//    @Override
    public void insert(Connection conn, Coupon obj) throws DbException {

    }

    @Override
    public void update(Connection conn, Coupon obj) throws DbException, BaseException {

    }

//    @Override
    public void delete(Connection conn, Coupon obj) throws DbException {

    }

    @Override
    public List<Coupon> loadAll() throws BaseException {
        List<Coupon> by = getBy(new Coupon());
        return by;
    }

    public static String q(Object object) {
        return " '" + object + "' ";
    }

    
//    @Override
    public List<Coupon> getBy(Coupon obj) throws BaseException {
//        return null;


        Connection conn = null;
//        Emp obj=(Emp)o;
        try {
            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
//            int step_order = 0;// 需要计算
            String sql = "select * from coupon ";

            Integer couponId = obj.getCouponId();
            boolean first = true;
            if (couponId != null) {
                if (first) {
                    sql += "where coupon_id = " + q(couponId);
                    first = false;
                } else {
                    sql += "and coupon_id = " + q(couponId);
                }

            }


            String  content = obj.getContent();
//            boolean first = true;
            if (content != null) {
                if (first) {
                    sql += "where content = " + q(content);
                    first = false;
                } else {
                    sql += "and content = " + q(content);
                }

            }

            BigDecimal deducAmou = obj.getDeducAmou();
            if (deducAmou != null) {
                if (first) {
                    sql += "where deduc_amou = " + q(deducAmou);
                    first = false;
                } else {
                    sql += "and deduc_amou = " + q(deducAmou);
                }

            }

            Date startTime= obj.getStartTime();
//			boolean first=true;
            if (startTime != null) {
                if (first) {
                    sql += "where start_time = " + q(startTime);
                    first = false;
                } else {
                    sql += "and start_time = " + q(startTime);
                }

            }

            Date endTime = obj.getEndTime();
            if (endTime != null) {
                if (first) {
                    sql += "where end_time = " + q(endTime);
                    first = false;
                } else {
                    sql += "and end_time = " + q(endTime);
                }

            }

            Integer userId = obj.getUserId();
            if (userId != null) {
                if (first) {
                    sql += "where user_id = " + q(userId);
                    first = false;
                } else {
                    sql += "and user_id = " + q(userId);
                }

            }


//            Integer carStatus = obj.getEndTime();
//            if (carStatus != null) {
//                if (first) {
//                    sql += "where car_status = " + q(carStatus);
//                    first = false;
//                } else {
//                    sql += "and car_status = " + q(carStatus);
//                }
//
//            }
////
//            String phone = obj.getPhone();
//            if (phone != null) {
//                if (first) {
//                    sql += "where phone = " + q(phone);
//                    first = false;
//                } else {
//                    sql += "and phone = " + q(phone);
//                }
//
//            }

            System.out.println("sql");
            System.out.println(sql);

            PreparedStatement pst = conn.prepareStatement(sql);
//			pstByPlanName.setInt(1, stepId);

            ResultSet resultSet = pst.executeQuery();
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

            List<Coupon> list = new ArrayList<>();
            while (resultSet.next()) {
//                NetInfo lstObj = new NetInfo();
                Coupon lstObj = new Coupon();
                lstObj.setCouponId(resultSet.getInt(1));
                lstObj.setContent(resultSet.getString(2));
                lstObj.setDeducAmou(resultSet.getBigDecimal(3));
                lstObj.setStartTime(resultSet.getTimestamp(4));
                lstObj.setEndTime(resultSet.getTimestamp(5));
                lstObj.setUserId(resultSet.getInt(6));
//                lstObj.setLicense(resultSet.getString(4));
//                lstObj.setCarStatus(resultSet.getInt(5));
//                lstObj.setLicense(resultSet.getString(4));
//                lstObj.setLicense(resultSet.getString(4));
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
            DBUtil.closeConnection(conn);
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
        }

    }
}
