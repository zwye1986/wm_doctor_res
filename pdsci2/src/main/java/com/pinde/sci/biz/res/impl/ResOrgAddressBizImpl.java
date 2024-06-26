package com.pinde.sci.biz.res.impl;

import com.pinde.core.pdf.utils.StringUtils;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResOrgAddressBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalConstant;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.ResOrgAddressMapper;
import com.pinde.sci.form.res.ResOrgAddressForm;
import com.pinde.sci.model.mo.ResOrgAddress;
import com.pinde.sci.model.mo.ResOrgAddressExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor=Exception.class)
public class ResOrgAddressBizImpl implements IResOrgAddressBiz{

    @Autowired
    private ResOrgAddressMapper orgAddressMapper;
    @Override
    public List<ResOrgAddress> readOrgAddress(String orgFlow) {
        if(StringUtil.isNotBlank(orgFlow)){
            ResOrgAddressExample example = new ResOrgAddressExample();
            example.createCriteria().andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            List<ResOrgAddress> list = orgAddressMapper.selectByExample(example);
            if(list.size()>0){
                return list;
            }
        }
        return null;
    }

    @Override
    public int saveOrgAddress(ResOrgAddress address) {
        int result=0;
        if(address!=null){
            if(StringUtil.isBlank(address.getRecordFlow())){//新增
                address.setRecordFlow(PkUtil.getUUID());
                GeneralMethod.setRecordInfo(address,true);
                result = orgAddressMapper.insert(address);
            }else{//修改
                GeneralMethod.setRecordInfo(address,false);
                result = orgAddressMapper.updateByPrimaryKeySelective(address);
            }
        }
        return result;
    }

    @Override
    public ResOrgAddress readOrgAddressByFlow(String recordFlow) {
        return orgAddressMapper.selectByPrimaryKey(recordFlow);
    }

    @Override
    public ResOrgAddress readOrgOneAddress(String orgFlow) {
        if(StringUtil.isNotBlank(orgFlow)){
            ResOrgAddressExample example = new ResOrgAddressExample();
            example.createCriteria().andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
            List<ResOrgAddress> list = orgAddressMapper.selectByExample(example);
            if(list.size()>0){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public int saveOrgAddresses(ResOrgAddressForm bean) {
        if(bean!=null&&bean.getAddresses()!=null&&bean.getAddresses().size()>0)
        {
            int c=0;
            List<ResOrgAddress> addresses=bean.getAddresses();
            List<String> recordFlows=new ArrayList<>();
            for(ResOrgAddress address:addresses)
            {
                if(StringUtil.isNotEmpty(bean.getAddressType())){
                    address.setAddressType(bean.getAddressType());
                }
                address.setOrgFlow(bean.getOrgFlow());
                c+=saveOrgAddress(address);
                recordFlows.add(address.getRecordFlow());
            }
//            if(c>0)
//            {
//                ResOrgAddressExample example = new ResOrgAddressExample();
//                example.createCriteria().andOrgFlowEqualTo(bean.getOrgFlow())
//                        .andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y).andRecordFlowNotIn(recordFlows);
//                ResOrgAddress address=new ResOrgAddress();
//                address.setRecordStatus(GlobalConstant.RECORD_STATUS_N);
//                orgAddressMapper.updateByExampleSelective(address,example);
//
//            }
            return c;

        }
        return 0;
    }
}
