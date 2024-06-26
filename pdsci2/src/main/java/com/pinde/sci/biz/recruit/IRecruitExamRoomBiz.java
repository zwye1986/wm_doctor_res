package com.pinde.sci.biz.recruit;

import com.pinde.sci.model.mo.RecruitExamRoom;

import java.util.List;

public interface IRecruitExamRoomBiz {

    /**
     * 查询考场
     * @param roomName
     * @param roomAddress
     * @param examNum
     * @return
     */
    List<RecruitExamRoom> searchExamRoomList( String orgFlow,String roomName, String roomAddress,String examNum);

    /**
     * 增加考场
     * @param recruitExamRoom
     * @return
     */
    int addExamRoom(RecruitExamRoom recruitExamRoom);

    RecruitExamRoom searchExamRoomByFlow(String roomFlow);

    List<RecruitExamRoom> searchAllExamRoom(String orgFlow);

    Integer updateExamRoom(RecruitExamRoom editInfo);

    Integer changeExamRoomStatus(String roomFlow, String recordStatus);

    List<String> searchAllExamRoomName(String orgFlow);

    RecruitExamRoom searchExamRoomByName(String roomName,String orgFlow);

    List<RecruitExamRoom> orgExamRooms(String orgFlow);
}
