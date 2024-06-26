package com.pinde.sci.biz.erp.impl;

import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.erp.IErpCustomerVisitBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.dao.base.ErpCrmCustomerVisitMapper;
import com.pinde.sci.model.mo.ErpCrmCustomerVisit;
import com.pinde.sci.model.mo.ErpCrmCustomerVisitExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ErpCustomerVisitBizImpl implements IErpCustomerVisitBiz {

    @Autowired
    ErpCrmCustomerVisitMapper customerVisitMapper;

    /**
     * 客户回访查询
     *
     * @param customerVisit
     * @return
     */
    @Override
    public List<ErpCrmCustomerVisit> searchCustomerVisit(ErpCrmCustomerVisit customerVisit) {
        ErpCrmCustomerVisitExample example = new ErpCrmCustomerVisitExample();
        ErpCrmCustomerVisitExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
        if (StringUtil.isNotBlank(customerVisit.getVisitFlow())) {
            criteria.andVisitFlowEqualTo(customerVisit.getVisitFlow());
        }
        if (StringUtil.isNotBlank(customerVisit.getVisitType())) {
            criteria.andVisitTypeEqualTo(customerVisit.getVisitType());
        }
        if (StringUtil.isNotBlank(customerVisit.getVisitRefFlow())) {
            criteria.andVisitRefFlowEqualTo(customerVisit.getVisitRefFlow());
        }
        if (StringUtil.isNotBlank(customerVisit.getVisitSubject())) {
            criteria.andVisitSubjectLike(customerVisit.getVisitSubject());
        }
        example.setOrderByClause(" VISIT_TIME DESC ");
        return customerVisitMapper.selectByExample(example);
    }

    /**
     * 保存客户回访
     *
     * @param customerVisit
     * @return
     */
    @Override
    public int saveCustomerVisit(ErpCrmCustomerVisit customerVisit) {
        int result = 0;
        if (StringUtil.isNotBlank(customerVisit.getVisitFlow())) {
            GeneralMethod.setRecordInfo(customerVisit, false);
            result = customerVisitMapper.updateByPrimaryKeySelective(customerVisit);
        } else {
            customerVisit.setVisitFlow(PkUtil.getUUID());
            GeneralMethod.setRecordInfo(customerVisit, true);
            result = customerVisitMapper.insertSelective(customerVisit);
        }
        return result;
    }

    /**
     * 查询单条回访信息
     *
     * @param visitFlow
     * @return
     */
    @Override
    public ErpCrmCustomerVisit readCustomerVisit(String visitFlow) {
        return customerVisitMapper.selectByPrimaryKey(visitFlow);
    }

    /**
     * 删除回访信息
     *
     * @param visitFlow
     * @return
     */
    @Override
    public int deleteCustomerVisit(String visitFlow) {
        ErpCrmCustomerVisit customerVisit = new ErpCrmCustomerVisit();
        customerVisit.setVisitFlow(visitFlow);
        customerVisit.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
        GeneralMethod.setRecordInfo(customerVisit, false);
        return customerVisitMapper.updateByPrimaryKeySelective(customerVisit);
    }
}