package cn.edu.zucc.personplan.util;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.dao.ItDao;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.svo.Svo;
import cn.edu.zucc.personplan.ui.TblData;
import cn.edu.zucc.personplan.ui.TblDataDo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class UiUtil {


    public static JPanel borderTable(String title, JTable table) {
        JPanel border = new JPanel(new BorderLayout());
        border.setBorder(BorderFactory.createTitledBorder(title));
        border.add(new JScrollPane(table));
        border.setPreferredSize(new Dimension(256, 0));
        return border;
    }


    public static void showFrame(String title, Component component, int x) {
//        JFrame frame = new JFrame(title);
//        frame.getContentPane().add(component);
//        frame.setSize(400, 500);
//        frame.setLocation(x, 300);//在屏幕中设置显示的位置
////      300 大概 中间  上下
//
////        x+=410;
//        frame.setVisible(true);

        showFrame(title, component, x, 300);
    }


    public static void showFrame(String title, Component component, int x, int y) {
        JFrame frame = new JFrame(title);
        frame.getContentPane().add(component);
        frame.setSize(400, 500);
        frame.setLocation(x, y);//在屏幕中设置显示的位置
//      300 大概 中间  上下

//        x+=410;
        frame.setVisible(true);
    }


    public static JFrame createFrame(String title, Component component, int x, int y) {
        JFrame frame = new JFrame(title);
        frame.getContentPane().add(component);
        frame.setSize(400, 500);
        frame.setLocation(x, y);//在屏幕中设置显示的位置
//      300 大概 中间  上下

//        x+=410;
//        frame.setVisible(true);
        return frame;
    }

    public static JFrame createShowTable(String title, Component component, int x, int y) {
        return createFrame(title, new JScrollPane(component), x, y);

    }


    public static void showTable(String title, Component component, int x) {
        showFrame(title, new JScrollPane(component), x);

    }

    public static void showTable(String title, Component component, int x, int y) {
        showFrame(title, new JScrollPane(component), x, y);

    }

    public static List<String> getRowVals(int row, DefaultTableModel model) {
        int columnCount = model.getColumnCount();
        List<String> vals = new ArrayList<>();
        for (int col = 0; col < columnCount; col++) {
//            Vector dataVector = model.getDataVector();
            String valueAt = (String) model.getValueAt(row, col);
            vals.add(valueAt);
        }
        return vals;

    }

    public static<T> List<String> getRowVals(TblData<T>tblData) {
      return   getRowVals(tblData.getSelectedRow(),tblData.getTabModel());
//        int columnCount = model.getColumnCount();
//        List<String> vals = new ArrayList<>();
//        for (int col = 0; col < columnCount; col++) {
////            Vector dataVector = model.getDataVector();
//            String valueAt = (String) model.getValueAt(row, col);
//            vals.add(valueAt);
//        }
//        return vals;

    }


    public static<T extends Do> List<String> getRowVals(TblDataDo<T>tblData) {
        return   getRowVals(tblData.getSelectedRow(),tblData.getTabModel());


    }

    //    因为 这个不是这个类吗
//    TblData
//JTable table
    public static List<List<String>> getTableVals(TblData table) {
//        TableModel model1 = table.getModel();
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
        DefaultTableModel model = table.getTabModel();
        int rowCount = model.getRowCount();

        List<List<String>> tableVals = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            List<String> rowVals = getRowVals(i, model);
            tableVals.add(rowVals);
        }
        return tableVals;
    }


    public static List<List<String>> getTableVals(DefaultTableModel model) {
//        TableModel model1 = table.getModel();
//        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        DefaultTableModel model = table.getTabModel();
        int rowCount = model.getRowCount();

        List<List<String>> tableVals = new ArrayList<>();
        for (int i = 0; i < rowCount; i++) {
            List<String> rowVals = getRowVals(i, model);
            tableVals.add(rowVals);
        }
        return tableVals;
    }


    public static <T extends Cellable> Object[][] getTblData(List<T> all, Object[] titles) {
        //        这几句 可以分离开来
        Object[][] tblData = new Object[all.size()][titles.length];
        for (int i = 0; i < all.size(); i++) {
            for (int j = 0; j < titles.length; j++)
                tblData[i][j] = all.get(i).getCell(j);
        }
        return tblData;
    }

