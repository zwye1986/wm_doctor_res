<%@include file="/jsp/common/doctype.jsp" %>
<html>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/hbres/htmlhead-hbres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript">
	$(document).ready(function(){
		selTag("AsseInfo");
	});
	function selTag(tabTag){
		if("AsseInfo"==tabTag)
		{
			$("#AsseInfo").removeClass("tab");
			$("#AsseInfo").addClass("tab_select");
			$("#AsseFile").addClass("tab");
			$("#AsseFile").removeClass("tab_select");
			//报考信息
			jboxLoad("mainDiv","<s:url value='/hbres/asse/AsseInfo'/>?isShow=${param.isShow}&doctorFlow=${param.doctorFlow}&applyYear=${param.applyYear}",true);
		}else{
			$("#AsseFile").removeClass("tab");
			$("#AsseFile").addClass("tab_select");
			$("#AsseInfo").addClass("tab");
			$("#AsseInfo").removeClass("tab_select");
			//报考材料
			jboxLoad("mainDiv","<s:url value='/hbres/asse/AsseFile'/>?recruitFlow=${param.doctorFlow}&applyYear=${param.applyYear}",true);
		}
	}
</script>

<div class="main_hd">
	<h2 style="height:50px;line-height: 50px;">${docotr.trainingSpeName}</h2>
    <div class="title_tab">
        <ul id="reducationTab">
            <li id="AsseInfo" class="tab" onclick="selTag('AsseInfo');"><a>报考信息</a></li>
            <li id="AsseFile" class="tab" onclick="selTag('AsseFile');"><a>报考材料</a></li>
        </ul>
    </div>
</div>
<div id="mainDiv">
</div>
</html>