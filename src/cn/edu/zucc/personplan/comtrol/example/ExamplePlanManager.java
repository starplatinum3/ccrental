package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Order;

import cn.edu.zucc.personplan.itf.IPlanManager;
import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.User;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.TimeUtil;

public class ExamplePlanManager implements IPlanManager {

	public static java.sql.Timestamp nowSqlTime() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

	@Override
	public BeanPlan addPlan(String name) throws BaseException {
		// TODO Auto-generated method stub
		System.out.println("判断计划名字");
		if (name == null || "".equals(name))
			throw new BusinessException("计划名称必须提供");

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String user_id = BeanUser.currentLoginUser.getUser_id();
			int plan_ord = 0;// 需要计算
			String sqlByPlanName = "select * from tbl_plan where user_id=? and plan_name=?";
			PreparedStatement pstByPlanName = conn.prepareStatement(sqlByPlanName);
			pstByPlanName.setString(1, user_id);
			pstByPlanName.setString(2, name);
			ResultSet resultSetByPlanName = pstByPlanName.executeQuery();
			System.out.println("resultSetByPlanName");
			if (resultSetByPlanName.next()) {
				resultSetByPlanName.close();
				pstByPlanName.close();
				throw new BusinessException("同名计划已经存在");
			}
			resultSetByPlanName.close();
			pstByPlanName.close();

//    	  System.out.println("");
//	      String sql="select max(plan_ord) from tbl_plan where user_id = ?";
//			最大的 plan Order 
			String sql = "select max(plan_order) from tbl_plan where user_id = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user_id);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				plan_ord = rs.getInt(1) + 1;
			} else {
				plan_ord = 1;
			}
			System.out.println("plan_ord");
			System.out.println(plan_ord);
			rs.close();
			pst.close();
			sql = "Insert into tbl_plan(user_id,plan_order,plan_name,create_time,step_count,start_step_count,finished_step_count) values(?,?,?,?,0,0,0)";
//	       sql="Insert into tbl_plan(plan_id,user_id,plan_order,plan_name,create_time,step_count,start_step_count,finished_step_count) values(?,?,?,?,0,0,0)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_id);
			pst.setInt(2, plan_ord);
			pst.setString(3, name);
			pst.setTimestamp(4, new java.sql.Timestamp(System.currentTimeMillis()));
			pst.execute();
			pst.close();
			BeanPlan p = new BeanPlan();
//			最大的 plan id
			sql = "select max(plan_id) from tbl_plan where user_id = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_id);
			rs = pst.executeQuery();
			if (rs.next()) {
				int pid = rs.getInt(1);
				System.out.println("pid");
				System.out.println(pid);
			} else {

			}

			rs.close();
			pst.close();
			return p;
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

//	
//	@Override
//	public BeanPlan addPlan(String name) throws BaseException {
//		// TODO Auto-generated method stub
//		if(name==null||"".equals(name)) throw new BusinessException("计划名称必须提供");
////		if(name.length()<100)
////		List<BeanPlan> result=new ArrayList<>();
//		Connection conn = null;
//		try {
//			conn = DBUtil.getConnection();
//			String userId=BeanUser.currentLoginUser.getUser_id();
//			int plan_ord=0;
//			String sqlMax="select max(plan_ord) from tbl_plan where user_id=? ";
//			PreparedStatement pstMax=conn.prepareStatement(sqlMax);
//			pstMax.setString(1, userId);
//			
//			ResultSet rs=pstMax.executeQuery();
//			if(rs.next()) {
//				plan_ord=rs.getInt(1)+1;
//			}else {
//				plan_ord=1;
//			}
//			rs.close();
//			pstMax.close();
//			
//			String sql = "INSERT into tbl_plan(user_id,plan_order,plan_name,create_time,\n" + 
//					"step_count,start_step_count,finished_step_count)\n" + 
//					"VALUES (?,?,?,?,0,0,0)";
//			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setString(1,userId);
//			pst.setInt(2,plan_ord);
//			pst.setString(3,name);
//			pst.setTimestamp(1,nowSqlTime());
//			pst.execute();
//			pst.close();
////			pst.setString(1,userId);
////			pst.setString(2, pwd);
////			ResultSet rs = pst.executeQuery();
////			String passTrue = null;
////			Date createTime = null;
//			
//			BeanPlan p=new BeanPlan();
//			String sqlMaxPlanId ="select max(plan_id) "
//			
//			
//
//			while (rs.next()) {
//				BeanPlan p=new BeanPlan();
//				
//				result.add(p);
//				rs.close();
//				pst.close();
//				System.out.println("查无此人");
//				return;
//			}
////			rs.close();
////			pst.close();
////			查到人了
//
////				String passTrue=rs.getString(2);
//			passTrue = rs.getString(2);
//			createTime = rs.getTimestamp(3);
//			String saveSql="update tbl_user set user_pwd=? \n" + 
//			 		"WHERE user_id=?";
////			String saveSql = "update tbl_user set user_pwd=? \n" + "and set register_time=?\n" + "WHERE user_id=?";
//			PreparedStatement pstSave = conn.prepareStatement(saveSql);
//			pstSave.setString(1, newPwd);
////			pst.setTimestamp(2, TimeUtil.da  user.getRegister_time());
//			pstSave.setString(2, user.getUser_id());
//			pstSave.execute();
////				if(!passTrue.equals(pwd)) {
////					throw new  BusinessException("密码错误");
////				}
////			rs.close();
//			pstSave.close();
////				throw new  BusinessException("用户已存在");
//
////			rs.close();
////			pst.close();
////			sql="insert into tbl_user(user_id,user_pwd,register_time) values(?,?,?)";
////			pst=conn.prepareStatement(sql);
////			pst.setString(1,userid);
////			pst.setString(2, pwd);
////			pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
////			pst.execute();
////			BeanUser user = new BeanUser();
////			user.setUser_id(userid);
////			user.setRegister_time(createTime);
//////			user.set(new Date());
////			return user;
//		} catch (SQLException ex) {
//			// TODO: handle exception
//			throw new DbException(ex);
//		} finally {
//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//			}
//		}
//
////		return null;
//	}

