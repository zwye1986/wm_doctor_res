package com.pinde.res.biz.jswjw.impl;

import com.pinde.app.common.GlobalConstant;
import com.pinde.core.commom.GeneralEnum;
import com.pinde.core.util.DateUtil;
import com.pinde.core.util.JaxbUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;

import com.pinde.res.biz.jswjw.IResDoctorBiz;
import com.pinde.res.biz.jswjw.IResDoctorProcessBiz;
import com.pinde.res.biz.jswjw.IResScoreBiz;
import com.pinde.res.dao.jswjw.ext.ResRecExtMapper;
import com.pinde.sci.dao.base.*;

import com.pinde.sci.model.mo.*;
import com.pinde.sci.model.mo.ResDoctorExample.Criteria;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Pattern;


@Service
@Transactional(rollbackFor=Exception.class)
public class ResDoctorBizImpl implements IResDoctorBiz {
	@Autowired
	private ResDoctorMapper doctorMapper;

	public static String _doubleTrans(double d) {
		if ((double) Math.round(d) - d == 0.0D)
			return String.valueOf((long) d);
		else
			return String.valueOf(d);
	}

	@Override
	public	List<ResDoctor> searchDoctor(){
		ResDoctorExample example = new ResDoctorExample();
		example.createCriteria().andRecordStatusEqualTo(GlobalConstant.RECORD_STATUS_Y);
		return doctorMapper.selectByExample(example);
	}

	@Override
	public ResDoctor readDoctor(String recordFlow){
		return doctorMapper.selectByPrimaryKey(recordFlow);
	}


	@Override
	public List<ResDoctor> searchByDoc(ResDoctor doctor){
		ResDoctorExample example = new ResDoctorExample();
		Criteria criteria = example.createCriteria();
		setCriteria(doctor,criteria);
		return doctorMapper.selectByExample(example);
	}

	@Override
	public int editDoctor(ResDoctor doctor) {
		if(doctor!=null){
			if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
				return doctorMapper.updateByPrimaryKeySelective(doctor);
			}
		}
		return GlobalConstant.ZERO_LINE;
	}

	private Criteria setCriteria(ResDoctor doctor, Criteria criteria){
		if(doctor!=null){
			if(StringUtil.isNotBlank(doctor.getSessionNumber())){
				criteria.andSessionNumberEqualTo(doctor.getSessionNumber());
			}
			if(StringUtil.isNotBlank(doctor.getTrainingSpeId())){
				criteria.andTrainingSpeIdEqualTo(doctor.getTrainingSpeId());
			}
			if(StringUtil.isNotBlank(doctor.getGraduatedId())){
				criteria.andGraduatedIdEqualTo(doctor.getGraduatedId());
			}
			if(StringUtil.isNotBlank(doctor.getRecordStatus())){
				criteria.andRecordStatusEqualTo(doctor.getRecordStatus());
			}
			if(StringUtil.isNotBlank(doctor.getDoctorCategoryId())){
				criteria.andDoctorCategoryIdEqualTo(doctor.getDoctorCategoryId());
			}
			if(StringUtil.isNotBlank(doctor.getOrgFlow())){
				criteria.andOrgFlowEqualTo(doctor.getOrgFlow());
			}
			if(StringUtil.isNotBlank(doctor.getDoctorName())){
				criteria.andDoctorNameEqualTo(doctor.getDoctorName());
			}
			if(StringUtil.isNotBlank(doctor.getDoctorFlow())){
				criteria.andDoctorFlowEqualTo(doctor.getDoctorFlow());
			}
			if(StringUtil.isNotBlank(doctor.getSchStatusId())){
				criteria.andSchStatusIdEqualTo(doctor.getSchStatusId());
			}
			if(StringUtil.isNotBlank(doctor.getDeptFlow())){
				criteria.andDeptFlowEqualTo(doctor.getDeptFlow());
			}
			if(StringUtil.isNotBlank(doctor.getGroupId())){
				criteria.andGroupIdEqualTo(doctor.getGroupId());
			}
			if(StringUtil.isNotBlank(doctor.getSelDeptFlag())){
				criteria.andSelDeptFlagEqualTo(doctor.getSelDeptFlag());
			}
			if(StringUtil.isNotBlank(doctor.getSchFlag())){
				criteria.andSchFlagEqualTo(doctor.getSchFlag());
			}
		}
		return criteria;
	}
	

}
 