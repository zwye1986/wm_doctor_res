package com.pinde.sci.biz.recruit.impl;

import com.pinde.core.util.DateUtil;
import com.pinde.core.util.PkUtil;
import com.pinde.core.util.StringUtil;
import com.pinde.sci.biz.recruit.IRecruitExamRoomBiz;
import com.pinde.sci.common.GlobalContext;
import com.pinde.sci.dao.base.RecruitExamRoomMapper;
import com.pinde.sci.model.mo.RecruitExamRoom;
import com.pinde.sci.model.mo.RecruitExamRoomExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Transactional(rollbackFor=Exception.class)
public class RecruitExamRoomBizImpl implements IRecruitExamRoomBiz {

    @Autowired
    private RecruitExamRoomMapper recruitExamRoomMapper;



    @Override
    public List<RecruitExamRoom> searchAllExamRoom(String orgFlow) {
        RecruitExamRoomExample example = new RecruitExamRoomExample();
        RecruitExamRoomExample.Criteria criteria = example.createCriteria();
        criteria.andOrgFlowEqualTo(orgFlow);
        return recruitExamRoomMapper.selectByExample(example);
    }


    @Override
    public List<RecruitExamRoom> searchExamRoomList(String orgFlow, String roomName, String roomAddress, String examNum) {
        RecruitExamRoomExample example = new RecruitExamRoomExample();
        RecruitExamRoomExample.Criteria criteria = example.createCriteria();
        criteria.andOrgFlowEqualTo(orgFlow);
        if (StringUtil.isNotBlank(roomAddress)){
            criteria.andRoomAddressLike("%"+roomAddress+"%");
        }
        if (StringUtil.isNotBlank(roomName)){
            criteria.andRoomNameLike("%"+roomName+"%");
        }
        if (StringUtil.isNotBlank(examNum)){
            criteria.andExamNumEqualTo(examNum);
        }
        return recruitExamRoomMapper.selectByExample(example);
    }

    @Override
    public int addExamRoom(RecruitExamRoom recruitExamRoom) {
        recruitExamRoom.setOrgFlow(GlobalContext.getCurrentUser().getOrgFlow());
        recruitExamRoom.setRoomFlow(PkUtil.getUUID());
        recruitExamRoom.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        recruitExamRoom.setCreateTime(DateUtil.getCurrDateTime());
        recruitExamRoom.setCreateUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        return recruitExamRoomMapper.insert(recruitExamRoom);
    }


    @Override
    public RecruitExamRoom searchExamRoomByFlow(String roomFlow) {
        return recruitExamRoomMapper.selectByPrimaryKey(roomFlow);
    }


    @Override
    public Integer updateExamRoom(RecruitExamRoom editInfo) {
        editInfo.setRecordStatus(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        editInfo.setModifyTime(DateUtil.getCurrDateTime());
        editInfo.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        return recruitExamRoomMapper.updateByPrimaryKeySelective(editInfo);
    }


    @Override
    public Integer changeExamRoomStatus(String roomFlow, String recordStatus) {
        RecruitExamRoom recruitExamRoom = new RecruitExamRoom();
        recruitExamRoom.setRoomFlow(roomFlow);
        recruitExamRoom.setRecordStatus(recordStatus);
        recruitExamRoom.setModifyTime(DateUtil.getCurrDateTime());
        recruitExamRoom.setModifyUserFlow(GlobalContext.getCurrentUser().getUserFlow());
        return recruitExamRoomMapper.updateByPrimaryKeySelective(recruitExamRoom);
    }

    @Override
    public List<String> searchAllExamRoomName(String orgFlow) {
        return recruitExamRoomMapper.searchAllExamRoomNameByOrgFlow(orgFlow);
    }


    @Override
    public RecruitExamRoom searchExamRoomByName(String examRoomName,String orgFlow) {
        RecruitExamRoomExample example = new RecruitExamRoomExample();
        RecruitExamRoomExample.Criteria criteria = example.createCriteria();
        criteria.andRoomNameEqualTo(examRoomName);
        criteria.andOrgFlowEqualTo(orgFlow);
        criteria.andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        List<RecruitExamRoom> recruitExamRooms = recruitExamRoomMapper.selectByExample(example);
        if (recruitExamRooms != null && recruitExamRooms.size() > 0){
            return recruitExamRooms.get(0);
        }else {
            return null;
        }
    }

    @Override
    public List<RecruitExamRoom> orgExamRooms(String orgFlow) {
        RecruitExamRoomExample example = new RecruitExamRoomExample();
        RecruitExamRoomExample.Criteria criteria = example.createCriteria();
        criteria.andOrgFlowEqualTo(orgFlow).andRecordStatusEqualTo(com.pinde.core.common.GlobalConstant.RECORD_STATUS_Y);
        return recruitExamRoomMapper.selectByExample(example);
    }
}
