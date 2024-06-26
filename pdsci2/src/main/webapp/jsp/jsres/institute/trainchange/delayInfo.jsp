<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="font" value="true"/>
	<jsp:param name="login" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
.boxHome .item:HOVER{background-color: #eee;}
.cur{color:red}
</style>
<script type="text/javascript">
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
function changeStatus(){
	 if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "")==""){
		 $("#orgFlow").val("");
	 }
}
$(document).ready(function(){
	$('#time').datepicker({
		startDate:"${doctor.sessionNumber}",
		startView: 2, 
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
});
function getChangeOrgDetail(doctorFlow, recordFlow){
	var url = "<s:url value='/jsres/institute/getChangeOrgDetail'/>?doctorFlow=" + doctorFlow + "&recordFlow="+recordFlow;
	jboxOpen(url, "详情", 1050, 550);
}
function search(){
	if($("#orgFlow").val()==""){
		$("#trainOrg").val("");
	}
	var url="<s:url value='/jsres/institute/delay'/>";
	jboxPostLoad("content",url,$("#submitForm").serialize(),false);
}
</script>
<c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
<div class="main_hd">
    <h2 class="underline">延期医师查询</h2> 
</div>
</c:if>
<div class="infoAudit">
	<form id="submitForm">
	<div class="div_table">
	<div class="div_search">
	<c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
		<label id="train">培训基地：</label>
		<input id="trainOrg" oncontextmenu="return false"  name="orgName" value="${param.orgName}" class="toggleView input" type="text"  autocomplete="off" style="margin-left: 0px;width: 156px" onkeydown="changeStatus();" onkeyup="changeStatus();" />
		<div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
			<div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
				<c:forEach items="${orgs}" var="org">
					<p class="item trainOrg allOrg orgs"  flow="${org.orgFlow}" value="${org.orgName}"  style="line-height: 20px; padding:10px 0;cursor: default;width: 200px ;height: 10px"
					   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
					>${org.orgName}</p>
				</c:forEach>
			</div>
			<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
		</div>&#12288;
	  		 学员姓名：<input type="text" name="operUserName" value="${param.operUserName}" class="input" style="width: 100px;"/>&nbsp;
		   <input class="btn_green" type="button" onclick="search()" value="查&#12288;询"/>
    </c:if>
 	</div>
	   <table border="0" cellpadding="0" cellspacing="0" class="grid">
	   	<tr>
	   		<th>培训基地</th>
	   		<th>学员姓名</th>
	   		<th>培训类型</th>
	   		<th>培训专业</th>
	   		<th>培训届别</th>
	   		<th>结业考核年份</th>
	   		<th>延期原因</th>
	   		<th>附件信息</th>
	   		<c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
	   		<th>详细</th>
	   		</c:if>
	   	</tr>
	   	<c:forEach items="${resRecList }" var="resRec">
	   		<tr>
				<td>${resRec.orgName}</td>	   		
				<td>${resRec.operUserName }</td>	   		
				<td>${dataMap[resRec.recFlow].trainType }</td>	   		
				<td>${dataMap[resRec.recFlow].trainSpe }</td>	   		
				<td>${dataMap[resRec.recFlow].sessionNumber }</td>	   		
				<td>${dataMap[resRec.recFlow].graduationYear }</td>	   		
				<td title="${dataMap[resRec.recFlow].delayReason }">${pdfn:cutString(dataMap[resRec.recFlow].delayReason,6,true,3)}</td>	   		
				<td>
				<c:if test="${not empty dataMap[resRec.recFlow].delayPicValueFile }">
				  [<a href="${sysCfgMap['upload_base_url']}/${dataMap[resRec.recFlow].delayPicValueFile}" target="_blank">查看附件</a>]
				</c:if>
				</td>	 
				<c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">  		
				<td><a class="btn" onclick="getChangeOrgDetail('${dataMap[resRec.recFlow].doctorFlow}','${dataMap[resRec.recFlow].recruitFlow}');">详情</a></td>
				</c:if>   		
	   		</tr>
	   	</c:forEach>
	   	<c:if test="${empty resRecList}">
	   		<tr>
	   			<td colspan="9">无记录</td>
	   		</tr>
	   	</c:if>
	   </table>
	   </div>
	</form>
</div>
