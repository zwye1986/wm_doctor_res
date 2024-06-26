<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	<jsp:param name="jquery_cxselect" value="true"/>
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
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
$(document).ready(function(){
// 	search();
});

function search() {
	jboxStartLoading();
	$("#searchForm").submit();
// 	var url = "<s:url value='/res/platform/orgPermissionList'/>";
// 	jboxPostLoad("contentDiv", url, $("#searchForm").serialize(), false); 
}

function toPage(page) {
	if(page!=undefined){
		$("#currentPage").val(page);			
	}
	search();
}

/**
*模糊查询加载
*/
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;
		
		var searchInput = this;
		searchInput.on("keyup focus",function(){
			$("#boxHome").show();
			if($.trim(this.value)){
				$("#boxHome .item").hide();
				var items = $("#boxHome .item[value*='"+this.value+"']").show();
				if(!items){
					$("#boxHome").hide();
				}
			}else{
				$("#boxHome .item").show();
			}
		});
		searchInput.on("blur",function(){
			if(!$("#boxHome.on").length){
				$("#boxHome").hide();
			}
		});
		$("#boxHome").on("mouseenter mouseleave",function(){
			$(this).toggleClass("on");
		});
		$("#boxHome .item").click(function(){
			$("#boxHome").hide();
			var value = $(this).attr("value");
			$("#itemName").val(value);
			searchInput.val(value);
			if(option.clickActive){
				option['clickActive']($(this).attr("flow"));
			}
		});
	};
})(jQuery);
//======================================
//页面加载完成时调用
$(function(){
	$("#orgSel").likeSearchInit({
		/* clickActive:function(flow){
			
		} */ 
	});
});

