package cn.itcast.ssm.mapper.custom;

import java.util.List;
import java.util.Map;

import cn.itcast.ssm.po.original.TItem;
import cn.itcast.ssm.vo.VoItem;

public interface CustomTItemMapper {

	List<TItem> findItemList(VoItem item);

	List<Map<String, String>> getItemMapLst(Map<String, String> param);

	List<TItem> getSearchResult(VoItem item);  //@Param("searchin") 別名
}