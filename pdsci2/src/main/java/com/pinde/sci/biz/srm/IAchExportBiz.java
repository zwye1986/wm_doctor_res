package com.pinde.sci.biz.srm;

import com.pinde.sci.model.srm.AchBookExportExt;
import com.pinde.sci.model.srm.AchPatentExportExt;
import com.pinde.sci.model.srm.AchSatExportExt;
import com.pinde.sci.model.srm.AchThesisExportExt;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-12-14.
 */
public interface IAchExportBiz {

    List<AchThesisExportExt> searchAchThesisExportByDept(Map<String,String> map);
    List<AchThesisExportExt> searchAchThesis(Map<String,String> map);

    List<AchSatExportExt> searchAchSat(Map<String,String> map);

    List<AchBookExportExt> searchAchBook(Map<String,String> map);

    List<AchPatentExportExt> searchAchPatent(Map<String,String> map);
}
