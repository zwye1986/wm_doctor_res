<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
	function save(){
		var form = $("#editForm");
		if(false==form.validationEngine("validate")){
			return ;
		}
		var requestData = form.serialize();
		var url = "<s:url value='/sch/autoArrange/saveStartDate'/>";
		jboxPost(url,requestData,function(resp){
			if(resp == '设置成功，开始排班'){
				window.parent.frames['mainIframe'].window.$("#startDate").html("排班开始时间："+$("#startDate").val());
				window.parent.frames['mainIframe'].window.startArrange();
				doClose();
			}
		},null,true);
	}
	
	function doClose(){
		jboxClose();
	}
</script>

</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
			<form id="editForm" method="post" style="position: relative;">
				<input type="hidden" name="recordFlow" value="${cfg.recordFlow}" />
				<input type="hidden" name="sessionNumber" value="${param.sessionNumber}" />
				<table width="100%" class="basic" >
					<tr>
						<th style="width: 30%"><font color="red" >*</font>年&#12288;&#12288;级：</th>
						<td style="width: 70%">
							${param.sessionNumber}
						</td>
					</tr>
					<tr>
						<th style="width: 30%"><font color="red" >*</font>开始时间：</th>
						<td style="width: 70%">
							<input type="text"  class="validate[required] xltext" id="startDate" name="startDate" value="${cfg.startDate}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="margin-left: 0px"/>
						</td>
					</tr>
				</table>
			</form>
			<p style="text-align: center;">
	       		<input type="button" onclick="save()"  class="search" value="确&#12288;认"/>
	       		<input type="button" onclick="doClose()" class="search" value="关&#12288;闭"/>
	        </p>
			</div>
		</div>
	</div>
</body>
</html>

	

		
