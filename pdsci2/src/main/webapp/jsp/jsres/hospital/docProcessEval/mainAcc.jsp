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
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#sessionNumber').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
		$('#graduationYear').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
		initOrg();
		$("#trainOrg").val("${org.orgName}");
		$("#orgFlow").val("${org.orgFlow}");
        changeTrainSpes();
		toPage(1,"${param.isQuery}");
	});

	function initOrg()
	{
		var datas=[];
		<c:forEach items="${orgs}" var="org">
		var d={};
		d.id="${org.orgFlow}";
		d.text="${org.orgName}";
		datas.push(d);
		</c:forEach>
		var itemSelectFuntion = function(){
			$("#orgFlow").val(this.id);
		};
		$.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
	}
	function toPage(page,isQuery) {
		var data="";
		$("input[class='docType']:checked").each(function(){
			data+="&datas="+$(this).val();
		});
		if(data==""){
			jboxTip("请选择人员类型！");
			return false;
		}
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/hospital/docProcessEvalList'/>?isQuery="+isQuery,$("#searchForm").serialize(),true);
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
	function showEvalList(trId,obj)
	{
		if($("#"+trId).is(":hidden"))
		{
			$("#"+trId).show();
			$(obj).html("收缩");
		}else{
			$("#"+trId).hide();
			$(obj).html("展开");
		}
	}
	function showEvalDetail(recordFlow)
	{
		var url="<s:url value='/jsres/teacher/showEvalDetail'/>?recordFlow="+recordFlow;
		jboxOpen(url,"考评",900,550,true);
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
<%--<div class="main_hd">--%>
	<%--<h2  class="underline" >学员考评查询</h2>--%>
<%--</div>--%>
<div class="div_search" style="width: 95%;line-height:normal;">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td class="td_left">培训基地：</td>
				<td>
					<input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off"  />
					<input type="hidden" name="orgFlow" id="orgFlow">
				</td>
				<td class="td_left">培训类别：</td>
				<td><select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >
					<option value="AssiGeneral">助理全科</option>
					<%--<option value="">请选择</option>
					<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
						<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
					</c:forEach>--%>
				</select>
				</td>
				<td class="td_left">培训专业：</td>
				<td><select name="trainingSpeId" id="trainingSpeId"class="select" >
					<option value="">全部</option>
				</select>
				</td>
				<td class="td_left">年&#12288;&#12288;级：</td>
				<td>
					<input type="text" id="sessionNumber" name="sessionNumber" class="input" readonly="readonly" />
				</td>
			</tr>
			<tr>
				<td class="td_left">结业考核&#12288;年&#12288;&#12288;份：</td>
				<td>
					<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" />
				</td>
				<td class="td_left">姓&#12288;&#12288;名：</td>
				<td>
					<input type="text" name="userName" value="${param.userName}" class="input"/>
				</td>
				<td class="td_left">证&nbsp;件&nbsp;号&nbsp;：</td>
				<td>
					<input type="text" name="idNo" value="${param.idNo}" class="input"/>
				</td>

				<td class="td_left">学员状态：</td>
				<td>
					<select name="doctorStatusId" class="select" >
						<option value="">全部</option>
						<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
							<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<td class="td_left">人员类型：</td>
				<td colspan="7">
					<c:forEach items="${jsResDocTypeEnumList}" var="type">
						<label><input type="checkbox" id="${type.id}"value="${type.id}" checked class="docType" name="datas" />${type.name}&nbsp;</label>
					</c:forEach>
                    &#12288;<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1,'');"/>
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
	<c:forEach items="${orgFlowList}" var="flow">
		<input type="hidden" id="${flow}" value="${flow}"/>
	</c:forEach>

</div>