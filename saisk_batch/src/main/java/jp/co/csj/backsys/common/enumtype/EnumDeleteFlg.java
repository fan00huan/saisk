/*****************************************************************************
 * プログラム ：EnumDeleteFlg.java
 * 説明 ：削除フラグ列挙型.
 *****************************************************************************
 * 変更履歴： 2020.xx.xx : 新規作成
 ******************************************************************************/

package jp.co.csj.backsys.common.enumtype;

public enum EnumDeleteFlg {

    /** 削除フラグ 0：未削除. */
    DELETE_FLG_0("0"),

    /** 削除フラグ 1：削除. */
    DELETE_FLG_1("1");

    private final String val;

    private EnumDeleteFlg(String val) {
        this.val = val;
    }

    public String getVal() {
        return this.val;
    }
}