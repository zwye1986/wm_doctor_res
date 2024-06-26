
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	toPage(1);
});
function toPage(page) {
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jszy/base/activityQuery/list'/>?roleFlag=${param.roleFlag}",$("#searchForm").serialize(),false);
}
function editActivity(activityFlow,role){
	if(activityFlow)
		jboxOpen("<s:url value='/jszy/base/activityQuery/editActivity'/>?activityFlow=" + activityFlow+"&role="+role,'编辑活动',600,400);
	else
		jboxOpen("<s:url value='/jszy/base/activityQuery/editActivity'/>?activityFlow=" + activityFlow+"&role="+role,'新增活动',600,400);
}
function exportExcel(){
	var url = "<s:url value='/jszy/base/activityQuery/exportList'/>?roleFlag=${param.roleFlag}";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}

function upload(activityFlow,isUpload){
	var url = "<s:url value='/jszy/base/activityQuery/upload'/>?activityFlow="+activityFlow+"&isUpload="+isUpload;
	if(isUpload=="Y")
	{
		jboxOpen(url, "上传活动图片",700,550);
	}else{
		jboxOpen(url, "查看活动图片",700,550);
	}
}
</script>
<div class="main_hd">
	<c:if test="${param.roleFlag eq 'teach'}">
		<h2 class="underline">教学活动维护</h2>
	</c:if>
	<c:if test="${ param.roleFlag eq 'head'}">
		<h2 class="underline">教学活动管理</h2>
	</c:if>
	<c:if test="${!(param.roleFlag eq 'teach'or param.roleFlag eq 'head')}">
		<h2 class="underline">教学活动查询</h2>
	</c:if>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
			<table class="searchTable">
				<tr>
					<td class="td_left">
						活动名称：
					</td>
					<td>
						<input type="text" name="activityName" value="" class="input" />
					</td>
					<td class="td_left">
						主&nbsp;讲&nbsp;人：
					</td>
					<td>
						<input type="text" name="userName" value="" class="input" />
					</td>
					<td class="td_left">
						活动形式：
					</td>
					<td>
						<select name="activityTypeId" class="select">
							<option value="">全部</option>
							<c:forEach items="${activityTypeEnumList}" var="a">
								<option value="${a.id}" >${a.name}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">
						科&#12288;&#12288;室：
					</td>
					<td>
						<select name="deptFlow" class="select">
							<option value="">全部</option>
							<c:forEach items="${depts}" var="dept">
								<option value="${dept.deptFlow}" >${dept.deptName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_left">
						开始时间：
					</td>
					<td>
						<input type="text" id="startDate" name="startTime" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
					</td>
					<td class="td_left">
						结束时间：
					</td>
					<td>
						<input type="text" id="endDate" name="endTime" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
					</td>
					<td id="func" colspan="3">
						&nbsp;<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage(1);"/>&nbsp;
						<c:if test="${param.roleFlag eq 'teach'or param.roleFlag eq 'head'}">
							<input class="btn_green" style="margin-left: 0px;" type="button" value="新&#12288;增" onclick="editActivity('','${param.roleFlag}');"/>&nbsp;
						</c:if>
						<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportExcel();"/>&nbsp;
					</td>
				</tr>
			</table>
	</form>
    </div>
   <div id="doctorListZi">
    </div>
</div>