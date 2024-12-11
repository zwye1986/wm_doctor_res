package com.pinde.sci.biz.sys;

import com.pinde.core.model.DictForm;
import com.pinde.core.model.SysDict;
import com.pinde.sci.form.sys.SubDictEditForm;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IDictBiz {

    /**
     * 保存字典
     *
     * @param dict
     * @throws Exception
     */
    void addDict(SysDict dict);

    /**
     * 根据流水号(主键)更新字典
     *
     * @param dict
     * @throws Exception
     */
    void modDict(SysDict dict);

    /**
     * 根据流水号更新字典(包过子字典)
     *
     * @param dict
     */
    void modeDictAndSubDict(SysDict dict);

    /**
     * 保存子字典
     *
     * @param subDictForm
     */
    void saveSubDict(SubDictEditForm subDictForm);

    /**
     * 根据流水号(主键)做软删除
     * @param dictFlow
     * @throws Exception
     */
//	public void delDict(String dictFlow,String recordStatus) ;

    /**
     * 根据流水号(主键)做软删除(包过子字典)
     *
     * @param dictFlow
     * @throws Exception
     */
    void delDictAndSubDict(String dictFlow, String recordStatus);

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
    SysDict readDict(String dictTypeId, String dictId,String orgFlow);

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

    List<SysDict> searchDictListByDictTypeIdAndDictName(String dictTypeId,String dictName);




//	public List<SysDict> searchDictListByDictTypeIdLike(String dictTypeId);

    /**
     * 根据字典类型id和字典流水号查找字典数据但是不包含该字典本身
     *
     * @param dict
     * @return
     */
    List<SysDict> searchDictListByDictTypeIdNotIncludeSelf(SysDict dict);

    List<SysDict> searchAllSecondLevelDictListByDictTypeIdNotIncludeSelf(String dictTypeId ,SysDict dict);

    /**
     * 给字典排序
     *
     * @param dictFlows
     */
    void saveOrder(String[] dictFlows);

    /**
     * 根据字典类型id查询所有的字典
     *
     * @param dictTypeId 字典类型id
     * @param isShowAll  是否查询所有 true:查询所有 false:查询启用的
     * @return
     */
    List<SysDict> searchDictListAllByDictTypeId(String dictTypeId, boolean isShowAll);

    //导入字典
    int importDict(MultipartFile file,String dictTypeId);

    List<SysDict> searchDictListByDictTypeIdAndDictId(String dictTypeId, String dictId);

    //根据条件查询字典表单
    List<DictForm> searchDictForm(DictForm dictForm);
    //编辑
    int editDictForm(DictForm dictForm);

    int updateSchoolSubmit(Map<String,Object> map);

    int saveSchoolSubmit(List<String> dictFlowList);

    int updateCheckAll(Map<String, Object> param);

    int updateSchoolNotSubmit(List<String> dictFlowList);

    SysDict searchDict(SysDict dictSearch);
}
