package com.pinde.sci.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pinde.core.common.enums.UserSexEnum;
import com.pinde.core.common.enums.UserStatusEnum;
import com.pinde.core.common.enums.WeixinStatusEnum;
import com.pinde.core.model.SysDept;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.StringUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class WeixinQiYeUtil {
	
	private static Logger logger = LoggerFactory.getLogger(WeixinQiYeUtil.class);
	
	private static long last_access_token_time = 0;
	
	private static String last_access_token = "";
	
	public static String gettoken(String corpid,String corpsecret){
		long access_token_time = System.currentTimeMillis();
		int gap = (int)(access_token_time-last_access_token_time)/1000;
		if(gap>=7200l || last_access_token_time==0l){
			String gettokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+StringUtil.defaultString(corpid)+"&corpsecret="+StringUtil.defaultString(corpsecret);
			String response = _get(gettokenUrl);
			System.err.println(response);
			Map responseMap = JSON.parseObject(response, Map.class);
			String access_token = (String)responseMap.get("access_token");
			last_access_token = access_token;
			last_access_token_time = access_token_time;
		}
		return StringUtil.defaultString(last_access_token);
//		String gettokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+StringUtil.defaultString(corpid)+"&corpsecret="+StringUtil.defaultString(corpsecret);
//		String response = _get(gettokenUrl);
//		System.err.println(response);
//		Map responseMap = JSON.parseObject(response, Map.class);
//		String access_token = (String)responseMap.get("access_token");
//		return access_token;
	}
		
	public static boolean createUser(String corpid,String corpsecret,String weiXinDeptId,SysUser user){
		if(StringUtil.isBlank(user.getUserFlow())){
			return false;
		}
		String accessToken = gettoken(corpid,corpsecret);
		String createUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token="+StringUtil.defaultString(accessToken);
		String jsonData = "{"
				  +"\"userid\": \""+StringUtil.defaultString(user.getUserFlow())+"\","
				  +"\"name\": \""+StringUtil.defaultString(user.getUserName())+"\","
				  +"\"department\": ["+StringUtil.defaultIfEmpty(weiXinDeptId,"1")+"],"
				  +"\"position\": \""+StringUtil.defaultString("")+"\","
				  +"\"mobile\": \""+StringUtil.defaultString(user.getUserPhone())+"\",";
				  if(StringUtil.isEquals(user.getSexId(), UserSexEnum.Man.getId())){
					  jsonData = jsonData +"\"gender\": 0,";
				  }else if(StringUtil.isEquals(user.getSexId(),UserSexEnum.Man.getId())){
					  jsonData = jsonData +"\"gender\": 1,";
				  }
				  jsonData = jsonData +"\"tel\": \""+StringUtil.defaultString(user.getUserPhone())+"\","
				  +"\"email\": \""+StringUtil.defaultString(user.getUserEmail())+"\","
//				  +"\"weixinid\": \"zhangsan4dev\","
//				  +"\"extattr\": {\"attrs\":[{\"name\":\"爱好\",\"value\":\"旅游\"},{\"name\":\"卡号\",\"value\":\"1234567234\"}]}"
			+"}";
					System.err.println(jsonData);
		String response = _post(createUrl,jsonData);
		System.err.println(response);
		Map responseMap = JSON.parseObject(response, Map.class);
		Integer errcode = (Integer)responseMap.get("errcode");
		if(errcode==0){
			return true;
		}
		return false;
	}
	
	public static boolean updateUser(String corpid,String corpsecret,String weiXinDeptId,SysUser user){
		if(StringUtil.isBlank(user.getUserFlow())){
			return false;
		}
		String accessToken = gettoken(corpid,corpsecret);
		String updateUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token="+StringUtil.defaultString(accessToken);
		String jsonData = "{"
				  +"\"userid\": \""+StringUtil.defaultString(user.getUserFlow())+"\","
				  +"\"name\": \""+StringUtil.defaultString(user.getUserName())+"\","
				  +"\"department\": ["+StringUtil.defaultIfEmpty(weiXinDeptId,"1")+"],"
				  +"\"position\": \""+StringUtil.defaultString("")+"\","
				  +"\"mobile\": \""+StringUtil.defaultString(user.getUserPhone())+"\",";
				  if(StringUtil.isEquals(user.getSexId(),UserSexEnum.Man.getId())){
					  jsonData = jsonData +"\"gender\": 0,";
				  }else if(StringUtil.isEquals(user.getSexId(), UserSexEnum.Man.getId())){
					  jsonData = jsonData +"\"gender\": 1,";
				  }
				  if(StringUtil.isEquals(user.getStatusId(), UserStatusEnum.Activated.getId())){
					  jsonData = jsonData +"\"enable\": 1,";
				  }else{
					  jsonData = jsonData +"\"enable\": 0,";
				  }
				  jsonData = jsonData +"\"tel\": \""+StringUtil.defaultString(user.getUserPhone())+"\","
				  +"\"email\": \""+StringUtil.defaultString(user.getUserEmail())+"\","
//				  +"\"weixinid\": \"zhangsan4dev\","
//				  +"\"extattr\": {\"attrs\":[{\"name\":\"爱好\",\"value\":\"旅游\"},{\"name\":\"卡号\",\"value\":\"1234567234\"}]}"
			+"}";
		String response = _post(updateUrl,jsonData);
		System.err.println(response);
		Map responseMap = JSON.parseObject(response, Map.class);
		Integer errcode = (Integer)responseMap.get("errcode");
		if(errcode==0){
			return true;
		}
		return false;
	}
	
	public static boolean delUser(String corpid,String corpsecret,String userFlow){
		if(StringUtil.isBlank(userFlow)){
			return false;
		}
		String accessToken = gettoken(corpid,corpsecret);
		String getUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token="+StringUtil.defaultString(accessToken)+"&userid="+StringUtil.defaultString(userFlow);
		String response = _get(getUrl);
		System.err.println(response);
		Map responseMap = JSON.parseObject(response, Map.class);
		Integer errcode = (Integer)responseMap.get("errcode");
		if(errcode==0){
			return true;
		}
		return false;
	}
	
	public static SysUser getUser(String corpid,String corpsecret,String getUserFlow){
		if(StringUtil.isBlank(getUserFlow)){
			return null;
		}
		String accessToken = gettoken(corpid,corpsecret);
		String getUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/get?access_token="+StringUtil.defaultString(accessToken)+"&userid="+StringUtil.defaultString(getUserFlow);
		String response = _get(getUrl);
		System.err.println(response);
		Map responseMap = JSON.parseObject(response, Map.class);
		Integer errcode = (Integer)responseMap.get("errcode");
		if(errcode==0){
			SysUser sysUser = new SysUser();
			sysUser.setUserFlow((String)responseMap.get("userid"));
			sysUser.setWeiXinId((String)responseMap.get("weixinid"));
//			sysUser.setUserName((String)responseMap.get("name"));
//			sysUser.setUserEmail((String)responseMap.get("email"));
//			sysUser.setUserPhone((String)responseMap.get("mobile"));
//			String gender = (String)responseMap.get("gender");
//			if(StringUtil.isEquals(gender,"0")){
//				sysUser.setSexId(UserSexEnum.Man.getId());
//				sysUser.setSexName(UserSexEnum.Man.getName());
//			}else if(StringUtil.isEquals(gender,"0")){
//				sysUser.setSexId(UserSexEnum.Woman.getId());
//				sysUser.setSexName(UserSexEnum.Woman.getName());
//			}else{
//				sysUser.setSexId(UserSexEnum.Unknown.getId());
//				sysUser.setSexName(UserSexEnum.Unknown.getName());
//			}
			Integer status = (Integer)responseMap.get("status");
			if(status==null){
				sysUser.setWeiXinStatusId(WeixinStatusEnum.Status0.getId());
				sysUser.setWeiXinStatusDesc(WeixinStatusEnum.Status0.getName());
			}else if(status==1){
				sysUser.setWeiXinStatusId(WeixinStatusEnum.Status1.getId());
				sysUser.setWeiXinStatusDesc(WeixinStatusEnum.Status1.getName());
			}else if(status==2){
				sysUser.setWeiXinStatusId(WeixinStatusEnum.Status2.getId());
				sysUser.setWeiXinStatusDesc(WeixinStatusEnum.Status2.getName());
			}else if(status==4){
				sysUser.setWeiXinStatusId(WeixinStatusEnum.Status4.getId());
				sysUser.setWeiXinStatusDesc(WeixinStatusEnum.Status4.getName());
			}
			return sysUser;
		}
		return null;
	}
	
	public static List<SysUser> getDeptUser(String corpid,String corpsecret,String weiXinDeptId){
		String accessToken = gettoken(corpid,corpsecret);
		String getUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token="+StringUtil.defaultString(accessToken)+"&department_id="+StringUtil.defaultIfEmpty(weiXinDeptId,"1")+"&fetch_child=1&status=0";
		String response = _get(getUrl);
		System.err.println(response);
		Map responseMap = JSON.parseObject(response, Map.class);
		Integer errcode = (Integer)responseMap.get("errcode");
		List<SysUser> sysUserList = new ArrayList<SysUser>();
		if(errcode==0){
			JSONArray array =  (JSONArray) responseMap.get("userlist");
			for(int i = 0; array!=null&&i < array.size();i++){
				JSONObject jobj = (JSONObject)array.get(i);
				String userid = jobj.getString("userid");
				SysUser sysUser = getUser(corpid, corpsecret, userid);
				sysUserList.add(sysUser);
			}
		}
		return sysUserList;
	}

	public static boolean saveUser(String corpid, String corpsecret, String weiXinDeptId, SysUser user) {
		SysUser sysUser = getUser(corpid,corpsecret,user.getUserFlow());
		if(sysUser==null){
			return createUser(corpid, corpsecret, weiXinDeptId, user);
		}else{
			if(UserStatusEnum.Locked.getId().equals(user.getStatusId())){
				return delUser(corpid, corpsecret, user.getUserFlow());
			}else{
				return updateUser(corpid, corpsecret, weiXinDeptId, user);
			}
		}
	}
	
	public static boolean createDept(String corpid,String corpsecret,SysDept dept){
		String accessToken = gettoken(corpid,corpsecret);
		String createUrl = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+StringUtil.defaultString(accessToken);
		String jsonData = "{"
				   +"\"name\": \""+StringUtil.defaultString(dept.getDeptName())+"\","
				   +"\"parentid\": \"1\","
				   +"\"order\": \""+StringUtil.defaultString(dept.getOrdinal()+"")+"\""
				+"}";
		String response = _post(createUrl,jsonData);
		System.err.println(response);
		Map responseMap = JSON.parseObject(response, Map.class);
		Integer errcode = (Integer)responseMap.get("errcode");
		if(errcode==0){
			return true;
		}
		return false;
	}

	public static boolean updateDept(String corpid, String corpsecret, SysDept dept) {
		String accessToken = gettoken(corpid,corpsecret);
		String createUrl = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+StringUtil.defaultString(accessToken);
		String jsonData = "{"
				   +"\"id\": \""+StringUtil.defaultString(dept.getDeptCode())+"\","
				   +"\"name\": \""+StringUtil.defaultString(dept.getDeptName())+"\","
				   +"\"parentid\": \"1\","
				   +"\"order\": \""+StringUtil.defaultString(dept.getOrdinal()+"")+"\""
				+"}";
		String response = _post(createUrl,jsonData);
		System.err.println(response);
		Map responseMap = JSON.parseObject(response, Map.class);
		Integer errcode = (Integer)responseMap.get("errcode");
		if(errcode==0){
			return true;
		}
		return false;
	}

	public static List<SysDept> getDept(String corpid,String corpsecret,SysDept dept){
		String accessToken = gettoken(corpid,corpsecret);
		String createUrl = "https://qyapi.weixin.qq.com/cgi-bin/department/list?access_token="+StringUtil.defaultString(accessToken);
		String response = _get(createUrl);
		System.err.println(response);
		Map responseMap = JSON.parseObject(response, Map.class);
		Integer errcode = (Integer)responseMap.get("errcode");
		List<SysDept> sysDeptList = new ArrayList<SysDept>();
		if(errcode==0){
			JSONArray array =  (JSONArray) responseMap.get("department");
			for(int i = 0; array!=null&&i < array.size();i++){
				JSONObject jobj = (JSONObject)array.get(i);
				String id = jobj.getString("id");
				String name = jobj.getString("name");
				String parentid = jobj.getString("parentid");
				SysDept sysDept = new SysDept();
				sysDept.setDeptCode(id);
				sysDept.setDeptName(name);
				sysDeptList.add(sysDept);
			}
		}
		return sysDeptList;
	}



	public static String sendMsg(String corpid,String corpsecret,String agentid,String getUserFlow,String content){
		String accessToken = gettoken(corpid,corpsecret);
		String sendUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+StringUtil.defaultString(accessToken);
		String jsonData = "{"
				  +" \"touser\": \""+StringUtil.defaultString(getUserFlow)+"\","
//				  +" \"toparty\": \" PartyID1 | PartyID2 \","
//				  +" \"totag\": \" TagID1 | TagID2 \","
				  +" \"msgtype\": \"text\","
				  +" \"agentid\": \""+StringUtil.defaultString(agentid)+"\","
				  +" \"text\": {"
				  +"     \"content\": \""+StringUtil.defaultString(content)+"\""
				  +" },"
				  +" \"safe\":\"0\""
			+"}";
		String response = _post(sendUrl,jsonData);
		System.err.println(response);
		return response;
	}

	public static String getUserFlowByCode(String corpid,String corpsecret,String agentid,String code){
		String accessToken = gettoken(corpid,corpsecret);
		String sendUrl = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token="+StringUtil.defaultString(accessToken)+"&code="+code+"&agentid="+agentid;
		String response = _get(sendUrl);
		System.err.println(response);
		Map responseMap = JSON.parseObject(response, Map.class);
		Integer errcode = (Integer)responseMap.get("errcode");
		if(errcode==null){
			String userId = (String)responseMap.get("UserId");
			return userId;
		}
		return null;
	}

	public static void main(String[] args){
//		getDeptUser("wx00e7d6d01ec6fb1b", "uUa2SGDxXHhM1MSjIlFegUopw2RQadAXMhxdyNYCjBs7zAnAf--LtTHiN4-9CDrv","1");
//		for(int i=0;i<10;i++){
//			sendMsg("wx00e7d6d01ec6fb1b", "uUa2SGDxXHhM1MSjIlFegUopw2RQadAXMhxdyNYCjBs7zAnAf--LtTHiN4-9CDrv","0", "shijian1", "内容"+i);
//		}
		createDept("wx00e7d6d01ec6fb1b", "uUa2SGDxXHhM1MSjIlFegUopw2RQadAXMhxdyNYCjBs7zAnAf--LtTHiN4-9CDrv", null);
	}

	private static String _get(String url){
		HttpClient httpClient = new HttpClient();
		GetMethod request = new GetMethod(url);
		HttpMethodParams param = request.getParams();
		param.setContentCharset("UTF-8");

		StringBuffer buf = new StringBuffer();
		BufferedReader in = null;
		try {
			httpClient.executeMethod(request);
			InputStream stream = request.getResponseBodyAsStream();
			in = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			String line;
			while (null != (line = in.readLine())) {
				buf.append(line).append("\n");
			}
			request.releaseConnection();
		} catch (Exception e) {
            logger.error("", e);
			if (in != null) {
				try {
					in.close();
				} catch (Exception e1) {
//					e1.printStackTrace();
				}
			}
		}

		String result = buf.toString();
		return result;

	}

	private static String _post(String url,String data){
		HttpClient httpClient = new HttpClient();
		PostMethod request = new PostMethod(url);
		HttpMethodParams param = request.getParams();
		param.setContentCharset("UTF-8");
		request.setRequestBody(data);
		StringBuffer buf = new StringBuffer();
		BufferedReader in = null;
		try {
			httpClient.executeMethod(request);
			InputStream stream = request.getResponseBodyAsStream();
			in = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
			String line;
			while (null != (line = in.readLine())) {
				buf.append(line).append("\n");
			}
			request.releaseConnection();
		} catch (Exception e) {
            logger.error("", e);
			if (in != null) {
				try {
					in.close();
				} catch (Exception e1) {
//					e1.printStackTrace();
				}
			}
		}

		String result = buf.toString();
		return result;
	}
}
