package com.pinde.sci.biz.jsres.impl;

import com.alibaba.fastjson.JSON;
import com.pinde.core.common.GeneralEnum;
import com.pinde.core.common.PasswordHelper;
import com.pinde.core.common.enums.osca.DoctorScoreEnum;
import com.pinde.core.common.enums.osca.ScanDocStatusEnum;
import com.pinde.core.common.enums.osca.ScoreStatusEnum;
import com.pinde.core.common.enums.pub.UserNationEnum;
import com.pinde.core.common.enums.pub.UserSexEnum;
import com.pinde.core.common.enums.pub.UserStatusEnum;
import com.pinde.core.common.enums.sys.CertificateTypeEnum;
import com.pinde.core.common.sci.dao.ResSchProcessExpressMapper;
import com.pinde.core.common.sci.dao.SysUserMapper;
import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.ISchRotationDeptAfterBiz;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResDoctorRecruitBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.biz.sys.IUserRoleBiz;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.common.util.IExcelUtil;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.jsres.TempMapper;
import com.pinde.core.model.OscaDoctorAssessment;
import com.pinde.core.model.OscaDoctorAssessmentExample;
import com.pinde.core.model.OscaSkillDocScore;
import com.pinde.core.model.OscaSkillRoomDoc;
import com.pinde.core.model.OscaSkillRoomDocExample;
import com.pinde.core.model.OscaSkillsAssessment;
import com.pinde.core.model.OscaSubjectMain;
import com.pinde.core.model.OscaSubjectPartScore;
import com.pinde.core.model.OscaSubjectPartScoreExample;
import com.pinde.core.model.OscaSubjectStation;
import com.pinde.core.model.OscaSubjectStationExample;
import com.pinde.core.model.OscaSubjectStationScore;
import com.pinde.core.model.OscaSubjectStationScoreExample;
import com.pinde.core.model.OscaTeaScanDoc;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.mo.ResDoctorSchProcess;
import com.pinde.sci.model.mo.SchRotationDeptAfterExample;
import com.pinde.sci.model.mo.SchRotationDeptAfterWithBLOBs;
import com.pinde.sci.model.mo.SysUserRole;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class SchRotationDeptAfterBizImpl implements ISchRotationDeptAfterBiz {

    @Autowired
    private SchRotationDeptAfterMapper afterMapper;
    @Autowired
    private ResSchProcessExpressMapper expressMapper;
    @Autowired
    private TempMapper tempMapper;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private IUserRoleBiz userRoleBiz;
    @Autowired
    private OscaSkillDocScoreMapper scoreMapper;
    @Autowired
    private OscaSkillRoomDocMapper roomDocMapper;
    @Autowired
    private OscaDoctorAssessmentMapper doctorAssessmentMapper;
    @Autowired
    private OscaSkillsAssessmentMapper skillsAssessmentMapper;
    @Autowired
    private  OscaSubjectMainMapper oscaSubjectMainMapper;
    @Autowired
    private OscaSubjectPartScoreMapper partScoreMapper;
    @Autowired
    private OscaSubjectStationScoreMapper stationScoreMapper;
    @Autowired
    private  OscaSubjectStationMapper stationMapper;
    @Autowired
    private  OscaTeaScanDocMapper scanDocMapper;
    @Autowired
    private IOrgBiz orgBiz;

    private static Logger logger = LoggerFactory.getLogger(SchRotationDeptAfterBizImpl.class);
    @Override
    public List<SchRotationDeptAfterWithBLOBs> getAll() {
        SchRotationDeptAfterExample example=new SchRotationDeptAfterExample();
        example.createCriteria();
        return afterMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public void updateNAllInfo(String applyYear) {

        SchRotationDeptAfterExample example=new SchRotationDeptAfterExample();
        example.createCriteria().andApplyYearEqualTo(applyYear);
        SchRotationDeptAfterWithBLOBs record=new SchRotationDeptAfterWithBLOBs();
        record.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
        afterMapper.updateByExampleWithBLOBs(record,example);
    }

    @Override
    public int edit(SchRotationDeptAfterWithBLOBs after) {
        if(StringUtil.isNotBlank(after.getRecordFlow()))
        {
            return afterMapper.updateByPrimaryKeyWithBLOBs(after);
        }else{
            after.setRecordFlow(PkUtil.getUUID());
            return afterMapper.insertSelective(after);
        }
    }

    @Override
    public List<SchRotationDeptAfterWithBLOBs> getAfterByDoctorFlow(String doctorFlow, String applyYear) {
        if(StringUtil.isNotBlank(doctorFlow))
        {
            SchRotationDeptAfterExample example=new SchRotationDeptAfterExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andDoctorFlowEqualTo(doctorFlow).andApplyYearEqualTo(applyYear);
            return afterMapper.selectByExampleWithBLOBs(example);
        }
        return null;
    }

    @Override
    public void updateRecruitAsseInfo(String applyYear) {
        tempMapper.updateRecruitAsseInfo(applyYear);
    }

    @Override
    public int deleteUriAuditInfo() {
        return tempMapper.deleteUriAuditInfo();
    }

    @Override
    public int updateUriAuditInfo() {
        tempMapper.insertAuditInfo();
        return 1;
    }

    @Override
    public void updateDeptDetail(String applyYear) {
        tempMapper.deleteDeptDetail(applyYear);
        tempMapper.insertDeptDetail(applyYear);

    }
    @Override
    public void updateDeptTemp(String applyYear) {
        tempMapper.deleteDeptTemp();
        tempMapper.updateDeptTemp();

    }
    @Override
    public void updateDeptAvgTemp(String applyYear) {
        tempMapper.deleteDeptAvgTemp();
        tempMapper.updateDeptAvgTemp();

    }
    @Override
    public int updateRecruitAvgTemp(String applyYear) {
      return  tempMapper.updateRecruitAvgTemp(applyYear);
    }

    @Override
    public ExcelUtile  chouquPhoto(MultipartFile file, final String dirUrl) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "idNo"
            };
            return  ExcelUtile.importDataExcel(HashMap.class, 0, wb, keys, new IExcelUtil<HashMap>() {

                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String,Object>> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";
                    String baseDirUrl= InitConfig.getSysCfg("upload_base_dir");

                    for(int i=0;i<datas.size();i++)
                    {
                        Map<String, Object> data=datas.get(i);
                        String idNo=((String) data.get("idNo")).toUpperCase();
                        String userImage= (String) data.get("imageUrl");

                        System.out.println(idNo+ ":"+ userImage);
                        if(StringUtil.isNotBlank(userImage)) {
                            File FiledirUrl = new File(dirUrl);
                            if(!FiledirUrl.exists())
                            {
                                FiledirUrl.mkdirs();
                            }

                            String newDirFile = dirUrl + File.separator + idNo + userImage.substring(userImage.lastIndexOf("."));
                            String oldDirFile = baseDirUrl + File.separator + userImage;
                            File oldFile = new File(oldDirFile);
                            if (!oldFile.isDirectory() && oldFile.exists()) {
                                File newFile = new File(newDirFile);
                                try {
                                    int bytesum = 0;
                                    int byteread = 0;
                                    InputStream inStream = new FileInputStream(oldFile); //读入原文件
                                    FileOutputStream fs = new FileOutputStream(newFile);
                                    byte[] buffer = new byte[1444];
                                    while ( (byteread = inStream.read(buffer)) != -1) {
                                        bytesum += byteread; //字节数 文件大小
                                        fs.write(buffer, 0, byteread);
                                    }
                                    inStream.close();
                                    fs.close();
                                }catch (Exception e) {
                                    System.out.println(idNo + "复制单个文件操作出错");
                                    logger.error("", e);
                                }
                                count++;
                            }
                        }else{

                            System.out.println(idNo + "未上传头像");
                        }
                    }
                    eu.put("code",code);
                    eu.put("count",count);
                }

                @Override
                public String checkExcelData(HashMap data, ExcelUtile eu) {
                    SysUser sysUser=new SysUser();
                    int rowNum= (Integer) eu.get(ExcelUtile.CURRENT_ROW);
                    for (Object key1 : data.keySet()) {
                        String key=(String) key1;
                        String value=(String) data.get(key);
                        if("idNo".equals(key))
                        {
                            if(StringUtil.isNotBlank(value)) {
                                sysUser.setIdNo(value);
                            }
                        }
                    }
                    if(StringUtil.isNotBlank(sysUser.getIdNo())) {
                        sysUser = userBiz.findByIdNo(sysUser.getIdNo());
                        if (sysUser != null) {
                            data.put("imageUrl",sysUser.getUserHeadImg());
                        }
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }



    //放大图片
    private static BufferedImage magnifyZoom(byte[] imageData) {
        //使用源图像文件名创建ImageIcon对象。
        ImageIcon imgIcon = new ImageIcon(imageData);
        //得到Image对象。
        Image img = imgIcon.getImage();
        int height=(int) (imgIcon.getIconHeight()*1.1);
        int width=(int) (imgIcon.getIconWidth()*1.1);
        Image new_img=img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return zoom(new_img,height,width);
    }

    private static final int WIDTH = 100; //缩略图宽度
    static final int HEIGHT = 100;//缩略图高度
    public static BufferedImage zoom(String srcFileName) {
        //使用源图像文件名创建ImageIcon对象。
        ImageIcon imgIcon = new ImageIcon(srcFileName);
        //得到Image对象。
        Image img = imgIcon.getImage();

        return zoom(img);
    }
    public static BufferedImage zoom(byte[] imageData) {
        //使用源图像文件名创建ImageIcon对象。
        ImageIcon imgIcon = new ImageIcon(imageData);
        //得到Image对象。
        Image img = imgIcon.getImage();

        return zoom(img);
    }
    //缩小图片
    public static BufferedImage shrinkZoom(byte[] imageData) {
        //使用源图像文件名创建ImageIcon对象。
        ImageIcon imgIcon = new ImageIcon(imageData);
        //得到Image对象。
        Image img = imgIcon.getImage();
        int height= (int) (imgIcon.getIconHeight()*0.9);
        int width= (int) (imgIcon.getIconWidth()*0.9);
        Image new_img=img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return zoom(new_img,height,width);
    }

    private static BufferedImage zoom(Image srcImage, int height, int width) {
        //构造一个预定义的图像类型的BufferedImage对象。
        BufferedImage buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//        buffImg.flush();
        //创建Graphics2D对象，用于在BufferedImage对象上绘图。
        Graphics2D g = buffImg.createGraphics();

        //设置图形上下文的当前颜色为白色。
        g.setColor(Color.WHITE);
        //用图形上下文的当前颜色填充指定的矩形区域。
        g.fillRect(0, 0, width, height);
        //按照缩放的大小在BufferedImage对象上绘制原始图像。
        g.drawImage(srcImage, 0, 0, width, height, null);
        //释放图形上下文使用的系统资源。
        g.dispose();
        //刷新此 Image 对象正在使用的所有可重构的资源.
        srcImage.flush();

        return buffImg;
    }

    public static BufferedImage zoom(Image srcImage) {
        //构造一个预定义的图像类型的BufferedImage对象。
        BufferedImage buffImg = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
//        buffImg.flush();
        //创建Graphics2D对象，用于在BufferedImage对象上绘图。
        Graphics2D g = buffImg.createGraphics();

        //设置图形上下文的当前颜色为白色。
        g.setColor(Color.WHITE);
        //用图形上下文的当前颜色填充指定的矩形区域。
        g.fillRect(0, 0, WIDTH, HEIGHT);
        //按照缩放的大小在BufferedImage对象上绘制原始图像。
        g.drawImage(srcImage, 0, 0, WIDTH, HEIGHT, null);
        //释放图形上下文使用的系统资源。
        g.dispose();
        //刷新此 Image 对象正在使用的所有可重构的资源.
        srcImage.flush();

        return buffImg;
    }
    @Override
    public List<SchRotationDeptAfterWithBLOBs> getAllByApplyYear(String applyYear) {
        SchRotationDeptAfterExample example=new SchRotationDeptAfterExample();
        example.createCriteria().andApplyYearEqualTo(applyYear);
        return afterMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public void saveRegisteManua(String registeManua, String recruitFlow, String applyYear) {

        tempMapper.saveRegisteManua( registeManua,  recruitFlow,  applyYear);
    }

    @Override
    public List<ResDoctorSchProcess> queryProcess(String userCode) {
        return tempMapper.queryProcess(userCode);
    }

    @Override
    public int delProcessType(String processFlow, String recTypeId) {
        return tempMapper.delProcessType(processFlow,recTypeId);
    }
    @Override
    public int backProcessType(String processFlow, String recTypeId) {
        return tempMapper.backProcessType(processFlow,recTypeId);
    }

    @Override
    public void delProcessTypeByFlow(String recFlow) {
        tempMapper.delProcessTypeByFlow(recFlow);
    }
    @Override
    public int backProcessTypeByFlow(String recFlow) {
        return   tempMapper.backProcessTypeByFlow(recFlow);
    }

    @Override
    public List<ResSchProcessExpress> searchRecByProcessWithBLOBsByDel(List<String> recTypeIds, String processFlow) {
        return tempMapper.searchRecByProcessWithBLOBsByDel(recTypeIds,processFlow);
    }

    @Override
    public List<Map<String, String>> afterAttendBackList(String userCode, String startDate, String endDate) {
        return tempMapper.afterAttendBackList(userCode,startDate,endDate);
    }

    @Override
    public int backAttendByFlow(String attendanceFlow) {
        return tempMapper.backAttendByFlow(attendanceFlow);
    }

    @Override
    public int delProcess(String processFlow) {
        int count=0;
        count+=tempMapper.delProcess(processFlow);
        count+=tempMapper.delExpressNotType(processFlow);
        count+=tempMapper.delRecNotType(processFlow);
        count+=tempMapper.delResult(processFlow);
        return count;
    }

    @Override
    public List<Map<String, String>> queryApplyList(String applyYear) {
        return  tempMapper.queryApplyList(applyYear);
    }

    @Override
    public List<ResDoctorSchProcess> queryDelProcess(String userCode) {
        return  tempMapper.queryDelProcess(userCode);
    }

    @Override
    public int backProcess(String processFlow) {
        int count=0;
        count+=tempMapper.backProcess(processFlow);
        count+=tempMapper.backExpressNotType(processFlow);
        count+=tempMapper.backRecNotType(processFlow);
        count+=tempMapper.backResult(processFlow);
        return count;
    }

    @Override
    public void updateResultAfterPic(String haveAfterPic, String processFlow, String operUserFlow) {
        tempMapper.updateResultAfterPic(haveAfterPic,processFlow,operUserFlow);
    }

    @Override
    public void examTeaRole() {

        String examTeaRole= InitConfig.getSysCfg("osca_examtea_role_flow");
        List<SysUser> users=tempMapper.examTeaRole();
        if(users!=null){
            for(SysUser su:users)
            {
                SysUserRole userRole=userRoleBiz.readUserRole(su.getUserFlow(),examTeaRole);
                if(userRole==null)
                {
                    userRole=new SysUserRole();
                    userRole.setUserFlow(su.getUserFlow());
                    userRole.setRoleFlow(examTeaRole);
                    userRole.setWsId("osca");
                    userRole.setAuthUserFlow("00000000");
                    userRoleBiz.saveSysUserRole(userRole);
                }
            }
        }
    }

    @Override
    public int backAttend(String userCode, String startDate, String endDate) {
        return tempMapper.backAttend( userCode,  startDate,  endDate);
    }

    @Override
    public void batchOscaSubmit(String orgFlow) {
        List<OscaTeaScanDoc> infos = tempMapper.getOscaNotSubmitInfo(orgFlow);
        if(infos!=null&&infos.size()>0)
        {
            for(OscaTeaScanDoc oscaTeaScanDoc:infos)
            {
                scoreBatchSubmit(oscaTeaScanDoc.getPartnerFlow(),oscaTeaScanDoc.getDoctorFlow());
            }
        }
    }

    @Override
    public void updateResultAfterPicNotHaveRec() {
            tempMapper.updateResultAfterPicNotHaveRec();
    }

    @Override
    public void updateMonthStatistics(String month) {
        tempMapper.deleteMonthStatistics(month);
        tempMapper.updateMonthStatistics(month,"All");
        tempMapper.updateMonthStatistics(month,"NotGraduate");
        tempMapper.updateMonthStatistics(month,"Graduate");
    }

    @Override
    public void updateMonthAppStatistics(String month) {
        tempMapper.deleteMonthAppStatistics(month);
        tempMapper.updateMonthAppStatistics(month,"All");
        tempMapper.updateMonthAppStatistics(month,"NotGraduate");
        tempMapper.updateMonthAppStatistics(month,"Graduate");
    }

    @Override
    public ExcelUtile addTempIdNo(MultipartFile file) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "idNo"
            };
            return  ExcelUtile.importDataExcel(HashMap.class, 0, wb, keys, new IExcelUtil<HashMap>() {

                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String,Object>> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";
                    for(int i=0;i<datas.size();i++)
                    {
                        Map<String, Object> data=datas.get(i);
                        String idNo=((String) data.get("idNo")).toUpperCase();
                        if(StringUtil.isNotBlank(idNo))
                        {
                            tempMapper.addTempNo(idNo);
                            count++;
                        }
                    }
                    eu.put("code",code);
                    eu.put("count",count);
                }

                @Override
                public String checkExcelData(HashMap data, ExcelUtile eu) {
                    return null;
                }
            });
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }

    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private IResDoctorRecruitBiz doctorRecruitBiz;
    @Autowired
    private ResDoctorRecruitMapper doctorRecruitMapper;
    @Override
    public ExcelUtile addUserInfo(MultipartFile file) {

        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "培训基地:orgName",
                    "培训状态:doctorStatusName",
                    "姓名:userName",
                    "性别:sexName",
                    "年龄:age",
                    "证件类型:cretTypeName",
                    "证件号:idNo",
                    "民族:nationName",
                    "手机号:userPhone",
                    "邮箱:userEmail",
                    "QQ:userQq",
                    "培训专业:speName",
                    "参培年份:sessionNumber",
                    "培训年限:trainigYears",
                    "学员类型:doctorTypeName",
            };
            List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId()); //住院医师的

            Map<String,SysOrg> orgMap=new HashMap<>();
            return  ExcelUtile.importDataExcel2(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {

                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String,Object>> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";
                    for(int i=0;i<datas.size();i++)
                    {
                        Map<String, Object> data=datas.get(i);
                        String orgName=((String) data.get("orgName"));
                        String doctorStatusName=((String) data.get("doctorStatusName"));
                        String userName=((String) data.get("userName"));
                        String sexName=((String) data.get("sexName"));
                        String cretTypeName=((String) data.get("cretTypeName"));
                        String idNo=((String) data.get("idNo")).toUpperCase();
                        String nationName=((String) data.get("nationName"));
                        String userPhone=((String) data.get("userPhone"));
                        String userEmail=((String) data.get("userEmail"));
                        String userQq=((String) data.get("userQq"));
                        String speName=((String) data.get("speName"));
                        String sessionNumber=((String) data.get("sessionNumber"));
                        String trainigYears=((String) data.get("trainigYears"));
                        String doctorTypeName=((String) data.get("doctorTypeName"));

                        SysUser user=userBiz.findByIdNo(idNo);
                        boolean isNew =false;
                        if(user==null) {
                            isNew=true;
                            user = new SysUser();
                            user.setUserFlow(PkUtil.getUUID());
                            user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(), InitConfig.getInitPassWord()));
                            user.setStatusId(UserStatusEnum.Activated.getId());
                            user.setStatusDesc(UserStatusEnum.Activated.getName());
                            user.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                            user.setCreateUserFlow("TempAdd");
                            user.setCreateTime("20180918000000");
                            user.setUserPhone(userPhone);
                            user.setUserEmail(userEmail);
                            user.setUserQq(userQq);
                            user.setUserName(userName);
                            user.setIdNo(idNo);
                            user.setUserCode(idNo);
                            user.setSexName(sexName);
                            Object id = parseEnumId(UserSexEnum.values(),sexName);
                            if(id!=null){
                                user.setSexId(id.toString());
                            }
                            user.setCretTypeName(cretTypeName);
                            Object sexid = parseEnumId(CertificateTypeEnum.values(),cretTypeName);
                            if(sexid!=null){
                                user.setSexId(sexid.toString());
                            }
                            user.setNationName(nationName);
                            Object nationId = parseEnumId(UserNationEnum.values(),nationName);
                            if(nationId!=null){
                                user.setNationId(nationId.toString());
                            }

                            editUser(user,isNew);
                        }
                        JsresRecruitDocInfoWithBLOBs recruitDocInfo=new JsresRecruitDocInfoWithBLOBs();
                        recruitDocInfo.setRecruitFlow(PkUtil.getUUID());
                        recruitDocInfo.setDoctorFlow(user.getUserFlow());
                        recruitDocInfo.setAuditStatusId("Passed");
                        recruitDocInfo.setAuditStatusName("审核通过");
                        recruitDocInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                        recruitDocInfo.setCreateUserFlow("TempAdd");
                        recruitDocInfo.setCreateTime("20180918000000");
                        recruitDocInfo.setOrgName(orgName);
                        SysOrg org=orgMap.get(orgName);
                        if(org==null)
                            org=orgBiz.readSysOrgByName(orgName);
                        if(org!=null)
                        {
                            recruitDocInfo.setOrgFlow(org.getOrgFlow());
                            orgMap.put(orgName,org);
                        }
                        recruitDocInfo.setCatSpeId("DoctorTrainingSpe");
                        recruitDocInfo.setCatSpeName("住院医师");
                        if(sysDictList!=null){
                            for(SysDict dict:sysDictList)
                            {
                                if(dict.getDictName().equals(speName))
                                {
                                    recruitDocInfo.setSpeId(dict.getDictId());
                                }
                            }
                        }
                        recruitDocInfo.setSpeName(speName);
                        recruitDocInfo.setSessionNumber(sessionNumber);
                        int year=0;
                        switch (trainigYears){
                            case "一年":{
                                trainigYears="OneYear";
                                year=1;
                                break;}
                            case "两年":{
                                trainigYears="TwoYear";
                                year=2;
                                break;}
                            case "三年":{
                                year=3;
                                trainigYears="ThreeYear";
                                break;}
                        }
                        recruitDocInfo.setTrainYear(trainigYears);
                        recruitDocInfo.setDoctorStatusName(doctorStatusName);
                        recruitDocInfo.setDoctorStatusId("20");

                        if(StringUtil.isNotBlank(sessionNumber))
                        {
                            int graduationYear=  Integer.valueOf(sessionNumber)+year;
                            recruitDocInfo.setGraduationYear(graduationYear+"");
                        }
                        JsresRecruitInfo doctorInfo=new JsresRecruitInfo();

                        doctorInfo.setRecordFlow(PkUtil.getUUID());
                        doctorInfo.setDoctorFlow(user.getUserFlow());
                        doctorInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                        doctorInfo.setCreateUserFlow("TempAdd");
                        doctorInfo.setCreateTime("20180918000000");
                        doctorInfo.setOrgFlow(recruitDocInfo.getOrgFlow());
                        doctorInfo.setOrgName(recruitDocInfo.getOrgName());
                        doctorInfo.setDoctorTypeName(doctorTypeName);
                        if (StringUtil.isNotBlank(doctorTypeName)) {
                            Object typeId = parseEnumId(com.pinde.core.common.enums.ResDocTypeEnum.values(), doctorTypeName);
                            if(typeId!=null){
                                doctorInfo.setDoctorTypeId(typeId.toString());
                            }
                        }
                        doctorInfo.setSpeId(recruitDocInfo.getSpeId());
                        doctorInfo.setSpeName(recruitDocInfo.getSpeName());
                        doctorInfo.setCatSpeId(recruitDocInfo.getCatSpeId());
                        doctorInfo.setCatSpeName(recruitDocInfo.getCatSpeName());
                        doctorInfo.setTrainYears(recruitDocInfo.getTrainYear());
                        doctorInfo.setSessionNumber(recruitDocInfo.getSessionNumber());
                        doctorInfo.setGraduationYear(recruitDocInfo.getGraduationYear());
                        doctorInfo.setDoctorStatusId(recruitDocInfo.getDoctorStatusId());
                        doctorInfo.setDoctorStatusName(recruitDocInfo.getDoctorStatusName());
                        insertJsresRecruitDocInfo(recruitDocInfo);
                        insertJsresRecruitInfo(doctorInfo);
                        count++;
                    }
                    eu.put("code",code);
                    eu.put("count",count);
                }

                @Override
                public String checkExcelData(HashMap data, ExcelUtile eu) {
                    return null;
                }
            });
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                is.close();
            } catch (IOException  e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }
    @Override
    public ExcelUtile upDateRecruitInfo(MultipartFile file) {

        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "培训基地:orgName",
                    "培训状态:doctorStatusName",
                    "姓名:userName",
                    "性别:sexName",
                    "年龄:age",
                    "证件类型:cretTypeName",
                    "证件号:idNo",
                    "民族:nationName",
                    "手机号:userPhone",
                    "邮箱:userEmail",
                    "QQ:userQq",
                    "培训专业:speName",
                    "参培年份:sessionNumber",
                    "培训年限:trainigYears",
                    "学员类型:doctorTypeName",
            };
            List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId()); //住院医师的
            Map<String,SysOrg> orgMap=new HashMap<>();
            return  ExcelUtile.importDataExcel2(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {

                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String,Object>> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";

                    List<Map<String,Object>> list=new ArrayList<>();
                    List<String> idNos=new ArrayList<>();
                    for(int i=0;i<datas.size();i++)
                    {
                        Map<String, Object> data=datas.get(i);
                        String orgName=((String) data.get("orgName"));
                        String doctorStatusName=((String) data.get("doctorStatusName"));
                        String userName=((String) data.get("userName"));
                        String sexName=((String) data.get("sexName"));
                        String cretTypeName=((String) data.get("cretTypeName"));
                        String idNo=((String) data.get("idNo")).toUpperCase();
                        String nationName=((String) data.get("nationName"));
                        String userPhone=((String) data.get("userPhone"));
                        String userEmail=((String) data.get("userEmail"));
                        String userQq=((String) data.get("userQq"));
                        String speName=((String) data.get("speName"));
                        String sessionNumber=((String) data.get("sessionNumber"));
                        String trainigYears=((String) data.get("trainigYears"));
                        String doctorTypeName=((String) data.get("doctorTypeName"));
                        Map<String,Object> map=new HashMap<String, Object>();
                        map.put("idNo",idNo);
                        map.put("userName",userName);
                        map.put("sessionNumber",sessionNumber);
                        list.add(map);
                        SysUser user=findByIdNo(idNo);
                        if(user==null)
                            user=findByIdNo(idNo.toLowerCase());

                        if(user!=null&&StringUtil.isNotBlank(sessionNumber)) {

                            JsresRecruitDocInfoWithBLOBs recruitDocInfo=readRecruitDocInfo(user.getUserFlow(),sessionNumber);
                            if(recruitDocInfo==null) {
                                recruitDocInfo = new JsresRecruitDocInfoWithBLOBs();
                                recruitDocInfo.setDoctorFlow(user.getUserFlow());
                                recruitDocInfo.setAuditStatusId("Passed");
                                recruitDocInfo.setAuditStatusName("审核通过");
                                recruitDocInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                                recruitDocInfo.setCreateUserFlow("TempModify");
                                recruitDocInfo.setCreateTime("20180918000000");
                            }else{
                                recruitDocInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                                recruitDocInfo.setModifyUserFlow("TempModify");
                                recruitDocInfo.setModifyTime("20180918000000");
                            }
                            recruitDocInfo.setOrgName(orgName);
                            SysOrg org=orgMap.get(orgName);
                            if(org==null)
                                org=orgBiz.readSysOrgByName(orgName);
                            if(org!=null)
                            {
                                recruitDocInfo.setOrgFlow(org.getOrgFlow());
                                orgMap.put(orgName,org);
                            }
                            recruitDocInfo.setCatSpeId("DoctorTrainingSpe");
                            recruitDocInfo.setCatSpeName("住院医师");
                            if(sysDictList!=null){
                                for(SysDict dict:sysDictList)
                                {
                                    if(dict.getDictName().equals(speName))
                                    {
                                        recruitDocInfo.setSpeId(dict.getDictId());
                                    }
                                }
                            }
                            recruitDocInfo.setSpeName(speName);
                            recruitDocInfo.setSessionNumber(sessionNumber);
                            int year=0;
                            switch (trainigYears){
                                case "一年":{
                                    trainigYears="OneYear";
                                    year=1;
                                    break;}
                                case "两年":{
                                    trainigYears="TwoYear";
                                    year=2;
                                    break;}
                                case "三年":{
                                    year=3;
                                    trainigYears="ThreeYear";
                                    break;}
                            }
                            recruitDocInfo.setTrainYear(trainigYears);
                            recruitDocInfo.setDoctorStatusName(doctorStatusName);
                            recruitDocInfo.setDoctorStatusId("20");

                            if(StringUtil.isNotBlank(sessionNumber))
                            {
                                int graduationYear=  Integer.valueOf(sessionNumber)+year;
                                recruitDocInfo.setGraduationYear(graduationYear+"");
                            }

                            JsresRecruitInfo doctorInfo=readRecruitInfo(user.getUserFlow(),sessionNumber);
                            if(doctorInfo==null) {
                                doctorInfo = new JsresRecruitInfo();

                                doctorInfo.setDoctorFlow(user.getUserFlow());
                                doctorInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                                doctorInfo.setCreateUserFlow("TempModify");
                                doctorInfo.setCreateTime("20180918000000");
                            }else{
                                doctorInfo.setModifyUserFlow("TempModify");
                                doctorInfo.setModifyTime("20180918000000");
                            }
                            doctorInfo.setOrgFlow(recruitDocInfo.getOrgFlow());
                            doctorInfo.setOrgName(recruitDocInfo.getOrgName());
                            doctorInfo.setDoctorTypeName(doctorTypeName);
                            if (StringUtil.isNotBlank(doctorTypeName)) {
                                Object typeId = parseEnumId(com.pinde.core.common.enums.ResDocTypeEnum.values(), doctorTypeName);
                                if(typeId!=null){
                                    doctorInfo.setDoctorTypeId(typeId.toString());
                                }
                            }
                            doctorInfo.setSpeId(recruitDocInfo.getSpeId());
                            doctorInfo.setSpeName(recruitDocInfo.getSpeName());
                            doctorInfo.setCatSpeId(recruitDocInfo.getCatSpeId());
                            doctorInfo.setCatSpeName(recruitDocInfo.getCatSpeName());
                            doctorInfo.setTrainYears(recruitDocInfo.getTrainYear());
                            doctorInfo.setSessionNumber(recruitDocInfo.getSessionNumber());
                            doctorInfo.setGraduationYear(recruitDocInfo.getGraduationYear());
                            doctorInfo.setDoctorStatusId(recruitDocInfo.getDoctorStatusId());
                            doctorInfo.setDoctorStatusName(recruitDocInfo.getDoctorStatusName());

                            saveJsresRecruitDocInfo(recruitDocInfo);
                            saveJsresRecruitInfo(doctorInfo);

                            count++;
                        }else{
                            idNos.add(idNo+"===="+sessionNumber+"&&");
                        }
                    }
                    eu.put("list",list);
                    eu.put("code",code);
                    eu.put("idNos",idNos);
                    eu.put("count",count);
                }

                @Override
                public String checkExcelData(HashMap data, ExcelUtile eu) {
                    return null;
                }
            });
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                is.close();
            } catch (IOException  e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }
    @Override
    public ExcelUtile updateReturenInfo(MultipartFile file) {

        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "培训基地:orgName",
                    "年级:sessionNumber",
                    "姓名:userName",
                    "证件号:idNo",
                    "培训专业:speName"
            };
            List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId()); //住院医师的
            Map<String,SysOrg> orgMap=new HashMap<>();
            return  ExcelUtile.importDataExcel2(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {

                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String,Object>> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";

                    List<Map<String,Object>> list=new ArrayList<>();
                    List<String> idNos=new ArrayList<>();
                    for(int i=0;i<datas.size();i++)
                    {
                        Map<String, Object> data=datas.get(i);
                        System.out.println(JSON.toJSONString(data));
                        String orgName=((String) data.get("orgName"));
                        String userName=((String) data.get("userName"));
                        String idNo=((String) data.get("idNo")).toUpperCase();
                        String speName=((String) data.get("speName"));
                        String sessionNumber=((String) data.get("sessionNumber"));
                        Map<String,Object> map=new HashMap<String, Object>();
                        map.put("idNo",idNo);
                        map.put("userName",userName);
                        map.put("sessionNumber",sessionNumber);
                        list.add(map);
                        SysUser user=findByIdNo(idNo);
                        if(user==null)
                            user=findByIdNo(idNo.toLowerCase());

                        if(user!=null&&StringUtil.isNotBlank(sessionNumber)) {
                            JsresRecruitDocInfoWithBLOBs recruitDocInfo=readRecruitDocInfo(user.getUserFlow(),sessionNumber);
                            JsresRecruitInfo doctorInfo=readRecruitInfo(user.getUserFlow(),sessionNumber);

                            ResDoctor doctor=doctorBiz.readDoctor(user.getUserFlow());
                            ResDoctorRecruit resDoctorRecruit = readResDoctorRecruitBySessionNumber(user.getUserFlow(), sessionNumber);
                            if(recruitDocInfo==null && doctorInfo==null) {
                                String traingYear="";
                                if(resDoctorRecruit!=null)
                                    traingYear=resDoctorRecruit.getTrainYear();
                                if(StringUtil.isBlank(traingYear))
                                    traingYear=doctor.getTrainingYears();
                                if(StringUtil.isBlank(traingYear))
                                    traingYear="";
                                recruitDocInfo = new JsresRecruitDocInfoWithBLOBs();
                                recruitDocInfo.setDoctorFlow(user.getUserFlow());
                                recruitDocInfo.setAuditStatusId("Passed");
                                recruitDocInfo.setAuditStatusName("审核通过");
                                recruitDocInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                                recruitDocInfo.setCreateUserFlow("TempModifyReturn");
                                recruitDocInfo.setCreateTime("20180918000000");

                                recruitDocInfo.setOrgName(orgName);
                                SysOrg org=orgMap.get(orgName);
                                if(org==null)
                                    org=orgBiz.readSysOrgByName(orgName);
                                if(org!=null)
                                {
                                    recruitDocInfo.setOrgFlow(org.getOrgFlow());
                                    orgMap.put(orgName,org);
                                }
                                if("助理全科".equals(speName))
                                {
                                    recruitDocInfo.setCatSpeId(com.pinde.core.common.enums.DictTypeEnum.AssiGeneral.getId());
                                    recruitDocInfo.setCatSpeName(com.pinde.core.common.enums.DictTypeEnum.AssiGeneral.getName());
                                    recruitDocInfo.setSpeId("50");
                                    recruitDocInfo.setSpeName(speName);
                                }else {
                                    recruitDocInfo.setCatSpeId("DoctorTrainingSpe");
                                    recruitDocInfo.setCatSpeName("住院医师");
                                    if (sysDictList != null) {
                                        for (SysDict dict : sysDictList) {
                                            if (dict.getDictName().equals(speName)) {
                                                recruitDocInfo.setSpeId(dict.getDictId());
                                            }
                                        }
                                    }
                                    recruitDocInfo.setSpeName(speName);
                                }
                                recruitDocInfo.setDoctorStatusName("在培");
                                recruitDocInfo.setDoctorStatusId("20");
                                recruitDocInfo.setSessionNumber(sessionNumber);

                                int year=0;
                                switch (traingYear){
                                    case "OneYear":{
                                        year=1;
                                        break;}
                                    case "TwoYear":{
                                        year=2;
                                        break;}
                                    case "ThreeYear":{
                                        year=3;
                                        break;}
                                }
                                recruitDocInfo.setTrainYear(traingYear);

                                if(StringUtil.isNotBlank(sessionNumber))
                                {
                                    int graduationYear=  Integer.valueOf(sessionNumber)+year;
                                    recruitDocInfo.setGraduationYear(graduationYear+"");
                                }
                                doctorInfo = new JsresRecruitInfo();

                                doctorInfo.setDoctorFlow(user.getUserFlow());
                                doctorInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                                doctorInfo.setCreateUserFlow("TempModifyReturn");
                                doctorInfo.setCreateTime("20180918000000");

                                doctorInfo.setOrgFlow(recruitDocInfo.getOrgFlow());
                                doctorInfo.setOrgName(recruitDocInfo.getOrgName());

                                doctorInfo.setSpeId(recruitDocInfo.getSpeId());
                                doctorInfo.setSpeName(recruitDocInfo.getSpeName());
                                doctorInfo.setCatSpeId(recruitDocInfo.getCatSpeId());
                                doctorInfo.setCatSpeName(recruitDocInfo.getCatSpeName());
                                doctorInfo.setTrainYears(recruitDocInfo.getTrainYear());
                                doctorInfo.setSessionNumber(recruitDocInfo.getSessionNumber());
                                doctorInfo.setGraduationYear(recruitDocInfo.getGraduationYear());
                                doctorInfo.setDoctorStatusId(recruitDocInfo.getDoctorStatusId());
                                doctorInfo.setDoctorStatusName(recruitDocInfo.getDoctorStatusName());
                                doctorInfo.setDoctorTypeId(doctor.getDoctorTypeId());
                                doctorInfo.setDoctorTypeName(doctor.getDoctorTypeName());

                                saveJsresRecruitDocInfo(recruitDocInfo);
                                saveJsresRecruitInfo(doctorInfo);

                                count++;

                            }else{
                                if(recruitDocInfo!=null&&doctorInfo!=null&& resDoctorRecruit!=null&&!orgName.equals(resDoctorRecruit.getOrgName()))
                                {

                                    recruitDocInfo.setOrgName(orgName);
                                    SysOrg org=orgMap.get(orgName);
                                    if(org==null)
                                        org=orgBiz.readSysOrgByName(orgName);
                                    if(org!=null)
                                    {
                                        recruitDocInfo.setOrgFlow(org.getOrgFlow());
                                        orgMap.put(orgName,org);
                                    }

                                    doctorInfo.setOrgFlow(recruitDocInfo.getOrgFlow());
                                    doctorInfo.setOrgName(recruitDocInfo.getOrgName());

                                    if(!speName.equals(resDoctorRecruit.getSpeName())) {
                                        if ("助理全科".equals(speName)) {

                                            recruitDocInfo.setCatSpeId(com.pinde.core.common.enums.DictTypeEnum.AssiGeneral.getId());
                                            recruitDocInfo.setCatSpeName(com.pinde.core.common.enums.DictTypeEnum.AssiGeneral.getName());
                                            recruitDocInfo.setSpeId("50");
                                            recruitDocInfo.setSpeName(speName);
                                        } else {
                                            recruitDocInfo.setCatSpeId("DoctorTrainingSpe");
                                            recruitDocInfo.setCatSpeName("住院医师");
                                            if (sysDictList != null) {
                                                for (SysDict dict : sysDictList) {
                                                    if (dict.getDictName().equals(speName)) {
                                                        recruitDocInfo.setSpeId(dict.getDictId());
                                                    }
                                                }
                                            }
                                            recruitDocInfo.setSpeName(speName);
                                        }

                                        doctorInfo.setSpeId(recruitDocInfo.getSpeId());
                                        doctorInfo.setSpeName(recruitDocInfo.getSpeName());
                                        doctorInfo.setCatSpeId(recruitDocInfo.getCatSpeId());
                                        doctorInfo.setCatSpeName(recruitDocInfo.getCatSpeName());
                                    }
                                    saveJsresRecruitDocInfo(recruitDocInfo);
                                    saveJsresRecruitInfo(doctorInfo);
                                }else {
                                    idNos.add(idNo + "====" + sessionNumber + "&&");
                                }
                            }
                        }else{
                            idNos.add(idNo+"===="+sessionNumber+"不存在");
                        }
                    }
                    eu.put("list",list);
                    eu.put("code",code);
                    eu.put("idNos",idNos);
                    eu.put("count",count);
                }

                @Override
                public String checkExcelData(HashMap data, ExcelUtile eu) {
                    return null;
                }
            });
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                is.close();
            } catch (IOException  e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }

    public ResDoctorRecruit readResDoctorRecruitBySessionNumber(String doctorFlow, String sessionNumber) {
        ResDoctorRecruitExample example = new ResDoctorRecruitExample();
            example.createCriteria().andDoctorFlowEqualTo(doctorFlow)
                    .andSessionNumberEqualTo(sessionNumber);
            example.setOrderByClause("MODIFY_TIME DESC");
        List<com.pinde.core.model.ResDoctorRecruit> recruitList = doctorRecruitMapper.selectByExample(example);
            if (recruitList != null && !recruitList.isEmpty()) {
                return recruitList.get(0);
            }
        return null;
    }
    @Override
    public ExcelUtile updateRecruitSpeInfo(MultipartFile file) {

        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "姓名:userName",
                    "证件号:idNo",
                    "年级:sessionNumber",
                    "培训专业:speName"
            };
            List<SysDict> sysDictList = dictBiz.searchDictListByDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId()); //住院医师的
            Map<String,SysOrg> orgMap=new HashMap<>();
            return  ExcelUtile.importDataExcel2(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {

                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String,Object>> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";

                    List<Map<String,Object>> list=new ArrayList<>();
                    List<String> idNos=new ArrayList<>();
                    for(int i=0;i<datas.size();i++)
                    {
                        Map<String, Object> data=datas.get(i);
                        String userName=((String) data.get("userName"));
                        String idNo=((String) data.get("idNo")).toUpperCase();
                        String speName=((String) data.get("speName"));
                        String sessionNumber=((String) data.get("sessionNumber"));
                        Map<String,Object> map=new HashMap<String, Object>();
                        map.put("idNo",idNo);
                        map.put("userName",userName);
                        map.put("sessionNumber",sessionNumber);
                        list.add(map);
                        SysUser user=findByIdNo(idNo);
                        if(user==null)
                            user=findByIdNo(idNo.toLowerCase());

                        if(user!=null&&StringUtil.isNotBlank(sessionNumber)) {

                            JsresRecruitDocInfoWithBLOBs recruitDocInfo=readRecruitDocInfo(user.getUserFlow(),sessionNumber);
                            if(recruitDocInfo==null) {
                                recruitDocInfo = new JsresRecruitDocInfoWithBLOBs();
                                recruitDocInfo.setDoctorFlow(user.getUserFlow());
                                recruitDocInfo.setAuditStatusId("Passed");
                                recruitDocInfo.setAuditStatusName("审核通过");
                                recruitDocInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                                recruitDocInfo.setCreateUserFlow("TempModify");
                                recruitDocInfo.setCreateTime("20180918000000");
                            }else{
                                recruitDocInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                                recruitDocInfo.setModifyUserFlow("TempModify");
                                recruitDocInfo.setModifyTime("20180918000000");
                            }
                            recruitDocInfo.setCatSpeId("DoctorTrainingSpe");
                            recruitDocInfo.setCatSpeName("住院医师");
                            if(sysDictList!=null){
                                for(SysDict dict:sysDictList)
                                {
                                    if(dict.getDictName().equals(speName))
                                    {
                                        recruitDocInfo.setSpeId(dict.getDictId());
                                    }
                                }
                            }
                            recruitDocInfo.setSpeName(speName);
                            recruitDocInfo.setSessionNumber(sessionNumber);

                            JsresRecruitInfo doctorInfo=readRecruitInfo(user.getUserFlow(),sessionNumber);
                            if(doctorInfo==null) {
                                doctorInfo = new JsresRecruitInfo();

                                doctorInfo.setDoctorFlow(user.getUserFlow());
                                doctorInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                                doctorInfo.setCreateUserFlow("TempModify");
                                doctorInfo.setCreateTime("20180918000000");
                            }else{
                                doctorInfo.setModifyUserFlow("TempModify");
                                doctorInfo.setModifyTime("20180918000000");
                            }
                            doctorInfo.setSpeId(recruitDocInfo.getSpeId());
                            doctorInfo.setSpeName(recruitDocInfo.getSpeName());
                            doctorInfo.setCatSpeId(recruitDocInfo.getCatSpeId());
                            doctorInfo.setCatSpeName(recruitDocInfo.getCatSpeName());
                            saveJsresRecruitDocInfo(recruitDocInfo);
                            saveJsresRecruitInfo(doctorInfo);

                            count++;
                        }else{
                            idNos.add(idNo+"===="+sessionNumber+"&&");
                        }
                    }
                    eu.put("list",list);
                    eu.put("code",code);
                    eu.put("idNos",idNos);
                    eu.put("count",count);
                }

                @Override
                public String checkExcelData(HashMap data, ExcelUtile eu) {
                    return null;
                }
            });
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                is.close();
            } catch (IOException  e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }
