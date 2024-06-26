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

		function save(userFlow,cfg){
			if (!$("#cfgForm").validationEngine("validate")) {
				return;
			}
			if($('#powerStartTime').val()>$('#powerEndTime').val()){
				jboxTip("开始时间大于结束时间,无法配置!")
				return;
			}
			var url = "<s:url value='/jsres/powerCfg/saveTime'/>?userFlow=${param.userFlow}";
			jboxPost(url, $("#cfgForm").serialize(), function(resp){
				if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
					jboxTip("操作成功！");
					window.parent.frames['mainIframe'].window.search();
					setTimeout(function(){
						jboxClose();
					},2000);
				}else{
					jboxTip("操作失败！");
				}
			}, null, false);
		}
	</script>
</head>
<body>
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
				<div>
					<form id="cfgForm" >
					<input type="hidden" name="key" value="${param.key}"/>
					<table style="width: 100%;" cellpadding="0" cellspacing="0" class="basic">
						<tr>
							<td>权限期限</td>
							<td>
								<input  id="powerStartTime" name="powerStartTime" type="text" value="${cfg.powerStartTime}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
								至
								<input  id="powerEndTime" name="powerEndTime" type="text" value="${cfg.powerEndTime}" readonly="readonly" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
							</td>
						</tr>
					</table>

					</form>
					<div class="button">
						<input class="search" type="button" value="保&#12288;存"  onclick="save();" />
						<input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>