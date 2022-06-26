package cn.itcast.ssm.po.original;

import java.math.BigDecimal;
import java.util.Date;

public class TOrderDetail extends TOrderDetailKey {
    private String itemId;

    private BigDecimal itemNum;

    private String deleteFlg;

    private Date createTime;

    private String createUserId;

    private Date updateTime;

    private String updateUserId;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getItemNum() {
        return itemNum;
    }

    public void setItemNum(BigDecimal itemNum) {
        this.itemNum = itemNum;
    }

    public String getDeleteFlg() {
        return deleteFlg;
    }

    public void setDeleteFlg(String deleteFlg) {
        this.deleteFlg = deleteFlg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }
}