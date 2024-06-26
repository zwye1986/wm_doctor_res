package com.pinde.sci.ctrl.pubedu;


import com.pinde.core.page.PageHelper;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pubedu.IStudyCourseBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.StudyCourseDetailInfoMapper;
import com.pinde.sci.enums.pubedu.CourseDetailTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/pubedu/hospital")
public class PubeduHospitalController extends GeneralController{


    @Autowired
    private IStudyCourseBiz studyCourseBiz;

    @Autowired
    private IResDoctorBiz resDoctorBiz;

    @RequestMapping("/home")
    public String home(){
        return "pubedu/hospital/index";
    }

    @RequestMapping("/courseSetting")
    public String courseSetting(Integer currentPage , Model model,HttpServletRequest request){
        PageHelper.startPage(currentPage,getPageSize(request));
        List<StudyCourse> courseList =studyCourseBiz.findStudyCourseListOrderByASC();
        model.addAttribute("courseList", courseList);
        return "pubedu/hospital/courseSetting";
    }

    /**
     * 上传图片
     * @param operType
     * @param model
     * @return
     */
    @RequestMapping("/uploadFile")
    public String uploadFile(String operType,Model model){
        model.addAttribute("operType" , operType);
        return "pubedu/hospital/uploadFile";
    }

    /**
     * 检查上传文件
     * @param operType
     * @param uploadFile
     * @param model
     * @return
     */
    @RequestMapping(value="/checkUploadFile",method={RequestMethod.POST})
    @ResponseBody
    public String checkUploadFile(String operType, MultipartFile uploadFile, Model model){
        Map<String , MultipartFile> fileMap = new LinkedHashMap<String , MultipartFile>();
        fileMap.put(operType, uploadFile);
        String[] fileCheckInfo = testFileMap(fileMap);
        model.addAttribute("operType" , operType);
        if(fileCheckInfo!=null){
//            model.addAttribute("result" , GlobalConstant.FLAG_N);
//            model.addAttribute("fileErrorMsg" , fileCheckInfo);
            return "fileErrorMsg";
        } else {
            model.addAttribute("result" , GlobalConstant.FLAG_Y);
            String resultPath = "";
            if(uploadFile!=null){
                if(!uploadFile.isEmpty()){
                    resultPath = resDoctorBiz.saveImg("",uploadFile, "pubeduImages");
                }
            }
            if(GlobalConstant.FLAG_N.equals(resultPath)){
                resultPath = "";
            }
//            model.addAttribute("filePath" , resultPath);
            return resultPath;
        }
    }

    private String[] testFileMap(Map<String , MultipartFile> fileMap){
        Set<Map.Entry<String, MultipartFile>> fileEntrySet = fileMap.entrySet();
        Iterator<Map.Entry<String, MultipartFile>> iterator = fileEntrySet.iterator();
        while(iterator.hasNext()){
            Map.Entry<String, MultipartFile> entry = iterator.next();
            String name = entry.getKey();
            MultipartFile file = entry.getValue();
            if(file!=null && !file.isEmpty()){
                if(file!=null && !file.isEmpty()){
                    String fileResult = resDoctorBiz.checkFile(file);
                    if(!GlobalConstant.FLAG_Y.equals(fileResult)){
                        return new String[]{name,fileResult};
                    }
                }
            }
        }
        return null;
    }
    /**
     * 新增/修改弹框
     */
    @RequestMapping(value="/courseDetailSetting")
    public String editCourse(String courseFlow,Model model){
        if(StringUtil.isNotBlank(courseFlow)){
            StudyCourse studyCourse = studyCourseBiz.searchCourseByFlow(courseFlow);
            model.addAttribute("studyCourse",studyCourse);
        }
        return "pubedu/hospital/courseDetailSetting";
    }

