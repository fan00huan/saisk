package cn.itcast.ssm.vo;

import java.util.List;

import cn.itcast.ssm.po.original.TUser;

public class VoUser extends TUser {

    private List<TUser> userLst;

    public List<TUser> getUserLst() {
        return userLst;
    }

    public void setUserLst(List<TUser> userLst) {
        this.userLst = userLst;
    }



}