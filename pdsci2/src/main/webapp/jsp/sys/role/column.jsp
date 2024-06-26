<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
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
	function doSave() {
		if(false==$("#popForm").validationEngine("validate")){
			return ;
		}
		var url = "<s:url value="/sys/role/savecol"/>";
		var data = $('#popForm').serialize();
		jboxPost(url, data, function() {
			//window.parent.frames['mainIframe'].window.search();
			jboxClose();
		});
	}
	function doSelectAll(obj){
		var $check = $(obj);
		$check.parent().next().find(":checkbox").attr("checked",obj.checked);
	}
</script>
</head>
<body>
<div class="mainright">
<form id="popForm" action="<s:url value="/sys/role/savecol"/>" method="post">
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
				<div>
					<table width="800" cellpadding="0" cellspacing="0" class="basic">
						<tr>
							<td class="mation-nab" style="width: 100px;">角色名称：
								<input name="roleFlow" type="hidden" value="${sysRole.roleFlow}">
								<input name="wsId" type="hidden" value="${sysRole.wsId}">
							</td>
							<td>
								${sysRole.roleName}
							</td>
						</tr>
								<tr>
									<td class="mation-nab" >
										<input id="allCheck" type="checkbox" value="" onchange="doSelectAll(this)">&#12288;<label for="allCheck">资讯栏目：</label>
									</td>
									<td>
										<c:set var="total" value="0" scope="page"></c:set>
											<c:forEach items="${colList}" var="column" varStatus="status">
												<c:set var="total" value="${total+1 }" scope="page"></c:set>
												<span style="width: 220px;display:block; float:left;"><input id="${column.columnId}" name="columnId" type="checkbox" value="${column.columnId}" class="validate[required]" <c:if test="${pdfn:contain(column.columnId, columnIds)}">checked</c:if>>
												&#12288;
													<label for="${column.columnId}">${column.columnName}</label>&#12288;
												</span>		
												<c:if test="${((total) mod 3)==0 }">
													<br>
												</c:if>
											</c:forEach>
									</td>
								</tr>		
					</table>
					<div class="button" style="width: 800px;">
						<input class="search" type="button" value="保&#12288;存"
										onclick="doSave();" />
						<input class="search" type="button" value="关&#12288;闭"
										onclick="jboxClose();" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</div>
</body>
</html>