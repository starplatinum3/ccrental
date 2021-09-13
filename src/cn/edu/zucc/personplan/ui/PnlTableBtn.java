package cn.edu.zucc.personplan.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PnlTableBtn  extends JPanel implements ActionListener {
//    JScrollPane jScrollPane;
    JTable jTable;
    JButton jButton;

    PnlTableBtn(){
//        this()
//        super(new GridLayout(2, 1));
//        JScrollPane jScrollPane = new JScrollPane(dataTableNetInfo);
//        this.add( new JButton("add"));
////        this.add(dataTableNetInfo);
////        this.add(dataTableNetInfo);
////        this.add(dataTableCoupon);
////        reloadNetInfoTable();
//        this.setVisible(true);
    }

//    public PnlTableBtn(LayoutManager layout, boolean isDoubleBuffered, JScrollPane jScrollPane, JButton jButton) {
//        super(layout, isDoubleBuffered);
//        this.jScrollPane = jScrollPane;
//        this.jButton = jButton;
//    }

//    public PnlTableBtn(JScrollPane jScrollPane, JButton jButton) {
//        super(new GridLayout(2, 1));
//        this.jScrollPane = jScrollPane;
//        this.jTable=jButton
//        this.jButton = jButton;
//        this.add( this.jScrollPane);
//        this.add( this.jScrollPane);
//    }

//    awt layout  比例
    public PnlTableBtn(JTable jTable, JButton jButton) {
        super(new GridLayout(2, 1));

        this.jTable = jTable;
        this.jButton = jButton;
        this.add( new JScrollPane(this.jTable));
        this.add( this.jButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
