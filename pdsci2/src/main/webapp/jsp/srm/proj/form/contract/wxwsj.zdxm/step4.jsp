
<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
<script type="text/javascript">
	$(document).ready(function(){
		if($("#yptTb tr").length<=0){
			add('ypt');
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
		<input type="hidden" id="pageName" name="pageName" value="step4" /> 
		<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
		<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
		<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
		
		
		<font style="font-size: 14px; font-weight:bold;color: #333; ">三、项目计划进度</font>
		<table class="bs_tb" style="width: 100%;margin-top: 10px; border-bottom-style: none;">
			<tr>
				<th colspan="10" class="theader">年度计划及年度目标
					<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
					<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('progressPlan')"></img>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('progressPlan');"></img></span>
					</c:if>
				</th>
			</tr>
			<tr>
				<td width="4%"></td>
				<td width="5%">序号</td>
				<td width="20%">时间安排</td>
				<td width="70%">明确分阶段进度与目标（包括时间进度安排、研究地点、参加人数、经费投入、阶段成果，所采取的主要方法和技术路线等，原则上按照季度或半年划分）</td>
			</tr>
			<tbody id="progressPlanTb">
			<c:if test="${not empty resultMap.progressPlan}">
				<c:forEach var="progress" items="${resultMap.progressPlan}" varStatus="status">
				<tr>
		             <td width="4%" style="text-align: center;"><input name="progressPlanIds" type="checkbox"/></td>
		             <td width="5%" style="text-align: center;" class="progressPlanSerial">${status.count}</td>
		             <td>
		             	 <input class="inputText ctime" type="text" name="progressPlan_startTime" value="${progress.objMap.progressPlan_startTime}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 70px; margin-right: 0px;text-align:left;"/>
		             	 ~<input class="inputText ctime" type="text" name="progressPlan_endTime" value="${progress.objMap.progressPlan_endTime}" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 70px;text-align:left;"/>
		             </td>
		             <td style="text-align:left;">
			     		 <textarea placeholder="" class="validate[maxSize[1000]] xltxtarea"  name="progressPlan_content">${progress.objMap.progressPlan_content}</textarea>
			     	 </td>
				</tr>
			    </c:forEach>
	    	</c:if>
			</tbody>
			<tr>
				<td colspan="4" style="text-align: left;">
					&#12288;本项目应于前结题。
                    本项目应于<input class="inputText ctime" type="text" name="projectCenterTime" value="${resultMap.projectCenterTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 120px;margin: 0px;"/>前完成中期进展报告，
                    于<input class="inputText ctime" type="text" name="finishTime" value="${resultMap.finishTime}" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 120px;margin: 0px;"/>月前进行验收。
                </td>
			</tr>
		</table>
	</form>
	
	<!-------------------------------------------------------------------------------------------------------->
		
	<div style="display: none">
		<!-- 模板 -->
		<table class="basic" id="progressPlanTemplate" style="width: 100%">
        <tr>
             <td width="4%" style="text-align: center;"><input name="progressPlanIds" type="checkbox"/></td>
             <td width="5%" style="text-align: center;" class="progressPlanSerial"></td>
             <td>
             	 <input class="inputText ctime" type="text" name="progressPlan_startTime" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 70px; margin-right: 0px;text-align:left;"/>
             	 ~<input class="inputText ctime" type="text" name="progressPlan_endTime" onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly" style="width: 70px;text-align:left;"/>
             </td>
             <td style="text-align:left;">
	     		 <textarea placeholder="" class="validate[maxSize[1000]] xltxtarea"  name="progressPlan_content"></textarea>
	     	 </td>
		</tr>
		</table>
	</div>	
		
   	<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
		<div align="center" style="margin-top: 10px">
		    <input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
		    <input id="nxt" type="button" onclick="nextOpt('step5')" class="search" value="下一步"/>
       	</div>
    </c:if>

		