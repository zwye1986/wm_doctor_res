package com.pinde.sci.dao.srm;

import com.pinde.sci.model.srm.AchBookExportExt;
import com.pinde.sci.model.srm.AchPatentExportExt;
import com.pinde.sci.model.srm.AchSatExportExt;
import com.pinde.sci.model.srm.AchThesisExportExt;

import java.util.List;
import java.util.Map;

/**
 * Created by www.0001.Ga on 2016-12-14.
 */
public interface AchExportExtMapper {


    List<AchThesisExportExt> searchAchThesisExportByDept(Map<String,String> map);

    List<AchThesisExportExt> searchAchThesisExt(Map<String,String> map);

    List<AchSatExportExt> searchAchSatExt(Map<String,String> map);

    List<AchBookExportExt> searchAchBookExt(Map<String,String> map);

    List<AchPatentExportExt> searchAchPatentExt(Map<String,String> map);

}