//    public static <T> void addEmptyRow(TblData<T> tblData, Class<?> aClass) throws IllegalAccessException, InstantiationException {
//        addEmptyRow(tblData);
//        tblData.getAll().add(aClass.newInstance());
//    }

//    public  static   void updateList(JTable table,List list) {
//        List<List<Object>> tableVals = getTableVals(table);
//        for (List<Object> tableVal : tableVals) {
//
//        }
//    }

    void dd() {
        List<List<Object>> tableVals = new ArrayList<>();
        for (List<Object> tableVal : tableVals) {
//            obj = listToBean(tableVal)
        }
    }


    /**
     * 把 数据放在表上 然后展示
     * 还是让他会重新画出来吧
     * 这不是 tvo
     *
     * @param list
     * @param tableTitles
     * @param model       是有设置 东西的
     * @param table
     * @param <T>
     */
    public static <T> void reloadTable(List<T> list, String[] tableTitles,
                                       DefaultTableModel model, JTable table) {
        //这是测试数据，需要用实际数替换
        try {
//			一个list
//            allPlan = PersonPlanUtil.planManager.loadAll();
//            NetInfo netInfo = new NetInfo();
////            netInfo.setNetId(1);
//            List<NetInfo> by = CcCarUtil.netInfoDao.getBy(netInfo);
//            System.out.println("by");
//            System.out.println(by);
//            全部 拿出来
//			System.out.println("allPlan");
//			System.out.println(allPlan);
//            Object[][] tblData = BeanUtil.getTblData(list, NetInfo.tableTitles.length);
//            list 一直是最新的吧  但是 改了表格就不是了
            Object[][] tblData = BeanUtil.getTblData(list, tableTitles.length);
//            model.setDataVector(tblData, NetInfo.tableTitles);
            model.setDataVector(tblData, tableTitles);
            table.validate();
            table.repaint();

        } catch (IllegalAccessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "错误", JOptionPane.ERROR_MESSAGE);
//            return;
        }

//        BeanUtil.getTblData(allPlan,BeanPlan.tableTitles.length);
//        tblPlanData = new Object[allPlan.size()][BeanPlan.tableTitles.length];
//        for (int i = 0; i < allPlan.size(); i++) {
//            for (int j = 0; j < BeanPlan.tableTitles.length; j++)
//                tblPlanData[i][j] = allPlan.get(i).getCell(j);
////            里面是 str
//        }
////		tblPlanTitle obj 列表
//        tabPlanModel.setDataVector(tblPlanData, tblPlanTitle);
//        this.dataTablePlan.validate();
//        this.dataTablePlan.repaint();
    }


    //    不一定需要 Svo吧
    public static <T extends Do> void reloadTable(List<T> list, String[] tableTitles,
                                                  DefaultTableModel model, JTable table, boolean toTvo) {
        //这是测试数据，需要用实际数替换
        try {
//			一个list
//            allPlan = PersonPlanUtil.planManager.loadAll();
//            NetInfo netInfo = new NetInfo();
////            netInfo.setNetId(1);
//            List<NetInfo> by = CcCarUtil.netInfoDao.getBy(netInfo);
//            System.out.println("by");
//            System.out.println(by);
//            全部 拿出来
//			System.out.println("allPlan");
//			System.out.println(allPlan);
//            Object[][] tblData = BeanUtil.getTblData(list, NetInfo.tableTitles.length);
            Object[][] tblData = BeanUtil.getTblData(list, tableTitles.length, toTvo);
//            model.setDataVector(tblData, NetInfo.tableTitles);
            model.setDataVector(tblData, tableTitles);
            table.validate();
            table.repaint();

        } catch (IllegalAccessException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(),
                    "错误", JOptionPane.ERROR_MESSAGE);
//            return;
        }

