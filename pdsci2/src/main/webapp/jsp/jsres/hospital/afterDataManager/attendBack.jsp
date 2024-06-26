<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
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
	.searchTable{
		width: auto;
	}
	.searchTable td{
		width: auto;
		height: auto;
		line-height: auto;
		text-align: left;
	}
	.searchTable .td_left{
		word-wrap:break-word;
		width:5em;
		height: auto;
		line-height: auto;
		text-align: right;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#startDate').datepicker({
			format:'yyyy-mm-dd'
		});
		$('#endDate').datepicker({
			format:'yyyy-mm-dd'
		});
		toPage();
	});

	function toPage(page) {
		var data="";
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/afterDataManager/afterAttendBackList'/>?roleFlag=${roleFlag}&baseFlag=${param.baseFlag}",$("#searchForm").serialize(),true);
	}
	function search(page) {

		var userCode=$('#userCode').val();
		if(!userCode){
			jboxTip("请输入学员用户名！");
			return false;
		}
		var startDate=$('#startDate').val();
		if(!startDate){
			jboxTip("请选择开始时间！");
			return false;
		}
		var endDate=$('#endDate').val();
		if(!endDate){
			jboxTip("请选择结束时间！");
			return false;
		}
		if(startDate>endDate)
		{
			jboxTip("开始时间不得大于结束时间！");
			return false;
		}
		toPage();
	}

	function attendBackList()
	{

		var userCode=$('#userCode').val();
		if(!userCode){
			jboxTip("请输入学员用户名！");
			return false;
		}
		var startDate=$('#startDate').val();
		if(!startDate){
			jboxTip("请选择开始时间！");
			return false;
		}
		var endDate=$('#endDate').val();
		if(!endDate){
			jboxTip("请选择结束时间！");
			return false;
		}
		if(startDate>endDate)
		{
			jboxTip("开始时间不得大于结束时间！");
			return false;
		}
		var url = "<s:url value='/jsres/afterDataManager/attendBackList'/>?baseFlag=${param.baseFlag}";
		jboxConfirm("确认退回学员考勤？",function(){
			jboxPost(url, $("#searchForm").serialize(), function (resp) {
				toPage();
			}, null, true);
		});
	}
</script>
<script type="text/javascript">
	String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
		if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
			return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
		} else {
			return this.replace(reallyDo, replaceWith);
		}
	}
</script>

<div class="main_hd">
	<h2 class="underline">退回日常考勤</h2>
</div>
<div>
	<div class="div_search" style="width: 95%;line-height:normal;">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td class="td_left">学员帐号：</td>
				<td colspan="2">
					<input type="text" id="userCode" name="userCode" value="${param.userCode}" class="input" style="width: 300px;margin-left: 0px;"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">考勤区间：</td>
				<td colspan="2">
					<input type="text" id="startDate" name="startDate"  class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
					~<input type="text" id="endDate" name="endDate"  class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
				</td>
				<td colspan="8" style="text-align: left;">
					<input class="btn_green" type="button" value="查&#12288;询" onclick="search();"/>
					<input class="btn_green" type="button" value="一键退回" onclick="attendBackList();"/>
				</td>
			</tr>
			<tr>
				<td colspan="8">
					注：1、只可处理本基地学员数据
				</td>
			</tr>
			<tr>
				<td colspan="8">
					&#12288;&#12288;2、退回学员日常考勤审核数据
				</td>
			</tr>
			<tr>
				<td colspan="8">
					&#12288;&#12288;3、【一键退回】为退回考勤区间内的所有考勤信息
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="doctorListZi" style="width: 95%">

</div>
<div style="display: none;">
	<select id="WMFirst_select">
		<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="WMSecond_select">
		<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="AssiGeneral_select">
		<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="DoctorTrainingSpe_select">
		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>

</div>
<div>
</div>
	<c:forEach items="${orgFlowList}" var="flow">
		<input type="hidden" id="${flow}" value="${flow}"/>
	</c:forEach>

</div>