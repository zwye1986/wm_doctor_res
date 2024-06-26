
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
<script type="text/javascript">
	$(document).ready(function(){
		if($("#psTb tr").length<=0){
			add('ps');
		}
	});

	function nextOpt(step){
		if(false==$("#projForm").validationEngine("validate")){
			return false;
		}
		var form = $('#projForm');
		var action = form.attr('action');
		action+="?nextPageName="+step;
		form.attr("action" , action);
		form.submit();
	}
	
	
	function add(tb){
	 	$("#"+tb+"Tb").append($("#"+tb+"Template tr:eq(0)").clone());
	 	
	 	var length = $("#"+tb+"Tb").children().length;
	 	//序号
		$("#"+tb+"Tb").children("tr").last().children("td").eq(1).text(length);
	}
	
	function delTr(tb){
		//alert("input[name="+tb+"Ids]:checked");
		var checkboxs = $("input[name='"+tb+"Ids']:checked");
		if(checkboxs.length==0){
			jboxTip("请勾选要删除的！");
			return false;
		}
		jboxConfirm("确认删除?",function () {
			var trs = $('#'+tb+'Tb').find(':checkbox:checked');
			$.each(trs , function(i , n){
				$(n).parent('td').parent("tr").remove();
			});
			//删除后序号
			var serial = 0;
			$("."+tb+"Serial").each(function(){
				serial += 1;
				$(this).text(serial);
			});
		});
	}
</script>
</c:if>

	<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
		id="projForm">
		<input type="hidden" id="pageName" name="pageName" value="step6" /> 
		<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
		<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
		<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

		<font style="font-size: 14px; font-weight:bold;color: #333; ">六、计划进度目标</font>
		<table class="bs_tb" style="width: 100%;margin-top: 10px; border-bottom-style: none;">
			<tr>
				<th colspan="10" class="theader" style="text-align: left;padding-left: 10px;">计划进度目标
					<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
					<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('ps')"></img>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('ps');"></img></span>
					</c:if>
				</th>
			</tr>
			<tr>
				<th width="4%" style="text-align: center;"></th>
				<th width="5%" style="text-align: center;">序号</th>
				<th width="24%" style="text-align: center;">工作进度<span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span></th>
				<th width="67%" style="text-align: center;">主要工作内容</th>
			</tr>
			<tbody id="psTb">
			<c:if test="${not empty resultMap.planSchedule}">
				<c:forEach var="ps" items="${resultMap.planSchedule}" varStatus="status">
				<tr>
		             <td style="text-align: center;"><input name="psIds" type="checkbox"/></td>
		             <td style="text-align: center;" class="psSerial">${status.count}</td>
		             <td>
		             	 <input class="validate[required] inputText" type="text" name="planSchedule_startDate" value="${ps.objMap.planSchedule_startDate}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 40%; margin-right: 0px;"/>
		             	 ~<input class="inputText" type="text" name="planSchedule_endDate" value="${ps.objMap.planSchedule_endDate}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 40%;"/>
		             </td>
		             <td>
			     		 <input type="text" name="planSchedule_content" value="${ps.objMap.planSchedule_content}" class="inputText" style="width: 80%"/>
			     	 </td>
				</tr>
			    </c:forEach>
	    	</c:if>
			</tbody>
		</table>
		
		
		<!-- <font style="font-size: 14px; font-weight:bold;color: #333;">项目完成后主要考核指标</font> -->
		<table class="bs_tb" style="width: 100%; margin-top: 10px;" >
			<tr>
				<th class="theader">项目完成后主要考核指标：</th>
			</tr>
	        <tr>
		     	<td style="text-align:left;">
		     		<textarea placeholder=""  class="validate[maxSize[1000]] xltxtarea" style="height: 350px;" name="assessIndicators">${resultMap.assessIndicators}</textarea>
		     	</td>
		    </tr>
		</table>
	</form>
	
	<!-------------------------------------------------------------------------------------------------------->
		
	<div style="display: none">
		<!-- 模板 -->
		<table class="bs_tb" id="psTemplate" style="width: 100%">
        <tr>
             <td style="text-align: center;"><input name="psIds" type="checkbox"/></td>
             <td style="text-align: center;" class="psSerial"></td>
             <td>
             	 <input class="validate[required] inputText" type="text" name="planSchedule_startDate" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 37%;margin-right: 0px;"/>
             	 ~<input class="inputText" type="text" name="planSchedule_endDate" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 37%;"/>
             </td>
             <td>
	     		 <input type="text" name="planSchedule_content" class="inputText" style="width: 80%"/>
	     	 </td>
		</tr>
		</table>
	</div>	
   	<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
		<div align="center" style="margin-top: 10px">
		    <input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="上一步"/>
		    <input id="nxt" type="button" onclick="nextOpt('step7')" class="search" value="下一步"/>
       	</div>
    </c:if>

		