package cn.edu.zucc.personplan.util;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

//public class TimeUtil {
//
//
//    /*
//    判读时间差距，两个时间相差多少天，时，分，秒
//     */
//    public static Long getDay(String date) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Long days = null;
//        try {
//            Date currentTime = dateFormat.parse(dateFormat.format(new Date()));//现在系统当前时间
//            Date pastTime = dateFormat.parse(date);//过去时间
//            long diff = currentTime.getTime() - pastTime.getTime();
//            days = diff / (1000 * 60 * 60 * 24);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        return days;
//    }
//
//    public static void  testDay() {
//        try {
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////            Date now = df.parse("2019-03-26 13:31:40");//当前时间
////            Date date = df.parse("2004-01-02 11:30:24");//过去
//
//            Date now = df.parse("2019-03-26 13:31:40");//当前时间
//            Date date = df.parse("2019-03-25 11:30:24");//过去
//
//
//            long l = now.getTime() - date.getTime();
//            long day = l / (24 * 60 * 60 * 1000);
//            long hour = (l / (60 * 60 * 1000) - day * 24);
//            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
//            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
//            System.out.println("" + day + "天" + hour + "小时" + min + "分" + s + "秒");
////            不足一小时 按照 一小时 计算
//            //            不足一天 按照 一天 计算
//
//        } catch (Exception e) {
//
//        }
//
//    }
//
//    /**
//     * 把符合日期格式的字符串转换为日期类型
//     */
//    public static Date stringtoDate(String dateStr, String format) {
//        Date d = null;
//        SimpleDateFormat formater = new SimpleDateFormat(format);
//        try {
//            formater.setLenient(false);
//            d = formater.parse(dateStr);
//        } catch (Exception e) {
//            // log.error(e);
//            d = null;
//        }
//        return d;
//    }
//
//
//    public static java.sql.Timestamp strToSqlTime(String strTime,String formatStr) {
//        Date date = stringtoDate(strTime, formatStr);
//        return new java.sql.Timestamp(date.getTime());
//    }
//
//    public static java.sql.Timestamp dateToSqlTime(Date date) {
//    	if(date==null) {
//    		return null;
//    	}
////        Date date = stringtoDate(strTime, formatStr);
//        return new java.sql.Timestamp(date.getTime());
//    }
//
//
//    public static java.sql.Timestamp nowSqlTime() {
////    	Date date=new Date();
////    	Timestamp timestamp=Timestamp.
//        return new java.sql.Timestamp(System.currentTimeMillis());
//    }
//
//    /*
//     * 鐏忓棙妞傞梻瀛樺煈鏉烆剚宕叉稉鐑樻闂傦拷
//     * https://www.cnblogs.com/qiantao/p/10218921.html
//     */
//    public static String stampToDate(String timeStampStr) {
//
//        long timeStamp = new Long(timeStampStr);
//        return stampToDate(timeStamp);
//
//    }
//
//    void stoD() {
//
//    }
//
//    static void test() throws InterruptedException, ParseException {
//
////        long l = sqlStampToMillionSeconds(nowSqlTime());
////        System.out.println("l");
////        System.out.println(l);
////
////        String timeStr = toTimeStr(new Date(), "yyyy-MM-dd");
////        System.out.println("timeStr");
////        System.out.println(timeStr);
//
//        Timestamp sendDate = TimeUtil.nowSqlTime();
//        System.out.println("sendDate");
//        System.out.println(sendDate);
//
////        sqlStampToTimeStr()
////        toTimeStr()
//
////        String format = new SimpleDateFormat("yyyy-MM-dd").format(sendDate);
//        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sendDate);
//        System.out.println("format");
//        System.out.println(format);
//        Date date = TimeUtil.sqlStampToDate(sendDate);
//
//        String timeStr = TimeUtil.toTimeStr(date, "yyyy-MM-dd");
//        System.out.println("timeStr");
//        System.out.println(timeStr);
//    }
//
//    void formatDay(){
//        Date date = sqlStampToDate(nowSqlTime());
//        Date date2 = sqlStampToDate(nowSqlTime());
//        System.out.println("date");
//        System.out.println(date);
//        System.out.println(date2.getDay()-date.getDay());
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = sdf.format(date);
////        date getday 瀵啰鏁?
//
//    }
//
//    public static void main(String[] args) throws ParseException, InterruptedException {
//
//        testDay();
//
//    }
//
//    public static long sqlStampToMillionSeconds(Timestamp timestamp) throws ParseException {
//        //閼惧嘲褰囪ぐ鎾冲閺冨爼妫?
////        Timestamp t = new Timestamp(new Date().getTime());
////        System.out.println("瑜版挸澧犻弮鍫曟？閿涳拷"+t);
//        //鐎规矮绠熼弮鍫曟？閺嶇厧绱?
//        Date date = sqlStampToDate(nowSqlTime());
//
////        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
////        String str = dateFormat.format(timestamp);
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
////        //濮濄倕顦╂潪顒佸床娑撶儤顕犵粔鎺撴殶
////        Date parse = sdf.parse(str);
////        System.out.println("parse");
////        System.out.println(parse);
//        long millionSeconds = date.getTime();
////        long millionSeconds = sdf.parse(str).getTime();// 濮ｎ偆顫?
////        System.out.println("濮ｎ偆顫楅弫甯窗" + millionSeconds);
//        return millionSeconds;
//    }
//
//    /**
//     * 娑撳秷鍏橀悽锟? .. date 娴兼艾顦块崙鐘层亯
//     * 閺?閫涚啊 閸欘垯浜掓担璺ㄦ暏娴滐拷
//     * 閺夈儴鍤? https://blog.csdn.net/gkx_csdn/article/details/88421994
//     *
//     * @param timestamp
//     * @return
//     * @throws
//     */
//    public static Date sqlStampToDate(Timestamp timestamp)  {
//        //閼惧嘲褰囪ぐ鎾冲閺冨爼妫?
////        Timestamp t = new Timestamp(new Date().getTime());
////        System.out.println("瑜版挸澧犻弮鍫曟？閿涳拷"+t);
//        //鐎规矮绠熼弮鍫曟？閺嶇厧绱?
//        return new Date(timestamp.getTime());
////        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
////        String str = dateFormat.format(timestamp);
////        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
////        //濮濄倕顦╂潪顒佸床娑撶儤顕犵粔鎺撴殶
////        Date parse = sdf.parse(str);
//////        System.out.println("parse");
//////        System.out.println(parse);
////
////        return parse;
////        long millionSeconds = sdf.parse(str).getTime();// 濮ｎ偆顫?
////        System.out.println("濮ｎ偆顫楅弫甯窗" + millionSeconds);
////        return millionSeconds;
//    }
//
//    public static String sqlStampToTimeStr(Timestamp timestamp, String timePattern) throws ParseException {
//        String formatTime = new SimpleDateFormat(timePattern).format(timestamp);
//        return formatTime;
//
//    }
//
//
//    public static String stampToDate(long timeStamp) {
//        String res;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        long lt = new Long(s);
//        Date date = new Date(timeStamp);
//        res = simpleDateFormat.format(date);
//        return res;
//    }
//
//    /*
//     * 鐏忓棙妞傞梻纾嬫祮閹诡澀璐熼弮鍫曟？閹达拷
//     */
//    public static String dateToStamp(String s) throws ParseException {
//        String res;
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = simpleDateFormat.parse(s);
//        long ts = date.getTime();
//        res = String.valueOf(ts);
//        return res;
//    }
//
//    public static long getUnixTimestamp() {
//
//        return System.currentTimeMillis() / 1000;
//    }
//
//    public static long getUnixTimestamp(String timeStr, String timePattern) throws ParseException {
////        "2020-03-25 15:01:17"
////        "yyyy-MM-dd HH:mm:ss"
//        Date date = new SimpleDateFormat(timePattern).parse(timeStr);
////        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern);
////        simpleDateFormat.format()
//        //        System.out.println(timestamp);
//        return date.getTime() / 1000;
//    }
//
//    public static String toTimeStr(Date date, String timePattern) {
////        "2020-03-25 15:01:17"
////        "yyyy-MM-dd HH:mm:ss"
//        //        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern);
////        simpleDateFormat.format()
//        //        System.out.println(timestamp);
//
//        return new SimpleDateFormat(timePattern).format(date);
//    }
//
//
//}


