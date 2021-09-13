package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Order;

import com.sun.glass.ui.PlatformFactory;
import com.sun.org.apache.bcel.internal.generic.SWAP;

import cn.edu.zucc.personplan.itf.IStepManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.BeanStep;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.TimeUtil;
import jdk.nashorn.internal.ir.ReturnNode;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import sun.print.resources.serviceui;

public class ExampleStepManager implements IStepManager {

    @Override
    public void add(BeanPlan plan, String name, String planstartdate, String planfinishdate) throws BaseException {
//		System.out.println("判断计划名字");
        if (name == null || "".equals(name))
            throw new BusinessException("步骤名称必须提供");

        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
            int step_order = 0;// 需要计算
            String sqlByPlanName = "select * from tbl_step where plan_id=? and step_name=?";
            PreparedStatement pstByPlanName = conn.prepareStatement(sqlByPlanName);
            pstByPlanName.setInt(1, plan.getPlan_id());
            pstByPlanName.setString(2, name);
            ResultSet resultSetByPlanName = pstByPlanName.executeQuery();
            System.out.println("resultSetByPlanName");
            if (resultSetByPlanName.next()) {
                resultSetByPlanName.close();
                pstByPlanName.close();
                throw new BusinessException("同名步骤已经存在");
            }
            resultSetByPlanName.close();
            pstByPlanName.close();

//    	  System.out.println("");
//	      String sql="select max(plan_ord) from tbl_plan where user_id = ?";
            String sql = "select max(step_order) from tbl_step where plan_id = ?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, plan.getPlan_id());
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                step_order = rs.getInt(1) + 1;
            } else {
                step_order = 1;
            }
            System.out.println("step_order");
            System.out.println(step_order);
            rs.close();
            pst.close();
            String insertSql = "Insert into tbl_step(plan_id,step_order,step_name,"
                    + "plan_begin_time,plan_end_time,real_begin_time,real_end_time) values(?,?,?,?,?,null,null)";
//	       sql="Insert into tbl_plan(plan_id,user_id,plan_order,plan_name,create_time,step_count,start_step_count,finished_step_count) values(?,?,?,?,0,0,0)";
            PreparedStatement pstIns = conn.prepareStatement(insertSql);
            pstIns.setInt(1, plan.getPlan_id());
            pstIns.setInt(2, step_order);
            pstIns.setString(3, name);
            String format = "yyyy-MM-dd";
//		Date date=TimeUtil.stringtoDate(planstartdate, format);
//		date.getTime();
//	Timestamp timestamp=	new Timestamp(11111L);
            pstIns.setTimestamp(4, TimeUtil.strToSqlTime(planstartdate, format));
            pstIns.setTimestamp(5, TimeUtil.strToSqlTime(planfinishdate, format));
            pstIns.execute();
            pstIns.close();


//			String sql = "update max(step_order) from tbl_step where plan_id = ?";
//			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setInt(1, plan.getPlan_id());

//            add 只要管 step cnt  +1  就行吗
//            开始一个 开始+1  直接加一 不行的，是之前是null 才加一 才对啊
//            不过这个好像不是
//            String sqlNewStep = "update tbl_plan set step_count=step_count+1 where plan_id=?";
//            PreparedStatement pstNewStep = conn.prepareStatement(sqlNewStep);
//            pstNewStep.setInt(1, plan.getPlan_id());
//            pstNewStep.execute();
//            pstNewStep.close();
//

//            planChange(conn, plan.getPlan_id());

            Integer planId = plan.getPlan_id();

