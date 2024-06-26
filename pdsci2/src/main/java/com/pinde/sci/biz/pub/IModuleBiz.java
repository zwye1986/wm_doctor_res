package com.pinde.sci.biz.pub;

import com.pinde.sci.model.mo.PubModule;
import com.pinde.sci.model.mo.PubModuleExample;

import java.util.List;


public interface IModuleBiz {
    List<PubModule> searchModuleList(PubModuleExample example);

    PubModule readPubModule(String flow);

    PubModule readPubModuleByCode(String code);

    List<PubModule> moduleSearch(String keyword);

    //新增
    int addPubModule(PubModule pm);

    //修改
    int updatePubModule(PubModule pm);

    List<PubModule> searchModuleListByDomain(String domain);
}   
 