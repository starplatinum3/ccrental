package cn.edu.zucc.personplan.dao;

import cn.edu.zucc.personplan.model.CarInfo;
import cn.edu.zucc.personplan.model.CarType;
import cn.edu.zucc.personplan.util.*;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarTypeDao  implements ItDao<CarType> {

    public static String q(Object object) {
        return " '" + object + "' ";
    }


//    @Override
    public List<CarType> getBy(Connection conn, CarType obj) throws BaseException {

//        Connection conn = null;
//        Emp obj=(Emp)o;
        try {
//            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
//            int step_order = 0;// 需要计算
            String sqlInsert = "select * from car_type ";

            String brand = obj.getBrand();
            boolean first = true;
            if (brand != null) {
                if (first) {
                    sqlInsert += "where brand = " + q(brand);
                    first = false;
                } else {
                    sqlInsert += "and brand = " + q(brand);
                }

            }


            Integer typeId = obj.getTypeId();
//            boolean first = true;
            if (typeId != null) {
                if (first) {
                    sqlInsert += "where type_id = " + q(typeId);
                    first = false;
                } else {
                    sqlInsert += "and type_id = " + q(typeId);
                }

            }

            Integer catId = obj.getCatId();
            if (catId != null) {
                if (first) {
                    sqlInsert += "where cat_id = " + q(catId);
                    first = false;
                } else {
                    sqlInsert += "and cat_id = " + q(catId);
                }

            }

            String typeName= obj.getTypeName();
//			boolean first=true;
            if (typeName != null) {
                if (first) {
                    sqlInsert += "where type_name = " + q(typeName);
                    first = false;
                } else {
                    sqlInsert += "and type_name = " + q(typeName);
                }

            }

            BigDecimal displacement = obj.getDisplacement();
            if (displacement != null) {
                if (first) {
                    sqlInsert += "where displacement = " + q(displacement);
                    first = false;
                } else {
                    sqlInsert += "and displacement = " + q(displacement);
                }

            }


            Integer gear = obj.getGear();
            if (gear != null) {
                if (first) {
                    sqlInsert += "where gear = " + q(gear);
                    first = false;
                } else {
                    sqlInsert += "and gear = " + q(gear);
                }

            }

            Integer seatNum = obj.getSeatNum();
            if (seatNum != null) {
                if (first) {
                    sqlInsert += "where seat_num = " + q(seatNum);
                    first = false;
                } else {
                    sqlInsert += "and seat_num = " + q(seatNum);
                }

            }


            BigDecimal price = obj.getPrice();
            if (price != null) {
                if (first) {
                    sqlInsert += "where price = " + q(price);
                    first = false;
                } else {
                    sqlInsert += "and price = " + q(price);
                }

            }

            String pic = obj.getPic();
            if (pic != null) {
                if (first) {
                    sqlInsert += "where pic = " + q(pic);
                    first = false;
                } else {
                    sqlInsert += "and pic = " + q(pic);
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

            List<CarType> list = new ArrayList<>();
            while (resultSet.next()) {
//                NetInfo lstObj = new NetInfo();
                CarType lstObj = new CarType();
                lstObj.setBrand(resultSet.getString("brand"));
                lstObj.setTypeId(resultSet.getInt("type_id"));
                lstObj.setCatId(resultSet.getInt("cat_id"));
                lstObj.setTypeName(resultSet.getString("type_name"));
                lstObj.setDisplacement(resultSet.getBigDecimal("displacement"));
                lstObj.setGear(resultSet.getInt("gear"));
                lstObj.setSeatNum(resultSet.getInt("seat_num"));
                lstObj.setPrice(resultSet.getBigDecimal("price"));
                lstObj.setPic(resultSet.getString("pic"));
//                lstObj.setCarStatus(resultSet.getInt(5));

//                resultSet.get
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
        }
//        finally {
//            DBUtil.closeConnection(conn);
//        }
    }

//    @Override
    public void insert(Connection conn, CarType obj) throws DbException {


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

            String sqlUp = "INSERT INTO car_type ( type_id,brand,cat_id,type_name,displacement,gear,seat_num,price,pic )\n" +
                    "VALUES\n" +
                    "(\n" +
                    "    ?,?,?,?,?,?,?,?,?\n" +
                    ");\n;";
            PreparedStatement pstByPlanName = conn.prepareStatement(sqlUp);
//            pstByPlanName.setInt(1, carInfo.getCatId());
//            pstByPlanName.setInt(2, carInfo.getOrderId());
//            pstByPlanName.setInt(3, carInfo.getTypeId());
//            pstByPlanName.setInt(4, carInfo.getNetId());
//            pstByPlanName.setString(5, carInfo.getLicense());
//            pstByPlanName.setInt(6, carInfo.getCarStatus());

//update 的时候 也 id  这样 编号就不用重新写了
            pstByPlanName.setInt(1, obj.getTypeId());
            pstByPlanName.setString(2, obj.getBrand());
            pstByPlanName.setInt(3, obj.getCatId());
            pstByPlanName.setString(4, obj.getTypeName());
            pstByPlanName.setBigDecimal(5, obj.getDisplacement());
            pstByPlanName.setInt(6, obj.getGear());
            pstByPlanName.setInt(7, obj.getSeatNum());
            pstByPlanName.setBigDecimal(8,obj.getPrice());
            pstByPlanName.setString(9, obj.getPic());
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

    }

//    @Override
    public void update(Connection conn, CarType obj) throws DbException, BaseException {


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

            String sqlUp = "UPDATE car_type\n" +
                    "SET type_id= ?,\n" +
                    "    brand = ?,\n" +
                    "    cat_id = ?,\n" +
                    "    type_name = ?,\n" +
                    "    displacement = ?,\n" +
                    "    gear = ?,\n" +
                    "    seat_num = ?,\n" +
                    "    price = ?,\n" +
                    "    pic = ?\n" +
                    "WHERE type_id = ?";
            PreparedStatement pstByPlanName = conn.prepareStatement(sqlUp);
//            pstByPlanName.setInt(1, carInfo.getCatId());
//            pstByPlanName.setInt(2, carInfo.getOrderId());
//            pstByPlanName.setInt(3, carInfo.getTypeId());
//            pstByPlanName.setInt(4, carInfo.getNetId());
//            pstByPlanName.setString(5, carInfo.getLicense());
//            pstByPlanName.setInt(6, carInfo.getCarStatus());


//            pstByPlanName.setInt(1, obj.getOrderId());
//            pstByPlanName.setInt(1, obj.getTypeId());
//            pstByPlanName.setInt(2, obj.getBrand());
//            pstByPlanName.setInt(3, obj.getCatId());
//            pstByPlanName.setInt(4, obj.getTypeName());
//            pstByPlanName.setInt(5, obj.getDisplacement());
//            pstByPlanName.setInt(6, obj.getGear());
//            pstByPlanName.setTimestamp(7, obj.getSeatNum());
//            pstByPlanName.setTimestamp(8, obj.getPrice());
//            pstByPlanName.setLong(9, obj.getRentalDur());
//            pstByPlanName.setBigDecimal(10, obj.getRawPrice());
//            pstByPlanName.setBigDecimal(11, obj.getFinalPrice());
//            pstByPlanName.setInt(12, obj.getStatus());
//            pstByPlanName.setInt(12, obj.getOrderId());

            pstByPlanName.setInt(1, obj.getTypeId());
            pstByPlanName.setString(2, obj.getBrand());
            pstByPlanName.setInt(3, obj.getCatId());
            pstByPlanName.setString(4, obj.getTypeName());
            pstByPlanName.setBigDecimal(5, obj.getDisplacement());
            pstByPlanName.setInt(6, obj.getGear());
            pstByPlanName.setInt(7, obj.getSeatNum());
            pstByPlanName.setBigDecimal(8,obj.getPrice());
            pstByPlanName.setString(9, obj.getPic());
            pstByPlanName.setInt(10, obj.getTypeId());
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

//    @Override
    public void delete(Connection conn, CarType obj) throws DbException {

    }

    @Override
    public List<CarType> loadAll() throws BaseException {
        try{
            List<CarType> by = getBy(new CarType());
            return by;
        }catch (SQLException e){
            e.printStackTrace();
//            throw e;
        }
        return null;

//        return null;
    }

//    @Override
    public List<CarType> getBy(CarType carType) throws BaseException, SQLException {

//        Connection connection = DBUtil.getConnection();
//        List<CarType> by = getBy(connection, carType);
       return JdbcUtil.getBy(carType);
//        return by;

    }
}
