package cn.edu.zucc.personplan.dao;

import cn.edu.zucc.personplan.model.NetInfo;
import cn.edu.zucc.personplan.model.Promotion;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PromotionDao implements ItDao<Promotion>  {

    @Override
    public List<Promotion> getBy(Connection conn, Promotion obj) throws BaseException {

//        Connection conn = null;
//        NetInfo obj=(NetInfo)o;
        try {
//            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
//            int step_order = 0;// 需要计算
            String sqlInsert = "select * from promotion ";

            Integer promId = obj.getPromId();
            boolean first = true;
            if (promId != null) {
                if (first) {
                    sqlInsert += "where prom_id = " + q(promId);
                    first = false;
                } else {
                    sqlInsert += "and prom_id = " + q(promId);
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

            Integer netId = obj.getNetId();
//			boolean first=true;
            if (netId != null) {
                if (first) {
                    sqlInsert += "where net_id = " + q(netId);
                    first = false;
                } else {
                    sqlInsert += "and net_id = " + q(netId);
                }

            }

            BigDecimal discount = obj.getDiscount();
            if (discount != null) {
                if (first) {
                    sqlInsert += "where discount = " + q(discount);
                    first = false;
                } else {
                    sqlInsert += "and discount = " + q(discount);
                }

            }

            Integer promQuantity = obj.getPromQuantity();
            if (promQuantity != null) {
                if (first) {
                    sqlInsert += "where prom_quantity = " + q(promQuantity);
                    first = false;
                } else {
                    sqlInsert += "and prom_quantity = " + q(promQuantity);
                }

            }

            Date startTime = obj.getStartTime();
            if (startTime != null) {
                if (first) {
                    sqlInsert += "where start_time = " + q(startTime);
                    first = false;
                } else {
                    sqlInsert += "and start_time = " + q(startTime);
                }

            }

            Date endTime = obj.getEndTime();
            if (endTime != null) {
                if (first) {
                    sqlInsert += "where end_time = " + q(endTime);
                    first = false;
                } else {
                    sqlInsert += "and end_time = " + q(endTime);
                }

            }

//            String phone = obj.getPromQuantity();
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

            List<Promotion> list = new ArrayList<>();
            while (resultSet.next()) {
                Promotion lstObj = new Promotion();
                lstObj.setPromId(resultSet.getInt(1));
                lstObj.setTypeId(resultSet.getInt(2));
                lstObj.setNetId(resultSet.getInt(3));
                lstObj.setDiscount(resultSet.getBigDecimal(4));
                lstObj.setPromQuantity(resultSet.getInt(5));
                lstObj.setStartTime(resultSet.getTimestamp(6));
                lstObj.setEndTime(resultSet.getTimestamp(7));
//                lstObj.setCity(resultSet.getString(3));
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
    }

    @Override
    public void insert(Connection conn, Promotion obj) throws DbException {

    }

    @Override
    public void update(Connection conn, Promotion obj) throws DbException, BaseException {

    }

    @Override
    public void delete(Connection conn, Promotion obj) throws DbException {

    }

    @Override
    public List<Promotion> loadAll() throws BaseException {
        List<Promotion> by = getBy(new Promotion());
        return by;
    }

    public static String q(Object object) {
        return " '" + object + "' ";
    }


    @Override
    public List<Promotion> getBy(Promotion obj) throws BaseException {
//        return null;

        Connection conn = null;
//        NetInfo obj=(NetInfo)o;
        try {
            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
//            int step_order = 0;// 需要计算
            String sqlInsert = "select * from promotion ";

            Integer promId = obj.getPromId();
            boolean first = true;
            if (promId != null) {
                if (first) {
                    sqlInsert += "where prom_id = " + q(promId);
                    first = false;
                } else {
                    sqlInsert += "and prom_id = " + q(promId);
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

            Integer netId = obj.getNetId();
//			boolean first=true;
            if (netId != null) {
                if (first) {
                    sqlInsert += "where net_id = " + q(netId);
                    first = false;
                } else {
                    sqlInsert += "and net_id = " + q(netId);
                }

            }

            BigDecimal discount = obj.getDiscount();
            if (discount != null) {
                if (first) {
                    sqlInsert += "where discount = " + q(discount);
                    first = false;
                } else {
                    sqlInsert += "and discount = " + q(discount);
                }

            }

            Integer promQuantity = obj.getPromQuantity();
            if (promQuantity != null) {
                if (first) {
                    sqlInsert += "where prom_quantity = " + q(promQuantity);
                    first = false;
                } else {
                    sqlInsert += "and prom_quantity = " + q(promQuantity);
                }

            }

            Date startTime = obj.getStartTime();
            if (startTime != null) {
                if (first) {
                    sqlInsert += "where start_time = " + q(startTime);
                    first = false;
                } else {
                    sqlInsert += "and start_time = " + q(startTime);
                }

            }

            Date endTime = obj.getEndTime();
            if (endTime != null) {
                if (first) {
                    sqlInsert += "where end_time = " + q(endTime);
                    first = false;
                } else {
                    sqlInsert += "and end_time = " + q(endTime);
                }

            }

//            String phone = obj.getPromQuantity();
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

            List<Promotion> list = new ArrayList<>();
            while (resultSet.next()) {
                Promotion lstObj = new Promotion();
                lstObj.setPromId(resultSet.getInt(1));
                lstObj.setTypeId(resultSet.getInt(2));
                lstObj.setNetId(resultSet.getInt(3));
                lstObj.setDiscount(resultSet.getBigDecimal(4));
                lstObj.setPromQuantity(resultSet.getInt(5));
                lstObj.setStartTime(resultSet.getTimestamp(6));
                lstObj.setEndTime(resultSet.getTimestamp(7));
//                lstObj.setCity(resultSet.getString(3));
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
