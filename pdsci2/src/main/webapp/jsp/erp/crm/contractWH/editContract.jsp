<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
<jsp:include page="/jsp/common/htmlhead.jsp">
	<jsp:param name="basic" value="true"/>
	<jsp:param name="jbox" value="true"/>
	<jsp:param name="jquery_form" value="true"/>
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
var mainContractFlowArray=new Array();
var saveFlag=false;
/**
 * 添加（合同产品、联系人、款项执行计划）空白行
 * 标签内容（普通-->会计）
 */
$(document).ready(function(){
	if($("#productTb tr").length<=0){
		addProduct();
	}
	if($("#payPlanTb tr").length<=0){
		addPayPlan();
	}
	if($("#billPlanTb tr").length<=0){
		addBillPlan();
	}
	if("${contractExt.customerFlow}"!="")
	{
		showCustomerDetail("${contractExt.customerFlow}");
	}
	moneyMask();
	init();
});
/**
 * 多个标签内容（普通-->会计）
 */
function moneyMask(){
	var moneyBox=$(".money");
	$.each(moneyBox,function(i,n){
		$(n).mask('000,000,000,000,000', {reverse: true});
	});
}
/**
 * 多个标签内容（会计-->普通）
 */
function reMoneyMask(){
	var moneyBox=$(".money");
	$.each(moneyBox,function(i,n){
		$(n).mask('000000000000000', {reverse: true});
	});
}
/**
 * 单个标签内容（普通-->会计）
 */
function moneyObjMask(obj){
	$(obj).mask('000,000,000,000,000', {reverse: true});
}
/**
 * 单个标签内容（会计-->普通）
 */
function reMoneyObjMask(obj){
	$(obj).mask('000000000000000', {reverse: true});
}
 
 //根据合同类别的不同添加产品空白行
function addProduct(){
	var projectId = "projectClone";
	var contractTypeId = $("select[name='contractTypeId']").val();
	 if (contractTypeId=="${contractTypeEnumProduct.id}") {
		projectId = "productClone";
	} else if (contractTypeId=="${contractTypeEnumProductProject.id}") {
		projectId = "productProjectClone";
	}
	$('#productTb').append($("#"+projectId+" tr:eq(0)").clone());
}
/**
 * 改变合同类型时清空产品列表
 */
function changeContractTypeTd(){
	$('#productTb').html("");
	addProduct();
}
/**
 * 把产品名称格式在下拉框和文本框之间切换
 */
function switchProduct(obj,type) {
	var productTd = $(obj).closest("td[id='productTd']");
	var imageUrl = '<s:url value="/css/skin/${skinPath}/images/check.png" />';
	var clickUrl = "switchProduct(this,'project');";
	if (type=="project") {
		clickUrl = "switchProduct(this,'product');";
		productTd.html("");
		productTd.append($("#projectClone span[id='projectSpan']").clone());
		productTd.append('&#12288;<span title="切换为产品" style="cursor: pointer;" onclick="'+clickUrl+'">></span>');
	} else {
		productTd.html("");
		productTd.append($("#productClone span[id='productSpan']").clone());
		productTd.append('&#12288;<span title="切换为项目" style="cursor: pointer;" onclick="'+clickUrl+'">></span>');
	}
}
/**
 * 删除产品
 */
function delProduct(){
	var mIds = $("#productTb input[name='id']:checked");
	if(mIds.length == 0){
		jboxTip("请勾选要删除的！");
		return false;
	}
	jboxConfirm("确认删除？", function() {
		var ids = "";
		mIds.each(function(){
			var id = $(this).val();
			$(this).parent().parent().remove();
		});
	});
}
/**
 * 添加联系人空白行
 */
function addLinkMan(){
	$('#linkManTb').append($("#clone tr:eq(0)").clone());
}
/**
 * 删除联系人
 */
function delLinkMan(){
	var mIds = $("#linkManTb input[name='id']:checked");
	if(mIds.length == 0){
		jboxTip("请勾选要删除的！");
		return false;
	}
	jboxConfirm("确认删除？", function() {
		var ids = "";
		mIds.each(function(){
		  $(this).parent().parent().remove();
		});
	});
}


$(function(){
	/*查询客户*/
	loadCustomerList();
});
/**
 * 加载客户列表(请求)
 */
function loadCustomerList() {
	var customers = new Array();
	var url = "<s:url value='/erp/crm/searchCustomerJson'/>";
	jboxPost(url,null,function(data){
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
			showMainContactInfo(customerFlow,'1');
			showCustomerDetail(customerFlow);
		},
	    enterFunc:function(customerFlow){
	    	showMainContactInfo(customerFlow,'1');
	    	showCustomerDetail(customerFlow);
	    }
	});
	$("#searchParam_Customer_other").suggest(customers,{
		attachObject:'#suggest_Customer_other',
		dataContainer:'#result_Customer_other',
		triggerFunc:function(customerFlow){
			showMainContactInfo(customerFlow,'2');
		},
	    enterFunc:function(customerFlow){
	    	showMainContactInfo(customerFlow,'2');
	    }
	});
}

/**
 * 添加查看客户详细信息的按钮
 */
function showCustomerDetail(customerFlow){
	$("#detailFlag").remove();
	$("#customerName").append('<span title="客户详细信息" id="detailFlag" style="margin-right: 10px;cursor: pointer;" onclick="customerInfo(\''+customerFlow+'\');">>></span>');
}
/**
 * 查看客户详细信息
 */
function customerInfo(customerFlow){
	var w = $('.mainright').width();
	var h = $('.mainright').height();
	var url = "<s:url value='/erp/crm/customerInfo'/>?customerFlow=" + customerFlow+"&type=open&no=third";
	var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+h+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
	jboxOpen(url,'客户详细信息',w,h);
}
/**
 * 查询客户的主合同
 */
function showMainContactInfo(customerFlow,type) {
	saveFlag=false;
	//setAllMainContractStatusToFalse();
	var mainContacts = "";
	jboxPost("<s:url value='/erp/crm/searchMainContact'/>?customerFlow="+customerFlow,null,function(data){
		if(data!=null){
			for(var i=0;i<mainContractFlowArray.length;i++){
				if(mainContractFlowArray[i]==data['customer'].customerFlow){
					saveFlag=true;
				}
			}
			mainContacts += data['customer'].customerName+"的主合同：<br/>";
			for ( var i = 0; i < data['contractList'].length; i++) {
				var ahref = 'javascript:contractInfo("'+data['contractList'][i].contractFlow+'");';
				mainContacts += "<input class='mainContract validate[required]' type='checkbox' onclick='showMainContactProduct(this)' name='mainContract' value='"+data['contractList'][i].contractFlow+"'>"+
						"&#12288;<a href='"+ahref+"'>";
				if(data['contractList'][i].contractNo!=null && data['contractList'][i].contractNo!=""){
					mainContacts += data['contractList'][i].contractNo+"：";
				}
				mainContacts += data['contractList'][i].contractName+"</a>&#12288;<br/>";
			}if(type=='1')
			{
				$("#mainContactSpan").html(mainContacts);
				mainContractFlowArray=new Array();
				mainContractFlowArray.push(data['customer'].customerFlow);
			}else {
				if(saveFlag==false){
					$("#mainContactSpan").append(mainContacts);
					mainContractFlowArray.push(data['customer'].customerFlow);
				}
			}
		}
		$("#assistContractSpan").show();
	},null,false);
}

/**
 * 查看合同详细信息
 */
