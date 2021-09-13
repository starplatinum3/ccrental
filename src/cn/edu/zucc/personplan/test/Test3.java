package cn.edu.zucc.personplan.test;

import javax.swing.*;

public class Test3 {
    private JPanel pnl1;
    private JTable tbl1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Test3");
        frame.setContentPane(new Test3().pnl1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
