package cn.edu.zucc.personplan.test;

import java.awt.*;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DisplayImageIcon extends JFrame {

    private JLabel lblImg;
    private Icon icon;

    public DisplayImageIcon(String picPath) {
        this(picPath, "车型图片");

    }

    public DisplayImageIcon(String picPath, String title) {

//  setTitle("swing显示图片");
//  setSize(200, 140);
//  setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//
//  lblImg = new JLabel();
//  add(lblImg);
//  setVisible(true);
//
//  try {
//   icon = new ImageIcon(new URL("http://www.wasw100.com/wp-content/themes/inove/img/logo.gif"));
//  } catch (MalformedURLException e) {
//   e.printStackTrace();
//  }
//  lblImg.setIcon(icon);


//        setTitle("swing显示图片");
//        setTitle("车型图片");
        setTitle(title);
//  setSize(200, 140);
        setSize(800, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocation(200, 100);
        setLocation(400, 300);


//        lblImg = new JLabel();
//        add(lblImg, BorderLayout.NORTH);
////     https://img.icoa.cn/colorful/14934323099430094.jpg
////  lblImg.setText("<html><img src='http://www.wasw100.com/wp-content/themes/inove/img/logo.gif' /><html>");
//        lblImg.setText("<html><img src='https://img.icoa.cn/colorful/14934323099430094.jpg' /><html>");

//  这个 路径可以
//        JLabel jl3 = new JLabel(new ImageIcon("img/baomax4.jpg"));
        JLabel jl3 = new JLabel(new ImageIcon(picPath));
        add(jl3, BorderLayout.CENTER);
        jl3.setBounds(0, 150, 700, 500);
//        setVisible(true);

    }

    public static void main(String[] args) {
        DisplayImageIcon displayImageIcon = new DisplayImageIcon(null);
        displayImageIcon.setVisible(true);

    }
}
//————————————————
//版权声明：本文为CSDN博主「flycomos_lee」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//原文链接：https://blog.csdn.net/flycomos_lee/article/details/83924303