package com.pinde.sci.common;

import java.util.HashMap;
import java.util.Map;

public class RolePathMapper {

    private static final Map<String, String> ROLE_PATH_MAP = new HashMap<>();

    static {
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_global_role_flow"), "/jsres/manage/global");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_quality_control_role_flow"), "/jsres/manage/quality");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_institute_role_flow"), "/jsres/manage/institute");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_qkzx_role_flow"), "/jsres/manage/province");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_bjw_role_flow"), "/jsres/manage/province");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_zyglj_role_flow"), "/jsres/manage/province");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_university_role_flow"), "/jsres/manage/university");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_university_son_role_flow"), "/jsres/manage/university");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_school_role_flow"), "/jsres/manage/school");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_charge_role_flow"), "/jsres/manage/charge");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_admin_role_flow"), "/jsres/manage/local");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_hospital_secretary_role_flow"), "/jsres/manage/local");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_head_role_flow"), "/jsres/kzr/index");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_teaching_head_role_flow"), "/jsres/kzr/index");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_secretary_role_flow"), "/jsres/km/index");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_teaching_secretary_role_flow"), "/jsres/km/index");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_teacher_role_flow"), "/jsres/teacher/index");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_responsible_teacher_role_flow"), "/res/responsibleTeacher/index");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_doctor_role_flow"), "/jsres/doctor/index");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_nurse_role_flow"), "/res/nurse/index");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_business_role_flow"), "/jsres/business/index");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_businessTwo_role_flow"), "/jsres/businessTwo/index");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_maintenance_role_flow"), "/jsres/manage/maintenance");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_professionalBase_admin_role_flow"), "/jsres/manage/speAdmin");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_professionalBase_adminSecretary_role_flow"), "/jsres/manage/speAdminSecretary");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_management_role_flow"), "/jsres/manage/management");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_expertLeader_role_flow"), "/jsres/manage/expertLeader");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_baseExpert_role_flow"), "/jsres/manage/baseExpert");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_hospitalLeader_role_flow"), "/jsres/manage/hospitalLeader");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_phyAss_role_flow"), "/jsres/manage/phyAss");
        ROLE_PATH_MAP.put(InitConfig.getSysCfg("res_test"), "/jsres/manage/baseQueryStudent");
    }

    public static String getPath(String roleFlow) {
        if (roleFlow == null) {
            throw new IllegalArgumentException("Role flow cannot be null.");
        }

        String path = ROLE_PATH_MAP.get(roleFlow);
        if (path == null) {
            throw new IllegalArgumentException("Unknown role flow: " + roleFlow);
        }

        return path;
    }
}
