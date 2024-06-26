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
		var re = /^\+?[1-9][0-9]*$/;
		if (!re.test($("#peopleNum").val()))  {
			jboxTip("开放人数必须为正整数");
			return;
		}
		var requestData = form.serialize();
		var url = "<s:url value='/sch/external/updateExtDept'/>";
		jboxPost(url,requestData,function(resp){
			if(resp == '${GlobalConstant.SAVE_SUCCESSED}'){
				window.parent.frames['setExternalDept'].getExternalTimes('${schDept.schDeptFlow}');
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
				<input type="hidden" name="recordFlow" value="${schExternalDept.recordFlow}" />
				<table width="100%" class="basic" >
					<tr>
						<th style="width: 30%">医院轮转科室：</th>
						<td style="width: 70%">${schDept.schDeptName}
						</td>
					</tr>
					<tr>
						<th>标准科室：</th>
						<td>
							${schExternalDept.standardDeptName}
						</td>
					</tr>
					<tr >
						<th>开始时间：</th>
						<td>
							${schExternalDept.startDate}
						</td>
					</tr>
					<tr >
						<th>结束时间：</th>
						<td>${schExternalDept.endDate}
						</td>
					</tr>
					<tr class="notExternal">
						<th>开放人数：</th>
						<td>
							<input type="text" class="validate[required,custom[integer]] xltext"
								   name="peopleNum" value="${schExternalDept.peopleNum}" id="peopleNum" style="margin-right: 0px"/>
							<font color="red" >*</font>
						</td>
					</tr>
				</table>
			</form>
				<p style="text-align: center;margin-top: 10px;">
					<input type="button" onclick="save()"  class="search" value="保&#12288;存"/>
					<input type="button" onclick="doClose()" class="search" value="关&#12288;闭"/>
				</p>
			</div>
		</div>
	</div>
</body>
</html>

	

		
