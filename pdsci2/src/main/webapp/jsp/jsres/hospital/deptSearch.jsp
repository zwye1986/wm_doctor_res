<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="queryFont" value="true"/>
</jsp:include>
<style type="text/css">
	.boxHome .item:HOVER {
		background-color: #eee;
	}
</style>
<link href="<s:url value='/css/form.css'/>" rel="stylesheet" type="text/css">
<script type="text/javascript">
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;
		
		var searchInput = this;
		var spaceId = this[0].id;
		searchInput.on("keyup focus",function(){
			var boxHome = $("#"+spaceId+"Sel");
			boxHome.show();
			if($.trim(this.value)){
				$("p."+spaceId+".item",boxHome).hide();
				var items = boxHome.find("p."+spaceId+".item[value*='"+this.value+"']").show();
				if(!items){
					boxHome.hide();
				}
			}else{
				$("p."+spaceId+".item",boxHome).show();
			}
		});
		searchInput.on("blur",function(){
			var boxHome = $("#"+ spaceId+"Sel");
			if(!$("."+spaceId+".boxHome.on").length){
				boxHome.hide();
			}
		});
		$("."+spaceId+".boxHome").on("mouseenter mouseleave",function(){
			$(this).toggleClass("on");
		});
		
		$("."+spaceId+".boxHome .item").click(function(){
			var boxHome = $("."+spaceId+".boxHome");
			boxHome.hide();
			var value = $(this).attr("value");
			var input = $("#"+spaceId);
			input.val(value);
			if(option.clickActive){
				option.clickActive($(this).attr("flow"));
			}
// 			var content = $("#clone").html().replace("title",value);
// 			$("#"+input.attr("id")+"Div").append(content);
		});
	};
})(jQuery);

$(document).ready(function(){
	search();
	$("#ksmc").likeSearchInit({
		 clickActive:function(flow){
			 $("."+flow).show();
		} 
	});
});

function toPage(page) {
	$("#currentPage").val(page);
	search();
}

function search(){
	var url = "<s:url value='/jsres/manage/deptList'/>?orgFlow=${sessionScope.currUser.orgFlow}";
	//jboxPostLoad("baseContent", url, $("#searchForm").serialize(), true);
	jboxPostLoad("searchConent", url, $("#searchForm").serialize(), true);
}

function edit(deptFlow){
	jboxOpen("<s:url value='/jsres/manage/editDept?deptFlow='/>"+deptFlow,"编辑科室信息", 500, 300);
}
function exportDept(){
	var url = "<s:url value='/jsres/manage/exportDept?orgFlow=${sessionScope.currUser.orgFlow}'/>";
	jboxExp($("#searchForm"),url);
}
function del(deptFlow,deptName,recordStatus){
	var msg = "";
	if (recordStatus == '${GlobalConstant.RECORD_STATUS_N}') {
		msg = "停用";
	} else if (recordStatus == '${GlobalConstant.RECORD_STATUS_Y}') {
		msg = "启用";
	}
	jboxConfirm("确认" + msg + "该科室吗？",function () {
		var url = "<s:url value='/jsres/manage/deleteDept?deptFlow='/>" + deptFlow+ "&recordStatus=" + recordStatus;
		jboxGet(url,null,function(){
			search();					
		});			
	});
}
function publicOpen(deptFlow){
	var url="<s:url value='/jsres/manage/publicOpen?deptFlow='/>"+deptFlow;
	jboxOpen(url,"对外开放",850,600,true);
}

function modifyCfg(deptFlow,recordFlow){
	var url="<s:url value='/jsres/manage/modifyCfg?deptFlow='/>"+deptFlow+"&recordFlow="+recordFlow;
	jboxOpen(url,"关联标准科室",850,600,true);
}

function searchReset() {
	$("#deptCode,#ksmc,#isUnion").val("");
}

function showDept(deptFlow, schDeptName, isJoin, speFlow) {
	var url = "<s:url value ='/jsres/base/showDeptInfo'/>?viewFlag=Y&deptFlow=" + deptFlow + "&orgFlow=${orgFlow}&isJoin=Y&speFlow=" + speFlow+"&isglobal=Y";
	jboxOpen(url, '科室信息（' + schDeptName + '）', 1200, 700);
}
</script>
<!-- <div class="main_hd">
    <h2 class="underline">科室维护</h2> 
