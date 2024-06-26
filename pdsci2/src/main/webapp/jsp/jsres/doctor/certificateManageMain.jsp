<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">

	$(document).ready(function(){
        selTag("${param.tabTag}");
	});
	function selTag(tabTag){
		if("certificateApply"==tabTag)
		{
			$("#certificateApply").removeClass("tab");
			$("#certificateApply").addClass("tab_select");
			$("#certificateShow").addClass("tab");
			$("#certificateShow").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/doctor/certificateApply'/>?tabTag="+tabTag,true);
		}else{
			$("#certificateShow").removeClass("tab");
			$("#certificateShow").addClass("tab_select");
			$("#certificateApply").addClass("tab");
			$("#certificateApply").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/doctor/certificateShow'/>?tabTag="+tabTag,true);
		}
	}
</script>

<div class="main_hd">
    <h2 class="underline" style="font-weight: bold;letter-spacing: 2px;">结业证书管理</h2>
</div>
<div class="title_tab" style="margin-top: 0px;">
        <ul id="reducationTab">
            <li id="certificateApply" class="tab" onclick="selTag('certificateApply');"><a>结业证书申请</a></li>
            <li id="certificateShow" class="tab" onclick="selTag('certificateShow');"><a>结业证书查看</a></li>
        </ul>
    </div>
</div>
<div id="mainDiv">
</div>
</html>