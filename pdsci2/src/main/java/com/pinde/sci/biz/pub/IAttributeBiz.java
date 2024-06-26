package com.pinde.sci.biz.pub;

import com.pinde.sci.model.mo.PubAttribute;
import com.pinde.sci.model.mo.PubAttributeExample;

import java.util.List;


public interface IAttributeBiz {
    List<PubAttribute> searchAttributeList(PubAttributeExample example);

    List<PubAttribute> searchAttributeList(String moduleCode, String elementCode);

    PubAttribute readPubAttribute(String flow);

    void addAttr(PubAttribute attr);

    void addAttrList(List<PubAttribute> attrList);

    PubAttribute readPubAttributeByCode(String attrCode);

    void modify(PubAttribute attr);

    void deleteAttr(PubAttribute attr);

    void saveAttributeOrder(String[] attrFlow);

    //属性复制
    void copyAttribute(PubAttribute attr);

}   
 