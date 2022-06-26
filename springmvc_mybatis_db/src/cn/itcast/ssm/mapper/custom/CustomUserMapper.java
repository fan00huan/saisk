package cn.itcast.ssm.mapper.custom;

import java.util.List;

import cn.itcast.ssm.po.original.TUser;

public interface CustomUserMapper {

    int insertSelective(TUser record);

    List<TUser> findUserLstByCondition(String name);

}