</div> -->
<div class="main_bd" id="div_table_0" >
	<div class="div_search">
	<form id="searchForm" action="<s:url value="/sys/dept/list/${sessionScope.deptListScope}" />" method="post" >
		<input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>

		<div class="form_search">
			<div class="form_item">
				<div class="form_label">科室代码：</div>
				<div class="form_content">
					<input type="text" name="deptCode" id="deptCode" value="${param.deptCode}" class="input"/>
				</div>
			</div>

			<div class="form_item">
				<div class="form_label">科室名称：</div>
				<div class="form_content">
					<input type="text" id="ksmc" name="deptName" id="deptName" value="${param.deptName}" class="input" autocomplete="off"/>
					<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; left:0px; top:30px;">
						<div class="boxHome ksmc" id="ksmcSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 159px;border-top: none;position: relative;display:none;">
							<c:forEach items="${sysDeptList}" var="dept" >
								<p  class="item ksmc" flow="${dept.deptFlow}" value="${dept.deptName}" style="line-height: 25px; padding:0px 0;cursor: default;width: 100% ">${dept.deptName}</p>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>

			<div class="form_item">
				<div class="form_label">出科考关联：</div>
				<div class="form_content">
					<select name="isUnion" class="select" style="width: 161px;" id="isUnion">
					<option value=""></option>
					<option value="Y">已关联</option>
					<option value="N">未关联</option>
				</select>
				</div>
			</div>
		</div>



<%--		<div style="display: flex;justify-content: flex-start; column-gap: 52px;margin-top: 15px">--%>
<%--			<div>--%>
<%--				科室代码：--%>
<%--				<input type="text" name="deptCode" id="deptCode" value="${param.deptCode}" class="input"/>--%>
<%--			</div>--%>
<%--			<div>--%>
<%--				科室名称：--%>
<%--				<input type="text" id="ksmc" name="deptName" id="deptName" value="${param.deptName}" class="input" autocomplete="off"/>--%>
<%--				<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; left:0px; top:30px;">--%>
<%--					<div class="boxHome ksmc" id="ksmcSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">--%>
<%--						<c:forEach items="${sysDeptList}" var="dept" >--%>
<%--							<p  class="item ksmc" flow="${dept.deptFlow}" value="${dept.deptName}" style="line-height: 25px; padding:0px 0;cursor: default;width: 100% ">${dept.deptName}</p>--%>
<%--						</c:forEach>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--			<div>--%>
<%--				出科考关联：--%>
<%--				<select name="isUnion" class="select" style="width: 161px;" id="isUnion">--%>
<%--					<option value=""></option>--%>
<%--					<option value="Y">已关联</option>--%>
<%--					<option value="N">未关联</option>--%>
<%--				</select>--%>
<%--			</div>--%>
<%--		</div>--%>
		<div style="margin-top: 15px;margin-bottom: 15px">
			<input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
			<input type="button" class="btn_grey" onclick="searchReset()" value="重&#12288;置">
			<input type="button" class="btn_green" onclick="edit()" value="新&#12288;增">
			<input type="button" class="btn_green" onclick="exportDept()" value="导&#12288;出">
		</div>


<%--		<table class="searchTable">--%>
<%--			<tr>--%>
<%--				<td class="td_left">--%>
<%--					科室代码：--%>
<%--				</td>--%>
<%--				<td>--%>
<%--					<input type="text" name="deptCode" id="deptCode" value="${param.deptCode}" class="input"/>--%>
<%--				</td>--%>
<%--				<td class="td_left">--%>
<%--					科室名称：--%>
<%--				</td>--%>
<%--				<td><input type="text" id="ksmc" name="deptName" id="deptName" value="${param.deptName}" class="input" autocomplete="off"/>--%>
<%--					<div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; left:0px; top:30px;">--%>
<%--						<div class="boxHome ksmc" id="ksmcSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">--%>
<%--							<c:forEach items="${sysDeptList}" var="dept" >--%>
<%--								<p  class="item ksmc" flow="${dept.deptFlow}" value="${dept.deptName}" style="line-height: 25px; padding:0px 0;cursor: default;width: 100% ">${dept.deptName}</p>--%>
<%--							</c:forEach>--%>
<%--						</div>--%>
<%--					</div>--%>
<%--				</td>--%>
<%--				<td class="td_left">出科考关联：</td>--%>
<%--				<td>--%>
<%--					<select name="isUnion" class="select" style="width: 127px;" id="isUnion">--%>
<%--						<option value=""></option>--%>
<%--						<option value="Y">已关联</option>--%>
<%--						<option value="N">未关联</option>--%>
<%--					</select>--%>
<%--				</td>--%>
<%--				<td colspan="4">--%>
<%--					<input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">--%>
<%--					<input type="button" class="btn_grey" onclick="searchReset()" value="重&#12288;置">--%>
<%--					<input type="button" class="btn_green" onclick="edit()" value="新&#12288;增">--%>
<%--					<input type="button" class="btn_green" onclick="exportDept()" value="导&#12288;出">--%>
<%--				</td>--%>
<%--			</tr>--%>
<%--		</table>--%>

	</form>
    </div>
    
    <div id="searchConent">

	</div>

</div>