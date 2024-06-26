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
		var name = "住院医师";
		var name2 = "助理全科";
		var root ="<s:url value='/'/>";
		/*if('/' == root){
			root = "/pdsci/";
		}*/
		var url = root+"jsres/manage/changePhyAcc?type=phy";
		var url2 = root+"jsres/manage/changePhyAcc?type=acc";
		var roleItem = $('<div/>').addClass("selRole").attr("url",url).text(name).click(function(){
			location.href = $(this).attr("url");
		});
		var roleItem2 = $('<div/>').addClass("selRole").attr("url",url2).text(name2).click(function(){
			location.href = $(this).attr("url");
		});
		$("#roleHome").append(roleItem);
		$("#roleHome").append(roleItem2);
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
	<a><c:out value="${param.actionName}" default="切换"/></a>
	&#12288;
</span>