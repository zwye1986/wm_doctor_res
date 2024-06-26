<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="d" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>江苏省住院医师规范化培训管理平台</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<style>
	.bg-grey {
		background-color: lightgrey;
	}
</style>
<script type="text/javascript">
function saveCoopBase(){
	jboxPostJson("<s:url value='/jsres/base/saveCoopBase?sessionNumber=${sessionNumber}'/>" , JSON.stringify(curJoint) , function(resp){
		jboxTip(resp);
		window.parent.coopBaseInfo("${sessionNumber}");
		jboxClose();
	} , null , false);
}
var curJoint = [];
var unjoint = [];
var otherJoint = [];
$(function() {
	<c:if test="${jointCount gt 0}">
	<c:forEach items="${jointOrgList}" var="curJointOrg" end="${jointCount - 1}">
		var curJointFlow = '${curJointOrg.jointOrgFlow}';
		var curJointName = '${curJointOrg.jointOrgName}';
		var curOrgName = '${curJointOrg.orgName}';
		var curOrgFlow = '${curJointOrg.orgFlow}';
		curJoint.push({jointOrgFlow: curJointFlow, jointOrgName: curJointName, orgName: curOrgName, orgFlow: curOrgFlow});
	</c:forEach>
	</c:if>
	<c:forEach items="${jointOrgList}" var="otherJointOrg" begin="${jointCount}">
		var otherJointFlow = '${otherJointOrg.jointOrgFlow}';
		var otherJointName = '${otherJointOrg.jointOrgName}';
		var otherOrgName = '${otherJointOrg.orgName}';
		otherJoint.push({jointOrgFlow: otherJointFlow, jointOrgName: otherJointName, orgName: otherOrgName});
	</c:forEach>
	<c:forEach items="${unjointOrgList}" var="unjointOrg">
		var unjointFlow = '${unjointOrg.orgFlow}';
		var unjointName = '${unjointOrg.orgName}';
		unjoint.push({jointOrgFlow: unjointFlow, jointOrgName: unjointName});
	</c:forEach>

});

function localSearchJointOrg(searchStr) {
	var curSearch = curJoint;
	var otherSearch = otherJoint;
	var unSearch = unjoint;
	if(searchStr) {
		curSearch = curSearch.filter(function(item) {
			return item.jointOrgName.indexOf(searchStr) > -1;
		});
		otherSearch = otherSearch.filter(function(item) {
			return item.jointOrgName.indexOf(searchStr) > -1;
		});
		unSearch = unSearch.filter(function(item) {
			return item.jointOrgName.indexOf(searchStr) > -1;
		});
	}

	var html = "";
	curSearch.forEach(function(item) {
		var ht = $("#coopBaseTemplate tbody").html();
		ht = ht.replace("\{0}", item.jointOrgFlow);
		ht = ht.replace("\{1}", item.jointOrgName);
		ht = ht.replace("\{2}", "已关联");
		ht = ht.replace("\{3}", item.orgName);
		ht = ht.replace("\{4}", "<input type='button' value='删除' class='btn btn_red' onclick='deleteJoint()' />");
		ht = ht.replace("\{5}", "bg-grey");
		html += ht;
	});

	unSearch.forEach(function(item) {
		var ht = $("#coopBaseTemplate tbody").html();
		ht = ht.replace("\{0}", item.jointOrgFlow);
		ht = ht.replace("\{1}", item.jointOrgName);
		ht = ht.replace("\{2}", "未关联");
		ht = ht.replace("\{3}", '');
		ht = ht.replace("\{4}", "<input type='button' value='关联' class='btn btn_green' onclick='addJoint()' />");
		ht = ht.replace("\{5}", "");
		html += ht;
	});

	otherSearch.forEach(function(item) {
		var ht = $("#coopBaseTemplate tbody").html();
		ht = ht.replace("\{0}", item.jointOrgFlow);
		ht = ht.replace("\{1}", item.jointOrgName);
		ht = ht.replace("\{2}", "已关联");
		ht = ht.replace("\{3}", item.orgName);
		ht = ht.replace("\{4}", "");
		ht = ht.replace("\{5}", "bg-grey");
		html += ht;
	});

	$("#coopBaseList").html(html);
}

