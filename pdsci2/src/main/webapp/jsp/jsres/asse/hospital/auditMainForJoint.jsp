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
		width:6em;
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
		$('#applyYear').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
		initOrg();
		toPage();
        // changeTrainSpes();
	});

	function initOrg()
	{
		var datas=[];
		<c:forEach items="${orgs}" var="org">
		<c:if test="${sessionScope.currUser.orgFlow!=org.orgFlow }">
		var d={};
		d.id="${org.orgFlow}";
		d.text="${org.orgName}";
		datas.push(d);
		</c:if>
		</c:forEach>
		var itemSelectFuntion = function(){
			$("#orgFlow").val(this.id);
		};
		$.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
	}
	function toPage(page) {
		var data="";
		$("input[class='docType']:checked").each(function(){
			data+="&datas="+$(this).val();
		});
		if(data==""){
			jboxTip("请选择人员类型！");
			return false;
		}
		if(!$("#applyYear").val()){
			jboxTip("请选申请年份！");
			return false;
		}
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/doctorTheoryScore/auditList'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),true);
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
	function exportInfo()
	{
		if(!$("#applyYear").val()){
			jboxTip("请选申请年份！");
			return false;
		}
		var url = "<s:url value='/jsres/doctorTheoryScore/exportList'/>";
		jboxExp($("#searchForm"),url);
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
	<%--<h2  class="underline" >考核资格审查</h2>--%>
</div>
<div class="div_search" style="width: 95%;line-height:normal;">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
		<input type="hidden" name="orgFlow" value="${sessionScope.currUser.orgFlow}"/>
		<input type="hidden" name="joinOrgFlow" value="${sessionScope.currUser.orgFlow}"/>
		<input type="hidden" name="tabTag" value="${param.tabTag}"/>
		<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td class="td_left">培训类别：</td>
				<td>
					<!-- 禅道2143更改 协同基地只看助理全科数据 -->
					<%--<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">--%>
						<%--<option value="AssiGeneral" selected="selected">助理全科</option>--%>
					<%--</select>--%>
					<%--<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">--%>
					<select name="trainingTypeId" id="trainingTypeId" class="select" style="width:107px;">
						<option value="">请选择</option>
						<c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristList'}">
							<option value="DoctorTrainingSpe" selected="selected">住院医师</option>
							<option value="WMFirst" <c:if test="${param.trainingTypeId eq 'WMFirst'}">selected="selected"</c:if>>一阶段</option>
							<option value="WMSecond" <c:if test="${param.trainingTypeId eq 'WMSecond'}">selected="selected"</c:if>>二阶段</option>
						</c:if>
						<c:if test="${param.tabTag eq 'FristWait2' or param.tabTag eq 'FristList2'}">
							<option value="AssiGeneral" selected="selected">助理全科</option>
						</c:if>
						<%--<c:forEach items="${trainCategoryEnumList}" var="trainCategory">--%>
							<%--<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
						<%--</c:forEach>--%>
					</select>
				</td>
				<td class="td_left">培训专业：</td>
				<td>
					<select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 106px;">
						<option value="">全部</option>
						<c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristList'}">
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
									<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${param.tabTag eq 'FristWait2' or param.tabTag eq 'FristList2'}">
							<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
								<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
									<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
								</c:if>
							</c:forEach>
						</c:if>
					</select>
				</td>
				<td class="td_left">年&#12288;&#12288;级：</td>
				<td>
					<input type="text" id="sessionNumber" name="sessionNumber" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
				</td>
				<td class="td_left">结业考核&#12288;年&#12288;&#12288;份：</td>
				<td>
					<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">报考资格&#12288;材&#12288;&#12288;料：</td>
				<td>
					<select id="qualificationMaterialId" name="qualificationMaterialId" class="select" style="width: 106px;">
						<option value="">请选择</option>
						<option value="176">医师执业证书</option>
						<option value="177">医师资格证书</option>
						<option value="178">已通过国家执业医师考试的成绩单</option>
						<option value="201">已通过国家助理执业医师考试成绩单</option>
						<option value="暂无">暂无</option>
					</select>
				</td>
				<td class="td_left" colspan="">公共科目&#12288;是否合格：</td>
				<td>
					<select name="passFlag" class="select" style="width: 106px;">
						<option value="">请选择</option>
						<option value="1">合格</option>
						<option value="0">不合格</option>
					</select>
				</td>
				<td class="td_left">姓&#12288;&#12288;名：</td>
				<td>
					<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;margin-left: 0px;"/>
				</td>
				<td class="td_left">证&nbsp;件&nbsp;号&nbsp;：</td>
				<td>
					<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 100px;margin-left: 0px;"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">审核状态：</td>
				<c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristWait2'}">
					<td>
						<select name="auditStatusId" id="auditStatusId" class="select" style="width:107px;">
							<option value="Auditing" selected>待基地审核</option>
						</select>
					</td>
				</c:if>
				<c:if test="${param.tabTag ne 'FristWait' and param.tabTag ne 'FristWait2'}">
					<td>
						<select name="auditStatusId" id="auditStatusId" class="select"  style="width:107px;">
							<option value="">请选择</option>
							<c:forEach items="${jsResAsseAuditListEnumList}" var="type">
								<c:if test="${type.id ne 'Auditing'}">
									<option value="${type.id}">${type.name}</option>
								</c:if>
							</c:forEach>
						</select>
					</td>
				</c:if>
				<td class="td_left">数据完成&#12288;比例低于：</td>
				<td>
					<input type="text" name="completeBi"  class="input" style="width: 100px;margin-left: 0px;"/>
				</td>
				<td class="td_left">数据审核&#12288;比例低于：</td>
				<td>
					<input type="text" name="auditBi"  class="input" style="width: 100px;margin-left: 0px;"/>
				</td>
				<td class="td_left">申请年份：</td>
				<td>
					<input type="text" id="applyYear" name="applyYear" class="input" readonly="readonly" value="${pdfn:getCurrYear()}" style="width: 100px;margin-left: 0px"/>
				</td>
				</tr>
			<tr>
				<td class="td_left">人员类型：</td>
				<td colspan="4">
					<c:forEach items="${resDocTypeEnumList}" var="type">
						<label><input type="checkbox" id="${type.id}"value="${type.id}" checked class="docType" name="datas" />${type.name}&nbsp;</label>
					</c:forEach>
				</td>
				<td colspan="3">
					<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>
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