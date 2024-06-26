package com.pinde.sci.ctrl.xjgl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinde.core.jspform.ItemGroupData;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.*;
import com.pinde.sci.biz.xjgl.IXjEduCourseBiz;
import com.pinde.sci.biz.xjgl.IXjEduUserBiz;
import com.pinde.sci.biz.xjgl.IXjQuestionnaireBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.EduStudentCourseMapper;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.xjgl.XjQuestionDetailExt;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * @Copyright njpdxx.com
 * @since 2018/3/7
 */
@Controller
@RequestMapping("/xjgl/questionnaire")
public class XjQuestionnaireController extends GeneralController {
    @Autowired
    private IXjQuestionnaireBiz questionnaireBiz;
    @Autowired
    private IXjEduUserBiz eduUserBiz;
    @Autowired
    private EduStudentCourseMapper studentCourseMapper;
    @Autowired
    private IXjEduCourseBiz eduCourseBiz;

    @RequestMapping("/courseQuestionList")
    public String courseQuestionList(NywjCourseQuestion courseQuestion, Model model, Integer currentPage, HttpServletRequest request){
        if(StringUtil.isNotBlank(courseQuestion.getCreateTime())){
            Date date= DateUtil.parseDate(courseQuestion.getCreateTime(),"yyyy-MM-dd");
            courseQuestion.setCreateTime(DateUtil.formatDate(date,"yyyyMMdd"));
        }
        PageHelper.startPage(currentPage, getPageSize(request));
        List<NywjCourseQuestion> dataList=questionnaireBiz.searchQuestionList(courseQuestion);
        model.addAttribute("dataList",dataList);
        return "xjgl/questionnaire/courseQuestionList";
    }
    @RequestMapping("/showEditQuestion")
    public String showEditQuestion(String recordFlow, Model model){
        if(StringUtil.isNotBlank(recordFlow)){
            Map<String,String> jsonMap=new HashMap<>();
            List<XjQuestionDetailExt> dataList=new ArrayList<>();
            NywjCourseQuestion courseQuestion=new NywjCourseQuestion();
            courseQuestion.setRecordFlow(recordFlow);
            List<NywjCourseQuestion> courseQuestions=questionnaireBiz.searchQuestionList(courseQuestion);
            if(courseQuestions!=null&&courseQuestions.size()>0){
                courseQuestion=courseQuestions.get(0);
                model.addAttribute("courseQuestion",courseQuestion);
                NywjQuestionDetail questionDetail=new NywjQuestionDetail();
                questionDetail.setQuestionFlow(recordFlow);
                List<NywjQuestionDetail> qdList=questionnaireBiz.searchQuestionDetailList(questionDetail);
                if(qdList!=null&&qdList.size()>0){
                    for (NywjQuestionDetail qd:qdList) {
                        if(qd!=null){
                            Map<String,Object> dataMap=new HashMap<>();//存储题目及选项-转成jason
                            XjQuestionDetailExt qde=new XjQuestionDetailExt();
                            NywjAnswerDetail answerDetail=new NywjAnswerDetail();
                            answerDetail.setDetailFlow(qd.getRecordFlow());
                            qde.setQuestionDetail(qd);
                            List<NywjAnswerDetail> adList=questionnaireBiz.searchAnswerDetailList(answerDetail);
                            qde.setAnswerDetailList(adList);
                            dataList.add(qde);
                            //封装json数据
                            List<Map<String,String>> answerDetailList=new ArrayList<>();
                            Map<String,String> qDetail=new HashMap<>();
                            if(adList!=null&&adList.size()>0){
                                for (NywjAnswerDetail adetail:adList) {
                                    Map<String,String> answerMap=new HashMap<>();
//                                        answerMap.put("recordFlow",adetail.getRecordFlow());
                                    answerMap.put("answerName",adetail.getAnswerName());
                                    answerMap.put("serial",adetail.getSerial());
                                    answerDetailList.add(answerMap);
                                }
                            }
                            qDetail.put("serial",qd.getSerial());
                            qDetail.put("questionTitle",qd.getQuestionTitle());
                            qDetail.put("questionTypeId",qd.getQuestionTypeId());
                            qDetail.put("questionTypeName",qd.getQuestionTypeName());
                            dataMap.put("questionDetail",qDetail);
                            dataMap.put("answerDetailList",answerDetailList);
                            String data=JSON.toJSONString(dataMap);
                            jsonMap.put(qd.getSerial(),data);
                        }
                    }
                }
                model.addAttribute("dataList",dataList);
            }
            model.addAttribute("jsonMap",jsonMap);
            model.addAttribute("recordFlow",recordFlow);
            return "xjgl/questionnaire/editQuestion1";
        }
        return "xjgl/questionnaire/editQuestion";
    }
    @RequestMapping("/showQuestionDetail")
    public String showQuestionDetail(String flag,String jsondata,Model model) throws Exception{
        if("edit".equals(flag)){
            model.addAttribute("flag",flag);
            if(StringUtil.isNotBlank(jsondata)) {
                jsondata=java.net.URLDecoder.decode(jsondata,"UTF-8");
                Map<String, Object> mp = JSON.parseObject(jsondata, Map.class);
                Map<String, Object> questionDetail = (Map<String, Object>) mp.get("questionDetail");
                if(questionDetail!=null){
                    String serial=(String)questionDetail.get("serial");
                    String questionTitle=(String)questionDetail.get("questionTitle");
                    String questionTypeId=(String)questionDetail.get("questionTypeId");
                    NywjQuestionDetail qd=new NywjQuestionDetail();
                    qd.setQuestionTypeId(questionTypeId);
                    qd.setQuestionTitle(questionTitle);
                    qd.setSerial(serial);
                    model.addAttribute("questionDetail",qd);
                    if(!"Subjective".equals(questionTypeId)){
                        List<NywjAnswerDetail> answerDetailList=new ArrayList<>();
                        List<Map<String, Object>> answerDetails=(List<Map<String, Object>>)mp.get("answerDetailList");
                        if(answerDetails!=null&&answerDetails.size()>0){
                            for (Map<String, Object> tmp:answerDetails) {
                                NywjAnswerDetail ad=new NywjAnswerDetail();
                                ad.setSerial((String)tmp.get("serial"));
                                ad.setAnswerName((String)tmp.get("answerName"));
                                answerDetailList.add(ad);
                            }
                        }
                        model.addAttribute("answerDetailList",answerDetailList);
                    }
                }
            }
        }
        return "xjgl/questionnaire/questionDetail";
    }

