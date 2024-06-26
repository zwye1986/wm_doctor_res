<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
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
	<c:forEach items="${jszyResDocTypeEnumList}" var="type">
		<c:forEach items="${datas}" var="data"> 
			if("${data}"=="${type.id}"){
				$("#"+"${data}").attr("checked","checked");
			}
		</c:forEach>
		<c:if test="${empty datas}">
			$("#"+"${type.id}").attr("checked","checked");
		</c:if>
	</c:forEach>
	<c:if test="${not empty param.trainingTypeId}">
		changeTrainSpes('0');
	</c:if>
    // 当枚举选项个数与勾选个数相同时，勾中全部选项
    if ("${fn:length(trainYears)}" == "${fn:length(jszyResTrainYearEnumList)}") {
        $("#trainYearAll").attr("checked", "checked");
    }

    <c:forEach items="${jszyResTrainYearEnumList}" var="trainYear">
        <c:forEach items="${trainYears}" var="data">
        if ("${data}" == "${trainYear.id}") {
            $("#" + "${data}").attr("checked", "checked");
        }
        </c:forEach>
        <c:if test="${empty trainYears}">
        $("#" + "${trainYear.id}").attr("checked", "checked");
        </c:if>
    </c:forEach>
	toPage();
    showProve();
});
function showProve()
{

    var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
    jboxGet(url,null, function(json) {
        // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
        <%--var newJsonData=new Array();--%>
        <%--var j=0;--%>
        <%--for(var i=0;i<json.length;i++){--%>
            <%--var provData = json[i];--%>
            <%--if(provData.v=='320000'){--%>
                <%--if("${sendCityId}"!="") {--%>
                    <%--var s = provData.s;--%>
                    <%--var k = 0;--%>
                    <%--var newS = new Array();--%>
                    <%--for (var m = 0; m < s.length; m++) {--%>
                        <%--if(s[m].v=="${sendCityId}"){--%>
                            <%--newS[k++]=s[m];--%>
                        <%--}--%>
                    <%--}--%>
                    <%--provData.s=newS;--%>
                <%--}--%>
                <%--newJsonData[j++]=provData;--%>
            <%--}--%>
        <%--}--%>
        $.cxSelect.defaults.url = json;
        $.cxSelect.defaults.nodata = "none";
        if("${sessionScope.userListScope==GlobalConstant.USER_LIST_CHARGE}"=="true"){
            $.cxSelect.defaults.required = true;
        }
        $("#native").cxSelect({
            selects : ["province", "city"],
            nodata : "none",
            firstValue : ""
        });
    },null,false);
}
function toPage(page) {

	var data="";
	<c:forEach items="${jszyResDocTypeEnumList}" var="type">
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
			}
	</c:forEach>
	if("${sessionScope.userListScope!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}"=="true"){
		if(data==""){
			jboxTip("请选择人员类型！");
			return false;
		}
	}
	$("#currentPage").val(page);
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jszy/doctorRecruit/doctorTrendListSun'/>?"+data+"&roleFlag=${roleFlag}&zlFlag=${zlFlag}",$("#searchForm").serialize(),false);
}

function doctorPassedList(doctorFlow,recruitFlow,zlFlag){
	var hideApprove="hideApprove";
	var url = "<s:url value='/jszy/manage/province/doctor/doctorPassedList'/>?info=${GlobalConstant.FLAG_Y}&liId="+recruitFlow+"&recruitFlow="+recruitFlow+"&openType=open&doctorFlow="+doctorFlow+"&hideApprove="+hideApprove+"&zlFlag="+zlFlag;
	jboxOpen(url,"学员信息",1050,600);
	
}

