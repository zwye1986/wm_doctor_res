package com.pinde.sci.biz.edc;

import com.pinde.sci.model.mo.EdcGroup;

import java.util.List;

public interface IEdcGroupBiz {

    EdcGroup read(String gruopFlow);

    void add(EdcGroup group);

    void mod(EdcGroup group);

    void del(EdcGroup group);

    List<EdcGroup> searchEdcGroup(String projFlow);

    void save(EdcGroup gruop);

    List<EdcGroup> searchEdcGroup(EdcGroup group);

    EdcGroup searchEdcGroup(String projFlow, String groupName);

}
