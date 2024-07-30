<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red;}
</style>
<script type="text/javascript">
$(document).ready(function(){
	toPage();
	
});
function toPage(page) {
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jsres/activityTarget/list'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),false);
}
function add(targetFlow)
{

	var url="<s:url value='/jsres/activityTarget/add'/>?targetFlow="+targetFlow;
	if(targetFlow==""){
		jboxOpen(url, "新增评价指标", 500, 250,true);
	}else{
		jboxOpen(url, "编辑评价指标", 500, 250,true);
	}
}
</script>
<div class="main_hd">
		<h2 class="underline">指标管理</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
			<table class="searchTable">
				<tr>
					<td class="td_left">
						评价指标：
					</td>
					<td>
						<input type="text" name="targetName" value="" class="input" />
					</td>
					<td class="td_left">
						活动形式：
					</td>
					<td>
						<select id="activityTypeId" name="activityTypeId" class="select"
								style="width: 125%;margin: 0 5px;">
							<option value=""></option>
							<c:forEach items="${activityTypeEnumList}" var="activityType">
								<option value="${activityType.id}" ${target.activityTypeId eq activityType.id ? 'selected' : ''}>${activityType.name}</option>
							</c:forEach>
						</select>
					</td>
					<td id="func" style="" colspan="6">
						&nbsp;<input class="btn_green" style="margin-left: 50px;" type="button" value="查&#12288;询" onclick="toPage(1);"/>&nbsp;
						<input class="btn_green" style="margin-left: 0px;" type="button" value="新&#12288;增" onclick="add('');"/>&nbsp;
					</td>
				</tr>
			</table>
	</form>
    </div>
   <div id="doctorListZi">
    </div>
</div>