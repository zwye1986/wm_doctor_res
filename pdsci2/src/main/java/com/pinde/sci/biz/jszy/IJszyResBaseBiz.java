package com.pinde.sci.biz.jszy;

import com.pinde.core.model.*;
import com.pinde.sci.form.jszy.JszyBaseInfoForm;
import com.pinde.sci.form.jszy.JszyCountryOrgExtInfoForm;
import com.pinde.core.model.JszyResBaseExt;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public interface IJszyResBaseBiz {

    /**
     * 保存
     *
     * @param resBase
     * @return
     */
    int saveResBase(ResBase resBase);

    /**
     * 保存基地信息
     *
     * @throws IOException
     * @throws JAXBException
     */
    int saveBaseInfo(String flag, JszyBaseInfoForm baseInfoForm, String index) throws Exception;

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
    List<JszyResBaseExt> searchResBaseExtList(Map<String, Object> paramMap);

    /**
     * 保存年份合格线配置
     * @param resPassScoreCfg
     * @return
     */
    int savePassScoreCfg(ResPassScoreCfg resPassScoreCfg);

    /**
     * 加载分数配置
     */
    ResPassScoreCfg readResPassScoreCfg(ResPassScoreCfg resPassScoreCfg);

    List<ResPassScoreCfg> readCfgs(ResPassScoreCfg resPassScoreCfg);
    /**
     * 删除分数配置信息
     * @param cfgYear
     * @return
     */
    int delScoreConf(String cfgYear);

    /**
     * 国家基地信息
     * @param orgFlow
     * @return
     */
    CountryOrgInfo queryCountryOrgInfo(String orgFlow);

    List<JszyCountryOrgExtInfoForm> parseXmlToBean(String content);

    String parseBeanToXml(List<JszyCountryOrgExtInfoForm> deptList);

    int saveCountryOrgInfo(CountryOrgInfo orgInfo);

    List<AttachedUnitInfo> queryUnitInfoList(String orgFlow);

    AttachedUnitInfo queryUnitInfoByFlow(String recordFlow);

    int saveJointOrgInfo(AttachedUnitInfo unitInfo);

    int delJointOrgInfo(String recordFlow);

    String uploadFile(String recordFlow,String unitTypeId,MultipartFile file);
}
