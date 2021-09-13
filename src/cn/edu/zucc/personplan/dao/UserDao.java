package cn.edu.zucc.personplan.dao;

import cn.edu.zucc.personplan.itf.IUserDao;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.User;
import cn.edu.zucc.personplan.util.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements IUserDao, ItDao<User> {
    @Override
    public List<User> getBy(Connection conn, User obj) throws BaseException {
        return null;
    }

    @Override
    public void insert(Connection conn, User obj) throws DbException {

    }

    @Override
    public void update(Connection conn, User obj) throws DbException, BaseException, SQLException {
        JdbcUtil.update(DBUtil.getConnection(),obj,obj.getUserId(),"user_id");
//        try{
//            JdbcUtil.update(DBUtil.getConnection(),obj);
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }

    }

    @Override
    public void delete(Connection conn, User obj) throws DbException {

    }

    @Override
    public List<User> loadAll() throws BaseException {
        try{
            List<User> by = JdbcUtil.getBy(new User());
            return by;
        }catch (SQLException e){
            return null;
        }

//        return null;
    }

    @Override
    public List<User> getBy(User obj) throws BaseException {
        try {
            return JdbcUtil.getBy(obj);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  null;

//        return JdbcUtil.getBy(obj);
    }

    /**
     * 需要去 用户表 查询，还有员工表，那还是有个按钮 来分开？
     *
     * @param username
     * @param pwd
     * @return
     * @throws BaseException
     */
    @Override
    public User login(String username, String pwd) throws BaseException {
        // TODO Auto-generated method stub
        Connection conn = null;
        User user = null;
        if (pwd == null) {
            throw new BusinessException("密码为空");
        }
        try {
            conn = DBUtil.getConnection();
            String sql = "select * from user where user_name = ? and password=?";
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, pwd);
            java.sql.ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setSex(rs.getInt(3));
                user.setPassword(rs.getString(4));
                user.setPhone(rs.getString(5));
                user.setMailbox(rs.getString(6));
                user.setCity(rs.getString(7));
                user.setRegTime(rs.getTimestamp(8));
                return user;
            } else {
                throw new BusinessException("用户不存在或者密码错误");
            }
//            user.get
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
}
