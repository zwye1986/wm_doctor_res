package com.pinde.sci.ctrl.jsres;

import com.pinde.core.common.GlobalConstant;
import com.pinde.core.common.enums.OrgLevelEnum;
import com.pinde.core.common.enums.OrgTypeEnum;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.jsres.IResDoctorArchiveBiz;
import com.pinde.sci.biz.res.IResJointOrgBiz;
import com.pinde.sci.biz.sys.impl.OrgBizImpl;
import com.pinde.sci.common.GeneralController;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.model.mo.ResArchiveSequence;
import com.pinde.sci.model.mo.ResJointOrg;
import com.pinde.sci.model.mo.SysOrg;
import com.pinde.sci.model.mo.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/jsres/baseinfo")
public class JsResBaseController  extends GeneralController {

    @Autowired
    private OrgBizImpl orgBiz;
    @Autowired
    private IResJointOrgBiz jointOrgBiz;
    @Autowired
    private IResDoctorArchiveBiz archiveBiz;

    /**
     * 查詢学员信息
     * @param model
     * @param roleFlag
     * @param isArchive
     * @param datas
     * @param userName
     * @param isBack
     * @param orgCityId
     * @param yearStr
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value="/provinceDoctorList")
    public String provinceDoctorList(Model model,String roleFlag,String isArchive,String []datas,String userName,
                                     String isBack,String orgCityId,String yearStr) throws UnsupportedEncodingException {

        SysUser sysuser=GlobalContext.getCurrentUser();
        SysOrg org=orgBiz.readSysOrg(sysuser.getOrgFlow());
        model.addAttribute("org",org);
        SysOrg sysorg =new  SysOrg();
        sysorg.setOrgProvId(org.getOrgProvId());
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_CHARGE)) {
            sysorg.setOrgCityId(org.getOrgCityId());
        }
        sysorg.setOrgTypeId(com.pinde.core.common.enums.OrgTypeEnum.Hospital.getId());
        sysorg.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        if(StringUtil.isNotBlank(orgCityId)){
            sysorg.setOrgCityId(orgCityId);
        }
        List<SysOrg> orgs=orgBiz.searchOrg(sysorg);
        sysorg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId());
        List<SysOrg>  countryList=orgBiz.searchOrg(sysorg);
        sysorg.setOrgLevelId(com.pinde.core.common.enums.OrgLevelEnum.ProvinceOrg.getId());
        List<SysOrg>  provinceList=orgBiz.searchOrg(sysorg);

        List<ResJointOrg> jointOrgs=jointOrgBiz.searchJointOrgAll();
        List<String> orgFlowList=new ArrayList<String>();
        if(jointOrgs!=null&&!jointOrgs.isEmpty()){
            for(ResJointOrg jointOrg:jointOrgs){
                orgFlowList.add(jointOrg.getOrgFlow());
            }
        }
        model.addAttribute("orgFlowList", orgFlowList);
        model.addAttribute("countryList", countryList);
        model.addAttribute("provinceList", provinceList);
        if (getSessionAttribute(com.pinde.core.common.GlobalConstant.USER_LIST_SCOPE).equals(com.pinde.core.common.GlobalConstant.USER_LIST_LOCAL)) {
            if (com.pinde.core.common.enums.OrgLevelEnum.CountryOrg.getId().equals(org.getOrgLevelId()))
            {
                orgs.clear();
                List<SysOrg> joinOrgs=orgBiz.searchJointOrgsByOrg(org.getOrgFlow());
                orgs.addAll(joinOrgs);
                orgs.add(org);
            }
        }
        model.addAttribute("orgs", orgs);
        model.addAttribute("roleFlag", roleFlag);
        List<ResJointOrg> joinOrgs = jointOrgBiz.searchResJointByJointOrgFlow(sysuser.getOrgFlow());
        //是协同基地
        if(!joinOrgs.isEmpty()&&joinOrgs.size()>0){
            model.addAttribute("isJointOrg","1");
        }
        model.addAttribute("datas",datas);
        if (com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isArchive)) {
            List<ResArchiveSequence> archiveSequenceList = archiveBiz.allResArchiveSequence();
            model.addAttribute("archiveSequenceList",archiveSequenceList);
            return  "jsres/archiveDoctorList";
        }
        if (StringUtil.isNotBlank(userName) && com.pinde.core.common.GlobalConstant.FLAG_Y.equals(isBack)) {
            userName = java.net.URLDecoder.decode(userName,"UTF-8");
            model.addAttribute("isBack",isBack);
        }
        model.addAttribute("userName",userName);
        model.addAttribute("yearDatas",StringUtil.isNotBlank(yearStr)?yearStr.split(","):null);
        return  "jsres/doctorList";
    }
}
