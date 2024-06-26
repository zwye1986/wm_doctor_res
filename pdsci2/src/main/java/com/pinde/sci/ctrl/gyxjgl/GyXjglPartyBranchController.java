package com.pinde.sci.ctrl.gyxjgl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.ExcleUtile;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.xjgl.IXjEduUserBiz;
import com.pinde.sci.biz.xjgl.IXjPartyBranchBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.NydwVoteDetailMapper;
import com.pinde.sci.dao.base.SysUserMapper;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.enums.sys.PoliticsAppearanceEnum;
import com.pinde.sci.form.xjgl.XjEduUserExtInfoForm;
import com.pinde.sci.form.xjgl.XjEduUserForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduUserExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright njpdxx.com
 * @since 2018/2/5
 */
@Controller
@RequestMapping("/gyxjgl/partyBranch")
public class GyXjglPartyBranchController extends GeneralController {
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IXjPartyBranchBiz partyBranchBiz;
    @Autowired
    private IXjEduUserBiz eduUserBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private NydwVoteDetailMapper detailMapper;

    @RequestMapping("/partyMemberList")
    public String partyMemberList(SysUser user, EduUser eduUser, String isRelationInto, Model model,Integer currentPage, HttpServletRequest request){
        Map<String,String> map=new HashMap<>();
        map.put("sid",eduUser.getSid());
        map.put("userName",user.getUserName());
        map.put("majorId",eduUser.getMajorId());
        map.put("partyBranchId",user.getPartyBranchId());
        map.put("userFlow",user.getUserFlow());
        map.put("isRelationInto",isRelationInto);
        PageHelper.startPage(currentPage, getPageSize(request));
        List<XjEduUserExt> dataList=partyBranchBiz.searchPartyMembers(map);
        if(dataList!=null&&dataList.size()>0){
            for (XjEduUserExt eduUserExt:dataList) {
                if(StringUtil.isNotBlank(eduUserExt.getContent())){
                    String content=eduUserExt.getContent();
                    XjEduUserExtInfoForm extInfoForm = eduUserBiz.parseExtInfoXml(content);
                    if(extInfoForm!=null){
                        eduUserExt.setModifyTime(extInfoForm.getJoinOrgTime());//借于存放入党时间
                        eduUserExt.setModifyUserFlow(extInfoForm.getIsRelationInto());//借于存放是否转入
                    }
                }
            }
        }
        model.addAttribute("dataList",dataList);
        return "gyxjgl/partyBranch/partyMemberList";
    }
    @RequestMapping("/editPartyMember")
    public String editPartyMember(String userFlow,Model model){
        Map<String,String> map=new HashMap<>();
        map.put("userFlow",userFlow);
        List<XjEduUserExt> dataList=partyBranchBiz.searchPartyMembers(map);
        if(dataList!=null&&dataList.size()>0){
            for (XjEduUserExt eduUserExt:dataList) {
                if(StringUtil.isNotBlank(eduUserExt.getContent())){
                    String content=eduUserExt.getContent();
                    XjEduUserExtInfoForm extInfoForm = eduUserBiz.parseExtInfoXml(content);
                    if(extInfoForm!=null){
                        eduUserExt.setModifyTime(extInfoForm.getJoinOrgTime());//借于存放入党时间
                        eduUserExt.setModifyUserFlow(extInfoForm.getIsRelationInto());//借于存放是否转入
                    }
                }
            }
            model.addAttribute("data",dataList.get(0));
        }
        return "gyxjgl/partyBranch/editPartyMember";
    }

