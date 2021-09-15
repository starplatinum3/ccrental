package cn.edu.zucc.personplan.util;

import cn.edu.zucc.personplan.model.NetInfo;
import sun.nio.ch.Net;
//import com.sun.org.apache.xpath.internal.operations.String;

import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class JdbcUtil<T> {

    public static <T> void insert(T obj) throws DbException, SQLException {
        Connection connection = DBUtil.getConnection();
        insert(connection, obj);
        DBUtil.closeConnection(connection);
    }

    public static <T> void insert(Connection conn, String sql, T obj) throws DbException {
        try {
//            String sqlUp = "INSERT INTO car_info ( car_id,net_id,type_id,license,car_status )\n" +
//                    "VALUES\n" +
//                    "(\n" +
//                    "    ?,?,?,?,?\n" +
//                    ");";
            PreparedStatement pstByPlanName = conn.prepareStatement(sql);

            JdbcUtil.set(pstByPlanName, obj);

            pstByPlanName.execute();

            pstByPlanName.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DbException(ex);
        }
    }

//
//    public static <T> void insertBatch(Connection conn, String sql, List<T> list) throws DbException {
//        try {
////            String sqlUp = "INSERT INTO car_info ( car_id,net_id,type_id,license,car_status )\n" +
////                    "VALUES\n" +
////                    "(\n" +
////                    "    ?,?,?,?,?\n" +
////                    ");";
//            PreparedStatement pstByPlanName = conn.prepareStatement(sql);
//
//            JdbcUtil.set(pstByPlanName, obj);
//
//            pstByPlanName.execute();
//
//            pstByPlanName.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new DbException(ex);
//        }
//    }


    //    public static List<String> getBeanUnderscoreNames(Object object){
//
    public static <T> String getTableName(T obj) {
        String tableName = obj.getClass().getSimpleName();
        tableName = StringUtils.underscoreNameLower(tableName);
        return tableName;
    }

    public static <T> void insert(Connection conn, T obj) throws DbException {

        String tableName = obj.getClass().getSimpleName();
        tableName = StringUtils.underscoreNameLower(tableName);
        List<String> beanUnderscoreNames = getBeanUnderscoreNames(obj);
        String join = String.join(",", beanUnderscoreNames);
//        根据 java类的顺序
//        System.out.println("collect");
//        System.out.println(collect);

//        java 生成 指定大小的
//        Arrays.fill();
//        Collection<String> wen = new ArrayList<>();
        int size = beanUnderscoreNames.size();
//        Collections.fill(wen, "?" );

        String[] arr = new String[size];
//      arr 构造 arr list
        Arrays.fill(arr, "?");
        List<String> questionMarkList = Arrays.asList(arr);
        String questionMarks = String.join(",", questionMarkList);
//        List<String> wen = new ArrayList<>(Arrays.asList(arr));
//        java  list fill
//        wen.
        String sql = "INSERT INTO " + tableName + " (  " + join + " )\n " +
                "VALUES\n" +
                " (\n " +
                questionMarks +
                "  )  ";
//        System.out.println("sql");
//        System.out.println(sql);

        insert(conn, sql, obj);
//
//        try {
//
////            String sqlUp = "INSERT INTO car_info ( car_id,net_id,type_id,license,car_status )\n" +
////                    "VALUES\n" +
////                    "(\n" +
////                    "    ?,?,?,?,?\n" +
////                    ");";
//            PreparedStatement pstByPlanName = conn.prepareStatement(sql);
//
//            JdbcUtil.set(pstByPlanName, obj);
//
//            pstByPlanName.execute();
//
//            pstByPlanName.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new DbException(ex);
//        }
    }


    void insert(Connection conn, String sql) throws SQLException {
        conn.createStatement().execute(sql);
    }


    public static <T> void replaceBatch(Connection conn, List<T> list) throws IllegalAccessException, SQLException {

        doBatch(conn,list,"replace ");

    }


    public static <T> void insertBatch(Connection conn, List<T> list) throws IllegalAccessException, SQLException {

        doBatch(conn,list,"insert ");
//        T obj = list.get(0);
//        String tableName = obj.getClass().getSimpleName();
//        tableName = StringUtils.underscoreNameLower(tableName);
//        List<String> beanUnderscoreNames = getBeanUnderscoreNames(obj);
//        String join = String.join(",", beanUnderscoreNames);
////        根据 java类的顺序
////        System.out.println("collect");
////        System.out.println(collect);
//
////        java 生成 指定大小的
////        Arrays.fill();
////        Collection<String> wen = new ArrayList<>();
////        int size = beanUnderscoreNames.size();
////        Collections.fill(wen, "?" );
//
////        String[] arr = new String[size];
//////      arr 构造 arr list
////        Arrays.fill(arr, "?");
////        List<String> questionMarkList = Arrays.asList(arr);
////        String questionMarks = String.join(",", questionMarkList);
//
////        List<String> wen = new ArrayList<>(Arrays.asList(arr));
////        java  list fill
////        wen.
////        这有必要 prepare吗
//
////        String sql = "INSERT INTO " + tableName + " (  " + join + " )\n " +
////                "VALUES\n" +
////                " (\n " +
////                questionMarks +
////                "  )  ";
//        StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (  " + join + " )   values  ");
////        System.out.println("sql");
////        System.out.println(sql);
//        for (T t : list) {
//            List<Object> objVals = BeanUtil.getObjVals(t, false);
//            List<String> collect = objVals.stream().map(JdbcUtil::quotation).collect(Collectors.toList());
//            String join1 = String.join(",", collect);
//            sql.append("  (  ").append(join1).append("  ),");
//        }
//        sql.deleteCharAt(sql.length() - 1);
//
//        System.out.println("sql");
//        System.out.println(sql);
////        insert();
//        conn.createStatement().execute(String.valueOf(sql));


//        in
//        insert(conn, sql, obj);
//
//        try {
//
////            String sqlUp = "INSERT INTO car_info ( car_id,net_id,type_id,license,car_status )\n" +
////                    "VALUES\n" +
////                    "(\n" +
////                    "    ?,?,?,?,?\n" +
////                    ");";
//            PreparedStatement pstByPlanName = conn.prepareStatement(sql);
//
//            JdbcUtil.set(pstByPlanName, obj);
//
//            pstByPlanName.execute();
//
//            pstByPlanName.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new DbException(ex);
//        }
    }


    public static <T> void doBatch(Connection conn, List<T> list,String  doString)
            throws IllegalAccessException,
            SQLException {

        T obj = list.get(0);
        String tableName = obj.getClass().getSimpleName();
        tableName = StringUtils.underscoreNameLower(tableName);
        List<String> beanUnderscoreNames = getBeanUnderscoreNames(obj);
        String join = String.join(",", beanUnderscoreNames);
//        根据 java类的顺序
//        System.out.println("collect");
//        System.out.println(collect);

//        java 生成 指定大小的
//        Arrays.fill();
//        Collection<String> wen = new ArrayList<>();
//        int size = beanUnderscoreNames.size();
//        Collections.fill(wen, "?" );

//        String[] arr = new String[size];
////      arr 构造 arr list
//        Arrays.fill(arr, "?");
//        List<String> questionMarkList = Arrays.asList(arr);
//        String questionMarks = String.join(",", questionMarkList);

//        List<String> wen = new ArrayList<>(Arrays.asList(arr));
//        java  list fill
//        wen.
//        这有必要 prepare吗

//        String sql = "INSERT INTO " + tableName + " (  " + join + " )\n " +
//                "VALUES\n" +
//                " (\n " +
//                questionMarks +
//                "  )  ";
        StringBuilder sql = new StringBuilder(doString+"  INTO " + tableName + " (  " + join + " )   values  ");
//        System.out.println("sql");
//        System.out.println(sql);
        for (T t : list) {
            List<Object> objVals = BeanUtil.getObjVals(t, false);
            List<String> collect = objVals.stream().map(JdbcUtil::quotation).collect(Collectors.toList());
            String join1 = String.join(",", collect);
            sql.append("  (  ").append(join1).append("  ),");
        }
        sql.deleteCharAt(sql.length() - 1);

        System.out.println("sql");
        System.out.println(sql);
//        insert();
        conn.createStatement().execute(String.valueOf(sql));
//        in
//        insert(conn, sql, obj);
//
//        try {
//
////            String sqlUp = "INSERT INTO car_info ( car_id,net_id,type_id,license,car_status )\n" +
////                    "VALUES\n" +
////                    "(\n" +
////                    "    ?,?,?,?,?\n" +
////                    ");";
//            PreparedStatement pstByPlanName = conn.prepareStatement(sql);
//
//            JdbcUtil.set(pstByPlanName, obj);
//
//            pstByPlanName.execute();
//
//            pstByPlanName.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new DbException(ex);
//        }
    }


    public static String quotation(Object o) {
        if (o == null) {
            return null;
        }
        return q(o);
    }


    static void insertBatchTest() throws SQLException, DbException, IllegalAccessException {
        Connection connection = DBUtil.getConnection();
        List<NetInfo> list = new ArrayList<>();
        NetInfo netInfo = new NetInfo();
        netInfo.setAddr("321");

        NetInfo netInfo2 = new NetInfo();
        netInfo2.setAddr("2");

        NetInfo netInfo3 = new NetInfo();
        netInfo3.setAddr("3");

        NetInfo netInfo4 = new NetInfo();
        netInfo4.setAddr("4");
        list.add(netInfo);
        list.add(netInfo2);
        list.add(netInfo3);
        list.add(netInfo4);
        insertBatch(connection, list);
    }

    static void insertBatchTest2() throws SQLException,  IllegalAccessException {
//        List<NetInfo> list = new ArrayList<>();

        List<NetInfo> list = new ArrayList<>();
//        NetInfo netInfo = new NetInfo();
//        netInfo.setAddr("321");
//
//        NetInfo netInfo2 = new NetInfo();
//        netInfo2.setAddr("2");
//
//        NetInfo netInfo3 = new NetInfo();
//        netInfo3.setAddr("3");
//
//        NetInfo netInfo4 = new NetInfo();
//        netInfo4.setAddr("4");
//        list.add(netInfo);
//        list.add(netInfo2);
//        list.add(netInfo3);
//        list.add(netInfo4);
        int cnt = 10000;
        for (int i = 0; i < cnt; i++) {
            NetInfo netInfo = new NetInfo();
            netInfo.setAddr("" + i);
            list.add(netInfo);

        }

        Connection connection = DBUtil.getConnection();

        long start = System.currentTimeMillis();

        insertBatch(connection, list);

        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("插入 "+cnt+" 条 数据");
//        System.out.println("插入 "+cnt+" 条 数据");
        System.out.println(time + "  ms");
        DBUtil.closeConnection(connection);

    }


    static void replaceBatchTest() throws SQLException,  IllegalAccessException {
//        List<NetInfo> list = new ArrayList<>();

        List<NetInfo> list = new ArrayList<>();
//        NetInfo netInfo = new NetInfo();
//        netInfo.setAddr("321");
//
//        NetInfo netInfo2 = new NetInfo();
//        netInfo2.setAddr("2");
//
//        NetInfo netInfo3 = new NetInfo();
//        netInfo3.setAddr("3");
//
//        NetInfo netInfo4 = new NetInfo();
//        netInfo4.setAddr("4");
//        list.add(netInfo);
//        list.add(netInfo2);
//        list.add(netInfo3);
//        list.add(netInfo4);
        int cnt = 10000;
        int no=20000;
        for (int i = 0; i < cnt; i++) {
            NetInfo netInfo = new NetInfo();
            netInfo.setAddr("88888" );
            netInfo.setNetId( no);
            no++;
            list.add(netInfo);

        }

        Connection connection = DBUtil.getConnection();

        long start = System.currentTimeMillis();

//        insertBatch(connection, list);
        replaceBatch(connection, list);

        long end = System.currentTimeMillis();
        long time = end - start;
        try(FileWriter fileWriter=new FileWriter("replace_batch_10000.log")){
            fileWriter.write("插入 "+cnt+" 条 数据\n");
            fileWriter.write(time + "  ms\n");
//            System.out.println(time + "  ms");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("插入 "+cnt+" 条 数据");
////        System.out.println("插入 "+cnt+" 条 数据");
//        System.out.println(time + "  ms");
        DBUtil.closeConnection(connection);

    }


    static void insertBatchTestOrigin() throws SQLException, IllegalAccessException, DbException {
//        List<NetInfo> list = new ArrayList<>();

        List<NetInfo> list = new ArrayList<>();
//        NetInfo netInfo = new NetInfo();
//        netInfo.setAddr("321");
//
//        NetInfo netInfo2 = new NetInfo();
//        netInfo2.setAddr("2");
//
//        NetInfo netInfo3 = new NetInfo();
//        netInfo3.setAddr("3");
//
//        NetInfo netInfo4 = new NetInfo();
//        netInfo4.setAddr("4");
//        list.add(netInfo);
//        list.add(netInfo2);
//        list.add(netInfo3);
//        list.add(netInfo4);
        int cnt = 10000;
        for (int i = 0; i < cnt; i++) {
            NetInfo netInfo = new NetInfo();
            netInfo.setAddr("" + i);
            list.add(netInfo);

        }


        Connection connection = DBUtil.getConnection();
        long start = System.currentTimeMillis();

        for (NetInfo netInfo : list) {
            insert(connection, netInfo);
        }


        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.println("插入 "+cnt+" 条 数据");
        System.out.println("不包括构造数据，循环插入");
        System.out.println(time + "  ms");
        DBUtil.closeConnection(connection);

    }



    //    https://www.cnblogs.com/Marydon20170307/p/14149970.html
//    <T>
//    public T getInstanceNoParam(T t) {
//        try {
//            return (T) t.getClass().newInstance();
//        } catch (Exception e) {
//            return null;
//        }
//    }


    public static <T> T getInstanceNoParam(T t) {
        try {
            return (T) t.getClass().newInstance();
        } catch (Exception e) {
            return null;
        }
    }


    private T createType() {

        try {
            Type superClass = getClass().getGenericSuperclass();
            Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
            Class<?> clazz = getRawType(type);
            return (T) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // type不能直接实例化对象，通过type获取class的类型，然后实例化对象
    public static Class<?> getRawType(Type type) {
        if (type instanceof Class) {
            return (Class) type;
        } else if (type instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type rawType = parameterizedType.getRawType();
            return (Class) rawType;
        } else if (type instanceof GenericArrayType) {
            Type componentType = ((GenericArrayType) type).getGenericComponentType();
            return Array.newInstance(getRawType(componentType), 0).getClass();
        } else if (type instanceof TypeVariable) {
            return Object.class;
        } else if (type instanceof WildcardType) {
            return getRawType(((WildcardType) type).getUpperBounds()[0]);
        } else {
            String className = type == null ? "null" : type.getClass().getName();
            throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + className);
        }
    }

    public static <T> List<T> getBy(T obj) throws DbException, SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "select * from  " + getTableName(obj) + "   ";
        List<T> by = getBy(conn, sql, obj);
        DBUtil.closeConnection(conn);
        return by;
    }

    public static <T> List<T> getBy(T obj, T likeObj) throws DbException, SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "select * from  " + getTableName(obj) + "   ";
        List<T> by = getBy(conn, sql, obj, likeObj, null);
//        dd
        DBUtil.closeConnection(conn);
        return by;
    }


    public static <T> List<T> getBy(Connection conn, T obj) throws DbException {
        String sql = "select * from  " + getTableName(obj) + "   ";
        List<T> by = getBy(conn, sql, obj);
        return by;
    }


    //    https://www.cnblogs.com/Marydon20170307/p/14149970.html
    public static <T> List<T> getBy(Connection conn, T obj, String tableName) throws DbException {
        String sql = "select * from  " + tableName + "   ";
        List<T> by = getBy(conn, sql, obj);
        return by;
    }

    public static <T> List<T> getBy(Connection conn, String sql, T obj) throws DbException {

        return getBy(conn, sql, obj, null);
//        Connection conn = null;
//        Emp obj=(Emp)o;
//        try {
////            conn = DBUtil.getConnection();
////			String user_id = BeanUser.currentLoginUser.getUser_id();
////            int step_order = 0;// 需要计算
////            String sqlInsert = "select * from car_info ";
//            StringBuilder stringBuilder = new StringBuilder(sql);
//            StringBuilder where = where(obj, stringBuilder);
//
//            PreparedStatement pst = conn.prepareStatement(String.valueOf(where));
////			pstByPlanName.setInt(1, stepId);
//
//            ResultSet resultSet = pst.executeQuery();
////			pstByPlanName.setString(2, name);
////			ResultSet resultSetByPlanName = pstByPlanName.executeQuery();
////			System.out.println("resultSetByPlanName");
////			if (resultSetByPlanName.next()) {
////				resultSetByPlanName.close();
////				pstByPlanName.close();
////				throw new BusinessException("同名步骤已经存在");
////			}
////			resultSetByPlanName.close();
////			pstByPlanName.close();
//
//            List<T> list = new ArrayList<>();
//            while (resultSet.next()) {
////                NetInfo lstObj = new NetInfo();
////                CarInfo lstObj = new CarInfo();
////                lstObj.setCarId(resultSet.getInt(1));
////                lstObj.setNetId(resultSet.getInt(2));
////                lstObj.setTypeId(resultSet.getInt(3));
////                lstObj.setLicense(resultSet.getString(4));
////                lstObj.setCarStatus(resultSet.getInt(5));
//
//
////                T t=null;
////                ParameterizedType ptype = (ParameterizedType) this.getClass().getGenericSuperclass();
////                Class clazz = (Class<T>) ptype.getActualTypeArguments()[0];
////                T o = (T) clazz.newInstance();
//
////                T type = createType();
//                T type = getInstanceNoParam(obj);
//
////                new T()
////                assert type != null;
//                putValToObj(resultSet, type);
//
//
////                lstObj.setLicense(resultSet.getString(4));
////                lstObj.setLicense(resultSet.getString(4));
////                lstObj.setAddr(resultSet.getString(4));
////                lstObj.setPhone(resultSet.getString(5));
////                lstObj.setPlanEndTime(resultSet.getTimestamp(6));
////                lstObj.setRealBeginTime(resultSet.getTimestamp(7));
////                lstObj.setRealEndTime(resultSet.getTimestamp(8));
//                list.add(type);
////				resultSet.get
////				throw new BusinessException("没找到");
////				return stepDa;
//            }
//            return list;
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new DbException(ex);
//        }
    }


    static void test9() {
        StringBuilder sqlNow = new StringBuilder("sle * where id = 1 and id =3");
        StringBuilder sqlNow2 = new StringBuilder("sle * and id = 1 and id =3");

        int whereStart = sqlNow.indexOf(" where ");
        if (whereStart != -1) {
//            有where  就不管
        } else {
            int andStart = sqlNow.indexOf(" and ");
            sqlNow.replace(andStart, andStart + 5, " where ");
        }


        whereStart = sqlNow2.indexOf(" where ");
        if (whereStart != -1) {
//            有where  就不管
        } else {
            int andStart = sqlNow2.indexOf(" and ");
            sqlNow2.replace(andStart, andStart + 5, " where ");
        }
//        可以转化
//        sqlNow = {StringBuilder@488} "sle * where id = 1 and id =3"
//        sqlNow2 = {StringBuilder@490} "sle * where id = 1 and id =3"


    }

    //    sql
//没有 where 就替换第一个 where
    public static <T> List<T> getBy(Connection conn, String sql, T obj, String addSql) throws DbException {

        return getBy(conn, sql, obj, null, addSql);
//
//        try {
//
//            StringBuilder stringBuilder = new StringBuilder(sql);
//            StringBuilder sqlNow = where(obj, stringBuilder);
////            sel  * where id= 1 and ..
////            and time <= ? and time >= ?
//            if (addSql != null) {
//                sqlNow.append("   ").append(addSql);
////            int where = sqlNow.indexOf("where");
////            sqlNow.replace(where,where+5,"and");
//
//                int whereStart = sqlNow.indexOf(" where ");
//                if (whereStart == -1) {
//
//                    int andStart = sqlNow.indexOf(" and ");
//                    if (andStart != -1) {
//                        sqlNow.replace(andStart, andStart + 5, " where ");
//                    }
////                如果没有 and ，就不用替换了  ， 也就是 没有and 也没有where
//
//                }
//                //            有where  就不管
//
//            }
//
//
////            sqlNow+=addSql;
////            String sqlStr = String.valueOf(sqlNow);
////            sqlStr += addSql;
//            PreparedStatement pst = conn.prepareStatement(String.valueOf(sqlNow));
//
//            ResultSet resultSet = pst.executeQuery();
//
//            List<T> list = new ArrayList<>();
//            while (resultSet.next()) {
//
//                T type = getInstanceNoParam(obj);
//
//                putValToObj(resultSet, type);
//
//                list.add(type);
//
//            }
//            return list;
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new DbException(ex);
//        }
    }

    //    sql
//没有 where 就替换第一个 where
    // TODO: 2021/9/10  like
    public static <T> List<T> getBy(Connection conn, String sql, T obj,
                                    T likeObj, String addSql) throws DbException {

        try {

            StringBuilder stringBuilder = new StringBuilder(sql);
            StringBuilder sqlNow = where(obj, stringBuilder);
            if (likeObj != null) {
                whereLike(likeObj, stringBuilder);
            }

//            sel  * where id= 1 and ..
//            and time <= ? and time >= ?
            if (addSql != null) {
                sqlNow.append("   ").append(addSql);
//            int where = sqlNow.indexOf("where");
//            sqlNow.replace(where,where+5,"and");

                int whereStart = sqlNow.indexOf(" where ");
                if (whereStart == -1) {

                    int andStart = sqlNow.indexOf(" and ");
                    if (andStart != -1) {
                        sqlNow.replace(andStart, andStart + 5, " where ");
                    }
//                如果没有 and ，就不用替换了  ， 也就是 没有and 也没有where

                }
                //            有where  就不管

            }


//            sqlNow+=addSql;
//            String sqlStr = String.valueOf(sqlNow);
//            sqlStr += addSql;
            PreparedStatement pst = conn.prepareStatement(String.valueOf(sqlNow));

            ResultSet resultSet = pst.executeQuery();

            List<T> list = new ArrayList<>();
            while (resultSet.next()) {

                T type = getInstanceNoParam(obj);

                putValToObj(resultSet, type);

                list.add(type);

            }
            return list;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DbException(ex);
        }
    }


    public static <T> List<T> getBy(String sql, T obj, String addSql) throws DbException, SQLException {
        Connection connection = DBUtil.getConnection();
        List<T> by = getBy(DBUtil.getConnection(), sql, obj, addSql);
        DBUtil.closeConnection(connection);
        return by;

    }

    public static <T> List<T> getBy(T obj, String addSql) throws DbException, SQLException {
        String sql = "select * from  " + getTableName(obj) + "   ";
        return getBy(sql, obj, addSql);

    }

    public static <T> List<T> getByAddSql(Connection connection, T obj, String addSql) throws DbException, SQLException {
//        String sql = "select * from  " + getTableName(obj) + "   ";
//        return getBy(sql, obj, addSql);
        String sql = "select * from  " + getTableName(obj) + "   ";
        return getBy(connection, sql, obj, addSql);


    }


    public static <T> void delete(Connection conn, T obj) throws DbException,
            IllegalAccessException, SQLException {
        delete(conn, obj, getTableName(obj));
//        USER_PHONE
    }


    public static <T> void delete(T obj) throws DbException,
            IllegalAccessException, SQLException {
        Connection conn = DBUtil.getConnection();
        delete(conn, obj, getTableName(obj));
        DBUtil.closeConnection(conn);
//        USER_PHONE
    }

    public static <T> void delete(Connection conn, T obj, String tableName) throws DbException,
            IllegalAccessException, SQLException {

        String sqlDel = "DELETE  FROM  " + tableName + "  ";

        StringBuilder stringBuilder = new StringBuilder(sqlDel);
//        表上的 null 会变成 0
        StringBuilder where = where(obj, stringBuilder);

        PreparedStatement pst = conn.prepareStatement(String.valueOf(where));
        pst.execute();
        pst.close();
//			pstByPlanName.setInt(1, stepId);
//
//        try {
//
//            PreparedStatement pst = conn.prepareStatement(sql);
//
////            填 问号
//            JdbcUtil.set(pst, obj);
//            pst.execute();
//            pst.close();
//
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            throw new DbException(ex);
//        }
    }


    public static <T> void delete(Connection conn, String sql, T obj) throws DbException {

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

//            填 问号
            JdbcUtil.set(pst, obj);
            pst.execute();
            pst.close();

        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DbException(ex);
        }
    }

    public static <T> void update(Connection conn, String sql, T obj, Integer id) throws DbException {

        try {

            PreparedStatement pst = conn.prepareStatement(sql);

            int index = set(pst, obj);
            set(pst, index, id);

//            System.out.println("pst");
//            System.out.println(pst.toString());
            pst.execute();


            pst.close();


        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DbException(ex);
        }

    }

    /**
     * 如果 约定大于配置， id 就叫做 id 的话，参数可以更少
     * 2021年9月4日19:59:54
     * 传了  conn 就不要 close , 如果在这个 函数new 出来的 就要 close
     *
     * @param conn
     * @param obj
     * @param id
     * @param idName
     * @param <T>
     * @throws DbException
     */
    public static <T> void update(Connection conn, T obj, Integer id, String idName) throws DbException {
        String sql = " UPDATE " + getTableName(obj) + "  \n" +
                "    SET\n";


        List<String> beanUnderscoreNames = getBeanUnderscoreNames(obj);
        List<String> collect = beanUnderscoreNames.stream().map(o -> o + " = ? ").collect(Collectors.toList());
        String join = String.join(" , ", collect);
        sql += join +
                "    WHERE\n" +
                "        " + idName + " = ? ";
        update(conn, sql, obj, id);
    }

    public static <T> void update(T obj, Integer id, String idName) throws SQLException, DbException {
        Connection connection = DBUtil.getConnection();

        update(connection, obj, id, idName);

        DBUtil.closeConnection(connection);
    }


    /**
     * 根据 实体 set 问号，视图 怎么办，需要别名？
     * 如果 参数都列出来 会  name= null and city =1，这样就查不出来了，所以 where 需要 不拼 null
     * 这样的话 需要实体。 实体的 null
     *
     * @param pst
     * @param object
     * @return
     * @throws SQLException
     * @throws IllegalAccessException
     */
    public static int set(PreparedStatement pst, Object object) throws SQLException, IllegalAccessException {
//        TblOrder tblOrder = new TblOrder();
//        tblOrder.setCarId(1);
        Field[] declaredFields = object.getClass().getDeclaredFields();

//        根据java类的顺序

        int index = 1;
        for (Field field : declaredFields) {
            field.setAccessible(true);

//            System.out.println("获取到字段：" + field.getType().getCanonicalName()
//                    + ",值：" + field.get(object));
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers)) {
//                if (!getStatic) {
//                    continue;
//                }
                continue;
            }
