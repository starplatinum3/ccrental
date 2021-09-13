
package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.tvo.CarCategoryTvo;
import cn.edu.zucc.personplan.tvo.CarInfoTvo;
import cn.edu.zucc.personplan.tvo.CarTypeTvo;
import cn.edu.zucc.personplan.tvo.PromotionTvo;
import cn.edu.zucc.personplan.util.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import sun.util.resources.cldr.teo.CalendarData_teo_UG;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrmAddCar extends JFrame implements ActionListener {

    private TblDataDo<CarCategory> dataTableCarCat;
    private TblDataDo<CarType> carTypeTblDataDo;
    private TblDataDo<CarInfo> tddCarInfo;
    //    private TblDataDo<Coupon> tddCoupon;
    private TblDataDo<Promotion> tddPromo;
    private TblDataDo<NetInfo> tddNetInfo;
//    private TblDataDo<NetInfo> tddNetInfo;


    private JPanel mdpTblPanel = new JPanel();

    private JPanel toolBarPanel = new JPanel();
    private JPanel inputPanel = new JPanel();

    private JButton btnUpdate = new JButton("更新");
    private JButton btnAdd = new JButton("新增");
    private JButton btnAddRow = new JButton("新增一行");
    private JButton btnAddConfirm = new JButton("确认新增");
    private JButton btnDel = new JButton("删除");
    private JButton btnRefresh = new JButton("刷新");
    private JButton btnSearch = new JButton("搜索");

    private JLabel labelGetTime = new JLabel("edtGetTime：");
    private JLabel labelRetTime = new JLabel("edtRetTime：");

    private JTextField edtGetTime = new JTextField(20);
    private JTextField edtRetTime = new JTextField(20);
    //    借的时间
    private JTextField edtRentalDur = new JTextField(20);
    private JTextField edtRemark = new JTextField(20);


    private JLabel lblBrand = new JLabel("车牌：");
    private JLabel lblTypeName = new JLabel("类型名：");
    private JLabel lblGear = new JLabel("排挡：");
    private JLabel lblSeatNum = new JLabel("座位数：");
    private JLabel lblLicense = new JLabel("车牌：");
//    private JLabel lblTypeName = new JLabel("lblRetTime：");

    private JTextField edtBrand = new JTextField(20);
    private JTextField edtLicense = new JTextField(20);
    private JTextField edtTypeName = new JTextField(20);
    private JTextField edtGear = new JTextField(20);
    private JTextField edtSeatNum = new JTextField(20);


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnUpdate) {
            System.out.println("更新");

            if (carTypeTblDataDo.current == null) {
                return;
            }
//            int selectedRow = dataTableCarCat.getSelectedRow();
//            if (selectedRow < 0) {
//                return;
//            }
            if (dataTableCarCat.current == null) {
                return;
            }

//            如果 信息有更新 不只是 更新  id的
//            if (carTypeTblDataDo.current.getCatId().
//                    equals(dataTableCarCat.current.getCatId())) {
//                return;
//            }
            carTypeTblDataDo.current.setCatId(dataTableCarCat.current.getCatId());
            List<String> rowVals = UiUtil.getRowVals(carTypeTblDataDo);

            carTypeTblDataDo.current.setVals(rowVals);

            try {
                JdbcUtil.update(carTypeTblDataDo.current, carTypeTblDataDo.current.getTypeId(),
                        "type_id");
                carTypeTblDataDo.reloadTable(true);

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
            if (carTypeTblDataDo.current == null) {
                UiUtil.showError("没有选中，无法删除");
                return;
            }

            try {
//                JdbcUtil.delete(dataTableCarCat.current);
                JdbcUtil.delete(carTypeTblDataDo.current);
//                dataTableCarCat.all.remove(carTypeTblDataDo.current);
//                dataTableCarCat.reloadTableNoSearch();
                carTypeTblDataDo.reloadDataAndRepaint(true, new CarType());
                UiUtil.showInfo("删除成功 ");

            } catch (MySQLIntegrityConstraintViolationException ex) {
                UiUtil.showError("不能删除  还有相关的 显示促销 或者 车子 ");
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


        } else if (e.getSource() == this.btnAdd) {

//            frmAddCarCat.setVisible(true);
//            add();

//一个 form  form 新增了
        } else if (e.getSource() == this.btnAddRow) {
//carTypeTblDataDo
            try {
                UiUtil.addEmptyRow(carTypeTblDataDo, CarType.class);
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (InstantiationException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == this.btnAddConfirm) {

//            edtBrand
            String license = edtLicense.getText();
            if (StringUtils.isNone(license)) {
                UiUtil.showError("请输入车牌");
                return;
            }
            if (carTypeTblDataDo.current == null) {
                UiUtil.showError("请选择车型");
                return;
            }
            if (tddNetInfo.current == null) {
                UiUtil.showError("请选择网点");
                return;
            }
            CarInfo carInfo = new CarInfo(
                    null,
                    tddNetInfo.current.getNetId(),
                    carTypeTblDataDo.current.getTypeId(),
                    license,
                    1
            );
//            空闲
//            carInfo.setNetId(tddNetInfo.current.getNetId());
//            carInfo.setTypeId(carTypeTblDataDo.current.getTypeId());
//            carInfo.
            try {
//                UiUtil.getRowVals()
//                List<String> rowVals = UiUtil.getRowVals(dataTableCarCat);
//                List<String> rowVals = UiUtil.getRowVals(carTypeTblDataDo);
//                CarType carType = new CarType();
//                carType.setVals(rowVals);
//                carType.setCatId(dataTableCarCat.current.getCatId());
//                JdbcUtil.insert(carType);
                CarInfo carInfo1 = new CarInfo();
                carInfo1.setLicense(license);
                List<CarInfo> by = JdbcUtil.getBy(carInfo1);
                if (by.size() >= 1) {
                    UiUtil.showError("车牌重复了");
                    return;
                }
                JdbcUtil.insert(carInfo);

//                dataTableCarCat.reloadTable(true);
//                carTypeTblDataDo.reloadTable(true);
//                carTypeTblDataDo.reloadDataAndRepaint(true, new CarType());
                tddCarInfo.reloadDataAndRepaint(true, new CarInfo());

//                需要从 里面拿值
//                UiUtil.getRowVals(dataTableCarCat.getSelectedRow(), (DefaultTableModel) dataTableCarCat.getModel());
//                JdbcUtil.insert(dataTableCarCat.current);
////                插入 之后 把他的值 变一下
//                UiUtil.updateRow(dataTableCarCat);
//                UiUtil.showInfo("更新成功");
                UiUtil.showInfo("增加成功");
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
                carTypeTblDataDo.reloadDataAndRepaint(true, new CarType());
//                carTypeTblDataDo.reloadTable(true);
            } catch (BaseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == this.btnSearch) {

            String brandText = edtBrand.getText();
            String gearText = edtGear.getText();
            String seatNumText = edtSeatNum.getText();
            String typeNameText = edtTypeName.getText();
//            if(StringUtils.isNone())

//            edtSeatNum
            CarType carType = new CarType();
            carType.setBrand(brandText);
//            类型名 是空的 是不会去 搜索的
            try {
                carType.setGear(Integer.valueOf(gearText));
                carType.setSeatNum(Integer.valueOf(seatNumText));
            } catch (NumberFormatException ignored) {

            }
//            try{
//
//
//
//            }catch (NumberFormatException ignored){
//
//            }
            carType.setTypeName(typeNameText);
            try {
//                dataTableCarCat.reloadTableNoSearch(true);
//                dataTableCarCat.reloadTable(true);
                carTypeTblDataDo.reloadDataAndRepaint(true, carType);
//                carTypeTblDataDo.reloadTable(true);
            } catch (BaseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }


    //    String[] titles = new String[]{"汽车大类","汽车类型","车","优惠券"};
//    String[] titles = new String[]{"汽车类型","车","优惠券"};
    String[] titles = new String[]{"车", "网点信息", "车型"};

    public FrmAddCar() throws BaseException, SQLException {

        this.setTitle("新增车");
        this.setSize(1700, 1000);
        this.setLocationRelativeTo(null);


        mdpTblPanel.setLayout(new GridLayout(1, titles.length));
        // 如果这里不设置的话，商品信息表将无法展示

        Container contentPane = this.getContentPane();

        contentPane.add(this.mdpTblPanel, BorderLayout.CENTER);

        setBottom(contentPane);

        List<JButton> btnList = new ArrayList<>();

//        btnList.add(this.btnUpdate);
//        btnList.add(this.btnAddRow);
        btnList.add(this.btnAddConfirm);
//        btnList.add(this.btnRefresh);
//        btnList.add(this.btnDel);
//        btnList.add(this.btnSearch);


        // toolBarPanel 设置
        toolBarPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        for (JButton jButton : btnList) {
            toolBarPanel.add(jButton);
            jButton.addActionListener(this);
        }


//        inputPanel.add(labelGetTime);
//        inputPanel.add(edtGetTime);
//
//        inputPanel.add(labelRetTime);
//        inputPanel.add(edtRetTime);
//
//
//        JLabel labelRentalDur = new JLabel("edtRentalDur：");
//        inputPanel.add(labelRentalDur);
//        inputPanel.add(edtRentalDur);
//
//        inputPanel.add(new JLabel("edtRemark："));
//        inputPanel.add(edtRemark);

//        inputPanel.add(lblBrand);
//        inputPanel.add(edtBrand);
//        inputPanel.add(lblTypeName);
//
//        inputPanel.add(edtTypeName);
//        inputPanel.add(lblGear);
//        inputPanel.add(edtGear);
//        inputPanel.add(lblSeatNum);
//        inputPanel.add(edtSeatNum);


        inputPanel.add(lblLicense);
        inputPanel.add(edtLicense);

        List<JTable> tblDataList = new ArrayList<>();

        this.dataTableCarCat = new TblDataDo<>(CarCategoryTvo.tableTitles, CcCarUtil.carCategoryDao);
        this.carTypeTblDataDo = new TblDataDo<>(CarTypeTvo.tableTitles, CcCarUtil.carTypeDao);
        this.tddCarInfo = new TblDataDo<>(CarInfoTvo.tableTitles, CcCarUtil.carInfoDao);
        this.tddPromo = new TblDataDo<>(PromotionTvo.tableTitles, CcCarUtil.promotionDao);
        this.tddNetInfo = new TblDataDo<>(NetInfo.tableTitles, CcCarUtil.netInfoDao);
//        this.tddCarInfo = new TblDataDo<>(CarInfoTvo.tableTitles, CcCarUtil.carInfoDao);

//        tblDataList.add(dataTableCarCat);
        tblDataList.add(tddCarInfo);
//        tblDataList.add(tddPromo);

        tblDataList.add(tddNetInfo);
        tblDataList.add(carTypeTblDataDo);


        int titleIdx = 0;

        for (JTable tblData : tblDataList) {
            JPanel panel = UiUtil.borderTable(titles[titleIdx++], tblData);
            this.mdpTblPanel.add(panel);
        }

//        dataTableCarCat.reloadTableNoSearch(true);
//        carTypeTblDataDo.reloadTableNoSearch(true);
//        dataTableCarCat.reloadTable(true);

//        tddCarInfo.reloadTable(true);
        tddCarInfo.reloadDataAndRepaint(new CarInfo());
//        tddPromo.reloadTable(true);
//        carTypeTblDataDo.reloadTable(true);
        carTypeTblDataDo.reloadDataAndRepaint(new CarType());
        tddNetInfo.reloadDataAndRepaint(false, new NetInfo());
//        tddNetInfo.re(new NetInfo());
//        carTypeTblDataDo.addMouseListener(new MouseAdapter() {
//
//            //            mouseClicked 怎么获取外面的数据
////            这是引用 只要弄到一次 以后都可以了
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//                if(carTypeTblDataDo.current==null){
//                    return;
//                }
//                CarInfo carInfo1 = new CarInfo();
//                carInfo1.setTypeId( carTypeTblDataDo.current.getTypeId());
//                try {
//                    tddCarInfo.reloadDataAndRepaint(true,carInfo1);
//                    Promotion promotion = new Promotion();
//                    promotion.setTypeId(carTypeTblDataDo.current.getTypeId());
//                    tddPromo.reloadDataAndRepaint(true,promotion);
//                } catch (BaseException ex) {
//                    ex.printStackTrace();
//                } catch (SQLException ex) {
//                    ex.printStackTrace();
//                }
//
////                carTypeTblDataDo.current
//
//            }
//        });

    }


    void setBottom(Container contentPane) {
        JPanel pnlBottom = new JPanel(new BorderLayout());

        pnlBottom.add(this.toolBarPanel, BorderLayout.SOUTH);

        pnlBottom.add(this.inputPanel, BorderLayout.CENTER);
        JLabel jLabelTip = new JLabel("填入数据可以搜索，也可以用这个数据新增");
        JPanel pnlTip = new JPanel();
        pnlTip.setLayout(new FlowLayout(FlowLayout.CENTER));
        pnlTip.add(jLabelTip);
//        new JLabel("填入数据可以搜索，也可以用这个数据新增")
        pnlBottom.add(pnlTip, BorderLayout.NORTH);

        contentPane.add(pnlBottom, BorderLayout.SOUTH);
    }


    public static void main(String[] args) throws BaseException, SQLException, IllegalAccessException {

    }
}