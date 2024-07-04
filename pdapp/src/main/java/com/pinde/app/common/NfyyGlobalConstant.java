package com.pinde.app.common;

public class NfyyGlobalConstant {

	public static String RESULT_CODE_SUCCESS="200";
	public static String RESULT_NAME_SUCCESS="交易成功";

	public static String RESULT_CODE_NOEXITS="404";
	public static String RESULT_NAME_NOEXITS="不存在的交易码";

	public static String RESULT_CODE_AUTHERROR="403";
	public static String RESULT_NAME_AUTHERROR="授权错误";

	public static String RESULT_CODE_SERVICEERROR="500";
	public static String RESULT_NAME_SERVICEERROR="后台错误";

	public static String RESULT_CODE_USERFLOW_NULL="30001";
	public static String RESULT_NAME_USERFLOW_NULL="用户标示为空";

	public static String RESULT_CODE_DEPTFLOW_NULL="30002";
	public static String RESULT_NAME_DEPTFLOW_NULL="科室标示为空";

	public static String RESULT_CODE_INDEXPAGE_NULL="30003";
	public static String RESULT_NAME_INDEXPAGE_NULL="当前页面为空";

	public static String RESULT_CODE_PAGESIZE_NULL="30004";
	public static String RESULT_NAME_PAGESIZE_NULL="每页显示条数为空";

	public static String RESULT_CODE_FUNCTYPE_ERROR="30101";
	public static String RESULT_NAME_FUNCTYPE_ERROR="功能类型标示错误";

	public static String RESULT_CODE_FUNCFLOW_ERROR="30102";
	public static String RESULT_NAME_FUNCFLOW_ERROR="功能标示错误";

	public static String RESULT_CODE_DATAFLOW_NULL="30103";
	public static String RESULT_NAME_DATAFLOW_NULL="数据标示为空";

	public static String RESULT_CODE_CATAFLOW_NULL="30104";
	public static String RESULT_NAME_CATAFLOW_NULL="分类标示为空";

	public static String RESULT_CODE_USERCODE_NULL="30201";
	public static String RESULT_NAME_USERCODE_NULL="用户名为空";

	public static String RESULT_CODE_PASSWD_NULL="30202";
	public static String RESULT_NAME_PASSWD_NULL="密码为空";

	public static String RESULT_CODE_NOTFOUND="30203";
	public static String RESULT_NAME_NOTFOUND="用户未找到";

	public static String RESULT_CODE_LOCKUSER="30204";
	public static String RESULT_NAME_LOCKUSER="用户被锁定";

	public static String RESULT_CODE_USERCODEORPASSWDERROR="30205";
	public static String RESULT_NAME_USERCODEORPASSWDERROR="用户名或密码错误";

	public static String RESULT_CODE_DOCTORFLOW_NULL="30005";
	public static String RESULT_NAME_DOCTORFLOW_NULL="学员标识为空";

	public static String RESULT_CODE_ROLEID_NULL = "30006";
	public static String RESULT_NAME_ROLEID_NULL = "角色为空";

	public static String RESULT_CODE_INDEX_TYPE_NULL = "30007";
	public static String RESULT_NAME_INDEX_TYPE_NULL = "查询类型为空";

	public static Integer DEFAULT_INDEX_PAGE=1;
	public static Integer DEFAULT_PAGE_SIZE=10;

	public static String ROLE_ID_4="Trainee";
	public static String ROLE_ID_6="Teacher";

//	public static Integer JOIN_ACTIVITY_TIME = 3000;//参加活动时间为开始时间前30分钟

	public static Integer PLAY_SCORE_ACTIVITY_TIME = 24 * 60;//给活动评分为活动结束时间后30分钟

	public static String   RESULT_CODE_NOT_JOIN_ACTIVITY = "30301";
	public static String   RESULT_NAME_NOT_JOIN_ACTIVITY = "活动不可参加";

	public static String   RESULT_CODE_NOT_PLAY_SCORE_ACTIVITY = "30302";
	public static String   RESULT_NAME_NOT_PLAY_SCORE_ACTIVITY = "活动不可评分";

//	public static Map<String , String> SEX_MAP = new HashMap<String , String>();
//
//	public static Map<String , String> ROLE_ID_MAP = new HashMap<String , String>();
//
//	public static Map<String , String> ROLE_NAME_MAP = new HashMap<String , String>();
//
//	static{
//		SEX_MAP.put("0", "女");
//		SEX_MAP.put("1", "男");
//
//		ROLE_ID_MAP.put("4", "Trainee");
//		ROLE_ID_MAP.put("6", "Teacher");
//
//		ROLE_NAME_MAP.put("4", "实习生");
//		ROLE_NAME_MAP.put("6", "带教老师");
//	}

}


