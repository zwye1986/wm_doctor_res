<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="queryFont" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
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
$(document).ready(function(){
	$('#sessionNumber').datepicker({
		startView: 2,
		maxViewMode: 2,
		minViewMode:2,
		format:'yyyy'
	});
	if("${speFlag}"=="0"){
		$("#speFlag").hide();
	}
});
function getChangeOrgDetail(doctorFlow, recordFlow){
	var url = "<s:url value='/hbzy/manage/getChangeOrgDetail'/>?change=Y&doctorFlow=" + doctorFlow + "&recordFlow="+recordFlow;
	jboxOpen(url, "详情", 1050, 550);
}
function search(){
	var url="<s:url value='/hbzy/manage/changeSpeMain'/>";
	if($('#jointOrg').get(0) != undefined){
		$('#jointOrg').get(0).checked==true?$('#jointOrg').val("checked"):$('#jointOrg').val("");
	}
	if("${GlobalConstant.FLAG_Y}"=="${param.viewFlag}"){
		url="<s:url value='/hbzy/manage/searchChangeSpe'/>?viewFlag=${param.viewFlag}";
	}
	jboxPostLoad("div_table",url,$("#inForm").serialize(),true);
}
function toPage(page){
	if(page != undefined){
		$("#currentPage").val(page);
	}
	search();
}
function audit(recordFlow,doctorFlow){
	var url="<s:url value='/hbzy/manage/audit'/>?doctorFlow="+doctorFlow+"&recordFlow="+recordFlow+"";
	jboxOpen(url, "专业变更记录审核", 680, 450);
}
function changeStatus(){
	 if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "")==""){
		 $("#orgFlow").val("");
	 }
}
</script>
<div class="main_bd">
	<div class="div_search">
		<form id="inForm">
			<input type="hidden" name="currentPage" id="currentPage"/>
			<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
				<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y}">
					<table class="searchTable">
						<tr>
							<td class="td_left">培训基地：</td>
							<td>
								<input id="trainOrg" oncontextmenu="return false" name="orgName"
									   value="${param.orgName}"
									   class="toggleView input" type="text" autocomplete="off"
									   style="margin-left: 0px;width: 100px"
									   onkeydown="changeStatus();" onkeyup="changeStatus();"/>
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
							<td class="td_left">年&#12288;&#12288;级：</td>
							<td colspan="3">
								<c:if test="${countryOrgFlag eq 'Y'}">
									<input type="checkbox" id="jointOrg" name="jointOrg"
										   <c:if test="${param.jointOrg eq 'checked'}">checked="checked"</c:if> /><label
										for="jointOrg">&nbsp;协同基地</label>&#12288;
								</c:if>
								<input type="text" id="sessionNumber" name="sessionNumber"
									   value="${param.sessionNumber}" class="input" readonly="readonly"
									   style="width: 100px;margin-left: 0px"/>&nbsp;
							</td>
						</tr>
						<tr>
							<td class="td_left">是否通过：</td>
							<td>
								<select name="passFlag" class="select" style="width:107px;">
									<option value="">请选择</option>
									<option value="${GlobalConstant.FLAG_Y}"
											<c:if test="${param.passFlag eq GlobalConstant.FLAG_Y }">selected="selected"</c:if>>
										是
									</option>
									<option value="${GlobalConstant.FLAG_N }"
											<c:if test="${param.passFlag eq GlobalConstant.FLAG_N  }">selected="selected"</c:if>>
										否
									</option>
								</select>
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
				<c:if test="${param.viewFlag ne GlobalConstant.FLAG_Y}">
					<table class="searchTable">
						<tr>
							<td class="td_left">培训基地：</td>
							<td>
								<input id="trainOrg" oncontextmenu="return false" name="orgName"
									   value="${param.orgName}"
									   class="toggleView input" type="text" autocomplete="off"
									   style="margin-left: 0px;width: 100px"
									   onkeydown="changeStatus();" onkeyup="changeStatus();"/>
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
							<td class="td_left">年&#12288;&#12288;级：</td>
							<td>
								<input type="text" id="sessionNumber" name="sessionNumber"
									   value="${param.sessionNumber}" class="input" readonly="readonly"
									   style="width: 100px;margin-left: 0px"/>&nbsp;
							</td>
							<td colspan="2">
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

			<c:if test="${GlobalConstant.USER_LIST_GLOBAL ne sessionScope.userListScope}">
				<c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y}">
					<table class="searchTable">
						<tr>
							<td class="td_left">姓&#12288;&#12288;名：</td>
							<td>
								<input type="text" name="doctorName" value="${param.doctorName}" class="input"
									   style="width: 100px;"/>
							</td>
							<td class="td_left">年&#12288;&#12288;级：</td>
							<td>
								<input type="text" id="sessionNumber" name="sessionNumber"
									   value="${param.sessionNumber}" class="input" readonly="readonly"
									   style="width: 100px;margin-left: 0px"/>&nbsp;
							</td>
							<td class="td_left">是否通过：</td>
							<td>
								<select name="passFlag" class="select" style="width:107px;">
									<option value="">请选择</option>
									<option value="${GlobalConstant.FLAG_Y}"
											<c:if test="${param.passFlag eq GlobalConstant.FLAG_Y }">selected="selected"</c:if>>
										是
									</option>
									<option value="${GlobalConstant.FLAG_N }"
											<c:if test="${param.passFlag eq GlobalConstant.FLAG_N  }">selected="selected"</c:if>>
										否
									</option>
								</select>
							</td>
							<td colspan="2">
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
				<c:if test="${param.viewFlag ne GlobalConstant.FLAG_Y}">
					<table class="searchTable">
						<tr>
							<td class="td_left">姓&#12288;&#12288;名：</td>
							<td>
								<input type="text" name="doctorName" value="${param.doctorName}" class="input"
									   style="width: 100px;"/>
							</td>
							<td class="td_left">年&#12288;&#12288;级：</td>
							<td>
								<input type="text" id="sessionNumber" name="sessionNumber"
									   value="${param.sessionNumber}" class="input" readonly="readonly"
									   style="width: 100px;margin-left: 0px"/>&nbsp;
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
			</c:if>
		</form>
	</div>
	<div style="padding-bottom: 20px;">
		<div class="search_table">
			<table border="0" cellpadding="0" cellspacing="0" class="grid">
				<colgroup>
					<col width="10%"/>
					<col width="5%"/>
					<col width="18%"/>
					<col width="15%"/>
					<col width="11%"/>
					<col width="11%"/>
					<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope and GlobalConstant.FLAG_Y eq param.viewFlag }">
						<col width="10%"/>
					</c:if>
					<col width="20%"/>
				</colgroup>
				<thead>
				<tr>
					<th>姓名</th>
					<th>年级</th>
					<th>培训基地</th>
					<th>审核状态</th>
					<th>原对应专业</th>
					<th>变更后专业</th>
					<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope and GlobalConstant.FLAG_Y eq param.viewFlag }">
						<th>附件</th>
					</c:if>
					<th>操作</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${historyExts }" var="docOrgHistoryExt">
					<tr>
						<td>${docOrgHistoryExt.resDoctor.doctorName}</td>
						<td>${docOrgHistoryExt.resDoctor.sessionNumber}</td>
						<td>${docOrgHistoryExt.historyOrgName}</td>
						<td>${docOrgHistoryExt.changeStatusName}</td>
						<td>${docOrgHistoryExt.historyTrainingSpeName}</td>
						<td>${docOrgHistoryExt.trainingSpeName}</td>
						<c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope and GlobalConstant.FLAG_Y eq param.viewFlag }">
							<td>[<a href="${sysCfgMap['upload_base_url']}/${docOrgHistoryExt.speChangeApplyFile }"
									target="_blank">查看附件</a>]
							</td>
						</c:if>
						<td>
							<a class="btn"
							   onclick="getChangeOrgDetail('${docOrgHistoryExt.doctorFlow}','${docOrgHistoryExt.recordFlow}');">详情</a>
							<c:if test="${(jszyResChangeApplySpeEnumBaseWaitingAudit.id == docOrgHistoryExt.changeStatusId and GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope) || (jszyResChangeApplySpeEnumGlobalWaitingAudit.id == docOrgHistoryExt.changeStatusId and GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope)}">
								<a class="btn"
								   onclick="audit('${docOrgHistoryExt.recordFlow}','${docOrgHistoryExt.doctorFlow}')">审核</a>
							</c:if>
						</td>
					</tr>
				</c:forEach>

				<c:if test="${empty historyExts }">
					<tr>
						<td colspan="8">无记录</td>
					</tr>
				</c:if>
				</tbody>
			</table>
		</div>
		<div class="page" style="padding-right: 40px;">
			<c:set var="pageView" value="${pdfn:getPageView(historyExts)}" scope="request"></c:set>
			<pd:pagination-jszy toPage="toPage"/>
		</div>
	</div>
</div>
