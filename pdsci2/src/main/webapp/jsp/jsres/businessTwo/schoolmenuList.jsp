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
	function operPerm(obj, orgFlow, operType){
		var cfgValue = "${GlobalConstant.FLAG_N}";
		if($(obj).attr("checked")=="checked") {
			cfgValue = "${GlobalConstant.FLAG_Y}";
		}
		$("#cfgCode").val(operType+orgFlow);
		$("#cfgValue").val(cfgValue);
		if(operType == "jsres_hospital_kslzcx_"){
			$("#desc").val("是否开放科室轮转查询");
		}
		if(operType == "jsres_hospital_xyckcjcx_"){
			$("#desc").val("是否开放学员出科查询");
		}
		if(operType == "jsres_hospital_sxpjcx_"){
			$("#desc").val("是否开放双向评价查询");
		}
		if(operType == "jsres_hospital_xygzlcx_"){
			$("#desc").val("是否开放学员工作量查询");
		}
		if(operType == "jsres_hospital_xylzcx_"){
			$("#desc").val("是否开放学员轮转查询");
		}
		if(operType == "jsres_hospital_ybcx_"){
			$("#desc").val("是否开放月报查询");
		}
		if(operType == "jsres_hospital_djgzlcx_"){
			$("#desc").val("是否开放带教工作量查询");
		}
		if(operType == "jsres_hospital_appsyqktj_"){
			$("#desc").val("是否开放APP使用情况统计");
		}
		if(operType == "jsres_hospital_xykqcx_"){
			$("#desc").val("是否开放学员考勤功能");
		}
		if(operType == "jsres_hospital_jzxxgl_"){
			$("#desc").val("是否开放讲座信息管理");
		}
		if(operType == "jsres_hospital_zpyjfk_"){
			$("#desc").val("是否开放住培意见反馈");
		}
		if(operType == "jsres_hospital_zpzngl_"){
			$("#desc").val("是否开放住培指南管理");
		}
		if(operType == "jsres_hospital_pxsjsh_"){
			$("#desc").val("是否开放培训数据审核");
		}
		if(operType == "jsres_hospital_ndkhsz_"){
			$("#desc").val("是否开放年度考核设置");
		}
		if(operType == "jsres_hospital_ndkhcx_"){
			$("#desc").val("是否开放年度考试成绩查询");
		}
		if(operType == "jsres_hospital_jyllmnkh_"){
			$("#desc").val("是否开放结业理论模拟考核");
		}
		if(operType == "jsres_hospital_activity_"){
			$("#desc").val("是否开放教学活动功能");
		}
		if(operType == "jsres_hospital_xzzljh_"){
            $("#desc").val("是否开放新增招录计划功能");
        }
		if (operType == "jsres_hospital_yjdd_"){
			$("#desc").val("是否开放院级督导功能");
		}
		save(orgFlow, operType);
	}

	function save(userFlow,cfg){
		$.ajax({
			url: "<s:url value='/jsres/powerCfg/saveOne'/>",
			type: "post",
			data: $($('#sysCfgForm').html().htmlFormart(cfg+userFlow)).serialize(),
			dataType: "json",
			success: function (res) {
				if (res== "${GlobalConstant.SAVE_SUCCESSED}"){
					jboxTip("操作成功！");
				}else {
					jboxTip("操作失败！");
				}
			}
		});
	}
	String.prototype.htmlFormart = function(){
		var html = this;
		for(var index in arguments){
			var reg = new RegExp('\\{'+index+'\\}','g');
			html = html.replace(reg,arguments[index]);
		}
		return html;
	};
	function selectBatch(type,checkBox){
		if('all' == type){
			if($(checkBox).attr("checked")){
				$("input[name='cfgCode']").each(function(index, domEle){
					if(!$(domEle).attr("checked")){
						$(domEle).click();
					}
				});
			}else {
				$("input[name='cfgCode']").each(function(index, domEle){
					if($(domEle).attr("checked")){
						$(domEle).click();
					}
				});
			}
		}else if('reverse' == type) {
			$("input[name='cfgCode']").each(function(index, domEle){
				$(domEle).click();
			});
		}

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
						<input type="hidden" id="cfgCode" name="cfgCode"/>
						<input type="hidden" id="cfgValue" name="{0}"/>
						<input type="hidden" id="wsId" value="${GlobalConstant.RES_WS_ID }" name="{0}_ws_id"/>
						<input type="hidden" id="desc" name="{0}_desc">
					</form>
					<input type="hidden" name="orgFlow" value="${orgFlow}"/>
					<table style="width: 100%;" cellpadding="0" cellspacing="0" class="basic">
						<tr>
							<td colspan="4">
								<c:if test="${empty param.isQuery}">&#12288;<a  id="allChecked" style="color:#459ae9;cursor: pointer;">全选</a>/<a id="allBackChecked" style="color:#459ae9;cursor: pointer;">反选</a></c:if>
							</td>
						</tr>
						<tr>
							<td>
								<c:set var="key" value="jsres_hospital_kslzcx_${orgFlow}"/>
								<input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_kslzcx_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>科室轮转查询
							</td>
							<td>
								<c:set var="key" value="jsres_hospital_xyckcjcx_${orgFlow}"/>
								<input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_xyckcjcx_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>学员出科查询
							</td>
							<td>
								<c:set var="key" value="jsres_hospital_sxpjcx_${orgFlow}"/>
								<input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_sxpjcx_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>双向评价查询
							</td>
							<td>
								<c:set var="key" value="jsres_hospital_xygzlcx_${orgFlow}"/>
								<input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_xygzlcx_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>学员工作量查询
							</td>
						</tr>
						<tr>
							<td>
								<c:set var="key" value="jsres_hospital_xylzcx_${orgFlow}"/>
								<input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_xylzcx_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>学员轮转查询
							</td>
							<td>
								<c:set var="key" value="jsres_hospital_ybcx_${orgFlow}"/>
								<input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_ybcx_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>月报查询
							</td>
							<td>
								<c:set var="key" value="jsres_hospital_djgzlcx_${orgFlow}"/>
								<input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_djgzlcx_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>带教工作量查询
							</td>
							<td>
								<c:set var="key" value="jsres_hospital_appsyqktj_${orgFlow}"/>
								<input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_appsyqktj_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>APP使用情况统计
							</td>
						</tr>
						<tr>
							<td>
								<c:set var="key" value="jsres_hospital_xykqcx_${orgFlow}"/>
								<input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_xykqcx_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>学员考勤功能
							</td>
							<td>
								<c:set var="key" value="jsres_hospital_jzxxgl_${orgFlow}"/>
								<input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_jzxxgl_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>讲座信息管理
							</td>
							<td>
								<c:set var="key" value="jsres_hospital_zpyjfk_${orgFlow}"/>
								<input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_zpyjfk_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>住培意见反馈
							</td>
							<td>
								<c:set var="key" value="jsres_hospital_zpzngl_${orgFlow}"/>
								<input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_zpzngl_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>住培指南管理
							</td>
						</tr>
						<tr>
							<c:set var="key" value="jsres_hospital_pxsjsh_${orgFlow}"/>
							<td><input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_pxsjsh_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>培训数据审核</td>
							<c:set var="key" value="jsres_hospital_ndkhsz_${orgFlow}"/>
							<td><input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_ndkhsz_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>考核设置</td>
							<c:set var="key" value="jsres_hospital_ndkhcx_${orgFlow}"/>
							<td><input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_ndkhcx_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>成绩查询</td>
							<c:set var="key" value="jsres_hospital_jyllmnkh_${orgFlow}"/>
							<td><input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_jyllmnkh_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>结业理论模拟考核</td>
						</tr>
						<tr>
							<c:set var="key" value="jsres_hospital_activity_${orgFlow}"/>
							<td><input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_activity_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>教学活动功能</td>
							<c:set var="key" value="jsres_hospital_xzzljh_${orgFlow}"/>
							<td><input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_xzzljh_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>新增招录计划功能</td>
							<c:set var="key" value="jsres_hospital_yjdd_${orgFlow}"/>
							<td><input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_hospital_yjdd_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>院级督导</td>
							<c:set var="key" value="jsres_baseInfo_maintenance_${orgFlow}"/>
							<td><input name="cfgCode" onchange="operPerm(this,'${orgFlow }','jsres_baseInfo_maintenance_');" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>>基地信息维护</td>
						</tr>
					</table>
					<div class="button">
						<input class="search" type="button" value="关&#12288;闭"
										onclick="top.jboxClose();" />
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	$("#allChecked").click(function(){
		$("input[name='cfgCode']").attr("checked","checked");

		$("input[name='cfgCode']").each(function(){
			$(this).change();
		})
	});
	$("#allBackChecked").click(function(){
		var elements=$("input[name='cfgCode']");
		for(var i=0;i<elements.length;i++)
		{
			var b=elements[i];
			if($(b).attr("checked")=="checked"){
				$(b).attr("checked",false);
			}else{
				$(b).attr("checked","checked");
			}
			$(b).change();
		}
	});
</script>
</body>
</html>