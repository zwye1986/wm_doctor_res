package com.pinde.sci.biz.jszy.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IJsResUserBlackBiz;
import com.pinde.sci.biz.jszy.IJszyResUserBlackBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.JsresUserBalcklistMapper;
import com.pinde.sci.dao.jsres.JsResUserBalckListExtMapper;
import com.pinde.sci.dao.jszy.JszyResUserBalckListExtMapper;
import com.pinde.sci.model.mo.JsresUserBalcklist;
import com.pinde.sci.model.mo.JsresUserBalcklistExample;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2016/8/31.
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class JszyResUserBlackBizImpl implements IJszyResUserBlackBiz {

    @Autowired
    private JsresUserBalcklistMapper jsresUserBalcklistMapper;
    @Autowired
    private JszyResUserBalckListExtMapper jszyResUserBalckListExtMapper;

    /**
     * 根据条件查询
     * @param jsresUserBalcklist,operUserFlows,orgFlowList
     * @param userFlowList
     * @param orgFlowList
     * @return
     */
    @Override
    public List<JsresUserBalcklist> searchInfo(JsresUserBalcklist jsresUserBalcklist,List<String> userFlowList,List<String> orgFlowList){
        JsresUserBalcklistExample example = new JsresUserBalcklistExample();
        JsresUserBalcklistExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);

        if(StringUtil.isNotBlank(jsresUserBalcklist.getSessionNumber())){
            criteria.andSessionNumberEqualTo(jsresUserBalcklist.getSessionNumber());
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getOrgFlow())){
            criteria.andOrgFlowEqualTo(jsresUserBalcklist.getOrgFlow());
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getUserName())){
            criteria.andUserNameLike("%"+jsresUserBalcklist.getUserName()+"%");
        }
        if(StringUtil.isNotBlank(jsresUserBalcklist.getTrainingSpeName())){
            criteria.andTrainingSpeNameLike("%"+jsresUserBalcklist.getTrainingSpeName()+"%");
        }
        if(orgFlowList!=null && !orgFlowList.isEmpty()){
            criteria.andOrgFlowIn(orgFlowList);
        }
        example.setOrderByClause("create_time desc");
        return jsresUserBalcklistMapper.selectByExample(example);
    }

    @Override
    public List<JsresUserBalcklist> searchInfo(Map<String, Object> param) {
        return jszyResUserBalckListExtMapper.searchInfo(param);
    }

    @Override
    public Map<Object, Object> paraseBlackInfo(List<JsresUserBalcklist> blackLists, String sessionNumber, String speName) throws DocumentException{
        List<JsresUserBalcklist> newRecList = new ArrayList<JsresUserBalcklist>();
        Map<Object, Object> backInfoMap = new HashMap<Object, Object>();
        Map<Object, Object> countMap = new HashMap<Object, Object>();
        if (blackLists != null && !blackLists.isEmpty()) {
            for (JsresUserBalcklist black : blackLists) {
                String result = GlobalConstant.FLAG_Y;
                if (StringUtil.isNotBlank(speName) && StringUtil.isNotBlank(black.getTrainingSpeName())) {
                    if (!black.getTrainingSpeName().equals(speName)) {
                        result = GlobalConstant.FLAG_N;
                    }
                }
                if (GlobalConstant.FLAG_Y.equals(result)) {
                    String key = black.getRecordFlow();

                    newRecList.add(black);
                    countMap.put(black.getUserFlow(), GlobalConstant.FLAG_F);
                }
            }
            backInfoMap.put(GlobalConstant.FLAG_F, countMap);
            backInfoMap.put(GlobalConstant.FLAG_Y, newRecList);
        }
        return backInfoMap;
    }

    /**
     * 保存、更新黑名单返回主键
     * @param black
     * @return
     */
    @Override
    public int saveBlack(JsresUserBalcklist black) {
        if(StringUtil.isNotBlank(black.getRecordFlow())){
            GeneralMethod.setRecordInfo(black, false);
            int ret = jsresUserBalcklistMapper.updateByPrimaryKeySelective(black);
            return ret;
        }else{
            black.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(black, true);
            int ret = jsresUserBalcklistMapper.insert(black);
            return ret;
        }
    }

    /**
     * 根据用户ID查询黑名单
     * @param userIdNo
     * @return
     */
    @Override
    public JsresUserBalcklist searchInfoByIdNo(String userIdNo) {
        JsresUserBalcklist jsresUserBalcklist = jszyResUserBalckListExtMapper.selectByIdNo(userIdNo);
        return jsresUserBalcklist;
    }

}
