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
	function doSave() {
		if(false==$("#submitForm").validationEngine("validate")){
			return ;
		}
		jboxConfirm("确认保存？",function(){
			var orgName = $("[name='orgFlow'] option:checked").text();
			$('#orgName').val(orgName);
			var url = "<s:url value='/res/idCtrl/saveIds'/>";
			var data = $('#submitForm').serialize();
			jboxPost(url, data, function(resp) {
				if(resp==1){
					jboxTip("操作成功");
					window.parent.frames['mainIframe'].window.search();
					jboxClose();
				}
			},null,false);
		});
	}
</script>
</head>
<body>	
<form id="submitForm" style="height: 100px;" >
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
				<table width="400px;" cellpadding="0" cellspacing="0" class="basic">
					<tr>
						<th>机构名称：</th>
						<td>
						<select class="validate[required] xlselect" name="orgFlow"  >
							<c:if test="${fn:length(orgList)>0} }">
								<option value="">请选择</option>
							</c:if>
							<c:forEach var="org" items="${orgList}">
								<option value="${org.orgFlow}">${org.orgName}</option>
							</c:forEach>
						</select>
						<input type="hidden" name="orgName" id="orgName">
						</td>
					</tr>
					<tr>
						<th>分配ID数量：</th>
						<td>
							<input class="validate[required,custom[integer],min[1]] xltext" name="idNumber" type="text" value="" />
						</td>
					</tr>
				</table>
				<div class="button" style="width: 400px;">                            		
					<input type="button" value="保&#12288;存" class="search" onclick="doSave();" />
					<input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>