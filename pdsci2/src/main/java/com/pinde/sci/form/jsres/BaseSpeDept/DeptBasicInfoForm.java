package com.pinde.sci.form.jsres.BaseSpeDept;

import java.io.Serializable;
import java.util.List;

public class DeptBasicInfoForm implements java.io.Serializable {

    private static final long serialVersionUID = -3081738391574662552L;

    /**
     * 科室信息
     */
    private String deptName;        //科室名称
    private String deptCode;        //科室代码
    private String deptRespName;    //科室负责人姓名
    private String deptRespPhone;    //科室负责人联系电话
    private String deptRespEmail;    //科室负责人邮箱
    private String deptDirName;        //科室主任姓名
    private String deptDirPhone;    //科室主任联系电话
    private String deptDirEmail;    //科室主任邮箱
    private String deptSceName;        //科室秘书姓名
    private String deptScePhone;    //科室秘书联系电话
    private String deptSceEmail;    //科室秘书邮箱

    /* 教学小组组长 */
    private String teachingGroupLeaderName;
    private String teachingGroupLeaderPhone;
    private String teachingGroupLeaderEmail;
    /* 教学小组成员 */
    private List<TeachingGroupMemberForm> teachingGroupMemberList;

    /**
     * 专业信息
     */
    private String speName;        //科室名称
    private String speCode;        //科室代码
    private String speRespName;    //科室负责人姓名
    private String speRespPhone;    //科室负责人联系电话
    private String speRespEmail;    //科室负责人邮箱
    private String speDirName;        //科室主任姓名
    private String speDirPhone;    //科室主任联系电话
    private String speDirEmail;    //科室主任邮箱
    private String speSceName;        //科室秘书姓名
    private String speScePhone;    //科室秘书联系电话
    private String speSceEmail;    //科室秘书邮箱

    private String hostType;        //医院类别
    private String bzzcws;            //编制总床位数
    private String syzcws;            //实有总床位数
    private String nszzybrs;        //年收治住院病人数
    private String bcsyl;            //病床使用率
    private String nmzl;            //年门诊量
    private String njzl;            //年急诊量
    private String bczzcs;            //病床周转次数
    private String pjzyr;            //平均住院日
    private String ncybrs;            //年出院病人数
    private String njzscls;            //年急诊手术例数
    private String npxzrl;            //3年培训总容量
    private String sypxrl;            //剩余培训容量
    private String cknfml;             //产科年分娩量
    private String fknszbrs;            //妇科年收治病人数
    private String cknszbrs;            //产科年收治病人数
    private String nmzzszmk;            //年麻醉总数
    private String mzhfsbrs;            //麻醉恢复室病人数
    private String ttmzbrs;             //疼痛门诊病人数
    private String zzjhsszbrs;          //重症监护室收治病人数
    private String nhjbbbls;            //年活检标本病例数
    private String nstjpbls;            //年尸体解剖病例数
    private String nbdkszdl;            //年冰冻快速诊断量
    private String nxbxjcbls;           //年细胞学检查病例数

    private String annualDiseases;  // 本年总病例病种数（个）
    private String annualDiseaseCategory; // 本年收治总疾病（种）
    private String annualDiseaseNumber; // 本年收治总疾病（个）
    private String teachingClinic; // 教学门诊
    private String threeYearTrainingCount; // 近三年培训人数总计（人）
    private String threeYearExamPassPer; // 近三年理论首考平均通过率（%）

    private String bksyw;            //本科生有无
    private String bksrs;            //本科生人数
    private String yjsyw;            //研究生（硕、博）有无
    private String yjsrs;            //研究生（硕、博）人数
    private String zyysyw;            //住院医师有无
    private String zyysrs;            //住院医师人数
    private String jxysyw;            //进修医师有无
    private String jxysrs;            //进修医师人数
    private String cdjjyx;            //承担继续医学教育情况

    private String td;                //特等
    private String yd;                //一等
    private String ed;                //二等
    private String sd;                //三等

    private String gjlczdzk;        //国家临床重点专科
    private String sszdjsxk;        //省重点建设学科
    // 市重点建设学科
    private String cityZdjsxk;
    private String xwpyd;            //学位培养点
    private String masterxw;        //硕士
    private List<MasterSchlAndSpeForm> masterSchlAndSpe; //硕士 学校和专业
    private String drxw;            //博士
    private List<DocSchlAndSpeForm> docSchlAndSpe;//博士学校专业

    private String kzzyys;            //开展住院医师规范化培训工作年限
    private String ljjyrs;            //累计结业人数

