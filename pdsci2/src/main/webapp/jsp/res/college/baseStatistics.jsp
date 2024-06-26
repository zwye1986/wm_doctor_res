
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="true" />
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
					if($("#result_Course").val() == orgName){
						$("#searchParam_Course").val(orgName);	
					}
				}
				jboxStartLoading();
				$("#searchParam_Course").suggest(courseArray,{
					attachObject:'#suggest_Course',
					dataContainer:'#result_Course',
					triggerFunc:function(orgName){
//						search();
					},
				    enterFunc:function(orgName){
//				    	search();
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
	function exportBase(){
		var url = "<s:url value='/res/platform/exportBase'/>";
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/res/platform/baseStatistics'/>">
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">年&#12288;&#12288;级：</label>
							<select name="sessionNumber" class="qselect">
								<option>
									<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="sessionNumber">
								<option value="${sessionNumber.dictId}" <c:if test="${param.sessionNumber eq sessionNumber.dictId}">selected</c:if> >${sessionNumber.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">专&#12288;&#12288;业：</label>
							<select name="speId" class="qselect">
								<option>
									<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
								<option value="${spe.dictId}" <c:if test="${param.speId eq spe.dictId}">selected</c:if> >${spe.dictName}</option>
								</c:forEach>
							</select>
						</div>
						<div class="inputDiv">
							<label class="qlable">机构名称：</label>
							<input id="searchParam_Course" class="qtext"  name="searchParam_Course" value="${param.orgName }" onchange="$('#result_Course').val(this.value);" placeholder="输入机构名称"   onkeydown="adjustResults();" onfocus="adjustResults();"/>
							<div id="suggest_Course" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 150px;"></div>
							<input type="hidden" id="result_Course" name="orgName" value="${param.orgName}"/>
							<input type="text" style="display:none" name="sss"/>
						</div>
						<div class="lastDiv" style="min-width: 195px;max-width: 195px;">
							<input type="button" value="查&#12288;询" class="search" onclick="search();"/>
							<input type="button" class="search" value="导&#12288;出" onclick="exportBase();"/>
						</div>
					</div>
				</form>
				<table class="basic" width="100%">
					<tr>
						<th style="width: 120px;text-align: center;padding: 0;max-width: 120px;min-width: 120px;" >专业</th>
						<c:forEach items="${orgList}" var="org">
							<th style="width: 150px;text-align: center;padding: 0;max-width: 150px;min-width: 150px;" >${org.orgName}</th>
						</c:forEach>
						<th style="width: 150px;text-align: center;padding: 0;max-width: 150px;min-width: 150px;" >汇总统计</th>
					</tr>
					<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
						<c:if test="${(!empty param.speId && spe.dictId eq param.speId) || empty param.speId}">
							<tr>
								<c:set value="0" var="sum"/>
								<td style="text-align: center;padding: 0;">${spe.dictName}</td>
								<c:forEach items="${orgList}" var="org">
									<c:set value="${org.orgFlow}${spe.dictId}" var="key"/>
									<c:set value="${allCountMap[key]+countTrainingResultMap[key]+countGraduationResultMap[key]}" var="s"/>
									<c:set value="${sum+s}" var="sum"/>
									<td style="text-align: center">${s}</td>
								</c:forEach>
								<td style="text-align: center">${sum}</td>
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
</body>
</html>