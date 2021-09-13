package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.CcCarUtil;
import cn.edu.zucc.personplan.model.NetInfo;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class PnlTblWithTitle extends JPanel {
    //    必须 frame penal 不行吗
    public static void main(String[] args) throws BaseException, SQLException {


//        test1();
//        test4();
        test3();
    }

    static void test3() throws BaseException, SQLException {

        TblData<NetInfo> tblNetInfo = new TblData<>(NetInfo.tableTitles,
                CcCarUtil.netInfoDao);

        PnlTblWithTitle pnlTblWithTitle = new PnlTblWithTitle("1", tblNetInfo);
        PnlTblWithTitle pnlTblWithTitle2 = new PnlTblWithTitle("2", tblNetInfo);

        JPanel panel = new JPanel();
        panel.add(pnlTblWithTitle, BorderLayout.WEST);
        panel.add(pnlTblWithTitle2, BorderLayout.EAST);


//        pnlTblWithTitle.setSize(320, 240);
//        pnlTblWithTitle.setVisible(true);
//        JPanel panel = new JPanel();
        JFrame frame = new JFrame();
//        frame.getContentPane().add(pnlTblWithTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(p);
        frame.setSize(320, 240);
//        frame.add(pnlTblWithTitle);
//        frame.getContentPane().add(pnlTblWithTitle);
//        frame.getContentPane().add(pnlTblWithTitle,BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
//        label 放上去了
        frame.setVisible(true);
//        有了
    }


    static void test1() throws BaseException, SQLException {

//        pnl 展示不了
//        pnlTblWithTitle.setVisible(true);

        JPanel panel = new JPanel();
//       JLabel jLabel = new JLabel("1");
        TextField textField = new TextField("1");
        textField.setPreferredSize(new Dimension(100, 50));
        panel.add(textField);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(p);
//        frame.setSize(320, 240);
        frame.setSize(500, 300);
//        frame.add(pnlTblWithTitle);
//        frame.getContentPane().add(pnlTblWithTitle);
        frame.getContentPane().add(panel, BorderLayout.NORTH);

        panel.setPreferredSize(new Dimension(100, 50));
//       panel. setPreferredSize(new Dimension(60,240));
        TblData<NetInfo> tblNetInfo = new TblData<>(NetInfo.tableTitles,
                CcCarUtil.netInfoDao);

        tblNetInfo.reloadTable();
//       this.netInfoTblData = new TblData<>(NetInfo.tableTitles,
//               CcCarUtil.netInfoDao);
//       JTable jTable = new JTable();
        JScrollPane jScrollPane = new JScrollPane(tblNetInfo);
//       frame.getContentPane().add(new JTable());
        frame.getContentPane().add(jScrollPane, BorderLayout.SOUTH);
//        label 放上去了
        frame.validate();

        frame.setVisible(true);
    }

    void test2() {
        /*
         * 窗体的基本设置
         */
        JFrame jf = new JFrame();
        jf.setSize(450, 300);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(3);
        jf.setResizable(false);
        /*
         * 生成窗体中的各种组件
         */
        ImageIcon imageQQ = new ImageIcon(this.getClass().getResource("QQ面板.png"));
        JLabel component1 = new JLabel(imageQQ);
        //组件1 是界面上的QQ蓝色面板图像，图像我们把它放在JLabel类对象上
        ImageIcon imageqq = new ImageIcon(this.getClass().getResource("QQ头像.png"));
        JLabel component2 = new JLabel(imageqq);
        //组件2 是界面上的QQ企鹅图像，同理图像我们把它放在JLabel类对象上
        JTextField component3 = new JTextField();
        //组件3是用户的账号输入框
        JLabel component4 = new JLabel("用户账号");
        //组件4是用户的账号输入框右边的提示标签
        JTextField component5 = new JTextField();
        //组件5是用户的密码输入框
        JLabel component6 = new JLabel("用户密码");
        //组件6是用户的密码输入框右边的提示标签
        JCheckBox component7 = new JCheckBox("记住密码");
        //组件7是用户的“记住密码”的勾选键
        JCheckBox component8 = new JCheckBox("自动登录");
        //组件8是用户的“自动登录”的勾选键
        JButton component9 = new JButton("安全登录");
        //组件8是用户的“安全登录”的按键
        /*
         * 对窗体进行布局
         */
        GridBagLayout gridBagLayout = new GridBagLayout(); //实例化布局对象
        jf.setLayout(gridBagLayout);                     //jf窗体对象设置为GridBagLayout布局
        GridBagConstraints gridBagConstraints = new GridBagConstraints();//实例化这个对象用来对组件进行管理
        gridBagConstraints.fill = GridBagConstraints.BOTH;//该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //NONE：不调整组件大小。
        //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        //BOTH：使组件完全填满其显示区域。
        /*
         * 分别对组件进行设置
         */
        //组件1(gridx,gridy)组件的左上角坐标，gridwidth，gridheight：组件占用的网格行数和列数
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 4;
        gridBagLayout.setConstraints(component1, gridBagConstraints);
        //组件2
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 4;
        gridBagLayout.setConstraints(component2, gridBagConstraints);
//————————————————
//        版权声明：本文为CSDN博主「努力学习cs」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/wstz_5461/article/details/78067176
    }

    static void test4() throws BaseException, SQLException {
        JFrame frame = new JFrame();
//        frame.getContentPane().add(pnlTblWithTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.getContentPane().add(p);
        frame.setSize(320, 240);
//        frame.add(pnlTblWithTitle);
//        frame.getContentPane().add(pnlTblWithTitle);
        JLabel jLabel = new JLabel("1");
        JPanel pnlTitle = new JPanel();
        pnlTitle.add(jLabel);
        Container contentPane = frame.getContentPane();
        contentPane.add(pnlTitle, BorderLayout.NORTH);
        TblData<NetInfo> tblNetInfo = new TblData<>(NetInfo.tableTitles,
                CcCarUtil.netInfoDao);

//        tblNetInfo.reloadTable();
        tblNetInfo.reloadTableByThisList();
        contentPane.add(new JScrollPane(tblNetInfo), BorderLayout.CENTER);
//        label 放上去了
        frame.setVisible(true);
    }

    //    JLabel jLabel;
    JPanel pnlTitle;
    TblData jTable;

    void setUp(String title, TblData table) throws BaseException, SQLException {
        JLabel jLabel = new JLabel(title);
        pnlTitle = new JPanel();
        pnlTitle.add(jLabel);
//        Container contentPane = getContentPane();
        this.add(pnlTitle, BorderLayout.NORTH);
//        TblData<NetInfo> tblNetInfo = new TblData<>(NetInfo.tableTitles,
//                CcCarUtil.netInfoDao);
        jTable = new TblData<>(NetInfo.tableTitles,
                CcCarUtil.netInfoDao);
//        tblNetInfo.reloadTable();
        jTable.reloadTableByThisList();
        this.add(new JScrollPane(jTable), BorderLayout.CENTER);
    }

    void setUpGridBagLayout(String title, JTable table) {
        //        JPanel jPanel=new JPanel();
        GridBagLayout gridBagLayout = new GridBagLayout(); //实例化布局对象
//        jPanel.setLayout(gridBagLayout);                     //jf窗体对象设置为GridBagLayout布局
        setLayout(gridBagLayout);                     //jf窗体对象设置为GridBagLayout布局
        GridBagConstraints gridBagConstraints = new GridBagConstraints();//实例化这个对象用来对组件进行管理
        gridBagConstraints.fill = GridBagConstraints.BOTH;//该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //组件1(gridx,gridy)组件的左上角坐标，gridwidth，gridheight：组件占用的网格行数和列数
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 1;
        JTextField jTextField = new JTextField(title);
        gridBagLayout.setConstraints(jTextField, gridBagConstraints);
//        setVisible(true);
//        setSize(100, 100);

        //组件2
        JScrollPane jScrollPane = new JScrollPane(table);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.gridheight = 7;
        gridBagLayout.setConstraints(jScrollPane, gridBagConstraints);


        /*
         * 把所有组件加入jf窗体对象中去
         */
        add(jTextField);
        add(jScrollPane);
//        setVisible(true);


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

    public PnlTblWithTitle(String title, TblData table) throws BaseException, SQLException {
        setUp(title, table);
    }

    void test() {

    }
}
