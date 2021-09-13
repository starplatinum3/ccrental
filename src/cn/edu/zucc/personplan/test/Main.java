package cn.edu.zucc.personplan.test;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.plaf.LayerUI;

public class Main {
  public static void main(String[] args) {
    JTabbedPane tab = new JTabbedPane();
    tab.addTab("New tab1", new JLabel("1"));
    tab.addTab("New Tab2", new JLabel("2"));
    JPanel p = new JPanel(new BorderLayout());
    p.add(new JLayer<JComponent>(tab, new TopRightCornerLabelLayerUI()));

    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.getContentPane().add(p);
    f.setSize(320, 240);
    f.setVisible(true);

//    new Date()
  }
}

//https://www.w3cschool.cn/java/codedemo-484052379.html
class TopRightCornerLabelLayerUI extends LayerUI<JComponent> {
  private JLabel l = new JLabel("A Label at right corner");
  private JPanel rubberStamp = new JPanel();

  @Override
  public void paint(Graphics g, JComponent c) {
    super.paint(g, c);
    Dimension d = l.getPreferredSize();
    int x = c.getWidth() - d.width - 5;
    SwingUtilities.paintComponent(g, l, rubberStamp, x, 2, d.width, d.height);
  }
}
