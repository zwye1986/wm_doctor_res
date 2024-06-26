<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/res/teacher/Style.css'/>"></link>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>

<script type="text/javascript">
	function scroll(){
		setTimeout(function(){
			$(".list li:first").animate({marginTop : "-25px"},500,function(){
				$(".list").append($(this).css({marginTop : "0px"}));
				scroll();
			});
		},3000);
	}
	$(function(){
		if($(".list li").length>1){
			scroll();
		}
	});
	
	function changeDept(deptFlow){
		location.href = "<s:url value='/res/manager/view'/>?deptFlow="+deptFlow;
	}
	
	function defaultImg(img){
		img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
	}
	
	function edit(flow){
		var url="<s:url value='/res/teacher/showDocAndUser'/>?flow="+flow;
		jboxOpen(url, "信息", 1000, 500);
	}
</script>
</head>
<body>
<div class="mainright">
	<div class="content" >
		<div>
			<jsp:include page="/res/doc/newNoticeList">
				<jsp:param name="fromSch" value="Y"></jsp:param>
			</jsp:include>
		</div>
		<div style="float: left;margin-top: 10px;width: 100%;">
			<div style="width: 25%;float: left;margin-bottom: 20px;">
				<table class="xllist">
					<tr>
						<th>科室名称</th>
						<th>待入科人数</th>
						<th>轮转人数</th>
						<th>待出科人数</th>
					</tr>
					<c:forEach items="${deptList}" var="dept">
						<tr onclick="changeDept('${dept.deptFlow}');" style="cursor: pointer;<c:if test="${param.deptFlow eq dept.deptFlow}">background: pink;</c:if>">
							<td>${dept.deptName}</td>
							<td>${willInResultListMap[dept.deptFlow].size()+0}</td>
							<td>${processListMap[dept.deptFlow].size()+0}</td>
							<td>${willAfterCountMap[dept.deptFlow]+0}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty deptList}">
						<tr><td colspan="99">无记录</td></tr>
					</c:if>
				</table>
			</div>
			<div style="width: 74%;float: right;margin-bottom: 20px;">
				<table class="xllist">
					<tr>
						<th colspan="99" style="text-align: left;padding-left: 10px;">待入科学员</th>
					</tr>
					<tr>
						<th style="width: 10%;">姓名</th>
						<th style="width: 5%;">性别</th>
						<th style="width: 10%;">手机</th>
						<th style="width: 10%;">人员类型</th>
						<th style="width: 10%;">轮转科室</th>
						<th style="width: 16%;">计划轮转时间</th>
						<th style="width: 10%;">备注</th>
					</tr>
					<c:set var="viewDataCount" value="0"/>
					<c:forEach items="${willInResultListMap[param.deptFlow]}" var="result">
						<c:set var="currDate" value="${pdfn:getCurrDate()}"/>
						<c:set var="overDay" value="${pdfn:signDaysBetweenTowDate(currDate,result.schStartDate)}"/>

							<c:set var="viewDataCount" value="${viewDataCount+1}"/>
							<c:set var="user" value="${userMap[result.doctorFlow]}"/>
							<c:set var="doctor" value="${doctorMap[result.doctorFlow]}"/>
							<tr>
								<td 
								onclick="edit('${doctor.doctorFlow}');" 
								title="
								<img 
								src='${sysCfgMap['upload_base_url']}/${user.userHeadImg}' 
								onerror='defaultImg(this);' 
								style='width: 110px;height: 130px;'
								/>
								" 
								style="cursor:pointer;<c:if test="${overDay>0}">color:red;</c:if>">
								<a>${user.userName}</a>
								</td>
	<%-- 							<td style="<c:if test="${overDay>0}">color:red;</c:if>">${user.userName}</td> --%>
								<td>${user.sexName}</td>
								<td>${user.userPhone}</td>
								<td>${doctor.doctorCategoryName}</td>
								<td>${result.schDeptName}</td>
								<td>${result.schStartDate}~${result.schEndDate}</td>
								<td>
									<c:if test="${overDay>0}">
										已超过入科日期${overDay}天
									</c:if>
									<c:if test="${!(overDay>0)}">
										还有${-overDay}天入科
									</c:if>
								</td>
							</tr>
					</c:forEach>
					
					<c:if test="${empty param.deptFlow}">
						<tr><td colspan="99">请选择科室</td></tr>
					</c:if>
					<c:if test="${(!empty param.deptFlow && empty willInResultListMap[param.deptFlow]) || viewDataCount==0}">
						<tr><td colspan="99">无记录</td></tr>
					</c:if>
				</table>
				<table class="xllist" style="margin-top: 10px;">
					<tr>
						<th colspan="99" style="text-align: left;padding-left: 10px;">轮转中学员</th>
					</tr>
					<tr>
						<th style="width: 10%;">姓名</th>
						<th style="width: 5%;">性别</th>
						<th style="width: 10%;">手机</th>
						<th style="width: 10%;">人员类型</th>
						<th style="width: 10%;">轮转科室</th>
						<th style="width: 16%;">计划轮转时间</th>
						<th style="width: 10%;">入科时间</th>
					</tr>
					
					<c:forEach items="${processListMap[param.deptFlow]}" var="process">
						<c:set var="user" value="${userMap[process.userFlow]}"/>
						<c:set var="doctor" value="${doctorMap[process.userFlow]}"/>
						<tr>
							<td 
							onclick="edit('${doctor.doctorFlow}');" 
							title="
							<img 
							src='${sysCfgMap['upload_base_url']}/${user.userHeadImg}' 
							onerror='defaultImg(this);' 
							style='width: 110px;height: 130px;'
							/>
							" 
							style="cursor:pointer;">
							<a>${user.userName}</a>
							</td>
