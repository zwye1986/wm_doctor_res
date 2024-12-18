package com.pinde.sci.biz.jsres.impl;

import com.pinde.core.model.*;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IResSkillTimeConfigBiz;
import com.pinde.sci.biz.sys.IDictBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.sci.dao.base.JsresExamSignupMapper;
import com.pinde.sci.dao.base.ResDoctorSkillMapper;
import com.pinde.sci.dao.base.ResSkillTimeConfigMapper;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResSkillTimeConfigBizImpl implements IResSkillTimeConfigBiz {
    @Autowired
    private ResSkillTimeConfigMapper skillTimeConfigMapper;
    @Autowired
    private IOrgBiz orgBiz;
    @Autowired
    private IDictBiz dictBiz;
    @Autowired
    private ResDoctorSkillMapper doctorSkillMapper;
    @Autowired
    private IUserBiz userBiz;
    @Autowired
    private JsresExamSignupMapper examSignupMapper;

    //查询所有的考试
    @Override
    public List<ResSkillTimeConfig> findAll() {
        ResSkillTimeConfigExample example = new ResSkillTimeConfigExample();
        example.setOrderByClause("CREATE_TIME DESC");
        return skillTimeConfigMapper.selectByExample(example);
    }

    //获取江苏省所有城市
    @Override
    public List<Map<String, String>> getAllCitys() {
        List<Map<String, String>> citys = new ArrayList<>();
        Map<String, String> city = new HashMap<>();
        city.put("cityId", "320100");
        city.put("cityName", "南京市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320200");
        city.put("cityName", "无锡市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320300");
        city.put("cityName", "徐州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320400");
        city.put("cityName", "常州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320500");
        city.put("cityName", "苏州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320600");
        city.put("cityName", "南通市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320700");
        city.put("cityName", "连云港市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320800");
        city.put("cityName", "淮安市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "320900");
        city.put("cityName", "盐城市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "321000");
        city.put("cityName", "扬州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "321100");
        city.put("cityName", "镇江市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "321200");
        city.put("cityName", "泰州市");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "321300");
        city.put("cityName", "宿迁市");
        citys.add(city);
        return citys;
    }

    //获取江苏省所有城市
    @Override
    public List<Map<String, String>> getAllCitysNew() {
        List<Map<String, String>> citys = new ArrayList<>();
        Map<String, String> city = new HashMap<>();
        city.put("cityId", "120100");
        city.put("cityName", "市辖区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120101");
        city.put("cityName", "和平区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120102");
        city.put("cityName", "河东区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120103");
        city.put("cityName", "河西区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120104");
        city.put("cityName", "南开区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120105");
        city.put("cityName", "河北区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120106");
        city.put("cityName", "红桥区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120110");
        city.put("cityName", "东丽区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120111");
        city.put("cityName", "西青区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120112");
        city.put("cityName", "津南区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120113");
        city.put("cityName", "北辰区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120114");
        city.put("cityName", "武清区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120115");
        city.put("cityName", "宝坻区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120116");
        city.put("cityName", "滨海新区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120117");
        city.put("cityName", "宁河区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120118");
        city.put("cityName", "静海区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120119");
        city.put("cityName", "蓟州区");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120200");
        city.put("cityName", "辖县");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120221");
        city.put("cityName", "宁河县");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120223");
        city.put("cityName", "静海县");
        citys.add(city);
        city = new HashMap<>();
        city.put("cityId", "120225");
        city.put("cityName", "蓟县");
        citys.add(city);
        return citys;
    }

    //插入一条记录
    @Override
    public int insert(ResSkillTimeConfig skillTimeConfig) {
        if (StringUtil.isNotBlank(skillTimeConfig.getSkillTimeFlow())) {
            GeneralMethod.setRecordInfo(skillTimeConfig, false);
            ResSkillTimeConfigExample example = new ResSkillTimeConfigExample();
            example.createCriteria().andSkillTimeFlowEqualTo(skillTimeConfig.getSkillTimeFlow());
            return skillTimeConfigMapper.updateByExampleSelective(skillTimeConfig, example);
        } else {
            skillTimeConfig.setSkillTimeFlow(PkUtil.getUUID());
            ResSkillTimeConfigExample example = new ResSkillTimeConfigExample();
            example.setOrderByClause("TEST_ID DESC");
            //查询出所有的考试
            List<ResSkillTimeConfig> resSkillTimeConfigs = skillTimeConfigMapper.selectByExample(example);
            if (resSkillTimeConfigs.size() > 0) {
                //判断今年有没有已经新增的考试
                if (DateUtil.getYear().equals(resSkillTimeConfigs.get(0).getTestId().substring(0, 4))) {
                    //如果有那么考试编号+1
                    skillTimeConfig.setTestId(String.valueOf(Integer.valueOf(resSkillTimeConfigs.get(0).getTestId()) + 1));
                } else {
                    //如果没有那么考试编号为当年年份拼接上01
                    skillTimeConfig.setTestId(DateUtil.getYear() + "01");
                }
            } else {
                skillTimeConfig.setTestId(DateUtil.getYear() + "01");
            }
            GeneralMethod.setRecordInfo(skillTimeConfig, true);
            return skillTimeConfigMapper.insert(skillTimeConfig);
        }
    }

    //校验当前时间段有没有进行中的考试
    @Override
    public Boolean checkTestExist(ResSkillTimeConfig skillTimeConfig) {
        ResSkillTimeConfigExample example = new ResSkillTimeConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        //获取出所有非关闭的考试
        List<ResSkillTimeConfig> skillTimeConfigs = skillTimeConfigMapper.selectByExample(example);
        if (StringUtil.isNotBlank(skillTimeConfig.getSkillTimeFlow())) {
            //如果是编辑当前这条考试信息，排除当前这条记录不考虑（如果不排除的话编辑这条考试记录时间会与自己冲突）
            skillTimeConfigs = skillTimeConfigs.stream().filter(resSkillTime -> !skillTimeConfig.getSkillTimeFlow().equals(resSkillTime.getSkillTimeFlow())).collect(Collectors.toList());
        }
        //新增的考试时间和库里已存在的考试时间重叠的集合
        List<ResSkillTimeConfig> list = skillTimeConfigs.stream().filter(resTest -> (skillTimeConfig.getSkillStartTime().compareTo(resTest.getSkillStartTime()) >= 0
                && skillTimeConfig.getSkillStartTime().compareTo(resTest.getSkillEndTime()) <= 0
        ) || (skillTimeConfig.getSkillEndTime().compareTo(resTest.getSkillStartTime()) >= 0 &&
                skillTimeConfig.getSkillEndTime().compareTo(resTest.getSkillEndTime()) <= 0)).collect(Collectors.toList());
        List<ResSkillTimeConfig> collect = skillTimeConfigs.stream().filter(resTest -> skillTimeConfig.getSkillStartTime().compareTo(resTest.getSkillStartTime()) <= 0
                && skillTimeConfig.getSkillEndTime().compareTo(resTest.getSkillEndTime()) >= 0).collect(Collectors.toList());
        return list.size() == 0 && collect.size() == 0;
    }
    //根据唯一标识获取考试信息
    @Override
    public ResSkillTimeConfig findOne(String skillTimeFlow) {
        ResSkillTimeConfigExample example = new ResSkillTimeConfigExample();
        example.createCriteria().andSkillTimeFlowEqualTo(skillTimeFlow);
        List<ResSkillTimeConfig> resTestConfigs = skillTimeConfigMapper.selectByExample(example);
        if (resTestConfigs.size() > 0) {
            return resTestConfigs.get(0);
        }
        return new ResSkillTimeConfig();
    }
    //关闭当前的考试
    @Override
    public int closeTest(String skillTimeFlow) {
        ResSkillTimeConfigExample example = new ResSkillTimeConfigExample();
        example.createCriteria().andSkillTimeFlowEqualTo(skillTimeFlow);
        List<ResSkillTimeConfig> resSkillTimeConfigs = skillTimeConfigMapper.selectByExample(example);
        if (resSkillTimeConfigs.size() > 0) {
            ResSkillTimeConfig skillTimeConfig = resSkillTimeConfigs.get(0);
            skillTimeConfig.setRecordStatus(com.pinde.core.common.GlobalConstant.FLAG_N);
            GeneralMethod.setRecordInfo(skillTimeConfig, false);
            ResSkillTimeConfigExample skillTimeConfigExample = new ResSkillTimeConfigExample();
            skillTimeConfigExample.createCriteria().andSkillTimeFlowEqualTo(skillTimeConfig.getSkillTimeFlow());
            return skillTimeConfigMapper.updateByExampleSelective(skillTimeConfig, example);
        }
        return 0;
    }
    //获取当前城市在当前时间存在的考试
    @Override
    public List<ResSkillTimeConfig> findEffective(String currDateTime, String orgCityId) {
        ResSkillTimeConfigExample example = new ResSkillTimeConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andApplyStartTimeLessThanOrEqualTo(currDateTime)
                .andApplyEndTimeGreaterThanOrEqualTo(currDateTime).andCitysIdLike("%" + orgCityId + "%");
        return skillTimeConfigMapper.selectByExample(example);
    }
    //获取所有非关闭的考试
    @Override
    public List<ResSkillTimeConfig> findAllEffective() {
        ResSkillTimeConfigExample example = new ResSkillTimeConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
        return skillTimeConfigMapper.selectByExample(example);
    }
    //获取基地审核时间包含当前时间的所有非关闭的考试
    @Override
    public List<ResSkillTimeConfig> findLocalEffective(String currDateTime) {
        ResSkillTimeConfigExample example = new ResSkillTimeConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andLocalAuditStartTimeLessThanOrEqualTo(currDateTime)
                .andLocalAuditEndTimeGreaterThanOrEqualTo(currDateTime);
        return skillTimeConfigMapper.selectByExample(example);
    }
    //获取市局审核时间包含当前时间的所有非关闭的考试
    @Override
    public List<ResSkillTimeConfig> findChargeEffective(String currDateTime) {
        ResSkillTimeConfigExample example = new ResSkillTimeConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andChargeAuditStartTimeLessThanOrEqualTo(currDateTime)
                .andChargeAuditEndTimeGreaterThanOrEqualTo(currDateTime);
        return skillTimeConfigMapper.selectByExample(example);
    }
    //获取省厅审核时间包含当前时间的所有非关闭的考试
    @Override
    public List<ResSkillTimeConfig> findGlobalEffective(String currDateTime) {
        ResSkillTimeConfigExample example = new ResSkillTimeConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andApplyStartTimeLessThanOrEqualTo(currDateTime)
                .andTestEndTimeGreaterThanOrEqualTo(currDateTime);
        return skillTimeConfigMapper.selectByExample(example);
    }

    /**
     * @param currDateTime
     * @param applyTime
     * @param orgCityId
     * @Department：研发部
     * @Description 查询结业申请创建时间内的考试时间没有结束的考试信息
     * @Author fengxf
     * @Date 2020/6/16
     */
    @Override
    public List<ResSkillTimeConfig> findEffectiveByParam(String currDateTime, String applyTime, String orgCityId) {
        ResSkillTimeConfigExample example = new ResSkillTimeConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andApplyStartTimeLessThanOrEqualTo(applyTime)
                .andApplyEndTimeGreaterThanOrEqualTo(applyTime).andCitysIdLike("%" + orgCityId + "%")
                .andTestEndTimeGreaterThanOrEqualTo(currDateTime);
        return skillTimeConfigMapper.selectByExample(example);
    }

    @Override
    public ResSkillTimeConfig findOneByCurrDate(String currTime) {
        ResSkillTimeConfigExample example = new ResSkillTimeConfigExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
                .andSkillStartTimeLessThan(currTime).andSkillEndTimeGreaterThan(currTime);
        List<ResSkillTimeConfig> lists = skillTimeConfigMapper.selectByExample(example);
        if(null != lists && lists.size()>0){
            return lists.get(0);
        }
        return null;
    }

    @Override
    public int importDocSkillExcel(MultipartFile file,String cityId,String testId) {
        InputStream is = null;
        try {
            is = file.getInputStream();
            byte[] fileData = new byte[(int) file.getSize()];
            is.read(fileData);
            Workbook wb = ExcelUtile.createCommonWorkbook(new ByteInputStream(fileData, (int) file.getSize()));
            return parseExcel(wb,cityId,testId);
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

    @Override
    public JsresExamSignup findExamSignup(JsresExamSignup examSignup) {
        JsresExamSignupExample example = new JsresExamSignupExample();
        JsresExamSignupExample.Criteria criteria = example.createCriteria();
        if(StringUtil.isNotBlank(examSignup.getRecordStatus())){
            criteria.andRecordStatusEqualTo(examSignup.getRecordStatus());
        }
        if(StringUtil.isNotBlank(examSignup.getSignupYear())){
            criteria.andSignupYearEqualTo(examSignup.getSignupYear());
        }
        if(StringUtil.isNotBlank(examSignup.getDoctorFlow())){
            criteria.andDoctorFlowEqualTo(examSignup.getDoctorFlow());
        }
        if(StringUtil.isNotBlank(examSignup.getSignupTypeId())){
            criteria.andSignupTypeIdEqualTo(examSignup.getSignupTypeId());
        }
        if(StringUtil.isNotBlank(examSignup.getAuditStatusId())){
            criteria.andAuditStatusIdEqualTo(examSignup.getAuditStatusId());
        }
        List<JsresExamSignup> list = examSignupMapper.selectByExample(example);
        if(null != list && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    private int parseExcel(Workbook wb, String cityId,String testId) throws Exception {
        int count = 0;//导入记录数
        int sheetNum = wb.getNumberOfSheets();
        //查询基地
        SysOrg org2 = new SysOrg();
        org2.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        org2.setOrgCityId(cityId);
        List<SysOrg> orgs = orgBiz.searchOrg(org2);
        //查询专业
        SysDict dict = new SysDict();
        dict.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        dict.setDictTypeId(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getId());
        List<SysDict> dictList = dictBiz.searchDictList(dict);

        if (sheetNum > 0) {
            Sheet sheet = wb.getSheetAt(0);
            int row_num = sheet.getLastRowNum() + 1;
            if (row_num == 1) {
                throw new Exception("导入文件内容为空！");
            }
            //获取表头
            Row titleR = sheet.getRow(0);
            //获取表头单元格数
            int cell_num = titleR.getLastCellNum();
            List<String> colnames = new ArrayList<String>();
            for (int i = 0; i < cell_num; i++) {
                colnames.add(titleR.getCell(i).getStringCellValue());
            }
            for (int i = 1; i < row_num; i++) {
                ResDoctorSkill doctorSkill = new ResDoctorSkill();//建立bean
                Row r = sheet.getRow(i);
                for (int j = 0; j < colnames.size(); j++) {
                    String value = "";
                    Cell cell = r.getCell(j);
                    if (null != cell && StringUtil.isNotBlank(cell.toString().trim())) {
                        if (cell.getCellType().getCode() == 1) {
                            value = cell.getStringCellValue().trim();
                        } else {
                            value = ExcelUtile._doubleTrans(cell.getNumericCellValue()).trim();
                        }
                    }
                    String currTitle = colnames.get(j);
                    if (currTitle.contains("考试编号")) {
                        doctorSkill.setTestId(value);
                    }
                    if (currTitle.contains("姓名")) {
                        doctorSkill.setDoctorName(value);
                    }
                    if(currTitle.contains("证件号码")){
                        doctorSkill.setIdNo(value);
                    }
                    if (currTitle.contains("培训专业")) {
                        doctorSkill.setSpeName(value);
                        for (SysDict sd : dictList) {
                            if (sd.getDictName().equals(value)) {    //遍历所有基地名称与表格里的基地进行匹配
                                doctorSkill.setSpeId(sd.getDictId());   //匹配上则把对象里的orgFlow往doctorSkill里赋上值
                                break;
                            }
                        }
                    }
                    if (currTitle.contains("准考证号")) {
                        doctorSkill.setTicketNumber(value);
                    }
                    if (currTitle.contains("考试地点")) {
                        doctorSkill.setSkillOrgName(value);
                        for (SysOrg so : orgs) {
                            if (so.getOrgName().equals(value)) {    //遍历所有基地名称与表格里的基地进行匹配
                                doctorSkill.setSkillOrgFlow(so.getOrgFlow());   //匹配上则把对象里的orgFlow往doctorSkill里赋上值
                                break;
                            }
                        }
                    }
                    if (currTitle.contains("考试时间")) {
                        doctorSkill.setSkillTime(value);
                    }
                    if (currTitle.contains("考点联系电话")) {
                        doctorSkill.setSkillOrgPhone(value);
                    }
                    if (currTitle.contains("注意事项")) {
                        doctorSkill.setSkillNote(value);
                    }
                    if (currTitle.contains("准考证标题")) {
                        doctorSkill.setTestName(value);
                    }
                }

                String idCard = "^d{15}$|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)";
                if (StringUtil.isBlank(doctorSkill.getTestId())) {
                    throw new Exception("导入失败！第" + (count + 2) + "行考试编号不能为空！");
                }
                if(!testId.equals(doctorSkill.getTestId())){
                    throw new Exception("导入失败！第" + (count + 2) + "行考试编号有误！");
                }
                if (StringUtil.isBlank(doctorSkill.getDoctorName())) {
                    throw new Exception("导入失败！第" + (count + 2) + "行姓名不能为空！");
                }
                if (StringUtil.isBlank(doctorSkill.getIdNo())) {
                    throw new Exception("导入失败！第" + (count + 2) + "行身份证号不能为空！");
                }
                if (!doctorSkill.getIdNo().matches(idCard)) {
                    throw new Exception("导入失败！第" + (count + 2) + "行身份证号码有误！");
                }
                //根据身份证号查询学员是否在系统存在
                SysUser user = userBiz.findByIdNo(doctorSkill.getIdNo());
                if(null == user){
                    throw new Exception("导入失败！第" + (count + 2) + "行学员不存在，请核对！");
                }else{
                    doctorSkill.setDoctorFlow(user.getUserFlow());
                }

                if (StringUtil.isBlank(doctorSkill.getTicketNumber())) {
                    throw new Exception("导入失败！第" + (count + 2) + "行准考证号不能为空！");
                }
                if (StringUtil.isBlank(doctorSkill.getSkillOrgName())) {
                    throw new Exception("导入失败！第" + (count + 2) + "行考试地点不能为空！");
                }
                if (StringUtil.isBlank(doctorSkill.getSkillOrgFlow())) {
                    throw new Exception("导入失败！第" + (count + 2) + "行考试地点有误！");
                }
                if (StringUtil.isBlank(doctorSkill.getSkillOrgPhone())) {
                    throw new Exception("导入失败！第" + (count + 2) + "行考点联系电话不能为空！");
                }
                if (StringUtil.isBlank(doctorSkill.getTestName())) {
                    throw new Exception("导入失败！第" + (count + 2) + "行准考证标题不能为空！");
                }
                if (StringUtil.isBlank(doctorSkill.getSpeName())) {
                    throw new Exception("导入失败！第" + (count + 2) + "行培训专业不能为空！");
                }
                if (StringUtil.isBlank(doctorSkill.getSpeId())) {
                    throw new Exception("导入失败！第" + (count + 2) + "行培训专业有误！");
                }
                doctorSkill.setCityId(cityId);
                doctorSkill.setDoctorSkillFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(doctorSkill, true);
                doctorSkillMapper.insert(doctorSkill);
                count++;
            }
        }
        return count;
    }
}