    /**
     * 保存评价问卷维护
     * @param jsondata
     * @param courseQuestion
     * @return
     * @throws IOException
     */
    @RequestMapping("/saveCourseQuestion")
    @ResponseBody
    public String saveCourseQuestion(String jsondata,NywjCourseQuestion courseQuestion) throws IOException {
        List<XjQuestionDetailExt> detailExtList=new ArrayList<>();
        if(StringUtil.isNotBlank(jsondata)){
            Map<String,Object> mp = JSON.parseObject(jsondata,Map.class);
            List<Map<String,Object>> dataList = (List<Map<String,Object>>)mp.get("questionDetailExtList");
            if(dataList!=null&&dataList.size()>0){
                for(Map<String,Object> ext:dataList){
                    Map<String,Object> dtMap=JSON.parseObject((String)ext.get("questionDetailExt"),Map.class);
                    if(dtMap!=null){
                        XjQuestionDetailExt detailExt=new XjQuestionDetailExt();
                        Map<String,Object> job= (Map<String,Object>)dtMap.get("questionDetail");
                        if(job!=null){
                            NywjQuestionDetail questionDetail=new NywjQuestionDetail();
                            String serial=(String)job.get("serial");
                            String questionTitle=(String)job.get("questionTitle");
                            String questionTypeId=(String)job.get("questionTypeId");
                            String questionTypeName=(String)job.get("questionTypeName");
                            questionDetail.setSerial(serial);
                            questionDetail.setQuestionTitle(questionTitle);
                            questionDetail.setQuestionTypeId(questionTypeId);
                            questionDetail.setQuestionTypeName(questionTypeName);
                            detailExt.setQuestionDetail(questionDetail);
                        }
                        List<JSONObject> jsonObjects = (List<JSONObject>)dtMap.get("answerDetailList");
                        List<NywjAnswerDetail> answerDetails=new ArrayList<>();
                        if(jsonObjects!=null&&jsonObjects.size()>0){
                            for(JSONObject jo:jsonObjects)
                            {
                                ObjectMapper objectMapper=new ObjectMapper();
                                NywjAnswerDetail m=objectMapper.readValue(jo.toString(), NywjAnswerDetail.class);
                                answerDetails.add(m);
                            }
                        }
                        detailExt.setAnswerDetailList(answerDetails);
                        detailExtList.add(detailExt);
                    }
                }
            }
            int num=questionnaireBiz.saveCourseQuestion(courseQuestion,detailExtList);
            if(num>0){
                return GlobalConstant.SAVE_SUCCESSED;
            }else if(num==-1){
                return "已经维护过该课程类型的问卷！";
            }
        }
        return GlobalConstant.SAVE_FAIL;
    }
    /**
     * 删除问卷
     */
    @RequestMapping(value="/delCourseQuestion" )
    @ResponseBody
    public String delCourseQuestion(String recordFlow) {
        int num=questionnaireBiz.delCourseQuestion(recordFlow);
        if(num>0){
            return GlobalConstant.OPERATE_SUCCESSED;
        }
        return GlobalConstant.OPERATE_FAIL;
    }

