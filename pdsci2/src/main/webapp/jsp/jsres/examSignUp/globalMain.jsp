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
		max-width: 150px;;
	}
	.searchTable tr{
		line-height: 40px;
	}
	.searchTable .select{
		padding: 4px 2px;
		box-sizing: content-box;
	}
	.searchTable .td_left{
		word-wrap:break-word;
		/*width:5em;*/
		height: auto;
		line-height: auto;
		text-align: right;
	}
	.input{
		margin:0px;
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
		$('#signupYear').datepicker({
			startView: 2,
			maxViewMode: 2,
			minViewMode:2,
			format:'yyyy'
		});
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
	function initOrg()
	{
		var datas=[];
		<c:forEach items="${orgs}" var="org">
		<c:if test="${(sessionScope.currUser.orgFlow!=org.orgFlow and roleFlag eq GlobalConstant.USER_LIST_GLOBAL)
		or roleFlag ne GlobalConstant.USER_LIST_GLOBAL}">
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
	function changeOrg(obj)
	{
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
		$("#orgName").val("");
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
		page=page||$("#currentPage").val();
		page=page||"1";
		$("#currentPage").val(page);
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/examSignUp/signList'/>?roleFlag=${roleFlag}&typeId=${typeId}",$("#searchForm").serialize(),false);
	}
	function exportInfo()
	{
		var data="";
		$("input[class='docType']:checked").each(function(){
			data+="&datas="+$(this).val();
		});
		if(data==""){
			jboxTip("请选择人员类型！");
			return false;
		}
		if(!$("#signupYear").val()){
			jboxTip("请选申请年份！");
			return false;
		}
		var url = "<s:url value='/jsres/examSignUp/exportList'/>?roleFlag=${roleFlag}&typeId=${typeId}";
		jboxExpLoading($("#searchForm"),url);
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
		var signupFlow = "";
		var selects = $("td input[type='checkbox'][name='signupFlow']:checked");
		if (selects.length == 0) {
			jboxTip("请选择批量审核的数据！");
			return false;
		}
		for (var i = 0; i < selects.length; i++) {
			if (i == 0)
				signupFlow += "signupFlow=" + $(selects[i]).val();
			else
				signupFlow += "&signupFlow=" + $(selects[i]).val();
		}

		var url = "<s:url value='/jsres/examSignUp/batchAuditApply?'/>" + signupFlow;
		jboxOpen(url, "审核学员补考信息", 800, 400);
	}
</script>
<div class="main_hd">
<%--			<h2 class="underline">补考审核</h2>--%>
</div>
<div class="div_search" style="box-sizing: border-box;line-height:normal;">
	<form id="searchForm">
		<input type="hidden" id="currentPage" name="currentPage"/>
		<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
		<input type="hidden" id="tabTag" name="tabTag" value="${tabTag}"/>
		<input type="hidden" id="orgCityFlag" value=""/>
		<%--省厅市局查询条件--%>
		<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td class="td_left">结业年份：</td>
				<td>
					<input type="text" id="signupYear" name="signupYear" class="input" readonly="readonly" value="${pdfn:getCurrYear()}" style="width: 128px;margin-left: 0px"/>
				</td>
				<td class="td_left">地&#12288;&#12288;市：</td>
				<td>
					<select id="cityId2" name="cityId" class="select"
							onchange="changeOrgCityId()"
							style="width:128px;"></select>
				</td>
				<td class="td_left">培训基地：</td>
				<td>
					<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off" onkeydown="changeStatus();" onkeyup="changeStatus();" style="width: 128px"/>
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
				<td class="td_left ">协同单位：</td>
				<td>
					<select name="joinOrgFlow" id="joinOrgFlow" class="select " style="width: 128px">
						<option value="">全部</option>
					</select>
				</td>
				<td class="td_left">培训专业：</td>
				<td>
					<select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 128px;">
						<option value="">全部</option>
						<c:if test="${param.tabTag eq 'SecondWait'}">
							<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
								<c:if test="${empty speIds or (pdfn:contain(dict.dictId, speIds))}">
									<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
								</c:if>
							</c:forEach>
						</c:if>
						<c:if test="${param.tabTag eq 'SecondWait2'}">
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
					<input type="text" name="userName" value="${param.userName}" class="input" style="width: 128px;margin-left: 0px;"/>
				</td>
			</tr>
			<tr>
				<td class="td_left">证&nbsp;件&nbsp;号&nbsp;：</td>
				<td>
					<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 128px;margin-left: 0px;"/>
				</td>
				<td class="td_left">年&#12288;&#12288;级：</td>
				<td>
					<input type="text" id="sessionNumber" name="sessionNumber"  class="input" readonly="readonly" style="width: 128px;margin-left: 0px"/>
				</td>
				<td class="td_left">培训类别：</td>
				<td>
					<%--<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:128px;">--%>
					<select name="trainingTypeId" id="trainingTypeId" class="select" style="width:128px;">
						<%--<option value="">请选择</option>--%>
						<c:if test="${param.tabTag eq 'SecondWait'}">
							<option value="DoctorTrainingSpe" selected="selected">住院医师</option>
							<option value="WMFirst" <c:if test="${param.trainingTypeId eq 'WMFirst'}">selected="selected"</c:if>>一阶段</option>
							<option value="WMSecond" <c:if test="${param.trainingTypeId eq 'WMSecond'}">selected="selected"</c:if>>二阶段</option>
						</c:if>
						<c:if test="${param.tabTag eq 'SecondWait2'}">
							<option value="AssiGeneral" selected="selected">助理全科</option>
						</c:if>
					</select>
				</td>
				<td class="td_left">审核状态：</td>
				<td>
					<select name="auditStatusId" id="auditStatusId" class="select"  style="width:128px;">
						<option value="">请选择</option>
						<option value="WaitGlobalPass" selected>待省厅审核</option>
					</select>
				</td>
				<td class="td_left">补考类型：</td>
				<td>
					<select name="signUpTypeId" class="select" style="width:128px;">
						<option value="">请选择</option>
						<option value="Skill">技能补考</option>
						<option value="Theory">理论补考</option>
					</select>
				</td>
				<td class="td_left">是否延期：</td>
				<td>
					<select class="select" name="isPostpone" style="width:128px;">
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
			</tr>
			<tr>
				<td class="td_left">考试编号：</td>
				<td>
					<select name="testId"  class="select" style="width: 128px;">
						<option value="">全部</option>
						<c:forEach items="${resTestConfigs}" var="resTest">
							<option value="${resTest.testId}" ${param.testId eq resTest.testId?'selected':''}>${resTest.testId}</option>
						</c:forEach>
					</select>
				</td>
				<td class="td_left">人员类型：</td>
				<td colspan="3">
					<c:forEach items="${resDocTypeEnumList}" var="type">
						<label><input type="checkbox" id="${type.id}"value="${type.id}" checked class="docType" name="datas" />${type.name}&nbsp;</label>
					</c:forEach>
				</td>
				<td colspan="6">
					<input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>&#12288;
					<input class="btn_green" type="button" value="导&#12288;出" onclick="exportInfo();"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="doctorListZi" style="padding: 10px 54px 10px 40px;">
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