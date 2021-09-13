package cn.edu.zucc.personplan.ui;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/*
https://blog.csdn.net/qq_41978199/article/details/80760961
 * 表格组件
 * 利用JTable类直接创建表格
 * 表格是最常用的数据统计形式之一，在swing中由 JTable类实现表格。
 * 创建表格
 * JTable类中除了默认的构造方法之外，还提供了利用指定表格列名数组和表格数据数组创建表格的构造方法
 *
 * JTable(Object[][] rowData,Object[] columnNames)
 * rowData:封装表格数据的数组
 * columnNames:封装表格列名的数组
 * 在使用表格时，通常将其添加到滚动面板中，然后将滚动面板添加到相应的位置
 */
public class ExampleFrame_01 extends JFrame{

    public static void main(String[] args) {
        new ExampleFrame_01().setVisible(true);
    }
    public ExampleFrame_01() {
        super();
        setTitle("创建滚动的表格");
        setBounds(100, 100, 240, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //定义表格列名数组
        String[] columnNames= {"A","B"};
        //定义表格数据数组
        String[][] tableValues={{"A1","B1"},{"A2","B2"},{"A3","B3"},{"A4","B4"},{"A5","B5"}};
        //创建指定列名和数据的表格
        JTable table =new JTable(tableValues,columnNames);
        //创建显示表格的滚动面板
//        JScrollPane scrollpane=new JScrollPane();
        JScrollPane scrollpane=new JScrollPane(table);
        Container contentPane = getContentPane();
//        contentPane.add(scrollpane);
        //将滚动面板添加到边界布局的中间
//        getContentPane().add(scrollpane,BorderLayout.CENTER);
//        没用啊
        contentPane.add(scrollpane,BorderLayout.CENTER);
//        contentPane.add(table);
//        setSize(200,200);
//        setSize(100,100);
        setSize(500,500);

    }

}
//————————————————
//版权声明：本文为CSDN博主「编程王子」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/qq_41978199/article/details/80760961