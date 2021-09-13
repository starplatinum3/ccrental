package cn.edu.zucc.personplan.itf;

import cn.edu.zucc.personplan.model.NetInfo;
import cn.edu.zucc.personplan.util.BaseException;

import java.util.List;

public interface INetInfoDao  {
    public List<NetInfo> getBy(NetInfo obj) throws BaseException ;
}
