package com.pinde.sci.biz.portal.impl;

import com.pinde.core.common.enums.InfoStatusEnum;
import com.pinde.core.common.sci.dao.*;
import com.pinde.core.model.*;
import com.pinde.core.model.PortalInfoExample.Criteria;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.portal.IPortalColumnManageBiz;
import com.pinde.sci.biz.portal.IPortalInfoManageBiz;
import com.pinde.sci.common.GeneralMethod;
import com.pinde.sci.common.InitConfig;
import com.pinde.sci.dao.portal.PortalInfoExtMapper;
import com.pinde.sci.form.portal.PortalInfoForm;
import com.pinde.core.model.PortalInfoExt;
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
import java.util.Map;

@Service
//@Transactional(rollbackFor=Exception.class)
public class PortalInfoManageBizImpl implements IPortalInfoManageBiz {
	@Autowired
	private PortalInfoMapper inxInfoMapper;
	@Autowired
	private PortalInfoExtMapper inxInfoExtMapper;
	@Autowired
	private IPortalColumnManageBiz columnManageBiz;
	@Autowired
	private PortalFileMapper portalFileMapper;
	@Autowired
	private PortalSuggestMapper portalSuggestMapper;
	@Autowired
	private JsszportalCommunicationMainMapper communicationMainMapper;
	@Autowired
	private JsszportalCommunicationReMapper communicationReMapper;

    private static final Logger logger = LoggerFactory.getLogger(PortalInfoManageBizImpl.class);


