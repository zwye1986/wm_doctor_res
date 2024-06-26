
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

function doBack(){
	window.location.href="<s:url value='/srm/proj/mine/process?projFlow='/>"+$("#projFlow").val();
}
</script>
</c:if>

        	    	<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
						<input type="hidden" name="pageName" value="homePage"/>
						<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                        <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
						<input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
						
						<table class="basic" style="width: 100%;margin-top: 10px;">
						  <tr>
						  	<th colspan="2" style="text-align: left;padding-left: 15px;font-size: 14px;">基本信息</th>
						  </tr>
						  <tr>
   							<td width="30%" style="text-align: right">项目编号：</td>
    						<td>
    							<input type="text" name="projNo" value="${proj.projNo}" readonly="readonly" class="inputText" style="width: 46%"/>
    						</td>
  						  </tr>
  						  <tr>
   							<td width="30%" style="text-align: right">项目名称：</td>
    						<td>
    							<input type="text" name="projName" value="<c:if test='${empty resultMap.projName and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${! empty resultMap.projName}'>${resultMap.projName}</c:if>" class="inputText" style="width: 46%"/>
    						</td>
  						  </tr>
  						  <tr>
   							<td width="30%" style="text-align: right">承担单位：</td>
    						<td><input type="text" name="orgName" value="<c:if test='${empty resultMap.orgName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyOrgName}</c:if><c:if test='${! empty resultMap.orgName}'>${resultMap.orgName}</c:if>" class="inputText" style="width: 46%"/></td>
  						  </tr>
						  <tr>
						    <td width="30%" style="text-align: right">承担单位主管部门：</td>
						    <td><input type="text" name="chargeOrgName" value="<c:if test='${empty resultMap.chargeOrgName and param.view!=GlobalConstant.FLAG_Y}'>${org.chargeOrgName}</c:if><c:if test='${! empty resultMap.chargeOrgName}'>${resultMap.chargeOrgName}</c:if>" class="inputText" style="width: 46%"/></td>
						  </tr>
						    <tr>
   							<td width="30%" style="text-align: right">项目负责人：</td>
    						<td><input type="text" name="applyUserName" value="<c:if test='${empty resultMap.applyUserName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyUserName}</c:if><c:if test='${! empty resultMap.applyUserName}'>${resultMap.applyUserName}</c:if>" class="inputText" style="width: 46%"/></td>
  						  </tr>
						  <tr>
						    <td width="30%" style="text-align: right">起止年限：</td>
						    <td>
						   	 	<input type="text" name="contractStartTime" value="<c:if test='${empty resultMap.contractStartTime}'>${applicationScope.sysCfgMap['srm_contract_start_time']}</c:if><c:if test='${! empty resultMap.contractStartTime}'>${resultMap.contractStartTime}</c:if>" class="inputText" style="margin-right: 0px;" readonly="readonly" <c:if test="${empty applicationScope.sysCfgMap['srm_contract_start_time']}">onClick="WdatePicker({dateFmt:'yyyy'})"</c:if>/>~
						   	 	<input type="text" name="contractEndTime" value="<c:if test='${empty resultMap.contractEndTime}'>${applicationScope.sysCfgMap['srm_contract_end_time']}</c:if><c:if test='${! empty resultMap.contractEndTime}'>${resultMap.contractEndTime}</c:if>" class="inputText" readonly="readonly" <c:if test="${empty applicationScope.sysCfgMap['srm_contract_end_time']}">onClick="WdatePicker({dateFmt:'yyyy'})"</c:if>/>
						    </td>
						  </tr>
					 </table>
					 </form>
                	 <div class="button" style="width:100%;
                	 <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
                	 	<input id="prev" type="button" onclick="doBack()" class="search" value="返&#12288;回"/>
                	    <input id="nxt" type="button" onclick="nextOpt('step2')" class="search" value="下一步"/>
      			     </div>

</body>
</html>