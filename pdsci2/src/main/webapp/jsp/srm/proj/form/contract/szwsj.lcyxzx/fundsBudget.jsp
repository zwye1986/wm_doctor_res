<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" /> 
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

function defaultIfEmpty(obj){
	if(isNaN(obj)){
		return 0;
	}else{
		return obj;
	}
}


function calculate() {
	
	var amountFund = 0.0;
	var constructOrgFund = parseFloat( $("input[name='constructOrgFund']").val()) ;
	var assumeOrgFund = parseFloat(  $("input[name='assumeOrgFund']").val());
	var assumeOrgChargeOrgFund =  parseFloat( $("input[name='assumeOrgChargeOrgFund']").val());
	var otherFund =  parseFloat( $("input[name='otherFund']").val());
	
	amountFund =  defaultIfEmpty(constructOrgFund) + defaultIfEmpty(assumeOrgFund) + defaultIfEmpty(assumeOrgChargeOrgFund) + defaultIfEmpty(otherFund);

	$("input[name='amountFund']").val(parseFloat(amountFund.toFixed(3)));
}


function budgetCalculate(index , obj){
	 var sumVal = 0;
	 var cell = $(obj).parent()[0].cellIndex+1;
	 $("#fund2 tr:not(:last) td:nth-child("+cell+")").each(function(){
		 var val = $(this).children("input").val();
		 if (val == null || val == undefined || val == '' || isNaN(val)){
				val = 0;
			}
		 sumVal = parseFloat(sumVal)+parseFloat(val);
	  });
	if(index==1){
		sumInput = $("input[name=amountFirstYear_SELF]");	
	}else if(index==2){
		sumInput = $("input[name=amountFirstYear_APPRO]");	
	}else if(index==3){
		sumInput = $("input[name=amountSecondYear_SELF]");
	}else if(index==4){
		sumInput = $("input[name=amountSecondYear_APPRO]");
	}else if(index==5){
		sumInput = $("input[name=amountThirdYear_SELF]");
	}else if(index==6){
		sumInput = $("input[name=amountThirdYear_APPRO]");
	}else if(index==7){
		sumInput = $("input[name=amountFourYear_SELF]");
	}else if(index==8){
		sumInput = $("input[name=amountFourYear_APPRO]");
	}else if(index==9){
		sumInput = $("input[name=amountFiveYear_SELF]");
	}else if(index==10){
		sumInput = $("input[name=amountFiveYear_APPRO]");
	}
	sumInput.val(parseFloat(sumVal.toFixed(3)));
}
</script>
</c:if>

<style type="text/css">
	#fund td{text-align: center;}
