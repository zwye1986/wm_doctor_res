<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
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
		max-width: 150px;;
	}
	.searchTable .td_left{
		word-wrap:break-word;
		width:5em;
		height: auto;
		line-height: auto;
		text-align: right;
	}
</style>
<%--<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect2.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		search();
	});

	function search() {
		jboxStartLoading();
		jboxPostLoad("doctorListZi","<s:url value='/jsres/supervisio/hospitalLeaderSign'/>",$("#searchForm").serialize(),false);
	}

	function setUserSign(userFlow) {
		var url = "<s:url value='/jsres/supervisio/addSign'/>?userFlow="+userFlow;
		jboxOpen(url, "上传签名图片", 600, 280);
		jboxTip("请上传无背景图的签名照片!");
	}

	function  editSupervisioUser(type,userFlow) {
		var url = "<s:url value ='/jsres/supervisio/editHospitalLeader'/>?userFlow="+userFlow;
		jboxOpen(url,"修改",800,315);
	}

</script>
<div class="main_hd">
	<h2 class="underline">督导管理 — 签名设置</h2>
</div>
<div class="div_search" style="width: 95%;line-height:normal;">
	<form id="searchForm">
		<input type="hidden" id="userFlow" name="userFlow" value="${userFlow}"/>
	</form>
</div>
<div id="doctorListZi" style="width: 95%">

</div>