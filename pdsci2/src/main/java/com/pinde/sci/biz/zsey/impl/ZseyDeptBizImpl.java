package com.pinde.sci.biz.zsey.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.zsey.IZseyDeptBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ZseyAppointMainMapper;
import com.pinde.sci.dao.base.ZseyAppointMaterialMapper;
import com.pinde.sci.dao.zsey.ZseyBaseExtMapper;
import com.pinde.sci.enums.zsey.ZseyAuditStatusEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class ZseyDeptBizImpl implements IZseyDeptBiz {

    @Autowired
    private ZseyBaseExtMapper extMapper;
    @Autowired
    private ZseyAppointMainMapper mainMapper;
    @Autowired
    private ZseyAppointMaterialMapper materialMapper;

    @Override
    public List<ZseyAppointMain> queryAppointList(Map<String, String> map) {
        String beginDate = map.get("trainBeginDate");
        String endDate = map.get("trainEndDate");
        String teacherName = map.get("teacherName");
        String auditStatusId = map.get("auditStatusId");
        ZseyAppointMainExample example = new ZseyAppointMainExample();
        ZseyAppointMainExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andApplicantFlowEqualTo(GlobalContext.getCurrentUser().getUserFlow());
        if(StringUtil.isNotBlank(beginDate)){
            criteria.andTrainDateGreaterThanOrEqualTo(beginDate);
        }
        if(StringUtil.isNotBlank(endDate)){
            criteria.andTrainDateLessThanOrEqualTo(endDate);
        }
        if(StringUtil.isNotBlank(teacherName)){
            criteria.andTeacherNameLike("%"+teacherName+"%");
        }
        if(StringUtil.isNotBlank(auditStatusId)){
            criteria.andAuditStatusIdEqualTo(auditStatusId);
        }
        example.setOrderByClause("TRAIN_DATE DESC,BEGIN_TIME DESC");
        return mainMapper.selectByExample(example);
    }

    @Override
    public List<SysUser> searchTeacher() {
        return extMapper.searchTeacher();
    }

    @Override
    public int saveAppoint(Map<String, Object> param) {
        int count = 0;
        ZseyAppointMain main = (ZseyAppointMain)param.get("appointMain");
        List<String> recordFlowList = (List<String>)param.get("recordFlowList");
        List<String> materialFlowList = (List<String>)param.get("materialFlowList");
        List<String> materialNameList = (List<String>)param.get("materialNameList");
        List<String> materialNumList = (List<String>)param.get("materialNumList");
        main.setApplicantFlow(GlobalContext.getCurrentUser().getUserFlow());//申请人名字和电话可填写，流水号必须为账户发起人
        if(StringUtil.isNotBlank(main.getAppointFlow())){
            GeneralMethod.setRecordInfo(main,false);
            count += mainMapper.updateByPrimaryKeySelective(main);
            if(null != recordFlowList && !recordFlowList.isEmpty()) {
                //删除不在此次记录中的历史数据
                ZseyAppointMaterialExample example = new ZseyAppointMaterialExample();
                example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andAppointFlowEqualTo(main.getAppointFlow()).andRecordFlowNotIn(recordFlowList);
                materialMapper.deleteByExample(example);
                //只是基于历史数据修改
                ZseyAppointMaterial material = new ZseyAppointMaterial();
                for(int i=0; i<recordFlowList.size(); i++){
                    material.setRecordFlow(recordFlowList.get(i));
                    material.setMaterialNumber(materialNumList.get(i));
                    GeneralMethod.setRecordInfo(material,false);
                    count += materialMapper.updateByPrimaryKeySelective(material);
                }
            }else{
                //删除历史数据
                ZseyAppointMaterialExample example = new ZseyAppointMaterialExample();
                example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andAppointFlowEqualTo(main.getAppointFlow());
                materialMapper.deleteByExample(example);
                if(null != materialFlowList && !materialFlowList.isEmpty()){
                    //新增设备耗材信息
                    ZseyAppointMaterial material = new ZseyAppointMaterial();
                    material.setAppointFlow(main.getAppointFlow());
                    for(int i=0; i<materialFlowList.size(); i++){
                        material.setRecordFlow(PkUtil.getUUID());
                        material.setMaterialFlow(materialFlowList.get(i));
                        material.setMaterialName(materialNameList.get(i));
                        material.setMaterialNumber(materialNumList.get(i));
                        GeneralMethod.setRecordInfo(material,true);
                        count += materialMapper.insertSelective(material);
                    }
                }
            }
            return count;
        }else{
            //添加培训预约记录
            String pk = PkUtil.getUUID();
            main.setAppointFlow(pk);
            main.setAuditStatusId(ZseyAuditStatusEnum.Passing.getId());
            main.setAuditStatusName(ZseyAuditStatusEnum.Passing.getName());//初始为待审核状态
            GeneralMethod.setRecordInfo(main,true);
            count += mainMapper.insertSelective(main);
            if(null != materialFlowList && !materialFlowList.isEmpty()){
                //新增设备信息
                ZseyAppointMaterial material = new ZseyAppointMaterial();
                material.setAppointFlow(pk);
                for(int i=0; i<materialFlowList.size(); i++){
                    material.setRecordFlow(PkUtil.getUUID());
                    material.setMaterialFlow(materialFlowList.get(i));
                    material.setMaterialName(materialNameList.get(i));
                    material.setMaterialNumber(materialNumList.get(i));
                    GeneralMethod.setRecordInfo(material,true);
                    count += materialMapper.insertSelective(material);
                }
            }
            return count;
        }
    }

    @Override
    public int delAppoint(String appointFlow) {
        return mainMapper.deleteByPrimaryKey(appointFlow);
    }

    @Override
    public ZseyAppointMain queryAppointByFlow(String appointFlow) {
        return mainMapper.selectByPrimaryKey(appointFlow);
    }

    @Override
    public List<ZseyAppointMaterial> queryMaterialList(String appointFlow) {
        ZseyAppointMaterialExample example = new ZseyAppointMaterialExample();
        example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                .andAppointFlowEqualTo(appointFlow);
        return materialMapper.selectByExample(example);

    }

    @Override
    public List<Map<String, Object>> queryAppointMaterialList(String materialName) {
        return extMapper.queryAppointMaterialList(materialName);
    }

}