//        BeanUtil.getTblData(allPlan,BeanPlan.tableTitles.length);
//        tblPlanData = new Object[allPlan.size()][BeanPlan.tableTitles.length];
//        for (int i = 0; i < allPlan.size(); i++) {
//            for (int j = 0; j < BeanPlan.tableTitles.length; j++)
//                tblPlanData[i][j] = allPlan.get(i).getCell(j);
////            里面是 str
//        }
////		tblPlanTitle obj 列表
//        tabPlanModel.setDataVector(tblPlanData, tblPlanTitle);
//        this.dataTablePlan.validate();
//        this.dataTablePlan.repaint();
    }


    //,T t  这里可以优化 成 逗号 update 吗？ 先不管了
    public static <T extends CanSetVals & Do> void updateBatchDo(TblData<T> dataTbl, ItDao<T> dao)
            throws SQLException, BaseException {

        Connection connection = null;
        connection = DBUtil.getConnection();
//        try {
//            connection = DBUtil.getConnection();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "创建连接失败\n" + ex.getMessage(),
//                    "错误", JOptionPane.ERROR_MESSAGE);
//        }

        List<List<String>> tableVals = UiUtil.getTableVals(dataTbl.getTabModel());
//            System.out.println("tableVals");
//            System.out.println(tableVals);
        int idx = 0;
        List<T> list = dataTbl.getAll();
//            System.out.println("list");
//            System.out.println(list);
        for (List<String> tableVal : tableVals) {
//            NetInfo netInfo = new NetInfo(tableVal);
//            T t=new T();
//            T 构造函数
//            new T()
//            T t;
//            T new java
//            t.setVals(tableVal);

//            ParameterizedType ptype = (ParameterizedType) t.getClass().getGenericSuperclass();
//            Class clazz = (Class<T>) ptype.getActualTypeArguments()[0];
//            T o = (T) clazz.newInstance();
//            t.setVals(tableVal);
//            id 不需要赋值吧
            T t = list.get(idx);
            t.setVals(tableVal);
//            万一个数不一样呢？
//            list.get(idx)  .setVals(tableVal);
//            t.setVals(tableVal);
//                System.out.println("netInfo");
//                System.out.println(netInfo);
//            list.set(idx++, t);
            idx++;
//            不行 这里会set
//            外面 new 出来 一个临时的 没事吧
            dao.update(connection, t);
//            try {
////                CcCarUtil.netInfoDao.update(connection,t);
//                dao.update(connection, t);
//            } catch (BaseException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null,
//                        "更新失败\n" + ex.getMessage(),
//                        "错误", JOptionPane.ERROR_MESSAGE);
//            }
//                NetInfo netInfo = this.netInfoTblData.all.get(idx++);

//            new NetInfo(tableVal);
        }
//        System.out.println( "this.netInfoTblData.all");
//        System.out.println( this.netInfoTblData.all);
//            System.out.println(list);
    }


    //,T t  这里可以优化 成 逗号 update 吗？ 先不管了