function contractInfo(contractFlow) {
	var mainIframe = window.parent.frames["mainIframe"];
	var width = mainIframe.document.body.scrollWidth;
	if (width >1000) {
		width = 1000;
	}
	var url = "<s:url value='/erp/crm/contractInfo'/>?contractFlow="+contractFlow+"&type=open&status=main";
	jboxOpen(url, "合同信息", width, 600);
}
/**
 * 保存合同信息
 */
 function save(){
	  reMoneyMask();
	  if($("#contractForm").validationEngine("validate")){
		var contract = $("#contractForm").serializeJson();
		var productTr=$("#productTb").children();
		var linkManTr=$("#linkManTb").children();
		var refTr=$("#refTb").children();
		var powerTr=$("#powerTb").children();
		var productDatas=[];
		var linkManDatas=[];
		var refDatas=[];
		var powerDatas=[];
		var otherPowerDatas=[];
		if(productTr.length>=1){
			 $.each(productTr , function(i , n){
				   var productFlow=$(n).find("select[name='productFlow']").val();
				   var productTypeId=$(n).find("select[name='productTypeId']").val();
				   var productTypeName=$(n).find("input[name='productTypeName']").val();
				   var productItem=$(n).find("input[name='productItem']").val();
				   var installPlace=$(n).find("input[name='installPlace']").val();
				   var versions=$(n).find("input[name='versions']").val();
				   var regFileClientName=$(n).find("input[name='regFileClientName']").val();
				   var regFileIndate=$(n).find("input[name='regFileIndate']").val();
				   var productData;
                   if(productTypeId==undefined || productTypeId==""){
                	   productData={
							   "productFlow":productFlow,
							   "productTypeName":productTypeName,
							   "productItem":productItem,
							   "installPlace":installPlace,
							   "versions":versions,
							   "regFileClientName":regFileClientName,
							   "regFileIndate":regFileIndate
					   }; 
				   }else{
					   productData={
						       "productFlow":productFlow,
							   "productTypeId":productTypeId,
							   "productItem":productItem,
							   "installPlace":installPlace,
							   "versions":versions,
							   "regFileClientName":regFileClientName,
							   "regFileIndate":regFileIndate
					   }; 
				   }  
				   productDatas.push(productData);
				}); 
		}else{
		    jboxTip("至少选择一个产品大项!");
		    return false;
		}
		
		 $.each(linkManTr , function(i , n){
		   var recordFlow=$(n).find("input[name='recordFlow']").val();
		   var userFlow=$(n).find("input[name='userFlow']").val();
		   var userName=$(n).find("input[name='userName']").val();
		   var postName=$(n).find("input[name='postName']").val();
		   var sexId=$(n).find("select[name='sexId']").val();
		   var deptName=$(n).find("input[name='deptName']").val();
		   var userTelphone=$(n).find("input[name='userTelphone']").val();
		   var userCelphone=$(n).find("input[name='userCelphone']").val();
		   var userEmail=$(n).find("input[name='userEmail']").val();
		   var isMain=$(n).find("select[name='isMain']").val();
		   var remark=$(n).find("input[name='remark']").val();
		   if(userName=="" && postName==""
			  && deptName=="" && userTelphone=="" && remark==""
			  && userCelphone=="" && userEmail=="" && isMain==""){
				   $(n).remove();
			}else{
				   var linkManData={
						    "recordFlow":recordFlow,
						    "userFlow":userFlow,
						    "deptName":	deptName,
						    "userName":userName,
						    "postName":postName,
						    "sexId":sexId,
						    "deptName":deptName,
						    "userTelphone":userTelphone,
						    "userCelphone":userCelphone,
						    "userEmail":userEmail,
						    "isMain":isMain,
						    "remark":remark
				   };
				   linkManDatas.push(linkManData); 
			   }
		   
		});
		 $.each(refTr,function(i,n){
			 var contractFlow=$(n).find("input[name='contractFlow']").val();
			 var refData={
				     "contractFlow":contractFlow	 
			 };
			 refDatas.push(refData);
		 });

		  var contractCategoryId = $("#contractCategoryId").val();
		  var contractTypeId = $("select[name='contractTypeId']").val();
		  if (contractCategoryId == "${contractCategoryEnumOperation.id}" &&
				  (contractTypeId == "${contractTypeEnumJsRes.id}" ||
				  contractTypeId == "${contractTypeEnumJsZyRes.id}" ||
				  contractTypeId == "${contractTypeEnumHbRes.id}")) {
			  if (powerTr.length > 0) {
				  $.each(powerTr, function (i, n) {
					  var sessionNumber = $(n).find("input[name='sessionNumber']").val();
					  var powerIds="";
					  var powerNames="";
					  $.each($(n).find("input[name='powerIds']:checked"),function(j){
						  if(j==0) {
							  powerIds+=$(this).val();
							  powerNames+=$(this).attr("idname");
						  }else{
							  powerIds+=","+$(this).val();
							  powerNames+=","+$(this).attr("idname");
						  }
					  });
					  var powerFlow = $(n).find("input[name='powerFlow']").val();
					  var peopleNum = $(n).find("input[name='peopleNum']").val();
					  var graduatePeopleNum = $(n).find("input[name='graduatePeopleNum']").val();
					  var fileFlow = $(n).find("input[name='fileFlow']").val();
					  var oldFileFlow = $(n).find("input[name='oldFileFlow']").val();
					  var powerData = {
						  "sessionNumber": sessionNumber,
						  "powerIds": powerIds,
						  "powerNames": powerNames,
						  "peopleNum": peopleNum,
						  "graduatePeopleNum": graduatePeopleNum,
						  "powerFlow": powerFlow,
						  "oldFileFlow": oldFileFlow,
						  "fileFlow": fileFlow
					  };
					  powerDatas.push(powerData);
				  });
			  } else {
				  jboxTip("至少填写一个权限开通情况!");
				  return false;
			  }
			  var powerIds="";
			  var powerNames="";
			  $.each($("#otherPower").find("input[name='powerIds']:checked"),function(j){
				  if(j==0) {
					  powerIds+=$(this).val();
					  powerNames+=$(this).attr("idname");
				  }else{
					  powerIds+=","+$(this).val();
					  powerNames+=","+$(this).attr("idname");
				  }
			  });
			  var otherPowerFlow=$("#otherPower").find("input[name='otherPowerFlow']").val();
			  var powerData = {
				  "powerIds": powerIds,
				  "powerNames": powerNames,
				  "otherPowerFlow": otherPowerFlow
			  };
			  otherPowerDatas.push(powerData);
		  }
		 if($("#productForm").validationEngine("validate")
		 && $("#linkManForm").validationEngine("validate")
		 && $("#planForm").validationEngine("validate")
		 && $("#powerForm").validationEngine("validate")){

			 if (contractCategoryId == "${contractCategoryEnumOperation.id}" &&
					 (contractTypeId == "${contractTypeEnumJsRes.id}" ||
					 contractTypeId == "${contractTypeEnumJsZyRes.id}" ||
					 contractTypeId == "${contractTypeEnumHbRes.id}")) {
					 jboxConfirm("请确认权限文件内容是否正确，保存将无法修改附件信息？", function () {
						 saveSubmit(contract,productDatas,linkManDatas,refDatas,powerDatas,otherPowerDatas);
					 }, null);
			 }else{
				 saveSubmit(contract,productDatas,linkManDatas,refDatas,powerDatas,otherPowerDatas);
			 }
		 }else{
			 $("#saveButton").removeAttr("disabled");
			 moneyMask();
			 return false;
		 }
	 }else{
		 moneyMask();
		 return false;
	 }
 }
function saveSubmit(contract,productDatas,linkManDatas,refDatas,powerDatas,otherPowerDatas)
{

	$("#saveButton").attr("disabled",true);
	var allData={
		'contract':contract,
		'productList':productDatas,
		'userExtList':linkManDatas,
		'refList':refDatas,
		'powerExtList':powerDatas,
		'otherPowerList':otherPowerDatas
	};
	$('#jsondata').val(JSON.stringify(allData));
	console.log(JSON.stringify(allData));
	var url = "<s:url value='/erp/crm/contractWH/saveContractInfo'/>?refType=Y";
	jboxSubmit($('#contractForm'),url,function(resp){
			if(resp=="1")
			{
				jboxTip("保存成功");
				setTimeout(function () {
					 window.parent.frames['mainIframe'].search();
					jboxCloseMessager();
				}, 2000);
			}else{
				jboxTip(resp);

			}
	},null,false);
}
 //添加款项执行计划
 function addPayPlan(){
		$('#payPlanTb').append($("#payPlanClone tr:eq(0)").clone());
		moneyMask();
	}
/**
 * 删除款项执行计划
 */
 function delPayPlan(){
		var mIds = $("#payPlanTb input[name='id']:checked");
		if(mIds.length == 0){
			jboxTip("请勾选要删除的！");
			return false;
		}
		jboxConfirm("确认删除？", function() {
			var ids = "";
			mIds.each(function(){
				var id = $(this).val();
				if(id != ''){
					ids = ids + "id="+ id + "&";
				}else{
					$(this).parent().parent().remove();
				}
			});
		});
	}
 //添加开票执行计划
 function addBillPlan(){
		$('#billPlanTb').append($("#billPlanClone tr:eq(0)").clone());
		moneyMask();
	}
/**
 * 删除开票执行计划
 */
 function delBillPlan(){
		var mIds = $("#billPlanTb input[name='id']:checked");
		if(mIds.length == 0){
			jboxTip("请勾选要删除的！");
			return false;
		}
		jboxConfirm("确认删除？", function() {
			var ids = "";
			mIds.each(function(){
				var id = $(this).val();
				if(id != ''){
					ids = ids + "id="+ id + "&";
				}else{
					$(this).parent().parent().remove();
				}
			});
		});
	}
 //检测产品是否重复选择
 function showProductCheck(obj) {
		var productType = $(obj).val();
		if(productType!=""){
			if ($("select[name='productTypeId'] option[value='"+productType+"']:selected").parent().length>1){
			     jboxTip("已存在该产品，请重新选择！");
				 $(obj).val("");
				 return; 
			 } 
		}
	 	showKsInfo(obj);
	}
	//合同编辑，添加产品/项目时，如果选的是考试系统，则备注提示“请输入详细题库版本及题目数量
	function showKsInfo(obj)
	{
		var productType = $(obj).val();
		var text=$(obj).find("option[value='"+productType+"']").text();
		var input=$(obj).parent().parent().next().next().children("input[name='productItem']");
		if(isContains(text,"考试系统"))
		{
			console.log($(input).val());
			console.log("success");
			$(input).attr("placeholder","请输入详细题库版本及题目数量");
		}else{
			console.log($(input).val());
			console.log("feild");
			$(input).removeAttr("placeholder");
		}

	}
	function isContains(str, substr) {
		return str.indexOf(substr) >= 0;
	}
//如果是运维合同，并且是平台级则显示开通权限情况表
	function showPower()
	{
		var contractCategoryId=$("#contractCategoryId").val();
		var contractTypeId = $("select[name='contractTypeId']").val();
		if(contractCategoryId=="${contractCategoryEnumOperation.id}"&&
				(contractTypeId=="${contractTypeEnumJsRes.id}"||contractTypeId=="${contractTypeEnumJsZyRes.id}"||contractTypeId=="${contractTypeEnumHbRes.id}"))
		{
			$("#powerTable").show();
			$("#otherPower").show();
			if($("#powerTb tr").length<=0){
				addPower();
			}
		}else{
			$("#powerTable").hide();
			$("#otherPower").hide();
		}
	}

function  changeFile(obj)
{
	var fileObj=$(obj).next();
	$(fileObj).click();
}

