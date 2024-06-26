package com.pinde.sci.dao.edc;

import com.pinde.sci.model.mo.PubModule;

import java.util.List;

public interface PubModuleExtMapper {
    List<PubModule> moduleSearch(String keyWord);
}