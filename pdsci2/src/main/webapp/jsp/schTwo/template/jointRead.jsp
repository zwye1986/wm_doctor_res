
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true" />
	<jsp:param name="jbox" value="true" />
	<jsp:param name="jquery_form" value="false" />
	<jsp:param name="jquery_ui_tooltip" value="true" />
	<jsp:param name="jquery_ui_combobox" value="false" />
	<jsp:param name="jquery_ui_sortable" value="false" />
	<jsp:param name="jquery_cxselect" value="true" />
	<jsp:param name="jquery_scrollTo" value="false" />
	<jsp:param name="jquery_jcallout" value="false" />
	<jsp:param name="jquery_validation" value="true" />
	<jsp:param name="jquery_datePicker" value="true" />
	<jsp:param name="jquery_fullcalendar" value="false" />
	<jsp:param name="jquery_fngantt" value="false" />
	<jsp:param name="jquery_fixedtableheader" value="true" />
	<jsp:param name="jquery_placeholder" value="true" />
	<jsp:param name="jquery_iealert" value="false" />
</jsp:include>
<script type="text/javascript">
	function saveOrg(){
		var addResult = [];
		var delResult = [];
		$(":checkbox:checked:not([isResult])").each(function(){
			addResult.push(this.value);
		});
		$(":checkbox[isResult]:not(:checked)").each(function(){
			delResult.push($(this).attr("isResult"));
		});
		
		var data = "";
		if(addResult.length>0){
			data += "&";
			data+=serializeList("jointOrgFlows",addResult);
		}
		if(delResult.length>0){
			data+=("&"+serializeList("delJointFlows",delResult));
		}
		if((addResult.length+delResult.length)<=0){
			top.jboxTip("${GlobalConstant.SAVE_SUCCESSED}");
			window.parent.document.mainIframe.search();
			return jboxClose();
		}
		var url = "<s:url value='/res/platform/save'/>?orgFlow=${org.orgFlow}";
		jboxPost(url,data, function(obj) {
			if(obj=="${GlobalConstant.SAVE_SUCCESSED}"){
				window.parent.document.mainIframe.search();
				jboxClose();
				}
			});
		}
	function serializeList(name,list){
		var result = "";
		for(var index in list){
			if(result){
				result+=("&"+name+"="+list[index]);
			}else{
				result+=(name+"="+list[index]);
			}
		}
		return result;
	}
	//模糊查询
	function likeSearch(name){
		if(name){
			$("[orgName]").hide();
			$("[orgName*='"+name+"']").show();
		}else{
			$("[orgName]").show();
		}
	}
	function save(){
		var trs = $(':checkbox:checked');
		var datas =[];
		$.each(trs , function(i , n){
			var obj={}; 
			var orgFlow=$(n).val();
			var orgName=$("#"+orgFlow).text();
			orgName=$.trim(orgName);
			obj.orgFlow=orgFlow;
			obj.orgName=orgName;
			obj.rotationFlow=$("#rotationFlow").val();
			obj.rotationName=$("#rotationName").val();
			obj.recordFlow=$(n).attr("nextVal");
			datas.push(obj);
		});
		 var url = "<s:url value='/res/rotation/saveSun'/>";
		jboxStartLoading();
		jboxPostJson(url,JSON.stringify(datas),function(){
			jboxClose();
		},null,true);
	}
</script>
</head>
<body>
	<div class="mainright">
		<div class="content">
				<table class="basic" style="width: 100%;">
				<input type="hidden" id="rotationFlow" name="rotationFlow" value="${schRotation.rotationFlow}"/>
				<input type="hidden" id="rotationName" name="rotationName" value="${schRotation.rotationName}"/>
					<tr>
						<th style="width: 10%; text-align:left; padding-left: 10px;">培训方案名称:</th>
						<td>${schRotation.rotationName}</td>
					</tr>
					<tr>
						<th colspan="2" style="text-align: left; padding-left: 10px;">
							关联基地
							<input placeholder="输入基地名称搜索" type="text" value="" style="float: right;" onkeyup="likeSearch(this.value);"/>
						</th>
					</tr>
					<tr>
							<td colspan="2" id="jointId">
								<c:forEach items="${sysOrgs}" var="orgl">
									<div style="width: 24%;float: left;" orgName="${orgl.orgName}"> 
										<!-- <c:if test="${!empty jointMap[orgl.orgFlow]}">checked isResult="${jointMap[orgl.orgFlow].jointFlow}"</c:if> -->
										<label>
										<input type="checkbox" name="jointOrgFlows" nextVal="${recordFlowMap[orgl.orgFlow]}" value="${orgl.orgFlow}" <c:if test="${map[orgl.orgFlow].recordStatus eq GlobalConstant.RECORD_STATUS_Y}">checked</c:if>/>
										<span id="${orgl.orgFlow}">
											${orgl.orgName}
										</span>
										
										</label>
									</div>
								</c:forEach>
							</td>
					</tr>
				</table>
				<div class="button" style="width: 800px;">
					<input class="search" type="button" value="保&#12288;存" onclick="save();"/>
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
				</div>
		</div>
	</div>
</body>
</html>