function uploadFile(obj)
{
	var bro=$(obj).prev();//上传文字
	var fileNamebro=$(obj).prev().prev();//显示的文件名
	var file=$(obj).prev().prev().prev();//文件流水号
	var fileName=$(obj).val();
	if(fileName=="")
	{
		jboxTip("请选择文件！");
		$(fileNamebro).attr("href","javascript:void(0);");
		$(fileNamebro).html("");
		return ;
	}
	var suffix = fileName.substring(fileName.indexOf(".")+1);
	if(suffix!="xlsx"&&suffix!="xls")
	{
		jboxTip("请选择EXECL文件！");
		$(obj).val("");
		$(fileNamebro).attr("href","javascript:void(0);");
		$(fileNamebro).html("");
		return ;
	}
	jboxStartLoading();
	var url = "<s:url value='/erp/crm/savePowerFile'/>";
	jboxSubmit($(obj).parent(),url,function(resp){
		jboxEndLoading();
		var data=eval("("+resp+")");
		if(data.result=='${GlobalConstant.UPLOAD_SUCCESSED}'){
			jboxTip("${GlobalConstant.UPLOAD_SUCCESSED}");
			$(file).val(data.fileFlow);
			var url="<s:url value='/erp/crm/downFile'/>?fileFlow="+data.fileFlow;
			$(fileNamebro).attr("href",url);
			$(bro).html("[重新上传]");
			$(fileNamebro).html(fileName);
		}else{
			jboxInfo("${GlobalConstant.UPLOAD_FAIL}");
		}
	},function(){
		jboxEndLoading();
		jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
	},false);
}
//添加权限开通情况
function addPower(){
	$('#powerTb').append($("#powerClone tr:eq(0)").clone());
}
/**
 * 删除权限开通情况
 */
function delPower(){
	var mIds = $("#powerTb input[name='id']:checked");
	if(mIds.length == 0){
		jboxTip("请勾选要删除的！");
		return false;
	}
	jboxConfirm("确认删除？", function() {
		var ids = "";
		mIds.each(function(){
			var id = $(this).val();
			if(id != ''){
				ids = ids + "id="+ id + "&";
			}else{
				$(this).parent().parent().remove();
			}
		});
	});
}
 //改变合同类别（决定下个字段是经销商还是合同类型）
 function changeContractCategoryTd(obj){
	 $('#productTb').html("");
	 var secondTitle=$("#secondTitle");
	 var secondContent=$("#secondContent");
	 var thirdTitle=$("#thirdTitle");
	 var thirdContent=$("#thirdContent");
	 secondTitle.text("");
	 secondContent.text("");
	 thirdTitle.text("");
	 thirdContent.text("");
	 var contractCategoryId=$(obj).val();
	 if(contractCategoryId!=""){
		 if (contractCategoryId != '${contractCategoryEnumSecond.id}' && contractCategoryId != '${contractCategoryEnumOperation.id}') {
			 secondTitle.html('<span class="red">*</span>合同类型：');
			 secondContent.append($("#clone_Sales").html());
		 } else if (contractCategoryId == '${contractCategoryEnumOperation.id}') {
			 secondTitle.html('<span class="red">*</span>合同类型：');
			 secondContent.append($("#clone_Operation").html());
		 }else{
			 secondTitle.html('<span class="red">*</span>合同类型：');
			 secondContent.append($("#clone_" + contractCategoryId).html());
		 }

		 if (contractCategoryId == '${contractCategoryEnumSell.id}') {
			 thirdTitle.html('<span class="red">*</span>经&nbsp;销&nbsp;&nbsp;商：');
			 thirdContent.append($("#clone_" + contractCategoryId).html());
		 }

		 if ("${contractCategoryEnumSecond.id}" == $(obj).val()) {
			 $("#mainContactTr").show();
		 } else {
			 $("#mainContactTr").hide();
			 setAllMainContractStatusToFalse();
		 }
	 }
	 addProduct();
 }
 //带出/删除主合同产品
 function showMainContactProduct(obj) {
		var contractFlow = $(obj).val();
		if ($(obj).attr("checked") == "checked"){
			var refContent=
				'<tr id="'+contractFlow+'">'+
				  '<td><input type="text" name="contractFlow" value="'+contractFlow+'"/></td>'+
				  '</tr>';
			    $("#refTb").append(refContent);
				$('#productTb').find("[class='templete']").each(function(){
				if ($(this).val()=="") {
					$(this).closest("tr").remove();
				}
			});
				$('#productTb').find("[name='productTypeName']").each(function(){
					if ($(this).val()=="") {
						$(this).closest("tr").remove();
					}
				});
			jboxPost("<s:url value='/erp/crm/searchContactProduct'/>?contractFlow="+contractFlow,null,function (data){
				if(data!=null){
					$(".signDateMain").each(function(){
						$(this).show();
					});
					for ( var i = 0; i < data['productList'].length; i++) {
						var productTypeId = data['productList'][i].productTypeId;
						if(productTypeId == null){
							productTypeId = "";
						}
						var productTypeName = data['productList'][i].productTypeName;
						if(productTypeName == null){
							productTypeName = "";
						}
						var productItem = data['productList'][i].productItem;
						if (productItem == null) {
							productItem = "";
						}
						var maintainDueDate = data['contract'].maintainDueDate;
						if (maintainDueDate == null) {
							maintainDueDate = "";
						}
						var installPlace = data['productList'][i].installPlace;
						if (installPlace == null) {
							installPlace = "";
						}
						var versions = data['productList'][i].versions;
						if (versions == null) {
							versions = "";
						}
						var regFileClientName = data['productList'][i].regFileClientName;
						if (regFileClientName == null) {
							regFileClientName = "";
						}
						var regFileIndate = data['productList'][i].regFileIndate;
						if (regFileIndate == null) {
							regFileIndate = "";
						}
						var productContent = 
							'<tr class="'+contractFlow+'">'+
								'<td width="3%;"><input type="checkbox" name="id" value=""/></td>'+
								'<td width="17%;">'+
									'<input type="text" class="inputText" name="productTypeName" style="width: 200px; text-align:left;" value="'+productTypeName+'" readonly/>'+
									'<select name="productTypeId" style="display:none;"><option value="'+productTypeId+'"></option></select>'+
								'</td>'+
								'<td width="15%;"><input type="text" name="productItem" class="inputText validate[maxSize[2000]]" style="width: 90%;" value="'+productItem+'"/>'+
								'</td>'+
								'<td width="15%;"><input type="text" name="installPlace" placeholder="输入详细地址及服务器位置" class="inputText validate[maxSize[500]]" style="width: 90%;" value="'+installPlace+'"/>'+
								'</td>'+
								'<td width="10%;"><input type="text" name="versions" class="inputText validate[maxSize[50]]" style="width: 90%;" value="'+versions+'"/>'+
								'</td>'+
								'<td width="15%;"><input type="text" name="regFileClientName" class="inputText validate[maxSize[250]]" style="width: 90%;" value="'+regFileClientName+'"/>'+
								'</td>'+
								'<td width="10%;"><input type="text" name="regFileIndate" class="inputText validate[maxSize[50]] " style="width: 90%;" value="'+regFileIndate+'" onClick="WdatePicker({dateFmt:\'yyyy-MM-dd\'})" readonly="readonly"/>'+
								'</td>'+
							'</tr>';
						$('#productTb').append(productContent);
					}
				}
			},null,false);
		} else {
			$("#"+contractFlow).remove();
			$("."+contractFlow).each(function(){
				$(this).remove();
			});
			if($("#productTb tr").length<=0){
				addProduct();
			}
		}
		checkMainContractStatus();
	}
 //删除所有带出的主合同的合同产品
 function setAllMainContractStatusToFalse(){
	 var mainContracts=$(".mainContract");
	 $.each(mainContracts,function(i,n){
		 if($(n).attr("checked") == "checked"){
			 $(n).removeAttr("checked");
			 showMainContactProduct($(n));
		 }
	 });
	 checkMainContractStatus();
 }
 //隐藏主合同产品的原维护到期日列
 function checkMainContractStatus(){
	 var mainContracts=$(".mainContract");
	 var status=true;
	 $.each(mainContracts,function(i,n){
		 if($(n).attr("checked") == "checked"){
			 status=false;
		 }
	 });
	 if(status){
		 $(".signDateMain").each(function(){
			$(this).hide();
		 });
	 }
 }
 //根据所选部门加载签订人列表和负责人列表
 function searchDeptUser(obj){
	 $("#chargeUserFlow").empty();
	 ///$("#chargeUser2Flow").empty();
	 var dataChargeUserFlow=$("#dataChargeUserFlow").val();
	 //var dataChargeUser2Flow=$("#dataChargeUser2Flow").val();
	 var deptFlow=$(obj).val();
	 if(deptFlow!=""){
		 jboxPost("<s:url value='/erp/crm/searchDeptUserJson'/>?deptFlow="+deptFlow,null,function(data){
			 if(data!=null){
			 $("#chargeUserFlow").append('<option></option>');
			 $("#chargeUser2Flow").append('<option></option>');
			 for ( var i = 0; i < data.length; i++) {
					 if(dataChargeUserFlow==data[i].userFlow){
						 $("#chargeUserFlow").append('<option value="'+data[i].userFlow+'" selected>'+data[i].userName+'</option>');
					 }else{
						 $("#chargeUserFlow").append('<option value="'+data[i].userFlow+'">'+data[i].userName+'</option>');
					 }
//					 if(dataChargeUser2Flow==data[i].userFlow){
//						 $("#chargeUser2Flow").append('<option value="'+data[i].userFlow+'" selected>'+data[i].userName+'</option>');
//					 }else{
//						 $("#chargeUser2Flow").append('<option value="'+data[i].userFlow+'">'+data[i].userName+'</option>');
//					 }
				 }
			 var signDeptName=$(obj).find("option:selected").text();
			 $("#signDeptName").val(signDeptName);
		  }
		 },null,false); 
	 }else{
		 var option='<option>请先选择负责部门</option>';
		 $("#chargeUserFlow").append(option);
		 //$("#chargeUser2Flow").append(option);
	 }
	 
 }
 //设置负责人和签订人姓名
 function setUserName(id){
	 $("#"+id+"Name").val($("#"+id+"Flow").find("option:selected").text());
 }
 //查询客户联系人
 function searchCustomerLinkMan() {
	 var customerFlow;
	 if($("#contractCategoryId").val()=='${contractCategoryEnumSell.id}'){
		 customerFlow=$("#consumerFlow").val();
		 if(customerFlow==""){
				jboxTip("请先填写经销商名称！");
				return false;
	      }
	 }else{
		 customerFlow=$("#result_Customer").val();
		 if(customerFlow==""){
				jboxTip("请先填写客户名称！"); 
				return false;
	      }
	 }
	jboxOpen("<s:url value='/erp/crm/searchCustomerLinkManList'/>?customerFlow="+customerFlow+"&type=edit","客户联系人", 600, 400); 
	 
 }
 //重置客户名称
 function resetCustomerName(name){
	 var customerFlow=$("#result_Customer").val();
	 var customerName=$("#searchParam_Customer");
	 if(customerFlow==""){
		 customerName.val("");
	 }
 }
 //重置客户流水号
 function resetCustomerFlow(name){
	 var customerFlow=$("#result_Customer");
	 $("#detailFlag").remove();
	 customerFlow.val("");
}
function clearCustomerList(){
	$("#linkManTb").html("");
}

 //检测日期范围
 function checkDate(date1,date2,info){
	 if($(date1).val()!="" && $(date2).val()!=""){
	 var date1Box=$(date1).val().split("-");
	 var date2Box=$(date2).val().split("-");
	 var date1Value="";
	 var date2Value="";
	 for(var i=0;i<date1Box.length;i++){
		 date1Value=date1Value+date1Box[i];
	 }
	 for(var i=0;i<date2Box.length;i++){
		 date2Value=date2Value+date2Box[i];
	 }
	 var date1Int=parseInt(date1Value);
	 var date2Int=parseInt(date2Value);
		 if(date1Int>date2Int){
			 jboxTip(info);
			 $(date1).val("");
			 $(date2).val("");
			 return false;
		 }else{
			 return true;
		 } 
	}
 }
 //快速切换金额格式
