
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
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
	html { overflow-x: scroll;}
</style>
<script type="text/javascript">
	function search(){
		$("#searchForm").submit();
	}
	$(document).ready(function(){
		loadMajorJson();
	});
	function loadMajorJson(){
		 var url = "<s:url value='/res/platform/searchSysOrg'/>";
		var courseArray = [];
		jboxGetAsync(url,null,function(data){
			if(data){
				for (var i = 0; i < data.length; i++) {
					var orgName=data[i].orgName;
					if(data[i].orgName==null){
						orgName="";
					}
					courseArray[i] = [orgName, orgName];
				}
				jboxStartLoading();
				$("#searchParam_Course").suggest(courseArray,{
					attachObject:'#suggest_Course',
					dataContainer:'#result_Course',
					triggerFunc:function(orgName){
						search();
					},
				    enterFunc:function(orgName){
				    	search();
				    }
				});
				jboxEndLoading();
			}
			
		},null,false);
	}
	function adjustResults() {
		$("#suggest_Course").css("left",$("#searchParam_Course").offset().left);
		$("#suggest_Course").css("top",$("#searchParam_Course").offset().top+$("#searchParam_Course").outerHeight());
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/resedu/report/turnSearch'/>">
					<table  class="basic" width="100%" style="margin-bottom: 10px;">
						<tr>
							<td>
								姓名：<input type="text" name="doctorName" value="${param.doctorName}" onchange="search();" style="width: 80px;">
								&#12288;
								年级：
								<select name="sessionNumber" style="width: 100px" onchange="search();">
									<option></option>
									<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
										<option value="${dict.dictName}" <c:if test="${dict.dictName eq param.sessionNumber}">selected</c:if>>${dict.dictName}</option>
									</c:forEach>
								</select>
								&#12288;
							</td>
						</tr>
					</table>
				</form>
				<table class="basic" width="100%">
					<tr>
						<th style="text-align: center;padding: 0;">医师姓名</th>
						<th style="text-align: center;padding: 0;">年级</th>
						<th style="text-align: center;padding: 0;">性别</th>
<!-- 						<th style="text-align: center;padding: 0;width: 150px;">学员单位</th> -->
						<th style="text-align: center;padding: 0;width: 150px;">身份证</th>
						<th style="text-align: center;padding: 0;line-height: 20px;">类型<br/>(西医、中医)</th>
						<th style="text-align: center;padding: 0;">学历</th>
						<th style="text-align: center;padding: 0;width: 60px;">培训年限</th>
						<th style="text-align: center;padding: 0;width: 150px;">转出机构</th>
						<th style="text-align: center;padding: 0;width: 150px;">转入机构</th>
						<th style="text-align: center;padding: 0;width: 80px;">转出日期</th>
						<th style="text-align: center;padding: 0;width: 80px;">转入日期</th>
						<th style="text-align: center;padding: 0;">手机</th>
					</tr>
					<c:forEach items="${orgHistoryList}" var="orgHistory">
						<tr>
							<td style="text-align: center;padding: 0;">${orgHistory.doctorName}</td>
							<td style="text-align: center;padding: 0;">${doctorMap[orgHistory.doctorFlow].sessionNumber}</td>
							<td style="text-align: center;padding: 0;">${userMap[orgHistory.doctorFlow].sexName}</td>
<%-- 							<td style="text-align: center;padding: 0;">${userMap[orgHistory.doctorFlow].orgName}</td> --%>
							<td style="text-align: center;padding: 0;">${userMap[orgHistory.doctorFlow].idNo}</td>
							<td style="text-align: center;padding: 0;"></td>
							<td style="text-align: center;padding: 0;">${userMap[orgHistory.doctorFlow].educationName}</td>
							<td style="text-align: center;padding: 0;">${doctorMap[orgHistory.doctorFlow].trainingYears}</td>
							<td style="text-align: center;padding: 0;line-height: 20px;">
								${orgHistory.historyOrgName}<br/>${orgHistory.historyTrainingSpeName}
							</td>
							<td style="text-align: center;padding: 0;line-height: 20px;">
								${orgHistory.orgName}<br/>${orgHistory.trainingSpeName}
							</td>
							<td style="text-align: center;padding: 0;">${orgHistory.outDate}</td>
							<td style="text-align: center;padding: 0;">${orgHistory.inDate}</td>
							<td style="text-align: center;padding: 0;">${userMap[orgHistory.doctorFlow].userPhone}</td>
						</tr>
					</c:forEach>
					<c:if test="${empty orgHistoryList}">
						<tr>
							<td colspan="12" style="text-align: center;">暂无记录！</td>
						</tr>
					</c:if>
				</table>
				
			</div>
		</div>
	</div>
</body>
</html>