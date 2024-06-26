
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
<script type="text/javascript">
function nextOpt(step){
	if(false==$("#projForm").validationEngine("validate")){
		return false;
	}
	var form = $('#projForm');
	form.append('<input type="hidden" name="nextPageName" value="'+step+'"/>');
	$('#nxt').attr({"disabled":"disabled"});
	$('#prev').attr({"disabled":"disabled"});
	jboxStartLoading();
	form.submit();
}

$(document).ready(function(){
	if($("#projFileTb tr").length<=0){
		addFile('projFileTb');
	}
});

function addFile(tb){
	var myDate = new Date();
	var createTime = myDate.getTime();
	var html = '<tr>'+
			'<td style="text-align: center"><input type="checkbox"/></td>'+
			'<td style="text-align: left;">&#12288;<input name="fileAddTime" type="hidden" value="'+createTime+'"/><input type="file" name="file" class="validate[required]"/></td>'+
			'</tr>';
	$('#'+tb).append(html);
}

function delTr(tb){
	var trs = $('#'+tb).find(':checkbox:checked');
	jboxConfirm("确认删除？" , function(){
		$.each(trs , function(i , n){
			$(n).parent('td').parent("tr").remove();
		});
	});
}
</script>
</c:if>

	<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" enctype="multipart/form-data" style="position: relative;">
		<input type="hidden" id="pageName" name="pageName" value="step15" /> 
		<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
		<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
		<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
		
		<table width="100%" cellspacing="0" cellpadding="0" class="bs_tb">
			<tr>
				<th colspan="2" class="theader">附件信息
					<c:if test="${param.view!=GlobalConstant.FLAG_Y}"><span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addFile('projFileTb')"></img></a>&#12288;<a href="javascript:void(0)"><img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('projFileTb')"></img></a></span></c:if>
				</th>
			</tr>
			<tr>
				<td width="3%" style="font-weight:bold;"></td>
				<td width="97%" style="font-weight:bold;">附件名称</td>
			</tr>
			<tbody id="projFileTb">
			<c:forEach var="file" items="${pageFileMap}" varStatus="status">
			<c:choose>
  				<c:when test="${param.view==GlobalConstant.FLAG_Y}">
  					<tr>
					<td style="text-align: center;">${status.count}</td>
					<td ><input name="file" value="${file.key}" type="hidden"/><a href='<s:url value="/pub/file/down?fileFlow=${file.key}"/>'>${file.value.fileName}</a></td>
				</tr>
                   </c:when>
  				<c:otherwise>
  					<tr>
					<td style="text-align: center;"><input type="checkbox"/></td>
					<td style="text-align: left;">&#12288;<input name="file" value="${file.key}" type="hidden"/><a href='<s:url value="/pub/file/down?fileFlow=${file.key}"/>'>${file.value.fileName}</a></td>
				</tr>
                    <tr style="display: none">
                        <td><input type="hidden" name="file_flow" value="${file.key}"/></td>
                    </tr>
  				</c:otherwise>
  			</c:choose>
			</c:forEach>
			</tbody>
		</table>
	</form>
		
	<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px; width: 100%">
		<input id="prev" type="button" onclick="nextOpt('step14')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('finish')" class="search" value="完&#12288;成"/>
	</div>
	</c:if>