function timeMask(obj){
	reMoneyObjMask($(obj));
	setTimeout(function(){
		moneyObjMask($(obj));
	},100);
}
//判断合同联系人是否重名已经是否填写正确的客户名称
function checkCustomerUserName(obj){
	var customerFlow=$("#result_Customer").val();
	if(customerFlow==null || customerFlow==""){
		$(obj).val("");
		jboxTip("请先填写客户名称！"); 
		return false;
	}
	var userName = $(obj).val();
	if(userName != ""){
		//判断当前页面是否已有重复姓名
		var num = 0;
		$("[name='userName']").each(function(){
			if(userName == $(this).val()){
				num++;
			}
		}); 
		if (num>1) {
			$(obj).focus();
			$(obj).val("");
			jboxInfo("当前合同已存在该联系人，不能重复添加!");
			return false;
		}
		//判断是否已存在该联系人
		var oldName = $(obj).attr("oldName");
		if(userName == oldName){
			$(obj).focus();
			$(obj).val("");
			jboxInfo("当前客户已存在该联系人，请选择!");
			return false;
		}
		var data = {
				userName:userName
			};
		var url = "<s:url value='/erp/crm/customerUserNameConfirm'/>?customerFlow="+customerFlow;
		jboxPost(url, data,
				function(resp){
					if(resp == "${GlobalConstant.OPRE_FAIL}"){
						$(obj).focus();
						$(obj).val("");
						$(obj).attr("oldName",userName);
						jboxInfo("当前客户已存在该联系人，请选择!");
						return false;
					}
				},null,false);
	}
}
/**
 * 根据合同类型切换买方，卖方和使用人
 */
function changeSellAndBuy(td1,td2){
	var contractCategoryId=$("#contractCategoryId").val();
	if(contractCategoryId=='${contractCategoryEnumSales.id}'
	|| contractCategoryId=='${contractCategoryEnumSecond.id}'
	|| contractCategoryId=='${contractCategoryEnumTrialAgreement.id}'
	|| contractCategoryId=='${contractCategoryEnumOperation.id}'){
		$("#"+td1).html('<span class=\"red\">*</span>买&#12288;&#12288;方：');
		$("#"+td2).html('<span class=\"red\">*</span>卖&#12288;&#12288;方：');
	}
	if(contractCategoryId=='${contractCategoryEnumPurchase.id}'){
		$("#"+td1).html('<span class=\"red\">*</span>卖&#12288;&#12288;方：');
		$("#"+td2).html('<span class=\"red\">*</span>买&#12288;&#12288;方：');
	}
	if(contractCategoryId=='${contractCategoryEnumSell.id}'){
		$("#"+td2).html('<span class=\"red\">*</span>卖&#12288;&#12288;方：');
		$("#"+td1).html('<span class=\"red\">*</span>使&nbsp;用&nbsp;&nbsp;方：');
	}
	
}

function adjustResults() {
	$("#suggest_Customer").css("left",$("#searchParam_Customer").offset().left);
	$("#suggest_Customer").css("top",$("#searchParam_Customer").offset().top+$("#searchParam_Customer").outerHeight());
	$("#suggest_Customer_other").css("left",$("#searchParam_Customer_other").offset().left);
	$("#suggest_Customer_other").css("top",$("#searchParam_Customer_other").offset().top+$("#searchParam_Customer_other").outerHeight());
}


function init(){
	$("#searchParam_Customer_other").hover(function() {
		adjustResults();
	},null);
}
function changeCheck(){
	var mainContacts=$("#mainContactSpan").find("input[name='mainContract']");
	var beforeRefTr=$("#beforeRefTb").children();
	$.each(mainContacts,function(a,b){
		$.each(beforeRefTr,function(i,n){
			if($(b).val()==$(n).find("input[name='beforeContractFlow']").val()){
				$(b).attr("checked","checked");
			}
		});
	});

}

