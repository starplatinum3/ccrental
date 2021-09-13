
package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.CarCategory;
import cn.edu.zucc.personplan.tvo.CarCategoryTvo;
import cn.edu.zucc.personplan.util.BaseException;
import cn.edu.zucc.personplan.util.DbException;
import cn.edu.zucc.personplan.util.JdbcUtil;
import cn.edu.zucc.personplan.util.UiUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FrmAddCarCat extends JFrame implements ActionListener {

    private TblDataDo<CarCategory> dataTableCarCat;

    private JPanel mdpTblPanel = new JPanel();

    private JPanel toolBarPanel = new JPanel();
    private JPanel inputPanel = new JPanel();

    private JButton btnUpdate = new JButton("更新");
    private JButton btnAdd = new JButton("新增");
    private JButton btnAddConfirm = new JButton("确认新增");
    private JButton btnDel = new JButton("删除");
    private JButton btnSearch = new JButton("搜索");

    private JLabel labelGetTime = new JLabel("edtGetTime：");
    private JLabel labelRetTime = new JLabel("edtRetTime：");

    private JTextField edtGetTime = new JTextField(20);
    private JTextField edtRetTime = new JTextField(20);
    //    借的时间
    private JTextField edtRentalDur = new JTextField(20);
    private JTextField edtRemark = new JTextField(20);

    private JLabel lblTypeName = new JLabel("类型名：");
    private JLabel lblDescrip = new JLabel("描述：");
//    这个可以根据 title 生成  但是到时候要拿值的时候 还是要 get 0, 反而看不懂

    private JTextField edtTypeName = new JTextField(20);
    private JTextField edtDescrip = new JTextField(20);


    @Override
    public void actionPerformed(ActionEvent e) {
//        JPanel
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
                JdbcUtil.delete(dataTableCarCat.current);
                dataTableCarCat.all.remove(dataTableCarCat.current);
                UiUtil.showInfo("删除成功 ");
                dataTableCarCat.reloadTableNoSearch();
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


        } else if (e.getSource() == this.btnAddConfirm) {
            try {
                addConfirm();
            } catch (DbException ex) {
                ex.printStackTrace();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } else if (e.getSource() == this.btnSearch) {
            search();
        }
    }

    // TODO: 2021/9/10  search
    void search() {

    }

    void addConfirm() throws DbException, SQLException {
//        String retTimeText = edtRetTime.getText();
        String typeNameText = edtTypeName.getText();
        String descripText = edtDescrip.getText();
        CarCategory carCategory = new CarCategory(null, typeNameText, descripText);
        JdbcUtil.insert(carCategory);
        this.setVisible(false);
        father.reloadCarCat();
    }

    void add() {
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


    String[] titles = new String[]{"汽车大类"};

    public FrmAddCarCat() throws BaseException, SQLException {

        this.setTitle("新增车大类");
//        this.setSize(1700, 1000);
        this.setSize(900, 200);
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
//        btnList.add(this.btnDel);
//        btnList.add(this.btnAdd);
        btnList.add(this.btnAddConfirm);


        // toolBarPanel 设置
        toolBarPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        for (JButton jButton : btnList) {
            toolBarPanel.add(jButton);
            jButton.addActionListener(this);
        }

        setUpInput();
        List<JTable> tblDataList = new ArrayList<>();

        this.dataTableCarCat = new TblDataDo<>(CarCategoryTvo.tableTitles, CcCarUtil.carCategoryDao);

//        tblDataList.add(dataTableCarCat);

        int titleIdx = 0;

        for (JTable tblData : tblDataList) {
            JPanel panel = UiUtil.borderTable(titles[titleIdx++], tblData);
            this.mdpTblPanel.add(panel);
        }

//        dataTableCarCat.reloadTableNoSearch(true);


    }

    FrmAdminCarCat father;

    public FrmAddCarCat(FrmAdminCarCat father) throws BaseException, SQLException {
        this();
        this.father = father;

    }


    void setUpInput() {
//
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
//

        inputPanel.add(lblTypeName);
        inputPanel.add(edtTypeName);
        inputPanel.add(lblDescrip);
        inputPanel.add(edtDescrip);


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