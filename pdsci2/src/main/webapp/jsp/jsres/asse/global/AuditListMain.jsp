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
		/*width: 157px;*/
		height: auto;
		text-align: left
	}
	.searchTable .input{
		width: 128px;
		margin-left: 0px;
		box-sizing: content-box;
		padding: 4px 2px;
	}
	.searchTable .select{
		width: 134px;
		margin-left: 0px;

	}
	.searchTable .td_left{
		word-wrap: break-word;
		width: 6em;
		height: auto;
		text-align: right;
	}
</style>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#sessionNumber').datepicker({
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
		$('#graduationYear').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
        // $('#AfterEvaluation').datepicker({
        //     startView: 0,
        //     maxViewMode: 0,
        //     minViewMode:0,
        //     format:'yyyy-mm-dd'
        // });
        // $('#completeTime').datepicker({
        //     startView: 0,
        //     maxViewMode: 0,
        //     minViewMode:0,
        //     format:'yyyy-mm-dd'
        // });
		getCityArea();
		initOrg();
		toPage();
	});

    (function($){
        $.fn.likeSearchInit = function(option){
            option.clickActive = option.clickActive || null;
            var searchInput = this;
            var spaceId = this[0].id;
            searchInput.on("keyup focus",function(){
                var boxHome = $("#"+spaceId+"Sel");
                boxHome.show();
                if($.trim(this.value)){
                    $("p."+spaceId+".item",boxHome).hide();
                    var orgTypeFlag= $("#orgTypeFlag").val();
                    var orgCityFlag= $("#orgCityFlag").val();
                    var selectStr="";
                    if(orgTypeFlag){
                        selectStr+=".item[type*='"+orgTypeFlag+"']";
                    }
                    if(orgCityFlag){
                        selectStr+=".item[orgCityId*='"+orgCityFlag+"']";
                    }
                    var items = boxHome.find("p."+spaceId+selectStr+".item[value*='"+this.value+"']").show();

                    if(!items){
                        boxHome.hide();
                    }
                    changeOrgFlow(this);
                }else{
                    var orgTypeFlag= $("#orgTypeFlag").val();
                    var orgCityFlag= $("#orgCityFlag").val();
                    $("p."+spaceId+".item",boxHome).hide();
                    var selectStr="";
                    if(orgTypeFlag){
                        selectStr+=".item[type*='"+orgTypeFlag+"']";
                    }
                    if(orgCityFlag){
                        selectStr+=".item[orgCityId*='"+orgCityFlag+"']";
                    }
                    $("p."+spaceId+selectStr,boxHome).show();
                    $("p."+spaceId+".item[type*='AllOrgP']",boxHome).show();
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
            <c:if test="${fn:length(orgs)!=1}">
            $("#trainOrg").likeSearchInit({
                clickActive:function(flow){
                    $("."+flow).show();
                }
            });
            </c:if>
        }
    });

    function changeOrgFlow(obj){
        var items = $("#pDiv").find("p."+$(obj).attr("id")+".item[value='"+$(obj).val()+"']");
        var flow=$(items).attr("flow") || '';
        $("#orgFlow").val(flow);
        showJointOrg(flow);
    }

    function showJointOrg(orgFlow){
        jboxPost("<s:url value='/jsres/recruitDoctorInfo/queryJoinOrg'/>?orgFlow="+orgFlow, null, function (resp) {
            $("#joinOrgFlow").empty();
            $("#joinOrgFlow").append("<option value=''>"+"全部"+"</option>");
            if (null != resp && resp.length > 0) {
                for(var i= 0;i<resp.length;i++){
                    var jointOrgName = resp[i].jointOrgName;
                    if(!jointOrgName) {jointOrgName = '';}
                    else{ jointOrgName = "("+jointOrgName+")"}
                    $("#joinOrgFlow").append("<option value='"+resp[i].jointOrgFlow+"'>"+resp[i].jointOrgName+"</option>");
                }
            }
        },null,false);
    }

    function orgStatus(){
        var trainOrg=$("#trainOrg").val();
        if(trainOrg.replace(/(^\s*)|(\s*$)/g, "")==""){
			$("#jointOrg").show();
        }
    }

    function changeOrgCityId(){
        $("#orgCityFlag").val($("#cityId2").val());
        $("#trainOrg").val("");
    }

    function changeStatus(){
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "")==""){
            orgStatus();
            $("#orgFlow").val("");
        }
    }

	var allOrgs=[];
	function initOrg() {
		var datas=[];
		<c:forEach items="${orgs}" var="org">
			<c:if test="${sessionScope.currUser.orgFlow!=org.orgFlow }">
				var d={};
				d.id="${org.orgFlow}";
				d.text="${org.orgName}";
				d.cityId="${org.orgCityId}";
				datas.push(d);
				allOrgs.push(d);
			</c:if>
		</c:forEach>
		// var itemSelectFuntion = function(){
		// 	$("#orgFlow").val(this.id);
		// };
		// $.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
	}
	function changeOrg(obj) {
		var cityId=$(obj).val();
		var datas=[];
		for(var i=0;i<allOrgs.length;i++)
		{
			var org=allOrgs[i];
			if(org.cityId==cityId||cityId=="")
			{
				datas.push(org);
			}
		}
		$("#orgFlow").val("");
		var itemSelectFuntion = function(){
			$("#orgFlow").val(this.id);
		};
		$.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
	}
	function freshen() {
        if("${param.tabTag}" == "FristWait"){
            jboxLoad("content","<s:url value='/jsres/asseGlobal/main'/>?roleFlag=global&tabTag=FristWait",true);
		}else{
            jboxLoad("content","<s:url value='/jsres/asseGlobal/asseAuditListMain'/>?roleFlag=global&tabTag=FristList",true);
		}
    }
	function toPage(page) {
		var data="";
		$("input[class='docType']:checked").each(function(){
			data+="&datas="+$(this).val();
		});
		if(!$("#applyYear").val()){
			jboxTip("请选申请年份！");
			return false;
		}
		if(data==""){
			jboxTip("请选择人员类型！");
			return false;
		}
		$("#currentPage").val(page);
		// if($("#graduationMaterialName2").val()!=''){
         //    $("#graduationMaterialName").val($("#graduationMaterialName2").val());
		// }else{
         //    $("#graduationMaterialName").val($("#graduationMaterialName1").val());
		// }
        var value =  $("#avgCompleteSearch").val();
        if(value != ''){
            var avgComplete = $("#avgComplete").val();
            if(avgComplete != ''){
                var completeTime = $("#completeTime").val();
                if(completeTime == ''){
                    jboxTip("请选择填写时间！");
                    return;
				}
			}else{
                jboxTip("请选择数据填写百分比！");
                return;
            }
        }
		jboxStartLoading();
        <%--jboxPostLoad("doctorListZi","<s:url value='/jsres/asseGlobal/AuditList'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),false);--%>
        if("FristList"=="${param.tabTag}" || "FristList2"=="${param.tabTag}"){
            <%--jboxPostLoad("doctorListZi","<s:url value='/jsres/asseGlobal/waitAuditListNew'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),false);--%>
            jboxPostLoad("doctorListZi","<s:url value='/jsres/asseGlobal/AuditListNew'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),true);
		}else if("FristWait"=="${param.tabTag}" || "FristWait2" == "${param.tabTag}"){
            jboxPostLoad("doctorListZi","<s:url value='/jsres/asseGlobal/waitAuditListNew'/>?roleFlag=${roleFlag}&tabTag=${param.tabTag}",$("#searchForm").serialize(),true);
        }else if("SecondList"=="${param.tabTag}" || "SecondList2"=="${param.tabTag}"){
			<%--jboxPostLoad("doctorListZi","<s:url value='/jsres/asseGlobal/AuditListNew'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),true);--%>
            jboxPostLoad("doctorListZi","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag=${param.roleFlag}&tabTag=${param.tabTag}",true);
		}else if("SecondWait"=="${param.tabTag}" || "SecondWait2"=="${param.tabTag}"){
            <%--jboxPostLoad("doctorListZi","<s:url value='/jsres/asseGlobal/AuditListNew'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),true);--%>
            jboxPostLoad("doctorListZi","<s:url value='/jsres/examSignUp/globalMain'/>?roleFlag=${param.roleFlag}&tabTag=${param.tabTag}",true);
        }
	}
	function exportInfo()
	{
		if(!$("#applyYear").val()){
			jboxTip("请选申请年份！");
			return false;
		}
        <%--var url = "<s:url value='/jsres/asseGlobal/exportList'/>";--%>
		var url = "<s:url value='/jsres/asseGlobal/exportListNew'/>?roleFlag=${roleFlag}";
		jboxExp($("#searchForm"),url);
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
	function getCityArea(){
		var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
		var provIds = "320000";
		jboxGet(url,null, function(json) {
			// 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
			var newJsonData=new Array();
			var j=0;
			var html ="<option value=''></option>";
			for(var i=0;i<json.length;i++){
				if(provIds==json[i].v){
					var citys=json[i].s;
					for(var k=0;k<citys.length;k++){
						var city=citys[k];
						html+="<option value='"+city.v+"'>"+city.n+"</option>";
					}
				}
			}
			$("#cityId2").html(html);
		},null,false);
	}

	function batchApply() {
		var applyFlow = "";
		var selects = $("td input[type='checkbox'][name='applyFlow']:checked");
		if (selects.length == 0) {
			jboxTip("请选择批量审核的数据！");
			return false;
		}
		for (var i = 0; i < selects.length; i++) {
			if (i == 0)
				applyFlow += "applyFlow=" + $(selects[i]).val();
			else
				applyFlow += "&applyFlow=" + $(selects[i]).val();
		}

		var url = "<s:url value='/jsres/asseGlobal/batchAuditApply?'/>" + applyFlow;
		jboxOpen(url, "审核学员填报信息", 800, 400);
	}
	
	function searchMore() {
		$(".searchLess").hide();
        $(".searchLess2").hide();
		$(".searchMore").show();
		if('${param.tabTag}' == 'FristList'){
            $(".searchMore2").show();
		}
    }
    
    function searchLess() {
        $(".searchLess").show();
        $(".searchMore").hide();
		$(".queryMore").val("");
    }
    
    function selectMaterialCode() {
		var value =  $("#graduationMaterialName2").val();
		if(value == '已通过国家执业医师考试的成绩单' || value == '' || value == '暂无'){
		    jboxTip("请选择医师资格证或医师执业证！");
            $("#qualificationMaterialCode").val("");
		}
    }

    function selectMaterialUrl() {
        var value =  $("#graduationMaterialName2").val();
        if(value == '已通过国家执业医师考试的成绩单' || value == '' || value == '暂无'){
            jboxTip("请选择医师资格证或医师执业证！");
            $("#qualificationMaterialUrl").val("");
        }
    }
    
    function searchSchMonth() {
        var value =  $("#schMonthSearch").val();
        if(value == ''){
            jboxTip("请选择轮转月数！");
            $("#schMonth").val("");
        }
    }

    function searchSkillScore() {
        var value =  $("#skillScoreSearch").val();
        if(value == ''){
            jboxTip("请选择考核成绩！");
            $("#skillScore").val("");
        }
    }

	function searchAvgComplete() {
        var value =  $("#avgCompleteSearch").val();
        if(value == ''){
            jboxTip("请选择数据填写度！");
            $("#avgComplete").val("");
        }
    }

    function searchAvgAudit() {
        var value =  $("#avgAuditSearch").val();
        if(value == ''){
            jboxTip("请选择数据审核度！");
            $("#avgAudit").val("");
        }
    }

    function searchAfterEvaluationScale() {
        var value =  $("#AfterEvaluationSearch").val();
        if(value == ''){
            jboxTip("请选择考核表附件条件！");
            $("#AfterEvaluationScale").val("");
        }
    }

    function changeCompleteTime() {
        var value =  $("#avgCompleteSearch").val();
        if(value == ''){
            jboxTip("请选择数据填写度！");
            $("#completeTime").val("");
        }
        var avgComplete = $("#avgComplete").val();
        if(avgComplete == ''){
            jboxTip("请选择数据填写百分比！");
            $("#completeTime").val("");
		}
    }
    
    function exportCountryList() {
        if(!$("#applyYear").val()){
            jboxTip("请选申请年份！");
            return false;
        }
        var url = "<s:url value='/jsres/asseGlobal/exportCountryList'/>";
        jboxExp($("#searchForm"),url);
    }


</script>
<div class="main_hd">
<%--	<c:if test="${param.tabTag eq 'WaitAudit'}">--%>
<%--		<h2 class="underline">考核资格待审核</h2>--%>
<%--	</c:if>--%>
<%--	<c:if test="${param.tabTag eq 'Audit'}">--%>
<%--		<h2 class="underline">考核资格查询</h2>--%>
<%--	</c:if>--%>
</div>
<div class="div_search" style="padding: 10px 30px;line-height: 35px;">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
		<input type="hidden" id="tabTag" name="tabTag" value="${param.tabTag}"/>
		<input type="hidden" id="orgCityFlag" value=""/>
		<%--<input type="hidden" id="graduationMaterialName" name="materialName"/>--%>
		<%--省厅市局查询条件--%>

		<table class="searchTable" style="width: 92%;border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td class="td_left">结业年份：</td>
				<td>
					<input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}" class="input" readonly="readonly" />
				</td>

				<td class="td_left">申请年份：</td>
				<td>
					<input type="text" id="applyYear" name="applyYear" class="input" readonly="readonly" value="${pdfn:getCurrYear()}" />
				</td>

				<td class="td_left">培训基地：</td>
				<td>
					<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off" onkeydown="changeStatus();" onkeyup="changeStatus();" />
					<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left :0px;top:30px;">
						<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 185px;border-top: none;position: relative;display:none;">
							<%--<p class="item trainOrg allOrg orgs" flow="" value="全部" type="AllOrgP" style="line-height: 20px; padding:10px 0;cursor: default; ">全部</p>--%>
							<c:forEach items="${orgs}" var="org">
								<p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}"
								   <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if>
								   <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>
								   orgCityId="${org.orgCityId}"
								   style="line-height: 20px; padding:5px 0;cursor: default; "
								   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
								>${org.orgName}</p>
							</c:forEach>
						</div>
						<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
					</div>
				</td>

				<td class="td_left">协同单位：</td>
				<td>
					<select name="joinOrgFlow" id="joinOrgFlow" class="select " >
						<option value="">全部</option>
					</select>
				</td>

				<td class="td_left">培训专业：</td>
				<td>
					<select name="trainingSpeId" id="trainingSpeId"class="select" >
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

				<td class="td_left">姓&#12288;&#12288;名：</td>
				<td>
					<input type="text" name="userName" value="${param.userName}" class="input" style="margin-left: 0px;"/>
				</td>
			</tr>

			<tr>
				<td class="td_left">证&nbsp;件&nbsp;号&nbsp;：</td>
				<td>
					<input type="text" name="idNo" value="${param.idNo}" class="input" style="margin-left: 0px;"/>
				</td>

				<td class="td_left">年&#12288;&#12288;级：</td>
				<td>
					<input type="text" name="sessionNumber" value="${param.sessionNumber}" class="input" style="margin-left: 0px;"/>
				</td>

				<td class="td_left">培训类别：</td>
				<td>
					<select name="trainingTypeId" id="trainingTypeId" class="select">
						<%--<option value="">请选择</option>--%>
						<c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristList'}">
							<option value="DoctorTrainingSpe" selected="selected">住院医师</option>
							<option value="WMFirst" <c:if test="${param.trainingTypeId eq 'WMFirst'}">selected="selected"</c:if>>一阶段</option>
							<option value="WMSecond" <c:if test="${param.trainingTypeId eq 'WMSecond'}">selected="selected"</c:if>>二阶段</option>
						</c:if>
						<c:if test="${param.tabTag eq 'FristWait2' or param.tabTag eq 'FristList2'}">
							<option value="AssiGeneral" selected="selected">助理全科</option>
						</c:if>
						<%--<c:forEach items="${trainCategoryEnumList}" var="trainCategory">--%>
						<%--<option value="${trainCategory.id}"--%>
						<%--<c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
						<%--</c:forEach>--%>
					</select>
				</td>

				<td class="td_left">审核状态：</td>
				<td>
					<c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristWait2'}">
						<select name="auditStatusId" id="auditStatusId" class="select" >
							<option value="WaitGlobalPass" selected>待省厅审核</option>
						</select>
					</c:if>
					<c:if test="${param.tabTag ne 'FristWait' and param.tabTag ne 'FristWait2'}">
						<select name="auditStatusId" id="auditStatusId" class="select" >
							<option value="">请选择</option>
								<%--<c:forEach items="${jsResAsseAuditListEnumList}" var="type">--%>
								<%--<option value="${type.id}">${type.name}</option>--%>
								<%--</c:forEach>--%>
								<%--<option value="WaitGlobalPass">待审核</option>--%>
							<option value="Auditing">待基地审核</option>
							<option value="LocalNotPassed">基地审核不通过</option>
							<option value="WaitChargePass">待市局审核</option>
							<option value="ChargeNotPassed">市局审核不通过</option>
							<option value="WaitGlobalPass">待省厅审核</option>
							<option value="GlobalNotPassed">省厅审核不通过</option>
							<option value="GlobalPassed">省厅审核通过</option>
							<option value="Black">省厅驳回</option>
						</select>
					</c:if>
				</td>

				<td class="td_left">是否延期：</td>
				<td>
					<select class="select" name="isPostpone">
						<option value="">请选择</option>
						<option
								<c:if test="${param.isPostpone eq GlobalConstant.FLAG_Y}">selected="selected"</c:if>
								value="${GlobalConstant.FLAG_Y}">是
						</option>
						<option
								<c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>
								value="${GlobalConstant.FLAG_N}">否
						</option>
					</select>
				</td>

				<td class="td_left">考试编号：</td>
				<td>
					<select name="testId"  class="select" >
						<option value="">请选择</option>
						<c:forEach items="${resTestConfigs}" var="resTest">
							<option value="${resTest.testId}" ${param.testId eq resTest.testId?'selected':''}>${resTest.testId}</option>
						</c:forEach>
					</select>
				</td>

			</tr>

			<tr>
				<td class="td_left">人员类型：</td>
				<td colspan="3">
					<c:forEach items="${resDocTypeEnumList}" var="type">
						<label><input type="checkbox" id="${type.id}"value="${type.id}" checked class="docType" name="datas" />${type.name}&nbsp;</label>
					</c:forEach>
				</td>
				<td class="td_left">异常报考：</td>
				<td>
					<select class="select" name="tempDoctorFlag">
						<option value="">全部</option>
						<option <c:if test="${param.tempDoctorFlag eq GlobalConstant.FLAG_Y}">selected="selected"</c:if>
								value="${GlobalConstant.FLAG_Y}">是
						</option>
						<option <c:if test="${param.tempDoctorFlag eq GlobalConstant.FLAG_N}">selected="selected"</c:if>
								value="${GlobalConstant.FLAG_N}">否
						</option>
					</select>
				</td>

				<td colspan="6">
					<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;
					<input class="btn_green" type="button" value="导&#12288;出" onclick="exportInfo();"/>&#12288;
					<c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristWait2'}">
						<input class="btn_green" type="button" value="批量审核" onclick="batchApply();"/>&#12288;
					</c:if>
					<input class="btn_green" type="button" value="更&#12288;多" onclick="searchMore();"/>&#12288;
					<input class="btn_green" type="button" value="重&#12288;置" onclick="freshen();"/>&#12288;
					<input class="btn_green" type="button" value="参考人员名称导出"
						   <c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristWait2'}">style="display: none" </c:if>
						   onclick="exportCountryList();"/>&#12288;
				</td>
			</tr>

		</table>

<%--			<div class="form_btn" >--%>
<%--				<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;--%>
<%--				<input class="btn_green" type="button" value="导&#12288;出" onclick="exportInfo();"/>&#12288;--%>
<%--				<c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristWait2'}">--%>
<%--					<input class="btn_green" type="button" value="批量审核" onclick="batchApply();"/>&#12288;--%>
<%--				</c:if>--%>
<%--				<input class="btn_green" type="button" value="更&#12288;多" onclick="searchMore();"/>&#12288;--%>
<%--				<input class="btn_green" type="button" value="重&#12288;置" onclick="freshen();"/>&#12288;--%>
<%--				<input class="btn_green" type="button" value="参考人员名称导出"--%>
<%--					   <c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristWait2'}">style="display: none" </c:if>--%>
<%--					   onclick="exportCountryList();"/>&#12288;--%>
<%--			</div>--%>

	<div class="searchMore" style="display: none">
		<div class="form_search" >

			<div class="form_item">
				<div class="form_label">地&#12288;&#12288;市：</div>
				<div class="form_content">
					<select id="cityId2" name="cityId" class="select"
												<%--onchange="changeOrg(this)"--%>
												onchange="changeOrgCityId()"
										></select>
				</div>
			</div>


			<div class="form_item">
				<div class="form_label">最高学历：</div>
				<div class="form_content">
					<select name="educationId" class="select" >
                        <option value="">请选择</option>
                        <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">
                            <option value="${dict.dictId }" <c:if test="${eduUserExt.sysUser.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>
                        </c:forEach>
                    </select>
				</div>
			</div>


			<div class="form_item">
				<div class="form_label">培训年限：</div>
				<div class="form_content">
					<select name="trainYear"  class="select" >
						<option value="">请选择</option>
						<option value="OneYear" ${param.trainYear eq 'OneYear'?'selected':''}>一年</option>
						<option value="TwoYear" ${param.trainYear eq 'TwoYear' ?'selected':''}>两年</option>
						<option value="ThreeYear" ${param.trainYear eq 'ThreeYear' ?'selected':''}>三年</option>
					</select>
				</div>
			</div>


			<div class="form_item">
				<div class="form_label">公共科目：</div>
				<div class="form_content">
					<select name="passFlag"  class="select" >
						<option value="">请选择</option>
						<option value="${GlobalConstant.PASS}"   ${param.passFlag eq GlobalConstant.PASS ? 'selected':''}>合格</option>
						<option value="${GlobalConstant.UNPASS}" ${param.passFlag eq GlobalConstant.UNPASS ?'selected':''}>不合格</option>
					</select>
				</div>
			</div>

			<div class="form_item">
				<div class="form_label">报考材料：</div>
				<div class="form_content">
					<select name="materialName"  class="select" >
                    <%--<select id="graduationMaterialName1"  class="select" >--%>
                        <option value="">请选择</option>
                        <option value="医师资格证书" ${param.graduationMaterialName eq '医师资格证书' ? 'selected':''}>医师资格证书</option>
                        <option value="医师执业证书" ${param.graduationMaterialName eq '医师执业证书' ? 'selected':''}>医师执业证书</option>
                        <option value="已通过国家执业医师考试的成绩单" ${param.graduationMaterialName eq '已通过国家执业医师考试的成绩单' ? 'selected':''}>已通过国家执业医师考试的成绩单</option>
                        <option value="暂无" ${param.graduationMaterialName eq '暂无' ?'selected':''}>暂无</option>
                    </select>
				</div>
			</div>


			<div class="form_item">
				<div class="form_label">资格证书：</div>
				<div class="form_content">
					<select id="qualificationName" name="qualificationName"  class="select queryMore">
						<option value="">请选择</option>
						<option value="Y" ${param.qualificationName eq 'Y' ? 'selected':''}>有</option>
						<option value="N" ${param.qualificationName eq 'N' ? 'selected':''}>无</option>
					</select>
				</div>
			</div>


			<div class="form_item">
				<div class="form_label">证书编号：</div>
				<div class="form_content">
					<select id="qualificationMaterialCode" name="qualificationMaterialCode"  class="select queryMore" onchange="selectMaterialCode()">
						<option value="">请选择</option>
						<option value="Y" ${param.qualificationMaterialCode eq 'Y'?'selected':''}>已填写</option>
						<option value="N" ${param.qualificationMaterialCode eq 'N'?'selected':''}>未填写</option>
					</select>
				</div>
			</div>


			<div class="form_item">
				<div class="form_label">证书附件：</div>
				<div class="form_content">
					<select id="qualificationMaterialUrl" name="qualificationMaterialUrl" class="select queryMore" >
						<option value="">请选择</option>
						<option value="Y" ${param.qualificationMaterialUrl eq 'Y'?'selected':''}>有</option>
						<option value="N" ${param.qualificationMaterialUrl eq 'N'?'selected':''}>无</option>
					</select>
				</div>
			</div>


			<div class="form_item">
				<div class="form_label">轮转月数：</div>
				<div class="form_content">
					<select id="schMonthSearch" name="schMonthSearch"  class="select queryMore" >
						<option value="">请选择</option>
						<option value="GreaterThan" ${param.schMonthSearch eq 'GreaterThan'?'selected':''}>大于</option>
						<option value="Equal" ${param.schMonthSearch eq 'Equal' ?'selected':''}>等于</option>
						<option value="LessThan" ${param.schMonthSearch eq 'LessThan' ?'selected':''}>小于</option>
					</select>
				</div>
			</div>


			<div class="form_item">
				<div class="form_label">月&#12288;&#12288;数：</div>
				<div class="form_content">
					<input type="text" id="schMonth" name="schMonth" value="${param.schMonth}" class="input queryMore" style="margin-left: 0px;" onchange="searchSchMonth()"/>
				</div>
			</div>


			<div class="form_item">
				<div class="form_label">减免证明：</div>
				<div class="form_content">
					<select name="proveFileUrl"  class="select queryMore" >
						<option value="">请选择</option>
						<option value="Y" ${param.proveFileUrl eq 'Y'?'selected':''}>有</option>
						<option value="N" ${param.proveFileUrl eq 'N'?'selected':''}>无</option>
					</select>
				</div>
			</div>


			<div class="form_item">
				<div class="form_label">科目附件：</div>
				<div class="form_content">
					<select name="passFile"  class="select" >
						<option value="">请选择</option>
						<option value="Y"   ${param.passFile eq 'Y' ? 'selected':''}>已上传附件</option>
						<option value="N" ${param.passFile eq 'N' ?'selected':''}>未上传附件</option>
					</select>
				</div>
			</div>


			<div class="form_item">
				<input style="margin-left: 30px;" type="checkbox" value="Y" name="isNotMatch" />&#12288;培训专业与执业范围不匹配&#12288;
			</div>


		</div>

		<div class="form_btn">
			<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;
			<input class="btn_green" type="button" value="导&#12288;出" onclick="exportInfo();"/>&#12288;
			<input class="btn_green" type="button" value="批量审核" onclick="batchApply();"/>&#12288;
			<input class="btn_green" type="button" value="缩&#12288;进" onclick="searchLess();"/>&#12288;
			<input class="btn_green" type="button" value="导出国家基地导入数据"
				   <c:if test="${param.tabTag eq 'FristWait'}">style="display: none" </c:if>
				   onclick="exportCountryList();"/>&#12288;
		</div>
	</div>




<%--		<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">--%>
<%--			<tr>--%>
<%--				<td class="td_left">结业年份：</td>--%>
<%--				<td>--%>
<%--					<input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}" class="input" readonly="readonly" style="margin-left: 0px"/>--%>
<%--				</td>--%>
<%--				<td class="td_left">申请年份：</td>--%>
<%--				<td>--%>
<%--					<input type="text" id="applyYear" name="applyYear" class="input" readonly="readonly" value="${pdfn:getCurrYear()}" style="margin-left: 0px"/>--%>
<%--				</td>--%>
<%--				<td class="td_left">培训基地：</td>--%>
<%--				<td>--%>
<%--					<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off" onkeydown="changeStatus();" onkeyup="changeStatus();" />--%>
<%--					<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left :0px;top:30px;">--%>
<%--						<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 185px;border-top: none;position: relative;display:none;">--%>
<%--							&lt;%&ndash;<p class="item trainOrg allOrg orgs" flow="" value="全部" type="AllOrgP" style="line-height: 20px; padding:10px 0;cursor: default; ">全部</p>&ndash;%&gt;--%>
<%--							<c:forEach items="${orgs}" var="org">--%>
<%--								<p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}"--%>
<%--								   <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if>--%>
<%--								   <c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if>--%>
<%--								   orgCityId="${org.orgCityId}"--%>
<%--								   style="line-height: 20px; padding:5px 0;cursor: default; "--%>
<%--								   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>--%>
<%--								>${org.orgName}</p>--%>
<%--							</c:forEach>--%>
<%--						</div>--%>
<%--						<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--				<td class="td_left ">协同单位：</td>--%>
<%--				<td>--%>
<%--					<select name="joinOrgFlow" id="joinOrgFlow" class="select " style="width: 134px">--%>
<%--						<option value="">全部</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td class="td_left">培训专业：</td>--%>
<%--				<td>--%>
<%--					<select name="trainingSpeId" id="trainingSpeId"class="select" >--%>
<%--						<option value="">全部</option>--%>
<%--						<c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristList'}">--%>
<%--							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
<%--								<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">--%>
<%--									<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>--%>
<%--								</c:if>--%>
<%--							</c:forEach>--%>
<%--						</c:if>--%>
<%--						<c:if test="${param.tabTag eq 'FristWait2' or param.tabTag eq 'FristList2'}">--%>
<%--							<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">--%>
<%--								<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">--%>
<%--									<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>--%>
<%--								</c:if>--%>
<%--							</c:forEach>--%>
<%--						</c:if>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td class="td_left">姓&#12288;&#12288;名：</td>--%>
<%--				<td>--%>
<%--					<input type="text" name="userName" value="${param.userName}" class="input" style="margin-left: 0px;"/>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td class="td_left">证&nbsp;件&nbsp;号&nbsp;：</td>--%>
<%--				<td>--%>
<%--					<input type="text" name="idNo" value="${param.idNo}" class="input" style="margin-left: 0px;"/>--%>
<%--				</td>--%>
<%--				<td class="td_left">年&#12288;&#12288;级：</td>--%>
<%--				<td>--%>
<%--					<input type="text" id="sessionNumber" name="sessionNumber"  class="input" readonly="readonly" style="margin-left: 0px"/>--%>
<%--				</td>--%>
<%--				<td class="td_left">培训类别：</td>--%>
<%--				<td>--%>
<%--					&lt;%&ndash;<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" >&ndash;%&gt;--%>
<%--					<select name="trainingTypeId" id="trainingTypeId" class="select">--%>
<%--						&lt;%&ndash;<option value="">请选择</option>&ndash;%&gt;--%>
<%--						<c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristList'}">--%>
<%--							<option value="DoctorTrainingSpe" selected="selected">住院医师</option>--%>
<%--							<option value="WMFirst" <c:if test="${param.trainingTypeId eq 'WMFirst'}">selected="selected"</c:if>>一阶段</option>--%>
<%--							<option value="WMSecond" <c:if test="${param.trainingTypeId eq 'WMSecond'}">selected="selected"</c:if>>二阶段</option>--%>
<%--						</c:if>--%>
<%--						<c:if test="${param.tabTag eq 'FristWait2' or param.tabTag eq 'FristList2'}">--%>
<%--							<option value="AssiGeneral" selected="selected">助理全科</option>--%>
<%--						</c:if>--%>
<%--						&lt;%&ndash;<c:forEach items="${trainCategoryEnumList}" var="trainCategory">&ndash;%&gt;--%>
<%--							&lt;%&ndash;<option value="${trainCategory.id}"&ndash;%&gt;--%>
<%--									&lt;%&ndash;<c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>&ndash;%&gt;--%>
<%--						&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td class="td_left">审核状态：</td>--%>
<%--				<c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristWait2'}">--%>
<%--					<td>--%>
<%--						<select name="auditStatusId" id="auditStatusId" class="select" >--%>
<%--							<option value="WaitGlobalPass" selected>待省厅审核</option>--%>
<%--						</select>--%>
<%--					</td>--%>
<%--				</c:if>--%>
<%--				<c:if test="${param.tabTag ne 'FristWait' and param.tabTag ne 'FristWait2'}">--%>
<%--					<td>--%>
<%--						<select name="auditStatusId" id="auditStatusId" class="select" >--%>
<%--							<option value="">请选择</option>--%>
<%--								&lt;%&ndash;<c:forEach items="${jsResAsseAuditListEnumList}" var="type">&ndash;%&gt;--%>
<%--								&lt;%&ndash;<option value="${type.id}">${type.name}</option>&ndash;%&gt;--%>
<%--								&lt;%&ndash;</c:forEach>&ndash;%&gt;--%>
<%--								&lt;%&ndash;<option value="WaitGlobalPass">待审核</option>&ndash;%&gt;--%>
<%--							<option value="Auditing">待基地审核</option>--%>
<%--							<option value="LocalNotPassed">基地审核不通过</option>--%>
<%--							<option value="WaitChargePass">待市局审核</option>--%>
<%--							<option value="ChargeNotPassed">市局审核不通过</option>--%>
<%--							<option value="WaitGlobalPass">待省厅审核</option>--%>
<%--							<option value="GlobalNotPassed">省厅审核不通过</option>--%>
<%--							<option value="GlobalPassed">省厅审核通过</option>--%>
<%--							<option value="Black">省厅驳回</option>--%>
<%--						</select>--%>
<%--					</td>--%>
<%--				</c:if>--%>
<%--				<td class="td_left">是否延期：</td>--%>
<%--				<td>--%>
<%--					<select class="select" name="isPostpone">--%>
<%--						<option value="">请选择</option>--%>
<%--						<option--%>
<%--								<c:if test="${param.isPostpone eq GlobalConstant.FLAG_Y}">selected="selected"</c:if>--%>
<%--								value="${GlobalConstant.FLAG_Y}">是--%>
<%--						</option>--%>
<%--						<option--%>
<%--								<c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>--%>
<%--								value="${GlobalConstant.FLAG_N}">否--%>
<%--						</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td class="td_left">考试编号：</td>--%>
<%--				<td>--%>
<%--					<select name="testId"  class="select" >--%>
<%--						<option value="">请选择</option>--%>
<%--						<c:forEach items="${resTestConfigs}" var="resTest">--%>
<%--							<option value="${resTest.testId}" ${param.testId eq resTest.testId?'selected':''}>${resTest.testId}</option>--%>
<%--						</c:forEach>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr>--%>
<%--				<td class="td_left">人员类型：</td>--%>
<%--				<td colspan="3">--%>
<%--					<c:forEach items="${resDocTypeEnumList}" var="type">--%>
<%--						<label><input type="checkbox" id="${type.id}"value="${type.id}" checked class="docType" name="datas" />${type.name}&nbsp;</label>--%>
<%--					</c:forEach>--%>
<%--				</td>--%>
<%--				<td colspan="8">--%>
<%--					<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;--%>
<%--					<input class="btn_green" type="button" value="导&#12288;出" onclick="exportInfo();"/>&#12288;--%>
<%--					<c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristWait2'}">--%>
<%--						<input class="btn_green" type="button" value="批量审核" onclick="batchApply();"/>&#12288;--%>
<%--					</c:if>--%>
<%--					<input class="btn_green" type="button" value="更&#12288;多" onclick="searchMore();"/>&#12288;--%>
<%--					<input class="btn_green" type="button" value="重&#12288;置" onclick="freshen();"/>&#12288;--%>
<%--					<input class="btn_green" type="button" value="参考人员名称导出"--%>
<%--						   <c:if test="${param.tabTag eq 'FristWait' or param.tabTag eq 'FristWait2'}">style="display: none" </c:if>--%>
<%--						   onclick="exportCountryList();"/>&#12288;--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr class="searchMore" hidden>--%>
<%--				<td class="td_left">地&#12288;&#12288;市：</td>--%>
<%--				<td>--%>
<%--					<select id="cityId2" name="cityId" class="select"--%>
<%--							&lt;%&ndash;onchange="changeOrg(this)"&ndash;%&gt;--%>
<%--							onchange="changeOrgCityId()"--%>
<%--					></select>--%>
<%--				</td>--%>

<%--				<td class="td_left">最高学历：</td>--%>
<%--				<td>--%>
<%--                    <select name="educationId" class="select" >--%>
<%--                        <option value="">请选择</option>--%>
<%--                        <c:forEach var="dict" items="${dictTypeEnumUserEducationList }">--%>
<%--                            <option value="${dict.dictId }" <c:if test="${eduUserExt.sysUser.educationId eq dict.dictId }">selected="selected"</c:if>>${dict.dictName }</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--				</td>--%>
<%--				<td class="td_left">培训年限：</td>--%>
<%--				<td>--%>
<%--					<select name="trainYear"  class="select" >--%>
<%--						<option value="">请选择</option>--%>
<%--						<option value="OneYear" ${param.trainYear eq 'OneYear'?'selected':''}>一年</option>--%>
<%--						<option value="TwoYear" ${param.trainYear eq 'TwoYear' ?'selected':''}>两年</option>--%>
<%--						<option value="ThreeYear" ${param.trainYear eq 'ThreeYear' ?'selected':''}>三年</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--            <tr class="searchMore" hidden>--%>
<%--				<td class="td_left">公共科目：</td>--%>
<%--				<td>--%>
<%--					<select name="passFlag"  class="select" >--%>
<%--						<option value="">请选择</option>--%>
<%--						<option value="${GlobalConstant.PASS}"   ${param.passFlag eq GlobalConstant.PASS ? 'selected':''}>合格</option>--%>
<%--						<option value="${GlobalConstant.UNPASS}" ${param.passFlag eq GlobalConstant.UNPASS ?'selected':''}>不合格</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--                <td class="td_left">报考材料：</td>--%>
<%--                <td>--%>
<%--					<select name="materialName"  class="select" >--%>
<%--                    &lt;%&ndash;<select id="graduationMaterialName1"  class="select" >&ndash;%&gt;--%>
<%--                        <option value="">请选择</option>--%>
<%--                        <option value="医师资格证书" ${param.graduationMaterialName eq '医师资格证书' ? 'selected':''}>医师资格证书</option>--%>
<%--                        <option value="医师执业证书" ${param.graduationMaterialName eq '医师执业证书' ? 'selected':''}>医师执业证书</option>--%>
<%--                        <option value="已通过国家执业医师考试的成绩单" ${param.graduationMaterialName eq '已通过国家执业医师考试的成绩单' ? 'selected':''}>已通过国家执业医师考试的成绩单</option>--%>
<%--                        <option value="暂无" ${param.graduationMaterialName eq '暂无' ?'selected':''}>暂无</option>--%>
<%--                    </select>--%>
<%--                </td>--%>
<%--                <td colspan="2">--%>
<%--                    <input type="checkbox" value="Y" name="isNotMatch" />培训专业与执业范围不匹配&#12288;--%>
<%--                </td>--%>
<%--            </tr>--%>
			<%--<tr class="searchLess2" <c:if test="${param.tabTag eq 'FristWait'}">hidden</c:if>>--%>
				<%--<td colspan="8">--%>
					<%--<input class="btn_green" type="button" value="导出国家基地导入数据" onclick="exportCountryList();"/>&#12288;--%>
				<%--</td>--%>
			<%--</tr>--%>

<%--			<tr class="searchMore" hidden>--%>
<%--				<td class="td_left">资格证书：</td>--%>
<%--				<td>--%>
<%--					<select id="qualificationName" name="qualificationName"  class="select queryMore">--%>
<%--						<option value="">请选择</option>--%>
<%--						<option value="Y" ${param.qualificationName eq 'Y' ? 'selected':''}>有</option>--%>
<%--						<option value="N" ${param.qualificationName eq 'N' ? 'selected':''}>无</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td class="td_left">证书编号：</td>--%>
<%--				<td>--%>
<%--					<select id="qualificationMaterialCode" name="qualificationMaterialCode"  class="select queryMore" onchange="selectMaterialCode()">--%>
<%--						<option value="">请选择</option>--%>
<%--						<option value="Y" ${param.qualificationMaterialCode eq 'Y'?'selected':''}>已填写</option>--%>
<%--						<option value="N" ${param.qualificationMaterialCode eq 'N'?'selected':''}>未填写</option>--%>
<%--					</select>--%>
<%--						&lt;%&ndash;<input type="text" id="qualificationMaterialCode" name="qualificationMaterialCode"&ndash;%&gt;--%>
<%--							   &lt;%&ndash;value="${param.qualificationMaterialCode}" class="input queryMore" style="margin-left: 0px;"/>&ndash;%&gt;--%>
<%--				</td>--%>
<%--				<td class="td_left">证书附件：</td>--%>
<%--				<td>--%>
<%--					<select id="qualificationMaterialUrl" name="qualificationMaterialUrl" class="select queryMore" >--%>
<%--						<option value="">请选择</option>--%>
<%--						<option value="Y" ${param.qualificationMaterialUrl eq 'Y'?'selected':''}>有</option>--%>
<%--						<option value="N" ${param.qualificationMaterialUrl eq 'N'?'selected':''}>无</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--			<tr class="searchMore" hidden>--%>
<%--				<td class="td_left">轮转月数：</td>--%>
<%--				<td>--%>
<%--					<select id="schMonthSearch" name="schMonthSearch"  class="select queryMore" >--%>
<%--						<option value="">请选择</option>--%>
<%--						<option value="GreaterThan" ${param.schMonthSearch eq 'GreaterThan'?'selected':''}>大于</option>--%>
<%--						<option value="Equal" ${param.schMonthSearch eq 'Equal' ?'selected':''}>等于</option>--%>
<%--						<option value="LessThan" ${param.schMonthSearch eq 'LessThan' ?'selected':''}>小于</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td class="td_left">月&#12288;&#12288;数：</td>--%>
<%--				<td colspan="2">--%>
<%--					<input type="text" id="schMonth" name="schMonth" value="${param.schMonth}" class="input queryMore" style="margin-left: 0px;" onchange="searchSchMonth()"/>--%>
<%--				</td>--%>
<%--				<td class="td_left">减免证明：</td>--%>
<%--				<td>--%>
<%--					<select name="proveFileUrl"  class="select queryMore" >--%>
<%--						<option value="">请选择</option>--%>
<%--						<option value="Y" ${param.proveFileUrl eq 'Y'?'selected':''}>有</option>--%>
<%--						<option value="N" ${param.proveFileUrl eq 'N'?'selected':''}>无</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td class="td_left">科目附件：</td>--%>
<%--				<td>--%>
<%--					<select name="passFile"  class="select" >--%>
<%--						<option value="">请选择</option>--%>
<%--						<option value="Y"   ${param.passFile eq 'Y' ? 'selected':''}>已上传附件</option>--%>
<%--						<option value="N" ${param.passFile eq 'N' ?'selected':''}>未上传附件</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--			</tr>--%>

			<%--<tr class="searchMore" hidden>
				<td class="td_left">考核表附件：</td>
				<td>
					<select name="AfterEvaluation"  class="select queryMore" >
						<option value="">请选择</option>
						<option value="Y" ${param.AfterEvaluation eq 'Y'?'selected':''}>有</option>
						<option value="N" ${param.AfterEvaluation eq 'N'?'selected':''}>无</option>
					</select>
				</td>
				<td class="td_left">完成比例：</td>
				<td>
					<select id="AfterEvaluationSearch" name="AfterEvaluationSearch"  class="select queryMore" >
						<option value="">请选择</option>
						<option value="GreaterThan" ${param.AfterEvaluationSearch eq 'GreaterThan'?'selected':''}>大于</option>
						<option value="Equal" ${param.AfterEvaluationSearch eq 'Equal' ?'selected':''}>等于</option>
						<option value="LessThan" ${param.AfterEvaluationSearch eq 'LessThan' ?'selected':''}>小于</option>
					</select>
				</td>
				<td class="td_left">百&nbsp;分&nbsp;比&nbsp;：</td>
				<td>
					<select id="AfterEvaluationScale" name="AfterEvaluationScale"  class="select queryMore" onchange="searchAfterEvaluationScale()">
						<option value="">请选择</option>
						<option value="10" ${param.AfterEvaluationScale eq '10'?'selected':''}>10</option>
						<option value="20" ${param.AfterEvaluationScale eq '20' ?'selected':''}>20</option>
						<option value="30" ${param.AfterEvaluationScale eq '30' ?'selected':''}>30</option>
						<option value="40" ${param.AfterEvaluationScale eq '40'?'selected':''}>40</option>
						<option value="50" ${param.AfterEvaluationScale eq '50' ?'selected':''}>50</option>
						<option value="60" ${param.AfterEvaluationScale eq '60' ?'selected':''}>60</option>
						<option value="70" ${param.AfterEvaluationScale eq '70'?'selected':''}>70</option>
						<option value="80" ${param.AfterEvaluationScale eq '80' ?'selected':''}>80</option>
						<option value="90" ${param.AfterEvaluationScale eq '90' ?'selected':''}>90</option>
						<option value="100" ${param.AfterEvaluationScale eq '100' ?'selected':''}>100</option>
					</select>
				</td>
				<td class="td_left">填写时间：</td>
				<td>
					<select id="completeTime" name="completeTime"  class="select queryMore" onchange="changeCompleteTime()">
						<option value="">请选择</option>
						<option value="oneHour" ${param.completeTime eq 'oneHour' ?'selected':''}>一小时内</option>
						<option value="oneDay" ${param.completeTime eq 'oneDay'?'selected':''}>一天内</option>
					</select>
				</td>
			</tr>

			<tr class="searchMore" hidden>
				<td class="td_left">数据填写度：</td>
				<td>
					<select id="avgCompleteSearch" name="avgCompleteSearch"  class="select queryMore" >
						<option value="">请选择</option>
						<option value="GreaterThan" ${param.avgCompleteSearch eq 'GreaterThan'?'selected':''}>大于</option>
						<option value="Equal" ${param.avgCompleteSearch eq 'Equal' ?'selected':''}>等于</option>
						<option value="LessThan" ${param.avgCompleteSearch eq 'LessThan' ?'selected':''}>小于</option>
					</select>
				</td>
				<td colspan="2">
					<select id="avgComplete" name="avgComplete"  class="select queryMore" onchange="searchAvgComplete()">
						<option value="">请选择</option>
						<option value="10" ${param.avgComplete eq '10'?'selected':''}>10</option>
						<option value="20" ${param.avgComplete eq '20' ?'selected':''}>20</option>
						<option value="30" ${param.avgComplete eq '30' ?'selected':''}>30</option>
						<option value="40" ${param.avgComplete eq '40'?'selected':''}>40</option>
						<option value="50" ${param.avgComplete eq '50' ?'selected':''}>50</option>
						<option value="60" ${param.avgComplete eq '60' ?'selected':''}>60</option>
						<option value="70" ${param.avgComplete eq '70'?'selected':''}>70</option>
						<option value="80" ${param.avgComplete eq '80' ?'selected':''}>80</option>
						<option value="90" ${param.avgComplete eq '90' ?'selected':''}>90</option>
						<option value="100" ${param.avgComplete eq '100' ?'selected':''}>100</option>
					</select>
				</td>
				<td class="td_left">数据审核度：</td>
				<td>
					<select id="avgAuditSearch" name="avgAuditSearch"  class="select queryMore" >
						<option value="">请选择</option>
						<option value="GreaterThan" ${param.avgAuditSearch eq 'GreaterThan'?'selected':''}>大于</option>
						<option value="Equal" ${param.avgAuditSearch eq 'Equal' ?'selected':''}>等于</option>
						<option value="LessThan" ${param.avgAuditSearch eq 'LessThan' ?'selected':''}>小于</option>
					</select>
				</td>
				<td colspan="2">
					<select id="avgAudit" name="avgAudit"  class="select queryMore" onchange="searchAvgAudit()">
						<option value="">请选择</option>
						<option value="10" ${param.avgAudit eq '10'?'selected':''}>10</option>
						<option value="20" ${param.avgAudit eq '20' ?'selected':''}>20</option>
						<option value="30" ${param.avgAudit eq '30' ?'selected':''}>30</option>
						<option value="40" ${param.avgAudit eq '40'?'selected':''}>40</option>
						<option value="50" ${param.avgAudit eq '50' ?'selected':''}>50</option>
						<option value="60" ${param.avgAudit eq '60' ?'selected':''}>60</option>
						<option value="70" ${param.avgAudit eq '70'?'selected':''}>70</option>
						<option value="80" ${param.avgAudit eq '80' ?'selected':''}>80</option>
						<option value="90" ${param.avgAudit eq '90' ?'selected':''}>90</option>
						<option value="100" ${param.avgAudit eq '100' ?'selected':''}>100</option>
					</select>
				</td>
			</tr>--%>
<%--			<tr class="searchMore" hidden>--%>
<%--				<td colspan="8">--%>
<%--					<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/>&#12288;--%>
<%--					<input class="btn_green" type="button" value="导&#12288;出" onclick="exportInfo();"/>&#12288;--%>
<%--					<input class="btn_green" type="button" value="批量审核" onclick="batchApply();"/>&#12288;--%>
<%--					<input class="btn_green" type="button" value="缩&#12288;进" onclick="searchLess();"/>&#12288;--%>
<%--					<input class="btn_green" type="button" value="导出国家基地导入数据"--%>
<%--						   <c:if test="${param.tabTag eq 'FristWait'}">style="display: none" </c:if>--%>
<%--						   onclick="exportCountryList();"/>&#12288;--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--		</table>--%>
	</form>
</div>
<div id="doctorListZi" style="padding: 0 20px;box-sizing: border-box;width: 92%;overflow-x: auto;">
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
<div>
	<c:forEach items="${orgFlowList}" var="flow">
		<input type="hidden" id="${flow}" value="${flow}"/>
	</c:forEach>

</div>