function addFile(obj){
	var td=$(obj).parent();
	td.text("");
	td.append($("#file").clone());

}
$(document).ready(function(){
	var obj=$("#signDeptFlow");
	searchDeptUser(obj);
	showPower();
	moneyMask();
	<c:forEach items="${customerList}" var="customer">
	mainContractFlowArray.push("${customer.customerFlow}");
	</c:forEach>
	$("#assistContractSpan").show();
});
</script>
</head>
<body>
<div class="mainright">
<div class="content">
	<div class="title1 clearfix">
		<div id="tagContent" >
			<div class="tagContent selectTag" id="tagContent0">
			<input type="hidden" id="contractNoFlag"/>
			<form id="contractForm" method="post" style="position: relative;" enctype="multipart/form-data">
			    <input id="jsondata" type="hidden" name="jsondata"/>
				<input name="contractFlow" id="contractFlow" type="hidden" value="${contractExt.contractFlow }"/>
				<input type="hidden" id="dataSignUserFlow" value="${contractExt.signUserFlow }">
				<input type="hidden" id="dataChargeUserFlow" value="${contractExt.chargeUserFlow }">
				<%--<input type="hidden" id="dataChargeUser2Flow" value="${contractExt.chargeUser2Flow }">--%>
				<table width="100%" cellpadding="0" cellspacing="0" class="basic">
					<colgroup>
						<col width="13%"/>
						<col width="20%"/>
						<col width="13%"/>
						<col width="20%"/>
						<col width="13%"/>
						<col width="20%"/>
					</colgroup>
					<tr>
						<th colspan="6" style="text-align: left;padding-left: 10px">合同信息</th>
					</tr>
					<tr>
					    <td style="text-align: right;padding-right: 10px;"><span class="red">*</span>合同类别：</td>
						<td>
							<select class="inputText validate[required]" id="contractCategoryId" name="contractCategoryId" style="width: 80%;" onchange="changeContractCategoryTd(this);changeSellAndBuy('firstTd','secondTd');showPower();">
				            	<option value="">请选择</option>
				             	<c:forEach var="contractCategory" items="${contractCategoryEnumList}">
									<c:if test="${empty refFlag or contractCategory.id != contractCategoryEnumSecond.id}">
										<option value="${contractCategory.id}" <c:if test="${contractCategory.id eq contractExt.contractCategoryId }">selected="selected"</c:if>>${contractCategory.name}</option>
									</c:if>
						        </c:forEach>
							</select>
						</td>
					    <td id="secondTitle" style="text-align: right;padding-right: 10px;"><span class="red">*</span>合同类型：</td>
						<td id="secondContent" >
							<c:if test="${contractExt.contractCategoryId eq contractCategoryEnumSecond.id }">
								<select class="inputText validate[required]"  name="contractTypeId" style="width: 90%;" onchange="showPower();">
									<option value="">请选择</option>
									<c:forEach items="${contractTypeEnumList }" var="contractType">
										<c:if test="${contractType.categoryId eq 'Second' }">
											<option value="${contractType.id }" <c:if test="${contractType.id eq contractExt.contractTypeId }">selected="selected"</c:if>>${contractType.name }</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
							<c:if test="${contractExt.contractCategoryId != contractCategoryEnumSecond.id }">
								<select class="inputText validate[required]"  name="contractTypeId" style="width: 90%;" onchange="showPower();">
									<option value="">请选择</option>
									<c:forEach items="${contractTypeEnumList }" var="contractType">
										<c:if test="${contractType.categoryId eq contractExt.contractCategoryId }">
											<option value="${contractType.id }" <c:if test="${contractType.id eq contractExt.contractTypeId }">selected="selected"</c:if>>${contractType.name }</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
						</td>
						<c:choose>
							<c:when test="${contractExt.contractCategoryId eq contractCategoryEnumSell.id }">
								<td id="thirdTitle" style="text-align: right;padding-right: 10px;"><c:choose><c:when test="${contractExt.contractCategoryId == contractCategoryEnumSell.id }"><span class="red">*</span>经&nbsp;销&nbsp;&nbsp;商：</c:when><c:otherwise><span class="red">*</span>合同类型：</c:otherwise></c:choose></td>
								<td id="thirdContent">
									<select name="consumerFlow" class="inputText validate[required]" style="width:90%;" onchange="changeContractTypeTd();">
										<option value="">请选择</option>
										<c:forEach items="${consumerList }" var="consumer">
											<option value="${consumer.customerFlow }"<c:if test="${contractExt.consumerFlow eq consumer.customerFlow }">selected="selected"</c:if>>${consumer.customerName }</option>
										</c:forEach>
									</select>
								</td>
							</c:when>
							<c:otherwise>
								<td id="thirdTitle" style="text-align: right;padding-right: 10px;"></td>
								<td id="thirdContent"></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
					    <td style="text-align: right;padding-right: 10px;" id="firstTd"><span class="red">*</span>
							<c:choose>
								<c:when test="${(contractExt.contractCategoryId eq contractCategoryEnumSales.id) or (contractExt.contractCategoryId eq contractCategoryEnumSecond.id) or (contractExt.contractCategoryId eq contractCategoryEnumTrialAgreement.id)}">
									买&#12288;&#12288;方：
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${contractExt.contractCategoryId eq contractCategoryEnumSell.id }">使&nbsp;用&nbsp;&nbsp;方：</c:when>
										<c:otherwise>卖&#12288;&#12288;方：
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</td>
						<td style="text-align: left;" id="customerName">
							<input id="searchParam_Customer"  placeholder="输入客户名称/别名" value="${contractExt.customer.customerName }" class="inputText validate[required]"  style="width: 80%;text-align: left;" onblur="resetCustomerName('');" onchange="resetCustomerFlow('main');clearCustomerList();" onkeydown="adjustResults();" onfocus="adjustResults();"/>
				        	<div id="suggest_Customer" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 370px;"></div>
				        	<input type="hidden" id="result_Customer" name="customerFlow" value="${contractExt.customerFlow }"/>
			        	</td>
			        	<td style="text-align: right;padding-right: 10px;">使用部门：</td>
			        	<td style="text-align: left;">
			        	    <input type="text" name="customerDept" class="inputText validate[maxSize[50]]"value="${contractExt.customerDept }" style="width:80%;text-align: left;"/>
			        	</td>
			            <td style="text-align: right;padding-right: 10px;"><span class="red" style="padding-left: 7px;">*</span>合同名称：</td>
						<td style="text-align: left;">
						<input class="inputText validate[required,maxSize[100]]" name="contractName"value="${contractExt.contractName }" style="width:80%;text-align: left;" type="text"/>
						</td>
								
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;" id="secondTd"><span class="red">*</span>
							<c:choose>
								<c:when test="${(contractExt.contractCategoryId eq contractCategoryEnumSales.id) or (contractExt.contractCategoryId eq contractCategoryEnumSecond.id) or (contractExt.contractCategoryId eq contractCategoryEnumTrialAgreement.id) or (contractExt.contractCategoryId eq contractCategoryEnumSell.id)}">卖&#12288;&#12288;方：
								</c:when>
								<c:otherwise>买&#12288;&#12288;方：
								</c:otherwise>
							</c:choose>
						</td>
						<td style="text-align: left;">
						    <select name="contractOwnId" class="inputText validate[required]" style="width: 80%;">
								<c:forEach items="${companyNameEnumList }" var="companyName">
									<option value="${companyName.id }" <c:if test="${contractExt.contractOwnId eq companyName.id }">selected="selected"</c:if>>${companyName.name }</option>
								</c:forEach>
						    </select>
						</td>
						<td style="text-align: right;padding-right: 10px;">签&nbsp;订&nbsp;&nbsp;人：</td>
						<td style="text-align: left;">
							<select name="signUserFlow" id="signUserFlow" class="inputText" style="width: 80%;" onchange="setUserName('signUser');">
								<option value=""></option>
								<c:forEach items="${users}" var="user">
									<option value="${user.userFlow}" <c:if test="${contractExt.signUserFlow eq user.userFlow }">selected="selected"</c:if>>${user.userName}</option>
								</c:forEach>
							</select>
							<input type="hidden" name="signUserName" id="signUserName" value="${contractExt.signUserName }"/>
						</td>
						<td style="text-align: right;padding-right: 10px;"><span class="red">*</span>签订日期：</td>
						<td style="text-align: left;"><input type="text" class="inputText ctime validate[required]" value="${contractExt.signDate }" name="signDate" id="signDate" style="width: 77%;text-align: left;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;"><span class="red">*</span>负责部门：</td>
						<td style="text-align: left;">
						   <select name="signDeptFlow" id="signDeptFlow" class="inputText validate[required]" onchange="searchDeptUser(this);" style="width: 80%;">
							   <option value="">请选择</option>
							   <c:forEach items="${deptList }" var="dept">
								   <option value="${dept.deptFlow }" <c:if test="${contractExt.signDeptFlow eq dept.deptFlow}">selected="selected"</c:if>>${dept.deptName }</option>
							   </c:forEach>
							   <%--<option value="f48a5060931147daa467eadbb5885629" <c:if test="${contractExt.signDeptFlow eq 'f48a5060931147daa467eadbb5885629'}">selected="selected"</c:if>>总经办</option>--%>
							   <%--<option value="a8a0aeef43c846c4a976589184b4ed8b" <c:if test="${contractExt.signDeptFlow eq 'a8a0aeef43c846c4a976589184b4ed8b'}">selected="selected"</c:if>>销售一部</option>--%>
							   <%--<option value="a02a8190fc58440f8635b7c5cfcd1949" <c:if test="${contractExt.signDeptFlow eq 'a02a8190fc58440f8635b7c5cfcd1949'}">selected="selected"</c:if>>销售二部</option>--%>
							   <%--<option value="d7f1afc58416446ba414d3c69bb204e2" <c:if test="${contractExt.signDeptFlow eq 'd7f1afc58416446ba414d3c69bb204e2'}">selected="selected"</c:if>>销售三部</option>--%>
							   <%--<option value="5b42e66a13e742d3a796f17d8104e02d" <c:if test="${contractExt.signDeptFlow eq '5b42e66a13e742d3a796f17d8104e02d'}">selected="selected"</c:if>>销售四部</option>--%>
							   <%--<option value="1f56f216c3fd4dc4a1b23e7dda645c0b" <c:if test="${contractExt.signDeptFlow eq '1f56f216c3fd4dc4a1b23e7dda645c0b'}">selected="selected"</c:if>>销售五部</option>--%>
						   </select>
						   <input type="hidden" name="signDeptName" id="signDeptName" value="${contractExt.signDeptName }"/>
						</td>
						<td style="text-align: right;padding-right: 10px;"><span class="red">*</span>合同负责人：</td>
						<td style="text-align: left;">
						   <select name="chargeUserFlow" id="chargeUserFlow" class="inputText validate[required]" style="width: 80%;" onchange="setUserName('chargeUser');">
						     <option value="">请先选择负责部门</option>
						   </select>
						   <input type="hidden" name="chargeUserName" id="chargeUserName" value="${contractExt.chargeUserName }"/>
						</td>
						<td style="text-align: right;padding-right: 10px;"><span class="red">*</span>合同负责人2：</td>
						<td style="text-align: left;">
						   <select name="chargeUser2Flow" id="chargeUser2Flow" class="inputText validate[required]" style="width: 80%;" onchange="setUserName('chargeUser2');">
						     <option value="">请选择</option>
							   <c:forEach items="${users2}" var="user">
								   <option value="${user.userFlow}" <c:if test="${contractExt.chargeUser2Flow eq user.userFlow }">selected="selected"</c:if>>${user.userName}</option>
							   </c:forEach>
						   </select>
						   <input type="hidden" name="chargeUser2Name" id="chargeUser2Name"value="${contractExt.chargeUser2Name }"/>
						</td>
					</tr>
					<tr>
						<td style="text-align: right;padding-right: 10px;"><span class="red" style="padding-left: 12px;">*</span>合同到期日：</td>
						<td style="text-align: left;"><input class="inputText ctime validate[required]" name="contractDueDate" id="contractDueDate" value="${contractExt.contractDueDate }" style="width: 77%;text-align: left;" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						</td>
						<td style="text-align: right;padding-right: 10px;"><span class="red" style="padding-left: 12px;">*</span>维护到期日：</td>
						<td style="text-align: left;"><input class="inputText ctime validate[required]" name="maintainDueDate" id="maintainDueDate" value="${contractExt.maintainDueDate }" style="width: 77%;text-align: left;" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						</td>
						<td style="text-align: right;padding-right: 10px;">计划验收日期：</td>
						<td style="text-align: left;"><input class="inputText ctime" name="planAcceptDate" id="planAcceptDate" value="${contractExt.planAcceptDate }" style="width: 77%;text-align: left;" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
						</td>
					</tr>
					<tr>
					<tr>
					    <td style="text-align: right;padding-right: 10px;"><span class="red" style="padding-left: 12px;">*</span>合同金额：</td>
						<td style="text-align: left;"><input class="inputText validate[required,custom[number],maxSize[9]] money" type="text" name="contractFund" value="${contractExt.contractFund }" style="width: 75%;text-align: left;" onblur="timeMask(this);"/>元
						</td>
						<td style="text-align: right;padding-right: 10px;"><span class="red" style="padding-left: 12px;">*</span>维护次数：</td>
						<td style="text-align: left;"><input class="inputText validate[required,custom[number],maxSize[3]]" type="text" name="maintainCount" value="${contractExt.maintainCount }"style="width: 75%;text-align: left;"/>次/年
						</td>
						<td style="text-align: right;padding-right: 10px;">合同档案号：</td>
						<td style="text-align: left;" colspan="5"><input type="text" name="contractArchivesNo" class="inputText validate[maxSize[50]]" value="${contractExt.contractArchivesNo }"style="width:80%;text-align: left;"/></span>
						</td>
					</tr>
					<tr>
					    <td style="text-align: right;padding-right: 10px;">电子原件：</td>
						<td style="text-align: left;" colspan="5">
							<c:choose>
								<c:when test="${empty file.fileName}">
									<input type="file" name="file" class="validate[custom[fileType]]"/><br/>
									<span style="color: red">支持格式jpg，bmp，png，jpeg，doc，docx，pdf，zip，rar，文件小于100MB</span>
								</c:when>
								<c:otherwise>
									<span>${file.fileName }</span>&#12288;<a href="javascript:void(0)" onclick="addFile(this);">[重新上传]</a>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr id="mainContactTr"  <c:if test="${contractExt.contractCategoryId != contractCategoryEnumSecond.id }">style="display:none;"</c:if>>
					    <td style="text-align: right;padding-right: 10px;line-height: 25px;">关联主合同：</td>
						<td colspan="5" id="mainContactTd" style="line-height: 25px;" >
						   <span id="assistContractSpan" style="display:none;">
						      <font class="red">添加其他客户主合同：</font>
						      <input id="searchParam_Customer_other"  placeholder="输入客户名称/别名" class="inputText"  style="width: 180px;text-align: left;" onkeydown="adjustResults();" onfocus="adjustResults();"/>
				        	  <div id="suggest_Customer_other" class="ac_results" style="margin:0;position: fixed; z-index: 100;width: 370px;"></div>
				        	  <input type="hidden" id="result_Customer_other" />
				           </span><br/>
						   <span id="mainContactSpan">
					        <c:forEach items="${customerList}" var="customer">
								${customer.customerName }的主合同：<br/>
								<c:forEach items="${contractMap[customer.customerFlow] }" var="mainContract">
									<input type="checkbox" class="validate[required]" name="mainContract" onchange="showMainContactProduct(this)" value="${mainContract.contractFlow }"
									<c:forEach items="${refExtList }" var="ref">
										   <c:if test="${mainContract.contractFlow eq ref.contract.contractFlow }">checked="checked"</c:if>
									</c:forEach>>&#12288;
									<c:if test="${not empty mainContract.contractNo }">
										${mainContract.contractNo }：
									</c:if>${mainContract.contractName }
									<br/>
								</c:forEach>
							</c:forEach>
						   </span>
						</td>
					</tr>
			      </table>
				</form>
				<form id="productForm" style="position: relative;">
				<table cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;border-top: 0px;">
					<colgroup>
						<col width="3%"/>
						<col width="17%"/>
						<col width="15%"/>
						<col width="15%"/>
						<col width="10%"/>
						<col width="15%"/>
						<col width="10%"/>
					</colgroup>
					<tr>
						<th colspan="8" style="text-align: left;padding-left: 10px">合同产品
							<img title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />" style="float: right;margin-right: 20px;cursor: pointer;" onclick="delProduct();"/>
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="float: right;margin-right: 10px;cursor: pointer;" onclick="addProduct();"/>
						</th>
					</tr>
					<tr>
						<th></th>
						<th><span class="red">*</span>产品/项目名称</th>
						<th>备注</th>
						<th>安装地点</th>
						<th>版本号</th>
						<th>注册文件客户名</th>
						<th>注册文件有效期</th>
					</tr>
					<tbody id="productTb">

					<c:if test="${contractExt.contractTypeId ne  contractTypeEnumProductProject.id}">
						<c:forEach items="${productList}" var="product" varStatus="num">
							<tr>
								<td>
									<input type="checkbox" name="id" value="${num.count }"/>
									<input type="hidden" name="productFlow" value="${product.productFlow }"/>
									<input type="hidden" name="contractFlow" value="${product.contractFlow }"/>
								</td>
								<td style="text-align: center;">
								<span id="productSpan">
										<c:if test="${empty product.productTypeId}">
											<input type="text" class="inputText validate[required,maxSize[25]] templete" value="${product.productTypeName}" name="productTypeName" style="width:80%;text-align: left;"/>
										</c:if>
										<c:if test="${not empty product.productTypeId}">
											<span>
											 <select class="inputText validate[required] templete" id="productTypeId" name="productTypeId" style="width:80%;" onchange="showProductCheck(this);">
												 <option value="">请选择</option>
												 <c:forEach var="dict" items="${dictTypeEnumProductTypeList}">
													 <option value="${dict.dictId}"<c:if test="${product.productTypeId eq dict.dictId}"> selected</c:if>>${dict.dictName}</option>
												 </c:forEach>
											 </select>
											</span>
										</c:if>
								</span>
								</td>
								<td>
									<input type="text" class="inputText validate[maxSize[2000]]"value="${product.productItem }" name="productItem" style="width:90%;text-align: left;"/>
								</td>
								<td>
									<input type="text" class="inputText validate[maxSize[250]]"value="${product.installPlace }" placeholder="输入详细地址及服务器位置"  name="installPlace" style="width:90%;text-align: left;"/>
								</td>
								<td>
									<input type="text" class="inputText validate[maxSize[50]]"value="${product.versions }" name="versions" style="width:90%;text-align: left;"/>
								</td>
								<td>
									<input type="text" class="inputText validate[maxSize[250]]"value="${product.regFileClientName }" name="regFileClientName" style="width:90%;text-align: left;"/>
								</td>
								<td>
									<input class="inputText ctime" name="regFileIndate" value="${product.regFileIndate }"style="text-align: left;" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					<c:if test="${contractExt.contractTypeId eq  contractTypeEnumProductProject.id}">
						<c:forEach items="${productList}" var="product" varStatus="num">
							<tr>
								<td>
									<input type="checkbox" name="id" value="${num.count }"/>
									<input type="hidden" name="productFlow" value="${product.productFlow }"/>
									<input type="hidden" name="contractFlow" value="${product.contractFlow }"/>
								</td>
								<td style="text-align: center;">
									<span id="productSpan">
										<c:if test="${empty product.productTypeId}">
											<input type="text" class="inputText validate[required,maxSize[25]] templete" value="${product.productTypeName}" name="productTypeName" style="width:80%;text-align: left;"/>
											&#12288;<span title="切换为产品" style="cursor: pointer;" onclick="switchProduct(this,'product');">></span>
										</c:if>
										<c:if test="${not empty product.productTypeId}">
											<span>
											 <select class="inputText validate[required] templete" id="productTypeId" name="productTypeId" style="width:80%;" onchange="showProductCheck(this);">
												 <option value="">请选择</option>
												 <c:forEach var="dict" items="${dictTypeEnumProductTypeList}">
													 <option value="${dict.dictId}"<c:if test="${product.productTypeId eq dict.dictId}"> selected</c:if>>${dict.dictName}</option>
												 </c:forEach>
											 </select>
											</span>
											&#12288;<span title="切换为项目" style="cursor: pointer;" onclick="switchProduct(this,'project');">></span>
										</c:if>
									</span>
								</td>
								<td>
									<input type="text" class="inputText validate[maxSize[2000]]"value="${product.productItem }" name="productItem" style="width:90%;text-align: left;"/>
								</td>
								<td>
									<input type="text" class="inputText validate[maxSize[250]]"value="${product.installPlace }" placeholder="输入详细地址及服务器位置"  name="installPlace" style="width:90%;text-align: left;"/>
								</td>
								<td>
									<input type="text" class="inputText validate[maxSize[50]]"value="${product.versions }" name="versions" style="width:90%;text-align: left;"/>
								</td>
								<td>
									<input type="text" class="inputText validate[maxSize[250]]"value="${product.regFileClientName }" name="regFileClientName" style="width:90%;text-align: left;"/>
								</td>
								<td>
									<input class="inputText ctime" name="regFileIndate" value="${product.regFileIndate }"style="text-align: left;" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
								</td>
							</tr>
						</c:forEach>
					</c:if>
					</tbody>
				</table>
				</form>
				<form id="linkManForm" style="position: relative;">
				<table cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;border-top: 0px;">
					<colgroup>
						<col width="3%"/>
						<col width="10%"/>
						<col width="7%"/>
						<col width="10%"/>
						<col width="15%"/>
						<col width="15%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
						<col width="10%"/>
					</colgroup>
					<tr>
						<th colspan="11" style="text-align: left;padding-left: 10px">联系人信息
							<img title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />" style="float: right;margin-right: 20px;cursor: pointer;" onclick="delLinkMan();"/>
							<img title="选择客户联系人" src="<s:url value="/css/skin/${skinPath}/images/add_user.png" />" style="float: right;margin-right: 10px;cursor: pointer;" onclick="searchCustomerLinkMan();"/>
						</th>
					</tr>
					<tr>
						<th></th>
						<th><span class="red">*</span>姓名</th>
						<th>性别</th>
						<th>部门</th>
						<th>职务</th>
						<th>固话</th>
						<th>手机</th>		
						<th>邮箱</th>
						<th>主要联系人</th>
						<th>备注</th>
					</tr>
					<tbody id="linkManTb">
					<c:forEach items="${userList }" var="user" varStatus="num">
						<tr <c:if test="${user.recordStatus eq GlobalConstant.FLAG_N}"> style="background-color:#eeeeee;"</c:if>>
							<td>
								<input name="id" type="checkbox">
								<input type="hidden" name="userFlow" value="${user.userFlow }"/>
								<input type="hidden" name="recordFlow" value="${user.recordFlow }">
							</td>
							<td>
								<input style="width:90%;" name="userName"  class="inputText validate[required,maxSize[20]]" value="${user.customerUser.userName }" readonly="" type="text">
							</td>
							<td><select name="sexId" class="inputText" style="width:90%;">
								<option value="Man" <c:if test="${user.customerUser.sexId eq 'Man' }">selected="selected"</c:if>>男</option>
								<option value="Woman"<c:if test="${user.customerUser.sexId eq 'Man' }">selected="selected"</c:if>>女</option>
								<option value=""></option>
							</select>
							</td>
							<td>
								<input style="width:90%;" name="deptName" class="inputText validate[maxSize[100]]"   value="${user.customerUser.deptName }" type="text">
							</td>
							<td>
								<input class="inputText validate[maxSize[50]]" name="postName" style="width:90%;"
									   value="${user.customerUser.postName }" type="text">
							</td>
							<td>
								<input class="inputText validate[custom[phone2],maxSize[20]]" name="userTelphone"
									   style="width:90%;" value="${user.customerUser.userTelphone }" type="text">
							</td>
							<td>
								<input class="inputText validate[custom[mobile],maxSize[20]]" name="userCelphone"
									   style="width:90%;" value="${user.customerUser.userCelphone }" type="text">
							</td>
							<td>
								<input class="inputText validate[custom[email],maxSize[50]]" name="userEmail"
									   style="width:90%;" value="${user.customerUser.userEmail }" type="text">
							</td>
							<td>
								<select name="isMain" class="inputText" style="width:90%;">
									<option value=""></option>
									<option value="${GlobalConstant.FLAG_Y}"<c:if test="${user.customerUser.isMain eq  GlobalConstant.FLAG_Y}">selected="selected"</c:if> >是</option>
									<option value="${GlobalConstant.FLAG_N}"<c:if test="${user.customerUser.isMain eq  GlobalConstant.FLAG_N}">selected="selected"</c:if> >否</option>
								</select>
							</td>
							<td>
								<input class="inputText validate[maxSize[200]]" name="remark" style="width:90%;"
									   value="${user.customerUser.remark }"  type="text">
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				</form>
				<form id="powerForm" style="position: relative;">
				<table id="powerTable" cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;border-top: 0px;display: none;">
					<colgroup>
						<col width="3%"/>
						<col width="10%"/>
						<col width="27%"/>
						<col width="15%"/>
						<col width="25%"/>
					</colgroup>
					<tr>
						<th colspan="6" style="text-align: left;padding-left: 10px">开通权限详情<span class="red" style="padding-left: 12px;">*</span>
							<img title="删除" src="<s:url value="/css/skin/${skinPath}/images/del1.png" />" style="float: right;margin-right: 20px;cursor: pointer;" onclick="delPower();"/>
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="float: right;margin-right: 10px;cursor: pointer;" onclick="addPower();"/>
						</th>
					</tr>
					<tr>
						<th></th>
						<th>届别</th>
						<th>权限</th>
						<th>住院医师开通人数</th>
						<th>在校专硕开通人数</th>
						<th>人员名单</th>
					</tr>
					<tbody id="powerTb">
					<c:forEach items="${powerList}" var="p">
						<tr>
							<td>
								<input type="checkbox" name="id" value=""/>
								<input type="hidden" name="powerFlow" value="${p.powerFlow}"/>
							</td>
							<td>
								<input type="text" class="inputText ctime validate[required]" value="${p.sessionNumber}" name="sessionNumber" style="width: 130px;" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
							</td>
							<td>
								<input type="checkbox"  class="validate[required]" name="powerIds" id="GCGL" value="GCGL" <c:if test="${pdfn:contains(p.powerIds,'GCGL')}">checked="checked"</c:if> idName="过程管理"/><label>过程管理</label>&#12288;
								<input type="checkbox"  class="validate[required]" name="powerIds" id="APP" value="APP" <c:if test="${pdfn:contains(p.powerIds,'APP')}">checked="checked"</c:if> idName="APP"/><label>APP</label>&#12288;
								<input type="checkbox"  class="validate[required]" name="powerIds" id="CKK" value="CKK" <c:if test="${pdfn:contains(p.powerIds,'CKK')}">checked="checked"</c:if> idName="出科考核"/><label>出科考核</label>&#12288;
								<input type="checkbox"  class="validate[required]" name="powerIds" id="PXSC" value="PXSC" <c:if test="${pdfn:contains(p.powerIds,'PXSC')}">checked="checked"</c:if> idName="培训手册"/><label>培训手册</label>&#12288;
							</td>
							<td>
								<input type="text" class="inputText validate[required,custom[number],maxSize[5]]" value="${p.peopleNum}" name="peopleNum" style="width: 150px;"/>
							</td>
							<td>
								<input type="text" class="inputText validate[required,custom[number],maxSize[5]]" value="${p.graduatePeopleNum}" name="graduatePeopleNum" style="width: 150px;"/>
							</td>
							<td>
								<c:set var="file" value="${fileMap[p.powerFlow]}"></c:set>
								<form  enctype="multipart/form-data" method="post">
									<input type="hidden" name="isTemp" value="temp"/>
									<input type="hidden" name="oldFileFlow" value="${file.fileFlow}"/>
									<input type="hidden" name="fileFlow" value=""/>
									<a name="fileName" href="<s:url value='/erp/crm/downFile'/>?fileFlow=${file.fileFlow}">${file.fileName}</a>
									<a href="javascript:void(0)" onclick="changeFile(this)">${(empty file) ? '[上传]' :'[重新上传]'}</a>
									<input type="file" style="display: none;" class="validate[]" name="file" accept=".xls, .xlsx" onchange="uploadFile(this);">
								</form>
							</td>
						</tr>
					</c:forEach>
					</tbody>
				</table>
				<table id="otherPower" cellpadding="0" cellspacing="0" class="xllist" style="width: 100%;border-top: 0px;display: none;">
					<tr>
						<th  style="text-align: left;padding-left: 10px">其他角色权限</th>
					</tr>
					<tr>
						<td style="text-align: left;padding-left: 20px">
							<c:forEach items="${otherPowerList}" var="p">
									<input hidden name="otherPowerFlow" value="${p.otherPowerFlow}"/>
									<input type="checkbox"  class="" name="powerIds" id="DJAPP" value="DJAPP"<c:if test="${pdfn:contains(p.powerIds,'DJAPP')}">checked="checked"</c:if> idName="带教APP"/><label>带教APP</label>&#12288;
							</c:forEach>
							<c:if test="${empty otherPowerList}">
									<input hidden name="otherPowerFlow" />
									<input type="checkbox"  class="" name="powerIds" id="DJAPP" value="DJAPP" idName="带教APP"/><label>带教APP</label>&#12288;

							</c:if>
						</td>
					</tr>
				</table>
				</form>
				<form id="refForm">
				   <table  style="display:none;">
				      <tbody id="refTb">
						  <c:forEach items="${customerList}" var="customer">
							  <c:forEach items="${contractMap[customer.customerFlow] }" var="mainContract">
								  <c:forEach items="${refExtList }" var="ref">
										 <c:if test="${mainContract.contractFlow eq ref.contract.contractFlow }">
											 <tr id="${mainContract.contractFlow}">
											 <td><input type="text" name="contractFlow" value="${mainContract.contractFlow}"/></td>
											 </tr>
										 </c:if>
								  </c:forEach>
							  </c:forEach>
						  </c:forEach>
				      </tbody>
				   </table>
				</form>
				<div class="button" style="width: 100%">
					<input id="saveButton" class="search" type="button" value="保&#12288;存" onclick="save();" />
					<input class="search" type="button" value="关&#12288;闭" onclick="jboxCloseMessager();"/>
				</div>
			
			</div>
		</div>
	</div>
