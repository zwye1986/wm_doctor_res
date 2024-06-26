
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
</head>
<script type="text/javascript">


	function showModule(domain,obj){
		$("#domainSpan").html(domain);
		jboxGet("<s:url value='/edc/design/modList?domain='/>" + domain,null,function(resp){
			$("#modDiv").html(resp);		
		},null,false);	
	}
	function saveModule(){
		jboxPost("<s:url value='/edc/design/saveModule'/>", $('#moudleForm').serialize(), function() {
			window.location.reload(true);
			window.parent.frames['mainIframe'].window.refresh();
		});
	}
	function doClose(){
		jboxClose();
	}
	function searchModule(){
		var data = {
				"searchName":$("#moduleSearch").val(),
				"projFlow":"${param.projFlow}"
		};
		jboxPost("<s:url value='/edc/design/searchModule'/>", data, function(resp) {
			$("#modDiv").html(resp);
		},null,false);
	}
	function count(projFlow){
		jboxGet("<s:url value='/edc/design/count'/>",null,function(resp){
			$("#moduleCount").html(resp);			
		},null,false);	
	}
	
	$(document).ready(function(){
		count('${param.projFlow}');
	});
	
	function showChooseModule(){
		//window.location.href="<s:url value='/edc/design/queryChooseModule'/>";
		//jboxOpen("<s:url value='/edc/design/queryChooseModuleAndGroupByType'/>", "已选择模块信息", 550, 500);
		var url = "<s:url value='/edc/design/queryChooseModuleAndGroupByType'/>";
		var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='550px' height='500px' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
		jboxMessager(iframe,'已选择模块信息',550,500);
	}
