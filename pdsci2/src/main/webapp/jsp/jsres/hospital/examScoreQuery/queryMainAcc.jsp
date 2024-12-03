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
		<c:forEach items="${resDocTypeEnumList}" var="type">
		<c:forEach items="${datas}" var="data">
		if("${data}"=="${type.id}"){
			$("#"+"${data}").attr("checked","checked");
		}
		</c:forEach>
		<c:if test="${empty datas}">
		$("#"+"${type.id}").attr("checked","checked");
		</c:if>
		</c:forEach>

	});
	function checkAll(obj){
		var f=false;
		if($(obj).attr("checked")=="checked")
		{
			f=true;
		}
		$(".docType").each(function(){
			if(f)
			{
				$(this).attr("checked","checked");
			}else {
				$(this).removeAttr("checked");
			}

		});
	}
	function changeAllBox(){
		if($(".docType:checked").length==$(".docType").length)
		{
			$("#all").attr("checked","checked");
		}else{
			$("#all").removeAttr("checked");
		}
	}
	function toPage(page) {
		if(page==undefined||page=="undefined")
				page=1;
		$("#currentPage").val(page);
		var year=$("#assessmentYear").val();
		var sessionNumber=$("#sessionNumber").val();
		if(year==""&&sessionNumber=="")
		{
			jboxTip("请选择年级！");
			return false;
		}
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/examScoreQuery/list'/>",$("#searchForm").serialize(),false);
	}
    function clearScore() {
        var assessmentYear=$("#assessmentYear").val();
        var sessionNumber=$("#sessionNumber").val();
        if(sessionNumber=="")
        {
            jboxTip("请选择年级！");
            return false;
        }
        if(assessmentYear=="")
        {
            jboxTip("请选择年度！");
            return false;
        }
		jboxConfirm('是否确认清空当前理论成绩，清空后无法恢复！',function(){
			var url = "<s:url value='/jsres/examScoreQuery/clearScore'/>";
			var data= $('#searchForm').serialize()
			jboxPost(url,data,function(resp){
				jboxTip("操作成功！清空 "+resp+" 条记录！");
				setTimeout(function(){
					toPage(1);
				},2000)
			},null,false);
		});
    }

	function exportInfo()
	{
		var year=$("#assessmentYear").val();
		if(year=="")
		{
			jboxTip("请选择年度！");
			return false;
		}
		jboxExp($("#searchForm"),"<s:url value='/jsres/examScoreQuery/exportInfo'/>");
	}
	function downloadExamPaper(recordFlow) {
		var url = "<s:url value='/jsres/examScoreQuery/downloadExamPaper?recordFlow='/>" + recordFlow;
		jboxGet(url,null,function(resp){
			var data=eval("("+resp+")");
			if(data.result=="1")
			{
//				window.open(data.url);
				$("#url").val(data.url);
				jboxExp($("#exportFrom"),"<s:url value='/jsres/examScoreQuery/downloadFile'/>");
			}else{
				jboxTip(data.msg);
			}
		},null,false);
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

<form id="exportFrom">
	<input type="hidden" id="url" name="url"/>
</form>
<div class="main_hd">
	<h2 class="underline">理论成绩查询</h2>
</div>

<div class="div_search" style="width: 95%;line-height:normal;">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td class="td_left">培训类别：</td>
				<td><select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">
				<%--	<option value="">请选择</option>
					<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
						<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
					</c:forEach>--%>
					<option value="AssiGeneral">助理全科</option>
				</select>
				</td>
				<td class="td_left">培训专业：</td>
				<td><select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 106px;">
					<option value="">全部</option>
				</select>
				</td>
				<td class="td_left">年&#12288;&#12288;级：</td>
				<td>
					<input type="text" id="sessionNumber" name="sessionNumber"  value="${pdfn:getCurrYear()-3}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
				</td>
				<td class="td_left">年&#12288;&#12288;度：</td>
				<td>
					<input type="text" id="assessmentYear" name="assessmentYear"  class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">姓&#12288;&#12288;名：</td>
				<td>
					<input type="text"  name="userName" class="input" style="width: 100px;margin-left: 0px"/>
				</td>
				<td class="td_left">人员类型：</td>
				<td colspan="3">
					<c:forEach items="${resDocTypeEnumList}" var="type">
						<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
					</c:forEach>
				</td>
				<td colspan="3">
					<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>
					<input class="btn_green" type="button" value="清空成绩" onclick="clearScore();"/>
					<input class="btn_green" type="button" value="导&#12288;出" onclick="exportInfo();"/>
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