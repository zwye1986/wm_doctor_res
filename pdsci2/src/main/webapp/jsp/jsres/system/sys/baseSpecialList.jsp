<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<title>${sysCfgMap['sys_title_name']}</title>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
</jsp:include>
<style type="text/css">
	#boxHome .item:HOVER{background-color: #eee;}
</style>
<script type="text/javascript">
$(function(){
	$("#orgSel").likeSearchInit({
		clickActive:function(flow){
			$("#orgFlow").val(flow).change();
		}
	});
	search();
});

/**
*模糊查询加载
*/
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;
		
		var searchInput = this;
		searchInput.on("keyup focus",function(){
			$("#boxHome").show();
			if($.trim(this.value)){
				$("#boxHome .item").hide();
				var items = $("#boxHome .item[value*='"+this.value+"']").show();
				if(!items){
					$("#boxHome").hide();
				}
			}else{
				$("#boxHome .item").show();
			}
		});
		searchInput.on("blur",function(){
			if(!$("#boxHome.on").length){
				$("#boxHome").hide();
			}
		});
		$("#boxHome").on("mouseenter mouseleave",function(){
			$(this).toggleClass("on");
		});
		$("#boxHome .item").click(function(){
			$("#boxHome").hide();
			var value = $(this).attr("value");
			$("#itemName").val(value);
			searchInput.val(value);
			if(option.clickActive){
				option['clickActive']($(this).attr("flow"));
			}
		});
	};
})(jQuery);

//((((((((((((((((((((((((((()))))))))))))))))))))))))))
function search(){
	var orgFlow = $("#orgFlow").val();
	/* var oldValue = $("#orgFlow").attr("oldValue");
	if(orgFlow == oldValue){
		return false;
	} */
	var url ="<s:url value='/jsres/base/serachOrgSpeList'/>";
	jboxPostLoad("contentDiv", url, $("#searchForm").serialize(), false);
}

function removeName(orgName){
	var $contains = $("p.item:contains('"+orgName+"')");
	var length = $contains.length;
	if(length != 1){
		$("#orgFlow").val("").change();
	}else{
		if($contains.text() == orgName){
			var orgFlow = $contains.attr("flow");
			$("#orgFlow").val(orgFlow).change();
		}else{
			$("#orgFlow").val("").change();
		}
	}
}
</script>
</head>
<body>
<div class="main_hd">
    <h2 class="underline">基地专业管理</h2> 
</div>
<div class="main_bd">
	<div class="div_search">
	<form id="searchForm">
		基地名称：<input id="orgSel" class="toggleView input" type="text" name="orgName" onchange="removeName(this.value);" value="${param.orgName}" autocomplete="off" style="width: 300px;"/>
		<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:69px;">
			<div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 305px;border-top: none;position: relative;display: none;">
				<p class="item" flow="ALL" value="全部" style="height: 40px;padding-left: 10px;">全部</p>
				<c:forEach items="${orgList}" var="org">
					<p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 40px;padding-left: 10px;">${org.orgName}</p>
				</c:forEach>
			</div>
		</div>
		<input type="hidden" id="orgFlow" name="orgFlow" oldValue="${param.orgFlow}" onchange="search();"/>
		&#12288;年度：<input class="input" type="text" name="sessionYear" value="${sessionYear}" onclick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly">
		&#12288;<input class="btn_green" type="button" onclick="search()" value="查&#12288;询"/>
	</form>
	</div>
	
	<div class="search_table"  id="contentDiv">
        
	</div>
</div>
</body>
</html>