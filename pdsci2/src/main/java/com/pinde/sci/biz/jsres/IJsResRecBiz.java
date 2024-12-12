package com.pinde.sci.biz.jsres;

import com.pinde.core.model.ResDoctorRecruit;
import com.pinde.core.model.ResDoctorRecruitWithBLOBs;
import com.pinde.sci.form.jsres.BackTrainForm;
import com.pinde.sci.model.mo.ResRec;
import org.dom4j.DocumentException;

import java.util.List;
import java.util.Map;

/**
 * @author tiger
 */
public interface IJsResRecBiz {
    int saveBackTrain(BackTrainForm form, ResDoctorRecruitWithBLOBs recruitWithBLOBs);
//在res_rec表拆分的时候将该方法注释掉
//    int saveBackTrainWithFiles(BackTrainForm form, ResDoctorRecruitWithBLOBs recruitWithBLOBs,List<PubFile> pubFiles);
//在res_rec表拆分的时候将该方法注释掉
//    int saveDelayInfo(BackTrainForm backTrainForm, ResDoctorRecruitWithBLOBs recruitWithBLOBs);

    Map<Object, Object> paraseBackTrain(List<ResRec> recList, String sessionNumber, String speName, String reasonId, String policyId) throws DocumentException;

    Map<String, Object> paraseDelayInfo(List<ResRec> recList) throws DocumentException;

    /**
     * 省厅审核
     * @param resRec
     * @param backTrainForm
     * @param doctorRecruit
     * @return
     * @throws DocumentException
     */
    int checkBackTrain(ResRec resRec, BackTrainForm backTrainForm, ResDoctorRecruit doctorRecruit) throws DocumentException;
}

	
