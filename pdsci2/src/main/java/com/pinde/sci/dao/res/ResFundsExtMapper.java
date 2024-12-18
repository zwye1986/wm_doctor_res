package com.pinde.sci.dao.res;

import com.pinde.core.model.ResBaseFundDetail;
import com.pinde.core.model.ResProvinceFundDetail;
import com.pinde.core.model.ResSyntheticalFundDetail;
import com.pinde.core.model.SysOrg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 经费管理
 */
public interface ResFundsExtMapper {

    List<ResBaseFundDetail> resBaseFundList(Map<String, Object> paramMap);

    /**
     * 综合费用详情
     * @param paramMap
     * @return
     */
    List<ResSyntheticalFundDetail> resResSyntheticalFundDetailList(HashMap<String, Object> paramMap);

    List<SysOrg> searchOrgs(Map<String, Object> paramMap);

    List<ResProvinceFundDetail> queryFundDetaiList();

    List<ResProvinceFundDetail> searchResProvinceFundDetailMoney(Map<String, Object> param);}
