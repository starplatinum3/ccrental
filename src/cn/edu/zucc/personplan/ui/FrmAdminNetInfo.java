
package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.tvo.CarCategoryTvo;
import cn.edu.zucc.personplan.tvo.CarInfoTvo;
import cn.edu.zucc.personplan.tvo.OrderLittleTvo;
import cn.edu.zucc.personplan.util.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrmAdminNetInfo extends JFrame implements ActionListener {

    private TblDataDo<CarCategory> dataTableCarCat;
    private TblData<NetInfo> tddNetInfo;
    //    所有的 表 只有一个 可以吗
    private TblDataDo<CarInfo> tddCarInfo;
    private TblDataDo<TblOrder> tddOrder;
    private TblDataDo<Promotion> tddPromotion;
    private TblData<Emp> tdEmp;
    private TblData<Coupon> tdCoupon;
    private TblData<Allocation> tdAllocation;
//    private TblData<NetInfo> tddNetInfo;

    private JPanel mdpTblPanel = new JPanel();

    private JPanel toolBarPanel = new JPanel();
    private JPanel inputPanel = new JPanel();

    private JButton btnUpdate = new JButton("更新网点信息");
    private JButton btnAdd = new JButton("新增（通过下面填入）");
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

    private JLabel lblNetName = new JLabel("网点名字");
    private JLabel lblCity = new JLabel("网点城市");
    private JLabel lblAddr = new JLabel("网点地址");
    private JLabel lblPhone = new JLabel("网点电话");


    private JTextField edtNetName = new JTextField(20);
    private JTextField edtCity = new JTextField(20);
    private JTextField edtAddr = new JTextField(20);
    private JTextField edtPhone = new JTextField(20);


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnUpdate) {
            System.out.println("更新");

            try {
//                UiUtil.updateBatch(dataTableCarCat);
                UiUtil.updateBatch(tddNetInfo);

            } catch (SQLException | BaseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "更新失败\n" + ex.getMessage(),
                        "错误", JOptionPane.ERROR_MESSAGE);
            }


        } else if (e.getSource() == this.btnDel) {
//            tddNetInfo.delRow();

            if (tddNetInfo.current == null) {
                UiUtil.showError("没有选中，无法删除");
                return;
            }

            try {
                JdbcUtil.delete(this.tddNetInfo.current);
//                tddNetInfo.all.remove(this.tddNetInfo);
                tddNetInfo.reloadTable();
                UiUtil.showInfo("删除成功 ");
//                this.reloadTableNoSearch();
            } catch (MySQLIntegrityConstraintViolationException ex) {
//                   UiUtil.showError("还有相关报废表 要去删除吗");
                //确认是否要送件
//                怎么判断 有什么 表
                if (tddCarInfo.all != null && tddCarInfo.all.size() > 0) {
                    int opt = JOptionPane.showConfirmDialog(this,
                            "还有相关汽车  要去删除吗?", "确认信息",
                            JOptionPane.YES_NO_OPTION);
                    if (opt == JOptionPane.YES_OPTION) {
                        //todo 确认继续操作
                        try {
                            FrmAdminCarInfo frmAdminCarInfo = new FrmAdminCarInfo();
                            frmAdminCarInfo.setVisible(true);

//                            FrmAdminScrap frmAdminScrap = new FrmAdminScrap();
//                            frmAdminScrap.setVisible(true);
                        } catch (BaseException exc) {
                            exc.printStackTrace();
                        } catch (SQLException exc) {
                            exc.printStackTrace();
                        }
                    }
                }
//                这样 两个 都会跳出来
//                int opt = JOptionPane.showConfirmDialog(this,
//                        "还有相关报废表 要去删除吗?", "确认信息",
//                        JOptionPane.YES_NO_OPTION);
//                if (opt == JOptionPane.YES_OPTION) {
//                    //todo 确认继续操作
//                    try {
//                        FrmAdminScrap frmAdminScrap = new FrmAdminScrap();
//                        frmAdminScrap.setVisible(true);
//                    } catch (BaseException exc) {
//                        exc.printStackTrace();
//                    } catch (SQLException exc) {
//                        exc.printStackTrace();
//                    }
//                }

            } catch (DbException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (BaseException ex) {
                ex.printStackTrace();
            }


//
////            System.out.println("更新");
//          if (dataTableCarCat.current == null) {
//              UiUtil.showError("没有选中，无法删除");
//              return;
//          }
//
//          try {
//              JdbcUtil.delete(dataTableCarCat.current);
//              dataTableCarCat.all.remove(dataTableCarCat.current);
//              UiUtil.showInfo("删除成功 ");
//              dataTableCarCat.reloadTableNoSearch();
//          } catch (DbException ex) {
//              ex.printStackTrace();
//          } catch (IllegalAccessException ex) {
//              ex.printStackTrace();
//          } catch (SQLException ex) {
//              ex.printStackTrace();
//          } catch (BaseException ex) {
//              ex.printStackTrace();
//          }


//            try {
//                UiUtil.updateBatch(dataTableCarCat);
//
//            } catch (SQLException | BaseException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null, "更新失败\n" + ex.getMessage(),
//                        "错误", JOptionPane.ERROR_MESSAGE);
//            }


        } else if (e.getSource() == this.btnAdd) {

            String netNameText = edtNetName.getText();
            String cityText = edtCity.getText();
            String addrText = edtAddr.getText();
            String phoneText = edtPhone.getText();
            if (StringUtils.isNone(netNameText)) {
                UiUtil.showError("请设置网点名称");
                return;
            }
            NetInfo netInfo = new NetInfo(
                    null,
                    netNameText,
                    cityText,
                    addrText,
                    phoneText
            );
            try {
                JdbcUtil.insert(netInfo);
                tddNetInfo.reloadDataAndRepaint(new NetInfo());
                UiUtil.showInfo("新增成功");
            } catch (DbException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (BaseException ex) {
                ex.printStackTrace();
            }
//            frmAddCarCat.setVisible(true);
//            add();


        } else if (e.getSource() == this.btnAddRow) {
//carTypeTblDataDo
            tddNetInfo.addEmptyRowPushObj();
//          try {
//              UiUtil.addEmptyRow(carTypeTblDataDo, CarType.class);
//          } catch (IllegalAccessException ex) {
//              ex.printStackTrace();
//          } catch (InstantiationException ex) {
//              ex.printStackTrace();
//          }
        } else if (e.getSource() == this.btnAddConfirm) {
//          tddNetInfo.addColumn();
//          这个 每种情况不一样 单表的可以封装
//          add 这一行的  选中的
//            tddNetInfo.current.
            UiUtil.addConfirm(tddNetInfo, new NetInfo());

//
//          try {
////                UiUtil.getRowVals()
////                List<String> rowVals = UiUtil.getRowVals(dataTableCarCat);
//              List<String> rowVals = UiUtil.getRowVals(carTypeTblDataDo);
//              CarType carType = new CarType();
//              carType.setVals(rowVals);
//              carType.setCatId(dataTableCarCat.current.getCatId());
//              JdbcUtil.insert(carType);
//
////                dataTableCarCat.reloadTable(true);
//              carTypeTblDataDo.reloadTable(true);
////                需要从 里面拿值
////                UiUtil.getRowVals(dataTableCarCat.getSelectedRow(), (DefaultTableModel) dataTableCarCat.getModel());
////                JdbcUtil.insert(dataTableCarCat.current);
//////                插入 之后 把他的值 变一下
////                UiUtil.updateRow(dataTableCarCat);
//              UiUtil.showInfo("更新成功");
//          } catch (DbException ex) {
//              ex.printStackTrace();
//          } catch (SQLException ex) {
//              ex.printStackTrace();
//          } catch (BaseException ex) {
//              ex.printStackTrace();
//          }


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
//              carTypeTblDataDo.reloadTable(true);
//              tddNetInfo.reloadTable(true);
                tddNetInfo.reloadTable();
            } catch (BaseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == this.btnSearch) {
            // TODO: 2021/9/9   btnSearch
            String addrText = edtAddr.getText();
            String cityText = edtCity.getText();
            String phoneText = edtPhone.getText();
            String netNameText = edtNetName.getText();
            NetInfo netInfo = new NetInfo();
            netInfo.setAddr(addrText);
            netInfo.setCity(cityText);
            netInfo.setPhone(phoneText);
            netInfo.setNetName(netNameText);
            try {

//                tddNetInfo.reloadTable();
                tddNetInfo.reloadDataAndRepaint(netInfo);
            } catch (BaseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        }
    }


    String[] titles = new String[]{"网点信息", "车子信息", "订单信息", "员工", "调度", "优惠券", "", "", ""};

    public FrmAdminNetInfo() throws BaseException, SQLException {

        this.setTitle("管理网点信息");
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
//        btnList.add(this.btnAdd);
        btnList.add(this.btnAddRow);
        btnList.add(this.btnAdd);

        btnList.add(this.btnAddConfirm);
        btnList.add(this.btnDel);
        btnList.add(this.btnRefresh);
        btnList.add(this.btnSearch);


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


        inputPanel.add(lblNetName);
        inputPanel.add(edtNetName);

        inputPanel.add(lblAddr);
        inputPanel.add(edtAddr);

        inputPanel.add(lblCity);
        inputPanel.add(edtCity);

        inputPanel.add(lblPhone);
        inputPanel.add(edtPhone);

//        JLabel labelRentalDur = new JLabel("edtRentalDur：");
//        inputPanel.add(labelRentalDur);
//        inputPanel.add(edtRentalDur);
//
//        inputPanel.add(new JLabel("edtRemark："));
//        inputPanel.add(edtRemark);

        List<JTable> tblDataList = new ArrayList<>();

//        this.dataTableCarCat = new TblDataDo<>(CarCategoryTvo.tableTitles, CcCarUtil.carCategoryDao);
//       新增 一个网点 是没事 的 ，但是 删除 很麻烦
        this.tddNetInfo = new TblData<>(NetInfo.tableTitles, CcCarUtil.netInfoDao);
        this.tddCarInfo = new TblDataDo<>(CarInfoTvo.tableTitles, CcCarUtil.carInfoDao);
//        this.tddOrder = new TblDataDo<>(TblOrder.tableTitles, CcCarUtil.tblOrderDao);
        this.tddOrder = new TblDataDo<>(OrderLittleTvo.tableTitles, CcCarUtil.tblOrderDao);
        this.tdEmp = new TblData<>(Emp.tableTitles, CcCarUtil.empDao);
        this.tdAllocation = new TblData<>(Allocation.tableTitles, CcCarUtil.allocationDao);
        this.tdCoupon = new TblData<>(Coupon.tableTitles, CcCarUtil.couponDao);

//        tblDataList.add(dataTableCarCat);
        tblDataList.add(tddNetInfo);
        tblDataList.add(tddCarInfo);
        tblDataList.add(tddOrder);
        tblDataList.add(tdEmp);
        tblDataList.add(tdAllocation);
        tblDataList.add(tdCoupon);

        int titleIdx = 0;

        for (JTable tblData : tblDataList) {
            JPanel panel = UiUtil.borderTable(titles[titleIdx++], tblData);
            this.mdpTblPanel.add(panel);
        }

//        dataTableCarCat.reloadTableNoSearch(true);
//        tddNetInfo.reloadTableNoSearch(true);
//        tddNetInfo.all
//        tddNetInfo.reloadTableNoSearch();
        tddNetInfo.reloadTable();
        this.tddCarInfo.reloadDataAndRepaint(new CarInfo());
        this.tddOrder.reloadDataAndRepaint(new TblOrder());
        this.tdEmp.reloadDataAndRepaint(new Emp());
        this.tdAllocation.reloadDataAndRepaint(new Allocation());
        this.tdCoupon.reloadDataAndRepaint(new Coupon());

        tddNetInfo.addMouseListener(new MouseAdapter() {

            //            mouseClicked 怎么获取外面的数据
//            这是引用 只要弄到一次 以后都可以了
            @Override
            public void mouseClicked(MouseEvent e) {
                if (tddNetInfo.current == null) {
                    return;
                }
                Integer netId = tddNetInfo.current.getNetId();

                CarInfo carInfo = new CarInfo();
                carInfo.setNetId(netId);

                TblOrder tblOrder = new TblOrder();
                tblOrder.setNetInId(netId);

                Emp emp = new Emp();
                emp.setNetId(netId);

                Allocation allocation = new Allocation();
                allocation.setNetInId(netId);


                Coupon coupon = new Coupon();
                coupon.setNetId(netId);
//                但是 有 进出的 net id 怎么
//                if (carTypeTblDataDo.current == null) {
//                    return;
//                }
//                CarInfo carInfo1 = new CarInfo();
//                carInfo1.setTypeId(carTypeTblDataDo.current.getTypeId());
                try {
                    tddCarInfo.reloadDataAndRepaint(true, carInfo);
                    tddOrder.reloadDataAndRepaint(tblOrder);
                    tdEmp.reloadDataAndRepaint(emp);
                    tdAllocation.reloadDataAndRepaint(allocation);
//                    Promotion promotion = new Promotion();
//                    promotion.setTypeId(carTypeTblDataDo.current.getTypeId());
//                    tddPromo.reloadDataAndRepaint(true, promotion);
                    tdCoupon.reloadDataAndRepaint(coupon);
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

        pnlBottom.add(this.inputPanel, BorderLayout.NORTH);

        contentPane.add(pnlBottom, BorderLayout.SOUTH);
    }


    public static void main(String[] args) throws BaseException, SQLException, IllegalAccessException {

    }
}