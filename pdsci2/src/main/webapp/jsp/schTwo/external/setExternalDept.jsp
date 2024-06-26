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
		var url = "<s:url value='/sch/external/saveExtDept'/>";
		jboxPost(url,requestData,function(resp){
			if(resp == '${GlobalConstant.SAVE_SUCCESSED}'){
				$("#startDate").val("");
				$("#endDate").val("");
				$("#peopleNum").val("");
				getExternalTimes('${schDept.schDeptFlow}');
			}
		},null,true);
	}
	
	function doClose(){
		jboxCloseMessager();
	}
	function getExternalTimes(schDeptFlow)
	{
		jboxLoad("detail","<s:url value='/sch/external/getExternalTimes'/>?schDeptFlow="+schDeptFlow,true);
	}
	function delExternalDept(recordFlow)
	{
		//recordFlow不为空就表示删除单个
		if(recordFlow!='')
		{
			jboxConfirm("确认删除开放时间信息吗？",function () {
				var url = "<s:url value='/sch/external/delExternalDept'/>?recordFlow="+recordFlow;
				jboxGet(url,null,function(resp){
					if(resp == '${GlobalConstant.DELETE_SUCCESSED}'){
						getExternalTimes('${schDept.schDeptFlow}');
					}
				}, null, true);
			});
		}else{
			var recordFlow="";
			$("input[name='externalDept']:checked").each(function(i){
				if(i==0) {
					recordFlow +="recordFlow="+$(this).val();
				}else{
					recordFlow +="&recordFlow="+$(this).val();
				}
			});
			if(recordFlow=="")
			{
				jboxTip("请选择需要删除的数据！");
				return false;
			}
			jboxConfirm("确认删除所选的数据信息吗？",function () {
				var url = "<s:url value='/sch/external/delExternalDept'/>?"+recordFlow;
				jboxGet(url,null,function(resp){
					if(resp == '${GlobalConstant.DELETE_SUCCESSED}'){
						getExternalTimes('${schDept.schDeptFlow}');
						$("input[name='choose']").attr("checked",false);
						$("input[name='externalDept']").attr("checked",false);
					}
				}, null, true);
			});
		}
	}
	function changePeopleNum(recordFlow)
	{
		var url = "<s:url value='/sch/external/changePeopleNum'/>?recordFlow="+recordFlow;

		<%--var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='300px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";--%>
		<%--jboxMessager(iframe,'修改开放人数',400,300,false);--%>

		jboxOpen(url,'修改开放人数',400,300,false);

	}
	$(document).ready(function(){
		getExternalTimes('${schDept.schDeptFlow}');
	});
	function choose(){
		var length=$("input[name='choose']:checked").length;
		if(length>0){
			$("input[name='externalDept']").attr("checked",true);
			return;
		}else{
			$("input[name='externalDept']").attr("checked",false);
			return;
		}
	}
</script>

</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
			<form id="editForm" method="post" style="position: relative;">
				<input type="hidden" name="schDeptFlow" value="${schDept.schDeptFlow}" />
				<input type="hidden" name="orgFlow" value="${orgFlow}"/>
				<table width="100%" class="basic" >
					<tr>
						<th style="width: 20%">医院轮转科室：</th>
						<td style="width: 80%">${schDept.schDeptName}
						</td>
					</tr>
					<tr>
						<th>标准科室：</th>
						<td>
							<select name="standardDeptId" class="validate[required] xlselect" style="margin-right: 0px">
								<c:if test="${fn:length(standardDepts)>1}">
									<option value="">请选择</option>
								</c:if>
								<c:forEach items="${standardDepts}" var="dept">
									<option value="${dept.standardDeptId}">${dept.standardDeptName}</option>
								</c:forEach>
							</select>
							<font color="red" >*</font>
						</td>
					</tr>
					<tr >
						<th>开始时间：</th>
						<td><input name="startDate" id="startDate" class="validate[required] xltext" readonly="readonly" style="margin-right: 0px"
								   onClick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate: '#F{$dp.$D(\'endDate\')}' })"/>
							<font color="red" >*</font>
						</td>
					</tr>
					<tr >
						<th>结束时间：</th>
						<td><input name="endDate" id="endDate" class="validate[required] xltext" readonly="readonly" style="margin-right: 0px"
								   onClick="WdatePicker({dateFmt:'yyyy-MM-dd',minDate: '#F{$dp.$D(\'startDate\')}'})"/>
							<font color="red" >*</font>
						</td>
					</tr>
					<tr class="notExternal">
						<th>开放人数：</th>
						<td>
							<input type="text" class="validate[required,custom[integer]] xltext"  name="peopleNum" id="peopleNum" style="margin-right: 0px"/>
							<font color="red" >*</font>
						</td>
					</tr>
				</table>
			</form>
				<p style="text-align: center;">
					<input type="button" onclick="save()"  class="search" value="保&#12288;存"/>
					<input type="button" onclick="doClose()" class="search" value="关&#12288;闭"/>
				</p>
				<table  class="basic" width="100%" style="margin-top: 10px;">
					<tr>
						<td colspan="7" style="text-align: right;">
							<input type="checkbox"  onclick="choose();"name="choose" />全选&#12288;
							<input type="button" onclick="delExternalDept('')"  class="search" value="删&#12288;除"/>
						</td>
					</tr>
				</table>
				<div style="width: 100%;height: 250px;overflow: auto;" id="detail">

				</div>
			</div>
		</div>
	</div>
</body>
</html>

	

		