//            field.i
//判断 field 是什么类型
            String canonicalName = field.getType().getCanonicalName();
            String packaging = BeanUtil.primaryToPackaging(canonicalName);
            Object val = field.get(object);
//            if(canonicalName.)
            set(pst, index, val, packaging);
            index++;

        }
        return index;
    }

    void test3() {

//        String name = new NetInfo().getClass().getName();
        Class<? extends NetInfo> aClass = new NetInfo().getClass();
        String name = aClass.getSimpleName();
        String canonicalName = aClass.getCanonicalName();
        String typeName = aClass.getTypeName();
//        String name = new NetInfo().getClass().getName();
        String lower = StringUtils.underscoreNameLower(name);
        System.out.println("name");
        System.out.println(name);
        System.out.println("lower");
        System.out.println(lower);

//        getSimpleName
//        name
//                NetInfo
//        lower
//                net_info

//        name
//        cn.edu.zucc.personplan.model.NetInfo
//                lower
//        cn_.edu_.zucc_.personplan_.model_._net_info
    }

    public static void main(String[] args) throws IllegalAccessException, SQLException, DbException {
//        test5();
//        test9();

//        insertBatchTest();
//        insertBatchTest2();
//        insertBatchTestOrigin();
        replaceBatchTest();
    }

    void test7() throws DbException, SQLException {
        NetInfo netInfo = new NetInfo(1, "1", "1", "1", "1");
        Connection co = DBUtil.getConnection();
        insert(co, netInfo);
//        public static <T> void insert(Connection conn, T obj) throws DbException
    }

    static void test5() throws SQLException, DbException {
        NetInfo netInfo = new NetInfo(1, "1", "1", "1", "1");
        Connection conn = DBUtil.getConnection();
        update(conn, netInfo, netInfo.getNetId(), "net_id");
    }

    void test4() {
        List<String> beanUnderscoreNames = getBeanUnderscoreNames(new NetInfo());
        String collect = String.join(",", beanUnderscoreNames);
        System.out.println("collect");
        System.out.println(collect);
//        collect
//                net_id,net_name,city,addr,phone

    }

    void test2() throws IllegalAccessException {
        NetInfo netInfo = new NetInfo(1, "1", "1", "1", "1");
        StringBuilder sql = new StringBuilder("select * from ne ");
        StringBuilder where = where(netInfo, sql);
        System.out.println("where");
        System.out.println(where);
    }

    public static String q(Object object) {
        return " '" + object + "' ";
    }

    public static List<String> getBeanUnderscoreNames(Object object) {
        List<String> beanNames = getBeanNames(object);
//        beanNames.forEach( StringUtils::underscoreNameLower);
        return beanNames.stream().
                map(StringUtils::underscoreNameLower).collect(Collectors.toList());
//      .stream().  collect java
//        https://blog.csdn.net/shine_guo_star/article/details/94383319
    }

    public static List<String> getBeanNames(Object object) {
//        TblOrder tblOrder = new TblOrder();
//        tblOrder.setCarId(1);
        Field[] declaredFields = object.getClass().getDeclaredFields();


        List<String> listName = new ArrayList<>();
//        int index = 1;
//        boolean first = true;
        for (Field field : declaredFields) {
            field.setAccessible(true);

//            System.out.println("获取到字段：" + field.getType().getCanonicalName()
//                    + ",值：" + field.get(object));
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers)) {
//                if (!getStatic) {
//                    continue;
//                }
                continue;
            }
