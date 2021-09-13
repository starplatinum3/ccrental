package cn.edu.zucc.personplan.util;

public class ListUtil {
    public static int index(String[] arr, String val) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(val)) {
                return i;
            }
        }
        return -1;
    }

}
