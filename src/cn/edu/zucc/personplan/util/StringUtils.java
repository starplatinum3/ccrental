package cn.edu.zucc.personplan.util;

import java.awt.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * string tool
 *
 * @author xuxueli 2018-05-02 20:43:25
 */
public class StringUtils {


//           System.out.println(isEmail("abc_aa@bbb.com"));
    //        false
//    public static boolean isEmail(String string) {
//
//        if (string == null)
//            return false;
//        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
//        Pattern p;
//        Matcher m;
//        p = Pattern.compile(regEx1);
//        m = p.matcher(string);
//        return m.matches();
//    }


    /**
     * 数据库 是可以大写的
     * https://blog.csdn.net/xianrenmodel/article/details/110792291
     * 将驼峰式命名的字符串转换为下划线大写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。
     * 例如：HelloWorld->HELLO_WORLD
     *
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if (name != null && name.length() > 0) {
            // 将第一个字符处理成大写
            result.append(name.substring(0, 1).toUpperCase());
            // 循环处理其余字符
            for (int i = 1; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                // 在大写字母前添加下划线
                if (s.equals(s.toUpperCase()) && !Character.isDigit(s.charAt(0))) {
                    result.append("_");
                }
                // 其他字符直接转成大写
                result.append(s.toUpperCase());
            }
        }
        return result.toString();
    }


    /**
     * 数据库 是可以大写的
     * https://blog.csdn.net/xianrenmodel/article/details/110792291
     * 将驼峰式命名的字符串转换为下划线小写方式。如果转换前的驼峰式命名的字符串为空，则返回空字符串。
     * 例如：HelloWorld->hello_world
     *
     * @param name 转换前的驼峰式命名的字符串
     * @return 转换后下划线大写方式命名的字符串
     */
    public static String underscoreNameLower(String name) {
        String underscoreName = underscoreName(name);
        return underscoreName.toLowerCase();

    }


    public static boolean checkTimeStr(String timeStr) {
        Date date = TimeUtil.stringtoDate(timeStr, TimeUtil.fmtYmd);
        if (date == null) {
            UiUtil.showError("日期格式异常");
            return false;
        }
        return true;
    }


