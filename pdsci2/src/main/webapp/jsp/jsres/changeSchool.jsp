<style>
	.selRole:HOVER {color: #0d6aa2;}
</style>

<script>
	$(function(){
		loadTheRoles();
		$("#changeRole").on("mouseenter mouseleave",function(){
			$("#roleContent").toggle();
		});
	});
	
	function loadTheRoles(){
		jboxPost("<s:url value='/sys/user/getSchools'/>",null,function(resp){
			if(resp){
				if(resp.length>1){
					debugger
					for(var index in resp){
						var sysUserSchool = resp[index];
						var school = sysUserSchool.school;
						var url ="<s:url value='/'/>";
						/*if('/' == url){
							url = "/pdsci/";
						}*/
						var url = url+"/jsres/manage/university?school="+sysUserSchool.school;
						var roleItem = $('<div/>').addClass("selRole").attr("url",url).text(school).click(function(){
							location.href = $(this).attr("url");
						});
						$("#roleHome").append(roleItem);
					}
				}else{
					$("#changeRole").remove();	
				}
			}
		},null,false);
	}
</script>

<span id="changeRole">
	<div id="roleContent" style="width: 0px;height: 0px;overflow: visible;float: right;display: none;">
		<div id="roleHome" 
		style="
		background: #6eb0dd;
		width: 120px;
		text-align: left;
		padding: 0px 5px;
		top:21px;
		border-radius:3px;
		position: relative;
		right: 165px;
		border: 1px solid #459fd0;
		cursor: pointer;"></div>
	</div>
	<a><c:out value="${param.actionName}" default="切换院校"/></a>
	&#12288;
</span>