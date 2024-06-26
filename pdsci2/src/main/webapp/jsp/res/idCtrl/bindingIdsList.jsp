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
<script type="text/javascript">
	function doSave(){
		if('${param.num}'!=$("[name='id']:checked").size()){
			jboxTip("学员数量和ID数量不一致");
			return;
		}
		jboxConfirm("确认绑定？",function(){
			var checkboxs = window.parent.frames['mainIframe'].$(".check:checked");
			var data =  [];
			var i = 0;
			checkboxs.each(function(){
				var userFlow = $(this).val();
				var endDate = $(this).attr("endDate");
				var recordFlow = $($("[name='id']:checked")[i]).attr("recordFlow");
				var single = {userFlow:userFlow,endDate:endDate,recordFlow:recordFlow};
				data.push(single)
				i++;
			});
			jboxPost("<s:url value='/res/idCtrl/saveBindingIds'/>","data="+JSON.stringify(data),function(resp){
			if(resp==-1){
			jboxTip("ID已被使用,请重新打开ID列表");
			jboxClose();
			}else if(resp==-2){
			jboxTip("学员没有届别,请完善学员信息后绑定");
			}else if(resp==1){
			jboxTip("操作成功");
			setTimeout(function(){
			window.parent.frames['mainIframe'].window.toPage("${param.currentPage}");
			jboxClose();
			},1000)
			}
			},null,false)
		})
	}

	function countNum(){
		var num = $("[name='id']:checked").size();
		$("#idNum").text(num);
	}
</script>
</head>
<body style="overflow: auto">
<form id="saveForm" style="height: 100px;" >
<input type="hidden" name="recordFlow" id="recordFlow">
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
				<table width="400px;" cellpadding="0" cellspacing="0" class="basic">
					<tr>
						<td>
							已选学员数量：${param.num}
						</td>
						<td>
							已选ID数量：<a id="idNum">0</a>
						</td>
					</tr>
					<tr>
						<th style="text-align: left;padding-left:10px;">选择</th>
						<th style="text-align: left;padding-left:10px;">ID号</th>
					</tr>
					<c:forEach items="${idctrlDetails}" var="item">
					<tr>
						<td><label><input type="checkbox" name="id" value="${item.id}" recordFlow="${item.recordFlow}" onchange="countNum()">&#12288;</label></td>
						<td>${item.id}</td>
					</tr>
					</c:forEach>
				</table>
				<div class="button" style="width: 400px;">
					<input type="button" class="search" onclick="doSave();" value="确&#12288;定">
					<input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>