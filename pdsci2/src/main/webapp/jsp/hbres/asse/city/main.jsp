<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
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
			jboxLoad("mainDiv","<s:url value='/hbres/asse/waitAuditMain'/>?roleFlag=${param.roleFlag}",false);
		}else{
			$("#AuditList").removeClass("tab");
			$("#AuditList").addClass("tab_select");
			$("#WaitAudit").addClass("tab");
			$("#WaitAudit").removeClass("tab_select");
			//审核列表
			jboxLoad("mainDiv","<s:url value='/hbres/asse/AuditListMain'/>?roleFlag=${param.roleFlag}",false);
		}
	}
</script>

<div class="main_hd">
	<h2>考核资格审查</h2>
    <div class="title_tab">
        <ul id="reducationTab">
            <li id="WaitAudit" class="tab" onclick="selTag('WaitAudit');"><a>待审核</a></li>
            <li id="AuditList" class="tab" onclick="selTag('AuditList');"><a>情况总览</a></li>
        </ul>
    </div>
</div>
<div id="mainDiv">
</div>
</html>