//            field.i
//判断 field 是什么类型
//            int

//            Object val = field.get(object);
//            if (val == null) {
//                continue;
//            }


//            String canonicalName = field.getType().getCanonicalName();
//            String packaging = BeanUtil.primaryToPackaging(canonicalName);

            String name = field.getName();
//            orderId== name
//            String underscoreName = StringUtils.underscoreName(name);
//            String underscoreName = StringUtils.underscoreNameLower(name);
//            listName.add(underscoreName);
            listName.add(name);
//            underscoreName  order_id
//            if (first) {
//                sql.append(" where ").append(underscoreName).append(" = ").append(q(val));
////                sql += " where "+underscoreName+" = " + q(val);
//                first = false;
//            } else {
////                sql += " and "+underscoreName+" = " + q(val);
//                sql.append(" and ").append(underscoreName).append(" = ").append(q(val));
////                sql += "and car_status = " + q(carStatus);
//            }

//            if(canonicalName.)
//            set(pst, index, val, packaging);
//            index++;

        }

//        Integer carStatus = obj.getCarStatus();
//        if (carStatus != null) {
//            if (first) {
//                sqlInsert += "where car_status = " + q(carStatus);
//                first = false;
//            } else {
//                sqlInsert += "and car_status = " + q(carStatus);
//            }
//
//        }
        return listName;
    }


    /**
     * 根据实体名字
     *
     * @param object
     * @param sql
     * @return
     * @throws IllegalAccessException
     */
    public static StringBuilder where(Object object, StringBuilder sql) throws
            IllegalAccessException {
//        TblOrder tblOrder = new TblOrder();
//        tblOrder.setCarId(1);
        Field[] declaredFields = object.getClass().getDeclaredFields();


        int index = 1;
        boolean first = true;
        for (Field field : declaredFields) {
            field.setAccessible(true);

//            System.out.println("获取到字段：" + field.getType().getCanonicalName()
//                    + ",值：" + field.get(object));
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers)) {
//                if (!getStatic) {
//                    continue;
//                }
                continue;
            }
