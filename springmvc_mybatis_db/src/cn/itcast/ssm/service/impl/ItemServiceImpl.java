package cn.itcast.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.itcast.ssm.mapper.custom.CustomTItemMapper;
import cn.itcast.ssm.mapper.original.TItemMapper;
import cn.itcast.ssm.po.original.TItem;
import cn.itcast.ssm.service.ItemService;
import cn.itcast.ssm.vo.VoItem;

/**
 *
 * <p>Title: ItemsServiceImpl</p>
 * <p>Description: 商品管理</p>
 * <p>Company: www.itcast.com</p>
 * @author	传智.燕青
 * @date	2015-4-13下午3:49:54
 * @version 1.0
 */
public class ItemServiceImpl implements ItemService{

	@Autowired
	private CustomTItemMapper customTItemMapper;

	@Autowired
	private TItemMapper tItemMapper;

	@Override
	public List<TItem> findItemList(VoItem item) throws Exception {
		return customTItemMapper.findItemList(item);
	}

	@Override
	public TItem findItemsById(String itemId) throws Exception {

		return tItemMapper.selectByPrimaryKey(itemId);
	}

	@Override
	public void updateItems(VoItem item) throws Exception {
		tItemMapper.updateByPrimaryKeySelective(item);
	}
	@Override
	public void insertItem(VoItem item) throws Exception {
		tItemMapper.insert(item);
	}

	@Override
	public void deleteItems(String[] itemIds) throws Exception {

		for (String itemId : itemIds) {
			tItemMapper.deleteByPrimaryKey(itemId);
		}
	}


}
