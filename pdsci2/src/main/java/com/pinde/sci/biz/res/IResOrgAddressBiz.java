package com.pinde.sci.biz.res;


import com.pinde.sci.form.res.ResOrgAddressForm;
import com.pinde.core.model.ResOrgAddress;

import java.util.List;

public interface IResOrgAddressBiz {


    List<ResOrgAddress> readOrgAddress(String orgFlow);

    int saveOrgAddress(ResOrgAddress address);

    ResOrgAddress readOrgAddressByFlow(String recordFlow);

    ResOrgAddress readOrgOneAddress(String orgFlow);

    int saveOrgAddresses(ResOrgAddressForm bean);
}

