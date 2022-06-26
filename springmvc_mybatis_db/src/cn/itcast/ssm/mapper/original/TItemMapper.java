package cn.itcast.ssm.mapper.original;

import cn.itcast.ssm.po.original.TItem;

public interface TItemMapper {
    int deleteByPrimaryKey(String itemId);

    int insert(TItem record);

    int insertSelective(TItem record);

    TItem selectByPrimaryKey(String itemId);

    int updateByPrimaryKeySelective(TItem record);

    int updateByPrimaryKey(TItem record);
}