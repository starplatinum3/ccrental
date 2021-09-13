package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.common.LoginType;
import cn.edu.zucc.personplan.model.Emp;
import cn.edu.zucc.personplan.util.BusinessException;
import cn.edu.zucc.personplan.util.DbException;

public interface IEmpDao {

    //    emp 和 user 不一样 那么现在登录的 那个人 是谁 要怎么知道
//    emp 是没有 user id 概念 所以 之间是没有关系的
    public Emp login(String username, String pwd, LoginType loginType)
            throws BusinessException, DbException ;
}
