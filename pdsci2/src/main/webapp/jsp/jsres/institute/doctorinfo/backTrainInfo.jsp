<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#sessionNumber').datepicker({
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
});
(function($){
	$.fn.likeSearchInit = function(option){
		option.clickActive = option.clickActive || null;
		
		var searchInput = this;
		var spaceId = this[0].id;
		searchInput.on("keyup focus",function(){
			var boxHome = $("#"+spaceId+"Sel");
			boxHome.show();
			 var pDiv=$(boxHome).parent();
			 $(pDiv).css("left",$("#train").outerWidth());
			 var w=$(this).css("marginTop").replace("px","");
			 w=w-0+$(this).outerHeight()+6+"px";
			 $(pDiv).css("top",w);
			if($.trim(this.value)){
				$("p."+spaceId+".item",boxHome).hide();
				var items = boxHome.find("p."+spaceId+".item[value*='"+this.value+"']").show();
				if(!items){
					boxHome.hide();
				}
				changeOrgFlow(this);
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
				$("#orgFlow").val($(this).attr("flow"));
			}
		});
	};
})(jQuery);
function changeOrgFlow(obj){
	var items = $("#pDiv").find("p."+$(obj).attr("id")+".item[value='"+$(obj).val()+"']");
	var flow=$(items).attr("flow") || '';
	$("#orgFlow").val(flow);
 }
$(function(){
	if($("#trainOrg").length){
		$("#trainOrg").likeSearchInit({
			 clickActive:function(flow){
				 $("."+flow).show();
			}
		});
	}
});
function toPage(page) {
	var currentPage="";
	if(!page&&page!=undefined){
		currentPage=page;
	}
	if(page==undefined||page==""){
		currentPage=1;
	}
	$("#currentPage").val(currentPage);
	searchRecInfo();
}
function changeStatus(){
	 if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "")==""){
		 $("#orgFlow").val("");
	 }
}
function searchRecInfo(){
	if($("#orgFlow").val()==""){
		$("#trainOrg").val("");
	}
	var url="<s:url value='/jsres/institute/backTrainInfo'/>";
	jboxPostLoad("content",url,$("#searchForm").serialize(),false);
}
function exportExcel(){
	var url = "<s:url value='/jsres/institute/exportForBack'/>";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
function change(){
	$("#trainOrg").val("");
}
</script>
<body>
<c:if test="${GlobalConstant.USER_LIST_PERSONAL != sessionScope.userListScope and param.seeFlag !=GlobalConstant.FLAG_Y}">
	<div class="main_hd">
	    <h2 class="underline">医师退培查询</h2>
	</div>
</c:if>
<div class="main_bd">
	<div class="div_table">
	 <div class="div_search">
	 <form id="searchForm">
	 <input type="hidden" name="currentPage" id="currentPage" value=""/>
	 	<c:if test="${param.seeFlag !=GlobalConstant.FLAG_Y }">
	 		<div >
				<label id="train">培训基地：</label>
				<input id="trainOrg" oncontextmenu="return false" value="${param.name}" name="name"  class="toggleView input" type="text"  autocomplete="off" style="margin-left: 0px;width: 156px"  onkeydown="changeStatus();" onkeyup="changeStatus();" onchange="change();" />
				<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
					<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
						<c:forEach items="${orgs}" var="org">
							<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}" <c:if test="${empty org.orgLevelId}">type="allOrg"</c:if><c:if test="${!empty org.orgLevelId }">type="${org.orgLevelId}"</c:if> style="line-height: 30px; padding:10px 0;cursor: default;width: 200px;height: 30px "
							<c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
							>${org.orgName}</p>
						</c:forEach>
					 </div>
				   <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
				</div>&#12288;
	 			学员姓名：
	 			<input type="text" value="${param.operUserName}" class="input" name="operUserName" style="width: 100px;"/>&#12288;
	 			培训届别：
	 			<input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input" readonly="readonly" style="width: 100px;margin-left: 0px"/>&#12288;
	 			培训专业：
	 			<input type="text" value="${param.speName}" class="input" name="speName"style="width: 100px;"/>&#12288;
	 			<br>退培主要原因：
				<select class="select" id="reasonId" name="reasonId" style="width: 100px;" onchange="changeReason(this);">
					<option value="">请选择</option>
					<option value="1"<c:if test="${param.reasonId eq 1}">selected="selected"</c:if>>辞职</option>
					<option value="2"<c:if test="${param.reasonId eq 2}">selected="selected"</c:if>>考研</option>
					<option value="3"<c:if test="${param.reasonId eq 3}">selected="selected"</c:if>>其他</option>
				</select>&#12288;		   
			 <input class="btn_green" type="button" onclick="searchRecInfo()" value="查&#12288;询"/>&#12288;
			 <input class="btn_green" type="button" value="导&#12288;出" onclick="exportExcel();"/>
	 		</div>
	 	</c:if>
	 	</form>
	 	</div>
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<tr>
        		<th>培训基地</th>
        		<th>培训届别</th>
        		<th>学员姓名</th>
        		<th>培训专业</th>
        		<th>退培主要原因</th>
        		<th>相关措施和规定</th>
        		<th>学员去向</th>
        		<th>备注(培训基地意见)</th>
        	</tr>
        	<c:forEach items="${backInfoMap[GlobalConstant.FLAG_Y]}" var="rec">
        		<tr>
        			<td>${rec.orgName}</td>
        			<td>${backInfoMap[rec.recFlow].sessionNumber}</td>
        			<td>${rec.operUserName}</td>
        			<td>${backInfoMap[rec.recFlow].trainSpe}</td>
        			<td>${backInfoMap[rec.recFlow].reasonName}
        				<label>
		        			<c:if test="${not empty backInfoMap[rec.recFlow].reason }">
		        				(${pdfn:cutString(backInfoMap[rec.recFlow].reason,5,true,3) })
		        			</c:if>
	        			</label>
        			</td>
        			<td>${backInfoMap[rec.recFlow].policyName}
        				<label>
	        				<c:if test="${not empty backInfoMap[rec.recFlow].policy }">
		        					(${pdfn:cutString(backInfoMap[rec.recFlow].policy,5,true,3) })
		        			</c:if>
	        			</label>
        			</td>
        			<td><label>${pdfn:cutString(backInfoMap[rec.recFlow].dispositon,7,true,3) }</label></td>
        			<td><label>${pdfn:cutString(backInfoMap[rec.recFlow].remark,7,true,3) }</label></td>
        		</tr>
        	</c:forEach>
        	<c:if test="${not empty backInfoMap[GlobalConstant.FLAG_Y] and (not empty param.orgFlow and not empty param.sessionNumber)}">
	        	<tr>
	        		<td colspan="8" style="text-align: center;">合计退培：${backInfoMap[GlobalConstant.FLAG_Y].size()}&#12288;退培比例：${percent}</td>
	        	</tr>
        	</c:if>
        	<c:if test="${empty backInfoMap[GlobalConstant.FLAG_Y]}">
        		<tr>
        			<td colspan="8">无记录</td>
        		</tr>
        	</c:if>
        </table>
	</div>
	</div>
</body>