//            field.i
//判断 field 是什么类型
//            int

            Object val = field.get(object);
            if (val == null) {
                continue;
            }
//            ""的字符串 也不搜索嘛
            if (val instanceof String) {
                if (val.equals("")) {
                    continue;
                }
            }


//            String canonicalName = field.getType().getCanonicalName();
//            String packaging = BeanUtil.primaryToPackaging(canonicalName);

            String name = field.getName();
//            orderId== name
//            String underscoreName = StringUtils.underscoreName(name);
            String underscoreName = StringUtils.underscoreNameLower(name);
//            underscoreName  order_id
            if (first) {
                sql.append(" where ").append(underscoreName).append(" = ").append(q(val));
//                sql += " where "+underscoreName+" = " + q(val);
                first = false;
            } else {
//                sql += " and "+underscoreName+" = " + q(val);
                sql.append(" and ").append(underscoreName).append(" = ").append(q(val));
//                sql += "and car_status = " + q(carStatus);
            }

//            if(canonicalName.)
//            set(pst, index, val, packaging);
            index++;

        }

//        Integer carStatus = obj.getCarStatus();
//        if (carStatus != null) {
//            if (first) {
//                sqlInsert += "where car_status = " + q(carStatus);
//                first = false;
//            } else {
//                sqlInsert += "and car_status = " + q(carStatus);
//            }
//
//        }
        return sql;
    }

    public static StringBuilder whereLike(Object object, StringBuilder sql) throws
            IllegalAccessException {
//        TblOrder tblOrder = new TblOrder();
//        tblOrder.setCarId(1);
        Field[] declaredFields = object.getClass().getDeclaredFields();


        int index = 1;
        boolean first = true;
        for (Field field : declaredFields) {
            field.setAccessible(true);

//            System.out.println("获取到字段：" + field.getType().getCanonicalName()
//                    + ",值：" + field.get(object));
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers)) {
//                if (!getStatic) {
//                    continue;
//                }
                continue;
            }
