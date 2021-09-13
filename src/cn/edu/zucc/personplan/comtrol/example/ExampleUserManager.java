package cn.edu.zucc.personplan.comtrol.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import cn.edu.zucc.personplan.itf.IUserManager;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.TimeUtil;

public class ExampleUserManager implements IUserManager {

	@Override
	public BeanUser reg(String userid, String pwd, String pwd2) throws BaseException {
		// TODO Auto-generated method stub
		if (userid == null || "".equals(userid))
			throw new BusinessException("账号不能为空");
		if (pwd == null || "".equals(pwd))
			throw new BusinessException("密码不能为空");
		if (!pwd.equals(pwd2))
			throw new BusinessException("两次输入的密码密码不一样");
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select user_id from tbl_user where user_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
//			pst.setString(2, pwd);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				rs.close();
				pst.close();
				throw new BusinessException("用户已存在");

			}
			rs.close();
			pst.close();
			sql = "insert into tbl_user(user_id,user_pwd,register_time) values(?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, pwd);
			pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pst.execute();
			BeanUser user = new BeanUser();
			user.setUser_id(userid);
			user.setRegister_time(new Date());
			return user;
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
	public BeanUser login(String userid, String pwd) throws BaseException {
		// TODO Auto-generated method stub
		Connection conn = null;
		BeanUser user = null;
		if (pwd == null) {

		}
		try {
			conn = DBUtil.getConnection();
			String sql = "select register_time from tbl_user where user_id = ? and user_pwd = ?";
			java.sql.PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, pwd);
			java.sql.ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				user = new BeanUser();
				user.setUser_id(userid);
				user.setRegister_time(rs.getTimestamp(1));
				return user;
			} else {
				throw new BusinessException("用户不存在或者密码错误");
			}
		} catch (SQLException ex) {
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

//	@Override
//	public BeanUser login(String userid, String pwd) throws BaseException {
//		// TODO Auto-generated method stub
//		if(userid==null||"".equals(userid)) throw new BusinessException("账号不能为空");
//		if(pwd==null||"".equals(pwd)) throw new BusinessException("密码不能为空");
////		if(!pwd.equals(pwd2)) throw new BusinessException("两次输入的密码密码不一样");
//		Connection conn=null;
//		try {
//			conn=DBUtil.getConnection();
//			String sql="select * from tbl_user where user_id=?";
//			PreparedStatement pst=conn.prepareStatement(sql);
//			pst.setString(1, userid);
////			pst.setString(2, pwd);
//			ResultSet rs=pst.executeQuery();
//			String passTrue=null;
//			Date createTime=null;
//			if(rs.next()) {
////				String passTrue=rs.getString(2);
//				 passTrue=rs.getString(2);
//			 createTime=	rs.getTimestamp(3);
//				if(!passTrue.equals(pwd)) {
//					throw new  BusinessException("密码错误");
//				}
//				rs.close();
//				pst.close();
////				throw new  BusinessException("用户已存在");
//				
//			}
//			rs.close();
//			pst.close();
////			sql="insert into tbl_user(user_id,user_pwd,register_time) values(?,?,?)";
////			pst=conn.prepareStatement(sql);
////			pst.setString(1,userid);
////			pst.setString(2, pwd);
////			pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
////			pst.execute();
//			BeanUser user=new BeanUser();
//			user.setUser_id(userid);
//			user.setRegister_time(createTime);
////			user.set(new Date());
//			return user;
//		}catch (SQLException ex) {
//			// TODO: handle exception
//			throw new DbException(ex);
//		}finally {
//			if(conn!=null) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					// TODO: handle exception
//					e.printStackTrace();
//				}
//			}
//		}
//		
//		
//	}

	@Override
	public void changePwd(BeanUser user, String oldPwd, String newPwd, String newPwd2) throws BaseException {
		// TODO Auto-generated method stub
//		if(userid==null||"".equals(userid)) throw new BusinessException("账号不能为空");
//		if(pwd==null||"".equals(pwd)) throw new BusinessException("密码不能为空");
		if (!newPwd.equals(newPwd))
			throw new BusinessException("两次输入的密码密码不一样");

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from tbl_user where user_id=?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getUser_id());
//			pst.setString(2, pwd);
			ResultSet rs = pst.executeQuery();
			String passTrue = null;
			Date createTime = null;

			if (!rs.next()) {
				rs.close();
				pst.close();
				System.out.println("查无此人");
				return;
			}
//			rs.close();
//			pst.close();
//			查到人了

//				String passTrue=rs.getString(2);
			passTrue = rs.getString(2);
			createTime = rs.getTimestamp(3);
			String saveSql="update tbl_user set user_pwd=? \n" + 
			 		"WHERE user_id=?";
//			String saveSql = "update tbl_user set user_pwd=? \n" + "and set register_time=?\n" + "WHERE user_id=?";
			PreparedStatement pstSave = conn.prepareStatement(saveSql);
			pstSave.setString(1, newPwd);
//			pst.setTimestamp(2, TimeUtil.da  user.getRegister_time());
			pstSave.setString(2, user.getUser_id());
			pstSave.execute();
//				if(!passTrue.equals(pwd)) {
//					throw new  BusinessException("密码错误");
//				}
//			rs.close();
			pstSave.close();
//				throw new  BusinessException("用户已存在");

//			rs.close();
//			pst.close();
//			sql="insert into tbl_user(user_id,user_pwd,register_time) values(?,?,?)";
//			pst=conn.prepareStatement(sql);
//			pst.setString(1,userid);
//			pst.setString(2, pwd);
//			pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
//			pst.execute();
//			BeanUser user = new BeanUser();
//			user.setUser_id(userid);
//			user.setRegister_time(createTime);
////			user.set(new Date());
//			return user;
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

}