            updateStep(planId);



//  return  new BeanStep();
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
//		return  null;

    }

    public void insert(BeanStep step) throws BaseException {

        Connection conn = null;
        try {
//			String timeStr="2021-01-01";
//			Date date = new Date(timeStr);
//			java.sql.Date sqlDate=	new java.sql.Date(date.getTime());
//
//			String timeStr2="2021-01-01";
//			java.sql.Date sqlDate2=	java.sql.Date.valueOf(timeStr);


//			Timestamp
            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
            int step_order = 0;// 需要计算
            String sqlInsert = "insert into tbl_step"
                    + " (step_id,plan_id,step_order,step_name,plan_begin_time,plan_end_time,real_begin_time,real_end_time)"
                    + " values(?,?,?,?,?,?,?,?)";
            PreparedStatement pstByPlanName = conn.prepareStatement(sqlInsert);
            pstByPlanName.setInt(1, step.getStepId());
            pstByPlanName.setInt(2, step.getPlanId());
            pstByPlanName.setInt(3, step.getStepOrder());
            pstByPlanName.setString(4, step.getStepName());
            pstByPlanName.setTimestamp(5, TimeUtil.dateToSqlTime(step.getPlanBeginTime()));
            pstByPlanName.setTimestamp(6, TimeUtil.dateToSqlTime(step.getPlanEndTime()));
            pstByPlanName.setTimestamp(7, TimeUtil.dateToSqlTime(step.getRealBeginTime()));
            pstByPlanName.setTimestamp(8, TimeUtil.dateToSqlTime(step.getRealEndTime()));
//			pstByPlanName.setDate(1,new java.sql.Date(new Date().getTime()));
//			pstByPlanName.setDate(1,sqlDate);
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

    public void update(BeanStep step) throws BaseException {

        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
            int step_order = 0;// 需要计算
            String sqlInsert = "update tbl_step set " + " step_id=?," + " plan_id=?," + " step_order=?,"
                    + " step_name=?," + " plan_begin_time=?," + " plan_end_time=?," + " real_begin_time=?,"
                    + " real_end_time=?" + " where step_id=? ";
            PreparedStatement pstByPlanName = conn.prepareStatement(sqlInsert);
            pstByPlanName.setInt(1, step.getStepId());
            pstByPlanName.setInt(2, step.getPlanId());
            pstByPlanName.setInt(3, step.getStepOrder());
            pstByPlanName.setString(4, step.getStepName());
            pstByPlanName.setTimestamp(5, TimeUtil.dateToSqlTime(step.getPlanBeginTime()));
            pstByPlanName.setTimestamp(6, TimeUtil.dateToSqlTime(step.getPlanEndTime()));
            pstByPlanName.setTimestamp(7, TimeUtil.dateToSqlTime(step.getRealBeginTime()));
            pstByPlanName.setTimestamp(8, TimeUtil.dateToSqlTime(step.getRealEndTime()));
            pstByPlanName.setInt(9, step.getStepId());
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

    public BeanStep getById(int stepId) throws BaseException {

        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
            int step_order = 0;// 需要计算
            String sqlInsert = "select * from tbl_step where step_id=?";
            PreparedStatement pstByPlanName = conn.prepareStatement(sqlInsert);
            pstByPlanName.setInt(1, stepId);

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
            pstByPlanName.close();
            if (resultSet.next()) {
                BeanStep step = new BeanStep();
                step.setStepId(resultSet.getInt(1));
                step.setPlanId(resultSet.getInt(2));
                step.setStepOrder(resultSet.getInt(3));
                step.setStepName(resultSet.getString(4));
                step.setPlanBeginTime(resultSet.getTimestamp(5));
                step.setPlanEndTime(resultSet.getTimestamp(6));
                step.setRealBeginTime(resultSet.getTimestamp(7));
                step.setRealEndTime(resultSet.getTimestamp(8));
//				resultSet.getDate(1);
//				resultSet.get
//				throw new BusinessException("没找到");
                return step;
            }
            return null;

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

    public static String l(Object object) {
        return " '%" + object + "%' ";
    }

    public List<BeanStep> getLike(BeanStep step) throws BaseException {

        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
            int step_order = 0;// 需要计算
            String sqlInsert = "select * from tbl_step ";

            Integer stepId = step.getStepId();
            boolean first = true;
            if (stepId != null) {
                if (first) {
                    sqlInsert += "where step_id like " + l(stepId);
                    first = false;
                } else {
                    sqlInsert += "and step_id like " + l(stepId);
                }

            }
            Integer planId = step.getPlanId();
            if (planId != null) {
                if (first) {
                    sqlInsert += "where plan_id like " + l(planId);
                    first = false;
                } else {
                    sqlInsert += "and plan_id like " + l(planId);
                }

            }

            Integer stepOrder = step.getStepOrder();
//			boolean first=true;
            if (stepOrder != null) {
                if (first) {
                    sqlInsert += "where step_order like " + l(stepOrder);
                    first = false;
                } else {
                    sqlInsert += "and step_order like " + l(stepOrder);
                }

            }
            String stepName = step.getStepName();
            if (stepName != null) {
                if (first) {
                    sqlInsert += "where step_name like " + l(stepName);
                    first = false;
                } else {
                    sqlInsert += "and step_name like " + l(stepName);
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
            pstByPlanName.close();

            List<BeanStep> list = new ArrayList<>();
            while (resultSet.next()) {
                BeanStep stepDa = new BeanStep();
                stepDa.setStepId(resultSet.getInt(1));
                stepDa.setPlanId(resultSet.getInt(2));
                stepDa.setStepOrder(resultSet.getInt(3));
                stepDa.setStepName(resultSet.getString(4));
                stepDa.setPlanBeginTime(resultSet.getTimestamp(5));
                stepDa.setPlanEndTime(resultSet.getTimestamp(6));
                stepDa.setRealBeginTime(resultSet.getTimestamp(7));
                stepDa.setRealEndTime(resultSet.getTimestamp(8));
                list.add(stepDa);
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

    public static String q(Object object) {
        return " '" + object + "' ";
    }

    public List<BeanStep> getBy(BeanStep step) throws BaseException {

        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
//			String user_id = BeanUser.currentLoginUser.getUser_id();
            int step_order = 0;// 需要计算
            String sqlInsert = "select * from tbl_step ";

            Integer stepId = step.getStepId();
            boolean first = true;
            if (stepId != null) {
                if (first) {
                    sqlInsert += "where step_id = " + q(stepId);
                    first = false;
                } else {
                    sqlInsert += "and step_id = " + q(stepId);
                }

            }
            Integer planId = step.getPlanId();
            if (planId != null) {
                if (first) {
                    sqlInsert += "where plan_id = " + q(planId);
                    first = false;
                } else {
                    sqlInsert += "and plan_id = " + q(planId);
                }

            }

            Integer stepOrder = step.getStepOrder();
//			boolean first=true;
            if (stepOrder != null) {
                if (first) {
                    sqlInsert += "where step_order = " + q(stepOrder);
                    first = false;
                } else {
                    sqlInsert += "and step_order = " + q(stepOrder);
                }

            }
            String stepName = step.getStepName();
            if (stepName != null) {
                if (first) {
                    sqlInsert += "where step_name = " + q(stepName);
                    first = false;
                } else {
                    sqlInsert += "and step_name = " + q(stepName);
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

            List<BeanStep> list = new ArrayList<>();
            while (resultSet.next()) {
                BeanStep stepDa = new BeanStep();
                stepDa.setStepId(resultSet.getInt(1));
                stepDa.setPlanId(resultSet.getInt(2));
                stepDa.setStepOrder(resultSet.getInt(3));
                stepDa.setStepName(resultSet.getString(4));
                stepDa.setPlanBeginTime(resultSet.getTimestamp(5));
                stepDa.setPlanEndTime(resultSet.getTimestamp(6));
                stepDa.setRealBeginTime(resultSet.getTimestamp(7));
                stepDa.setRealEndTime(resultSet.getTimestamp(8));
                list.add(stepDa);
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
    public List<BeanStep> loadSteps(BeanPlan plan) throws BaseException {

        boolean fake = false;
        if (fake) {
            List<BeanStep> result = new ArrayList<BeanStep>();
            BeanStep p = new BeanStep();
            result.add(p);
            return result;
        }

//		

//		if (!newPwd.equals(newPwd))
//			throw new BusinessException("两次输入的密码密码不一样");
        List<BeanStep> result = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from tbl_step where \n" + " plan_id=?\n" + "order by step_order";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, plan.getPlan_id());
//			pst.setString(1, BeanUser.currentLoginUser.getUser_id());
//			pst.setString(2, pwd);
            ResultSet rs = pst.executeQuery();
            String passTrue = null;
            Date createTime = null;

            while (rs.next()) {
                BeanStep step = new BeanStep();
                step.setStepId(rs.getInt(1));
                step.setPlanId(rs.getInt(2));
                step.setStepOrder(rs.getInt(3));
                step.setStepName(rs.getString(4));
                step.setPlanBeginTime(rs.getTimestamp(5));
                step.setPlanEndTime(rs.getTimestamp(6));
                step.setRealBeginTime(rs.getTimestamp(7));
                step.setRealEndTime(rs.getTimestamp(8));

                result.add(step);
//				rs.close();
//				pst.close();
//				System.out.println("查无此人");
//				return;
            }
            System.out.println("result");
            System.out.println(result);
            return result;

        } catch (SQLException ex) {
            // TODO: handle exception
            throw new DbException(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
            }
        }
    }

    //	@Override
    public void modifySql(Connection conn, PreparedStatement preparedStatement) throws BaseException, SQLException {
        // TODO Auto-generated method stub
//		"delete from tbl_step where tbl_step_id=#{tblStepId}"

//			Connection conn = null;

//		Connection conn = DBUtil.getConnection();
//				String user_id = BeanUser.currentLoginUser.getUser_id();
//				int step_order = 0;// 需要计算
//				String sqlInsert = "update tbl_step set " + " step_id=?," + " plan_id=?," + " step_order=?,"
//						+ " step_name=?," + " plan_begin_time=?," + " plan_end_time=?," + " real_begin_time=?,"
//						+ " real_end_time=?" + " where step_id=? ";
        try {
            preparedStatement.execute();
            preparedStatement.close();
//				PreparedStatement modifySqlPst = conn.prepareStatement(sql);
//				modifySqlPst.setInt(1, step.getStepId());
//				modifySqlPst.setInt(2, step.getPlanId());
//				modifySqlPst.setInt(3, step.getStepOrder());
//				modifySqlPst.setString(4, step.getStepName());
//				modifySqlPst.setTimestamp(5, TimeUtil.dateToSqlTime(step.getPlanBeginTime()));
//				modifySqlPst.setTimestamp(6, TimeUtil.dateToSqlTime(step.getPlanEndTime()));
//				modifySqlPst.setTimestamp(7, TimeUtil.dateToSqlTime(step.getRealBeginTime()));
//				modifySqlPst.setTimestamp(8, TimeUtil.dateToSqlTime(step.getRealEndTime()));
//				modifySqlPst.setInt(9, step.getStepId());
//				modifySqlPst.execute();
//				pstByPlanName.setString(2, name);
//				ResultSet resultSetByPlanName = pstByPlanName.executeQuery();
//				System.out.println("resultSetByPlanName");
//				if (resultSetByPlanName.next()) {
//					resultSetByPlanName.close();
//					pstByPlanName.close();
//					throw new BusinessException("同名步骤已经存在");
//				}
//				resultSetByPlanName.close();
//				modifySqlPst.close();

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

    public ResultSet resultSql(Connection conn, PreparedStatement preparedStatement)
            throws BaseException, SQLException {
        // TODO Auto-generated method stub
//		"delete from tbl_step where tbl_step_id=#{tblStepId}"

//			Connection conn = null;

//		Connection conn = DBUtil.getConnection();
//				String user_id = BeanUser.currentLoginUser.getUser_id();
//				int step_order = 0;// 需要计算
//				String sqlInsert = "update tbl_step set " + " step_id=?," + " plan_id=?," + " step_order=?,"
//						+ " step_name=?," + " plan_begin_time=?," + " plan_end_time=?," + " real_begin_time=?,"
//						+ " real_end_time=?" + " where step_id=? ";
        try {
            ResultSet resultSet = preparedStatement.executeQuery();
            preparedStatement.close();
//				PreparedStatement modifySqlPst = conn.prepareStatement(sql);
//				modifySqlPst.setInt(1, step.getStepId());
//				modifySqlPst.setInt(2, step.getPlanId());
//				modifySqlPst.setInt(3, step.getStepOrder());
//				modifySqlPst.setString(4, step.getStepName());
//				modifySqlPst.setTimestamp(5, TimeUtil.dateToSqlTime(step.getPlanBeginTime()));
//				modifySqlPst.setTimestamp(6, TimeUtil.dateToSqlTime(step.getPlanEndTime()));
//				modifySqlPst.setTimestamp(7, TimeUtil.dateToSqlTime(step.getRealBeginTime()));
//				modifySqlPst.setTimestamp(8, TimeUtil.dateToSqlTime(step.getRealEndTime()));
//				modifySqlPst.setInt(9, step.getStepId());
//				modifySqlPst.execute();
//				pstByPlanName.setString(2, name);
//				ResultSet resultSetByPlanName = pstByPlanName.executeQuery();
//				System.out.println("resultSetByPlanName");
//				if (resultSetByPlanName.next()) {
//					resultSetByPlanName.close();
//					pstByPlanName.close();
//					throw new BusinessException("同名步骤已经存在");
//				}
//				resultSetByPlanName.close();
//				modifySqlPst.close();
            return resultSet;

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


    public  void updateStep(int planid)throws BaseException {
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
            String sqlString = sqlString = "select count(*) from tbl_step where plan_id = ?";

            PreparedStatement pStatement = connection.prepareStatement(sqlString);
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1, planid);
            ResultSet rs = pStatement.executeQuery();
            int cntOfstep;
            int cntOfstartStep;
            int cntOfendStep;
            if(rs.next()) {
                cntOfstep = rs.getInt(1);
            } else {
                cntOfstep = 0;
            }
            rs.close();
            pStatement.close();
            sqlString = "update tbl_plan set step_count = ? where plan_id = " + planid;
            pStatement =connection.prepareStatement(sqlString);
            System.out.println("step"+cntOfstep);
            pStatement.setInt(1, cntOfstep);
            pStatement.execute();
            pStatement.close();

            sqlString = "select count(*) from tbl_step where plan_id = ? and real_begin_time is not null";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1, planid);
            rs = pStatement.executeQuery();
            if(rs.next()) {
                cntOfstartStep = rs.getInt(1);
            } else {
                cntOfstartStep = 0;
            }
            rs.close();
            pStatement.close();

            sqlString = "update tbl_plan set start_step_count = ? where plan_id = " + planid;
            pStatement =connection.prepareStatement(sqlString);
            System.out.println("start"+cntOfstartStep);
            pStatement.setInt(1, cntOfstartStep);
            pStatement.execute();
            pStatement.close();

            sqlString = "select count(*) from tbl_step where plan_id = ? and real_end_time is not null";
            pStatement = connection.prepareStatement(sqlString);
            pStatement.setInt(1, planid);
            rs = pStatement.executeQuery();
            if(rs.next()) {
                cntOfendStep = rs.getInt(1);
            } else {
                cntOfendStep = 0;
            }
            rs.close();
            pStatement.close();

            sqlString = "update tbl_plan set finished_step_count = ? where plan_id = " + planid;
            pStatement =connection.prepareStatement(sqlString);
            System.out.println("end"+cntOfendStep);
            pStatement.setInt(1, cntOfendStep);
            pStatement.execute();
            pStatement.close();

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            throw new DbException(e);
        }finally {
            if(connection!=null) {
                try {
                    connection.close();
                } catch (Exception e2) {
                    // TODO: handle exception
                    e2.printStackTrace();
                }
            }
        }
    }


//	private Connection getConn() throws SQLException {
//		// TODO Auto-generated method stub
//
//		Connection conn = DBUtil.getConnection();
//		return conn;
//	}

    //	删除之后 Order 要变化
    @Override
    public void deleteStep(BeanStep step) throws BaseException {
        // TODO Auto-generated method stub

//		try {
//			Connection conn = DBUtil.getConnection();
//			String delSql = "delete from tbl_step where step_id=?";
//
//			PreparedStatement delPst = conn.prepareStatement(delSql);
//			delPst.setInt(1, step.getStepId());
//
//			modifySql(conn, delPst);
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}

//		
//		Connection conn = null;
//		try {
//			conn = DBUtil.getConnection();
////			String user_id = BeanUser.currentLoginUser.getUser_id();
////			int step_order = 0;// 需要计算
//			String sqlInsert = "delete from tbl_step where step_id=?";
//			PreparedStatement pstByPlanName = conn.prepareStatement(sqlInsert);
//			pstByPlanName.setInt(1, step.getStepId());
//
//			pstByPlanName.execute();
////			pstByPlanName.setString(2, name);
////			ResultSet resultSetByPlanName = pstByPlanName.executeQuery();
////			System.out.println("resultSetByPlanName");
////			if (resultSetByPlanName.next()) {
////				resultSetByPlanName.close();
////				pstByPlanName.close();
////				throw new BusinessException("同名步骤已经存在");
////			}
////			resultSetByPlanName.close();
//			pstByPlanName.close();
//
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			throw new DbException(ex);
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}

//		int plan_id = plan.getPlan_id();
        int step_id = step.getStepId();
//		这个是写死的 怪不得 。。。
        Connection conn = null;

        try {
//			conn = DBUtil.getConnection();
//			String sql = "select count(*) from tbl_step where plan_id= " + plan_id;
//			String user_id = BeanUser.currentLoginUser.getUser_id();
//			int plan_ord = 0;// 需要计算
//			String sqlByPlanName = "select * from tbl_plan where user_id=? and plan_name=?";
//			PreparedStatement pstByPlanName = conn.prepareStatement(sqlByPlanName);

//			Statement statement = conn.createStatement();
//			ResultSet resultSet = statement.executeQuery(sql);

//			if (resultSet.next()) {
//				if (resultSet.getInt(1) > 0) {
//					resultSet.close();
//					statement.close();
//					throw new BusinessException("该计划已经存在步骤,不能删除");
//				}
//
//			}

            conn = DBUtil.getConnection();
            Statement statement = conn.createStatement();
//			resultSet.close();
            String sqlString = "select step_order from tbl_step where step_id =" + step_id;
            ResultSet resultSet2 = statement.executeQuery(sqlString);
            int step_ord = 0;

            if (resultSet2.next()) {
                step_ord = resultSet2.getInt(1);
//				plan_user_id = resultSet2.getString(2);
            } else {
                throw new BusinessException("该步骤不存在");
            }

            String sqlSelUserId = "SELECT *\n" + "from tbl_plan \n" + "where plan_id in(\n" + "\n"
                    + "SELECT plan_id from tbl_step\n" + "where step_id =  ?\n" + ")";
            PreparedStatement pstSelUserId = conn.prepareStatement(sqlSelUserId);
            pstSelUserId.setInt(1, step_id);
            ResultSet resultSet = pstSelUserId.executeQuery();
            if (resultSet.next()) {
//				String plan_user_id = null;
                String plan_user_id = resultSet.getString(2);
//				if(userIdString.endsWith(suffix))
//				这边逻辑还没有验证过
                if (!BeanUser.currentLoginUser.getUser_id().equals(plan_user_id)) {
                    throw new BusinessException("不能删除别人的计划");
                }
            } else {
                throw new BusinessException("不能删除别人的计划");
            }

//		ResultSet resultSet=	resultSql(conn, pstSelUserId);

            String delSqlString = "delete from tbl_step where step_id= " + step_id;
            statement.execute(delSqlString);
            String upSqlString = "update tbl_step set step_order = step_order - 1 where  step_order >" + step_ord;
            PreparedStatement pst = conn.prepareStatement(upSqlString);
//			pst.setString(1, plan_user_id);
            pst.execute();


//            String sqlNewStep = "update tbl_plan set step_count=step_count-1 where plan_id=?";
//            PreparedStatement pstNewStep = conn.prepareStatement(sqlNewStep);
//            pstNewStep.setInt(1, step.getPlanId());
//            pstNewStep.execute();
//            pstNewStep.close();

//            planChange(step.getPlanId());
            Integer planId = step.getPlanId();
            planChange(conn, step.getPlanId());

            updateStep(planId);


//            Integer planId = step.getPlanId();
//
//            String sqlNewStep = "select *\n" +
//                    "from tbl_step where plan_id=?";
//            PreparedStatement pstNewStep = conn.prepareStatement(sqlNewStep);
//            pstNewStep.setInt(1, planId);
//            ResultSet resultSet1 = pstNewStep.executeQuery();
//            int startStepCnt = 0;
//            int endStepCnt = 0;
//            int stepCnt = 0;
////            Integer startStepCnt=0;
//
//            while (resultSet1.next()) {
//                stepCnt++;
//                Date plBeg = resultSet1.getTimestamp(5);
//                Date plEnd = resultSet1.getTimestamp(6);
//                Date realBeg = resultSet1.getTimestamp(7);
//                if (realBeg != null) {
//                    startStepCnt++;
//                }
//                Date realEnd = resultSet1.getTimestamp(8);
//                if (realEnd != null) {
//                    endStepCnt++;
//                }
//            }
////            System.out.println();
//            pstNewStep.close();
//
//            String sqlUp = "update  tbl_plan set step_count =?\n" +
//                    "and start_step_count=?\n" +
//                    "and finished_step_count=?\n" +
//                    "where plan_id=?";
//            PreparedStatement pstUp = conn.prepareStatement(sqlUp);
//            pstUp.setInt(1, stepCnt);
//            pstUp.setInt(2, startStepCnt);
//            pstUp.setInt(3, endStepCnt);
//            pstUp.setInt(4, planId);
//
//            System.out.println("pstUp : ");
//            System.out.println(pstUp.toString());
//
//            pstUp.execute();
//            pstUp.close();
//
//            System.out.println("startStepCnt : "+startStepCnt);
//            System.out.println("endStepCnt : "+endStepCnt);
//            System.out.println("stepCnt : "+stepCnt);

//            conn=DBUtil.getConnection();
//            String sqlNewStep = "select *\n" +
//                    "from tbl_step where plan_id=?";
//            PreparedStatement pstNewStep = conn.prepareStatement(sqlNewStep);
//            pstNewStep.setInt(1, step.getPlanId());
//            ResultSet resultSet1 = pstNewStep.executeQuery();
//            int startStepCnt=0;
//            int endStepCnt=0;
//            int stepCnt=0;
////            Integer startStepCnt=0;
//
//            while (resultSet1.next()){
//                stepCnt++;
//                Date plBeg=   resultSet.getTimestamp(4);
//                Date plEnd=   resultSet.getTimestamp(5);
//                Date realBeg=   resultSet.getTimestamp(6);
//                if(realBeg!=null){
//                    startStepCnt++;
//                }
//                Date realEnd=   resultSet.getTimestamp(7);
//                if(realEnd!=null){
//                    endStepCnt++;
//                }
//            }
//            pstNewStep.close();
//
//            String sqlUp = "update  tbl_plan set step_count =?\n" +
//                    "and start_step_count=?\n" +
//                    "and finished_step_count=?\n" +
//                    "where plan_id=?";
//            PreparedStatement pstUp = conn.prepareStatement(sqlUp);
//            pstUp.setInt(1,stepCnt);
//            pstUp.setInt(2,startStepCnt);
//            pstUp.setInt(3,endStepCnt);
//            pstUp.setInt(4,step.getPlanId());
//            pstUp.execute();
//            pstUp.close();


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

    /**
     * 有多少个 step
     *
     * @param planId
     */
    public void planChange(Connection conn, Integer planId) throws DbException {
//        Connection conn=null;
        try {
//            conn=DBUtil.getConnection();
            String sqlNewStep = "select *\n" +
                    "from tbl_step where plan_id=?";
            PreparedStatement pstNewStep = conn.prepareStatement(sqlNewStep);
            pstNewStep.setInt(1, planId);
            ResultSet resultSet = pstNewStep.executeQuery();
            int startStepCnt = 0;
            int endStepCnt = 0;
            int stepCnt = 0;
//            Integer startStepCnt=0;

            while (resultSet.next()) {
                stepCnt++;
                Date plBeg = resultSet.getTimestamp(5);
                Date plEnd = resultSet.getTimestamp(6);
                Date realBeg = resultSet.getTimestamp(7);
                if (realBeg != null) {
                    startStepCnt++;
                }
                Date realEnd = resultSet.getTimestamp(8);
                if (realEnd != null) {
                    endStepCnt++;
                }
            }
//            System.out.println();
            pstNewStep.close();

            String sqlUp = "update  tbl_plan set step_count = ?,\n" +
                    "     start_step_count= ?,\n" +
                    "     finished_step_count= ?\n" +
                    "where plan_id= ?";
            PreparedStatement pstUp = conn.prepareStatement(sqlUp);
            pstUp.setInt(1, stepCnt);
            pstUp.setInt(2, startStepCnt);
            pstUp.setInt(3, endStepCnt);
            pstUp.setInt(4, planId);

            System.out.println("pstUp : ");
            System.out.println(pstUp.toString());

            pstUp.execute();
            pstUp.close();

            System.out.println("startStepCnt : "+startStepCnt);
            System.out.println("endStepCnt : "+endStepCnt);
            System.out.println("stepCnt : "+stepCnt);


        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DbException(ex);
        }
    }

    /**
     * step 新增了 需要 plan也要变
     *
     * @param step
     * @throws BaseException
     */
    @Override
    public void startStep(BeanStep step) throws BaseException {
        // TODO Auto-generated method stub
        step.setRealBeginTime(new Date());
        update(step);

        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
//            String sqlNewStep = "update tbl_plan set start_step_count=start_step_count+1 where plan_id=?";
//            PreparedStatement pstNewStep = conn.prepareStatement(sqlNewStep);
//            pstNewStep.setInt(1, step.getPlanId());
//            pstNewStep.execute();
//            pstNewStep.close();

//            planChange(conn, step.getPlanId());
            Integer planId = step.getPlanId();

            updateStep(planId);



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
    public void finishStep(BeanStep step) throws BaseException {
        // TODO Auto-generated method stub
        step.setRealEndTime(new Date());
        update(step);


        Connection conn = null;
        try {
            conn = DBUtil.getConnection();
//            String sqlNewStep = "update tbl_plan set finished_step_count=finished_step_count+1 where plan_id=?";
//            PreparedStatement pstNewStep = conn.prepareStatement(sqlNewStep);
//            pstNewStep.setInt(1, step.getPlanId());
//            pstNewStep.execute();
//            pstNewStep.close();

//            planChange(conn, step.getPlanId());


            Integer planId = step.getPlanId();

            updateStep(planId);




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

    void swap(BeanStep a, BeanStep b) {
        int t = a.getStepOrder();
        a.setStepOrder(b.getStepOrder());
        b.setStepOrder(t);
    }

    @Override
    public void moveUp(BeanStep step) throws BaseException {
        // TODO Auto-generated method stub
        int stepOrder = step.getStepOrder();
        if (stepOrder <= 1) {
//			已经最上面了
            return;
        }
        Connection conn = null;
        try {
//        	2 要上移动 ， 2变成 1 这样就有两个1 了，然后 1 要变成2 ，两个都变成2了。。。
//			除非同时变？ case？
            conn = DBUtil.getConnection();

//
//            String upMe = "update tbl_step set step_order=step_order-1 WHERE step_order= ?";
//            PreparedStatement upMePst = conn.prepareStatement(upMe);
//            upMePst.setInt(1, stepOrder);
//            upMePst.execute();
//
//			int stepOrderUp = stepOrder - 1;

//            String upUp = "update tbl_step set step_order=step_order+1 WHERE step_order= ?";
//            PreparedStatement upUpPst = conn.prepareStatement(upUp);
//            upUpPst.setInt(1, stepOrderUp);
//            upUpPst.execute();
//			modifySql(connection,upMePst);
//			modifySql(connection,upUpPst);
            int stepOrderUp = stepOrder - 1;

//			这样写在一句话里面 好像可以，但是好复杂的感觉。。但是这样只有一句 sql，应该是比较优的
//			因为数据库操作越少越好嘛。。但是就这么几个操作，调优干嘛呢，浪费的时间的对比怎么说
//			我也不知道怎么样才有意义。而且这样写可能也不是最优的。 这样的性能怎么样 我也不清楚
            String upSql = "\n" +
                    "update tbl_step set step_order=\n" +
                    "  CASE \n" +
                    "    WHEN step_order =? THEN\n" +
                    "     ?\n" +
                    "    WHEN  step_order =? THEN\n" +
                    "     ?\n" +
                    "  END\n" +
                    "where step_order=\n" +
                    " CASE \n" +
                    "    WHEN step_order =? THEN\n" +
                    "     ?\n" +
                    "    WHEN  step_order =? THEN\n" +
                    "     ?\n" +
                    "  END\n";
            PreparedStatement upPst = conn.prepareStatement(upSql);
            upPst.setInt(1, stepOrderUp);
            upPst.setInt(2, stepOrder);
            upPst.setInt(3, stepOrder);
            upPst.setInt(4, stepOrderUp);

            upPst.setInt(5, stepOrderUp);
            upPst.setInt(6, stepOrderUp);
            upPst.setInt(7, stepOrder);
            upPst.setInt(8, stepOrder);
            upPst.execute();
            upPst.close();

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


//		这样搜出来 不优啊 。。。
//		BeanStep me = new BeanStep();
//		me.setStepOrder(stepOrder);
////		不是 包装类型 不是null ..
//		BeanStep ano = new BeanStep();
//		ano.setStepOrder(stepOrder - 1);
//		List<BeanStep> meBeanSteps = getBy(me);
//		List<BeanStep> anoBeanSteps = getBy(ano);
//		if (meBeanSteps.size() != 1) {
//			throw new BusinessException("上面没东西");
//		}
//		if (anoBeanSteps.size() != 1) {
//			throw new BusinessException("上面没东西");
//		}
//
//		BeanStep meBeanStep = meBeanSteps.get(0);
//
//		BeanStep anoBeanStep = anoBeanSteps.get(0);
//
//		swap(meBeanStep, anoBeanStep);
//		update(meBeanStep);
//		update(anoBeanStep);

    }

    //	删除之后 order 有问题的 并不是 +1 这样
    @Override
    public void moveDown(BeanStep step) throws BaseException {
        // TODO Auto-generated method stub
//		int stepOrder = step.getStepOrder();
////		if (stepOrder <= 1) {
//////			已经最上面了
////			return;
////		}
//		BeanStep nothing = new BeanStep();
//		List<BeanStep> allBeanSteps = getBy(nothing);
//
//		Optional<BeanStep> maxOrderBeanStepOp = allBeanSteps.stream()
//				.max(Comparator.comparingInt(BeanStep::getStepOrder));
//		if (!maxOrderBeanStepOp.isPresent()) {
//			throw new BusinessException("下面没东西");
//		}
//
//		BeanStep maxOrderBeanStep = maxOrderBeanStepOp.get();
//		if (stepOrder >= maxOrderBeanStep.getStepOrder()) {
//			throw new BusinessException("下面没东西");
//		}
//
//		BeanStep me = new BeanStep();
//		me.setStepOrder(stepOrder);
////		不是 包装类型 不是null ..
//		BeanStep ano = new BeanStep();
////		下面的order 更大
//		ano.setStepOrder(stepOrder + 1);
//		List<BeanStep> meBeanSteps = getBy(me);
//		List<BeanStep> anoBeanSteps = getBy(ano);
//		if (meBeanSteps.size() != 1) {
//			throw new BusinessException("下面没东西");
//		}
//		if (anoBeanSteps.size() != 1) {
//			throw new BusinessException("下面没东西");
//		}
//
//		BeanStep meBeanStep = meBeanSteps.get(0);
//
//		BeanStep anoBeanStep = anoBeanSteps.get(0);
//
//		swap(meBeanStep, anoBeanStep);
//		update(meBeanStep);
//		update(anoBeanStep);


        int stepOrder = step.getStepOrder();
//		if (stepOrder <= 1) {
////			已经最上面了
//			return;
//		}
        Connection conn = null;
        try {
            conn = DBUtil.getConnection();

            String sqlMax = "select max(step_order)  from tbl_step";
            PreparedStatement selMaxPst = conn.prepareStatement(sqlMax);
//			selMaxPst.setInt(1,stepOrder);
            ResultSet resultSet = selMaxPst.executeQuery();
            int maxOrder = Integer.MAX_VALUE;
            if (resultSet.next()) {
                maxOrder = resultSet.getInt(1);
            }
            if (stepOrder >= maxOrder) {
                throw new BusinessException("下面没东西");
            }
////
//            String upMe = "update tbl_step set step_order=step_order+1 WHERE step_order= ?";
//            PreparedStatement upMePst = conn.prepareStatement(upMe);
//            upMePst.setInt(1, stepOrder);
//            upMePst.execute();
//
//            int stepOrderDown = stepOrder + 1;
//            String upDown = "update tbl_step set step_order=step_order-1 WHERE step_order= ?";
//            PreparedStatement updateDownPst = conn.prepareStatement(upDown);
//			updateDownPst.setInt(1, stepOrderDown);
//			updateDownPst.execute();
//			modifySql(connection,upMePst);
//			modifySql(connection,upUpPst);


            int stepOrderDown = stepOrder + 1;

//			这样写在一句话里面 好像可以，但是好复杂的感觉。。
            String upSql = "\n" +
                    "update tbl_step set step_order=\n" +
                    "  CASE \n" +
                    "    WHEN step_order =? THEN\n" +
                    "     ?\n" +
                    "    WHEN  step_order =? THEN\n" +
                    "     ?\n" +
                    "  END\n" +
                    "where step_order=\n" +
                    " CASE \n" +
                    "    WHEN step_order =? THEN\n" +
                    "     ?\n" +
                    "    WHEN  step_order =? THEN\n" +
                    "     ?\n" +
                    "  END\n";
            PreparedStatement upPst = conn.prepareStatement(upSql);
            upPst.setInt(1, stepOrderDown);
            upPst.setInt(2, stepOrder);
            upPst.setInt(3, stepOrder);
            upPst.setInt(4, stepOrderDown);

            upPst.setInt(5, stepOrderDown);
            upPst.setInt(6, stepOrderDown);
            upPst.setInt(7, stepOrder);
            upPst.setInt(8, stepOrder);
            upPst.execute();
            upPst.close();
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
