package cn.edu.zucc.personplan.dao;

import cn.edu.zucc.personplan.model.CarCategory;
import cn.edu.zucc.personplan.svo.OrderSvo;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.JdbcUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderSvoDao  implements ItDao<OrderSvo> {

    @Override
    public List<OrderSvo> getBy(Connection conn, OrderSvo obj) throws BaseException {
        return null;
    }

    @Override
    public void insert(Connection conn, OrderSvo obj) throws DbException {

    }

    @Override
    public void update(Connection conn, OrderSvo obj) throws DbException, BaseException {

    }

    @Override
    public void delete(Connection conn, OrderSvo obj) throws DbException {

    }

    @Override
    public List<OrderSvo> loadAll() throws BaseException, SQLException {
        return getBy(new OrderSvo());
    }

    @Override
    public List<OrderSvo> getBy(OrderSvo orderSvo) throws BaseException, SQLException {
        return JdbcUtil.getBy(orderSvo);
    }
}
