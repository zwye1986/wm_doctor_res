package com.pinde.sci.biz.res;




import com.pinde.sci.model.mo.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface IFundsBiz {
    // 通过主键查询省厅收支
    ResProvinceFund readResProvinceFund(String recordFlow );
    //编辑省厅收支
    int editResProvinceFund(ResProvinceFund resProvinceFund);
    //查询省厅收支
    List<ResProvinceFund> searchResProvinceFund(String startDate,String endDate);

    // 通过主键查询省厅收支明细

    //编辑单条省厅收支详情
    int editResProvinceFundDetail(ResProvinceFundDetail resProvinceFundDetail);
    //查询省厅收支详情
    List<ResProvinceFundDetail> searchResProvinceFundDetail(ResProvinceFundDetail resProvinceFundDetail);
    //查询省厅收支详情（资金来源）
    List<ResProvinceFundDetail> searchResProvinceFundDetailMoney(Map<String, Object> paramMap);
    //保存多条省厅收支详情
    int editResProvinceFundDetailBatch(String jsonData);

    // 根据主键查询基地
    ResBaseFund readResBaseFund(String recordFlow);
    //编辑基地收支
    int editResBaseFund(ResBaseFund resBaseFund);
    //查询基地收支
    List<ResBaseFund> searchResBaseFund(String startDate,String endDate,ResBaseFund resBaseFund);
    // 新增基地收支
    int addResBaseFund(ResBaseFund resBaseFunds);
    //编辑基地收支详情
    int addResBaseFundDetail(ResBaseFundDetail resBaseFundDetail);
    //编辑基地收支详情
    int editResBaseFundDetail(ResBaseFundDetail resBaseFundDetail);
    //查询基地收支详情
    List<ResBaseFundDetail> searchResBaseFundDetail(ResBaseFundDetail resBaseFundDetail);
    // 基地收支详情
    List<ResBaseFundDetail> resBaseFundList(HashMap<String, Object> paramMap);

    //编辑综合收支
    int editResSyntheticalFund(ResSyntheticalFund resSyntheticalFund);
    // 根据主键查询综合收支
    ResSyntheticalFund  readResSyntheticalFund(String recordFlow);
    //查询综合收支
    List<ResSyntheticalFund> searchResSyntheticalFund(String startDate,String endDate,ResSyntheticalFund resSyntheticalFund);
    //编辑综合收支详情
    int editResSyntheticalFundDetail(ResSyntheticalFundDetail resSyntheticalFundDetail);
    //查询综合收支详情
    List<ResSyntheticalFundDetail> searchResSyntheticalFundDetail(ResSyntheticalFundDetail resSyntheticalFundDetail);
    // 新增综合收支详情
    int addResSyntheticalFund(ResSyntheticalFund resSyntheticalFund);
    // 查询综合收支详情
    List<ResSyntheticalFundDetail> resResSyntheticalFundDetailList(HashMap<String, Object> paramMap);
    // 查询基地
    List<SysOrg> searchOrgs(Map<String, Object> paramMap);

    List<ResProvinceFundDetail> queryFundDetaiList();

}
