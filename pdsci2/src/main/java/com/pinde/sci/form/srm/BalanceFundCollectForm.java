package com.pinde.sci.form.srm;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2017-08-23.
 */
public class BalanceFundCollectForm implements Serializable {

    private static final long serialVersionUID = 4796066286598653914L;

    private String userFlow;
    private String userName;
    /*//剩余经费（行政）来源为除国库下拨、院基金会的
    private double balanceAdministration;
    //剩余经费（国库）来源为国库下拨
    private double balanceNational;
    //剩余经费（院基金会） 来源为院基金会
    private double balanceHospital;*/
    //剩余经费按到账来源分类
    private Map<String,BigDecimal> balanceFundMapBySourceId;

    public String getUserFlow() {
        return userFlow;
    }

    public void setUserFlow(String userFlow) {
        this.userFlow = userFlow;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<String, BigDecimal> getBalanceFundMapBySourceId() {
        return balanceFundMapBySourceId;
    }

    public void setBalanceFundMapBySourceId(Map<String, BigDecimal> balanceFundMapBySourceId) {
        this.balanceFundMapBySourceId = balanceFundMapBySourceId;
    }
}