<%-- 							<td>${user.userName}</td> --%>
							<td>${user.sexName}</td>
							<td>${user.userPhone}</td>
							<td>${doctor.doctorCategoryName}</td>
							<td>${process.schDeptName}</td>
							<td>${process.schStartDate}~${process.schEndDate}</td>
							<td>${process.startDate}</td>
						</tr>
					</c:forEach>
					
					<c:if test="${empty param.deptFlow}">
						<tr><td colspan="99">请选择科室</td></tr>
					</c:if>
					<c:if test="${!empty param.deptFlow && empty processListMap[param.deptFlow]}">
						<tr><td colspan="99">无记录</td></tr>
					</c:if>
				</table>
				<table class="xllist" style="margin-top: 10px;">
					<tr>
						<th colspan="99" style="text-align: left;padding-left: 10px;">待出科学员</th>
					</tr>
					<tr>
						<th style="width: 10%;">姓名</th>
						<th style="width: 5%;">性别</th>
						<th style="width: 10%;">手机</th>
						<th style="width: 10%;">人员类型</th>
						<th style="width: 10%;">轮转科室</th>
						<th style="width: 16%;">计划轮转时间</th>
					</tr>
					
					<c:forEach items="${processListMap[param.deptFlow]}" var="process">
						<c:set var="user" value="${userMap[process.userFlow]}"/>
						<c:set var="doctor" value="${doctorMap[process.userFlow]}"/>
						<c:if test="${willAfterDocCountMap[process.processFlow]>0}">
							<tr>
								<td 
								onclick="edit('${doctor.doctorFlow}');" 
								title="
								<img 
								src='${sysCfgMap['upload_base_url']}/${user.userHeadImg}' 
								onerror='defaultImg(this);' 
								style='width: 110px;height: 130px;'
								/>
								" 
								style="cursor:pointer;">
								<a>${user.userName}</a>
								</td>
	<%-- 							<td>${user.userName}</td> --%>
								<td>${user.sexName}</td>
								<td>${user.userPhone}</td>
								<td>${doctor.doctorCategoryName}</td>
								<td>${process.schDeptName}</td>
								<td>${process.schStartDate}~${process.schEndDate}</td>
							</tr>
						</c:if>
					</c:forEach>
					
					<c:if test="${empty param.deptFlow}">
						<tr><td colspan="99">请选择科室</td></tr>
					</c:if>
					<c:if test="${!empty param.deptFlow && empty processListMap[param.deptFlow]}">
						<tr><td colspan="99">无记录</td></tr>
					</c:if>
				</table>
			</div>
		</div>
	</div>
</div>
</body>
</html>