package com.pinde.core.common;

public class GlobalConstant {

    public static final String RECORD_STATUS_Y = com.pinde.core.common.GlobalConstant.FLAG_Y;
    public static final String RECORD_STATUS_N = com.pinde.core.common.GlobalConstant.FLAG_N;
    public static final String OSCA_WS_ID = "osca";
    public static final String RES_WS_ID = "res";
    public static final String SYS_WS_ID = "sys";
    public static final String RECRUIT_WS_ID = "recruit";
    public static final String FLAG_Y = "Y";
    public static final String FLAG_N = "N";
    public static final String FLAG_F = "F";
    public static final String NULL = "NULL";
    public static final String NOT_NULL = "NOTNULL";
    //用户维护 限本专业基地
    public static final String USER_LIST_SPE = "spe";
    public static final int ZERO_LINE = 0;
    public static final int ONE_LINE = 1;
    public static final String PARAM_FLAG_ONE = "1";
    public static final String ORG_SPE_BASE_ALL_SETTING_PREFIX = "org_spe_all";
    public static final String PD_ORG_FLOW = "00000000000000000000000000000000";
    public static final String PD_ORG_CODE = "080279573";
    public static final String PD_ORG_NAME = "南京品德网络信息技术有限公司";
    public static final String PD_SUPPPWD = "@teh666";
    public static final String PD_SECURITY_SUPPPWD = "2a93ef719a22f79028835959bb90a8ef";//与PD_SUPPWD对应md5加密

    //系统默认密码
    public static final String INIT_PASSWORD = "cnnj123";

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

    //自定义通用资源获取sql
    public static final String COMMON_SQL = "commonSql";
    public static final String EXECUTE_IDS = "executeIds";
    public static final String ARGUMENTS = "arguments";


    public static final String RES_NOTICE_TYPE5_ID = "LM05";
    public static final String RES_NOTICE_TYPE5_NAME = "系统公告";
    public static final String RES_NOTICE_TYPE6_ID = "LM06";
    public static final String RES_NOTICE_TYPE6_NAME = "政策法规";
    public static final String RES_NOTICE_TYPE7_ID = "LM07";
    public static final String RES_NOTICE_TYPE7_NAME = "专题报道";
    public static final String RES_NOTICE_TYPE8_ID = "LM08";
    public static final String RES_NOTICE_TYPE8_NAME = "学术专区";
    public static final String RES_NOTICE_TYPE9_ID = "LM09";
    public static final String RES_NOTICE_TYPE9_NAME = "经验分享";
    public static final String RES_NOTICE_TYPE10_ID = "LM10";
    public static final String RES_NOTICE_TYPE10_NAME = "基地公告";
    //用户名重复
    public static final String USER_CODE_REPETE = "该用户名已经被注册！";
    //身份证号重复
    public static final String USER_ID_NO_REPETE = "该身份证号已经被注册！";
    //学号重复
    public static final String USER_SID_REPETE = "该学号已经存在！";
    //手机号重复
    public static final String USER_PHONE_REPETE = "该手机号已经被注册！";
    //该手机号已绑定过账号
    public static final String USER_PHONE_REPEAT = "该手机号已绑定过账号";
    //该手机号码未经认证，无法通过手机号码找回密码，请联系管理员重置密码
    public static final String USER_PHONE_NOTAUTHEN = "该手机号码未经认证，无法通过手机号码找回密码，请联系管理员重置密码";
    //电子邮箱重复
    public static final String USER_EMAIL_REPETE = "该电子邮箱已经被注册！";
    //验证码超时，请重新获取验证码
    public static final String VERIFT_CODE_TIMEOUT = "验证码超时，请重新获取验证码！";
    //验证码错误
    public static final String VERIFT_CODE_ERROR = "验证码错误";
    //验证码正确
    public static final String VERIFT_CODE_RIGHT = "验证码正确";
    //两次手机号不一致
    public static final String PHONE_NOT_SAME = "两次手机号不一致,请重新获取验证码";
    //操作失败，请刷新页面重试
    public static final String VERIFT_CODE_REFRESH = "操作失败，请刷新页面重试";
    //姓名不匹配
    public static final String USER_NAME_NOT_EQUAL = "姓名不匹配！";
    //身份证号不匹配
    public static final String USER_ID_NO_NOT_EQUAL = "身份证号不匹配！";
    //身份证号不匹配
    public static final String USER_ID_NO_VALIDATE = "请填写有效身份证";
    //身份证号不匹配
    public static final String USER_ID_NO_NULL = "证件号码不能为空";
    //手机号不匹配
    public static final String USER_PHONE_NOT_EQUAL = "手机号不匹配！";
    //所在机构不匹配
    public static final String USER_ORG_NOT_EQUAL = "所在机构不匹配！";
    //电子邮箱不匹配
    public static final String USER_EMAIL_NOT_EQUAL = "电子邮箱不匹配！";

    //客户名称已存在
    public static final String CRM_CUSTOMER_NAME_EXIST = "客户名称已存在！";

