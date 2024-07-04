package com.pinde.res.biz.zsyk.impl;

import com.pinde.app.common.PasswordUtil;
import com.pinde.core.util.CodeUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.res.biz.zsyk.IZsykAppBiz;
import com.pinde.res.dao.zsyk.ext.ZsykAppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class ZsykAppBizImpl implements IZsykAppBiz {
	@Autowired
	private ZsykAppMapper appMapper;

	@Override
	public Map<String,String> login(String userCode, String password) {
		String md5Password = _md5Dnet(password, userCode);
		List<Map<String,String>> userList =  appMapper.login(userCode);
		if(userList.size()==1){
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
		return appMapper.readUser(userFlow);
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
	
}