//            field.i
//判断 field 是什么类型
//            int

            Object val = field.get(object);
            if (val == null) {
                continue;
            }
//            ""的字符串 也不搜索嘛
            if (val instanceof String) {
                if (val.equals("")) {
                    continue;
                }
            }


//            String canonicalName = field.getType().getCanonicalName();
//            String packaging = BeanUtil.primaryToPackaging(canonicalName);

            String name = field.getName();
//            orderId== name
//            String underscoreName = StringUtils.underscoreName(name);
            String underscoreName = StringUtils.underscoreNameLower(name);
//            underscoreName  order_id
            if (first) {
                sql.append(" where ").append(underscoreName).append(" like ").append(l(val));
//                sql += " where "+underscoreName+" = " + q(val);
                first = false;
            } else {
//                sql += " and "+underscoreName+" = " + q(val);
                sql.append(" and ").append(underscoreName).append(" like ").append(l(val));
//                sql += "and car_status = " + q(carStatus);
            }

//            if(canonicalName.)
//            set(pst, index, val, packaging);
            index++;

        }

//        Integer carStatus = obj.getCarStatus();
//        if (carStatus != null) {
//            if (first) {
//                sqlInsert += "where car_status = " + q(carStatus);
//                first = false;
//            } else {
//                sqlInsert += "and car_status = " + q(carStatus);
//            }
//
//        }
        return sql;
    }

    static String l(Object o) {
        return " '%" + o + "%' ";
//        new FlowLayout(FlowLayout.CENTER)
    }


    public static void putValToObj(ResultSet resultSet, Object object)
            throws NoSuchMethodException, IllegalAccessException, SQLException, InvocationTargetException {
        Class<?> aClass = object.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();


        for (Field field : declaredFields) {
            field.setAccessible(true);
//           System.out.println("获取到字段：" + field.getType().getCanonicalName()
//                   + ",值：" + field.get(tblOrder));
//            String modifier = Modifier.toString(field.getModifiers());
            int modifiers = field.getModifiers();
//            System.out.println(modifier);
            if (Modifier.isStatic(modifiers)) {
                continue;
            }
            String name = field.getName();
//            name
//            orderId


//            String up = StringUtils.underlineToCamelCase(name);
//            String up = StringUtils.upperCaseFirst(name);
            String canonicalName = field.getType().getCanonicalName();
            String packaging = BeanUtil.primaryToPackaging(canonicalName);
//            Object val = field.get(object);
//            现在是往 类里面放值
//            第一个参数 是 int； set(int)
//            order_id
//            orderId
//            Method set = aClass.getDeclaredMethod("set" + up, int.class);
//            set.invoke(object, )

//            设置的值的type  packaging
            putValToObj(resultSet, packaging, name, object, aClass);

        }
    }

    void test6() {

    }


    //    int index,
    public static void putValToObj(ResultSet resultSet, String type,
                                   String fieldName, Object object, Class<?> aClass)
            throws SQLException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        if(packaging=="java.lang.Byte"){
//            resultSet.getByte()
//        }

        String underscoreName = StringUtils.underscoreName(fieldName);
        String up = StringUtils.upperCaseFirst(fieldName);
        Method set;
        switch (type) {
//            Byte 对应 mysql
            case "java.lang.Byte":
//                resultSet.getByte(index);
//                StringUtils.underlineToCamelCase()
//                String s = StringUtils.camelName(fieldName);
//                fieldName
                byte aByte = resultSet.getByte(underscoreName);
//                set = aClass.getDeclaredMethod("set" + up, byte.class);
                try {
                    set = aClass.getDeclaredMethod("set" + up, Byte.class);
                } catch (NoSuchMethodException e) {
                    set = aClass.getDeclaredMethod("set" + up, byte.class);
                }
                set.invoke(object, aByte);

//                if (val == null) {
//                    pst.setNull(index, Types.BIT);
////                pstByPlanName.se
//                } else {
////                    pst.setInt(index, val);
////                    pst.setByte(Byte(val));
//                    pst.setByte(index, (Byte) val);
////                    Byte.
////                    new Byte((Byte) val);
//                }
                break;
            case "java.lang.Short":
                short aShort = resultSet.getShort(underscoreName);
//                set = aClass.getDeclaredMethod("set" + up, short.class);
                try {
                    set = aClass.getDeclaredMethod("set" + up, Short.class);
                } catch (NoSuchMethodException e) {
                    set = aClass.getDeclaredMethod("set" + up, short.class);
                }
                set.invoke(object, aShort);
                break;
            case "java.lang.Integer":
                int anInt = resultSet.getInt(underscoreName);
                try {
                    set = aClass.getDeclaredMethod("set" + up, Integer.class);
                } catch (NoSuchMethodException e) {
                    set = aClass.getDeclaredMethod("set" + up, int.class);
                }
                set.invoke(object, anInt);
                break;
            case "java.lang.Long":
                long aLong = resultSet.getLong(underscoreName);
//                set = aClass.getDeclaredMethod("set" + up, long.class);
                try {
                    set = aClass.getDeclaredMethod("set" + up, Long.class);
                } catch (NoSuchMethodException e) {
                    set = aClass.getDeclaredMethod("set" + up, long.class);
                }
                set.invoke(object, aLong);
                break;
            case "java.lang.Float":
                float aFloat = resultSet.getFloat(underscoreName);
//                set = aClass.getDeclaredMethod("set" + up, float.class);
                try {
                    set = aClass.getDeclaredMethod("set" + up, Float.class);
                } catch (NoSuchMethodException e) {
                    set = aClass.getDeclaredMethod("set" + up, float.class);
                }
                set.invoke(object, aFloat);
                break;
            case "java.lang.Double":
                double aDouble = resultSet.getDouble(underscoreName);
//                set = aClass.getDeclaredMethod("set" + up, double.class);
                try {
                    set = aClass.getDeclaredMethod("set" + up, Double.class);
                } catch (NoSuchMethodException e) {
                    set = aClass.getDeclaredMethod("set" + up, double.class);
                }
                set.invoke(object, aDouble);
                break;
            case "java.lang.Boolean":
                boolean aBoolean = resultSet.getBoolean(underscoreName);
//                set = aClass.getDeclaredMethod("set" + up, boolean.class);
                try {
                    set = aClass.getDeclaredMethod("set" + up, Boolean.class);
                } catch (NoSuchMethodException e) {
                    set = aClass.getDeclaredMethod("set" + up, boolean.class);
                }
                set.invoke(object, aBoolean);
                break;
            case "java.lang.Character":
                String string = resultSet.getString(underscoreName);
//                set = aClass.getDeclaredMethod("set" + up, char.class);
                try {
                    set = aClass.getDeclaredMethod("set" + up, Character.class);
                } catch (NoSuchMethodException e) {
                    set = aClass.getDeclaredMethod("set" + up, char.class);
                }
                set.invoke(object, string.charAt(0));
                break;
            case "java.util.Date":
                java.util.Date date = resultSet.getTimestamp(underscoreName);
                set = aClass.getDeclaredMethod("set" + up, java.util.Date.class);
//                try {
//                    set = aClass.getDeclaredMethod("set" + up, Short.class);
//                }catch (NoSuchMethodException e){
//                    set = aClass.getDeclaredMethod("set" + up, short.class);
//                }
                set.invoke(object, date);
                break;
            case "java.math.BigDecimal":
                java.math.BigDecimal bigDecimal = resultSet.getBigDecimal(underscoreName);
                set = aClass.getDeclaredMethod("set" + up, BigDecimal.class);
//                try {
//                    set = aClass.getDeclaredMethod("set" + up, Short.class);
//                }catch (NoSuchMethodException e){
//                    set = aClass.getDeclaredMethod("set" + up, short.class);
//                }
                set.invoke(object, bigDecimal);
                break;
            case "java.lang.String":
//                String string
                String string1 = resultSet.getString(underscoreName);
                set = aClass.getDeclaredMethod("set" + up, String.class);
//                try {
//                    set = aClass.getDeclaredMethod("set" + up, Short.class);
//                }catch (NoSuchMethodException e){
//                    set = aClass.getDeclaredMethod("set" + up, short.class);
//                }
                set.invoke(object, string1);
                break;
            default:
                break;
        }
    }


    /**
     * 目前 可以用 2021年9月4日10:32:20
     *
     * @param pst
     * @param index
     * @param val
     * @param type
     * @throws SQLException
     */
    public static void set(PreparedStatement pst, int index, Object val, String type) throws SQLException {
        switch (type) {
//            Byte 对应 mysql
            case "java.lang.Byte":
                if (val == null) {
                    pst.setNull(index, Types.BIT);
//                pstByPlanName.se
                } else {
//                    pst.setInt(index, val);
//                    pst.setByte(Byte(val));
                    pst.setByte(index, (Byte) val);
//                    Byte.
//                    new Byte((Byte) val);
                }
                break;
            case "java.lang.Short":
                if (val == null) {
                    pst.setNull(index, Types.SMALLINT);
//                pstByPlanName.se
                } else {
//                    pst.setInt(index, val);
//                    pst.setByte(Byte(val));
                    pst.setShort(index, (Short) val);
//                    Byte.
//                    new Byte((Byte) val);
                }
                break;
            case "java.lang.Integer":
                if (val == null) {
                    pst.setNull(index, Types.INTEGER);
//                pstByPlanName.se
                } else {
//                    pst.setInt(index, val);
//                    pst.setByte(Byte(val));
                    pst.setInt(index, (Integer) val);
//                    Byte.
//                    new Byte((Byte) val);
                }
                break;
            case "java.lang.Long":
                if (val == null) {
                    pst.setNull(index, Types.BIGINT);
//                pstByPlanName.se
                } else {
//                    pst.setInt(index, val);
//                    pst.setByte(Byte(val));
                    pst.setLong(index, (Long) val);
//                    pst.setObject();
//                    Byte.
//                    new Byte((Byte) val);
                }
                break;
            case "java.lang.Float":
                if (val == null) {
                    pst.setNull(index, Types.FLOAT);
//                pstByPlanName.se
                } else {
//                    pst.setInt(index, val);
//                    pst.setByte(Byte(val));
                    pst.setFloat(index, (Float) val);
//                    Byte.
//                    new Byte((Byte) val);
                }
                break;
            case "java.lang.Double":
                if (val == null) {
                    pst.setNull(index, Types.DOUBLE);
//                pstByPlanName.se
                } else {
//                    pst.setInt(index, val);
//                    pst.setByte(Byte(val));
                    pst.setDouble(index, (Double) val);
//                    Byte.
//                    new Byte((Byte) val);
                }
                break;
            case "java.lang.Boolean":
                if (val == null) {
                    pst.setNull(index, Types.BOOLEAN);
//                pstByPlanName.se
                } else {
//                    pst.setInt(index, val);
//                    pst.setByte(Byte(val));
                    pst.setBoolean(index, (Boolean) val);
//                    Byte.
//                    new Byte((Byte) val);
                }
                break;
            case "java.lang.Character":
                if (val == null) {
                    pst.setNull(index, Types.CHAR);
//                pstByPlanName.se
                } else {
//                    pst.setInt(index, val);
//                    pst.setByte(Byte(val));
//                    pst.setString(index, (String) val);
                    pst.setString(index, Character.toString((Character) val));
//                    setCharacterStream jdbc
//                    Character 转化为 string
//                    new String(new Character('1'))
//                    Character.toString()
//                    Character character = new Character('1');
//                    se
//                    Byte.
//                    new Byte((Byte) val);
                }
                break;
            case "java.util.Date":
//                Date
                if (val == null) {
                    pst.setNull(index, Types.DATE);
//                pstByPlanName.se
                } else {
//                    pst.setInt(index, val);
//                    pst.setByte(Byte(val));
//                    pst.setString(index, (String) val);
                    java.util.Date date = (java.util.Date) val;
                    pst.setTimestamp(index, TimeUtil.dateToSqlTime(date));
//                    BigDecimal
//                    java.math.BigDecimal
//                    setCharacterStream jdbc
//                    Character 转化为 string
//                    new String(new Character('1'))
//                    Character.toString()
//                    Character character = new Character('1');
//                    se
//                    Byte.
//                    new Byte((Byte) val);
                }
                break;
            case "java.math.BigDecimal":
//                Date
                if (val == null) {
                    pst.setNull(index, Types.DECIMAL);
//                pstByPlanName.se
                } else {
//                    pst.setInt(index, val);
//                    pst.setByte(Byte(val));
//                    pst.setString(index, (String) val);
//                    java.util.Date date = (java.util.Date) val;
                    pst.setBigDecimal(index, (BigDecimal) val);
//                    BigDecimal
//                    java.math.BigDecimal
//                    setCharacterStream jdbc
//                    Character 转化为 string
//                    new String(new Character('1'))
//                    Character.toString()
//                    Character character = new Character('1');
//                    se
//                    Byte.
//                    new Byte((Byte) val);
                }
                break;
            case "java.lang.String":
//                Date
                if (val == null) {
                    pst.setNull(index, Types.VARCHAR);
//                pstByPlanName.se
                } else {
//                    pst.setInt(index, val);
//                    pst.setByte(Byte(val));
//                    pst.setString(index, (String) val);
//                    java.util.Date date = (java.util.Date) val;
                    pst.setString(index, (String) val);
//                    BigDecimal
//                    java.math.BigDecimal
//                    setCharacterStream jdbc
//                    Character 转化为 string
//                    new String(new Character('1'))
//                    Character.toString()
//                    Character character = new Character('1');
//                    se
//                    Byte.
//                    new Byte((Byte) val);
                }
                break;
            default:
                break;
        }
//        if (val == null) {
//            pst.setNull(index, Types.INTEGER);
////                pstByPlanName.se
//        } else {
//            pst.setInt(index, val);
//        }
    }


