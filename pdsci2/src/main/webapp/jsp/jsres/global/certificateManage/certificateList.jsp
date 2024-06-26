<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
<script type="text/javascript">
$(document).ready(function(){
	$("li").click(function(){
		$(".tab_select").addClass("tab");
		$(".tab_select").removeClass("tab_select");
		$(this).removeClass("tab");
		$(this).addClass("tab_select");
	});
	if ("${param.liId}" != "") {
		$("#${param.liId}").addClass("tab_select");
	} else {
		$('li').first().addClass("tab_select");
	}
	$(".tab_select").click();
});

function showCertificate(recruitFlow){
	jboxLoad("doctorContent","<s:url value='/jsres/certificateManage/showCertificate'/>?recruitFlow="+recruitFlow+"&noDown=noDown",true);
}

</script>

<div class="main_bd">
	<div class="title_tab" <c:if test="${roleFlag eq 'doctor'}">style="margin-top: 0px;" </c:if>>
		<ul>
			<c:forEach items="${doctorList}" var="doctor" varStatus="s">
				<c:if test="${s.index eq 0}">
					<li class="tab_select" onclick="showCertificate('${doctor.recruitFlow}')"><a>${doctor.speName}(${doctor.catSpeName})</a></li>
				</c:if>
				<c:if test="${s.index ne 0}">
					<li class="tab" onclick="showCertificate('${doctor.recruitFlow}')"><a>${doctor.speName}(${doctor.catSpeName})</a></li>
				</c:if>
			</c:forEach>
		</ul>
	</div>
    <div id="doctorContent">
    </div>
</div>


