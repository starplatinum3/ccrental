
package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.tvo.CarCategoryTvo;
import cn.edu.zucc.personplan.tvo.CarTypeTvo;
import cn.edu.zucc.personplan.util.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrmAdminCarInfo extends JFrame implements ActionListener {

    private TblDataDo<CarCategory> dataTableCarCat;
    private TblDataDo<CarType> carTypeTblDataDo;
    private TblDataDo<CarInfo> tddCarInfo;
    private TblDataDo<NetInfo> tddNetInfo;
    private TblData<Scrap> tddScrap;
//    FrmAddCar  frmAddCar =new FrmAddCar();
    FrmAddCar  frmAddCar ;
    private JPanel mdpTblPanel = new JPanel();

    private JPanel toolBarPanel = new JPanel();
    private JPanel inputPanel = new JPanel();

    private JButton btnUpdate = new JButton("更新");
    private JButton btnAdd = new JButton("下面填表新增");
    private JButton btnAddRow = new JButton("新增一行");
    private JButton btnAddConfirm = new JButton("确认新增");
    private JButton btnDel = new JButton("删除");
    private JButton btnRefresh = new JButton("刷新");
    private JButton btnAddCar = new JButton("新增车");

    private JLabel labelGetTime = new JLabel("edtGetTime：");
    private JLabel labelRetTime = new JLabel("edtRetTime：");

    private JTextField edtGetTime = new JTextField(20);
    private JTextField edtRetTime = new JTextField(20);
    private JTextField edtLicense = new JTextField(20);
    //    private JTextField edtRetTime = new JTextField(20);
    //    借的时间
    private JTextField edtRentalDur = new JTextField(20);
    private JTextField edtRemark = new JTextField(20);

    JComboBox cmb = new JComboBox();    //创建JComboBox


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnUpdate) {
            System.out.println("更新");

            if (carTypeTblDataDo.current == null) {
                UiUtil.showError("请选择车类型");

                return;
            }
//            int selectedRow = dataTableCarCat.getSelectedRow();
//            if (selectedRow < 0) {
//                return;
//            }
//            if (dataTableCarCat.current == null) {
//                UiUtil.showError("请选择车大类");
//
//                return;
//            }


            if (tddCarInfo.current == null) {
                UiUtil.showError("请选择车");
                return;
            }


//            如果 信息有更新 不只是 更新  id的
//            if (carTypeTblDataDo.current.getCatId().
//                    equals(dataTableCarCat.current.getCatId())) {
//                return;
//            }

//            tddCarInfo.current.setca(carTypeTblDataDo.current.getTypeId());
//            carTypeTblDataDo.current.setCatId(dataTableCarCat.current.getCatId());
//            List<String> rowVals = UiUtil.getRowVals(carTypeTblDataDo);
//            set 会set net id 什么的额 ，先set 再用新的 覆盖
            List<String> rowVals = UiUtil.getRowVals(tddCarInfo);

//            carTypeTblDataDo.current.setVals(rowVals);
            tddCarInfo.current.setVals(rowVals);
//            current本身是有 id的
            tddCarInfo.current.setTypeId(carTypeTblDataDo.current.getTypeId());

            try {
//                JdbcUtil.update(carTypeTblDataDo.current, carTypeTblDataDo.current.getTypeId(),
//                        "type_id");

                JdbcUtil.update(tddCarInfo.current, tddCarInfo.current.getCarId(),
                        "car_id");


//                carTypeTblDataDo.reloadTable(true);
//                tddCarInfo.reloadTable(true);
                tddCarInfo.reloadDataAndRepaint(new CarInfo());

                UiUtil.showInfo("更新成功");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (DbException ex) {
                ex.printStackTrace();
            } catch (BaseException ex) {
                ex.printStackTrace();
            }


//            try {
////                UiUtil.updateBatch(dataTableCarCat);
//                UiUtil.updateRow(dataTableCarCat, selectedRow);
//
//            } catch (SQLException | BaseException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null, "更新失败\n" + ex.getMessage(),
//                        "错误", JOptionPane.ERROR_MESSAGE);
//            }


        } else if (e.getSource() == this.btnDel) {
//            System.out.println("更新");
//            UiUtil
//            try{
//                tddCarInfo.delRow();
//            }catch (MySQLIntegrityConstraintViolationException ex){
//
//            }

            if (tddCarInfo.current == null) {
                UiUtil.showError("没有选中，无法删除");
                return;
            }

            try {
                JdbcUtil.delete(tddCarInfo.current);
//            this.all.remove(this.current);
                UiUtil.showInfo("删除成功 ");
//                tddCarInfo.reloadTable(true);
                tddCarInfo.reloadDataAndRepaint(true,new CarInfo());
//            this.reloadTableNoSearch();
//            new T().toTvo()
//            null
//
//            this.reloadTableBy(true, (T) current.getClass().newInstance());
            } catch (MySQLIntegrityConstraintViolationException ex){
//                   UiUtil.showError("还有相关报废表 要去删除吗");
                //确认是否要送件
                int opt = JOptionPane.showConfirmDialog(this,
                        "还有相关报废表 要去删除吗?", "确认信息",
                        JOptionPane.YES_NO_OPTION);
                if (opt == JOptionPane.YES_OPTION) {
                    //todo 确认继续操作
                    try {
                        FrmAdminScrap frmAdminScrap = new FrmAdminScrap();
                        frmAdminScrap.setVisible(true);
                    } catch (BaseException exc) {
                        exc.printStackTrace();
                    } catch (SQLException exc) {
                        exc.printStackTrace();
                    }
                }

            }catch (DbException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (BaseException ex) {
                ex.printStackTrace();
            }



//            if (dataTableCarCat.current == null) {
//                UiUtil.showError("没有选中，无法删除");
//                return;
//            }
//
//            try {
//                JdbcUtil.delete(dataTableCarCat.current);
//                dataTableCarCat.all.remove(dataTableCarCat.current);
//                UiUtil.showInfo("删除成功 ");
//                dataTableCarCat.reloadTableNoSearch();
//            } catch (DbException ex) {
//                ex.printStackTrace();
//            } catch (IllegalAccessException ex) {
//                ex.printStackTrace();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            } catch (BaseException ex) {
//                ex.printStackTrace();
//            }

//            try {
//                UiUtil.updateBatch(dataTableCarCat);
//
//            } catch (SQLException | BaseException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null, "更新失败\n" + ex.getMessage(),
//                        "错误", JOptionPane.ERROR_MESSAGE);
//            }


        } else if (e.getSource() == this.btnAdd) {

//            frmAddCarCat.setVisible(true);
//            add();
//            Object selectedItem = cmb.getSelectedItem();
//            int selectedIndex = cmb.getSelectedIndex();
//            if (selectedIndex == -1) {
//                UiUtil.showError("请选择状态");
//                return;
//            }

            String licenseText = edtLicense.getText();
//            品牌
            if (StringUtils.isNone(licenseText)) {
                UiUtil.showError("车牌是空的");
                return;
            }
            if (tddNetInfo.current == null) {
                UiUtil.showError("请选择 网点");
                return;
            }
            if (carTypeTblDataDo.current == null) {
                UiUtil.showError("请选择 车型");
                return;
            }
            CarInfo carInfo = new CarInfo(
                    null,
                    tddNetInfo.current.getNetId(),
                    carTypeTblDataDo.current.getTypeId(),
                    licenseText,
//                    selectedIndex + 1
                    1
//                    空闲
            );
            try {
                JdbcUtil.insert(carInfo);
                tddCarInfo.reloadDataAndRepaint(true,new CarInfo());
            } catch (DbException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (BaseException ex) {
                ex.printStackTrace();
            }

//            System.out.println("selectedItem");
//            System.out.println(selectedItem);


        } else if (e.getSource() == this.btnAddRow) {
//carTypeTblDataDo

//            tddCarInfo.add
            try {
//                UiUtil.addEmptyRow(carTypeTblDataDo,CarType.class);
                UiUtil.addEmptyRow(tddCarInfo, CarInfo.class);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == this.btnAddConfirm) {
            if (carTypeTblDataDo.current == null) {
                UiUtil.showError("请选择车类型");
                return;
            }
            try {
//                UiUtil.getRowVals()
//                List<String> rowVals = UiUtil.getRowVals(dataTableCarCat);
                List<String> rowVals = UiUtil.getRowVals(tddCarInfo);
//                CarType carType = new CarType();
//                carType.setVals(rowVals);
//                carType.setCatId(dataTableCarCat.current.getCatId());

                CarInfo carInfo = new CarInfo();
                carInfo.setVals(rowVals);
                carInfo.setTypeId(carTypeTblDataDo.current.getTypeId());


                JdbcUtil.insert(carInfo);

//                dataTableCarCat.reloadTable(true);
                tddCarInfo.reloadTable(true);
//                需要从 里面拿值
//                UiUtil.getRowVals(dataTableCarCat.getSelectedRow(), (DefaultTableModel) dataTableCarCat.getModel());
//                JdbcUtil.insert(dataTableCarCat.current);
////                插入 之后 把他的值 变一下
//                UiUtil.updateRow(dataTableCarCat);
                UiUtil.showInfo("更新成功");
            } catch (DbException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (BaseException ex) {
                ex.printStackTrace();
            }
//            dataTableCarCat.adde
//            try {
//                UiUtil.addEmptyRow(dataTableCarCat,CarCategory.class);
//            } catch (IllegalAccessException ex) {
//                ex.printStackTrace();
//            } catch (InstantiationException ex) {
//                ex.printStackTrace();
//            }


        }
//
        else if (e.getSource() == this.btnRefresh) {
            try {
//                dataTableCarCat.reloadTableNoSearch(true);
//                dataTableCarCat.reloadTable(true);
//                tddCarInfo.reloadTable(true);
                tddCarInfo.reloadDataAndRepaint(new CarInfo());
                carTypeTblDataDo.reloadDataAndRepaint(new CarType());
            } catch (BaseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }else if (e.getSource() == this.btnAddCar) {
            try {
                frmAddCar =new FrmAddCar();
            } catch (BaseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            frmAddCar.setVisible(true);
//            try {
////                dataTableCarCat.reloadTableNoSearch(true);
////                dataTableCarCat.reloadTable(true);
//                tddCarInfo.reloadTable(true);
//            } catch (BaseException ex) {
//                ex.printStackTrace();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            }

        }
    }


//    String[] titles = new String[]{"汽车类型", "网点信息", "车","报废信息"};
    String[] titles = new String[]{"车", "报废信息", "车型","报废信息"};

    public FrmAdminCarInfo() throws BaseException, SQLException {

        this.setTitle("管理车");
        this.setSize(1700, 1000);
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null,
                            "删除已创建的订单失败" + e1.getMessage(),
                            "错误", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        mdpTblPanel.setLayout(new GridLayout(1, titles.length));
        // 如果这里不设置的话，商品信息表将无法展示

        Container contentPane = this.getContentPane();

        contentPane.add(this.mdpTblPanel, BorderLayout.CENTER);

        setBottom(contentPane);

        List<JButton> btnList = new ArrayList<>();

        btnList.add(this.btnUpdate);
//        btnList.add(this.btnAddRow);
//        btnList.add(this.btnAddConfirm);
        btnList.add(this.btnRefresh);
        btnList.add(this.btnDel);
        btnList.add(this.btnAdd);
        btnList.add(this.btnAddCar);


//        空闲，在途，修理报废
        cmb.addItem("--请选择--");    //向下拉列表中添加一项
        cmb.addItem("空闲");
        cmb.addItem("在途");
        cmb.addItem("报废");

        // toolBarPanel 设置
        toolBarPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        for (JButton jButton : btnList) {
            toolBarPanel.add(jButton);
            jButton.addActionListener(this);
        }

//
//        inputPanel.add(new JLabel("汽车牌子"));
        inputPanel.add(new JLabel("汽车车牌号"));
//        inputPanel.add(edtGetTime);
        inputPanel.add(edtLicense);
//
//        inputPanel.add(labelRetTime);
//        inputPanel.add(edtRetTime);
//        inputPanel.add(new JLabel("状态"));
//        inputPanel.add(cmb);
//
//
//        JLabel labelRentalDur = new JLabel("edtRentalDur：");
//        inputPanel.add(labelRentalDur);
//        inputPanel.add(edtRentalDur);
//
//        inputPanel.add(new JLabel("edtRemark："));
//        inputPanel.add(edtRemark);

//        setSize(1000,800);
        List<JTable> tblDataList = new ArrayList<>();

        this.dataTableCarCat = new TblDataDo<>(CarCategoryTvo.tableTitles, CcCarUtil.carCategoryDao);
        this.carTypeTblDataDo = new TblDataDo<>(CarTypeTvo.tableTitles, CcCarUtil.carTypeDao);
        this.tddCarInfo = new TblDataDo<>(CarInfo.tableTitles, CcCarUtil.carInfoDao);
        this.tddNetInfo = new TblDataDo<>(NetInfo.tableTitles, CcCarUtil.netInfoDao);
        this.tddScrap = new TblData<>(Scrap.tableTitles, CcCarUtil.scrapDao);

//        写在前面的就能显示
//        tblDataList.add(carTypeTblDataDo);
//        tblDataList.add(tddNetInfo);
        tblDataList.add(tddCarInfo);

        tblDataList.add(tddScrap);
        tblDataList.add(carTypeTblDataDo);

//        tblDataList.add(dataTableCarCat);

        int titleIdx = 0;

        for (JTable tblData : tblDataList) {
            JPanel panel = UiUtil.borderTable(titles[titleIdx++], tblData);
            this.mdpTblPanel.add(panel);
        }

//        dataTableCarCat.reloadTableNoSearch(true);
//        carTypeTblDataDo.reloadTableNoSearch(true);
//        carTypeTblDataDo.reloadTableBy(true,new CarType());
//        tddCarInfo.reloadTableBy(false,new CarInfo());
//        carTypeTblDataDo.reloadTable(true);
//        tddCarInfo.reloadTableNoSearch();
//        tddNetInfo.reloadTableNoSearch();

        tddCarInfo.reloadTableNoSearch(true);
//        tddNetInfo.reloadTableNoSearch(true);
//        tddNetInfo.reloadTableNoSearch();
//        tddScrap.reloadDataAndRepaint(new Scrap());
//        一开始 不显示 点击了 显示
//        carTypeTblDataDo.reloadDataAndRepaint(true,new CarType());
//        carTypeTblDataDo.reloadDataAndRepaint(true,new CarType());

//        tddCarInfo
        carTypeTblDataDo.reloadDataAndRepaint(new CarType());
        tddCarInfo.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int i = tddCarInfo.getSelectedRow();
//                这个知道是自己吗
                if (i < 0) {
                    return;
                }

                Scrap scrap = new Scrap();
                scrap.setCarId(tddCarInfo.current.getCarId());

//                NetInfo netInfo = new NetInfo();
//                netInfo.set

//                CarType carType = new CarType();
//                carType.setCatId(dataTableCarCat.current.getCatId());

                try {
                    tddScrap.reloadDataAndRepaint(scrap);
                } catch (BaseException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });
    }


    void setBottom(Container contentPane) {
        JPanel pnlBottom = new JPanel(new BorderLayout());

        pnlBottom.add(this.toolBarPanel, BorderLayout.SOUTH);

        pnlBottom.add(this.inputPanel, BorderLayout.NORTH);

        contentPane.add(pnlBottom, BorderLayout.SOUTH);
    }


    public static void main(String[] args) throws BaseException, SQLException, IllegalAccessException {

    }
}