//package zucc.kinect.util;

//        import lombok.extern.slf4j.Slf4j;
//        import org.apache.tomcat.jni.Local;
//        import zucc.kinect.dto.UsernameAndExceCnt;
//        import zucc.kinect.page.ComRedPageTime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

//@Slf4j
public class TimeUtil {

    public static final  String fmtAll= "yyyy-MM-dd HH:mm:ss";
    public static final  String fmtYmd= "yyyy-MM-dd";

    /*
    判读时间差距，两个时间相差多少天，时，分，秒
     */
    public static Long getDay(String date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long days = null;
        try {
            Date currentTime = dateFormat.parse(dateFormat.format(new Date()));//现在系统当前时间
            Date pastTime = dateFormat.parse(date);//过去时间
            long diff = currentTime.getTime() - pastTime.getTime();
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }


    /**
     * 超过一天的部分 算一天
     * @param oldDate
     * @param newDate
     * @return
     */
   public static long daysBetween(Date oldDate, Date newDate) {
        long l = newDate.getTime() - oldDate.getTime();
        long day = l / (24 * 60 * 60 * 1000);
//        long mod=l%(24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long sec = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (hour > 0 || min > 0 || sec > 0) {
            day++;
        }
//        System.out.println("day");
//        System.out.println(day);
        return day;

    }