    /**
     * 查看问卷
     * @return
     */
    @RequestMapping("/showQuestionInfo")
    public String showQuestionInfo(String recordFlow,String courseFlow,Model model){
        List<XjQuestionDetailExt> dataList=new ArrayList<>();
        if(StringUtil.isNotBlank(recordFlow)){
            NywjCourseQuestion courseQuestion=new NywjCourseQuestion();
            courseQuestion.setRecordFlow(recordFlow);
            List<NywjCourseQuestion> courseQuestions=questionnaireBiz.searchQuestionList(courseQuestion);
            if(courseQuestions!=null&&courseQuestions.size()>0){
                courseQuestion=courseQuestions.get(0);
                model.addAttribute("courseQuestion",courseQuestion);
                NywjQuestionDetail questionDetail=new NywjQuestionDetail();
                questionDetail.setQuestionFlow(recordFlow);
                List<NywjQuestionDetail> qdList=questionnaireBiz.searchQuestionDetailList(questionDetail);
                if(qdList!=null&&qdList.size()>0){
                    for (NywjQuestionDetail qd:qdList) {
                        if(qd!=null){
                            XjQuestionDetailExt qde=new XjQuestionDetailExt();
                            NywjAnswerDetail answerDetail=new NywjAnswerDetail();
                            answerDetail.setDetailFlow(qd.getRecordFlow());
                            qde.setQuestionDetail(qd);
                            List<NywjAnswerDetail> adList=questionnaireBiz.searchAnswerDetailList(answerDetail);
                            qde.setAnswerDetailList(adList);
                            dataList.add(qde);
                        }
                    }
                }
                model.addAttribute("dataList",dataList);
            }
        }
        if(StringUtil.isNotBlank(courseFlow)){
            model.addAttribute("courseFlow",courseFlow);
            model.addAttribute("recordFlow",recordFlow);
            return "xjgl/questionnaire/toEvaluate";
        }
        return "xjgl/questionnaire/questionInfo";
    }

