package cn.edu.zucc.personplan.dao;

import cn.edu.zucc.personplan.model.CarInfo;
import cn.edu.zucc.personplan.model.TblOrder;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DbException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ItDao<T> {
    public List<T> getBy(Connection conn, T obj) throws BaseException ;

//    List<T> loadAll(Connection conn) throws BaseException;

    public void insert(Connection conn, T obj) throws DbException;

    void update(Connection conn, T obj) throws DbException, BaseException, SQLException;

    public void delete(Connection conn, T obj) throws DbException;

    List<T> loadAll() throws BaseException, SQLException;

    List<T> getBy(T t) throws BaseException, SQLException;
}