    public static void testDay() {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date now = df.parse("2019-03-26 13:31:40");//当前时间
//            Date date = df.parse("2004-01-02 11:30:24");//过去

            Date now = df.parse("2019-03-26 13:31:40");//当前时间
            Date date = df.parse("2019-03-25 11:30:24");//过去


            long l = now.getTime() - date.getTime();
            long day = l / (24 * 60 * 60 * 1000);
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            System.out.println("" + day + "天" + hour + "小时" + min + "分" + s + "秒");
//            不足一小时 按照 一小时 计算
            //            不足一天 按照 一天 计算

        } catch (Exception e) {

        }

    }


    public static java.sql.Timestamp nowSqlTime() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }

    public static java.sql.Timestamp strToSqlTime(String strTime, String formatStr) {
        Date date = DateUtil.stringtoDate(strTime, formatStr);
        return new java.sql.Timestamp(date.getTime());
    }


    /*
     * 将时间戳转换为时间
     * https://www.cnblogs.com/qiantao/p/10218921.html
     */
    public static String stampToDate(String timeStampStr) {

        long timeStamp = new Long(timeStampStr);
        return stampToDate(timeStamp);

    }

    void stoD() {

    }

    static void test() throws InterruptedException, ParseException {

//        long l = sqlStampToMillionSeconds(nowSqlTime());
//        System.out.println("l");
//        System.out.println(l);
//
//        String timeStr = toTimeStr(new Date(), "yyyy-MM-dd");
//        System.out.println("timeStr");
//        System.out.println(timeStr);

//        List<ComRedPageTime> list = new ArrayList<>();
//        ComRedPageTime comRedPageTime = new ComRedPageTime(nowSqlTime(), "11");
//
//        list.add(comRedPageTime);
//
//        Thread.sleep(1000);
//        list.add(new ComRedPageTime(nowSqlTime(), "22"));
//
////        list.sort();
//        list.sort(Comparator.comparing(ComRedPageTime::getUpdateTime, Comparator.reverseOrder()));
//        System.out.println(list);

        Timestamp sendDate = TimeUtil.nowSqlTime();
        System.out.println("sendDate");
        System.out.println(sendDate);

//        sqlStampToTimeStr()
//        toTimeStr()
//        Timestamp.

//        String format = new SimpleDateFormat("yyyy-MM-dd").format(sendDate);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sendDate);
        System.out.println("format");
        System.out.println(format);
        Date date = TimeUtil.sqlStampToDate(sendDate);
//        log.info("date");
//        log.info(String.valueOf(date));
        String timeStr = TimeUtil.toTimeStr(date, "yyyy-MM-dd");
        System.out.println("timeStr");
        System.out.println(timeStr);
    }


    void formatDay() {
        Date date = sqlStampToDate(nowSqlTime());
        Date date2 = sqlStampToDate(nowSqlTime());
        System.out.println("date");
        System.out.println(date);
        System.out.println(date2.getDay() - date.getDay());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
//        date getday 弃用

    }

    /**
     * https://www.cnblogs.com/fantasy01/p/3994005.html
     */
    public static String calendarToStr(Calendar calendar, String format) {
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//       calendar.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String dateString = dateFormat.format(calendar.getTime());
        return dateString;
    }

//    直接算 毫秒 的差别 有多的 就是多了一天


//    https://www.cnblogs.com/xiohao/p/5294412.html

    /**
     * https://www.cnblogs.com/xiohao/p/5294412.html
     * 使用Calendar类 辅助完成天数的差值
     * 在不涉及到跨年的情况，此种方法是没问题的
     * 但是设计跨年的情况，此种方法回出问题的哦
     * <p>
     * 如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 1
     *
     * @throws ParseException
     */
//    @Test
    public void daysOfTwo_1() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //跨年的情况会出现问题哦
        //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 1
        Date fDate = sdf.parse("2015-12-31");
        Date oDate = sdf.parse("2016-01-01");
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(fDate);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(oDate);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        int days = day2 - day1;
        System.out.print(days);
    }

    /**
     * https://www.cnblogs.com/xiohao/p/5294412.html
     * 通过毫秒值，手动计算日期间的相关的值
     * <p>
     * 跨年不会出现问题
     * 使用此种方法的话需要注意
     * 如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
     *
     * @throws ParseException
     */
