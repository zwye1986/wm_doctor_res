package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResDeptManagementBiz;
import com.pinde.sci.dao.jsres.JsResDeptManagementMapper;
import com.pinde.sci.model.vo.ResDeptRelStdDeptVO;
import com.pinde.sci.model.vo.ResSpeBaseStdDeptVO;
import com.pinde.sci.model.vo.ResStandardDeptVO;
import com.pinde.sci.model.vo.SysDeptVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class JsResDeptManagementBizImpl implements IJsResDeptManagementBiz {
    @Autowired
    private JsResDeptManagementMapper deptManagementMapper;

    @Override
    public List<ResStandardDeptVO> searchStandardDept(ResStandardDeptVO standardDeptVO) {
        return deptManagementMapper.searchStandardDept(standardDeptVO);
    }

    @Override
    public List<ResStandardDeptVO> searchStandardDeptLevelAllByNameFuzzy(ResStandardDeptVO standardDeptVO) {
        if(standardDeptVO == null || StringUtil.isEmpty(standardDeptVO.getStandardDeptNameFuzzy())) {
            return Collections.emptyList();
        }

        return deptManagementMapper.searchStandardDeptLevelAllByNameFuzzy(standardDeptVO);
    }

    @Override
    public int saveStandardDept(ResStandardDeptVO standardDeptVO) {
        return deptManagementMapper.saveStandardDept(standardDeptVO);
    }

    @Override
    public int updateStandardDept(ResStandardDeptVO standardDeptVO) {
        return deptManagementMapper.updateStandardDept(standardDeptVO);
    }

    @Override
    public int batchUpdateStandardDeptSelective(List<ResStandardDeptVO> standardDeptVOList) {
        return deptManagementMapper.batchUpdateStandardDeptSelective(standardDeptVOList);
    }

    /**
     * 使用该方法时注意要判空，防止全表删除
     * @param standardDeptVO
     * @return
     */
    @Override
    public int deleteStandardDept(ResStandardDeptVO standardDeptVO) {
        return deptManagementMapper.deleteStandardDept(standardDeptVO);
    }

    @Override
    public int deleteStandardDeptByDeptFlow(String standardDeptFlow) {
        if(StringUtil.isEmpty(standardDeptFlow)) {
            return 0;
        }

        ResStandardDeptVO standardDeptVO = new ResStandardDeptVO();
        standardDeptVO.setStandardDeptFlow(standardDeptFlow);
        return deptManagementMapper.deleteStandardDept(standardDeptVO);
    }

    @Override
    public List<ResSpeBaseStdDeptVO> searchSpeBaseStdDept(ResSpeBaseStdDeptVO speBaseStdDeptVO) {
        return deptManagementMapper.searchSpeBaseStdDept(speBaseStdDeptVO);
    }

    @Override
    public int updateSpeBaseStdDept(ResSpeBaseStdDeptVO speBaseStdDeptVO) {
        return deptManagementMapper.updateSpeBaseStdDept(speBaseStdDeptVO);
    }

    @Override
    public int updateSpeBaseStdDeptSelective(ResSpeBaseStdDeptVO speBaseStdDeptVO) {
        return deptManagementMapper.updateSpeBaseStdDeptSelective(speBaseStdDeptVO);
    }

    @Override
    public int saveSpeBaseStdDept(ResSpeBaseStdDeptVO speBaseStdDeptVO) {
        return deptManagementMapper.saveSpeBaseStdDept(speBaseStdDeptVO);
    }

    @Override
    public int deleteSpeBaseStdDeptByKey(String speBaseStdDeptFlow) {
        return deptManagementMapper.deleteSpeBaseStdDeptByKey(speBaseStdDeptFlow);
    }

    @Override
    public List<ResDeptRelStdDeptVO> searchResDeptRelStdDept(ResDeptRelStdDeptVO deptRelStdDeptVO) {
        return deptManagementMapper.selectResDeptRelStdDept(deptRelStdDeptVO);
    }

    @Override
    public int saveDeptRelStdDept(ResDeptRelStdDeptVO deptRelStdDeptVO) {
        return deptManagementMapper.saveDeptRelStdDept(deptRelStdDeptVO);
    }

    @Override
    public int updateDeptRelStdDept(ResDeptRelStdDeptVO deptRelStdDeptVO) {
        return deptManagementMapper.updateDeptRelStdDept(deptRelStdDeptVO);
    }

    @Override
    public int deleteBaseDeptRelStdDeptByKey(String deptFlow) {
        return deptManagementMapper.deleteBaseDeptRelStdDeptByKey(deptFlow);
    }

    @Override
    public List<SysDeptVO> selectSysDeptRelStdDept(SysDeptVO sysDeptVO) {
        return deptManagementMapper.selectSysDeptRelStdDept(sysDeptVO);
    }
}
