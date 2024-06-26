<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
	.boxHome .item:HOVER{background-color: #eee;}
	.cur{color:red;}
	.searchTable{
		width: auto;
	}
	.searchTable td{
		width: auto;
		height: auto;
		line-height: auto;
		text-align: left;
	}
	.searchTable .td_left{
		word-wrap:break-word;
		width:5em;
		height: auto;
		line-height: auto;
		text-align: right;
	}
	.searchTable input[type='text']{
		width: 200px;
		margin-left: 0px
	}
</style>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select2.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
	$(document).ready(function(){
		initOrg();
		jboxStartLoading();
		jboxPostLoad("attachmentListZi","<s:url value='/hbres/asse/attachmentList'/>?",$("#searchForm").serialize(),false);
	});

	function initOrg()
	{
		var datas=[];
		<c:forEach items="${orgs}" var="org">
		<c:if test="${sessionScope.currUser.orgFlow!=org.orgFlow }">
		var d={};
		d.id="${org.orgFlow}";
		d.text="${org.orgName}";
		datas.push(d);
		</c:if>
		</c:forEach>
		var itemSelectFuntion = function(){
			$("#orgFlow").val(this.id);
		};
		$.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
	}

	function search() {
		var orgFlow = $('#orgFlow').val();
		if(!orgFlow){
			jboxTip("请选择基地！");
			return false;
		}
		jboxStartLoading();
		jboxPostLoad("attachmentListZi","<s:url value='/hbres/asse/attachmentList'/>?",$("#searchForm").serialize(),false);
	}
</script>
<div class="main_hd">
	<h2>年度考核成绩材料</h2>
	<div class="title_tab">
	</div>
<div class="div_search" style="width: 95%;line-height:normal;">
	<form id="searchForm">
		<%--查询条件--%>
		<table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
			<tr>
				<td class="td_left">培训基地：</td>
				<td style="width: 250px;">
					<input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off"/>
					<input type="hidden" name="orgFlow" id="orgFlow">
				</td>
				<td style="text-align: left;">
					<input class="btn_green" type="button" value="查&emsp;询" onclick="search();"/>
				</td>
			</tr>
		</table>
	</form>
</div>
<div id="attachmentListZi" style="">
</div>
</div>
<div>
	<c:forEach items="${orgFlowList}" var="flow">
		<input type="hidden" id="${flow}" value="${flow}"/>
	</c:forEach>
</div>