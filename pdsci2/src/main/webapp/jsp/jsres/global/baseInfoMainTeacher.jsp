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

	function teacherManage(){
		var url = "<s:url value='/jsres/statistic/globalTeacherMain'/>?roleFlag=global";
		jboxLoad("div_table_0", url, true);
	}

	function teacherList(){
		var url = "<s:url value='/jsres/statistic/commonSzSearch'/>?roleFlag=global";
		jboxLoad("div_table_0", url, true);
	}

	function selTag(gradeRole){
		if(gradeRole == 'sztj'){
			teacherManage();
		}
		if(gradeRole == 'szlb'){
			teacherList();
		}
	}

</script>

<div class="title_tab" style="margin-top:0px">
	<ul>
		<li class="tab_select" onclick="selTag('sztj');"><a>师资统计</a></li>
		<li class="tab" onclick="selTag('szlb');"><a>师资列表</a></li>
	</ul>
</div>

<div class="main_bd" id="div_table_0" >

</div>


