package cn.itcast.ssm.mapper.original;

import cn.itcast.ssm.po.original.TOrderDetail;
import cn.itcast.ssm.po.original.TOrderDetailKey;

public interface TOrderDetailMapper {
    int deleteByPrimaryKey(TOrderDetailKey key);

    int insert(TOrderDetail record);

    int insertSelective(TOrderDetail record);

    TOrderDetail selectByPrimaryKey(TOrderDetailKey key);

    int updateByPrimaryKeySelective(TOrderDetail record);

    int updateByPrimaryKey(TOrderDetail record);
}