</script>
<body>
<div class="mainright">
<div class="main">
<div style="float: left;">
	<table class="xllist" style="width: 500px">
		<tr><th colspan="4" align="center">特殊目的域</th></tr>
		<tr><td><a href="javascript:showModule('DM',this);" id="DM" style="<c:if test="${!empty existModuleMap['DM']}" >color:red</c:if>">人口统计学(DM)</a></td><td><a href="javascript:showModule('CO');" id="CO" style="<c:if test="${!empty existModuleMap['CO']}">color:red</c:if>">备注(CO)</a></td><td><a href="javascript:showModule('SE');" id="SE"  style="<c:if test="${!empty existModuleMap['SE']}">color:red</c:if>">受试者基本条件(SE)</a></td><td><a href="javascript:showModule('SV');" id="SV"  style="<c:if test="${!empty existModuleMap['SV']}">color:red</c:if>">受试者访视(SV)</a></td></tr>
		<tr><th colspan="4" align="center">常规类观察</th></tr>
		<tr><td colspan="2" align="center">干预措施</td><td colspan="2" align="center">检查结果</td></tr>
		<tr><td colspan="2">有意实施的治疗/程序<br/>■<a href="javascript:showModule('CM');" id="CM"  style="<c:if test="${!empty existModuleMap['CM']}">color:red</c:if>">伴随用药(CM)</a><br/>■<a href="javascript:showModule('EX');" id="EX"  style="<c:if test="${!empty existModuleMap['EX']}">color:red</c:if>">试验用药(EX)</a><br/>■<a href="javascript:showModule('SU');" id="SU"  style="<c:if test="${!empty existModuleMap['SU']}">color:red</c:if>">非试验用药(SU)</a></td>
		<td colspan="2" rowspan="3">用以说明具体问题的计划性评估
		<br/>■<a href="javascript:showModule('EG');" id="EG" style="<c:if test="${!empty existModuleMap['EG']}">color:red</c:if>">心电图试验结果(EG)</a>
		<br/>■<a href="javascript:showModule('IE');" id="IE" style="<c:if test="${!empty existModuleMap['IE']}">color:red</c:if>">入选/排除标准没有满足(IE)</a>
		<br/>■<a href="javascript:showModule('LB');" id="LB" style="<c:if test="${!empty existModuleMap['LB']}">color:red</c:if>">实验室检查结果(LB)</a>
		<br/>■<a href="javascript:showModule('PE');" id="PE" style="<c:if test="${!empty existModuleMap['PE']}">color:red</c:if>">体格检查(PE)</a>
		<br/>■<a href="javascript:showModule('QS');" id="QS" style="<c:if test="${!empty existModuleMap['QS']}">color:red</c:if>">问卷调查表(QS)</a>
		<br/>■<a href="javascript:showModule('SC');" id="SC" style="<c:if test="${!empty existModuleMap['SC']}">color:red</c:if>">受试者特征(SC)</a>
		<br/>■<a href="javascript:showModule('VS');" id="VS" style="<c:if test="${!empty existModuleMap['VS']}">color:red</c:if>">生命体征(VS)</a>
		<br/>■<a href="javascript:showModule('DA');" id="DA" style="<c:if test="${!empty existModuleMap['DA']}">color:red</c:if>">用药计数(DA)</a>
		<br/>■<a href="javascript:showModule('MB');" id="MB" style="<c:if test="${!empty existModuleMap['MB']}">color:red</c:if>">微生物样本(MB)</a>
		<br/>■<a href="javascript:showModule('MS');" id="MS" style="<c:if test="${!empty existModuleMap['MS']}">color:red</c:if>">微生物药敏试验(MS)</a>
		<br/>■<a href="javascript:showModule('PC');" id="PC" style="<c:if test="${!empty existModuleMap['PC']}">color:red</c:if>">药代动力学浓度(PC)</a>
		<br/>■<a href="javascript:showModule('PP');" id="PP" style="<c:if test="${!empty existModuleMap['PP']}">color:red</c:if>">药代动力学参数(PP)</a>
		<br/>■<a href="javascript:showModule('FA');" id="FA" style="<c:if test="${!empty existModuleMap['FA']}">color:red</c:if>">事件或干预措施的结果(FA)</a>
		</td></tr>
		<tr><td colspan="2" align="center">事件</td></tr>
		<tr><td colspan="2">独立的计划研究评估的观察结果<br/>■<a href="javascript:showModule('AE');" id="AE"  style="<c:if test="${!empty existModuleMap['AE']}">color:red</c:if>">不良事件(AE)</a>
		<br/>■<a href="javascript:showModule('DS');" id="DS"  style="<c:if test="${!empty existModuleMap['DS']}">color:red</c:if>">处置(DS)</a>
		<br/>■<a href="javascript:showModule('MH');" id="MH" style="<c:if test="${!empty existModuleMap['MH']}">color:red</c:if>">病史(MH)</a>
		<br/>■<a href="javascript:showModule('DV');" id="DV" style="<c:if test="${!empty existModuleMap['DV']}">color:red</c:if>">方案的偏移(DV)</a>
		<br/>■<a href="javascript:showModule('CE');" id="CE" style="<c:if test="${!empty existModuleMap['CE']}">color:red</c:if>">临床事件(CE)</a></td></tr>
		<tr><th colspan="4" align="center">试验设计域</th></tr>
		<tr><td colspan="4" width="100%" style="border:0px;">
				<table width="100%" border="0">
					<tr>
						<td><a href="javascript:showModule('TA');" id="TA" style="<c:if test="${!empty existModuleMap['TA']}">color:red</c:if>">试验分组(TA)</a></td>
						<td><a href="javascript:showModule('TE');" id="TE" style="<c:if test="${!empty existModuleMap['TE']}">color:red</c:if>">试验组成(TE)</a></td>
						<td><a href="javascript:showModule('TV');" id="TV" style="<c:if test="${!empty existModuleMap['TV']}">color:red</c:if>">试验访视(TV)</a></td>
						<td><a href="javascript:showModule('TI');" id="TI" style="<c:if test="${!empty existModuleMap['TI']}">color:red</c:if>">试验入选/排除标准(TI)</a></td>
						<td><a href="javascript:showModule('TS');" id="TS" style="<c:if test="${!empty existModuleMap['TS']}">color:red</c:if>">试验总结(TS)</a></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr><th colspan="4" align="center">特殊用途(关系)数据集</th></tr>
		<tr><td colspan="2">附加的限定值(补充数据集)</td><td colspan="2">相关记录(RELREC)</td></tr>
	</table>
</div>
<div style="float: left;width: 350px">
	<p style="margin-top: 5px">&#12288;当前域：<span id="domainSpan" style="margin-top: 5px"></span></p>
	模块名称：<input type="text" id="moduleSearch" name="moduleSearch"  class="show" style="width: 120px" /> 
	<input type="button" onclick="searchModule();" class="dingdan-d" value="查询"/>已选：<span style="color: red;border:none;border-bottom: 1px solid;cursor: pointer;" id="moduleCount" onclick="showChooseModule();"></span>
	<!-- <input type="button" onclick="doClose();" class="dingdan-d" value="关闭"/> -->
	<!-- <input type="button" onclick="saveModule();" class="dingdan-d" value="保存"/> -->
	<hr />
	<div id="modDiv">
		
	</div>
</div>
</div></div>
</body></html>
