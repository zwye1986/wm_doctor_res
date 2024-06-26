package com.pinde.sci.biz.njmuedu.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.njmuedu.INjmuEduCourseBiz;
import com.pinde.sci.biz.njmuedu.INjmuEduExamScoreBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.EduExamScoreMapper;
import com.pinde.sci.dao.base.EduStudentCourseMapper;
import com.pinde.sci.dao.njmuedu.NjmuEduExamScoreExtMapper;
import com.pinde.sci.enums.njmuedu.NjmuEduStudyStatusEnum;
import com.pinde.sci.enums.sys.DictTypeEnum;
import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.njmuedu.EduExamScoreExt;
import com.pinde.sci.model.njmuedu.EduUserCourseExt;
import com.pinde.sci.model.njmuedu.EduUserExt;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NjmuEduExamScoreBizImpl implements INjmuEduExamScoreBiz {
    @Autowired
    private EduExamScoreMapper eduExamScoreMapper;
    @Autowired
    private NjmuEduExamScoreExtMapper eduExamScoreExtMapper;
    @Autowired
    private EduStudentCourseMapper eduStudentCourseMapper;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDictBiz dictBiz;

    @Override
    public int saveEduExamScore(EduExamScore eduExamScore) {
        if(StringUtil.isBlank(eduExamScore.getDetaliFlow())){
            eduExamScore.setDetaliFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(eduExamScore,true);
            return eduExamScoreMapper.insert(eduExamScore);
        }else{
            GeneralMethod.setRecordInfo(eduExamScore,false);
            return eduExamScoreMapper.updateByPrimaryKeySelective(eduExamScore);
        }
    }

    @Override
    public int saveEduExamScore(SysUser user, List<EduExamScore> eduExamScoreList) {
        SysUser user1 = userBiz.findByIdNo(user.getIdNo()) ;
        if(eduExamScoreList.size() >0 && null != user1 ){
            for (EduExamScore examScore : eduExamScoreList) {
                List<EduExamScore> examScoreList = findEduExamScore(user1.getUserFlow(),examScore.getCourseFlow());
                if(null != examScoreList && examScoreList.size() > 0){
                   EduExamScore oldExamScore = examScoreList.get(0);
                   examScore.setDetaliFlow(oldExamScore.getDetaliFlow());
                }
                  examScore.setUserFlow(user1.getUserFlow());
                  saveEduExamScore(examScore);
                 //   examScore.setUserFlow(PkUtil.getUUID());
                  //  saveEduExamScore(examScore);
            }
            return  1;
        }
        return 0;
    }
    private Workbook createCommonWorkbook(InputStream inS) throws IOException, InvalidFormatException {
        // 首先判断流是否支持mark和reset方法，最后两个if分支中的方法才能支持
        if (!inS.markSupported()) {
            // 还原流信息
            inS = new PushbackInputStream(inS);
        }
        // EXCEL2003使用的是微软的文件系统
        if (POIFSFileSystem.hasPOIFSHeader(inS)) {
            return new HSSFWorkbook(inS);
        }
        // EXCEL2007使用的是OOM文件格式
        if (POIXMLDocument.hasOOXMLHeader(inS)) {
            // 可以直接传流参数，但是推荐使用OPCPackage容器打开
            return new XSSFWorkbook(OPCPackage.open(inS));
        }
        throw new IOException("不能解析的excel版本");
    }
    private static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.format("%.1f",d);
    }
    @Override
    public int importUserFromExcel(MultipartFile file) {
        InputStream is = null;
        try {
            is =  file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int)file.getSize() ));
            return parseExcel4(wb);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }finally{
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
    @Autowired
    private INjmuEduCourseBiz courseBiz;
    private int parseExcel4(Workbook wb){
        int count = 0;
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum>0){
            List<String> colnames = new ArrayList<String>();
            Sheet sheet;
            try{
                sheet = wb.getSheetAt(0);
            }catch(Exception e){
                sheet = wb.getSheetAt(0);
            }
            int row_num = sheet.getLastRowNum();
            //获取表头
            Row titleR =  sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            String title = "";
            for(int i = 0 ; i <cell_num; i++){
                title = titleR.getCell(i).getStringCellValue();
                colnames.add(title);
            }

            List<SysOrg> orgList = orgBiz.queryAllSysOrg(null);
            Map<String,SysOrg> orgMap=new HashMap<>();
            if(orgList!=null)
            {
                for(SysOrg org:orgList)
                {
                    orgMap.put(org.getOrgName(),org);
                }
            }
            List<EduCourse> courseList = courseBiz.searchCourseList(null);
            Map<String,EduCourse> courseMap = new HashMap<>();
            if(courseList != null){
                for(EduCourse course:courseList){
                    courseMap.put(course.getCourseName(),course);
                }
            }
            for(int i = 1;i <= row_num; i++){
                Row r =  sheet.getRow(i);
                List<EduExamScore> examScoreList = new ArrayList<>();
                SysUser sysUser = new SysUser();
                String userName;
                String idNo;
                String orgFlow;
                String orgName;
                String majorId;
                String majorName;
                String courseFlow;
                String courseName;
                String score;
                EduUser user = new EduUser();
                for (int j = 0; j < colnames.size(); j++) {
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(cell!=null && StringUtil.isNotBlank(cell.toString().trim())){
                            value = cell.toString().trim();
                    }
                    /* 姓名 证件号 基地 专业 课程名称  平时成绩 总成绩*/
                    if("姓名".equals(colnames.get(j))){
                        userName = value;
                        sysUser.setUserName(userName);
                    }else  if("证件号".equals(colnames.get(j))){
                        idNo = value;
                        sysUser.setIdNo(idNo);
                    }else  if("基地".equals(colnames.get(j))){
                        orgName = value;
                        if(StringUtil.isNotBlank(orgName)){
                            sysUser.setOrgName(orgName);
                            SysOrg org=orgMap.get(orgName);
                            if(org!=null)
                            {
                                sysUser.setOrgFlow(org.getOrgFlow());
                            }
                            if(StringUtil.isBlank(sysUser.getOrgFlow()))
                            {
                                throw new RuntimeException("导入失败！第"+ (count+2) +"行，基地输入错误！");
                            }
                        }

                    }else
                    if("培训专业".equals(colnames.get(j))){
                        majorName = value;
                        user.setMajorName(majorName);
                    }else if(!"".equals(colnames.get(j))){
                        courseName = colnames.get(j).trim();
                        List<EduCourse> courseList1 = courseBiz.findByCourseName(courseName);
                        if(courseList1.size() == 0){
                            throw new RuntimeException("导入失败！第"+(j+1)+"列课程名称填写错误");
                        }
                        score = value;
                        EduExamScore examScore = new EduExamScore();
                        if(StringUtil.isNotBlank(score)){

                            boolean f = score.matches("^(([0-9]{1,4}+)([.]([0-9]{0,1}+))?)$");
                            if(!f) {
                               throw new RuntimeException("导入失败！第"+(count+2)+"行【"+courseName+"】,成绩输入错误");
                           }
                           if (Double.valueOf(score) > 100 || Double.valueOf(score) <0) {
                               throw new RuntimeException("导入失败！第"+(count+2)+"行【"+courseName+"】,成绩不得大于100或小于0");
                           }
                            examScore.setScore(score);
                            if(Double.parseDouble(score) >= 60){
                                examScore.setIsQualified(GlobalConstant.FLAG_Y);
                            }else{
                                examScore.setIsQualified(GlobalConstant.FLAG_N);
                            }
                        }
                        EduCourse course = courseMap.get(courseName);
                        if(course!=null){
                            examScore.setCourseFlow(course.getCourseFlow());
                            examScore.setCourseName(course.getCourseName());
                        }
                        examScoreList.add(examScore);
                    }
                }
                if(StringUtil.isBlank(sysUser.getUserName())){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，姓名不能为空！");
                }
                if(StringUtil.isBlank(sysUser.getIdNo())){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，证件号不能为空！");
                }
//                if(StringUtil.isBlank(sysUser.getOrgName())){
//                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，基地不能为空！");
//                }
                SysDict sysDict = new SysDict();
                sysDict.setDictTypeId(DictTypeEnum.NjmuCourseMajor.getId());
                sysDict.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                List<SysDict> sysDictList = dictBiz.searchDictList(sysDict);
                boolean speFlag = false;
                if(sysDictList!=null&&sysDictList.size()>0){
                    for(SysDict dict:sysDictList){
                            if(user.getMajorName().equals(dict.getDictName())){
                                user.setMajorId(dict.getDictId());
                                speFlag = true;
                                break;
                            }
                    }
                }
                if(!speFlag && ""!= user.getMajorName()){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，专业有误，请检查名称！");
                }
                //执行保存
                int result = saveEduExamScore(sysUser,examScoreList);
                if(result == 0){
                    throw new RuntimeException("导入失败！第"+ (count+2) +"行，导入失败！");
                }
                count ++;
            }
        }
        return count;
    }

    @Override
    public Map<String, Object> findEduExamScoreByList(List<EduUserExt> eduUserExtList, String courseFlow) {
        Map<String, Object> scoreMap = new HashMap<>();
        if(eduUserExtList.size() >0 && eduUserExtList != null){
            for (EduUserExt eduUserExt : eduUserExtList) {
              List<EduExamScore> examScoreList = this.findEduExamScore(eduUserExt.getUserFlow(),courseFlow);
              if(examScoreList.size() >0){
                  scoreMap.put(eduUserExt.getUserFlow(),examScoreList.get(0).getScore());
              }
            }
        }
        return scoreMap;
    }

    @Override
    public List<EduExamScore> findEduExamScore(String userFlow, String courseFlow) {
        EduExamScoreExample examScoreExample = new EduExamScoreExample();
        EduExamScoreExample.Criteria criteria = examScoreExample.createCriteria();
        criteria.andUserFlowEqualTo(userFlow).andCourseFlowEqualTo(courseFlow)
                .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return eduExamScoreMapper.selectByExample(examScoreExample);

    }

    @Override
    public List<EduExamScoreExt> searchEduExamScoreList(Map<String, Object> paramMap) {
        return eduExamScoreExtMapper.searchEduExamScoreList(paramMap);
    }

    @Override
    public List<EduUserCourseExt> searchEduUserList(Map<String, Object> paramMap) {
        return eduExamScoreExtMapper.searchEduUserList(paramMap);
    }

    @Override
    public Map<String, Map<String, Object>> searchStuScore(List<EduUserExt> eduUserExtList) {
        Map<String,Map<String,Object>> resultMap = new HashMap<>();
        EduCourse course = new EduCourse();
        List<EduCourse> courseList = this.courseBiz.searchCourseList(course);
        if(courseList.size() >0 && !courseList.isEmpty()){
            for (EduCourse eduCourse : courseList) {
                resultMap.put(eduCourse.getCourseFlow(),this.findEduExamScoreByList(eduUserExtList,eduCourse.getCourseFlow()));
            }
        }
        return resultMap;
    }

    @Override
    public Map<String, Map<String, Object>> searchStuCourse(List<EduUserExt> eduUserExtList) {
        Map<String,Map<String,Object>> resultMap = new HashMap<>();
        EduCourse course = new EduCourse();
        List<EduCourse> courseList = this.courseBiz.searchCourseList(course);
        if(courseList.size() >0 && !courseList.isEmpty()){
            for (EduCourse eduCourse : courseList) {
                resultMap.put(eduCourse.getCourseFlow(),findEduStudentCourseByList(eduUserExtList,eduCourse.getCourseFlow()));
            }
        }
        return resultMap;
    }



    @Override
    public Map<String, Object> searchByStudentList(List<EduUserExt> eduUserExtList,String courseFlow) {
        Map<String,Object> performanceMap = new HashMap<>();
        if(eduUserExtList.size() >0 && !eduUserExtList.isEmpty()){
            for (EduUserExt eduUserExt : eduUserExtList) {
               int isFinish = 0 ;
                    List<EduStudentCourse> eduStudentCourseList = this.findByUserFlowAndCourseFlow(eduUserExt.getUserFlow(),courseFlow);
                    if(eduStudentCourseList.size() >0 ){
                        EduStudentCourse eduStudentCourse = eduStudentCourseList.get(0);
                        if(NjmuEduStudyStatusEnum.Finish.getId().equals(eduStudentCourse.getStudyStatusId())){
                            isFinish =20;
                            performanceMap.put(eduUserExt.getUserFlow(),isFinish);
                        }
                    }
                performanceMap.put(eduUserExt.getUserFlow(),isFinish);
            }
        }
        return performanceMap;
    }

    /**
     * 查询每门课,每个学生的学习情况
     * @param eduUserExtList
     * @return
     */
    @Override
    public Map<String, Map<String, Object>> searchFinish(List<EduUserExt> eduUserExtList) {
        Map<String,Map<String,Object>> finishMap = new HashMap<>();
        EduCourse course = new EduCourse();
        List<EduCourse> courseList = this.courseBiz.searchCourseList(course);
        if(courseList.size() >0 && !courseList.isEmpty()){
            for (EduCourse eduCourse : courseList) {
                finishMap.put(eduCourse.getCourseFlow(),searchByStudentList(eduUserExtList,eduCourse.getCourseFlow()));
            }
        }
        return finishMap;
    }

    @Override
    public Map<String, Object> findEduStudentCourseByList(List<EduUserExt> eduUserExtList, String courseFlow) {
        Map<String,Object> courseMap = new HashMap<>();
        if(eduUserExtList.size()>0 && !eduUserExtList.isEmpty()){
            for (EduUserExt eduUserExt : eduUserExtList) {
              List<EduStudentCourse> eduStudentCourseList = this.findByUserFlowAndCourseFlow(eduUserExt.getUserFlow(),courseFlow);
              if(eduStudentCourseList.size() >0){
                  courseMap.put(eduUserExt.getUserFlow(),eduStudentCourseList.get(0).getStudyStatusName());
              }
            }
        }
        return courseMap;
    }

    public List<EduStudentCourse> findByUserFlowAndCourseFlow(String userFlow,String courseFlow){
        EduStudentCourseExample example = new EduStudentCourseExample();
        EduStudentCourseExample.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotBlank(userFlow)){
            criteria.andUserFlowEqualTo(userFlow);
        }
        if(StringUtil.isNotBlank(courseFlow)){
            criteria.andCourseFlowEqualTo(courseFlow);
        }
        criteria.andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        return eduStudentCourseMapper.selectByExample(example);
    }

    @Override
    public int searchByIsqualified(String userFlow) {
        EduExamScoreExample examScoreExample = new EduExamScoreExample();
        EduExamScoreExample.Criteria criteria = examScoreExample.createCriteria();
        criteria.andUserFlowEqualTo(userFlow).andIsQualifiedEqualTo(GlobalConstant.FLAG_Y).
                andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        return eduExamScoreMapper.countByExample(examScoreExample);
    }

    @Override
    public int searchByStudyStatusId(String userFlow) {
        EduStudentCourseExample eduStudentCourseExample = new EduStudentCourseExample();
        EduStudentCourseExample.Criteria criteria = eduStudentCourseExample.createCriteria();
        criteria.andUserFlowEqualTo(userFlow).andStudyStatusIdEqualTo(NjmuEduStudyStatusEnum.Finish.getId())
                .andRecordStatusEqualTo(GlobalConstant.FLAG_Y);
        return eduStudentCourseMapper.countByExample(eduStudentCourseExample);
    }
}
