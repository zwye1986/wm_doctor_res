package com.pinde.sci.biz.res.impl;

import com.pinde.core.model.ResUserSpe;
import com.pinde.core.model.ResUserSpeExample;
import com.pinde.core.model.ResUserSpeExample.Criteria;
import com.pinde.core.model.SysUser;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.res.IResUserSpeBiz;
import com.pinde.sci.biz.sys.IUserBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.dao.base.ResUserSpeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class ResUserSpeBizImpl implements IResUserSpeBiz {
	@Autowired
	private ResUserSpeMapper userSpeMapper;
	@Autowired
	private IUserBiz userBiz;

//	@Override
//	public ResUserSpe readUserSpe(String recordFlow){
//		return userSpeMapper.selectByPrimaryKey(recordFlow);
//	}
	
	@Override
	public List<ResUserSpe> searchUserSpesByUser(String userFlow){
		List<ResUserSpe> userSpes = null;
		if(StringUtil.isNotBlank(userFlow)){
			ResUserSpeExample example = new ResUserSpeExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y)
			.andUserFlowEqualTo(userFlow);
			userSpes = userSpeMapper.selectByExample(example);
		}
		return userSpes;
	}
	@Override
	public int editUserSpe(ResUserSpe userSpe){
		if(userSpe!=null){
			if(StringUtil.isNotBlank(userSpe.getRecordFlow())){
				GeneralMethod.setRecordInfo(userSpe, false);
				return userSpeMapper.updateByPrimaryKeySelective(userSpe);
			}else{
				userSpe.setRecordFlow(PkUtil.getUUID());
				GeneralMethod.setRecordInfo(userSpe, true);
				return userSpeMapper.insertSelective(userSpe);
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

//	@Override
//	public List<ResUserSpe> searchAllUserSpesByUser(String userFlow){
//		List<ResUserSpe> userSpes = null;
//		if(StringUtil.isNotBlank(userFlow)){
//			ResUserSpeExample example = new ResUserSpeExample();
//			example.createCriteria().andUserFlowEqualTo(userFlow);
//			userSpes = userSpeMapper.selectByExample(example);
//		}
//		return userSpes;
//	}
	
	@Override
	public int editUserSpesBySpes(String userFlow,List<String> speIds){
		if(StringUtil.isNotBlank(userFlow)){
			//读取用户
			SysUser user = userBiz.readSysUser(userFlow);
			if(user!=null){
				ResUserSpeExample example = new ResUserSpeExample();
				
				ResUserSpe userSpe = new ResUserSpe();
				//将不再speids内的禁用
                userSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_N);
				Criteria criteria = example.createCriteria().andUserFlowEqualTo(userFlow);
				if(speIds!=null && speIds.size()>0){
					criteria.andTrainingSpeIdNotIn(speIds);
				}
				userSpeMapper.updateByExampleSelective(userSpe,example);
				
				//将speids内的启用
				if(speIds!=null && speIds.size()>0){
                    userSpe.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
					example.clear();
					example.createCriteria().andUserFlowEqualTo(userFlow).andTrainingSpeIdIn(speIds);
					userSpeMapper.updateByExampleSelective(userSpe,example);
					
					//将未保存的数据保存
					List<ResUserSpe> userSpeList = searchUserSpesByUser(userFlow);
					if(userSpeList!=null && userSpeList.size()>0){
						for(ResUserSpe rus : userSpeList){
							String speId = rus.getTrainingSpeId();
							speIds.remove(speId);
						}
					}
					
					//创建新的关系记录
					userSpe.setUserFlow(userFlow);
					userSpe.setOrgFlow(user.getOrgFlow());
					userSpe.setOrgName(user.getOrgName());
					for(String speId : speIds){
						if(!StringUtil.isNotBlank(speId))
							continue;
						
						userSpe.setRecordFlow(null);
						userSpe.setTrainingSpeId(speId);
                        userSpe.setTrainingSpeName(com.pinde.core.common.enums.DictTypeEnum.DoctorTrainingSpe.getDictNameById(speId));
						editUserSpe(userSpe);
					}
				}
                return com.pinde.core.common.GlobalConstant.ONE_LINE;
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}
	
	@Override
	public List<ResUserSpe> searchAllUserSpes(List<String> userFlows){
		List<ResUserSpe> userSpes = null;
		if(userFlows!=null && userFlows.size()>0){
			ResUserSpeExample example = new ResUserSpeExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y).andUserFlowIn(userFlows);
			userSpes = userSpeMapper.selectByExample(example);
		}
		return userSpes;
	}
}
