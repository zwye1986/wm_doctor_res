<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
</jsp:include>
<style>
</style>
<script type="text/javascript">
	function exportDoc(){
		if(${empty schArrangeResults}){
			jboxTip("当前无记录!");
			return;
		}
		var url = "<s:url value='/res/zsrykqmonth/exportDetailExl'/>?doctorFlow=${param.doctorFlow}&startDate=${param.startDate}&endDate=${param.endDate}";
		jboxTip("导出中…………");
		window.location.href=url;
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<div style="text-align: left">
					<input class="search" type="button" onclick="exportDoc()" value="导&#12288;出" style="margin: 10px;">
				</div>
				<table class="basic" width="100%">
					<tr style="font-weight: bold;">
						<td style="text-align: center;padding: 0px;">姓名</td>
						<td style="text-align: center;padding: 0px;">年级</td>
						<td style="text-align: center;padding: 0px;">所属科室</td>
						<td style="text-align: center;padding: 0px;">培训专业</td>
						<td style="text-align: center;padding: 0px;">培训年限</td>
						<td style="text-align: center;padding: 0px;">入培时间</td>
						<td style="text-align: center;padding: 0px;">结业时间</td>
						<td style="text-align: center;padding: 0px;">轮转科室</td>
						<td style="text-align: center;padding: 0px;">轮转时间</td>
						<td style="text-align: center;padding: 0px;">病假</td>
						<td style="text-align: center;padding: 0px;">事假</td>
						<td style="text-align: center;padding: 0px;">带薪年假</td>
						<td style="text-align: center;padding: 0px;">婚假</td>
						<td style="text-align: center;padding: 0px;">产假</td>
						<td style="text-align: center;padding: 0px;">陪产假</td>
						<td style="text-align: center;padding: 0px;">计生假</td>
						<td style="text-align: center;padding: 0px;">出国</td>
						<td style="text-align: center;padding: 0px;">进修</td>
						<td style="text-align: center;padding: 0px;">脱产读研</td>
						<td style="text-align: center;padding: 0px;">放射假</td>
						<td style="text-align: center;padding: 0px;">旷工</td>
						<td style="text-align: center;padding: 0px;">请假合计</td>
						<td style="text-align: center;padding: 0px;">出勤</td>
						<td style="text-align: center;padding: 0px;">考勤天数</td>
						<td style="text-align: center;padding: 0px;">出勤率</td>
					</tr>
					<c:forEach items="${schArrangeResults}" var="result">
						<tr>
							<td style="text-align: center;padding: 0px;">${result.doctorName}</td>
							<td style="text-align: center;padding: 0px;">${doctor.sessionNumber}</td>
							<td style="text-align: center;padding: 0px;">
								<c:choose>
								<c:when test="${doctor.doctorTypeId eq 'Company'}">
									${doctor.departMentName}
								</c:when>
								<c:when test="${doctor.doctorTypeId eq 'Social'}">
									--
								</c:when>
								<c:otherwise>
									${doctor.workOrgName}
								</c:otherwise>
								</c:choose>
							</td>
							<td style="text-align: center;padding: 0px;">${speName}</td>
							<td style="text-align: center;padding: 0px;">${doctor.trainingYears}</td>
							<td style="text-align: center;padding: 0px;">${doctor.inHosDate}</td>
							<td style="text-align: center;padding: 0px;">${doctor.graduationYear}</td>
							<td style="text-align: center;padding: 0px;">${result.schDeptName}</td>
							<td style="text-align: center;padding: 0px;">${result.schStartDate}~${result.schEndDate}</td>
							<c:set value="${result.resultFlow}02" var="key0"></c:set>
							<c:set value="${result.resultFlow}03" var="key1"></c:set>
							<td style="text-align: center;padding: 0px;">${resultMap[key0]+resultMap[key1]/2}</td>
							<c:set value="${result.resultFlow}04" var="key4"></c:set>
							<c:set value="${result.resultFlow}05" var="key5"></c:set>
							<td style="text-align: center;padding: 0px;">${resultMap[key4]+resultMap[key5]/2}</td>
							<c:set value="${result.resultFlow}06" var="key6"></c:set>
							<c:set value="${result.resultFlow}07" var="key7"></c:set>
							<td style="text-align: center;padding: 0px;">${resultMap[key6]+resultMap[key7]/2}</td>
							<c:set value="${result.resultFlow}10" var="key10"></c:set>
							<td style="text-align: center;padding: 0px;">${resultMap[key10]+0.0}</td>
							<c:set value="${result.resultFlow}11" var="key11"></c:set>
							<td style="text-align: center;padding: 0px;">${resultMap[key11]+0.0}</td>
							<c:set value="${result.resultFlow}12" var="key12"></c:set>
							<td style="text-align: center;padding: 0px;">${resultMap[key12]+0.0}</td>
							<c:set value="${result.resultFlow}13" var="key13"></c:set>
							<td style="text-align: center;padding: 0px;">${resultMap[key13]+0.0}</td>
							<c:set value="${result.resultFlow}15" var="key15"></c:set>
							<td style="text-align: center;padding: 0px;">${resultMap[key15]+0.0}</td>
							<c:set value="${result.resultFlow}16" var="key16"></c:set>
							<td style="text-align: center;padding: 0px;">${resultMap[key16]+0.0}</td>
							<c:set value="${result.resultFlow}18" var="key18"></c:set>
							<td style="text-align: center;padding: 0px;">${resultMap[key18]+0.0}</td>
							<c:set value="${result.resultFlow}19" var="key19"></c:set>
							<c:set value="${result.resultFlow}22" var="key22"></c:set>
							<td style="text-align: center;padding: 0px;">${resultMap[key19]+resultMap[key22]/2}</td>
							<c:set value="${result.resultFlow}20" var="key20"></c:set>
							<c:set value="${result.resultFlow}21" var="key21"></c:set>
							<td style="text-align: center;padding: 0px;">${resultMap[key20]+resultMap[key21]/2}</td>
							<td style="text-align: center;padding: 0px;">${sumMap[result.resultFlow]+0.0}</td>
							<td style="text-align: center;padding: 0px;">${attendMap[result.resultFlow]}</td>
							<td style="text-align: center;padding: 0px;">${sumAllMap[result.resultFlow]}</td>
							<td style="text-align: center;padding: 0px;">${percentMap[result.resultFlow]}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty schArrangeResults}">
						<tr><td style="text-align: center;" colspan="30">无记录</td></tr>
					</c:if>
				</table>
			</div>
		</div>
	</div>
</body>
</html>