
package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.test.DisplayImageIcon;
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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrmAdminUser extends JFrame implements ActionListener {

    private TblDataDo<CarCategory> dataTableCarCat;
    private TblDataDo<CarType> carTypeTblDataDo;
    private TblDataDo<CarInfo> tddCarInfo;
    //    private TblDataDo<Coupon> tddCoupon;
    private TblDataDo<Promotion> tddPromo;
    //    private TblData<User> tddUser;
    private TblDataDo<User> tddUser;


    private JPanel mdpTblPanel = new JPanel();

    private JPanel toolBarPanel = new JPanel();
    private JPanel inputPanel = new JPanel();

    private JButton btnUpdate = new JButton("更新选中行");
    private JButton btnAdd = new JButton("新增");
    private JButton btnAddRow = new JButton("新增一行");
    private JButton btnAddConfirm = new JButton("确认新增");
    private JButton btnDel = new JButton("删除");
    private JButton btnRefresh = new JButton("刷新");
    private JButton btnSearch = new JButton("搜索");
    private JButton btnToAddCarType = new JButton("新增车类型");
    private JButton btnSelFile = new JButton("选择图片");
    private JButton btnLookPic = new JButton("查看车型图片");

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
    private JLabel lblDisplacement = new JLabel("排量：");
    private JLabel lblPrice = new JLabel("价格：");
    private JLabel lblPic = new JLabel("图片：");
    private JLabel lblName = new JLabel("名字：");
    //    private JLabel lblPic= new JLabel("图片：");
//    private JLabel lblPic= new JLabel("图片：");
//    private JLabel edtName= new JLabel(20);
    private JLabel lblPhone = new JLabel("手机");
    private JLabel lblMail = new JLabel("邮箱");
    private JLabel lblCity = new JLabel("城市");
//    private JLabel lblSeatNum = new JLabel("座位数：");
//    private JLabel lblTypeName = new JLabel("lblRetTime：");

    private JTextField edtBrand = new JTextField(20);
    private JTextField edtTypeName = new JTextField(20);
    private JTextField edtGear = new JTextField(20);
    private JTextField edtSeatNum = new JTextField(20);
    private JTextField edtDisplacement = new JTextField(20);
    private JTextField edtPrice = new JTextField(20);
    private JTextField edtPic = new JTextField(20);
    private JTextField edtName = new JTextField(20);
    private JTextField edtPhone = new JTextField(20);
    private JTextField edtMail = new JTextField(20);
    private JTextField edtCity = new JTextField(20);

    void update() {
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
//                return;
//            }

//            如果 信息有更新 不只是 更新  id的
//            if (carTypeTblDataDo.current.getCatId().
//                    equals(dataTableCarCat.current.getCatId())) {
//                return;
//            }
        List<String> rowVals = UiUtil.getRowVals(carTypeTblDataDo);

        carTypeTblDataDo.current.setVals(rowVals);
        File file = fileDto.getFile();
//            String dstFilePath;
//            if(file==null){
//                dstFilePath
//            }
        try {
//                if(file.exists()){
//                    DeleteFileUtil.deleteFile(file.getAbsolutePath());
//                }
//                dstFilePath = FileUtil.copyToDir(file, CcCarUtil.imgPath);
//                如果有新的 file 就用新的
            String name = file.getName();
            String dstFilePath = CcCarUtil.imgPath + "/" + name;
            File dst = new File(dstFilePath);
            if (dst.exists()) {
//                    DeleteFileUtil.deleteFile(dstFilePath);
                DeleteFileUtil.deleteFile(dst);
            }
            FileUtil.copy(file, dst);
            carTypeTblDataDo.current.setPic(dstFilePath);
//                if(dataTableCarCat.current!=null){
//                    carTypeTblDataDo.current.setCatId(dataTableCarCat.current.getCatId());
//                }
//                JdbcUtil.update(carTypeTblDataDo.current, carTypeTblDataDo.current.getTypeId(),
//                        "type_id");
////                carTypeTblDataDo.reloadTable(true);
//                carTypeTblDataDo.reloadDataAndRepaint(new CarType());
//
//                UiUtil.showInfo("更新成功");

        } catch (IOException ex) {
            ex.printStackTrace();
//                dstFilePath=edt
//                dstFilePath=null;
        }
//            catch (FileAlreadyExistsException ex){
////               DeleteFileUtil.deleteFile(file.getAbsolutePath());
////                try {
////                    dstFilePath = FileUtil.copyToDir(file, CcCarUtil.imgPath);
////                } catch (IOException exc) {
////                    exc.printStackTrace();
////                }
//            }


//            if(dstFilePath!=null){
//                carTypeTblDataDo.current.setPic(dstFilePath);
//            }


        if (dataTableCarCat.current != null) {
            carTypeTblDataDo.current.setCatId(dataTableCarCat.current.getCatId());
        }

        try {
            JdbcUtil.update(carTypeTblDataDo.current, carTypeTblDataDo.current.getTypeId(),
                    "type_id");
//                carTypeTblDataDo.reloadTable(true);
            carTypeTblDataDo.reloadDataAndRepaint(new CarType());

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


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnUpdate) {

            try {
                UiUtil.updateRow(tddUser);
                UiUtil.showInfo("更新成功");
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (BaseException ex) {
                ex.printStackTrace();
            }
//            update();

        } else if (e.getSource() == this.btnDel) {
//            System.out.println("更新");
            tddUser.delRow();
//            if (carTypeTblDataDo.current == null) {
//                UiUtil.showError("没有选中，无法删除");
//                return;
//            }
//
//            try {
////                JdbcUtil.delete(dataTableCarCat.current);
//                JdbcUtil.delete(carTypeTblDataDo.current);
////                dataTableCarCat.all.remove(carTypeTblDataDo.current);
////                dataTableCarCat.reloadTableNoSearch();
//                carTypeTblDataDo.reloadDataAndRepaint(true, new CarType());
//                UiUtil.showInfo("删除成功 ");
//
//            } catch (MySQLIntegrityConstraintViolationException ex) {
//                UiUtil.showError("不能删除  还有相关的 显示促销 或者 车子 ");
//            } catch (DbException ex) {
//                ex.printStackTrace();
//            } catch (IllegalAccessException ex) {
//                ex.printStackTrace();
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            } catch (BaseException ex) {
//                ex.printStackTrace();
//            }
//

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
//                carTypeTblDataDo.reloadDataAndRepaint(true, new CarType());
//                carTypeTblDataDo.reloadTable(true);
                tddUser.reloadTable();
            } catch (BaseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == this.btnSearch) {

//            search();
//            String brandText = edtBrand.getText();
//            String gearText = edtGear.getText();
//            String seatNumText = edtSeatNum.getText();
//            String typeNameText = edtTypeName.getText();
//            if(StringUtils.isNone())

            String nameText = edtName.getText();
            String cityText = edtCity.getText();
            String mailText = edtMail.getText();
            String phoneText = edtPhone.getText();
//            String nameText = edtName.getText();
//            if()
//            注册一定要邮箱吗
            User user = new User();
            user.setUserName(nameText);
            user.setCity(cityText);


            if(StringUtils.isNone(mailText) ){
                user.setMailbox(mailText);
            }else if(StringUtils.isEmail(mailText)){
                user.setMailbox(mailText);
            }

            if(StringUtils.isNone(phoneText)|| StringUtils.isPhone(phoneText)){
                user.setPhone(phoneText);
            }

//            edtSeatNum
//            CarType carType = new CarType();
//            carType.setBrand(brandText);
//            类型名 是空的 是不会去 搜索的
//            try {
//                carType.setGear(Integer.valueOf(gearText));
//                carType.setSeatNum(Integer.valueOf(seatNumText));
//            } catch (NumberFormatException ignored) {
//
//            }
//            try{
//
//
//
//            }catch (NumberFormatException ignored){
//
//            }
//            carType.setTypeName(typeNameText);
            try {
//                dataTableCarCat.reloadTableNoSearch(true);
//                dataTableCarCat.reloadTable(true);
//                carTypeTblDataDo.reloadDataAndRepaint(true, carType);
//                carTypeTblDataDo.reloadTable(true);
                tddUser.reloadDataLikeAndRepaint(new User(),user);
            } catch (BaseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == this.btnToAddCarType) {
            frmAddCarType.setVisible(true);
        } else if (e.getSource() == this.btnSelFile) {
            frmSelFile.setVisible(true);
        } else if (e.getSource() == this.btnLookPic) {
            lookPic();
//            frmSelFile.setVisible(true);
        }
    }

    void search() {

        String brandText = edtBrand.getText();
        String gearText = edtGear.getText();
        String seatNumText = edtSeatNum.getText();
        String typeNameText = edtTypeName.getText();
//            if(StringUtils.isNone())

//        String nameText = edtName.getText();
//        String cityText = edtCity.getText();
//        String mailText = edtMail.getText();
//        String phoneText = edtPhone.getText();
//            String nameText = edtName.getText();

//        User user = new User();
//        user.setUserName(nameText);
//        user.setCity(cityText);
//        user.setMailbox(mailText);
//        user.setPhone(phoneText);
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

    void lookPic() {

//            frmUserOrderStatus.setVisible(true);
        if (carTypeTblDataDo.current == null) {
            UiUtil.showError("请选择车型");
            return;
        }

        Integer typeId = carTypeTblDataDo.current.getTypeId();
        CarType carType = new CarType();
        carType.setTypeId(typeId);
        try {
            List<CarType> by = JdbcUtil.getBy(carType);
            if (by.size() <= 0) {
                UiUtil.showError("没有这种车型");
                return;
            }
            DisplayImageIcon displayImageIcon = new DisplayImageIcon(by.get(0).getPic());
            displayImageIcon.setVisible(true);
        } catch (DbException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    FrmAddCarType frmAddCarType = new FrmAddCarType();

    //    String[] titles = new String[]{"汽车大类","汽车类型","车","优惠券"};
//    String[] titles = new String[]{"汽车类型", "车", "优惠券"};
    String[] titles = new String[]{"用户", "车", "优惠券"};

    FileDto fileDto = new FileDto();
    FrmSelFile frmSelFile = new FrmSelFile(fileDto);

    public FrmAdminUser() throws BaseException, SQLException {

        this.setTitle("查看车类别");
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
        btnList.add(this.btnSearch);
//        btnList.add(this.btnToAddCarType);
//        btnList.add(this.btnSelFile);
//        btnList.add(this.btnLookPic);


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
//        inputPanel.add(edtTypeName);
//        inputPanel.add(lblGear);
//        inputPanel.add(edtGear);
//        inputPanel.add(lblSeatNum);
//        inputPanel.add(edtSeatNum);
//
//        inputPanel.add(lblDisplacement);
//        inputPanel.add(edtDisplacement);
//        inputPanel.add(lblGear);
//        inputPanel.add(edtGear);
//        inputPanel.add(lblPic);
//        inputPanel.add(edtPic);
//

        inputPanel.add(lblName);
        inputPanel.add(edtName);
        inputPanel.add(lblCity);
        inputPanel.add(edtCity);
        inputPanel.add(lblMail);
        inputPanel.add(edtMail);
        inputPanel.add(lblPhone);
        inputPanel.add(edtPhone);

        List<JTable> tblDataList = new ArrayList<>();

        this.dataTableCarCat = new TblDataDo<>(CarCategoryTvo.tableTitles, CcCarUtil.carCategoryDao);
        this.carTypeTblDataDo = new TblDataDo<>(CarTypeTvo.tableTitles, CcCarUtil.carTypeDao);
        this.tddCarInfo = new TblDataDo<>(CarInfoTvo.tableTitles, CcCarUtil.carInfoDao);
        this.tddPromo = new TblDataDo<>(PromotionTvo.tableTitles, CcCarUtil.promotionDao);
        this.tddUser = new TblDataDo<>(User.tableTitles, CcCarUtil.userDao);
//        this.tddCarInfo = new TblDataDo<>(CarInfoTvo.tableTitles, CcCarUtil.carInfoDao);

//        tblDataList.add(dataTableCarCat);
//        tblDataList.add(carTypeTblDataDo);
//        tblDataList.add(tddCarInfo);
//        tblDataList.add(tddPromo);
        tblDataList.add(tddUser);

        int titleIdx = 0;

        for (JTable tblData : tblDataList) {
            JPanel panel = UiUtil.borderTable(titles[titleIdx++], tblData);
            this.mdpTblPanel.add(panel);
        }

//        dataTableCarCat.reloadTableNoSearch(true);
//        carTypeTblDataDo.reloadTableNoSearch(true);
//        dataTableCarCat.reloadTable(true);
//        carTypeTblDataDo.reloadTable(true);
//        tddCarInfo.reloadTable(true);
//        tddPromo.reloadTable(true);
//        tddUser.reloadTable();
        tddUser.reloadDataAndRepaint(new User());
//        carTypeTblDataDo.addMouseListener(new MouseAdapter() {
//
//            //            mouseClicked 怎么获取外面的数据
////            这是引用 只要弄到一次 以后都可以了
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//                if (carTypeTblDataDo.current == null) {
//                    return;
//                }
//                CarInfo carInfo1 = new CarInfo();
//                carInfo1.setTypeId(carTypeTblDataDo.current.getTypeId());
//                try {
//                    tddCarInfo.reloadDataAndRepaint(true, carInfo1);
//                    Promotion promotion = new Promotion();
//                    promotion.setTypeId(carTypeTblDataDo.current.getTypeId());
//                    tddPromo.reloadDataAndRepaint(true, promotion);
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
//       String lblTxt= "填入数据可以搜索，也可以用这个数据新增";
        String lblTxt = "";
        JLabel jLabelTip = new JLabel(lblTxt);
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