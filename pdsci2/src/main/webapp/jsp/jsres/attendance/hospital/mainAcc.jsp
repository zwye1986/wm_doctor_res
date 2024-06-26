<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">

	$(document).ready(function(){
		selTag("WaitAudit");
	});
	function selTag(tabTag){
		if("WaitAudit"==tabTag)
		{
			$("#WaitAudit").removeClass("tab");
			$("#WaitAudit").addClass("tab_select");
			$("#AuditList").addClass("tab");
			$("#AuditList").removeClass("tab_select");
			//待审核
			jboxLoad("mainDiv","<s:url value='/jsres/teacher/attendanceSearchTabAcc/hospital'/>",true);
		}else{
			$("#AuditList").removeClass("tab");
			$("#AuditList").addClass("tab_select");
			$("#WaitAudit").addClass("tab");
			$("#WaitAudit").removeClass("tab_select");
			//审核列表
			jboxLoad("mainDiv","<s:url value='/jsres/teacher/attendanceSearchAcc/hospital'/>",true);
		}
	}
</script>

<div class="main_hd">
	<h2>学员考勤查询</h2>
    <div class="title_tab">
        <ul id="reducationTab">
            <li id="WaitAudit" class="tab" onclick="selTag('WaitAudit');"><a>学员考勤统计</a></li>
            <li id="AuditList" class="tab" onclick="selTag('AuditList');"><a>学员考勤详情</a></li>
        </ul>
    </div>
</div>
<div id="mainDiv">
</div>
</html>