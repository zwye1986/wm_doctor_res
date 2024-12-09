package com.pinde.sci.biz.sch.impl;

import com.pinde.core.common.enums.sch.SchCycleTypeEnum;
import com.pinde.core.common.enums.sch.SchSelYearEnum;
import com.pinde.core.util.BeanUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sch.ISchDeptBiz;
import com.pinde.sci.biz.sch.ISchRotationCfgBiz;
import com.pinde.sci.biz.sys.IDeptBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.SchRotationOrgCfgMapper;
import com.pinde.sci.dao.base.SchRotationOrgDeptMapper;
import com.pinde.sci.dao.base.SchRotationOrgGroupMapper;
import com.pinde.sci.dao.base.SysOrgMapper;
import com.pinde.sci.dao.sch.SchRotationCfgExtMapper;
import com.pinde.sci.form.sch.SchRotationOrgDeptForm;
import com.pinde.sci.form.sch.SchRotationOrgGroupForm;
import com.pinde.sci.form.sch.SchRotationOrgSchDeptForm;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class SchRotationCfgBizImpl implements ISchRotationCfgBiz {

    @Autowired
    private SchRotationOrgGroupMapper rotationGroupMapper;
    @Autowired
    private SchRotationOrgDeptMapper rotationDeptMapper;
    @Autowired
    private SchRotationCfgExtMapper rotationCfgMapper;
    @Autowired
    private SchRotationOrgCfgMapper cycleCfgMapper;
    @Autowired
    private SysOrgMapper orgMapper;
    @Autowired
    private ISchDeptBiz schDeptBiz;
    @Autowired
    private IDeptBiz sysDeptBiz;

    @Override
    public List<SchRotationOrgGroup> searchSchRotationOrgGroup(Map<String, String> paramMap) {

        SchRotationOrgGroupExample example = new SchRotationOrgGroupExample();
        SchRotationOrgGroupExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(paramMap.get("rotationFlow"))){
            criteria.andRotationFlowEqualTo(paramMap.get("rotationFlow"));
        }
        if(StringUtil.isNotBlank(paramMap.get("orgFlow"))){
            criteria.andOrgFlowEqualTo(paramMap.get("orgFlow"));
        }
        if(StringUtil.isNotBlank(paramMap.get("sessionNumber"))){
            criteria.andSessionNumberEqualTo(paramMap.get("sessionNumber"));
        }
        if(StringUtil.isNotBlank(paramMap.get("selectYear"))){
            criteria.andSelectYearEqualTo(paramMap.get("selectYear"));
        }
        if(StringUtil.isNotBlank(paramMap.get("standardGroupFlow"))){
            criteria.andStandardGroupFlowEqualTo(paramMap.get("standardGroupFlow"));
        }
        example.setOrderByClause("ORDINAL ,SCH_STAGE_ID ");
        return rotationGroupMapper.selectByExample(example);
    }

    @Override
    public List<SchRotationOrgDept> searchSchRotationOrgDept(Map<String,String> paramMap) {
        SchRotationOrgDeptExample example = new SchRotationOrgDeptExample();
        SchRotationOrgDeptExample.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotBlank(paramMap.get("rotationFlow"))){
            criteria.andRotationFlowEqualTo(paramMap.get("rotationFlow"));
        }
        if(StringUtil.isNotBlank(paramMap.get("orgFlow"))){
            criteria.andOrgFlowEqualTo(paramMap.get("orgFlow"));
        }
        if(StringUtil.isNotBlank(paramMap.get("sessionNumber"))){
            criteria.andSessionNumberEqualTo(paramMap.get("sessionNumber"));
        }
        if(StringUtil.isNotBlank(paramMap.get("recordStatus"))){
            criteria.andRecordStatusEqualTo(paramMap.get("recordStatus"));
        }
        if(StringUtil.isNotBlank(paramMap.get("selectYear"))){
            criteria.andSelectYearEqualTo(paramMap.get("selectYear"));
        }
        if(StringUtil.isNotBlank(paramMap.get("standardDeptId"))){
            criteria.andStandardDeptIdEqualTo(paramMap.get("standardDeptId"));
        }
        if(StringUtil.isNotBlank(paramMap.get("groupFlow"))){
            criteria.andGroupFlowEqualTo(paramMap.get("groupFlow"));
        }
        if("schDeptFlowIsNull".equals(paramMap.get("schDeptFlowIsNull"))){
            criteria.andSchDeptFlowIsNull();
        }
        if("schDeptFlowIsNotNull".equals(paramMap.get("schDeptFlowIsNotNull"))){
            criteria.andSchDeptFlowIsNotNull();
        }
        example.setOrderByClause("ORDINAL");
        return rotationDeptMapper.selectByExample(example);
    }

    @Override
    public int saveSchRotationOrgDept(SchRotationOrgDept newDept) {
        if(newDept!=null)
        {
            if(StringUtil.isNotBlank(newDept.getRecordFlow()))
            {

                return updateDept(newDept);
            }else{

                return saveDept(newDept);
            }
        }
        return 0;
    }

    @Override
    public int checkRotationCfg(String orgFlow, String selectYear, String sessionNumber, String rotationFlow) {
        return rotationCfgMapper.checkRotationCfg(orgFlow,selectYear,sessionNumber,rotationFlow);
    }

    @Override
    public SchRotationOrgCfg readOrgRotationCycleType(String rotationFlow, String orgFlow, String sessionNumber) {
        if(StringUtil.isNotBlank(rotationFlow)&&StringUtil.isNotBlank(orgFlow)&&StringUtil.isNotBlank(sessionNumber))
        {
            SchRotationOrgCfgExample example=new SchRotationOrgCfgExample();
            example.createCriteria().andOrgFlowEqualTo(orgFlow).andSessionNumberEqualTo(sessionNumber).andRotationFlowEqualTo(rotationFlow)
                    .andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
            List<SchRotationOrgCfg> list=cycleCfgMapper.selectByExample(example);
            if(list!=null&&list.size()>0)
            {
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public int checkSelNum(String orgFlow, String sessionNumber, String rotationFlow) {
        return rotationCfgMapper.checkSelNum(orgFlow, sessionNumber, rotationFlow);
    }

    @Override
    public int checkSchNum(String orgFlow, String sessionNumber, String rotationFlow) {
        return rotationCfgMapper.checkSchNum(orgFlow, sessionNumber, rotationFlow);
    }

    @Override
    public void saveCycleCfg(String orgFlow, String sessionNumber, String rotationFlow, String cycleTypeId) {

        SchRotationOrgCfg cfg=readOrgRotationCycleType(rotationFlow,orgFlow,sessionNumber);
        if(cfg==null)
        {
            cfg=new SchRotationOrgCfg();
            cfg.setSessionNumber(sessionNumber);
            cfg.setRotationFlow(rotationFlow);
            cfg.setOrgFlow(orgFlow);
        }
        cfg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        cfg.setCycleTypeId(cycleTypeId);
        cfg.setCycleTypeName(SchCycleTypeEnum.getNameById(cycleTypeId));
        int c=saveCfg(cfg);
        if(c==1)
        {
            //删除学员选科信息
            rotationCfgMapper.delSelInfo(orgFlow,sessionNumber,rotationFlow, "");
            //重置学员选科未选科
            rotationCfgMapper.updateSelN(orgFlow,sessionNumber,rotationFlow, "");
        }
    }

    @Override
    public void saveCfg(List<SchRotationOrgGroupForm> groupForms, String orgFlow, String selectYear, String sessionNumber, String rotationFlow) throws Exception {
        //
        SysOrg org=orgMapper.selectByPrimaryKey(orgFlow);
        Map<String,SchDept> deptMap=new HashMap<>();
        List<SysDept> sysDeptList = sysDeptBiz.searchDeptByOrg(orgFlow);
        if(sysDeptList!=null && sysDeptList.size()>0){
            List<String> deptFlows = new ArrayList<String>();
            for(SysDept dept : sysDeptList){
                deptFlows.add(dept.getDeptFlow());
            }
            List<SchDept> deptList = schDeptBiz.searchDeptByDeotFlows(deptFlows);
            if(deptList!=null)
            {
                for(SchDept dept:deptList)
                {
                    deptMap.put(dept.getSchDeptFlow(),dept);
                }
            }
        }
        //所有组信息
        List<SchRotationOrgGroup> groups=new ArrayList<>();
        //所有标准科室信息 主要是用来标记是否被移除
        List<SchRotationOrgDept> baseDepts=new ArrayList<>();
        List<SchRotationOrgDept> schDepts=new ArrayList<>();
        List<String> orgDeptFlows=new ArrayList<>();
        for(SchRotationOrgGroupForm groupForm:groupForms)
        {
            SchRotationOrgGroup group=new SchRotationOrgGroup();
            group.setGroupFlow(groupForm.getGroupFlow());
            group.setSchMonth(groupForm.getSchMonth());
            group.setOneSchMonth(groupForm.getOneSchMonth());
            group.setTwoSchMonth(groupForm.getTwoSchMonth());
            group.setThreeSchMonth(groupForm.getThreeSchMonth());
            groups.add(group);
            List<SchRotationOrgDeptForm> orgStandardDeptList=groupForm.getOrgStandardDeptList();
            if(orgStandardDeptList!=null)
            {
                for(SchRotationOrgDeptForm deptForm:orgStandardDeptList)
                {
                    SchRotationOrgDept baseDept=new SchRotationOrgDept();
                    baseDept.setRecordFlow(deptForm.getRecordFlow());
                    baseDept.setRecordStatus(deptForm.getRecordStatus());
                    baseDepts.add(baseDept);
                    List<SchRotationOrgSchDeptForm> orgSchDeptList=deptForm.getOrgSchDeptList();
                    if(orgSchDeptList!=null)
                    {
                        for(SchRotationOrgSchDeptForm schDeptForm:orgSchDeptList)
                        {
                            if(StringUtil.isNotBlank(schDeptForm.getSchDeptFlow()))
                            {
                                SchRotationOrgDept d=new SchRotationOrgDept();
                                BeanUtil.mergeNotSameClassObject(schDeptForm,d);
                                d.setOrgFlow(orgFlow);
                                d.setRotationFlow(rotationFlow);
                                d.setOrgName(org.getOrgName());
                                d.setSessionNumber(sessionNumber);
                                d.setSelectYear(selectYear);
                                SchDept dept=deptMap.get(schDeptForm.getSchDeptFlow());
                                if(dept==null)
                                {
                                    dept=schDeptBiz.readSchDept(schDeptForm.getSchDeptFlow());
                                }
                                if(dept!=null)
                                {
                                    d.setDeptFlow(dept.getDeptFlow());
                                    d.setDeptName(dept.getDeptName());
                                }
                                schDepts.add(d);
                                if(StringUtil.isNotBlank(schDeptForm.getRecordFlow()))
                                {
                                    orgDeptFlows.add(schDeptForm.getRecordFlow());
                                }
                            }
                        }
                    }
                }
            }
        }
        //保存组信息
        for(SchRotationOrgGroup group:groups)
        {
            saveOrgGroup(group);
        }
        //保存标准科室信息
        for(SchRotationOrgDept d:baseDepts)
        {
            saveOrgDept(d);
        }
        // 保存轮转科室信息
        delOrgSchDepts(orgDeptFlows,orgFlow,selectYear,sessionNumber,rotationFlow);
        for(SchRotationOrgDept d:schDepts)
        {
            saveOrgDept(d);
        }

        if(SchSelYearEnum.One.getId().equals(selectYear))
        {
            selectYear="1";
        }
        if(SchSelYearEnum.Two.getId().equals(selectYear))
        {
            selectYear="2";
        }
        if(SchSelYearEnum.Three.getId().equals(selectYear))
        {
            selectYear="3";
        }
        //删除学员选科信息
        rotationCfgMapper.delSelInfo(orgFlow,sessionNumber,rotationFlow,selectYear);
        //重置学员选科未选科
        rotationCfgMapper.updateSelN(orgFlow,sessionNumber,rotationFlow,selectYear);
    }

    private void delOrgSchDepts(List<String> orgDeptFlows, String orgFlow, String selectYear, String sessionNumber, String rotationFlow) {
        rotationCfgMapper.delOrgSchDepts(orgDeptFlows,orgFlow,selectYear,sessionNumber,rotationFlow);
    }

    private int saveOrgDept(SchRotationOrgDept d) {
        if(StringUtil.isBlank(d.getRecordFlow()))
        {
            d.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(d, true);
            return rotationDeptMapper.insertSelective(d);
        }else{
            GeneralMethod.setRecordInfo(d, false);
            return rotationDeptMapper.updateByPrimaryKeySelective(d);
        }
    }

    private int saveOrgGroup(SchRotationOrgGroup group) {
        if(StringUtil.isBlank(group.getGroupFlow()))
        {
            group.setGroupFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(group, true);
            return rotationGroupMapper.insertSelective(group);
        }else{
            GeneralMethod.setRecordInfo(group, false);
            return rotationGroupMapper.updateByPrimaryKeySelective(group);
        }
    }

    private int saveCfg(SchRotationOrgCfg cfg) {
        if(StringUtil.isBlank(cfg.getRecordFlow()))
        {
            cfg.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(cfg, true);
            return cycleCfgMapper.insertSelective(cfg);
        }else{
            GeneralMethod.setRecordInfo(cfg, false);
            return cycleCfgMapper.updateByPrimaryKeySelective(cfg);
        }
    }

    private int saveDept(SchRotationOrgDept newDept) {
        newDept.setRecordFlow(PkUtil.getUUID());
        GeneralMethod.setRecordInfo(newDept, true);
        return rotationDeptMapper.insertSelective(newDept);
    }

    private int updateDept(SchRotationOrgDept newDept) {
        GeneralMethod.setRecordInfo(newDept, false);
        return rotationDeptMapper.updateByPrimaryKeySelective(newDept);
    }

    @Override
    public int saveSchRotationOrgGroup(SchRotationOrgGroup rotationGroup) {
        if(rotationGroup != null){
            if(StringUtil.isNotBlank(rotationGroup.getGroupFlow())){
                return update(rotationGroup);
            }else{
                return save(rotationGroup);
            }
        }
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
    }

    public int save(SchRotationOrgGroup rotationGroup){
        rotationGroup.setGroupFlow(PkUtil.getUUID());
        GeneralMethod.setRecordInfo(rotationGroup, true);
        return rotationGroupMapper.insertSelective(rotationGroup);
    }

    public int update(SchRotationOrgGroup rotationGroup){
        GeneralMethod.setRecordInfo(rotationGroup, false);
        return rotationGroupMapper.updateByPrimaryKeySelective(rotationGroup);
    }

}
