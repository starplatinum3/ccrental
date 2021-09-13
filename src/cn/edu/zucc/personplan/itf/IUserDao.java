package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.BeanUser;
import cn.edu.zucc.personplan.model.NetInfo;
import cn.edu.zucc.personplan.model.User;
import cn.edu.zucc.personplan.util.BaseException;

import java.util.List;

public interface IUserDao {
    public List<User> getBy(User obj) throws BaseException;

    /**
     * 登陆
     * 1、如果用户不存在或者密码错误，抛出一个异常
     * 2、如果认证成功，则返回当前用户信息
     * @param username
     * @param pwd
     * @return
     * @throws BaseException
     */
    public User login(String username, String pwd)throws BaseException;
}