</div>
</div>
<table style="display: none;">
	<tbody id="beforeRefTb">
	<tr><td>以前的:</td></tr>
	<c:forEach items="${refExtList }" var="ref">
		<tr>
			<td><input type="text" name="beforeContractFlow" value="${ref.contractFlow }"/></td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<div style="display: none">
<span id="file">
 <input type="file" name="file"  class="validate[custom[fileType]]"/><br/>
 <span style="color: red">支持格式jpg，bmp，png，jpeg，doc，docx，pdf，zip，rar，文件小于100MB</span>
</span>
</div>
<span id="clone_${contractCategoryEnumOperation.id }" style="display:none;">
    <select class="inputText validate[required]"  name="contractTypeId" style="width: 80%;" onchange="showPower();">
       <option value="">请选择</option>
	   <c:forEach items="${contractTypeEnumList }" var="contractType">
	       <c:if test="${contractType.categoryId eq 'Operation' }">
	       <option value="${contractType.id }">${contractType.name }</option>
	       </c:if>
	   </c:forEach>
    </select>
</span>
<span id="clone_${contractCategoryEnumSales.id }" style="display:none;">
    <select class="inputText validate[required]"  name="contractTypeId" style="width: 80%;" onchange="changeContractTypeTd();">
		<option value="">请选择</option>
		<c:forEach items="${contractTypeEnumList }" var="contractType">
			<c:if test="${contractType.categoryId eq 'Sales' }">
				<option value="${contractType.id }">${contractType.name }</option>
			</c:if>
		</c:forEach>
	</select>
