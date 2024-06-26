
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

function save(){
	var url = "<s:url value='/srm/proj/mine/savePageGroupStep'/>";
	jboxPost(url , $('#itemGroup1').serialize() , function(){
		window.parent.frames['mainIframe'].location.reload(true);
		jboxClose();
	} , null , true);
	
}

</script>
</head>
<body>
<div id="main">
   <div class="mainright">
      <div class="content">
        <div class="title1 clearfix">
	<form action="<s:url value='/srm/proj/mine/savePageGroupStep'/>" id="itemGroup1" method="post">
		<input type="hidden" name="pageName" value="step4"/>
		<input type="hidden" name="itemGroupName" value="complete"/>
		<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
		<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
		<input type="hidden" name="itemGroupFlow" value="${param.itemGroupFlow}"/>
		<table class="basic" width="680">
                 <thead>
                    <tr>
                        <th colspan="4" class="bs_name">添加验收委员</th>
                    </tr>
                 </thead>
                 <tbody>
                     <tr>
                        <td>单位：</td>
                        <td><input type="text" name="complete_org" value="${resultMap.complete_org }"/></td>
                        <td>姓名:</td>
                        <td><input type="text" name="complete_name" value="${resultMap.complete_name }"/></td>
                     </tr>
                     <tr>
                        <td>性别:</td>
                        <td><input type="text" name="complete_sex" value="${resultMap.complete_sex }"/></td>
                        <td>出生年月:</td>
                        <td><input type="text" name="complete_birthday" value="${resultMap.complete_birthday }"/></td>
                     </tr>
                     <tr>
                        <td>专业:</td>
                        <td><input type="text" name="complete_process" value="${resultMap.complete_process }"/></td>
                        <td>学历:</td>
                        <td><input type="text" name="complete_education" value="${resultMap.complete_education }"/></td>
                     </tr>
                     <tr>
                        <td>职务职称:</td>
                        <td><input type="text" name="complete_title" value="${resultMap.complete_title }"/></td>
                        <td>单位电话:</td>
                        <td><input type="text" name="complete_orgTel" value="${resultMap.complete_orgTel }"/></td>
                     </tr>
                     <tr>
                        <td>手机:</td>
                        <td><input type="text" name="complete_mobile" value="${resultMap.complete_mobile }"/></td>
                        <td>邮箱:</td>
                        <td><input type="text" name="complete_email" value="${resultMap.complete_email }"/></td>
                     </tr>
                     <tr>
                        <td>联系地址:</td>
                        <td><input type="text" name="complete_address" value="${resultMap.complete_address }"/></td>
                        <td>邮编:</td>
                        <td><input type="text" name="complete_mailCode" value="${resultMap.complete_mailCode }"/></td>
                     </tr>
                 </tbody>
                 </table>
		</form>
		 <div class="button" style="width: 650px;">	
		    <input type="button" id="sv" onclick="save();" value="保存" class="search"/>
	     </div>
	</div>
	</div>
	</div>
	</div>
</body>
</html>