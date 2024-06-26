<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript">
$(document).ready(function(){
	deptInfoManage('baseDept');
});

function deptInfoManage(tab){
	// tab选中的突出显示
	if(window.event.type == 'click') {
		$("#reducationTab li").attr("class", "tab");
		$(window.event.target).closest("li").attr("class", "tab_select");
	}

	if(tab == 'testDept') {
		var url = "<s:url value='/jsres/manage/deptSearch'/>?orgFlow=${sessionScope.currUser.orgFlow}";
		jboxLoad("deptMaintain", url, true);
	}
	if(tab == 'baseDept') {
		var url = "<s:url value='/jsres/dept/baseDeptMain'/>?orgFlow=${sessionScope.currUser.orgFlow}";
		jboxLoad("deptMaintain", url, true);
	}
}

</script>

<div class="main_hd">
	<h2>科室信息管理</h2>
	<div class="title_tab">
		<ul id="reducationTab">
			<li id="baseDeptMaintain" class="tab_select" onclick="deptInfoManage('baseDept');"><a href="#">基地科室维护</a></li>
			<li id="testDeptMaintain" class="tab" onclick="deptInfoManage('testDept');"><a href="#">考试科室维护</a></li>
		</ul>
	</div>
</div>

<div class="main_bd" id="deptMaintain" >

</div>