</span>
<span id="clone_${contractCategoryEnumSecond.id }" style="display:none;">
    <select class="inputText validate[required]"  name="contractTypeId" style="width: 80%;">
       <option value="">请选择</option>
	   <c:forEach items="${contractTypeEnumList }" var="contractType">
	      <c:if test="${contractType.categoryId eq 'Second' }">
	      <option value="${contractType.id }">${contractType.name }</option>
	      </c:if>
	   </c:forEach>
    </select>
</span>
<span id="clone_${contractCategoryEnumSell.id }" style="display:none;">
    <select name="consumerFlow" id="consumerFlow" class="inputText validate[required]" style="width:80%;">
        <option value="">请选择</option>
        <c:forEach items="${consumerList }" var="consumer">
          <option value="${consumer.customerFlow }">${consumer.customerName }</option>
        </c:forEach>
    </select>
</span>
<table style="display: none;" id="productClone">
	<tr>
		<td><input type="checkbox" name="id" value=""/></td>
		<td style="text-align: center;">
		<span id="productSpan">
			<select class="inputText validate[required] templete" id="productTypeId" name="productTypeId" style="width:80%;" onchange="showProductCheck(this);">
				<option value="">请选择</option>
				<c:forEach var="dict" items="${dictTypeEnumProductTypeList}">
					<option value="${dict.dictId}">${dict.dictName}</option>
				</c:forEach>
			</select>
		</span>
		</td>
		<td>
		 <input type="text" class="inputText validate[maxSize[2000]]" name="productItem" style="width:60%;text-align: left;"/>
		</td>
		<td> 
		   <input type="text" class="inputText validate[maxSize[250]]" placeholder="输入详细地址及服务器位置"  name="installPlace" style="width:90%;text-align: left;"/>
		</td>
		<td> 
		   <input type="text" class="inputText validate[maxSize[50]]" name="versions" style="width:90%;text-align: left;"/>
		</td>
		<td> 
		   <input type="text" class="inputText validate[maxSize[250]]" name="regFileClientName" style="width:90%;text-align: left;"/>
		</td>
		<td> 
		   <input class="inputText ctime" name="regFileIndate" style="text-align: left;" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
		</td>
	</tr>
