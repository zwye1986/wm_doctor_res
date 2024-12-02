package com.pinde.sci.biz.res.impl;


import com.pinde.core.common.GlobalConstant;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResDoctorBiz;
import com.pinde.sci.biz.res.IResIdCtrlBiz;
import com.pinde.sci.biz.res.IResPowerCfgBiz;
import com.pinde.sci.biz.sys.IOrgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResIdctrlDetailMapper;
import com.pinde.sci.dao.base.ResIdctrlMainMapper;
import com.pinde.sci.dao.res.ResIdCtrlExtMapper;
import com.pinde.sci.model.mo.*;
import net.sourceforge.pinyin4j.PinyinHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class ResIdCtrlBizImpl implements IResIdCtrlBiz {
    @Autowired
    private ResIdctrlMainMapper idctrlMainMapper;
    @Autowired
    private ResIdctrlDetailMapper idctrlDetailMapper;
    @Autowired
    private IOrgBiz sysOrgBiz;
    @Autowired
    private ResIdCtrlExtMapper idCtrlExtMapper;
    @Autowired
    private IResDoctorBiz doctorBiz;
    @Autowired
    private IResPowerCfgBiz powerCfgBiz;

    @Override
    public List<ResIdctrlMain> searchMain(ResIdctrlMain idctrlMain) {
        ResIdctrlMainExample example = new ResIdctrlMainExample();
        ResIdctrlMainExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(idctrlMain.getOperUserName())){
            criteria.andOperUserNameLike("%"+idctrlMain.getOperUserName()+"%");
        }
        if(StringUtil.isNotBlank(idctrlMain.getOrgName())){
            criteria.andOrgNameLike("%"+idctrlMain.getOrgName()+"%");
        }
        example.setOrderByClause("CREATE_TIME DESC");
        return idctrlMainMapper.selectByExample(example);
    }

    @Override
    public List<ResIdctrlDetail> searchDetail(ResIdctrlDetail idctrlDetail) {
        ResIdctrlDetailExample example = new ResIdctrlDetailExample();
        ResIdctrlDetailExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(idctrlDetail!=null){
            if(StringUtil.isNotBlank(idctrlDetail.getIdctrlMainFlow())){
                criteria.andIdctrlMainFlowEqualTo(idctrlDetail.getIdctrlMainFlow());
            }
            if(StringUtil.isNotBlank(idctrlDetail.getOrgFlow())){
                criteria.andOrgFlowEqualTo(idctrlDetail.getOrgFlow());
            }
            if("NoBind".equals(idctrlDetail.getIsBinding())){
                criteria.andIsBindingIsNull();
            }
        }
        example.setOrderByClause("IS_BINDING DESC,ID");
        return idctrlDetailMapper.selectByExample(example);
    }

    @Override
    public int saveIds(ResIdctrlMain resIdctrlMain) {
        if(StringUtil.isNotBlank(resIdctrlMain.getOrgFlow())&&StringUtil.isNotBlank(resIdctrlMain.getIdNumber())){
            SysUser currentUser = GlobalContext.getCurrentUser();
            resIdctrlMain.setOperUserFlow(currentUser.getUserFlow());
            resIdctrlMain.setOperUserName(currentUser.getUserName());
            String mainRecordFlow = PkUtil.getUUID();
            resIdctrlMain.setRecordFlow(mainRecordFlow);
            GeneralMethod.setRecordInfo(resIdctrlMain,true);
            idctrlMainMapper.insertSelective(resIdctrlMain);

            SysOrg org = sysOrgBiz.readSysOrg(resIdctrlMain.getOrgFlow());
            String provinceName = org.getOrgProvName();
            String idPart1 = "WWW";
            if(StringUtil.isNotBlank(provinceName)){
                String pinyin = getPinYinHeadChar(provinceName).toUpperCase();
                idPart1 = pinyin.substring(0,pinyin.length()-1);
                if(idPart1.length()<3){
                    idPart1 = "0"+idPart1;
                }
            }
            String idPart2 = "01";

            List<ResIdctrlDetail> idctrlDetails = searchDetail(null);
            List<Integer> idOrders = new ArrayList<>();
            int max = 0;
            if(idctrlDetails!=null&&idctrlDetails.size()>0){
                for(ResIdctrlDetail idctrlDetail:idctrlDetails){
                    String id = idctrlDetail.getId();
                    Integer idOrder = Integer.parseInt(id.split("ID")[1]);
                    idOrders.add(idOrder);
                }
                max = Collections.max(idOrders);
            }

            String idNumber = resIdctrlMain.getIdNumber();
            int idNum = Integer.parseInt(idNumber);
            DecimalFormat df = new DecimalFormat("0000000000");
            for(int i=0;i<idNum;i++){
                String idPart3 = df.format(max+1+i);
                String id = idPart1+idPart2+"ID"+idPart3;
                ResIdctrlDetail saveIdctrlDetail = new ResIdctrlDetail();
                saveIdctrlDetail.setRecordFlow(PkUtil.getUUID());
                saveIdctrlDetail.setIdctrlMainFlow(mainRecordFlow);
                saveIdctrlDetail.setId(id);
                saveIdctrlDetail.setOrgFlow(resIdctrlMain.getOrgFlow());
                saveIdctrlDetail.setOrgName(resIdctrlMain.getOrgName());
                GeneralMethod.setRecordInfo(saveIdctrlDetail,true);
                idctrlDetailMapper.insertSelective(saveIdctrlDetail);
            }
            return 1;
        }
        return 0;
    }

    // 返回中文的首字母
    String getPinYinHeadChar(String str) {
        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }

    @Override
    public int editId(ResIdctrlDetail idctrlDetail) {
        if(StringUtil.isNotBlank(idctrlDetail.getRecordFlow())){
            GeneralMethod.setRecordInfo(idctrlDetail,false);
            if (idctrlDetail.getRecordStatus() != null && idctrlDetail.getRecordStatus().equals(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N)) {
                ResIdctrlDetail detail = readDetail(idctrlDetail.getRecordFlow());
                ResIdctrlMain main = idctrlMainMapper.selectByPrimaryKey(detail.getIdctrlMainFlow());
                String idNumber = main.getIdNumber();
                String newNumber = String.valueOf(Integer.parseInt(idNumber)-1);
                main.setIdNumber(newNumber);
                idctrlMainMapper.updateByPrimaryKeySelective(main);
            }
            return idctrlDetailMapper.updateByPrimaryKeySelective(idctrlDetail);
        }else {
            String recordFlow = PkUtil.getUUID();
            idctrlDetail.setRecordFlow(recordFlow);
            GeneralMethod.setRecordInfo(idctrlDetail,true);
            return idctrlDetailMapper.insertSelective(idctrlDetail);
        }
    }

    @Override
    public ResIdctrlDetail readDetail(String recordFlow) {
        return idctrlDetailMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public List<Map<String, Object>> doctorIdList(Map<String, Object> paramMap) {
        return idCtrlExtMapper.doctorIdList(paramMap);
    }

    @Override
    public int bindingID(ResIdctrlDetail detail) {
        //ResIdctrlDetail表数据
        ResIdctrlDetail oldDetail = readDetail(detail.getRecordFlow());
        if (oldDetail != null && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(oldDetail.getIsBinding())) {
            return -1;
        }
        detail.setIsBinding(com.pinde.core.common.GlobalConstant.FLAG_Y);
        String endDate = detail.getEndDate();
        if(StringUtil.isNotBlank(endDate)){
            detail.setStartDate(endDate);
            int year = Integer.parseInt(endDate.split("-")[0])+1;
            detail.setEndDate(year+"-"+endDate.split("-")[1]+"-"+endDate.split("-")[2]);
        }else {
            ResDoctor doctor = doctorBiz.readDoctor(detail.getDoctorFlow());
            String sessionNumber = doctor.getSessionNumber();
            if(StringUtil.isBlank(sessionNumber)){
                return -2;
            }else {
                detail.setStartDate(sessionNumber+"-09-01");
                detail.setEndDate(Integer.parseInt(sessionNumber)+1+"-09-01");
            }
        }
        editId(detail);
        //ResPowerfg表数据
        String webCfgCode = "res_doctor_web_"+detail.getDoctorFlow();
        ResPowerCfg webCfg = powerCfgBiz.read(webCfgCode);
        if(webCfg!=null){
            webCfg.setCfgValue(com.pinde.core.common.GlobalConstant.FLAG_Y);
            if(StringUtil.isBlank(webCfg.getPowerStartTime())){
                webCfg.setPowerStartTime(detail.getStartDate());
            }
            webCfg.setPowerEndTime(detail.getEndDate());
            powerCfgBiz.mod(webCfg);
        }else{
            ResPowerCfg saveCfg = new ResPowerCfg();
            saveCfg.setCfgCode(webCfgCode);
            saveCfg.setCfgValue(com.pinde.core.common.GlobalConstant.FLAG_Y);
            saveCfg.setPowerStartTime(detail.getStartDate());
            saveCfg.setPowerEndTime(detail.getEndDate());
            saveCfg.setCfgDesc("是否开放学员web登录权限");
            powerCfgBiz.add(saveCfg);
        }
        String appCfgCode = "res_doctor_app_login_"+detail.getDoctorFlow();
        ResPowerCfg appCfg = powerCfgBiz.read(appCfgCode);
        if(appCfg!=null){
            appCfg.setCfgValue(com.pinde.core.common.GlobalConstant.FLAG_Y);
            if(StringUtil.isBlank(appCfg.getPowerStartTime())){
                appCfg.setPowerStartTime(detail.getStartDate());
            }
            appCfg.setPowerEndTime(detail.getEndDate());
            powerCfgBiz.mod(appCfg);
        }else{
            ResPowerCfg saveCfg = new ResPowerCfg();
            saveCfg.setCfgCode(appCfgCode);
            saveCfg.setCfgValue(com.pinde.core.common.GlobalConstant.FLAG_Y);
            saveCfg.setPowerStartTime(detail.getStartDate());
            saveCfg.setPowerEndTime(detail.getEndDate());
            saveCfg.setCfgDesc("是否开放学员app登录权限");
            powerCfgBiz.add(saveCfg);
        }
        return 1;
    }
}
