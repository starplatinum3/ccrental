package cn.edu.zucc.personplan.dao;

import cn.edu.zucc.personplan.model.Allocation;
import cn.edu.zucc.personplan.model.CarCategory;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AllocationDao implements ItDao<Allocation> {
    @Override
    public List<Allocation> getBy(Connection conn, Allocation obj) throws BaseException {
        return null;
    }

    @Override
    public void insert(Connection conn, Allocation obj) throws DbException {

    }

    @Override
    public void update(Connection conn, Allocation obj) throws DbException, BaseException, SQLException {

    }

    @Override
    public void delete(Connection conn, Allocation obj) throws DbException {

    }

    @Override
    public List<Allocation> loadAll() throws BaseException, SQLException {
        return getBy(new Allocation());
    }

    @Override
    public List<Allocation> getBy(Allocation allocation) throws BaseException, SQLException {

        return  JdbcUtil.getBy(allocation);
    }
}
