<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="false"/>
	<jsp:param name="jquery_ui_tooltip" value="true"/>
	<jsp:param name="jquery_ui_combobox" value="false"/>
	<jsp:param name="jquery_ui_sortable" value="false"/>
	<jsp:param name="jquery_cxselect" value="true"/>
	<jsp:param name="jquery_scrollTo" value="false"/>
	<jsp:param name="jquery_jcallout" value="false"/>
	<jsp:param name="jquery_validation" value="true"/>
	<jsp:param name="jquery_datePicker" value="true"/>
	<jsp:param name="jquery_fullcalendar" value="false"/>
	<jsp:param name="jquery_fngantt" value="false"/>
	<jsp:param name="jquery_fixedtableheader" value="true"/>
	<jsp:param name="jquery_placeholder" value="true"/>
	<jsp:param name="jquery_iealert" value="false"/>
	<jsp:param name="jquery_mask" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
$(document).ready(function(){
	moneyMask();
});

function moneyMask(){
	var moneyBox=$(".money");
	$.each(moneyBox,function(i,n){
		$(n).mask('000,000,000,000,000', {reverse: true});
	});
	
}

function save() {
	if(!$("#clientForm").validationEngine("validate")){
		return false;
	}
	var  statusId=$("input[name='statusId']:checked").val();
	var msg="";
	if('Y'==statusId)
	{
		msg="确认通过此合同信息？";
	}else  if('N'==statusId)
	{
		msg="确认驳回此合同信息？";
	}else{
		jboxTip("请选择审核状态！");
		return false;
	}
	jboxConfirm(msg,function(){
		var url = "<s:url value='/erp/crm/contractWH/saveAuditContract'/>";
		jboxPost(url,$("#clientForm").serialize(),function(resp){
			if('${GlobalConstant.OPRE_SUCCESSED_FLAG}'==resp){
				jboxTip("${GlobalConstant.OPRE_SUCCESSED}");
				setTimeout(function(){
					window.parent.frames['mainIframe'].window.search();
					jboxClose();
				},2000)
			}
			if('${GlobalConstant.OPRE_FAIL_FLAG}'==resp){
				jboxTip("${GlobalConstant.OPRE_FAIL}");
			}
			if('${GlobalConstant.OPRE_FAIL_FLAG}'!=resp&&'${GlobalConstant.OPRE_SUCCESSED_FLAG}'!=resp)
			{
				jboxTip(resp);
			}
		},null,false);
	},null);
}

