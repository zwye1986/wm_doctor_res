package com.pinde.sci.biz.inx.impl;

import com.pinde.core.common.enums.InfoStatusEnum;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.inx.IinxColumnManageBiz;
import com.pinde.sci.biz.inx.IinxInfoManageBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.base.InxInfoMapper;
import com.pinde.sci.dao.inx.InxInfoExtMapper;
import com.pinde.sci.form.inx.InxInfoForm;
import com.pinde.sci.model.inx.InxInfoExt;
import com.pinde.sci.model.mo.InxColumn;
import com.pinde.sci.model.mo.InxInfo;
import com.pinde.sci.model.mo.InxInfoExample;
import com.pinde.sci.model.mo.InxInfoExample.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class InxInfoManageBizImpl implements IinxInfoManageBiz {
	@Autowired
	private InxInfoMapper inxInfoMapper;
	@Autowired
	private InxInfoExtMapper inxInfoExtMapper;
	@Autowired
	private IinxColumnManageBiz columnManageBiz;

	@Override
	public int save(InxInfo info) {
		info.setInfoFlow(PkUtil.getUUID());
//		info.setInfoStatusId(InfoStatusEnum.Edit.getId());
//		info.setInfoStatusName(InfoStatusEnum.Edit.getName());
		info.setInfoStatusId(InfoStatusEnum.Passed.getId());
		info.setInfoStatusName(InfoStatusEnum.Passed.getName());
		InxColumn col = columnManageBiz.getById(info.getColumnId());
		info.setColumnName(col.getColumnName());
		info.setViewNum(new Long(0));
		GeneralMethod.setRecordInfo(info, true);
		return this.inxInfoMapper.insert(info);
	}

	@Override
	public String uploadImg(HttpServletRequest request, String fileInputName) {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		MultipartFile file = multipartRequest.getFile(fileInputName);
		String fileName = "";
		if(file!=null){
			List<String> mimeList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))){
				mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
			}
			List<String> suffixList = new ArrayList<String>();
			if(StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))){
				suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
			}
			
			String fileType = file.getContentType();//MIME类型;
			fileName = file.getOriginalFilename();//文件名
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			if(!(mimeList.contains(fileType)&&suffixList.contains(suffix))){
                return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_TYPE_ERROR;
			}
			long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
			if(file.getSize()>limitSize*1024*1024){
                return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize + "M";
			}
			try {
				/*创建目录*/
				String dateString = DateUtil.getCurrDate2();
				String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+"//titleImages//" + dateString ;
				File fileDir = new File(newDir);
				if(!fileDir.exists()){
					fileDir.mkdirs();
				}
				/*文件名*/
				fileName = file.getOriginalFilename();
				fileName = PkUtil.getUUID()+fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(fileDir, fileName); 
				file.transferTo(newFile);
				return "success:"+dateString+"/"+fileName;
			} catch (Exception e) {
                logger.error("", e);
                return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}
	
	@Override
	public InxInfoExt getExtByFlow(String infoFlow) {
		return this.inxInfoExtMapper.selectExtByFlow(infoFlow);
	}

	@Override
	public String getImageBaseUrl() {
		String inxImageUrl = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_url"));
		if(StringUtil.isNotBlank(inxImageUrl)){
			String temp = String.valueOf(inxImageUrl.charAt(inxImageUrl.length()-1));
			if(!"/".equals(temp)){
				inxImageUrl = inxImageUrl+"/";			
			}
			inxImageUrl = inxImageUrl +"titleImages/";
			return inxImageUrl;
		}
		return null;
		
	}

	@Override
	public int update(InxInfo info) {
		if(info!=null){
			if(StringUtil.isNotBlank(info.getColumnId())){
				InxColumn col = columnManageBiz.getById(info.getColumnId());
				info.setColumnName(col.getColumnName());
			}
			GeneralMethod.setRecordInfo(info, false);
			return this.inxInfoMapper.updateByPrimaryKeySelective(info);
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public InxInfo getByFlow(String infoFlow) {
		return this.inxInfoMapper.selectByPrimaryKey(infoFlow);
	}

	@Override
	public int updateInfoStatus(List<String> infoFlows, String infoStatusId) {
		InxInfoForm form = new InxInfoForm();
		form.setInfoFlows(infoFlows);
		form.setInfoStatusId(infoStatusId);
		form.setInfoStatusName(InfoStatusEnum.getNameById(infoStatusId));
		return this.inxInfoExtMapper.updateInfoState(form) ;
		
	}

	@Override
	public int deleteTitleImg(String infoFlow) {
		if(StringUtil.isNotBlank(infoFlow)){
			InxInfo info = getByFlow(infoFlow);
			if(info!=null){ //修改资讯时删除
				String img = info.getTitleImg();
				if(StringUtil.isNotBlank(img)){
					try {
						//img = img.replace("/", "\\");
						img = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+File.separator+"titleImages" +File.separator+img;
						File file = new File(img);
						if(file.exists()){
							boolean delRestlt =  file.delete();
							if(delRestlt){//删除成功
								info.setTitleImg(null);
								return this.inxInfoMapper.updateByPrimaryKey(info);
							}
						}
					} catch (Exception e) {
                        logger.error("", e);
                        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
					}
					
				}
			}else{//新增资讯时删除
				try {
					String img = infoFlow;
					img = img.replace("/", "\\");
					img = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir"))+"//titleImages//" + img;
					File file = new File(img);
					if(file.exists()){
						boolean delRestlt =  file.delete();
						if(delRestlt){
                            return com.pinde.core.common.GlobalConstant.ONE_LINE;
						}
					}
				} catch (Exception e) {
                    logger.error("", e);
                    return com.pinde.core.common.GlobalConstant.ZERO_LINE;
				}
			}
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<InxInfo> getList(InxInfoForm form) {
		InxInfoExample example = new InxInfoExample();
		Criteria criteria = example.createCriteria();
        criteria.andInfoStatusIdNotEqualTo(InfoStatusEnum.Failure.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(StringUtil.isNotBlank(form.getRecordStatus())){
			criteria.andRecordStatusEqualTo(form.getRecordStatus());
		}
		if(StringUtil.isNotBlank(form.getColumnId())){
			criteria.andColumnIdEqualTo(form.getColumnId());
		}
		if(StringUtil.isNotBlank(form.getRoleFlow())){
			criteria.andRoleFlowEqualTo(form.getRoleFlow());
		}
		if(null!=form.getRoleFlows()&&form.getRoleFlows().size()>0){
			criteria.andRoleFlowIn(form.getRoleFlows());
		}
		if(null!=form.getColumnIds()&&form.getColumnIds().size()>0){
			criteria.andColumnIdIn(form.getColumnIds());
		}
		if(StringUtil.isNotBlank(form.getInfoTitle())){
			criteria.andInfoTitleLike("%"+form.getInfoTitle()+"%");
		}
		if(StringUtil.isNotBlank(form.getStartDate())){
			criteria.andInfoTimeGreaterThanOrEqualTo(form.getStartDate());
		}
		if(StringUtil.isNotBlank(form.getEndDate())){
			criteria.andInfoTimeLessThanOrEqualTo(form.getEndDate());
		}
		if(StringUtil.isNotBlank(form.getInfoStatusId())){
			criteria.andInfoStatusIdEqualTo(form.getInfoStatusId());
		}
		example.setOrderByClause("MODIFY_TIME DESC, INFO_TIME DESC");
		return this.inxInfoMapper.selectByExample(example);
	}

	@Override
	public int modifyInxInfo(InxInfo info) {
		if(StringUtil.isNotBlank(info.getInfoFlow())){
			GeneralMethod.setRecordInfo(info, false);
			return this.inxInfoMapper.updateByPrimaryKeySelective(info);
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

    private static Logger logger = LoggerFactory.getLogger(InxInfoManageBizImpl.class);


}
