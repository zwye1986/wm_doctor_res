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
		if("GraduationTest"==tabTag)
		{
			$("#GraduationTest").removeClass("tab");
			$("#GraduationTest").addClass("tab_select");
			$("#SkillTest").addClass("tab");
			$("#SkillTest").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/testConfig/main'/>?tabTag="+tabTag,true);
		}else{
			$("#SkillTest").removeClass("tab");
			$("#SkillTest").addClass("tab_select");
			$("#GraduationTest").addClass("tab");
			$("#GraduationTest").removeClass("tab_select");
            jboxLoad("mainDiv","<s:url value='/jsres/skillTimeConfig/main'/>?tabTag="+tabTag,true);
		}
	}
</script>

<div class="main_hd">
    <h2 class="underline" style="font-weight: bold;letter-spacing: 2px;">考核配置</h2>
</div>
<div class="title_tab" style="margin-top: 0px;">
        <ul id="reducationTab">
            <li id="GraduationTest" class="tab" onclick="selTag('GraduationTest');"><a>结业考核配置</a></li>
            <li id="SkillTest" class="tab" onclick="selTag('SkillTest');"><a>技能考核配置</a></li>
        </ul>
    </div>
</div>
<div id="mainDiv">
</div>
</html>