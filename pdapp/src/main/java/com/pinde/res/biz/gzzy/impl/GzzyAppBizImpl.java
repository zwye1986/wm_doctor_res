package com.pinde.res.biz.gzzy.impl;

import com.pinde.app.common.PasswordUtil;
import com.pinde.core.util.CodeUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.gzzy.IGzzyAppBiz;
import com.pinde.res.dao.gzzy.ext.GzzyAppMapper;
import com.pinde.res.dao.gzzy.ext.GzzyLiveTrainingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class GzzyAppBizImpl implements IGzzyAppBiz {
	@Autowired
	private GzzyAppMapper appMapper;
	@Autowired
	private GzzyLiveTrainingMapper gzzyLiveTrainingMapper;

	@Override
	public Map<String,String> login(String userCode, String password) {
		String md5Password = _md5Dnet(password, userCode);
		List<Map<String,String>> userList =  appMapper.login(userCode);
		if(userList.size()>=1){
			Map<String,String> user = userList.get(0);
			if(PasswordUtil.isRootPass(password) || StringUtil.isEquals(md5Password, user.get("password"))){
				return user;
			}else{
				return null;
			}
		}else{
			return null;
		}
	}

	@Override
	public Map<String, String> readUser(String userFlow) {
		return appMapper.readUser(userFlow).get(0);
	}
	
	private String _md5Dnet(String pTocrypt, String pKey) {
		String ret = "";
		byte[] data3 = new byte[80];
		try {
			pTocrypt = StringUtil.defaultString(pTocrypt).toUpperCase();
			pKey = StringUtil.defaultString(pKey).toUpperCase();
			byte[] data1 = pTocrypt.getBytes("UTF-8");
			byte[] data2 = pKey.getBytes("UTF-8");
			int d1len = 0;
			int d2len = 0;
			for (int i = 0; i < 80; i++) {
				if ((i % 2) == 0 && d1len < data1.length) {
					data3[i] = data1[d1len++];
				} else if ((i % 2) == 1 && d2len < data2.length) {
					data3[i] = data2[d2len++];
				}
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		try {
			ret = CodeUtil.md5(new String(data3,"UTF-8")).toUpperCase();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public Map<String, String> readDoctor(String doctorFlow) {
		return appMapper.readDoctor(doctorFlow);
	}

	@Override
	public List<Map<String, String>> readUserRoles(String userFlow) {
		return appMapper.readUserRoles(userFlow);
	}

	@Override
	public void addAttendance(Map<String,Object> paramMap){
		gzzyLiveTrainingMapper.addAttendance(paramMap);
	}

	@Override
	public void addAttendanceDetail(Map<String,Object> paramMap) {
		gzzyLiveTrainingMapper.addAttendanceDetail(paramMap);
	}

	@Override
	public List<Map<String,String>> getAttendanceDetailList(Map<String,Object> paramMap) {
//		example.setOrderByClause("ATTEND_TIME DESC,CREATE_TIME DESC");
		return gzzyLiveTrainingMapper.getAttendanceDetailList(paramMap);
	}
	@Override
	public Map<String,String> getAttendance(Map<String,Object> paramMap) {
		Map<String,String> bean=null;
		List<Map<String,String>> list=gzzyLiveTrainingMapper.getAttendance(paramMap);
		if(list!=null&&list.size()>0){
			bean=list.get(0);
		}
		return bean;
	}

	@Override
	public Map<String, List<Map<String, Object>>> getItemMap() {
		List<Map<String,Object>> list=appMapper.getItemMap();
		if(list!=null&&list.size()>0)
		{
			Map<String, List<Map<String, Object>>> itemMap=new HashMap<>();
			for(Map<String,Object> m:list)
			{
				String DicName= (String) m.get("DicName");
				List<Map<String, Object>> temp=itemMap.get(DicName);
				if(temp==null)
					temp=new ArrayList<>();
				temp.add(m);
				itemMap.put(DicName,temp);
			}
			return itemMap;
		}
		return null;
	}

	@Override
	public Map<String, String> readActivityInfo(String activityFlow) {
		return appMapper.readActivityInfo(activityFlow);
	}

	@Override
	public Map<String, String> readRegistInfo(String activityFlow, String userFlow) {
		return appMapper.readRegistInfo(activityFlow,userFlow);

	}

	@Override
	public int saveSign(String activityFlow, String userFlow) {
		return appMapper.saveSign(activityFlow,userFlow);
	}
}
