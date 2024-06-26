<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="true"/>
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
		function search(){
			jboxStartLoading();
			$("#searchForm").submit();
		}

		function toPage(page) {
			var currentPage="";
			if(!page||page!=undefined){
				currentPage=page;
			}
			if(page==undefined||page==""){
				currentPage=1;
			}
			$("#currentPage").val(currentPage);
		}
		function oper(obj, dictId,operType){
			var cfgValue = "${GlobalConstant.FLAG_N}";
			if($(obj).attr("checked")=="checked") {
				cfgValue = "${GlobalConstant.FLAG_Y}";
			}
			var $P007 = $("#"+dictId+"_P007");
			var $P010 = $("#"+dictId+"_P010");
			if($P007.attr("checked")=="checked") {
				$P010.removeAttr("disabled");
			}else{
				if($P010.attr("checked")=="checked") {
					jboxInfo("请先取消出科考！");
					$P007.attr("checked", true);
					return false;
				}
				$P010.attr("disabled", true);
			}
			$("#cfgCode").val("jswjw_sendSchool_"+dictId+operType);
			$("#cfgValue").val(cfgValue);
			if(operType=="_P007"){
				$("#desc").val("是否开放派送学校过程管理");
			}else if(operType=="_P010"){
				$("#desc").val("是否开放派送学校出科考");
			}
			save(dictId, operType);
		}
		function save(dictId, operType){
			var url = "<s:url value='/sys/cfg/saveOne'/>";
			jboxPost(url, $($('#sysCfgForm').html().htmlFormart("jswjw_sendSchool_"+dictId+operType)).serialize(), function(resp){
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
	</script>
</head>
<body>
<div class="mainright">
	<div class="content">
		<div class="title1 clearfix">
			<form id="searchForm" action="<s:url value="/res/platform/schoolPermission" />?isQuery=${param.isQuery}" method="post" >
				<input type="hidden" name="currentPage" id="currentPage" value=""/>
				<div class="queryDiv">
					<div class="inputDiv">
						<label class="qlable">派送学校名称：</label>
						<input class="qtext" name="dictName" type="text" value="${param.dictName}"/>
					</div>
					<div class="lastDiv">
						<input type="button" class="searchInput" onclick="search();" value="查&#12288;询">
					</div>
				</div>
			</form>
			<form id="sysCfgForm" >
				<input type="hidden" id="cfgCode" name="cfgCode"/>
				<input type="hidden" id="cfgValue" name="{0}"/>
				<input type="hidden" id="wsId" value="${GlobalConstant.RES_WS_ID }" name="{0}_ws_id"/>
				<input type="hidden" id="desc" name="{0}_desc">
			</form>
		</div>
		<table class="xllist">
			<tr>
				<th width="10%">序号</th>
				<th width="30%">派送学校名称</th>
				<th width="40%" >派送学校过程管理</th>
				<th width="20%" >出科考核</th>
			</tr>
			<tbody id="sorttable">
			<c:forEach items="${dictList}" var="dict" varStatus="num">
				<tr id="${dict.dictFlow}">
					<td>${num.count}</td>
					<td>${dict.dictName}</td>
					<td>
						<c:set var="key" value="jswjw_sendSchool_${dict.dictId}_P007"/>
						<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>  id="${dict.dictId }_P007" ${sysCfgMap[key]==GlobalConstant.FLAG_Y or sysCfgMap[key]==null?'checked':''} onchange="oper(this,'${dict.dictId }','_P007');"/>
					</td>
					<td>
						<c:set var="key10" value="jswjw_sendSchool_${dict.dictId}_P010"/>
						<input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if>  id="${dict.dictId }_P010" ${sysCfgMap[key10]==GlobalConstant.FLAG_Y?'checked':''}  ${sysCfgMap[key]==GlobalConstant.FLAG_N?'disabled':''} onchange="oper(this,'${dict.dictId }','_P010');"/>
					</td>
				</tr>
			</c:forEach>
			</tbody>
			<c:if test="${dictList == null || dictList.size()==0 }">
				<tr>
					<td align="center" colspan="4">无记录</td>
				</tr>
			</c:if>
		</table>
	</div>
	<c:set var="pageView" value="${pdfn:getPageView(dictList)}" scope="request"></c:set>
	<pd:pagination toPage="toPage"/>
</div>
</body>
</html>