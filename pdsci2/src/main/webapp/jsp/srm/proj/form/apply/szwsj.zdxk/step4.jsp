
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
	if($("#personTb tr").length<=0){
		add('person');
	}
});

function add(tb){
	var length = $("#"+tb+"Tb").children().length;
	if(length > 19){
		jboxTip("限20人以内！");
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


function calculate(){
	var directorPhysicianAmount = 0;
	var directorPhysicianLess35 = 0;
	var directorPhysicianBetween36And45 = 0;
	var directorPhysicianBetween46And55 = 0;
	var directorPhysicianBetween56And60 = 0;
	var directorPhysicianGreater61 = 0;
	
	var assistantPhysicianAmount = 0;
	var assistantPhysicianLess35 = 0;
	var assistantPhysicianBetween36And45 = 0;
	var assistantPhysicianBetween46And55 = 0;
	var assistantPhysicianBetween56And60 = 0;
	var assistantPhysicianGreater61 = 0;
	
	var staffPhysicianAmount = 0;
	var staffPhysicianLess35 = 0;
	var staffPhysicianBetween36And45 = 0;
	var staffPhysicianBetween46And55 = 0;
	var staffPhysicianBetween56And60 = 0;
	var staffPhysicianGreater61 = 0;
	
	var zyysAmount = 0;
	var zyysLess35 = 0;
	var zyysBetween36And45 = 0;
	var zyysBetween46And55 = 0;
	var zyysBetween56And60 = 0;
	var zyysGreater61 = 0;
	//遍历年龄
	$("input[name='person_age']").each(function (index, domEle) {
		var age = domEle.value;
		var technologyTitle = $(this).parent().next().next().children().val();
		//alert(domEle.value+","+technologyTitle);
		if(technologyTitle =='主任医师'){
			directorPhysicianAmount += 1;
			if(age > 0 && age <= 35){
				directorPhysicianLess35 += 1;
			}else if(age >= 36 && age <= 45){
				directorPhysicianBetween36And45 += 1;
			}else if(age >= 46 && age <= 55){
				directorPhysicianBetween46And55 += 1;
			}else if(age >= 56 && age <= 60){
				directorPhysicianBetween56And60 += 1;
			}else if(age >= 61){
				directorPhysicianGreater61 += 1;
			}
		}
		if(technologyTitle =='副主任医师'){
			assistantPhysicianAmount += 1;
			if(age > 0 && age <= 35){
				assistantPhysicianLess35 += 1;
			}else if(age >= 36 && age <= 45){
				assistantPhysicianBetween36And45 += 1;
			}else if(age >= 46 && age <= 55){
				assistantPhysicianBetween46And55 += 1;
			}else if(age >= 56 && age <= 60){
				assistantPhysicianBetween56And60 += 1;
			}else if(age >= 61){
				assistantPhysicianGreater61 += 1;
			}
		}
		if(technologyTitle =='主治医师'){
			staffPhysicianAmount += 1;
			if(age > 0 && age <= 35){
				staffPhysicianLess35 += 1;
			}else if(age >= 36 && age <= 45){
				staffPhysicianBetween36And45 += 1;
			}else if(age >= 46 && age <= 55){
				staffPhysicianBetween46And55 += 1;
			}else if(age >= 56 && age <= 60){
				staffPhysicianBetween56And60 += 1;
			}else if(age >= 61){
				staffPhysicianGreater61 += 1;
			}
		}
		if(technologyTitle =='住院医师'){
			zyysAmount += 1;
			if(age > 0 && age <= 35){
				zyysLess35 += 1;
			}else if(age >= 36 && age <= 45){
				zyysBetween36And45 += 1;
			}else if(age >= 46 && age <= 55){
				zyysBetween46And55 += 1;
			}else if(age >= 56 && age <= 60){
				zyysBetween56And60 += 1;
			}else if(age >= 61){
				zyysGreater61 += 1;
			}
		}
	}); 
	
	$("input[name='directorPhysicianAmount']").val(directorPhysicianAmount);
	$("input[name='directorPhysicianLess35']").val(directorPhysicianLess35);
	$("input[name='directorPhysicianBetween36And45']").val(directorPhysicianBetween36And45);
	$("input[name='directorPhysicianBetween46And55']").val(directorPhysicianBetween46And55);
	$("input[name='directorPhysicianBetween56And60']").val(directorPhysicianBetween56And60);
	$("input[name='directorPhysicianGreater61']").val(directorPhysicianGreater61);
	
	$("input[name='assistantPhysicianAmount']").val(assistantPhysicianAmount);
	$("input[name='assistantPhysicianLess35']").val(assistantPhysicianLess35);
	$("input[name='assistantPhysicianBetween36And45']").val(assistantPhysicianBetween36And45);
	$("input[name='assistantPhysicianBetween46And55']").val(assistantPhysicianBetween46And55);
	$("input[name='assistantPhysicianBetween56And60']").val(assistantPhysicianBetween56And60);
	$("input[name='assistantPhysicianGreater61']").val(assistantPhysicianGreater61);
	
	$("input[name='staffPhysicianAmount']").val(staffPhysicianAmount);
	$("input[name='staffPhysicianLess35']").val(staffPhysicianLess35);
	$("input[name='staffPhysicianBetween36And45']").val(staffPhysicianBetween36And45);
	$("input[name='staffPhysicianBetween46And55']").val(staffPhysicianBetween46And55);
	$("input[name='staffPhysicianBetween56And60']").val(staffPhysicianBetween56And60);
	$("input[name='staffPhysicianGreater61']").val(staffPhysicianGreater61);
	
	$("input[name='zyysAmount']").val(zyysAmount);
	$("input[name='zyysLess35']").val(zyysLess35);
	$("input[name='zyysBetween36And45']").val(zyysBetween36And45);
	$("input[name='zyysBetween46And55']").val(zyysBetween46And55);
	$("input[name='zyysBetween56And60']").val(zyysBetween56And60);
	$("input[name='zyysGreater61']").val(zyysGreater61);
}

</script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
	<input type="hidden" name="pageName" value="step4" /> 
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}" />
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}" /> 
	<input type="hidden" name="recTypeId" value="${param.recTypeId}" />
	
	<table class="bs_tb" style="width: 100%; margin-top: 10px;">
		<tr>
			<th colspan="9" class="theader">
				表5：重点学科人员信息（限20人以内）
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
				<span style="float: right;padding-right: 10px">
					<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('person')"/>&#12288;
					<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('person');"/>
				</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<td style="font-weight: bold;" width="4%"></td>
			<td style="font-weight: bold;" width="4%">序号</td>
			<td style="font-weight: bold;" width="10%">姓名</td>
			<td style="font-weight: bold;" width="8%">年龄</td>
			<td style="font-weight: bold;" width="10%">学历</td>
			<td style="font-weight: bold;" width="15%">技术职称</td>
			<td style="font-weight: bold;" width="20%">所在单位</td>
			<td style="font-weight: bold;" width="15%">科室</td>
			<td style="font-weight: bold;" width="15%">专业</td>
		</tr>
		<tbody id="personTb">
		<c:forEach var="person" items="${resultMap.person}" varStatus="status">
			<tr>
				<td><input name="personIds" type="checkbox" /></td>
				<td style="text-align: center;" class="personSerial">${status.count}</td>
				<td><input type="text" class="validate[required] inputText" name="person_name" value="${person.objMap.person_name}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText validate[custom[integer]]" name="person_age" onchange="calculate()" value="${person.objMap.person_age}" style="width: 90%;" /></td>
				<td>
					<select name="person_education" class="inputText" style="width: 90%;">
						<option value="">请选择</option>
						<c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
							<option value="${dict.dictId}"
								<c:if test="${person.objMap.person_education eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
						</c:forEach>
					</select>
				</td>
				<td>
					<select name="person_technologyTitle" class="validate[required] inputText" style="width: 90%;" onchange="calculate()">
						<option value="">请选择</option>
						<option value="医师"
							<c:if test="${person.objMap.person_technologyTitle eq '医师'}">selected="selected"</c:if>>医师</option>
						<option value="主治医师"
							<c:if test="${person.objMap.person_technologyTitle eq '主治医师'}">selected="selected"</c:if>>主治医师</option>
						<option value="副主任医师"
							<c:if test="${person.objMap.person_technologyTitle eq '副主任医师'}">selected="selected"</c:if>>副主任医师</option>
						<option value="主任医师"
							<c:if test="${person.objMap.person_technologyTitle eq '主任医师'}">selected="selected"</c:if>>主任医师</option>
						<option value="主管护师"
							<c:if test="${person.objMap.person_technologyTitle eq '主管护师'}">selected="selected"</c:if>>主管护师</option>
						<option value="住院医师"
							<c:if test="${person.objMap.person_technologyTitle eq '住院医师'}">selected="selected"</c:if>>住院医师</option>
						<option value="副主任护师"
							<c:if test="${person.objMap.person_technologyTitle eq '副主任护师'}">selected="selected"</c:if>>副主任护师</option>
						<option value="主任护师"
							<c:if test="${person.objMap.person_technologyTitle eq '主任护师'}">selected="selected"</c:if>>主任护师</option>
						<option value="检验主管技师"
							<c:if test="${person.objMap.person_technologyTitle eq '检验主管技师'}">selected="selected"</c:if>>检验主管技师</option>
						<option value="副主任检验师"
							<c:if test="${person.objMap.person_technologyTitle eq '副主任检验师'}">selected="selected"</c:if>>副主任检验师</option>
						<option value="主任检验师"
							<c:if test="${person.objMap.person_technologyTitle eq '主任检验师'}">selected="selected"</c:if>>主任检验师</option>
						<option value="工程师"
							<c:if test="${person.objMap.person_technologyTitle eq '工程师'}">selected="selected"</c:if>>工程师</option>
						<option value="技师"
							<c:if test="${person.objMap.person_technologyTitle eq '技师'}">selected="selected"</c:if>>技师</option>
					</select>
				</td>
				<td><input type="text" class="inputText" name="person_organization" value="${person.objMap.person_organization}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="person_offices" value="${person.objMap.person_offices}" style="width: 90%;" /></td>
				<td><input type="text" class="inputText" name="person_major" value="${person.objMap.person_major}" style="width: 90%;" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>

	<br />


	<table class="bs_tb" style="width: 100%;">
		<tr>
			<td rowspan="5" width="100px" style="font-weight: bold;">年龄结构</td>
			<td width="150px" style="font-weight: bold;">技术职称</td>
			<td style="font-weight: bold;">合 计</td>
			<td style="font-weight: bold;">35岁以下</td>
			<td style="font-weight: bold;">36-45岁</td>
			<td style="font-weight: bold;">46-55岁</td>
			<td style="font-weight: bold;">56-60岁</td>
			<td style="font-weight: bold;">61岁以上</td>
		</tr>
		<tr>
			<td style="font-weight: bold;">主任医师</td>
			<td><input readonly="readonly" type="text"
				name="directorPhysicianAmount"
				value="${resultMap.directorPhysicianAmount}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="directorPhysicianLess35"
				value="${resultMap.directorPhysicianLess35}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="directorPhysicianBetween36And45"
				value="${resultMap.directorPhysicianBetween36And45}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="directorPhysicianBetween46And55"
				value="${resultMap.directorPhysicianBetween46And55}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="directorPhysicianBetween56And60"
				value="${resultMap.directorPhysicianBetween56And60}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="directorPhysicianGreater61"
				value="${resultMap.directorPhysicianGreater61}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
		</tr>
		<tr>
			<td style="font-weight: bold;">副主任医师</td>
			<td><input readonly="readonly" type="text"
				name="assistantPhysicianAmount"
				value="${resultMap.assistantPhysicianAmount}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="assistantPhysicianLess35"
				value="${resultMap.assistantPhysicianLess35}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="assistantPhysicianBetween36And45"
				value="${resultMap.assistantPhysicianBetween36And45}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="assistantPhysicianBetween46And55"
				value="${resultMap.assistantPhysicianBetween46And55}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="assistantPhysicianBetween56And60"
				value="${resultMap.assistantPhysicianBetween56And60}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="assistantPhysicianGreater61"
				value="${resultMap.assistantPhysicianGreater61}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
		</tr>
		<tr>
			<td style="font-weight: bold;">主治医师</td>
			<td><input readonly="readonly" type="text"
				name="staffPhysicianAmount"
				value="${resultMap.staffPhysicianAmount}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="staffPhysicianLess35"
				value="${resultMap.staffPhysicianLess35}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="staffPhysicianBetween36And45"
				value="${resultMap.staffPhysicianBetween36And45}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="staffPhysicianBetween46And55"
				value="${resultMap.staffPhysicianBetween46And55}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="staffPhysicianBetween56And60"
				value="${resultMap.staffPhysicianBetween56And60}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="staffPhysicianGreater61"
				value="${resultMap.staffPhysicianGreater61}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
		</tr>
		<tr>
			<td style="font-weight: bold;">住院医师</td>
			<td><input readonly="readonly" type="text"
				name="zyysAmount"
				value="${resultMap.zyysAmount}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="zyysLess35"
				value="${resultMap.zyysLess35}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="zyysBetween36And45"
				value="${resultMap.zyysBetween36And45}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="zyysBetween46And55"
				value="${resultMap.zyysBetween46And55}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="zyysBetween56And60"
				value="${resultMap.zyysBetween56And60}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
			<td><input readonly="readonly" type="text"
				name="zyysGreater61"
				value="${resultMap.zyysGreater61}"
				class="inputText validate[custom[integer]]" style="width: 90%;" /></td>
		</tr>
	</table>
