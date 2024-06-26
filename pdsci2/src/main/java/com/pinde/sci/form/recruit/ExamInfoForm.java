package com.pinde.sci.form.recruit;

import com.pinde.sci.model.mo.RecruitExamInfo;
import com.pinde.sci.model.mo.RecruitExamRoomInfo;

import java.io.Serializable;
import java.util.List;

/**
 * @BelongsProject: 18svn
 * @BelongsPackage: com.pinde.sci.form.recruit
 * @Author: yex
 * @CreateTime: 2019-05-07 14:28
 * @Description: ${Description}
 */
public class ExamInfoForm extends RecruitExamInfo implements Serializable {

    private static final long serialVersionUID = 4961548952138944295L;

    private List<RecruitExamRoomInfo> roomInfos;

    public List<RecruitExamRoomInfo> getRoomInfos() {
        return roomInfos;
    }

    public void setRoomInfos(List<RecruitExamRoomInfo> roomInfos) {
        this.roomInfos = roomInfos;
    }
}
