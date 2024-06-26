<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<c:set value="schUnitEnum${empty sysCfgMap['res_rotation_unit']?schUnitEnumMonth.id:sysCfgMap['res_rotation_unit']}" var="unitKey"/>
<script type="text/javascript">
    //评分
    function grade(recTypeName,recTypeId,recFlow){
        var height = (window.screen.height) * 0.6;
        var width = (window.screen.width) * 0.8;
        jboxOpen("<s:url value='/res/rec/grade'/>?recTypeId="+recTypeId+"&recFlow="+recFlow, recTypeName,width,height);
    }

</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<table class="basic" width="100%" style="margin-bottom: 10px;margin-top: 10px;">
			<tr>
				<td>
					医师：${doctor.doctorName}
					&#12288;
					轮转方案：${doctor.rotationName}
					<c:if test="${not empty doctor.secondRotationName}">
						&#12288;
						二级轮转方案：${doctor.rotationName}
					</c:if>
				</td>
			</tr>
		</table>
		<table class="basic list" width="100%">
            <colgroup>
                <col width="15%"/>
                <col width="8%"/>
                <col width="24%"/>
                <col width="10%"/>
                <col width="10%"/>
                <col width="8%"/>
                <col width="10%"/>
                <col width="5%"/>
                <col width="10%"/>
            </colgroup>
			<tr>
				<th>轮转科室</th>
				<th>轮转时长</th>
				<th>轮转时间</th>
				<th>带教老师</th>
				<th>出入科状态</th>
				<th>评价带教</th>
				<th>评价科主任</th>
				<th>是否已评价带教/科主任</th>
				<th>备注</th>
			</tr>
			<c:forEach items="${arrResultList}" var="result">
				<tr>
					<td>${result.schDeptName}</td>
					<td><p style="text-align: right;width: 72%">${result.schMonth+0.0}${applicationScope[unitKey].name}</p></td>
					<td>${result.schStartDate} ~ ${result.schEndDate}</td>
					<td>${processMsg[result.resultFlow].teacherUserName}</td>
					<td>
						<c:if test="${processMsg[result.resultFlow].schFlag == GlobalConstant.FLAG_Y}">
							已出科
						</c:if>
						<c:if test="${processMsg[result.resultFlow].isCurrentFlag == GlobalConstant.FLAG_Y}">
							轮转中
						</c:if>
						<c:if test="${processMsg[result.resultFlow].schFlag ne GlobalConstant.FLAG_Y and
							processMsg[result.resultFlow].isCurrentFlag ne GlobalConstant.FLAG_Y}">
							待入科
						</c:if>
					</td>
                    <td>
                        <a style="color: #00a1e5" href="javascript:grade('${resRecTypeEnumTeacherGrade.name}','${resRecTypeEnumTeacherGrade.id}','${result.isTeacher}');">${empty result.isTeacher?'未评':(teacherGradeMap[result.isTeacher].totalScore)}分
                        </a>
                    </td>
					<td>
                        <a style="color: #00a1e5" href="javascript:grade('${resRecTypeEnumDeptGrade.name}','${resRecTypeEnumDeptGrade.id}','${result.isDept}');">${empty result.isDept?'未评':(deptGradeMap[result.isDept].totalScore)}分</a>
                    </td>

					<td>${result.evaluateFlag}</td>
					<td>
						<c:if test="${!empty absenceCount[result.schDeptFlow]}">
							缺勤${absenceCount[result.schDeptFlow]}天！
						</c:if>
					</td>
				</tr>
			</c:forEach>
			<c:if test="">
				<tr><td colspan="3">暂无轮转计划!</td></tr>
			</c:if>
		</table>
	</div>
</div>
</body>
</html>