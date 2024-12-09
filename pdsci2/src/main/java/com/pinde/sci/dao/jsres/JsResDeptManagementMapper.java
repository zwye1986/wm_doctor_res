package com.pinde.sci.dao.jsres;

import com.pinde.core.vo.ResDeptRelStdDeptVO;
import com.pinde.core.vo.ResSpeBaseStdDeptVO;
import com.pinde.core.vo.ResStandardDeptVO;
import com.pinde.core.vo.SysDeptVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface JsResDeptManagementMapper {
    List<ResStandardDeptVO> searchStandardDept(ResStandardDeptVO standardDeptVO);

    List<ResStandardDeptVO> searchStandardDeptLevelAllByNameFuzzy(ResStandardDeptVO standardDeptVO);

    int saveStandardDept(ResStandardDeptVO standardDeptVO);

    int updateStandardDept(ResStandardDeptVO standardDeptVO);

    int batchUpdateStandardDeptSelective(@Param("standardDeptVOList") List<ResStandardDeptVO> standardDeptVOList);

    int deleteStandardDept(ResStandardDeptVO standardDeptVO);

    List<ResSpeBaseStdDeptVO> searchSpeBaseStdDept(ResSpeBaseStdDeptVO speBaseStdDeptVO);

    int updateSpeBaseStdDept(ResSpeBaseStdDeptVO speBaseStdDeptVO);

    int updateSpeBaseStdDeptSelective(ResSpeBaseStdDeptVO speBaseStdDeptVO);

    int saveSpeBaseStdDept(ResSpeBaseStdDeptVO speBaseStdDeptVO);

    int deleteSpeBaseStdDeptByKey(@Param("speBaseStdDeptFlow") String speBaseStdDeptFlow);

    List<ResDeptRelStdDeptVO> selectResDeptRelStdDept(ResDeptRelStdDeptVO deptRelStdDeptVO);

    int saveDeptRelStdDept(ResDeptRelStdDeptVO deptRelStdDeptVO);

    int updateDeptRelStdDept(ResDeptRelStdDeptVO deptRelStdDeptVO);

    int deleteBaseDeptRelStdDeptByKey(@Param("deptFlow") String deptFlow);

    List<SysDeptVO> selectSysDeptRelStdDept(SysDeptVO sysDeptVO);
}
