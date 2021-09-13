
package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.tvo.CarCategoryTvo;
import cn.edu.zucc.personplan.tvo.CarInfoTvo;
import cn.edu.zucc.personplan.tvo.CarTypeTvo;
import cn.edu.zucc.personplan.tvo.PromotionTvo;
import cn.edu.zucc.personplan.util.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrmAddCarType extends JFrame implements ActionListener {

    private TblDataDo<CarCategory> dataTableCarCat;
    private TblDataDo<CarType> carTypeTblDataDo;
    private TblDataDo<CarInfo> tddCarInfo;
    //    private TblDataDo<Coupon> tddCoupon;
    private TblDataDo<Promotion> tddPromo;


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

    private JButton btnSelFile = new JButton("选择图片");


    private JLabel labelGetTime = new JLabel("edtGetTime：");
    private JLabel labelRetTime = new JLabel("edtRetTime：");

    private JTextField edtGetTime = new JTextField(14);
    private JTextField edtRetTime = new JTextField(14);
    //    借的时间
    private JTextField edtRentalDur = new JTextField(14);
    private JTextField edtRemark = new JTextField(14);


//    private JLabel lblBrand = new JLabel("车牌：");
    private JLabel lblBrand = new JLabel("品牌：");
    private JLabel lblTypeName = new JLabel("类型名：");
    private JLabel lblGear = new JLabel("排挡：");
    private JLabel lblSeatNum = new JLabel("座位数：");
//    private JLabel lblTypeName = new JLabel("lblRetTime：");

    private JTextField edtBrand = new JTextField(14);
    private JTextField edtTypeName = new JTextField(14);
    private JTextField edtGear = new JTextField(14);
    private JTextField edtSeatNum = new JTextField(14);

    private JLabel lblDisplacement = new JLabel("排量：");
    private JLabel lblPrice = new JLabel("价格：");
    private JLabel lblPic = new JLabel("图片：");

    private JTextField edtDisplacement = new JTextField(14);
    private JTextField edtPrice = new JTextField(14);
    private JTextField edtPic = new JTextField(14);

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

            if (dataTableCarCat.current == null) {
                UiUtil.showError("请选择车大类");
                return;
            }


            String brandText = edtBrand.getText();
            String typeNameText = edtTypeName.getText();
            String displacementText = edtDisplacement.getText();
//            String gearText = edtGear.getText();
            String gearText="1" ;
            if(rdoAuto.isSelected()){
//                gearText="自动挡";
                gearText="1";
            }else if(rdoHand.isSelected()){
//                gearText="手动挡";
                gearText="2";
            }
            String seatNumText = edtSeatNum.getText();
            String priceText = edtPrice.getText();
            String picText = edtPic.getText();
            File file = fileDto.getFile();
            String dstFilePath;
            try {
//                dstFilePath = FileUtil.writeFileToDir(file, CcCarUtil.imgPath);
                dstFilePath=   FileUtil. copyToDir(file,CcCarUtil.imgPath);
            } catch (IOException ex) {
                ex.printStackTrace();
                dstFilePath = picText;
            }

//            String seatNumText1 = edtSeatNum.getText();
//            frmAddCarCat.setVisible(true);
//            add();
            BigDecimal displacement = new BigDecimal(displacementText);
            if (displacement.compareTo(BigDecimal.ZERO) <= 0) {
                UiUtil.showError("排量不能小于0");
                return;
            }
            int gear = Integer.parseInt(gearText);
            int seatNum = Integer.parseInt(seatNumText);
            BigDecimal price = new BigDecimal(priceText);
//            int seatNum = Integer.parseInt(priceText);
            CarType carType = new CarType(
                    null,

                    brandText,
                    dataTableCarCat.current.getCatId(),
                    typeNameText,
                    displacement,
                    gear,
                    seatNum,
                    price,
                    dstFilePath
            );
            try {
                JdbcUtil.insert(carType);
                carTypeTblDataDo.reloadDataAndRepaint(new CarType());
//                carTypeTblDataDo.reloadDataAndRepaint(new CarType());
                UiUtil.showInfo("新增成功");
            } catch (DbException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (BaseException ex) {
                ex.printStackTrace();
            }


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
            if (carTypeTblDataDo.current == null) {
                UiUtil.showError("选择的汽车类型为空 新增需要在表格里填上新的车，然后点击 确认新增");
                return;
            }
            try {

//                UiUtil.getRowVals()
//                List<String> rowVals = UiUtil.getRowVals(dataTableCarCat);
                List<String> rowVals = UiUtil.getRowVals(carTypeTblDataDo);
                CarType carType = new CarType();
                carType.setVals(rowVals);
                carType.setCatId(dataTableCarCat.current.getCatId());
                JdbcUtil.insert(carType);

//                dataTableCarCat.reloadTable(true);
//                carTypeTblDataDo.reloadTable(true);
                carTypeTblDataDo.reloadDataAndRepaint(true, new CarType());

//                需要从 里面拿值
//                UiUtil.getRowVals(dataTableCarCat.getSelectedRow(), (DefaultTableModel) dataTableCarCat.getModel());
//                JdbcUtil.insert(dataTableCarCat.current);
////                插入 之后 把他的值 变一下
//                UiUtil.updateRow(dataTableCarCat);
                UiUtil.showInfo("新增");
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

        } else if (e.getSource() == this.btnSelFile) {
            frmSelFile.setVisible(true);


        }
    }

    FileDto fileDto = new FileDto();
    FrmSelFile frmSelFile = new FrmSelFile(fileDto);
    //    String[] titles = new String[]{"汽车大类","汽车类型","车","优惠券"};
//    String[] titles = new String[]{"汽车类型", "车", "优惠券"};
    String[] titles = new String[]{"汽车类型", "车大类", "优惠券"};

    private JRadioButton rdoAuto = new JRadioButton("自动", true);
    private JRadioButton rdoHand = new JRadioButton("手动");
//    private JRadioButton jrRoot = new JRadioButton("管理员");
    private ButtonGroup btnG = new ButtonGroup();

    public FrmAddCarType() throws BaseException, SQLException {

        this.setTitle("新增车型");
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

//        btnList.add(this.btnUpdate);
        btnList.add(this.btnAdd);
        btnList.add(this.btnSelFile);
//        btnList.add(this.btnAddRow);
//        btnList.add(this.btnAddConfirm);
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

        inputPanel.add(lblBrand);
        inputPanel.add(edtBrand);
        inputPanel.add(lblTypeName);

        inputPanel.add(edtTypeName);
        inputPanel.add(lblGear);
//        inputPanel.add(edtGear);
        inputPanel.add(rdoAuto);
        inputPanel.add(rdoHand);
//        inputPanel.add(btnG);
        // TODO: 2021/9/12  edtGear
        btnG.add(rdoAuto);
        btnG.add(rdoHand);
//        btnG.add(jrRoot);
        inputPanel.add(lblSeatNum);
        inputPanel.add(edtSeatNum);
        inputPanel.add(lblDisplacement);
        inputPanel.add(edtDisplacement);
//        inputPanel.add(lblGear);
//        inputPanel.add(edtGear);
        inputPanel.add(lblPrice);
        inputPanel.add(edtPrice);

        inputPanel.add(lblPic);
        inputPanel.add(edtPic);


        List<JTable> tblDataList = new ArrayList<>();

        this.dataTableCarCat = new TblDataDo<>(CarCategoryTvo.tableTitles, CcCarUtil.carCategoryDao);
        this.carTypeTblDataDo = new TblDataDo<>(CarTypeTvo.tableTitles, CcCarUtil.carTypeDao);
        this.tddCarInfo = new TblDataDo<>(CarInfoTvo.tableTitles, CcCarUtil.carInfoDao);
        this.tddPromo = new TblDataDo<>(PromotionTvo.tableTitles, CcCarUtil.promotionDao);
//        this.tddCarInfo = new TblDataDo<>(CarInfoTvo.tableTitles, CcCarUtil.carInfoDao);

//        tblDataList.add(dataTableCarCat);
        tblDataList.add(carTypeTblDataDo);
        tblDataList.add(dataTableCarCat);
//        tblDataList.add(tddCarInfo);
//        tblDataList.add(tddPromo);

        int titleIdx = 0;

        for (JTable tblData : tblDataList) {
            JPanel panel = UiUtil.borderTable(titles[titleIdx++], tblData);
            this.mdpTblPanel.add(panel);
        }

//        dataTableCarCat.reloadTableNoSearch(true);
//        carTypeTblDataDo.reloadTableNoSearch(true);
//        dataTableCarCat.reloadTable(true);
//        carTypeTblDataDo.reloadTable(true);
        carTypeTblDataDo.reloadDataAndRepaint(new CarType());
//        tddCarInfo.reloadTable(true);
//        tddPromo.reloadTable(true);
        dataTableCarCat.reloadDataAndRepaint(new CarCategory());

        carTypeTblDataDo.addMouseListener(new MouseAdapter() {

            //            mouseClicked 怎么获取外面的数据
//            这是引用 只要弄到一次 以后都可以了
            @Override
            public void mouseClicked(MouseEvent e) {

                if (carTypeTblDataDo.current == null) {
                    return;
                }
                CarInfo carInfo1 = new CarInfo();
                carInfo1.setTypeId(carTypeTblDataDo.current.getTypeId());
                try {
                    tddCarInfo.reloadDataAndRepaint(true, carInfo1);
                    Promotion promotion = new Promotion();
                    promotion.setTypeId(carTypeTblDataDo.current.getTypeId());
                    tddPromo.reloadDataAndRepaint(true, promotion);
                } catch (BaseException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

//                carTypeTblDataDo.current

            }
        });

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