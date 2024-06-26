package com.pinde.sci.biz.xjgl;

import com.pinde.sci.form.xjgl.XjEduUserForm;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjEduUserExt;

import java.util.List;
import java.util.Map;

/**
 * @Copyright njpdxx.com
 * @since 2018/2/6
 */
public interface IXjPartyBranchBiz {

    /**
     * 查询党员信息
     */
    List<XjEduUserExt> searchPartyMembers(Map<String,String> map);

    int editPartyMembers(XjEduUserForm eduUserForm);
    //投票管理查询
    List<NydwVoteMain> searchVoteMainList(NydwVoteMain main);
    //投票管理查询-党员
    List<NydwVoteMain> searchVoteMainList(Map<String,String> map);
    //投票对象信息查询
    List<NydwElector> searchElectorList(NydwElector elector);
    //投票人信息查询
    List<NydwVoter> searchVoterList(NydwVoter voter);
    //投票详情查询
    List<NydwVoteDetail> searchVoteDetailList(NydwVoteDetail voteDetail);
    //被投票人详情查询
    List<Map<String,String>> searchElectorList(String voteFlow);
    //投票人详情查询
    List<Map<String,String>> searchVoterList(String voteFlow,String electorFlow);
    //保存投票管理
    int saveVoteManage(List<String> electorFlows,List<String> voterFlows,NydwVoteMain voteMain);
    //保存投票详情
    int saveVoteDetail(NydwVoteDetail voteDetail);
    //删除投票详情
    int delVoteDetail(NydwVoteDetail voteDetail);
    //删除投票主表
    int delVoteMain(String recordFlow);
    //删除投票对象
    int delElector(NydwElector elector);
    //删除投票人
    int delVoter(NydwVoter voter);
    //专题讨论查询
    List<NydwSpecialTopic> searchSpecialTopicList(NydwSpecialTopic specialTopic);
    //保存专题讨论
    int saveSpecialTopic(NydwSpecialTopic specialTopic);
    //参与专题讨论党员
    List<NydwTopicDetail> topicDetailList(NydwTopicDetail topicDetail);
    //保存参与专题讨论
    int saveTopicDetail(NydwTopicDetail topicDetail);
    //删除专题讨论
    int delSpecialTopic(String topicFlow);
    //查询通知公告
    List<NydwCaucusNotice> searchCaucusNoticeList(NydwCaucusNotice caucusNotice);
    NydwCaucusNotice searchCaucusNotice(String recordFlow);
    //保存通知公告
    int saveCaucusNotice(NydwCaucusNotice caucusNotice);
}
