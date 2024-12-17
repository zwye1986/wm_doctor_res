package com.pinde.sci.biz.sys;

import com.pinde.core.model.SysCfg;
import com.pinde.core.model.SysCfgExample;
import com.pinde.core.model.SysCfgLogWithBLOBs;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface ICfgBiz {

    SysCfg read(String cfgCode);

    List<SysCfg> readByExample(SysCfgExample example);

    int add(SysCfg cfg);

    int mod(SysCfg cfg);

    void save(List<SysCfg> sysCfgList);

    List<SysCfg> search(SysCfg cfg);
//	
//	public int addSysCfg(String [] cfgCodes);

    String getPageSize(HttpServletRequest request);

    void savePageSize(HttpServletRequest request);


    int saveSysCfg(SysCfg start, SysCfg end);

    int saveSysCfgInfo(List<SysCfg> sysCfgList);
//	List<SysCfg> searchCfgByCfgCodeList(SysCfg sysCfg,List<String> cfgCodeList);


    /**
     * 查询该开关使用人数
     * @param paramMap
     * @return
     */
    int getOpenSwitchCount(Map<String, Object> paramMap);

    /**
     * 根据前后缀规则读取配置信息
     * @param paramMap
     * @return
     */
    List<SysCfg> searchByPreAndSuf(Map<String, Object> paramMap);

    int saveList(List<SysCfg> sysCfgList, HttpServletRequest request);

    void saveLog(List<SysCfgLogWithBLOBs> sysCfgLogList);

    void addCfgLog(SysCfgLogWithBLOBs sysCfg);
}
