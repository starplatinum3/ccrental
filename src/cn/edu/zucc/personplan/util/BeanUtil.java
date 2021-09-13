package cn.edu.zucc.personplan.util;

import cn.edu.zucc.personplan.model.BeanPlan;
import cn.edu.zucc.personplan.model.Do;
import cn.edu.zucc.personplan.model.NetInfo;
import cn.edu.zucc.personplan.model.TblOrder;
import cn.edu.zucc.personplan.svo.Svo;
import cn.edu.zucc.personplan.tvo.Tvo;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BeanUtil {
    //	所有类型的值 转化为 string java
    public static List<String> getVals(Object object, boolean getStatic) throws IllegalAccessException {
//		Object st = new String();
        List<String> vals = new ArrayList<>();
        Field[] declaredFields = object.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            Object o = field.get(object);
//            获取 值
//            String modifier = Modifier.toString(field.getModifiers());
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers)) {
                if (!getStatic) {
                    continue;
                }
            }


            vals.add(String.valueOf(o));
        }
        return vals;
    }

    //    https://www.jb51.net/article/212718.htm
    public static void getObjectValue(Object object) throws Exception {
        //我们项目的所有实体类都继承BaseDomain （所有实体基类：该类只是串行化一下）
        //不需要的自己去掉即可
//        https://www.jb51.net/article/212718.htm
//        && object instanceof BaseDomain
        if (object == null) {
            return;
        }
        //if (object!=null )  ----begin
        // 拿到该类
        Class<?> clz = object.getClass();
        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clz.getDeclaredFields();
        for (Field field : fields) {// --for() begin
            System.out.println(field.getGenericType());//打印该类的所有属性类型
            // 如果类型是String
            if (field.getGenericType().toString().equals(
                    "class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
                // 拿到该属性的gettet方法
                /**
                 * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的
                 * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX）
                 * 如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范
                 */
                Method m = (Method) object.getClass().getMethod(
                        "get" + getMethodName(field.getName()));
                String val = (String) m.invoke(object);// 调用getter方法获取属性值
                if (val != null) {
                    System.out.println("String type:" + val);
                }
            }
            // 如果类型是Integer
            if (field.getGenericType().toString().equals(
                    "class java.lang.Integer")) {
                Method m = (Method) object.getClass().getMethod(
                        "get" + getMethodName(field.getName()));
                Integer val = (Integer) m.invoke(object);
                if (val != null) {
                    System.out.println("Integer type:" + val);
                }
            }
            // 如果类型是Double
            if (field.getGenericType().toString().equals(
                    "class java.lang.Double")) {
                Method m = (Method) object.getClass().getMethod(
                        "get" + getMethodName(field.getName()));
                Double val = (Double) m.invoke(object);
                if (val != null) {
                    System.out.println("Double type:" + val);
                }
            }
            // 如果类型是Boolean 是封装类
            if (field.getGenericType().toString().equals(
                    "class java.lang.Boolean")) {
                Method m = (Method) object.getClass().getMethod(
                        field.getName());
                Boolean val = (Boolean) m.invoke(object);
                if (val != null) {
                    System.out.println("Boolean type:" + val);
                }
            }
            // 如果类型是boolean 基本数据类型不一样 这里有点说名如果定义名是 isXXX的 那就全都是isXXX的
            // 反射找不到getter的具体名
            if (field.getGenericType().toString().equals("boolean")) {
                Method m = (Method) object.getClass().getMethod(
                        field.getName());
                Boolean val = (Boolean) m.invoke(object);
                if (val != null) {
                    System.out.println("boolean type:" + val);
                }
            }
            // 如果类型是Date
            if (field.getGenericType().toString().equals(
                    "class java.util.Date")) {
                Method m = (Method) object.getClass().getMethod(
                        "get" + getMethodName(field.getName()));
                Date val = (Date) m.invoke(object);
                if (val != null) {
                    System.out.println("Date type:" + val);
                }
            }
            // 如果类型是Short
            if (field.getGenericType().toString().equals(
                    "class java.lang.Short")) {
                Method m = (Method) object.getClass().getMethod(
                        "get" + getMethodName(field.getName()));
                Short val = (Short) m.invoke(object);
                if (val != null) {
                    System.out.println("Short type:" + val);
                }
            }
            // 如果还需要其他的类型请自己做扩展
        }//for() --end
        //if (object!=null )  ----end
    }

    //    https://www.jb51.net/article/212718.htm
    // 把一个字符串的第一个字母大写、效率是最高的、
    private static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }


    /**
     * byte	Byte
     * short	Short
     * int	Integer
     * long	Long
     * float	Float
     * double	Double
     * boolean	Boolean
     * char	Character
     *
     * @param canonicalName
     * @return
     */
    public static String primaryToPackaging(String canonicalName) {
        switch (canonicalName) {
            case "byte":
                return "java.lang.Byte";
            case "short":
                return "java.lang.Short";
            case "int":
                return "java.lang.Integer";
            case "long":
                return "java.lang.Long";
            case "float":
                return "java.lang.Float";
            case "double":
                return "java.lang.Double";
            case "boolean":
                return "java.lang.Boolean";
            case "char":
                return "java.lang.Character";
            default:
                return canonicalName;
        }
    }

    //    i 是 obj 个数， j 是属性的个数
    //    这是 一个表的数据
    public static <T> List<List<String>> getObjListVals(List<T> objList) throws IllegalAccessException {
        List<List<String>> listVals = new ArrayList<>();
//        System.out.println("objList.size()");
//        System.out.println(objList.size());
        for (T t : objList) {
            List<String> vals = getVals(t, false);
//            System.out.println("vals");
//            System.out.println(vals);
            listVals.add(vals);
        }
        return listVals;
//        objList.forEach(o->getVals(o,false));
    }

    //    可以返回的吗
