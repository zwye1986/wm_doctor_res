package com.pinde.sci.model.jsres;

import java.io.Serializable;

/**
 * @创建人 zsq
 * @创建时间 2021/6/3
 * 描述
 */
public class DictVo implements Serializable {
    private String dictValue;
    private String dictLabel;

    private String Superiorid;
    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public String getSuperiorid() {
        return Superiorid;
    }

    public void setSuperiorid(String superiorid) {
        Superiorid = superiorid;
    }

}
