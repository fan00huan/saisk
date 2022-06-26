package cn.itcast.ssm.service;

import java.util.List;

import cn.itcast.ssm.po.original.TItem;
import cn.itcast.ssm.vo.VoItem;

/**
 *
 * <p>Title: ItemsService</p>
 * <p>Description:商品管理service </p>
 * <p>Company: www.itcast.com</p>
 * @author	传智.燕青
 * @date	2015-4-13下午3:48:09
 * @version 1.0
 */
public interface ItemService {

	//商品查询列表
	public List<TItem> findItemList(VoItem item) throws Exception;
	//根据id查询商品信息
	/**
	 *
	 * <p>Title: findItemsById</p>
	 * <p>Description: </p>
	 * @param id 查询商品的id
	 * @return
	 * @throws Exception
	 */
	public TItem findItemsById(String itemId) throws Exception;

	//修改商品信息
	/**
	 *
	 * <p>Title: updateItems</p>
	 * <p>Description: </p>
	 * @param itemsCustom 修改的商品信息
	 * @throws Exception
	 */
	public void updateItems(VoItem item) throws Exception;

	//增加商品信息
	/**
	 *
	 * <p>Title: insertItem</p>
	 * <p>Description: </p>
	 * @param itemsCustom 增加的商品信息
	 * @throws Exception
	 */
	public void insertItem(VoItem item) throws Exception;
	public void deleteItems(String[] itemIds) throws Exception;

}
