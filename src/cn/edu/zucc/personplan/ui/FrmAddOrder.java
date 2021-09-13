package cn.edu.zucc.personplan.ui;

import cn.edu.zucc.personplan.PersonPlanUtil;
import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.TblOrder;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//yige 一个 实体类 维护 在 所有 的 有按钮的 table
//不用按钮 只要选中 .点击 按钮  get sele row
public class FrmAddOrder extends JDialog implements ActionListener {

    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private Button btnOk = new Button("确定");
    private Button btnCancel = new Button("取消");
    private JLabel labelName = new JLabel("名称：");

//    也就是 每个 控件里面  是可以 把数据设置到bus里面
    private JTextField edtName = new JTextField(20);
    private JTextField edtGetTime = new JTextField(20);
    private JTextField edtRetNet = new JTextField(20);
    private JTextField edtRetTime = new JTextField(20);
    private JTextField edtRentalDuration = new JTextField(20);
    public FrmAddOrder(JFrame f, String s, boolean b) {
        super(f, s, b);
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnOk);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.add(labelName);
        workPane.add(edtName);
        workPane.add(edtGetTime);
        workPane.add(edtRetNet);
        workPane.add(edtRetTime);
        workPane.add(edtRentalDuration);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(320, 180);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);
        this.btnCancel.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnCancel) {
            this.setVisible(false);
            return;
        }
        else if(e.getSource()==this.btnOk){
            String name=this.edtName.getText();
            String getTimeText=this.edtGetTime.getText();
            String edtRetNetText=this.edtRetNet.getText();
            String retTimeText=this.edtRetTime.getText();
            String rentalDurationText=this.edtRentalDuration.getText();
            String user_id = BeanUser.currentLoginUser.getUser_id();
//            他是哪辆车  选择 车 ，车名字，知道 id
//            下拉框 可供选择 网点
//            列出网点，列出车子
            // TODO: 2021/9/2   列出网点，列出车子
//            TblOrder order=new TblOrder(null,Integer.valueOf(user_id),carId,);
            try {
                PersonPlanUtil.planManager.addPlan(name);
                this.setVisible(false);
//				需要他加完了 之后发信息，那是比较麻烦的事情，还是算了
            } catch (BaseException e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
//                return;
            }
        }

    }


}
