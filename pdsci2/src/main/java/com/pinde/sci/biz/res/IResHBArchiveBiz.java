package com.pinde.sci.biz.res;

import com.pinde.core.model.PubUserResume;
import com.pinde.core.model.ResArchiveSequence;
import com.pinde.core.model.SysUser;
import com.pinde.sci.model.mo.ResDoctor;
import com.pinde.sci.model.res.ResDoctorExt;
import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2017-05-22.
 */
public interface IResHBArchiveBiz {
	/**
	 * 当前存档时间是否存在
	 * @param archiveTime
	 * @return
	 */
	int checkArchive(String archiveTime,String sessionNumber);

	boolean saveArchiveInfo4Hb(String archiveTime,String sessionNumber) throws DocumentException;

	//获取所有存档记录
	List<ResArchiveSequence> allResArchiveSequence();

	//根据主键读存档记录
	ResArchiveSequence readResArchiveSequence(String archiveFlow);

	//根据条件查询存档记录
	List<ResDoctorExt> searchArchiveList(Map<String,Object> paramMap);

	//根据USER_FLOW查询存档USER
	SysUser readUserArchive(Map<String,Object> paramMap);

	//根据USER_FLOW查询存档DOCTOR
	ResDoctor readDoctorArchive(Map<String,Object> paramMap);

	//根据USER_FLOW查询存档USERRESUME
	PubUserResume readUserResumeArchive(Map<String,Object> paramMap);
}
