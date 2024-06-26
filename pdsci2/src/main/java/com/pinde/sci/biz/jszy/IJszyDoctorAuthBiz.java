package com.pinde.sci.biz.jszy;

import com.pinde.sci.form.jszy.JszyBackTrainForm;
import com.pinde.sci.model.mo.DoctorAuth;
import com.pinde.sci.model.mo.ResDoctorRecruitWithBLOBs;
import com.pinde.sci.model.mo.ResRec;
import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IJszyDoctorAuthBiz {

    /**
     * 保存/更新学员声明
     * @param doctorAuth
     * @return
     */
    int edit(DoctorAuth doctorAuth);

    /**
     * 根据operUserFlow查找学员声明
     * @param operUserFlow
     * @return
     */
    DoctorAuth searchAuthByOperUserFlow(String operUserFlow);
    DoctorAuth readByFlow(String recordFlow);

    List<DoctorAuth> searchAuthsByOperUserFlow(String operUserFlow);

    Map<String,String> saveInfo(String recordFlow, MultipartFile checkFile, String fileAddress);
}

	
