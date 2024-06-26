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

//$(document).ready(function(){
//	if($("#userlistTb tr").length<=0){
//		add('userlist');
//	}
//});

function add(tb){
	var length = $("#"+tb+"Tb").children().length;
	if(length > 18){
		jboxTip("限填19项！");
		return false; 
	}
	//计算行数
	$("#"+tb+"Num").val(length+1);
	
 	$("#"+tb+"Tb").append($("#"+tb+"Template tr:eq(0)").clone());
 	var length = $("#"+tb+"Tb").children().length;
 	//序号
	$("#"+tb+"Tb").children("tr").last().children("td").eq(1).children("input").val(length);
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
			$(this).children("input").val(serial);
		});
		//计算行数
		var length = $("#"+tb+"Tb").children().length;
		$("#"+tb+"Num").val(length);
		theLevelAgeTotal();
	});
}

function defaultIfEmpty(obj){
	if(isNaN(obj)){
		return 0;
	}else{
		return obj;
	}
}

function setTotal(op){
	var amountFund = 0.0;
	var opone = parseFloat( $("input[name='"+op+"one']").val()) ;
	var optwo= parseFloat(  $("input[name='"+op+"two']").val()) ;
	var opthree =  parseFloat( $("input[name='"+op+"three']").val()) ;
	var opfour =  parseFloat( $("input[name='"+op+"four']").val()) ;
	var opfive =  parseFloat( $("input[name='"+op+"five']").val()) ;
	amountFund =  defaultIfEmpty(opone) + defaultIfEmpty(optwo) + defaultIfEmpty(opthree) + defaultIfEmpty(opfour) + defaultIfEmpty(opfive);
	$("input[name='"+op+"total']").val(parseFloat(amountFund.toFixed(3)));
}

function theLevelAgeTotal(){
	$("#titAgeTotal input").each(function(){
		$(this).val("0");
	});
	$("#userlistTb select[name=userlist_level]").each(function (){
		var titStr = this.value;
		var ageStr = $(this).parent().prev().prev().children().val();
		if(isNaN(ageStr)){
			ageStr = 0;
		}else{
			ageStr = ageStr*1;
		}
		//alert(titStr+"="+ageStr);

		var tit = "主任医师" == titStr ? "m" : "副主任医师" == titStr ? "f" : "主治医师" == titStr ? "z" : "";
		var titAge = "";
		if(ageStr < 35){
			titAge = "one";
		}else if(ageStr >= 35 && ageStr <= 45){
			titAge = "two";
		}else if(ageStr >= 46 && ageStr <= 55){
			titAge = "three";
		}else if(ageStr >= 56 && ageStr <= 60){
			titAge = "four";
		}else if(ageStr >= 61){
			titAge = "five";
		}
		if(tit != "" && titAge != ""){
			var num = parseFloat($("input[name="+tit + titAge+"]").val());
			if(isNaN(num)){
				num = 0;
			}
			$("input[name="+tit + titAge+"]").val(num+1);
		}
	});
	setTotal('m');
	setTotal('f');
	setTotal('z');
}
</script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" name="projForm" style="position: relative;">
	<input type="hidden" name="pageName" value="step5" />
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}" />
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}" />
	<input type="hidden" name="recTypeId" value="${param.recTypeId}" />

	<%--<font style="font-size: 14px; font-weight:bold;color: #333; "></font>--%>
	<table class="bs_tb" style="width: 100%;margin-top: 10px;">
		<tr>
			<th colspan="9" class="theader">五、临床医学中心（创新平台）人员信息（限19人以内）
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
									<span style="float: right;padding-right: 10px">
									<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('userlist')"/>&#12288;
									<img title="删除"  style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="delTr('userlist');"/></span>
				</c:if>
			</th>
		</tr>
		<tr>
			<td width="50px"></td>
			<td width="50px">序号</td>
			<td>姓名</td>
			<td>年龄</td>
			<td>学历</td>
			<td>技术职称</td>
			<td>所在单位</td>
			<td>科室</td>
			<td>专业</td>
		</tr>
		<tbody id="userlistTb">
		<c:if test="${not empty resultMap.userlist}">
			<c:forEach var="userlist" items="${resultMap.userlist}" varStatus="status">
				<tr>
					<td width="50px" style="text-align: center;"><input name="userlistIds" type="checkbox"/></td>
					<td width="50px" style="text-align: center;" class="userlistSerial"><input name="userlistSerialNum" type="text" value="${userlist.objMap.userlistSerialNum}"  style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
					<td><input type="text" name="userlist_name" value="${userlist.objMap.userlist_name}" class="inputText validate[required]" style="width: 90%"/></td>
					<td><input type="text" name="userlist_age" value="${userlist.objMap.userlist_age}" onchange="theLevelAgeTotal();" class="inputText validate[required,custom[number],min[1],max[150]]" style="width: 90%"/></td>
					<td>
						<select name="userlist_edu" class="inputText validate[required]" style="width: 90%;">
							<option value="">请选择</option>
							<c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
								<option value="${dict.dictName}" <c:if test="${userlist.objMap.userlist_edu eq dict.dictName}">selected="selected"</c:if>>${dict.dictName}</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select name="userlist_level" class="inputText validate[required]" style="width: 90%;" onchange="theLevelAgeTotal();">
							<option value="">请选择</option>
							<c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
								<option value="${dict.dictName }" <c:if test="${userlist.objMap.userlist_level eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
							</c:forEach>
						</select>
					</td>

					<td><input type="text" name="userlist_org" value="${userlist.objMap.userlist_org}" class="inputText validate[required]" style="width: 90%"/></td>
					<td><input type="text" name="userlist_room" value="${userlist.objMap.userlist_room}" class="inputText validate[required]" style="width: 90%"/></td>
					<td><input type="text" name="userlist_title" value="${userlist.objMap.userlist_title}" class="inputText validate[required]" style="width: 90%"/></td>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>
	<table id="titAgeTotal" class="basic" style="width: 100%; margin-top: 10px;">
		<tr>
			<th colspan="7" class="theader" style="text-align:left;padding-left:10px;">年龄结构</th>
		</tr>
		<tr>
			<th style="text-align: center;padding-left: 20px;">技术职称</th>
			<th style="text-align: center;">合计</th>
			<th style="text-align: center;">35岁以下</th>
			<th style="text-align: center;">35-45岁</th>
			<th style="text-align: center;">46-55岁</th>
			<th style="text-align: center;">56-60岁</th>
			<th style="text-align: center;">61岁以上</th>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">主任医师</td>
			<td><input type="text" name="mtotal" value="${resultMap.mtotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="mone" value="${resultMap.mone}" onchange="setTotal('m')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="mtwo" value="${resultMap.mtwo}" onchange="setTotal('m')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="mthree" value="${resultMap.mthree}" onchange="setTotal('m')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="mfour" value="${resultMap.mfour}" onchange="setTotal('m')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="mfive" value="${resultMap.mfive}" onchange="setTotal('m')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">副主任医师</td>
			<td><input type="text" name="ftotal" value="${resultMap.ftotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fone" value="${resultMap.fone}" onchange="setTotal('f')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="ftwo" value="${resultMap.ftwo}" onchange="setTotal('f')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fthree" value="${resultMap.fthree}" onchange="setTotal('f')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="ffour" value="${resultMap.ffour}" onchange="setTotal('f')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="ffive" value="${resultMap.ffive}" onchange="setTotal('f')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">主治医师</td>
			<td><input type="text" name="ztotal" value="${resultMap.ztotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zone" value="${resultMap.zone}" onchange="setTotal('z')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="ztwo" value="${resultMap.ztwo}" onchange="setTotal('z')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zthree" value="${resultMap.zthree}" onchange="setTotal('z')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zfour" value="${resultMap.zfour}" onchange="setTotal('z')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zfive" value="${resultMap.zfive}" onchange="setTotal('z')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
	</table>

