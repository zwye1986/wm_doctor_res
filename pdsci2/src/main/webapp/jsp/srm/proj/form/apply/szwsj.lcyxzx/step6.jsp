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

$(document).ready(function(){
	if($("#researchTb tr").length<=0){
		add('research');
	}
});

function add(tb){
	var length = $("#"+tb+"Tb").children().length;
	if(length > 14){
		jboxTip("限填15项！");
		return false; 
	}
	//计算行数
	$("#"+tb+"Num").val(length+1);
	
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
		//计算行数
		var length = $("#"+tb+"Tb").children().length;
		$("#"+tb+"Num").val(length);
	});
}


function defaultIfEmpty(obj){
	if(isNaN(obj)){
		return 0;
	}else{
		return obj;
	}
}

//计算项目、经费
function calculate(){
	var countryProjNum = 0; 
	var hygieneMinistryProjNum = 0;
	var educationProjNum = 0;
	var scienceOfficeProjNum = 0;
	var internationalProjNum = 0;
	var scienceBureauProjNum = 0;
	var hygieneBureauProjNum = 0;
	var amountNum = 0;
	
	var countryProjFund = 0; 
	var hygieneMinistryProjFund = 0;
	var educationProjFund = 0;
	var scienceOfficeProjFund = 0;
	var internationalProjFund = 0;
	var scienceBureauProjFund = 0;
	var hygieneBureauProjFund = 0;
	var amountFunds = 0;

	//遍历经费
	$("input[name='research_projFund']").each(function (index, domEle) {
		var projFund  = domEle.value;
		//alert(projFund);
		if(projFund ==''){
			projFund = 0;
		}else{
			projFund  = parseFloat(projFund);
		}
		//项目来源
		var projSource = $(this).parent().prev().prev().children().val();
		if(projSource == '国家级科研项目（科技部）'){
			countryProjNum += 1;
			countryProjFund += projFund;
		}
		if(projSource == '国家自然科学基金'){
			hygieneMinistryProjNum += 1;
			hygieneMinistryProjFund += projFund;
		}
		if(projSource == '国家卫计委'){
			educationProjNum += 1;
			educationProjFund += projFund;
		}
		if(projSource == '科技厅'){
			scienceOfficeProjNum += 1;
			scienceOfficeProjFund += projFund;
		}
		if(projSource == '省卫计委'){
			internationalProjNum += 1;
			internationalProjFund += projFund;
		}
		if(projSource == '科技局'){
			scienceBureauProjNum += 1;
			scienceBureauProjFund += projFund;
		}
		if(projSource == '卫生局' || projSource == '市卫计委'){
			hygieneBureauProjNum += 1;
			hygieneBureauProjFund += projFund;
		}
	});
	
	amountNum = countryProjNum + hygieneMinistryProjNum + educationProjNum + scienceOfficeProjNum + internationalProjNum + scienceBureauProjNum + hygieneBureauProjNum;
	
	$("input[name='countryProjNum']").val(countryProjNum);
	$("input[name='hygieneMinistryProjNum']").val(hygieneMinistryProjNum);
	$("input[name='educationProjNum']").val(educationProjNum);
	$("input[name='scienceOfficeProjNum']").val(scienceOfficeProjNum);
	$("input[name='internationalProjNum']").val(internationalProjNum);
	$("input[name='scienceBureauProjNum']").val(scienceBureauProjNum);
	$("input[name='hygieneBureauProjNum']").val(hygieneBureauProjNum);
	$("input[name='amountNum']").val(amountNum);
	
	amountFunds =  defaultIfEmpty(countryProjFund) + defaultIfEmpty(hygieneMinistryProjFund) + defaultIfEmpty(educationProjFund) + defaultIfEmpty(scienceOfficeProjFund) + defaultIfEmpty(internationalProjFund) + defaultIfEmpty(scienceBureauProjFund)  + defaultIfEmpty(hygieneBureauProjFund) ;
	
	// alert(amountFund +"  =  "+ countryProjFund  +" + "+  hygieneMinistryProjFund  +" + "+  educationProjFund  +" + "+  scienceOfficeProjFund  +" + "+  internationalProjFund  +" + "+  scienceBureauProjFund   +" + "+  hygieneBureauProjFund);
	
	$("input[name='countryProjFund']").val(countryProjFund);
	$("input[name='hygieneMinistryProjFund']").val(hygieneMinistryProjFund);
	$("input[name='educationProjFund']").val(educationProjFund);
	$("input[name='scienceOfficeProjFund']").val(scienceOfficeProjFund);
	$("input[name='internationalProjFund']").val(internationalProjFund);
	$("input[name='scienceBureauProjFund']").val(scienceBureauProjFund);
	$("input[name='hygieneBureauProjFund']").val(hygieneBureauProjFund);
	$("input[name='amountFunds']").val(parseFloat(amountFunds.toFixed(3)));
	
}
</script>
</c:if>


