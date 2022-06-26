package cn.itcast.ssm.po.custom;

import cn.itcast.ssm.po.original.TUser;

public class CustomUser extends TUser {

    private String birthdayStr;

    public String getBirthdayStr() {
        return birthdayStr;
    }

    public void setBirthdayStr(String birthdayStr) {
        this.birthdayStr = birthdayStr;
    }
}