    /**
     * 保存课程
     * @param
     * @param
     * @return
     */
    @RequestMapping("/saveDetail")
    @ResponseBody
    public String saveDetail(StudyCourse course){
        int count = 0;
        if(StringUtil.isBlank(course.getCourseFlow())){
            course.setCourseFlow(PkUtil.getUUID());
            course.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            course.setCreateTime(DateUtil.getCurrDateTime());
            course.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            course.setModifyTime(DateUtil.getCurrDateTime());
            course.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            count = studyCourseBiz.insert(course);
        }else{
            course.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
            course.setModifyTime(DateUtil.getCurrDateTime());
            course.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
            count = studyCourseBiz.updateStudyCourse(course);
        }

        if(count > 0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 删除课程
     * @param courseFlow
     * @return
     */
    @RequestMapping("/delCourse")
    @ResponseBody
    public String delCourse(String courseFlow){
        int count = studyCourseBiz.deleteByCourseFlow(courseFlow);
        if(count>0){
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 删除图片
     * @param courseFlow
     * @return
     */
    @RequestMapping("/delFile")
    @ResponseBody
    public String delFile(String type,String courseFlow,String courseName)throws IOException{

        int count = studyCourseBiz.updateStudyCourseBySelect(type,courseFlow,java.net.URLDecoder.decode(courseName,"UTF-8"));
        if(count > 0){
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 详情维护
     * @param courseName
     * @param model
     * @return
     * @throws IOException
     */
    @RequestMapping("/detailMaintenance")
    public String detailMaintenance(String courseFlow,String courseName,Model model) throws IOException{

        List<StudyCourseDetail>  courseDetails= studyCourseBiz.findStudyCourseDetailByFlow(courseFlow);

        Map<String,StudyCourseDetail> detailMap = new HashMap<>();
        if(null!=courseDetails){
            for(StudyCourseDetail details:courseDetails){
                if(null!=details.getDetailTypeId() && details.getDetailTypeId().equals(CourseDetailTypeEnum.ChapterHandout.getId())){
                    detailMap.put(CourseDetailTypeEnum.ChapterHandout.getId(),details);
                }
                if(null!=details.getDetailTypeId() && details.getDetailTypeId().equals(CourseDetailTypeEnum.CoursePPT.getId())){
                    detailMap.put(CourseDetailTypeEnum.CoursePPT.getId(),details);
                }
                if(null!=details.getDetailTypeId() && details.getDetailTypeId().equals(CourseDetailTypeEnum.CourseData.getId())){
                    detailMap.put(CourseDetailTypeEnum.CourseData.getId(),details);
                }
            }


        List<StudyCourseDetailInfo> courseDetailInfos =  studyCourseBiz.findStudyCourseDetailInfoByCourseFlow(courseFlow);

        List<StudyCourseDetailInfo> detailInfoCoursePPT = new ArrayList<>();
        List<StudyCourseDetailInfo> detailInfoChapterHandout = new ArrayList<>();
        List<StudyCourseDetailInfo> detailInfoChapterVideo = new ArrayList<>();
        if(null != courseDetailInfos) {
            for (StudyCourseDetailInfo detailInfo : courseDetailInfos) {
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

        model.addAttribute("detailMap",detailMap);
        model.addAttribute("courseDetails",courseDetails);
        model.addAttribute("courseDetailInfos",courseDetailInfos);
        model.addAttribute("courseName",java.net.URLDecoder.decode(courseName,"UTF-8"));
        model.addAttribute("courseFlow",courseFlow);

    }
        return "pubedu/hospital/detailMaintenance";
    }

    /**
     * 添加资料
     * @param detailInfo
     * @param model
     * @return
     */
    @RequestMapping("/saveCourseDetailInfo")
    @ResponseBody
    public String saveCourseDetailInfo(StudyCourseDetailInfo detailInfo){
        int count = studyCourseBiz.insertDetailInfo(detailInfo);
        if(count>0){
            return GlobalConstant.SAVE_SUCCESSED;
        }
        return GlobalConstant.SAVE_FAIL;
    }

    /**
     * 删除study_course_detail_info信息
     * @param recordFlow
     * @return
     */
    @RequestMapping("/deleCourseDetailInfo")
    @ResponseBody
    public String deleCourseDetailInfo(String recordFlow){

        int count = studyCourseBiz.deleteByRecordFlow(recordFlow);
        if(count > 0){
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 删除study_course_detail信息
     * @param
     * @return
     */
    @RequestMapping("/deleCourseDetail")
    @ResponseBody
    public String deleCourseDetail(StudyCourseDetail studyCourseDetail)throws IOException{

        studyCourseDetail.setDetailTypeName(java.net.URLDecoder.decode(studyCourseDetail.getDetailTypeName(),"UTF-8"));
        int count = studyCourseBiz.deleteByDetailFlow(studyCourseDetail);
        if(count > 0){
            return GlobalConstant.DELETE_SUCCESSED;
        }
        return GlobalConstant.DELETE_FAIL;
    }

    /**
     * 编辑课程详情
     * @return
     */
    @RequestMapping("/saveCourseDetail")
    @ResponseBody
    public String saveCourseDetail(String type,String flag,StudyCourseDetail courseDetail,StudyCourseDetailInfo detailInfo){
        if("detail".equals(flag)){
            if(StringUtil.isEmpty(courseDetail.getDetailFlow())){
                if("CoursePPT".equals(type)){
                    courseDetail.setDetailTypeId(CourseDetailTypeEnum.CoursePPT.getId());
                    courseDetail.setDetailTypeName(CourseDetailTypeEnum.CoursePPT.getName());
                }else if("ChapterHandout".equals(type)){
                    courseDetail.setDetailTypeId(CourseDetailTypeEnum.ChapterHandout.getId());
                    courseDetail.setDetailTypeName(CourseDetailTypeEnum.ChapterHandout.getName());
                }
                int count = studyCourseBiz.insertStudyDetail(courseDetail);
                if(count>0){
                    return GlobalConstant.SAVE_SUCCESSED;
                }
            }else {
                int count = studyCourseBiz.updateStudyCourseDetail(courseDetail);
                if(count>0){
                    return GlobalConstant.SAVE_SUCCESSED;
                }
            }

        }
        if("detailInfo".equals(flag)){
            int count = studyCourseBiz.updateStudyCourseDetailInfo(detailInfo);
            if(count>0){
                return GlobalConstant.SAVE_SUCCESSED;
            }
        }
            return GlobalConstant.SAVE_FAIL;
        }
    }

