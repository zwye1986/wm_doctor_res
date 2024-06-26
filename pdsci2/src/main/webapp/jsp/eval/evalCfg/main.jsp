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
		<jsp:param name="jquery_mask" value="true"/>
		<jsp:param name="treetable" value="true"/>
	</jsp:include>

	<style type="text/css">
		.selSysDept span{color: red;}
		.searchTable{
			border: 0px;
		}
		.searchTable tbody td{
			border: 0px;
			background-color: white;
			color: #575656;
		}
		.searchTable tbody th{
			border: 0px;
			background: white;
			color: #575656;
		}
	</style>

	<style type="text/css">
		#boxHome .item:HOVER{background-color: #eee;}
	</style>
	<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			toPage(1);
		});
		function search(){
			var id="";
			$("#tbody tr").each(function(){
				if($(this).hasClass("selected")){
					id=$(this).attr("data-tt-id");
				};
			});
			jboxPostLoad("userList" ,"<s:url value='/eval/evalCfg/list'/>?selectedId="+id ,$("#searchForm").serialize() , true);
		}
		function toPage(page) {
			if(page){
				$("#currentPage").val(page);
			}
			search();
		}

		function add(cfgFlow,parentCfgFlow){
			var url="<s:url value='/eval/evalCfg/edit'/>?cfgFlow="+cfgFlow+"&parentCfgFlow="+parentCfgFlow;
			if(cfgFlow!="")
			{
				jboxOpen(url,"编辑配置信息", 900, 500);
			}else {
				jboxOpen(url,"新增配置信息", 900, 500);
			}
		}
		function addSpe(cfgFlow){
			var url="<s:url value='/eval/evalCfg/addSpe'/>?cfgFlow="+cfgFlow;
			jboxOpen(url,"专业基地关联", 400, 200);
		}
		function showCfg(cfgFlow,parentCfgFlow){
			var url="<s:url value='/eval/evalCfg/showCfg'/>?cfgFlow="+cfgFlow+"&parentCfgFlow="+parentCfgFlow;
			jboxOpen(url,"配置信息详情", 900, 500);
		}
	</script>
</head>
<body>
<div class="mainright">
	<div class="content" id="provCityAreaId">
		<div class="queryDiv">
		<form  id="searchForm" method="post">
			<input type="hidden" id="currentPage" name="currentPage"/>
			<div class="inputDiv">
				<label class="qlable">评估年份：</label>
				<input class="qtext" id="evalYear" name="evalYear"  type="text"  readonly onClick="WdatePicker({dateFmt:'yyyy'})">
			</div>
			<div class="inputDiv">
				<label class="qlable">评估名称：</label>
				<input class="qtext" id="cfgName" name="cfgName"  type="text" >
			</div>
			<div class="inputDiv">
				<label class="qlable">发布状态：</label>
				<select name="isPublish" class="qselect">
					<option value="" ></option>
					<option value="Y">已发布</option>
					<option value="N">未发布</option>
				</select>
			</div>
			<div class="qcheckboxDiv" style="padding-left: 10px;">
				<input type="button" class="searchInput"  onclick="toPage(1);" value="查&#12288;询">
				<input type="button" class="searchInput"  onclick="add('','');" value="新&#12288;增">
			</div>
		</form>
		</div>
		<div id="userList" class="resultDiv">

		</div>
	</div>
</div>

</body>
</html>