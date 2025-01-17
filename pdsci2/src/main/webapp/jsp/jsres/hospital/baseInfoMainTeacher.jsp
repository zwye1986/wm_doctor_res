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
		var url = "<s:url value='/jsres/manage/userSearch'/>?orgFlow=${sessionScope.currUser.orgFlow}";
		jboxLoad("div_table_0", url, true);
	}

	function commonSzManage(teacherLevelId){
		var url = "<s:url value='/jsres/manage/commonSzManage'/>?orgFlow=${sessionScope.currUser.orgFlow}" + "&teacherLevelId=" + teacherLevelId;
		jboxLoad("div_table_0", url, true);
	}

	function responsibleTutor(){
		var url = "<s:url value='/jsres/manage/responsibleTutor'/>?orgFlow=${sessionScope.currUser.orgFlow}";
		jboxLoad("div_table_0", url, true);
	}

	function auditTeacherApplicationMain(){
		var url = "<s:url value='/jsres/statistic/auditTeacherApplicationMain'/>?roleFlag=local";
		jboxLoad("div_table_0", url, true);
	}

	function selTag(gradeRole){
		if(gradeRole == 'sysuser'){
			teacherManage();
		}
		if(gradeRole == 'ybsz'){
			commonSzManage('GeneralFaculty');
		}
		if(gradeRole == 'ggsz'){
			commonSzManage('KeyFaculty');
		}
		if(gradeRole == 'szsh'){
			auditTeacherApplicationMain('KeyFaculty');
		}
		if(gradeRole == 'responsibleTutor'){
			responsibleTutor();
		}
	}

</script>

<div class="title_tab" style="margin-top:0px">
	<ul>
		<li class="tab_select" onclick="selTag('sysuser');"><a>用户信息</a></li>
		<li class="tab" onclick="selTag('ybsz','');"><a>一般师资</a></li>
		<li class="tab" onclick="selTag('ggsz','');"><a>骨干师资</a></li>
		<li class="tab" onclick="selTag('szsh','');"><a>师资审核</a></li>
<%--		<li class="tab" onclick="selTag('responsibleTutor','');"><a>责任导师</a></li>--%>
	</ul>
</div>

<div class="main_bd" id="div_table_0" >

</div>


