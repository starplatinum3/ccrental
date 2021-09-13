package cn.edu.zucc.personplan.dao;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.BeanStep;
import cn.edu.zucc.personplan.model.CarInfo;
import cn.edu.zucc.personplan.model.Emp;
import cn.edu.zucc.personplan.util.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarInfoDao implements ItDao<CarInfo> {


    //    一个很大的缺点 是 ，参数顺序必须一样，而且通用性很低，扩展性很低
    public static void main(String[] args) throws SQLException, BaseException {
        Connection connection = DBUtil.getConnection();
//        CarInfo carInfo = new CarInfo(1, 2, 2, "4", 5);
        CarInfo carInfo = new CarInfo();
        carInfo.setNetId(2);
//        CcCarUtil.carInfoDao.delete(connection, carInfo);
        List<CarInfo> by = CcCarUtil.carInfoDao.getBy(connection, carInfo);
        System.out.println("by");
        System.out.println(by);
    }

    void up() throws SQLException, BaseException {
        Connection connection = DBUtil.getConnection();
        CarInfo carInfo = new CarInfo(1, 2, 2, "4", 5);
        CcCarUtil.carInfoDao.update(connection, carInfo);
//        因为是接口  不写的话 调用不了
    }


//    @Override
    public List<CarInfo> getBy(Connection conn, CarInfo obj) throws BaseException {

        JdbcUtil<CarInfo> jdbcUtil = new JdbcUtil<>();
        String sql = "select * from car_info ";
        List<CarInfo> by = jdbcUtil.getBy(conn, sql, obj);
        return by;
//        System.out.println();


//        备份 2021年9月4日18:44:48
//
////        Connection conn = null;
////        Emp obj=(Emp)o;
//        try {
////            conn = DBUtil.getConnection();
////			String user_id = BeanUser.currentLoginUser.getUser_id();
////            int step_order = 0;// 需要计算
//            String sqlInsert = "select * from car_info ";
//
//            Integer carId = obj.getCarId();
//            boolean first = true;
//            if (carId != null) {
//                if (first) {
//                    sqlInsert += "where car_id = " + q(carId);
//                    first = false;
//                } else {
//                    sqlInsert += "and car_id = " + q(carId);
//                }
//
//            }
//
//
//            Integer netId = obj.getNetId();
////            boolean first = true;
//            if (netId != null) {
//                if (first) {
//                    sqlInsert += "where net_id = " + q(netId);
//                    first = false;
//                } else {
//                    sqlInsert += "and net_id = " + q(netId);
//                }
//
//            }
//
//            Integer typeId = obj.getTypeId();
//            if (typeId != null) {
//                if (first) {
//                    sqlInsert += "where type_id = " + q(typeId);
//                    first = false;
//                } else {
//                    sqlInsert += "and type_id = " + q(typeId);
//                }
//
//            }
//
//            String license = obj.getLicense();
////			boolean first=true;
//            if (license != null) {
//                if (first) {
//                    sqlInsert += "where license = " + q(license);
//                    first = false;
//                } else {
//                    sqlInsert += "and license = " + q(license);
//                }
//
//            }
//
//            Integer carStatus = obj.getCarStatus();
//            if (carStatus != null) {
//                if (first) {
//                    sqlInsert += "where car_status = " + q(carStatus);
//                    first = false;
//                } else {
//                    sqlInsert += "and car_status = " + q(carStatus);
//                }
//
//            }
////
////            String phone = obj.getPhone();
////            if (phone != null) {
////                if (first) {
////                    sqlInsert += "where phone = " + q(phone);
////                    first = false;
////                } else {
////                    sqlInsert += "and phone = " + q(phone);
////                }
////
////            }
//
//            System.out.println("sqlInsert");
//            System.out.println(sqlInsert);
//
//            PreparedStatement pstByPlanName = conn.prepareStatement(sqlInsert);
////			pstByPlanName.setInt(1, stepId);
//
//            ResultSet resultSet = pstByPlanName.executeQuery();
////			pstByPlanName.setString(2, name);
////			ResultSet resultSetByPlanName = pstByPlanName.executeQuery();
////			System.out.println("resultSetByPlanName");
////			if (resultSetByPlanName.next()) {
////				resultSetByPlanName.close();
////				pstByPlanName.close();
////				throw new BusinessException("同名步骤已经存在");
////			}
////			resultSetByPlanName.close();
////			pstByPlanName.close();
//
//            List<CarInfo> list = new ArrayList<>();
//            while (resultSet.next()) {
////                NetInfo lstObj = new NetInfo();
//                CarInfo lstObj = new CarInfo();
//                lstObj.setCarId(resultSet.getInt(1));
//                lstObj.setNetId(resultSet.getInt(2));
//                lstObj.setTypeId(resultSet.getInt(3));
//                lstObj.setLicense(resultSet.getString(4));
//                lstObj.setCarStatus(resultSet.getInt(5));
////                lstObj.setLicense(resultSet.getString(4));
////                lstObj.setLicense(resultSet.getString(4));
////                lstObj.setAddr(resultSet.getString(4));
////                lstObj.setPhone(resultSet.getString(5));
////                lstObj.setPlanEndTime(resultSet.getTimestamp(6));
////                lstObj.setRealBeginTime(resultSet.getTimestamp(7));
////                lstObj.setRealEndTime(resultSet.getTimestamp(8));
//                list.add(lstObj);
////				resultSet.get
////				throw new BusinessException("没找到");
////				return stepDa;
//            }
//            return list;
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new DbException(ex);
//        }

//        以上备份 2021年9月4日18:45:03
    }

//    @Override
    public void insert(Connection conn, CarInfo obj) throws DbException {


        try {
            String sqlUp = "INSERT INTO car_info ( car_id,net_id,type_id,license,car_status )\n" +
                    "VALUES\n" +
                    "(\n" +
                    "    ?,?,?,?,?\n" +
                    ");";

//            JdbcUtil.insert(conn, sqlUp, obj);

            PreparedStatement pstByPlanName = conn.prepareStatement(sqlUp);

            JdbcUtil.set(pstByPlanName, obj);

            pstByPlanName.execute();

            pstByPlanName.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DbException(ex);
        }
    }
//    备份 以下  2021年9月4日17:08:28

//    public void update(Connection conn, CarInfo carInfo) throws BaseException {
//
//        String sqlUp = "UPDATE car_info\n" +
//                "SET car_id     = ?,\n" +
//                "    net_id     = ?,\n" +
//                "    type_id    = ?,\n" +
//                "    license    = ?,\n" +
//                "    car_status = ?\n" +
//                "WHERE car_id = ?";
//
//
//        JdbcUtil.update(conn,sqlUp,carInfo,carInfo.getCarId());
//
//
//
////        Connection conn = null;
//        try {
////            conn = DBUtil.getConnection();
////			String user_id = BeanUser.currentLoginUser.getUser_id();
////            int step_order = 0;// 需要计算
////            String sqlInsert = " UPDATE car_info\n" +
////                    "    SET\n" +
////                    "        cat_id = ?,\n" +
////                    "        order_id = ?,\n" +
////                    "        type_id = ?\n" +
////                    "        net_id = ?,\n" +
////                    "        license = ?,\n" +
////                    "        car_status = ?\n" +
////                    "    WHERE\n" +
////                    "        car_id = ? ";
//
////            String sqlUp = "UPDATE car_info\n" +
////                    "SET\n" +
////                    "    net_id = ?,\n" +
////                    "    type_id = ?,\n" +
////                    "    license = ?,\n" +
////                    "    car_status = ?\n" +
////                    "WHERE car_id=?";
//
////            String sqlUp = "UPDATE car_info\n" +
////                    "SET car_id     = ?,\n" +
////                    "    net_id     = ?,\n" +
////                    "    type_id    = ?,\n" +
////                    "    license    = ?,\n" +
////                    "    car_status = ?\n" +
////                    "WHERE car_id = ?";
////
////
////            JdbcUtil.update(conn,sqlUp,carInfo,carInfo.getCarId());
//
//
////            备份
//
////            PreparedStatement pstByPlanName = conn.prepareStatement(sqlUp);
//////            pstByPlanName.setInt(1, carInfo.getCatId());
//////            pstByPlanName.setInt(2, carInfo.getOrderId());
//////            pstByPlanName.setInt(3, carInfo.getTypeId());
//////            pstByPlanName.setInt(4, carInfo.getNetId());
//////            pstByPlanName.setString(5, carInfo.getLicense());
//////            pstByPlanName.setInt(6, carInfo.getCarStatus());
////
////
////            pstByPlanName.setInt(1, carInfo.getNetId());
////            pstByPlanName.setInt(2, carInfo.getTypeId());
////            pstByPlanName.setString(3, carInfo.getLicense());
////            pstByPlanName.setInt(4, carInfo.getCarStatus());
//////            pstByPlanName.setString(5, carInfo.getLicense());
//////            pstByPlanName.setInt(6, carInfo.getCarStatus());
//////
////
////            pstByPlanName.execute();
//////			pstByPlanName.setString(2, name);
//////			ResultSet resultSetByPlanName = pstByPlanName.executeQuery();
//////			System.out.println("resultSetByPlanName");
//////			if (resultSetByPlanName.next()) {
//////				resultSetByPlanName.close();
//////				pstByPlanName.close();
//////				throw new BusinessException("同名步骤已经存在");
//////			}
//////			resultSetByPlanName.close();
////            pstByPlanName.close();
//
////            以上 备份
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new DbException(ex);
//        }
//
////        finally {
//////            DBUtil.closeConnection(conn);
//////            if (conn != null) {
//////                try {
//////                    conn.close();
//////                } catch (SQLException e) {
//////                    e.printStackTrace();
//////                }
//////            }
////        }
//
//    }

//    备份 以上

    public void update(Connection conn, CarInfo carInfo) throws BaseException {

        String sqlUp = "UPDATE car_info\n" +
                "SET car_id     = ?,\n" +
                "    net_id     = ?,\n" +
                "    type_id    = ?,\n" +
                "    license    = ?,\n" +
                "    car_status = ?\n" +
                "WHERE car_id = ?";


        JdbcUtil.update(conn, sqlUp, carInfo, carInfo.getCarId());


    }

//    @Override
    public void delete(Connection conn, CarInfo obj) throws DbException {
        String sqlDel = "DELETE\n" +
                "FROM car_info\n" +
                "WHERE car_info.car_id = ?\n" +
                "  and net_id = ?\n" +
                "  and type_id = ?\n" +
                "  and license = ?\n" +
                "  and car_status = ?";
        JdbcUtil.delete(conn, sqlDel, obj);
//
////        Connection conn = null;
//        try {
////            conn = DBUtil.getConnection();
////			String user_id = BeanUser.currentLoginUser.getUser_id();
////            int step_order = 0;// 需要计算
////            String sqlInsert = " UPDATE car_info\n" +
////                    "    SET\n" +
////                    "        cat_id = ?,\n" +
////                    "        order_id = ?,\n" +
////                    "        type_id = ?\n" +
////                    "        net_id = ?,\n" +
////                    "        license = ?,\n" +
////                    "        car_status = ?\n" +
////                    "    WHERE\n" +
////                    "        car_id = ? ";
//
//            String sqlDel = "DELETE\n" +
//                    "FROM car_info\n" +
//                    "WHERE car_info.car_id = ?\n" +
//                    "  and net_id = ?\n" +
//                    "  and type_id = ?\n" +
//                    "  and license = ?\n" +
//                    "  and car_status = ?";
//            PreparedStatement pstByPlanName = conn.prepareStatement(sqlDel);
////            pstByPlanName.setInt(1, carInfo.getCatId());
////            pstByPlanName.setInt(2, carInfo.getOrderId());
////            pstByPlanName.setInt(3, carInfo.getTypeId());
////            pstByPlanName.setInt(4, carInfo.getNetId());
////            pstByPlanName.setString(5, carInfo.getLicense());
////            pstByPlanName.setInt(6, carInfo.getCarStatus());
//
////            填 问号
//            JdbcUtil.set(pstByPlanName, obj);
////update 的时候 也 id  这样 编号就不用重新写了
////            pstByPlanName.setInt(1, obj.getOrderId());
////            pstByPlanName.setInt(2, obj.getPromId());
////            pstByPlanName.setInt(3, obj.getNetInId());
////            pstByPlanName.setInt(4, obj.getUserId());
////            pstByPlanName.setInt(5, obj.getCarId());
////            pstByPlanName.setInt(6, obj.getNetOutId());
////            pstByPlanName.setInt(7, obj.getCouponId());
////            pstByPlanName.setTimestamp(8, TimeUtil.dateToSqlTime(obj.getGetTime()));
////            pstByPlanName.setTimestamp(9, TimeUtil.dateToSqlTime(obj.getRetTime()));
////            pstByPlanName.setInt(10, obj.getRentalDur());
////            pstByPlanName.setBigDecimal(11, obj.getRawPrice());
////            pstByPlanName.setBigDecimal(12, obj.getFinalPrice());
////            pstByPlanName.setInt(13, obj.getStatus());
////            pstByPlanName.setInt(12, obj.getOrderId());
////
//
//            pstByPlanName.execute();
////			pstByPlanName.setString(2, name);
////			ResultSet resultSetByPlanName = pstByPlanName.executeQuery();
////			System.out.println("resultSetByPlanName");
////			if (resultSetByPlanName.next()) {
////				resultSetByPlanName.close();
////				pstByPlanName.close();
////				throw new BusinessException("同名步骤已经存在");
////			}
////			resultSetByPlanName.close();
//            pstByPlanName.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new DbException(ex);
//        }

    }

    //    字段的删减貌似没有问题 因为 接口是一整个 实体
    @Override
    public List<CarInfo> loadAll() throws BaseException {
        List<CarInfo> by = getBy(new CarInfo());
        return by;
    }

    public static String q(Object object) {
        return " '" + object + "' ";
    }


//    @Override
    public List<CarInfo> getBy(CarInfo obj) throws BaseException {

        Connection conn = null;
//        Emp obj=(Emp)o;
        try {
            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
//            int step_order = 0;// 需要计算
            String sqlInsert = "select * from car_info ";

            Integer carId = obj.getCarId();
            boolean first = true;
            if (carId != null) {
                if (first) {
                    sqlInsert += "where car_id = " + q(carId);
                    first = false;
                } else {
                    sqlInsert += "and car_id = " + q(carId);
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

            Integer typeId = obj.getTypeId();
            if (typeId != null) {
                if (first) {
                    sqlInsert += "where type_id = " + q(typeId);
                    first = false;
                } else {
                    sqlInsert += "and type_id = " + q(typeId);
                }

            }

            String license = obj.getLicense();
//			boolean first=true;
            if (license != null) {
                if (first) {
                    sqlInsert += "where license = " + q(license);
                    first = false;
                } else {
                    sqlInsert += "and license = " + q(license);
                }

            }

            Integer carStatus = obj.getCarStatus();
            if (carStatus != null) {
                if (first) {
                    sqlInsert += "where car_status = " + q(carStatus);
                    first = false;
                } else {
                    sqlInsert += "and car_status = " + q(carStatus);
                }

            }
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

            List<CarInfo> list = new ArrayList<>();
            while (resultSet.next()) {
//                NetInfo lstObj = new NetInfo();
                CarInfo lstObj = new CarInfo();
                lstObj.setCarId(resultSet.getInt(1));
                lstObj.setNetId(resultSet.getInt(2));
                lstObj.setTypeId(resultSet.getInt(3));
                lstObj.setLicense(resultSet.getString(4));
                lstObj.setCarStatus(resultSet.getInt(5));
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
