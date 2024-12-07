package com.pinde.sci.biz.pub.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pub.IPubUserResumeBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.PubUserResumeMapper;
import com.pinde.sci.model.mo.PubUserResume;
import com.pinde.sci.model.mo.PubUserResumeExample;
import com.pinde.sci.model.mo.SysUser;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 
 * @author tiger
 *
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class PubResumeBizImpl implements IPubUserResumeBiz{
	@Autowired
	private PubUserResumeMapper userResumpMapper;
    private static Logger logger = LoggerFactory.getLogger(PubResumeBizImpl.class);

	/**
	 * 获取个人履历
	 */
	@Override
	public PubUserResume readPubUserResume(String userFlow) {
		if(StringUtil.isNotBlank(userFlow)){
			PubUserResume resume = userResumpMapper.selectByPrimaryKey(userFlow);
			return resume;
		}
		return null;
	}

	@Override
	public List<PubUserResume> findPubUserResume() {
		PubUserResumeExample example = new PubUserResumeExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		return userResumpMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int savePubUserResume(SysUser user, PubUserResume resume) {
		//根据用户的流水号判断新增、修改履历
		if(StringUtil.isNotBlank(resume.getUserFlow())){//修改
			GeneralMethod.setRecordInfo(resume, false);
			return userResumpMapper.updateByPrimaryKeyWithBLOBs(resume);
		}else{//新增
			if(null != user){
				resume.setUserFlow(user.getUserFlow());
				resume.setUserName(user.getUserName());
				GeneralMethod.setRecordInfo(resume, true);
				return userResumpMapper.insert(resume);
			}else{
                return com.pinde.core.common.GlobalConstant.ZERO_LINE;
			}
		}
	}

	@Override
	public <T> T converyToJavaBean(String xml,Class<T> clazz) throws DocumentException {
		T t=null;
		try {
			t = clazz.newInstance();
		} catch (Exception e) {
            logger.error("", e);
		}
		if (StringUtil.isNotBlank(xml)) {
			if(t!=null){
				//解析xml返回对象
				Document dom = null;
				try {
					dom = DocumentHelper.parseText(xml);
				} catch (DocumentException e) {
                    logger.error("", e);
				}
				Element root = dom.getRootElement();
				List<Element> elements=root.elements();
				if(elements!=null && !elements.isEmpty()){
					for(Element e: elements){
						try {
							setValue(t,e.getName(),e.getText());
						} catch (Exception e1) {
							e1.printStackTrace();
						} finally {
							continue;
						}
					}
				}
			}
		}
		return t;
	}

	@Override
	public <T> T converyToJavaBean(String xml,Class<T> clazz,String flag) throws DocumentException {
		T t=null;
		try {
			t = clazz.newInstance();
		} catch (Exception e) {
            logger.error("", e);
		}
		if(t!=null){
			//解析xml返回对象
			Document dom = null;
			dom = DocumentHelper.parseText(xml);
			Element root = dom.getRootElement();
			String className = t.getClass().getName();
			className = className.substring(className.lastIndexOf(".")+1).toLowerCase();
			if(className.equals(root.getName().toLowerCase())){
				List<Element> elements=root.elements();
				if(elements!=null && !elements.isEmpty()){
					for(Element e: elements){
						setValue(t,e.getName(),e.getText());
					}
				}
			}
		}
		return t;
	}

	@Override
	public List<PubUserResume> findPubUserResumeByUserFlows(List<String> userFlows) {
		if(userFlows != null && userFlows.size() > 0){
			PubUserResumeExample example = new PubUserResumeExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y)
			.andUserFlowIn(userFlows);
			return userResumpMapper.selectByExampleWithBLOBs(example);
		}else {
			return null;
		}
	}

	private void setValue(Object obj,String attrName,String attrValue){
		try{
			Class<?> objClass = obj.getClass();
			String firstLetter = attrName.substring(0,1).toUpperCase();
			String methedName = "set"+firstLetter+attrName.substring(1);
			Method setMethod = objClass.getMethod(methedName, String.class);
			setMethod.invoke(obj, attrValue);
		}catch(Exception e){
            logger.error("", e);
		}
	}
}
