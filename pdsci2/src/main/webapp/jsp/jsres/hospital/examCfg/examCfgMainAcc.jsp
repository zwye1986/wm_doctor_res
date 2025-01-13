<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
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
		$('#sessionNumber').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
		$('#assessmentYear').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		})
		toPage(1);
	});
	function toPage(page) {
		if(page==undefined||page=="undefined")
				page=1;
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/examCfg/list'/>",$("#searchForm").serialize(),false);
	}
	function edit(arrangeFlow) {
		var url = "<s:url value='/jsres/examCfg/edit'/>?type=acc&arrangeFlow="+arrangeFlow;
		jboxOpen(url, "考核设置",1000,550);
	}
	function openInfo(arrangeFlow,isOpen) {
		var msg = "";
		if (isOpen == '${GlobalConstant.RECORD_STATUS_N}') {
			msg = "关闭";
		} else if (isOpen == '${GlobalConstant.RECORD_STATUS_Y}') {
			msg = "开放";
		}
		jboxConfirm("确认" + msg + "该年度考核吗？",function () {
			var url = "<s:url value='/jsres/examCfg/updateCfg?arrangeFlow='/>" + arrangeFlow+ "&isOpen=" + isOpen;
			jboxGet(url,null,function(resp){
				if(resp=="操作成功！")
					toPage($("#currentPage").val());
			},null,true);
		});
	}
	function del(arrangeFlow) {
		jboxConfirm("确认删除该年度考核吗？",function () {
			var url = "<s:url value='/jsres/examCfg/updateCfg?arrangeFlow='/>" + arrangeFlow+ "&recordStatus=N";
			jboxGet(url,null,function(resp){
				if(resp=="操作成功！")
					toPage(1);
			},null,true);
		});
	}
	function changeTrainSpes(t){
		var trainCategoryid=$("#trainingTypeId").val();
		if(trainCategoryid==""){
			$("select[name=trainingSpeId] option[value != '']").remove();
			return false;
		}
		$("select[name=trainingSpeId] option[value != '']").remove();
		$("#"+trainCategoryid+"_select").find("option").each(function(){
			$(this).clone().appendTo($("#trainingSpeId"));
		});
		return false;
	}
</script>

<div class="main_hd">
	<h2 class="underline">理论考核设置</h2>
</div>

<div class="div_search" style="width: 95%;line-height:normal;">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td class="td_left">培训类别：</td>
				<td><select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">
					<option value="AssiGeneral">助理全科</option>
					<%--<option value="">请选择</option>
					<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
						<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
					</c:forEach>--%>
				</select>
				</td>
				<td class="td_left">培训专业：</td>

				<td><select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 106px;">
					<option value="">全部</option>
				</select>
				</td>
				<td class="td_left">年&#12288;&#12288;级：</td>
				<td>
					<input type="text" id="sessionNumber" name="sessionNumber"  class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
				</td>
				<td class="td_left">年&#12288;&#12288;度：</td>
				<td>
					<input type="text" id="assessmentYear" name="assessmentYear" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>

				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;
					<input class="btn_green" type="button" value="考核设置" onclick="edit('');"/>
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
			<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
				<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:if>
		</c:forEach>
	</select>
	<select id="WMSecond_select">
		<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
			<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
				<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:if>
		</c:forEach>
	</select>
	<select id="AssiGeneral_select">
		<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
			<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
				<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:if>
		</c:forEach>
	</select>
	<select id="DoctorTrainingSpe_select">
		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
			<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
				<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:if>
		</c:forEach>
	</select>

</div>
</html>