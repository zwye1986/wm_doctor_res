<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
	<jsp:include page="/jsp/common/htmlhead.jsp">
		<jsp:param name="basic" value="true"/>
		<jsp:param name="jbox" value="true"/>
		<jsp:param name="jquery_form" value="false"/>
		<jsp:param name="jquery_ui_tooltip" value="true"/>
		<jsp:param name="jquery_ui_combobox" value="false"/>
		<jsp:param name="jquery_ui_sortable" value="false"/>
		<jsp:param name="jquery_cxselect" value="false"/>
		<jsp:param name="jquery_scrollTo" value="false"/>
		<jsp:param name="jquery_jcallout" value="false"/>
		<jsp:param name="jquery_validation" value="true"/>
		<jsp:param name="jquery_datePicker" value="true"/>
		<jsp:param name="jquery_fullcalendar" value="false"/>
		<jsp:param name="jquery_fngantt" value="false"/>
		<jsp:param name="jquery_fixedtableheader" value="true"/>
		<jsp:param name="jquery_placeholder" value="true"/>
		<jsp:param name="jquery_iealert" value="false"/>
	</jsp:include>
<script type="text/javascript">
	function expertEvalOrg(obj,userFlow,orgFlow,speId,evalYear,recordFlow,userName,userCode,orgName,speName)
	{
		var checkedVal = $(obj).attr("checked");
		var recordStatus = "${GlobalConstant.RECORD_STATUS_N}";
		if ("checked" == checkedVal) {
			recordStatus = "${GlobalConstant.RECORD_STATUS_Y}";
		}
		if(recordStatus == "${GlobalConstant.RECORD_STATUS_N}")
		{
			jboxConfirm("确认取消此专家评估？取消的同时将删除相应的评估配置",function(){
				save(obj,userFlow,orgFlow,speId,evalYear,recordFlow,userName,userCode,orgName,speName);
			},function(){
				$(obj).attr("checked",true);
			});
		}else{
			save(obj,userFlow,orgFlow,speId,evalYear,recordFlow,userName,userCode,orgName,speName);
		}
	}
	function save(obj,userFlow,orgFlow,speId,evalYear,recordFlow,userName,userCode,orgName,speName)
	{
		var checkedVal = $(obj).attr("checked");
		var recordStatus = "${GlobalConstant.RECORD_STATUS_N}";
		if ("checked" == checkedVal) {
			recordStatus = "${GlobalConstant.RECORD_STATUS_Y}";
		}
		var evalOrg={
			expertUserFlow:userFlow,
			orgFlow:orgFlow,
			speId:speId,
			evalYear:evalYear,
			recordFlow:recordFlow,
			recordStatus:recordStatus
		};
		jboxStartLoading();
		jboxPost2("<s:url value='/eval/expertEvalOrgSpe/saveEvalOrgSpe'/>",evalOrg,function(resp){
			if('${GlobalConstant.SAVE_SUCCESSED}'!=resp){
				$(obj).attr("checked",false);
			}else{
				if(recordStatus == "${GlobalConstant.RECORD_STATUS_N}")
				{
					window.parent.frames['mainIframe'].window.$("#"+orgFlow+speId+userFlow).remove();
				}else {
					var html=
						"<div style='width: 24%;float: left;' id='"+orgFlow+speId+userFlow+"'>" +
							"<table cellpadding='0' cellspacing='0' class='basic' style='width: 100%;border: 0px;'>" +
								"<tr>" +
									"<td style='width: 100%;border: 0px;'  cellpadding='0' cellspacing='0'>" +
										"<label>" +
												"<a style='color:#449bcd;' href='javascript:void(0);'" +
												" onclick='cfgOrgDetail(\""+userFlow+"\",\""+orgFlow+"\",\""+speId+"\",\""+evalYear+"\",\""+userName+"\",\""+orgName+"\",\""+speName+"\")'>"+
												userName+"["+userCode+"]"+"</a>"+
										"</label> " +
									"</td>" +
								"</tr>" +
							"</table>" +
						"</div>";
					window.parent.frames['mainIframe'].window.$("#"+orgFlow+speId).append(html);
				}
			}
			setTimeout(function(){
				jboxEndLoading();
			},1000);
		},null,true);
	}
	function jboxPost2(posturl,postdata,funcOk,funcErr,showResp){
		$.ajax({
			type : "post",
			url : posturl,
			data : postdata,
			cache : false,
			beforeSend : function(){
			},
			success : function(resp) {
				if(showResp==false){

				}else{
					jboxTip(resp);
				}
				if(funcOk!=null){
					funcOk(resp);
				}
			},
			error : function() {
				jboxEndLoading();
				jboxTip("操作失败,请刷新页面后重试");
				if(funcErr!=null){
					funcErr();
				}
			},
			complete : function(){
			}
		});
	}
	//模糊查询
	function likeSearch(name){
		if(name){
			$("[userName]").hide();
			$("[userName*='"+name+"']").show();
		}else{
			$("[userName]").show();
		}
	}
</script>
		<div class="content">
			<div class="title1 clearfix">
				<div id="tagContent">
					<div class="tagContent selectTag" id="tagContent0">
						<div class="div_search" style="padding: 10px;">
							<input type="text" name="userName" placeholder="可通过关键字检索" onkeyup="likeSearch(this.value);" class="input" />
						</div>
							<div class="div_search" style="padding: 10px;margin: 10px;float:left;overflow:auto;height: auto;max-height: 250px;height: 250px;width: 95%;">
								<c:forEach items="${userList}" var="user">
									<div style="width: 48%;float: left;" userName="${user.userName}">
										<table cellpadding="0" cellspacing="0" class="basic" style="width: 100%;border: 0px;">
											<tr>
												<td style="width: 100%;border: 0px;"  cellpadding="0" cellspacing="0">
													<label>
														<input type="checkbox" id="${user.userFlow}${org.orgFlow}Org"
															   onclick="expertEvalOrg(this,'${user.userFlow}','${param.orgFlow}','${param.speId}','${param.evalYear}','${evalOrgMap[user.userFlow].recordFlow}','${user.userName}','${user.userCode}','${org.orgName}','${speName}')"
															   style="vertical-align: middle; cursor: pointer;"
															   <c:if test="${evalOrgMap[user.userFlow].recordStatus eq 'Y'}">checked</c:if>  value="${o.orgFlow}"/>
															${user.userName}(${user.userCode})
													</label>
												</td>
											</tr>
										</table>
									</div>
								</c:forEach>
							</div>
						<div class="button">
							<input type="button" class="search" id="save" onclick="jboxClose();" value="关&#12288;闭"/>
						</div>
					</div>
				</div>
			</div>
		</div>