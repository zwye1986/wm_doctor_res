package com.pinde.sci.biz.res;

import com.pinde.core.model.TjDocinfo;
import com.pinde.core.model.TjExamInfo;
import com.pinde.sci.common.util.ExcelUtile;
import com.pinde.core.model.NjDocinfoExt;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by www.0001.Ga on 2016-11-02.
 */
public interface INjResExamBiz {

    List<NjDocinfoExt> searchNjDocinfoExt(TjDocinfo searchDocinfo);

    List<NjDocinfoExt> readNjDocinfo(TjDocinfo searchDocinfo,List<String> orgNames);

    void editNjDocinfo(List<NjDocinfoExt> extList,TjExamInfo examInfo);

    ExcelUtile importDocInfoExcel(MultipartFile file);

    ExcelUtile importExamUserExcel(MultipartFile file,String examFlow);

    int editDocInfo(TjDocinfo docInfo);

    TjDocinfo readDocInfo(String idNo);

    List<TjExamInfo> selTjExamInfos(TjExamInfo examInfo);

    List<TjExamInfo> selTjExamInfoById(String recordFlow);

    int editExamInfo(TjExamInfo examInfo);

    int setExamInfo(String userFlow,String recordFlow);

    int delExamInfo(String recordFlow);
    int delDocInfo(String userFlow,String idNo);

    void chargeExportList(List<NjDocinfoExt> extList, HttpServletResponse response) throws IOException;

    void chargeExportList2(List<NjDocinfoExt> extList, HttpServletResponse response) throws IOException;
}
