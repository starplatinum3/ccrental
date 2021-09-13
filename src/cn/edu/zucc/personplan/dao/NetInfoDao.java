package cn.edu.zucc.personplan.dao;

import cn.edu.zucc.personplan.itf.IEmpDao;
import cn.edu.zucc.personplan.itf.INetInfoDao;
import cn.edu.zucc.personplan.itf.IStepManager;
import cn.edu.zucc.personplan.model.BeanStep;
import cn.edu.zucc.personplan.model.NetInfo;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NetInfoDao implements ItDao<NetInfo> {

    public static String q(Object object) {
        return " '" + object + "' ";
    }

    @Override
    public List<NetInfo> getBy(NetInfo obj) throws BaseException {

        Connection conn = null;
//        NetInfo obj=(NetInfo)o;
        try {
            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
//            int step_order = 0;// 需要计算
            String sqlInsert = "select * from net_info ";

            Integer netId = obj.getNetId();
            boolean first = true;
            if (netId != null) {
                if (first) {
                    sqlInsert += "where net_id = " + q(netId);
                    first = false;
                } else {
                    sqlInsert += "and net_id = " + q(netId);
                }

            }
            String netName = obj.getNetName();
            if (netName != null) {
                if (first) {
                    sqlInsert += "where net_name = " + q(netName);
                    first = false;
                } else {
                    sqlInsert += "and net_name = " + q(netName);
                }

            }

            String city = obj.getCity();
//			boolean first=true;
            if (city != null) {
                if (first) {
                    sqlInsert += "where city = " + q(city);
                    first = false;
                } else {
                    sqlInsert += "and city = " + q(city);
                }

            }

            String stepName = obj.getAddr();
            if (stepName != null) {
                if (first) {
                    sqlInsert += "where step_name = " + q(stepName);
                    first = false;
                } else {
                    sqlInsert += "and step_name = " + q(stepName);
                }

            }

            String phone = obj.getPhone();
            if (phone != null) {
                if (first) {
                    sqlInsert += "where phone = " + q(phone);
                    first = false;
                } else {
                    sqlInsert += "and phone = " + q(phone);
                }

            }

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

            List<NetInfo> list = new ArrayList<>();
            while (resultSet.next()) {
                NetInfo lstObj = new NetInfo();
//                对于每个field
//                使用他的 set
                JdbcUtil.putValToObj(resultSet,lstObj);
                // TODO: 2021/9/4  可以使用吗


//                原来代码啊 备份  2021年9月4日10:12:22
//                lstObj.setNetId(resultSet.getInt(1));
////                resultSet.getInt("")
//                lstObj.setNetName(resultSet.getString(2));
//                lstObj.setCity(resultSet.getString(3));
//                lstObj.setAddr(resultSet.getString(4));
//                lstObj.setPhone(resultSet.getString(5));
//
//                原来代码啊 备份 以上


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

    @Override
    public List<NetInfo> getBy(Connection conn, NetInfo obj) throws BaseException {
        return null;
    }

    @Override
    public void insert(Connection conn, NetInfo obj) throws DbException {

    }

    @Override
    public void update(Connection conn, NetInfo obj) throws DbException, BaseException {


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

            String sqlUp = "UPDATE net_info\n" +
                    "SET net_id=?,\n" +
                    "    net_name = ?,\n" +
                    "    city = ?,\n" +
                    "    addr = ?,\n" +
                    "    phone = ?\n" +
                    "WHERE net_id = ?\n";
            PreparedStatement pstByPlanName = conn.prepareStatement(sqlUp);
//            pstByPlanName.setInt(1, carInfo.getCatId());
//            pstByPlanName.setInt(2, carInfo.getOrderId());
//            pstByPlanName.setInt(3, carInfo.getTypeId());
//            pstByPlanName.setInt(4, carInfo.getNetId());
//            pstByPlanName.setString(5, carInfo.getLicense());
//            pstByPlanName.setInt(6, carInfo.getCarStatus());

            JdbcUtil.set(pstByPlanName,obj);

//            pstByPlanName.setInt(1, carInfo.getNetId());
//            pstByPlanName.setInt(2, carInfo.getTypeId());
//            pstByPlanName.setString(3, carInfo.getLicense());
//            pstByPlanName.setInt(4, carInfo.getCarStatus());
//            pstByPlanName.setString(5, carInfo.getLicense());
            pstByPlanName.setInt(6, obj.getNetId());
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


    public void updateBatch(Connection conn, List<NetInfo> obj) throws DbException, BaseException {

    }


    @Override
    public void delete(Connection conn, NetInfo obj) throws DbException {

    }

    @Override
    public List loadAll() throws BaseException {
        return getBy(new NetInfo());
    }



//    @Override
//    public List getBy(Object object) {
//        return null;
//    }

//    @Override
//    public List<NetInfo> getBy(NetInfo obj) throws BaseException {
//        return null;
//    }
}
