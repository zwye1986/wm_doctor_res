package com.pinde.sci.biz.jsres;

import com.pinde.core.model.ResLectureRandomScan;
import com.pinde.core.model.ResLectureRandomSign;

import java.util.List;

public interface IResLectureRandomSignBiz {

    List<ResLectureRandomSign> searchRandomByLectureFlow(String lectureFlow);

    int saveRandom(ResLectureRandomSign randomSign);

    ResLectureRandomSign read(String randomFlow);

    List<ResLectureRandomScan> searchRandomScan(String operUserFlow,String lectureFlow,String randomFlow);

    List<ResLectureRandomScan> searchIsScan(String lectureFlow, List<String> roles);

    //ResLectureRandomScan searchByOperUserFlowAndLectureFlow(String operUserFlow,String lectureFlow,String randomFlow);

    List<ResLectureRandomScan> getRandomScanList(List<String> randomFlows, String lectureFlow);
}