</form>

<c:if test="${param.view!=GlobalConstant.FLAG_Y}">
	<div align="center" style="margin-top: 10px">
		<input id="prev" type="button" onclick="nextOpt('step4')" class="search" value="上一步"/>
		<input id="nxt" type="button" onclick="nextOpt('step6')" class="search" value="下一步"/>
	</div>
</c:if>	

<div style="display: none;">
	<!-- 1.人员信息模板 -->
	<table class="basic" id="userlistTemplate" style="width: 100%">
		<tr>
			<td width="50px" style="text-align: center;"><input name="userlistIds" type="checkbox"/></td>
			<td width="50px" style="text-align: center;" class="userlistSerial"><input name="userlistSerialNum" type="text" value="${userlist.objMap.userlistSerialNum}"  style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
			<td><input type="text" name="userlist_name" class="inputText validate[required]" style="width: 90%"/></td>
			<td><input type="text" name="userlist_age" class="inputText validate[required,custom[number],min[1],max[150]]" onchange="theLevelAgeTotal();" style="width: 90%"/></td>
			<td>
				<select name="userlist_edu" class="inputText validate[required]" style="width: 90%;">
					<option value="">请选择</option>
					<c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
						<option value="${dict.dictName}">${dict.dictName}</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<select name="userlist_level" class="inputText validate[required]" style="width: 90%;" onchange="theLevelAgeTotal();">
					<option value="">请选择</option>
					<c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
						<option value="${dict.dictName }">${dict.dictName }</option>
					</c:forEach>
				</select>
			</td>
			<td><input type="text" name="userlist_org" class="inputText validate[required]" style="width: 90%"/></td>
			<td><input type="text" name="userlist_room" class="inputText validate[required]" style="width: 90%"/></td>
			<td><input type="text" name="userlist_title" class="inputText validate[required]" style="width: 90%"/></td>
		</tr>
	</table>
</div>
