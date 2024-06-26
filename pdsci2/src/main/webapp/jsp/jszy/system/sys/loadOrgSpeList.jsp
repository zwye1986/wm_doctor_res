
<script type="text/javascript">
$(document).ready(function(){
	checkDisabled();
	<c:if test="${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">
		var $speId = $("input[name='speId']");
		$speId.attr("disabled", true); 
	</c:if>
});

function checkDisabled(){
	var orgFlow="${orgFlow}";
	if(orgFlow == ""){
		var $speId = $("input[name='speId']");
		$speId.attr("disabled", true); 
	}
}

function saveOrgSpeManage(obj){
	jboxStartLoading();
	var orgFlow="${orgFlow}";
	var orgName="${orgName}";
	var id = $(obj).attr("id");
	var speId = $(obj).val();
	var speName = $("#"+id+"_speName").val();
	var speTypeId = $("#"+id+"_speTypeId").val();
	var speTypeName = $("#"+id+"_speTypeName").val();
	var checkedVal = $(obj).attr("checked");
	var recordStatus = "${GlobalConstant.RECORD_STATUS_N}";
	if("checked" == checkedVal){
		recordStatus = "${GlobalConstant.RECORD_STATUS_Y}";
	}
	var data = {
		"speId":speId,
		"speName":speName,
		"speTypeId":speTypeId,
		"speTypeName":speTypeName,
		"orgFlow":orgFlow,
		"orgName":orgName,
		"recordStatus":recordStatus
	};
	var url = "<s:url value='/jszy/base/saveOrgSpeManage'/>";
	jboxPost(url, data,
		function(resp){
			jboxEndLoading();
			/* if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
				search();
			} */
		}, null, true);
}
</script>
	<form id="checkedForm">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
        	<colgroup>
                <col width="35%"/>
                <col width="65%"/>
        	</colgroup>
        	<tr>
                <th>培训专业</th>
                <th>对应专业</th>
            </tr>
            <c:forEach items="${jszyTrainCategoryEnumList}" var="speType" varStatus="status">
            	<tr>
            		<td>${speType.name }</td>
	                <td style="text-align: left;padding-left: 20px;line-height: 40px;">
						<c:set var="dictName" value="dictTypeEnum${speType.id }List" />
			            <c:forEach items="${applicationScope[dictName] }" var="dict">
							<c:set var="key" value="${speType.id }${dict.dictId }" />
			            	<input type="hidden" id="${key}_speTypeId" name="speTypeId" value="${speType.id }" />
			            	<input type="hidden" id="${key}_speTypeName" name="speTypeName" value="${speType.name }" />
			            	<input type="hidden" id="${key}_speName" name="speName" value="${dict.dictName }" />
				            <label style="display: inline-block; padding-left: 10px; cursor: pointer;min-width: 200px;">
				            	 <input type="checkbox" id="${key}" name="speId" value="${dict.dictId }" onclick="saveOrgSpeManage(this)" style="vertical-align: middle; cursor: pointer;" <c:if test="${not empty orgSpeMap[key]}">checked="checked"</c:if>/>&nbsp;${dict.dictName }
				            </label>
						</c:forEach>
	                </td>
	            </tr>
			</c:forEach>
        </table>
    </form>
