<%@include file="/jsp/common/doctype.jsp" %>
<html>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<script type="text/javascript">
$(document).ready(function(){
	initDepts();
	toPage(1);
});
function initDepts()
{
	var datas=[];
	<c:forEach items="${depts}" var="dept">
	var d={};
	d.id="${dept.deptFlow}";
	d.text="${dept.deptName}";
	datas.push(d);
	</c:forEach>
	var itemSelectFuntion = function(){
		$("#deptFlow").val(this.id);
	};
	$.selectSuggest('trainDept',datas,itemSelectFuntion,"deptFlow",true);
}
function toPage(page) {
	$("#currentPage").val(page);
	jboxLoad("doctorListZi","<s:url value='/res/activityQuery/list'/>?roleFlag=${param.roleFlag}&"+$("#searchForm").serialize(),true);
}
function editActivity(activityFlow,role){
	if(activityFlow)
		jboxOpen("<s:url value='/res/activityQuery/editActivity'/>?activityFlow=" + activityFlow+"&role="+role,'编辑活动',600,400);
	else
		jboxOpen("<s:url value='/res/activityQuery/editActivity'/>?activityFlow=" + activityFlow+"&role="+role,'新增活动',600,400);
}
function exportExcel(){
	var url = "<s:url value='/res/activityQuery/exportList'/>?roleFlag=${param.roleFlag}";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}

function upload(activityFlow,isUpload){
	var url = "<s:url value='/res/activityQuery/upload'/>?activityFlow="+activityFlow+"&isUpload="+isUpload;
	if(isUpload=="Y")
	{
		jboxOpen(url, "上传活动图片",700,550);
	}else{
		jboxOpen(url, "查看活动图片",700,550);
	}
}
</script>
<div class="mainright" style="min-height: 300px;">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm">
				<input type="hidden" id="currentPage" name="currentPage"/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">活动名称：</label>
						<input type="text" name="activityName" value="" class="qtext" />
					</div>
					<c:if test="${param.roleFlag ne 'teach' }">
						<div class="inputDiv">
							<label class="qlable">主&nbsp;讲&nbsp;人：</label>
							<input type="text" name="userName" value="" class="qtext" />
						</div>
					</c:if>
					<div class="inputDiv">
						<label class="qlable">活动形式：</label>
						<select name="activityTypeId" class="qselect">
							<option value="">全部</option>
							<c:forEach items="${dictTypeEnumActivityTypeList}" var="a">
								<option value="${a.dictId}" >${a.dictName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="inputDiv">
						<label class="qlable">科&#12288;&#12288;室：</label>

						<input id="trainDept" name="deptName" value="${param.deptName}"  class="toggleView qtext" type="text"  autocomplete="off"  />
						<input type="hidden" name="deptFlow" value="${param.deptFlow}" id="deptFlow">
					</div>
					<div class="inputDiv">
						<label class="qlable">开始时间：</label>
						<input type="text" id="startDate" name="startTime" value="" class="qtext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
					</div>
					<div class="inputDiv">
						<label class="qlable">结束时间：</label>
						<input type="text" id="endDate" name="endTime" value="" class="qtext"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
					</div>
					<div class="lastDiv" style="max-width: 250px;min-width: 250px;">
						<input class="searchInput" type="button" value="查&#12288;询" onclick="toPage(1);"/>
						<c:if test="${param.roleFlag eq 'teach'or param.roleFlag eq 'head'or param.roleFlag eq 'spe'}">
							<input class="searchInput" type="button" value="新&#12288;增" onclick="editActivity('','${param.roleFlag}');"/>
						</c:if>
						<input class="searchInput" type="button" value="导&#12288;出" onclick="exportExcel();"/>
					</div>
				</div>
			</form>
		</div>
		<div id="doctorListZi">
		</div>
	</div>
</div>
</html>