<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
	<input type="hidden" name="pageName" value="step6" /> 
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}" />
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}" /> 
	<input type="hidden" name="recTypeId" value="${param.recTypeId}" />
	
	<table class="bs_tb" style="width: 100%; margin-top: 10px;">
		<tr>
			<th colspan="8" class="theader">
				表 7：近五年为第一负责人在研的市级及以上科技项目共 <input type="text" name="researchNum" id="researchNum" value="${resultMap.researchNum}" class="validate[custom[integer]] inputText" style="width: 30px;" /> 项。（限填15项代表作） 
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('research')"></img>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('research');"></img>
				</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<td style="font-weight:bold;" width="4%"></td>
			<td style="font-weight:bold;" width="4%">序号</td>
			<td style="font-weight:bold;" width="15%">课题编号</td>
			<td style="font-weight:bold;" width="20%">项目名称</td>
			<td style="font-weight:bold;" width="20%">项目来源</td>
			<td style="font-weight:bold;" width="20%">项目起迄时间</td>
			<td style="font-weight:bold;" width="10%">项目经费(万元)</td>
			<td style="font-weight:bold;" width="7%">第一负责人</td>
		</tr>
		<tbody id="researchTb">
		<c:forEach var="research" items="${resultMap.research}" varStatus="status">
			<tr>
				<td style="text-align: center;"><input name="researchIds" type="checkbox"/></td>
				<td style="text-align: center;" class="researchSerial">${status.count}</td>
				<td><input type="text" class="inputText" name="research_lessonNo" value="${research.objMap.research_lessonNo}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="research_projName" value="${research.objMap.research_projName}" style="width: 90%;" /></td>
				<td>
					<select name="research_projSource" class="validate[required] inputText" style="width: 90%;" onchange="calculate()">
						<option value="">请选择</option>
						<option value="国家级科研项目（科技部）"
							<c:if test="${research.objMap.research_projSource eq '国家级科研项目（科技部）'}">selected="selected"</c:if>>国家级科研项目（科技部）</option>
						<option value="国家自然科学基金"
							<c:if test="${research.objMap.research_projSource eq '国家自然科学基金'}">selected="selected"</c:if>>国家自然科学基金</option>
						<option value="国家卫计委"
							<c:if test="${research.objMap.research_projSource eq '国家卫计委'}">selected="selected"</c:if>>国家卫计委</option>
						<option value="科技厅"
							<c:if test="${research.objMap.research_projSource eq '科技厅'}">selected="selected"</c:if>>科技厅</option>
						<option value="省卫计委"
							<c:if test="${research.objMap.research_projSource eq '省卫计委'}">selected="selected"</c:if>>省卫计委</option>
						<option value="科技局"
							<c:if test="${research.objMap.research_projSource eq '科技局'}">selected="selected"</c:if>>科技局</option>
						<option value="市卫计委"
							<c:if test="${research.objMap.research_projSource eq '卫生局' or research.objMap.research_projSource eq '市卫计委'}">selected="selected"</c:if>>市卫计委</option>
					</select>
				</td>
				<td>
					<input type="text" class="inputText" name="research_projStartTime" value="${research.objMap.research_projStartTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 40%; margin-right: 0px;" readonly="readonly" />
					~ <input type="text" class="inputText" name="research_projEndTime" value="${research.objMap.research_projEndTime}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 40%;" readonly="readonly" />
				</td>
				<td><input type="text" class="inputText validate[custom[number]]" name="research_projFund" value="${research.objMap.research_projFund}" onchange="calculate()" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="research_firstResponsiblePerson" value="${research.objMap.research_firstResponsiblePerson}" style="width: 90%;" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br />


	<table id="amount" class="bs_tb" style="width: 100%;">
		<tr>
			<td style="font-weight:bold;">按项目、课题来源统计</td>
			<td style="font-weight:bold;">国家级科研项目<br/>（科技部）</td>
			<td style="font-weight:bold;">国家自然科学基金</td>
			<td style="font-weight:bold;">国家卫计委</td>
			<td style="font-weight:bold;">科技厅</td>
			<td style="font-weight:bold;">省卫计委</td>
			<td style="font-weight:bold;">科技局</td>
			<td style="font-weight:bold;">市卫计委</td>
			<td style="font-weight:bold;">合计</td>
		</tr>
		<tr>
			<td style="font-weight:bold;">项目(个)</td>
			<td><input readonly="readonly" type="text" name="countryProjNum"
				value="${resultMap.countryProjNum}"
				class="inputText validate[custom[integer]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text"
				name="hygieneMinistryProjNum"
				value="${resultMap.hygieneMinistryProjNum}"
				class="inputText validate[custom[integer]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text"
				name="educationProjNum" value="${resultMap.educationProjNum}"
				class="inputText validate[custom[integer]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text"
				name="scienceOfficeProjNum"
				value="${resultMap.scienceOfficeProjNum}"
				class="inputText validate[custom[integer]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text"
				name="internationalProjNum"
				value="${resultMap.internationalProjNum}"
				class="inputText validate[custom[integer]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text"
				name="scienceBureauProjNum"
				value="${resultMap.scienceBureauProjNum}"
				class="inputText validate[custom[integer]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text"
				name="hygieneBureauProjNum"
				value="${resultMap.hygieneBureauProjNum}"
				class="inputText validate[custom[integer]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text" name="amountNum"
				value="${resultMap.amountNum}"
				class="inputText validate[custom[integer]]" style="width: 90%" /></td>
		</tr>
		<tr>
			<td style="font-weight:bold;">经费(万元)</td>
			<td><input readonly="readonly" type="text"
				name="countryProjFund" value="${resultMap.countryProjFund}"
				class="inputText validate[custom[number]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text"
				name="hygieneMinistryProjFund"
				value="${resultMap.hygieneMinistryProjFund}"
				class="inputText validate[custom[number]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text"
				name="educationProjFund" value="${resultMap.educationProjFund}"
				class="inputText validate[custom[number]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text"
				name="scienceOfficeProjFund"
				value="${resultMap.scienceOfficeProjFund}"
				class="inputText validate[custom[number]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text"
				name="internationalProjFund"
				value="${resultMap.internationalProjFund}"
				class="inputText validate[custom[number]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text"
				name="scienceBureauProjFund"
				value="${resultMap.scienceBureauProjFund}"
				class="inputText validate[custom[number]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text"
				name="hygieneBureauProjFund"
				value="${resultMap.hygieneBureauProjFund}"
				class="inputText validate[custom[number]]" style="width: 90%" /></td>
			<td><input readonly="readonly" type="text" name="amountFunds"
				value="${resultMap.amountFunds}"
				class="inputText validate[custom[number]]" style="width: 90%" /></td>
		</tr>
	</table>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('step7')" class="search" value="下一步"/>
	</div>
