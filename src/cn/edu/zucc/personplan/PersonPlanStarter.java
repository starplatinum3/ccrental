package cn.edu.zucc.personplan;

import cn.edu.zucc.personplan.ui.FrmMain;
import cn.edu.zucc.personplan.util.BaseException;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.sql.SQLException;
import java.util.Enumeration;

public class PersonPlanStarter {

	/**
	 * 统一设置字体，父界面设置之后，所有由父界面进入的子界面都不需要再次设置字体
	 */
	private static void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}
//————————————————
//	版权声明：本文为CSDN博主「秦时明月之君临天下」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//	原文链接：https://blog.csdn.net/weixin_41287260/article/details/94382050
//
	/**
	 * @param args
	 */
	public static void main(String[] args) throws BaseException, SQLException {
		// TODO Auto-generated method stub
//		https://blog.csdn.net/weixin_41287260/article/details/94382050
		InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 13)); // 统一设置字体
//		InitGlobalFont(new Font("微软雅黑", Font.PLAIN,)); // 统一设置字体
		new FrmMain();
	}

}
