<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
	<jsp:param name="impromptu" value="true"/>
</jsp:include>
<script type="text/javascript">
<%--$(document).ready(function(){--%>
<%--	checkDisabled();--%>
<%--	<c:if test="${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">--%>
<%--		var $speId = $("input[name='speId']");--%>
<%--		$speId.attr("disabled", true); --%>
<%--	</c:if>--%>
<%--});--%>

<%--function checkDisabled(){--%>
<%--	var orgFlow="${orgFlow}";--%>
<%--	if(orgFlow == ""){--%>
<%--		var $speId = $("input[name='speId']");--%>
<%--		$speId.attr("disabled", true); --%>
<%--	}--%>
<%--}--%>

<%--function saveOrgSpeManage(obj){--%>
<%--	jboxStartLoading();--%>
<%--	var orgFlow="${orgFlow}";--%>
<%--	var orgName="${orgName}";--%>
<%--	var id = $(obj).attr("id");--%>
<%--	var speId = $(obj).val();--%>
<%--	var speName = $("#"+id+"_speName").val();--%>
<%--	var speTypeId = $("#"+id+"_speTypeId").val();--%>
<%--	var speTypeName = $("#"+id+"_speTypeName").val();--%>
<%--	var checkedVal = $(obj).attr("checked");--%>
<%--	var recordStatus = "${GlobalConstant.RECORD_STATUS_N}";--%>
<%--	if("checked" == checkedVal){--%>
<%--		recordStatus = "${GlobalConstant.RECORD_STATUS_Y}";--%>
<%--	}--%>
<%--	var data = {--%>
<%--		"speId":speId,--%>
<%--		"speName":speName,--%>
<%--		"speTypeId":speTypeId,--%>
<%--		"speTypeName":speTypeName,--%>
<%--		"orgFlow":orgFlow,--%>
<%--		"orgName":orgName,--%>
<%--		"recordStatus":recordStatus--%>
<%--	};--%>
<%--	var url = "<s:url value='/jsres/base/saveOrgSpeManage'/>";--%>
<%--	jboxPost(url, data,--%>
<%--		function(resp){--%>
<%--			jboxEndLoading();--%>
<%--			/* if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){--%>
<%--				search();--%>
<%--			} */--%>
<%--		}, null, true);--%>
<%--}--%>

function saveOrgSpeManage(speTypeId,speTypeName,speId,speName,status){
	jboxStartLoading();
	var orgFlow="${orgFlow}";
	var orgName="${orgName}";
	var sessionYear="${sessionYear}";
	var data = {
		"speId":speId,
		"speName":speName,
		"speTypeId":speTypeId,
		"speTypeName":speTypeName,
		"orgFlow":orgFlow,
		"orgName":orgName,
		"status":status,
		"sessionYear":sessionYear
	};
	var url = "<s:url value='/jsres/base/saveOrgSpeManage'/>";
	var msg;
	if (status == "open") {
		msg = "确认开启此专业基地？"
	}
	if (status == "close") {
		msg = "确认关闭此专业基地？"
	}
	if (status == "stop") {
		msg = "确认停止招录此专业基地？"
	}
	jboxConfirm(msg, function () {
		jboxPost(url, data,
				function(){
					jboxEndLoading();
					search();
				}, null, true)
	}, function () {
		jboxEndLoading();
		search();
	});
}

function dialogCapacity(speTypeId, speId) {
	var orgFlow="${orgFlow}";
	var sessionYear="${sessionYear}";
	var id = "pop" + speTypeId + speId;
	$.prompt({
		state0: {
			title: "请输入最小培训容量",
			html: "<input type='text' id='" + id + "' maxlength='10' class='input' style='width=105px;' />",
			buttons: {确定: 1, 取消: 0},
			submit:function(e,v,m,f){
				if(v == 1) {
					var capacity = $("#" + id).val();
					var reg = /^[0-9]{1,10}$/;
				if (!reg.test(capacity) || Number.parseInt(capacity) < 0) {
						$.prompt('请输入不小于0的整数', {
							title: "提示",
							buttons: {确定: 1},
							classes: {
							}
						});
					}else {
						var data = {
							"speId":speId,
							"speTypeId":speTypeId,
							"orgFlow":orgFlow,
							"sessionYear":sessionYear,
							minRecruitCapacity: capacity
						}
						var url = "<s:url value='/jsres/base/saveOrgSpeManage'/>";
						jboxPost(url, data,
								function(){
									search();
								}, null, false);
					}
				}
			}
		}
	});
}

</script>
	<form id="checkedForm">
