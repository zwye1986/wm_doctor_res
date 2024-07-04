package com.pinde.res.biz.common;

import com.pinde.sci.model.mo.SysDict;

import java.util.List;

public interface IDictBiz {

    /**
     * 根据流水号(主键)查找
     *
     * @param dictFlow
     * @return
     * @throws Exception
     */
    SysDict readDict(String dictFlow);

    /**
     * 根据字典类型，字典代码
     *
     * @param dictTypeId
     * @param dictId
     * @return
     */
    SysDict readDict(String dictTypeId, String dictId);

    SysDict readAllSecondLevelDict(String dictTypeId, String dictId);
    //根据字典类型，字典代码及组织机构
    SysDict readDict(String dictTypeId, String dictId, String orgFlow);

    /**
     * 根据条件查询字典
     *
     * @param sysDict
     * @return
     * @throws Exception
     */
    List<SysDict> searchDictList(SysDict sysDict);

    List<SysDict> searchDictListByDictName(SysDict sysDict);

    /**
     * 根据字典类型id查找字典数据
     *
     * @param dictTypeId
     * @return
     */
    List<SysDict> searchDictListByDictTypeId(String dictTypeId);

    List<SysDict> searchDictListByDictTypeIdAndDictName(String dictTypeId, String dictName);

    /**
     * 根据字典类型id和字典流水号查找字典数据但是不包含该字典本身
     *
     * @param dict
     * @return
     */
    List<SysDict> searchDictListByDictTypeIdNotIncludeSelf(SysDict dict);

    List<SysDict> searchAllSecondLevelDictListByDictTypeIdNotIncludeSelf(String dictTypeId, SysDict dict);

    /**
     * 根据字典类型id查询所有的字典
     *
     * @param dictTypeId 字典类型id
     * @param isShowAll  是否查询所有 true:查询所有 false:查询启用的
     * @return
     */
    List<SysDict> searchDictListAllByDictTypeId(String dictTypeId, boolean isShowAll);

    List<SysDict> searchDictListByDictTypeIdAndDictId(String dictTypeId, String dictId);
}