//    list 一定是有值的 啊 不然怎么返回 出去 可以看的、
    public static <T> Object[][] getTblData(List<T> objList, int attrLen) throws IllegalAccessException {
//        List<List<String>> listVals = new ArrayList<>();
//        int attrLen=0;
        Object[][] tblData = new Object[objList.size()][attrLen];

//        for (T t : objList) {
////            一个类的 各种属性
//            List<String> vals = getVals(t, false);
////            listVals.add(vals);
////            attrLen=vals.size();
//        }


        for (int i = 0; i < objList.size(); i++) {
            T t = objList.get(i);
            List<String> vals = getVals(t, false);

            tblData[i] = vals.toArray();
//        	一行数据
//            for (int j = 0; j < attrLen; j++){
//                tblData[i]
//            }
//                tblData[i][j] = dataList.get(i).getCell(j);
            //            里面是 str

        }
        return tblData;
//        objList.forEach(o->getVals(o,false));
    }

//    这样他们都得是 svo了  很奇怪 ,但是
    public static <T extends Do> Object[][] getTblData(List<T> objList,
                                                       int attrLen, boolean toTvo)
            throws IllegalAccessException {
//        List<List<String>> listVals = new ArrayList<>();
//        int attrLen=0;
        Object[][] tblData = new Object[objList.size()][attrLen];

//        for (T t : objList) {
////            一个类的 各种属性
//            List<String> vals = getVals(t, false);
////            listVals.add(vals);
////            attrLen=vals.size();
//        }


        for (int i = 0; i < objList.size(); i++) {
            T t = objList.get(i);
            List<String> vals;
            if (toTvo) {
                Tvo tvo = t.toTvo();
//                t.
                vals = getVals(tvo, false);

            } else {
                vals = getVals(t, false);

            }
//            List<String> vals = getVals(t, false);

            tblData[i] = vals.toArray();
//        	一行数据
//            for (int j = 0; j < attrLen; j++){
//                tblData[i]
//            }
//                tblData[i][j] = dataList.get(i).getCell(j);
            //            里面是 str

        }
        return tblData;
//        objList.forEach(o->getVals(o,false));
    }


    static void testCopy() throws InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        NetInfo netInfo = new NetInfo();
        netInfo.setNetId(1);
        NetInfo netInfo1 = (NetInfo) BeanUtils.cloneBean(netInfo);

    }

    void testBeanPlanVals() throws IllegalAccessException {
        BeanPlan beanPlan = new BeanPlan();
        beanPlan.setPlan_id(1);
        beanPlan.setUser_id("2");
        beanPlan.setPlan_order(1);

//        vals
//                [1, 2, 1, null, null, null, null, null]
        List<String> vals = getVals(beanPlan, false);
        System.out.println("vals");
        System.out.println(vals);
    }

    void test() throws IllegalAccessException {

        BeanPlan beanPlan = new BeanPlan();
        beanPlan.setPlan_id(1);
        beanPlan.setUser_id("2");
        beanPlan.setPlan_order(1);

        BeanPlan beanPlan2 = new BeanPlan();
        beanPlan2.setPlan_id(3);
        beanPlan2.setUser_id("3");
        beanPlan2.setPlan_order(3);
        List<BeanPlan> list = new ArrayList<>();
        list.add(beanPlan);
        list.add(beanPlan2);
//        不能返回这种东西
        Object[][] tblData = getTblData(list, 8);
        System.out.println("tblData");
//        System.out.println(tblData);
        System.out.println(Arrays.deepToString(tblData));
//        可以的
//        tblData
//                [[1, 2, 1, null, null, null, null, null], [3, 3, 3, null, null, null, null, null]]

//        tblData
//[]

        List<List<String>> objListVals = getObjListVals(list);
        System.out.println("objListVals");
        System.out.println(objListVals);
//        objListVals
//                [[1, 2, 1, null, null, null, null, null], [3, 3, 3, null, null, null, null, null]]

//        tblData
//                [[Ljava.lang.Object;@63947c6b
//                objListVals
//                []


//        System.out.println(Arrays.deepToString(tblData));
    }

    /**
     * //        private
     * //        name
     * //                couponId
     * //        genericType
     * //        class java.lang.Integer
     * //        accessible before
     * //        false
     * //        获取到字段：java.util.Date,值：null
     *
     * @throws IllegalAccessException
     */
    static void test2() throws IllegalAccessException {
//        Object redPacketRecord = new RedPacketRecord();
        TblOrder tblOrder = new TblOrder();
        tblOrder.setCarId(1);
        Field[] declaredFields = tblOrder.getClass().getDeclaredFields();


        for (Field field : declaredFields) {
//			System.out.println("field");
//			System.out.println(field);
            System.out.println("accessible before");
            boolean accessible = field.isAccessible();
            System.out.println(accessible);
            field.setAccessible(true);
            System.out.println("获取到字段：" + field.getType().getCanonicalName()
                    + ",值：" + field.get(tblOrder));
            String modifier = Modifier.toString(field.getModifiers());
            System.out.println(modifier);
//			Object o = field.get(redPacketRecord);
//			System.out.println("o");
//			System.out.println(o);
            String name = field.getName();
//			System.out.println("name");
////			就要 name 是短的名字
//			System.out.println(name);
//

            Type genericType = field.getGenericType();
//			System.out.println("genericType");
//			System.out.println(genericType);
//
//			Class<?> aClass = Class.forName(String.valueOf(genericType).replace("class ",""));
//			System.out.println("aClass");
//			System.out.println(aClass);
            String genericString = field.toGenericString();
//			System.out.println("genericString");
//			System.out.println(genericString);


            Class<?> type = field.getType();
//			System.out.println("type");
//			System.out.println(type);

//			type
//			class java.lang.Integer

//			type
//			long

//			ListUtil.printListRet(Collections.singletonList(type));
//			ListUtil.printListRet(type);

            Class<?> declaringClass = field.getDeclaringClass();
//			System.out.println("declaringClass");
//			System.out.println(declaringClass);
//			declaringClass


            Class<? extends Field> aClass = field.getClass();

//			System.out.println("aClass");
//			System.out.println(aClass);
//			aClass
//			class java.lang.reflect.Field
            boolean accessibleAfter = field.isAccessible();
//            System.out.println("accessibleAfter");
//            System.out.println(accessibleAfter);
        }
    }

    void test3() throws ClassNotFoundException {


//        Class<?> aClass = Class.forName("java.lang.Byte");
        Class<?> aClass = Class.forName("java.lang.String");
        System.out.println(aClass);
        String string = "1";
        Object strObj = (Object) string;
        Object cast = aClass.cast(strObj);
        System.out.println("cast");
        System.out.println(cast);
        System.out.println("cast.getClass()");
        System.out.println(cast.getClass());

//        class java.lang.String
//                cast
//        1
//        cast.getClass()
//        class java.lang.String

        String name = aClass.getName();
        System.out.println("name");
        System.out.println(name);
    }

    static void test5() throws NoSuchMethodException, IllegalAccessException {
        NetInfo netInfo = new NetInfo();
        netInfo.setNetId(1);
        Class<? extends NetInfo> aClass = netInfo.getClass();
        Method[] methods = aClass.getDeclaredMethods(); // 返回public方法
        for (Method method : methods) {
            System.out.println(method);
//            method.invoke()
        }

//        Method method03 = aClass.getDeclaredMethod("set" +, int.class);
        TblOrder tblOrder = new TblOrder();
        tblOrder.setCarId(1);
        Field[] declaredFields = tblOrder.getClass().getDeclaredFields();


        for (Field field : declaredFields) {
            field.setAccessible(true);
//           System.out.println("获取到字段：" + field.getType().getCanonicalName()
//                   + ",值：" + field.get(tblOrder));
//            String modifier = Modifier.toString(field.getModifiers());
            int modifiers = field.getModifiers();
//            System.out.println(modifier);
            if (Modifier.isStatic(modifiers)) {
                continue;
            }
            String name = field.getName();
//            String up = StringUtils.underlineToCamelCase(name);
            String up = StringUtils.upperCaseFirst(name);
            String canonicalName = field.getType().getCanonicalName();
            String packaging = BeanUtil.primaryToPackaging(canonicalName);
//            Object val = field.get(object);
//            现在是往 类里面放值
//            第一个参数 是 int； set(int)
            Method set = aClass.getDeclaredMethod("set" + up, int.class);


        }

    }

//  public  static<T>  Object  listToBean(List<Object> rowVals,T t ){
//
//    }

    public static void main(String[] args) throws IllegalAccessException,
            ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
//        test2();

//        test5();

        testCopy();

    }
}