function deleteJoint(e) {
	var target = (e || event).target;
	var flow = $(target).parents("tr").attr("data");
	for(var i = 0; i < curJoint.length; i++) {
		if(curJoint[i].jointOrgFlow == flow) {
			unjoint.unshift(curJoint[i]);
			curJoint.splice(i, 1);
		}
	}
	localSearchJointOrg();
}

function addJoint(e) {
	var target = (e || event).target;
	var flow = $(target).parents("tr").attr("data");
	for(var i = 0; i < unjoint.length; i++) {
		if(unjoint[i].jointOrgFlow == flow) {
			unjoint[i].orgFlow = $("#orgFlow").val();
			unjoint[i].orgName = $("#orgName").val();
			curJoint.unshift(unjoint[i]);
			delete unjoint.splice(i, 1);
		}
	}
	localSearchJointOrg();
}
</script>
</head>
<body>
<div class="">
	<h2>${sessionNumber}年</h2>
	<input type="hidden" id="orgFlow" value="${curOrgFlow}" />
	<input type="hidden" id="orgName" value="${curOrgName}" />
	<input type="hidden" id="sessionNumber" value="${sessionNumber}" />
</div>
<div class="div_search">
<form id="coopBaseForm">
		<label class="label">协同单位名称：</label>
		<input type="text" name="jointOrgName" id="jointOrgName" class="input_text input" style="width: 150px" />
		<input type="button" onclick='localSearchJointOrg($("#jointOrgName").val())' value="查询" class='btn btn_green' />
</form>
 </div>
<div class="div_table" style="height: 500px;overflow: auto">
	<table border="0" cellpadding="0" cellspacing="0" class="base_info">
		<thead>
			<colgroup>
				<col width="25%">
				<col width="25%">
				<col width="25%">
				<col width="25%">
			</colgroup>
			<tr>
				<th style="text-align: center">协同单位名称</th>
				<th style="text-align: center">状态</th>
				<th style="text-align: center">关联主基地</th>
				<th style="text-align: center">操作</th>
			</tr>
		</thead>
		<tbody id="coopBaseList">
			<c:if test="${jointCount gt 0}">
				<c:forEach items="${jointOrgList}" var="curJointOrg" end="${jointCount - 1}">
					<tr id="" class="bg-grey" data="${curJointOrg.jointOrgFlow}">
						<td>${curJointOrg.jointOrgName}</td>
						<td>已关联</td>
						<td>${curJointOrg.orgName}</td>
						<td><input type="button" class="btn btn_red" value="删除" onclick="deleteJoint()" ></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:forEach items="${unjointOrgList}" var="unjointOrg">
				<tr id="" class="" data="${unjointOrg.orgFlow}">
					<td>${unjointOrg.orgName}</td>
					<td>未关联</td>
					<td></td>
					<td><input type="button" class="btn btn_green" onclick="addJoint()" value="关联"></td>
				</tr>
			</c:forEach>
			<c:forEach items="${jointOrgList}" var="otherJointOrg" begin="${jointCount}">
				<tr id="" class="bg-grey" data="${otherJointOrg.jointOrgFlow}">
					<td>${otherJointOrg.jointOrgName}</td>
					<td>已关联</td>
					<td>${otherJointOrg.orgName}</td>
					<td></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div class="btn_info">
	<input class="btn_green" onclick="saveCoopBase()" type="button" value="保&#12288;存"/>
	<input class="btn_grey" onclick="jboxClose()" type="button" value="关&#12288;闭"/>
</div>
<table id="coopBaseTemplate" style="display: none">
	<tbody>
	<tr id="" class="{5}" data="{0}">
		<td>{1}</td>
		<td>{2}</td>
		<td>{3}</td>
		<td>{4}</td>
	</tr>
	</tbody>
</table>
</body>
</html>