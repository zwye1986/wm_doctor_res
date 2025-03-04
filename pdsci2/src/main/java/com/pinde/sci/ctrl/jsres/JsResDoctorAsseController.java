package com.pinde.sci.ctrl.jsres;


import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.AfterRecTypeEnum;
import com.pinde.core.model.*;
import com.pinde.core.page.PageHelper;
import com.pinde.sci.biz.sch.ISchArrangeResultBiz;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.*;
import com.pinde.sci.biz.jszy.IJszyDoctorReductionBiz;
import com.pinde.sci.biz.pub.IFileBiz;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.biz.res.*;
import com.pinde.sci.biz.sch.*;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.core.common.form.UserResumeExtInfoForm;
import org.apache.commons.collections4.CollectionUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/jsres/asse")
public class JsResDoctorAsseController extends GeneralController {
    @Autowired
    private IJsResDoctorBiz jsResDoctorBiz;
    @Autowired
    private IJsResDoctorRecruitBiz jsResDoctorRecruitBiz;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IResDoctorBiz resDoctorBiz;
    @Autowired
    private IPubUserResumeBiz userResumeBiz;
    @Autowired
    private OrgBizImpl orgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IResRecBiz resRecBiz;
    @Autowired
    private ISchRotationDeptAfterBiz afterBiz;
    @Autowired
    private IResScoreBiz resScoreBiz;
    @Autowired
    private ISchRotationBiz rotationBiz;
    @Autowired
    private ISchArrangeResultBiz resultBiz;
    @Autowired
    private ISchRotationGroupBiz groupBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private ISchDoctorDeptBiz doctorDeptBiz;
    @Autowired
    private ISchRotationDeptBiz rotationDeptBiz;
    @Autowired
    private IResTestConfigBiz resTestConfigBiz;
    public static final String SCOREYEAR_NOT_FIND="请选择成绩年份";
    @Autowired
    private IJsResGraduationApplyBiz graduationApplyBiz;
    @Autowired
    private IFileBiz fileBiz;
    @Autowired
    private IResSchProcessExpressBiz expressBiz;
    @Autowired
    private IResDoctorDelayTeturnBiz resDoctorDelayTeturnBiz;
    @Autowired
    private IResDoctorRecruitBiz doctorRecruitBiz;
    @Autowired
    private IJszyDoctorReductionBiz reductionBiz;

    @RequestMapping(value="/recruitDetail")
    public String recruitDetail(String recruitFlow,Model model,String applyYear) {
        return "jsres/doctor/asseApplication/schDetail";
    }

    @RequestMapping(value="/recruitDetailData")
    public String recruitDetailData(String recruitFlow,Model model,String applyYear) {

        //培训记录
        com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        model.addAttribute("recruit", recruit);
        //培训方案
        SchRotation rotation = rotationBiz.getRotationByRecruit(recruit);
        if(rotation!=null&&recruit!=null&&StringUtil.isNotBlank(rotation.getRotationFlow())) {

            model.addAttribute("rotation", rotation);

            String doctorFlow = recruit.getDoctorFlow();
            String rotationFlow = rotation.getRotationFlow();

            ResDoctor resDoctor = resDoctorBiz.findByFlow(doctorFlow);
            resDoctor.setRotationFlow(rotationFlow);

            SysUser sysUser = userBiz.readSysUser(doctorFlow);
            model.addAttribute("sysUser", sysUser);

            //学员的减免方案
            List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchDoctorDeptForReductionIgnoreStatus
                    (doctorFlow, rotationFlow);
            Map<String, SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
            if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
                for (SchDoctorDept sdd : doctorDeptList) {
                    String key = sdd.getGroupFlow() + sdd.getStandardDeptId();
                    doctorDeptMap.put(key, sdd);
                }
            }
            Map<String,Integer> groupRowSpan=new HashMap<>();
            Map<String,Integer> deptRowSpan=new HashMap<>();


            //方案中的组
            List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(rotationFlow);
            model.addAttribute("groupList", groupList);
            //方案中的科室
            List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotationFlow);

            Map<String, List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();
            Map<String, Object> afterImgMap = new HashMap<String, Object>();
            Map<String, List<SchArrangeResult>> resultMap = new HashMap<String, List<SchArrangeResult>>();
            Map<String, Float> realMonthMap = new HashMap<String, Float>();
            Map<String,Object> biMap=new HashMap<>();
            for (SchRotationDept dept : deptList) {
                List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
                if (temp == null) {
                    temp = new ArrayList<SchRotationDept>();
                }
                rotationDeptMap.put(dept.getGroupFlow(), temp);
                String reductionKey = dept.getGroupFlow() + dept.getStandardDeptId();
                SchDoctorDept reductionDept = doctorDeptMap.get(reductionKey);
                if (reductionDept != null) {
                    if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(reductionDept.getRecordStatus())) {
                        continue;
                    }
                    String reductionSchMonth = reductionDept.getSchMonth();
                    dept.setSchMonth(reductionSchMonth);
                }
                Integer count= groupRowSpan.get(dept.getGroupFlow());
                if(count==null)
                    count=0;
                count++;
                groupRowSpan.put(dept.getGroupFlow(),count);
                temp.add(dept);

                //轮转科室
                List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(),dept.getStandardDeptId(),doctorFlow);
                if(resultList!=null&&resultList.size()>0)
                {
                    String key = dept.getGroupFlow() + dept.getStandardDeptId();
                    resultMap.put(key, resultList);
                    Integer t=groupRowSpan.get(dept.getGroupFlow());
                    if(t==null)
                        t=0;
                    groupRowSpan.put(dept.getGroupFlow(),resultList.size()+t);
                    deptRowSpan.put(key,resultList.size());
                    for (SchArrangeResult result : resultList) {
                        Float month = realMonthMap.get(key);
                        if (month == null) {
                            month = 0f;
                        }
                        String realMonth = result.getSchMonth();
                        if (StringUtil.isNotBlank(realMonth)) {
                            Float realMonthF = Float.parseFloat(realMonth);
                            month += realMonthF;
                        }
                        realMonthMap.put(key, month);
                    }
                }else{
                    Integer t=groupRowSpan.get(dept.getGroupFlow());
                    if(t==null)
                        t=0;
                    groupRowSpan.put(dept.getGroupFlow(),1+t);
                }

