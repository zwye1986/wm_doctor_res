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
	changeTrainSpes();
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
	if(!page||page!=undefined){
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
	var url="<s:url value='/jsres/doctor/backTrainInfoForUni'/>";
	jboxPostLoad("content",url,$("#searchForm").serialize(),false);
}
function exportExcel(){
	var url = "<s:url value='/jsres/doctor/exportForBackForUni'/>";
	jboxTip("导出中…………");
	jboxSubmit($("#searchForm"), url, null, null, false);
	jboxEndLoading();
}
function change(){
	$("#trainOrg").val("");
}
function changeTrainSpes(){
	//清空原来专业！！！
	var trainCategoryid=$("#trainingTypeId").val();
	if(trainCategoryid==""){
		$("select[name=trainingSpeId] option[value != '']").remove();
		return false;
	}
	$("select[name=trainingSpeId] option[value != '']").remove();
	$("#"+trainCategoryid+"_select").find("option").each(function(){
		$(this).clone().appendTo($("#trainingSpeId"));
	});
	return false;
}
</script>
<body>
	<div class="main_hd">
	    <h2 class="underline">退培医师查询</h2>
	</div>
<div class="main_bd">
	<div class="div_table">
	 <div class="div_search">
	 <form id="searchForm">
	 <input type="hidden" name="currentPage" id="currentPage" value=""/>
		 <table style="width:100%">
			 <tr>
				 <td>原培训基地：</td>
				 <td>
					 <select class="select" style="width: 106px" id="orgFlow" name="orgFlow"onchange="changeTrainSpes()">
						 <option></option>
						 <c:forEach items="${orgs}" var="org" varStatus="status">
							 <option value="${org.orgFlow}" <c:if test="${param.orgFlow eq org.orgFlow}">selected</c:if>>${org.orgName}</option>
						 </c:forEach>
					 </select>
				 </td>
				 <td>&nbsp;&#12288;&#12288;学员姓名：</td>
				 <td>
					 <input type="text" value="${param.doctorName}" class="input" name="doctorName" style="width: 100px;"/>
				 </td>
				 <td>&nbsp;年&#12288;&#12288;级：</td>
				 <td><input type="text" id="sessionNumber" name="sessionNumber"
						 <c:if test="${not empty param.sessionNumber }"> value="${param.sessionNumber}"</c:if> class="input" readonly="readonly" style="width: 100px;"/>
				 </td>
				 <td>&nbsp;培训专业：</td>
				 <td>
					 <input type="text" id="trainingSpeName" name="trainingSpeName" value="${param.trainingSpeName}" class="input" style="width: 100px;margin-left: 0px"/>
				 </td>
			 </tr>
			 <tr>
				 <td>退培类型：</td>
				 <td>
					 <select class="select" id="policyId" name="policyId" style="width: 107px;">
						 <option value="">请选择</option>
						 <option value="1"<c:if test="${param.policyId eq 1}">selected="selected"</c:if>>协议退培</option>
						 <option value="2"<c:if test="${param.policyId eq 2}">selected="selected"</c:if>>违约退培</option>
					 </select>
				 </td>
				 <td>&nbsp;退培主要原因：</td>
				 <td>&nbsp;
					 <select class="select" id="reasonId" name="reasonId" style="width: 107px;" onchange="changeReason(this);">
						 <option value="">请选择</option>
						 <option value="1"<c:if test="${param.reasonId eq 1}">selected="selected"</c:if>>辞职</option>
						 <option value="2"<c:if test="${param.reasonId eq 2}">selected="selected"</c:if>>考研</option>
						 <option value="3"<c:if test="${param.reasonId eq 3}">selected="selected"</c:if>>其他</option>
					 </select>
				 </td>
				 <td><input class="btn_green" type="button" value="查&#12288;询" onclick="toPage();"/></td>
				 <td></td>
				 <td></td>
				 <td></td>
			 </tr>
		 </table>
	 	</form>
	 	</div>
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<tr>
        		<th>培训基地</th>
        		<th>年级</th>
        		<th>学员姓名</th>
        		<th>培训专业</th>
        		<th>退培主要原因</th>
        		<th>退培类型</th>
        		<th>学员去向</th>
        		<th>备注(培训基地意见)</th>
        	</tr>
        	<c:forEach items="${resRecList}" var="rec">
        		<tr>
        			<td>${rec.orgName}</td>
        			<td>${rec.sessionNumber}</td>
        			<td>${rec.doctorName}</td>
        			<td>${rec.trainingSpeName}</td>
        			<td>${rec.reasonName}
        				<label title="${rec.reason}">
		        			<c:if test="${not empty rec.reason }">
		        				(${pdfn:cutString(rec.reason,5,true,3) })
		        			</c:if>
	        			</label>
        			</td>
        			<td>${rec.policyName}
        				<label title="${rec.policy }">
	        				<c:if test="${not empty rec.policy }">
		        					(${pdfn:cutString(rec.policy,5,true,3) })
		        			</c:if>
	        			</label>
        			</td>
        			<td><label title="${rec.dispositon}">${pdfn:cutString(rec.dispositon,7,true,3) }</label></td>
					<c:set var="modifyTime" value="${pdfn:transDateTime(rec.modifyTime)}"></c:set>
					<c:set var="modifyTime" value="${ '  审核时间：'.concat(modifyTime)}"></c:set>
					<c:if test="${empty rec.remark}">
						<td><label title="${modifyTime}">${pdfn:cutString(modifyTime,10,true,3) }</label></td>
					</c:if>
					<c:if test="${not empty rec.remark}">
						<td><label title="${rec.remark.concat(modifyTime)}">${pdfn:cutString(rec.remark.concat(modifyTime),10,true,3) }</label></td>
					</c:if>
        			<%--<td><label title="${rec.remark}">${pdfn:cutString(rec.remark,7,true,3) }</label></td>--%>
        		</tr>
        	</c:forEach>
        	<c:if test="${empty resRecList}">
        		<tr>
        			<td colspan="8">无记录</td>
        		</tr>
        	</c:if>
        </table>
 			<div class="page" style="padding-right: 40px;">
				<c:set var="pageView" value="${pdfn:getPageView(resRecList)}" scope="request"></c:set>
				<pd:pagination-jsres toPage="toPage"/>
       		</div>
	</div>
	</div>
	<div style="display: none;">
	<select id="WMFirst_select">
		<c:forEach items="${dictTypeEnumWMFirstList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="WMSecond_select">
		<c:forEach items="${dictTypeEnumWMSecondList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="AssiGeneral_select">
		<c:forEach items="${dictTypeEnumAssiGeneralList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>
	<select id="DoctorTrainingSpe_select">
		<c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
			<option <c:if test="${param.trainingSpeId eq dict.dictId}">selected="selected"</c:if> value="${dict.dictId}">${dict.dictName}</option>
		</c:forEach>
	</select>

</div>
</body>
