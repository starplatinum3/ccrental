package cn.edu.zucc.personplan.dao;

import cn.edu.zucc.personplan.model.CarCategory;
import cn.edu.zucc.personplan.model.CarInfo;
import cn.edu.zucc.personplan.model.Scrap;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DBUtil;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CarCategoryDao implements ItDao<CarCategory> {


    @Override
    public List<CarCategory> getBy(Connection conn, CarCategory obj) throws BaseException {
        return null;
    }

    @Override
    public void insert(Connection conn, CarCategory obj) throws DbException {

    }

    @Override
    public void update(Connection conn, CarCategory obj) throws DbException, BaseException {
        JdbcUtil.update(conn, obj, obj.getCatId(), "cat_id");
    }

    @Override
    public void delete(Connection conn, CarCategory obj) throws DbException {

    }

    @Override
    public List<CarCategory> loadAll() throws BaseException, SQLException {
        return getBy(new CarCategory());
    }

    @Override
    public List<CarCategory> getBy(CarCategory carCategory) throws BaseException, SQLException {
        Connection conn = DBUtil.getConnection();
        List<CarCategory> by = JdbcUtil.getBy(conn, carCategory);
        DBUtil.closeConnection(conn);
        return by;
    }


}
