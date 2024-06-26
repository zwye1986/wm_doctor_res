<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="false"/>
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
<script type="text/javascript">
	$(function(){
		<c:forEach items="${baseevalFormCfgs}" var="item">
			$('#${item.formFlow}').attr("checked","checked");
		</c:forEach>
	})
	function search(){
		$("#searchForm").submit();
	}
	function save(){
		if(!$(".trainingSpeId").val()){
			jboxTip("请选择专业基地")
			return;
		}
		if(!$("input[type='checkbox']:checked").val()){
			jboxTip("请选择评价表单")
			return;
		}
		jboxConfirm("确认保存？",function(){
			var trainingSpeId = $(".trainingSpeId").val();
			var recordFlow = $(".recordFlow").val();
			var formFlows = [];
			$("input[type='checkbox']:checked").each(function(){
				var formFlow = $(this).val();
				formFlows.push(formFlow);
			});
			var data = {formFlows:formFlows};
			jboxPost('<s:url value="/res/ProfessionalBase/saveEvalCfg"/>?trainingSpeId='+trainingSpeId,
					'jsonData='+JSON.stringify(data),function(resp){
				if(resp == 1){
					jboxTip("操作成功");
					setTimeout(function() {
						search();
					},500)
				}
		},null,false);
		})
	}
	function searchform(obj){
		var formName = $(obj).val();
		if(formName==""){
			$(obj).parent().parent().find("label").show();
		}else{
			$(obj).parent().parent().find("label").each(function(){
				var name = $(this).children().eq(0).attr("value2");
				var array = formName.split();
				for(var i=0;i<array.length;i++){
					if(name.indexOf(array[i])>-1){
						$(this).show();
					}else{
						$(this).hide();
					}
				}
			})
		}
	}
</script>
</head>
<body style="overflow: auto">
	<table class="xllist">
		<tr>
			<th style="width: 15%">专业基地</th>
			<th style="text-align: left;padding-left:5px;">
				<form id="searchForm" action='<s:url value="/res/ProfessionalBase/evalCfg"/>'>
				<select name="trainingSpeId" class="xlselect trainingSpeId" onchange="search()">
					<option  value="">请选择</option>
					<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
						<option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
					</c:forEach>
				</select>
				</form>
			</th>
		</tr>
		<tr>
			<td>
				评估表单列表
			</td>
			<td>
				<div style="text-align: left;margin: 5px">					
					<input type="text" class="xltext" id="fuzzyQuery" placeholder="关键字检索" onkeyup="searchform(this);">
				</div>
				<div style="position: relative" style="max-height:250px;overflow: auto;">
					<c:forEach items="${baseevalFormList}" var="form">
						<div style="width: 43.5%;float: left;text-align: left;padding-left: 5px;">
						<label><input type="checkbox" id="${form.formFlow}" value="${form.formFlow}"
									  value2="${form.formName}">${form.formName}</label>
						</div>
					</c:forEach>
					<%--<input type="hidden" name="recordFlow" class="recordFlow" value="${baseevalFormCfg.recordFlow}">--%>
				</div>
			</td>
		</tr>
	</table>
	<div style="margin: 15px;text-align: center">
		<button class="search" onclick="save();">保&#12288;存</button>
		<button class="search" onclick="jboxClose();">关&#12288;闭</button>
	</div>
</body>
</html>