</c:if>	

<div style="display: none;">
	<!-- 模板 -->
	<table id="researchTemplate" width="100%" class="bs_tb">
		<tr>
			<td style="text-align: center;"><input name="researchIds" type="checkbox"/></td>
			<td style="text-align: center;" class="researchSerial"></td>
			<td><input type="text" class="inputText" name="research_lessonNo" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="research_projName" style="width: 90%;" /></td>
			<td>
				<select name="research_projSource" class="validate[required] inputText" style="width: 90%;" onchange="calculate()">
					<option value="">请选择</option>
					<option value="国家级科研项目（科技部）">国家级科研项目（科技部）</option>
					<option value="国家自然科学基金">国家自然科学基金</option>
					<option value="国家卫计委">国家卫计委</option>
					<option value="科技厅">科技厅</option>
					<option value="省卫计委">省卫计委</option>
					<option value="科技局">科技局</option>
					<option value="市卫计委">市卫计委</option>
				</select>
			</td>
			<td>
				<input type="text" class="inputText" name="research_projStartTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 40%; margin-right: 0px;" readonly="readonly" />
				~ <input type="text" class="inputText" name="research_projEndTime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width: 40%;" readonly="readonly" />
			</td>
			<td><input type="text" class="inputText validate[custom[number]]" name="research_projFund" onchange="calculate()" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="research_firstResponsiblePerson" style="width: 90%;" /></td>
		</tr>
	</table>
</div>
