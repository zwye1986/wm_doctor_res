
<script type="text/javascript">

function saveOrgSpeManage(obj){
	jboxStartLoading();
	var speId = "${param.trainingSpeId}";
	var speName = $("select[name=trainingSpeId] option[value='${param.trainingSpeId}']").text();
	var practiceId = $(obj).attr("practiceId");
	var practiceName = $(obj).attr("practiceName");
	var typeId = $(obj).attr("typeId");
	var typeName = $(obj).attr("typeName");
	var recordFlow = $(obj).attr("recordFlow");
	var checkedVal = $(obj).attr("checked");
	var recordStatus = "${GlobalConstant.RECORD_STATUS_N}";
	if("checked" == checkedVal){
		recordStatus = "${GlobalConstant.RECORD_STATUS_Y}";
	}
	var data = {
		"speId":speId,
		"speName":speName,
		"typeId":typeId,
		"typeName":typeName,
		"practiceId":practiceId,
		"practiceName":practiceName,
		"recordFlow":recordFlow,
		"recordStatus":recordStatus
	};
	var url = "<s:url value='/sys/spePractice/save'/>";
	jboxPost(url, data,
		function(resp){
			jboxEndLoading();
			if(resp!="${GlobalConstant.SAVE_SUCCESSED}"){
				if(recordStatus == "${GlobalConstant.RECORD_STATUS_Y}")
					$(obj).attr("checked",false);
				if(recordStatus == "${GlobalConstant.RECORD_STATUS_N}")
					$(obj).attr("checked",true);
			}
		}, null, false);
}
</script>
	<form id="checkedForm">
		<table class="xllist" style="width:100%;margin-top: 10px;text-align: center">
        	<colgroup>
        		<col style="text-align: left;padding-left: 40px;width:200px;"/>
        		<col style="text-align: left;padding-left: 40px;"/>
        	</colgroup>
        	<tr>
                <th>执业类型</th>
                <th>执业范围</th>
            </tr>
			<c:forEach items="${dictTypeEnumPracticeTypeList}" var="dict">
				<tr>
					<td>${dict.dictName }</td>
					<td style="text-align: left;padding-left: 20px;line-height: 40px;">
						<c:set var="dictName" value="dictTypeEnumPracticeType.${dict.dictId }List" />
						<c:forEach items="${applicationScope[dictName] }" var="scope">
							<c:set var="key" value="${dict.dictId }${scope.dictId }" />
							<label style="display: inline-block; padding-left: 10px; cursor: pointer;">
								<input type="checkbox" id="${key}" typeId="${dict.dictId}"  typeName="${dict.dictName}"  practiceId="${scope.dictId }" practiceName="${scope.dictName}" recordFlow="${spePracticeMap[key].recordFlow}"
									   onclick="saveOrgSpeManage(this)" style="vertical-align: middle; cursor: pointer;"
									<c:if test="${canCheck eq 'N'}">disabled</c:if>
									   <c:if test="${not empty spePracticeMap[key]}">checked="checked"</c:if>/>&nbsp;${scope.dictName }
							</label>
						</c:forEach>
					</td>
				</tr>
			</c:forEach>
        </table>
    </form>
