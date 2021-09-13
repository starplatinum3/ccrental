package cn.edu.zucc.personplan.test;



import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;

import java.awt.BorderLayout;



import javax.swing.JFrame;

import javax.swing.JLabel;

import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTable;

import javax.swing.event.ListSelectionEvent;

import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


//import charactor.Hero;


//https://www.cnblogs.com/Lanht/p/12615447.html
public class TestGUI {

    public static void main(String[] args) {



        JFrame f = new JFrame("LoL");

        f.setSize(400, 300);

        f.setLocation(200, 200);

        f.setLayout(new BorderLayout());



//        final HeroTableModel htm = new HeroTableModel();
        DefaultTableModel htm=new DefaultTableModel();

//        htm.setDataVector();


        final JTable t = new JTable(htm);

        // 准备一个Panel上面放一个Label用于显示哪条被选中了

        JPanel p = new JPanel();

        final JLabel l = new JLabel("暂时未选中条目");

        p.add(l);
//        上下两个 penal
//        大小会自己控制吗



        JScrollPane sp = new JScrollPane(t);



        // 使用selection监听器来监听table的哪个条目被选中

        t.getSelectionModel().addListSelectionListener(

                new ListSelectionListener() {



                    // 当选择了某一行的时候触发该事件

                    public void valueChanged(ListSelectionEvent e) {

                        // 获取哪一行被选中了

                        int row = t.getSelectedRow();

                        // 根据选中的行，到HeroTableModel中获取对应的对象

//                        Hero h = htm.heros.get(row);

                        // 更新标签内容

//                        l.setText("当前选中的英雄是： " + h.name);



                    }

                });



        f.add(p, BorderLayout.NORTH);

        f.add(sp, BorderLayout.CENTER);



        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        f.setVisible(true);

    }

}