package com.pinde.sci.dao.sys;


import com.pinde.sci.form.sys.SubjectForm;


/**
 * subject扩展mapper
 * @author huangfei
 *
 */
public interface SysSubjCodeExtMapper {

    int updateByIds(SubjectForm form);

    int updateParentId(SubjectForm form);
}
