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
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/j.suggest.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
 <script type="text/javascript">
 $(document).ready(function(){
	 loadCustomerList();
     showCustomerContract('${lic.customerFlow}'); 
     $("#vaildDate").show();
 });
 function loadCustomerList() {
		var customers = new Array();
		var url = "<s:url value='/erp/crm/searchCustomerJson'/>";
		jboxPostAsync(url,null,function(data){
			if(data!=null){
				for (var i = 0; i < data.length; i++) {
					var aliasName=data[i].aliasName;
					if(data[i].aliasName==null){
						aliasName="";
					}
					customers[i]=new Array(data[i].customerFlow,data[i].customerName,aliasName);
				}
			}
		},null,false);
		$("#searchParam_Customer").suggest(customers,{
			attachObject:'#suggest_Customer',
			dataContainer:'#result_Customer',
			triggerFunc:function(customerFlow){
				showCustomerContract(customerFlow);
			},
		    enterFunc:function(customerFlow){
		    	showCustomerContract(customerFlow);
		    }
		});
 }
 function adjustResults() {
		$("#suggest_Customer").css("left",$("#searchParam_Customer").offset().left);
		$("#suggest_Customer").css("top",$("#searchParam_Customer").offset().top+$("#searchParam_Customer").outerHeight());
 }
//重置客户名称
 function resetCustomerName(){
	 var customerFlow=$("#result_Customer").val();
	 var customerName=$("#searchParam_Customer");
	 if(customerFlow==""){
		 customerName.val("");
	 }
 }
 //重置客户流水号
 function resetCustomerFlow(){
	 var customerFlow=$("#result_Customer");
	 customerFlow.val("");
}
 
 function accreditChange(obj){
	 if($(obj).val()=='${accreditTypeEnumDevelop.id}' || $(obj).val()=='${accreditTypeEnumShow.id}'){
		 //$("#normalCus").hide();
		// $("#contractTable").hide();
		// $("#speCus").show();
		 resetCustomerFlow();
		 resetCustomerName();
		 //$("#result_Customer").val('${GlobalConstant.PD_ORG_FLOW}');
		 removeProCheck();
		 $("#vaildDate").hide();
		 $("#autodate").hide();
		 $("#date").show();
	 }
	 if($(obj).val()=='${accreditTypeEnumOfficial.id}'){
		 //$("#normalCus").show();
		 //$("#contractTable").show();
		 //$("#speCus").hide();
		 $("#vaildDate").show();
		 $("#autodate").show();
		 $("#date").hide();
		 $("#vaildDate").val("");
		 $("#date").text("");
	 }
	 if($(obj).val()=='${accreditTypeEnumProbation.id}'){
		 //$("#normalCus").show();
		 //$("#speCus").hide();
		 //$("#contractTable").hide();
		 $("#vaildDate").hide();
		 $("#autodate").hide();
		 $("#date").show();
	 }
		var url = "<s:url value='/erp/lic/time'/>?type="+$(obj).val();
		jboxGet(url, null, function(resp){
				if(resp!=""){
					$("#date").text(resp);
					$("#vaildDate").val(resp);
					if($(obj).val()=='${accreditTypeEnumOfficial.id}'){
						$("#date").hide();
					}
				}
			}, null , false);
 }
 //展示客户合同
 function showCustomerContract(customerFlow){
	 var radio=$("input[name=accredit]:checked").val();
	 if(radio=="${accreditTypeEnumProbation.id}"){
		 return false;
	 }
	 if(customerFlow!="" && customerFlow!="${GlobalConstant.PD_ORG_FLOW}"){
	   var url="<s:url value='/erp/lic/loadContractByCustomer'/>?customerFlow="+customerFlow;
	   jboxLoad("contractTb",url,false);
	 }
 }
 function changeLan(id){
	 removeProCheck();
	 $(".proContent").hide();
	 $("#"+id+"Content").show();
	 checkProFromContract();
 }
 //取消勾选已选择的产品
 function removeProCheck(){
	 $(".proContent").find(".workStation").each(function(){
			$(this).removeAttr("checked"); 
	 });
	 $("#encryptName").val("");
 }
 function selectSingle(obj){
		var value = $(obj).val();
		var name = $(obj).attr("name");
		$("input[name="+name+"][value!="+value+"]:checked").attr("checked",false);
	}
 //切换合同
 function changeContract(contractFlow){
	 removeProCheck();
	 checkProFromContract();
 }
 //勾选合同中的产品
 function checkProFromContract(){
	 $("input[name='contractFlow']:checked").each(function(){
		 var contractFlow=$(this).val();
		 if($(this).attr("checked")=="checked"){
			 var workStations=$("#content").find(".workStation:visible");
			 var products=$(this).closest("tr").find("td:eq(3)").find("span");
			 var proIdArray=new Array();
			 $(products).each(function(){
				if($(this).attr("id")!=""){
					proIdArray.push($(this).attr("id"));
				} 
			 });
			 $(workStations).each(function(){
				 var regFileClientName;
				 if($.inArray($(this).val(),proIdArray)!=-1){
                    $(this).attr("checked","checked");
                    $("#encryptName").val(regFileClientName=$("#"+contractFlow+"_regFileClientName").val());
				 }
			 });
		 }
	 });
 }
 
 function searchDeptUser(obj){
	 $("#applyUserFlow").empty();
	 var deptFlow=$(obj).val();
	 if(deptFlow!=""){
		 jboxPost("<s:url value='/erp/lic/searchDeptUserJson'/>?deptFlow="+deptFlow,null,function(data){
			 if(data!=null){
			 $("#applyUserFlow").append('<option></option>');
			 for ( var i = 0; i < data.length; i++) {
				 var userEmail=data[i].userEmail;
				 if(data[i].userEmail==null)
						 userEmail="暂无邮箱";
					$("#applyUserFlow").append('<option title="'+userEmail+'" value="'+data[i].userFlow+'">'+data[i].userName+'</option>');
				 }
			 var applyDeptName=$(obj).find("option:selected").text();
			 $("#applyDeptName").val(applyDeptName);
		  }
		 },null,false); 
	 }else{
		 var option='<option>请先选择负责部门</option>';
		 $("#applyUserFlow").append(option);
	 }
	 
 }
 function setUserName(id){
	 $("#"+id+"Name").val($("#"+id+"Flow").find("option:selected").text());
 }
 
 function save(){
	 if($("#licForm").validationEngine("validate")){
		 if($(":checkbox[name='contractFlow']:checked").length<=0)
		 {
			 jboxTip("请选择合同！");
			 return false;
		 }
		 var msg = "确定保存并发送License文件至申请人邮箱吗？<br />申请人邮箱为："+$("#applyUserFlow option:selected").attr("title")+" <br />(注意：操作后将不可撤消或更改！)";
		 if($("#vaildDate").val()){
			msg = "<span style='color:red;'>请确认到期时间：" + $("#vaildDate").val() + "</span><br />" + msg;
		 }
		 jboxConfirm(msg , function(){
			 var url="<s:url value='/erp/lic/saveLicKey'/>";
			 jboxPost(url,$("#licForm").serialize(),function(data){
				 window.parent.frames['mainIframe'].window.search();
				 jboxCloseMessager();
			 },null,true);
		 });
	 }
 }
 function changePro(workStationId,type,obj){
	 if(workStationId!='netexam'){
			 $("#encryptFlag").removeAttr("checked");
			 $("#encryptName").val("");
	 } 
	 if(type=='net'){
		 selectSingle(obj);
	 }
 }
 function contractInfo(contractFlow) {
		var mainIframe = window.parent.frames["mainIframe"];
		var width = mainIframe.document.body.scrollWidth;
		var url = "<s:url value='/erp/crm/contractInfo'/>?contractFlow="+contractFlow+"&status=main&type=open";
		jboxOpen(url, "合同详细信息", 1000, 600);
	}

 function  setAutoDate(day){
	 var url = "<s:url value='/erp/lic/addTime'/>?type="+day;
	 jboxGet(url, null, function(resp){
		 if(resp!=""){
			 $("#vaildDate").val(resp);
		 }
	 }, null , false);
 }
