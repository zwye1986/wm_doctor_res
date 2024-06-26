
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
	function modPasswd() {
		if(false==$("#mailPasswdForm").validationEngine("validate")){
			return ;
		}
		if($("#mailPassword").val()!= $("#mailPassword2").val()) {
			jboxInfo("两次密码输入不一致,请重新输入");
			return ;
		}
		var url = "<s:url value='/sys/cfg/saveMailPassword'/>";
		var data = $('#mailPasswdForm').serialize();
		jboxPost(url, data, function() {
			jboxClose();
		});
	}
</script>
</head>
<body>
<form id="mailPasswdForm">
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
   		 		<table width="100%" cellpadding="0" cellspacing="0" class="basic">
                    <tr>
                        <th>请输入邮箱密码：</th>
                        <td>
                            <input class="validate[required] xltext" id="mailPassword" name="mailPassword" type="password"/>                                         
                        </td>
                    </tr>
                    <tr>
                        <th>请再次输入邮箱密码：</th>
                        <td>
                            <input class="validate[required] xltext" id="mailPassword2" name="mailPassword2" type="password">
                        </td>
                     </tr>
                     <tr>
                        <th>微信提醒：</th>
                        <td>
                            <input class="validate[required]" id="wx_${GlobalConstant.FLAG_Y }" name="weixinRemind" type="radio" value="${GlobalConstant.FLAG_Y }"><label for="wx_${GlobalConstant.FLAG_Y }">是</label>&nbsp;
                            <input class="validate[required]" id="wx_${GlobalConstant.FLAG_N }" name="weixinRemind" type="radio" value="${GlobalConstant.FLAG_N }"><label for="wx_${GlobalConstant.FLAG_N }">否</label>
                        </td>
                     </tr>
                </table>
				<div class="button" width="100%">
					<input type="hidden" name="userFlow" value="${param.userFlow}" />
					<input type="button" value="保&#12288;存" class="search" onclick="modPasswd()" />
				</div>
			</div>
		</div>
	</div>
</div>
</form>
</body>
</html>