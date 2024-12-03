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
.grid td{
	border: 1px solid #e7e7eb;
}
</style>
<script type="text/javascript" src="<s:url value='/js/Scoll/Scorll2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){
		var style={"margin-left":"0px","width":"940px"};
		var options ={
			"colums":2//根据固定列的数量
		};
		//$("#dataTable").Scorll(options,style,true,null);

	});
$(document).ready(function(){
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
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
	changeTrainSpes();
});
function toPage() {
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("年级不能为空！");
		return;
	}
	var trainTypeId=$("#trainTypeId").val();
	if(trainTypeId==""){
		jboxTip("培训类别不能为空！");
		return;
	}
	var data="";
	<c:forEach items="${resDocTypeEnumList}" var="type">
	if($("#"+"${type.id}").attr("checked")){
		data+="&datas="+$("#"+"${type.id}").val();
	}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return false;
	}
	jboxPostLoad("content","<s:url value='/jsres/manage/zlxytj2'/>",$("#searchForm").serialize(),true);
}

function exportExcel(){
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("年级不能为空！");
		return;
	}
	var trainTypeId=$("#trainTypeId").val();
	if(trainTypeId==""){
		jboxTip("培训类别不能为空！");
		return;
	}
	var data="";
	<c:forEach items="${resDocTypeEnumList}" var="type">
	if($("#"+"${type.id}").attr("checked")){
		data+="&datas="+$("#"+"${type.id}").val();
	}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return false;
	}
	var url = "<s:url value='/jsres/manage/exportZlxytj2'/>";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
	function heightFiexed(){
		//修正高度
		var toFixedTd = $(".toFiexdDept");
		$(".fixedBy").each(function(i){
			$(toFixedTd[i]).height($(this).height());
		});
	}

	onresize = function(){
		heightFiexed();
	};
	$(function(){
		heightFiexed();
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
function showSubList(obj){
	var subSize=$(obj).attr("subSize");
	var cityId=$(obj).attr("cityId");
	var orgFlow=$(obj).attr("orgFlow");
	var rowspan=$("#"+cityId+"First").attr("rowspan")||1;
	var rowspan2=$("#"+cityId+"Second").attr("rowspan")||1;
	if(parseInt(subSize)>0)
	{
		if($("."+orgFlow).is(":hidden")){
			rowspan=parseInt(rowspan)+parseInt(subSize);
			rowspan2=parseInt(rowspan2)+parseInt(subSize);
			$("."+orgFlow).show();
		}else{
			rowspan=parseInt(rowspan)-parseInt(subSize);
			rowspan2=parseInt(rowspan2)-parseInt(subSize);
			$("."+orgFlow).hide();
		}
		$("#"+cityId+"First").attr("rowspan",rowspan);
		$("#"+cityId+"Second").attr("rowspan",rowspan2);
	}
}
	function tableFixed(div){
		$("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
		$("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
	}

	function changeTrainSpes(t){
		var trainCategoryid=$("#trainTypeId").val();
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
    <h2 class="underline">招录学员统计</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
		<input type="hidden" id="joint" name="joint" value="Y" class="input" readonly="readonly"/>
			<table class="searchTable">
				<tr>
					<td class="td_left">年&#12288;&#12288;级：</td>
					<td>
						<input type="text" id="sessionNumber" name="sessionNumber" value="${sessionNumber}" class="input" readonly="readonly"/>
					</td>
					<td class="td_left">城&#12288;&#12288;市：</td>
					<td>
						<select name="orgCityId" id="orgCityId" class="select">
							<option value="" ${empty param.orgCityId?"selected":""}>全部</option>
							<option value="320100" ${param.orgCityId eq 320100?"selected":""}>南京市</option>
							<option value="320200" ${param.orgCityId eq 320200?"selected":""}>无锡市</option>
							<option value="320300" ${param.orgCityId eq 320300?"selected":""}>徐州市</option>
							<option value="320400" ${param.orgCityId eq 320400?"selected":""}>常州市</option>
							<option value="320500" ${param.orgCityId eq 320500?"selected":""}>苏州市</option>
							<option value="320600" ${param.orgCityId eq 320600?"selected":""}>南通市</option>
							<option value="320700" ${param.orgCityId eq 320700?"selected":""}>连云港市</option>
							<option value="320800" ${param.orgCityId eq 320800?"selected":""}>淮安市</option>
							<option value="320900" ${param.orgCityId eq 320900?"selected":""}>盐城市</option>
							<option value="321000" ${param.orgCityId eq 321000?"selected":""}>扬州市</option>
							<option value="321100" ${param.orgCityId eq 321100?"selected":""}>镇江市</option>
							<option value="321200" ${param.orgCityId eq 321200?"selected":""}>泰州市</option>
							<option value="321300" ${param.orgCityId eq 321300?"selected":""}>宿迁市</option>
						</select>
					</td>
					<td class="td_left">培训类别：</td>
					<td>
						<select name="trainTypeId" id="trainTypeId" class="select" onchange="changeTrainSpes('1')">
							<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
								<option value="${trainCategory.id}" <c:if test="${param.trainTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
							</c:forEach>
						</select>
					</td>
					<td class="td_left">培训专业：</td>
					<td>
						<select name="trainingSpeId" id="trainingSpeId"class="select" >
							<option value="">全部</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="td_left">人员类型：</td>
					<td colspan="3">
						<label><input type="checkbox" id="all"value="all"  name="all" checked onclick="checkAll(this);"/>全部&nbsp;</label>
						<c:forEach items="${resDocTypeEnumList}" var="type">
							<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
						</c:forEach>
					</td>
					<td id="func" colspan="6">
						<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;
						<input class="btn_green" style="margin-left: 0px;" type="button" value="导&#12288;出" onclick="exportExcel();"/>&nbsp;
					</td>
				</tr>
			</table>
	</form>
    </div>
   <div class="div_search">
	   <div id="tableContext"style="width:900px; margin-top: 10px;margin-bottom: 10px;overflow: auto;margin-left: 0px;" onscroll="tableFixed(this);">
		   <!--第一次 -->
		   <div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
			   <table class="grid"style="width: auto;" >
				   <tr>
					   <th style="min-width: 100px;max-width: 100px;border: 1px solid #e7e7eb;" class="toFiexdDept">地市名称</th>
					   <th style="min-width: 150px;max-width: 150px;border: 1px solid #e7e7eb;" class="toFiexdDept">基地名称</th>
					   <c:set var= "mk" value="dictTypeEnum${param.trainTypeId}List"/>
					   <c:forEach items="${applicationScope[mk]}" var="dict">
						   <c:if test="${not empty param.trainingSpeId and param.trainingSpeId eq dict.dictId }">
							   <th style="min-width: 150px;max-width: 150px;border: 1px solid #e7e7eb;"class="fixedBy">
									   ${dict.dictName}
							   </th>
						   </c:if>
						   <c:if test="${empty param.trainingSpeId}">
							   <th style="min-width: 150px;max-width: 150px;border: 1px solid #e7e7eb;"class="fixedBy">
									   ${dict.dictName}
							   </th>
						   </c:if>
					   </c:forEach>
					   <th style="min-width: 80px;max-width: 80px;border: 1px solid #e7e7eb;"class="fixedBy">总计</th>
				   </tr>
			   </table>
		   </div>
		   <!--第二次 -->
		   <div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">
			   <table class="grid" style="min-width:250px;max-width: 250px;border-right: 0px">
				   <tr>
					   <th style="min-width: 100px;max-width: 100px;border: 1px solid #e7e7eb;" class="toFiexdDept">地市名称</th>
					   <th style="min-width: 150px;max-width: 150px;border: 1px solid #e7e7eb;" class="toFiexdDept">基地名称</th>
				   </tr>
				   <c:forEach items="${citys}" var="city">
					   <c:set var="orgFlows" value="${cityOrgMap[city.cityId]}"></c:set>
					   <c:set var="rowspan" value="${fn:length(orgFlows)}"></c:set>
					   <c:if test="${not empty orgFlows and (city.cityId eq param.orgCityId || empty param.orgCityId)}">
						   <c:forEach items="${orgFlows}" var="orgFlow" varStatus="s">
							   <c:set var="jointOrgFlows" value="${jointOrgMap[orgFlow]}"></c:set>
							   <c:set var="subSize" value="${fn:length(jointOrgFlows)}"></c:set>
							   <c:if test="${s.first}">
									<tr >
										<td  style="min-width: 100px;max-width: 100px;border: 1px solid #e7e7eb;background-color: white;" rowspan="${rowspan}" id="${city.cityId}First" >
											${city.cityName}
										</td>
										<td <c:if test="${param.joint eq 'Y' and (not empty jointOrgFlows)}">style="color:#459ae9;cursor: pointer;background-color: white;min-width: 100px;max-width: 100px;border: 1px solid #e7e7eb;" onclick="showSubList(this);" </c:if>
										 	<c:if test="${param.joint ne 'Y'or empty jointOrgFlows}">style="background-color: white;min-width: 100px;max-width: 100px;border: 1px solid #e7e7eb;" </c:if>
											class="fix"  cityId="${city.cityId}" orgFlow="${orgFlow}" subSize="${subSize}">
											${orgNameMap[orgFlow]}
										</td>
									</tr>
							   </c:if>
							   <c:if test="${!s.first}">
								   <tr >
									   <td <c:if test="${param.joint eq 'Y' and (not empty jointOrgFlows)}">style="color:#459ae9;cursor: pointer;background-color: white;" onclick="showSubList(this);" </c:if>
									     <c:if test="${param.joint ne 'Y'or empty jointOrgFlows}">style="background-color: white;" </c:if>
										   class="fix"  cityId="${city.cityId}" orgFlow="${orgFlow}" subSize="${subSize}">
											${orgNameMap[orgFlow]}
									   </td>
								   </tr>
							   </c:if>
							   <c:forEach items="${jointOrgFlows}" var="jointOrgFlow">
								   <tr style="display: none;" class="${orgFlow}">
									   <th style="border: 1px solid #e7e7eb;font-weight: normal;"class="fix">
											${orgNameMap[jointOrgFlow]}
									   </th>
								   </tr>
							   </c:forEach>
						   </c:forEach>
					   </c:if>
				   </c:forEach>
				   <tr >
					   <td style="background-color: white;" colspan="2">
						   合计(已包含协同)
					   </td>
				   </tr>
			   </table>
		   </div>
		   <!--第三次  -->
		   <div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
			   <table class="grid" style="width: auto;border-right: 0px" >
				   <tr>
					   <th style="min-width: 100px;max-width: 100px;border: 1px solid #e7e7eb;" class="toFiexdDept">地市名称</th>
					   <th style="min-width: 150px;max-width: 150px;border: 1px solid #e7e7eb;" class="toFiexdDept">基地名称</th>
				   </tr>
			   </table>
		   </div>
		   <div style="width: 0px; overflow: visible;   top: 0px; left: 0px;">
			   <table border="0" cellpadding="0" cellspacing="0" class="grid" id="dataTable">
				   <tr>
					   <th style="min-width: 100px;max-width: 100px;border: 1px solid #e7e7eb;">地市名称</th>
					   <th style="min-width: 150px;max-width: 150px;border: 1px solid #e7e7eb;">基地名称</th>
					   <c:set var= "mk" value="dictTypeEnum${param.trainTypeId}List"/>
					   <c:forEach items="${applicationScope[mk]}" var="dict">
						   <c:if test="${not empty param.trainingSpeId and param.trainingSpeId eq dict.dictId }">
							   <th style="min-width: 150px;max-width: 150px;border: 1px solid #e7e7eb;"class="fixedBy">
									   ${dict.dictName}
							   </th>
						   </c:if>
						   <c:if test="${empty param.trainingSpeId}">
							   <th style="min-width: 150px;max-width: 150px;border: 1px solid #e7e7eb;"class="fixedBy">
									   ${dict.dictName}
							   </th>
						   </c:if>
					   </c:forEach>
					   <th style="min-width: 80px;max-width: 80px;border: 1px solid #e7e7eb;">总计</th>
				   </tr>
				   <c:set var="zongji" value="0"></c:set>
				   <c:forEach items="${citys}" var="city">
					   <c:set var="orgFlows" value="${cityOrgMap[city.cityId]}"></c:set>
					   <c:set var="rowspan" value="${fn:length(orgFlows)}"></c:set>
					   <c:if test="${not empty orgFlows and (city.cityId eq param.orgCityId || empty param.orgCityId)}">
						   <c:forEach items="${orgFlows}" var="orgFlow" varStatus="s">
							   <c:set var="jointOrgFlows" value="${jointOrgMap[orgFlow]}"></c:set>
							   <c:set var="subSize" value="${fn:length(jointOrgFlows)}"></c:set>
							   <c:if test="${s.first}">
									<tr >
										<td  style="min-width: 100px;max-width: 100px;border: 1px solid #e7e7eb;" rowspan="${rowspan}" id="${city.cityId}Second">
											${city.cityName}
										</td>
										<td  style="min-width: 150px;max-width: 150px;border: 1px solid #e7e7eb;" cityId="${city.cityId}" orgFlow="${orgFlow}" subSize="${subSize}">
											${orgNameMap[orgFlow]}
										</td>
										<c:set var="sum" value="0"></c:set>
										<c:forEach items="${applicationScope[mk]}" var="dict">
											<c:if test="${not empty param.trainingSpeId and param.trainingSpeId eq dict.dictId }">
												<c:set var="key" value="${city.cityId}${orgFlow}${dict.dictId}"></c:set>
												<c:set var="flow" value="${orgFlow}${param.trainTypeId}${dict.dictId}"/>
												<c:if test="${orgSpeFlagMap[flow] eq GlobalConstant.FLAG_Y}">
													<c:set var="sum" value="${sum+cityOrgNumMap[key]}"></c:set>
													<td>${empty cityOrgNumMap[key]?0:cityOrgNumMap[key]}</td>
												</c:if>
												<c:if test="${orgSpeFlagMap[flow] != GlobalConstant.FLAG_Y}">
													<td>--</td>
												</c:if>
											</c:if>
											<c:if test="${empty param.trainingSpeId}">
												<c:set var="key" value="${city.cityId}${orgFlow}${dict.dictId}"></c:set>
												<c:set var="flow" value="${orgFlow}${param.trainTypeId}${dict.dictId}"/>
												<c:if test="${orgSpeFlagMap[flow] eq GlobalConstant.FLAG_Y}">
													<c:set var="sum" value="${sum+cityOrgNumMap[key]}"></c:set>
													<td>${empty cityOrgNumMap[key]?0:cityOrgNumMap[key]}</td>
												</c:if>
												<c:if test="${orgSpeFlagMap[flow] != GlobalConstant.FLAG_Y}">
													<td>--</td>
												</c:if>
											</c:if>
										</c:forEach>
										<td>
											${sum}
											<c:set var="zongji" value="${sum+zongji}"></c:set>
										</td>
									</tr>
							   </c:if>
							   <c:if test="${!s.first}">
								   <tr >
									   <td  cityId="${city.cityId}" orgFlow="${orgFlow}" subSize="${subSize}">
											${orgNameMap[orgFlow]}
									   </td>
									   <c:set var="sum" value="0"></c:set>
									   <c:forEach items="${applicationScope[mk]}" var="dict">
										   <c:if test="${not empty param.trainingSpeId and param.trainingSpeId eq dict.dictId }">
											   <c:set var="key" value="${city.cityId}${orgFlow}${dict.dictId}"></c:set>
											   <c:set var="flow" value="${orgFlow}${param.trainTypeId}${dict.dictId}"/>
											   <c:if test="${orgSpeFlagMap[flow] eq GlobalConstant.FLAG_Y}">
												   <c:set var="sum" value="${sum+cityOrgNumMap[key]}"></c:set>
												   <td>${empty cityOrgNumMap[key]?0:cityOrgNumMap[key]}</td>
											   </c:if>
											   <c:if test="${orgSpeFlagMap[flow] != GlobalConstant.FLAG_Y}">
												   <td>--</td>
											   </c:if>
										   </c:if>
										   <c:if test="${empty param.trainingSpeId}">
											   <c:set var="key" value="${city.cityId}${orgFlow}${dict.dictId}"></c:set>
											   <c:set var="flow" value="${orgFlow}${param.trainTypeId}${dict.dictId}"/>
											   <c:if test="${orgSpeFlagMap[flow] eq GlobalConstant.FLAG_Y}">
												   <c:set var="sum" value="${sum+cityOrgNumMap[key]}"></c:set>
												   <td>${empty cityOrgNumMap[key]?0:cityOrgNumMap[key]}</td>
											   </c:if>
											   <c:if test="${orgSpeFlagMap[flow] != GlobalConstant.FLAG_Y}">
												   <td>--</td>
											   </c:if>
										   </c:if>
									   </c:forEach>
									   <td>
											   ${sum}
										   <c:set var="zongji" value="${sum+zongji}"></c:set>
									   </td>
								   </tr>
							   </c:if>
							   <c:forEach items="${jointOrgFlows}" var="jointOrgFlow">
								   <tr style="display: none;" class="${orgFlow}">
									   <th style="border: 1px solid #e7e7eb;font-weight: normal;">
											${orgNameMap[jointOrgFlow]}
									   </th>
									   <c:set var="sum" value="0"></c:set>
									   <c:forEach items="${applicationScope[mk]}" var="dict">
										   <c:if test="${not empty param.trainingSpeId and param.trainingSpeId eq dict.dictId }">
											   <c:set var="key" value="${city.cityId}${jointOrgFlow}${dict.dictId}"></c:set>
											   <c:set var="flow" value="${jointOrgFlow}${param.trainTypeId}${dict.dictId}"/>
											   <c:if test="${orgSpeFlagMap[flow] eq GlobalConstant.FLAG_Y}">
												   <c:set var="sum" value="${sum+cityOrgNumMap[key]}"></c:set>
												   <th style="border: 1px solid #e7e7eb;font-weight: normal;">${empty cityOrgNumMap[key]?0:cityOrgNumMap[key]}</th>
											   </c:if>
											   <c:if test="${orgSpeFlagMap[flow] != GlobalConstant.FLAG_Y}">
												   <th style="border: 1px solid #e7e7eb;font-weight: normal;">--</th>
											   </c:if>
										   </c:if>
										   <c:if test="${empty param.trainingSpeId}">
											   <c:set var="key" value="${city.cityId}${jointOrgFlow}${dict.dictId}"></c:set>
											   <c:set var="flow" value="${jointOrgFlow}${param.trainTypeId}${dict.dictId}"/>
											   <c:if test="${orgSpeFlagMap[flow] eq GlobalConstant.FLAG_Y}">
												   <c:set var="sum" value="${sum+cityOrgNumMap[key]}"></c:set>
												   <th style="border: 1px solid #e7e7eb;font-weight: normal;">${empty cityOrgNumMap[key]?0:cityOrgNumMap[key]}</th>
											   </c:if>
											   <c:if test="${orgSpeFlagMap[flow] != GlobalConstant.FLAG_Y}">
												   <th style="border: 1px solid #e7e7eb;font-weight: normal;">--</th>
											   </c:if>
										   </c:if>

									   </c:forEach>
									   <th style="border: 1px solid #e7e7eb;font-weight: normal;">${sum}</th>
								   </tr>
							   </c:forEach>
						   </c:forEach>
					   </c:if>
				   </c:forEach>
				   <tr>
					   <td colspan="2">合计(已包含协同)</td>
					   <c:forEach items="${applicationScope[mk]}" var="dict">
						   <c:if test="${not empty param.trainingSpeId and param.trainingSpeId eq dict.dictId }">
							   <td>${empty typeNumMap[dict.dictId]?0:typeNumMap[dict.dictId]}</td>
						   </c:if>
						   <c:if test="${empty param.trainingSpeId}">
							   <td>${empty typeNumMap[dict.dictId]?0:typeNumMap[dict.dictId]}</td>
						   </c:if>
					   </c:forEach>
					   <td>${zongji}</td>
				   </tr>
				   <c:if test="${empty citys}">
					   <tr>
						   <td colspan="7" >无记录！</td>
					   </tr>
				   </c:if>
			   </table>
		   </div>
	   </div>
    </div>
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