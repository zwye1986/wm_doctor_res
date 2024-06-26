
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
<script type="text/javascript">
	function nextOpt(step, targetFormId){
		var files = $('#projFileTb input[name="file"]');
		if(files.length==1&&!$(files[0]).val()){
			$(files[0]).parents("tr").remove();
		}
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
			'<td style="text-align: center;"><input type="checkbox"/></td>'+
			'<td style="text-align: left;">&#12288;<input name="fileAddTime" type="hidden" value="'+createTime+'"/><input type="file" name="file" class="validate[required]"/></td>'+
		'</tr>';
		$('#'+tb).append(html);
	}
	function delTr(tb){
		var trs = $('#'+tb).find(':checkbox:checked');
		if(trs.length==0){
			jboxTip("请勾选要删除的！");
			return false;
		}
		jboxConfirm("确认删除？" , function(){
			$.each(trs , function(i , n){
				$(n).parent('td').parent("tr").remove();
				
			});
			
		});
	}
</script>
</c:if>

	<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
		id="projForm" enctype="multipart/form-data">
		<input type="hidden" id="pageName" name="pageName" value="step8" /> 
		<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
		<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
		<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
		
		<table width="100%" cellspacing="0" cellpadding="0" class="basic">
			<tr>
				<th colspan="2"  style="text-align: left;padding-left: 20px;font-size: 14px;">九、附件清单
					<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
					<span style="float: right;padding-right: 10px">
					<a href="javascript:void(0)"><img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addFile('projFileTb')"></img></a>
					<a href="javascript:void(0)"><img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('projFileTb')"></img></a></span></c:if>
				</th>
			</tr>
			<tr>
				<td style="text-align: center;" width="60">序号</td>
				<td style="text-align: left;padding-left: 10px;">附件名称</td>
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
		
		<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
		<div align="center" style="margin-top: 10px">
		    <input id="prev" type="button" onclick="nextOpt('step7')" class="search" value="上一步"/>
		    <input id="nxt" type="button" onclick="nextOpt('finish')" class="search" value="完&#12288;成"/>
       	</div>
       	</c:if>
	</form>
	</body>