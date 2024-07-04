package com.pinde.res.dao.jszy.ext;

import com.pinde.sci.model.mo.ResDiscipleRecordInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by pdkj on 2016/10/8.
 */
public interface ResFollowTeacherRecordExtMapper {


    List<ResDiscipleRecordInfo> searchFolowTeacherRecordByType(@Param("userFlow") String userFlow, @Param("typeId") String typeId);

    Map<String,Object> folowTeacherRecordFinishMap(@Param("userFlow") String userFlow);

    Map<String,Object> discipleNoteInfoFinishMap(@Param("userFlow") String userFlow, @Param("noteTypeId") String noteTypeId);

    Map<String,Object> typicalCasesFinishMap(@Param("userFlow") String userFlow);
}
