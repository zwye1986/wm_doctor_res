package com.pinde.sci.biz.pubedu.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.pubedu.IStudyCourseBiz;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.StudyCourseDetailInfoMapper;
import com.pinde.sci.dao.base.StudyCourseDetailMapper;
import com.pinde.sci.dao.base.StudyCourseMapper;
import com.pinde.core.common.enums.pubedu.CourseDetailTypeEnum;
import com.pinde.sci.model.mo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 公共科目学习平台课程业务实现类
 *
 */
@Service
//@Transactional(rollbackFor = Exception.class)
public class StudyCourseBizImpl implements IStudyCourseBiz {
	@Autowired
	private StudyCourseMapper courseMapper;
	@Autowired
	private StudyCourseDetailMapper detailMapper;
	@Autowired
	private StudyCourseDetailInfoMapper detailInfoMapper;

	@Override
	public List<StudyCourse> findStudyCourseList(StudyCourse studyCourse) {
		StudyCourseExample example = new StudyCourseExample();
        StudyCourseExample.Criteria criteria = example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y);
		if(null != studyCourse){
			if(StringUtil.isNotBlank(studyCourse.getCourseFlow())){
				criteria.andCourseFlowEqualTo(studyCourse.getCourseFlow());
			}
			if(StringUtil.isNotBlank(studyCourse.getCourseName())){
				criteria.andCourseNameLike(studyCourse.getCourseName());
			}
		}
		example.setOrderByClause("ORDINAL ASC");
		return courseMapper.selectByExample(example);
	}

	@Override
	public StudyCourse searchCourseByFlow(String courseFlow) {
		if(StringUtil.isNotBlank(courseFlow)){
			return courseMapper.selectByPrimaryKey(courseFlow);
		}
		return null;
	}

	@Override
	public List<StudyCourseDetail> findStudyCourseDetailByFlow(String courseFlow) {
		if(StringUtil.isNotBlank(courseFlow)){
			StudyCourseDetailExample example= new StudyCourseDetailExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andCourseFlowEqualTo(courseFlow);
			return detailMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public List<StudyCourseDetailInfo> findDetailInfoByFlow(String courseFlow) {
		if(StringUtil.isNotBlank(courseFlow)){
			StudyCourseDetailInfoExample example = new StudyCourseDetailInfoExample();
            example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.FLAG_Y).andCourseFlowEqualTo(courseFlow);
			return detailInfoMapper.selectByExample(example);
		}
		return null;
	}

	@Override
	public StudyCourseDetail findCourseDetailByRecordFlow(String detailFlow) {
		if(StringUtil.isNotBlank(detailFlow)){
			return detailMapper.selectByPrimaryKey(detailFlow);
		}
		return null;
	}

	@Override
	public StudyCourseDetailInfo findDetailInfoByRecordFlow(String detailInfoFlow) {
		if(StringUtil.isNotBlank(detailInfoFlow)){
			return detailInfoMapper.selectByPrimaryKey(detailInfoFlow);
		}
		return null;
	}

	@Override
	public  List<StudyCourseDetail> findStudyCourseByCourseFlow(String courseFlow){
		if(StringUtil.isNotBlank(courseFlow)){
			StudyCourseDetailExample detailExample = new StudyCourseDetailExample();
			detailExample.createCriteria().andCourseFlowEqualTo(courseFlow);
		     return detailMapper.selectByExample(detailExample);
		}
		return null;
	}

	@Override
	public List<StudyCourse> findStudyCourseListOrderByASC(){
		StudyCourseExample example = new StudyCourseExample();
        example.createCriteria().andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
		example.setOrderByClause("ORDINAL asc");
		return courseMapper.selectByExample(example);
	}

	@Override
	public int insert(StudyCourse studyCourse){
		int count=0;
		if(null!=studyCourse){
			count = courseMapper.insert(studyCourse);
		}
		return count;
	}

	@Override
	public int updateStudyCourse(StudyCourse studyCourse){
		int count =0;
		if(null!=studyCourse){
			count=courseMapper.updateByPrimaryKey(studyCourse);
		}
		return count;
	}

	@Override
	public int deleteByCourseFlow(String courseFlow){
		int count = 0;
		if(StringUtil.isNotBlank(courseFlow)){
			count = courseMapper.deleteByPrimaryKey(courseFlow);
		}
		return count;
	}

	@Override
	public int updateStudyCourseBySelect(String type,String courseFlow,String courseName){
		int count =0;
		if(StringUtil.isNotBlank(type)&&StringUtil.isNotBlank(courseFlow)&&StringUtil.isNotBlank(courseName)){
			StudyCourseExample example = new StudyCourseExample();
			example.createCriteria().andCourseFlowEqualTo(courseFlow).andCourseNameEqualTo(courseName);
			StudyCourse course = new StudyCourse();
			if("courseImgUrl".equals(type)){
				course.setCourseImgUrl("");
			}
			if("detailImgUrl".equals(type)){
				course.setDetailImgUrl("");
			}
			count = courseMapper.updateByExampleSelective(course,example);
		}
		return count;
	}
	@Override
	public List<StudyCourseDetailInfo> findStudyCourseDetailInfoByCourseFlow(String courseFlow){
		if(StringUtil.isNotBlank(courseFlow)){
			StudyCourseDetailInfoExample infoExample = new StudyCourseDetailInfoExample();
			infoExample.createCriteria().andCourseFlowEqualTo(courseFlow);
			return detailInfoMapper.selectByExample(infoExample);
		}
		return null;
	}

	@Override
	public int insertDetailInfo(StudyCourseDetailInfo detailInfo){
		int count=0;
		if(null!=detailInfo){
			detailInfo.setRecordFlow(PkUtil.getUUID());
			detailInfo.setCourseFlow(detailInfo.getCourseFlow());
			detailInfo.setDetailFlow(detailInfo.getDetailFlow());
			detailInfo.setDetailTypeName(CourseDetailTypeEnum.getNameById(detailInfo.getDetailTypeId()));
            detailInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			detailInfo.setCreateTime(DateUtil.getCurrDateTime());
			detailInfo.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			detailInfo.setModifyTime(DateUtil.getCurrDateTime());
			detailInfo.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			count = detailInfoMapper.insert(detailInfo);
		}
		return count;
	}
	@Override
	public int updateStudyCourseDetail(StudyCourseDetail courseDetail){
		int count=0;
		if(null!=courseDetail){
			courseDetail.setModifyTime(DateUtil.getCurrDateTime());
			courseDetail.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			count= detailMapper.updateByPrimaryKeySelective(courseDetail);
		}
		return count;
	}


	@Override
	public int updateStudyCourseDetailInfo(StudyCourseDetailInfo detailInfo){
		int count=0;
		if(null!=detailInfo){
			detailInfo.setModifyTime(DateUtil.getCurrDateTime());
			detailInfo.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			count = detailInfoMapper.updateByPrimaryKeySelective(detailInfo);
		}
		return count;
	}

	@Override
	public int deleteByRecordFlow(String recordFlow){
		int count=0;
		if(StringUtil.isNotBlank(recordFlow)){
			count = detailInfoMapper.deleteByPrimaryKey(recordFlow);
		}
		return count;
	}

	@Override
	public int deleteByDetailFlow(StudyCourseDetail studyCourseDetail){
		int count=0;
		if(null!=studyCourseDetail){
			studyCourseDetail.setModifyTime(DateUtil.getCurrDateTime());
			studyCourseDetail.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			studyCourseDetail.setDetailUrl("");
			studyCourseDetail.setDetailName("");
			count = detailMapper.updateByPrimaryKeySelective(studyCourseDetail);
		}
		return count;
	}

	@Override
	public int insertStudyDetail(StudyCourseDetail detail){
		int count=0;
		if(null!=detail){
			detail.setDetailFlow(PkUtil.getUUID());
            detail.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
			detail.setCreateTime(DateUtil.getCurrDateTime());
			detail.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			detail.setModifyTime(DateUtil.getCurrDateTime());
			detail.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
			count = detailMapper.insert(detail);
		}
		return count;
	}
}
