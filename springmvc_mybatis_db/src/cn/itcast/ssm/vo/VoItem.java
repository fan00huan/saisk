package cn.itcast.ssm.vo;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cn.itcast.ssm.controller.validation.ValidGroup1;
import cn.itcast.ssm.controller.validation.ValidGroup2;
import cn.itcast.ssm.po.original.TItem;

public class VoItem extends TItem{

	private String[] items_id;

	public String[] getItems_id() {
		return items_id;
	}

	public void setItems_id(String[] items_id) {
		this.items_id = items_id;
	}

	private String picMode;

    //校验名称在1到30字符中间
    //message是提示校验出错显示的信息
    //groups：此校验属于哪个分组，groups可以定义多个分组
    @Size(min=1,max=30,message="{item.itemName.length.error}",groups={ValidGroup1.class})
    private String itemName;

    //非空校验
    @NotNull(message="{item.itemProductDate.isNUll}",groups={ValidGroup2.class})
    private Date itemProductDate;



	private List<TItem> itemLst;

	public List<TItem> getItemLst() {
		return itemLst;
	}

	public void setItemLst(List<TItem> itemLst) {
		this.itemLst = itemLst;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Date getItemProductDate() {
		return itemProductDate;
	}

	public void setItemProductDate(Date itemProductDate) {
		this.itemProductDate = itemProductDate;
	}

	public String getPicMode() {
		return picMode;
	}

	public void setPicMode(String picMode) {
		this.picMode = picMode;
	}
}