    public String getAnnualDiseases() {
        return annualDiseases;
    }

    public void setAnnualDiseases(String annualDiseases) {
        this.annualDiseases = annualDiseases;
    }

    public String getAnnualDiseaseCategory() {
        return annualDiseaseCategory;
    }

    public void setAnnualDiseaseCategory(String annualDiseaseCategory) {
        this.annualDiseaseCategory = annualDiseaseCategory;
    }

    public String getAnnualDiseaseNumber() {
        return annualDiseaseNumber;
    }

    public void setAnnualDiseaseNumber(String annualDiseaseNumber) {
        this.annualDiseaseNumber = annualDiseaseNumber;
    }

    public String getTeachingClinic() {
        return teachingClinic;
    }

    public void setTeachingClinic(String teachingClinic) {
        this.teachingClinic = teachingClinic;
    }

    public String getThreeYearTrainingCount() {
        return threeYearTrainingCount;
    }

    public void setThreeYearTrainingCount(String threeYearTrainingCount) {
        this.threeYearTrainingCount = threeYearTrainingCount;
    }

    public String getThreeYearExamPassPer() {
        return threeYearExamPassPer;
    }

    public void setThreeYearExamPassPer(String threeYearExamPassPer) {
        this.threeYearExamPassPer = threeYearExamPassPer;
    }

    public List<TeachingGroupMemberForm> getTeachingGroupMemberList() {
        return teachingGroupMemberList;
    }

    public void setTeachingGroupMemberList(List<TeachingGroupMemberForm> teachingGroupMemberList) {
        this.teachingGroupMemberList = teachingGroupMemberList;
    }

    public String getTeachingGroupLeaderName() {
        return teachingGroupLeaderName;
    }

    public void setTeachingGroupLeaderName(String teachingGroupLeaderName) {
        this.teachingGroupLeaderName = teachingGroupLeaderName;
    }

    public String getTeachingGroupLeaderPhone() {
        return teachingGroupLeaderPhone;
    }

    public void setTeachingGroupLeaderPhone(String teachingGroupLeaderPhone) {
        this.teachingGroupLeaderPhone = teachingGroupLeaderPhone;
    }

    public String getTeachingGroupLeaderEmail() {
        return teachingGroupLeaderEmail;
    }

