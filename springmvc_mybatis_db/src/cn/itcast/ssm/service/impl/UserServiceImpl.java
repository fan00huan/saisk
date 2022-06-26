package cn.itcast.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.mapper.custom.CustomUserMapper;
import cn.itcast.ssm.mapper.original.TUserMapper;
import cn.itcast.ssm.po.original.TUser;
import cn.itcast.ssm.service.UserService;

/**
 *
 * <p>Title: ItemsServiceImpl</p>
 * <p>Description: 商品管理</p>
 * <p>Company: www.itcast.com</p>
 * @author	传智.燕青
 * @date	2015-4-13下午3:49:54
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private TUserMapper tUserMapper;

    @Autowired
    private CustomUserMapper customUserMapper;

    @Override
    public TUser findUser(String name) throws Exception {
        return tUserMapper.selectByPrimaryKey(name);
    }

    @Override
    public int inserUser(TUser user) throws Exception {
        return customUserMapper.insertSelective(user);
    }

    @Override
    public List<TUser> findUserLst(String name) throws Exception {
        return customUserMapper.findUserLstByCondition(name);
    }
}
