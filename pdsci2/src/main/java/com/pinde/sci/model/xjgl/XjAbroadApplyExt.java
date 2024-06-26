package com.pinde.sci.model.xjgl;

import com.pinde.sci.form.xjgl.XjAbroadFamilyForm;
import com.pinde.sci.model.mo.NygoAbroadApply;

import java.util.List;

/**
 * @Copyright njpdxx.com
 * @since 2018/4/17
 */
public class XjAbroadApplyExt {

    private NygoAbroadApply abroadApply;
    private List<XjAbroadFamilyForm> familyFormList;

    public NygoAbroadApply getAbroadApply() {
        return abroadApply;
    }

    public void setAbroadApply(NygoAbroadApply abroadApply) {
        this.abroadApply = abroadApply;
    }

    public List<XjAbroadFamilyForm> getFamilyFormList() {
        return familyFormList;
    }

    public void setFamilyFormList(List<XjAbroadFamilyForm> familyFormList) {
        this.familyFormList = familyFormList;
    }
}
