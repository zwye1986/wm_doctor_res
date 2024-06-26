<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
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
</style>
<script type="text/javascript">
$(document).ready(function(){
	
});
function toPage(page) {
	if(!$("#userName").val())
	{
		jboxTip("请输入姓名！")
		return false;
	}
	if(!$("#idNo").val())
	{
		jboxTip("请输入证件号！")
		return false;
	}
	jboxStartLoading();
	jboxPostLoad("doctorListZi","<s:url value='/jsres/certificateManage/freeList'/>?roleFlag=${roleFlag}",$("#searchForm").serialize(),false);
}

</script>
<div class="main_hd">
    <h2 class="underline">证书信息查询</h2>
</div>
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm">
			<table class="searchTable">
				<tr>
					<td class="td_left">姓&#12288;&#12288;名：</td>
					<td>
						<input type="text" id="userName" name="userName" value="${userName}" placeholder="必填" class="input" />
					</td>
					<td  class="td_left">证件号码：</td>
					<td>
						<input type="text" id="idNo" name="idNo" value="${param.idNo}" placeholder="必填" class="input" />
					</td>
					<td colspan="4" style="text-align: left;">
						<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage();"/>
					</td>
				</tr>
			</table>
	</form>
    </div>
   <div id="doctorListZi">
	   <img src="<s:url value='/jsp/jsres/images/init.png'/>">
    </div>
</div>