    /**
     * 将下划线大写方式命名的字符串转换为驼峰式。如果转换前的下划线大写方式命名的字符串为空，则返回空字符串。  * 例如：HELLO_WORLD->HelloWorld
     *
     * @param name 转换前的下划线大写方式命名的字符串
     * @return 转换后的驼峰式命名的字符串
     */
    public static String camelName(String name) {
        StringBuilder result = new StringBuilder();
        // 快速检查
        if (name == null || name.isEmpty()) {
            // 没必要转换
            return "";
        } else if (!name.contains("_")) {
            // 不含下划线，仅将首字母小写
            return name.substring(0, 1).toLowerCase() + name.substring(1);
        }
        // 用下划线将原始字符串分割
        String camels[] = name.split("_");
        for (String camel : camels) {
            // 跳过原始字符串中开头、结尾的下换线或双重下划线
            if (camel.isEmpty()) {
                continue;
            }
            // 处理真正的驼峰片段
            if (result.length() == 0) {
                // 第一个驼峰片段，全部字母都小写
                result.append(camel.toLowerCase());
            } else {
                // 其他的驼峰片段，首字母大写
                result.append(camel.substring(0, 1).toUpperCase());
                result.append(camel.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }


    /**
     * 首字母大写
     *
     * @param str
     * @return
     */
    public static String upperCaseFirst(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param str
     * @return
     */
    public static String lowerCaseFirst(String str) {
        //2019-2-10 解决StringUtils.lowerCaseFirst潜在的NPE异常@liutf
        return (str != null && str.length() > 1) ? str.substring(0, 1).toLowerCase() + str.substring(1) : "";
    }

//    驼峰 转化为 下划线

    /**
     * 下划线，转换为驼峰式
     *
     * @param underscoreName
     * @return
     */
    public static String underlineToCamelCase(String underscoreName) {
        StringBuilder result = new StringBuilder();
        if (underscoreName != null && underscoreName.trim().length() > 0) {
            boolean flag = false;
            for (int i = 0; i < underscoreName.length(); i++) {
                char ch = underscoreName.charAt(i);
                if ("_".charAt(0) == ch) {
                    flag = true;
                } else {
                    if (flag) {
                        result.append(Character.toUpperCase(ch));
                        flag = false;
                    } else {
                        result.append(ch);
                    }
                }
            }
        }
        return result.toString();
    }
//    public static boolean isNotNull(String str){
////        return org.apache.commons.lang3.StringUtils.isNotEmpty(str);
//        return false;
//    }

    static void test6() {
        String name_up_lo = camelName("_name_up_lo");
//       String name_up_lo = camelName("_name_up_lo");
        String name_up_lo2 = camelName("_name_up_lo_");
        String name_up_lo3 = camelName("_name__up_lo_");
        String name_up_lo4 = camelName("_na_me_up_lo_");
        String name_up_lo5 = camelName("na_me_up_lo");

        System.out.println("name_up_lo");
        System.out.println(name_up_lo);


        System.out.println("name_up_lo2");
        System.out.println(name_up_lo2);

        System.out.println("name_up_lo3");
        System.out.println(name_up_lo3);

        System.out.println("name_up_lo4");
        System.out.println(name_up_lo4);

        System.out.println("name_up_lo5");
        System.out.println(name_up_lo5);

//        name_up_lo
//                nameUpLo
//        name_up_lo2
//                nameUpLo
//        name_up_lo3
//                nameUpLo
//        name_up_lo4
//                naMeUpLo
//        name_up_lo5
//                naMeUpLo
    }

//       System.out.println(isEmail("abc_aa@bbb.com"));
//        false
//   public static boolean isEmail(String  email){
//
//        //电子邮件
//        String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
//        Pattern regex = Pattern.compile(check);
////        Matcher matcher = regex.matcher("dffdfdf@qq.com");
//        Matcher matcher = regex.matcher(email);
//        boolean isMatched = matcher.matches();
////        System.out.println(isMatched);
//        return isMatched;
//    }


//       System.out.println(d("abc_aa@bbb.com"));
//    //        true

    //        System.out.println(isEmail("42142178hdjoad@dada.ndajd"));
//    //        true

    //        System.out.println(isEmail(""));
//    //        false
    public static boolean isEmail(String mail) {
//        String mail=null;
//        System.out.println("请输入E-Mail：");
//        mail=scanner.next();
        Pattern pattern = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}");//\w表示a-z，A-Z，0-9(\\转义符)
        Matcher matcher = pattern.matcher(mail);
//        boolean b=matcher.matches();
        return matcher.matches();
//        if (b) {
//            System.out.println(mail+"有效的邮箱地址！");
//        }else {
//            System.out.println(mail+"的格式错误！！");
//        }
    }

    void test7() {
        //        System.out.println(isEmail("abc_aa@bbb.com"));
//        false
//        false
//        com.mchange.v2.lang.StringUtils.
    }

    public static boolean isNone(String string) {
        return string == null || string.equals("");
    }


    public static boolean isCarNo(String carNo){
        if (carNo.length() >= 7 && carNo.length() <= 8){
            Pattern p = Pattern.compile("^([京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[a-zA-Z](([DF]((?![IO])[a-zA-Z0-9](?![IO]))[0-9]{4})|([0-9]{5}[DF]))|[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1})$");
            Matcher m = p.matcher(carNo);
            return m.matches();
        }else{
            return false;
        }
    }
//————————————————
//    版权声明：本文为CSDN博主「husbandgod」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/u011010220/article/details/109509484


    //    https://www.cnblogs.com/sunxun001/p/13150897.html
    public static boolean isPhone(String  phone) {
//        String phone = "13123456789";

//        System.out.println(isPhone("18358507552"));
////        false
//        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        String regex ="^1[3|4|5|7|8][0-9]\\d{4,8}$";
//        System.out.println(isPhone("18358507552"));
////        true
        if (phone.length() != 11) {
//            System.out.println("手机号应为11位数");
            return false;
        }
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phone);
//        boolean isMatch = m.matches();
        return  m.matches();
//        if (isMatch) {
//            System.out.println("您的手机号格式正确");
//        } else {
//            System.out.println("您的手机号格式不正确");
//        }
    }

    public static void main(String[] args) {
//        test6();


//        System.out.println(isEmail("abc_aa@bbb.com"));
//        true

//        System.out.println(isEmail("42142178hdjoad@dada.ndajd"));
//        true

//        System.out.println(isEmail(""));
//        false

//        System.out.println(isPhone("412 431 241 22"));
//        System.out.println(isPhone("41243124122"));
//        false
//        false
        System.out.println(isPhone("18358507552"));
//        true
//        false
    }

}
