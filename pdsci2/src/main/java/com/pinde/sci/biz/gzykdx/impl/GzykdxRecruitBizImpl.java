package com.pinde.sci.biz.gzykdx.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.gzykdx.IGzykdxRecruitBiz;
import com.pinde.sci.biz.gzykdx.IGzykdxSchoolAdminBiz;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.common.util.PasswordHelper;
import com.pinde.sci.dao.base.*;
import com.pinde.sci.dao.gzykdx.GzykdxRecruitExtMapper;
import com.pinde.sci.dao.lcjn.LcjnBaseManagerExtMapper;
import com.pinde.sci.enums.gzykdx.GzykdxAdmissionStatusEnum;
import com.pinde.sci.enums.gzykdx.GzykdxAuditStatusEnum;
import com.pinde.sci.enums.gzykdx.GzykdxDegreeTypeEnum;
import com.pinde.sci.enums.pub.UserStatusEnum;
import com.pinde.sci.form.gzykdx.GzykdxRecruitExtInfoForm;
import com.pinde.sci.model.mo.*;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class GzykdxRecruitBizImpl implements IGzykdxRecruitBiz {
    @Autowired
    private GzykdxRecruitExtMapper recruitExtMapper;
    @Autowired
    private SysUserMapper userMapper;
    @Autowired
    private ICfgBiz cfgBiz;
    @Autowired
    private SysUserRoleMapper userRoleMapper;
    @Autowired
    private DoctorTeacherRecruitMapper doctorTeacherRecruitMapper;
    @Autowired
    private PubUserResumeMapper pubUserResumeMapper;
    @Autowired
    private TeacherTargetApplyMapper teacherTargetApplyMapper;
    @Autowired
    private DoctorTeacherRecruitBatchMapper doctorTeacherRecruitBatchMapper;
    @Autowired
    private IUserBiz userBiz;

    @Override
    public List<Map<String, Object>> searchReexamineStudent(Map<String, String> map) {
        return recruitExtMapper.searchReexamineStudent(map);
    }

    @Override
    public int importReexamStudentExcel(MultipartFile file) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseExcel(wb);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
    private Workbook createCommonWorkbook(InputStream inS) throws Exception {
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
        throw new Exception("不能解析的excel版本");
    }
    private int parseExcel(Workbook wb) throws Exception{
        int count = 0;//导入记录数
        int sheetNum = wb.getNumberOfSheets();
        if(sheetNum > 0){
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum()+1;
            if(row_num<=2){
                throw new Exception("导入文件内容为空！");
            }
            //获取表头
            Row titleR =  sheet.getRow(1);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            List<String> colnames = new ArrayList<String>();
            for(int i = 0 ; i <cell_num; i++){
                colnames.add(titleR.getCell(i).getStringCellValue());
            }
            for(int i = 2;i < row_num; i++) {

                SysUser user = new SysUser();//新建javaBean
                DoctorTeacherRecruit dtr = new DoctorTeacherRecruit();
                GzykdxRecruitExtInfoForm resume = new GzykdxRecruitExtInfoForm();
                List<String> titles = new ArrayList<>();//判断是否是已存在的导入项目列名
                TeacherTargetApply apply = new TeacherTargetApply();

                Row r = sheet.getRow(i);
                for(int j = 0; j < colnames.size(); j++){
                    String value = "";
                    Cell cell = r.getCell(j);
                    if(null != cell && StringUtil.isNotBlank(cell.toString().trim())){
                        if(cell.getCellType() == 1){
                            value = cell.getStringCellValue().trim();
                        }else{
                            value = _doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    String currTitle = colnames.get(j);
                    //sysUser表
                    if("ksbh".equals(currTitle)){
                        titles.add("ksbh");
                        user.setUserCode(value);
                        if(StringUtil.isBlank(user.getUserCode())){
                            throw new Exception("导入失败！第"+ (count+3) +"行考生编号不能为空！");
                        }
                    }
                    if("xm".equals(currTitle)){
                        titles.add("xm");
                        user.setUserName(value);
                        dtr.setDoctorName(value);
                        if(StringUtil.isBlank(user.getUserName())){
                            throw new Exception("导入失败！第"+ (count+3) +"行姓名不能为空！");
                        }
                    }
                    if("lxfs".equals(currTitle)){
                        titles.add("lxfs");
                        user.setUserPhone(value);
                    }
                    if("sfxwks".equals(currTitle)){
                        titles.add("sfxwks");
                        if("是".equals(value)){
                            user.setIsOwnerStu("N");
                        }else if("否".equals(value)){
                            user.setIsOwnerStu("Y");
                        }
                        if(StringUtil.isBlank(user.getIsOwnerStu())){
                            throw new Exception("导入失败！第"+ (count+3) +"行是否校外考生不能为空！");
                        }
                    }

                    //doctorTeacherRecruit表
                    dtr.setAuditStatusId(GzykdxAdmissionStatusEnum.Passing.getId());
                    dtr.setAuditStatusName(GzykdxAdmissionStatusEnum.Passing.getName());
                    dtr.setSchoolAuditStatusId(GzykdxAdmissionStatusEnum.Passing.getId());
                    dtr.setSchoolAuditStatusName(GzykdxAdmissionStatusEnum.Passing.getName());
                    dtr.setRecruitBatch("0");

                    if("nf".equals(currTitle)){
                        titles.add("nf");
                        if(StringUtil.isNotBlank(value)){
                            dtr.setRecruitYear(value);
                        }else {
                            dtr.setRecruitYear(DateUtil.getYear());
                        }
                    }
                    if("bkzydm".equals(currTitle)){
                        titles.add("bkzydm");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行报考专业代码不能为空！");
                        }
                        dtr.setSpeId(value);
                        dtr.setFinalSpeId(value);
                        apply.setSpeId(value);
                    }
//                    if("yjfxm".equals(currTitle)){
//                        titles.add("yjfxm");
//                        if(StringUtil.isBlank(value)){
//                            throw new Exception("导入失败！第"+ (count+3) +"行研究方向码不能为空！");
//                        }
//                        dtr.setResearchAreaId(value);
//                        dtr.setFinalResearchAreaId(value);
//                    }
                    if("yjfxmc".equals(currTitle)){
                        titles.add("yjfxmc");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行研究方向名称不能为空！");
                        }
                        dtr.setResearchAreaName(value);
                        dtr.setFinalResearchAreaName(value);
                        apply.setResearchDirection(value);
                    }
                    if("bklx".equals(currTitle)){
                        titles.add("bklx");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"报考类型不能为空！");
                        }
                        dtr.setRecruitTypeName(value);
                    }
                    if("bkds".equals(currTitle)){
                        titles.add("bkds");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行报考导师不能为空！");
                        }
                        dtr.setUserName(value);
                        dtr.setFinalUserName(value);
                    }
                    if("bkdw".equals(currTitle)){
                        titles.add("bkdw");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行报考单位不能为空！");
                        }
                        dtr.setOrgName(value);
                        dtr.setFinalOrgName(value);
                    }
                    if("dssfzh".equals(currTitle)){
                        titles.add("dssfzh");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行导师身份证号不能为空！");
                        }
                        dtr.setUserIdNo(value);
                        SysUser user1 = userBiz.findByIdNo(value);
                        if(user1!=null){
                            String userFLow = user1.getUserFlow();
                            dtr.setUserFlow(userFLow);
                            dtr.setFinalUserFlow(userFLow);
                            dtr.setOrgFlow(user1.getOrgFlow());
                            dtr.setFinalOrgFlow(user1.getOrgFlow());
                            apply.setUserFlow(user1.getUserFlow());
                        }else{
                            throw new Exception("导入失败！第"+ (count+3) +"行导师不存在！");
                        }
                    }

                    if("bkzymc".equals(currTitle)){
                        titles.add("bkzymc");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行报考专业名称不能为空！");
                        }
                        dtr.setSpeName(value);
                        dtr.setFinalSpeName(value);
                    }
                    //大字段必填项目
                    if("ksfsm".equals(currTitle)){
                        titles.add("ksfsm");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行考试方式码不能为空！");
                        }
                        resume.setKsfsm(value);
                    }
                    if("ywk1mc".equals(currTitle)){
                        titles.add("ywk1mc");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行业务课一名称不能为空！");
                        }
                        resume.setYwk1mc(value);
                    }
                    if("ywk2mc".equals(currTitle)){
                        titles.add("ywk2mc");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行业务课二名称不能为空！");
                        }
                        resume.setYwk2mc(value);
                    }
                    if("zzll".equals(currTitle)){
                        titles.add("zzll");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行政治理论成绩不能为空！");
                        }
                        resume.setZzll(value);
                    }
                    if("wgy".equals(currTitle)){
                        titles.add("wgy");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行外国语成绩不能为空！");
                        }
                        resume.setWgy(value);
                    }
                    if("ywk1".equals(currTitle)){
                        titles.add("ywk1");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行业务课一成绩不能为空！");
                        }
                        resume.setYwk1(value);
                    }
                    if("ywk2".equals(currTitle)){
                        titles.add("ywk2");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行业务课二成绩不能为空！");
                        }
                        resume.setYwk2(value);
                    }
                    if("zf".equals(currTitle)){
                        titles.add("zf");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行总分不能为空！");
                        }
                        resume.setZf(value);
                    }
                    if("cscj".equals(currTitle)){
                        titles.add("cscj");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行初试成绩不能为空！");
                        }
                        DecimalFormat df = new DecimalFormat("0.0");
                        Double cscj = Double.parseDouble(value);
                        resume.setCscj(df.format(cscj));
                    }
                    if("zcjzsbf".equals(currTitle)){
                        titles.add("zcjzsbf");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行总成绩折算办法不能为空！");
                        }
                        resume.setZcjzsbf(value);
                    }
                    if("xwlx".equals(currTitle)){
                        titles.add("xwlx");
                        if(StringUtil.isBlank(value)){
                            throw new Exception("导入失败！第"+ (count+3) +"行学位类型不能为空！");
                        }
                        dtr.setDegreeTypeName(value);
                        dtr.setFinalDegreeTypeName(value);
                        if("学术型".equals(value)){
                            dtr.setDegreeTypeId(GzykdxDegreeTypeEnum.AcademicType.getId());
                            dtr.setFinalDegreeTypeId(GzykdxDegreeTypeEnum.AcademicType.getId());
                        }
                        if("专业型".equals(value)){
                            dtr.setDegreeTypeId(GzykdxDegreeTypeEnum.ProfessionalType.getId());
                            dtr.setFinalDegreeTypeId(GzykdxDegreeTypeEnum.ProfessionalType.getId());
                        }
                    }
                    if("fsh".equals(currTitle)){
                        titles.add("fsh");
                        resume.setFsh(value);
                    }
                    if("js1cj".equals(currTitle)){
                        titles.add("js1cj");
                        resume.setJs1cj(value);
                    }
                    if("js2cj".equals(currTitle)){
                        titles.add("js2cj");
                        resume.setJs2cj(value);
                    }
                    if("ywk1m".equals(currTitle)){
                        titles.add("ywk1m");
                        resume.setYwk1m(value);
                    }
                    if("ywk2m".equals(currTitle)){
                        titles.add("ywk2m");
                        resume.setYwk2m(value);
                    }
                    if("xmpy".equals(currTitle)){
                        titles.add("xmpy");
                        resume.setXmpy(value);
                    }
                    if("zjlx".equals(currTitle)){
                        titles.add("zjlx");
                        resume.setZjlx(value);
                    }
                    if("zjhm".equals(currTitle)){
                        titles.add("zjhm");
                        resume.setZjhm(value);
                    }
                    if("csny".equals(currTitle)){
                        titles.add("csny");
                        resume.setCsny(value);
                    }
                    if("ywk1m".equals(currTitle)){
                        titles.add("ywk1m");
                        resume.setYwk1m(value);
                    }
                    if("mzm".equals(currTitle)){
                        titles.add("mzm");
                        resume.setMzm(value);
                    }
                    if("xbm".equals(currTitle)){
                        titles.add("xbm");
                        resume.setXbm(value);
                        if("1".equals(value)){
                            user.setSexName("男");
                        }
                        if("2".equals(value)){
                            user.setSexName("女");
                        }
                    }
                    if("hfm".equals(currTitle)){
                        titles.add("hfm");
                        resume.setHfm(value);
                    }
                    if("xyjrm".equals(currTitle)){
                        titles.add("xyjrm");
                        resume.setXyjrm(value);
                    }
                    if("zzmmm".equals(currTitle)){
                        titles.add("zzmmm");
                        resume.setZzmmm(value);
                    }
                    if("hkszdm".equals(currTitle)){
                        titles.add("hkszdm");
                        resume.setHkszdm(value);
                    }
                    if("hkszdxxdz".equals(currTitle)){
                        titles.add("hkszdxxdz");
                        resume.setHkszdxxdz(value);
                    }
                    if("daszdm".equals(currTitle)){
                        titles.add("daszdm");
                        resume.setDaszdm(value);
                    }
                    if("daszdw".equals(currTitle)){
                        titles.add("daszdw");
                        resume.setDaszdw(value);
                    }
                    if("daszdwdz".equals(currTitle)){
                        titles.add("daszdwdz");
                        resume.setDaszdwdz(value);
                    }
                    if("daszdwyzbm".equals(currTitle)){
                        titles.add("daszdwyzbm");
                        resume.setDaszdwyzbm(value);
                    }
                    if("bydwm".equals(currTitle)){
                        titles.add("bydwm");
                        resume.setBydwm(value);
                    }
                    if("bydw".equals(currTitle)){
                        titles.add("bydw");
                        resume.setBydw(value);
                    }
                    if("byzydm".equals(currTitle)){
                        titles.add("byzydm");
                        resume.setByzydm(value);
                    }
                    if("byzymc".equals(currTitle)){
                        titles.add("byzymc");
                        resume.setByzymc(value);
                    }
                    if("xxxs".equals(currTitle)){
                        titles.add("xxxs");
                        resume.setXxxs(value);
                    }
                    if("xlm".equals(currTitle)){
                        titles.add("xlm");
                        resume.setXlm(value);
                    }
                    if("xlzsbh".equals(currTitle)){
                        titles.add("xlzsbh");
                        resume.setXlzsbh(value);
                    }
                    if("kslym".equals(currTitle)){
                        titles.add("kslym");
                        resume.setKslym(value);
                    }
                    if("byny".equals(currTitle)){
                        titles.add("byny");
                        resume.setByny(value);
                    }
                    if("zcxh".equals(currTitle)){
                        titles.add("zcxh");
                        resume.setZcxh(value);
                    }
                    if("xwm".equals(currTitle)){
                        titles.add("xwm");
                        resume.setXwm(value);
                    }
                    if("xwzsbh".equals(currTitle)){
                        titles.add("xwzsbh");
                        resume.setXwzsbh(value);
                    }
                    if("zxjh".equals(currTitle)){
                        titles.add("zxjh");
                        resume.setZxjh(value);
                    }
                    if("dxwpdwszdm".equals(currTitle)){
                        titles.add("dxwpdwszdm");
                        resume.setDxwpdwszdm(value);
                    }
                    if("dxwpdw".equals(currTitle)){
                        titles.add("dxwpdw");
                        resume.setDxwpdw(value);
                    }
                    if("zzllm".equals(currTitle)){
                        titles.add("zzllm");
                        resume.setZzllm(value);
                    }
                    if("zzllmc".equals(currTitle)){
                        titles.add("zzllmc");
                        resume.setZzllmc(value);
                    }
                    if("wgym".equals(currTitle)){
                        titles.add("wgym");
                        resume.setWgym(value);
                    }
                    if("wgymc".equals(currTitle)){
                        titles.add("wgymc");
                        resume.setWgymc(value);
                    }
                    if("rwpzsbh".equals(currTitle)){
                        titles.add("rwpzsbh");
                        resume.setRwpzsbh(value);
                    }
                    if("tcxyzbh".equals(currTitle)){
                        titles.add("tcxyzbh");
                        resume.setTcxyzbh(value);
                    }
                    if("lqdwdm".equals(currTitle)){
                        titles.add("lqdwdm");
                        resume.setLqdwdm(value);
                    }
                    if("lqdwmc".equals(currTitle)){
                        titles.add("lqdwmc");
                        resume.setLqdwmc(value);
                    }
                    if("lqzydm".equals(currTitle)){
                        titles.add("lqzydm");
                        resume.setLqzydm(value);
                    }
                    if("lqzymc".equals(currTitle)){
                        titles.add("lqzymc");
                        resume.setLqzymc(value);
                    }
                    if("lqlbm".equals(currTitle)){
                        titles.add("lqlbm");
                        resume.setLqlbm(value);
                    }
                    if("lqxysm".equals(currTitle)){
                        titles.add("lqxysm");
                        resume.setLqxysm(value);
                    }
                    if("fscj".equals(currTitle)){
                        titles.add("fscj");
                        resume.setFscj(value);
                    }
                    if("zcjzsbf".equals(currTitle)){
                        titles.add("zcjzsbf");
                        resume.setZcjzsbf(value);
                    }
                    if("zcj".equals(currTitle)){
                        titles.add("zcj");
                        resume.setZcj(value);
                    }
                    if("js1mc".equals(currTitle)){
                        titles.add("js1mc");
                        resume.setJs1mc(value);
                    }
                    if("js2mc".equals(currTitle)){
                        titles.add("js2mc");
                        resume.setJs2mc(value);
                    }
                    if("blzgnx".equals(currTitle)){
                        titles.add("blzgnx");
                        resume.setBlzgnx(value);
                    }
                    if("qg".equals(currTitle)){
                        titles.add("qg");
                        resume.setQg(value);
                    }
                    if("xszgzc".equals(currTitle)){
                        titles.add("xszgzc");
                        resume.setXszgzc(value);
                    }
                    if("szssm".equals(currTitle)){
                        titles.add("szssm");
                        resume.setSzssm(value);
                    }
                    if("bz".equals(currTitle)){
                        titles.add("bz");
                        resume.setBz(value);
                    }
                    if("lhpydwm".equals(currTitle)){
                        titles.add("lhpydwm");
                        resume.setLhpydwm(value);
                    }
                    if("lhpydw".equals(currTitle)){
                        titles.add("lhpydw");
                        resume.setLhpydw(value);
                    }
                    if("xz".equals(currTitle)){
                        titles.add("xz");
                        resume.setXz(value);
                    }
                    if("bkdwdm".equals(currTitle)){
                        titles.add("bkdwdm");
                        resume.setBkdwdm(value);
                    }
                    if("bklbm".equals(currTitle)){
                        titles.add("bklbm");
                        resume.setBklbm(value);
                    }
                    if("bkyxsm".equals(currTitle)){
                        titles.add("bkyxsm");
                        resume.setBkyxsm(value);
                    }
                    if("jgszdm".equals(currTitle)){
                        titles.add("jgszdm");
                        resume.setJgszdm(value);
                    }
                    if("csdm".equals(currTitle)){
                        titles.add("csdm");
                        resume.setCsdm(value);
                    }
                    if("bmddm".equals(currTitle)){
                        titles.add("bmddm");
                        resume.setBmddm(value);
                    }
                    if("bmh".equals(currTitle)){
                        titles.add("bmh");
                        resume.setBmh(value);
                    }
                    if("xxgzdw".equals(currTitle)){
                        titles.add("xxgzdw");
                        resume.setXxgzdw(value);
                    }
                    if("xxgzjl".equals(currTitle)){
                        titles.add("xxgzjl");
                        resume.setXxgzjl(value);
                    }
                    if("jlcf".equals(currTitle)){
                        titles.add("jlcf");
                        resume.setJlcf(value);
                    }
                    if("kszbqk".equals(currTitle)){
                        titles.add("kszbqk");
                        resume.setKszbqk(value);
                    }
                    if("jtcy".equals(currTitle)){
                        titles.add("jtcy");
                        resume.setJtcy(value);
                    }
                    if("txdz".equals(currTitle)){
                        titles.add("txdz");
                        resume.setTxdz(value);
                    }
                    if("yzbm".equals(currTitle)){
                        titles.add("yzbm");
                        resume.setYzbm(value);
                    }
                    if("lxdh".equals(currTitle)){
                        titles.add("lxdh");
                        resume.setLxdh(value);
                    }
                    if("yddh".equals(currTitle)){
                        titles.add("yddh");
                        resume.setYddh(value);
                    }
                    if("dzxx".equals(currTitle)){
                        titles.add("dzxx");
                        resume.setDzxx(value);
                    }
                    if("zsdwsm".equals(currTitle)){
                        titles.add("zsdwsm");
                        resume.setZsdwsm(value);
                    }
                    if("bkdsm".equals(currTitle)){
                        titles.add("bkdsm");
                        resume.setBkdsm(value);
                    }
                    if("byxx".equals(currTitle)){
                        titles.add("byxx");
                        resume.setByxx(value);
                    }
                    if("byxx1".equals(currTitle)){
                        titles.add("byxx1");
                        resume.setByxx1(value);
                    }
                    if("byxx2".equals(currTitle)){
                        titles.add("byxx2");
                        resume.setByxx2(value);
                    }
                    if("byxx3".equals(currTitle)){
                        titles.add("byxx3");
                        resume.setByxx3(value);
                    }
                    if("bydwssm".equals(currTitle)){
                        titles.add("bydwssm");
                        resume.setBydwssm(value);
                    }
                    if("kzyxx".equals(currTitle)){
                        titles.add("kzyxx");
                        resume.setKzyxx(value);
                    }
                    if("jfbz".equals(currTitle)){
                        titles.add("jfbz");
                        resume.setJfbz(value);
                    }
                    if("zxbz".equals(currTitle)){
                        titles.add("zxbz");
                        resume.setZxbz(value);
                    }
                    if("bmsj".equals(currTitle)){
                        titles.add("bmsj");
                        resume.setBmsj(value);
                    }
                    if("xgsj".equals(currTitle)){
                        titles.add("xgsj");
                        resume.setXgsj(value);
                    }
                    if("qrsj".equals(currTitle)){
                        titles.add("qrsj");
                        resume.setQrsj(value);
                    }
                    if("bkdwmc".equals(currTitle)){
                        titles.add("bkdwmc");
                        resume.setBkdwmc(value);
                    }
                    if("bkyxsmc".equals(currTitle)){
                        titles.add("bkyxsmc");
                        resume.setBkyxsmc(value);
                    }
                    if(!titles.contains(currTitle)){
                        throw new Exception("导入失败！无代码为“"+ currTitle +"”的字段");
                    }
                }
                TeacherTargetApplyExample applyExample=new TeacherTargetApplyExample();
                TeacherTargetApplyExample.Criteria criteria=applyExample.createCriteria();
                criteria.andAuditStatusIdEqualTo(GzykdxAuditStatusEnum.SchoolPassed.getId())
                        .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y)
                        .andUserFlowEqualTo(apply.getUserFlow())
                        .andResearchDirectionEqualTo(apply.getResearchDirection())
                        .andSpeIdEqualTo(apply.getSpeId());
                List<TeacherTargetApply> applyList=new ArrayList<>();
                applyList=teacherTargetApplyMapper.selectByExample(applyExample);
                if(applyList.size()==0){
                    throw new Exception("导入失败！第"+ (count+3) +"行所报考的导师指标信息不存在！");
                }

                SysUserExample example = new SysUserExample();
                example.createCriteria().andUserCodeEqualTo(user.getUserCode());
                int num = userMapper.countByExample(example);
                if(num <= 0){//新增
                    user.setUserFlow(PkUtil.getUUID());
                    user.setUserPasswd(PasswordHelper.encryptPassword(user.getUserFlow(),"123456"));
                    user.setStatusId(UserStatusEnum.Activated.getId());
                    GeneralMethod.setRecordInfo(user,true);
                    userMapper.insertSelective(user);

                    SysUserRole userRole = new SysUserRole();//插入学员角色
                    userRole.setRecordFlow(PkUtil.getUUID());
                    userRole.setWsId("gzykdx");
                    userRole.setUserFlow(user.getUserFlow());
                    userRole.setRecordStatus(GlobalConstant.RECORD_STATUS_Y);
                    userRole.setAuthTime(DateUtil.getCurrDateTime());
                    userRole.setAuthUserFlow(user.getUserFlow());
                    userRole.setCreateTime(DateUtil.getCurrDateTime());
                    userRole.setCreateUserFlow(user.getUserFlow());
                    userRole.setModifyTime(DateUtil.getCurrDateTime());
                    userRole.setModifyUserFlow(user.getUserFlow());
                    SysCfg cfg = cfgBiz.read("gzykdx_doctor_role_flow");
                    String doctorRole ="";
                    if (null != cfg) {
                        doctorRole = cfg.getCfgValue();
                        if(!StringUtil.isNotBlank(doctorRole)){
                            doctorRole = cfg.getCfgBigValue();
                        }
                    }
                    if(StringUtil.isNotBlank(doctorRole)){
                        userRole.setRoleFlow(doctorRole);
                    }else{
                        throw new Exception("导入失败！请配置考生角色！");
                    }
                    userRoleMapper.insertSelective(userRole);
                    //插入完毕

                    String userFlow = userMapper.selectByExample(example).get(0).getUserFlow();
                    dtr.setRecordFlow(PkUtil.getUUID()); //插入学生招录表信息
                    dtr.setDoctorFlow(userFlow);
                    GeneralMethod.setRecordInfo(dtr, true);
                    doctorTeacherRecruitMapper.insertSelective(dtr);
                    //插入完毕

                    //插入学生信息大字段信息
                    PubUserResume userResume = new PubUserResume();
                    userResume.setUserFlow(userFlow);
                    userResume.setUserName(user.getUserName());
                    String content = JaxbUtil.convertToXml(resume);
                    userResume.setUserResume(content);
                    GeneralMethod.setRecordInfo(userResume, true);
                    pubUserResumeMapper.insertSelective(userResume);
                    //插入完毕

                }else{//修改
                    userMapper.updateByExampleSelective(user,example);//user

                    String userFlow = userMapper.selectByExample(example).get(0).getUserFlow(); //recruit
                    DoctorTeacherRecruitExample recruitExample = new DoctorTeacherRecruitExample();
                    recruitExample.createCriteria().andDoctorFlowEqualTo(userFlow).andRecordStatusEqualTo("Y");
                    DoctorTeacherRecruit doctorTeacherRecruit1 = doctorTeacherRecruitMapper.selectByExample(recruitExample).get(0);
                    String dtrFlow = doctorTeacherRecruit1.getRecordFlow();
                    dtr.setRecordFlow(dtrFlow);
                    doctorTeacherRecruitMapper.updateByPrimaryKeySelective(dtr);


                    PubUserResume pubUserResume = new PubUserResume();//大字段
                    pubUserResume.setUserFlow(userFlow);
                    pubUserResume.setUserName(user.getUserName());
                    pubUserResume.setUserResume(JaxbUtil.convertToXml(resume));
                    pubUserResume.setRecordStatus("Y");
                    GeneralMethod.setRecordInfo(pubUserResume,false);
                    pubUserResumeMapper.updateByPrimaryKeyWithBLOBs(pubUserResume);
                }
                count ++;
            }
        }
        return count;
    }
    private static String _doubleTrans(double d) {
        if ((double) Math.round(d) - d == 0.0D)
            return String.valueOf((long) d);
        else
            return String.valueOf(d);
    }

    @Override
    public List<Map<String, Object>> vacanciesQuery(Map<String, String> map) {
        return recruitExtMapper.vacanciesQuery(map);
    }

    @Override
    public List<Map<String, Object>> teacherRecruitInfo(Map<String, String> map) {
        return recruitExtMapper.teacherRecruitInfo(map);
    }

    @Override
    public List<Map<String, Object>> searchSecondaryRecriutInfo(Map<String, String> map) {
        return recruitExtMapper.searchSecondaryRecriutInfo(map);
    }

    @Override
    public List<TeacherTargetApply> searchTeacherTargetApply(TeacherTargetApply teacherTargetApply) {
        TeacherTargetApplyExample example = new TeacherTargetApplyExample();
        TeacherTargetApplyExample.Criteria criteria = example.createCriteria().
                andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andAuditStatusIdEqualTo(GzykdxAuditStatusEnum.SchoolPassed.getId());
        if(StringUtil.isNotBlank(teacherTargetApply.getRecruitYear())){
            criteria.andRecruitYearEqualTo(teacherTargetApply.getRecruitYear());
        }
        if(StringUtil.isNotBlank(teacherTargetApply.getSpeId())){
            criteria.andSpeIdEqualTo(teacherTargetApply.getSpeId());
        }
        if(StringUtil.isNotBlank(teacherTargetApply.getResearchDirection())){
            criteria.andResearchDirectionLike("%"+teacherTargetApply.getResearchDirection()+"%");
        }
        if(StringUtil.isNotBlank(teacherTargetApply.getUserFlow())){
            criteria.andUserFlowEqualTo(teacherTargetApply.getUserFlow());
        }
        if(StringUtil.isNotBlank(teacherTargetApply.getUserName())){
            criteria.andUserNameLike("%"+teacherTargetApply.getUserName()+"%");
        }
        if(StringUtil.isNotBlank(teacherTargetApply.getIsAcademic())){
            criteria.andIsAcademicIsNotNull();
        }
        if(StringUtil.isNotBlank(teacherTargetApply.getIsSpecialized())){
            criteria.andIsSpecializedIsNotNull();
        }
        if(StringUtil.isNotBlank(teacherTargetApply.getOrgFlow())){
            criteria.andOrgFlowEqualTo(teacherTargetApply.getOrgFlow());
        }
        example.setOrderByClause("CREATE_TIME");
        return teacherTargetApplyMapper.selectByExample(example);
    }

    @Override
    public List<DoctorTeacherRecruit> searchDoctorTeacherRecruit(DoctorTeacherRecruit doctorTeacherRecruit) {
        DoctorTeacherRecruitExample example = new DoctorTeacherRecruitExample();
        DoctorTeacherRecruitExample.Criteria criteria = example.createCriteria().
                andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(doctorTeacherRecruit.getRecruitYear())){
            criteria.andRecruitYearEqualTo(doctorTeacherRecruit.getRecruitYear());
        }
        if(StringUtil.isNotBlank(doctorTeacherRecruit.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(doctorTeacherRecruit.getDoctorFlow());
        }
        return doctorTeacherRecruitMapper.selectByExample(example);
    }

    @Override
    public List<Map<String, Object>> searchSecondaryReexamineStudent(Map<String, String> map) {
        return recruitExtMapper.searchSecondaryReexamineStudent(map);
    }

    @Override
    public int editDocTeaRec(DoctorTeacherRecruit doctorTeacherRecruit) {
        GeneralMethod.setRecordInfo(doctorTeacherRecruit,false);
        return doctorTeacherRecruitMapper.updateByPrimaryKeySelective(doctorTeacherRecruit);
    }

    @Override
    public int editTTA(TeacherTargetApply teacherTargetApply) {
        GeneralMethod.setRecordInfo(teacherTargetApply,false);
        return teacherTargetApplyMapper.updateByPrimaryKeySelective(teacherTargetApply);
    }

    @Override
    public DoctorTeacherRecruit readDocTeaRec(String recordFlow) {
        if(StringUtil.isNotBlank(recordFlow)){
            return doctorTeacherRecruitMapper.selectByPrimaryKey(recordFlow);
        }
        return null;
    }

    @Override
    public List<TeacherTargetApply> changeTeacherList(Map<String, String> map) {
        return recruitExtMapper.changeTeacherList(map);
    }

    @Override
    public List<DoctorTeacherRecruitBatch> searchBatches(DoctorTeacherRecruitBatch doctorTeacherRecruitBatch) {
        DoctorTeacherRecruitBatchExample example = new DoctorTeacherRecruitBatchExample();
        DoctorTeacherRecruitBatchExample.Criteria criteria= example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(doctorTeacherRecruitBatch.getRefRecordFlow())){
            criteria.andRefRecordFlowEqualTo(doctorTeacherRecruitBatch.getRefRecordFlow());
        }
        return doctorTeacherRecruitBatchMapper.selectByExample(example);
    }

    @Override
    public int getLeftNum(Map<String,String> map) {
        Integer result = recruitExtMapper.leftStuNum(map);
        if(null == result){
            return 0;
        }
        return result;
    }
}
