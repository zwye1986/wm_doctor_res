package com.pinde.sci.biz.jsres;

import com.pinde.sci.model.vo.ResDeptRelStdDeptVO;
import com.pinde.sci.model.vo.ResSpeBaseStdDeptVO;
import com.pinde.sci.model.vo.ResStandardDeptVO;
import com.pinde.sci.model.vo.SysDeptVO;

import java.util.List;

public interface IJsResDeptManagementBiz {
    List<ResStandardDeptVO> searchStandardDept(ResStandardDeptVO standardDeptVO);

    List<ResStandardDeptVO> searchStandardDeptLevelAllByNameFuzzy(ResStandardDeptVO standardDeptVO);

    int saveStandardDept(ResStandardDeptVO standardDeptVO);

    int updateStandardDept(ResStandardDeptVO standardDeptVO);

    int batchUpdateStandardDeptSelective(List<ResStandardDeptVO> standardDeptVOList);

    int deleteStandardDept(ResStandardDeptVO standardDeptVO);

    int deleteStandardDeptByDeptFlow(String standardDeptFlow);

    List<ResSpeBaseStdDeptVO> searchSpeBaseStdDept(ResSpeBaseStdDeptVO speBaseStdDeptVO);

    int updateSpeBaseStdDept(ResSpeBaseStdDeptVO speBaseStdDeptVO);

    int updateSpeBaseStdDeptSelective(ResSpeBaseStdDeptVO speBaseStdDeptVO);

    int saveSpeBaseStdDept(ResSpeBaseStdDeptVO speBaseStdDeptVO);

    int deleteSpeBaseStdDeptByKey(String speBaseStdDept);

    List<ResDeptRelStdDeptVO> searchResDeptRelStdDept(ResDeptRelStdDeptVO deptRelStdDeptVO);

    int saveDeptRelStdDept(ResDeptRelStdDeptVO deptRelStdDeptVO);

    int updateDeptRelStdDept(ResDeptRelStdDeptVO deptRelStdDeptVO);

    int deleteBaseDeptRelStdDeptByKey(String deptFlow);

    List<SysDeptVO> selectSysDeptRelStdDept(SysDeptVO sysDeptVO);
}