//    如果 表格 里的东西 不是全的 ，那怎么改
//    里面的 do 是全的 但是 改的数据 在 表格上
//    这样 还是不要转化为别的 对象 就自己对象 getcell 其实更好啊
//    车的类型
//     列表更新了，然后写入数据库
    public static <T extends CanSetVals> void updateBatch(TblData<T> dataTbl, ItDao<T> dao)
            throws SQLException, BaseException {

        Connection connection = null;
        connection = DBUtil.getConnection();
//        try {
//            connection = DBUtil.getConnection();
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//            JOptionPane.showMessageDialog(null, "创建连接失败\n" + ex.getMessage(),
//                    "错误", JOptionPane.ERROR_MESSAGE);
//        }

        List<List<String>> tableVals = UiUtil.getTableVals(dataTbl.getTabModel());
//            System.out.println("tableVals");
//            System.out.println(tableVals);
        int idx = 0;
        List<T> list = dataTbl.getAll();
//            System.out.println("list");
//            System.out.println(list);
        for (List<String> tableVal : tableVals) {
//            NetInfo netInfo = new NetInfo(tableVal);
//            T t=new T();
//            T 构造函数
//            new T()
//            T t;
//            T new java
//            t.setVals(tableVal);

//            ParameterizedType ptype = (ParameterizedType) t.getClass().getGenericSuperclass();
//            Class clazz = (Class<T>) ptype.getActualTypeArguments()[0];
//            T o = (T) clazz.newInstance();
//            t.setVals(tableVal);
            T t = list.get(idx);
            t.setVals(tableVal);
//            万一个数不一样呢？
//            list.get(idx)  .setVals(tableVal);
//            t.setVals(tableVal);
//                System.out.println("netInfo");
//                System.out.println(netInfo);
//            list.set(idx++, t);
            idx++;
//            不行 这里会set
//            外面 new 出来 一个临时的 没事吧
            dao.update(connection, t);
//            try {
////                CcCarUtil.netInfoDao.update(connection,t);
//                dao.update(connection, t);
//            } catch (BaseException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null,
//                        "更新失败\n" + ex.getMessage(),
//                        "错误", JOptionPane.ERROR_MESSAGE);
//            }
//                NetInfo netInfo = this.netInfoTblData.all.get(idx++);

//            new NetInfo(tableVal);
        }
//        System.out.println( "this.netInfoTblData.all");
//        System.out.println( this.netInfoTblData.all);
//            System.out.println(list);
        DBUtil.closeConnection(connection);
//        之前可能是因为 没有关闭连接 就死机了
//        但是之前好像是搜索的时候
    }


    public static <T extends CanSetVals & Do> void updateBatch(TblDataDo<T> dataTbl,
                                                               ItDao<T> dao)
            throws SQLException, BaseException {

        Connection connection = null;
        connection = DBUtil.getConnection();

        List<List<String>> tableVals = UiUtil.getTableVals(dataTbl.getTabModel());

        int idx = 0;
        List<T> list = dataTbl.getAll();

        for (List<String> tableVal : tableVals) {

            T t = list.get(idx);
            t.setVals(tableVal);

            idx++;
//            不行 这里会set
//            外面 new 出来 一个临时的 没事吧
            dao.update(connection, t);
//            JdbcUtil.update(connection,t);

        }
//        System.out.println( "this.netInfoTblData.all");
//        System.out.println( this.netInfoTblData.all);
//            System.out.println(list);
        DBUtil.closeConnection(connection);
//        之前可能是因为 没有关闭连接 就死机了
//        但是之前好像是搜索的时候
    }


    public static <T extends CanSetVals & Do> void updateRow(TblDataDo<T> dataTbl,
                                                              int row)
            throws SQLException, BaseException {

        Connection  connection = DBUtil.getConnection();

//        List<List<String>> tableVals = UiUtil.getTableVals(dataTbl.getTabModel());

        List<String> rowVals = UiUtil.getRowVals(row, dataTbl.getTabModel());
        T t = dataTbl.getAll().get(row);

        t.setVals(rowVals);
//        dao.update(connection, t);
        dataTbl.getDao().update(connection,t);


//        int idx = 0;
//        List<T> list = dataTbl.getAll();
//
//        for (List<String> tableVal : tableVals) {
//
//            T t = list.get(idx);
//            t.setVals(tableVal);
//
//            idx++;
////            不行 这里会set
////            外面 new 出来 一个临时的 没事吧
//            dao.update(connection, t);
////            JdbcUtil.update(connection,t);
//
//        }
//        System.out.println( "this.netInfoTblData.all");
//        System.out.println( this.netInfoTblData.all);
//            System.out.println(list);
        DBUtil.closeConnection(connection);
//        之前可能是因为 没有关闭连接 就死机了
//        但是之前好像是搜索的时候
    }



    public static <T extends CanSetVals> void updateRow(TblData<T> dataTbl,
                                                             int row)
            throws SQLException, BaseException {

        Connection  connection = DBUtil.getConnection();

//        List<List<String>> tableVals = UiUtil.getTableVals(dataTbl.getTabModel());

        List<String> rowVals = UiUtil.getRowVals(row, dataTbl.getTabModel());
        T t = dataTbl.getAll().get(row);

        t.setVals(rowVals);
//        dao.update(connection, t);
        dataTbl.getDao().update(connection,t);
//        JdbcUtil.up
//        由于不知道要update的类的id字段名字，所以用不了jdbcUtil的update
//        于是用 table里的dao，这个dao其实也是用了JdbcUtil，就是每个类都需要实现一个update

        DBUtil.closeConnection(connection);
//        之前可能是因为 没有关闭连接 就死机了
//        但是之前好像是搜索的时候
    }


    public static <T extends CanSetVals> void updateRow(TblData<T> dataTbl
                                                       )
            throws SQLException, BaseException {

        updateRow(dataTbl,dataTbl.getSelectedRow());

    }




    public static <T extends CanSetVals & Do> void updateRow(TblDataDo<T> dataTbl
                                                             )
            throws SQLException, BaseException {
        updateRow(dataTbl,dataTbl.getSelectedRow());

    }


    //,T t  这里可以优化 成 逗号 update 吗？ 先不管了
    public static <T extends CanSetVals> void updateBatch(TblData<T> dataTbl)
            throws SQLException, BaseException {

        updateBatch(dataTbl, dataTbl.getDao());

    }

    public static <T extends CanSetVals & Do> void updateBatch(TblDataDo<T> dataTbl)
            throws SQLException, BaseException {

        updateBatch(dataTbl, dataTbl.getDao());

    }




    public static void showError(String error) {
        JOptionPane.showMessageDialog(null, error,
                "错误", JOptionPane.ERROR_MESSAGE);
    }

    public static void showInfo(String info) {
        JOptionPane.showMessageDialog(null, info,
                "提示", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 这里会帮忙报错 主要 try ca 比较难看、、、
     *
     * @return
     */
    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DBUtil.getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
            showError("数据库连接失败");

        }
        return connection;
    }


    /**
     * 会吧数据 写入list
     *
     * @param tableTitles
     * @param model
     * @param table
     * @param dao
     * @param t
     * @param list
     * @param <T>
     * @throws BaseException
     */
    public static <T> void reloadTable(String[] tableTitles,
                                       DefaultTableModel model, JTable table,
                                       ItDao<T> dao, T t, List<T> list) throws BaseException, SQLException {
        //这是测试数据，需要用实际数替换

        //			一个list
//            allPlan = PersonPlanUtil.planManager.loadAll();
//            NetInfo netInfo = new NetInfo();
//            netInfo.setNetId(1);
//        List<T> by = dao.getBy(t);
//        list = dao.getBy(t);
//        list = dao.loadAll();
//        all 好像没有更新
//        全部 生成
        UiUtil.reloadTable(list, tableTitles,
                model, table);

//			一个list
//            allPlan = PersonPlanUtil.planManager.loadAll();
//            NetInfo netInfo = new NetInfo();
////            netInfo.setNetId(1);
//            List<NetInfo> by = CcCarUtil.netInfoDao.getBy(netInfo);
//            System.out.println("by");
//            System.out.println(by);
//            全部 拿出来
//			System.out.println("allPlan");
//			System.out.println(allPlan);
//            Object[][] tblData = BeanUtil.getTblData(list, NetInfo.tableTitles.length);
//            Object[][] tblData = BeanUtil.getTblData(list, tableTitles.length);
//            model.setDataVector(tblData, NetInfo.tableTitles);
//            table.validate();
//            table.repaint();

        //        BeanUtil.getTblData(allPlan,BeanPlan.tableTitles.length);
//        tblPlanData = new Object[allPlan.size()][BeanPlan.tableTitles.length];
//        for (int i = 0; i < allPlan.size(); i++) {
//            for (int j = 0; j < BeanPlan.tableTitles.length; j++)
//                tblPlanData[i][j] = allPlan.get(i).getCell(j);
////            里面是 str
//        }
////		tblPlanTitle obj 列表
//        tabPlanModel.setDataVector(tblPlanData, tblPlanTitle);
//        this.dataTablePlan.validate();
//        this.dataTablePlan.repaint();
    }

    //    <T extends Do
    public static <T extends Do> void reloadTable(String[] tableTitles,
                                                  DefaultTableModel model, JTable table,
                                                  ItDao<T> dao, T t, List<T> list, boolean toTvo)
            throws BaseException,
            SQLException {
        //这是测试数据，需要用实际数替换

        //			一个list
//            allPlan = PersonPlanUtil.planManager.loadAll();
//            NetInfo netInfo = new NetInfo();
//            netInfo.setNetId(1);
//        List<T> by = dao.getBy(t);
//        list = dao.getBy(t);

//        list = dao.loadAll();

//        all 好像没有更新
//        全部 生成
        UiUtil.reloadTable(list, tableTitles,
                model, table, toTvo);

//			一个list
//            allPlan = PersonPlanUtil.planManager.loadAll();
//            NetInfo netInfo = new NetInfo();
////            netInfo.setNetId(1);
//            List<NetInfo> by = CcCarUtil.netInfoDao.getBy(netInfo);
//            System.out.println("by");
//            System.out.println(by);
//            全部 拿出来
//			System.out.println("allPlan");
//			System.out.println(allPlan);
//            Object[][] tblData = BeanUtil.getTblData(list, NetInfo.tableTitles.length);
//            Object[][] tblData = BeanUtil.getTblData(list, tableTitles.length);
//            model.setDataVector(tblData, NetInfo.tableTitles);
//            table.validate();
//            table.repaint();

        //        BeanUtil.getTblData(allPlan,BeanPlan.tableTitles.length);
//        tblPlanData = new Object[allPlan.size()][BeanPlan.tableTitles.length];
//        for (int i = 0; i < allPlan.size(); i++) {
//            for (int j = 0; j < BeanPlan.tableTitles.length; j++)
//                tblPlanData[i][j] = allPlan.get(i).getCell(j);
////            里面是 str
//        }
////		tblPlanTitle obj 列表
//        tabPlanModel.setDataVector(tblPlanData, tblPlanTitle);
//        this.dataTablePlan.validate();
//        this.dataTablePlan.repaint();
    }

    //    @Deprecated
    //    <T extends Do
    public static <T extends Do> void reloadTableByList(String[] tableTitles,
                                                        DefaultTableModel model, JTable table,
                                                        ItDao<T> dao, T t, List<T> list, boolean toTvo)
            throws BaseException,
            SQLException {
        //这是测试数据，需要用实际数替换

        //			一个list
//            allPlan = PersonPlanUtil.planManager.loadAll();
//            NetInfo netInfo = new NetInfo();
//            netInfo.setNetId(1);
//        List<T> by = dao.getBy(t);
//        list = dao.getBy(t);
//        list = dao.loadAll();
//        all 好像没有更新
//        全部 生成
        UiUtil.reloadTable(list, tableTitles, model, table, toTvo);

    }

    public static <T> void reloadTableByList(String[] tableTitles,
                                             DefaultTableModel model, JTable table,
                                             ItDao<T> dao, T t, List<T> list)
            throws BaseException,
            SQLException {
        //这是测试数据，需要用实际数替换

        //			一个list
//            allPlan = PersonPlanUtil.planManager.loadAll();
//            NetInfo netInfo = new NetInfo();
//            netInfo.setNetId(1);
//        List<T> by = dao.getBy(t);
//        list = dao.getBy(t);
//        list = dao.loadAll();
//        all 好像没有更新
//        全部 生成
        UiUtil.reloadTable(list, tableTitles, model, table);
//        public static <T> void reloadTable(List<T> list, String[] tableTitles,
//                DefaultTableModel model, JTable table) {
    }


    public static void addEmptyRow(JTable table) {
        int count = table.getColumnCount();
        Object[] data = new Object[count];
        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
        defaultTableModel.addRow(data);
    }

    public static<T> void addEmptyRow(TblData<T> table,Class<T> clz) throws IllegalAccessException, InstantiationException {
//        int count = table.getColumnCount();
//        Object[] data = new Object[count];
//        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
//        defaultTableModel.addRow(data);
        addEmptyRow(table);
        table.getAll().add(clz.newInstance());
    }

    public  static <T extends  CanSetVals> void addConfirm(TblData<T> table,T t){
        try {
//                UiUtil.getRowVals()
//                List<String> rowVals = UiUtil.getRowVals(dataTableCarCat);
            List<String> rowVals = UiUtil.getRowVals(table);
//            T t=JdbcUtil.getInstanceNoParam(t)
//            CarType carType = new CarType();
            t.setVals(rowVals);
//            carType.setVals(rowVals);
//            carType.setCatId(table.current.getCatId());
//            如果还需要别的表 就不行
            JdbcUtil.insert(t);

//                dataTableCarCat.reloadTable(true);
//            table.reloadTable(true);
            table.reloadTable();
//            carTypeTblDataDo.reloadTable(true);
//                需要从 里面拿值
//                UiUtil.getRowVals(dataTableCarCat.getSelectedRow(), (DefaultTableModel) dataTableCarCat.getModel());
//                JdbcUtil.insert(dataTableCarCat.current);
////                插入 之后 把他的值 变一下
//                UiUtil.updateRow(dataTableCarCat);
            UiUtil.showInfo("更新成功");
        } catch (SQLException | BaseException ex) {
            UiUtil.showError("更新失败  "+ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static<T> void addEmptyRow(TblData<T> table,T t) throws IllegalAccessException, InstantiationException {
//        int count = table.getColumnCount();
//        Object[] data = new Object[count];
//        DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
//        defaultTableModel.addRow(data);
        addEmptyRow(table);
        table.getAll().add(JdbcUtil.getInstanceNoParam(t));
    }

    public static<T extends Do> void addEmptyRow(TblDataDo<T> table, Class<T> clz)
            throws IllegalAccessException,
            InstantiationException {

        addEmptyRow(table);
        table.getAll().add(clz.newInstance());
    }

    public static<T extends Do> void addEmptyRow(TblDataDo<T> table, T t)
            throws IllegalAccessException,
            InstantiationException {

        addEmptyRow(table);
//        table.getAll().add(t.getClass().newInstance());
        table.getAll().add(JdbcUtil.getInstanceNoParam(t));
    }


//    public static<T > void addEmptyRow(TblData<T> table, Class<T> clz)
//            throws IllegalAccessException,
//            InstantiationException {
//
//        addEmptyRow(table);
////        table.getAll().add(t.getClass().newInstance());
////        table.getAll().add(JdbcUtil.getInstanceNoParam(t));
//        table.getAll().add(clz.newInstance());
//    }


    //    <T extends Do
    public static <T extends Do> void reloadTableBy(String[] tableTitles,
                                                    DefaultTableModel model, JTable table,
                                                    ItDao<T> dao, T t, List<T> list, boolean toTvo)
            throws BaseException,
            SQLException {

//        list = dao.getBy(t);
//        这个list 是新的 ,所以 没有赋值
//        list = dao.loadAll();
//        all 好像没有更新
//        全部 生成
//        if()
        UiUtil.reloadTable(list, tableTitles, model, table, toTvo);

    }


}