function updateDoctorTrend(recruitFlow,doctorFlow){
	var url = "<s:url value='/jszy/manage/updateDoctorRecruit'/>?doctorFlow="+doctorFlow+"&recruitFlow="+recruitFlow;
	jboxOpen(url,"更新学员信息", 950, 500);
}
function changeTrainSpes(t){
	//清空原来专业！！！
	var sessionNumber=$("#sessionNumber").val();
	var trainCategoryid=$("#trainingTypeId").val();
	<%--if(trainCategoryid =="${dictTypeEnumDoctorTrainingSpe.id}"){--%>
		<%--$("#derateFlagLabel").show();--%>
	<%--}else{--%>
		<%--$("#derateFlag").attr("checked",false);--%>
		<%--$("#derateFlagLabel").hide();--%>
	<%--}--%>
	var orgFlow=$("#orgFlow").val();
	if(trainCategoryid==""){
		$("select[name=trainingSpeId] option[value != '']").remove();
		return false;
	}
	
	if("${GlobalConstant.USER_LIST_LOCAL}" == "${sessionScope.userListScope}"){
		orgFlow="${sessionScope.currUser.orgFlow}";
	}
	if("${GlobalConstant.USER_LIST_CHARGE}" == "${sessionScope.userListScope}"||"${GlobalConstant.USER_LIST_GLOBAL}" == "${sessionScope.userListScope}"){
		if(orgFlow == ""){
			$("select[name=trainingSpeId] option[value != '']").remove();
			$("#"+trainCategoryid+"_select").find("option").each(function(){
				$(this).clone().appendTo($("#trainingSpeId"));
			});
			return false;
		}
	}
	var url = "<s:url value='/jszy/doctor/searchResOrgSpeList'/>?sessionNumber="+sessionNumber+"&orgFlow="+orgFlow+"&speTypeId="+trainCategoryid;
	jboxGet(url, null, function(resp){
		$("select[name=trainingSpeId] option[value != '']").remove();
			if(resp!=""){
		   		var dataObj = resp;
		   	 for(var i = 0; i<dataObj.length;i++){
			     	var speId = dataObj[i].speId;
			     	var speName = dataObj[i].speName;
				 	if("${param.trainingSpeId}"==speId)
					{
						$option =$("<option selected='selected'></option>");
					}else{
						$option =$("<option></option>");
					}
			     	$option.attr("value",speId);
			     	$option.text(speName);
			     	$("select[name=trainingSpeId]").append($option);
			   }
			}
		<c:if test="${not empty param.trainingTypeId}">
			if(t=='0') {
				toPage();
			}
		</c:if>
		}, null , false);
}
function editDoctorTrend(doctorFlow,recruitflow){
	var url = "<s:url value='/jsres/doctorRecruit/updateDoctorRecruit'/>?doctorFlow="+doctorFlow+"&recruitFlow="+recruitflow;
	jboxOpen(url,"更新学员走向",650,250);
}
function exportExcel(){
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("请选择年级！");
		return false;
	}
	var data="";
	<c:forEach items="${jszyResDocTypeEnumList}" var="type">
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
			}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return false;
	}
	var url = "<s:url value='/jszy/doctor/exportDoctor'/>?"+data+"&sessionNumber="+sessionNumber+"&zlFlag=${zlFlag}";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
