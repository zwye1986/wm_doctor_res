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

	function editCommonSzInfo(userFlow){
		var url = "<s:url value='/jsres/manage/editCommonSzInfo?'/>" + "recordFlow=" + userFlow + "&flag=edit" + "&roleFlag=teacher";
		jboxLoad("div_table_0", url, true);
	}

	function applicationStatus(userFlow) {
		var url = "<s:url value='/jsres/statistic/applicationStatus'/>?recordFlow=" + userFlow;
		jboxLoad("div_table_0", url, true);
	}

	function selTag(gradeRole){
		if(gradeRole == 'szxx'){
			editCommonSzInfo('${sessionScope.currUser.userFlow}');
		}
		if(gradeRole == 'sqjl'){
			applicationStatus('${sessionScope.currUser.userFlow}');
		}
	}

</script>

<div class="title_tab" style="margin-top:0px">
	<ul>
		<li class="tab_select" onclick="selTag('szxx');"><a>师资信息</a></li>
		<li class="tab" onclick="selTag('sqjl');"><a>申请记录</a></li>
	</ul>
</div>

<div class="main_bd" id="div_table_0" >

</div>