//    @Test
    public void daysOfTwo_2() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //跨年不会出现问题
        //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 0
        Date fDate = sdf.parse("2015-12-31");
        Date oDate = sdf.parse("2016-01-01");
        long days = (oDate.getTime() - fDate.getTime()) / (1000 * 3600 * 24);
        System.out.print(days);
    }


    /**
     * 得到两个日期的间隔天数（不够一天的按一天算）
     * https://www.iteye.com/blog/wlc-1699899
     * 硬算
     *
     * @param startDate
     * @param endDate
     * @return
     */

    public static synchronized long daysBetween(Calendar startDate, Calendar endDate) {

//        Calendar(new Date())
//        Calendar.Builder()
//        Date 转化为 Calendar
        Calendar date = (Calendar) startDate.clone();

        long daysBetween = 0;

        while (date.before(endDate)) {

            date.add(Calendar.DAY_OF_MONTH, 1);

            daysBetween++;

        }

        return daysBetween;

    }

    /**
     //     * 把符合日期格式的字符串转换为日期类型
     //     */
    public static Date stringtoDate(String dateStr, String format) {
        Date d = null;
        SimpleDateFormat formater = new SimpleDateFormat(format);
        try {
            formater.setLenient(false);
            d = formater.parse(dateStr);
        } catch (Exception e) {
            // log.error(e);
            d = null;
        }
        return d;
    }
//
//


     public static Timestamp dateToSqlTime(Date date){
         if(date==null) {
    		return null;
    	}
//        Date date = stringtoDate(strTime, formatStr);
        return new java.sql.Timestamp(date.getTime());
    }


    //    不足一天 按照一天 算 java
    public static void main(String[] args) throws ParseException, InterruptedException {

//        testDay();

        String format="yyyy-MM-dd hh:mm:ss";
//        Date date = DateUtil.stringtoDate("2021-6-8", format);
//        Date dateOld = DateUtil.stringtoDate("2021-6-7", format);
//        1 天

//        不管了  输入就两天好了

        Date date = DateUtil.stringtoDate("2021-6-8", format);
        Date dateOld = DateUtil.stringtoDate("2021-6-7 7:50:55", format);
//        days
//        1

//        Date date = DateUtil.stringtoDate("2021-6-8 ", format);
//        Date dateOld = DateUtil.stringtoDate("2021-6-7 7:50:55", format);
        long l = daysBetween(dateOld, date);
        System.out.println("days ");
        System.out.println(l);

    }

    public static long sqlStampToMillionSeconds(Timestamp timestamp) throws ParseException {
        //获取当前时间
//        Timestamp t = new Timestamp(new Date().getTime());
//        System.out.println("当前时间："+t);
        //定义时间格式
        Date date = sqlStampToDate(nowSqlTime());

//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
//        String str = dateFormat.format(timestamp);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
//        //此处转换为毫秒数
//        Date parse = sdf.parse(str);
//        System.out.println("parse");
//        System.out.println(parse);
        long millionSeconds = date.getTime();
//        long millionSeconds = sdf.parse(str).getTime();// 毫秒
//        System.out.println("毫秒数：" + millionSeconds);
        return millionSeconds;
    }

    /**
     * 不能用 .. date 会多几天
     * 改了 可以使用了
     * 来自 https://blog.csdn.net/gkx_csdn/article/details/88421994
     *
     * @param timestamp
     * @return
     * @throws
     */
    public static Date sqlStampToDate(Timestamp timestamp) {
        //获取当前时间
//        Timestamp t = new Timestamp(new Date().getTime());
//        System.out.println("当前时间："+t);
        //定义时间格式
        return new Date(timestamp.getTime());
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmss");
//        String str = dateFormat.format(timestamp);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmm");
//        //此处转换为毫秒数
//        Date parse = sdf.parse(str);
////        System.out.println("parse");
////        System.out.println(parse);
//
//        return parse;
//        long millionSeconds = sdf.parse(str).getTime();// 毫秒
//        System.out.println("毫秒数：" + millionSeconds);
//        return millionSeconds;
    }

    public static String sqlStampToTimeStr(Timestamp timestamp, String timePattern) throws ParseException {
        String formatTime = new SimpleDateFormat(timePattern).format(timestamp);
        return formatTime;

    }


    public static String stampToDate(long timeStamp) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        long lt = new Long(s);
        Date date = new Date(timeStamp);
        res = simpleDateFormat.format(date);
        return res;
    }

    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }

    public static long getUnixTimestamp() {

        return System.currentTimeMillis() / 1000;
    }

    public static long getUnixTimestamp(String timeStr, String timePattern) throws ParseException {
//        "2020-03-25 15:01:17"
//        "yyyy-MM-dd HH:mm:ss"
        Date date = new SimpleDateFormat(timePattern).parse(timeStr);
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern);
//        simpleDateFormat.format()
        //        System.out.println(timestamp);
        return date.getTime() / 1000;
    }

    public static String toTimeStr(Date date, String timePattern) {
//        "2020-03-25 15:01:17"
//        "yyyy-MM-dd HH:mm:ss"
        //        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(timePattern);
//        simpleDateFormat.format()
        //        System.out.println(timestamp);

        return new SimpleDateFormat(timePattern).format(date);
    }


}