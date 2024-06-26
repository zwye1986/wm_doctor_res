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
</style>

<script type="text/javascript">

	$(document).ready(function(){
//		changeSpeId();
		toPage(1);
	});
	<%--function changeSpeId()--%>
	<%--{--%>
		<%--var typeId=$("#doctorCategoryId").val();--%>
		<%--$("select[name=trainingSpeId] option").remove();--%>
		<%--if(typeId&&typeId !='${recDocCategoryEnumDoctor.id}'){--%>
			<%--$("#"+typeId+"_select").find("option").each(function(){--%>
				<%--$(this).clone().appendTo($("#trainingSpeId"));--%>
			<%--});--%>
		<%--}else {--%>
			<%--$("#DoctorTrainingSpe_select").find("option").each(function(){--%>
				<%--$(this).clone().appendTo($("#trainingSpeId"));--%>
			<%--});--%>
		<%--}--%>
	<%--}--%>

	function search(){

		jboxPostLoad("userList" ,"<s:url value='/sch/manual/userList'/>" ,$("#doctorSearchForm").serialize() , true);
	}
	function toPage(page) {
		if(page){
			$("#currentPage").val(page);
		}
		search();
	}
	function oper(obj, userFlow){
		var cfgValue = "${GlobalConstant.FLAG_N}";
		if($(obj).attr("checked")=="checked") {
			cfgValue = "${GlobalConstant.FLAG_Y}";
		}
		$("#cfgCode").val("stu_manual_"+userFlow);
		$("#cfgValue").val(cfgValue);
		$("#desc").val("是否开放学员手册");
		save(userFlow);
	}
	function save(userFlow){
		var url = "<s:url value='/sys/cfg/saveOne'/>";
		jboxPost(url, $($('#sysCfgForm').html().htmlFormart("stu_manual_"+userFlow)).serialize(), function(resp){
			if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
				jboxTip("操作成功！");
			}else{
				jboxTip("操作失败！");
			}
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
	function choose(){
		var length=$("input[name='choose']:checked").length;
		var recordStatus='Y';
		if(length>0){
			$("input[name='userFlows']").attr("checked",true);
			recordStatus='Y';
		}else{
			$("input[name='userFlows']").attr("checked",false);
			recordStatus='N';
		}
		var userFlows="";
		$("input[name='userFlows']").each(function(i){
			userFlows +="&userFlows="+$(this).val();
		});

		var url = "<s:url value='/sch/manual/save'/>?recordStatus="+recordStatus+userFlows;
		jboxPost(url, null, function(resp){
			if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
				jboxTip("操作成功！");
			}else{
				jboxTip("操作失败！");
			}
		}, null, false);
	}
	function showSendSchool(obj)
	{

		var v="";
		$(".docType:checked").each(function(){
			v+=$(this).val();
		});
		if(v=='Graduate') {
				$(".Graduate").show();
				$(".GraduateNext").hide();
		}else{
			$(".Graduate").hide();
			$(".GraduateNext").show();
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
			<table class="basic" style="width:100%;margin-top: 10px;">
				<tr>
					<th>
						培训基地：
					</th>
					<td>
						<select name="orgFlow"  id="orgFlow" class="xlselect" onchange="" style="width: 150px;">
							<option/>
							<c:forEach items="${applicationScope.sysOrgList}" var="org">
								<option  value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
							</c:forEach>
						</select>
					</td>
					<%--<td>--%>
						<%--培训类别：--%>
					<%--</td>--%>
					<%--<td>--%>
						<%--<select name="doctorCategoryId" id="doctorCategoryId" class="xlselect" style="width: 150px;" onchange="changeSpeId();">--%>
							<%--<c:forEach items="${recDocCategoryEnumList}" var="category">--%>
								<%--<c:set var="res_doctor_category_key" value="res_doctor_category_${category.id}"/>--%>
								<%--<c:if test="${sysCfgMap[res_doctor_category_key]==GlobalConstant.FLAG_Y}">--%>
									<%--<option value="${category.id}" ${doctor.doctorCategoryId eq category.id?'selected':''}>${category.name}</option>--%>
								<%--</c:if>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					<%--</td>--%>
					<%--<td>培训专业：</td>--%>
					<%--<td>--%>
						<%--<select name="trainingSpeId" id="trainingSpeId"class="xlselect" style="width: 150px;">--%>
							<%--<option>请选择</option>--%>
							<%--<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
								<%--<option value="${dict.dictId}">${dict.dictName}</option>--%>
							<%--</c:forEach>--%>
						<%--</select>--%>
					<%--</td>--%>
					<th>
						年级：
					</th>
					<td>
						<select name="sessionNumber"class="xlselect" onchange="" style="width: 150px;">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumDoctorSessionNumberList}" var="dict">
								<option value="${dict.dictName}">${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th>
						姓&#12288;&#12288;名：
					</th>
					<td>
						<input type="text" name="userName" class="xltext"  style="width: 150px;">
					</td>
					<th>
						证件号码：
					</th>
					<td>
						<input type="text" name="idNo" class="xltext" style="width:150px;">
					</td>
				</tr>
				<tr>
					<th>人员类型：</th>
					<td colspan="3">
						<c:forEach items="${dictTypeEnumDoctorTypeList}" var="type">
							<label><input name="datas" class="docType" type="checkbox" checked onclick="showSendSchool(this);" value="${type.dictId}"/>${type.dictName}&nbsp;</label>
						</c:forEach>
					</td>
					<th class="Graduate" style="display: none">派送学校：</th>
					<td class="Graduate" style="display: none">
						<select name="workOrgId" id="workOrgId"  class="xlselect" style="width:158px;">
							<option value="">请选择</option>
							<c:forEach items="${dictTypeEnumSendSchoolList}" var="dict">
								<option value="${dict.dictId}" >${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<th class="GraduateNext" ></th>
					<td class="GraduateNext" >
					</td>
					<th></th>
					<td>
						<input type="button" value="查&#12288;询" class="search" onclick="toPage(1);"/>
					</td>
				</tr>
			</table>
		</form>
		<div id="userList">
			
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