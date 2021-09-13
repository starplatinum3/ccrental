package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.model.NetInfo;
import cn.edu.zucc.personplan.model.WithTitles;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

// extends WithTitles
public class PnlTbl<T> extends JTable implements ActionListener {
    //    JTable jTable;
    //  网点 两个
    T current = null;
    private Object[] titles;
    private Object[][] tblData;
    DefaultTableModel tabModel = new DefaultTableModel();
//    private JTable dataTable = new JTable(tabModel);
    List<T> all = null;

    PnlTbl(T t) {
//        titles = t.getTitles();
        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
//          FrmOrder.没有 不行
                int i = PnlTbl.this.getSelectedRow();
                if (i < 0) {
                    return;
                }
//                {
//                    // add for 商品分类
//                    FrmOrder.this.currentNetInfo = FrmOrder.this.allNetInfo.get(i);
//                    FrmOrder.this.reloadProduct_DirTable();
//                    // 每次刷新都顺便增加默认选择
//                    FrmOrder.this.currentProduct_Dir = FrmOrder.this.allProduct_Dir.get(0);
//                    FrmOrder.this.reloadProductTable();
//                    FrmOrder.this.currentProduct = FrmOrder.this.allProduct.get(0);
//                }
//                {
//                    // Debug OK
//                    System.out.println("allMerchant.size is " + this.allMerchant.size());
//                    System.out.println(this.currentMerchant);
//                }
            }
        });
    }

    PnlTbl(JTable table) {
        JPanel jPanel=new JPanel();
        GridBagLayout gridBagLayout=new GridBagLayout(); //实例化布局对象
        jPanel.setLayout(gridBagLayout);                     //jf窗体对象设置为GridBagLayout布局
        GridBagConstraints gridBagConstraints=new GridBagConstraints();//实例化这个对象用来对组件进行管理
        gridBagConstraints.fill=GridBagConstraints.BOTH;//该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //组件1(gridx,gridy)组件的左上角坐标，gridwidth，gridheight：组件占用的网格行数和列数
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=0;
        gridBagConstraints.gridwidth=4;
        gridBagConstraints.gridheight=1;
        JTextField jTextField=new JTextField("1");
        gridBagLayout.setConstraints(jTextField, gridBagConstraints);
        //组件2
        gridBagConstraints.gridx=0;
        gridBagConstraints.gridy=1;
        gridBagConstraints.gridwidth=4;
        gridBagConstraints.gridheight=7;
        gridBagLayout.setConstraints(table, gridBagConstraints);

//        titles = t.getTitles();
//        this.addMouseListener(new MouseAdapter() {
//
//            @Override
//            public void mouseClicked(MouseEvent e) {
////          FrmOrder.没有 不行
//                int i = PnlTbl.this.getSelectedRow();
//                if (i < 0) {
//                    return;
//                }
////                {
////                    // add for 商品分类
////                    FrmOrder.this.currentNetInfo = FrmOrder.this.allNetInfo.get(i);
////                    FrmOrder.this.reloadProduct_DirTable();
////                    // 每次刷新都顺便增加默认选择
////                    FrmOrder.this.currentProduct_Dir = FrmOrder.this.allProduct_Dir.get(0);
////                    FrmOrder.this.reloadProductTable();
////                    FrmOrder.this.currentProduct = FrmOrder.this.allProduct.get(0);
////                }
////                {
////                    // Debug OK
////                    System.out.println("allMerchant.size is " + this.allMerchant.size());
////                    System.out.println(this.currentMerchant);
////                }
//            }
//        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
