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

function toPage(page){
	if(page != undefined){
		$("#currentPage").val(page);
	}
	search();
}
function getChangeOrgDetail(doctorFlow, recordFlow){
	var url = "<s:url value='/hbzy/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow="+recordFlow+ "&jointOrg="+"${param.jointOrg}";
	jboxOpen(url, "详情", 1050, 550);
}
function search(){
	if($("#orgFlow").val()==""){
		$("#trainOrg").val("");
	}
	if($('#jointOrg').get(0) != undefined){
		$('#jointOrg').get(0).checked==true?$('#jointOrg').val("checked"):$('#jointOrg').val("");
	}
	var url="<s:url value='/hbzy/doctor/delay'/>";
	jboxPostLoad("content",url,$("#submitForm").serialize(),true);
}
</script>
<c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
<div class="main_hd">
    <h2 class="underline">延期学员查询</h2>
</div>
</c:if>
<div class="main_bd" id="div_table_0">
	<div class="div_search">
		<form id="submitForm">
			<input type="hidden" name="currentPage" id="currentPage"/>
			<c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
				<c:if test="${GlobalConstant.USER_LIST_LOCAL != sessionScope.userListScope}">
					<table class="searchTable">
						<tr>
							<td class="td_left"><label>培训基地：</label></td>
							<td>
								<input id="trainOrg" oncontextmenu="return false" name="orgName"
									   value="${param.orgName}" class="toggleView input" type="text" autocomplete="off"
									   style="margin-left: 0px;width: 100px" onkeydown="changeStatus();"
									   onkeyup="changeStatus();"/>
								<div id="pDiv"
									 style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
									<div class="boxHome trainOrg" id="trainOrgSel"
										 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
										<c:forEach items="${orgs}" var="org">
											<p class="item trainOrg allOrg orgs" flow="${org.orgFlow}"
											   value="${org.orgName}"
											   style="line-height: 20px; padding:5px 0;cursor: default;"
											   <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
											>${org.orgName}</p>
										</c:forEach>
									</div>
									<input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}"
										   style="display: none;"/>
								</div>
							</td>
							<td class="td_left">姓&#12288;&#12288;名：</td>
							<td>
								<input type="text" name="doctorName" value="${param.doctorName}" class="input"
									   style="width: 100px;"/>
							</td>
							<td colspan="4">
								<c:if test="${countryOrgFlag eq 'Y'}">
									<input type="checkbox" id="jointOrg" name="jointOrg"
										   <c:if test="${param.jointOrg eq 'checked'}">checked="checked"</c:if> /><label
										for="jointOrg">&nbsp;协同基地</label>&#12288;
								</c:if>
								<input class="btn_brown" type="button" onclick="search()" value="查&#12288;询"/>
							</td>
						</tr>
					</table>
				</c:if>
				<c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope}">
					<table class="searchTable">
						<tr>
							<td class="td_left">姓&#12288;&#12288;名：</td>
							<td>
								<input type="text" name="doctorName" value="${param.doctorName}" class="input"
									   style="width: 100px;"/>
							</td>
							<td colspan="6">
								<c:if test="${countryOrgFlag eq 'Y'}">
									<input type="checkbox" id="jointOrg" name="jointOrg"
										   <c:if test="${param.jointOrg eq 'checked'}">checked="checked"</c:if> /><label
										for="jointOrg">&nbsp;协同基地</label>&#12288;
								</c:if>
								<input class="btn_brown" type="button" onclick="search()" value="查&#12288;询"/>
							</td>
						</tr>
					</table>
				</c:if>
			</c:if>
		</form>
	</div>
	<div style="padding-bottom: 20px;">
		<div class="search_table">
			<table border="0" cellpadding="0" cellspacing="0" class="grid">
				<tr>
					<th>姓名</th>
					<th>年级</th>
					<th>培训基地</th>
					<th>培训专业</th>
					<th>对应专业</th>
					<th>延期时间</th>
					<th>延期原因</th>
					<th>附件信息</th>
					<c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
						<th>详细</th>
					</c:if>
				</tr>
				<c:forEach items="${resRecList }" var="resRec">
					<tr>
						<td>${resRec.doctorName }</td>
						<td>${resRec.sessionNumber }</td>
						<td title="${resRec.orgName}">${pdfn:cutString(resRec.orgName,6,true,3) }</td>
						<td>${resRec.trainingTypeName }</td>
						<td>${resRec.trainingSpeName }</td>
						<td>
							<c:forEach items="${jszyResTrainYearEnumOneYearList}" var="trainYearEnum">
								<c:if test="${trainYearEnum.name eq resRec.trainingYears}">
									<c:set var="trainYear" value="${trainYearEnum.id}"/>
								</c:if>
							</c:forEach>
								${resRec.graduationYear-resRec.sessionNumber - resRec.trainingYears}年
						</td>
						<td><label title="${resRec.delayreason}">${pdfn:cutString(resRec.delayreason,6,true,3) }</label>
						</td>
						<td>
							<c:if test="${not empty resRec.delayFilePath}">
								[<a href="${sysCfgMap['upload_base_url']}/${resRec.delayFilePath}" target="_blank">查看附件</a>]
							</c:if>
						</td>
						<c:if test="${param.seeFlag != GlobalConstant.FLAG_Y}">
							<td>
								<a class="btn"
								   onclick="getChangeOrgDetail('${resRec.doctorFlow}','${resRec.recruitFlow}');">详情</a>
							</td>
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
		<div class="page" style="padding-right: 40px;">
			<c:set var="pageView" value="${pdfn:getPageView(resRecList)}" scope="request"></c:set>
			<pd:pagination-jsres toPage="toPage"/>
		</div>
	</div>
</div>
