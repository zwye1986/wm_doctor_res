package com.pinde.core.common;

public class GlobalConstant {

    public static final String RECORD_STATUS_Y = GlobalConstant.FLAG_Y;
    public static final String RECORD_STATUS_N = GlobalConstant.FLAG_N;
    public static final String OSCA_WS_ID = "osca";
    public static final String RES_WS_ID = "res";
    public static final String SYS_WS_ID = "sys";
    public static final String FLAG_Y = "Y";
    public static final String FLAG_N = "N";
    //用户维护 限本专业基地
    public static final String USER_LIST_SPE = "spe";
    public static final int ZERO_LINE = 0;
    public static final int ONE_LINE = 1;
    public static final String PARAM_FLAG_ONE = "1";

    //要求默认其他项
    public static final String RES_REQ_OTHER_ITEM_ID = "00000000000000000000000000000000";
    //默认表单版本
    public static final String RES_DEFAULT_FORM_VER = "1.0";

    //配置文件中标记为file的
    public static final String IS_FILE = "isFile";

    //角色
    public static final String RES_ROLE_SCOPE_TEACHER = "teacher";//带教
    public static final String RES_ROLE_SCOPE_HEAD = "head";//科主任
    public static final String RES_ROLE_SCOPE_MANAGER = "manager";//基地主任
    public static final String RES_ROLE_SCOPE_PROFESSIONALBASE = "professionalBase";//专业基地主任
    public static final String RES_ROLE_SCOPE_ADMIN = "admin";//管理员

    public static final String SAVE_SUCCESSED = "保存成功！";
    public static final String SAVE_FAIL = "保存失败！";

    public static final String RES_NOTICE_TYPE5_ID = "LM05";
    public static final String RES_NOTICE_TYPE5_NAME = "系统公告";

    public static final String RES_NOTICE_SYS_ID = "0b9e7ef396a24e129d09131bbd4f1ff4";//过程
    //住院医师表单
    public static final String RES_FORM_CONFIG_PATH = "res/form";
    public static final String RES_FORM_PRODUCT = "product";
    public static final String RES_FORM_PRODUCT_VER = "1.0";

    public static final String USER_LIST_LOCAL = "local";
    public static final String USER_LIST_SPELOCAL = "speAdmin";
    public static final String USER_LIST_SPELOCALSECRETARY = "speAdminSecretary";
    public static final String USER_LIST_CHARGE = "charge";
    public static final String USER_LIST_GLOBAL = "global";

    /**
     * 当前登陆用户
     */
    public static final String CURR_USER = "currUser";

    //工作站名称
    public static final String SRM_WS_ID = "srm";
    public static final String EDC_WS_ID = "edc";
    public static final String SCH_WS_ID = "sch";
    public static final String EVAL_WS_ID = "eval";
    public static final String PORTALS_WS_ID = "portals";

    //判断USER是否位考官
    public static final String IS_EXAM_TEA_Y = GlobalConstant.FLAG_Y;
    public static final String IS_EXAM_TEA_N = GlobalConstant.FLAG_N;

    //超级用户账号
    public static final String ROOT_USER_FLOW = "00000000000000000000000000000000";
    //超级用户账号
    public static final String ROOT_USER_CODE = "njpdxx_001";

    public static final String DECLARER_ROLE_FLOW = "declarer_role_flow";
    //用户维护 范围标记
    public static final String USER_LIST_SCOPE = "userListScope";
    //督导-管理专家
    public static final String USER_LIST_MANAGEMENT = "management";
    //督导-专业专家
    public static final String USER_LIST_EXPERTLEADER = "expertLeader";
    //督导-专业基地专家
    public static final String USER_LIST_BASEEXPERT = "baseExpert";
    //督导-评分专家
    public static final String USER_LIST_HOSPITALLEADER = "hospitalLeader";
    //师资-医师协会
    public static final String USER_LIST_PHYASS = "phyAss";
    //省厅下级审核部门
    public static final String USER_LIST_PROVINCE = "province";
    // 外省管理员角色
    public static final String USER_LIST_BASE = "baseQueryStudent";
    //用户维护 主管部门
    public static final String USER_LIST_UNIVERSITY = "university";
    //用户维护 限本人
    public static final String USER_LIST_PERSONAL = "personal";