    /**
     * 保存eduUserForm
     * @param eduUserForm
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/savePartyMember")
    @ResponseBody
    public String savePartyMember(XjEduUserForm eduUserForm) throws Exception {
        int result=partyBranchBiz.editPartyMembers(eduUserForm);
        if(result!=GlobalConstant.ZERO_LINE){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    @RequestMapping("/exportPartyMembers")
    public void exportPartyMembers(SysUser user, EduUser eduUser, String isRelationInto,HttpServletResponse response)throws Exception {
        Map<String,String> map=new HashMap<>();
        map.put("sid",eduUser.getSid());
        map.put("userName",user.getUserName());
        map.put("majorId",eduUser.getMajorId());
        map.put("partyBranchId",user.getPartyBranchId());
        map.put("userFlow",user.getUserFlow());
        map.put("isRelationInto",isRelationInto);
        List<XjEduUserExt> dataList=partyBranchBiz.searchPartyMembers(map);
        if(dataList!=null&&dataList.size()>0){
            Integer count = 1;//序号
            for (XjEduUserExt eduUserExt:dataList) {
                SysUser sysUser=eduUserExt.getSysUser();
                if(StringUtil.isNotBlank(eduUserExt.getContent())){
                    String content=eduUserExt.getContent();
                    XjEduUserExtInfoForm extInfoForm = eduUserBiz.parseExtInfoXml(content);
                    if(extInfoForm!=null){
                        eduUserExt.setModifyTime(extInfoForm.getJoinOrgTime());//借于存放入党时间
                    }
                }
                if(sysUser!=null){
                    sysUser.setNativePlaceProvName(sysUser.getNativePlaceProvName()+sysUser.getNativePlaceCityName());
                    eduUserExt.setSysUser(sysUser);
                }
                eduUserExt.setIntro(count.toString());
                count++;
            }
        }
        String []titles = new String[]{
                "intro:序号",
                "sysUser.idNo:身份证号",
                "sysUser.userName:姓名",
                "sysUser.partyBranchName:所在支部",
                "sysUser.sexName:性别",
                "sysUser.nationName:民族",
                "sysUser.nativePlaceProvName:籍贯",
                "sysUser.userBirthday:出生日期",
                "period:年级",
                "modifyTime:入党时间"
        };
        String fileName = "党员信息表.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ExcleUtile.exportSimpleExcleByObjs(titles, dataList, response.getOutputStream());
    }
    /**
     * 投票管理列表
     */
    @RequestMapping(value="/voteManageList" )
    public String voteManageList(NydwVoteMain main,Integer currentPage,Model model,HttpServletRequest request) {
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NydwVoteMain> dataList=partyBranchBiz.searchVoteMainList(main);
        model.addAttribute("dataList",dataList);
        return "gyxjgl/partyBranch/voteManageList";
    }

