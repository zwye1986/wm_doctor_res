package com.pinde.sci.biz.res;

import java.util.List;
import java.util.Map;

public interface IResEntryReportBiz {

    List<Map<String,String>> getReportList(Map<String, Object> param);
}
