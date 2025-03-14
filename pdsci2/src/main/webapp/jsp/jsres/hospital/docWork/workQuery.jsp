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
		toPage("1");
	});
	function toPage(page) {
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
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/base/doc/docWorking'/>?baseFlag=${param.baseFlag}",$("#searchForm").serialize(),false);
	}
	function daochu() {
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
		jboxTip("导出中......");
		var url = "<s:url value='/jsres/base/doc/export'/>";
		jboxSubmit($("#searchForm"), url, null, null);
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
		if(orgFlow == ""){
			$("select[name=trainingSpeId] option[value != '']").remove();
			$("#"+trainCategoryid+"_select").find("option").each(function(){
				$(this).clone().appendTo($("#trainingSpeId"));
			});
			return false;
		}
		if("${GlobalConstant.USER_LIST_LOCAL}" == "${sessionScope.userListScope}" && "${hasJointOrg}" != "1"){
			orgFlow="${sessionScope.currUser.orgFlow}";
		}
		var url = "<s:url value='/jsres/doctor/searchResOrgSpeList'/>?sessionNumber="+sessionNumber+"&orgFlow="+orgFlow+"&speTypeId="+trainCategoryid;
		jboxGet(url, null, function(resp){
			console.log("resp = " + resp);
			$("select[name=trainingSpeId] option").remove();
			var option="<option value=''>全部</option>";
			$("#trainingSpeId").append(option);
			if(resp!=""){
				var dataObj = resp.main;
				for(var i = 0; i<dataObj.length;i++){
					var speId = dataObj[i].speId;
					var speName = dataObj[i].speName;
					$("#trainingSpeId").append('<option value="'+speId+'">'+speName+'</option>');
				}
			}
		}, null , false);
	}
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
		changeTrainSpes(1);
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
	<h2 class="underline">学员工作量查询<font color="red" style="font-size:12px">（数据统计截至当前日期前一天）</font></h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
		<form id="searchForm">
			<input type="hidden" id="currentPage" name="currentPage"/>
			<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
			<table class="searchTable" style="border-collapse:separate; border-spacing:0px 10px;">
				<tr>
					<c:if test="${'1' eq hasJointOrg}">
						<td class="td_left">培训基地：</td>
						<td class="td_right">
							<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" autocomplete="off"  onkeydown="changeStatus();" onkeyup="changeStatus();" />
							<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
								<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 140px;border-top: none;position: relative;display:none;">
									<c:forEach items="${orgs}" var="org">
										<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:10px 0;cursor: default; "
										   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
										>${org.orgName}</p>
									</c:forEach>
								</div>
								<input type="text" name="orgFlow" id="orgFlow" style="display: none;"/>
							</div>
						</td>
					</c:if>
					<td class="td_left">排&#12288;&#12288;序：</td>
					<td class="td_right">
						<select name="sort"   class="select" >
							<option value="">全部</option>
							<option value="completeBidesc">完成比例从高到低</option>
							<option value="completeBiasc">完成比例从低到高</option>
							<option value="auditBidesc">审核比例从高到低</option>
							<option value="auditBiasc">审核比例从低到高</option>
						</select>
					</td>

					<td class="td_left">培训类别：</td>
					<td class="td_right">
						<select name="trainingTypeId" id="trainingTypeId" class="select"   onchange="changeTrainSpes('1')">
                       <%--							<option value="">请选择</option>--%>
							<option value="DoctorTrainingSpe" <c:if test="${param.trainingTypeId=='DoctorTrainingSpe'}">selected="selected"</c:if>>住院医师</option>
							<%-- <option value="AssiGeneral" <c:if test="${param.trainingTypeId=='AssiGeneral'}">selected="selected"</c:if>>助理全科</option> --%>
						</select>
					</td>
					<td class="td_left">培训专业：</td>
					<td class="td_right">
						<select name="trainingSpeId" id="trainingSpeId" class="select" >
							<option value="">全部</option>
						</select>
					</td>

					<td class="td_left">人员类型：</td>
					<td colspan="3">
						<c:forEach items="${resDocTypeEnumList}" var="type">
							<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" checked/>${type.name}&nbsp;</label>
						</c:forEach>
						&nbsp;
						<label style="cursor: pointer;display: none;" id='jointOrg'>
							<%--<input type="checkbox" id="jointOrgFlag" name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>显示协同基地--%>
						</label>
					</td>
				</tr>
				<tr>
					<td class="td_left">年&#12288;&#12288;级：</td>
					<td class="td_right">
						<input type="text" id="sessionNumber" name="sessionNumber"value="${pdfn:getCurrYearByMonth()}"   class="input" readonly="readonly"/>
					</td>
					<td class="td_left">姓&#12288;&#12288;名：</td>
					<td class="td_right"><input type="text" name="userName" class="input" /></td>
					<td class="td_left">证&nbsp;件&nbsp;号：</td>
					<td class="td_right"><input type="text" name="idNo" value="${param.idNo}" class="input" /></td>
					<td class="td_left">结业考核年份：</td>
					<td class="td_right">
						<input type="text" id="graduationYear" name="graduationYear" class="input" readonly="readonly"  />&#12288;
					</td>
				</tr>
				<tr>
					<td colspan="8">
						<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>
						<input class="btn_green" type="button" value="导&#12288;出" onclick="daochu();"/>
					</td>
				</tr>
			</table>
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