function exportForDetail(){
	var sessionNumber=$("#sessionNumber").val();
	if(sessionNumber==""){
		jboxTip("请选择年级");
		return false;
	}
	var data="";
	<c:forEach items="${jszyResDocTypeEnumList}" var="type">
		if($("#"+"${type.id}").attr("checked")){
			data+="&datas="+$("#"+"${type.id}").val();
			}
	</c:forEach>
	if(data==""){
		jboxTip("请选择人员类型！");
		return false;
	}
	var url = "<s:url value='/jszy/doctor/exportForDetail'/>?"+data+"&sessionNumber="+sessionNumber+"&zlFlag=${zlFlag}";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
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
//			 $(pDiv).css("left",$(this).offset().left-$(this).prev().prev().prev().offset().left);
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
			input.attr("title",value);
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

function InformationQuery(doctorFlow,roleFlag){
	var formdate=$("#searchForm").serialize();
	formdate=formdate.replaceAll("&","formAnd");
	formdate=formdate.replaceAll("=","formeq");
	jboxPost("<s:url value='/jszy/doctor/checkDoctorAuth'/>?doctorFlow="+doctorFlow+"&roleFlag="+roleFlag,null,function(resp){
		if(resp=="${GlobalConstant.FLAG_Y}"){
			jboxLoad("content","<s:url value='/jszy/doctor/trainRegister'/>?hideApprove=hideApprove&doctorFlow="+doctorFlow+"&roleFlag="+roleFlag+"&search="+formdate,true);
		}else{
			jboxTip("该学员尚未上传诚信声明！");
		}
	},null,false);
}
function changeOrgFlow(obj){
	var items = $("#pDiv").find("p."+$(obj).attr("id")+".item[value='"+$(obj).val()+"']");
	var flow=$(items).attr("flow") || '';
	$("#orgFlow").val(flow);
	showJointOrg(flow);
 }
 function changeCheckBox(obj){
	 var company=true;
	 var social=true;
	 var companyEntrust=false;
	 var graduate=false;
	 $(".docType:checked").each(function(){
		 if($(this).val()=="${jszyResDocTypeEnumCompany.id}"){
			 company=false;
		 }
		 if($(this).val()=="${jszyResDocTypeEnumCompanyEntrust.id}"){
			 companyEntrust=true;
		 }
		 if($(this).val()=="${jszyResDocTypeEnumSocial.id}"){
			 social=false;
		 }
		 if($(this).val()=="${jszyResDocTypeEnumGraduate.id}"){
			 graduate=true;
		 }
	 });
	 if(company&social){
         if(graduate){
             $("[name='sendProvId']").val("");
             $("[name='sendCityId']").val("");
             $(".sendCityName").hide();
             if(companyEntrust){
                 $("#workOrgName").val("");
                 $(".workOrgName").hide();
             }else{
                 $(".workOrgName").show();
             }
         }else{
             $("#workOrgName").val("");
             $(".workOrgName").hide();
             if(companyEntrust){
                 $(".sendCityName").show();

             }else{
				 $("[name='sendProvId']").val("");
                 $("[name='sendCityId']").val("");
                 $(".sendCityName").hide();

             }
         }
	 }else {
		 $("#workOrgName").val("");
		 $(".workOrgName").hide();
		 $("[name='sendProvId']").val("");
         $("[name='sendCityId']").val("");
         $(".sendCityName").hide();
	 }
 }
 function changeDerateBox(obj){
     debugger;
     console.log(obj);
     if(obj.checked){
         $("#3").attr("checked", false);
     }
 }
	function exTrainingDocPdf(){
		location.href = '<s:url value="/jszy/manage/exTrainingDocPdf"/>?orgFlow=${currUser.orgFlow}';
	}
	function archiveDoctor(){
		var url = "<s:url value="/jszy/archive/addArchive"/>";
		jboxOpen(url,"添加存档",500,280,true);
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
    <h2 class="underline">学员信息查询</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
		<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_GLOBAL or sessionScope.userListScope eq GlobalConstant.USER_LIST_CHARGE}">
			<form id="searchForm">
				<input type="hidden" id="currentPage" name="currentPage"/>
				<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
				<table class="searchTable">
					<tr>
						<td class="td_left">姓&#12288;&#12288;名：</td>
						<td>
							<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;"/>
						</td>
						<td class="td_left">年&#12288;&#12288;级：</td>
						<td>
							<input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()-1}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
						</td>
						<%--<td class="td_left">培训类别：</td>--%>
						<%--<td>--%>
							<%--<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">--%>
								<%--<option value="">请选择</option>--%>
								<%--<c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">--%>
									<%--<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</td>--%>
						<%--<td class="td_left">培训专业：</td>--%>
						<%--<td>--%>
							<%--<select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 106px;">--%>
								<%--<option value="">全部</option>--%>
							<%--</select>--%>
						<%--</td>--%>
						<td class="td_left">培训专业：</td>
						<td>
							<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">
								<option value="">全部</option>
								<c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">
									<%--<c:if test="${trainCategory.isView eq GlobalConstant.FLAG_Y}">--%>
										<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
									<%--</c:if>--%>
								</c:forEach>
							</select>
						</td>
						<td class="td_left">对应专业：</td>
						<td>
							<select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 106px;">
								<option value="">全部</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="td_left">证&ensp;件&ensp;号：</td>
						<td>
							<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 100px;"/>
						</td>
						<td class="td_left">结业考核&#12288;<br/>年&#12288;&#12288;份：</td>
						<td>
							<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
						</td>
						<td class="td_left">人员类型：</td>
						<td  colspan="3">
							<c:forEach items="${jszyResDocTypeEnumList}" var="type">
								<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
								<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
							</c:forEach>
						</td>
					</tr>
					<tr>
						<td class="td_left">培训基地：</td>
						<td>
							<c:set var="orgName" value=""/>
							<c:forEach items="${orgs}" var="org">
								<c:if test="${param.orgFlow==org.orgFlow }">
									<c:set var="orgName" value="${org.orgName}"/>
								</c:if>
							</c:forEach>

							<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off" style="margin-left: 0px;width: 100px"  onkeydown="changeStatus();" onkeyup="changeStatus();" />
							<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px; left:0px;">
								<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
									<c:forEach items="${orgs}" var="org">
										<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:5px 0;cursor: default;"
										   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
										>${org.orgName}</p>
									</c:forEach>
								</div>
								<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
							</div>
						</td>
						<td class="td_left">学员状态：</td>
						<td>
							<select name="doctorStatusId" class="select" style="width: 106px">
								<option value="">全部</option>
								<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
									<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>

                        <td class="td_left">培养年限：</td>
                        <td colspan="2">
                            <c:forEach items="${jszyResTrainYearEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}" value="${type.id}" class="trainYear"
                                              name="trainYears"/>${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
						<td >
							<%--<label style="cursor: pointer; " id='TCMAssiGeneral'>--%>
								<%--<input type="checkbox"--%>
									   <%--<c:if test="${param.TCMAssiGeneral eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>--%>
									   <%--name="TCMAssiGeneral" value="${GlobalConstant.FLAG_Y}"/>&nbsp;仅中医助理全科&#12288;--%>
							<%--</label>--%>
							<label  id="derateFlagLabel" style="cursor: pointer;">
								<input type="checkbox" id="derateFlag" onclick="changeDerateBox(this);" <c:if test="${param.derateFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="derateFlag" value="${GlobalConstant.FLAG_Y}">
								&nbsp;只显示减免
								&#12288;<label style="cursor: pointer;display: none;" id='jointOrg'><input type="checkbox" id="jointOrgFlag" <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;协同基地</label>
							</label>
						</td>
					</tr>
					<tr>
                        <td style="display: none" class="td_left sendCityName">
                            派送地市：
                        </td>
                        <td style="display: none" class="sendCityName" colspan="2">
                            <div id="native" class="sendCityName">
								<select id="sendProvId" class="notBlank province select" style="width: 107px;"
										data-value="${doctor.sendProvId}" name="sendProvId">

								</select>
                                <select id="sendCityId" name="sendCityId"
                                        class="notBlank city select" style="width: 107px" data-value="${doctor.sendCityId}">${doctor.sendCityName}
                                    <input id="sendCityName" type="hidden" name="sendCityName" value="${doctor.sendCityName}"/>
                                </select>
                            </div>
                        </td>
                        <td style="display: none" class="td_left workOrgName">
                            派送学校：
                        </td>
                        <td style="display: none" class="workOrgName">
                            <select name="workOrgName" id="workOrgName" class="select" style="width: 107px">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
                                    <option value="${dict.dictName}" <c:if test="${param.dictName==dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
						<td id="func" colspan="6">
							<input class="btn_brown" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;&nbsp;
							<input class="btn_brown" type="button" value="导出花名册" onclick="exportExcel();"/>&nbsp;&nbsp;
							<input class="btn_brown" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>&nbsp;&nbsp;
							<%--<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_GLOBAL and zlFlag ne GlobalConstant.FLAG_Y }">--%>
								<%--<input class="btn_brown" style="margin-left: 0px;" type="button" value="设置存档时间" onclick="archiveDoctor();"/>&nbsp;--%>
							<%--</c:if>--%>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
		<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_LOCAL}">
			<form id="searchForm">
				<input type="hidden" id="currentPage" name="currentPage"/>
				<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>
				<table class="searchTable">
					<tr>
						<td class="td_left">姓&#12288;&#12288;名：</td>
						<td>
							<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;"/>
						</td>
						<td class="td_left">年&#12288;&#12288;级：</td>
						<td>
							<input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
						</td>
						<%--<td class="td_left">培训类别：</td>--%>
						<%--<td>--%>
							<%--<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">--%>
								<%--<option value="">全部</option>--%>
								<%--<c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">--%>
									<%--<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
								<%--</c:forEach>--%>
							<%--</select>--%>
						<%--</td>--%>
						<%--<td class="td_left">培训专业：</td>--%>
						<%--<td>--%>
							<%--<select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 107px;">--%>
								<%--<option value="">全部</option>--%>
							<%--</select>--%>
						<%--</td>--%>
						<td class="td_left">培训专业：</td>
						<td>
							<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">
								<option value="">全部</option>
								<c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">
									<%--<c:if test="${trainCategory.isView eq GlobalConstant.FLAG_Y}">--%>
										<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
									<%--</c:if>--%>
								</c:forEach>
							</select>
						</td>
						<td class="td_left">对应专业：</td>
						<td>
							<select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 106px;">
								<option value="">全部</option>
							</select>
						</td>
					</tr>
					<tr>
						<td class="td_left">学员状态：</td>
						<td>
							<select name="doctorStatusId" class="select" style="width: 107px">
								<option></option>
								<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">
									<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<td class="td_left">结业考核&#12288;<br/>年&#12288;&#12288;份：</td>
						<td>
							<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>
						</td>
						<td class="td_left">人员类型：</td>
						<td  colspan="3">
							<c:forEach items="${jszyResDocTypeEnumList}" var="type">
								<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>
								<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>
							</c:forEach>
						</td>
					</tr>
					<tr>

						<td class="td_left">证&ensp;件&ensp;号：</td>
						<td>
							<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 100px;"/>
						</td>
                        <td style="display: none" class="td_left sendCityName">
                            派送地市：
                        </td>
                        <td style="display: none" class="sendCityName"colspan="2">
                            <div id="native" class="sendCityName">
								<select id="sendProvId" class="notBlank province select" style="width: 107px;"
										data-value="${doctor.sendProvId}" name="sendProvId">

								</select>
                                <select id="sendCityId" name="sendCityId"
                                        class="notBlank city select" style="width: 107px" data-value="${doctor.sendCityId}">${doctor.sendCityName}
                                    <input id="sendCityName" type="hidden" name="sendCityName" value="${doctor.sendCityName}"/>
                                </select>
                            </div>
                        </td>
						<td style="display: none" class="td_left workOrgName">
							派送学校：
						</td>
						<td style="display: none" class="workOrgName">
							<select name="workOrgName" id="workOrgName" class="select" style="width: 107px">
								<option value="">请选择</option>
								<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
									<option value="${dict.dictName}" <c:if test="${param.dictName==dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>
								</c:forEach>
							</select>
						</td>
						<td colspan="4">
							<%--<label style="cursor: pointer; " id='TCMAssiGeneral'>--%>
								<%--<input type="checkbox"--%>
									   <%--<c:if test="${param.TCMAssiGeneral eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>--%>
									   <%--name="TCMAssiGeneral" value="${GlobalConstant.FLAG_Y}"/>&nbsp;仅中医助理全科&#12288;--%>
							<%--</label>--%>
							<label  id="derateFlagLabel" style="cursor: pointer;">
								<input type="checkbox" id="derateFlag"  <c:if test="${param.derateFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="derateFlag" value="${GlobalConstant.FLAG_Y}">
								&nbsp;只显示减免
								<c:if test="${empty isJointOrg}">
									&#12288;<label style="cursor: pointer;" id='jointOrg'><input type="checkbox" id="jointOrgFlag"  <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;协同基地</label>
								</c:if>
							</label>
						</td>
					</tr>
					<tr>
						<td id="func" colspan="8">
							<input class="btn_brown" type="button" value="查&#12288;询" onclick="toPage();"/>&nbsp;&nbsp;
							<input class="btn_brown" type="button" value="导出花名册" onclick="exportExcel();"/>&nbsp;&nbsp;
							<input class="btn_brown" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>&nbsp;&nbsp;
							<input class="btn_brown" type="button" value="在培医师花名册" onclick="exTrainingDocPdf();"/>
						</td>
					</tr>
				</table>
			</form>
		</c:if>
	<%--<form id="searchForm">--%>
		<%--<input type="hidden" id="currentPage" name="currentPage"/>--%>
		<%--<input type="hidden" id="orgTypeFlag" value="${param.orgLevel}"/>--%>
	   <%--<c:if test="${sessionScope.userListScope != GlobalConstant.USER_LIST_LOCAL}">--%>
		   <%--<label>基地类型：</label>--%>
	   		<%--<select name="orgLevel" id="orgLevelId" class="select" style="width:107px;" onchange="chanegOrgLevel(this);">--%>
				 	<%--<option value="">请选择</option>--%>
					<%--<c:forEach items="${orgLevelEnumList}" var="level">--%>
						<%--<option value="${level.id}" <c:if test="${param.orgLevel==level.id}">selected="selected"</c:if>--%>
						<%--<c:if test="${orgLevelEnumGaugeTrainingBase.id ==level.id}">style="display: none;"</c:if>--%>
						<%-->${level.name}</option>--%>
					<%--</c:forEach>--%>
			<%--</select>&#12288;&nbsp;--%>
		   	 <%--<label>培训基地：</label>--%>
		   <%--<c:set var="orgName" value=""/>--%>
			   <%--<c:forEach items="${orgs}" var="org">--%>
				   <%--<c:if test="${param.orgFlow==org.orgFlow }">--%>
					   <%--<c:set var="orgName" value="${org.orgName}"/>--%>
				   <%--</c:if>--%>
			   <%--</c:forEach>--%>

		   	 <%--<input id="trainOrg" oncontextmenu="return false"  class="toggleView input" type="text" value="${orgName}"  autocomplete="off" style="margin-left: 0px;width: 156px"  onkeydown="changeStatus();" onkeyup="changeStatus();" />--%>
			<%--<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">--%>
			    <%--<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">--%>
				    <%--<c:forEach items="${orgs}" var="org">--%>
				    	<%--<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 20px; padding:10px 0;cursor: default; "--%>
				    	<%--<c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>--%>
				    	<%-->${org.orgName}</p>--%>
				    <%--</c:forEach>--%>
				 <%--</div>--%>
			   <%--<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>--%>
			<%--</div>&#12288;&#12288;&nbsp;--%>
	   <%--</c:if>--%>
	        <%--培训类别：--%>
	        <%--<select name="trainingTypeId" id="trainingTypeId" class="select" onchange="changeTrainSpes('1')" style="width:107px;">--%>
	        	<%--<option value="">请选择</option>--%>
				<%--<c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">--%>
					<%--<option value="${trainCategory.id}" <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
				<%--</c:forEach>--%>
			<%--</select>&#12288;&nbsp;--%>
		<%--<c:if test="${sessionScope.userListScope!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}">--%>
			<%--结业考核年份：--%>
			<%--<input type="text" id="graduationYear" name="graduationYear"value="${param.graduationYear}" class="input" readonly="readonly" style="width: 110px;margin-left: 0px"/>&#12288;--%>
		<%--</c:if>--%>

	   <%--<c:if test="${sessionScope.userListScope==GlobalConstant.RES_ROLE_SCOPE_SCHOOL}"><br/></c:if>--%>
	        <%--培训专业：--%>
		<%--<select name="trainingSpeId" id="trainingSpeId"class="select" style="width: 106px;">--%>
		    <%--<option value="">全部</option>--%>
	    <%--</select>&#12288;&nbsp;--%>
		<%--<c:if test="${sessionScope.userListScope != GlobalConstant.USER_LIST_LOCAL }">--%>
			<%--<label style="cursor: pointer;display: none;" id='jointOrg'><input type="checkbox" id="jointOrgFlag" <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>--%>
		<%--</c:if>--%>
		<%--<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_LOCAL }">--%>
			<%--<c:if test="${empty isJointOrg}">--%>
				<%--&#12288;<label style="cursor: pointer;" id='jointOrg'><input type="checkbox" id="jointOrgFlag"  <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>--%>
			<%--</c:if>--%>
		<%--</c:if>--%>
	  	 <%--<c:if test="${sessionScope.userListScope!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}">&#12288;<br/></c:if>--%>
		<%--医师状态：--%>
		<%--<select name="doctorStatusId" class="select" style="width: 107px">--%>
			<%--<option></option>--%>
			<%--<c:forEach var="dict" items="${dictTypeEnumDoctorStatusList}">--%>
				<%--<option value="${dict.dictId}" <c:if test="${param.doctorStatusId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>--%>
			<%--</c:forEach>--%>
		<%--</select>&#12288;&nbsp;--%>
	  	 <%--年&#12288;&#12288;级：--%>
		<%--<input type="text" id="sessionNumber" name="sessionNumber"  <c:if test="${empty param.sessionNumber }">value="${pdfn:getCurrYear()}"</c:if>value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 135px;margin-left: 0px"/>&#12288;--%>
		 <%--<c:if test="${sessionScope.userListScope==GlobalConstant.RES_ROLE_SCOPE_SCHOOL}">&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;</c:if>--%>
		<%--<c:if test="${sessionScope.userListScope!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}">--%>
			<%--人员类型：--%>
			<%--<c:forEach items="${jszyResDocTypeEnumList}" var="type">--%>
				<%--<label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeCheckBox(this);"/>${type.name}&nbsp;</label>--%>
				<%--<c:if test="${type.id eq 'Company'}"><c:set var="flag" value="Y"></c:set></c:if>--%>
			<%--</c:forEach>&nbsp;&nbsp;--%>
			<%--</c:if>--%>

	  	<%--<c:if test="${sessionScope.userListScope != GlobalConstant.USER_LIST_LOCAL}">--%>
	 	 	<%--<label  id="derateFlagLabel" style="cursor: pointer;display: none;">--%>
	 	 	<%--&#12288;<input type="checkbox" id="derateFlag"  <c:if test="${param.derateFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if> name="derateFlag" value="${GlobalConstant.FLAG_Y}">--%>
	 	 	<%--&nbsp;只显示减免</label>--%>
	  	<%--</c:if>--%>
		<%--<br/>姓&#12288;&#12288;名：<input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px;"/>&#12288;--%>
		<%--证&nbsp;件&nbsp;号&nbsp;：<input type="text" name="idNo" value="${param.idNo}" class="input" style="width: 135px;"/>&#12288;--%>
		<%--是否为应届生：--%>
		<%--<select  class="select" name="isYearGraduate" style="width: 106px">--%>
				<%--<option value="">请选择</option>--%>
				<%--<option  <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_Y}">selected="selected"</c:if> value="${GlobalConstant.FLAG_Y}">是</option>--%>
				<%--<option <c:if test="${param.isYearGraduate eq GlobalConstant.FLAG_N}">selected="selected"</c:if>  value="${GlobalConstant.FLAG_N}">否</option>--%>
		<%--</select>&#12288;--%>
		<%--<input class="btn_green" type="button" value="查询" onclick="toPage();"/><br>--%>
	    <%--<input class="btn_green" type="button" value="导出花名册" onclick="exportExcel();"/>&#12288;--%>
	    <%--<c:if test="${sessionScope.userListScope!=GlobalConstant.RES_ROLE_SCOPE_SCHOOL}">--%>
    		<%--<input class="btn_green" type="button" value="国家医师协会名册" onclick="exportForDetail();"/>--%>
    	<%--</c:if>--%>
		<%--<c:if test="${sessionScope.userListScope eq GlobalConstant.USER_LIST_LOCAL}">--%>
			<%--&#12288;--%>
			<%--<input class="btn_green" type="button" value="在培医师花名册" onclick="exTrainingDocPdf();"/>--%>
		<%--</c:if>--%>
	    <%--</form>--%>
    </div>
   <div id="doctorListZi" style="padding-bottom: 20px;">
    </div>
</div>
<div style="display: none;">
	<select id="ChineseMedicine_select">
		<c:forEach items="${dictTypeEnumChineseMedicineList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="TCMGeneral_select">
		<c:forEach items="${dictTypeEnumTCMGeneralList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="TCMAssiGeneral_select">
		<c:forEach items="${dictTypeEnumTCMAssiGeneralList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
</div>
<div>
<c:forEach items="${orgFlowList}" var="flow">
	<input type="hidden" id="${flow}" value="${flow}"/>		
</c:forEach>

</div>