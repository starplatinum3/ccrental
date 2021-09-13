
package cn.edu.zucc.personplan.ui;

import java.awt.*;
import java.awt.event.*;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.*;
import cn.edu.zucc.personplan.tvo.CarCategoryTvo;
import cn.edu.zucc.personplan.tvo.CarTypeTvo;
import cn.edu.zucc.personplan.util.*;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class FrmAdminCarCat extends JFrame implements ActionListener {

    private TblDataDo<CarCategory> dataTableCarCat;
    private TblDataDo<CarType> tddCarType;

    private JPanel mdpTblPanel = new JPanel();

    private JPanel toolBarPanel = new JPanel();
    private JPanel inputPanel = new JPanel();

    private JButton btnUpdate = new JButton("更新");
    private JButton btnAdd = new JButton("新增");
    private JButton btnAddConfirm = new JButton("确认新增");
    private JButton btnDel = new JButton("删除");
    private JButton btnRefresh = new JButton("刷新");

    private JLabel labelGetTime = new JLabel("edtGetTime：");
    private JLabel labelRetTime = new JLabel("edtRetTime：");

    private JTextField edtGetTime = new JTextField(20);
    private JTextField edtRetTime = new JTextField(20);
    //    借的时间
    private JTextField edtRentalDur = new JTextField(20);
    private JTextField edtRemark = new JTextField(20);


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnUpdate) {
            System.out.println("更新");

            try {
                UiUtil.updateBatch(dataTableCarCat);

            } catch (SQLException | BaseException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "更新失败\n" + ex.getMessage(),
                        "错误", JOptionPane.ERROR_MESSAGE);
            }


        } else if (e.getSource() == this.btnDel) {
//            System.out.println("更新");
            if (dataTableCarCat.current == null) {
                UiUtil.showError("没有选中，无法删除");
                return;
            }

            try {

//                车大类 要找个 车类型的 表，点一下 车大类，车类型就展现，有东西的话
//                说明还有人依赖他，他不能删除
                JdbcUtil.delete(dataTableCarCat.current);
                dataTableCarCat.all.remove(dataTableCarCat.current);
                UiUtil.showInfo("删除成功 ");
                dataTableCarCat.reloadTableNoSearch();
            } catch (MySQLIntegrityConstraintViolationException ex) {
                UiUtil.showError("不能删除  还有相关的 车类型 ");
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
            try {
                frmAddCarCat = new FrmAddCarCat(this);
            } catch (BaseException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            frmAddCarCat.setVisible(true);
//            add();


        } else if (e.getSource() == this.btnAddConfirm) {
            try {
//                需要从 里面拿值
//                UiUtil.getRowVals(dataTableCarCat.getSelectedRow(), (DefaultTableModel) dataTableCarCat.getModel());
                JdbcUtil.insert(dataTableCarCat.current);
//                插入 之后 把他的值 变一下
                UiUtil.updateRow(dataTableCarCat);
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
            reloadCarCat();
        }
    }

    public void reloadCarCat() {
        try {
//                dataTableCarCat.reloadTableNoSearch(true);
            dataTableCarCat.reloadTable(true);
        } catch (BaseException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    void add() {

//            dataTableCarCat.adde
        try {
            UiUtil.addEmptyRow(dataTableCarCat, CarCategory.class);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        }

//            System.out.println("更新");
//            if (dataTableCarCat.current == null) {
////                UiUtil.showError("没有选中，无法删除");
////                return;
////            }
////
////            try {
////                JdbcUtil.delete(dataTableCarCat.current);
////                dataTableCarCat.all.remove(dataTableCarCat.current);
////                UiUtil.showInfo("删除成功 ");
////                dataTableCarCat.reloadTableNoSearch();
////            } catch (DbException ex) {
////                ex.printStackTrace();
////            } catch (IllegalAccessException ex) {
////                ex.printStackTrace();
////            } catch (SQLException ex) {
////                ex.printStackTrace();
////            } catch (BaseException ex) {
////                ex.printStackTrace();
////            }

//            try {
//                UiUtil.updateBatch(dataTableCarCat);
//
//            } catch (SQLException | BaseException ex) {
//                ex.printStackTrace();
//                JOptionPane.showMessageDialog(null, "更新失败\n" + ex.getMessage(),
//                        "错误", JOptionPane.ERROR_MESSAGE);
//            }

    }

    FrmAddCarCat frmAddCarCat ;


    String[] titles = new String[]{"汽车大类", "汽车类型"};

    public FrmAdminCarCat() throws BaseException, SQLException {

//        setFont(new );
        this.setTitle("查看车大类");
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
        btnList.add(this.btnDel);
        btnList.add(this.btnAdd);
        btnList.add(this.btnRefresh);
//        btnList.add(this.btnAddConfirm);


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

        List<JTable> tblDataList = new ArrayList<>();

        this.dataTableCarCat = new TblDataDo<>(CarCategoryTvo.tableTitles, CcCarUtil.carCategoryDao);
        this.tddCarType = new TblDataDo<>(CarTypeTvo.tableTitles, CcCarUtil.carTypeDao);

        tblDataList.add(dataTableCarCat);
        tblDataList.add(tddCarType);

        int titleIdx = 0;

        for (JTable tblData : tblDataList) {
            JPanel panel = UiUtil.borderTable(titles[titleIdx++], tblData);
            this.mdpTblPanel.add(panel);
        }

//        dataTableCarCat.reloadTableNoSearch(true);
        dataTableCarCat.reloadTable(true);
        tddCarType.reloadDataAndRepaint(true, new CarType());

        dataTableCarCat.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int i = dataTableCarCat.getSelectedRow();
//                这个知道是自己吗
                if (i < 0) {
                    return;
                }

                CarType carType = new CarType();
                carType.setCatId(dataTableCarCat.current.getCatId());

                try {
                    tddCarType.reloadDataAndRepaint(true, carType);
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