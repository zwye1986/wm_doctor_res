package com.pinde.sci.biz.sys.impl;

import com.pinde.core.model.SysCfg;
import com.pinde.core.model.SysCfgExample;
import com.pinde.core.model.SysCfgExample.Criteria;
import com.pinde.core.model.SysCfgLogWithBLOBs;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.sys.ICfgBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.SysCfgLogMapper;
import com.pinde.sci.dao.base.SysCfgMapper;
import com.pinde.sci.dao.sys.SysCfgExtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
//@Transactional(rollbackFor = Exception.class)
public class CfgBizImpl implements ICfgBiz {
	@Autowired
	private SysCfgLogMapper sysCfgLogMapper;
	@Autowired
	private SysCfgMapper sysCfgMapper;
	@Autowired
	private SysCfgExtMapper sysCfgExtMapper;

	@Override
	public SysCfg read(String cfgCode) {
		return sysCfgMapper.selectByPrimaryKey(cfgCode);
	}

	@Override
	public List<SysCfg> readByExample(SysCfgExample example) {
		return sysCfgMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int add(SysCfg cfg) {
		GeneralMethod.setRecordInfo(cfg, true);
		return sysCfgMapper.insert(cfg);
	}

	@Override
	public int mod(SysCfg cfg) {
		GeneralMethod.setRecordInfo(cfg, false);
		return sysCfgMapper.updateByPrimaryKeySelective(cfg);
	}

	@Override
	public List<SysCfg> search(SysCfg cfg) {
		List<String> wsIdList = new ArrayList<String>();
        wsIdList.add(com.pinde.core.common.GlobalConstant.SYS_WS_ID);
		SysCfgExample example = new SysCfgExample();
		Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(cfg.getWsId())) {
			wsIdList.add(cfg.getWsId());
			criteria.andWsIdIn(wsIdList);
		}
		return sysCfgMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public void save(List<SysCfg> sysCfgList) {
		for (SysCfg sysCfg : sysCfgList) {
			SysCfg temp = this.read(sysCfg.getCfgCode());
			if (temp == null) {
				this.add(sysCfg);
			} else {
				this.mod(sysCfg);
			}
		}
	}

	@Override
	public String getPageSize(HttpServletRequest request) {
		String cfgValue = null;
        String getPath = StringUtil.defaultString(request.getServletPath());

		if(GlobalContext.getCurrentUser()!=null){
			String cfgCode = getPath + "/" +GlobalContext.getCurrentUser().getUserFlow();
			String sessionValue = (String) GlobalContext.getSessionAttribute(cfgCode);
			if(StringUtil.isNotBlank(sessionValue)){
				cfgValue = sessionValue;
            } else if (StringUtil.isEquals(InitConfig.getSysCfg("sys_page_size_cfg"), com.pinde.core.common.GlobalConstant.FLAG_Y)) {//从数据库取值
				SysCfg cfg = this.read(cfgCode);
				if(cfg!=null){
					cfgValue = cfg.getCfgValue();
					GlobalContext.setSessionAttribute(cfgCode, cfgValue);
				}
			}
		}
		
		if(StringUtil.isBlank(cfgValue)){
            cfgValue = com.pinde.core.common.GlobalConstant.DEFAULT_PAGE_SIZE + "";
		}
		return cfgValue;
	}

	@Override
	public void savePageSize(HttpServletRequest request) {
        String cfgValue = request.getParameter(com.pinde.core.common.GlobalConstant.PAGE_SIZE);
		if(StringUtil.isBlank(cfgValue)){
            cfgValue = com.pinde.core.common.GlobalConstant.DEFAULT_PAGE_SIZE + "";
		}
        String getPath = request.getParameter(com.pinde.core.common.GlobalConstant.PAGE_SERVLET_PATH);
		String cfgCode = getPath + "/" +GlobalContext.getCurrentUser().getUserFlow();
        if (StringUtil.isEquals(InitConfig.getSysCfg("sys_page_size_cfg"), com.pinde.core.common.GlobalConstant.FLAG_Y)) {
			SysCfg cfg = this.read(cfgCode);
			if(cfg==null){
				cfg = new SysCfg();
				cfg.setCfgCode(cfgCode);
				cfg.setCfgValue(cfgValue);
				cfg.setWsId("page");
				cfg.setWsName("分页配置");
				GeneralMethod.setRecordInfo(cfg, true);
				this.add(cfg);
			}else{
				cfg.setCfgValue(cfgValue);
				GeneralMethod.setRecordInfo(cfg, false);
				this.mod(cfg);
			}
		}
		GlobalContext.setSessionAttribute(cfgCode, cfgValue);
	}

	@Override
	public int saveSysCfg(SysCfg start,SysCfg end) {
		if (start!=null) {
			if(StringUtil.isNotBlank(start.getCfgCode())){
				GeneralMethod.setRecordInfo(start, false);
				return sysCfgMapper.updateByPrimaryKeySelective(start);
			}if(StringUtil.isBlank(start.getCfgCode())){
				start.setCfgCode("choose_course_start_time");
				GeneralMethod.setRecordInfo(start, true);
				return sysCfgMapper.insert(start);
			}
		}
		if (end!=null) {
			if(StringUtil.isNotBlank(end.getCfgCode())){
				GeneralMethod.setRecordInfo(end, false);
				return sysCfgMapper.updateByPrimaryKeySelective(end);
			}
			if(StringUtil.isBlank(end.getCfgCode())){
				end.setCfgCode("choose_course_end_time");
				GeneralMethod.setRecordInfo(end, true);
				return sysCfgMapper.insert(end);
			}
		}
		return 0;
	}

//	@Override
//	public List<SysCfg> searchCfgByCfgCodeList(SysCfg sysCfg, List<String> cfgCodeList) {
//		SysCfgExample example = new SysCfgExample();
//		Criteria criteria = example.createCriteria().andCfgCodeIn(cfgCodeList).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
//		if(StringUtil.isNotBlank(sysCfg.getWsId())){
//			criteria.andWsIdEqualTo(sysCfg.getWsId());
//		}
//		return sysCfgMapper.selectByExample(example);
//	}

	@Override
	public int saveSysCfgInfo(List<SysCfg> sysCfgList) {
		if(sysCfgList!=null && !sysCfgList.isEmpty()){
			SysCfg sysCfg = sysCfgList.get(0);
			SysCfg temp = this.read(sysCfg.getCfgCode());
			if (temp == null) {
				return add(sysCfg);
			} else {
				return mod(sysCfg);
			}
		}else{
            return com.pinde.core.common.GlobalConstant.ZERO_LINE;
		}
	}


	@Override
	public int getOpenSwitchCount(Map<String, Object> paramMap){
		return sysCfgExtMapper.getOpenSwitchCount(paramMap);
	}

	@Override
	public List<SysCfg> searchByPreAndSuf(Map<String, Object> paramMap){
		return sysCfgExtMapper.searchByPreAndSuf(paramMap);
	}

	@Override
	public int saveList(List<SysCfg> sysCfgList, HttpServletRequest request) {
		if(sysCfgList!=null&&sysCfgList.size()>0) {
			ServletContext application = request.getServletContext();
			Map<String, String> sysCfgMap = (Map<String, String>) application.getAttribute("sysCfgMap");
			for (SysCfg sysCfg : sysCfgList) {
				SysCfg temp = this.read(sysCfg.getCfgCode());
				if (temp == null) {
					this.add(sysCfg);
				} else {
					this.mod(sysCfg);
				}
				sysCfgMap.put(sysCfg.getCfgCode(), sysCfg.getCfgValue());
			}
			return sysCfgList.size();
		}
		return 0;
	}

	@Override
	public void saveLog(List<SysCfgLogWithBLOBs> sysCfgLogList) {
		for (SysCfgLogWithBLOBs sysCfg : sysCfgLogList) {
			sysCfg.setCfgFlow(PkUtil.getUUID());
			this.addCfgLog(sysCfg);
		}
	}

	@Override
	public void addCfgLog(SysCfgLogWithBLOBs cfg) {
		GeneralMethod.setRecordInfo(cfg, true);
		 sysCfgLogMapper.insert(cfg);
	}
}
