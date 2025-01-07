package com.pinde.sci.ctrl.res;

import com.pinde.core.common.enums.sch.SchStatusEnum;
import com.pinde.core.model.ResScore;
import com.pinde.core.page.PageHelper;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResScoreBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.core.model.GradeDetail4ShiYan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/res/score")
public class ResScoreController extends GeneralController {
	private static Logger logger = LoggerFactory.getLogger(ResScoreController.class);
	@Autowired
	private IResScoreBiz resScoreBiz;
	
	@RequestMapping(value="/saveResScore")
	public @ResponseBody String saveResScore(@RequestBody List<ResScore> resScoreList){
		for (ResScore resScore : resScoreList) {
			resScoreBiz.save(resScore);
		}
        return com.pinde.core.common.GlobalConstant.SAVE_SUCCESSED;
	}
	@RequestMapping(value="/auditing")
	@ResponseBody
	public  String auditing(String scoreFlow){
		if (StringUtil.isNotBlank(scoreFlow)) {
			ResScore resScore=resScoreBiz.searchByScoreFlow(scoreFlow);
			resScore.setAuditStatusId(SchStatusEnum.AuditY.getId());
			resScore.setAuditStatusName(SchStatusEnum.AuditY.getName());
			int i=resScoreBiz.save(resScore);
			if (i>=1) {
                return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED;
			}else{
                return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
			}
		}else{
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL;
		}
	}
	
	@RequestMapping(value="/getScoreByProcess")
	@ResponseBody
	public Integer getScoreByProcess(String processFlow){
		if(!StringUtil.isNotBlank(processFlow)){
			return null;
		}
		
		ResScore score = resScoreBiz.getScoreByProcess(processFlow);
		if(score==null){
			return null;
		}
		
		BigDecimal bd = score.getTheoryScore();
		if(bd==null){
			return null;
		}
		
		return bd.intValue();
	}
	
	@RequestMapping(value="/saveScore")
	@ResponseBody
	public String saveScore(ResScore score){
		if(score==null){
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
		}
		
//		String doctorFlow = score.getDoctorFlow();
//		if(!StringUtil.isNotBlank(doctorFlow)){
//			return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
//		}
		
		String scoreFlow = score.getScoreFlow();
		if(!StringUtil.isNotBlank(scoreFlow)){
            score.setScoreTypeId(com.pinde.core.common.enums.ResScoreTypeEnum.DeptScore.getId());
            score.setScoreTypeName(com.pinde.core.common.enums.ResScoreTypeEnum.DeptScore.getName());
		}
		
		int result = resScoreBiz.save(score);

        if (com.pinde.core.common.GlobalConstant.ZERO_LINE == result) {
            return com.pinde.core.common.GlobalConstant.OPRE_FAIL_FLAG;
		}

        return com.pinde.core.common.GlobalConstant.OPRE_SUCCESSED_FLAG;
	}

	@RequestMapping(value="/osceScoreList")//十堰市太和医院用
	public String osceScoreList(Integer currentPage, String userName, String trainingSpeId, String sessionNumber, String scoreYear,
								String idNo, HttpServletRequest request,Model model){
		Map<String,Object> paramMap = new HashMap<>();
		paramMap.put("userName",userName);
		paramMap.put("trainingSpeId",trainingSpeId);
		paramMap.put("sessionNumber",sessionNumber);
		paramMap.put("scoreYear",scoreYear);
		paramMap.put("idNo",idNo);
		PageHelper.startPage(currentPage,getPageSize(request));
		List<Map<String,Object>> resultMapList = resScoreBiz.searchOsceScoreList(paramMap);
		Map<String,Object> detailMap = new HashMap<>();
		if(resultMapList!=null&&resultMapList.size()>0){
			for(Map<String,Object> result:resultMapList){
				String xmlContent = (String)result.get("EXT_SCORE");
				GradeDetail4ShiYan detail4ShiYan = JaxbUtil.converyToJavaBean(xmlContent,GradeDetail4ShiYan.class);
				detailMap.put((String)result.get("SCORE_FLOW"),detail4ShiYan);
			}
		}
		model.addAttribute("resultMapList",resultMapList);
		model.addAttribute("detailMap",detailMap);
		return "res/hospital/osceScoreList";
	}

	/**
	 * 导入分数页面
	 */
	@RequestMapping("/importScores")
	public String importUsers(){
		return "res/hospital/osceScoresImport";
	}

	@RequestMapping(value="/importOsceScore")//十堰市太和医院用
	@ResponseBody
	public String importGrades(MultipartFile file){
		if(file.getSize() > 0){
			try{
				int result = resScoreBiz.importOsceGrades(file);
                if (com.pinde.core.common.GlobalConstant.ZERO_LINE != result) {
                    return com.pinde.core.common.GlobalConstant.UPLOAD_SUCCESSED + "导入" + result + "条记录！";
				}else{
                    return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
				}
            } catch (RuntimeException e) {
                logger.error("", e);
                return e.getMessage();
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}

	@RequestMapping(value="/changeOsceScore")//十堰市太和医院用
	@ResponseBody
	public void changeOsceScore(String station,String score,String scoreFlow){
		ResScore oldScore = resScoreBiz.searchByScoreFlow(scoreFlow);
		String xmlContent = oldScore.getExtScore();
		GradeDetail4ShiYan oldDetail = JaxbUtil.converyToJavaBean(xmlContent,GradeDetail4ShiYan.class);
		if("station1".equals(station)){
			oldDetail.setStation1(score);
		}else if("station2".equals(station)){
			oldDetail.setStation2(score);
		}else if("station3".equals(station)){
			oldDetail.setStation3(score);
		}else if("station4".equals(station)){
			oldDetail.setStation4(score);
		}else if("station5".equals(station)){
			oldDetail.setStation5(score);
		}else if("station6".equals(station)){
			oldDetail.setStation6(score);
		}else if("station7".equals(station)){
			oldDetail.setStation7(score);
		}else if("station8".equals(station)){
			oldDetail.setStation8(score);
		}else if("station9".equals(station)){
			oldDetail.setStation9(score);
		}else if("result".equals(station)){
			oldDetail.setResult(score);
		}
		Double total = Double.parseDouble(oldDetail.getStation1())+Double.parseDouble(oldDetail.getStation2())+Double.parseDouble(oldDetail.getStation3())+
				Double.parseDouble(oldDetail.getStation4())+Double.parseDouble(oldDetail.getStation5())+Double.parseDouble(oldDetail.getStation6())+
				Double.parseDouble(oldDetail.getStation7())+Double.parseDouble(oldDetail.getStation8())+Double.parseDouble(oldDetail.getStation9());
		oldDetail.setTotal(String.valueOf(total));
		String newXml = JaxbUtil.convertToXml(oldDetail);
		oldScore.setExtScore(newXml);
		resScoreBiz.save(oldScore);
	}
}