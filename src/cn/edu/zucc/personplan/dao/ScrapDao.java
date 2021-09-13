package cn.edu.zucc.personplan.dao;

import cn.edu.zucc.personplan.model.Promotion;
import cn.edu.zucc.personplan.model.Scrap;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ScrapDao implements ItDao<Scrap>  {

    @Override
    public List<Scrap> getBy(Connection conn, Scrap obj) throws BaseException {
        return null;
    }

    @Override
    public void insert(Connection conn, Scrap obj) throws DbException {

    }

    @Override
    public void update(Connection conn, Scrap obj) throws DbException, BaseException {

    }

    @Override
    public void delete(Connection conn, Scrap obj) throws DbException {

    }

    @Override
    public List<Scrap> loadAll() throws BaseException, SQLException {
        return getBy(new Scrap());
    }

    @Override
    public List<Scrap> getBy(Scrap scrap) throws BaseException, SQLException {
//        Connection conn= DBUtil.getConnection();
//        List<Scrap> by = JdbcUtil.getBy(conn, scrap);
      return   JdbcUtil.getBy(scrap);
//        return by;
    }
}