function changeClass(){

	var  statusId=$("input[name='statusId']:checked").val();
	if('Y'==statusId)
	{
		$("#auditReason").attr("class","validate[maxSize[200]] xltxtarea");
	}else  if('N'==statusId)
	{
		$("#auditReason").attr("class","validate[required,maxSize[200]] xltxtarea");
	}

}
</script>
</head>
<body>
<div class="mainright">
<div class="content">
	<div class="title1 clearfix">
				<table width="100%" cellpadding="0" cellspacing="0" class="basic">
					<colgroup>
						<col width="10%"/>
						<col width="20%"/>
						<col width="10%"/>
						<col width="20%"/>
						<col width="10%"/>
						<col width="20%"/>
					</colgroup>
					<tr>
						<th colspan="6" style="text-align: left;padding-left: 10px">合同信息
						<c:if test="${not empty resultMap['contractExt'].contractNo }">&#12288;[${resultMap['contractExt'].contractNo }]</c:if>
						</th>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">合同类别：</td>
						<td>${resultMap['contractExt'].contractCategoryName }</td>
						<td style="text-align: right;padding-right: 10px;">合同类型：</td>
						<td>${resultMap['contractExt'].contractTypeName }</td>
						<c:choose>
						 <c:when test="${resultMap['contractExt'].contractCategoryId == contractCategoryEnumSell.id }"> 
						<td style="text-align: right;padding-right: 10px;">经 销 商：</td>
						<td><a href="javascript:customerInfo('${resultMap['contractExt'].consumerFlow }','${resultMap['contractExt'].consumerName }')">${resultMap['contractExt'].consumerName }</a></td>
						 </c:when>
						 <c:otherwise>
						  <td></td>
						  <td></td>
						 </c:otherwise>
						</c:choose>
					</tr>
					<tr>
					    <td style="text-align: right;padding-right: 10px;">
					     <c:choose>
					      <c:when test="${(resultMap['contractExt'].contractCategoryId eq contractCategoryEnumSales.id) or (resultMap['contractExt'].contractCategoryId eq contractCategoryEnumSecond.id) or (resultMap['contractExt'].contractCategoryId eq contractCategoryEnumTrialAgreement.id) or (resultMap['contractExt'].contractCategoryId eq contractCategoryEnumOperation.id)}">
					                    买&#12288;&#12288;方：
					      </c:when>
					      <c:otherwise>
					       <c:choose>
					         <c:when test="${resultMap['contractExt'].contractCategoryId eq contractCategoryEnumSell.id}">
					                                  使&nbsp;用&nbsp;&nbsp;方：
					         </c:when>
					         <c:otherwise>
					                                   卖&#12288;&#12288;方：
					         </c:otherwise>
					        </c:choose>
					      </c:otherwise>
					     </c:choose>
					    </td>
						<td>
						   <c:if test="${param.status!='main' }">
						     <a href="javascript:customerInfo('${resultMap['contractExt'].customer.customerFlow }','${resultMap['contractExt'].customer.customerName }')">${resultMap['contractExt'].customer.customerName }</a>
						   </c:if>
						   <c:if test="${param.status=='main' }">
						      ${resultMap['contractExt'].customer.customerName }
						   </c:if>
						</td>
						<td style="text-align: right;padding-right: 10px;">使用部门：</td>
						<td>${resultMap['contractExt'].customerDept}</td>
					    <td style="text-align: right;padding-right: 10px;">合同名称：</td>
						<td>${resultMap['contractExt'].contractName}</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">
						    <c:choose>
					         <c:when test="${(resultMap['contractExt'].contractCategoryId eq contractCategoryEnumSales.id) or (resultMap['contractExt'].contractCategoryId eq contractCategoryEnumSecond.id) or (resultMap['contractExt'].contractCategoryId eq contractCategoryEnumTrialAgreement.id) or (resultMap['contractExt'].contractCategoryId eq contractCategoryEnumSell.id) or (resultMap['contractExt'].contractCategoryId eq contractCategoryEnumOperation.id)}">
					                          卖&#12288;&#12288;方：
					         </c:when>
					        <c:otherwise>
					                           买&#12288;&#12288;方：
					        </c:otherwise>
					     </c:choose>
						</td>
						<td>
							${resultMap['contractExt'].contractOwnName }
						</td>
						<td style="text-align: right;padding-right: 10px;">签&nbsp;订&nbsp;人：</td>
						<td>${resultMap['contractExt'].signUserName}</td>
						<td style="text-align: right;padding-right: 10px;">签订日期：</td>
						<td>${resultMap['contractExt'].signDate}</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">负责部门：</td>
						<td>${resultMap['contractExt'].signDeptName}</td>
						<td style="text-align: right;padding-right: 10px;">合同负责人：</td>
						<td>${resultMap['contractExt'].chargeUserName}</td>
						<td style="text-align: right;padding-right: 10px;">合同负责人2：</td>
						<td>${resultMap['contractExt'].chargeUser2Name}</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">合同到期日：</td>
						<td>${resultMap['contractExt'].contractDueDate}</td>
						<td style="text-align: right;padding-right: 10px;">维护到期日：</td>
						<td>${resultMap['contractExt'].maintainDueDate}</td>
						<td style="text-align: right;padding-right: 10px;">计划验收日期：</td>
						<td>${resultMap['contractExt'].planAcceptDate}</td>
					</tr>
					<tr>
					    <td style="text-align: right;padding-right: 10px;">合同金额：</td>
						<td><label class="money">${resultMap['contractExt'].contractFund}</label>元</td>
						<td style="text-align: right;padding-right: 10px;">合同状态：</td>
						<td>${resultMap['contractExt'].contractStatusName}</td>
						<td style="text-align: right;padding-right: 10px;">维护次数：</td>
						<td>${resultMap['contractExt'].maintainCount}次/年</td>
					</tr>
					<tr>
					    <td style="text-align: right;padding-right: 10px;">电子原件：</td>
						<td>
						  <c:if test="${not empty resultMap['contractExt'].contractFileFlow }">
						   <a id="down" href='<s:url value="/pub/file/down?fileFlow=${resultMap['contractExt'].contractFileFlow}"/>'>${resultMap['file'].fileName}</a>
						  </c:if>
						</td>
						<td style="text-align: right;padding-right: 10px;">合同档案号：</td>
						<td>${resultMap['contractExt'].contractArchivesNo}
						</td>
						<td style="text-align: right;padding-right: 10px;">合同创建人：</td>
						<td>
							${resultMap['createUserName']}
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">创建时间：</td>
						<td>
							${pdfn:transDateTime(resultMap['createTime'])}
						</td>
						<td style="text-align: right;padding-right: 10px;">合同最后修改人：</td>
						<td>
							${resultMap['modifyUserName']}
						</td>
						<td style="text-align: right;padding-right: 10px;">最后修改时间：</td>
						<td>
							${pdfn:transDateTime(resultMap['modifyTime'])}
						</td>
					</tr>
					<c:if test="${resultMap['contractExt'].contractCategoryId eq contractCategoryEnumSecond.id }">
					<tr>
					    <td style="text-align: right;padding-right: 10px;">关联主合同：</td>
					    <td colspan="5">
					       <c:if test="${empty resultMap['mainRefExtList'] }">
					                               无记录
					       </c:if>
					       <c:forEach items="${resultMap['mainRefExtList'] }" var="ref" varStatus="num">
					          <span style="line-height: 25px;">${num.count }.&#12288;
					          <c:if test="${param.status=='main' }">
					            <c:if test="${not empty ref.contract.contractNo }">${ref.contract.contractNo }：</c:if>${ref.contract.contractName }
					          </c:if>
					          <c:if test="${param.status!='main' }"> 
					            <a href="javascript:contractInfo('${ref.contract.contractFlow }');"><c:if test="${not empty ref.contract.contractNo }">${ref.contract.contractNo }：</c:if>${ref.contract.contractName }</a>
					          </c:if>
					          </span><br/>
					        
					       </c:forEach>
					    </td> 
					</tr>
					</c:if>
					<c:if test="${resultMap['contractExt'].subCount>0 }">
					   <tr>
					    <td style="text-align: right;padding-right: 10px;">相关子合同：</td>
					    <td colspan="5">
					       <c:if test="${empty resultMap['subRefExtList'] }">
					                               无记录
					       </c:if>
					       <c:forEach items="${resultMap['subRefExtList'] }" var="ref" varStatus="num">
					          <span style="line-height: 25px;">${num.count }.&#12288;
					          <c:if test="${param.status=='main' }">
					            <c:if test="${not empty ref.contract.contractNo }">${ref.contract.contractNo }：</c:if>${ref.contract.contractName }
					          </c:if>
					          <c:if test="${param.status!='main' }"> 
					            <a href="javascript:contractInfo('${ref.contract.contractFlow }');"><c:if test="${not empty ref.contract.contractNo }">${ref.contract.contractNo }：</c:if>${ref.contract.contractName }</a>
					          </c:if>
					          </span><br/>
					       </c:forEach>
					    </td> 
					</tr>
					 </c:if>
				</table>
				<table width="100%" cellpadding="0" cellspacing="0" class="basic">
					<colgroup>
						<col width="10%"/>
						<col width="20%"/>
						<col width="10%"/>
						<col width="20%"/>
						<col width="10%"/>
						<col width="20%"/>
					</colgroup>
					<tr>
						<th colspan="6" style="text-align: left;padding-left: 10px">合同首次安装信息</th>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">安装部门：</td>
						<td>${resultMap['info'].deptName }</td>
						<td style="text-align: right;padding-right: 10px;">安装人员：</td>
						<td>${resultMap['info'].buildingUserName }</td>
						<td style="text-align: right;padding-right: 10px;">安装地点：</td>
						<td>${resultMap['info'].buildingAddress }</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;">出差日期：</td>
						<td>${resultMap['info'].travelDate}</td>
						<td style="text-align: right;padding-right: 10px;">安装时间：</td>
						<td>${resultMap['info'].buildingDate}</td>
						<td style="text-align: right;padding-right: 10px;">派工单号：</td>
						<td>${resultMap['info'].orderNo}</td>
					</tr>
					<tr>
					    <td style="text-align: right;padding-right: 10px;">实际验收日期：</td>
						<td>${resultMap['info'].acceptDate}</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</table>
				
				<table cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;border-top: 0px;">
					<colgroup>
						<col width="3%"/>
						<col width="25%"/>
						<col width="20%"/>
						<col width="12%"/>
						<col width="15%"/>
						<col width="10%"/>
						<col width="15%"/>
					</colgroup>
					<tr>
						<th colspan="7" style="text-align: left;padding-left: 10px">合同产品
						</th>
					</tr>
					<tr>
						<th></th>
						<th>产品/项目名称</th>
						<th>备注</th>
						<!-- <th>使用人</th> -->
						<th>安装地点</th>
						<th>版本号</th>
						<th>注册文件客户名</th>
						<th><c:if test="${isShow eq GlobalConstant.FLAG_Y}">注册文件有效期</c:if></th>
					</tr>
					<tbody>
					  <c:forEach items="${resultMap['productList']}" var="product" varStatus="num">
						<tr>
							<td>${num.count }</td>
							<td>${product.productTypeName }</td>
							<td>${product.productItem }</td>
							<td>
							   ${product.installPlace }
							</td>
							<td>
							   ${product.versions }
							</td>
							<td>
							   ${product.regFileClientName }
							</td>
							<td><c:if test="${isShow eq GlobalConstant.FLAG_Y}">
							   ${product.regFileIndate }
							</c:if></td>
						</tr>
					  </c:forEach>
					</tbody>
				</table>
				<table cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;border-top: 0px;">
					<colgroup>
						<col width="3%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="11%"/>
						<col width="11%"/>
						<col width="15%"/>
						<col width="10%"/>
					</colgroup>
					<tr>
						<th colspan="11" style="text-align: left;padding-left: 10px">联系人信息
						</th>
					</tr>
					<tr>
						<th></th>
						<th>姓名</th>
						<th>性别</th>
						<th>科室</th>
						<th>职务</th>
						<th>人员类别</th>
						<th>固话</th>
						<th>手机</th>		
						<th>邮箱</th>
						<th>主要联系人</th>
					</tr>
					<tbody id="linkManTb">
					 <c:if test="${empty resultMap['userList'] }">
					   <tr>
					     <td colspan="11">无记录</td>
					   </tr>
					 </c:if>
					 <c:forEach items="${resultMap['userList'] }" var="user" varStatus="num">
						<tr <c:if test="${user.recordStatus eq GlobalConstant.FLAG_N}"> style="background-color:#eeeeee;"</c:if>>
							<td>
							  ${num.count }
							  <input type="hidden" name="userFlow" value="${user.userFlow }"/>
							  <input type="hidden" name="recordFlow" value="${user.recordFlow }">
							</td>
							<td>${user.customerUser.userName }</td>
							<td>${user.customerUser.sexName }</td>
							<td>${user.customerUser.deptName }</td>
							<td>${user.customerUser.postName }</td>
							<td>${user.userCategoryName }</td>
							<td>${user.customerUser.userTelphone }</td>
							<td>${user.customerUser.userCelphone }</td>
							<td>${user.customerUser.userEmail }</td>
							<td>
							 <c:if test="${user.isMain eq GlobalConstant.RECORD_STATUS_Y }">是</c:if>
							 <c:if test="${user.isMain eq GlobalConstant.RECORD_STATUS_N }">否</c:if>
							</td>
						</tr>
					 </c:forEach>	
					</tbody>
				</table>
				<table cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;border-top: 0px;">
					<colgroup>
						<col width="3%"/>
						<col width="24%"/>
						<col width="24%"/>
						<col width="24%"/>
						<col width="25%"/>
					</colgroup>
					<tr>
						<th colspan="6" style="text-align: left;padding-left: 10px">款项执行计划
						</th>
					</tr>
					<tr>
						<th></th>
						<th>计划日期</th>
						<th>计划金额</th>
						<th>
							<c:set var="contractCategoryId" value="${resultMap['contractExt'].contractCategoryId}"/>
							<c:if test="${contractCategoryId == contractCategoryEnumPurchase.id}">付款金额</c:if>
							<c:if test="${contractCategoryId != contractCategoryEnumPurchase.id}">到账金额</c:if>
						</th>
						<th>状态</th>
					</tr>
					<tbody>
					<c:if test="${empty resultMap['payPlanList']}">
					   <tr>
					     <td colspan="5">无记录</td>
					   </tr>
					</c:if>
					<c:forEach items="${resultMap['payPlanList'] }" var="payPlan" varStatus="num">
						<tr>
							<td>${num.count}</td>
							<td>${payPlan.planDate}</td>
							<td><label class="money">${payPlan.payFund}</label>元</td>
							<td><label class="money">${payPlan.balanceFund}</label>元</td>
							<td>${payPlan.planStatusName}</td>
						</tr>
						<c:forEach items="${resultMap['balanceMap'][payPlan.planFlow]}" var="payBalance" varStatus="status">
						<tr>
							<td colspan="5">
								<ul>
									<li>
										<span style="display:inline-block;text-align:left;width: 3%;"></span>
										<span>
										[${status.count}]&nbsp;${payBalance.payDate}&nbsp;
										<c:if test="${contractCategoryId == contractCategoryEnumPurchase.id}">付款</c:if>
										<c:if test="${contractCategoryId != contractCategoryEnumPurchase.id}">到账</c:if>
										&nbsp;<label class="money">${payBalance.payFund}</label>元 （操作人：${payBalance.createUserName}）&#12288;
										<!-- <a href="javascript:void(0);" onclick="billInfo();"> [发票单]</a> -->
										</span>
									</li>
								</ul>
							</td>
						</tr>
						</c:forEach>
				    </c:forEach>
					</tbody>
				</table>
				<table cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;border-top: 0px;">
					<c:if test="${'open' != param.type }">
					<colgroup>
						<col width="3%"/>
						<col width="24%"/>
						<col width="24%"/>
						<col width="24%"/>
						<col width="25%"/>
					</colgroup>
					<tr>
						<th colspan="6" style="text-align: left;padding-left: 10px">开票执行计划
							<c:if test="${isCaiWu eq GlobalConstant.FLAG_N}">
								<c:if test="${'open' != param.type }">
								<%--<img title="编辑" src="<s:url value="/css/skin/${skinPath}/images/main_edit.png" />" style="float: right;margin-right: 20px;cursor: pointer;" onclick="editBillPlan('${resultMap['contractExt'].contractFlow }');"/>--%>
								</c:if>
							</c:if>
						</th>
					</tr>
					</c:if>
					<c:if test="${'open' == param.type }">
					<colgroup>
						<col width="3%"/>
						<col width="24%"/>
						<col width="24%"/>
						<col width="24%"/>
						<col width="25%"/>
					</colgroup>
					<tr>
						<th colspan="5" style="text-align: left;padding-left: 10px">开票执行计划
							<c:if test="${isCaiWu eq GlobalConstant.FLAG_N}">
								<c:if test="${'open' != param.type }">
								<%--<img title="编辑" src="<s:url value="/css/skin/${skinPath}/images/main_edit.png" />" style="float: right;margin-right: 20px;cursor: pointer;" onclick="editBillPlan('${resultMap['contractExt'].contractFlow }');"/>--%>
								</c:if>
							</c:if>
						</th>
					</tr>
					</c:if>
					<tr>
						<th></th>
						<th>计划开票日期</th>
						<th>计划开票金额</th>
						<th>实际开票金额</th>
						<th>状态</th>
						<c:if test="${'open' != param.type }">
						<%--<th>操作</th>--%>
						</c:if>
					</tr>
					<tbody>
					<c:if test="${empty resultMap['billPlanList']}">
					   <tr>
					     <td colspan="5">无记录</td>
					   </tr>
					</c:if>
					<c:forEach items="${resultMap['billPlanList'] }" var="billPlan" varStatus="num">
						<tr>
							<td>${num.count}</td>
							<td>${billPlan.billPlanDate}</td>
							<td><label class="money">${billPlan.billPayFund}</label>元</td>
							<td><label class="money">${billPlan.billBalanceFund}</label>元</td>
							<td>${billPlan.billPlanStatusName}</td>
							<%--<td>--%>
								<%--<c:if test="${sessionScope.userListScope==GlobalConstant.USER_LIST_GLOBAL and (pdfn:contain('action-erp-swgl-crmhtgl-crmhtcx-xzkp', sessionScope.currUserMenuIdList))}">--%>
									<%--<c:if test="${billPlan.billPayFund > billPlan.billBalanceFund and 'open' != param.type}">--%>
										<%--<a href="javascript:void(0);" onclick="addBill('${billPlan.contractFlow}','${billPlan.billPlanFlow}');">--%>
										<%--[新增开票信息]--%>
										<%--</a>--%>
									<%--</c:if>--%>
								<%--</c:if>--%>
							<%--</td>--%>
						</tr>
						<c:if test="${not empty resultMap['billBalanceMap'][billPlan.billPlanFlow]}">

						<tr>
							<td></td>
							<td colspan="6">
								<table width="100%">
									<tr>
										<th>序号</th>
										<th>开票日期</th>
										<th>开票金额</th>
										<th>发票号</th>
										<th>开票操作人</th>
										<th>操作时间</th>
										<th>快递号</th>
										<th>寄送信息</th>
										<th>快递编辑人</th>
										<th>编辑时间</th>
										<%--<th>操作</th>--%>
									</tr>
									<c:forEach items="${resultMap['billBalanceMap'][billPlan.billPlanFlow]}" var="billBalance" varStatus="status">
										<tr>
											<td>${status.count}</td>
											<td>${billBalance.billDate}</td>
											<td>${billBalance.billFund}</td>
											<td>${billBalance.billNo}</td>
											<td>${billBalance.createUserName}</td>
											<td>${pdfn:transDate(billBalance.createTime)}</td>
											<td>${billBalance.billTrackeNumber}</td>
											<td>${billBalance.billTrackeContent}</td>
											<td>${billBalance.modifyUserName}</td>
											<td>${pdfn:transDate(billBalance.modifyTime)}</td>
											<%--<c:if test="${pdfn:contain('action-erp-swgl-crmhtgl-crmhtcx-kdxx', sessionScope.currUserMenuIdList) and (empty billBalance.billTrackeNumber or billBalance.billTrackeNumber == 0)}">--%>
											<%--<td>--%>
												<%--<a href="javascript:void(0);" onclick="updateBill('${billPlan.contractFlow}','${billPlan.billPlanFlow}','${billBalance.recordFlow}');">--%>
												<%--[编辑快递信息]--%>
												<%--</a>--%>
											<%--</td>--%>
											<%--</c:if>--%>
											<%--<c:if test="${not empty billBalance.billTrackeNumber and billBalance.billTrackeNumber != 0}">--%>
												<%--<td>&nbsp;</td>--%>
											<%--</c:if>--%>
										</tr>
									</c:forEach>
								</table>
							</td>
						</tr>

						</c:if>
				    </c:forEach>
					</tbody>
				</table>
				<c:if test="${(contractCategoryEnumOperation.id eq resultMap['contractExt'].contractCategoryId )
				and (
					( resultMap['contractExt'].contractTypeId eq contractTypeEnumJsRes.id)or
					( resultMap['contractExt'].contractTypeId eq contractTypeEnumJsZyRes.id)or
					( resultMap['contractExt'].contractTypeId eq contractTypeEnumHbRes.id)
				)
				}">
					<table id="" cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;border-top: 0px;">
						<colgroup>
							<col width="10%"/>
							<col width="25%"/>
							<col width="20%"/>
							<col width="20%"/>
							<col width="25%"/>
						</colgroup>
						<tr>
							<th colspan="6" style="text-align: left;padding-left: 10px">开通权限详情
								<c:if test="${(resultMap['contractExt'].contractStatusId eq contractStatusEnumAuditing.id)
								and ((isShangWu eq GlobalConstant.FLAG_Y) or (resultMap['contractExt'].createUserFlow eq sessionScope.currUser.userFlow ))}">
								<%--<img title="编辑" src="<s:url value="/css/skin/${skinPath}/images/main_edit.png" />" style="float: right;margin-right: 20px;cursor: pointer;"--%>
									 <%--onclick="editPowerList('${resultMap['contractExt'].contractFlow }');"/>--%>
								</c:if>
							</th>
						</tr>
						<tr>
							<th>届别</th>
							<th>权限</th>
							<th>住院医师开通人数</th>
							<th>在校专硕开通人数</th>
							<th>人员名单</th>
						</tr>
						<tbody>
						<c:if test="${not empty resultMap['powerList']}">
							<c:forEach items="${resultMap['powerList']}" var="p">
								<tr>
									<td>${p.sessionNumber}</td>
									<td>${p.powerNames}</td>
									<td>${p.peopleNum}</td>
									<td>${p.graduatePeopleNum}</td>
									<td>
										<c:set var="file" value="${resultMap['fileMap'][p.powerFlow]}"></c:set>
										<c:if test="${not empty file}">
											<a href="<s:url value='/erp/crm/downFile'/>?fileFlow=${file.fileFlow}">${file.fileName}</a>
										</c:if>
									</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty resultMap['powerList']}">
							<tr><td colspan="4">暂无开通权限信息</td></tr>
						</c:if>
						</tbody>
					</table>
					<table id="" cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;border-top: 0px;">
						<tr>
							<th style="text-align: left;padding-left: 10px">开通角色权限</th>
						</tr>
						<tbody>
						<c:if test="${not empty resultMap['otherPowerList']}">
							<c:forEach items="${resultMap['otherPowerList']}" var="p">
								<tr>
									<td   style="text-align: left;padding-left: 20px">${p.powerNames}</td>
								</tr>
							</c:forEach>
						</c:if>
						<c:if test="${empty resultMap['otherPowerList']}">
							<tr><td colspan="4">暂无其他权限开通信息</td></tr>
						</c:if>
						</tbody>
					</table>
				</c:if>
				<c:if test="${resultMap['contractExt'].contractStatusId eq contractStatusEnumAuditing}">
					<form id="clientForm" method="post">
						<input type="hidden" name="contractFlow" value="${resultMap['contractExt'].contractFlow }">
							<table class="xllist">
								<colgroup>
									<col width="20%"/>
									<col width="80%"/>
								</colgroup>
								<tr>
									<td style="text-align: right;padding-right: 10px;">审核意见：</td>
									<td style="text-align: left; ">
										&#12288;<input id="agree"  name="statusId" value="Y" type="radio" onclick="changeClass();" class="validate[required]">
										<label for="agree">审核通过</label>&#12288;&#12288;
										<input id="disAgree"  name="statusId" value="N" type="radio" onclick="changeClass();" class="validate[required]">
										<label for="disAgree">审核退回</label>&#12288;
									</td>
								</tr>
								<tr>
									<td style="text-align: right;padding-right: 10px;">驳回意见：</td>
									<td>
									<textarea class="xltxtarea " placeholder="200个字符以内" style="margin-left: 0px;resize: none;"
											  name="auditReason"  id="auditReason" ></textarea>
									</td>
								</tr>
							</table>
						</form>
					</c:if>
					<div class="button" style="width: 100%">
						<c:if test="${resultMap['contractExt'].contractStatusId eq contractStatusEnumAuditing}">
							<input class="search" type="button" value="保&#12288;存" onclick="save();" />
						</c:if>
						<input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();" />
					</div>
			</div>
		</div>
	</div>
</body>
</html>