//    @Autowired
//    private  SptUserInfoMapper sptUserInfoMapper;
//    @Autowired
//    private  GjptUserInfoMapper gjptUserInfoMapper;
    @Override
    public ExcelUtile insertSptUserInfo(MultipartFile file) {

        InputStream is = null;
        try {
            return new ExcelUtile();
//            is = file.getInputStream();
//            byte[] fileData = new byte[(int) file.getSize()];
//            is.read(fileData);
//            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
//            //map中的keys  个数与execl中表头字段数量一致
//            final String[] keys = {
//                    "基地:orgName",
//                    "姓名:userName",
//                    "证件号:idNo",
//                    "专业:spe",
//                    "年级:sessionNumber",
//                    "年限:years"
//            };
//            return  ExcelUtile.importDataExcel2(SptUserInfo.class, 1, wb, keys, new IExcelUtil<SptUserInfo>() {
//
//                @Override
//                public void operExcelData(ExcelUtile eu) {
//                    List<SptUserInfo> datas=eu.getExcelDatas();
//                    int count = 0;
//                    String code="0";
//                    for(SptUserInfo u:datas)
//                    {
//                        u.setRecordFlow(PkUtil.getUUID());
//                        count+=sptUserInfoMapper.insertSelective(u);
//                    }
//                    eu.put("code",code);
//                    eu.put("count",count);
//                }
//
//                @Override
//                public String checkExcelData(SptUserInfo data, ExcelUtile eu) {
//                    return null;
//                }
//            });
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                is.close();
            } catch (IOException  e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }
    @Override
    public ExcelUtile insertGjptUserInfo(MultipartFile file) {

        InputStream is = null;
        try {
            return new ExcelUtile();
//            is = file.getInputStream();
//            byte[] fileData = new byte[(int) file.getSize()];
//            is.read(fileData);
//            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
//            //map中的keys  个数与execl中表头字段数量一致
//            final String[] keys = {
//                    "基地:orgName",
//                    "姓名:userName",
//                    "证件号:idNo",
//                    "专业:spe",
//                    "年级:sessionNumber",
//                    "年限:years"
//            };
//            return  ExcelUtile.importDataExcel2(GjptUserInfo.class, 1, wb, keys, new IExcelUtil<GjptUserInfo>() {
//
//                @Override
//                public void operExcelData(ExcelUtile eu) {
//                    List<GjptUserInfo> datas=eu.getExcelDatas();
//                    int count = 0;
//                    String code="0";
//                    for(GjptUserInfo u:datas)
//                    {
//                        u.setRecordFlow(PkUtil.getUUID());
//                        count+=gjptUserInfoMapper.insertSelective(u);
//                    }
//                    eu.put("code",code);
//                    eu.put("count",count);
//                }
//
//                @Override
//                public String checkExcelData(GjptUserInfo data, ExcelUtile eu) {
//                    return null;
//                }
//            });
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                is.close();
            } catch (IOException  e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public void updateNotStudy() {
        tempMapper.updateStudentCourse();
        tempMapper.updateStudentCourseSchedule();
    }

    @Override
    public List<ResSchProcessExpress> updateAfterEvalutaion() {
        return  tempMapper.updateAfterEvalutaion();
    }

    @Override
    public void updateAfter(ResSchProcessExpress express) {
        expressMapper.updateByPrimaryKeySelective(express);
    }

    @Override
    public ExcelUtile updateRecruitOrgInfo(MultipartFile file) {

        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            //map中的keys  个数与execl中表头字段数量一致
            final String[] keys = {
                    "培训基地:orgName",
                    "年级:sessionNumber",
                    "姓名:userName",
                    "证件号:idNo"
            };
            Map<String,SysOrg> orgMap=new HashMap<>();
            return  ExcelUtile.importDataExcel2(HashMap.class, 1, wb, keys, new IExcelUtil<HashMap>() {
                @Override
                public void operExcelData(ExcelUtile eu) {
                    List<Map<String,Object>> datas=eu.getExcelDatas();
                    int count = 0;
                    String code="0";

                    List<Map<String,Object>> list=new ArrayList<>();
                    List<String> idNos=new ArrayList<>();
                    System.out.println(datas.size());
                    for(int i=0;i<datas.size();i++)
                    {
                        Map<String, Object> data=datas.get(i);
                        System.out.println(JSON.toJSONString(data));
                        String orgName=((String) data.get("orgName"));
                        String userName=((String) data.get("userName"));
                        String idNo=((String) data.get("idNo")).toUpperCase();
                        String sessionNumber=((String) data.get("sessionNumber"));
                        Map<String,Object> map=new HashMap<String, Object>();
                        map.put("idNo",idNo);
                        map.put("userName",userName);
                        map.put("sessionNumber",sessionNumber);
                        list.add(map);
                        SysUser user=findByIdNo(idNo);
                        if(user==null)
                            user=findByIdNo(idNo.toLowerCase());

                        if(user!=null&&StringUtil.isNotBlank(sessionNumber)) {
                            JsresRecruitDocInfoWithBLOBs recruitDocInfo=readRecruitDocInfo(user.getUserFlow(),sessionNumber);
                            if(recruitDocInfo==null) {
                                recruitDocInfo = new JsresRecruitDocInfoWithBLOBs();
                                recruitDocInfo.setDoctorFlow(user.getUserFlow());
                                recruitDocInfo.setAuditStatusId("Passed");
                                recruitDocInfo.setAuditStatusName("审核通过");
                                recruitDocInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                                recruitDocInfo.setCreateUserFlow("TempModifyOrg");
                                recruitDocInfo.setCreateTime("20180918000000");
                            }else{
                                recruitDocInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                                recruitDocInfo.setModifyUserFlow("TempModifyOrg");
                                recruitDocInfo.setModifyTime("20180918000000");
                            }
                            recruitDocInfo.setOrgName(orgName);
                            SysOrg org=orgMap.get(orgName);
                            if(org==null)
                                org=orgBiz.readSysOrgByName(orgName);
                            if(org!=null)
                            {
                                recruitDocInfo.setOrgFlow(org.getOrgFlow());
                                orgMap.put(orgName,org);
                            }
                            JsresRecruitInfo doctorInfo=readRecruitInfo(user.getUserFlow(),sessionNumber);
                            if(doctorInfo==null) {
                                doctorInfo = new JsresRecruitInfo();
                                doctorInfo.setDoctorFlow(user.getUserFlow());
                                doctorInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_Y);
                                doctorInfo.setCreateUserFlow("TempModifyOrg");
                                doctorInfo.setCreateTime("20180918000000");
                            }else{
                                doctorInfo.setModifyUserFlow("TempModifyOrg");
                                doctorInfo.setModifyTime("20180918000000");
                            }
                            doctorInfo.setOrgFlow(recruitDocInfo.getOrgFlow());
                            doctorInfo.setOrgName(recruitDocInfo.getOrgName());

                            saveJsresRecruitDocInfo(recruitDocInfo);
                            saveJsresRecruitInfo(doctorInfo);

                            count++;
                        }else{
                            idNos.add(idNo+"===="+sessionNumber+"&&");
                        }
                    }
                    eu.put("list",list);
                    eu.put("code",code);
                    eu.put("idNos",idNos);
                    eu.put("count",count);
                }

                @Override
                public String checkExcelData(HashMap data, ExcelUtile eu) {
                    return null;
                }
            });
        } catch (Exception e) {
            logger.error("", e);
        } finally {
            try {
                is.close();
            } catch (IOException  e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return null;
    }
    public SysUser findByIdNo(String idNo) {
        SysUserExample sysUserExample=new SysUserExample();
        SysUserExample.Criteria criteria=sysUserExample.createCriteria();
        criteria.andIdNoEqualTo(idNo);
        List<SysUser> sysUserList = sysUserMapper.selectByExample(sysUserExample);
        if(sysUserList.size()>0){
            return sysUserList.get(0);
        }
        return null;
    }
    private void saveJsresRecruitInfo(JsresRecruitInfo doctorInfo) {
        if(StringUtil.isBlank(doctorInfo.getRecordFlow()))
        {
            doctorInfo.setRecordFlow(PkUtil.getUUID());
            docMapper.insertSelective(doctorInfo);
        }else{
            docMapper.updateByPrimaryKeySelective(doctorInfo);
        }
    }

    private void saveJsresRecruitDocInfo(JsresRecruitDocInfoWithBLOBs recruitDocInfo) {

        if(StringUtil.isBlank(recruitDocInfo.getRecruitFlow()))
        {
            recruitDocInfo.setRecruitFlow(PkUtil.getUUID());
            recruitInfoMapper.insertSelective(recruitDocInfo);
        }else{
            recruitInfoMapper.updateByPrimaryKeySelective(recruitDocInfo);
        }
    }

    private JsresRecruitInfo readRecruitInfo(String doctorFlow, String sessionNumber) {
        JsresRecruitInfoExample example=new JsresRecruitInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow).andSessionNumberEqualTo(sessionNumber);
        List<JsresRecruitInfo> list=docMapper.selectByExample(example);
        if(list!=null&&list.size()>0)
        {
            return  list.get(0);
        }
        return null;
    }

    private JsresRecruitDocInfoWithBLOBs readRecruitDocInfo(String doctorFlow, String sessionNumber) {
        JsresRecruitDocInfoExample example=new JsresRecruitDocInfoExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
                .andDoctorFlowEqualTo(doctorFlow).andSessionNumberEqualTo(sessionNumber);
        List<JsresRecruitDocInfoWithBLOBs> list=recruitInfoMapper.selectByExampleWithBLOBs(example);
        if(list!=null&&list.size()>0)
        {
            return  list.get(0);
        }
        return null;
    }

    private void insertJsresRecruitInfo(JsresRecruitInfo doctorInfo) {
        docMapper.insertSelective(doctorInfo);
    }

    private void insertJsresRecruitDocInfo(JsresRecruitDocInfoWithBLOBs recruitDocInfo) {
        recruitInfoMapper.insertSelective(recruitDocInfo);
    }

    @Autowired
    private JsresRecruitDocInfoMapper recruitInfoMapper;
    @Autowired
    private JsresRecruitInfoMapper docMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    
    private void editUser(SysUser user, boolean isNew) {

        if(isNew) 
            sysUserMapper.insertSelective(user);
        else
            sysUserMapper.updateByPrimaryKeySelective(user);
    }

    private Object parseEnumId(GeneralEnum[] ts, String enumName){
        Object id = null;
        if(StringUtil.isNotBlank(enumName)){
            for(GeneralEnum e : ts){
                String name = e.getName();
                if(enumName.equals(name)){
                    id = e.getId();
                    break;
                }
            }
        }
        return id;
    }
    public void scoreBatchSubmit(String userFlow,String doctorFlow) {

        SysUser tea=userBiz.findByFlow(userFlow);
        List<OscaSkillDocScore> scores=getTeaDocScores(userFlow,doctorFlow);
        if(scores!=null&&scores.size()>0){
            for(OscaSkillDocScore score:scores){
                //提交成绩
                if(score!=null&&score.getStatusId().equals(ScoreStatusEnum.Save.getId())){
                    score.setStatusId(ScoreStatusEnum.Submit.getId());
                    score.setStatusName(ScoreStatusEnum.Submit.getName());
                    editOscaSkillDocScore(score,tea);
                    //计算站点的平均值
                    String clinicalFlow=score.getClinicalFlow();
                    String stationFlow=score.getStationFlow();
                    Map<String, String> param=new HashMap<>();
                    param.put("userFlow", userFlow);
                    param.put("doctorFlow", score.getDoctorFlow());
                    param.put("clinicalFlow", clinicalFlow);
                    param.put("stationFlow", stationFlow);
                    ////设置当前站点学员已经考核
                    OscaSkillRoomDoc docRoom=getOscaSkillRoomDocByDoc(param);
                    if (docRoom != null && docRoom.getIsAdminAudit().equals(com.pinde.core.common.GlobalConstant.FLAG_N)) {
                        //获取所有站点老师提交的总成绩，以及有多少老师提交了成绩
                        Map<String,Object> map=getDocStationAllScore(param);
                        String avgScore=(String) map.get("STATION_AVG_SCORE");
                        if(StringUtil.isNotBlank(avgScore))
                        {
                            docRoom.setExamScore(avgScore);
                            docRoom.setExamSaveScore(avgScore);
                        }else{
                            docRoom.setExamScore("0");
                            docRoom.setExamSaveScore("0");
                        }
                        editOscaSkillRoomDoc(docRoom,tea);
                        //修改预约信息表的状态
                        updateDoctorAssessment(clinicalFlow,score.getDoctorFlow(),tea);
                    }
                    updateTeaScanDocStatus( score.getDoctorFlow(),clinicalFlow,tea);
                }
            }
        }
    }
    public OscaDoctorAssessment getOscaDoctorAssessment(String clinicalFlow, String doctorFlow) {
        OscaDoctorAssessmentExample example=new OscaDoctorAssessmentExample();
        OscaDoctorAssessmentExample.Criteria criteria=example.createCriteria();
        criteria.andDoctorFlowEqualTo(doctorFlow).andClinicalFlowEqualTo(clinicalFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<OscaDoctorAssessment> list=doctorAssessmentMapper.selectByExample(example);
        if(list!=null&&list.size()>0){
            return  list.get(0);
        }
        return null;
    }
    public OscaSkillsAssessment getOscaSkillsAssessmentByFlow(String clinicalFlow) {
        return skillsAssessmentMapper.selectByPrimaryKey(clinicalFlow);
    }
    public List<OscaSkillRoomDoc> getOscaSkillRoomDocs(String clinicalFlow, String doctorFlow) {
        OscaSkillRoomDocExample example=new OscaSkillRoomDocExample();
        OscaSkillRoomDocExample.Criteria criteria=example.createCriteria();
        criteria.andClinicalFlowEqualTo(clinicalFlow).andDoctorFlowEqualTo(doctorFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        return roomDocMapper.selectByExample(example);
    }
    public OscaSubjectMain getOscaSubjectMain(String subjectFlow) {

        return oscaSubjectMainMapper.selectByPrimaryKey(subjectFlow);
    }
    public List<OscaSubjectPartScore> getOscaSubjectPartScores(String subjectFlow) {
        if(StringUtil.isNotBlank(subjectFlow))
        {
            OscaSubjectPartScoreExample example=new OscaSubjectPartScoreExample();
            OscaSubjectPartScoreExample.Criteria criteria =example.createCriteria();
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
            List<OscaSubjectPartScore> list=partScoreMapper.selectByExample(example);
            return list;
        }
        return null;
    }
    public List<OscaSubjectStationScore> getOscaSubjectStationScores(String subjectFlow) {
        if(StringUtil.isNotBlank(subjectFlow))
        {
            OscaSubjectStationScoreExample example=new OscaSubjectStationScoreExample();
            OscaSubjectStationScoreExample.Criteria criteria =example.createCriteria();
            criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
            List<OscaSubjectStationScore> list=stationScoreMapper.selectByExample(example);
            return list;
        }
        return null;
    }

    public List<OscaSubjectStation> getOscaSubjectStations(String subjectFlow) {
        OscaSubjectStationExample station=new OscaSubjectStationExample();
        OscaSubjectStationExample.Criteria criteria=station.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andSubjectFlowEqualTo(subjectFlow);
        station.setOrderByClause(" ORDINAL ASC ");
        return stationMapper.selectByExample(station);
    }
    public void updateDoctorAssessment(String clinicalFlow, String doctorFlow,SysUser user) {
        OscaDoctorAssessment doctorAssessment=getOscaDoctorAssessment(clinicalFlow,doctorFlow);
        OscaSkillsAssessment skillAssess=getOscaSkillsAssessmentByFlow(clinicalFlow);
        //学员当前考核信息的所有考站信息
        List<OscaSkillRoomDoc> roomDocs=getOscaSkillRoomDocs(clinicalFlow,doctorFlow);
        boolean f=false;
        if(doctorAssessment!=null&&skillAssess!=null){
            if(roomDocs!=null&&roomDocs.size()>0) {
                OscaSubjectMain main = getOscaSubjectMain(skillAssess.getSubjectFlow());
                //总分
                Integer allScore=main.getAllScore();
                //每一部分的分数
                Map<String,Integer> partPassScoreMap=new HashMap<>();
                List<OscaSubjectPartScore> partScores=getOscaSubjectPartScores(skillAssess.getSubjectFlow());
                if(partScores!=null)
                {
                    for(OscaSubjectPartScore partScore:partScores)
                    {
                        partPassScoreMap.put(partScore.getPartFlow(),partScore.getPartScore());
                    }
                }
                //每一站的合格分
                Map<String,Integer> stationPassScoreMap=new HashMap<>();
                List<OscaSubjectStationScore> stationScores=getOscaSubjectStationScores(skillAssess.getSubjectFlow());
                if(stationScores!=null)
                {
                    for(OscaSubjectStationScore stationScore:stationScores)
                    {
                        stationPassScoreMap.put(stationScore.getStationFlow(),stationScore.getStationScore());
                    }
                }

                //获取考核方案下的站点
                List<OscaSubjectStation> stations = getOscaSubjectStations(skillAssess.getSubjectFlow());

                if (stations != null) {
                    //如果学员所有站点都没有考核完，直接是不通过
                    if (roomDocs.size() < stations.size()) {
                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
                        doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
                        doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
                        f=true;
                    }else {
                        //每部分的站点数量
                        Map<String, List<OscaSubjectStation>> partMap = new HashMap<>();
                        for (OscaSubjectStation s : stations) {
                            List<OscaSubjectStation> temp = partMap.get(s.getPartFlow());
                            if (temp == null)
                                temp = new ArrayList<>();
                            temp.add(s);
                            partMap.put(s.getPartFlow(), temp);
                        }
                        //考核总分
                        double examAllScore = 0;
                        //考核分数
                        Map<String, Double> examScoreMap = new HashMap<>();
                        for (OscaSkillRoomDoc d : roomDocs) {
                            Double examScore = 0.0;
                            if (StringUtil.isNotBlank(d.getExamScore())) {
                                examScore=Double.valueOf(d.getExamScore());
                            }
                            examAllScore += examScore;
                            examScoreMap.put(d.getStationFlow(), examScore);
                        }
                        //如果合格总分配置了 并且 考核 总分小于合格总分 直接不通过
                        if(allScore!=null&&allScore>examAllScore)
                        {
                            doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
                            doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
                            doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
                            f=true;
                        }else {
                            //有几部分
                            int partCount = partMap.size();
                            int partPassCount = 0;
                            if (partCount > 0) {
                                //计算每一部分的总分，考核总分，以及是否通过
                                for (String key : partMap.keySet()) {
                                    List<OscaSubjectStation> temp = partMap.get(key);
                                    double examAll = 0;
                                    for (OscaSubjectStation s : temp) {
                                        Double examScore = examScoreMap.get(s.getStationFlow());
                                        examAll += examScore;
                                    }
                                    Integer partPassScore=partPassScoreMap.get(key);
                                    //如果未配置 或 每一部分部分大于每一部分考核总分 就算合格
                                    if(partPassScore==null||examAll>=partPassScore)
                                    {
                                        partPassCount++;
                                    }
                                }
                                //所有部分都合格 则计算每一站是否都合格
                                if (partCount == partPassCount) {
                                    int stationPassCount=0;
                                    for (OscaSubjectStation s : stations) {
                                        Integer stationPassScore=stationPassScoreMap.get(s.getStationFlow());
                                        Double examScore = examScoreMap.get(s.getStationFlow());
                                        //如果未配置 或 每一部分部分大于每一部分考核总分 就算合格
                                        if (examScore!=null&&(stationPassScore == null || examScore >= stationPassScore) )
                                        {
                                            stationPassCount++;
                                        }
                                    }
                                    if(stationPassCount==stations.size()) {
                                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_Y);
                                        doctorAssessment.setIsPass(DoctorScoreEnum.Passed.getId());
                                        doctorAssessment.setIsPassName(DoctorScoreEnum.Passed.getName());
                                        f = true;
                                    }else{
                                        doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
                                        doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
                                        doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
                                        f=true;
                                    }
                                }else{
                                    doctorAssessment.setIsSavePass(com.pinde.core.common.GlobalConstant.FLAG_N);
                                    doctorAssessment.setIsPass(DoctorScoreEnum.UnPassed.getId());
                                    doctorAssessment.setIsPassName(DoctorScoreEnum.UnPassed.getName());
                                    f=true;
                                }
                            }
                        }

                    }
                }
            }
        }
        if(f){
            editOscaDoctorAssessment(doctorAssessment,user);
        }
    }

    public int editOscaDoctorAssessment(OscaDoctorAssessment doctorAssessment, SysUser user) {
        if(doctorAssessment!=null){
            if(StringUtil.isNotBlank(doctorAssessment.getRecordFlow())){//修改
                doctorAssessment.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                doctorAssessment.setModifyUserFlow(user.getUserFlow());
                doctorAssessment.setModifyTime(DateUtil.getCurrDateTime());
                return this.doctorAssessmentMapper.updateByPrimaryKeySelective(doctorAssessment);
            }else{//新增
                doctorAssessment.setRecordFlow(PkUtil.getUUID());
                doctorAssessment.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                doctorAssessment.setCreateUserFlow(user.getUserFlow());
                doctorAssessment.setCreateTime(DateUtil.getCurrDateTime());
                doctorAssessment.setModifyUserFlow(user.getUserFlow());
                doctorAssessment.setModifyTime(DateUtil.getCurrDateTime());
                return this.doctorAssessmentMapper.insertSelective(doctorAssessment);
            }
        }
        return 0;
    }
    public int editOscaSkillRoomDoc(OscaSkillRoomDoc docStation, SysUser user) {
        if(docStation!=null){
            if(StringUtil.isNotBlank(docStation.getDocRoomFlow())){//修改
                docStation.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                docStation.setModifyUserFlow(user.getUserFlow());
                docStation.setModifyTime(DateUtil.getCurrDateTime());
                return this.roomDocMapper.updateByPrimaryKeySelective(docStation);
            }else{//新增
                docStation.setDocRoomFlow(PkUtil.getUUID());
                docStation.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                docStation.setCreateUserFlow(user.getUserFlow());
                docStation.setCreateTime(DateUtil.getCurrDateTime());
                docStation.setModifyUserFlow(user.getUserFlow());
                docStation.setModifyTime(DateUtil.getCurrDateTime());
                return this.roomDocMapper.insertSelective(docStation);
            }
        }
        return 0;
    }
    public Map<String, Object> getDocStationAllScore(Map<String, String> param) {
        return tempMapper.getDocStationAllScore(param);
    }
    public List<OscaSkillDocScore> getTeaDocScores(String userFlow,String doctorFlow) {
        return tempMapper.getTeaDocScores(userFlow,doctorFlow);
    }
    public OscaSkillRoomDoc getOscaSkillRoomDocByDoc(Map<String, String> param) {
        List<OscaSkillRoomDoc> scores=tempMapper.getOscaSkillRoomDocByDoc(param);
        if(scores!=null&&!scores.isEmpty())
        {
            return scores.get(0);
        }
        return null;
    }
    public int editOscaSkillDocScore(OscaSkillDocScore score, SysUser user) {
        if(score!=null){
            if(StringUtil.isNotBlank(score.getScoreFlow())){//修改
                score.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                score.setModifyUserFlow(user.getUserFlow());
                score.setModifyTime(DateUtil.getCurrDateTime());
                return this.scoreMapper.updateByPrimaryKeySelective(score);
            }else{//新增
                score.setScoreFlow(PkUtil.getUUID());
                score.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                score.setCreateUserFlow(user.getUserFlow());
                score.setCreateTime(DateUtil.getCurrDateTime());
                score.setModifyUserFlow(user.getUserFlow());
                score.setModifyTime(DateUtil.getCurrDateTime());
                return this.scoreMapper.insertSelective(score);
            }
        }
        return 0;
    }
    public List<OscaSubjectStation> getTeaDocStation(Map<String, String> paramMap) {
        return tempMapper.getTeaDocStation(paramMap);
    }

    public List<OscaSubjectStation> getTeaSubDocStation(Map<String, String> paramMap) {
        return tempMapper.getTeaSubDocStation(paramMap);
    }
    public List<OscaSkillDocScore> getDocScoreByParam(Map<String, String> param) {
        List<OscaSkillDocScore> scores=tempMapper.getDocScoreByParam(param);
        return scores;
    }
    public OscaTeaScanDoc getOscaTeaScanDoc(Map<String, String> param) {
        List<OscaTeaScanDoc> scores=tempMapper.getOscaTeaScanDoc(param);
        if(scores!=null&&!scores.isEmpty())
        {
            return scores.get(0);
        }
        return null;
    }
    public void updateTeaScanDocStatus(String doctorFlow, String clinicalFlow,SysUser user) {
        //查询学员当前状态
        //查询当前考官可以考核当前学员学员哪些站点
        Map<String,String> p = new HashMap<String,String>();
        p.put("doctorFlow",doctorFlow);
        p.put("clinicalFlow",clinicalFlow);
        p.put("userFlow",user.getUserFlow());
        List<OscaSubjectStation> stations=getTeaDocStation(p);
        //换考官后 再次查看学员
        if(stations==null||stations.size()<=0)
        {
            stations=getTeaSubDocStation(p);
        }
        String statusId= ScanDocStatusEnum.StayAssessment.getId();
        String statusName=ScanDocStatusEnum.StayAssessment.getName();
        if(stations!=null&&stations.size()>0) {
            int NotHaveScore = 0;
            int SaveScore = 0;
            int SubmitScore = 0;
            for (int i = 0; i < stations.size(); i++) {
                OscaSubjectStation s = stations.get(i);
                //查询当前站点考官可以考核学员的考场
                Map<String, String> param = new HashMap<>();
                param.put("userFlow", user.getUserFlow());
                param.put("doctorFlow", doctorFlow);
                param.put("stationFlow", s.getStationFlow());
                param.put("clinicalFlow", clinicalFlow);
                //当前站点考官是否给学员打过分
                List<OscaSkillDocScore> scores=getDocScoreByParam(param);
                if(scores!=null&&!scores.isEmpty())
                {
                    String examScoreStatusId="";
                    int submitCount=0;
                    int saveCount=0;
                    for(OscaSkillDocScore score:scores) {
                        if (ScoreStatusEnum.Submit.getId().equals(score.getStatusId())) {
                            submitCount++;
                        }
                        if (ScoreStatusEnum.Save.getId().equals(score.getStatusId())) {
                            saveCount++;
                        }
                    }
                    //当前考站状态
                    if(submitCount==0&&saveCount==0)
                    {
                        examScoreStatusId="";
                    }else if(submitCount>0&&submitCount==scores.size())
                    {
                        examScoreStatusId="Submit";
                    }else if(saveCount>0)
                    {
                        examScoreStatusId="Save";
                    }
                    if (StringUtil.isBlank(examScoreStatusId)) {
                        NotHaveScore++;
                    } else if (ScoreStatusEnum.Save.getId().equals(examScoreStatusId)) {
                        SaveScore++;
                    } else if (ScoreStatusEnum.Submit.getId().equals(examScoreStatusId)) {
                        SubmitScore++;
                    }
                }else{
                    NotHaveScore++;
                }
            }
            if (SaveScore > 0) {
                statusId=ScanDocStatusEnum.NotSubmit.getId();
                statusName=ScanDocStatusEnum.NotSubmit.getName();
            }
            if (SubmitScore == stations.size()) {
                statusId=ScanDocStatusEnum.Submit.getId();
                statusName=ScanDocStatusEnum.Submit.getName();
            }
            if (NotHaveScore > 0) {
                statusId=ScanDocStatusEnum.StayAssessment.getId();
                statusName=ScanDocStatusEnum.StayAssessment.getName();
            }
        }

        Map<String,String> paramMap = new HashMap<String,String>();
        paramMap.put("userFlow",user.getUserFlow());
        paramMap.put("clinicalFlow", clinicalFlow);
        paramMap.put("doctorFlow", doctorFlow);
        OscaTeaScanDoc doc=getOscaTeaScanDoc(paramMap);
        if(doc!=null)
        {
            doc.setStatusId(statusId);
            doc.setStatusName(statusName);
            editTeaScanDoc(doc,user);
        }
    }

    public int editTeaScanDoc(OscaTeaScanDoc b, SysUser user) {
        if(b!=null){
            if(StringUtil.isNotBlank(b.getRecordFlow())){//修改
                b.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                b.setModifyUserFlow(user.getUserFlow());
                b.setModifyTime(DateUtil.getCurrDateTime());
                return this.scanDocMapper.updateByPrimaryKeySelective(b);
            }else{//新增
                b.setRecordFlow(PkUtil.getUUID());
                b.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
                b.setCreateUserFlow(user.getUserFlow());
                b.setCreateTime(DateUtil.getCurrDateTime());
                b.setModifyUserFlow(user.getUserFlow());
                b.setModifyTime(DateUtil.getCurrDateTime());
                return this.scanDocMapper.insertSelective(b);
            }
        }
        return 0;
    }

}