    public void setTeachingGroupLeaderEmail(String teachingGroupLeaderEmail) {
        this.teachingGroupLeaderEmail = teachingGroupLeaderEmail;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptRespName() {
        return deptRespName;
    }

    public void setDeptRespName(String deptRespName) {
        this.deptRespName = deptRespName;
    }

    public String getDeptRespPhone() {
        return deptRespPhone;
    }

    public void setDeptRespPhone(String deptRespPhone) {
        this.deptRespPhone = deptRespPhone;
    }

    public String getDeptRespEmail() {
        return deptRespEmail;
    }

    public void setDeptRespEmail(String deptRespEmail) {
        this.deptRespEmail = deptRespEmail;
    }

    public String getDeptDirName() {
        return deptDirName;
    }

    public void setDeptDirName(String deptDirName) {
        this.deptDirName = deptDirName;
    }

    public String getDeptDirPhone() {
        return deptDirPhone;
    }

    public void setDeptDirPhone(String deptDirPhone) {
        this.deptDirPhone = deptDirPhone;
    }

    public String getDeptDirEmail() {
        return deptDirEmail;
    }

    public void setDeptDirEmail(String deptDirEmail) {
        this.deptDirEmail = deptDirEmail;
    }

    public String getDeptSceName() {
        return deptSceName;
    }

    public void setDeptSceName(String deptSceName) {
        this.deptSceName = deptSceName;
    }

    public String getDeptScePhone() {
        return deptScePhone;
    }

    public void setDeptScePhone(String deptScePhone) {
        this.deptScePhone = deptScePhone;
    }

    public String getDeptSceEmail() {
        return deptSceEmail;
    }

    public void setDeptSceEmail(String deptSceEmail) {
        this.deptSceEmail = deptSceEmail;
    }

    public String getSpeName() {
        return speName;
    }

    public void setSpeName(String speName) {
        this.speName = speName;
    }

    public String getSpeCode() {
        return speCode;
    }

    public void setSpeCode(String speCode) {
        this.speCode = speCode;
    }

    public String getSpeRespName() {
        return speRespName;
    }

    public void setSpeRespName(String speRespName) {
        this.speRespName = speRespName;
    }

    public String getSpeRespPhone() {
        return speRespPhone;
    }

    public void setSpeRespPhone(String speRespPhone) {
        this.speRespPhone = speRespPhone;
    }

    public String getSpeRespEmail() {
        return speRespEmail;
    }

    public void setSpeRespEmail(String speRespEmail) {
        this.speRespEmail = speRespEmail;
    }

    public String getSpeDirName() {
        return speDirName;
    }

    public void setSpeDirName(String speDirName) {
        this.speDirName = speDirName;
    }

    public String getSpeDirPhone() {
        return speDirPhone;
    }

    public void setSpeDirPhone(String speDirPhone) {
        this.speDirPhone = speDirPhone;
    }

    public String getSpeDirEmail() {
        return speDirEmail;
    }

    public void setSpeDirEmail(String speDirEmail) {
        this.speDirEmail = speDirEmail;
    }

    public String getSpeSceName() {
        return speSceName;
    }

    public void setSpeSceName(String speSceName) {
        this.speSceName = speSceName;
    }

    public String getSpeScePhone() {
        return speScePhone;
    }

    public void setSpeScePhone(String speScePhone) {
        this.speScePhone = speScePhone;
    }

    public String getSpeSceEmail() {
        return speSceEmail;
    }

    public void setSpeSceEmail(String speSceEmail) {
        this.speSceEmail = speSceEmail;
    }

    public String getHostType() {
        return hostType;
    }

    public void setHostType(String hostType) {
        this.hostType = hostType;
    }

    public String getBzzcws() {
        return bzzcws;
    }

    public void setBzzcws(String bzzcws) {
        this.bzzcws = bzzcws;
    }

    public String getSyzcws() {
        return syzcws;
    }

    public void setSyzcws(String syzcws) {
        this.syzcws = syzcws;
    }

    public String getNszzybrs() {
        return nszzybrs;
    }

    public void setNszzybrs(String nszzybrs) {
        this.nszzybrs = nszzybrs;
    }

    public String getBcsyl() {
        return bcsyl;
    }

    public void setBcsyl(String bcsyl) {
        this.bcsyl = bcsyl;
    }

    public String getNmzl() {
        return nmzl;
    }

    public void setNmzl(String nmzl) {
        this.nmzl = nmzl;
    }

    public String getNjzl() {
        return njzl;
    }

    public void setNjzl(String njzl) {
        this.njzl = njzl;
    }

    public String getBczzcs() {
        return bczzcs;
    }

    public void setBczzcs(String bczzcs) {
        this.bczzcs = bczzcs;
    }

    public String getPjzyr() {
        return pjzyr;
    }

    public void setPjzyr(String pjzyr) {
        this.pjzyr = pjzyr;
    }

    public String getNcybrs() {
        return ncybrs;
    }

    public void setNcybrs(String ncybrs) {
        this.ncybrs = ncybrs;
    }

    public String getNjzscls() {
        return njzscls;
    }

    public void setNjzscls(String njzscls) {
        this.njzscls = njzscls;
    }

    public String getNpxzrl() {
        return npxzrl;
    }

    public void setNpxzrl(String npxzrl) {
        this.npxzrl = npxzrl;
    }

    public String getSypxrl() {
        return sypxrl;
    }

    public void setSypxrl(String sypxrl) {
        this.sypxrl = sypxrl;
    }

    public String getBksyw() {
        return bksyw;
    }

    public void setBksyw(String bksyw) {
        this.bksyw = bksyw;
    }

    public String getBksrs() {
        return bksrs;
    }

    public void setBksrs(String bksrs) {
        this.bksrs = bksrs;
    }

    public String getYjsyw() {
        return yjsyw;
    }

    public void setYjsyw(String yjsyw) {
        this.yjsyw = yjsyw;
    }

    public String getYjsrs() {
        return yjsrs;
    }

    public void setYjsrs(String yjsrs) {
        this.yjsrs = yjsrs;
    }

    public String getZyysyw() {
        return zyysyw;
    }

    public void setZyysyw(String zyysyw) {
        this.zyysyw = zyysyw;
    }

    public String getZyysrs() {
        return zyysrs;
    }

    public void setZyysrs(String zyysrs) {
        this.zyysrs = zyysrs;
    }

    public String getJxysyw() {
        return jxysyw;
    }

    public void setJxysyw(String jxysyw) {
        this.jxysyw = jxysyw;
    }

    public String getJxysrs() {
        return jxysrs;
    }

    public void setJxysrs(String jxysrs) {
        this.jxysrs = jxysrs;
    }

    public String getCdjjyx() {
        return cdjjyx;
    }

    public void setCdjjyx(String cdjjyx) {
        this.cdjjyx = cdjjyx;
    }

    public String getTd() {
        return td;
    }

    public void setTd(String td) {
        this.td = td;
    }

    public String getYd() {
        return yd;
    }

    public void setYd(String yd) {
        this.yd = yd;
    }

    public String getEd() {
        return ed;
    }

    public void setEd(String ed) {
        this.ed = ed;
    }

    public String getSd() {
        return sd;
    }

    public void setSd(String sd) {
        this.sd = sd;
    }

    public String getGjlczdzk() {
        return gjlczdzk;
    }

    public void setGjlczdzk(String gjlczdzk) {
        this.gjlczdzk = gjlczdzk;
    }

    public String getSszdjsxk() {
        return sszdjsxk;
    }

    public void setSszdjsxk(String sszdjsxk) {
        this.sszdjsxk = sszdjsxk;
    }

    public String getCityZdjsxk() {
        return cityZdjsxk;
    }

    public void setCityZdjsxk(String cityZdjsxk) {
        this.cityZdjsxk = cityZdjsxk;
    }

    public String getXwpyd() {
        return xwpyd;
    }

    public void setXwpyd(String xwpyd) {
        this.xwpyd = xwpyd;
    }

    public String getMasterxw() {
        return masterxw;
    }

    public void setMasterxw(String masterxw) {
        this.masterxw = masterxw;
    }

    public List<MasterSchlAndSpeForm> getMasterSchlAndSpe() {
        return masterSchlAndSpe;
    }

    public void setMasterSchlAndSpe(List<MasterSchlAndSpeForm> masterSchlAndSpe) {
        this.masterSchlAndSpe = masterSchlAndSpe;
    }

    public String getDrxw() {
        return drxw;
    }

    public void setDrxw(String drxw) {
        this.drxw = drxw;
    }

    public List<DocSchlAndSpeForm> getDocSchlAndSpe() {
        return docSchlAndSpe;
    }

    public void setDocSchlAndSpe(List<DocSchlAndSpeForm> docSchlAndSpe) {
        this.docSchlAndSpe = docSchlAndSpe;
    }

    public String getKzzyys() {
        return kzzyys;
    }

    public void setKzzyys(String kzzyys) {
        this.kzzyys = kzzyys;
    }

    public String getLjjyrs() {
        return ljjyrs;
    }

    public void setLjjyrs(String ljjyrs) {
        this.ljjyrs = ljjyrs;
    }

    public String getCknfml() {
        return cknfml;
    }

    public void setCknfml(String cknfml) {
        this.cknfml = cknfml;
    }

    public String getFknszbrs() {
        return fknszbrs;
    }

    public void setFknszbrs(String fknszbrs) {
        this.fknszbrs = fknszbrs;
    }

    public String getCknszbrs() {
        return cknszbrs;
    }

    public void setCknszbrs(String cknszbrs) {
        this.cknszbrs = cknszbrs;
    }

    public String getNmzzszmk() {
        return nmzzszmk;
    }

    public void setNmzzszmk(String nmzzszmk) {
        this.nmzzszmk = nmzzszmk;
    }

    public String getMzhfsbrs() {
        return mzhfsbrs;
    }

    public void setMzhfsbrs(String mzhfsbrs) {
        this.mzhfsbrs = mzhfsbrs;
    }

    public String getTtmzbrs() {
        return ttmzbrs;
    }

    public void setTtmzbrs(String ttmzbrs) {
        this.ttmzbrs = ttmzbrs;
    }

    public String getZzjhsszbrs() {
        return zzjhsszbrs;
    }

    public void setZzjhsszbrs(String zzjhsszbrs) {
        this.zzjhsszbrs = zzjhsszbrs;
    }

    public String getNhjbbbls() {
        return nhjbbbls;
    }

    public void setNhjbbbls(String nhjbbbls) {
        this.nhjbbbls = nhjbbbls;
    }

    public String getNstjpbls() {
        return nstjpbls;
    }

    public void setNstjpbls(String nstjpbls) {
        this.nstjpbls = nstjpbls;
    }

    public String getNbdkszdl() {
        return nbdkszdl;
    }

    public void setNbdkszdl(String nbdkszdl) {
        this.nbdkszdl = nbdkszdl;
    }

    public String getNxbxjcbls() {
        return nxbxjcbls;
    }

    public void setNxbxjcbls(String nxbxjcbls) {
        this.nxbxjcbls = nxbxjcbls;
    }
}
