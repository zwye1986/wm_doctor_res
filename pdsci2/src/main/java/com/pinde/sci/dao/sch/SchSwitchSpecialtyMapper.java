package com.pinde.sci.dao.sch;

import com.pinde.sci.model.mo.ResDoctorSchProcess;
import com.pinde.sci.model.mo.ResRec;
import com.pinde.sci.model.mo.ResSchProcessExpress;
import com.pinde.sci.model.mo.SchRotationDeptReq;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName SchSwitchSpecialtyMapper
 * @Deacription 切换专业相关Mapper
 * @Author shengl
 * @Date 2020-09-10 17:49
 * @Version 1.0
 **/
public interface SchSwitchSpecialtyMapper {

    /**
     * @Author shengl
     * @Description // 批量添加出科数据
     * @Date  2020-09-10
     * @Param [addExpressList] 
     * @return int
    **/
    int insertExpressList(@Param("addExpressList") List<ResSchProcessExpress> addExpressList);

    /**
     * @Author shengl
     * @Description //批量添加轮转数据
     * @Date  2020-09-10
     * @Param [addResRecList]
     * @return int
    **/
    int insertResRecList(@Param("addResRecList") List<ResRec> addResRecList);

    /**
     * @Author shengl
     * @Description // 批量添加轮转数据
     * @Date  2020-09-10
     * @Param [addReqList] 
     * @return int
    **/
    int insertReqList(@Param("addReqList") List<SchRotationDeptReq> addReqList);

    /**
     * @Author shengl
     * @Description // 新增轮转相关信息
     * @Date  2020-09-11
     * @Param [addProcessList] 
     * @return int
    **/
    int insertProcessList(@Param("addProcessList") List<ResDoctorSchProcess> addProcessList);
}
