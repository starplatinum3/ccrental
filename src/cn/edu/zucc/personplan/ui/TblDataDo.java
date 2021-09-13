package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.dao.ItDao;
import cn.edu.zucc.personplan.model.CanSetVals;
import cn.edu.zucc.personplan.model.Do;
import cn.edu.zucc.personplan.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

//public class TblData {
//}
//package cn.edu.zucc.personplan.ui;

// extends WithTitles
public class TblDataDo<T extends Do> extends JTable implements ActionListener {
    //    JTable jTable;
    //  网点 两个
    T current = null;
    private Object[] titles;
    private Object[][] tblData;
//    这个好像没被用到

    DefaultTableModel tabModel;
    //    DefaultTableModel tabModel = new DefaultTableModel();
    //    private JTable dataTable = new JTable(tabModel);
    List<T> all = null;
    //    这个 list 貌似也不用外面给
    ItDao<T> dao;
//    Preserve preserve;
//
//    void reloadTable() {
//
//    }
//, Preserve preserve


    public void delRow() {
        if (this.current == null) {
            UiUtil.showError("没有选中，无法删除");
            return;
        }

        try {
            JdbcUtil.delete(this.current);
//            this.all.remove(this.current);
            UiUtil.showInfo("删除成功 ");
            this.reloadTable(true);
//            this.reloadTableNoSearch();
//            new T().toTvo()
//            null
//
//            this.reloadTableBy(true, (T) current.getClass().newInstance());
        } catch (DbException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (BaseException ex) {
            ex.printStackTrace();
        }
//            try {
//                UiUtil.updateBatch(dataTableCarCat);
//
//            } catch (SQLException | BaseException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null, "更新失败\n" + ex.getMessage(),
//                        "错误", JOptionPane.ERROR_MESSAGE);
//            }

    }

    public void reloadTableNoSearch() throws BaseException, SQLException {
//        UiUtil.reloadTable((String[])titles,this.tabModel,
//                this, CcCarUtil.netInfoDao,new NetInfo(),all);
//        UiUtil.reloadTable((String[]) titles, this.tabModel,
//                this, this.dao, null, all, toTvo);

        UiUtil.reloadTableByList((String[]) titles, tabModel, this,
                this.dao, null, all);

    }


//    void updateCur(){
//        List<String> rowVals = UiUtil.getRowVals(this);
//
//        this.current.setVals(rowVals);
//    }

    TblDataDo(Object[] titles,
              List<T> all, ItDao<T> dao) throws BaseException, SQLException {
//        this.all=all;
//        this.dao=dao;
//        tabModel= new DefaultTableModel();
//        Object[][] objects = new Object[1][1];
        setUp(titles, null, new DefaultTableModel(), all, dao);


    }

    public TblDataDo(Object[] titles,
                     ItDao<T> dao) throws BaseException, SQLException {
//        this.all=all;
//        this.dao=dao;
//        tabModel= new DefaultTableModel();
//        Object[][] objects = new Object[1][1];
        setUp(titles, null, new DefaultTableModel(), null, dao);
//        this.all = new ArrayList<>();
//        这个有什么用处吗
        setSize(100, 100);
//        没有用处啊


    }


    //    T 应该是需要的
    TblDataDo(Object[] titles, Object[][] tblData, DefaultTableModel tabModel,
              List<T> all, ItDao<T> dao) throws BaseException, SQLException {
//        TblData(Object[] titles, Object[][] tblData, List<T> all, ItDao<T>dao) {

//            setUp(titles,tblData,all,dao);
//        有model 也没用 会跳掉
//        setUp(titles, tblData, tabModel, all, dao,preserve);
        setUp(titles, tblData, tabModel, all, dao);
//
////        titles = t.getTitles();
//       this. titles = titles;
////        一个 实体 这很奇怪
////        titles = T.getTitles();
//        this.tblData = tblData;
//        this.tabModel = tabModel;
//        this.all = all;
//        this.setModel(tabModel);
//
////        mouseClicked 可以增加 多个 awt
////        this.addMouseListener();
////        同时有两个 正常吗
//        this.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
////          FrmOrder.没有 不行
//                int i = TblData.this.getSelectedRow();
////                这个知道是自己吗
//                if (i < 0) {
//                    return;
//                }
//                current = all.get(i);
//                try {
//                    reloadTable();
//                } catch (BaseException ex) {
//                    ex.printStackTrace();
//                }
//
//                System.out.println("all.size is " + all.size());
//                System.out.println(all);
//
////                TblData.this.reloadProduct_DirTable();
//                // 每次刷新都顺便增加默认选择
////                TblData.this.currentProduct_Dir = TblData.this.allProduct_Dir.get(0);
////                TblData.this.reloadProductTable();
////                TblData.this.currentProduct = TblData.this.allProduct.get(0);
////                {
////                    // add for 商品分类
////
////                    TblData.this.currentNetInfo = TblData.this.allNetInfo.get(i);
////                    TblData.this.reloadProduct_DirTable();
////                    // 每次刷新都顺便增加默认选择
//////                    这有多余的 那就不是 可以模板的了
////                    TblData.this.currentProduct_Dir = TblData.this.allProduct_Dir.get(0);
////                    TblData.this.reloadProductTable();
////                    TblData.this.currentProduct = TblData.this.allProduct.get(0);
////                }
////                {
////                    // Debug OK
////                    System.out.println("allMerchant.size is " + this.allMerchant.size());
////                    System.out.println(this.currentMerchant);
////                }
//            }
//        });

//        this.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
////          FrmOrder.没有 不行
//                int i = TblData.this.getSelectedRow();
////                这个知道是自己吗
//                if (i < 0) {
//                    return;
//                }
//                current=all.get(i);
//                reloadTable();
//
//                System.out.println("all.size is " + all.size());
//                System.out.println(all);
//
////                TblData.this.reloadProduct_DirTable();
//                // 每次刷新都顺便增加默认选择
////                TblData.this.currentProduct_Dir = TblData.this.allProduct_Dir.get(0);
////                TblData.this.reloadProductTable();
////                TblData.this.currentProduct = TblData.this.allProduct.get(0);
////                {
////                    // add for 商品分类
////
////                    TblData.this.currentNetInfo = TblData.this.allNetInfo.get(i);
////                    TblData.this.reloadProduct_DirTable();
////                    // 每次刷新都顺便增加默认选择
//////                    这有多余的 那就不是 可以模板的了
////                    TblData.this.currentProduct_Dir = TblData.this.allProduct_Dir.get(0);
////                    TblData.this.reloadProductTable();
////                    TblData.this.currentProduct = TblData.this.allProduct.get(0);
////                }
////                {
////                    // Debug OK
////                    System.out.println("allMerchant.size is " + this.allMerchant.size());
////                    System.out.println(this.currentMerchant);
////                }
//            }
//        });
    }

//    ,Preserve preserve
//    void setUp(Object[] titles, Object[][] tblData, DefaultTableModel tabModel,
//               List<T> all, ItDao<T> dao ,Preserve preserve) {

    void setUp(Object[] titles, Object[][] tblData, DefaultTableModel tabModel,
               List<T> all, ItDao<T> dao) throws BaseException, SQLException {
        this.titles = titles;
//        一个 实体 这很奇怪
//        titles = T.getTitles();
        this.tblData = tblData;
//        this.tabModel = tabModel;
//        this.all = all;
        this.all = dao.loadAll();
        this.tabModel = tabModel;
        this.setModel(this.tabModel);
        this.dao = dao;
//        this.preserve=preserve;
//        mouseClicked 可以增加 多个 awt
//        this.addMouseListener();
//        同时有两个 正常吗
//        TblData that=this;
//        List<T> that
//        that.all.add();
//        List<T> thatList = this.all;

//        MouseAdapter.
//        this.addAncestorListener();
        this.addMouseListener(new MouseAdapter() {

            //            mouseClicked 怎么获取外面的数据
//            这是引用 只要弄到一次 以后都可以了
            @Override
            public void mouseClicked(MouseEvent e) {
//          FrmOrder.没有 不行
//                因为这个吗
//                int i = TblData.this.getSelectedRow();
                int i = getSelectedRow();
//                这个知道是自己吗
                if (i < 0) {
                    return;
                }
//                这个不是 this的 all？
//                this.
//                这里的this 不一样了

//                current = all.get(i);
//                current = (T) that.all.get(i);
//                这里要 引用一个 不然 用不了 this
//                current = thatList.get(i);
                //                这样写 不行 需要 ，这样改过 列表 之后 引用的东西不一样了

//                这样可以的
                current = TblDataDo.this.all.get(i);
//                引用 传值
//                curr 设置了 之后 列表 也变了
//                但是 还要传入 数据库
//                Object o = that.all.get(i);
//                current= (T) o;
//                try {
//                    reloadTable();
////                    是这个 reload的问题吧 。。
//                } catch (BaseException ex) {
//                    ex.printStackTrace();
//                }

//                System.out.println("all.size is " + all.size());
//                System.out.println(all);
                System.out.println("current");
                System.out.println(current);

//                TblData.this.reloadProduct_DirTable();
                // 每次刷新都顺便增加默认选择
//                TblData.this.currentProduct_Dir = TblData.this.allProduct_Dir.get(0);
//                TblData.this.reloadProductTable();
//                TblData.this.currentProduct = TblData.this.allProduct.get(0);
//                {
//                    // add for 商品分类
//
//                    TblData.this.currentNetInfo = TblData.this.allNetInfo.get(i);
//                    TblData.this.reloadProduct_DirTable();
//                    // 每次刷新都顺便增加默认选择
////                    这有多余的 那就不是 可以模板的了
//                    TblData.this.currentProduct_Dir = TblData.this.allProduct_Dir.get(0);
//                    TblData.this.reloadProductTable();
//                    TblData.this.currentProduct = TblData.this.allProduct.get(0);
//                }
//                {
//                    // Debug OK
//                    System.out.println("allMerchant.size is " + this.allMerchant.size());
//                    System.out.println(this.currentMerchant);
//                }
            }
        });

    }


    //适配器模式
    /*
    用于接收鼠标事件的抽象适配器类。 此类中的方法为空。 此类用于创建侦听器对象。
    通过鼠标事件，您可以跟踪鼠标按下，释放，单击，移动，拖动，进入组件，退出时以及移动鼠标滚轮的时间。
    https://blog.csdn.net/weixin_50569789/article/details/117898473
     */
    @Deprecated
    private class CurrentMouseListener extends MouseAdapter {
        //鼠标按下 弹起  按住不放
        @Override
        public void mousePressed(MouseEvent e) {
//            MyFrame frame = (MyFrame) e.getSource();//这里时frame来调用 获取这个资源
//            //这里我们点击的时候，就会在界面上产生一个点-->画
//            frame.addPaint(new Point(e.getX(), e.getY()));
//            //这个点就是鼠标的点
//            //每次点击鼠标 都需要重写画一遍
//            frame.repaint();//刷新
//            TblData.this.all.
        }
    }
//————————————————
//    版权声明：本文为CSDN博主「七元K」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/weixin_50569789/article/details/117898473

//    public static <T> void reloadTable(List<T> list, String[] tableTitles,
//                                       DefaultTableModel model, JTable table)

    /**
     * 只是重新加载 ，数据库 之前已经读过了 在 all 里面了 。数据
     */
    public void reloadTableByThisList() {
        UiUtil.reloadTable(all, (String[]) this.titles,
                tabModel, this);
    }

    /**
     * 会重新画
     * 2021年9月3日18:22:52  现在让他不会重新画 只是重新加载数据
     * Exception in thread "AWT-EventQueue-0" java.lang.NullPointerException 因为 all 没有初始化吧
     *
     * @throws BaseException
     */
    public void reloadTable() throws BaseException, SQLException {
//        UiUtil.reloadTable((String[])titles,this.tabModel,
//                this, CcCarUtil.netInfoDao,new NetInfo(),all);
        UiUtil.reloadTable((String[]) titles, this.tabModel,
                this, this.dao, null, all);
//        这个 时候 没有 all 吗？
        System.out.println("all");
        System.out.println(all);
//        建立一个 泛型的实体


//        try {
//            allMerchant = Bus.merchantManager.loadALL();
//        } catch (BaseException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        tblMerchantData = new Object[allMerchant.size()][BeanMerchant.tableTitles.length];
//        for (int i = 0; i < allMerchant.size(); i++) {
//            for (int j = 0; j < BeanMerchant.tableTitles.length; j++)
//                tblMerchantData[i][j] = allMerchant.get(i).getCell(j);
//        }
//        tabMerchantModel.setDataVector(tblMerchantData, tblMerchantTitle);
//        this.dataTableMerchant.validate();
//        this.dataTableMerchant.repaint();
    }


    @Deprecated
    public void reloadTable(boolean toTvo) throws BaseException, SQLException {
//        UiUtil.reloadTable((String[])titles,this.tabModel,
//                this, CcCarUtil.netInfoDao,new NetInfo(),all);
        all = dao.loadAll();
        UiUtil.reloadTable((String[]) titles, this.tabModel,
                this, this.dao, null, all, toTvo);
//        传入了 自己的all ,所以 外面可以 不 select
//        这个 时候 没有 all 吗？
        System.out.println("all");
        System.out.println(all);
//        建立一个 泛型的实体


//        try {
//            allMerchant = Bus.merchantManager.loadALL();
//        } catch (BaseException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        tblMerchantData = new Object[allMerchant.size()][BeanMerchant.tableTitles.length];
//        for (int i = 0; i < allMerchant.size(); i++) {
//            for (int j = 0; j < BeanMerchant.tableTitles.length; j++)
//                tblMerchantData[i][j] = allMerchant.get(i).getCell(j);
//        }
//        tabMerchantModel.setDataVector(tblMerchantData, tblMerchantTitle);
//        this.dataTableMerchant.validate();
//        this.dataTableMerchant.repaint();
    }


    public void reloadTableNoSearch(boolean toTvo) throws BaseException, SQLException {
//        UiUtil.reloadTable((String[])titles,this.tabModel,
//                this, CcCarUtil.netInfoDao,new NetInfo(),all);
//        UiUtil.reloadTable((String[]) titles, this.tabModel,
//                this, this.dao, null, all, toTvo);

        UiUtil.reloadTableByList((String[]) titles, tabModel, this,
                this.dao, null, all, toTvo);
//        传入了 自己的all ,所以 外面可以 不 select
//        这个 时候 没有 all 吗？
//        System.out.println("all");
//        System.out.println(all);
//        建立一个 泛型的实体


//        try {
//            allMerchant = Bus.merchantManager.loadALL();
//        } catch (BaseException e) {
//            JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        tblMerchantData = new Object[allMerchant.size()][BeanMerchant.tableTitles.length];
//        for (int i = 0; i < allMerchant.size(); i++) {
//            for (int j = 0; j < BeanMerchant.tableTitles.length; j++)
//                tblMerchantData[i][j] = allMerchant.get(i).getCell(j);
//        }
//        tabMerchantModel.setDataVector(tblMerchantData, tblMerchantTitle);
//        this.dataTableMerchant.validate();
//        this.dataTableMerchant.repaint();
    }


    //    @Deprecated
    public void reloadDataAndRepaint(boolean toTvo, T t) throws BaseException, SQLException {
//        UiUtil.reloadTable((String[])titles,this.tabModel,
//                this, CcCarUtil.netInfoDao,new NetInfo(),all);
//        UiUtil.reloadTable((String[]) titles, this.tabModel,
//                this, this.dao, null, all, toTvo);

        all = JdbcUtil.getBy(t);
//        all=by;
        reloadTableNoSearch(toTvo);
//        UiUtil.reloadTableByList((String[]) titles,      tabModel, this,
//                this.dao,null, all,  toTvo);
    }


    public void reloadDataLikeAndRepaint(boolean toTvo, T t,T tLike) throws BaseException, SQLException {

        // TODO: 2021/9/10  JdbcUtil.getBy(t);
        all = JdbcUtil.getBy(t,tLike);

        reloadTableNoSearch(toTvo);

    }

    public void reloadDataLikeAndRepaint( T t,T tLike) throws BaseException, SQLException {
        reloadDataLikeAndRepaint(true,t,tLike);
    }

    public void reloadDataAndRepaint(T t) throws BaseException, SQLException {
        reloadDataAndRepaint(true, t);

    }

//    public void reloadDataLikeAndRepaint(T t) throws BaseException, SQLException {
//        reloadDataAndRepaint(true, t);
//
//    }



    public void reloadTableBy(boolean toTvo, T t) throws BaseException, SQLException {
//        UiUtil.reloadTable((String[])titles,this.tabModel,
//                this, CcCarUtil.netInfoDao,new NetInfo(),all);
//        UiUtil.reloadTable((String[]) titles, this.tabModel,
//                this, this.dao, null, all,toTvo);

        all = JdbcUtil.getBy(t);
        UiUtil.reloadTableBy((String[]) titles, this.tabModel, this, this.dao, t, all, toTvo);
//        这个 时候 没有 all 吗？
//        System.out.println("all");
//        System.out.println(all);
//        建立一个 泛型的实体

    }

//    public void loadAllDataAndShow(boolean toTvo, T t) throws BaseException, SQLException {
//
//        UiUtil.reloadTableBy((String[]) titles, this.tabModel, this,
//                this.dao, JdbcUtil.getInstanceNoParam(t), all, toTvo);
//
//    }


//    public  <T extends CanSetVals> void updateBatch(){
//        updateBatch(this,this.dao);
//    }


    //,T t  这里可以优化 成 逗号 update 吗？ 先不管了
    public <T extends CanSetVals & Do> void updateBatch(TblDataDo<T> dataTbl, ItDao<T> dao)
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
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


    public T getCurrent() {
        return current;
    }

    public void setCurrent(T current) {
        this.current = current;
    }

    public Object[] getTitles() {
        return titles;
    }

    public void setTitles(Object[] titles) {
        this.titles = titles;
    }

    public Object[][] getTblData() {
        return tblData;
    }

    public void setTblData(Object[][] tblData) {
        this.tblData = tblData;
    }

    public DefaultTableModel getTabModel() {
        return tabModel;
    }

    public void setTabModel(DefaultTableModel tabModel) {
        this.tabModel = tabModel;
    }

    public List<T> getAll() {
        return all;
    }

    public void setAll(List<T> all) {
        this.all = all;
    }

    public ItDao<T> getDao() {
        return dao;
    }

    public void setDao(ItDao<T> dao) {
        this.dao = dao;
    }

}
