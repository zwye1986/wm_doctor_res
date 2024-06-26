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
		<c:forEach items="${trainingSpeDeptList}" var="dept">
			$('#${dept.deptFlow}').attr("checked","checked");
		</c:forEach>

		<c:forEach items="${notCurrentSpeDeptList}" var="dept">
			$('#${dept.deptFlow}').attr("disabled","disabled").parent().attr("title",'${dept.trainingSpeName}');
		</c:forEach>
	})
	function search(){
		$("#searchForm").submit();
	}
	function save(){
		if(!$(".resTrainingSpeId").val()){
			jboxTip("请选择专业基地")
			return;
		}
		jboxConfirm("确认保存？",function(){
			var resTrainingSpeId = $(".resTrainingSpeId").val();
			var deptList = [];
			$("input[type='checkbox']:checked").filter(":not(:disabled)").each(function(){
				var deptFlow = $(this).val();
				var deptName = $(this).attr("value2");
				deptList.push({"deptFlow":deptFlow,"deptName":deptName});
			});
			var jsonData = {"resTrainingSpeId":resTrainingSpeId,"deptList":deptList};
			console.log({jsondata:JSON.stringify(jsonData)});
			jboxPost('<s:url value="/res/ProfessionalBase/saveDepts"/>',{jsondata:JSON.stringify(jsonData)},function(resp){
				if(resp == "${GlobalConstant.OPRE_SUCCESSED}"){
					jboxTip(resp);
					setTimeout(function() {
						search();
					},500)
				}
		},null,false);
		})
	}
	function searchdept(obj){
		var deptName = $(obj).val();
		if(deptName==""){
			$(obj).parent().parent().find("label").show();
		}else{
			$(obj).parent().parent().find("label").each(function(){
				var name = $(this).children().eq(0).attr("value2");
				var array = deptName.split();
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
				<form id="searchForm" action='<s:url value="/res/ProfessionalBase/deptsCfg"/>'>
				<select name="resTrainingSpeId" class="xlselect resTrainingSpeId" onchange="search()">
					<option  value="">请选择</option>
					<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
						<option value="${dict.dictId}" ${param.resTrainingSpeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
					</c:forEach>
				</select>
				</form>
			</th>
		</tr>
		<tr>
			<td>
				所含科室
			</td>
			<td>
				<div style="text-align: left;margin: 5px">					
					<input type="text" class="xltext" id="fuzzyQuery" placeholder="关键字检索" onkeyup="searchdept(this);">
				</div>
				<div style="position: relative" style="max-height:250px;overflow: auto;">
					<c:forEach items="${sysDeptList}" var="dept">
						<div style="width: 23.5%;float: left;text-align: left;padding-left: 5px;">
						<label><input type="checkbox" id="${dept.deptFlow}" value="${dept.deptFlow}" value2="${dept.deptName}">${dept.deptName}</label>
						</div>
					</c:forEach>
				</div>
			</td>
		</tr>
		<c:if test="${empty sysDeptList}">
			<tr><td colspan="20" style="text-align: center">暂无数据</td></tr>
		</c:if>
		<tr>
			<td colspan="2"><font color="red">Tip:每个科室只能对应一个专业基地</font></td>
		</tr>
	</table>
	<div style="margin: 15px;text-align: center">
		<button class="search" onclick="save();">保&#12288;存</button>
		<button class="search" onclick="jboxClose();">关&#12288;闭</button>
	</div>
</body>
</html>