    //当前用户
    public static final String CURRENT_USER = "currUser";
    //当前部门列表
    public static final String CURRENT_DEPT_LIST = "currDeptList";
    //当前用户
    public static final String CURRENT_USER_NAME = "currUserName";
    // 当前登录用户角色
    public static final String CURRENT_ROLE = "currRole";

    // 当前登录用户角色名称
    public static final String CURRENT_ROLE_NAME = "currRoleName";
    //当前用户具有的角色
    public static final String CURRENT_ROLE_LIST = "currRoleList";
    //当前用户允许使用的菜单
    public static final String CURRENT_MENU_ID_LIST = "currUserMenuIdList";
    //当前用户允许使用的菜单组
    public static final String CURRENT_MENU_SET_ID_LIST = "currUserMenuSetIdList";
    //当前用户允许使用的模块
    public static final String CURRENT_MODULE_ID_LIST = "currUserModuleIdList";
    //当前用户允许使用的工作站
    public static final String CURRENT_WORKSTATION_ID_LIST = "currUserWorkStationIdList";
    //当前用户具有的角色-备份
    public static final String CURRENT_ROLE_LIST_BACKUP = "currRoleListBackup";
    //当前用户允许使用的菜单-备份
    public static final String CURRENT_MENU_ID_LIST_BACKUP = "currUserMenuIdListBackup";
    //当前用户允许使用的菜单组-备份
    public static final String CURRENT_MENU_SET_ID_LIST_BACKUP = "currUserMenuSetIdListBackup";
    //当前用户允许使用的模块-备份
    public static final String CURRENT_MODULE_ID_LIST_BACKUP = "currUserModuleIdListBackup";
    //当前用户允许使用的工作站-备份
    public static final String CURRENT_WORKSTATION_ID_LIST_BACKUP = "currUserWorkStationIdListBackup";
    //当前工作站名称
    public static final String CURRENT_WS_ID = "currWsId";


    //数据伪删除标记，没有业务含义
    public static final String RECORD_STATUS_D = "D";
    public static final String FLAG_F = "F";
    public static final String NULL = "NULL";

    public static final String OPRE_SUCCESSED = "操作成功！";

    public static final String OPRE_FAIL = "操作失败！";


    public static final String OPRE_SUCCESSED_FLAG = "1";

    public static final String OPRE_FAIL_FLAG = "0";

    public static final String UPDATE_SUCCESSED = "修改成功！";

    public static final String UPDATE_FAIL = "修改失败！";

    public static final String RESET_SUCCESSED = "重置成功！";
    public static final String LOCK_SUCCESSED = "锁定成功！";

    public static final String UnLOCK_SUCCESSED = "解锁成功！";

    //项目种类 范围标记
    public static final String PROJ_CATE_SCOPE = "projCateScope";

    //项目显示 范围标记
    public static final String PROJ_LIST_SCOPE = "projListScope";

    public static final String PASSWD_ERROR = "输入错误,请重新输入原始密码";

    public static final String DELETE_SUCCESSED = "删除成功！";

    public static final String DELETE_FAIL = "删除失败！";

    public static final String STOP_USE_SUCCESSED = "停用成功！";

    public static final String OPERATE_SUCCESSED = "操作成功！";

