package com.pinde.res.biz.gzzy.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gzzy.IGzzyResLiveTrainingBiz;
import com.pinde.res.biz.hbres.IHbResLiveTrainingBiz;
import com.pinde.res.dao.gzzy.ext.GzzyLiveTrainingMapper;
import com.pinde.res.dao.hbres.ext.HbRestarinNoticeExtMapper;
import com.pinde.sci.dao.base.ResTarinNoticeMapper;
import com.pinde.sci.dao.base.ResTrainingOpinionMapper;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class GzzyResLiveTrainingBizImpl implements IGzzyResLiveTrainingBiz {
    @Autowired
    private ResTrainingOpinionMapper trainingOpinionMapper;
    @Autowired
    private ResTarinNoticeMapper resTarinNoticeMapper;
    @Autowired
    private HbRestarinNoticeExtMapper resTarinNoticeExtMapper;
    @Autowired
    private GzzyLiveTrainingMapper gzzyLiveTrainingMapper;


    @Override
    public Map<String,String> read(String trainingOpinionFlow){
        return gzzyLiveTrainingMapper.read(trainingOpinionFlow);
    }

    @Override
    public void delOpinion(String trainingOpinionFlow) {
        gzzyLiveTrainingMapper.delOpinion(trainingOpinionFlow);
    }

    @Override
    public void edit(Map<String,Object> paramMap) {
        if(StringUtil.isNotBlank((String)paramMap.get("Record_Flow"))){
            gzzyLiveTrainingMapper.editOpinion(paramMap);
        }else{
            String currTime = DateUtil.getCurrDateTime();
            paramMap.put("Create_Time",currTime);
            gzzyLiveTrainingMapper.addOpinion(paramMap);
        }
    }

    @Override
    public List<Map<String,String>> readByOpinionUserFlow(Map<String,Object> paramMap) {
        return gzzyLiveTrainingMapper.readByOpinionUserFlow(paramMap);
    }

    @Override
    public List<ResTrainingOpinion> searchByOpinionUserContentAndReplayStatus(String opinionUserContent, String replayStatus,String orgFlow) {
        ResTrainingOpinionExample example = new ResTrainingOpinionExample();
        ResTrainingOpinionExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(opinionUserContent)){
            criteria.andOpinionUserContentLike("%"+opinionUserContent+"%");
        }
        if("Y".equals(replayStatus)){
            criteria.andReplyTimeIsNull();
        }
        if(StringUtil.isNotBlank(orgFlow)){
            criteria.andOrgFlowEqualTo(orgFlow);
        }
        example.setOrderByClause("EVA_TIME DESC");
        return trainingOpinionMapper.selectByExample(example);
    }

    @Override
    public Map<String,String> readNotice(String recordFlow) {
        return gzzyLiveTrainingMapper.readNotice(recordFlow);
    }


    @Override
    public int edit(ResTarinNotice tarinNotice,String userFlow) {
        if(tarinNotice!=null){
            if(StringUtil.isNotBlank(tarinNotice.getRecordFlow())){
                setRecordInfo(tarinNotice, false,userFlow);
                return resTarinNoticeMapper.updateByPrimaryKeySelective(tarinNotice);
            }else{
                tarinNotice.setRecordFlow(PkUtil.getUUID());
                setRecordInfo(tarinNotice, true,userFlow);
                return resTarinNoticeMapper.insertSelective(tarinNotice);
            }
        }
        return GlobalConstant.ZERO_LINE;
    }

    @Override
    public List<Map<String,String>> searchByTitleOrgFlow(Map<String,Object> paramMap) {
        return gzzyLiveTrainingMapper.readNoticeByOrgFlow(paramMap);
    }
    @Override
    public List<ResTarinNotice> searchByTitleOrgFlowAndWorkOrgId(String resNoticeTitle, String orgFlow, ResDoctor doctor) {
        return resTarinNoticeExtMapper.searchByTitleOrgFlowAndWorkOrgId(resNoticeTitle, orgFlow, doctor);
    }
    public static void setRecordInfo(Object obj, boolean isAdd,String userFlow) {
        Class<?> clazz = obj.getClass();
        try {
            if (isAdd) {
                Method setRecordStatusMethod = clazz.getMethod("setRecordStatus", String.class);
                setRecordStatusMethod.invoke(obj, GlobalConstant.RECORD_STATUS_Y);
                Method setCreateTime = clazz.getMethod("setCreateTime", String.class);
                setCreateTime.invoke(obj, DateUtil.getCurrDateTime());
                Method setCreateUserFlow = clazz.getMethod("setCreateUserFlow", String.class);
                if (userFlow != null) {
                    setCreateUserFlow.invoke(obj, userFlow);
                }
            }
            Method setModifyTime = clazz.getMethod("setModifyTime", String.class);
            setModifyTime.invoke(obj, DateUtil.getCurrDateTime());
            Method setModifyUserFlow = clazz.getMethod("setModifyUserFlow", String.class);
            if (userFlow != null) {
                setModifyUserFlow.invoke(obj, userFlow);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
