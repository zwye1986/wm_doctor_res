package com.pinde.sci.ctrl.pubedu;


import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pubedu.IStudyCourseBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.InitConfig;
import com.pinde.core.common.enums.pubedu.CourseDetailTypeEnum;
import com.pinde.core.model.StudyCourse;
import com.pinde.core.model.StudyCourseDetail;
import com.pinde.core.model.StudyCourseDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2017-05-02.
 */
@Controller
@RequestMapping("/pubedu/doctor")
public class PubeduDoctorController extends GeneralController {
	@Autowired
	private IStudyCourseBiz courseBiz;
	@RequestMapping("/home")
	public String home(Integer currentPage , Model model, HttpServletRequest request){
		List<StudyCourse> studyCourseList = courseBiz.findStudyCourseList(new StudyCourse());
		model.addAttribute("studyCourseList",studyCourseList);
		return "pubedu/doctor/index";
	}
	@RequestMapping("/courseDetail")
	public String courseDetail(String courseFlow,Model model){
		StudyCourse course = courseBiz.searchCourseByFlow(courseFlow);
		List<StudyCourseDetail> detailList = courseBiz.findStudyCourseDetailByFlow(courseFlow);
		Map<String,StudyCourseDetail> detailMap = new HashMap<>();
		for(StudyCourseDetail detail: detailList ){
			if(CourseDetailTypeEnum.CoursePPT.getId().equals(detail.getDetailTypeId())){
				detailMap.put(CourseDetailTypeEnum.CoursePPT.getId(),detail);
				continue;
			}
			if(CourseDetailTypeEnum.ChapterHandout.getId().equals(detail.getDetailTypeId())){
				detailMap.put(CourseDetailTypeEnum.ChapterHandout.getId(),detail);
				continue;
			}
			if(CourseDetailTypeEnum.CourseData.getId().equals(detail.getDetailTypeId())){
				detailMap.put(CourseDetailTypeEnum.CourseData.getId(),detail);
				continue;
			}
			if(CourseDetailTypeEnum.CourseTest.getId().equals(detail.getDetailTypeId())){
				detailMap.put(CourseDetailTypeEnum.CourseTest.getId(),detail);
				continue;
			}
		}
		model.addAttribute("course",course);
		model.addAttribute("detailMap",detailMap);
		return "pubedu/doctor/courseDetail";
	}
	@RequestMapping("/initCoursPPT")
	public String initCoursePPt(String detailFlow,Model model){
		StudyCourseDetail courseDetail = courseBiz.findCourseDetailByRecordFlow(detailFlow);
		model.addAttribute("courseDetail",courseDetail);
		return "pubedu/doctor/coursePPT";
	}
	@RequestMapping("/initCourseData")
	public String initCourseData(String courseFlow,Model model){
		List<StudyCourseDetailInfo> detailInfoList = courseBiz.findDetailInfoByFlow(courseFlow);
		List<StudyCourseDetailInfo> detailInfoCoursePPT = new ArrayList<>();
		List<StudyCourseDetailInfo> detailInfoChapterHandout = new ArrayList<>();
		List<StudyCourseDetailInfo> detailInfoChapterVideo = new ArrayList<>();
		if(null != detailInfoList) {
			for (StudyCourseDetailInfo detailInfo : detailInfoList) {
				if (CourseDetailTypeEnum.DataPPT.getId().equals(detailInfo.getDetailTypeId())) {
					detailInfoCoursePPT.add(detailInfo);
				}
				if (CourseDetailTypeEnum.DataHandout.getId().equals(detailInfo.getDetailTypeId())) {
					detailInfoChapterHandout.add(detailInfo);
				}
				if (CourseDetailTypeEnum.ChapterVideo.getId().equals(detailInfo.getDetailTypeId())) {
					detailInfoChapterVideo.add(detailInfo);
				}
			}
		}
		model.addAttribute("detailInfoCoursePPT",detailInfoCoursePPT);
		model.addAttribute("detailInfoChapterHandout",detailInfoChapterHandout);
		model.addAttribute("detailInfoChapterVideo",detailInfoChapterVideo);
		return "pubedu/doctor/courseData";
	}
	@RequestMapping("/showDetailInfo")
	public String showVideo(String detailInfoFlow,String typeId,Model model){
		StudyCourseDetailInfo detailInfo = courseBiz.findDetailInfoByRecordFlow(detailInfoFlow);
		if(CourseDetailTypeEnum.ChapterVideo.getId().equals(typeId)) {
			if (null != detailInfo) {
				if (detailInfo.getDetailUrl().length() > 5) {
					if ("http".equals(detailInfo.getDetailUrl().substring(0, 4))) {
						String newUrl = "<embed src='" + detailInfo.getDetailUrl() + "' allowFullScreen='true' quality='high' width='100%' height='100%' align='middle' allowScriptAccess='always' type='application/x-shockwave-flash'></embed>";
						detailInfo.setDetailUrl(newUrl);
					}
				}
			}
			model.addAttribute("detailInfo", detailInfo);
			return "pubedu/doctor/courseVideo";
		}
		if(CourseDetailTypeEnum.DataPPT.getId().equals(typeId)){
			model.addAttribute("courseDetail", detailInfo);
			return "pubedu/doctor/coursePPT";
		}
		return "";
	}
	@RequestMapping("/handoutIsExist")
	@ResponseBody
	public String handoutIsExist(String recordFlow,String typeId){
		if(CourseDetailTypeEnum.ChapterHandout.getId().equals(typeId)){
			StudyCourseDetail courseDetail = courseBiz.findCourseDetailByRecordFlow(recordFlow);
			if(null != courseDetail){
				String path = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + courseDetail.getDetailUrl();
				File file = new File(path);
				if(file.isFile()) {//该路径文件存在
                    return com.pinde.core.common.GlobalConstant.FLAG_Y;
				}
			}
		}
		if(CourseDetailTypeEnum.DataHandout.getId().equals(typeId)){
			StudyCourseDetailInfo detailInfo = courseBiz.findDetailInfoByRecordFlow(recordFlow);
			if(null != detailInfo){
				String path = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + detailInfo.getDetailUrl();
				File file = new File(path);
				if(file.isFile()) {
                    return com.pinde.core.common.GlobalConstant.FLAG_Y;
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.FLAG_N;
	}
}
