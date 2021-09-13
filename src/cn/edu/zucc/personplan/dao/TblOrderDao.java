package cn.edu.zucc.personplan.dao;

import cn.edu.zucc.personplan.model.CarInfo;
import cn.edu.zucc.personplan.model.Promotion;
import cn.edu.zucc.personplan.model.Scrap;
import cn.edu.zucc.personplan.model.TblOrder;
import cn.edu.zucc.personplan.util.*;
import sun.management.jdp.JdpController;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class TblOrderDao implements ItDao<TblOrder> {
    public void delete(Connection conn, TblOrder obj) throws DbException {

//        Connection conn = null;
        try {
//            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
//            int step_order = 0;// 需要计算
//            String sqlInsert = " UPDATE car_info\n" +
//                    "    SET\n" +
//                    "        cat_id = ?,\n" +
//                    "        order_id = ?,\n" +
//                    "        type_id = ?\n" +
//                    "        net_id = ?,\n" +
//                    "        license = ?,\n" +
//                    "        car_status = ?\n" +
//                    "    WHERE\n" +
//                    "        car_id = ? ";

            String sqlDel = "DELETE\n" +
                    "FROM\n" +
                    "    tbl_order\n" +
                    "WHERE  order_id=?";
            PreparedStatement pstByPlanName = conn.prepareStatement(sqlDel);
//            pstByPlanName.setInt(1, carInfo.getCatId());
//            pstByPlanName.setInt(2, carInfo.getOrderId());
//            pstByPlanName.setInt(3, carInfo.getTypeId());
//            pstByPlanName.setInt(4, carInfo.getNetId());
//            pstByPlanName.setString(5, carInfo.getLicense());
//            pstByPlanName.setInt(6, carInfo.getCarStatus());

//update 的时候 也 id  这样 编号就不用重新写了
            pstByPlanName.setInt(1, obj.getOrderId());
//            pstByPlanName.setInt(2, obj.getPromId());
//            pstByPlanName.setInt(3, obj.getNetInId());
//            pstByPlanName.setInt(4, obj.getUserId());
//            pstByPlanName.setInt(5, obj.getCarId());
//            pstByPlanName.setInt(6, obj.getNetOutId());
//            pstByPlanName.setInt(7, obj.getCouponId());
//            pstByPlanName.setTimestamp(8, TimeUtil.dateToSqlTime(obj.getGetTime()));
//            pstByPlanName.setTimestamp(9, TimeUtil.dateToSqlTime(obj.getRetTime()));
//            pstByPlanName.setInt(10, obj.getRentalDur());
//            pstByPlanName.setBigDecimal(11, obj.getRawPrice());
//            pstByPlanName.setBigDecimal(12, obj.getFinalPrice());
//            pstByPlanName.setInt(13, obj.getStatus());
//            pstByPlanName.setInt(12, obj.getOrderId());
//

            pstByPlanName.execute();
//			pstByPlanName.setString(2, name);
//			ResultSet resultSetByPlanName = pstByPlanName.executeQuery();
//			System.out.println("resultSetByPlanName");
//			if (resultSetByPlanName.next()) {
//				resultSetByPlanName.close();
//				pstByPlanName.close();
//				throw new BusinessException("同名步骤已经存在");
//			}
//			resultSetByPlanName.close();
            pstByPlanName.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DbException(ex);
        }

    }


    @Override
    public List<TblOrder> getBy(Connection conn, TblOrder obj) throws BaseException {
/*
        public List<T> getBy(Connection conn, String sql, T obj) throws DbException {
JdbcUtil<TblOrder> jdbcUtil =new JdbcUtil<>();
String sql="se"
jdbcUtil.getBy(conn,)
*/
        List<TblOrder> tbl_order = JdbcUtil.getBy(conn, obj, "tbl_order");
//        return null;
        return tbl_order;
    }

    public void insert(Connection conn, TblOrder obj) throws DbException {

//        Connection conn = null;
        try {
//            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
//            int step_order = 0;// 需要计算
//            String sqlInsert = " UPDATE car_info\n" +
//                    "    SET\n" +
//                    "        cat_id = ?,\n" +
//                    "        order_id = ?,\n" +
//                    "        type_id = ?\n" +
//                    "        net_id = ?,\n" +
//                    "        license = ?,\n" +
//                    "        car_status = ?\n" +
//                    "    WHERE\n" +
//                    "        car_id = ? ";

            String sqlUp = "INSERT INTO tbl_order ( order_id,prom_id,net_in_id,user_id,car_id,net_out_id,coupon_id,get_time,ret_time,rental_dur,raw_price,final_price,status )\n" +
                    "VALUES\n" +
                    "(\n" +
                    "    ?,?,?,?,?,?,?,?,?,?,?,?,?\n" +
                    ");";
            PreparedStatement pstByPlanName = conn.prepareStatement(sqlUp);
//            pstByPlanName.setInt(1, carInfo.getCatId());
//            pstByPlanName.setInt(2, carInfo.getOrderId());
//            pstByPlanName.setInt(3, carInfo.getTypeId());
//            pstByPlanName.setInt(4, carInfo.getNetId());
//            pstByPlanName.setString(5, carInfo.getLicense());
//            pstByPlanName.setInt(6, carInfo.getCarStatus());

            JdbcUtil.set(pstByPlanName, obj);
//            可以使用了

//update 的时候 也 id  这样 编号就不用重新写了
//            Integer orderId = obj.getOrderId();
//            if(orderId==null){
//                pstByPlanName.setNull(1, Types.INTEGER);
////                pstByPlanName.se
//            }else{
//                pstByPlanName.setInt(1, orderId);
//            }
//
//            // TODO: 2021/9/4
//            JdbcUtil.set(pstByPlanName,1,obj.getOrderId());
//            Class<? extends TblOrder> aClass = obj.getClass();
////            aClass.
////            反射 所有的 get
////            pstByPlanName.set
////            如果是null 怎么办
////            jdbc set int 假如是 null，自动
//            pstByPlanName.setInt(2, obj.getPromId());
//            pstByPlanName.setInt(3, obj.getNetInId());
//            pstByPlanName.setInt(4, obj.getUserId());
//            pstByPlanName.setInt(5, obj.getCarId());
//            pstByPlanName.setInt(6, obj.getNetOutId());
//            pstByPlanName.setInt(7, obj.getCouponId());
//            pstByPlanName.setTimestamp(8, TimeUtil.dateToSqlTime(obj.getGetTime()));
//            pstByPlanName.setTimestamp(9, TimeUtil.dateToSqlTime(obj.getRetTime()));
//            pstByPlanName.setLong(10, obj.getRentalDur());
//            pstByPlanName.setBigDecimal(11, obj.getRawPrice());
//            pstByPlanName.setBigDecimal(12, obj.getFinalPrice());
//            pstByPlanName.setInt(13, obj.getStatus());


//            pstByPlanName.setInt(12, obj.getOrderId());
//

            pstByPlanName.execute();
//			pstByPlanName.setString(2, name);
//			ResultSet resultSetByPlanName = pstByPlanName.executeQuery();
//			System.out.println("resultSetByPlanName");
//			if (resultSetByPlanName.next()) {
//				resultSetByPlanName.close();
//				pstByPlanName.close();
//				throw new BusinessException("同名步骤已经存在");
//			}
//			resultSetByPlanName.close();
            pstByPlanName.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DbException(ex);
        }

//        finally {
////            DBUtil.closeConnection(conn);
////            if (conn != null) {
////                try {
////                    conn.close();
////                } catch (SQLException e) {
////                    e.printStackTrace();
////                }
////            }
//        }
    }

    @Override
    public void update(Connection conn, TblOrder obj) throws DbException {


//        Connection conn = null;
        try {
//            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
//            int step_order = 0;// 需要计算
//            String sqlInsert = " UPDATE car_info\n" +
//                    "    SET\n" +
//                    "        cat_id = ?,\n" +
//                    "        order_id = ?,\n" +
//                    "        type_id = ?\n" +
//                    "        net_id = ?,\n" +
//                    "        license = ?,\n" +
//                    "        car_status = ?\n" +
//                    "    WHERE\n" +
//                    "        car_id = ? ";

            String sqlUp = "\n" +
                    "UPDATE tbl_order\n" +
                    "SET prom_id     = ?,\n" +
                    "    net_in_id   = ?,\n" +
                    "    user_id     = ?,\n" +
                    "    car_id      = ?,\n" +
                    "    net_out_id  = ?,\n" +
                    "    coupon_id   = ?,\n" +
                    "    get_time    = ?,\n" +
                    "    ret_time    = ?,\n" +
                    "    rental_dur  = ?,\n" +
                    "    raw_price   = ?,\n" +
                    "    final_price = ?,\n" +
                    "    status      = ?\n" +
                    "WHERE order_id = ?";
            PreparedStatement pstByPlanName = conn.prepareStatement(sqlUp);
//            pstByPlanName.setInt(1, carInfo.getCatId());
//            pstByPlanName.setInt(2, carInfo.getOrderId());
//            pstByPlanName.setInt(3, carInfo.getTypeId());
//            pstByPlanName.setInt(4, carInfo.getNetId());
//            pstByPlanName.setString(5, carInfo.getLicense());
//            pstByPlanName.setInt(6, carInfo.getCarStatus());


//            pstByPlanName.setInt(1, obj.getOrderId());
            pstByPlanName.setInt(1, obj.getPromId());
            pstByPlanName.setInt(2, obj.getNetInId());
            pstByPlanName.setInt(3, obj.getUserId());
            pstByPlanName.setInt(4, obj.getCarId());
            pstByPlanName.setInt(5, obj.getNetOutId());
            pstByPlanName.setInt(6, obj.getCouponId());
            pstByPlanName.setTimestamp(7, TimeUtil.dateToSqlTime(obj.getGetTime()));
            pstByPlanName.setTimestamp(8, TimeUtil.dateToSqlTime(obj.getRetTime()));
            pstByPlanName.setLong(9, obj.getRentalDur());
            pstByPlanName.setBigDecimal(10, obj.getRawPrice());
            pstByPlanName.setBigDecimal(11, obj.getFinalPrice());
            pstByPlanName.setInt(12, obj.getStatus());
            pstByPlanName.setInt(12, obj.getOrderId());
//

            pstByPlanName.execute();
//			pstByPlanName.setString(2, name);
//			ResultSet resultSetByPlanName = pstByPlanName.executeQuery();
//			System.out.println("resultSetByPlanName");
//			if (resultSetByPlanName.next()) {
//				resultSetByPlanName.close();
//				pstByPlanName.close();
//				throw new BusinessException("同名步骤已经存在");
//			}
//			resultSetByPlanName.close();
            pstByPlanName.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DbException(ex);
        }

//        finally {
////            DBUtil.closeConnection(conn);
////            if (conn != null) {
////                try {
////                    conn.close();
////                } catch (SQLException e) {
////                    e.printStackTrace();
////                }
////            }
//        }
    }

    @Override
    public List<TblOrder> loadAll() throws BaseException, SQLException {
        return getBy(new TblOrder());
    }

    @Override
    public List<TblOrder> getBy(TblOrder tblOrder) throws BaseException, SQLException {
//        Connection conn= DBUtil.getConnection();
//        List<TblOrder> by = JdbcUtil.getBy(conn, tblOrder);
//        return by;
      return   JdbcUtil.getBy(tblOrder);
    }
}
