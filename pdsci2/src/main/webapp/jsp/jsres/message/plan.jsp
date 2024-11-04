<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
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

	function selTag(gradeRole){
		if(gradeRole == 'zljh'){
			toPlanPage(1);
		}
		if(gradeRole == 'bsjh'){
			toSendPage(1);
		}
	}

	// 分页
	function toPlanPage(currentPage){
        getPlanList('${param.assignYear}',currentPage);
	}

	function toSendPage(currentPage){
		getSendList('${param.assignYear}',currentPage);
	}

	// 招录计划 切换年份 加载对应的招录计划
	function getPlanList(assignYear, currentPage){
        if(!currentPage){
            currentPage = 1;
        }
        jboxLoad("div_table_0","<s:url value='/jsres/message/planList'/>?assignYear="+assignYear + "&currentPage=" + currentPage,true);
	}

	function getSendList(assignYear, currentPage){
		if(!currentPage){
			currentPage = 1;
		}
		jboxLoad("div_table_0","<s:url value='/jsres/message/sendList'/>?assignYear="+assignYear + "&currentPage=" + currentPage,true);
	}

</script>

<div class="title_tab" style="margin-top:0px">
	<ul>
		<li class="tab_select" onclick="selTag('zljh');"><a>招录计划管理</a></li>
		<li class="tab" onclick="selTag('bsjh','');"><a>报送计划管理</a></li>
	</ul>
</div>
  	<div class="main_bd" id="div_table_0">

  	</div>
      
