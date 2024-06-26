package com.pinde.sci.biz.fstu;


import com.pinde.sci.form.pub.PubFileForm;
import com.pinde.sci.model.mo.PubImpactorFactor;

import java.util.List;

public interface IFstuImpactorFactorBiz {

    /**
     * 导入Excel文件
     * @param fileForm
     * @param factor
     */
    void importExcel(PubFileForm fileForm, PubImpactorFactor factor);

    /**
     * 查询
     * @param factor
     * @return
     */
    List<PubImpactorFactor> queryImpactorFactorList(PubImpactorFactor factor);

    /**
     * 根据ISSN号查询最新的影响因子
     * @param issn
     * @return
     */
    List<PubImpactorFactor> getImpactorFactorByISSN(String issn);


}