	@Override
	public List<BeanPlan> loadAll() throws BaseException {
		boolean fake = false;
		if (fake) {
			List<BeanPlan> result = new ArrayList<BeanPlan>();
			BeanPlan p = new BeanPlan();
			result.add(p);
			return result;
		}

//		

//		if (!newPwd.equals(newPwd))
//			throw new BusinessException("两次输入的密码密码不一样");
		List<BeanPlan> result = new ArrayList<>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from tbl_plan where user_id=? order by plan_order";
			PreparedStatement pst = conn.prepareStatement(sql);
//			pst.setString(1, BeanUser.currentLoginUser.getUser_id());
			pst.setInt(1, User.currentLoginUser.getUserId());
//			pst.setString(2, pwd);
			ResultSet rs = pst.executeQuery();
			String passTrue = null;
			Date createTime = null;

			while (rs.next()) {
				BeanPlan p = new BeanPlan();
				p.setPlan_id(rs.getInt(1));
				p.setUser_id(rs.getString(2));
				p.setPlan_order(rs.getInt(3));
				p.setPlan_name(rs.getString(4));
				p.setCreate_time(rs.getTimestamp(5));
				p.setStep_count(rs.getInt(6));
				p.setStart_step_count(rs.getInt(7));
				p.setFinished_step_count(rs.getInt(8));

				result.add(p);
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

	@Override
	public void deletePlan(BeanPlan plan) throws BaseException {
//		Connection conn=null;
//		try {
//			conn=DBUtil.getConnection();
//			String user_id=BeanUser.currentLoginUser.getUser_id();
//		}

//		int plan_id = 2;
		int plan_id = plan.getPlan_id();
//		这个是写死的 怪不得 。。。
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) from tbl_step where plan_id= " + plan_id;
//			String user_id = BeanUser.currentLoginUser.getUser_id();
//			int plan_ord = 0;// 需要计算
//			String sqlByPlanName = "select * from tbl_plan where user_id=? and plan_name=?";
//			PreparedStatement pstByPlanName = conn.prepareStatement(sqlByPlanName);

			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			if (resultSet.next()) {
				if (resultSet.getInt(1) > 0) {
					resultSet.close();
					statement.close();
					throw new BusinessException("该计划已经存在步骤,不能删除");
				}

			}
//			resultSet.close();
			String sqlString = "select plan_order,user_id from tbl_plan where plan_id=" + plan_id;
			ResultSet resultSet2 = statement.executeQuery(sqlString);
			int plan_ord = 0;
			String plan_user_id = null;
			if (resultSet2.next()) {
				plan_ord = resultSet2.getInt(1);
				plan_user_id = resultSet2.getString(2);
			} else {
				throw new BusinessException("该计划不存在");
			}
			if (!BeanUser.currentLoginUser.getUser_id().equals(plan_user_id)) {
				throw new BusinessException("不能删除别人的计划");
			}

			String delSqlString = "delete from tbl_plan where plan_id=" + plan_id;
			statement.execute(delSqlString);
			String upSqlString = "update tbl_plan set plan_order = plan_order - 1 where user_id=? and plan_order>" + plan_ord;
			PreparedStatement pst = conn.prepareStatement(upSqlString);
			pst.setString(1, plan_user_id);
			pst.execute();

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
