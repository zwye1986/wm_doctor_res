package com.pinde.sci.biz.res;


import com.pinde.core.model.ResPowerCfg;
import com.pinde.sci.common.util.ExcelUtile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IResPowerCfgBiz {
    /**
     * 根据主键查找
     * @param cfgCode
     * @return
     */
    ResPowerCfg read(String cfgCode);

    List<ResPowerCfg> searchByCfgCode(String cfgCode);

    int updateDate(ResPowerCfg powerCfg);

    int saveList(List<ResPowerCfg> cfgList);

    int add(ResPowerCfg cfg);

    int mod(ResPowerCfg cfg);

    ExcelUtile importPowerFromExcel(MultipartFile file);
}
