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
	.blue2{
		background-color:dodgerblue;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('#sessionNumber').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		}).on('changeDate', function(e) {
			changeTrainSpes();
		});
        $('#startTime,#endTime').datepicker();
		//toPage("1");
		search($("#thisMonth"),'1','thisMonth');
	});
	function search(obj,page,type)
	{
		$("#type").val(type);
		$("input[type='radio']").attr("checked",false);
		if(type) {
			$("#" + type).attr("checked", true);
		}
		if(type != 'other') {
            $(".otherTime").hide();
            toPage(page);
        }else{
		    $(".otherTime").show();
		}
	}
	function chanegOrgLevel(obj)
	{
		$("#orgFlow").find("option").show();
		$("#orgFlow  option[value = '']").attr("selected","selected");
		var orgLevel=$(obj).val();
		if(orgLevel!="")
		{
			$("#orgFlow option[class != '"+orgLevel+"']").hide();
		}
		$("#orgFlow  option[value = '']").show();
	}
	function toPage(page) {
		var data="";
		<c:forEach items="${jsResDocTypeEnumList}" var="type">
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
		}
		</c:forEach>
		if(data==""){
			jboxTip("请选择人员类型！");
			return false;
		}
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();

        if(startTime>endTime){
            jboxTip("开始时间不能大于结束时间！");
            return;
        }
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/appUseInfo/appNotUseList'/>",$("#searchForm").serialize(),false);
	}

	function exportExcel(){
		var data="";
		<c:forEach items="${jsResDocTypeEnumList}" var="type">
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
		}
		</c:forEach>
		if(data==""){
			jboxTip("请选择人员类型！");
			return;
		}
        var startTime = $("#startTime").val();
        var endTime = $("#endTime").val();

        if(startTime>endTime){
            jboxTip("开始时间不能大于结束时间！");
            return;
        }
		var url = "<s:url value='/jsres/appUseInfo/exportExcel'/>";
		jboxTip("导出中…………");
		jboxExp($("#searchForm"), url);
		jboxEndLoading();
	}
	function changeTrainSpes(t){
		//清空原来专业！！！
		var sessionNumber=$("#sessionNumber").val();
		var trainCategoryid=$("#trainingTypeId").val();
		var orgFlow=$("#orgFlow").val();
		if(trainCategoryid==""){
			$("select[name=trainingSpeId] option[value != '']").remove();
			return false;
		}
		if(orgFlow == ""||sessionNumber==""){
			$("select[name=trainingSpeId] option[value != '']").remove();
			$("#"+trainCategoryid+"_select").find("option").each(function(){
				$(this).clone().appendTo($("#trainingSpeId"));
			});
			return false;
		}
		var url = "<s:url value='/jsres/doctor/searchResOrgSpeList'/>?sessionNumber="+sessionNumber+"&orgFlow="+orgFlow+"&speTypeId="+trainCategoryid;
		jboxGet(url, null, function(resp){
			$("select[name=trainingSpeId] option[value != '']").remove();
			if(resp!=""){
				var dataObj = resp;
				for(var i = 0; i<dataObj.length;i++){
					var speId = dataObj[i].speId;
					var speName = dataObj[i].speName;
					$option =$("<option></option>");
					$option.attr("value",speId);
					$option.text(speName);
					$("select[name=trainingSpeId]").append($option);
				}
			}
		}, null , false);
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
<div class="main_bd" id="div_table_0" >
	<div class="div_search" style="padding-left: 40px;">
		<form id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage"/>
			<input type="hidden" id="type" name="type"/>
			<input type="hidden" id="userListScope" name="userListScope" value="${param.userListScope}"/>
			<c:if test="${param.userListScope eq GlobalConstant.USER_LIST_LOCAL}">
				<table style="width: 100%;">
					<tr>
						<td style="text-align: right">培训基地：</td>
						<td>
							<select name="orgFlow" id="orgFlow" class="select" style="width: 136px"  onchange="changeTrainSpes('1');">
								<c:forEach items="${orgs}" var="org">
									<option value="${org.orgFlow}">${org.orgName}</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right">年&#12288;&#12288;级：</td>
						<td>
							<input type="text" id="sessionNumber" name="sessionNumber"value="${pdfn:getCurrYearByMonth()}"  style="width: 130px;margin-left: 0px;" class="input" readonly="readonly"/>
						</td>
						<td style="text-align: right">培训类别：</td>
						<td>
							<select name="trainingTypeId" id="trainingTypeId" class="select" style="width: 136px"  onchange="changeTrainSpes('1');">
								<option value="DoctorTrainingSpe">住院医师</option>
								<%--<option value="">请选择</option>
								<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
									<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
								</c:forEach>--%>
							</select>
						</td>
						<td style="text-align: right">培训专业：</td>
						<td>
							<select name="trainingSpeId" id="trainingSpeId" style="width: 136px" class="select" >
								<option value="">全部</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align: right">人员类型：</td>
						<td colspan="7">
							<c:forEach items="${jsResDocTypeEnumList}" var="type">
								<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas"  checked/>${type.name}&nbsp;</label>
							</c:forEach>
							&#12288;快速查询：
							<input id="thisMonth" name="fastSearch" type="radio" value="" onclick="search(this,'1','thisMonth');" >
							<label for="thisMonth">本月</label>&#12288;
							<input id="twoMonth" name="fastSearch" type="radio" value="" onclick="search(this,'1','twoMonth');" >
							<label for="twoMonth">上个月</label>&#12288;
							<input id="threeMonth" name="fastSearch" type="radio" value="" onclick="search(this,'1','threeMonth');" >
							<label for="threeMonth">前三个月</label>&#12288;
							<input id="other" name="fastSearch" type="radio" value="" onclick="search(this,'1','other');" >
							<label for="other">自定义</label>&#12288;
						</td>
					</tr>
					<tr>
						<td class="otherTime" hidden style="text-align: right">开始日期：</td>
						<td class="otherTime" hidden>
							<input type="text" id="startTime" name="startTime" value="${param.startTime}" style="width: 130px"  class="input" readonly="readonly" />
						</td>
						<td class="otherTime" hidden style="text-align: right">结束日期：</td>
						<td class="otherTime" hidden>
							<input type="text" id="endTime" name="endTime" value="${param.endTime}" style="width: 130px"  class="input" readonly="readonly" />
						</td>
						<td colspan="2">
							<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
							<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>
						</td>
					</tr>
				</table>
			</c:if>
			<c:if test="${param.userListScope eq GlobalConstant.USER_LIST_CHARGE}">

				<table style="width: 100%;">
					<tr>
						<td style="text-align: right">培训基地：</td>
						<td>
							<select name="orgFlow" id="orgFlow" class="select" style="width: 136px"  onchange="changeTrainSpes('1');">
								<option value="">全部</option>
								<c:forEach items="${orgs}" var="org">
									<option value="${org.orgFlow}">${org.orgName}</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right">年&#12288;&#12288;级：</td>
						<td>
							<input type="text" id="sessionNumber" name="sessionNumber"value="${pdfn:getCurrYear()}"  style="width: 130px;margin-left: 0px;" class="input" readonly="readonly"/>
						</td>
						<td style="text-align: right">培训类别：</td>
						<td>
							<select name="trainingTypeId" id="trainingTypeId" class="select" style="width: 136px"  onchange="changeTrainSpes('1');">
								<option value="DoctorTrainingSpe">住院医师</option>
								<%--<option value="">请选择</option>
								<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
									<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
								</c:forEach>--%>
							</select>
						</td>
						<td style="text-align: right">培训专业：</td>
						<td>
							<select name="trainingSpeId" id="trainingSpeId" style="width: 136px" class="select" >
								<option value="">全部</option>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align: right">人员类型：</td>
						<td colspan="7">
							<c:forEach items="${jsResDocTypeEnumList}" var="type">
								<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas"  checked/>${type.name}&nbsp;</label>
							</c:forEach>
							&#12288;快速查询：
							<input id="thisMonth" name="fastSearch" type="radio" value="" onclick="search(this,'1','thisMonth');" >
							<label for="thisMonth">本月</label>&#12288;
							<input id="twoMonth" name="fastSearch" type="radio" value="" onclick="search(this,'1','twoMonth');" >
							<label for="twoMonth">上个月</label>&#12288;
							<input id="threeMonth" name="fastSearch" type="radio" value="" onclick="search(this,'1','threeMonth');" >
							<label for="threeMonth">前三个月</label>&#12288;
							<input id="other" name="fastSearch" type="radio" value="" onclick="search(this,'1','other');" >
							<label for="other">自定义</label>&#12288;
							<%--<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>--%>
							<%--<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>--%>
						</td>
					<tr>
						<td class="otherTime" hidden style="text-align: right">开始日期：</td>
						<td class="otherTime" hidden>
							<input type="text" id="startTime" name="startTime" value="${param.startTime}" style="width: 130px;margin-right: 0px"  class="input" readonly="readonly" />
						</td>
						<td class="otherTime" hidden style="text-align: right">结束日期：</td>
						<td class="otherTime" hidden>
							<input type="text" id="endTime" name="endTime" value="${param.endTime}" style="width: 130px;margin-right: 0px"  class="input" readonly="readonly" />
						</td>
						<td colspan="2">
							<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
							<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>
						</td>
					</tr>
					</tr>
				</table>
			</c:if>
			<c:if test="${param.userListScope eq GlobalConstant.USER_LIST_GLOBAL}">

				<table style="width: 100%;margin-top: 10px;">
					<tr>
						<td style="text-align: right">基地类型：</td>
						<td>
							<select name="orgLevel" id="orgLevelId" class="select" style="width: 136px" onchange="chanegOrgLevel(this);">
								<option value="">全部</option>
								<c:forEach items="${orgLevelEnumList}" var="level">
									<c:if test="${orgLevelEnumGaugeTrainingBase.id !=level.id}">
										<option value="${level.id}" >${level.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right">培训基地：</td>
						<td>
							<select name="orgFlow" id="orgFlow" class="select" style="width: 136px"  onchange="changeTrainSpes('1');toPage('1')">
								<option value="">全部</option>
								<c:forEach items="${orgs}" var="org">
									<option value="${org.orgFlow}" class="${org.orgLevelId}">${org.orgName}</option>
								</c:forEach>
							</select>
						</td>
						<td style="text-align: right">年&#12288;&#12288;级：</td>
						<td>
							<input type="text" id="sessionNumber" name="sessionNumber"value="${pdfn:getCurrYear()}"  style="width: 130px;margin-left: 0px;" class="input" readonly="readonly"/>
						</td>
						<td style="text-align: right">培训类别：</td>
						<td>
							<select name="trainingTypeId" id="trainingTypeId" class="select" style="width: 136px"  onchange="changeTrainSpes('1');">
								<option value="DoctorTrainingSpe">住院医师</option>
								<%--<option value="">请选择</option>
								<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
									<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
								</c:forEach>--%>
							</select>
						</td>
					</tr>
					<tr>
						<td style="text-align: right">培训专业：</td>
						<td>
							<select name="trainingSpeId" id="trainingSpeId" style="width: 136px" class="select" >
								<option value="">全部</option>
							</select>
						</td>
						<td style="text-align: right">人员类型：</td>
						<td colspan="7">
							<c:forEach items="${jsResDocTypeEnumList}" var="type">
								<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" checked/>${type.name}&nbsp;</label>
							</c:forEach>
							&#12288;快速查询：
							<input id="thisMonth" name="fastSearch" type="radio" value="" onclick="search(this,'1','thisMonth');" >
							<label for="thisMonth">本月</label>
							<input id="twoMonth" name="fastSearch" type="radio" value="" onclick="search(this,'1','twoMonth');" >
							<label for="twoMonth">上个月</label>
							<input id="threeMonth" name="fastSearch" type="radio" value="" onclick="search(this,'1','threeMonth');" >
							<label for="threeMonth">前三个月</label>
							<input id="other" name="fastSearch" type="radio" value="" onclick="search(this,'1','other');" >
							<label for="other">自定义</label>
						</td>
					</tr>
					<%--<tr>--%>
						<%--<td colspan="8">--%>
							<%--<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>&#12288;--%>
							<%--<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>--%>
						<%--</td>--%>
					<%--</tr>--%>
					<tr>
						<td class="otherTime" hidden style="text-align: right">开始日期：</td>
						<td class="otherTime" hidden>
							<input type="text" id="startTime" name="startTime" value="${param.startTime}" style="width: 130px;margin-right: 0px"  class="input" readonly="readonly" />
						</td>
						<td class="otherTime" hidden style="text-align: right">结束日期：</td>
						<td class="otherTime" hidden>
							<input type="text" id="endTime" name="endTime" value="${param.endTime}" style="width: 130px;margin-right: 0px"  class="input" readonly="readonly" />
						</td>
						<td colspan="4">
							<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
							<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>
						</td>
					</tr>
				</table>
			</c:if>
		</form>
	</div>
	<div id="doctorListZi">
	</div>
</div>
<div style="display: none;">
	<select id="WMFirst_select">
		<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
			<option  value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="WMSecond_select">
		<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
			<option  value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="AssiGeneral_select">
		<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
			<option  value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="DoctorTrainingSpe_select">
		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
			<option  value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>

</div>
<div>
	<c:forEach items="${orgFlowList}" var="flow">
		<input type="hidden" id="${flow}" value="${flow}"/>
	</c:forEach>

</div>