<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
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
</style>
<script type="text/javascript">
	$(document).ready(function(){
		toPage("1");
	});
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
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/doctorActivityStatistics/list'/>?baseFlag=${param.baseFlag}",$("#searchForm").serialize(),false);
	}

	function changeTrainSpes(){
		var trainCategoryid=$("#trainingTypeId").val();
		if(trainCategoryid =="${dictTypeEnumDoctorTrainingSpe.id}"){
			$("#derateFlagLabel").show();
		}else{
			$("#derateFlag").attr("checked",false);
			$("#derateFlagLabel").hide();
		}
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
	<%--function changeTrainSpes(t){--%>
		<%--//清空原来专业！！！--%>
		<%--var sessionNumber=$("#sessionNumber").val();--%>
		<%--var trainCategoryid=$("#trainingTypeId").val();--%>
		<%--var orgFlow=$("#orgFlow").val();--%>
		<%--if(trainCategoryid==""){--%>
			<%--$("select[name=trainingSpeId] option[value != '']").remove();--%>
			<%--return false;--%>
		<%--}--%>
		<%--if(orgFlow == ""){--%>
			<%--$("select[name=trainingSpeId] option[value != '']").remove();--%>
			<%--$("#"+trainCategoryid+"_select").find("option").each(function(){--%>
				<%--$(this).clone().appendTo($("#trainingSpeId"));--%>
			<%--});--%>
			<%--return false;--%>
		<%--}--%>
		<%--if("${GlobalConstant.USER_LIST_LOCAL}" == "${sessionScope.userListScope}" && "${hasJointOrg}" != "1"){--%>
			<%--orgFlow="${sessionScope.currUser.orgFlow}";--%>
		<%--}--%>
		<%--var url = "<s:url value='/jsres/doctor/searchResOrgSpeList'/>?sessionNumber="+sessionNumber+"&orgFlow="+orgFlow+"&speTypeId="+trainCategoryid;--%>
		<%--jboxGet(url, null, function(resp){--%>
			<%--$("select[name=trainingSpeId] option[value != '']").remove();--%>
			<%--if(resp!=""){--%>
				<%--var dataObj = resp;--%>
				<%--for(var i = 0; i<dataObj.length;i++){--%>
					<%--var speId = dataObj[i].speId;--%>
					<%--var speName = dataObj[i].speName;--%>
					<%--$option =$("<option></option>");--%>
					<%--$option.attr("value",speId);--%>
					<%--$option.text(speName);--%>
					<%--$("select[name=trainingSpeId]").append($option);--%>
				<%--}--%>
			<%--}--%>
		<%--}, null , false);--%>
	<%--}--%>
	(function($){
		$.fn.likeSearchInit = function(option){
			option.clickActive = option.clickActive || null;
			var searchInput = this;
			var spaceId = this[0].id;
			searchInput.on("keyup focus",function(){
				var boxHome = $("#"+spaceId+"Sel");
				boxHome.show();
				var pDiv=$(boxHome).parent();
				// $(pDiv).css("left",$(this).offset().left-$(this).prev().prev().prev().offset().left);
				var w=$(this).css("marginTop").replace("px","");
				w=w-0+$(this).outerHeight()+6+"px";
				$(pDiv).css("top",w);
				if($.trim(this.value)){
					$("p."+spaceId+".item",boxHome).hide();
					var items = boxHome.find("p."+spaceId+".item[value*='"+this.value+"']").show();
					if(!items){
						boxHome.hide();
					}
					changeOrgFlow(this);
				}else{
					var orgTypeFlag= $("#orgTypeFlag").val();
					$("p."+spaceId+".item",boxHome).hide();
					if(orgTypeFlag==""){
						$("p."+spaceId+".item",boxHome).show();
					}else{
						$("p."+spaceId+".item[type*='"+orgTypeFlag+"']",boxHome).show();
					}
				}
			});
			searchInput.on("blur",function(){
				var boxHome = $("#"+ spaceId+"Sel");
				if(!$("."+spaceId+".boxHome.on").length){
					boxHome.hide();
				}
			});
			$("."+spaceId+".boxHome").on("mouseenter mouseleave",function(){
				$(this).toggleClass("on");
			});

			$("."+spaceId+".boxHome .item").click(function(){
				var boxHome = $("."+spaceId+".boxHome");
				boxHome.hide();
				var value = $(this).attr("value");
				var input = $("#"+spaceId);
				input.val(value);
				if(option.clickActive){
					option.clickActive($(this).attr("flow"));
					$("#orgFlow").val($(this).attr("flow"));
				}
				//改变协同医院状态
				$("select[name=trainingSpeId] option[value != '']").remove();
				$("select[name=trainingTypeId] option[value = '']").attr('selected','selected');
				var orgFlag=$("#trainOrg").val();
				var orgFlow=$("#orgFlow").val();
				if(orgFlag.replace(/(^\s*)|(\s*$)/g, "")==""){
					$("#jointOrg").hide();
				}else{
					showJointOrg(orgFlow);
				}
				orgStatus();
			});
		};
	})(jQuery);
	$(function(){
		if($("#trainOrg").length){
			$("#trainOrg").likeSearchInit({
				clickActive:function(flow){
					$("."+flow).show();
				}
			});
		}
	});
	function showJointOrg(orgFlow){
		if($("#"+orgFlow).length){
			$("#jointOrg").show();
		}else{
			$("#jointOrg").hide();
		}
		$("#jointOrgFlag").removeAttr("checked");
	}
	function changeStatus(){
		$("select[name=trainingSpeId] option[value != '']").remove();
		$("select[name=trainingTypeId] option[value = '']").attr('selected','selected');
		if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "")==""){
			$("#jointOrgFlag").attr("checked",false);
			orgStatus();
			$("#orgFlow").val("");
		}
	}
	function chanegOrgLevel(obj){
		$("#jointOrgFlag").removeAttr("checked");
		$("#orgTypeFlag").val($(obj).val());
		$("#trainOrg").val("");
		orgStatus();
	}

	function orgStatus(){
		var trainOrg=$("#trainOrg").val();
		if(trainOrg.replace(/(^\s*)|(\s*$)/g, "")==""){
			if($("#orgLevelId").val()=="${orgLevelEnumCountryOrg.id}"){
				$("#jointOrg").show();
			}else{
				$("#jointOrg").hide();
			}
		}
	}

	function changeOrgFlow(obj){
		var items = $("#pDiv").find("p."+$(obj).attr("id")+".item[value='"+$(obj).val()+"']");
		var flow=$(items).attr("flow") || '';
		$("#orgFlow").val(flow);
		showJointOrg(flow);
	}
	function exportExcel(){

		var url = "<s:url value='/jsres/doctorActivityStatistics/exportList'/>?roleFlag=${param.roleFlag}&baseFlag=${param.baseFlag}";
		jboxTip("导出中…………");
		jboxSubmit($("#searchForm"), url, null, null, false);
		jboxEndLoading();
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
	<h2 class="underline">学员活动统计</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
		<form id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage"/>
			<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
			<c:choose>
				<%-- 外省管理员账号--%>
				<c:when test="${sessionScope.userListScope == GlobalConstant.USER_LIST_BASE}">
					<table style="width:100%">
						<tr>
							<td style="text-align: right">培训类别：</td>
							<td>
								<select name="trainingTypeId" id="trainingTypeId" class="select" style="width: 136px"  onchange="changeTrainSpes('1')">
									<option value="">请选择</option>
									<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
										<option value="${trainCategory.id}">${trainCategory.name}</option>
									</c:forEach>
								</select>
							</td>
							<td style="text-align: right">培训专业：</td>
							<td>
								<select name="trainingSpeId" id="trainingSpeId" style="width: 136px" class="select" >
									<option value="">全部</option>
								</select>
							</td>
							<td style="text-align: right">年&#12288;&#12288;级：</td>
							<td>
								<input type="text" id="sessionNumber" name="sessionNumber"value="${pdfn:getCurrYearByMonth()}"   readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" style="width: 130px;margin-left: 0px;" class="input" />
							</td>
							<td style="text-align: right">姓&#12288;&#12288;名：</td>
							<td><input type="text" name="userName" class="input"  style="width: 130px;margin-left: 0px;"/></td>
						</tr>
						<tr>
							<td style="text-align: right">证&nbsp;件&nbsp;号：</td>
							<td><input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 130px;margin-left: 0px;"/></td>
							<td  style="text-align: right">
								开始时间：
							</td>
							<td>
								<input type="text" id="startDate" name="startTime" style="width: 130px;margin-left: 0px;" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
							</td>
							<td  style="text-align: right">
								结束时间：
							</td>
							<td>
								<input type="text" id="endDate" name="endTime" style="width: 130px;margin-left: 0px;" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
							</td>
						</tr>
						<tr>
							<td style="text-align: right">人员类型：</td>
							<td colspan="3">
								<c:forEach items="${jsResDocTypeEnumList}" var="type">
									<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" checked/>${type.name}&nbsp;</label>
								</c:forEach>
								&nbsp;
								<label style="cursor: pointer;display: none;" id='jointOrg'>
								</label>
							</td>
							<td colspan="4">
								<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;
								<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>
							</td>
						</tr>
					</table>
				</c:when>
				<c:otherwise>
					<table style="width:100%">
						<tr>
							<td style="text-align: right">培训基地：</td>
							<td>
								<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" autocomplete="off" value="${currentOrg.orgName}" style="margin-left: 0px;width: 130px"  onkeydown="changeStatus();" onkeyup="changeStatus();" />
								<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
									<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 140px;border-top: none;position: relative;display:none;">
										<c:forEach items="${orgs}" var="org">
											<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:10px 0;cursor: default; "
											   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
											>${org.orgName}</p>
										</c:forEach>
									</div>
									<input type="text" name="orgFlow" id="orgFlow" value="${currentOrg.orgFlow}" style="display: none;"/>
								</div>
							</td>

							<td style="text-align: right">培训类别：</td>
							<td>
								<select name="trainingTypeId" id="trainingTypeId" class="select" style="width: 136px"  onchange="changeTrainSpes('1')">
									<option value="AssiGeneral">助理全科</option>
								<%--	<option value="">请选择</option>
									<c:forEach items="${trainCategoryEnumList}" var="trainCategory">
										<option value="${trainCategory.id}">${trainCategory.name}</option>
									</c:forEach>--%>
								</select>
							</td>
							<td style="text-align: right">培训专业：</td>
							<td>
								<select name="trainingSpeId" id="trainingSpeId" style="width: 136px" class="select" >
									<option value="">全部</option>
								</select>
							</td>
							<td style="text-align: right">年&#12288;&#12288;级：</td>
							<td>
								<input type="text" id="sessionNumber" name="sessionNumber"value="${pdfn:getCurrYearByMonth()}"   readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" style="width: 130px;margin-left: 0px;" class="input" />
							</td>
						</tr>
						<tr>
							<td style="text-align: right">姓&#12288;&#12288;名：</td>
							<td><input type="text" name="userName" class="input"  style="width: 130px;margin-left: 0px;"/></td>
							<td style="text-align: right">证&nbsp;件&nbsp;号：</td>
							<td><input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 130px;margin-left: 0px;"/></td>
							<td  style="text-align: right">
								开始时间：
							</td>
							<td>
								<input type="text" id="startDate" name="startTime" style="width: 130px;margin-left: 0px;" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
							</td>
							<td  style="text-align: right">
								结束时间：
							</td>
							<td>
								<input type="text" id="endDate" name="endTime" style="width: 130px;margin-left: 0px;" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
							</td>
						</tr>
						<tr>
							<td style="text-align: right">人员类型：</td>
							<td colspan="3">
								<c:forEach items="${jsResDocTypeEnumList}" var="type">
									<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" checked/>${type.name}&nbsp;</label>
								</c:forEach>
								&nbsp;
								<label style="cursor: pointer;display: none;" id='jointOrg'>
								</label>
							</td>
							<td colspan="4">
								<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;
								<input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>
							</td>
						</tr>
					</table>
				</c:otherwise>
			</c:choose>

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