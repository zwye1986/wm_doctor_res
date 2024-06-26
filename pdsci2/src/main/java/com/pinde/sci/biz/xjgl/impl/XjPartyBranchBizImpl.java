package com.pinde.sci.biz.xjgl.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.edu.IEduUserBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.xjgl.IXjEduUserBiz;
import com.pinde.sci.biz.xjgl.IXjPartyBranchBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.xjgl.XjPartyBranchExtMapper;
import com.pinde.sci.dao.xjgl.XjVoteManageExtMapper;
import com.pinde.sci.enums.sys.PoliticsAppearanceEnum;
import com.pinde.sci.form.xjgl.XjEduUserExtInfoForm;
import com.pinde.sci.form.xjgl.XjEduUserForm;
import com.pinde.sci.form.xjgl.XjRegisterDateForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduUserExt;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright njpdxx.com
 * @since 2018/2/6
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class XjPartyBranchBizImpl implements IXjPartyBranchBiz{
    @Autowired
    private XjPartyBranchExtMapper partyBranchExtMapper;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private EduUserMapper eduUserMapper;
    @Autowired
    private IXjEduUserBiz eduUserBiz;
    @Autowired
    private NydwVoteMainMapper mainMapper;
    @Autowired
    private NydwElectorMapper electorMapper;
    @Autowired
    private NydwVoteDetailMapper voteDetailMapper;
    @Autowired
    private XjVoteManageExtMapper voteManageExtMapper;
    @Autowired
    private NydwVoterMapper voterMapper;
    @Autowired
    private NydwSpecialTopicMapper specialTopicMapper;
    @Autowired
    private NydwTopicDetailMapper topicDetailMapper;
    @Autowired
    private NydwCaucusNoticeMapper caucusNoticeMapper;

    @Override
    public List<XjEduUserExt> searchPartyMembers(Map<String, String> map) {
        return partyBranchExtMapper.querypartyMembers(map);
    }

    @Override
    public int editPartyMembers(XjEduUserForm eduUserForm) {
        int num=0;
        SysUser user = eduUserForm.getSysUser();
        XjEduUserExtInfoForm eduUserExtInfoForm=eduUserForm.getEduUserExtInfoForm();
        SysUser oldUser = userBiz.readSysUser(user.getUserFlow());
        EduUser oldEduUser = eduUserMapper.selectByPrimaryKey(user.getUserFlow());
        if(oldUser!=null&&user!=null){
            oldUser.setPartyBranchId(user.getPartyBranchId());
            oldUser.setPartyBranchName(user.getPartyBranchName());
            oldUser.setPoliticsStatusId(user.getPoliticsStatusId());
            oldUser.setPoliticsStatusName(PoliticsAppearanceEnum.getNameById(user.getPoliticsStatusId()));
            num=userBiz.saveUser(oldUser);
        }
        if(oldEduUser!=null&& StringUtil.isNotBlank(oldEduUser.getContent())){
            XjEduUserExtInfoForm oldForm=eduUserBiz.parseExtInfoXml(oldEduUser.getContent());
            if(oldForm!=null&&eduUserExtInfoForm!=null){
                oldForm.setIsRelationInto(eduUserExtInfoForm.getIsRelationInto());
                oldForm.setJoinOrgTime(eduUserExtInfoForm.getJoinOrgTime());
                String content=getXmlFromExtInfo(oldForm);
                oldEduUser.setContent(content);
            }
            num=eduUserBiz.saveEduUser(oldEduUser);
        }
        return num;
    }
    @Override
    public List<NydwVoteMain> searchVoteMainList(NydwVoteMain main) {
        NydwVoteMainExample example=new NydwVoteMainExample();
        NydwVoteMainExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(main.getRecordFlow())){
            criteria.andRecordFlowEqualTo(main.getRecordFlow());
        }
        if(StringUtil.isNotBlank(main.getVoteName())){
            criteria.andVoteNameLike("%"+main.getVoteName()+"%");
        }
        if(StringUtil.isNotBlank(main.getBeginTime())){
            criteria.andBeginTimeGreaterThanOrEqualTo(main.getBeginTime());
        }
        if(StringUtil.isNotBlank(main.getEndTime())){
            criteria.andEndTimeLessThanOrEqualTo(main.getEndTime());
        }
        example.setOrderByClause("MODIFY_TIME DESC");
        return mainMapper.selectByExample(example);
    }

    @Override
    public List<NydwVoteMain> searchVoteMainList(Map<String, String> map) {
        return voteManageExtMapper.queryVoteMainList(map);
    }

    @Override
    public List<NydwElector> searchElectorList(NydwElector elector) {
        NydwElectorExample example=new NydwElectorExample();
        NydwElectorExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(elector.getVoteFlow())){
            criteria.andVoteFlowEqualTo(elector.getVoteFlow());
        }
        return electorMapper.selectByExample(example);
    }

    @Override
    public List<NydwVoter> searchVoterList(NydwVoter voter) {
        NydwVoterExample example=new NydwVoterExample();
        NydwVoterExample.Criteria criteria =example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(voter.getVoteFlow())){
            criteria.andVoteFlowEqualTo(voter.getVoteFlow());
        }
        if(StringUtil.isNotBlank(voter.getVoterFlow())){
            criteria.andVoterFlowEqualTo(voter.getVoterFlow());
        }
        return voterMapper.selectByExample(example);
    }

    @Override
    public List<NydwVoteDetail> searchVoteDetailList(NydwVoteDetail voteDetail) {
        NydwVoteDetailExample example=new NydwVoteDetailExample();
        NydwVoteDetailExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(voteDetail.getVoteFlow())){
            criteria.andVoteFlowEqualTo(voteDetail.getVoteFlow());
        }
        if(StringUtil.isNotBlank(voteDetail.getElectorFlow())){
            criteria.andElectorFlowEqualTo(voteDetail.getElectorFlow());
        }
        if(StringUtil.isNotBlank(voteDetail.getRecordFlow())){
            criteria.andRecordFlowEqualTo(voteDetail.getRecordFlow());
        }
        if(StringUtil.isNotBlank(voteDetail.getVoterFlow())){
            criteria.andVoterFlowEqualTo(voteDetail.getVoterFlow());
        }
        return voteDetailMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, String>> searchElectorList(String voteFlow) {
        Map<String,String> map=new HashMap<>();
        map.put("voteFlow",voteFlow);
        return voteManageExtMapper.queryElectorList(map);
    }

    @Override
    public List<Map<String, String>> searchVoterList(String voteFlow, String electorFlow) {
        Map<String,String> map=new HashMap<>();
        map.put("voteFlow",voteFlow);
        map.put("electorFlow",electorFlow);
        return voteManageExtMapper.queryVoterList(map);
    }

    @Override
    public int saveVoteManage(List<String> electorFlows, List<String> voterFlows, NydwVoteMain voteMain) {
        String voteFlow= PkUtil.getUUID();
        voteMain.setRecordFlow(voteFlow);
        GeneralMethod.setRecordInfo(voteMain, true);
        int num=mainMapper.insert(voteMain);
        if(num>0){
            for(String electorFlow:electorFlows){
                SysUser user=userBiz.findByFlow(electorFlow);
                ResDoctor resDoctor=resDoctorBiz.findByFlow(electorFlow);
                if(user!=null){
                    NydwElector elector=new NydwElector();
                    elector.setRecordFlow(PkUtil.getUUID());
                    elector.setVoteFlow(voteFlow);
                    elector.setPartyBranchId(user.getPartyBranchId());
                    elector.setPartyBranchName(user.getPartyBranchName());
                    elector.setElectorFlow(user.getUserFlow());
                    elector.setElectorName(user.getUserName());
                    elector.setSexId(user.getSexId());
                    elector.setSexName(user.getSexName());
                    if(resDoctor!=null){
                        elector.setPydwOrgFlow(resDoctor.getOrgFlow());
                        elector.setPydwOrgName(resDoctor.getOrgName());
                    }
                    GeneralMethod.setRecordInfo(elector, true);
                    electorMapper.insert(elector);
                }
            }
            for(String voterFlow:voterFlows){
                SysUser user=userBiz.findByFlow(voterFlow);
                ResDoctor resDoctor=resDoctorBiz.findByFlow(voterFlow);
                if(user!=null){
                    NydwVoter voter=new NydwVoter();
                    voter.setRecordFlow(PkUtil.getUUID());
                    voter.setVoteFlow(voteFlow);
                    voter.setPartyBranchId(user.getPartyBranchId());
                    voter.setPartyBranchName(user.getPartyBranchName());
                    voter.setVoterFlow(user.getUserFlow());
                    voter.setVoterName(user.getUserName());
                    voter.setSexId(user.getSexId());
                    voter.setSexName(user.getSexName());
                    if(resDoctor!=null){
                        voter.setPydwOrgFlow(resDoctor.getOrgFlow());
                        voter.setPydwOrgName(resDoctor.getOrgName());
                    }
                    GeneralMethod.setRecordInfo(voter, true);
                    voterMapper.insert(voter);
                }
            }
            return 1;
        }
        return 0;
    }

    @Override
    public int saveVoteDetail(NydwVoteDetail voteDetail) {
        int count=0;
        if(StringUtil.isNotBlank(voteDetail.getRecordFlow())){
            GeneralMethod.setRecordInfo(voteDetail,false);
            count = voteDetailMapper.updateByPrimaryKeySelective(voteDetail);
        }else{
            voteDetail.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(voteDetail,true);
            count =  voteDetailMapper.insert(voteDetail);
        }
        return count;
    }

    @Override
    public int delVoteDetail(NydwVoteDetail voteDetail) {
        NydwVoteDetailExample example=new NydwVoteDetailExample();
        NydwVoteDetailExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(voteDetail.getRecordFlow())){
            criteria.andRecordFlowEqualTo(voteDetail.getRecordFlow());
        }
        if(StringUtil.isNotBlank(voteDetail.getVoteFlow())){
            criteria.andVoteFlowEqualTo(voteDetail.getVoteFlow());
        }
        if(StringUtil.isNotBlank(voteDetail.getVoterFlow())){
            criteria.andVoterFlowEqualTo(voteDetail.getVoterFlow());
        }
        return voteDetailMapper.deleteByExample(example);
    }

    @Override
    public int delVoteMain(String recordFlow) {
        if(StringUtil.isNotBlank(recordFlow)){
            return mainMapper.deleteByPrimaryKey(recordFlow);
        }
        return 0;
    }

    @Override
    public int delElector(NydwElector elector) {
        NydwElectorExample example=new NydwElectorExample();
        NydwElectorExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(elector.getRecordFlow())){
            criteria.andRecordFlowEqualTo(elector.getRecordFlow());
        }
        if(StringUtil.isNotBlank(elector.getVoteFlow())){
            criteria.andVoteFlowEqualTo(elector.getVoteFlow());
        }
        if(StringUtil.isNotBlank(elector.getElectorFlow())){
            criteria.andElectorFlowEqualTo(elector.getElectorFlow());
        }
        return electorMapper.deleteByExample(example);
    }

    @Override
    public int delVoter(NydwVoter voter) {
        NydwVoterExample example=new NydwVoterExample();
        NydwVoterExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(voter.getRecordFlow())){
            criteria.andRecordFlowEqualTo(voter.getRecordFlow());
        }
        if(StringUtil.isNotBlank(voter.getVoteFlow())){
            criteria.andVoteFlowEqualTo(voter.getVoteFlow());
        }
        if(StringUtil.isNotBlank(voter.getVoterFlow())){
            criteria.andVoterFlowEqualTo(voter.getVoterFlow());
        }
        return voterMapper.deleteByExample(example);
    }

    @Override
    public List<NydwSpecialTopic> searchSpecialTopicList(NydwSpecialTopic specialTopic) {
        NydwSpecialTopicExample example=new NydwSpecialTopicExample();
        NydwSpecialTopicExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(specialTopic.getTopicFlow())){
            criteria.andTopicFlowEqualTo(specialTopic.getTopicFlow());
        }
        if(StringUtil.isNotBlank(specialTopic.getTopicName())){
            criteria.andTopicNameLike("%"+specialTopic.getTopicName()+"%");
        }
        if(StringUtil.isNotBlank(specialTopic.getCreateTime())){//发布时间
            criteria.andPublishTimeGreaterThanOrEqualTo(specialTopic.getCreateTime());
        }
        if(StringUtil.isNotBlank(specialTopic.getModifyTime())){//发布时间
            criteria.andPublishTimeLessThanOrEqualTo(specialTopic.getModifyTime());
        }
        example.setOrderByClause("MODIFY_TIME DESC");
        return specialTopicMapper.selectByExample(example);
    }

    @Override
    public int saveSpecialTopic(NydwSpecialTopic specialTopic) {
        if(StringUtil.isNotBlank(specialTopic.getTopicFlow())){
            GeneralMethod.setRecordInfo(specialTopic,false);
            return specialTopicMapper.updateByPrimaryKeySelective(specialTopic);
        }else{
            specialTopic.setTopicFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(specialTopic,true);
            return specialTopicMapper.insertSelective(specialTopic);
        }
    }

    @Override
    public List<NydwTopicDetail> topicDetailList(NydwTopicDetail topicDetail) {
        NydwTopicDetailExample example=new NydwTopicDetailExample();
        NydwTopicDetailExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(topicDetail.getTopicFlow())){
            criteria.andTopicFlowEqualTo(topicDetail.getTopicFlow());
        }
        if(StringUtil.isNotBlank(topicDetail.getUserFlow())){
            criteria.andUserFlowEqualTo(topicDetail.getUserFlow());
        }
        example.setOrderByClause("TALK_TIME");
        return topicDetailMapper.selectByExample(example);
    }

    @Override
    public int saveTopicDetail(NydwTopicDetail topicDetail) {
        if(StringUtil.isNotBlank(topicDetail.getRecordFlow())){
            GeneralMethod.setRecordInfo(topicDetail,false);
            return topicDetailMapper.updateByPrimaryKeySelective(topicDetail);
        }else{
            topicDetail.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(topicDetail,true);
            return topicDetailMapper.insertSelective(topicDetail);
        }
    }

    @Override
    public int delSpecialTopic(String topicFlow) {
        int num = specialTopicMapper.deleteByPrimaryKey(topicFlow);
        if(num>0){
            NydwTopicDetailExample example=new NydwTopicDetailExample();
            NydwTopicDetailExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            if(StringUtil.isNotBlank(topicFlow)){
                criteria.andTopicFlowEqualTo(topicFlow);
            }
            topicDetailMapper.deleteByExample(example);
        }
        return num;
    }

    @Override
    public List<NydwCaucusNotice> searchCaucusNoticeList(NydwCaucusNotice caucusNotice) {
        NydwCaucusNoticeExample example=new NydwCaucusNoticeExample();
        NydwCaucusNoticeExample.Criteria criteria=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        NydwCaucusNoticeExample.Criteria criteria2=example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(caucusNotice.getRecordFlow())){
            criteria.andRecordFlowEqualTo(caucusNotice.getRecordFlow());
            criteria2.andRecordFlowEqualTo(caucusNotice.getRecordFlow());
        }
        if(StringUtil.isNotBlank(caucusNotice.getNoticeName())){
            criteria.andNoticeNameLike("%"+caucusNotice.getNoticeName()+"%");
            criteria2.andNoticeNameLike("%"+caucusNotice.getNoticeName()+"%");
        }
        if(StringUtil.isNotBlank(caucusNotice.getNoticeTypeId())){
            criteria.andNoticeTypeIdEqualTo(caucusNotice.getNoticeTypeId());
            criteria2.andNoticeTypeIdEqualTo(caucusNotice.getNoticeTypeId());
        }
        if(StringUtil.isNotBlank(caucusNotice.getCreateTime())){//发布时间
            criteria.andPublishTimeGreaterThanOrEqualTo(caucusNotice.getCreateTime());
            criteria2.andPublishTimeGreaterThanOrEqualTo(caucusNotice.getCreateTime());
        }
        if(StringUtil.isNotBlank(caucusNotice.getModifyTime())){//发布时间
            criteria.andPublishTimeLessThanOrEqualTo(caucusNotice.getModifyTime());
            criteria2.andPublishTimeLessThanOrEqualTo(caucusNotice.getModifyTime());
        }
        if(StringUtil.isNotBlank(caucusNotice.getPartyBranchId())){
            criteria.andPartyBranchIdEqualTo(caucusNotice.getPartyBranchId());
            criteria2.andPartyBranchIdIsNull();
        }
        if(StringUtil.isNotBlank(caucusNotice.getPublishFlag())){
            criteria.andPublishFlagEqualTo(caucusNotice.getPublishFlag());
            criteria2.andPublishFlagEqualTo(caucusNotice.getPublishFlag());
        }
        example.or(criteria2);
        example.setOrderByClause("PUBLISH_TIME DESC");
        return caucusNoticeMapper.selectByExample(example);
    }

    @Override
    public NydwCaucusNotice searchCaucusNotice(String recordFlow) {
        return caucusNoticeMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public int saveCaucusNotice(NydwCaucusNotice caucusNotice) {
        if(StringUtil.isNotBlank(caucusNotice.getRecordFlow())){
            GeneralMethod.setRecordInfo(caucusNotice,false);
            return caucusNoticeMapper.updateByPrimaryKeySelective(caucusNotice);
        }else{
            caucusNotice.setRecordFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(caucusNotice,true);
            return caucusNoticeMapper.insertSelective(caucusNotice);
        }
    }

    //将form对象封装为xml文本
    public String getXmlFromExtInfo(XjEduUserExtInfoForm extInfo){
        String xmlBody = null;
        if(extInfo!=null){
            Document doc = DocumentHelper.createDocument();
            Element root = doc.addElement("eduUserExtInfoForm");
            Element extInfoForm = root.addElement("xjEduUserExtInfoForm");
            Map<String,String> filedMap = getClassFieldMap(extInfo);
            if(filedMap!=null && filedMap.size()>0){
                for(String key : filedMap.keySet()){
                    Element item = extInfoForm.addElement(key);
                    item.setText(filedMap.get(key));
                }
            }

            List<XjRegisterDateForm> regList = extInfo.getRegisterDateList();
            if(regList!=null && regList.size()>0){
                Element regListEle = root.addElement("registerDateList");
                for(XjRegisterDateForm reg : regList){
                    Element regEle = regListEle.addElement("xjRegisterDateForm");
                    Map<String,String> rgeFiledMap = getClassFieldMap(reg);
                    if(rgeFiledMap!=null && rgeFiledMap.size()>0){
                        for(String key : rgeFiledMap.keySet()){
                            Element item = regEle.addElement(key);
                            item.setText(rgeFiledMap.get(key));
                        }
                    }
                }
            }

            xmlBody=doc.asXML();
        }
        return xmlBody;
    }
    //获取属性名和值
    private Map<String,String> getClassFieldMap(Object obj){
        Map<String,String> filedMap = null;
        if(obj!=null){
            try{
                filedMap = new HashMap<String, String>();
                String stringClassName = String.class.getSimpleName();
                Class<?> objClass = obj.getClass();
                Field[] fileds = objClass.getDeclaredFields();
                for(Field f : fileds){
                    String typeName = f.getType().getSimpleName();
                    if(stringClassName.equals(typeName)){
                        String attrName = f.getName();
                        String firstLetter = attrName.substring(0,1).toUpperCase();
                        String methedName = "get"+firstLetter+attrName.substring(1);
                        Method getMethod = objClass.getMethod(methedName);
                        String value = (String)getMethod.invoke(obj);
                        filedMap.put(attrName,StringUtil.defaultString(value));
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return filedMap;
    }
}
