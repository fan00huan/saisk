package cn.itcast.ssm.mapper.original;

import cn.itcast.ssm.po.original.TOrder;

public interface TOrderMapper {
    int deleteByPrimaryKey(String ordersNo);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    TOrder selectByPrimaryKey(String ordersNo);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKey(TOrder record);
}