//    public static void set(PreparedStatement pst, int index, Object val, String type) throws SQLException {
//        switch (type) {
////            Byte 对应 mysql
//            case "java.lang.Byte":
//                if (val == null) {
//                    pst.setNull(index, Types.BIT);
////                pstByPlanName.se
//                } else {
////                    pst.setInt(index, val);
////                    pst.setByte(Byte(val));
//                    pst.setByte(index, (Byte) val);
////                    Byte.
////                    new Byte((Byte) val);
//                }
//                break;
//            case "java.lang.Short":
//                if (val == null) {
//                    pst.setNull(index, Types.SMALLINT);
////                pstByPlanName.se
//                } else {
////                    pst.setInt(index, val);
////                    pst.setByte(Byte(val));
//                    pst.setShort(index, (Short) val);
////                    Byte.
////                    new Byte((Byte) val);
//                }
//                break;
//            case "int":
//                return "java.lang.Integer";
//            case "long":
//                return "java.lang.Long";
//            case "float":
//                return "java.lang.Float";
//            case "double":
//                return "java.lang.Double";
//            case "boolean":
//                return "java.lang.Boolean";
//            case "char":
//                return "java.lang.Character";
//            default:
//                return canonicalName;
//        }
//        if (val == null) {
//            pst.setNull(index, Types.INTEGER);
////                pstByPlanName.se
//        } else {
//            pst.setInt(index, val);
//        }
//    }


    public static void set(PreparedStatement pst, int index, Integer val) throws SQLException {
        if (val == null) {
            pst.setNull(index, Types.INTEGER);
//                pstByPlanName.se
        } else {
            pst.setInt(index, val);
        }
    }

    public static void set(PreparedStatement pst, int index, String val) throws SQLException {
        if (val == null) {
            pst.setNull(index, Types.VARCHAR);
//                pstByPlanName.se
        } else {
            pst.setString(index, val);
        }
    }

    public static void set(PreparedStatement pst, int index, BigDecimal val) throws SQLException {
        if (val == null) {
            pst.setNull(index, Types.DECIMAL);
//                pstByPlanName.se
        } else {
            pst.setBigDecimal(index, val);
        }
    }

    public static void set(PreparedStatement pst, int index, Date val) throws SQLException {
        if (val == null) {
            pst.setNull(index, Types.DATE);
//                pstByPlanName.se
        } else {
            pst.setTimestamp(index, TimeUtil.dateToSqlTime(val));
        }
    }

    public static void set(PreparedStatement pst, int index, Long val) throws SQLException {
        if (val == null) {
            pst.setNull(index, Types.BIGINT);
//                pstByPlanName.se
        } else {
            pst.setLong(index, val);
        }
    }
}
