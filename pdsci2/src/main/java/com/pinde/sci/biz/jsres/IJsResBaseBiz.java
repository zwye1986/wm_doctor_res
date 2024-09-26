package com.pinde.sci.biz.jsres;

import com.pinde.sci.form.jsres.BaseExtInfo;
import com.pinde.sci.form.jsres.BaseInfoForm;
import com.pinde.sci.model.jsres.ResBaseExt;
import com.pinde.sci.model.mo.ResBase;
import com.pinde.sci.model.mo.ResOrgSpe;
import com.pinde.sci.model.mo.ResPassScoreCfg;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IJsResBaseBiz {

    /**
     * 删除分数配置信息
     * @param cfgYear
     * @return
     */
     int delScoreConf(String cfgYear);

    /**
     * 保存
     *
     * @param resBase
     * @return
     */
    int saveResBase(ResBase resBase);

    /**
     * 保存年份合格线配置
     * @param resPassScoreCfg
     * @return
     */
    int savePassScoreCfg(ResPassScoreCfg resPassScoreCfg);

    /**
     * 加载分数配置
     * @param cfgYear
     * @return
     */
    ResPassScoreCfg readResPassScoreCfg(ResPassScoreCfg resPassScoreCfg);

    List<ResPassScoreCfg> readCfgs(ResPassScoreCfg resPassScoreCfg);

    /**
     * 保存基地信息
     *
     * @throws IOException
     * @throws JAXBException
     */
    int saveBaseInfo(String flag, BaseInfoForm baseInfoForm, String index, String type, String[] fileFlows, HttpServletRequest request,
                     String[] jointOrgFlows, String[] speIds, String[] fileUploadNum, String[] jointContractFileFlows, String[] fileRemainNum,
                     BaseExtInfo baseExtInfo) throws Exception;

    /**
     * 保存专业信息
     *
     * @param orgName
     */
    int saveTrainSpe(List<ResOrgSpe> saveList, String oldCategory, String orgFlow, String orgName);

    /**
     * 查找基地
     */
    ResBase readBase(String orgFlow);

    /**
     * 查找扩展的基地信息
     *
     * @param paramMap
     * @return
     */
    List<ResBaseExt> searchResBaseExtList(Map<String, Object> paramMap);
    /**
     * 查找基地List
     * @param resBase
     * @return
     */
//	public List<ResBase> searchResBaseList(ResBase resBase);

	/**
     * 绑定住培学员角色
     * @param userFlow
     */
    void bindDoctorRole(String userFlow);

    List<ResBase> readBaseList(List<String> orgFlows);

    List<ResBase> readBaseListByYear(List<String> orgFlows, String sessionNumber);

    Map<String,ResBase> searchAll();

    ResBase readBaseBySessionNumber(String baseFlow, String sessionNumber);
}