    /**
     * 学生-课程评价
     * @return
     */
    @RequestMapping("/studentQuestionList")
    public String studentQuestionList(String studentPeriod,String questionName,String courseName,String courseCode,String courseTypeId,String roleFlag,Model model, Integer currentPage, HttpServletRequest request){
        Map<String,String> map=new HashMap<>();
        if("admin".equals(roleFlag)){
            map.put("studentPeriod",studentPeriod);
            map.put("questionName",questionName);
            map.put("courseName",courseName);
            map.put("courseCode",courseCode);
            map.put("courseTypeId",courseTypeId);
            PageHelper.startPage(currentPage, getPageSize(request));
            List<Map<String,String>> dataList=questionnaireBiz.searchQuestions(map);
            model.addAttribute("dataList",dataList);
            model.addAttribute("roleFlag",roleFlag);
        }
        if("student".equals(roleFlag)){
            SysUser currUser =  GlobalContext.getCurrentUser();
            String userFlow = currUser.getUserFlow();
            EduUser eduUser = eduUserBiz.readEduUser(userFlow);
            model.addAttribute("eduUser", eduUser);
            if(eduUser!=null){
                map.put("userFlow",userFlow);
                map.put("studentPeriod",eduUser.getPeriod());
            }
            PageHelper.startPage(currentPage, getPageSize(request));
            List<Map<String,String>> dataList=questionnaireBiz.searchQuestions(map);
            model.addAttribute("dataList",dataList);
            model.addAttribute("roleFlag",roleFlag);
        }
        return "xjgl/questionnaire/studentQuestionList";
    }
//    public String studentQuestionList(Model model, Integer currentPage, HttpServletRequest request){
//        SysUser currUser =  GlobalContext.getCurrentUser();
//        String userFlow = currUser.getUserFlow();
//        EduUser eduUser = eduUserBiz.readEduUser(userFlow);
//        model.addAttribute("eduUser", eduUser);
//        if(eduUser!=null){
//            Map<String,String> map=new HashMap<>();
//            map.put("userFlow",userFlow);
//            Map<String,Object> infoMap=new HashMap<>();//课程信息
//            List<Map<String,String>> courseInfos=questionnaireBiz.searchStudentCourseInfos(map);
//            if(courseInfos!=null&&courseInfos.size()>0){
//                for (Map<String,String> in:courseInfos) {
//                    infoMap.put(in.get("COURSE_FLOW"),in);
//                }
//            }
//            model.addAttribute("infoMap",infoMap);
//            Map<String,Object> questionMap=new HashMap<>();//问卷信息
//            List<NywjCourseQuestion> questions=questionnaireBiz.searchQuestionList(new NywjCourseQuestion());
//            if(questions!=null&&questions.size()>0){
//                for (NywjCourseQuestion cq:questions) {
//                    questionMap.put(cq.getCourseTypeId(),cq);
//                }
//            }
//            model.addAttribute("questionMap",questionMap);
//            Map<String,Object> evaluateMap=new HashMap<>();//问卷填写信息
//            NywjStudentEvaluate studentEvaluate=new NywjStudentEvaluate();
//            studentEvaluate.setStudentFlow(userFlow);
//            studentEvaluate.setSessionNumber(eduUser.getPeriod());
//            List<NywjStudentEvaluate> evaluates=questionnaireBiz.searchStudentEvaluateList(studentEvaluate);
//            if(evaluates!=null&&evaluates.size()>0){
//                for (NywjStudentEvaluate ev:evaluates) {
//                    evaluateMap.put(ev.getCourseFlow(),ev);
//                }
//            }
//            model.addAttribute("evaluateMap",evaluateMap);
//            List<EduStudentCourse> studentCourseList =new ArrayList<>();
//            PageHelper.startPage(currentPage, getPageSize(request));
//            EduStudentCourseExample example = new EduStudentCourseExample();
//            EduStudentCourseExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
//            if(StringUtil.isNotBlank(userFlow)&&StringUtil.isNotBlank(eduUser.getPeriod())){
//                criteria.andUserFlowEqualTo(userFlow);
//                criteria.andStudentPeriodEqualTo(eduUser.getPeriod());
//                example.setOrderByClause("COURSE_TYPE_ID");
//                studentCourseList=studentCourseMapper.selectByExample(example);
//            }
//            model.addAttribute("studentCourseList",studentCourseList);
//        }
//        return "xjgl/questionnaire/studentQuestionList";
//    }
    /**
     * 学生-保存课程评价
     */
    @RequestMapping("/saveStuQuestion")
    @ResponseBody
    public String saveStuQuestion(String courseFlow,String questionFlow,String jsondata){
        SysUser user=GlobalContext.getCurrentUser();
        EduCourse ec=new EduCourse();
        if(StringUtil.isNotBlank(courseFlow)){
            ec=eduCourseBiz.readCourse(courseFlow);
        }
        NywjStudentEvaluate stuEvaluate=new NywjStudentEvaluate();
        stuEvaluate.setStudentFlow(user.getUserFlow());
        stuEvaluate.setQuestionFlow(questionFlow);
        stuEvaluate.setCourseFlow(courseFlow);
        stuEvaluate.setCourseCode(ec.getCourseCode());
        stuEvaluate.setCourseName(ec.getCourseName());
        stuEvaluate.setSessionNumber(ec.getCourseSession());
        stuEvaluate.setCourseTypeId(ec.getCourseTypeId());
        stuEvaluate.setCourseTypeName(ec.getCourseTypeName());
        if(StringUtil.isNotBlank(jsondata)) {
            Map<String, Object> mp = JSON.parseObject(jsondata, Map.class);
            List<Map<String, Object>> dataList = (List<Map<String, Object>>) mp.get("answerList");
            if(dataList!=null&&dataList.size()>0){
                //第一次新增XML
                Document dom = DocumentHelper.createDocument();
                Element root = dom.addElement("answerList");
                for (Map<String, Object> tmap:dataList) {
                    String questionType=(String)tmap.get("questionType");
                    String questionSerial=(String)tmap.get("questionSerial");
                    String answer=(String)tmap.get("answer");
                    Element titleElement = root.addElement("answerInfo").addAttribute("id", PkUtil.getUUID());
//                    titleElement.addAttribute("questionType",questionType);
//                    titleElement.addAttribute("questionSerial",questionSerial);
//                    titleElement.addAttribute("answer",answer);
                    titleElement.addElement("questionType").setText(questionType);
                    titleElement.addElement("questionSerial").setText(questionSerial);
                    titleElement.addElement("answer").setText(answer);
                }
                stuEvaluate.setContent(dom.asXML());
            }
        }
        int num=questionnaireBiz.saveStuEvaluate(stuEvaluate);
        if(num>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    @RequestMapping("/questionResult")
    public String questionResult(String courseFlow,String questionFlow,Model model) throws DocumentException {
        //课程信息
        EduCourse eduCourse=eduCourseBiz.readCourse(courseFlow);
        model.addAttribute("eduCourse",eduCourse);
        //问卷答案信息
        NywjStudentEvaluate studentEvaluate=new NywjStudentEvaluate();
        studentEvaluate.setQuestionFlow(questionFlow);
        studentEvaluate.setCourseFlow(courseFlow);
        List<NywjStudentEvaluate> evaluateList=questionnaireBiz.searchStudentEvaluateList(studentEvaluate);
        Map<String,Object> answerDetailMap=new HashMap<>();//选项Map key:题号+选项 value:num
        if(evaluateList!=null&&evaluateList.size()>0){
            for (NywjStudentEvaluate se:evaluateList) {
                if(StringUtil.isNotBlank(se.getContent())){
                    Document dom = DocumentHelper.parseText(se.getContent());
                    Element root = dom.getRootElement();
                    if (root != null) {
                        List<Element> answerList = root.elements("answerInfo");
                        if(answerList!=null){
                            for (Element answerInfo:answerList) {
                                String questionType=answerInfo.element("questionType").getText();
                                String questionSerial=answerInfo.element("questionSerial").getText();
                                String answer=answerInfo.element("answer").getText();
                                if("Multiselect".equals(questionType)&&StringUtil.isNotBlank(answer)){
                                    String[] arr=answer.split(",");
                                    if(arr!=null&&arr.length>0){
                                        for (String a:arr) {
                                            if(answerDetailMap.get(questionSerial+","+a)==null){
                                                answerDetailMap.put(questionSerial+","+a,1);
                                            }else{
                                                answerDetailMap.put(questionSerial+","+a,(int)answerDetailMap.get(questionSerial+","+a)+1);
                                            }
                                        }
                                    }
                                }
                                if("Radio".equals(questionType)&&StringUtil.isNotBlank(answer)){
                                    if(answerDetailMap.get(questionSerial+","+answer)==null){
                                        answerDetailMap.put(questionSerial+","+answer,1);
                                    }else{
                                        answerDetailMap.put(questionSerial+","+answer,(int)answerDetailMap.get(questionSerial+","+answer)+1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            model.addAttribute("answerDetailMap",answerDetailMap);
            model.addAttribute("evaluateList",evaluateList);
        }
        List<XjQuestionDetailExt> dataList=new ArrayList<>();
        if(StringUtil.isNotBlank(questionFlow)){
            NywjCourseQuestion courseQuestion=new NywjCourseQuestion();
            courseQuestion.setRecordFlow(questionFlow);
            List<NywjCourseQuestion> courseQuestions=questionnaireBiz.searchQuestionList(courseQuestion);
            if(courseQuestions!=null&&courseQuestions.size()>0){
                courseQuestion=courseQuestions.get(0);
                model.addAttribute("courseQuestion",courseQuestion);
                NywjQuestionDetail questionDetail=new NywjQuestionDetail();
                questionDetail.setQuestionFlow(questionFlow);
                List<NywjQuestionDetail> qdList=questionnaireBiz.searchQuestionDetailList(questionDetail);
                if(qdList!=null&&qdList.size()>0){
                    for (NywjQuestionDetail qd:qdList) {
                        if(qd!=null){
                            XjQuestionDetailExt qde=new XjQuestionDetailExt();
                            NywjAnswerDetail answerDetail=new NywjAnswerDetail();
                            answerDetail.setDetailFlow(qd.getRecordFlow());
                            qde.setQuestionDetail(qd);
                            List<NywjAnswerDetail> adList=questionnaireBiz.searchAnswerDetailList(answerDetail);
                            qde.setAnswerDetailList(adList);
                            dataList.add(qde);
                        }
                    }
                }
                model.addAttribute("dataList",dataList);
            }
        }
        return "xjgl/questionnaire/questionResult";
    }
    @RequestMapping("/evaluateDetails")
    public String evaluateDetails(String courseFlow,String questionFlow,Model model){
        Map<String,String> map=new HashMap<>();
        map.put("courseFlow",courseFlow);
        map.put("questionFlow",questionFlow);
        List<Map<String,String>> dataList=questionnaireBiz.searchEvaluateDetails(map);
        model.addAttribute("dataList",dataList);
        model.addAttribute("courseFlow",courseFlow);
        model.addAttribute("questionFlow",questionFlow);
        return "xjgl/questionnaire/evaluateDetails";
    }
    @RequestMapping("/exportEvaluateDetails")
    public void exportEvaluateDetails(String courseFlow,String questionFlow,HttpServletResponse response)throws Exception {
        Map<String,String> map=new HashMap<>();
        map.put("courseFlow",courseFlow);
        map.put("questionFlow",questionFlow);
        List<Map<String,String>> dataList=questionnaireBiz.searchEvaluateDetails(map);
        String[] titles;//导出列表头信息
        titles = new String[]{
                "SID:学号",
                "USER_NAME:姓名",
                "TRAIN_CATEGORY_NAME:培养类型",
                "CLASS_NAME:行政班级",
                "USER_PHONE:联系方式",
                "ORG_NAME:培养单位"
        };
        ExcleUtile.exportSimpleExcleByObjs(titles, dataList, response.getOutputStream());
        String fileName = "问卷评价名单.xls";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
    }
    /**
     * 查看学生问卷详情
     * @return
     */
    @RequestMapping("/stuEvaluateDetail")
    public String stuEvaluateDetail(String studentFlow,String questionFlow,String courseFlow,Model model) throws Exception{
        //问卷答案信息
        NywjStudentEvaluate studentEvaluate=new NywjStudentEvaluate();
        studentEvaluate.setQuestionFlow(questionFlow);
        studentEvaluate.setCourseFlow(courseFlow);
        studentEvaluate.setStudentFlow(studentFlow);
        List<NywjStudentEvaluate> evaluateList=questionnaireBiz.searchStudentEvaluateList(studentEvaluate);
        Map<String,Object> answerDetailMap=new HashMap<>();//选项Map key:题号+选项 value:num
        if(evaluateList!=null&&evaluateList.size()>0){
            NywjStudentEvaluate se=evaluateList.get(0);
            if(StringUtil.isNotBlank(se.getContent())){
                Document dom = DocumentHelper.parseText(se.getContent());
                Element root = dom.getRootElement();
                if (root != null) {
                    List<Element> answerList = root.elements("answerInfo");
                    if(answerList!=null){
                        for (Element answerInfo:answerList) {
                            String questionType=answerInfo.element("questionType").getText();
                            String questionSerial=answerInfo.element("questionSerial").getText();
                            String answer=answerInfo.element("answer").getText();
                            if("Subjective".equals(questionType)&&StringUtil.isNotBlank(answer)){
                                answerDetailMap.put(questionSerial,answer);
                            }
                            if("Multiselect".equals(questionType)&&StringUtil.isNotBlank(answer)){
                                String[] arr=answer.split(",");
                                if(arr!=null&&arr.length>0){
                                    for (String a:arr) {
                                        if(answerDetailMap.get(questionSerial+","+a)==null){
                                            answerDetailMap.put(questionSerial+","+a,1);
                                        }else{
                                            answerDetailMap.put(questionSerial+","+a,(int)answerDetailMap.get(questionSerial+","+a)+1);
                                        }
                                    }
                                }
                            }
                            if("Radio".equals(questionType)&&StringUtil.isNotBlank(answer)){
                                if(answerDetailMap.get(questionSerial+","+answer)==null){
                                    answerDetailMap.put(questionSerial+","+answer,1);
                                }else{
                                    answerDetailMap.put(questionSerial+","+answer,(int)answerDetailMap.get(questionSerial+","+answer)+1);
                                }
                            }
                        }
                    }
                }
            }
            model.addAttribute("answerDetailMap",answerDetailMap);
        }
        List<XjQuestionDetailExt> dataList=new ArrayList<>();
        if(StringUtil.isNotBlank(questionFlow)){
            NywjCourseQuestion courseQuestion=new NywjCourseQuestion();
            courseQuestion.setRecordFlow(questionFlow);
            List<NywjCourseQuestion> courseQuestions=questionnaireBiz.searchQuestionList(courseQuestion);
            if(courseQuestions!=null&&courseQuestions.size()>0){
                courseQuestion=courseQuestions.get(0);
                model.addAttribute("courseQuestion",courseQuestion);
                NywjQuestionDetail questionDetail=new NywjQuestionDetail();
                questionDetail.setQuestionFlow(questionFlow);
                List<NywjQuestionDetail> qdList=questionnaireBiz.searchQuestionDetailList(questionDetail);
                if(qdList!=null&&qdList.size()>0){
                    for (NywjQuestionDetail qd:qdList) {
                        if(qd!=null){
                            XjQuestionDetailExt qde=new XjQuestionDetailExt();
                            NywjAnswerDetail answerDetail=new NywjAnswerDetail();
                            answerDetail.setDetailFlow(qd.getRecordFlow());
                            qde.setQuestionDetail(qd);
                            List<NywjAnswerDetail> adList=questionnaireBiz.searchAnswerDetailList(answerDetail);
                            qde.setAnswerDetailList(adList);
                            dataList.add(qde);
                        }
                    }
                }
                model.addAttribute("dataList",dataList);
            }
        }
        return "xjgl/questionnaire/stuEvaluateDetail";
    }

    @RequestMapping("/downLoadStuQuestions")
    public void downLoadStuQuestions(String studentPeriod,String questionName,String courseName,String courseCode,String courseTypeId, HttpServletRequest request, HttpServletResponse response) throws Exception{
        String folder=System.getProperty("java.io.tmpdir")+ File.separator;
        String zipFile = PkUtil.getUUID();
        String dir = folder+zipFile;
        File dirFile = new File(dir);
        if(dirFile.exists()==false){
            dirFile.mkdirs();
        }
        Map<String,String> map=new HashMap<>();
        map.put("studentPeriod",studentPeriod);
        map.put("questionName",questionName);
        map.put("courseName",courseName);
        map.put("courseCode",courseCode);
        map.put("courseTypeId",courseTypeId);
        List<Map<String,String>> questions=questionnaireBiz.searchQuestions(map);
        List<WordprocessingMLPackage> addTemplates = new ArrayList<WordprocessingMLPackage>();
        if(questions!=null&&questions.size()>0){
            for (Map<String,String> mp:questions) {
                String name="";
                WordprocessingMLPackage temeplete = new WordprocessingMLPackage();
                ServletContext context =  request.getServletContext();
                String watermark = GeneralMethod.getWatermark(null);
                Map<String, Object> dataMap = new HashMap<String, Object>();
                if(StringUtil.isNotBlank(mp.get("COURSE_FLOW"))&&StringUtil.isNotBlank(mp.get("QUESTION_FLOW"))){
                    List<ItemGroupData> questionTargetList = new ArrayList<ItemGroupData>();
                    //课程信息
                    EduCourse eduCourse=eduCourseBiz.readCourse(mp.get("COURSE_FLOW"));
                    //问卷答案信息
                    NywjStudentEvaluate studentEvaluate=new NywjStudentEvaluate();
                    studentEvaluate.setQuestionFlow(mp.get("QUESTION_FLOW"));
                    studentEvaluate.setCourseFlow(mp.get("COURSE_FLOW"));
                    List<NywjStudentEvaluate> evaluateList=questionnaireBiz.searchStudentEvaluateList(studentEvaluate);
                    int num=0;
                    Map<String,Object> answerDetailMap=new HashMap<>();//选项Map key:题号+选项 value:num
                    if(evaluateList!=null&&evaluateList.size()>0){
                        num=evaluateList.size();
                        for (NywjStudentEvaluate se:evaluateList) {
                            if(StringUtil.isNotBlank(se.getContent())){
                                Document dom = DocumentHelper.parseText(se.getContent());
                                Element root = dom.getRootElement();
                                if (root != null) {
                                    List<Element> answerList = root.elements("answerInfo");
                                    if(answerList!=null){
                                        for (Element answerInfo:answerList) {
                                            String questionType=answerInfo.element("questionType").getText();
                                            String questionSerial=answerInfo.element("questionSerial").getText();
                                            String answer=answerInfo.element("answer").getText();
                                            if("Multiselect".equals(questionType)&&StringUtil.isNotBlank(answer)){
                                                String[] arr=answer.split(",");
                                                if(arr!=null&&arr.length>0){
                                                    for (String a:arr) {
                                                        if(answerDetailMap.get(questionSerial+","+a)==null){
                                                            answerDetailMap.put(questionSerial+","+a,1);
                                                        }else{
                                                            answerDetailMap.put(questionSerial+","+a,(int)answerDetailMap.get(questionSerial+","+a)+1);
                                                        }
                                                    }
                                                }
                                            }
                                            if("Radio".equals(questionType)&&StringUtil.isNotBlank(answer)){
                                                if(answerDetailMap.get(questionSerial+","+answer)==null){
                                                    answerDetailMap.put(questionSerial+","+answer,1);
                                                }else{
                                                    answerDetailMap.put(questionSerial+","+answer,(int)answerDetailMap.get(questionSerial+","+answer)+1);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(StringUtil.isNotBlank(mp.get("QUESTION_FLOW"))){
                        NywjCourseQuestion courseQuestion=new NywjCourseQuestion();
                        courseQuestion.setRecordFlow(mp.get("QUESTION_FLOW"));
                        List<NywjCourseQuestion> courseQuestions=questionnaireBiz.searchQuestionList(courseQuestion);
                        if(courseQuestions!=null&&courseQuestions.size()>0){
                            courseQuestion=courseQuestions.get(0);
                            NywjQuestionDetail questionDetail=new NywjQuestionDetail();
                            questionDetail.setQuestionFlow(mp.get("QUESTION_FLOW"));
                            List<NywjQuestionDetail> qdList=questionnaireBiz.searchQuestionDetailList(questionDetail);
                            if(qdList!=null&&qdList.size()>0){
                                for (NywjQuestionDetail qd:qdList) {
                                    ItemGroupData  igd = new ItemGroupData();
                                    Map<String , Object> objMap = new HashMap<String, Object>();
                                    String answerText="";//选项及人数统计
                                    if(qd!=null&&!"Subjective".equals(qd.getQuestionTypeId())){
                                        NywjAnswerDetail answerDetail=new NywjAnswerDetail();
                                        answerDetail.setDetailFlow(qd.getRecordFlow());
                                        List<NywjAnswerDetail> adList=questionnaireBiz.searchAnswerDetailList(answerDetail);
                                        if(adList!=null&&adList.size()>0){
                                            for (NywjAnswerDetail ad:adList) {
                                                String numN=answerDetailMap.get(qd.getSerial()+","+ad.getSerial())==null?"0":(answerDetailMap.get(qd.getSerial()+","+ad.getSerial())+"");
                                                answerText+=ad.getSerial()+"、"+ad.getAnswerName()+" "+numN+"人 ";
                                            }
                                        }
                                        objMap.put("questionText",qd.getSerial()+"、"+qd.getQuestionTitle());
                                        objMap.put("answerText",answerText);
                                        igd.setObjMap(objMap);
                                        questionTargetList.add(igd);
                                    }
                                }
                            }
                            dataMap.put("questionName", courseQuestion.getQuestionName());//问卷名称
                            dataMap.put("courseSession", eduCourse==null?"":eduCourse.getCourseSession());//年级
                            dataMap.put("count", num+"");//人数
                            dataMap.put("courseName", eduCourse==null?"":eduCourse.getCourseName());//课程名称
                            dataMap.put("courseCode", eduCourse==null?"":eduCourse.getCourseCode());//课程代码
                            dataMap.put("questionTargetList", questionTargetList);
                            name=  eduCourse.getCourseSession()+eduCourse.getCourseName()+"问卷分析.docx";
                        }
                    }
                }

                String path ="/jsp/xjgl/print/questionResult.docx";//中文模板
                temeplete = Docx4jUtil.convert(new File(context.getRealPath(path)), dataMap,watermark,true);
                addTemplates.add(temeplete);
                File f = new File(dir+File.separator+name);
                temeplete.save(f);
            }
        ZipUtil.makeDirectoryToZip(dirFile, new File(folder+zipFile+".zip"), "", 7);
        String fileName = "问卷分析.zip";
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.setContentType("application/octet-stream;charset=UTF-8");
        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
        byte[] data = getByte(new File(folder+zipFile+".zip"));
        if (data != null) {
            outputStream.write(data);
            new File(folder+zipFile+".zip").delete();
        }
        outputStream.flush();
        outputStream.close();
        }
    }
    public static byte[] getByte(File file) throws Exception {
        if (file == null) {
            return null;
        }
        try {
            int length = (int) file.length();
            if (length > Integer.MAX_VALUE) {    //当文件的长度超过了int的最大值
                System.out.println("this file is max ");
            }
            FileInputStream stream = new FileInputStream(file);
            ByteArrayOutputStream out = new ByteArrayOutputStream(length);
            byte[] b = new byte[length];
            int n;
            while ((n = stream.read(b)) != -1)
                out.write(b, 0, n);
            stream.close();
            out.close();
            return out.toByteArray();
        } catch (IOException e) {
        }
        return null;
    }
}
