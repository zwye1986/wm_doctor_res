package com.pinde.sci.dao.webService;

import com.pinde.sci.model.mo.SchArrangeResult;
import com.pinde.sci.model.mo.SchDept;
import com.pinde.sci.model.mo.SysDept;
import com.pinde.sci.webservice.bean.shzs.DeptInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ShzsWebServiceMapper {

   void updateInProcessDeptNameByFlow(@Param("dept") SysDept dept);

   void updateUserDeptNameByFlow(@Param("dept") SysDept dept);

   void updateResultDeptNameByFlow(@Param("schDept") SchDept schDept);

   void updateProcessDeptNameByFlow(@Param("schDept") SchDept schDept);

   int stopSchDept(@Param("deptInfo")DeptInfo deptInfo);

   int delDeptRel(@Param("deptInfo")DeptInfo deptInfo);

   int delExamStandardDept(@Param("deptInfo")DeptInfo deptInfo);

   int enableDept(@Param("deptInfo")DeptInfo deptInfo);

   int enableDeptRel(@Param("deptInfo")DeptInfo deptInfo);

   int enableExamStandardDept(@Param("deptInfo")DeptInfo deptInfo);

   void delUserRole(@Param("userFlow") String userFlow);

   void delUserDept(@Param("userFlow") String userFlow);

   int readDocCountByTea(@Param("userFlow") String userFlow);

   int readDocCountByHead(@Param("userFlow") String userFlow);

   int readDocProcess(@Param("userFlow") String userFlow);

   List<Map<String,String>> readRotationDeptByFlow(@Param("rotationFlow") String rotationFlow);

   int checkUserDept(@Param("userFlow") String userFlow, @Param("deptFlow") String deptFlow);

   List<SchArrangeResult> checkResultDate(Map<String, Object> paramMap);
}