</style>
</head>
		
        	    	<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
						<input type="hidden" id="pageName" name="pageName" value="fundsBudget"/>
						<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                        <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
						<input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

  					<table width="100%" cellspacing="0" cellpadding="0" class="basic" style="margin-top: 10px;">
  						<tr>
						  	<th colspan="2" style="text-align: left;padding-left: 15px;font-size: 14px;">六、经费预算</th>
						</tr>
						<tr>
							<td width="25%" style="text-align: center;">甲方资助金额：</td>
							<td>
  								<input name="constructOrgFund" value="${resultMap.constructOrgFund}"  onchange="calculate()" type="text" class="inputText validate[custom[number]]" style="margin-right: 0px; width:26%; margin-left: 22px;"/>（万元）
  							    <span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>
  							</td>
						</tr>
						<tr>
							<td style="text-align: center;">乙方匹配经费：</td>
  							<td>
  								<input name="assumeOrgFund" value="${resultMap.assumeOrgFund}" onchange="calculate()" type="text" class="inputText validate[custom[number]]" style="margin-right: 0px; width:26%; margin-left: 22px;"/>（万元）
  							</td>
						</tr>
						<tr>
							<td style="text-align: center;">其他来源经费：</td>
  							<td>
  								<input name="otherFund" value="${resultMap.otherFund}" onchange="calculate()" type="text" class="inputText validate[custom[number]]" style="margin-right: 0px; width:26%; margin-left: 22px;"/>（万元）
  							</td>
						</tr>
						<tr>
							<td style="text-align: center;">合      计：</td>
  							<td>
  								<input name="amountFund" value="${resultMap.amountFund}" type="text" class="inputText validate[custom[number]]" style="margin-right: 0px; width:26%; margin-left: 22px;"/>（万元）
  							</td>
						</tr>
					</table>

					<table width="100%" cellspacing="0" cellpadding="0" class="basic" style="border-top: none;">
						<tr>
							<th width="25%" style="text-align: center;background: #fff;" rowspan="2">申请资助的预算支出科目</th>
							<td style="text-align: center;" colspan="2">
						    	<fmt:formatDate value="${now}" type="both" dateStyle="long" pattern="yyyy" var="bb"/>
				                <input name="year1" class='inputText'  type="text" <c:if test='${empty resultMap.year1 and param.view != GlobalConstant.FLAG_Y}'>value="${bb}"</c:if><c:if test='${!empty resultMap.year1}'>value="${resultMap.year1}"</c:if>  />
				            </td>
				            <td style="text-align: center;" colspan="2">
				            	<input name="year2" class='inputText' type="text" <c:if test='${empty resultMap.year2 and param.view != GlobalConstant.FLAG_Y}'>value="${bb+1}"</c:if><c:if test='${!empty resultMap.year2}'>value="${resultMap.year2}"</c:if>  />
				            </td> 
				            <td style="text-align: center;" colspan="2">
				            	<input name="year3" class='inputText' type="text" <c:if test='${empty resultMap.year3 and param.view != GlobalConstant.FLAG_Y}'>value="${bb+2}"</c:if><c:if test='${!empty resultMap.year3}'>value="${resultMap.year3}"</c:if>  />
				            </td>
				             <td style="text-align: center;" colspan="2">
				            	<input name="year4" class='inputText' type="text" <c:if test='${empty resultMap.year4 and param.view != GlobalConstant.FLAG_Y}'>value="${bb+3}"</c:if><c:if test='${!empty resultMap.year4}'>value="${resultMap.year4}"</c:if>  />
				            </td>
				             <td style="text-align: center;" colspan="2">
				            	<input name="year5" class='inputText' type="text" <c:if test='${empty resultMap.year5 and param.view != GlobalConstant.FLAG_Y}'>value="${bb+4}"</c:if><c:if test='${!empty resultMap.year5}'>value="${resultMap.year5}"</c:if>  />
				            </td>
						</tr>
						<tr>
							<td>单位配套</td><td>财政拨款</td><td>单位配套</td><td>财政拨款</td><td>单位配套</td><td>财政拨款</td><td>单位配套</td><td>财政拨款</td><td>单位配套</td><td>财政拨款</td>
						</tr>
						<tbody id="fund2">
						<tr>
			   				<td style="text-align: center">1.国内外进修费用(万元)：</td>
							<td><input type="text" name="inlandFirstYear_SELF" value="${resultMap.inlandFirstYear_SELF}" onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="inlandFirstYear_APPRO" value="${resultMap.inlandFirstYear_APPRO}" onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="inlandSecondYear_SELF" value="${resultMap.inlandSecondYear_SELF}" onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="inlandSecondYear_APPRO" value="${resultMap.inlandSecondYear_APPRO}" onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="inlandThirdYear_SELF" value="${resultMap.inlandThirdYear_SELF}" onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="inlandThirdYear_APPRO" value="${resultMap.inlandThirdYear_APPRO}" onchange="budgetCalculate(6,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="inlandFourYear_SELF" value="${resultMap.inlandFourYear_SELF}" onchange="budgetCalculate(7,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="inlandFourYear_APPRO" value="${resultMap.inlandFourYear_APPRO}" onchange="budgetCalculate(8,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="inlandFiveYear_SELF" value="${resultMap.inlandFiveYear_SELF}" onchange="budgetCalculate(9,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="inlandFiveYear_APPRO" value="${resultMap.inlandFiveYear_APPRO}" onchange="budgetCalculate(10,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							</tr>
						<tr>
						    <td style="text-align: center">2.学术交流费用(万元)：</td>
							<td><input type="text" name="communicationFirstYear_SELF" value="${resultMap.communicationFirstYear_SELF}" onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="communicationFirstYear_APPRO" value="${resultMap.communicationFirstYear_APPRO}" onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="communicationSecondYear_SELF" value="${resultMap.communicationSecondYear_SELF}" onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="communicationSecondYear_APPRO" value="${resultMap.communicationSecondYear_APPRO}" onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="communicationThirdYear_SELF" value="${resultMap.communicationThirdYear_SELF}" onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="communicationThirdYear_APPRO" value="${resultMap.communicationThirdYear_APPRO}" onchange="budgetCalculate(6,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="communicationFourYear_SELF" value="${resultMap.communicationFourYear_SELF}" onchange="budgetCalculate(7,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="communicationFourYear_APPRO" value="${resultMap.communicationFourYear_APPRO}" onchange="budgetCalculate(8,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="communicationFiveYear_SELF" value="${resultMap.communicationFiveYear_SELF}" onchange="budgetCalculate(9,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="communicationFiveYear_APPRO" value="${resultMap.communicationFiveYear_APPRO}" onchange="budgetCalculate(10,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
						</tr>
						<tr>
						    <td style="text-align: center">3.仪器设备费(万元)：</td>
							<td><input type="text" name="equipmentFirstYear_SELF" value="${resultMap.equipmentFirstYear_SELF}" onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="equipmentFirstYear_APPRO" value="${resultMap.equipmentFirstYear_APPRO}" onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="equipmentSecondYear_SELF" value="${resultMap.equipmentSecondYear_SELF}" onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="equipmentSecondYear_APPRO" value="${resultMap.equipmentSecondYear_APPRO}" onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="equipmentThirdYear_SELF" value="${resultMap.equipmentThirdYear_SELF}" onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="equipmentThirdYear_APPRO" value="${resultMap.equipmentThirdYear_APPRO}" onchange="budgetCalculate(6,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="equipmentFourYear_SELF" value="${resultMap.equipmentFourYear_SELF}" onchange="budgetCalculate(7,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="equipmentFourYear_APPRO" value="${resultMap.equipmentFourYear_APPRO}" onchange="budgetCalculate(8,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="equipmentFiveYear_SELF" value="${resultMap.equipmentFiveYear_SELF}" onchange="budgetCalculate(9,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="equipmentFiveYear_APPRO" value="${resultMap.equipmentFiveYear_APPRO}" onchange="budgetCalculate(10,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
						</tr>
						<tr>
						    <td style="text-align: center">4.实验材料费(万元)：</td>
							<td><input type="text" name="materialFirstYear_SELF" value="${resultMap.materialFirstYear_SELF}" onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="materialFirstYear_APPRO" value="${resultMap.materialFirstYear_APPRO}" onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="materialSecondYear_SELF" value="${resultMap.materialSecondYear_SELF}" onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="materialSecondYear_APPRO" value="${resultMap.materialSecondYear_APPRO}" onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="materialThirdYear_SELF" value="${resultMap.materialThirdYear_SELF}" onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="materialThirdYear_APPRO" value="${resultMap.materialThirdYear_APPRO}" onchange="budgetCalculate(6,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="materialFourYear_SELF" value="${resultMap.materialFourYear_SELF}" onchange="budgetCalculate(7,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="materialFourYear_APPRO" value="${resultMap.materialFourYear_APPRO}" onchange="budgetCalculate(8,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="materialFiveYear_SELF" value="${resultMap.materialFiveYear_SELF}" onchange="budgetCalculate(9,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="materialFiveYear_APPRO" value="${resultMap.materialFiveYear_APPRO}" onchange="budgetCalculate(10,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
						</tr>
						<tr>
						    <td style="text-align: center">5.实验动物费(万元)：</td>
							<td><input type="text" name="animalFirstYear_SELF" value="${resultMap.animalFirstYear_SELF}" onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="animalFirstYear_APPRO" value="${resultMap.animalFirstYear_APPRO}" onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="animalSecondYear_SELF" value="${resultMap.animalSecondYear_SELF}" onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="animalSecondYear_APPRO" value="${resultMap.animalSecondYear_APPRO}" onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="animalThirdYear_SELF" value="${resultMap.animalThirdYear_SELF}" onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="animalThirdYear_APPRO" value="${resultMap.animalThirdYear_APPRO}" onchange="budgetCalculate(6,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="animalFourYear_SELF" value="${resultMap.animalFourYear_SELF}" onchange="budgetCalculate(7,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="animalFourYear_APPRO" value="${resultMap.animalFourYear_APPRO}" onchange="budgetCalculate(8,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="animalFiveYear_SELF" value="${resultMap.animalFiveYear_SELF}" onchange="budgetCalculate(9,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="animalFiveYear_APPRO" value="${resultMap.animalFiveYear_APPRO}" onchange="budgetCalculate(10,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
					    </tr>
					    
						<tr>
						    <td style="text-align: center">6.人员培养费用(万元)：</td>
							<td><input type="text" name="trainFirstYear_SELF" value="${resultMap.trainFirstYear_SELF}" onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="trainFirstYear_APPRO" value="${resultMap.trainFirstYear_APPRO}" onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="trainSecondYear_SELF" value="${resultMap.trainSecondYear_SELF}" onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="trainSecondYear_APPRO" value="${resultMap.trainSecondYear_APPRO}" onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="trainThirdYear_SELF" value="${resultMap.trainThirdYear_SELF}" onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="trainThirdYear_APPRO" value="${resultMap.trainThirdYear_APPRO}" onchange="budgetCalculate(6,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="trainFourYear_SELF" value="${resultMap.trainFourYear_SELF}" onchange="budgetCalculate(7,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="trainFourYear_APPRO" value="${resultMap.trainFourYear_APPRO}" onchange="budgetCalculate(8,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="trainFiveYear_SELF" value="${resultMap.trainFiveYear_SELF}" onchange="budgetCalculate(9,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="trainFiveYear_APPRO" value="${resultMap.trainFiveYear_APPRO}" onchange="budgetCalculate(10,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
					    </tr>
						<tr>
						    <td style="text-align: center">7.购买图书、资料费(万元)：</td>
							<td><input type="text" name="bookTrFirstYear_SELF" value="${resultMap.bookTrFirstYear_SELF}" onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="bookTrFirstYear_APPRO" value="${resultMap.bookTrFirstYear_APPRO}" onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="bookTrSecondYear_SELF" value="${resultMap.bookTrSecondYear_SELF}" onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="bookTrSecondYear_APPRO" value="${resultMap.bookTrSecondYear_APPRO}" onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="bookTrThirdYear_SELF" value="${resultMap.bookTrThirdYear_SELF}" onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="bookTrThirdYear_APPRO" value="${resultMap.bookTrThirdYear_APPRO}" onchange="budgetCalculate(6,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="bookTrFourYear_SELF" value="${resultMap.bookTrFourYear_SELF}" onchange="budgetCalculate(7,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="bookTrFourYear_APPRO" value="${resultMap.bookTrFourYear_APPRO}" onchange="budgetCalculate(8,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="bookTrFiveYear_SELF" value="${resultMap.bookTrFiveYear_SELF}" onchange="budgetCalculate(9,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="bookTrFiveYear_APPRO" value="${resultMap.bookTrFiveYear_APPRO}" onchange="budgetCalculate(10,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
					    </tr>
						<tr>
						    <td style="text-align: center">8.研究项目费用(万元)：</td>
							<td><input type="text" name="researchFirstYear_SELF" value="${resultMap.researchFirstYear_SELF}" onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="researchFirstYear_APPRO" value="${resultMap.researchFirstYear_APPRO}" onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="researchSecondYear_SELF" value="${resultMap.researchSecondYear_SELF}" onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="researchSecondYear_APPRO" value="${resultMap.researchSecondYear_APPRO}" onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="researchThirdYear_SELF" value="${resultMap.researchThirdYear_SELF}" onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="researchThirdYear_APPRO" value="${resultMap.researchThirdYear_APPRO}" onchange="budgetCalculate(6,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="researchFourYear_SELF" value="${resultMap.researchFourYear_SELF}" onchange="budgetCalculate(7,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="researchFourYear_APPRO" value="${resultMap.researchFourYear_APPRO}" onchange="budgetCalculate(8,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="researchFiveYear_SELF" value="${resultMap.researchFiveYear_SELF}" onchange="budgetCalculate(9,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="researchFiveYear_APPRO" value="${resultMap.researchFiveYear_APPRO}" onchange="budgetCalculate(10,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
					    </tr>
						<tr>
						    <td style="text-align: center">9.其他费用(万元)：</td>
							<td><input type="text" name="otherFirstYear_SELF" value="${resultMap.otherFirstYear_SELF}" onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="otherFirstYear_APPRO" value="${resultMap.otherFirstYear_APPRO}" onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="otherSecondYear_SELF" value="${resultMap.otherSecondYear_SELF}" onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="otherSecondYear_APPRO" value="${resultMap.otherSecondYear_APPRO}" onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="otherThirdYear_SELF" value="${resultMap.otherThirdYear_SELF}" onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="otherThirdYear_APPRO" value="${resultMap.otherThirdYear_APPRO}" onchange="budgetCalculate(6,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="otherFourYear_SELF" value="${resultMap.otherFourYear_SELF}" onchange="budgetCalculate(7,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="otherFourYear_APPRO" value="${resultMap.otherFourYear_APPRO}" onchange="budgetCalculate(8,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="otherFiveYear_SELF" value="${resultMap.otherFiveYear_SELF}" onchange="budgetCalculate(9,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="otherFiveYear_APPRO" value="${resultMap.otherFiveYear_APPRO}" onchange="budgetCalculate(10,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
					    </tr>
						<tr>
						    <td style="text-align: center">合     计(万元)：</td>
							<td><input type="text" name="amountFirstYear_SELF" value="${resultMap.amountFirstYear_SELF}" onchange="budgetCalculate(1,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="amountFirstYear_APPRO" value="${resultMap.amountFirstYear_APPRO}" onchange="budgetCalculate(2,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="amountSecondYear_SELF" value="${resultMap.amountSecondYear_SELF}" onchange="budgetCalculate(3,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="amountSecondYear_APPRO" value="${resultMap.amountSecondYear_APPRO}" onchange="budgetCalculate(4,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="amountThirdYear_SELF" value="${resultMap.amountThirdYear_SELF}" onchange="budgetCalculate(5,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="amountThirdYear_APPRO" value="${resultMap.amountThirdYear_APPRO}" onchange="budgetCalculate(6,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="amountFourYear_SELF" value="${resultMap.amountFourYear_SELF}" onchange="budgetCalculate(7,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="amountFourYear_APPRO" value="${resultMap.amountFourYear_APPRO}" onchange="budgetCalculate(8,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="amountFiveYear_SELF" value="${resultMap.amountFiveYear_SELF}" onchange="budgetCalculate(9,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
							<td><input type="text" name="amountFiveYear_APPRO" value="${resultMap.amountFiveYear_APPRO}" onchange="budgetCalculate(10,this)" class="inputText validate[custom[number]]" style="width:80%;"/></td>
					    </tr>
					    </tbody>
					</table>
      						
           			</form>
               		<div class="button" style="width:100%;
               		<c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
               			<input id="prev" type="button" onclick="nextOpt('subjectUserList')" class="search" value="上一步"/>
                	    <input id="nxt" type="button" onclick="nextOpt('finish')" class="search" value="完&#12288;成"/>
					</div>