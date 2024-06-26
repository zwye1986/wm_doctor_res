<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<script type="text/javascript">
	function save(){
		if (!$("#sysCfgForm").validationEngine("validate")) {
			return;
		}
		if($('#startDate').val()>$('#endDate').val()){
			jboxTip("开始时间大于结束时间,无法配置!")
			return;
		}
		$.ajax({
			url: "<s:url value='/jsres/powerCfg/savePayPower'/>",
			type: "post",
			data: $('#sysCfgForm').serialize(),
			dataType: "json",
			success: function (res) {
				if (res== "${GlobalConstant.SAVE_SUCCESSED}"){
					jboxTip("操作成功！");
					window.parent.searchHospital();
					setTimeout(function(){
						top.jboxClose();
					},1000);
				}else {
					jboxTip("操作失败！");
				}
			}
		});
	}

</script>
</head>
<body>
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent">
			<div class="tagContent selectTag" id="tagContent0">
				<div>
					<form id="sysCfgForm" >
						<input type="hidden" name="orgFlow" value="${orgFlow}"/>
						<table style="width: 100%;" cellpadding="0" cellspacing="0" class="basic">
							<colgroup>
								<col width="40%" />
								<col width="60%" />
							</colgroup>
							<tr>
								<td >
									付费功能开始时间：
								</td>
								<td>
									<input type="text" style="width: 90%" id="startDate" name="startDate" class="input validate[required]"
										   value="${startDateCfg.cfgValue}"
										   readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
								</td>
							</tr>
							<tr>
								<td>
									付费功能结束时间：
								</td>
								<td>
									<input type="text" style="width: 90%" id="endDate" name="endDate" class="input validate[required]"
										   value="${endDateCfg.cfgValue}"
										   readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
								</td>
							</tr>
						</table>
					</form>
					<div class="button">
						<input class="search" type="button" value="保&#12288;存" onclick="save();" />&#12288;
						<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>