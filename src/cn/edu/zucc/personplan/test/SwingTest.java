package cn.edu.zucc.personplan.test;

import cn.edu.zucc.personplan.util.DBUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SwingTest extends JFrame {
    JTable table;

    public SwingTest(String name) {
        super(name);

        /**********************************************
         * 给JFrame中添加菜单条 开始(维信科技it教育)
         *******************************************/

        JMenuBar jmentBar = new JMenuBar();

        JMenu menu = new JMenu("文件");

        JMenuItem it = new JMenuItem("保存");

        jmentBar.add(menu);

        menu.add(it);

        setJMenuBar(jmentBar);

        /**********************************************
         * 给JFrame中添加菜单条 结束 http://www.bjweixin.com/
         *******************************************/

        //表格中的列名

        String[] col = {"id", "用户名", "密码", "年龄", "性别", "体重"};

        //创建table

        table = new JTable();

        //默认管理二维表格数据的实例

        DefaultTableModel mm = new DefaultTableModel(col, 0);

        //可以从数据库中取出

        for (int i = 0; i < 30; i++) {
            String[] str_row = {"123", "123", "23", "321", "321"};

            mm.addRow(str_row);

        }

        //把实例加到表格

        table.setModel(mm);

        //给表格添加监听器

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                //修改表格内数据

                printData(table);

            }

        });

        //创建滚动面板

        JScrollPane scrollPane = new JScrollPane(table);

        //加到pane中

        getContentPane().add(scrollPane);

        setSize(500, 200);

        setVisible(true);

    }


    public static void putOneVal(Object value, int type, StringBuffer sb) throws SQLException {
        if (value != null) {
//            int type = rsmd.getColumnType(colCnt + 1);
//                        这么多列 就是 横着的 这些 就是一个实体里面 的遍历
            if (type == Types.CHAR || type == Types.VARCHAR) {
                sb.append("'").append(value).append("'");
            } else if (type == Types.DATE
                    || type == Types.TIMESTAMP
                    || type == Types.DATALINK) {
                sb.append("to_date('").append(value.toString().trim()).append("','yyyy-mm-dd')");
            } else {
                sb.append(value);
            }
        } else {
            sb.append("null");
        }
        sb.append(",");

    }

    public static void putOneColEq(Object value, int type, StringBuffer sb,
                                   String colunmName) throws SQLException {

        if (value != null) {
//            type = rsmd.getColumnType(k + 1);
            if (type == Types.CHAR || type == Types.VARCHAR) {
                sb.append(colunmName).append("=").append("'").append(value).append("'");
            } else if (type == Types.DATE
                    || type == Types.TIMESTAMP
                    || type == Types.DATALINK) {
                sb.append(colunmName).append("=").append("to_date('")
                        .append(value.toString().trim()).append("','yyyy-mm-dd')");
            } else {
                sb.append(colunmName).append("=").append(value);
            }
        } else {
            sb.append(colunmName).append(" is null");
        }
        sb.append(" and ");

    }

    List<Object> getRowVals(int row, DefaultTableModel model) {
        int columnCount = model.getColumnCount();
        List<Object> vals = new ArrayList<>();
        for (int col = 0; col < columnCount; col++) {
            Object valueAt = model.getValueAt(row, col);
            vals.add(valueAt);
        }
        return vals;

    }

    void getTableVals(JTable table) {
//        TableModel model1 = table.getModel();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        for (int i = 0; i < rowCount; i++) {
            List<Object> rowVals = getRowVals(i, model);
        }
    }

    Object getTableSelectedVal(JTable table) {
        TableModel model1 = table.getModel();
//        model1.
//        model1.setValueAt();
//        获取table所有的值 写入数据库 swing
//        table.setModel();
//        可以把  表格的所有值 因为他改过了 ，所有值 都重新写入数据库
        int row = table.getSelectedRow();

        int col = table.getSelectedColumn();
        //获取管理数据的模式

        DefaultTableModel model = (DefaultTableModel) table.getModel();
//        System.out.println(model.getValueAt(row, col) + "" + a + "" + id);
        return model.getValueAt(row, col);
    }

    private void printData(JTable table) {
        //获取到所有行数

        //int row = table.getRowCount();

        //int col = table.getColumnCount();

        //获取选中的行数

        int row = table.getSelectedRow();

        int col = table.getSelectedColumn();


        //获取管理数据的模式

        DefaultTableModel model = (DefaultTableModel) table.getModel();

        //获取ID

        String id = (String) model.getValueAt(row, 0);

        //获取列名

        String a = model.getColumnName(col);

        System.out.println(model.getValueAt(row, col) + "" + a + "" + id);
//        管理员有权限 直接这样修改 ，用户没有

        //此处可以连接db做修改或删除操作

    }

    public static void main(String[] args) {
        SwingTest s = new SwingTest("swingtable");

    }

}
//————————————————
//        版权声明：本文为CSDN博主「kljz」的原创文章，遵循CC4.0BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/u010619705/article/details/9419953