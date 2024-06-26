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
	function del(recordFlow,obj) {
		jboxConfirm("确认删除？",function(){
			var url = "<s:url value='/res/idCtrl/delId'/>";
			jboxPost(url, {"recordFlow":recordFlow}, function(resp) {
				if(resp==1){
					jboxTip("删除成功");
					$(obj).closest("tr").hide();
				}
			},null,false);
		});
	}

	function doClose(){
		window.parent.frames['mainIframe'].window.toPage('${param.currentPage}');
		jboxClose();
	}
</script>
</head>
<body style="overflow: auto">
<form id="submitForm" style="height: 100px;" >
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
				<table width="400px;" cellpadding="0" cellspacing="0" class="basic">
					<tr>
						<th style="text-align: left;padding-left:10px;">ID</th>
						<th style="text-align: left;padding-left:10px;">是否已被使用</th>
						<th style="text-align: left;padding-left:10px;">操作</th>
					</tr>
					<c:forEach items="${idctrlDetails}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.isBinding eq 'Y'?'是':'否'}</td>
						<td>
							<c:if test="${item.isBinding ne 'Y'}">
							<a style="cursor: pointer;color: blue;" onclick="del('${item.recordFlow}',this)">删除</a></td>
							</c:if>
							<c:if test="${item.isBinding eq 'Y'}">
								-
							</c:if>
					</tr>
					</c:forEach>
				</table>
				<div class="button" style="width: 400px;">                            		
					<input type="button" class="search" onclick="doClose();" value="关&#12288;闭">
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>