</script>
</head>
<body>
     <div class="mainright">
		<div class="content">
			<div class="title1 clearfix">
			  <form id="licForm" style="position: relative;">
			   <div style="margin-bottom: 10px">
			              授权类型：<c:forEach items="${accreditTypeEnumList }" var="accredit">
			                <input type="radio" name="accredit" id="${accredit.id }_radio" onclick="accreditChange(this);" value="${accredit.id }" <c:if test="${accredit.id eq accreditTypeEnumOfficial.id}">checked</c:if>/><label for="${accredit.id }_radio">${accredit.name }</label>
			            </c:forEach>
			    &#12288; <span class="red">*</span>客户名称：<span id="normalCus"><input id="searchParam_Customer" name="customerName"  placeholder="输入客户名称/别名" class="inputText validate[required]"  style="width: 260px;text-align: left;padding-left: 5px;" onblur="resetCustomerName();" onchange="resetCustomerFlow();" onkeydown="adjustResults();" onfocus="adjustResults();"/>
				       <div id="suggest_Customer" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 370px;"></div>
				       <input type="hidden" id="result_Customer" name="customerFlow" />
				       </span>
				       <span id="speCus" style="display: none;">南京品德网络信息技术有限公司</span>
			   </div>
			   <div>
                 <table id="contractTable" class="xllist" style="margin-bottom: 10px">
			       <colgroup>
			           <col width="5%"/>
			           <col width="32%"/>
			           <col width="32%"/>	
			           <col width="31%"/>
			        </colgroup>
			        <tr>
			           <th></th>
			           <th>客户名称</th>
			           <th>合同号</th>
			           <th>产品名称</th>
			        </tr>
			        <tbody id="contractTb">
			           <tr><td colspan="4">无记录</td></tr>
			        </tbody>
			     </table>
			   </div>
			   <div style="margin-bottom: 10px">
			    <table  width="100%" cellpadding="0" cellspacing="0" class="basic">
			     <colgroup>
			           <col width="30%"/>
			           <col width="70%"/>
			        </colgroup>
			     <tr>
			      <th colspan="2" style="text-align: left;padding-left: 10px">license信息</th>
			     </tr>
			     <tr> 
			       <th><span class="red">*</span>开发语言：</th>         
			       <td>
			           <c:forEach items="${developLanguageEnumList }" var="lan">
			                <input type="radio" name="developLanguage" id="radio_${lan.id }" onchange="changeLan('${lan.id }');" class="validate[required]" value="${lan.id }"/><label for="radio_${lan.id }">${lan.name }</label>
			           </c:forEach>  
			       </td>
			     </tr>
			     <tr>
			        <th><span class="red">*</span>授权文件内容：</th>
			        <td id="content">
			           <span id="javaContent" style="display: none;" class="proContent">
	                     <c:forEach items="${javaWorkStationList }" var="workStation" varStatus="num">
	                      <li style="width:180px; float: left;">
	                         <input type="checkbox" id="${workStation.workStationId }_lic" class="workStation validate[required]" name="workStationId" value="${workStation.workStationId }" onchange="changePro('${workStation.workStationId }','java',this);"/>
	                         <label for="${workStation.workStationId }_lic">${workStation.workStationName }</label>
	                      </li>
	                     </c:forEach>
	                    </span>
	                   <span id="netContent" style="display: none;" class="proContent">
	                      <c:forEach items="${netWorkStationList }" var="workStation">
	                      <li>
	                         <input type="checkbox" id="${workStation.workStationId }_lic" class="workStation validate[required]" name="workStationId" value="${workStation.workStationId }" onchange="changePro('${workStation.workStationId }','net',this);"/>
	                         <label for="${workStation.workStationId }_lic">
	                            ${workStation.workStationName }
	                            <c:if test="${workStation.workStationId eq 'netexam' }">
	                            <span>
			                                                               （<input type="checkbox" id="encryptFlag" value="Y" class="workStation" name="encryptFlag"><label for="encryptFlag">加密</label>
			                      &nbsp;加密关键字：<input  type="text" id="encryptName" value="" class="workStation inputText" name="encryptName" style="text-align: left;width: 150px;">）&#12288;&#12288;                                  
			                    </span>
			                    </c:if>
	                         </label>
	                      </li>
	                      </c:forEach>
	                   </span>
			        </td>
			     </tr>
			     <tr>
			        <th><span class="red">*</span>机&nbsp;&nbsp;器&nbsp;&nbsp;码：</th>
			        <td><input type="text" name="machineId" class="inputText validate[required]" style="width:400px;text-align: left"></td>
			     </tr>
			     <tr>
			        <th><span class="red">*</span>到&nbsp;&nbsp;期&nbsp;&nbsp;日：</th>
			        <td>
			           <input type="text"id="vaildDate" name="vaildDate" class="inputText ctime validate[required]" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="display: none;">
			        	<span id="date" style="display: none;"></span>
						<span id="autodate"> 便捷选择：
							<a href="javascript:void(0)" onclick="setAutoDate(1)">1个月</a> |
							<a href="javascript:void(0)" onclick="setAutoDate(3)">3个月</a> |
							<a href="javascript:void(0)" onclick="setAutoDate(6)">6个月</a> |
							<a href="javascript:void(0)" onclick="setAutoDate(12)">12个月</a> |
							<a href="javascript:void(0)" onclick="setAutoDate(24)">24个月</a> |
							<a href="javascript:void(0)" onclick="setAutoDate(500)">500天</a> |
						</span>
			        </td>
			     </tr>
			     <tr>
			        <th><span class="red">*</span>申请部门：</th>
			        <td>
			            <select id="applyDeptFlow" class="inputText validate[required]" style="width:120px;" name="applyDeptFlow" onchange="searchDeptUser(this);">
			               <option value="">请选择</option>
			               <c:forEach items="${deptList }" var="dept">
						      <option value="${dept.deptFlow }">${dept.deptName }</option>
						   </c:forEach>
			            </select>
			            <input type="hidden" name="applyDeptName" id="applyDeptName"/>
			        </td>
			     </tr>
			     <tr>
			        <th><span class="red">*</span>申&nbsp;&nbsp;请&nbsp;&nbsp;人：</th>
			        <td>
			           <select id="applyUserFlow" class="inputText validate[required]" style="width:120px;" name="applyUserFlow" onchange="setUserName('applyUser');">
			               <option value="">请选择申请部门</option>
			            </select>
			            <input type="hidden" name="applyUserName" id="applyUserName"/>
			        </td>
			     </tr>
					<tr>
						<th><span class="red">*</span>安装信息：</th>
						<td>
							<textarea name="licContent" placeholder="这里填写安装信息" rows="10" cols="20"
									  class="validate[required] xltxtarea"></textarea>
						</td>
					</tr>
			     </table>
			   </div>
			   
			   <div class="button" style="width: 100%">
					<input id="saveButton" class="search" type="button" value="保&#12288;存" onclick="save();" />
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxCloseMessager();" />
				</div>
			  </form>
	        </div>
	   </div>
	  </div>
	  
</body>
</html>