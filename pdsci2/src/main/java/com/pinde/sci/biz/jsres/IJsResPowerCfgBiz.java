package com.pinde.sci.biz.jsres;

import com.pinde.core.model.JsresDeptConfig;
import com.pinde.core.model.JsresPowerCfg;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.core.model.ResOrgCkxz;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface IJsResPowerCfgBiz {

    int add(JsresPowerCfg cfg);

    int mod(JsresPowerCfg cfg);

    JsresPowerCfg read(String cfgCode);

    int saveList(List<JsresPowerCfg> cfgList);

    int save(JsresPowerCfg cfg);

    ExcelUtile importTime(MultipartFile file);

    void exportInfo(List<Map<String,Object>> list,List<String> docTypeList,HttpServletResponse response) throws IOException;

    int saveDeptConfig(JsresDeptConfig deptConfig);

    int saveCkxzConfig(ResOrgCkxz orgCkxz);

    List<JsresDeptConfig> searchDeptConfigs(String orgFlow);

    JsresDeptConfig searchDeptCfgByCfgFlow(String cfgFlow);

    JsresDeptConfig searchBaseDeptConfig(String orgFlow);

    JsresDeptConfig searchDeptCfg(String orgFlow, String schDeptFlow);

    int deleteCfg(String cfgCode);

    String saveCfgDoctorFlows(Map<String, String> param,List<String> doctorFlows);

    String saveCkshConfig(String[] mulDeptFlow);

    List<JsresPowerCfg> selectLikeCode(String code);
}
