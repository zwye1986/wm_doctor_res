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

	function operPerm(obj, dictId, operType){
		var cfgValue = "${GlobalConstant.FLAG_N}";
		if($(obj).attr("checked")=="checked") {
			cfgValue = "${GlobalConstant.FLAG_Y}";
		}
		$("#cfgCode").val(operType+dictId);
		$("#cfgValue").val(cfgValue);
		if(operType == "jsres_sendSchool_yslccx_"){
			$("#desc").val("是否开放医师轮转查询");
		}
		if(operType == "jsres_sendSchool_ysckdjcx_"){
			$("#desc").val("是否开放医师出科成绩查询");
		}
		if(operType == "jsres_sendSchool_sxpjck_"){
			$("#desc").val("是否开放出科评价查看");
		}
		if(operType == "jsres_sendSchool_ysgzlcx_"){
			$("#desc").val("是否开放医师工作量查询");
		}
		if(operType == "jsres_sendSchool_xykqcx_"){
			$("#desc").val("是否开放学员考勤查询");
		}
		if(operType == "jsres_sendSchool_jddjshqk_"){
			$("#desc").val("是否开放基地带教审核情况");
		}
		if(operType == "jsres_sendSchool_xysccx_"){
			$("#desc").val("是否开放学员手册查询");
		}
		if(operType == "jsres_sendSchool_zpyjfk_"){
			$("#desc").val("是否开放住培意见反馈");
		}
		if(operType == "jsres_sendSchool_zpzngl_"){
			$("#desc").val("是否开放住培指南管理");
		}
        if(operType == "jsres_sendSchool_djgzlcx_"){
            $("#desc").val("是否开放带教工作量查询");
        }
		save(dictId, operType);
	}

	function save(userFlow,cfg){
		var url = "<s:url value='/jsres/powerCfg/saveOne'/>";
		jboxPost(url, $($('#sysCfgForm').html().htmlFormart(cfg+userFlow)).serialize(), function(resp){
			if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
				jboxTip("操作成功！");
			}else{
				jboxTip("操作失败！");
			}
		}, null, false);
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
			<div class="tagContent selectTag" id="tagContent0" style="height:auto;">
				<div>
<form id="sysCfgForm" >
	<input type="hidden" id="cfgCode" name="cfgCode"/>
	<input type="hidden" id="cfgValue" name="{0}"/>
	<input type="hidden" id="wsId" value="${GlobalConstant.RES_WS_ID }" name="{0}_ws_id"/>
	<input type="hidden" id="desc" name="{0}_desc">
</form>
<div style="max-height: 300px;min-height: 300px;">
	<table class="basic" style="width:100%;margin-top: 10px;text-align: center">
		<td colspan="4">
			<c:if test="${empty param.isQuery}">&#12288;<a id="allChecked" style="color:#459ae9;cursor: pointer;">全选</a>/<a <c:if test="${not empty param.isQuery}">disabled</c:if> id="allBackChecked" style="color:#459ae9;cursor: pointer;">反选</a></c:if>
		</td>
		<tr>
			<td>
				<c:set var="key" value="jsres_sendSchool_yslccx_${dictId}"/>
				<input name="cfgCode"  <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} onchange="operPerm(this,'${dictId }','jsres_sendSchool_yslccx_');"/>医师轮转查询
			</td>
			<td>
				<c:set var="key" value="jsres_sendSchool_ysckdjcx_${dictId}"/>
				<input name="cfgCode"  <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} onchange="operPerm(this,'${dictId }','jsres_sendSchool_ysckdjcx_');"/>医师出科成绩查询
			</td>
			<td>
				<c:set var="key" value="jsres_sendSchool_sxpjck_${dictId}"/>
				<input name="cfgCode"  <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} onchange="operPerm(this,'${dictId }','jsres_sendSchool_sxpjck_');"/>出科评价查看
			</td>
			<td>
				<c:set var="key" value="jsres_sendSchool_ysgzlcx_${dictId}"/>
				<input name="cfgCode"  <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} onchange="operPerm(this,'${dictId }','jsres_sendSchool_ysgzlcx_');"/>医师工作量查询
			</td>
		</tr>
		<tr>
			<td>
				<c:set var="key" value="jsres_sendSchool_xykqcx_${dictId}"/>
				<input name="cfgCode"  <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} onchange="operPerm(this,'${dictId }','jsres_sendSchool_xykqcx_');"/>学员考勤查询
			</td>
			<td>
				<c:set var="key" value="jsres_sendSchool_jddjshqk_${dictId}"/>
				<input name="cfgCode"  <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} onchange="operPerm(this,'${dictId }','jsres_sendSchool_jddjshqk_');"/>基地带教审核情况
			</td>
			<td>
				<c:set var="key" value="jsres_sendSchool_xysccx_${dictId}"/>
				<input name="cfgCode"  <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} onchange="operPerm(this,'${dictId }','jsres_sendSchool_xysccx_');"/>学员手册查询
			</td>
			<td>
				<c:set var="key" value="jsres_sendSchool_zpyjfk_${dictId}"/>
				<input name="cfgCode"  <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} onchange="operPerm(this,'${dictId }','jsres_sendSchool_zpyjfk_');"/>住培意见反馈
			</td>
		</tr>
		<tr>
			<td>
				<c:set var="key" value="jsres_sendSchool_zpzngl_${dictId}"/>
				<input name="cfgCode"  <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} onchange="operPerm(this,'${dictId }','jsres_sendSchool_zpzngl_');"/>住培指南管理
			</td>
			<td>
				<c:set var="key" value="jsres_sendSchool_djgzlcx_${dictId}"/>
				<input name="cfgCode"  <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''} onchange="operPerm(this,'${dictId }','jsres_sendSchool_djgzlcx_');"/>带教工作量查询
			</td>
			<td>
			</td>
			<td>
			</td>
		</tr>
	</table>
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
<div class="btn_info" style="text-align: center">
	<input type="button" style="width:100px;" class="search" onclick="javascript:jboxClose();" value="关闭"></input>
</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>