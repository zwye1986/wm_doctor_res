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
		
		//选择科室
// 		$("#selDeptImg,.tips_select").on("mouseenter mouseleave",function(){
// 			$(".tips_select").toggle();
// 		});
		
	});
	
	function changeDept(deptFlow){
		location.href = "<s:url value='/res/teacher/showView/${roleFlag}'/>?schDeptFlow="+deptFlow;
	}

	function showView(){
		$("#currentPage").val(1);
		$("#searchForm").submit();
	}

	function toPage(page) {
		if (page) {
			$("#currentPage").val(page);
		}
		showView();
	}

	function goAudit(doctorName){
		$("#doctorName").val(doctorName);
		$("#doctorForm").submit();
		$("#dateAreaValue").val();
	}
	function edit(flow){
		var url="<s:url value='/res/teacher/showDocAndUser'/>?flow="+flow;
		jboxOpen(url, "信息", 1000, 500);
	}
	function afterAudit(rotationFlow,userFlow,processFlow){
		location.href="<s:url value='/res/rec/showRegistryForm'/>?roleFlag=${roleFlag}&recTypeId=${resRecTypeEnumAfterSummary.id}&rotationFlow="+rotationFlow+"&schDeptFlow=${schDeptFlow}&operUserFlow="+userFlow+"&processFlow="+processFlow+"&isView=true";
	}

	//选择日期区间
	function selDateArea(area,num){
		$(".selected").removeClass("selected");
		$(area).addClass("selected");
		$("#dateAreaValue").text(num);
	}
	
	//操作入科
	function openChoose(resultFlow,schDeptFlow){
		var url="<s:url value='/res/doc/showChoose' />?resultFlow="+resultFlow+"&schDeptFlow="+schDeptFlow+"&roleFlag=${roleFlag}&headUserFlow=${sessionScope.currUser.userFlow}";
		jboxOpen(url,"选择科主任和带教老师",400,200);
	}
	
	function teachPlan(){
		window.location.href="<s:url value='/res/teacher/teachPlanList'/>";
	}
	
	function defaultImg(img){
		img.src = "<s:url value='/css/skin/up-pic.jpg'/>";
	}
</script>
</head>
<body>
<form id="doctorForm" action="<s:url value='/res/teacher/auditListContent'/>" method="post">
	<input id="doctorName" type="hidden" name="doctorName" />
	<input type="hidden" name="roleFlag" value="${roleFlag}" />
</form>
<div class="mainright">
	<div class="content" >
		<div>
			<div>
				<jsp:include page="/res/doc/newNoticeList" flush="true">
					<jsp:param name="fromSch" value="Y"></jsp:param>
				</jsp:include>
			</div>
			<form id="searchForm" action="<s:url value='/res/teacher/showView/kmHead'/>" method="post">
				<input type="hidden" name="currentPage" id="currentPage" value="${param.currentPage}">
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">姓&#12288;&#12288;名：</label>
						<input type="text" class="qtext" name="doctorName" value="${param.doctorName}" >
					</div>
					<div class="qcheckboxDiv">
						&#12288;<label class="qlable">
							<input type="checkbox" name="isCurrentFlag" value="${GlobalConstant.FLAG_Y}" ${param.isCurrentFlag eq GlobalConstant.FLAG_Y?"checked":""}<%-- onchange="showView();"--%>>
							轮转中学员
						</label>
						<input type="button" value="查&#12288;询" class="searchInput" onclick="showView();"/>
					</div>

				</div>
			</form>
		</div>

		<div style="padding-top: 5px;">
			<table class="xllist" style=" width:100%;">
			<tr>
				<th style="width: 10%;">姓名</th>
				<th style="width: 5%;">性别</th>
				<th style="width: 10%;">手机</th>
				<th style="width: 10%;">入院时间</th>
				<th style="width: 10%;">人员类型</th>
				<th style="width: 15%;">轮转科室</th>
				<th style="width: 15%;">计划轮转时间</th>
				<th style="width: 10%;">入科时间</th>
				<th style="width: 5%;">状态</th>
				<th style="width: 5%;">出科考核材料</th>
			</tr>
			<c:forEach items="${processList}" var="process">
				<c:set var="doctor" value="${doctorMap[process.userFlow]}"/>
				<tr>
					<td>${doctor.doctorName}</td>
					<td>${userMap[doctor.doctorFlow].sexName}</td>
					<td>${userMap[doctor.doctorFlow].userPhone}</td>
					<td>${doctor.inHosDate}</td>
					<td>${doctor.doctorCategoryName}</td>
					<td>${process.schDeptName}</td>
					<td>${process.schStartDate} ~ ${process.schEndDate}</td>
					<td>${process.startDate}</td>
					<td>
						<c:set var="key" value="${process.processFlow}"/>
						<c:if test="${process.schFlag eq GlobalConstant.FLAG_Y}">
							已出科
						</c:if>
						<c:if test="${!(process.schFlag eq GlobalConstant.FLAG_Y)}">
							<c:if test="${process.isCurrentFlag eq GlobalConstant.FLAG_Y}">
								<c:if test="${!empty afterMap[key] || !empty recMap[key]}">
									待出科
								</c:if>
								<c:if test="${!(!empty afterMap[key] || !empty recMap[key])}">
									轮转中
								</c:if>
							</c:if>
							<c:if test="${process.isCurrentFlag ne GlobalConstant.FLAG_Y}">
								待入科
							</c:if>
						</c:if>
					</td>
					<td>

					</td>
				</tr>
			</c:forEach>
			<c:if test="${empty processList}">
				<tr><td colspan="99">没有符合条件的学员轮转信息！</td></tr>
			</c:if>
		</table>
		</div>
		<p>
			<c:set var="pageView" value="${pdfn:getPageView(processList)}" scope="request"/>
			<pd:pagination toPage="toPage"/>
		</p>
	</div>
</div>
<script type="text/javascript">
</script>
</body>
</html>