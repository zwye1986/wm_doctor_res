<script type="text/javascript">
$(document).ready(function(){
	teacherManage();
});

function teacherManage(){
	var url = "<s:url value='/jsres/manage/userSearch'/>?orgFlow=${sessionScope.currUser.orgFlow}";
	jboxLoad("div_table_0", url, true);
}

</script>

<div class="main_hd">
	<h2>师资管理</h2>
</div>

<div class="main_bd" id="div_table_0" >

</div>


