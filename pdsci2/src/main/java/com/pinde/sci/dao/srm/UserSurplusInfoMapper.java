package com.pinde.sci.dao.srm;

import com.pinde.sci.model.mo.SrmProjFundInfo;
import com.pinde.sci.model.srm.PersonalFundInfoExt;
import com.pinde.sci.model.srm.PubProjExt;
import com.pinde.sci.model.srm.UserSurplusInfo;

import java.util.List;
import java.util.Map;

/**
 * 经费结余接口
 * Created by www.0001.Ga on 2017-06-20.
 */
public interface UserSurplusInfoMapper {
    /**
     * 经费结转列表(按用户)
     * @param map
     * @return
     */
    List<UserSurplusInfo> surplusInfoList(Map<String,String> map);

    /**
     * 经费结转列表(按项目)
     * @param pubProjExt
     * @return
     */
    List<PubProjExt> surplusProjInfoList(PubProjExt pubProjExt);


    /**
     * 个人（医院）经费列表
     * @param map
     * @return
     */
    List<PersonalFundInfoExt> personalFundList(Map<String,String> map);
}
