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
			$("#forGraduate").hide();
		});

		function search(){
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
			jboxPostLoad("userList" ,"<s:url value='/res/doctorCfg/userList'/>?isQuery=${param.isQuery}" ,$("#doctorSearchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}
		//
		function oper(obj, userFlow,cfg){
			var cfgValue = "${GlobalConstant.FLAG_N}";

			var web = $("#res_doctor_web_"+userFlow);
			var app = $("#res_doctor_app_login_"+userFlow);

			if($(obj).attr("checked")=="checked") {
				cfgValue = "${GlobalConstant.FLAG_Y}";
			}

			if(web.attr("checked")=="checked") {	//如果web权限处于选中状态，则app权限可编辑
				$("#res_doctor_qxqj").show();
				app.removeAttr("disabled");
			}else{
				if(app.attr("checked")=="checked") {
					jboxInfo("请先取消app登录权限！");
					web.attr("checked", true);
					return false;
				}
				app.attr("disabled", true);
				$("#res_doctor_qxqj").hide();
			}
			$("#cfgCode").val(cfg+userFlow);
			$("#cfgValue").val(cfgValue);
			if(cfg=="res_doctor_web_"){
				$("#desc").val("是否开放学员web登录权限");
			}else if(cfg=="res_doctor_app_login_"){
				$("#desc").val("是否开放学员app登录权限");
			}else if(cfg=="res_doctor_ckks_"){
				$("#desc").val("是否开放学员出科考试权限");
			}else if(cfg=="res_doctor_pxsc_"){
				$("#desc").val("是否开放学员手册");
			}else if(cfg=="res_doctor_graduation_exam_"){
				$("#desc").val("是否开放结业模拟考试");
			}


			save(userFlow,cfg);
		}

		function save(userFlow,cfg){
			var url = "<s:url value='/res/powerCfg/saveOne'/>";
			jboxPost(url, $($('#sysCfgForm').html().htmlFormart(cfg+userFlow)).serialize(), function(resp){
				if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
					jboxTip("操作成功！");
				}else{
					jboxTip("操作失败！");
				}
				initCheck();
			}, null, false);
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
			if(type=="webLogin"&&length<=0)
			{
				$("input[name='"+types+"']").each(function(){
					 var id="res_doctor_app_login_"+$(this).val();
					if($("#"+id).attr("checked")=="checked") {
						f=false;
					}
				});
				if(!f){
					$("input[name='"+type+"']").attr("checked",true);
					jboxInfo("请先取消app登录权限！");
					return false;
				}
			}
			if(type=="appLogin"&&length>0)
			{
				$("input[name='"+types+"']").each(function(i){
					 var id="res_doctor_web_"+$(this).val();
					if($("#"+id).attr("checked")!="checked") {
						f=false;
					}
				});
				if(!f){
					$("input[name='"+type+"']").attr("checked",false);
					jboxInfo("请先开通web登录权限！");
					return false;
				}
			}

			if(f){
				var recordStatus='Y';
				if(length>0){
					$("input[name='"+types+"']").attr("checked",true);
					if(type=="webLogin")
					{
						$("input[name='appLogins']").removeAttr("disabled");
						$("#res_doctor_qxqj").show();
					}
					recordStatus='Y';
				}else{
					$("input[name='"+types+"']").attr("checked",false);
					if(type=="webLogin")
					{
						$("input[name='appLogins']").attr("disabled",true);
						$("#res_doctor_qxqj").hide();
					}
					recordStatus='N';
				}
				var userFlows="";
				$("input[name='"+types+"']").each(function(i){
					userFlows +="&userFlows="+$(this).val();
				});
				var url = "<s:url value='/res/doctorCfg/save'/>?recordStatus="+recordStatus+"&cfg="+cfg+userFlows;
				jboxPost(url, null, function(resp){
					if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
						jboxTip("操作成功！");
					}else{
						jboxTip("操作失败！");
					}
				}, null, false);
			}
		}
		function initCheck()
		{

			var cl=$("input[name='webLogins']:checked").length;
			var al=$("input[name='webLogins']").length;
			if(cl!= 0&&cl==al)
			{
				$("input[name='webLogin']").attr("checked",true);
			}else{
				$("input[name='webLogin']").attr("checked",false);
			}


			var cl=$("input[name='appLogins']:checked").length;
			var al=$("input[name='appLogins']").length;
			if(cl!= 0&&cl==al)
			{
				$("input[name='appLogin']").attr("checked",true);
			}else{
				$("input[name='appLogin']").attr("checked",false);
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
		}


		//显示派送学校
		function showSendSchool(obj)
		{
			var v="";
			$(".docType:checked").each(function(){
				v+=$(this).val();
			});
			if(v=='Graduate') {
				$(".Graduate").parent("div").show();
				$(".Graduate").show();
				$("#forGraduate").hide();
				$("#workOrgId").find("option[value='']").attr("selected",true);
				$(".GraduateNext").hide();
			}else{
				$(".Graduate").parent("div").hide();
				$(".Graduate").hide();
				$("#forGraduate").hide();
				$(".GraduateNext").show();
			}
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

		 function changeTrainSpes(){
			var trainCategoryid=$("#doctorCategoryId").val();//取 人员类别ID的值

			if(trainCategoryid==""){
				$("select[name=trainingSpeId] option[value != '']").remove();
				return false;
			}
			$("select[name=trainingSpeId] option[value != '']").remove();
			 if(trainCategoryid=='${recDocCategoryEnumDoctor.id}')
			 {
				 $("#DoctorTrainingSpe_select").find("option").each(function () {//
					 $(this).clone().appendTo($("#trainingSpeId"));
				 });
			 }else {
				 $("#" + trainCategoryid + "_select").find("option").each(function () {//
					 $(this).clone().appendTo($("#trainingSpeId"));
				 });
			 }
			return false;
		}
		//导入
		function showImport() {
			var url = "<s:url value='/res/doctorCfg/showImport'/>";
			typeName = "导入";
			jboxOpen(url, typeName, 550, 260);
		}
		//导出
		function exportPowers(){
			var url="<s:url value='/res/doctorCfg/exportPowers'/>";
			jboxTip("导出中…………");
			jboxExp($("#doctorSearchForm"), url);
			jboxEndLoading();
		}
		function changeOpen(vari){
			if(!$(vari).val()){
				$("input[name='powerTypeId']").attr({"disabled":"disabled"}).removeAttr("checked");
				$("#powerTypes").parent().hide();
				$("#powerTypes").hide();
			}else {
				$("input[name='powerTypeId']").removeAttr("disabled");
				$("#powerTypes").parent().show();
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
					<input id="orgSel" class="toggleView qtext" type="text" name="orgName" value="${param.orgName}" autocomplete="off"/>
					<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top: 36px; left: 98px;">
						<div id="boxHome" style="padding-left:5px;max-height: 250px;overflow: auto;border: 1px #ccc solid;
							  vertical-align:middle; background-color: white;min-width: 156px;border-top: none;position: relative;display: none;">
							<c:forEach items="${applicationScope.sysOrgList}" var="org">
								<p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="text-align:left;line-height: 20px; padding:10px 0;cursor: default; ">${org.orgName}</p>
							</c:forEach>
						</div>
					</div>
					<input type="text" name="orgFlow" id="orgFlow" value="" style="display: none;"/>
				</div>
				<div class="inputDiv">
					<label class="qlable">年&#12288;&#12288;级：</label>
					<select name="sessionNumber"class="qselect">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
							<option value="${dict.dictName}">${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					<label class="qlable">姓&#12288;&#12288;名：</label>
					<input type="text" name="userName" class="qtext">
				</div>
				<div class="inputDiv">
					<label class="qlable">证件号码：</label>
					<input type="text" name="idNo" class="qtext">
				</div>
				<div class="inputDiv">
					<label class="qlable">人员类别：</label>
					<select name="doctorCategoryId" id="doctorCategoryId" class="qselect">
						<option value="">请选择</option>
						<c:forEach items="${recDocCategoryEnumList}" var="category">
							<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id}"/>
							<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">
								<option value="${category.id}" <c:if test="${param.doctorCategoryId==category.id}">selected="selected"</c:if>>${category.name}</option>
							</c:if>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv">
					<label class="qlable">培训专业：</label>
					<select name="trainingSpeId" id="trainingSpeId" class="qselect">
						<option value="">请选择</option>
						<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
							<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="inputDiv" style="display: none;">
					<label class="qlable"><span class="Graduate" style="display: none">派送学校：</span></label>
					<span class="Graduate" style="display: none">
						<select name="workOrgId" id="workOrgId" class="qselect">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
								<option value="${dict.dictId}"<c:if test="${param.workOrgId eq dict.dictId}">selected="selected"</c:if>  >${dict.dictName}</option>
							</c:forEach>
						</select>
					</span>
				</div>
				<div class="doctorTypeDiv">
					<div class="doctorTypeLabel">人员类型：</div>
					<div class="doctorTypeContent">
						<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
							<label><input name="datas" class="docType" type="checkbox" checked onclick="showSendSchool(this);" value="${type.dictId}"/>${type.dictName}&nbsp;</label>
						</c:forEach>
					</div>
				</div>
				<div id="forGraduate" class="inputDiv" style="min-width: 80px;max-width: 80px;">
				</div>
				<div class="inputDiv">
					<label class="qlable">权限筛选：</label>
					<select name="ifOpen" id="ifOpen" class="qselect" onchange="changeOpen(this);">
						<option value="">全部</option>
						<option value="Y">已开通</option>
						<option value="N">未开通</option>
					</select>
				</div>
				<div class="inputDiv" style="min-width: 334px;max-width: 334px;display: none;">
					<span id="powerTypes" style="display: none">
						权限类型：
						<input name="powerTypeId" disabled type="checkbox" value="web"/>web&nbsp;
						<input name="powerTypeId" disabled type="checkbox" value="APP"/>APP&nbsp;
						<input name="powerTypeId" disabled type="checkbox" value="ckks"/>出科考试&nbsp;
						<input name="powerTypeId" disabled type="checkbox" value="pxsc"/>培训手册&nbsp;
					</span>
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
				<div class="lastDiv" style="min-width: 185px;max-width: 185px;">
					<input type="button" value="查&#12288;询" class="searchInput" onclick="toPage(1);"/>
					<%--<input type="button" value="导&#12288;入" class="search" onclick="showImport();"/>--%>
					<c:if test="${empty param.isQuery}">
						<input type="button" value="导&#12288;出" class="searchInput" onclick="exportPowers();"/>
					</c:if>
				</div>
			</div>
		</form>
		<div id="userList">

		</div>
	</div>
	<div style="display: none;">

		<c:forEach items="${recDocCategoryEnumList}" var="category">
			<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id}"/>
			<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">
				<select id="${category.id}_select" name="${category.id}_select" onchange="ChangeTrainingSpes()">
					<c:set var="dictName" value="dictTypeEnum${category.id }List" />
					<c:forEach items="${applicationScope[dictName]}" var="trainSpe">
						<option  value="${trainSpe.dictId }" <c:if test="${param.trainingSpeId eq trainSpe.dictId}">selected="selected"</c:if> >${trainSpe.dictName }</option>
					</c:forEach>
				</select>
			</c:if>
		</c:forEach>

		<select id="DoctorTrainingSpe_select">
			<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
				<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
			</c:forEach>
		</select>
	</div>
</div>
</body>
</html>