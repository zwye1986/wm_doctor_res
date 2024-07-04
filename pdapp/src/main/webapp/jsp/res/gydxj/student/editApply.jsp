<%@ page language="java" contentType="aplication/json; charset=UTF-8" pageEncoding="UTF-8"%>
{
    "resultId": ${resultId}, 
    "resultType": ${pdfn:toJsonString(resultType)}
    <c:if test="${resultId eq '200' }">
    	,
		"action":{
			"save":${pdfn:toJsonString(empty apply.statusId || apply.statusId eq 'Save'?'保存':'')}
		},
		"applyInfo": [
			{"inputId": "recordFlow", "label": "申请流水号", "value": ${pdfn:toJsonString(apply.recordFlow)} ,"readonly":true, "isHidden": true},
			{"inputId": "applyTypeId", "label": "申请类型", "value": ${pdfn:toJsonString(param.applyTypeId)} ,"readonly":true,"inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},<c:forEach items="${userChangeApplyTypeEnumList}" var="data" varStatus="s">{"optionId": ${pdfn:toJsonString(data.id)},"optionDesc": ${pdfn:toJsonString(data.name)}}<c:if test="${not s.last }">,</c:if></c:forEach>]},
			{"inputId": "sid", "label": "学号", "value": ${pdfn:toJsonString(user.sid)} ,"readonly":true},
			{"inputId": "userName", "label": "姓名", "value": ${pdfn:toJsonString(sysUser.userName)} ,"readonly":true},
			{"inputId": "trainCategoryName", "label": "培养类型", "value": ${pdfn:toJsonString(user.trainCategoryName)} ,"readonly":true},
			{"inputId": "majorName", "label": "专业", "value": ${pdfn:toJsonString(user.majorName)} ,"readonly":true},
			{"inputId": "sexName", "label": "性别", "value": ${pdfn:toJsonString(sysUser.sexName)} ,"readonly":true},
			{"inputId": "orgName", "label": "培养单位", "value": ${pdfn:toJsonString(doctor.orgName)} ,"readonly":true},
			{"inputId": "trainOrgName", "label": "学位分委员会", "value": ${pdfn:toJsonString(user.trainOrgName)} ,"readonly":true},
			{"inputId": "applyReason", "label": "申请原因", "value": ${pdfn:toJsonString(form.applyReason)} ,"readonly":false},
			<c:if test="${param.applyTypeId eq 'Makeup'}">
				{"inputId": "applyMakeUpCou", "label": "申请补考科目", "value": ${pdfn:toJsonString(form.applyMakeUpCou)} ,"readonly":false,"inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},<c:forEach items="${eduCourseList}" var="data" varStatus="s">{"optionId": ${pdfn:toJsonString(data.courseName)},"optionDesc": ${pdfn:toJsonString(data.courseName)}}<c:if test="${not s.last }">,</c:if></c:forEach>]}
			</c:if>
			<c:if test="${param.applyTypeId eq 'ChangeTrainType'}">
				{"inputId": "firstTeacher", "label": "导师", "value": ${pdfn:toJsonString(user.firstTeacher)} ,"readonly":true},
				{"inputId": "qualifiedNo", "label": "执业医师资格证", "value": ${pdfn:toJsonString(doctor.qualifiedNo)} ,"readonly":true},
				{"inputId": "doctorLicenseNo", "label": "医师资格证号", "value": ${pdfn:toJsonString(doctor.doctorLicenseNo)} ,"readonly":true},
				{"inputId": "willTrainType", "label": "拟培养类型", "value": ${pdfn:toJsonString(form.willTrainType)} ,"readonly":false,"inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},<c:forEach items="${dictTypeEnumGyTrainCategoryList}" var="data" varStatus="s">{"optionId": ${pdfn:toJsonString(data.dictName)},"optionDesc": ${pdfn:toJsonString(data.dictName)}}<c:if test="${not s.last }">,</c:if></c:forEach>]}
			</c:if>
			<c:if test="${param.applyTypeId eq 'ChangeTeacher'}">
				{"inputId": "firstTeacher", "label": "原导师姓名", "value": ${pdfn:toJsonString(user.firstTeacher)} ,"readonly":true},
				{"inputId": "changeTutorName", "label": "拟更换导师姓名", "value": ${pdfn:toJsonString(form.changeTutorName)} ,"readonly":false},
				{"inputId": "qualifiedNo", "label": "执业医师资格证", "value": ${pdfn:toJsonString(doctor.qualifiedNo)} ,"readonly":true},
				{"inputId": "doctorLicenseNo", "label": "医师资格证号", "value": ${pdfn:toJsonString(doctor.doctorLicenseNo)} ,"readonly":true},
				{"inputId": "willTrainType", "label": "拟培养类型", "value": ${pdfn:toJsonString(form.willTrainType)} ,"readonly":false,"inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},<c:forEach items="${dictTypeEnumGyTrainCategoryList}" var="data" varStatus="s">{"optionId": ${pdfn:toJsonString(data.dictName)},"optionDesc": ${pdfn:toJsonString(data.dictName)}}<c:if test="${not s.last }">,</c:if></c:forEach>]}
			</c:if>
			<c:if test="${param.applyTypeId eq 'ChangeMajor'}">
				{"inputId": "swichmajorId", "label": "拟转入专业名称", "value": ${pdfn:toJsonString(form.swichmajorId)} ,"readonly":false,"inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},<c:forEach items="${dictTypeEnumGyMajorList}" var="data" varStatus="s">{"optionId": ${pdfn:toJsonString(data.dictId)},"optionDesc": ${pdfn:toJsonString(data.dictName)}}<c:if test="${not s.last }">,</c:if></c:forEach>]}
			</c:if>
			<c:if test="${param.applyTypeId eq 'DelayExam'}">
				{"inputId": "delayExamTime", "label": "缓考课程考试时间", "value": ${pdfn:toJsonString(form.delayExamTime)} ,"readonly":false,"inputType":"date"},
				{"inputId": "makeUpTime", "label": "预计补考时间", "value": ${pdfn:toJsonString(form.makeUpTime)} ,"readonly":false,"inputType":"date"},
				{"inputId": "delayCourName", "label": "缓考课程名称", "value": ${pdfn:toJsonString(form.delayCourName)} ,"readonly":false,"inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},<c:forEach items="${eduCourseList}" var="data" varStatus="s">{"optionId": ${pdfn:toJsonString(data.courseName)},"optionDesc": ${pdfn:toJsonString(data.courseName)}}<c:if test="${not s.last }">,</c:if></c:forEach>]}
			</c:if>
			<c:if test="${param.applyTypeId eq 'DelayStudy'}">
				{"inputId": "delayStudyTime", "label": "拟缓修起始时间", "value": ${pdfn:toJsonString(form.delayStudyTime)} ,"readonly":false,"inputType":"date"},
				{"inputId": "againStudyTime", "label": "预计缓修时间", "value": ${pdfn:toJsonString(form.againStudyTime)} ,"readonly":false,"inputType":"date"},
				{"inputId": "delayStudycourName", "label": " 缓修课程名称", "value": ${pdfn:toJsonString(form.delayStudycourName)} ,"readonly":false,"inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},<c:forEach items="${eduCourseList}" var="data" varStatus="s">{"optionId": ${pdfn:toJsonString(data.courseName)},"optionDesc": ${pdfn:toJsonString(data.courseName)}}<c:if test="${not s.last }">,</c:if></c:forEach>]}
			</c:if>
			<c:if test="${param.applyTypeId eq 'LeaveSchool' || apply.applyTypeId eq 'DelayGraduate' || apply.applyTypeId eq 'TransferStudy'}">
				{"inputId": "trainTypeName", "label": "层次", "value": ${pdfn:toJsonString(user.trainTypeName)} ,"readonly":true}
			</c:if>
			<c:if test="${param.applyTypeId eq 'OutStudy'}">
				{"inputId": "destination", "label": "目的地", "value": ${pdfn:toJsonString(form.destination)} ,"readonly":false},
				{"inputId": "startTime", "label": "起始时间", "value": ${pdfn:toJsonString(form.startTime)} ,"readonly":false,"inputType":"date"},
				{"inputId": "endTime", "label": "预计结束时间", "value": ${pdfn:toJsonString(form.endTime)} ,"readonly":false,"inputType":"date"}
			</c:if>
			<c:if test="${param.applyTypeId eq 'StopStudy'}">
				{"inputId": "trainTypeName", "label": "层次", "value": ${pdfn:toJsonString(user.trainTypeName)} ,"readonly":true},
				{"inputId": "stopStudyStarTime", "label": "拟休学开始时间", "value": ${pdfn:toJsonString(form.stopStudyStarTime)} ,"readonly":false,"inputType":"date"},
				{"inputId": "stopStudyEndTime", "label": "拟休学结束时间", "value": ${pdfn:toJsonString(form.stopStudyEndTime)} ,"readonly":false,"inputType":"date"}
			</c:if>
			<c:if test="${param.applyTypeId eq 'BackStudy'}">
				{"inputId": "trainTypeName", "label": "层次", "value": ${pdfn:toJsonString(user.trainTypeName)} ,"readonly":true},
				{"inputId": "stopStudyStarTime", "label": "拟复学开始时间", "value": ${pdfn:toJsonString(form.stopStudyStarTime)} ,"readonly":false,"inputType":"date"},
				{"inputId": "stopStudyEndTime", "label": "拟复学结束时间", "value": ${pdfn:toJsonString(form.stopStudyEndTime)} ,"readonly":false,"inputType":"date"}
			</c:if>
			<c:if test="${param.applyTypeId eq 'AddStudy'}">
				{"inputId": "delayStudyTime", "label": "拟补修起始时间", "value": ${pdfn:toJsonString(form.delayStudyTime)} ,"readonly":false,"inputType":"date"},
				{"inputId": "againStudyTime", "label": "预计补修时间", "value": ${pdfn:toJsonString(form.againStudyTime)} ,"readonly":false,"inputType":"date"},
				{"inputId": "delayStudycourName", "label": " 缓修课程名称", "value": ${pdfn:toJsonString(form.delayStudycourName)} ,"readonly":false,"inputType":"select",
				"options":[{"optionId": "","optionDesc": "请选择"},<c:forEach items="${eduCourseList}" var="data" varStatus="s">{"optionId": ${pdfn:toJsonString(data.courseName)},"optionDesc": ${pdfn:toJsonString(data.courseName)}}<c:if test="${not s.last }">,</c:if></c:forEach>]}
			</c:if>
		]
    </c:if>
}