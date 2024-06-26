package com.pinde.sci.biz.nyzl;


import com.pinde.sci.model.mo.NyzlVacancySwap;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface INyzlVacancySwapBiz {
    //查询缺额调剂信息
    List<NyzlVacancySwap> searchVacancySwapList(NyzlVacancySwap vacancySwap);
    //保存缺额调剂信息
    int save(NyzlVacancySwap vacancySwap);
    //复试学员信息导入操作
    int importVacancySwap(MultipartFile file, String stuSignFlag);

}
