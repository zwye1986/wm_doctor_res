package com.pinde.sci.biz.jszy;

import com.pinde.sci.form.jszy.JszyBackTrainForm;
import com.pinde.sci.model.mo.ResDocotrDelayTeturn;
import com.pinde.sci.model.mo.ResDoctorRecruitWithBLOBs;
import com.pinde.sci.model.mo.ResRec;
import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IJszyResRecBiz {
    int saveBackTrain(JszyBackTrainForm form, ResDoctorRecruitWithBLOBs recruitWithBLOBs);

//    int saveDelayInfo(JszyBackTrainForm backTrainForm, ResDoctorRecruitWithBLOBs recruitWithBLOBs);
     int saveDelayInfo(ResDocotrDelayTeturn docotrDelayTeturn);
    Map<Object, Object> paraseBackTrain(List<ResRec> recList, String sessionNumber, String speName, String reasonId, String policyId) throws DocumentException;

    Map<String, Object> paraseDelayInfo(List<ResRec> recList) throws DocumentException;
}

	
