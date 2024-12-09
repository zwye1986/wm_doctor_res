package com.pinde.sci.common;

import com.pinde.core.common.enums.RegistryTypeEnum;

/**
 * Created by Administrator on 2016/3/10 0010.
 */
public class GeneralResMethod {
//    public static boolean canShowByVersionForRes(String version) {
//        boolean result = StringUtil.isBlank(version);
//        if (!result) {
//            result = version.equals(InitConfig.getSysCfg("res_for_use"));
//        }
//        return result;
//    }

    public static RegistryTypeEnum getRegistryTypeEnumById(String id) {
        for (RegistryTypeEnum registryType : RegistryTypeEnum.values()) {
            if (registryType.getId().equals(id)) {
                return registryType;
            }
        }
        return null;
    }
}