	@Override
	public int save(PortalInfo info) {
		info.setInfoFlow(PkUtil.getUUID());
		if (StringUtil.isBlank(info.getInfoStatusId())) {
			info.setInfoStatusId(InfoStatusEnum.Edit.getId());
			info.setInfoStatusName(InfoStatusEnum.Edit.getName());
		}
//		info.setInfoStatusId(InfoStatusEnum.Passed.getId());
//		info.setInfoStatusName(InfoStatusEnum.Passed.getName());
		PortalColumn col = columnManageBiz.getById(info.getColumnId());
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
		if (file != null) {
			List<String> mimeList = new ArrayList<String>();
			if (StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")))) {
				mimeList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_mime")).split(","));
			}
			List<String> suffixList = new ArrayList<String>();
			if (StringUtil.isNotBlank(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")))) {
				suffixList = Arrays.asList(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_support_suffix")).split(","));
			}

			String fileType = file.getContentType();//MIME类型;
			fileName = file.getOriginalFilename();//文件名
			String suffix = fileName.substring(fileName.lastIndexOf("."));//后缀名
			if (!(mimeList.contains(fileType) && suffixList.contains(suffix))) {
                return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_TYPE_ERROR;
			}
			long limitSize = Long.parseLong(StringUtil.defaultString(InitConfig.getSysCfg("inx_image_limit_size")));//图片大小限制
			if (file.getSize() > limitSize * 1024 * 1024) {
                return com.pinde.core.common.GlobalConstant.UPLOAD_IMG_SIZE_ERROR + limitSize + "M";
			}
			try {
				/*创建目录*/
				String dateString = DateUtil.getCurrDate2();
				String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + "//titleImages//" + dateString;
				File fileDir = new File(newDir);
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				/*文件名*/
				fileName = file.getOriginalFilename();
				fileName = PkUtil.getUUID() + fileName.substring(fileName.lastIndexOf("."));
				File newFile = new File(fileDir, fileName);
				file.transferTo(newFile);
				return "success:" + dateString + "/" + fileName;
			} catch (Exception e) {
                logger.error("", e);
                return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
			}
		}
        return com.pinde.core.common.GlobalConstant.UPLOAD_FAIL;
	}

	@Override
	public PortalInfoExt getExtByFlow(String infoFlow) {

		return this.inxInfoExtMapper.selectExtByFlow(infoFlow);
	}

	@Override
	public String getImageBaseUrl() {
		String inxImageUrl = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_url"));
		if (StringUtil.isNotBlank(inxImageUrl)) {
			String temp = String.valueOf(inxImageUrl.charAt(inxImageUrl.length() - 1));
			if (!"/".equals(temp)) {
				inxImageUrl = inxImageUrl + "/";
			}
			inxImageUrl = inxImageUrl + "titleImages/";
			return inxImageUrl;
		}
		return null;

	}

	@Override
	public int update(PortalInfo info) {
		if (info != null) {
			if (StringUtil.isNotBlank(info.getColumnId())) {
				PortalColumn col = columnManageBiz.getById(info.getColumnId());
				info.setColumnName(col.getColumnName());
			}
			GeneralMethod.setRecordInfo(info, false);
			return this.inxInfoMapper.updateByPrimaryKeySelective(info);
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public PortalInfo getByFlow(String infoFlow) {
		return this.inxInfoMapper.selectByPrimaryKey(infoFlow);
	}

	@Override
	public int updateInfoStatus(List<String> infoFlows, String infoStatusId) {
		PortalInfoForm form = new PortalInfoForm();
		form.setInfoFlows(infoFlows);
		form.setInfoStatusId(infoStatusId);
		form.setInfoStatusName(InfoStatusEnum.getNameById(infoStatusId));
		return this.inxInfoExtMapper.updateInfoState(form);

	}

	@Override
	public int deleteTitleImg(String infoFlow) {
		if (StringUtil.isNotBlank(infoFlow)) {
			PortalInfo info = getByFlow(infoFlow);
			if (info != null) { //修改资讯时删除
				String img = info.getTitleImg();
				if (StringUtil.isNotBlank(img)) {
					try {
						//img = img.replace("/", "\\");
						img = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "titleImages" + File.separator + img;
						File file = new File(img);
						if (file.exists()) {
							boolean delRestlt = file.delete();
							if (delRestlt) {//删除成功
								info.setTitleImg(null);
								return this.inxInfoMapper.updateByPrimaryKey(info);
							}
						}
					} catch (Exception e) {
                        logger.error("", e);
                        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
					}

				}
			} else {//新增资讯时删除
				try {
					String img = infoFlow;
					img = img.replace("/", "\\");
					img = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + "//titleImages//" + img;
					File file = new File(img);
					if (file.exists()) {
						boolean delRestlt = file.delete();
						if (delRestlt) {
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
	public List<PortalInfo> getList(PortalInfoForm form,List<String> statusList) {
		PortalInfoExample example = new PortalInfoExample();
		Criteria criteria = example.createCriteria();
        criteria.andInfoStatusIdNotEqualTo(InfoStatusEnum.Failure.getId()).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if (StringUtil.isNotBlank(form.getRecordStatus())) {
			criteria.andRecordStatusEqualTo(form.getRecordStatus());
		}
		if (StringUtil.isNotBlank(form.getColumnId())) {
			criteria.andColumnIdEqualTo(form.getColumnId());
		}
		if (StringUtil.isNotBlank(form.getColumnIdLike())) {
			criteria.andColumnIdLike(form.getColumnIdLike());
		}
		if (null != form.getColumnIds() && form.getColumnIds().size() > 0) {
			criteria.andColumnIdIn(form.getColumnIds());
		}
		if (StringUtil.isNotBlank(form.getInfoTitle())) {
			criteria.andInfoTitleLike("%" + form.getInfoTitle() + "%");
		}
		if (StringUtil.isNotBlank(form.getStartDate())) {
			criteria.andInfoTimeGreaterThanOrEqualTo(form.getStartDate());
		}
		if (StringUtil.isNotBlank(form.getEndDate())) {
			criteria.andInfoTimeLessThanOrEqualTo(form.getEndDate());
		}
		if (StringUtil.isNotBlank(form.getInfoStatusId())) {
			criteria.andInfoStatusIdEqualTo(form.getInfoStatusId());
		}
		if (StringUtil.isNotBlank(form.getUserFlow())) {
			criteria.andCreateUserFlowEqualTo(form.getUserFlow());
		}
		if (StringUtil.isNotBlank(form.getCityId())) {
			criteria.andCityIdEqualTo(form.getCityId());
		}
		if (StringUtil.isNotBlank(form.getWeType())) {
			criteria.andWestEaetTypeEqualTo(form.getWeType());
		}
		if(statusList!=null&&!statusList.isEmpty()){
			criteria.andInfoStatusIdIn(statusList);
		}
		example.setOrderByClause("INFO_TIME DESC");
		return this.inxInfoMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int modifyPortalInfo(PortalInfo info) {
		if (StringUtil.isNotBlank(info.getInfoFlow())) {
			GeneralMethod.setRecordInfo(info, false);
			return this.inxInfoMapper.updateByPrimaryKeySelective(info);
		}
        return com.pinde.core.common.GlobalConstant.ZERO_LINE;
	}

	@Override
	public List<Map<String, Object>> getStatistics(Map<String, Object> paramMap) {
		return inxInfoExtMapper.getStatistics(paramMap);
	}

	@Override
	public int edit(PortalFile file) {
		String recordFlow = file.getRecordFlow();
		if (StringUtil.isNotBlank(recordFlow)) {
			GeneralMethod.setRecordInfo(file, false);
			return portalFileMapper.updateByPrimaryKeySelective(file);
		} else {
			file.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(file, true);
			return portalFileMapper.insert(file);
		}
	}

	@Override
	public List<PortalFile> getFileList(PortalFile file) {
		PortalFileExample example = new PortalFileExample();
		PortalFileExample.Criteria criteria = example.createCriteria();
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(file!=null){
			if (StringUtil.isNotBlank(file.getFileName())) {
				criteria.andFileNameLike("%" + file.getFileName() + "%");
			}
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return portalFileMapper.selectByExample(example);
	}

	@Override
	public int uploadFile(MultipartFile uploadFile) {
		if (uploadFile != null && (!uploadFile.isEmpty())) {
			String originalFileName = uploadFile.getOriginalFilename();
			String type = originalFileName.split("\\.")[1];
			//定义上传路径
			String dateString = DateUtil.getCurrDate2();
			String newDir = StringUtil.defaultString(InitConfig.getSysCfg("upload_base_dir")) + File.separator + "portalFile" + File.separator + dateString;
			File fileDir = new File(newDir);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}
			//重命名上传后的文件名
			String newFileName = PkUtil.getUUID()+"."+type;
			File newFile = new File(fileDir, newFileName);
			try {
				uploadFile.transferTo(newFile);
			} catch (Exception e) {
				throw new RuntimeException("文件上传异常");
			}
			String filePath = "/portalFile/" + dateString + "/" + newFileName;
			PortalFile portalFile = new PortalFile();
			portalFile.setRecordFlow(PkUtil.getUUID());
			portalFile.setFileName(originalFileName);
			portalFile.setFileSize(getPrintSize(uploadFile.getSize()));
			portalFile.setFileUrl(filePath);
			portalFile.setUploadTime(DateUtil.getCurrDate());
			GeneralMethod.setRecordInfo(portalFile,true);
			return portalFileMapper.insert(portalFile);
		}
		return 0;
	}

	public static String getPrintSize(long size) {
		//如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
		if (size < 1024) {
            return size + "B";
		} else {
			size = size / 1024;
		}
		//如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
		//因为还没有到达要使用另一个单位的时候
		//接下去以此类推
		if (size < 1024) {
            return size + "KB";
		} else {
			size = size / 1024;
		}
		if (size < 1024) {
			//因为如果以MB为单位的话，要保留最后1位小数，
			//因此，把此数乘以100之后再取余
			size = size * 100;
            return size / 100 + "."
                    + size % 100 + "MB";
		} else {
			//否则如果要以GB为单位的，先除于1024再作同样的处理
			size = size * 100 / 1024;
            return size / 100 + "."
                    + size % 100 + "GB";
		}
	}

	@Override
	public List<PortalSuggest> getSuggestList(PortalSuggest suggest) {
		PortalSuggestExample example = new PortalSuggestExample();
        PortalSuggestExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(suggest!=null){
			if(StringUtil.isNotBlank(suggest.getSuggestTitle())){
				criteria.andSuggestTitleLike("%"+suggest.getSuggestTitle()+"%");
			}
			if(StringUtil.isNotBlank(suggest.getShowFlag())){
				criteria.andShowFlagEqualTo(suggest.getShowFlag());
			}
		}
		example.setOrderByClause("SHOW_FLAG,CREATE_TIME DESC");
		return portalSuggestMapper.selectByExample(example);
	}

	@Override
	public PortalSuggest readSuggest(String recordFlow) {
		return portalSuggestMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int editSuggest(PortalSuggest suggest) {
		String recordFlow = suggest.getRecordFlow();
		if(StringUtil.isNotBlank(recordFlow)){
			GeneralMethod.setRecordInfo(suggest,false);
			return portalSuggestMapper.updateByPrimaryKeySelective(suggest);
		}else {
			suggest.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(suggest,true);
			return portalSuggestMapper.insert(suggest);
		}
	}

	@Override
	public List<JsszportalCommunicationMain> searchCommunicationMain(JsszportalCommunicationMain communicationMain, String order) {
		JsszportalCommunicationMainExample example = new JsszportalCommunicationMainExample();
        JsszportalCommunicationMainExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(communicationMain!=null){
			if(StringUtil.isNotBlank(communicationMain.getDiseaseId())){
				criteria.andDiseaseIdEqualTo(communicationMain.getDiseaseId());
			}
			if(StringUtil.isNotBlank(communicationMain.getTitle())){
				criteria.andTitleLike("%"+communicationMain.getTitle()+"%");
			}
		}
		if("time".equals(order)){
			example.setOrderByClause("UPLOAD_TIME DESC");
		}else if("hot".equals(order)){
			example.setOrderByClause("TO_NUMBER(REPLAY_TIMES) DESC");
		}else{
			example.setOrderByClause("UPLOAD_TIME DESC");
		}

		return communicationMainMapper.selectByExample(example);
	}

	@Override
	public JsszportalCommunicationMain readCommunicationMain(String recordFlow) {
		return communicationMainMapper.selectByPrimaryKey(recordFlow);
	}

	@Override
	public int editCommunicationMain(JsszportalCommunicationMain communicationMain) {
		String recordFlow = communicationMain.getRecordFlow();
		if(StringUtil.isBlank(recordFlow)){
			communicationMain.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(communicationMain,true);
			return communicationMainMapper.insertSelective(communicationMain);
		}else{
			GeneralMethod.setRecordInfo(communicationMain,false);
			return communicationMainMapper.updateByPrimaryKeySelective(communicationMain);
		}
	}

	@Override
	public List<JsszportalCommunicationRe> searchCommunicationRe(JsszportalCommunicationRe communicationRe) {
		JsszportalCommunicationReExample example = new JsszportalCommunicationReExample();
        JsszportalCommunicationReExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		if(communicationRe!=null){
			if(StringUtil.isNotBlank(communicationRe.getMainFlow())){
				criteria.andMainFlowEqualTo(communicationRe.getMainFlow());
			}
		}
		example.setOrderByClause("CREATE_TIME DESC");
		return communicationReMapper.selectByExampleWithBLOBs(example);
	}

	@Override
	public int editCommunicationRe(JsszportalCommunicationRe communicationRe) {
		String recordFlow = communicationRe.getRecordFlow();
		if(StringUtil.isBlank(recordFlow)){
			communicationRe.setRecordFlow(PkUtil.getUUID());
			GeneralMethod.setRecordInfo(communicationRe,true);
			return communicationReMapper.insertSelective(communicationRe);
		}else{
			GeneralMethod.setRecordInfo(communicationRe,false);
			return communicationReMapper.updateByPrimaryKeySelective(communicationRe);
		}
	}
}
