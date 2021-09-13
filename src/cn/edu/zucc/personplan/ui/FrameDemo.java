package cn.edu.zucc.personplan.ui;

import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;
public class FrameDemo extends JFrame {
    JTable jtb;
     FrameDemo() {
        String[] title = {"name","interest"};
        String[][] data= {
                {"张三","羽毛球"},
                {"李四","乒乓球"},
                {"张三","羽毛球"},
                {"李四","乒乓球"},
                {"张三","羽毛球"},
                {"李四","乒乓球"},
                {"张三","羽毛球"},
                {"李四","乒乓球"}
        };
        jtb = new JTable(data,title);
         
        JScrollPane jsp = new JScrollPane(jtb);

//        放在最上面 里面有 tabl
        add(jsp);
         
        JPanel jp = new JPanel();
        JButton jb = new JButton("确定");
        jp.add(jb);
         
        add(jp,BorderLayout.SOUTH);
        // 窗口属性的设置
        setTitle("表格窗口");// 标题
//        setSize(242,170);// 窗口大小
        setSize(500,500);// 窗口大小
//         只有不够了 才会有滚动条
//         不设置大小的话 好像不会显示 ，全屏就会没有滚动条了
        setLocationRelativeTo(null);// 窗口居中
        setDefaultCloseOperation(EXIT_ON_CLOSE);
 
    }
 
    public static void main(String[] args) {
        new FrameDemo().setVisible(true);// 创建窗口实例, 并让窗口可见
    }
}