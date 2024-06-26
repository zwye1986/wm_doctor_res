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

/**
 * 查询转出
 */
function searchTurnOut(){
	var url ="<s:url value='/jsres/manage/turnOutMain'/>";
	jboxPostLoad("doctorContent", url, $("#outForm").serialize(), true);
}

/**
 * 查询转入
 */
function searchTurnIn(){
	var url ="<s:url value='/jsres/manage/turnInMain'/>";
	jboxPostLoad("doctorContent", url, $("#inForm").serialize(), true);
}


function trainSpeMain(){
	jboxLoad("doctorContent","<s:url value='/jsp/jsres/hospital/doctor/trainSpeMain.jsp'/>",false);
}

</script>

<div class="main_hd">
	<h2>基地变更审核</h2>
    <div class="title_tab">
        <ul>
            <li class="tab_select" onclick="searchTurnIn()"><a>转入审核</a></li>
            <li class="tab" onclick="searchTurnOut()"><a>转出审核</a></li>
            <!-- <li class="tab" onclick="trainSpeMain()"><a>专业变更</a></li> -->
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="doctorContent">
    </div>
</div>


