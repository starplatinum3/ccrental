package cn.edu.zucc.personplan.ui;

import java.awt.*;

import javax.swing.*;

public class DemoButtons {
    public DemoButtons() {
        final JFrame frame = new JFrame("Demo buttons");

//        frame.setDefaultCloSEOperation(JFrame.EXIT_ON_CLOSE);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1));

        buttonPanel.add(new JButton("Export do SVG"));

        buttonPanel.add(new JButton("Export do PNG"));

        buttonPanel.add(new JButton("Tisk..."));

        JPanel east = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.anchor = GridBagConstraints.NORTH;

        gbc.weighty = 1;

        east.add(buttonPanel, gbc);

        JPanel center = new JPanel() {
            @Override

            public Dimension getPreferredSize() {
                return new Dimension(200, 200);

            }

        };

        center.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        frame.add(east, BorderLayout.EAST);

        frame.add(center);

        frame.pack();

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        } catch (Exception e) {
            e.printStackTrace();

        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override

            public void run() {
                new DemoButtons();

            }

        });

    }

}
//————————————————
//        版权声明：本文为CSDN博主「Yvonne一万」的原创文章，遵循CC4.0BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/weixin_36319237/article/details/114104679