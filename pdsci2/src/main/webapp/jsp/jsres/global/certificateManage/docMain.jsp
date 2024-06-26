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
	var roleFlag="doctor";
	jboxLoad("doctorListZi","<s:url value='/jsres/certificateManage/freeList'/>?roleFlag="+roleFlag,true);
});
</script>
<div class="main_hd">
    <h2 class="underline">证书信息查询</h2>
</div>
<div class="main_bd" id="div_table_0" >
   <div id="doctorListZi">

    </div>
</div>