//                //完成比例与审核比例
//                JsresDoctorDeptDetail doctorDeptDetail=resultBiz.deptDoctorWorkDetail(dept.getRecordFlow(),dept.getRotationFlow(),doctorFlow);
//                biMap.put(dept.getRecordFlow(),doctorDeptDetail);
            }

            //完成比例与审核比例
            List<JsresDoctorDeptDetail> details=resultBiz.deptDoctorAllWorkDetail(rotation.getRotationFlow(),doctorFlow, applyYear);
            if(details!=null&&details.size()>0)
            {
                for(JsresDoctorDeptDetail d:details)
                {
                    biMap.put(d.getSchStandardDeptFlow(),d);
                }
            }

            //出科考核表

            List<SchRotationDeptAfterWithBLOBs> afters=afterBiz.getAfterByDoctorFlow(doctorFlow, applyYear);
            if(afters!=null&&afters.size()>0)
            {
                for(SchRotationDeptAfterWithBLOBs a:afters)
                {
                    List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
                    String imageUrls=a.getImageUrls();
                    String thumbUrls=a.getThumbUrls();
                    if(StringUtil.isNotBlank(imageUrls)&&StringUtil.isNotBlank(thumbUrls))
                    {
                        String images[]=imageUrls.split(",");
                        String thumbs[]=thumbUrls.split(",");
                        for(int i=0;i<images.length;i++)
                        {
                            Map<String, Object> recContent = new HashMap<String, Object>();
                            recContent.put("imageUrl",images[i]);
                            if(i<thumbs.length)
                                recContent.put("thumbUrl",thumbs[i]);
                            imagelist.add(recContent);
                        }
                    }
                    afterImgMap.put(a.getSchRotationDeptFlow(), imagelist);
                }
            }
            //平均完成比例与平均审核比例
            Map<String,Object> avgBiMap=resultBiz.doctorDeptAvgWorkDetail(recruitFlow, applyYear);

            model.addAttribute("avgBiMap", avgBiMap);
            model.addAttribute("biMap", biMap);//各科室比例
            model.addAttribute("rotationDeptMap", rotationDeptMap);
            model.addAttribute("afterImgMap", afterImgMap);

            model.addAttribute("resultMap", resultMap);
            model.addAttribute("groupRowSpan", groupRowSpan);
            model.addAttribute("deptRowSpan", deptRowSpan);
            model.addAttribute("realMonthMap", realMonthMap);
        }
        return "jsres/doctor/asseApplication/schDetailData";
    }
    @RequestMapping(value="/showScore")
    public String showScore(String recruitFlow,String scoreFlow,Model model) throws DocumentException {

        //培训记录
        com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        model.addAttribute("recruit", recruit);
        ResScore score=resScoreBiz.searchByScoreFlow(scoreFlow);
        model.addAttribute("score",score);
        //是否是全科、助理全科、全科方向（中医）、全科方向（西医）
        String isAssiGeneral = "";
        if("0700".equals(recruit.getSpeId())||"51".equals(recruit.getSpeId())||"52".equals(recruit.getSpeId())
                ||"18".equals(recruit.getSpeId())||"50".equals(recruit.getSpeId())){
            isAssiGeneral = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }else {
            isAssiGeneral = com.pinde.core.common.GlobalConstant.FLAG_N;
        }
        model.addAttribute("isAssiGeneral",isAssiGeneral);

        Map<String,String> extScore=new HashMap<String,String>();
        String content = null==score ? "":score.getExtScore();
        if(StringUtil.isNotBlank(content)) {
            Document doc = DocumentHelper.parseText(content);
            Element root = doc.getRootElement();
            Element extScoreInfo = root.element("extScoreInfo");
            if (extScoreInfo != null) {
                List<Element> extInfoAttrEles = extScoreInfo.elements();
                if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                    for (Element attr : extInfoAttrEles) {
                        String attrName = attr.getName();
                        String attrValue = attr.getText();
                        extScore.put(attrName, attrValue);
                    }
                }
            }
        }
        model.addAttribute("extScore",extScore);
        //附件
        List<PubFile> files = fileBiz.findFileByTypeFlow("asseApplication",recruitFlow);
        PubFile file = null;
        if(files != null && files.size() > 0){
            file = files.get(0);
            model.addAttribute("file",file);
        }
        return "jsres/doctor/asseApplication/showScore";
    }

    @RequestMapping(value="/showAllInfoMain")
    public String showAllInfoMain(String recruitFlow,Model model,String isShow) throws DocumentException {
        //培训记录
        com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        model.addAttribute("recruit", recruit);
        return "jsres/doctor/asseApplication/showAllInfo/main";
    }

    @RequestMapping(value="/AsseInfo")
    public String AsseInfo(String recruitFlow,Model model,String applyYear,String applyFlow,String flag,String roleFlag) throws DocumentException {
        //培训记录
        com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        model.addAttribute("doctorRecruit", recruit);
        //查询减免信息
        ResDoctorReduction reduction = reductionBiz.findReductionByRecruitFlowandStatusId(recruitFlow,"GlobalPassed");
        model.addAttribute("reduction", reduction);
        String operUserFlow=recruit.getDoctorFlow();
        //个人信息
        ResDoctor resDoctor=resDoctorBiz.searchByUserFlow(operUserFlow);
//        SysUser currUser = userBiz.readSysUser(operUserFlow);
//        model.addAttribute("currUser",currUser);
        SchRotation rotation = rotationBiz.readSchRotation(resDoctor.getRotationFlow());
//        if (resDoctor.getSessionNumber().compareTo("2019") < 0) {
//            rotation = rotationBiz.getRotationByRecruitNew(recruit);
//        } else {
//            //默认赋值学员方案
//            rotation = rotationBiz.getRotationByRecruit(recruit);
//        }
        List<ResScore> scorelist = resScoreBiz.selectByRecruitFlowAndScoreType(recruitFlow, com.pinde.core.common.enums.ResScoreTypeEnum.PublicScore.getId());
        //公共成绩
        ResScore publicScore=null;
        if(null!=scorelist&&scorelist.size()>0)
        {
            publicScore=scorelist.get(0);
            model.addAttribute("publicScoreUser",userBiz.readSysUser(publicScore.getCreateUserFlow()).getUserName());
            //公共科目成绩详情
            String content = null==publicScore ? "":publicScore.getExtScore();
            Map<String,String> extScoreMap=new HashMap<String,String>();
            if(StringUtil.isNotBlank(content)) {
                Document doc = DocumentHelper.parseText(content);
                Element root = doc.getRootElement();
                Element extScoreInfo = root.element("extScoreInfo");
                if (extScoreInfo != null) {
                    List<Element> extInfoAttrEles = extScoreInfo.elements();
                    if (extInfoAttrEles != null && extInfoAttrEles.size() > 0) {
                        for (Element attr : extInfoAttrEles) {
                            String attrName = attr.getName();
                            String attrValue = attr.getText();
                            extScoreMap.put(attrName, attrValue);
                        }
                    }
                }
            }
            model.addAttribute("extScore",extScoreMap);
        }
        model.addAttribute("publicScore",publicScore);
        SysUser sysUser=userBiz.readSysUser(operUserFlow);
        PubUserResume pubUserResume = userResumeBiz.readPubUserResume(operUserFlow);
        if(pubUserResume != null){
            String xmlContent =  pubUserResume.getUserResume();
            if(StringUtil.isNotBlank(xmlContent)){
                //xml转换成JavaBean
                UserResumeExtInfoForm  userResumeExt=null;
                userResumeExt=userResumeBiz.converyToJavaBean(xmlContent,UserResumeExtInfoForm.class);
//				UserResumeExtInfoForm  userResumeExt = JaxbUtil.converyToJavaBean(xmlContent, UserResumeExtInfoForm.class);
                if(userResumeExt!=null){

                    if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
                        List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.GraduateSchool.getId());
                        if(sysDictList!=null && !sysDictList.isEmpty()){
                            for(SysDict dict:sysDictList){
                                if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
                                    if(dict.getDictId().equals(userResumeExt.getGraduatedId())){
                                        userResumeExt.setGraduatedName(dict.getDictName());
                                    }
                                }
                            }

                        }
                    }
                    model.addAttribute("userResumeExt", userResumeExt);
                }
            }
        }
        JsresGraduationApply jsresGraduationApply = graduationApplyBiz.searchByRecruitFlow(recruitFlow, applyYear);
		//保存医师培训时间
		if(recruit!=null)
		{   String endTime="";
			String startTime="";
			//开始时间
			String recruitDate=recruit.getRecruitDate();
			//培养年限
			String trianYear=recruit.getTrainYear();
			String graudationYear=recruit.getGraduationYear();
			if(StringUtil.isNotBlank(recruitDate)&&StringUtil.isNotBlank(graudationYear))
			{
				try {
					int year=0;
					year=Integer.valueOf(graudationYear)-Integer.valueOf(recruitDate.substring(0,4));
                    if(year!=0) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = sdf.parse(recruitDate);
                        startTime = recruitDate;
                        //然后使用Calendar操作日期
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(date);
                        calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
                        calendar.add(Calendar.DATE, -1);
                        //把得到的日期格式化成字符串类型的时间
                        endTime = sdf.format(calendar.getTime());
                    }
				} catch (Exception e) {
					endTime="";
				}
			}
			//如果没有结业考核年份，按照届别与培养年限计算
			if(StringUtil.isNotBlank(recruitDate)&&StringUtil.isNotBlank(trianYear)&&StringUtil.isBlank(endTime))
			{
				int year = 0;
                if (com.pinde.core.common.enums.JsResTrainYearEnum.OneYear.getId().equals(trianYear)) {
					year = 1;
				}
                if (com.pinde.core.common.enums.JsResTrainYearEnum.TwoYear.getId().equals(trianYear)) {
					year = 2;
				}
                if (com.pinde.core.common.enums.JsResTrainYearEnum.ThreeYear.getId().equals(trianYear)) {
					year = 3;
				}
				if (year != 0) {
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
						Date date = sdf.parse(recruitDate);
						startTime = recruitDate;
						//然后使用Calendar操作日期
						Calendar calendar = Calendar.getInstance();
						calendar.setTime(date);
						calendar.add(Calendar.YEAR, year);//对小时数进行+2操作,同理,减2为-2
						calendar.add(Calendar.DATE,-1);
						//把得到的日期格式化成字符串类型的时间
						endTime = sdf.format(calendar.getTime());

					} catch (Exception e) {

					}
				}
			}
            model.addAttribute("startDate", startTime);
            model.addAttribute("endDate", endTime);
		}
        //平均完成比例与平均审核比例
        Map<String,Object> avgBiMap=resultBiz.doctorDeptAvgWorkDetail(recruitFlow, applyYear);
        if(null != avgBiMap.get("avgComplete")){
            String avgComplete = (String) avgBiMap.get("avgComplete");
            model.addAttribute("avgCompleteDouble",Double.parseDouble(avgComplete));
        }else{
            model.addAttribute("avgCompleteDouble",0.00);
        }
        model.addAttribute("avgBiMap", avgBiMap);

        if(rotation!=null) {
            //学员的减免方案
//            List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchDoctorDeptForReductionIgnoreStatus
//                    (operUserFlow, rotation.getRotationFlow());
//            Map<String, SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
//            if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
//                for (SchDoctorDept sdd : doctorDeptList) {
//                    String key = sdd.getGroupFlow() + sdd.getStandardDeptId();
//                    doctorDeptMap.put(key, sdd);
//                }
//            }
//            List<SchArrangeResult> resultAllList = resultBiz.searchSchArrangeResultByDoctor(operUserFlow);
            float allMonth = resultBiz.getDoctorSchMonthSumInfo(rotation.getRotationFlow(), operUserFlow);
            //方案中的科室
//            List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotation.getRotationFlow());
//            for (SchRotationDept dept : deptList) {
//                String reductionKey = dept.getGroupFlow() + dept.getStandardDeptId();
//                SchDoctorDept reductionDept = doctorDeptMap.get(reductionKey);
//                if (reductionDept != null) {
//                    if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(reductionDept.getRecordStatus())) {
//                        continue;
//                    }
//                    String reductionSchMonth = reductionDept.getSchMonth();
//                    dept.setSchMonth(reductionSchMonth);
//                }
//                //轮转科室
//                List<SchArrangeResult> resultList = resultAllList.stream().filter(schArrangeResult -> dept.getGroupFlow().equals(schArrangeResult.getStandardGroupFlow())
//                        && dept.getStandardDeptId().equals(schArrangeResult.getStandardDeptId()) ).collect(Collectors.toList());
////                List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(), dept.getStandardDeptId(), operUserFlow);
//                if (resultList != null && resultList.size() > 0) {
//                    for (SchArrangeResult result : resultList) {
//                        String realMonth = result.getSchMonth();
//                        if (StringUtil.isNotBlank(realMonth)) {
//                            Float realMonthF = Float.parseFloat(realMonth);
//                            allMonth += realMonthF;
//                        }
//                    }
//                }
//
//            }
            model.addAttribute("allMonth", allMonth);
//            String rotationFlow = rotation.getRotationFlow();
//            String doctorFlow = recruit.getDoctorFlow();
//            Map<String, Integer> groupRowSpan = new HashMap<>();
//            Map<String, Integer> deptRowSpan = new HashMap<>();
//            //方案中的组
//            List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(rotationFlow);
//            model.addAttribute("groupList", groupList);


//            Map<String, List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();
//            Map<String, Object> afterImgMap = new HashMap<String, Object>();
//            Map<String, List<SchArrangeResult>> resultMap = new HashMap<String, List<SchArrangeResult>>();
//            Map<String, Float> realMonthMap = new HashMap<String, Float>();
//            Map<String, Object> biMap = new HashMap<>();
//            for (SchRotationDept dept : deptList) {
//                List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
//                if (temp == null) {
//                    temp = new ArrayList<SchRotationDept>();
//                }
//                rotationDeptMap.put(dept.getGroupFlow(), temp);
//                String reductionKey = dept.getGroupFlow() + dept.getStandardDeptId();
//                SchDoctorDept reductionDept = doctorDeptMap.get(reductionKey);
//                if (reductionDept != null) {
//                    if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(reductionDept.getRecordStatus())) {
//                        continue;
//                    }
//                    String reductionSchMonth = reductionDept.getSchMonth();
//                    dept.setSchMonth(reductionSchMonth);
//                }
//                Integer count = groupRowSpan.get(dept.getGroupFlow());
//                if (count == null)
//                    count = 0;
//                count++;
//                groupRowSpan.put(dept.getGroupFlow(), count);
//                temp.add(dept);
//
//                //轮转科室
//                List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(), dept.getStandardDeptId(), doctorFlow);
//                if (resultList != null && resultList.size() > 0) {
//                    String key = dept.getGroupFlow() + dept.getStandardDeptId();
//                    resultMap.put(key, resultList);
//                    Integer t = groupRowSpan.get(dept.getGroupFlow());
//                    if (t == null)
//                        t = 0;
//                    groupRowSpan.put(dept.getGroupFlow(), resultList.size() + t);
//                    deptRowSpan.put(key, resultList.size());
//                    for (SchArrangeResult result : resultList) {
//                        Float month = realMonthMap.get(key);
//                        if (month == null) {
//                            month = 0f;
//                        }
//                        String realMonth = result.getSchMonth();
//                        if (StringUtil.isNotBlank(realMonth)) {
//                            Float realMonthF = Float.parseFloat(realMonth);
//                            month += realMonthF;
//                        }
//                        realMonthMap.put(key, month);
//                    }
//                } else {
//                    Integer t = groupRowSpan.get(dept.getGroupFlow());
//                    if (t == null)
//                        t = 0;
//                    groupRowSpan.put(dept.getGroupFlow(), 1 + t);
//                }
//
//            }

            //完成比例与审核比例
//            List<JsresDoctorDeptDetail> details = resultBiz.deptDoctorAllWorkDetail(rotation.getRotationFlow(), operUserFlow, applyYear);
//            if (details != null && details.size() > 0) {
//                for (JsresDoctorDeptDetail d : details) {
//                    biMap.put(d.getSchStandardDeptFlow(), d);
//                }
//            }
//            //出科考核表
//            List<ResSchProcessExpress> recs = expressBiz.searchByUserFlowAndTypeId(doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
//            if (recs != null && recs.size() > 0) {
//                for (ResSchProcessExpress rec : recs) {
//                    List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
//                    String content = null == rec ? "" : rec.getRecContent();
//                    if (StringUtil.isNotBlank(content)) {
//                        Document doc = DocumentHelper.parseText(content);
//                        Element root = doc.getRootElement();
//                        List<Element> imageEles = root.elements();
//                        if (imageEles != null && imageEles.size() > 0) {
//                            for (Element image : imageEles) {
//                                Map<String, Object> recContent = new HashMap<String, Object>();
//                                String imageFlow = image.attributeValue("imageFlow");
//                                List<Element> elements = image.elements();
//                                for (Element attr : elements) {
//                                    String attrName = attr.getName();
//                                    String attrValue = attr.getText();
//                                    recContent.put(attrName, attrValue);
//                                }
//                                imagelist.add(recContent);
//                            }
//                        }
//                    }
//                    afterImgMap.put(rec.getSchRotationDeptFlow(), imagelist);
//                }
//            }
//            model.addAttribute("biMap", biMap);//各科室比例
//            model.addAttribute("rotationDeptMap", rotationDeptMap);
//            model.addAttribute("afterImgMap", afterImgMap);
//
//            model.addAttribute("resultMap", resultMap);
//            model.addAttribute("groupRowSpan", groupRowSpan);
//            model.addAttribute("deptRowSpan", deptRowSpan);
//            model.addAttribute("realMonthMap", realMonthMap);
        }
        //查延期次数
        ResDocotrDelayTeturn resDocotrDelayTeturn = new ResDocotrDelayTeturn();
        resDocotrDelayTeturn.setDoctorFlow(resDoctor.getDoctorFlow());
        resDocotrDelayTeturn.setTypeId(com.pinde.core.common.enums.ResRecTypeEnum.Delay.getId());
        List<ResDocotrDelayTeturn> resDocotrDelayTeturns = resDoctorDelayTeturnBiz.searchInfoNew(resDocotrDelayTeturn, null, null, null);
        if(CollectionUtils.isNotEmpty(resDocotrDelayTeturns) && resDocotrDelayTeturns.size() > 1){
            model.addAttribute("delayNum", resDocotrDelayTeturns.size());
        }
        //查考核次数 （一次首考+两次补考）
        List<JsresExamSignup> signupLists = doctorRecruitBiz.readDoctorExanSignUps(resDoctor.getDoctorFlow(), null);
        if(CollectionUtils.isNotEmpty(signupLists) && signupLists.size() > 2){
            model.addAttribute("signNum", signupLists.size());
        }
        //是否是全科、助理全科、全科方向（中医）、全科方向（西医）
        String isAssiGeneral = com.pinde.core.common.GlobalConstant.FLAG_N;
        if("0700".equals(recruit.getSpeId())||"51".equals(recruit.getSpeId())||"52".equals(recruit.getSpeId())
                ||"18".equals(recruit.getSpeId())||"50".equals(recruit.getSpeId())){
            isAssiGeneral = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        List<PubFile> files = fileBiz.findFileByTypeFlow("asseApplication",recruitFlow);
        PubFile file = null;
        if(files != null && files.size() > 0){
            file = files.get(0);
            model.addAttribute("file",file);
        }
        List<JsresGraduationApplyLog> logs = graduationApplyBiz.getAuditLogsByApplyFlow(applyFlow);
        // 是否在异常记录表中
        GraduationDoctorTemp graduationDoctorTemp = graduationApplyBiz.getGraduationDoctorTemp(resDoctor.getDoctorFlow());
        // 默认不存在异常记录表
        String tempDoctorFlag = GlobalConstant.FLAG_N;
        if(graduationDoctorTemp != null){
            tempDoctorFlag = GlobalConstant.FLAG_Y;
        }
        model.addAttribute("tempDoctorFlag", tempDoctorFlag);
        model.addAttribute("recruit", recruit);
        model.addAttribute("flag", flag);
        model.addAttribute("logs", logs);
        model.addAttribute("resDoctor",resDoctor);
        model.addAttribute("user",sysUser);
        model.addAttribute("isAssiGeneral",isAssiGeneral);
        model.addAttribute("jsresGraduationApply",jsresGraduationApply);
        model.addAttribute("recruitFlow",recruitFlow);
        model.addAttribute("roleFlag", roleFlag);
        return "jsres/doctor/asseApplication/showAllInfo/AsseInfo";
    }
    @RequestMapping(value="/AsseFile")
    public String AsseFile(String recruitFlow,String applyYear,Model model) throws DocumentException {
        //培训记录
        com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(recruitFlow);
        model.addAttribute("recruit", recruit);

        JsresGraduationApply jsresGraduationApply = graduationApplyBiz.searchByRecruitFlow(recruitFlow, applyYear);
        model.addAttribute("jsresGraduationApply",jsresGraduationApply);
        //培训方案
//        SchRotation rotation = rotationBiz.getRotationByRecruit(recruit);
        SchRotation rotation = rotationBiz.readSchRotation(recruit.getRotationFlow());
        if(rotation!=null&&recruit!=null&&StringUtil.isNotBlank(rotation.getRotationFlow())) {
            model.addAttribute("rotation", rotation);
            String doctorFlow = recruit.getDoctorFlow();
            String rotationFlow = rotation.getRotationFlow();
            ResDoctor resDoctor = resDoctorBiz.findByFlow(doctorFlow);
            model.addAttribute("doctor",resDoctor);
//            PubUserResume pubUserResume = userResumeBiz.readPubUserResume(doctorFlow);
//            if(pubUserResume != null){
//                String xmlContent =  pubUserResume.getUserResume();
//                if(StringUtil.isNotBlank(xmlContent)){
//                    //xml转换成JavaBean
//                    UserResumeExtInfoForm userResumeExt=null;
//                    userResumeExt=userResumeBiz.converyToJavaBean(xmlContent,UserResumeExtInfoForm.class);
//                    if(userResumeExt!=null){
//                        if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
//                            List<SysDict> sysDictList=dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.GraduateSchool.getId());
//                            if(sysDictList!=null && !sysDictList.isEmpty()){
//                                for(SysDict dict:sysDictList){
//                                    if(StringUtil.isNotBlank(userResumeExt.getGraduatedId())){
//                                        if(dict.getDictId().equals(userResumeExt.getGraduatedId())){
//                                            userResumeExt.setGraduatedName(dict.getDictName());
//                                        }
//                                    }
//                                }
//
//                            }
//                        }
//                        model.addAttribute("userResumeExt", userResumeExt);
//                    }
//                }
//            }
            resDoctor.setRotationFlow(rotationFlow);
//            SysUser sysUser = userBiz.readSysUser(doctorFlow);
//            model.addAttribute("sysUser", sysUser);
            //学员的减免方案
            List<SchDoctorDept> doctorDeptList = doctorDeptBiz.searchDoctorDeptForReductionIgnoreStatus(doctorFlow, rotationFlow);
            Map<String, SchDoctorDept> doctorDeptMap = new HashMap<String, SchDoctorDept>();
            if (doctorDeptList != null && !doctorDeptList.isEmpty()) {
                for (SchDoctorDept sdd : doctorDeptList) {
                    String key = sdd.getGroupFlow() + sdd.getStandardDeptId();
                    doctorDeptMap.put(key, sdd);
                }
            }
            Map<String,Integer> groupRowSpan=new HashMap<>();
            Map<String,Integer> deptRowSpan=new HashMap<>();


            //方案中的组
            List<SchRotationGroup> groupList = groupBiz.searchSchRotationGroup(rotationFlow);
            model.addAttribute("groupList", groupList);
            //方案中的科室
            List<SchRotationDept> deptList = rotationDeptBiz.searchSchRotationDept(rotationFlow);

            Map<String, List<SchRotationDept>> rotationDeptMap = new HashMap<String, List<SchRotationDept>>();
            Map<String, Object> afterImgMap = new HashMap<String, Object>();
            Map<String, List<SchArrangeResult>> resultMap = new HashMap<String, List<SchArrangeResult>>();
            Map<String, Float> realMonthMap = new HashMap<String, Float>();
            Map<String,Object> biMap=new HashMap<>();
            List<SchArrangeResult> resultAllList = resultBiz.searchSchArrangeResultByDoctor(doctorFlow);
            for (SchRotationDept dept : deptList) {
                List<SchRotationDept> temp = rotationDeptMap.get(dept.getGroupFlow());
                if (temp == null) {
                    temp = new ArrayList<SchRotationDept>();
                }
                rotationDeptMap.put(dept.getGroupFlow(), temp);
                String reductionKey = dept.getGroupFlow() + dept.getStandardDeptId();
                SchDoctorDept reductionDept = doctorDeptMap.get(reductionKey);
                if (reductionDept != null) {
                    if (!com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y.equals(reductionDept.getRecordStatus())) {
                        continue;
                    }
                    String reductionSchMonth = reductionDept.getSchMonth();
                    dept.setSchMonth(reductionSchMonth);
                }
                Integer count= groupRowSpan.get(dept.getGroupFlow());
                if(count==null)
                    count=0;
                count++;
                groupRowSpan.put(dept.getGroupFlow(),count);
                temp.add(dept);

                //轮转科室
                List<SchArrangeResult> resultList = resultAllList.stream().filter(schArrangeResult -> dept.getGroupFlow().equals(schArrangeResult.getStandardGroupFlow())
                        && dept.getStandardDeptId().equals(schArrangeResult.getStandardDeptId()) ).collect(Collectors.toList());
//                List<SchArrangeResult> resultList = resultBiz.searchResultByGroupFlow(dept.getGroupFlow(),dept.getStandardDeptId(),doctorFlow);
                if(resultList!=null&&resultList.size()>0) {
                    String key = dept.getGroupFlow() + dept.getStandardDeptId();
                    resultMap.put(key, resultList);
                    Integer t=groupRowSpan.get(dept.getGroupFlow());
                    if(t==null)
                        t=0;
                    groupRowSpan.put(dept.getGroupFlow(),resultList.size()+t);
                    deptRowSpan.put(key,resultList.size());
                    for (SchArrangeResult result : resultList) {
                        Float month = realMonthMap.get(key);
                        if (month == null) {
                            month = 0f;
                        }
                        String realMonth = result.getSchMonth();
                        if (StringUtil.isNotBlank(realMonth)) {
                            Float realMonthF = Float.parseFloat(realMonth);
                            month += realMonthF;
                        }
                        realMonthMap.put(key, month);
                    }
                }else{
                    Integer t=groupRowSpan.get(dept.getGroupFlow());
                    if(t==null)
                        t=0;
                    groupRowSpan.put(dept.getGroupFlow(),1+t);
                }

            }

            //完成比例与审核比例
            List<JsresDoctorDeptDetail> details=resultBiz.deptDoctorAllWorkDetail(rotation.getRotationFlow(),doctorFlow,applyYear);
            if(details!=null&&details.size()>0)
            {
                for(JsresDoctorDeptDetail d:details)
                {
                    biMap.put(d.getSchStandardDeptFlow(),d);
                }
            }
            //出科考核表
            List<SchRotationDeptAfterWithBLOBs> afters=afterBiz.getAfterByDoctorFlow(doctorFlow,applyYear);
            if(afters!=null&&afters.size()>0)
            {
                for(SchRotationDeptAfterWithBLOBs a:afters)
                {
                    List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
                    String imageUrls=a.getImageUrls();
                    String thumbUrls=a.getThumbUrls();
                    if(StringUtil.isNotBlank(imageUrls)&&StringUtil.isNotBlank(thumbUrls))
                    {
                        String images[]=imageUrls.split(",");
                        String thumbs[]=thumbUrls.split(",");
                        for(int i=0;i<images.length;i++)
                        {
                            Map<String, Object> recContent = new HashMap<String, Object>();
                            recContent.put("imageUrl",images[i]);
                            if(i<thumbs.length)
                                recContent.put("thumbUrl",thumbs[i]);
                            imagelist.add(recContent);
                        }
                    }
                    afterImgMap.put(a.getSchRotationDeptFlow(), imagelist);
                }
            }
            //平均完成比例与平均审核比例
            Map<String,Object> avgBiMap=resultBiz.doctorDeptAvgWorkDetail(recruitFlow,applyYear);

            model.addAttribute("avgBiMap", avgBiMap);
            model.addAttribute("biMap", biMap);//各科室比例
            model.addAttribute("rotationDeptMap", rotationDeptMap);
            model.addAttribute("afterImgMap", afterImgMap);

            model.addAttribute("resultMap", resultMap);
            model.addAttribute("groupRowSpan", groupRowSpan);
            model.addAttribute("deptRowSpan", deptRowSpan);
            model.addAttribute("realMonthMap", realMonthMap);

        }
        //附件
//        List<PubFile> files = fileBiz.findFileByTypeFlow("asseApplication",recruitFlow);
//        PubFile file = null;
//        if(files != null && files.size() > 0){
//            file = files.get(0);
//            model.addAttribute("file",file);
//        }

        return "jsres/doctor/asseApplication/showAllInfo/AsseFile";
    }


    @RequestMapping(value="/main")
    public String main(Model model) {

        return "jsres/asse/city/main";
    }

    /**
     * 市局待审核查询条件
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/waitAuditMain")
    public String waitAuditMain(Model model,String roleFlag,String tabTag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        model.addAttribute("org",org);
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());

        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {//市局
            sysorg.setOrgCityId(org.getOrgCityId());
        }
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        List<ResTestConfig> resTestConfigs = resTestConfigBiz.findAllEffective();
        model.addAttribute("resTestConfigs", resTestConfigs);
        return "jsres/asse/city/waitAuditMain";
    }

    /**
     * 市局待审核列表
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/waitAuditList")
    public String waitAuditList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,String applyYear,
                                 String orgFlow,String trainingTypeId,String trainingSpeId,String datas[], String sessionNumber,
                                String graduationYear,String qualificationMaterialId,String passFlag, String userName,String idNo,
                                String completeBi, String auditBi,String auditStatusId,String testId,String isNotMatch
                                ){
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String>orgFlowList=new ArrayList<String>();
        if(StringUtil.isBlank(orgFlow)) {
            SysUser sysuser=GlobalContext.getCurrentUser();
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {//市局
                sysorg.setOrgCityId(org.getOrgCityId());
//                List<SysOrg> orgList = orgBiz.searchOrgAndJointOrgList(sysorg);
//                if(orgList!=null&&!orgList.isEmpty()){
//                    for(SysOrg so:orgList){
//                        //判断是否为协同基地
//                        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(so.getOrgFlow());
//                        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
//                            //判断主基地是否为本市
//                            SysOrg s = orgBiz.readSysOrg(tempJoinOrgs.get(0).getOrgFlow());
//                            if(s.getOrgCityId().equals(org.getOrgCityId())){
//                                orgFlowList.add(so.getOrgFlow());
//                            }
//                        }else {
//                            orgFlowList.add(so.getOrgFlow());
//                        }
//                    }
//                }
//                param.put("orgFlowList",orgFlowList);
            }
            param.put("org",sysorg);
        }else{
            //查询协同基地学员
            List<String> jointOrgFlowList = new ArrayList<>();
            List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow);
            if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                for(ResJointOrg jointOrg:resJointOrgList){
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
            jointOrgFlowList.add(orgFlow);
            param.put("orgFlowList",jointOrgFlowList);
        }
//        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("applyYear",applyYear);
        param.put("auditStatusId", auditStatusId);
        param.put("testId", testId);
        param.put("isNotMatch", isNotMatch);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String f = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResTestConfig> testConfigList = resTestConfigBiz.findChargeEffective(DateUtil.getCurrDateTime2());
        if (testConfigList.size() > 0) {
            f = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        model.addAttribute("f",f);
        model.addAttribute("roleFlag", roleFlag);
        return "jsres/asse/city/waitAuditList";
    }

    @RequestMapping(value="/waitAuditListNew")
    public String waitAuditListNew(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,String applyYear, String orgFlow,
                                   String trainingTypeId,String trainingSpeId,String datas[], String sessionNumber, String graduationYear,
                                   String qualificationMaterialId,String passFlag, String userName,String idNo, String completeBi,
                                   String auditBi,String auditStatusId,String testId,String isNotMatch,String tabTag,String joinOrgFlow,String isPostpone, String tempDoctorFlag
    ){
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String>orgFlowList=new ArrayList<String>();
        if(StringUtil.isBlank(orgFlow)) {
            SysUser sysuser=GlobalContext.getCurrentUser();
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {//市局
                sysorg.setOrgCityId(org.getOrgCityId());
            }
            param.put("org",sysorg);
        }else{
            //查询协同基地学员
            List<String> jointOrgFlowList = new ArrayList<>();
            List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow);
            if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                for(ResJointOrg jointOrg:resJointOrgList){
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
            jointOrgFlowList.add(orgFlow);
            param.put("orgFlowList",jointOrgFlowList);
            SysOrg org=orgBiz.readSysOrg(orgFlow);
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {//市局
                sysorg.setOrgCityId(org.getOrgCityId());
            }
            param.put("org",sysorg);
        }
//        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("tabTag",tabTag);
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("applyYear",applyYear);
        param.put("auditStatusId", auditStatusId);
        param.put("testId", testId);
        param.put("isNotMatch", isNotMatch);
        param.put("roleFlag",roleFlag);
        param.put("jointOrgFlow",joinOrgFlow);
        param.put("isPostpone",isPostpone);
        param.put("tempDoctorFlag", tempDoctorFlag);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        Map<String,List<ResRec>> nonComplianceRecordsMap= new HashMap<String, List<ResRec>>();
        list.stream().forEach(e->{
            Map<String, List<ResRec>> doctorFlowMap = graduationApplyBiz.getNonComplianceRecords(e.get("doctorFlow").toString());
            nonComplianceRecordsMap.putAll(doctorFlowMap);

        });
        model.addAttribute("list",list);
        model.addAttribute("nonComplianceRecordsMap",nonComplianceRecordsMap);
        String f = com.pinde.core.common.GlobalConstant.FLAG_N;
        List<ResTestConfig> testConfigList = resTestConfigBiz.findChargeEffective(DateUtil.getCurrDateTime2());
        if (testConfigList.size() > 0) {
            f = com.pinde.core.common.GlobalConstant.FLAG_Y;
        }
        model.addAttribute("f",f);
        model.addAttribute("roleFlag", roleFlag);
        return "jsres/asse/city/waitAuditList";
    }

    @RequestMapping(value="/asseAuditListMain")
    public String asseAuditListMain(Model model) {
        return "jsres/asse/city/asseAuditListMain";
    }

    @RequestMapping(value="/showImages")
    public String showImages(Model model,String deptFlow ,String doctorFlow,String applyYear ) throws DocumentException {
        List<Map<String, Object>> imagelist = new ArrayList<Map<String, Object>>();
        ResRec rec = resRecBiz.queryResRec(deptFlow, doctorFlow, AfterRecTypeEnum.AfterSummary.getId());
        String content = null == rec ? "" : rec.getRecContent();
        if (StringUtil.isNotBlank(content)) {
            Document doc = DocumentHelper.parseText(content);
            Element root = doc.getRootElement();
            List<Element> imageEles = root.elements();
            if (imageEles != null && imageEles.size() > 0) {
                for (Element image : imageEles) {
                    Map<String, Object> recContent = new HashMap<String, Object>();
                    String imageFlow = image.attributeValue("imageFlow");
                    List<Element> elements = image.elements();
                    for (Element attr : elements) {
                        String attrName = attr.getName();
                        String attrValue = attr.getText();
                        recContent.put(attrName, attrValue);
                    }
                    imagelist.add(recContent);
                }
            }
        }
        model.addAttribute("imagelist",imagelist);
        return "jsres/doctor/asseApplication/showImages";
    }
    /**
     * 市局已审核列表
     * @param model
     * @param roleFlag
     * @return
     */
    @RequestMapping(value="/AuditList")
    public String AuditList(Model model,String roleFlag,Integer currentPage ,HttpServletRequest request,String applyYear,
                                 String orgFlow,String trainingTypeId,String trainingSpeId,String datas[],
                                String sessionNumber,String graduationYear,String qualificationMaterialId,String passFlag,
                                String userName,String idNo,String completeBi,String auditBi,String auditStatusId
                                ){
        //查询条件
        Map<String,Object> param=new HashMap<>();
        List<String>orgFlowList=new ArrayList<String>();
        if(StringUtil.isBlank(orgFlow))
        {
            SysUser sysuser=GlobalContext.getCurrentUser();
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {//市局
                sysorg.setOrgCityId(org.getOrgCityId());
            }
            param.put("org",sysorg);
        }
        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("auditStatusId",auditStatusId);
        param.put("applyYear",applyYear);
        PageHelper.startPage(currentPage,getPageSize(request));
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryApplyList(param);
        model.addAttribute("list",list);
        String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
        String startDate=InitConfig.getSysCfg("charge_submit_start_time");
        String endDate=InitConfig.getSysCfg("charge_submit_end_time");
        String f = com.pinde.core.common.GlobalConstant.FLAG_N;
        if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
            if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
            {
                f = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
        }
        model.addAttribute("f",f);
        return "jsres/asse/city/AuditList";
    }
    @RequestMapping(value="/exportList")
    public void exportList(Model model, String roleFlag , HttpServletResponse response, HttpServletRequest request,String applyYear,
                             String orgFlow, String trainingTypeId, String trainingSpeId, String datas[], String sessionNumber, String graduationYear,
                           String qualificationMaterialId, String passFlag, String userName, String idNo, String completeBi, String auditBi,
                           String auditStatusId, String isWaitAudit,String tabTag,String joinOrgFlow,String isPostpone
                                ) throws IOException {
        //查询条件
        Map<String,Object> param=new HashMap<>();
        if(StringUtil.isBlank(orgFlow)) {
            SysUser sysuser=GlobalContext.getCurrentUser();
            SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
            SysOrg sysorg =new  SysOrg();
            sysorg.setOrgProvId(org.getOrgProvId());
            if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {//市局
                sysorg.setOrgCityId(org.getOrgCityId());
            }
            param.put("org",sysorg);
        }else{
            //查询协同基地学员
            List<String> jointOrgFlowList = new ArrayList<>();
            List<ResJointOrg> resJointOrgList=jointOrgBiz.searchResJointByOrgFlow(orgFlow);
            if(resJointOrgList!=null&&!resJointOrgList.isEmpty()){
                for(ResJointOrg jointOrg:resJointOrgList){
                    jointOrgFlowList.add(jointOrg.getJointOrgFlow());
                }
            }
            jointOrgFlowList.add(orgFlow);
            param.put("orgFlowList",jointOrgFlowList);
        }
//        param.put("orgFlow",orgFlow);//培训基地
        List<String>docTypeList=new ArrayList<String>();//人员类型
        if(datas!=null&&datas.length>0){
            for(String s:datas){
                docTypeList.add(s);
            }
        }
        param.put("docTypeList",docTypeList);
        param.put("trainingTypeId",trainingTypeId);
        param.put("trainingSpeId",trainingSpeId);
        param.put("sessionNumber",sessionNumber);
        param.put("graduationYear",graduationYear);
        param.put("qualificationMaterialId",qualificationMaterialId);
        param.put("passFlag",passFlag);
        param.put("userName",userName);
        param.put("idNo",idNo);
        param.put("completeBi",completeBi);
        param.put("auditBi",auditBi);
        param.put("auditStatusId",auditStatusId);
        param.put("applyYear",applyYear);
        param.put("roleFlag",roleFlag);
        param.put("tabTag",tabTag);
        param.put("jointOrgFlow",joinOrgFlow);
        param.put("isPostpone",isPostpone);
        List<Map<String,Object>> list=graduationApplyBiz.chargeQueryListForExport(param);
//        graduationApplyBiz.chargeExportList2(list,response,isWaitAudit,roleFlag);
        graduationApplyBiz.chargeExportList3(list,response,isWaitAudit,roleFlag);
    }
    @RequestMapping(value="/chargeAuditApply")
    public String chargeAuditApply(Model model,String applyFlow){
        List<JsresGraduationApplyLog> logs=graduationApplyBiz.getAuditLogsByApplyFlow(applyFlow);
        model.addAttribute("logs",logs);
        return "jsres/asse/city/chargeAuditApply";
    }
    @RequestMapping(value="/localAuditApply")
    public String localAuditApply(Model model,String applyFlow){
        List<JsresGraduationApplyLog> logs=graduationApplyBiz.getAuditLogsByApplyFlow(applyFlow);
        model.addAttribute("logs",logs);
        return "jsres/asse/hospital/localAuditApply";
    }
    @RequestMapping(value="/chargeSaveAudit")
    @ResponseBody
    public String chargeSaveAudit(Model model,String applyFlow,String auditStatusId,String auditReason){
        JsresGraduationApply apply=graduationApplyBiz.readByFlow(applyFlow);
        if(apply!=null)
        {
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(apply.getLocalAuditStatusId()))
            {
                return "基地已退回该学员考核资格申请信息，无法审核！";
            }
            if(StringUtil.isNotBlank(apply.getGlobalAuditStatusId()))
            {
                return "省厅已审核该学员考核资格申请信息，无法审核！";
            }
            if(StringUtil.isBlank(auditStatusId))
            {
                return "请选择审核结果！";
            }
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId) && StringUtil.isBlank(auditReason))
            {
                return "请输入原因！";
            }
            apply.setCityAuditStatusId(auditStatusId);
            apply.setCityAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
            apply.setCityReason(auditReason);
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId))//市局审核不通过 直接不通过
            {
                apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getId());
                apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getName());
            }else{//市局审核通过 需要省厅审核
                apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
            }
            String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
            String startDate=InitConfig.getSysCfg("charge_submit_start_time");
            String endDate=InitConfig.getSysCfg("charge_submit_end_time");
            String f = com.pinde.core.common.GlobalConstant.FLAG_N;
            if (StringUtil.isNotBlank(startDate) && StringUtil.isNotBlank(endDate)) {
                if(startDate.compareTo(nowTime)<=0&&endDate.compareTo(nowTime)>=0)
                {
                    f = com.pinde.core.common.GlobalConstant.FLAG_Y;
                }
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(f))
            {
                return "当前时间不在审核时间段内，无法审核！";
            }
            JsresGraduationApplyLog log=new JsresGraduationApplyLog();
            log.setApplyFlow(applyFlow);
            log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE);
            log.setAuditRoleName("市局");
            log.setAuditReason(auditReason);
            log.setAuditStatusId(auditStatusId);
            log.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
            log.setAuditTime(nowTime);
            SysUser sysuser=GlobalContext.getCurrentUser();
            log.setUserFlow(sysuser.getUserFlow());
            log.setUserName(sysuser.getUserName());
            graduationApplyBiz.saveChargeAudit(apply,log);
            return "审核成功";
        }
        return  "学员申请信息不存在，请刷新后再试！";
    }
    @RequestMapping(value="/localSaveAudit")
    @ResponseBody
    public String localSaveAudit(Model model,String applyFlow,String auditStatusId,String auditReason,String roleFlag){
        SysUser currentUser = GlobalContext.getCurrentUser();
        List<ResJointOrg> tempJoinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(currentUser.getOrgFlow());
        if(!tempJoinOrgs.isEmpty() && tempJoinOrgs.size()>0){
            roleFlag = "jointOrg";
        }
        JsresGraduationApply apply=graduationApplyBiz.readByFlow(applyFlow);
        if (apply != null) {
            com.pinde.core.model.ResDoctorRecruit recruit = jsResDoctorRecruitBiz.readRecruit(apply.getRecruitFlow());
            if (StringUtil.isBlank(auditStatusId)) {
                return "请选择审核结果！";
            }
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId) && StringUtil.isBlank(auditReason)) {
                return "请输入原因！";
            }
            if(!"Black".equals(auditStatusId)) {
                //  基地或协同基地
                if("jointOrg".equals(roleFlag)){
                    if("AssiGeneral".equals(recruit.getCatSpeId())){
                        apply.setLocalAuditStatusId(auditStatusId);
                        apply.setLocalAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                        apply.setLocalReason(auditReason);
                    }else {
                        apply.setJointLocalAuditStatusId(auditStatusId);
                        apply.setJointLocalAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                        apply.setJointLocalReason(auditReason);
                    }
                } else if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                    apply.setLocalAuditStatusId(auditStatusId);
                    apply.setLocalAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                    apply.setLocalReason(auditReason);
                } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                    apply.setCityAuditStatusId(auditStatusId);
                    apply.setCityAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                    apply.setCityReason(auditReason);
                } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                    apply.setGlobalAuditStatusId(auditStatusId);
                    apply.setGlobalAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
                    apply.setGlobalReason(auditReason);
                }
            }else if("Black".equals(auditStatusId)) {
                apply.setLocalAuditStatusId("");
                apply.setLocalAuditStatusName("");
                apply.setLocalReason("");
                apply.setCityAuditStatusId("");
                apply.setCityAuditStatusName("");
                apply.setCityReason("");
                apply.setGlobalAuditStatusId("Black");
                apply.setGlobalAuditStatusName("省厅驳回");
                apply.setGlobalReason(auditReason);
            }

            List<ResTestConfig> resTestConfigList = resTestConfigBiz.findLocalEffective(DateUtil.getCurrDateTime2());
            if (com.pinde.core.common.enums.JsResAuditStatusEnum.NotPassed.getId().equals(auditStatusId)) {
                if("jointOrg".equals(roleFlag)){
                    apply.setAuditStatusId("JointLocalNotPassed");
                    apply.setAuditStatusName("协同基地审核不通过");
                } else if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                    apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.LocalNotPassed.getId());
                    apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.LocalNotPassed.getName());
                } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                    apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getId());
                    apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.ChargeNotPassed.getName());
                } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                    apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalNotPassed.getId());
                    apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalNotPassed.getName());
                }
            } else if (com.pinde.core.common.enums.JsResAuditStatusEnum.Passed.getId().equals(auditStatusId)) {
                if("jointOrg".equals(roleFlag)){
                    if("AssiGeneral".equals(recruit.getCatSpeId())){//助理全科只需要培训基地审核
                        if (resTestConfigList.size() > 0) {
                            ResTestConfig resTestConfig = resTestConfigList.get(0);
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getChargeAudit())) {
                                apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getId());
                                apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getName());
                            } else {
                                apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                                apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
                            }
                        }
                    }else {
                        //如果协同基地审核通过判断这场考试需不需要主基地审核
                        if (resTestConfigList.size() > 0) {
                            ResTestConfig resTestConfig = resTestConfigList.get(0);
                            if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getLocalAudit())) {
                                apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getId());
                                apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.Auditing.getName());
                            } else {
                                //判断是否需要市局审核
                                if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getChargeAudit())) {
                                    apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getId());
                                    apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getName());
                                } else {
                                    apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                                    apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
                                }
                            }
                        }
                    }
                } else if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                    //如果基地审核通过判断这场考试需不需要市局审核
                    if (resTestConfigList.size() > 0) {
                        ResTestConfig resTestConfig = resTestConfigList.get(0);
                        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(resTestConfig.getChargeAudit())) {
                            apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getId());
                            apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitChargePass.getName());
                        } else {
                            apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                            apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
                        }
                    }
                } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                    apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getId());
                    apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.WaitGlobalPass.getName());
                } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                    apply.setAuditStatusId(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalPassed.getId());
                    apply.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.GlobalPassed.getName());
                }

            }else {
                apply.setAuditStatusId("Black");
                apply.setAuditStatusName("省厅驳回");
            }
            String nowTime=DateUtil.transDateTime(DateUtil.getCurrentTime());
            String f = com.pinde.core.common.GlobalConstant.FLAG_N;
            if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                resTestConfigList = resTestConfigBiz.findChargeEffective(DateUtil.getCurrDateTime2());
            } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                resTestConfigList = resTestConfigBiz.findGlobalEffective(DateUtil.getCurrDateTime2());
            }
            if (resTestConfigList.size() > 0) {
                f = com.pinde.core.common.GlobalConstant.FLAG_Y;
            }
            if (com.pinde.core.common.GlobalConstant.FLAG_N.equals(f))
            {
                return "当前时间不在审核时间段内，无法审核！";
            }
            JsresGraduationApplyLog log = new JsresGraduationApplyLog();
            if("jointOrg".equals(roleFlag)){
                log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL);
                log.setAuditRoleName("协同基地");
            } else if (com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL.equals(roleFlag)) {
                log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL);
                log.setAuditRoleName("基地");
            } else if (com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE.equals(roleFlag)) {
                log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE);
                log.setAuditRoleName("市局");
            } else if (com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL.equals(roleFlag)) {
                log.setAuditRoleId(com.pinde.core.common.GlobalConstant.USER_LIST_GLOBAL);
                log.setAuditRoleName("省厅");
            }
            log.setApplyFlow(applyFlow);
            log.setAuditReason(auditReason);
            log.setAuditStatusId(auditStatusId);
            if("Black".equals(auditStatusId)){
                log.setAuditStatusName("省厅驳回");
            }else {
                log.setAuditStatusName(com.pinde.core.common.enums.JsResAuditStatusEnum.getNameById(auditStatusId));
            }
            log.setAuditTime(nowTime);
            SysUser sysuser=GlobalContext.getCurrentUser();
            log.setUserFlow(sysuser.getUserFlow());
            log.setUserName(sysuser.getUserName());
            graduationApplyBiz.saveChargeAudit(apply,log);
            return "审核成功";
        }
        return  "学员申请信息不存在，请刷新后再试！";
    }
    @RequestMapping(value="/AuditListMain")
    public String AuditListMain(Model model,String roleFlag){
        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        model.addAttribute("org",org);
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());

        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {//市局
            sysorg.setOrgCityId(org.getOrgCityId());
        }
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        return "jsres/asse/city/AuditListMain";
    }

}