    @RequestMapping(value="/addVoteInfo" )
    public String addVoteInfo(Model model) {
        SysUserExample sysUserExample=new SysUserExample();
        SysUserExample.Criteria criteria=sysUserExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria.andPoliticsStatusIdEqualTo(PoliticsAppearanceEnum.Zgdy.getId());
        SysUserExample.Criteria criteria1=sysUserExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria1.andPoliticsStatusIdEqualTo(PoliticsAppearanceEnum.Rdjjfz.getId());
        SysUserExample.Criteria criteria2=sysUserExample.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteria2.andPoliticsStatusIdEqualTo(PoliticsAppearanceEnum.Zgybdy.getId());
        sysUserExample.or(criteria1);
        sysUserExample.or(criteria2);
        sysUserExample.setOrderByClause("MODIFY_TIME DESC");
        List<SysUser> userList=sysUserMapper.selectByExample(sysUserExample);
        model.addAttribute("userList",userList);//被投票人
        //投票人
//        return "gyxjgl/partyBranch/addVoteInfo";
        return "gyxjgl/partyBranch/addVoteInfoNew";
    }
    @RequestMapping("/selectVoters")
    public String selectVoters(Model model){
        SysUserExample sysUserExampleV=new SysUserExample();
        SysUserExample.Criteria criteriaV=sysUserExampleV.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        criteriaV.andPoliticsStatusIdEqualTo(PoliticsAppearanceEnum.Zgdy.getId());
        sysUserExampleV.setOrderByClause("PARTY_BRANCH_ID,MODIFY_TIME DESC");
        List<SysUser> voterList=sysUserMapper.selectByExample(sysUserExampleV);
        model.addAttribute("voterList",voterList);//投票人
        return "gyxjgl/partyBranch/selectVoters";
    }
    /**
     * 新增投票
     */
    @RequestMapping(value="/saveVoteInfo" )
    @ResponseBody
    public String saveVoteInfo(String jsondata,NydwVoteMain main) {
        Map<String,Object> dataMap = JSON.parseObject(jsondata,Map.class);
        List<String> electorFlows = (List<String>)dataMap.get("electorFlows");
        List<String> voterFlows = (List<String>)dataMap.get("voterFlows");
        String voteName = (String)dataMap.get("voteName");
        String beginTime = (String)dataMap.get("beginTime");
        String endTime = (String)dataMap.get("endTime");
        if((electorFlows!=null&&electorFlows.size()==0)||(voterFlows!=null&&voterFlows.size()==0)){
            return "被投票人或投票人不能为空！";
        }
        int num=partyBranchBiz.saveVoteManage(electorFlows,voterFlows,main);
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    /**
     * 被投票人详情
     */
    @RequestMapping(value="/electorInfoList" )
    public String electorInfoList(String recordFlow,String userFlag,Model model) {
        NydwElector elector=new NydwElector();
        elector.setVoteFlow(recordFlow);
        List<NydwElector> dataList=partyBranchBiz.searchElectorList(elector);
        if(dataList!=null&&dataList.size()>0){
            for (NydwElector ne:dataList) {
                NydwVoteDetail nvd=new NydwVoteDetail();
                nvd.setVoteFlow(recordFlow);
                nvd.setElectorFlow(ne.getElectorFlow());
                List<NydwVoteDetail> countList=partyBranchBiz.searchVoteDetailList(nvd);
                if(countList!=null){
                    ne.setModifyUserFlow(countList.size()+"");
                }else {
                    ne.setModifyUserFlow("0");
                }
            }
        }
        model.addAttribute("dataList",dataList);
        model.addAttribute("userFlag",userFlag);
        return "gyxjgl/partyBranch/electorInfoList";
    }
    /**
     * 投票人详情
     */
    @RequestMapping(value="/voterInfoList" )
    public String voterInfoList(String recordFlow,String userFlag,Model model) {
        Map<String,Object> dataMap=new HashMap<>();
        NydwElector elector=new NydwElector();
        elector.setVoteFlow(recordFlow);
        List<NydwElector> dataList=partyBranchBiz.searchElectorList(elector);
        if(dataList!=null&&dataList.size()>0){
            for (NydwElector ne:dataList) {
                List<Map<String,String>> voterList=partyBranchBiz.searchVoterList(recordFlow,ne.getElectorFlow());
                dataMap.put(ne.getElectorFlow(),voterList);
            }
        }
        model.addAttribute("dataList",dataList);
        model.addAttribute("dataMap",dataMap);
        return "gyxjgl/partyBranch/voterInfoList";
    }

    /**
     * 查看投票
     * @param recordFlow
     * @param model
     * @return
     */
    @RequestMapping(value="/showVoteInfo" )
    public String showVoteInfo(String recordFlow,Model model) {
        NydwVoteMain main=new NydwVoteMain();
        main.setRecordFlow(recordFlow);
        List<NydwVoteMain> dataList=partyBranchBiz.searchVoteMainList(main);
        if(dataList!=null&&dataList.size()>0){
            model.addAttribute("main",dataList.get(0));
        }
        NydwElector elector=new NydwElector();
        elector.setVoteFlow(recordFlow);
        //投票对象信息
        List<NydwElector> electorList=partyBranchBiz.searchElectorList(elector);
        model.addAttribute("electorList",electorList);
        NydwVoter voter=new NydwVoter();
        voter.setVoteFlow(recordFlow);
        //投票人信息
        List<NydwVoter> voterList=partyBranchBiz.searchVoterList(voter);
        model.addAttribute("voterList",voterList);
        return "gyxjgl/partyBranch/showVoteInfo";
    }
    /**
     * 删除投票
     */
    @RequestMapping(value="/delVoteInfo" )
    @ResponseBody
    public String delVoteInfo(String recordFlow) {
        int num=partyBranchBiz.delVoteMain(recordFlow);
        if(num>0){
            NydwElector elector=new NydwElector();
            elector.setVoteFlow(recordFlow);
            partyBranchBiz.delElector(elector);
            NydwVoter voter=new NydwVoter();
            voter.setVoteFlow(recordFlow);
            partyBranchBiz.delVoter(voter);
            NydwVoteDetail delDetail=new NydwVoteDetail();
            delDetail.setVoteFlow(recordFlow);
            partyBranchBiz.delVoteDetail(delDetail);
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }
    /**
     * 投票管理列表-党员
     */
    @RequestMapping(value="/toVoteList" )
    public String toVoteList(NydwVoteMain main,Integer currentPage,Model model,HttpServletRequest request) {
        Map<String,String> map =new HashMap<>();
        map.put("voteName",main.getVoteName());
        map.put("beginTime",main.getBeginTime());
        map.put("endTime",main.getEndTime());
        map.put("voterFlow",GlobalContext.getCurrentUser().getUserFlow());
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NydwVoteMain> dataList=partyBranchBiz.searchVoteMainList(map);
        model.addAttribute("dataList",dataList);
        if(!("01".equals(GlobalContext.getCurrentUser().getPoliticsStatusId()))){
            model.addAttribute("dataList",null);
        }
        return "gyxjgl/partyBranch/toVoteList";
    }
    /**
     * 投票页面-党员
     */
    @RequestMapping(value="/toVoteInfo" )
    public String toVoteInfo(String voteFlow,Model model,HttpServletRequest request) {
        NydwVoteMain main=new NydwVoteMain();
        main.setRecordFlow(voteFlow);
        List<NydwVoteMain> dataList=partyBranchBiz.searchVoteMainList(main);
        NydwElector elector=new NydwElector();
        elector.setVoteFlow(voteFlow);
        List<NydwElector> electorList=partyBranchBiz.searchElectorList(elector);
        if(electorList!=null&&electorList.size()>0){
            for (NydwElector ne:electorList) {
                NydwVoteDetail nvd=new NydwVoteDetail();
                nvd.setVoteFlow(voteFlow);
                nvd.setElectorFlow(ne.getElectorFlow());
                nvd.setVoterFlow(GlobalContext.getCurrentUser().getUserFlow());
                List<NydwVoteDetail> dList=partyBranchBiz.searchVoteDetailList(nvd);
                if(dList!=null&&dList.size()>0){
                    ne.setModifyUserFlow(GlobalConstant.FLAG_Y);
                }
            }
        }
        if(dataList!=null&&dataList.size()>0){
            model.addAttribute("main",dataList.get(0));
            model.addAttribute("electorList",electorList);
        }
        return "gyxjgl/partyBranch/toVoteInfo";
    }
    /**
     * 保存投票-党员
     */
    @RequestMapping(value="/saveToVote" )
    @ResponseBody
    public String saveToVote(String recordFlow,String jsondata) {
        Map<String,Object> dataMap = JSON.parseObject(jsondata,Map.class);
        List<String> electorFlows = (List<String>)dataMap.get("electorFlows");
        NydwVoteDetail delDetail=new NydwVoteDetail();
        delDetail.setVoteFlow(recordFlow);
        delDetail.setVoterFlow(GlobalContext.getCurrentUser().getUserFlow());
        partyBranchBiz.delVoteDetail(delDetail);
        if(electorFlows!=null&&electorFlows.size()>0){
            for (String electorFlow:electorFlows) {
                if(StringUtil.isNotBlank(electorFlow)){
                    String flow=electorFlow.split(",")[0];
                    String name=electorFlow.split(",")[1];
                    NydwVoteDetail voteDetail=new NydwVoteDetail();
                    voteDetail.setVoteFlow(recordFlow);
                    voteDetail.setElectorFlow(flow);
                    voteDetail.setElectorName(name);
                    voteDetail.setVoterFlow(GlobalContext.getCurrentUser().getUserFlow());
                    voteDetail.setVoterName(GlobalContext.getCurrentUser().getUserName());
                    partyBranchBiz.saveVoteDetail(voteDetail);
                }
            }
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }
    /**
     * 专题讨论
     */
    @RequestMapping(value="/specialTopicList" )
    public String specialTopicList(NydwSpecialTopic specialTopic,String role,Model model,Integer currentPage, HttpServletRequest request){
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NydwSpecialTopic> dataList=partyBranchBiz.searchSpecialTopicList(specialTopic);
        model.addAttribute("dataList",dataList);
        if(StringUtil.isNotBlank(role)&&!("01".equals(GlobalContext.getCurrentUser().getPoliticsStatusId()))){
            model.addAttribute("dataList",null);
        }
        model.addAttribute("role",role);
        return "gyxjgl/partyBranch/specialTopicList";
    }
    @RequestMapping(value="/addSpecialTopic" )
    public String addSpecialTopic(){
        return "gyxjgl/partyBranch/addSpecialTopic";
    }

    /**
     * 保存专题讨论
     * @param specialTopic
     * @return
     */
    @RequestMapping(value="/saveSpecialTopic" )
    @ResponseBody
    public String saveSpecialTopic(NydwSpecialTopic specialTopic){
        specialTopic.setPublishTime(DateUtil.getCurrDate());
        int num=partyBranchBiz.saveSpecialTopic(specialTopic);
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    @RequestMapping(value="/specialTopicInfo" )
    public String specialTopicInfo(String topicFlow,String role,Model model){
        NydwSpecialTopic specialTopic=new NydwSpecialTopic();
        specialTopic.setTopicFlow(topicFlow);
        List<NydwSpecialTopic> dataList=partyBranchBiz.searchSpecialTopicList(specialTopic);
        if(dataList!=null&&dataList.size()>0){
            model.addAttribute("specialTopic",dataList.get(0));
        }
        NydwTopicDetail topicDetail=new NydwTopicDetail();
        topicDetail.setTopicFlow(topicFlow);
        List<NydwTopicDetail> detailList=partyBranchBiz.topicDetailList(topicDetail);
        model.addAttribute("detailList",detailList);
        model.addAttribute("flag","view");
        model.addAttribute("role",role);
        return "gyxjgl/partyBranch/addSpecialTopic";
    }
    /**
     * 提交参与专题讨论
     * @return
     */
    @RequestMapping(value="/saveTopicDetail" )
    @ResponseBody
    public String saveTopicDetail(String topicFlow,String talkMemo){
        SysUser user=GlobalContext.getCurrentUser();
        NydwTopicDetail topicDetail=new NydwTopicDetail();
        topicDetail.setTopicFlow(topicFlow);
        topicDetail.setUserFlow(user.getUserFlow());
        topicDetail.setUserName(user.getUserName());
        topicDetail.setSexId(user.getSexId());
        topicDetail.setSexName(user.getSexName());
        topicDetail.setPartyBranchId(user.getPartyBranchId());
        topicDetail.setPartyBranchName(user.getPartyBranchName());
        topicDetail.setTalkMemo(talkMemo);
        topicDetail.setTalkTime(DateUtil.getCurrDateTime());
        int num=partyBranchBiz.saveTopicDetail(topicDetail);
        if(num>0){
            return GlobalConstant.OPRE_SUCCESSED;
        }
        return GlobalConstant.OPRE_FAIL;
    }
    /**
     * 被投票人详情
     */
    @RequestMapping(value="/topicDetailInfo" )
    public String topicDetailInfo(String topicFlow,Model model) {
        NydwTopicDetail topicDetail=new NydwTopicDetail();
        topicDetail.setTopicFlow(topicFlow);
        List<NydwTopicDetail> dataList=partyBranchBiz.topicDetailList(topicDetail);
        List<NydwTopicDetail> dataListTemp=new ArrayList<>();
        List<String> userFlowList=new ArrayList<>();
        if(dataList!=null&&dataList.size()>0){
            for (NydwTopicDetail td:dataList) {
                if(!userFlowList.contains(td.getUserFlow())){
                    userFlowList.add(td.getUserFlow());
                    dataListTemp.add(td);
                }
            }
        }
        model.addAttribute("dataList",dataListTemp);
        return "gyxjgl/partyBranch/topicDetailInfo";
    }
    /**
     * 删除专题讨论
     */
    @RequestMapping(value="/delSpecialTopic" )
    @ResponseBody
    public String delSpecialTopic(String topicFlow) {
        int num=partyBranchBiz.delSpecialTopic(topicFlow);
        if(num>0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }
    @RequestMapping(value = "/caucusNotices")
    public String caucusNotices(NydwCaucusNotice caucusNotice,String role,Model model,Integer currentPage, HttpServletRequest request){
        if(StringUtil.isNotBlank(role)&&"doctor".equals(role)){
            caucusNotice.setPartyBranchId(GlobalContext.getCurrentUser().getPartyBranchId());
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NydwCaucusNotice> dataList=partyBranchBiz.searchCaucusNoticeList(caucusNotice);
        model.addAttribute("dataList",dataList);
        if(StringUtil.isNotBlank(role)&&!("01".equals(GlobalContext.getCurrentUser().getPoliticsStatusId()))){
            model.addAttribute("dataList",null);
        }
        model.addAttribute("role",role);
        return "gyxjgl/partyBranch/caucusNotices";
    }
    @RequestMapping(value = "/editCaucusNotice")
    public String editCaucusNotice(String recordFlow,String role,Model model){
        if(StringUtil.isNotBlank(recordFlow)){
            NydwCaucusNotice caucusNotice=new NydwCaucusNotice();
            caucusNotice.setRecordFlow(recordFlow);
            List<NydwCaucusNotice> dataList=partyBranchBiz.searchCaucusNoticeList(caucusNotice);
            if(dataList!=null&&dataList.size()>0){
                model.addAttribute("caucusNotice",dataList.get(0));
            }
        }
        model.addAttribute("role",role);
        return "gyxjgl/partyBranch/editCaucusNotice";
    }
    /**
     * 保存通知公告
     * @return
     */
    @RequestMapping(value="/addCaucusNotice" )
    @ResponseBody
    public String addCaucusNotice(NydwCaucusNotice caucusNotice){
        caucusNotice.setPublishTime(DateUtil.getCurrDate());
        caucusNotice.setPublishFlag(GlobalConstant.FLAG_Y);
        int num=partyBranchBiz.saveCaucusNotice(caucusNotice);
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }
    @RequestMapping(value="/noticeView")
    public String message(Model model,String recordFlow) throws Exception{
        if(StringUtil.isNotBlank(recordFlow)){
            NydwCaucusNotice data=partyBranchBiz.searchCaucusNotice(recordFlow);
            if(data!=null){
                model.addAttribute("msg", data);
                String clicks=data.getClicks();
                data.setClicks(Integer.parseInt(clicks)+1+"");
                partyBranchBiz.saveCaucusNotice(data);
            }
        }
        return "gyxjgl/partyBranch/message";
    }
    /**
     * 党支部字典管理
     */
    @RequestMapping(value="/dictList" )
    public ModelAndView selectDictList(SysDict sysDict) {
        ModelAndView mav = new ModelAndView("gyxjgl/partyBranch/partyBranchManage");
        sysDict.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        sysDict.setOrgName(GlobalContext.getCurrentUser().getOrgName());
        sysDict.setDictTypeId(DictTypeEnum.GyXjPartyBranch.getId());
        List<SysDict> dictList = this.dictBiz.searchDictList(sysDict);
        mav.addObject("dictList",dictList);
        return mav;
    }
    @RequestMapping(value="/edit")
    public ModelAndView edit(@RequestParam(value="dictFlow",required=false) String dictFlow) {
        ModelAndView mav = new ModelAndView("gyxjgl/partyBranch/editDict");
        if(StringUtil.isNotBlank(dictFlow)){
            SysDict dict = this.dictBiz.readDict(dictFlow);
            mav.addObject("dict" , dict);
        }
        return mav;
    }
    @RequestMapping(value="/save" , method= RequestMethod.POST)
    @ResponseBody
    public String saveDict(SysDict dict) {
        dict.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        dict.setOrgName(GlobalContext.getCurrentUser().getOrgName());
        if(StringUtil.isBlank(dict.getDictFlow())){
            //新增字典时判断该类型字典代码是否存在
            SysDict sysDict=dictBiz.readDict(dict.getDictTypeId(),dict.getDictId(),dict.getOrgFlow());
            if(null!=sysDict){
                return GlobalConstant.OPRE_FAIL_FLAG;
            }
            this.dictBiz.addDict(dict);
        }else{
            //修改字典时，字典代码可以与自身相同，可以是新ID，但不能与其他字典相同
            List<SysDict> dictList=dictBiz.searchDictListByDictTypeIdNotIncludeSelf(dict);
            for(SysDict sysDict:dictList){
                if(dict.getDictId().equals(sysDict.getDictId())){
                    return GlobalConstant.OPRE_FAIL_FLAG;
                }
            }
            this.dictBiz.modeDictAndSubDict(dict);
        }
        return GlobalConstant.SAVE_SUCCESSED;
    }
    @RequestMapping(value="/doRefresh" , method=RequestMethod.GET)
    @ResponseBody
    public String doRefresh(HttpServletRequest request) {
        InitConfig.refresh(request.getServletContext());
        return "刷新成功！";
    }
}
