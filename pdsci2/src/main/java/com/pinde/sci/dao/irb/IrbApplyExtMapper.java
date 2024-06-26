package com.pinde.sci.dao.irb;

import com.pinde.sci.form.irb.IrbApplyForm;
import com.pinde.sci.model.mo.IrbApply;

import java.util.List;
import java.util.Map;

public interface IrbApplyExtMapper {
    List<IrbApply> queryList(IrbApplyForm form);

    List<IrbApply> searchIrbList(String userFlow);

    List<IrbApply> searchCommitteeIrbList(Map<String, Object> paramMap);

    List<IrbApply> searchUnReviewIrbs(Map<String, Object> paramMap);

    int searchOrgCount(String orgFlow);

    List<IrbApply> searchTrackIrbList();
}