    public static final String OPERATE_FAIL = "操作失败！";
    //用户名重复
    public static final String USER_CODE_REPETE = "该用户名已经被注册！";
    //身份证号重复
    public static final String USER_ID_NO_REPETE = "该身份证号已经被注册！";
    //手机号重复
    public static final String USER_PHONE_REPETE = "该手机号已经被注册！";
    //电子邮箱重复
    public static final String USER_EMAIL_REPETE = "该电子邮箱已经被注册！";
    //姓名不匹配
    public static final String USER_NAME_NOT_EQUAL = "姓名不匹配！";
    //身份证号不匹配
    public static final String USER_ID_NO_NOT_EQUAL = "身份证号不匹配！";
    //所在机构不匹配
    public static final String USER_ORG_NOT_EQUAL = "所在机构不匹配！";
    //两次密码输入不一致
    public static final String USER_PASSWD_NOT_EQUAL = "两次密码输入不一致！";
    //用户信息被列入黑名单
    public static final String USER_INFO_IN_BLACK = "用户信息被列入黑名单！";
    //证件号被列入黑名单
    public static final String ID_NO_IN_BLACK = "该证件号已被列入黑名单！";
    //手机号被列入黑名单
    public static final String USER_PHONE_IN_BLACK = "该手机号已被列入黑名单！";

    //上传图片
    public static final String UPLOAD_SUCCESSED = "上传成功！";
    public static final String UPLOAD_FAIL = "上传失败！";
    public static final String UPLOAD_IMG_SIZE_ERROR = "文件大小不能超过";
    public static final String UPLOAD_IMG_TYPE_ERROR = "只支持上传bmp/gif/jpg/png！";
    public static final String FILE_PIXEL_ERROR = "上传图片像素不符合要求";
    //系统管理员角色
    public static final String SYSTEM_ROLE = "system";
    //res角色
    public static final String RES_ROLE_SCOPE_DOCTOR = "doctor";//医师
    public static final String RES_ROLE_SCOPE_TUTOR = "tutor";//导师
    public static final String RES_ROLE_SCOPE_SCHOOL = "school";//学校
    public static final String RES_ROLE_SCOPE_SECRETARY = "secretary";//科秘
    public static final String RES_ROLE_SCOPE_GLOBAL = "charge";//平台

    //江苏省审核信息
    public static final String BASIC_INFO = "BasicInfo";

    public static final String ORG_MANAGE = "OrgManage";

    public static final String TEACH_CONDITION = "TeachCondition";

    public static final String SUPPORT_CONDITION = "SupportCondition";

    public static final String BASIC_MAIN_ALL = "basicMainAll";

    //科室基本信息的类别
    public static final String DEPT_BASIC_INFO = "DeptBasicInfo";   //科室基本信息
    public static final String DIAG_DISEASE = "DiagDisease";    //诊疗疾病范围
    public static final String EQUIPMENT_INSTRUMENTS = "EquipmentInstruments";  //医疗设备仪器
    public static final String GUIDING_PHYSICIAN = "GuidingPhysician";  //指导医师情况
    public static final String DEPARTMENT_HEAD = "DepartmentHead";  //负责人信息
    public static final String ROTATING_DEPARTMENTS = "RotatingDepartments"; //轮转科室
    public static final String TRAINING = "Training"; //培训情况


    //江苏西医成绩状态标识
    public static final int PASS = 1;
    public static final int UNPASS = 0;
    public static final int NORMAL = 2;


    public static final String PHOTO_ALREADY = "当天数据已经备份，请勿重新操作！";


    public static final String HAVE_NO_ROTATION_CFG = "请对本年度轮转方案进行配置后，审核学员信息！";

    /**
     * 一次处理一类全部的记录或数据
     */
    public static final String OPER_ALL = "ALL";

    public static final String ORG_SPE_ALL_WS_ID = "org_spe_all";

    //默认起始页
    public static final Integer DEFAULT_START_PAGE = 1;
    //默认每页显示记录数
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final String PAGE_SIZE = "pageSize";
    public static final String PAGE_SERVLET_PATH = "pageServletPath";

    //当前工作站名称
    public static final String CURRENT_MODULE_ID = "currModuleId";
}