</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('step5')" class="search" value="下一步"/>
	</div>
</c:if>	


<div style="display: none;">
	<!-- 模板 -->
	<table id="personTemplate" width="100%" class="bs_tb">
		<tr>
			<td><input name="personIds" type="checkbox" /></td>
			<td style="text-align: center;" class="personSerial"></td>
			<td><input type="text" class="validate[required] inputText" name="person_name" style="width: 90%;" /></td>
			<td><input type="text" class="inputText validate[custom[integer]]" name="person_age" onchange="calculate()" style="width: 90%;" /></td>
			<td>
				<select name="person_education" class="inputText" style="width: 90%;">
					<option value="">请选择</option>
					<c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
						<option value="${dict.dictId}">${dict.dictName}</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="person_technologyTitle" class="validate[required] inputText" style="width: 90%;" onchange="calculate()">
					<option value="">请选择</option>
					<option value="医师">医师</option>
					<option value="主治医师">主治医师</option>
					<option value="副主任医师">副主任医师</option>
					<option value="主任医师">主任医师</option>
					<option value="主管护师">主管护师</option>
					<option value="住院医师">住院医师</option>
					<option value="副主任护师">副主任护师</option>
					<option value="主任护师">主任护师</option>
					<option value="检验主管技师">检验主管技师</option>
					<option value="副主任检验师">副主任检验师</option>
					<option value="主任检验师">主任检验师</option>
					<option value="工程师">工程师</option>
					<option value="技师">技师</option>
				</select>
			</td>
			<td><input type="text" class="inputText" name="person_organization" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="person_offices" style="width: 90%;" /></td>
			<td><input type="text" class="inputText" name="person_major" style="width: 90%;" /></td>
		</tr>
	</table>
</div>


