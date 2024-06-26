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
.title_tab{
	margin-top: 0;
}

</style>
<script type="text/javascript">
$(document).ready(function(){
	 toPage(1);
});
function toPage(page) {
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jsres/speAdmin/activityQuerylist'/>",$("#searchForm").serialize(),false);
}

function exportExcel(){
	var url = "<s:url value='/jsres/speAdmin/activityExportList'/>";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}

function upload(activityFlow,isUpload){
	var url = "<s:url value='/jsres/activityQuery/upload'/>?activityFlow="+activityFlow+"&isUpload="+isUpload;
	if(isUpload=="Y")
	{
		jboxOpen(url, "上传活动图片",700,550);
	}else{
		jboxOpen(url, "查看活动图片",700,550);
	}
}

function selTag(tag,type,flag){
    $(".selectTag").removeClass("selectTag");
    $(tag).addClass("selectTag");
   if(flag == 'activityManage'){
       toPage(1);
   }else {
       var url ="<s:url value='/jsres/activityQuery/activityMain'/>?roleFlag=${param.roleFlag}";
       var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
       jboxMessager(iframe,'教学活动一览',1000,800);
   }

}
</script>
<div class="main_hd">

	<h2 class="underline">教学活动查询</h2>
	<div class="title_tab">
		<ul id="reducationTab2">
			<li  id="selectTab2" class="selectTag" onclick="selTag(this,'selectTab2','activityManage');"><a id="activityManage2">教学活动查询</a></li>
			<li  id="tab2" onclick="selTag(this,'tab2','EventCalendar');"><a id="EventCalendar2">教学活动一览</a></li>
		</ul>
	</div>
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
					<td class="td_left">
						审核状态：
					</td>
					<td>
						<select name="activityStatus" class="select">
							<option value="">全部</option>
							<option value="audit">待审核</option>
							<option value="pass">已通过</option>
							<option value="unpass">未通过</option>
							<option value="over">已过期</option>
						</select>
					</td>
				</tr>
				<tr>

					<td id="func" colspan="3">
						<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage(1);"/>&nbsp;
						<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportExcel();"/>&nbsp;
					</td>
				</tr>
			</table>
	</form>
    </div>
   <div id="doctorListZi">
    </div>
</div>