function operPerm(obj, orgFlow, operType){
	var cfgValue = "${GlobalConstant.FLAG_N}";
	if($(obj).attr("checked")=="checked") {
		cfgValue = "${GlobalConstant.FLAG_Y}";
	}
	var $P001 = $("#"+orgFlow+"_P001");
	var $P002 = $("#"+orgFlow+"_P002");
//	var $P004 = $("#"+orgFlow+"_P004");
//	var $P005 = $("#"+orgFlow+"_P005");
	var $P006 = $("#"+orgFlow+"_P006");
//	var $P008 = $("#"+orgFlow+"_P008");
//	var $P009 = $("#"+orgFlow+"_P009");
	var $P010 = $("#"+orgFlow+"_P010");
	var $P011 = $("#"+orgFlow+"_P011");
	var $P012 = $("#"+orgFlow+"_downExamFile");
	var $P013 = $("#"+orgFlow+"_P013");
	if($P001.attr("checked")=="checked") {
		$P002.removeAttr("disabled");
//		$P004.removeAttr("disabled");
//		$P005.removeAttr("disabled");
		$P006.removeAttr("disabled");
//		$P008.removeAttr("disabled");
//		$P009.removeAttr("disabled");
        $P010.removeAttr("disabled");
        $P011.removeAttr("disabled");
        $P012.removeAttr("disabled");
        $P013.removeAttr("disabled");
	}else{
//	|| $P004.attr("checked")=="checked" || $P005.attr("checked")=="checked"
		if($P002.attr("checked")=="checked" ) {
			jboxInfo("请先取消支持委培！");
			$P001.attr("checked", true);
			return false;
		}
		if($P006.attr("checked")=="checked") {
			jboxInfo("请先取消支持过程带教App！");
			$P001.attr("checked", true);
			return false;
		}
//		if($P008.attr("checked")=="checked") {
//			jboxInfo("请先取消支持在校专硕过程管理！");
//			$P001.attr("checked", true);
//			return false;
//		}
//		if($P009.attr("checked")=="checked") {
//			jboxInfo("请先取消专硕出科考！");
//			$P001.attr("checked", true);
//			return false;
//		}
        if($P010.attr("checked")=="checked") {
            jboxInfo("请先取消一键审核培训数据！");
            $P001.attr("checked", true);
            return false;
        }
        if($P011.attr("checked")=="checked") {
            jboxInfo("请先取消一键审核跟师数据！");
            $P001.attr("checked", true);
            return false;
        }
		$P002.attr("disabled", true);
//		$P004.attr("disabled", true);
//		$P005.attr("disabled", true);
		$P006.attr("disabled", true);
//		$P008.attr("disabled", true);
//		$P009.attr("disabled", true);
		$P010.attr("disabled", true);
		$P011.attr("disabled", true);
		$P012.attr("disabled", true);
		$P013.attr("disabled", true);
	}
	$("#cfgCode").val("jswjw_"+orgFlow+operType);
	$("#cfgValue").val(cfgValue);
	if(operType=="_P001"){
		$("#desc").val("是否开放过程管理");
	}else if(operType=="_P002"){
		$("#desc").val("是否开放委培学员");
	}else if(operType=="_P003"){
		$("#desc").val("是否开放登记数据查询");
	}else if(operType=="_P006"){
		$("#desc").val("是否开放过程带教APP");
	}else if(operType=="_P008"){
		$("#desc").val("是否开放基地专硕过程管理");
	}else if(operType=="_P009"){
		$("#desc").val("是否开放基地专硕出科考");
	}else if(operType=="_P010"){
        $("#desc").val("是否开放一键审核培训数据");
    }else if(operType=="_P011"){
        $("#desc").val("是否开放一键审核跟师数据");
    }else if(operType=="_downExamFile"){
        $("#desc").val("是否开放出科考核试卷下载");
    }else if(operType=="_P013"){
        $("#desc").val("是否开放排班");
    }
	save(orgFlow, operType);

	$('.' + orgFlow + '_P004').toggle(obj.checked);
}
function openOrg(obj){
	var orgFlag="";
	if($(obj).is(":checked")){
		orgFlag = $(obj).val();
	}
	var url="<s:url value='/res/platform/orgPermission'/>?isQuery=${param.isQuery}&orgFlag="+orgFlag;
	window.location.href=url;
}
function save(orgFlow, operType){
	var url = "<s:url value='/sys/cfg/saveOne'/>";
	jboxPost(url, $($('#sysCfgForm').html().htmlFormart("jswjw_"+orgFlow+operType)).serialize(), function(resp){
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

	function openPersonPermission(orgFlow){
		jboxOpen('<s:url value="/res/cfg/personPermission"/>?orgFlow='+orgFlow,'学员权限控制',1000,500);
	}

	function saveCfg(input){
		var val = input.value;
		if(!val){
			return false;
		}

		if(isNaN(val) || (val%1!=0)){
			return jboxTip('请输入整数！');
		}

		var code = input.dataset.cfgCode;
		var sData = {};
		sData['cfgCode'] = code;
		sData[code] = val;
		sData[code+'_ws_id'] = '${GlobalConstant.RES_WS_ID}';
		sData[code+'_desc'] = input.dataset.switchDesc;
		var url = '<s:url value="/sys/cfg/saveOne"/>';
		jboxPost(url,sData,function(resp){
			jboxTip('操作成功');
		},null,false);
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
				<form id="searchForm" method="post" action="<s:url value='/res/platform/orgPermission'/>?isQuery=${param.isQuery}">
					<input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage}"/>
					<div class="queryDiv">
						<div class="inputDiv">
							<label class="qlable">基地名称：</label>
							<input id="orgSel" class="toggleView xltext" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" onmouseover="this.title = this.value"/>
							<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:36px;left:83px;">
								<div id="boxHome" style="padding-left: 5px;max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
									<c:forEach items="${allSysOrgList}" var="org">
										<p class="item" flow="${org.orgFlow}" value="${org.orgName}"style="line-height: 20px; padding:10px 0;cursor: default;text-align: left;">${org.orgName}</p>
									</c:forEach>
								</div>
							</div>
						</div>
						<div class="inputDiv" style="min-width: 115px;max-width: 115px;">
							<label><input type="checkbox" name="orgFlag" value="${GlobalConstant.FLAG_Y }"<c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">checked</c:if> onchange="openOrg(this);">&nbsp;管理机构&#12288;</label>

						</div>
						<div class="lastDiv">
							<input type="button" class="searchInput" onclick="toPage(1);" value="查&#12288;询">
						</div>
					</div>
				</form>
				
				<form id="sysCfgForm" >
					<input type="hidden" id="cfgCode" name="cfgCode"/>
					<input type="hidden" id="cfgValue" name="{0}"/>
					<input type="hidden" id="wsId" value="${GlobalConstant.RES_WS_ID }" name="{0}_ws_id"/>		
					<input type="hidden" id="desc" name="{0}_desc">
				</form>
				<%--<div>--%>
					<%--Tip：开放人数中<font color="red">0</font>表示不限制人数。--%>
				<%--</div>--%>
			</div>
			<table id="orgTable" class="xllist">
				<thead>
				<tr>
					<th width="35%">基地名称</th>
					<th width="20%"<c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>过程管理</th>
					<%--<th width="20%"<c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>在校专硕过程管理</th>--%>
					<%--<th width="20%"<c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>过程App</th>--%>
					<th width="20%"<c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>过程带教App</th>
					<%--<th width="20%"<c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>考试对接</th>--%>
					<%--<th width="20%"<c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>专硕出科考</th>--%>
					<th width="20%"<c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>支持委培</th>
					<th width="40%"<c:if test="${param.orgFlag != GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>登记数据查询</th>
                    <th width="20%"<c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>一键审核培训数据</th>
                    <th width="20%"<c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>一键审核跟师数据</th>
                    <th width="20%"<c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>出科考核试卷下载</th>
                    <th width="20%"<c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>是否排班</th>
					<%--<th width="15%">学员权限</th>--%>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${sysOrgList}" var="sysOrg">
					<tr>
						<td>${sysOrg.orgName }</td>
						<c:set var="key1" value="jswjw_${sysOrg.orgFlow }_P001"/>
						<c:set var="key2" value="jswjw_${sysOrg.orgFlow }_P002"/>
						<c:set var="key3" value="jswjw_${sysOrg.orgFlow }_P003"/>
						<c:set var="key4" value="jswjw_${sysOrg.orgFlow }_P004"/>
						<c:set var="key5" value="jswjw_${sysOrg.orgFlow }_P005"/>
						<c:set var="key6" value="jswjw_${sysOrg.orgFlow }_P006"/>
						<c:set var="key8" value="jswjw_${sysOrg.orgFlow }_P008"/>
						<c:set var="key9" value="jswjw_${sysOrg.orgFlow }_P009"/>
						<c:set var="key10" value="jswjw_${sysOrg.orgFlow }_P010"/>
						<c:set var="key11" value="jswjw_${sysOrg.orgFlow }_P011"/>
						<c:set var="key12" value="jswjw_${sysOrg.orgFlow }_downExamFile"/>
						<c:set var="key13" value="jswjw_${sysOrg.orgFlow }_P013"/>
						<td <c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
							<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="${sysOrg.orgFlow }_P001" ${sysCfgMap[key1]==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P001');"/>
						</td>
						<%--<td <c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>--%>
							<%--<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="${sysOrg.orgFlow }_P008" ${sysCfgMap[key8]==GlobalConstant.FLAG_Y?'checked':''} ${sysCfgMap[key1]!=GlobalConstant.FLAG_Y?'disabled':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P008');"/>--%>
						<%--</td>--%>
						<%--<td <c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>--%>
							<%--<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="${sysOrg.orgFlow }_P005" ${sysCfgMap[key5]==GlobalConstant.FLAG_Y?'checked':''} ${sysCfgMap[key1]!=GlobalConstant.FLAG_Y?'disabled':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P005');"/>--%>
						<%--</td>--%>
						<td <c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
							<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="${sysOrg.orgFlow }_P006" ${sysCfgMap[key6]==GlobalConstant.FLAG_Y?'checked':''} ${sysCfgMap[key1]!=GlobalConstant.FLAG_Y?'disabled':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P006');"/>
						</td>
						<%--<td <c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>--%>
							<%--<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="${sysOrg.orgFlow }_P004" ${sysCfgMap[key4]==GlobalConstant.FLAG_Y?'checked':''} ${sysCfgMap[key1]!=GlobalConstant.FLAG_Y?'disabled':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P004');"/>--%>
							<%--<c:set var="personNumKey" value="P004_limit_num_${sysOrg.orgFlow}"/>--%>
							<%--<input--%>
									<%--class="${sysOrg.orgFlow }_P004"--%>
									<%--data-cfg-desc="机构可对接考试人数"--%>
									<%--data-cfg-code="${personNumKey}"--%>
									<%--type="text"--%>
									<%--value="${sysCfgMap[personNumKey]}"--%>
									<%--style="width: 30px;<c:if test="${!(sysCfgMap[key4] eq GlobalConstant.FLAG_Y)}">display: none;</c:if>"--%>
									<%--onchange="saveCfg(this);"--%>
									<%--placeholder="人数"--%>
							<%--/>--%>
						<%--</td>--%>
						<%--<td <c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>--%>
							<%--<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="${sysOrg.orgFlow }_P009" ${sysCfgMap[key9]==GlobalConstant.FLAG_Y?'checked':''} ${sysCfgMap[key1]!=GlobalConstant.FLAG_Y?'disabled':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P009');"/>--%>
						<%--</td>--%>
						<td <c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
							<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="${sysOrg.orgFlow }_P002" ${sysCfgMap[key2]==GlobalConstant.FLAG_Y?'checked':''} ${sysCfgMap[key1]!=GlobalConstant.FLAG_Y?'disabled':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P002');"/>
						</td>
						<td <c:if test="${param.orgFlag != GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
							<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> id="${sysOrg.orgFlow }_P003" ${sysCfgMap[key3]==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P003');"/>
						</td>
						<%--<td>--%>
							<%--[<a style="color: blue;cursor:pointer;" onclick="openPersonPermission('${sysOrg.orgFlow}');">设置</a>]--%>
						<%--</td>--%>
                        <td <c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
                            <input type="checkbox" <c:if test="${sysCfgMap[key1]!=GlobalConstant.FLAG_Y}">disabled</c:if> id="${sysOrg.orgFlow }_P010" ${sysCfgMap[key10]==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P010');"/>
                        </td>
                        <td <c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
                            <input type="checkbox" <c:if test="${sysCfgMap[key1]!=GlobalConstant.FLAG_Y}">disabled</c:if> id="${sysOrg.orgFlow }_P011" ${sysCfgMap[key11]==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P011');"/>
                        </td>
                        <td <c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
                            <input type="checkbox" <c:if test="${sysCfgMap[key1]!=GlobalConstant.FLAG_Y}">disabled</c:if> id="${sysOrg.orgFlow }_downExamFile" ${sysCfgMap[key12]==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_downExamFile');"/>
                        </td>
                        <td <c:if test="${param.orgFlag eq GlobalConstant.FLAG_Y }">style="display: none;"</c:if>>
                            <input type="checkbox" <c:if test="${sysCfgMap[key1]!=GlobalConstant.FLAG_Y}">disabled</c:if> id="${sysOrg.orgFlow }_P013" ${sysCfgMap[key13]==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_P013');"/>
                        </td>
					</tr>
				</c:forEach>
				</tbody>
				<c:if test="${empty sysOrgList}"> 
					<tr> 
						<td align="center" colspan="6">无记录</td>
					</tr>
				</c:if>
			</table>
			
			<c:set var="pageView" value="${pdfn:getPageView(sysOrgList)}" scope="request"></c:set>
			<pd:pagination toPage="toPage"/>
	</div>
</div>
</body>
</html>