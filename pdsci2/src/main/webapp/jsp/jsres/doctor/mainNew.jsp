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
		if("FirstTest"==tabTag)
		{
			$("#FirstTest").removeClass("tab");
			$("#FirstTest").addClass("tab_select");
			$("#SecondTest").addClass("tab");
			$("#SecondTest").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/doctor/asseApplicationMain'/>?tabTag="+tabTag,true);
		}else{
			$("#SecondTest").removeClass("tab");
			$("#SecondTest").addClass("tab_select");
			$("#FirstTest").addClass("tab");
			$("#FirstTest").removeClass("tab_select");
			<%--jboxLoad("mainDiv","<s:url value='/jsres/doctor/asseApplicationMain'/>?tabTag="+tabTag,true);--%>
            jboxLoad("mainDiv","<s:url value='/jsres/examSignUp/main'/>?tabTag="+tabTag,true);
		}
	}
</script>

<div class="main_hd">
    <h2 class="underline" style="font-weight: bold;letter-spacing: 2px;">结业考试申请</h2>
</div>
<div class="title_tab" style="margin-top: 0px;">
        <ul id="reducationTab">
            <li id="FirstTest" class="tab" onclick="selTag('FirstTest');"><a>首考申请</a></li>
            <c:if test="${isAllowApply eq 'Y'}">
                <li id="SecondTest" class="tab" onclick="selTag('SecondTest');"><a>补考申请</a></li>
            </c:if>
        </ul>
    </div>
</div>
<div id="mainDiv">
</div>
</html>