</table>
<table style="display: none;" id="projectClone">
	<tr>
		<td>
			<input type="checkbox" name="id" value=""/>
			<input type="hidden" name="productFlow" value=""/>
			<input type="hidden" name="contractFlow" value=""/>
		</td>
		<td style="text-align: center;">
			<span id="projectSpan">
				<input type="text" class="inputText validate[required,maxSize[25]] templete" name="productTypeName" style="width:80%;text-align: left;"/>
			</span>
		</td>
		<td>
		 <input type="text" class="inputText validate[maxSize[2000]]" name="productItem" style="width:90%;text-align: left;"/>
		</td>
		<td> 
		   <input type="text" class="inputText validate[maxSize[250]]"  placeholder="输入详细地址及服务器位置"  name="installPlace" style="width:90%;text-align: left;"/>
		</td>
		<td> 
		   <input type="text" class="inputText validate[maxSize[50]]" name="versions" style="width:90%;text-align: left;"/>
		</td>
		<td> 
		   <input type="text" class="inputText validate[maxSize[250]]" name="regFileClientName" style="width:90%;text-align: left;"/>
		</td>
		<td> 
		   <input class="inputText ctime" name="regFileIndate" style="text-align: left;" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
		</td>
	</tr>
</table>
<table style="display: none;" id="productProjectClone">
	<tr>
		<td>
			<input type="checkbox" name="id" value=""/>
			<input type="hidden" name="productFlow" value=""/>
			<input type="hidden" name="contractFlow" value=""/>
		</td>
		<td style="text-align: center;" id="productTd">
			<span>
			 <select class="inputText validate[required] templete" id="productTypeId" name="productTypeId" style="width:80%;" onchange="showProductCheck(this);">
				<option value="">请选择</option>
				<c:forEach var="dict" items="${dictTypeEnumProductTypeList}">
					<option value="${dict.dictId}">${dict.dictName}</option>
				</c:forEach>
			</select>
			</span>
			&#12288;<span title="切换为项目" style="cursor: pointer;" onclick="switchProduct(this,'project');">></span>
		</td>
		<td>
		 <input type="text" class="inputText validate[maxSize[2000]]" name="productItem" style="width:90%;text-align: left;"/>
		</td>
		<td> 
		   <input type="text" class="inputText validate[maxSize[250]]" placeholder="输入详细地址及服务器位置"  name="installPlace" style="width:90%;text-align: left;"/>
		</td>
		<td> 
		   <input type="text" class="inputText validate[maxSize[50]]" name="versions" style="width:90%;text-align: left;"/>
		</td>
		<td> 
		   <input type="text" class="inputText validate[maxSize[250]]" name="regFileClientName" style="width:90%;text-align: left;"/>
		</td>
		<td> 
		   <input class="inputText ctime" name="regFileIndate" style="text-align: left;" type="text" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
		</td>
	</tr>
</table>

<table style="display: none;" id="clone">
	<tr>
		<td>
		   <input type="checkbox" name="id" value=""/>
		   <input type="hidden" name="userFlow"/>
		</td>
		<td><input type="text" class="inputText validate[required,maxSize[10]]" oldName="" name="userName" onchange="checkCustomerUserName(this);" style="width:90%;"/></td>
		<td><select class="inputText" name="sexId" style="width:90%;">
				<c:forEach var="userSex" items="${userSexEnumList}">
				  <c:if test="${userSex.id != userSexEnumUnknown.id}">
					<option value="${userSex.id}" >${userSex.name}</option>
				  </c:if>
				</c:forEach>
				<option value=""></option>
			</select>
		</td>
		<td><input type="text" class="inputText validate[maxSize[50]]" name="deptName" style="width:90%;"/></td>
		<td><input type="text" class="inputText validate[maxSize[25]]" name="postName" style="width:90%;"/></td>
		<%-- <td><select class="inputText" name="userCategoryId" style="width:90%;">
				<option value=""></option>
				<c:forEach var="userCategory" items="${dictTypeEnumUserCategoryList}">
					<option value="${userCategory.dictId}">${userCategory.dictName}</option>
				</c:forEach>
			</select>
		</td> --%>
		
		<td><input type="text" class="inputText validate[custom[phone2],maxSize[20]]" name="userTelphone" style="width:90%;"/></td>
		<td><input type="text" class="inputText validate[custom[mobile],maxSize[20]]" name="userCelphone" style="width:90%;"/></td>
		<td><input type="text" class="inputText validate[custom[email],maxSize[50]]" name="userEmail" style="width:90%;"/></td>
		<!-- <td><input type="text" class="inputText validate[custom[qq],maxSize[50]]" name="userQq" style="width:90%;"/></td> -->
		<td>
			<select name="isMain" class="inputText" style="width:90%;">
			    <option value=""></option>
				<option value="${GlobalConstant.FLAG_Y}">是</option>
				<option value="${GlobalConstant.FLAG_N}">否</option>
			</select>
		</td>
		<td><input type="text" class="inputText validate[maxSize[100]]" name="remark" style="width:90%;"/></td>
	</tr>
</table>
<table style="display: none;" id="powerClone">
	<tr>
		<td>
			<input type="checkbox" name="id" value=""/>
			<input type="hidden" name="powerFlow" value=""/>
		</td>
		<td>
			<input type="text" class="inputText ctime validate[required]" name="sessionNumber" style="width: 130px;" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
		</td>
		<td>
			<input type="checkbox"  class="validate[required]" name="powerIds" id="GCGL" value="GCGL" idName="过程管理"/><label>过程管理</label>&#12288;
			<input type="checkbox"  class="validate[required]" name="powerIds" id="APP" value="APP" idName="APP"/><label>APP</label>&#12288;
			<input type="checkbox"  class="validate[required]" name="powerIds" id="CKK" value="CKK" idName="出科考核"/><label>出科考核</label>&#12288;
			<input type="checkbox"  class="validate[required]" name="powerIds" id="PXSC" value="PXSC" idName="培训手册"/><label>培训手册</label>&#12288;
		</td>
		<td>
			<input type="text" class="inputText validate[required,custom[number],maxSize[5]]" name="peopleNum" style="width: 150px;"/>
		</td>
		<td>
			<input type="text" class="inputText validate[required,custom[number],maxSize[5]]" name="graduatePeopleNum" style="width: 150px;"/>
		</td>
		<td>
			<form  enctype="multipart/form-data" method="post">
				<input type="hidden" name="isTemp" value="temp"/>
				<input type="hidden" name="oldFileFlow" value=""/>
				<input type="hidden" name="fileFlow" value=""/>
				<a name="fileName" href="javascript:void(0);"></a>
				<a href="javascript:void(0)" onclick="changeFile(this)">[上传]</a>
				<input type="file" style="display: none;" class="validate[required]" name="file" accept=".xls, .xlsx" onchange="uploadFile(this);">
			</form>
		</td>
	</tr>
</table>
	<tr>
		<td><input type="checkbox" name="id" value=""/></td>
		<td><input type="text" class="inputText ctime validate[required]" name="billPlanDate" style="width: 130px;" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
		</td>
		<td><input type="text" class="inputText validate[required,custom[number],maxSize[9]] money" name="billPayFund" style="width: 150px;" onblur="timeMask(this);"/>元
		</td>
		<!-- <td></td> -->
	</tr>
</table>
<table style="display: none;" id="powerClone">
	<tr>
		<td>
			<input type="checkbox" name="id" value=""/>
		</td>
		<td>
			<input type="text" class="inputText ctime validate[required]" name="sessionNumber" style="width: 130px;" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
		</td>
		<td>
			<input type="checkbox"  class="validate[required]" name="powerIds" id="GCGL" value="GCGL" idName="过程管理"/><label>过程管理</label>&#12288;
			<input type="checkbox"  class="validate[required]" name="powerIds" id="APP" value="APP" idName="APP"/><label>APP</label>&#12288;
			<input type="checkbox"  class="validate[required]" name="powerIds" id="CKK" value="CKK" idName="出科考核"/><label>出科考核</label>&#12288;
			<input type="checkbox"  class="validate[required]" name="powerIds" id="PXSC" value="PXSC" idName="培训手册"/><label>培训手册</label>&#12288;
		</td>
		<td>
			<input type="text" class="inputText validate[required,custom[number],maxSize[5]]" name="peopleNum" style="width: 150px;"/>
		</td>
		<td>
			<input type="text" class="inputText validate[required,custom[number],maxSize[5]]" name="graduatePeopleNum" style="width: 150px;"/>
		</td>
		<td>
			<form  enctype="multipart/form-data" method="post">
				<input type="hidden" name="isTemp" value="temp"/>
				<input type="hidden" name="fileFlow" value=""/>
				<input type="file"  class="validate[]" name="file" accept=".xls, .xlsx" onchange="uploadFile(this);">
			</form>
		</td>
	</tr>
</table>

</body>
</html>