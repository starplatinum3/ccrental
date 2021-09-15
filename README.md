zucc cc租车 数据库短学期

jdbc 管理系统， 租车下单

参考

https://gitee.com/SevDaisy/TakeawayGo

个人觉得比较有意义的一个东西，JdbcUtil，利用反射，不用写sql

## 运行
project structure

选择 sdk，编译出来的目录

导入 lib

(1条消息) IDEA导入jar包_hwt1070359898的博客-CSDN博客_idea导入jar包
https://blog.csdn.net/hwt1070359898/article/details/90517291

文档：project structure.note
链接：http://note.youdao.com/noteshare?id=533e78157551489144c0e68c3d0788d8&sub=E3B92DA453844C6C9FC9FB7180A4D3E2


导入 sql，在sql目录

配置连接池
cn.edu.zucc.personplan.util.DBPool

```java
dataSource = new ComboPooledDataSource();
dataSource.setUser("root");
dataSource.setPassword("123");

dataSource
        .setJdbcUrl("jdbc:mysql://127.0.0.1:3307/cc?autoReconnect=true&useUnicode=true" +
                "&characterEncoding=utf-8&useSSL=false&serverTimezone=GMT");
dataSource.setDriverClass("com.mysql.jdbc.Driver");
```

2021年9月15日12:21:18
更新 jdbcUtil，增加了 批量插入和批量更新
批量更新用了mysql的replace into， 估计别的数据库可能是没有的