package cn.edu.zucc.personplan.common;

public enum LoginType {
    ADMIN("管理员", 1),
    USER("用户", 2),
    EMP("员工",3);


    // 构造方法
    LoginType(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // 成员变量
    private String name;
    private int index;

    // 普通方法
    public static String getName(int index) {
        for (LoginType c : LoginType.values()) {
            if (c.getIndex() == index) {
                return c.getName();
            }
        }
        return null;
    }

    // get set 方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
//————————————————
//    版权声明：本文为CSDN博主「浮生夢」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//    原文链接：https://blog.csdn.net/echizao1839/article/details/80890490
}

