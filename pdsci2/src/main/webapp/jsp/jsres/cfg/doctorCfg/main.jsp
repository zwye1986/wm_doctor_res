<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

	<style type="text/css">
		.selSysDept span{color: red;}
		.searchTable{
			border: 0px;
		}
		.searchTable tbody td{
			border: 0px;
			background-color: white;
			color: #575656;
		}
		.searchTable tbody th{
			border: 0px;
			background: white;
			color: #575656;
		}
		.doctorTypeDiv {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeLabel {
			border: 0px;
			float: left;
			width: 96px;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
		.doctorTypeContent {
			border: 0px;
			float: left;
			width: auto;
			line-height: 35px;
			height: 35px;
			text-align: right;
		}
	</style>

	<style type="text/css">
		#boxHome .item:HOVER{background-color: #eee;}
		/*#boxHome .item{line-height: normal;  vertical-align:middle; }*/
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			toPage(1);
		});

		function search(){
			if(!$("#orgFlow").val()){
				$("#orgSel").val("");
			}
			if($("#ifOpen").find("option:selected ").val()){
				if($("input[name='powerTypeId']:checked").size()<1){
					jboxTip("请选择权限类型！");
					return false;
				}
			}
			var orgName = $("#orgSel").val();
			if(!orgName){
				$("#orgFlow").val("");
			}
			jboxPostLoad("userList" ,"<s:url value='/jsres/doctorCfg/userList'/>?isQuery=${param.isQuery}" ,$("#doctorSearchForm").serialize() , true);
		}
		function clearOrgFlow(){
			$("#orgFlow").val("");
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}
		//导出学员信息
		function exportDoctor(){
			var orgName = $("#orgSel").val();
			if(!orgName){
				$("#orgFlow").val("");
			}
			jboxExp($("#doctorSearchForm"),"<s:url value='/jsres/doctorCfg/exportDoctor'/>");
		}

		function importTime()
		{
			jboxOpen("<s:url value='/jsres/doctorCfg/importTime'/>","导入审核期限配置",500,200,true);
		}
		function changeTime(userFlow,key)
		{
			jboxOpen("<s:url value='/jsres/doctorCfg/changeTime'/>?key="+key+"&userFlow="+userFlow,"编辑审核时间区间",500,150,true);
		}
		function oper(obj, userFlow,cfg){
			var cfgValue = "${GlobalConstant.FLAG_N}";
			var login = $("#jsres_doctor_app_login_"+userFlow);
			var menu = $("#jsres_doctor_app_menu_"+userFlow);
			if($(obj).attr("checked")=="checked") {
				cfgValue = "${GlobalConstant.FLAG_Y}";
			}

			if(login.attr("checked")=="checked") {
				menu.removeAttr("disabled");
			}else{
				if(menu.attr("checked")=="checked") {
					jboxInfo("请先取消app付费功能菜单权限！");
					login.attr("checked", true);
					return false;
				}
				menu.attr("disabled", true);
			}
			$("#cfgCode").val(cfg+userFlow);
			$("#cfgValue").val(cfgValue);
			if(cfg=="jsres_doctor_app_login_"){
				$("#desc").val("是否开放学员app登录权限");
			}else if(cfg=="jsres_doctor_app_menu_"){
				$("#desc").val("是否开放学员付费菜单");
			}else if(cfg=="jsres_doctor_exam_"){
				$("#desc").val("是否开放学员出科考核表");
			}else if(cfg=="jsres_doctor_manual_"){
				$("#desc").val("是否开放学员手册");
			}else if(cfg=="jsres_doctor_graduation_exam_"){
				$("#desc").val("是否开放学员结业理论模拟考核");
			}else if(cfg=="jsres_doctor_activity_"){
				$("#desc").val("是否开放学员教学活动功能");
			}else if(cfg=="jsres_doctor_attendance_"){
				$("#desc").val("是否开放学员考勤功能");
			}
			save(userFlow,cfg,cfgValue);
		}

		function save(userFlow,cfg,cfgValue){
			var url = "<s:url value='/jsres/powerCfg/saveOne'/>?userFlow="+userFlow;
			var title = "确认开放权限？";
			if(cfgValue == "N"){
                title = "确认取消权限？";
			}
            jboxButtonConfirm(title,"确认","取消", function () {
				jboxPost(url, $($('#sysCfgForm').html().htmlFormart(cfg+userFlow)).serialize(), function(resp){
					if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
						jboxTip("操作成功！");
						search();
						if(cfg=="jsres_doctor_app_menu_") {
							var menu = $("#jsres_doctor_app_menu_" + userFlow);
							var time = $("#jsres_doctor_data_time_" + userFlow);
							if (menu.attr("checked") == "checked") {
								var c = "changeTime('" + userFlow + "','jsres_doctor_data_time_" + userFlow + "')";
								time.attr("onclick", c);
							} else {
								time.removeAttr("onclick");
							}
						}
					}else{
						jboxTip("操作失败！");
					}
					initCheck();
				}, null, false);
			},function(){
				search();
			},300);
		}

		String.prototype.htmlFormart = function(){
			var html = this;
			for(var index in arguments){
				var reg = new RegExp('\\{'+index+'\\}','g');
				html = html.replace(reg,arguments[index]);
			}
			return html;
		};
		function choose(type,types,cfg){
			var length=$("input[name='"+type+"']:checked").length;
			var f=true;
			if(type=="appLogin"&&length<=0) {
				$("input[name='"+types+"']").each(function(i){
					var id="jsres_doctor_app_menu_"+$(this).val();
					if($("#"+id).attr("checked")=="checked") {
						f=false;
					}
				});
				if(!f){
					$("input[name='"+type+"']").attr("checked",true);
					jboxInfo("请先取消app付费功能菜单权限！");
					return false;
				}
			}
			if(type=="appMenu"&&length>0) {
				$("input[name='"+types+"']").each(function(i){
					var id="jsres_doctor_app_login_"+$(this).val();
					if($("#"+id).attr("checked")!="checked") {
						f=false;
					}
				});
				if(!f){
					$("input[name='"+type+"']").attr("checked",false);
					jboxInfo("请先开通app登录权限！");
					return false;
				}
			}

			if(f){
				var recordStatus='Y';
				if(length>0){
					$("input[name='"+types+"']").attr("checked",true);
					if(type=="appLogin") {
						$("input[name='appMenus']").removeAttr("disabled");
					}
					recordStatus='Y';
				}else{
					$("input[name='"+types+"']").attr("checked",false);
					if(type=="appLogin") {
						$("input[name='appMenus']").attr("disabled",true);
					}
					recordStatus='N';
				}
				var userFlows="";
				/*$("input[name='"+types+"']").each(function(i){
					userFlows +="&userFlows="+$(this).val();
				});*/
				var count = 0;
                var title = "确认开放权限？";
				if(recordStatus == "N"){
                    title = "确认取消权限？";
                    $("input[name='"+types+"']").each(function(i){
                        userFlows +="&userFlows="+$(this).val();
                        count += 1;
                    });
				}else{
                    $("input[name='"+cfg+"']").each(function(i){
                        userFlows +="&userFlows="+$(this).val();
                        count += 1;
                    });
				}
				var url = "<s:url value='/jsres/doctorCfg/save'/>?recordStatus="+recordStatus+"&cfg="+cfg+userFlows;
                jboxButtonConfirm("共"+count+"条数据，"+title,"确认","取消", function () {
					jboxPost(url, null, function(resp){
						if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
							search();
							jboxTip("操作成功！");
						}else{
							jboxTip("操作失败！");
						}
					}, null, false);
				},function () {
                    search();
                },300)
			}
		}

		function chooseAll(type,types,cfg){
			var length = $("input[name='activitys']:checked").length;
			length += $("input[name='attendances']:checked").length;
			length += $("input[name='appMenus']:checked").length;
			debugger
            var recordStatus='Y';
            if(length>0){
                $("input[name='"+types+"']").attr("checked",true);
                if(type=="appLogin") {
                    $("input[name='appMenus']").removeAttr("disabled");
                }
                recordStatus = 'Y';
            }else{
                $("input[name='"+types+"']").attr("checked",false);
                if(type=="appLogin") {
                    $("input[name='appMenus']").attr("disabled",true);
                }
                recordStatus = 'N';
            }
            var userFlows="";
            var count = 0;
            var title = "确认开放权限？";
            if(recordStatus == "N"){
                title = "确认取消权限？";
                $("input[name='"+types+"']").each(function(i){
                    userFlows +="&userFlows="+$(this).val();
                    count += 1;
                });
            }else{
                $("input[name='"+cfg+"']").each(function(i){
                    userFlows +="&userFlows="+$(this).val();
                    count += 1;
                });
            }
            var url = "<s:url value='/jsres/doctorCfg/saveAll'/>?recordStatus="+recordStatus+"&cfg="+cfg+userFlows;
            jboxButtonConfirm("共"+count+"条数据，"+title,"确认","取消", function () {
                jboxPost(url, null, function(resp){
                    if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                        search();
                        jboxTip("操作成功！");
                    }else{
                        jboxTip("操作失败！");
                    }
                }, null, false);
            },function () {
                search();
            },300)
		}

		function updateSubmit() {
            $("input[name='isSubmitId']").prop("checked",true);
            var userFlows="";
            var count = 0;
            $("input[name='isSubmitId']").each(function(i){
                if(i == 0){
                    userFlows += $(this).val();
                    count += 1;
				}else {
                    userFlows += "&userFlows=" + $(this).val();
                    count += 1;
                }
            });
            var url = "<s:url value='/jsres/powerCfg/updateSubmit'/>?userFlows="+userFlows;
            jboxConfirm("共"+count+"条数据，确认提交？", function () {
                jboxPost(url, null, function (resp) {
                    if ("${GlobalConstant.OPRE_FAIL}" == resp) {
                        jboxTip("操作失败！");
                    }else{
                        search();
                        jboxTip(resp);
                    }
                }, null, true);
            });
        }

        function updateSubmitOne(userFlow) {
            var userFlows = userFlow;
            var url = "<s:url value='/jsres/powerCfg/updateSubmit'/>?userFlows="+userFlows;
            jboxConfirm("确认提交？", function () {
                jboxPost(url, null, function (resp) {
                    debugger
                    if ("${GlobalConstant.OPRE_FAIL}" == resp) {
                        jboxTip("操作失败！");
                    }else{
                        search();
                        jboxTip(resp);
                    }
                }, null, true);
            });
        }
		
		function initCheck()
		{


			var cl=$("input[name='appLogins']:checked").length;
			var al=$("input[name='appLogins']").length;
			if(cl!= 0&&cl==al)
			{
				$("input[name='appLogin']").attr("checked",true);
			}else{
				$("input[name='appLogin']").attr("checked",false);
			}
			var cl=$("input[name='appMenus']:checked").length;
			var al=$("input[name='appMenus']").length;
			if(cl!= 0&&cl==al)
			{
				$("input[name='appMenu']").attr("checked",true);
			}else{
				$("input[name='appMenu']").attr("checked",false);
			}
			var cl=$("input[name='attendances']:checked").length;
			var al=$("input[name='attendances']").length;
			if(cl!= 0&&cl==al)
			{
				$("input[name='attendance']").attr("checked",true);
			}else{
				$("input[name='attendance']").attr("checked",false);
			}
			var cl=$("input[name='activitys']:checked").length;
			var al=$("input[name='activitys']").length;
			if(cl!= 0&&cl==al)
			{
				$("input[name='activity']").attr("checked",true);
			}else{
				$("input[name='activity']").attr("checked",false);
			}
			var cl=$("input[name='outProcessExams']:checked").length;
			var al=$("input[name='outProcessExams']").length;
			if(cl!= 0&&cl==al)
			{
				$("input[name='outProcessExam']").attr("checked",true);
			}else{
				$("input[name='outProcessExam']").attr("checked",false);
			}
			var cl=$("input[name='outGraduationExams']:checked").length;
			var al=$("input[name='outGraduationExams']").length;
			if(cl!= 0&&cl==al)
			{
				$("input[name='outGraduationExam']").attr("checked",true);
			}else{
				$("input[name='outGraduationExam']").attr("checked",false);
			}
			var cl=$("input[name='schManuals']:checked").length;
			var al=$("input[name='schManuals']").length;
			if(cl!= 0&&cl==al)
			{
				$("input[name='schManual']").attr("checked",true);
			}else{
				$("input[name='schManual']").attr("checked",false);
			}
            if ($("input[name='activity']").is(':checked') && $("input[name='attendance']").is(':checked') && $("input[name='appMenu']").is(':checked')) {
                $("input[name='all']").attr("checked",true);
            } else {
                $("input[name='all']").attr("checked",false);
            }
		}
		function showSendSchool(obj)
		{

			var v="";
			$(".docType:checked").each(function(){
				v+=$(this).val();
			});
			// if(v=='Graduate') {
			// 	$(".Graduate").show();
			// 	$("#workOrgId").find("option[value='']").attr("selected",true);
			// 	$(".GraduateNext").hide();
			// }else{
			// 	$(".Graduate").hide();
			// 	$(".GraduateNext").show();
			// }
		}
		/**
		 *模糊查询加载
		 */
		(function($){
			$.fn.likeSearchInit = function(option){
				option.clickActive = option.clickActive || null;

				var searchInput = this;
				searchInput.on("keyup focus",function(){
					$("#boxHome").show();
					if($.trim(this.value)){
						$("#boxHome .item").hide();
						var items = $("#boxHome .item[value*='"+this.value+"']").show();
						if(!items){
							$("#boxHome").hide();
						}
					}else{
						$("#boxHome .item").show();
					}
				});
				searchInput.on("blur",function(){
					if(!$("#boxHome.on").length){
						$("#boxHome").hide();
					}
				});
				$("#boxHome").on("mouseenter mouseleave",function(){
					$(this).toggleClass("on");
				});
				$("#boxHome .item").click(function(){
					$("#boxHome").hide();
					var value = $(this).attr("value");
					$("#itemName").val(value);
					searchInput.val(value);
					if(option.clickActive){
						option['clickActive']($(this).attr("flow"));
					}
				});
			};
		})(jQuery);
		//======================================
		//页面加载完成时调用
		$(function(){
			$("#orgSel").likeSearchInit({
				clickActive:function(flow){
					$("#orgFlow").val(flow);
				}
			});
		});
		function changeOpen(vari){
			if(!$(vari).val()){
				$("input[name='powerTypeId']").attr({"disabled":"disabled"}).removeAttr("checked");
				$("#powerTypes").hide();
			}else {
				$("input[name='powerTypeId']").removeAttr("disabled");
				$("#powerTypes").show();
			}
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<form id="sysCfgForm" >
			<input type="hidden" id="cfgCode" name="cfgCode"/>
			<input type="hidden" id="cfgValue" name="{0}"/>
			<input type="hidden" id="wsId" value="${GlobalConstant.RES_WS_ID }" name="{0}_ws_id"/>
			<input type="hidden" id="desc" name="{0}_desc">
		</form>
		<form  id="doctorSearchForm" method="post">
			<input id="currentPage" type="hidden" name="currentPage" value=""/>
			<div class="queryDiv">
				<div class="inputDiv">
					<label class="qlable">培训基地：</label>
					<input id="orgSel" onchange="clearOrgFlow();" class="toggleView qtext"  type="text" name="orgName" value="${param.orgName}" autocomplete="off"/>
					<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top: 36px; left:100px;">
						<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;
							  vertical-align:middle; background-color: white;min-width: 156px;border-top: none;position: relative;display: none;">
							<c:forEach items="${applicationScope.sysOrgList}" var="org">
								<p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="line-height: 20px; padding:10px 0;cursor: default; text-align: left;">${org.orgName}</p>
							</c:forEach>
						</div>
					</div>
					<input type="text" name="orgFlow" id="orgFlow" value="" style="display: none;"/>
				</div>
				<div class="inputDiv">
					<label class="qlable">年&#12288;&#12288;级：</label>
					<select name="sessionNumber"class="qselect" onchange="">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
							<option value="${dict.dictName}">${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					<label class="qlable">姓&#12288;&#12288;名：</label>
					<input type="text" name="userName" class="qtext"  >
				</div>
				<div class="inputDiv">
					<label class="qlable">证件号码：</label>
					<input type="text" name="idNo" class="qtext" qtext>
				</div>
				<div class="inputDiv">
					<label class="qlable">权限筛选：</label>
					<select name="ifOpen" id="ifOpen" class="qselect"  onchange="changeOpen(this);">
						<option value="">全部</option>
						<option value="Y">已开通</option>
						<option value="N">未开通</option>
					</select>
				</div>
				<div class="inputDiv Graduate" >
					<label class="qlable">派送学校：</label>
					<select name="workOrgId" id="workOrgId"  class="qselect" qtext>
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
							<option value="${dict.dictId}" >${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="qcheckboxDiv" id="powerTypes" style="display: none;min-width: 365px; text-align: right;">
					<label class="qlable">权限类型：</label>
					<input name="powerTypeId" disabled type="checkbox" value="APP"/>APP&nbsp;
					<input name="powerTypeId" disabled type="checkbox" value="menu"/>付费功能 &nbsp;
					<input name="powerTypeId" disabled type="checkbox" value="ckks"/>出科考试&nbsp;
					<input name="powerTypeId" disabled type="checkbox" value="pxsc"/>培训手册&nbsp;
				</div>
				<div class="inputDiv">
					<label class="qlable">登&ensp;录&ensp;名：</label>
					<input type="text" name="userCode" class="qtext">
				</div>
				<div class="inputDiv">
					<label class="qlable">培养年限：</label>
					<select name="trainingYears" class="qselect">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumTrainingYearsList}" var="dict">
							<option value="${dict.dictId}" ${param.trainingYears eq dict.dictId?'selected':''}>${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					<label class="qlable">审核状态：</label>
					<select name="checkStatusId" class="qselect">
						<option value="">请选择</option>
						<option value="NotSubmit" ${param.checkStatusId eq 'NotSubmit'?'selected':''}>未提交</option>
						<option value="Passing" ${param.checkStatusId eq 'Passing'?'selected':''}>待审核</option>
						<option value="Passed" ${param.checkStatusId eq 'Passed'?'selected':''}>审核通过</option>
						<option value="UnPassed" ${param.checkStatusId eq 'UnPassed'?'selected':''}>审核不通过</option>
					</select>
				</div>
				<div class="doctorTypeDiv">
					<label class="doctorTypeLabel">审核区间：</label>
					<input  id="startTime" name="startTime" class="qtext" type="text" value="${param.startTime}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					至
					<input  id="endTime" name="endTime" class="qtext" type="text" value="${param.endTime}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
				</div>

                <div class="doctorTypeDiv" style="max-width: 420px;min-width: 235px;">
                    <label class="qlable">培训类别：</label>
                    <select name="trainingTypeId" id="trainingTypeId" class="qselect"
							onchange="changeTrainSpes('1')" >
                        <option value="">请选择</option>
                        <c:forEach items="${trainCategoryEnumList}" var="trainCategory">
                            <option value="${trainCategory.id}"
                                    <c:if test="${param.trainingTypeId==trainCategory.id}">selected="selected"</c:if>
                                    <%--<c:if test="${trainCategoryEnumWMFirst.id ==trainCategory.id}">style="display: none;"</c:if>--%>
                                    <%--<c:if test="${trainCategoryEnumWMSecond.id ==trainCategory.id}">style="display: none;"</c:if>--%>
                            >${trainCategory.name}</option>
                        </c:forEach>
                    </select>
                </div>
				<div class="doctorTypeDiv">
					<div class="doctorTypeLabel">人员类型：</div>
					<div class="doctorTypeContent">
						<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
							<label><input name="datas" class="docType" type="checkbox" checked onclick="showSendSchool(this);" value="${type.dictId}"/>${type.dictName}&nbsp;</label>
						</c:forEach>
					</div>
				</div>
				<div class="qcheckboxDiv" style="min-width: 400px;padding-left: 23px;">
					<input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
					<input type="button" value="一 键 提 交" class="searchInput" onclick="updateSubmit();"/>
					<c:if test="${empty param.isQuery}">
						<input type="button" value="导入时间配置" class="searchInput" onclick="importTime();"/>
						<input type="button" value="导&#12288;出" class="searchInput" onclick="exportDoctor()">
					</c:if>
				</div>
			</div>
		</form>
		<div id="userList" class="resultDiv">

		</div>
	</div>
	<div style="display: none;">

		<c:forEach items="${recDocCategoryEnumList}" var="category">
			<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id}"/>
			<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">
				<select id="${category.id}_select">
					<option>请选择</option>
					<c:set var="dictName" value="dictTypeEnum${category.id }List" />
					<c:forEach items="${applicationScope[dictName]}" var="trainSpe">
						<option value="${trainSpe.dictId }">${trainSpe.dictName }</option>
					</c:forEach>
				</select>
			</c:if>
		</c:forEach>

		<select id="DoctorTrainingSpe_select">
			<option>请选择</option>
			<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
				<option value="${dict.dictId}">${dict.dictName}</option>
			</c:forEach>
		</select>
	</div>
</div>
</body>
</html>