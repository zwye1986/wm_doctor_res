<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script type="text/javascript">
function nextOpt(step){
	if(false==$("#projForm").validationEngine("validate")){
		return false;
	}
	var form = $('#projForm');
	form.append('<input type="hidden" name="nextPageName" value="'+step+'"/>');
	$('#nxt').attr({"disabled":"disabled"});
	$('#prev').attr({"disabled":"disabled"});
	jboxStartLoading();
	form.submit();
}

$(document).ready(function(){
	/* if($("#xjzcTb tr").length<=0){
		add('xjzc');
	} */
});

function add(tb){
	$("#noRec").hide();
	$("#"+tb+"Tb").append($("#"+tb+"Template tr:eq(0)").clone());
	//序号
	var length = $("#"+tb+"Tb").children().length; 	
	$("#"+tb+"Tb").children("tr").last().children("td").eq(1).text(length);
}

function delTr(tb){
	//alert("input[name="+tb+"Ids]:checked");
	var checkboxs = $("input[name='"+tb+"Ids']:checked");
	if(checkboxs.length==0){
		jboxTip("请勾选要删除的！");
		return false;
	}
	jboxConfirm("确认删除?",function () {
		var trs = $('#'+tb+'Tb').find(':checkbox:checked');
		$.each(trs , function(i , n){
			$(n).parent('td').parent("tr").remove();
		});
		//删除后序号
		var serial = 0;
		$("."+tb+"Serial").each(function(){
			serial += 1;
			$(this).text(serial);
		});
		//计算行数
		var length = $("#"+tb+"Tb").children().length;
		$("#"+tb+"Num").val(length);
	});
}

function saveRegisterDate(){
	if(!$("#editForm").validationEngine("validate")){
		return;
	}
	var trs = $('#xjzcTb').children();
	var datas = [];
	$.each(trs , function(i , n){
		var registerDate = $(n).find("input[name='registerDate']").val();
		var data = {
			"id":i+1,
			"registerDate":registerDate
		};
		datas.push(data);
	});
	var requestData = {"registerDateList":datas};
	var url = "<s:url value='/gyxjgl/user/saveRegisterDate'/>?userFlow=${param.userFlow}";
	//alert(JSON.stringify(requestData));
	//return false;
	jboxPostJson(url, JSON.stringify(requestData), 
		function(resp){
			if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
				jboxClose();
			}
		}, null, true);
}
</script>
</head>
<body>
<div class="mainright" >
<div class="content">
<form id="editForm">
	<table class="basic" style="width: 100%; margin-top: 10px;">
		<tr>
			<th colspan="5" class="theader">
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png"/>" style="cursor: pointer;" onclick="add('xjzc')"/>&#12288;
					<img title="删除" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" style="cursor: pointer;" onclick="delTr('xjzc');"/>
				</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<td style="font-weight: bold;" width="5%"></td>
			</c:if>
			<td style="font-weight: bold;" width="10%">序号</td>
			<td style="font-weight: bold;" width="85%">注册时间</td>
		</tr>
		<tbody id="xjzcTb">
		<c:forEach var="regDate" items="${registerDateList}">
			<tr>
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
					<td style="text-align: center;padding: 0px;"><input name="xjzcIds" type="checkbox" /></td>
				</c:if>
				<td style="text-align: center;padding: 0px;" class="xjzcSerial">${regDate.id}</td>
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
					<td><input type="text" class="inputText ctime validate[required]" name="registerDate" value="${regDate.registerDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" /></td>
				</c:if>
				<c:if test="${param.view==GlobalConstant.FLAG_Y }">
					<td>${regDate.registerDate}</td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
		<c:if test="${empty registerDateList}">
		<tr><td colspan="5" id="noRec" style="text-align: center;">无记录</td></tr>
		</c:if>
	</table>
</form>
<div align="center" style="margin-top: 10px">
	<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
		<input type="button" id="saveBtn" onclick="saveRegisterDate()" class="search" value="保&#12288;存"/>
	</c:if>
	<input type="button" onclick="jboxClose()" class="search" value="关&#12288;闭"/>
</div>


<div style="display: none;">
	<!-- 模板 -->
	<table id="xjzcTemplate" width="100%" class="basic">
		<tr>
			<td style="text-align: center;padding: 0px;"><input name="xjzcIds" type="checkbox" /></td>
			<td style="text-align: center;padding: 0px;" class="xjzcSerial"></td>
			<td><input type="text" class="inputText ctime validate[required]" name="registerDate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" /></td>
		</tr>
	</table>
</div>

</div>
</div>
</body>

