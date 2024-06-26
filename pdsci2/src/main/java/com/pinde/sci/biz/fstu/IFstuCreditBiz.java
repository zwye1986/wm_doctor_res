package com.pinde.sci.biz.fstu;

import com.pinde.sci.model.mo.FstuCredit;

import java.util.List;

public interface IFstuCreditBiz {
//	List<FstuCredit> searchByUserFlows(List<String> userFlow);

    /**
     * FstuCredit查询
     */
    List<FstuCredit> search(FstuCredit credit);

    /**
     * 编辑
     */
    int edit(FstuCredit credit);

    FstuCredit findByFlow(String flow);


    List<FstuCredit> searchCreditByUserFlow(String userFlow);

    int saveCreditList(List<FstuCredit> credit);
}