    //两次密码输入不一致
    public static final String USER_PASSWD_NOT_EQUAL = "两次密码输入不一致！";
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
    public static final String RES_REQ_OTHER_ITEM_NAME = "其他";

    // 当前登录用户角色
    public static final String CURR_ROLE_OBJ = "currRoleObj";
    //发送短信模板
    public static final String JSRES_TEMPLATE = "516946";

    public static final String USER_REG_SUCCESSED = "注册成功！";

    //评分详情表中已有数据
    public static final String TOTAL_SCORE_IN_INFO = "评分详情表中已有评分，总得分将以详情表中数据为准！";

    //当前机构
    public static final String CURRENT_ORG = "currOrg";


    public static final String HAVE_AUDIT_PASS_STUDENT = "该专业已有审核通过的学员，不可再修改轮转方案！";

    public static final String HAVE_NO_ROTATION_CFG = "请对本年度轮转方案进行配置后，审核学员信息！";
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

    //工作站名称
    public static final String IRB_WS_ID = "irb";
    public static final String GCP_WS_ID = "gcp";
    public static final String AEM_WS_ID = "aem";
    public static final String LCJN_WS_ID = "lcjn";
    public static final String EDU_WS_ID = "edu";
    public static final String ERP_WS_ID = "erp";
    public static final String CMIS_WS_ID = "cmis";//学籍工作站ID
    public static final String NJMUEDU_WS_ID = "njmuedu";
    public static final String GZYKDX_WS_ID = "gzykdx";
    public static final String FSTU_WS_ID = "fstu";
    public static final String STUDY_WS_ID = "study";

    //判断USER是否位考官
    public static final String IS_EXAM_TEA_Y = com.pinde.core.common.GlobalConstant.FLAG_Y;
    public static final String IS_EXAM_TEA_N = com.pinde.core.common.GlobalConstant.FLAG_N;

    //超级用户账号
    public static final String ROOT_USER_FLOW = "00000000000000000000000000000000";
    //超级用户账号
    public static final String ROOT_USER_CODE = "njpdxx_001";
    public static final String ROOT_USER_NAME = "南京品德信息";

    //部门维护 范围标记
    public static final String DEPT_LIST_SCOPE = "deptListScope";
    //部门维护 不限
    public static final String DEPT_LIST_GLOBAL = "global";
    //部门维护 限本机构
    public static final String DEPT_LIST_LOCAL = "local";

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
    public static final String CURRENT_ROLE_FLOW = "currRoleFlow";

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
    //用户信息被列入黑名单
    public static final String USER_INFO_IN_BLACK = "用户信息被列入黑名单！";
    //证件号被列入黑名单
    public static final String ID_NO_IN_BLACK = "该证件号已被列入黑名单！";
    //手机号被列入黑名单
    public static final String USER_PHONE_IN_BLACK = "该手机号已被列入黑名单！";

    //上传图片
    //上传图片
    public static final String UPLOAD_SUCCESSED = "上传成功！";
    public static final String UPLOAD_FAIL = "上传失败！";
    public static final String UPLOAD_IMG_SIZE_ERROR = "文件大小不能超过";
    public static final String UPLOAD_IMG_TYPE_ERROR = "只支持上传bmp/gif/jpg/png！";
    public static final String UPLOAD_FILE_NULL = "文件为空！";
    public static final String VALIDATE_FILE_FAIL = "文件校验失败,请选择正确的文件重新上传";
    public static final String FILE_SIZE_PASS_SCOPE = "文件大小超过范围";
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

    // 显示项目列表的scope 卫生局
    public static final String PROJ_STATUS_SCOPE_GLOBAL = "global";

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

    public static final String RES_ROLE_SCOPE_RESPONSIBLETEACHER = "responsibleTeacher";//责任导师

    public static final String SEND_SUCCESSED = "发送成功！";

    public static final String SAVE_SEND_SUCCESSED = "保存并发送成功！";

    public static final String SEND_FAIL = "发送失败！";

    //用户名不存在
    public static final String USER_CODE_NOT_EXIST = "该用户名不存在！";
    //手机号不存在
    public static final String USER_PHONE_NOT_EXIST = "该手机号不存在！";
    //电子邮箱不存在
    public static final String USER_EMAIL_NOT_EXIST = "该电子邮箱不存在！";
    //电子邮箱不存在
    public static final String ID_NO_NOT_EXIST = "该身份证号不存在！";
    //电子邮箱不存在
    public static final String NO_NOT_EXIST = "该证件号不存在！";

    public static final String DUPLICATE_NAME = "名称重复";

    public static final String NOT_BASE_EXPERT = "请创建专业专家账号！";

    // 其他毕业院校编码
    public static final String OTHER_GRADUATE_SCHOOL_CODE = "9999999999";
    // 其他毕业专业编码
    public static final String OTHER_GRADUATEMAJOR_CODE = "99999999";

    //解决住宿问题
    public static final String SOLVE_ALL = "全部解决";
    public static final String SOLVE_PART = "部分解决";
}
