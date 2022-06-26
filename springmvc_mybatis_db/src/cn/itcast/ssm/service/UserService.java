package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.po.original.TUser;

/**
 *
 * <p>Title: ItemsService</p>
 * <p>Description:商品管理service </p>
 * <p>Company: www.itcast.com</p>
 * @author	传智.燕青
 * @date	2015-4-13下午3:48:09
 * @version 1.0
 */
public interface UserService {
	public TUser findUser(String name) throws Exception;

	public int inserUser(TUser user) throws Exception;

	public List<TUser> findUserLst(String name) throws Exception;
}