<%--        <table border="0" cellpadding="0" cellspacing="0" class="grid">--%>
<%--        	<colgroup>--%>
<%--        		<col style="text-align: left;padding-left: 40px;width:200px;"/>--%>
<%--        		<col style="text-align: left;padding-left: 40px;"/>--%>
<%--        	</colgroup>--%>
<%--        	<tr>--%>
<%--                <th>培训类别</th>--%>
<%--                <th>专业名称</th>--%>
<%--            </tr>--%>
<%--            <c:forEach items="${trainCategoryEnumList}" var="speType" varStatus="status">--%>
<%--				<c:if test="${speType.id eq 'DoctorTrainingSpe' or speType.id eq 'AssiGeneral'}">--%>
<%--				<tr>--%>
<%--					<td>${speType.name }</td>--%>
<%--					<td style="text-align: left;padding-left: 20px;line-height: 40px;">--%>
<%--						<c:set var="dictName" value="dictTypeEnum${speType.id }List" />--%>
<%--						<c:forEach items="${applicationScope[dictName] }" var="dict">--%>
<%--							<c:set var="key" value="${speType.id }${dict.dictId }" />--%>
<%--							<input type="hidden" id="${key}_speTypeId" name="speTypeId" value="${speType.id }" />--%>
<%--							<input type="hidden" id="${key}_speTypeName" name="speTypeName" value="${speType.name }" />--%>
<%--							<input type="hidden" id="${key}_speName" name="speName" value="${dict.dictName }" />--%>
<%--							<label style="display: inline-block; padding-left: 10px; cursor: pointer;">--%>
<%--								<input type="checkbox" id="${key}" name="speId" value="${dict.dictId }" onclick="saveOrgSpeManage(this)" style="vertical-align: middle; cursor: pointer;" <c:if test="${not empty orgSpeMap[key]}">checked="checked"</c:if>/>&nbsp;${dict.dictName }--%>
<%--							</label>--%>
<%--						</c:forEach>--%>
<%--					</td>--%>
<%--				</tr>--%>
<%--				</c:if>--%>
<%--			</c:forEach>--%>
<%--        </table>--%>
		<table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>
        		<col style="text-align: left;padding-left: 40px;width: 20%;"/>
        		<col style="text-align: left;padding-left: 40px;width: 30%"/>
        		<col style="text-align: left;padding-left: 40px;"/>
				<c:if test="${not empty orgFlow and sessionYear eq pdfn:getCurrYear().toString()}"><col style="text-align: left;padding-left: 40px;"/></c:if>
        	</colgroup>
        	<tr>
				<th>专业基地编码</th>
				<th>专业基地名称</th>
				<th>操作</th>
				<c:if test="${not empty orgFlow and sessionYear eq pdfn:getCurrYear().toString()}"><th>最小培训容量</th></c:if>
            </tr>
            <c:forEach items="${trainCategoryEnumList}" var="speType" varStatus="status">
				<c:if test="${speType.id eq 'DoctorTrainingSpe'}">
					<c:set var="dictName" value="dictTypeEnum${speType.id }List" />
					<c:forEach items="${applicationScope[dictName] }" var="dict">
						<c:set var="key" value="${speType.id }${dict.dictId }" />
						<tr>
							<td>${dict.dictId }</td>
							<td>${dict.dictName }</td>
							<td>
								<input type="radio" id="${key}status" name="${key}status" value="open" style="vertical-align: middle; cursor: pointer;" onclick="saveOrgSpeManage('${speType.id}','${speType.name}','${dict.dictId}','${dict.dictName}','open')"
									   <c:if test="${empty orgFlow or empty sessionYear}"> disabled </c:if> <c:if test="${not empty orgFlow and not empty sessionYear and orgSpeMap[key].status eq 'open'}">checked="checked"</c:if>/>&nbsp;开启&nbsp;
								<c:choose>
									<c:when test="${orgFlow ne 'ALL'}">
										<input type="radio" id="${key}status" name="${key}status" value="close" style="vertical-align: middle; cursor: pointer;" onclick="saveOrgSpeManage('${speType.id}','${speType.name}','${dict.dictId}','${dict.dictName}','close')"
												<c:if test="${empty orgFlow or empty sessionYear}"> disabled </c:if> <c:if test="${not empty orgFlow and not empty sessionYear and (orgSpeMap[key].status eq 'close' or empty orgSpeMap[key].status)}">checked="checked"</c:if>/>&nbsp;关闭&nbsp;
									</c:when>
									<c:otherwise>
										<input type="radio" id="${key}status" name="${key}status" value="close" style="vertical-align: middle; cursor: pointer;" onclick="saveOrgSpeManage('${speType.id}','${speType.name}','${dict.dictId}','${dict.dictName}','close')"
												<c:if test="${empty orgFlow or empty sessionYear}"> disabled </c:if> <c:if test="${not empty orgFlow and not empty sessionYear and orgSpeMap[key].status eq 'close'}">checked="checked"</c:if>/>&nbsp;关闭&nbsp;
									</c:otherwise>
								</c:choose>

								<input type="radio" id="${key}status" name="${key}status" value="stop" style="vertical-align: middle; cursor: pointer;" onclick="saveOrgSpeManage('${speType.id}','${speType.name}','${dict.dictId}','${dict.dictName}','stop')"
									   <c:if test="${empty orgFlow or empty sessionYear}"> disabled </c:if> <c:if test="${not empty orgFlow and not empty sessionYear and orgSpeMap[key].status eq 'stop'}">checked="checked"</c:if>/>&nbsp;停止招录&nbsp;
							</td>
							<c:if test="${not empty orgFlow and sessionYear eq pdfn:getCurrYear().toString()}">
							<td>
								<input type="text" id='${key}Capacity' class="input" value="${null != orgSpeMap[key] ? orgSpeMap[key].minRecruitCapacity : ''}" <c:if test="${orgSpeMap[key].status eq 'close' or orgSpeMap[key].status eq 'stop'}">disabled</c:if> readonly onclick="dialogCapacity('${speType.id }', '${dict.dictId }')">
							</td>
							</c:if>
						</tr>
					</c:forEach>
				</c:if>
			</c:forEach>
        </table>
    </form>
