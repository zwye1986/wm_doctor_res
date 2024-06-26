
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
$(function(){ 
    var tables=$(".tb");
    $.each(tables , function(i , n){
    	var count=$(n).children().length;
    	var id=$(n).parent().attr("id");
    	$("input[name="+id+"Num]").val(count);
    });
});
function delTr(tb){
	var checkboxs = $("input[name='ids']:checked");
	if(checkboxs.length==0){
		jboxTip("请勾选要删除的！");
		return false;
	}
	jboxConfirm("确认删除?",function () {
		var trs = $('#'+tb+'Tb').find(':checkbox:checked');
		$.each(trs , function(i , n){
			$(n).parent('td').parent("tr").remove();
		});
		//计算行数
		var length = $("#"+tb+"Tb").children().length;
		$("input[name="+tb+"Num]").val(length);
		//删除后序号
		var serial = 0;
		$("."+tb+"Serial").each(function(){
			serial += 1;
			$(this).text(serial);
		});
	});
}

function addProj(tb,obj){
	var length = $("#"+tb+"Tb").children().length;
	if(length > 9){
		jboxTip("限填10项!");
		return false; 
	}
	$("input[name="+tb+"Num]").val(length+1);
	var html = '<tr>'+
	'<td width="50px"><input name="ids" type="checkbox"/></td>'+
	'<td width="50px" class="praiseSerial"></td>'+
	'<td><input type="text" class="inputText"  name="praise_praiseTime" style="width:80%;" onclick="WdatePicker({dateFmt:'+"'yyyy-MM-dd'"+'})" readonly="readonly"/></td>'+
	'<td><input type="text" class="inputText" name="praise_praiseName" style="width:80%;"/></td>'+
	'<td><input type="text" class="inputText" name="praise_completePerson" style="width:80%;"/></td>'+
	'<td><input type="text" class="inputText" name="praise_praiseDepartment" style="width:80%;"/></td>'+
	'<td><input type="text" class="inputText" name="praise_praiseGrade" style="width:80%;"/></td>'+
	'</tr>'; 
	$('#'+tb+'Tb').append(html);
	//序号
	$("#"+tb+"Tb").children("tr").last().children("td").eq(1).text(length+1);
}
</script>
</c:if>


        	    	<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
						<input type="hidden" name="pageName" value="step7"/>
						<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                        <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
						<input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
   					<table class="bs_tb" style="width: 100%;margin-top: 10px;" id="praise">
   						<tr>
	   						<th colspan="7" class="theader">表8：近五年为第一完成人的市级及以上科技进步奖共 <input type="text" name="praiseNum" value="${resultMap.praiseNum}" class="validate[custom[integer]] inputText" style="width:30px;"/> 项。（限填10项代表作）
	   						<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
	   						<span style="float: right;padding-right: 10px">
	   						<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="addProj('praise',this);"></img>&nbsp;
	   						<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('praise');"></img>
	   						</span> 
	   						</c:if>
	   						</th>
   						</tr>
       					<tr>
			           		<td style="font-weight:bold;" width="3%"></td>
			           		<td style="font-weight:bold;" width="5%">序号</td>
			                <td style="font-weight:bold;" >获奖时间</td>
			                <td style="font-weight:bold;" >名称</td>
			                <td style="font-weight:bold;" >完成人</td>
			                <td style="font-weight:bold;" >奖励部门</td>
			                <td style="font-weight:bold;" >获奖等级</td>
          				</tr>
          			<tbody id="praiseTb" class="tb">
          			<c:if test="${empty resultMap.praise}">
          			   <tr>
				           <td width="3%"><input name="ids" type="checkbox"/></td>
				           <td width="3%" style="text-align:center;" class="praiseSerial">1</td>
				           <td>
				           		<input type="text"  class="inputText"  name="praise_praiseTime" style="width:80%;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
				           </td>
				           <td>
				           		<input type="text"  class="inputText" name="praise_praiseName"  style="width:80%;"/>
				           </td>
				           <td>
				           		<input type="text" class="inputText" name="praise_completePerson"  style="width:80%;"/>
				           </td>
				           <td>
				           		<input type="text" class="inputText" name="praise_praiseDepartment"  style="width:80%;"/>
				           </td>
				           <td>
				           		<input type="text" class="inputText" name="praise_praiseGrade"  style="width:80%;"/>
				           </td>
				        </tr>
          			
          			</c:if>
          			<c:if test="${! empty resultMap.praise}">
					<c:forEach var="praise" items="${resultMap.praise}" varStatus="status">
				        <tr>
				           <td width="3%"><input name="ids" type="checkbox"/></td>
				           <td width="3%" style="text-align:center;" class="praiseSerial">${status.count}</td>
				           <td>
				           		<input type="text"  class="inputText"  name="praise_praiseTime" value="${praise.objMap.praise_praiseTime}" style="width:80%;" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
				           </td>
				           <td>
				           		<input type="text"  class="inputText" name="praise_praiseName" value="${praise.objMap.praise_praiseName}" style="width:80%;"/>
				           </td>
				           <td>
				           		<input type="text" class="inputText" name="praise_completePerson" value="${praise.objMap.praise_completePerson}" style="width:80%;"/>
				           </td>
				           <td>
				           		<input type="text" class="inputText" name="praise_praiseDepartment" value="${praise.objMap.praise_praiseDepartment}" style="width:80%;"/>
				           </td>
				           <td>
				           		<input type="text" class="inputText" name="praise_praiseGrade" value="${praise.objMap.praise_praiseGrade}" style="width:80%;"/>
				           </td>
				        </tr>
				    </c:forEach>
				    </c:if>
				    </tbody>
					</table>
					</form>
					
                	 <div class="button" style="width:100%;
                	 <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none;</c:if>">
                	 	<input id="prev" type="button" onclick="nextOpt('step6')" class="search" value="上一步"/>
                	    <input id="nxt" type="button" onclick="nextOpt('step8')" class="search" value="下一步"/>
      			     </div>
   