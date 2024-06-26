<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
   							<td width="30%" style="text-align: right">中心名称：</td>
    						<td>
    							<input type="text" name="projName" value="<c:if test='${empty resultMap.projName and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${! empty resultMap.projName}'>${resultMap.projName}</c:if>" class="inputText" style="width: 46%"/>
    						</td>
  						  </tr>
  						  <tr>
   							<td width="30%" style="text-align: right">中心主任：</td>
    						<td><input type="text" name="subjectDirector" value="${resultMap.subjectDirector}" class="inputText" style="width: 46%"/></td>
  						  </tr>
  						  <tr>
   							<td width="30%" style="text-align: right">建设单位（甲方）：</td>
    						<td>
    							<input type="text" name="constructOrg" 
    									 <c:choose>
    									 	<c:when test="${empty resultMap.constructOrg}">value="苏州市卫计委"</c:when>
		    								<c:otherwise>value="${resultMap.constructOrg}"</c:otherwise>
    									 </c:choose>
		    							 class="inputText" style="width: 46%"/>
    						</td>
  						  </tr>
  						  <tr>
   							<td width="30%" style="text-align: right">承担单位（乙方）：</td>
    						<td><input type="text" name="orgName" value="<c:if test='${empty resultMap.orgName and param.view!=GlobalConstant.FLAG_Y}'>${proj.applyOrgName}</c:if><c:if test='${! empty resultMap.orgName}'>${resultMap.orgName}</c:if>" class="inputText" style="width: 46%"/></td>
  						  </tr>
						  <tr>
						    <td width="30%" style="text-align: right">合同起止年限：</td>
						    <td>
						   	 	<input type="text" name="contractStartTime" value="<c:if test='${empty resultMap.contractStartTime}'>${applicationScope.sysCfgMap['srm_contract_start_time']}</c:if><c:if test='${! empty resultMap.contractStartTime}'>${resultMap.contractStartTime}</c:if>" class="inputText" style="margin-right: 0px;" readonly="readonly" <c:if test="${empty applicationScope.sysCfgMap['srm_contract_start_time']}">onClick="WdatePicker({dateFmt:'yyyy'})"</c:if>/>~
						   	 	<input type="text" name="contractEndTime" value="<c:if test='${empty resultMap.contractEndTime}'>
						   	 	<%--${pdfn:addYear(applicationScope.sysCfgMap['srm_contract_end_time'],2)} 临床医学中心合同时间写死--%>2020-12-31</c:if>
						   	 	<c:if test='${! empty resultMap.contractEndTime}'>${resultMap.contractEndTime}</c:if>" class="inputText" readonly="readonly" <c:if test="${empty applicationScope.sysCfgMap['srm_contract_end_time']}">onClick="WdatePicker({dateFmt:'yyyy'})"</c:if>/>
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