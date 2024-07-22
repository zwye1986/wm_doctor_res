package com.pinde.sci.common;

import com.pinde.core.util.StringUtil;
import com.pinde.sci.enums.edc.AttrDataTypeEnum;
import com.pinde.sci.enums.edc.EdcBlindTypeEnum;
import com.pinde.sci.enums.edc.EdcInputStatusEnum;
import com.pinde.sci.enums.edc.ProjInputTypeEnum;
import com.pinde.sci.enums.irb.IrbStageEnum;
import com.pinde.sci.enums.irb.IrbTypeEnum;
import com.pinde.sci.enums.pub.ProjOrgTypeEnum;
import com.pinde.sci.enums.pub.UserSexEnum;
import com.pinde.sci.model.mo.*;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2016/3/10 0010.
 */
public class GeneralEdcMethod {

    public static String getSinglePic(String status) {
        String skinPath = InitConfig.getSysCfg("sys_skin");
        if (EdcInputStatusEnum.Save.getId().equals(status)) {
            return "/css/skin/" + skinPath + "/images/shu.gif";
        } else if (EdcInputStatusEnum.Submit.getId().equals(status)) {
            return "/css/skin/" + skinPath + "/images/gou.gif";
        }
        return "";
    }
    public static List<PubProjOrg> filterProjOrg(List<PubProjOrg> projOrgList) {
        List<PubProjOrg> result = new ArrayList<PubProjOrg>();
        if (projOrgList != null && projOrgList.size() > 0) {
            for (PubProjOrg projOrg : projOrgList) {
                if (ProjOrgTypeEnum.Leader.getId().equals(projOrg.getOrgTypeId())
                        || ProjOrgTypeEnum.Parti.getId().equals(projOrg.getOrgTypeId())) {
                    result.add(projOrg);
                }
            }
        }
        return result;
    }

    public static String getDateSeason(String month) {
        Integer monthInt = Integer.parseInt(month);
        switch (monthInt) {
            case 1:
                return "1";
            case 2:
                return "1";
            case 3:
                return "1";
            case 4:
                return "2";
            case 5:
                return "2";
            case 6:
                return "2";
            case 7:
                return "3";
            case 8:
                return "3";
            case 9:
                return "3";
            case 10:
                return "4";
            case 11:
                return "4";
            case 12:
                return "4";
            default:
                break;
        }
        return "";
    }


    public static String getProcessClass(String currIrbStageId, String irbStageId) {
        if (IrbStageEnum.Apply.getId().equals(irbStageId)) {
            return "2";
        } else if (IrbStageEnum.Handle.getId().equals(irbStageId)) {
            if (IrbStageEnum.Apply.getId().equals(currIrbStageId)) {
                return "1";
            } else {
                return "2";
            }
        } else if (IrbStageEnum.Review.getId().equals(irbStageId)) {
            if (IrbStageEnum.Apply.getId().equals(currIrbStageId) || IrbStageEnum.Handle.getId().equals(currIrbStageId)) {
                return "1";
            } else {
                return "2";
            }
        } else if (IrbStageEnum.Decision.getId().equals(irbStageId)) {
            if (IrbStageEnum.Apply.getId().equals(currIrbStageId) || IrbStageEnum.Handle.getId().equals(currIrbStageId)
                    || IrbStageEnum.Review.getId().equals(currIrbStageId)) {
                return "1";
            } else {
                return "2";
            }
        } else if (IrbStageEnum.Archive.getId().equals(irbStageId)) {
            if (IrbStageEnum.Archive.getId().equals(currIrbStageId) || IrbStageEnum.Filing.getId().equals(currIrbStageId)) {
                return "2";
            } else {
                return "1";
            }
        } else if (IrbStageEnum.Filing.getId().equals(irbStageId)) {
            if (IrbStageEnum.Filing.getId().equals(currIrbStageId)) {
                return "2";
            } else {
                return "1";
            }
        }
        return "";
    }

    public static String getIrbTypeNameById(String irbTypeId) {
        return IrbTypeEnum.getNameById(irbTypeId);
    }

    public static Integer getIrbStageOrdinal(String stageId) {
        if (IrbStageEnum.Handle.getId().equals(stageId)) {
            return 0;
        } else if (IrbStageEnum.Review.getId().equals(stageId)) {
            return 1;
        } else if (IrbStageEnum.Decision.getId().equals(stageId)) {
            return 2;
        } else if (IrbStageEnum.Archive.getId().equals(stageId)) {
            return 3;
        } else if (IrbStageEnum.Filing.getId().equals(stageId)) {
            return 4;
        }
        return -1;
    }


}
