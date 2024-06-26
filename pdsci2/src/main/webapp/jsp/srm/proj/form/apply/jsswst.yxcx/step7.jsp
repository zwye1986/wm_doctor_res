<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style>
	.basic td {
		text-align: center;
		padding-left: 0px;
	}
</style>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
	<script type="text/javascript">
		function nextOpt(step) {
			if (false == $("#projForm").validationEngine("validate")) {
				return false;
			}
			var form = $('#projForm');
			form.append('<input type="hidden" name="nextPageName" value="' + step + '"/>');
			$('#nxt').attr({"disabled": "disabled"});
			$('#prev').attr({"disabled": "disabled"});
			jboxStartLoading();
			form.submit();
		}

		function add(templateId) {
			if (templateId) {
				if ($('.' + templateId + ' .toDel').length < 9) {
					$('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
					reSeq(templateId);
				} else {
					jboxTip('团队人员信息限制在9人以内！');
				}

			}
		}

		function del(templateId) {
			if (templateId) {
				if (!$('.' + templateId + ' .toDel:checked').length) {
					return jboxTip('请选择需要删除的项目！');
				}
				jboxConfirm('确认删除？', function () {
					$('.' + templateId + ' .toDel:checked').closest('tr').remove();
					reSeq(templateId);
					theLevelAgeTotal();
				}, null);
			}
		}

		function reSeq(templateId) {
			$('.' + templateId + ' .seq').children("input").each(function (i, n) {
				$(n).val(i + 1);
			});
		}

		$(function () {
			$('#template tr').each(function(){
				var id = this.id;
				if(id){
					if(!$('.'+id+' .toDel').length){
						add(id);
					}
				}
			});
		});

		function getIdByName(name){
			/* 住院医师、主治医师、主任医师、副主任医师、主任医师技师、主管技师、副主任技师、主任技师药师、主管药师、副主任药师、
			 主任药师、研究实习员、助理研究员、副研究员、研究员护师、主管护师、副主任护师、主任护师、助教、讲师、教授、副教授、其他*/
			if(name == "住院医师"){
				return "r";
			}else if(name == "主治医师"){
				return "z";
			}else if(name == "副主任医师"){
				return "f";
			}else if(name == "主任医师"){
				return "zy";
			}else if(name == "主管技师"){
				return "zgjs";
			}else if(name == "副主任技师"){
				return "fzrjs";
			}else if(name == "主任技师"){
				return "zrjsys";
			}else if(name == "主管药师"){
				return "zgys";
			}else if(name == "主任药师"){
				return "zrys";
			}else if(name == "副主任药师"){
				return "fzrys";
			}else if(name == "研究实习员"){
				return "yjsxy";
			}else if(name == "助理研究员"){
				return "zlyjy";
			}else if(name == "副研究员"){
				return "fyjy";
			}else if(name == "研究员"){
				return "yjyhs";
			}else if(name == "主管护师"){
				return "zghs";
			}else if(name == "副主任护师"){
				return "fzrhs";
			}else if(name == "主任护师"){
				return "zrhs";
			}else if(name == "助教"){
				return "zj";
			}else if(name == "讲师"){
				return "js";
			}else if(name == "教授"){
				return "jso";
			}else if(name == "副教授"){
				return "fjs";
			}else if(name == "技师"){
				return "jis";
			}else if(name == "药师"){
				return "ys";
			}else if(name == "护师"){
				return "hs";
			}else if(name == "其他"){
				return "other";
			}
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
			$(".memberList select[name=memberList_title]").each(function(index,element){
				var titStr = $(element).val();
				var ageStr = $(element).parent().prev().prev().children().val();
				if(isNaN(ageStr)){
					ageStr = 0;
				}else{
					ageStr = ageStr*1;
				}
				// alert(titStr+"="+ageStr);

				var tit = getIdByName(titStr);
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
					setTotal(tit);
				}
			});
		}
	</script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
	  id="projForm" style="position: relative;">
	<input type="hidden" id="pageName" name="pageName" value="step7"/>
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

	<table class="basic" style="width: 100%">
		<tr>
			<th style="text-align: left;padding-left: 15px;" colspan="9">
				六、创新团队人员信息（限9人以内）
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
								 style="cursor: pointer;" onclick="add('memberList');"/>
							<img title="删除" style="cursor: pointer;"
								 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
								 onclick="del('memberList');"/>
						</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
				<td style="text-align: center;" width="5%">选择</td>
			</c:if>
			<td style="text-align: center;" width="5%">序号</td>
			<td style="text-align: center;" width="10%"><font color="red">*</font>姓名</td>
			<td style="text-align: center;" width="5%"><font color="red">*</font>年龄</td>
			<td style="text-align: center;" width="10%"><font color="red">*</font>学历</td>
			<td style="text-align: center;" width="10%"><font color="red">*</font>技术职称</td>
			<td style="text-align: center;" width="10%"><font color="red">*</font>专业</td>
			<td style="text-align: center;" width="20%"><font color="red">*</font>所在单位及科室</td>
			<td style="text-align: center;" width="20%"><font color="red">*</font>承担主要任务</td>
		</tr>
		<tbody class="memberList">
		<c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
			<c:forEach var="memberList" items="${resultMap.memberList}" varStatus="memberListStatus">
				<tr>
					<td><input type="checkbox" class="toDel"></td>
					<td class="seq"><input name="memberList_serialNum" type="text" value="${memberList.objMap.memberList_serialNum}"  style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
					<td>
						<input type="text" class="inputText validate[required]" name="memberList_name"
							   value="${memberList.objMap.memberList_name}" style="width: 80%"/>
					</td>
					<td>
						<input type="text" class="inputText validate[required,custom[integer]]" name="memberList_age"
							   value="${memberList.objMap.memberList_age}" style="width: 80%" onchange="theLevelAgeTotal();"/>
					</td>
					<td>
						<select name="memberList_education" class="inputText validate[required]" style="width: 80%;" >
							<option value="">请选择</option>
							<c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
								<option value="${dict.dictName }"
										<c:if test="${memberList.objMap.memberList_education eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<select name="memberList_title" class="inputText validate[required]" style="width: 80%;" onchange="theLevelAgeTotal();">
							<option value="">请选择</option>
							<option value="住院医师"
									<c:if test="${memberList.objMap.memberList_title eq '住院医师'}">selected="selected"</c:if>>
								住院医师
							</option>
							<option value="主治医师"
									<c:if test="${memberList.objMap.memberList_title eq '主治医师'}">selected="selected"</c:if>>
								主治医师
							</option>
							<%--<option value="主任医师"
									<c:if test="${memberList.objMap.memberList_title eq '主任医师'}">selected="selected"</c:if>>
								主任医师
							</option>--%>
							<option value="副主任医师"
									<c:if test="${memberList.objMap.memberList_title eq '副主任医师'}">selected="selected"</c:if>>
								副主任医师
							</option>
							<option value="主任医师"
									<c:if test="${memberList.objMap.memberList_title eq '主任医师'}">selected="selected"</c:if>>
								主任医师
							</option>
							<option value="主管技师"
									<c:if test="${memberList.objMap.memberList_title eq '主管技师'}">selected="selected"</c:if>>
								主管技师
							</option>
							<option value="副主任技师"
									<c:if test="${memberList.objMap.memberList_title eq '副主任技师'}">selected="selected"</c:if>>
								副主任技师
							</option>
							<option value="主任技师"
									<c:if test="${memberList.objMap.memberList_title eq '主任技师'}">selected="selected"</c:if>>
								主任技师
							</option>
							<option value="主管药师"
									<c:if test="${memberList.objMap.memberList_title eq '主管药师'}">selected="selected"</c:if>>
								主管药师
							</option>
							<option value="主任药师"
									<c:if test="${memberList.objMap.memberList_title eq '主任药师'}">selected="selected"</c:if>>
								主任药师
							</option>
							<option value="副主任药师"
									<c:if test="${memberList.objMap.memberList_title eq '副主任药师'}">selected="selected"</c:if>>
								副主任药师
							</option>
							<option value="研究实习员"
									<c:if test="${memberList.objMap.memberList_title eq '研究实习员'}">selected="selected"</c:if>>
								研究实习员
							</option>
							<option value="助理研究员"
									<c:if test="${memberList.objMap.memberList_title eq '助理研究员'}">selected="selected"</c:if>>
								助理研究员
							</option>
							<option value="副研究员"
									<c:if test="${memberList.objMap.memberList_title eq '副研究员'}">selected="selected"</c:if>>
								副研究员
							</option>
							<option value="研究员"
									<c:if test="${memberList.objMap.memberList_title eq '研究员'}">selected="selected"</c:if>>
								研究员
							</option>
							<option value="主管护师"
									<c:if test="${memberList.objMap.memberList_title eq '主管护师'}">selected="selected"</c:if>>
								主管护师
							</option>
							<option value="副主任护师"
									<c:if test="${memberList.objMap.memberList_title eq '副主任护师'}">selected="selected"</c:if>>
								副主任护师
							</option>
							<option value="主任护师"
									<c:if test="${memberList.objMap.memberList_title eq '主任护师'}">selected="selected"</c:if>>
								主任护师
							</option>
							<option value="助教"
									<c:if test="${memberList.objMap.memberList_title eq '助教'}">selected="selected"</c:if>>
								助教
							</option>
							<option value="讲师"
									<c:if test="${memberList.objMap.memberList_title eq '讲师'}">selected="selected"</c:if>>
								讲师
							</option>
							<option value="教授"
									<c:if test="${memberList.objMap.memberList_title eq '教授'}">selected="selected"</c:if>>
								教授
							</option>
							<option value="副教授"
									<c:if test="${memberList.objMap.memberList_title eq '副教授'}">selected="selected"</c:if>>
								副教授
							</option>
							<option value="技师"
									<c:if test="${memberList.objMap.memberList_title eq '技师'}">selected="selected"</c:if>>
								技师
							</option>
							<option value="药师"
									<c:if test="${memberList.objMap.memberList_title eq '药师'}">selected="selected"</c:if>>
								药师
							</option>
							<option value="护师"
									<c:if test="${memberList.objMap.memberList_title eq '护师'}">selected="selected"</c:if>>
								护师
							</option>
							<option value="其他"
									<c:if test="${memberList.objMap.memberList_title eq '其他'}">selected="selected"</c:if>>
								其他
							</option>
						</select>
							<%--<input type="text" class="inputText validate[required]" name="memberList_title"--%>
							<%--value="${memberList.objMap.memberList_title}" style="width: 80%"/>--%>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="memberList_spe"
							   value="${memberList.objMap.memberList_spe}" style="width: 80%"/>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="memberList_dept"
							   value="${memberList.objMap.memberList_dept}" style="width: 80%"/>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="memberList_bearResponsibility"
							   value="${memberList.objMap.memberList_bearResponsibility}" style="width: 80%"/>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${param.view eq GlobalConstant.FLAG_Y}">
			<c:forEach var="memberList" items="${resultMap.memberList}" varStatus="memberListStatus">
				<tr>
					<td>${memberList.objMap.memberList_serialNum}</td>
					<td>
							${memberList.objMap.memberList_name}
					</td>
					<td>
							${memberList.objMap.memberList_age}
					</td>
					<td>
							${memberList.objMap.memberList_education}
					</td>

					<td>
							${memberList.objMap.memberList_title}
					</td>
					<td>
                            ${memberList.objMap.memberList_spe}
					</td>
					<td>
                            ${memberList.objMap.memberList_dept}
					</td>
					<td>
							${memberList.objMap.memberList_bearResponsibility}
					</td>
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
		<%--<tr>
			<td style="text-align: center;" width="120px;">主任医师</td>
			<td><input type="text" name="mtotal" value="${resultMap.mtotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="mone" value="${resultMap.mone}" onchange="setTotal('m')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="mtwo" value="${resultMap.mtwo}" onchange="setTotal('m')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="mthree" value="${resultMap.mthree}" onchange="setTotal('m')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="mfour" value="${resultMap.mfour}" onchange="setTotal('m')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="mfive" value="${resultMap.mfive}" onchange="setTotal('m')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>--%>
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
		<tr>
			<td style="text-align: center;" width="120px;">住院医师</td>
			<td><input type="text" name="rtotal" value="${resultMap.rtotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="rone" value="${resultMap.rone}" onchange="setTotal('r')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="rtwo" value="${resultMap.rtwo}" onchange="setTotal('r')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="rthree" value="${resultMap.rthree}" onchange="setTotal('r')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="rfour" value="${resultMap.rfour}" onchange="setTotal('r')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="rfive" value="${resultMap.rfive}" onchange="setTotal('r')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">主任医师</td>
			<td><input type="text" name="zytotal" value="${resultMap.zytotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zyone" value="${resultMap.zyone}" onchange="setTotal('zy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zytwo" value="${resultMap.zytwo}" onchange="setTotal('zy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zythree" value="${resultMap.zythree}" onchange="setTotal('zy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zyfour" value="${resultMap.zyfour}" onchange="setTotal('zy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zyfive" value="${resultMap.zyfive}" onchange="setTotal('zy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">主管技师</td>
			<td><input type="text" name="zgjstotal" value="${resultMap.zgjstotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zgjsone" value="${resultMap.zgjsone}" onchange="setTotal('zgjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zgjstwo" value="${resultMap.zgjstwo}" onchange="setTotal('zgjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zgjsthree" value="${resultMap.zgjsthree}" onchange="setTotal('zgjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zgjsfour" value="${resultMap.zgjsfour}" onchange="setTotal('zgjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zgjsfive" value="${resultMap.zgjsfive}" onchange="setTotal('zgjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">副主任技师</td>
			<td><input type="text" name="fzrjstotal" value="${resultMap.fzrjstotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrjsone" value="${resultMap.fzrjsone}" onchange="setTotal('fzrjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrjstwo" value="${resultMap.fzrjstwo}" onchange="setTotal('fzrjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrjsthree" value="${resultMap.fzrjsthree}" onchange="setTotal('fzrjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrjsfour" value="${resultMap.fzrjsfour}" onchange="setTotal('fzrjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrjsfive" value="${resultMap.fzrjsfive}" onchange="setTotal('fzrjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">主任技师</td>
			<td><input type="text" name="zrjsystotal" value="${resultMap.zrjsystotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrjsysone" value="${resultMap.zrjsysone}" onchange="setTotal('zrjsys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrjsystwo" value="${resultMap.zrjsystwo}" onchange="setTotal('zrjsys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrjsysthree" value="${resultMap.zrjsysthree}" onchange="setTotal('zrjsys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrjsysfour" value="${resultMap.zrjsysfour}" onchange="setTotal('zrjsys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrjsysfive" value="${resultMap.zrjsysfive}" onchange="setTotal('zrjsys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">主管药师</td>
			<td><input type="text" name="zgystotal" value="${resultMap.zgystotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zgysone" value="${resultMap.zgysone}" onchange="setTotal('zgys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zgystwo" value="${resultMap.zgystwo}" onchange="setTotal('zgys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zgysthree" value="${resultMap.zgysthree}" onchange="setTotal('zgys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zgysfour" value="${resultMap.zgysfour}" onchange="setTotal('zgys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zgysfive" value="${resultMap.zgysfive}" onchange="setTotal('zgys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">主任药师</td>
			<td><input type="text" name="zrystotal" value="${resultMap.zrystotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrysone" value="${resultMap.zrysone}" onchange="setTotal('zrys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrystwo" value="${resultMap.zrystwo}" onchange="setTotal('zrys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrysthree" value="${resultMap.zrysthree}" onchange="setTotal('zrys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrysfour" value="${resultMap.zrysfour}" onchange="setTotal('zrys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrysfive" value="${resultMap.zrysfive}" onchange="setTotal('zrys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">副主任药师</td>
			<td><input type="text" name="fzrystotal" value="${resultMap.fzrystotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrysone" value="${resultMap.fzrysone}" onchange="setTotal('fzrys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrystwo" value="${resultMap.fzrystwo}" onchange="setTotal('fzrys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrysthree" value="${resultMap.fzrysthree}" onchange="setTotal('fzrys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrysfour" value="${resultMap.fzrysfour}" onchange="setTotal('fzrys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrysfive" value="${resultMap.fzrysfive}" onchange="setTotal('fzrys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">研究实习员</td>
			<td><input type="text" name="yjsxytotal" value="${resultMap.yjsxytotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="yjsxyone" value="${resultMap.yjsxyone}" onchange="setTotal('yjsxy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="yjsxytwo" value="${resultMap.yjsxytwo}" onchange="setTotal('yjsxy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="yjsxythree" value="${resultMap.yjsxythree}" onchange="setTotal('yjsxy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="yjsxyfour" value="${resultMap.yjsxyfour}" onchange="setTotal('yjsxy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="yjsxyfive" value="${resultMap.yjsxyfive}" onchange="setTotal('yjsxy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">助理研究员</td>
			<td><input type="text" name="zlyjytotal" value="${resultMap.zlyjytotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zlyjyone" value="${resultMap.zlyjyone}" onchange="setTotal('zlyjy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zlyjytwo" value="${resultMap.zlyjytwo}" onchange="setTotal('zlyjy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zlyjythree" value="${resultMap.zlyjythree}" onchange="setTotal('zlyjy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zlyjyfour" value="${resultMap.zlyjyfour}" onchange="setTotal('zlyjy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zlyjyfive" value="${resultMap.zlyjyfive}" onchange="setTotal('zlyjy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">副研究员</td>
			<td><input type="text" name="fyjytotal" value="${resultMap.fyjytotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fyjyone" value="${resultMap.fyjyone}" onchange="setTotal('fyjy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fyjytwo" value="${resultMap.fyjytwo}" onchange="setTotal('fyjy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fyjythree" value="${resultMap.fyjythree}" onchange="setTotal('fyjy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fyjyfour" value="${resultMap.fyjyfour}" onchange="setTotal('fyjy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fyjyfive" value="${resultMap.fyjyfive}" onchange="setTotal('fyjy')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">研究员</td>
			<td><input type="text" name="yjyhstotal" value="${resultMap.yjyhstotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="yjyhsone" value="${resultMap.yjyhsone}" onchange="setTotal('yjyhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="yjyhstwo" value="${resultMap.yjyhstwo}" onchange="setTotal('yjyhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="yjyhsthree" value="${resultMap.yjyhsthree}" onchange="setTotal('yjyhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="yjyhsfour" value="${resultMap.yjyhsfour}" onchange="setTotal('yjyhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="yjyhsfive" value="${resultMap.yjyhsfive}" onchange="setTotal('yjyhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">主管护师</td>
			<td><input type="text" name="zghstotal" value="${resultMap.zghstotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zghsone" value="${resultMap.zghsone}" onchange="setTotal('zghs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zghstwo" value="${resultMap.zghstwo}" onchange="setTotal('zghs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zghsthree" value="${resultMap.zghsthree}" onchange="setTotal('zghs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zghsfour" value="${resultMap.zghsfour}" onchange="setTotal('zghs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zghsfive" value="${resultMap.zghsfive}" onchange="setTotal('zghs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">副主任护师</td>
			<td><input type="text" name="fzrhstotal" value="${resultMap.fzrhstotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrhsone" value="${resultMap.fzrhsone}" onchange="setTotal('fzrhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrhstwo" value="${resultMap.fzrhstwo}" onchange="setTotal('fzrhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrhsthree" value="${resultMap.fzrhsthree}" onchange="setTotal('fzrhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrhsfour" value="${resultMap.fzrhsfour}" onchange="setTotal('fzrhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fzrhsfive" value="${resultMap.fzrhsfive}" onchange="setTotal('fzrhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">主任护师</td>
			<td><input type="text" name="zrhstotal" value="${resultMap.zrhstotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrhsone" value="${resultMap.zrhsone}" onchange="setTotal('zrhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrhstwo" value="${resultMap.zrhstwo}" onchange="setTotal('zrhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrhsthree" value="${resultMap.zrhsthree}" onchange="setTotal('zrhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrhsfour" value="${resultMap.zrhsfour}" onchange="setTotal('zrhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zrhsfive" value="${resultMap.zrhsfive}" onchange="setTotal('zrhs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">助教</td>
			<td><input type="text" name="zjtotal" value="${resultMap.zjtotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zjone" value="${resultMap.zjone}" onchange="setTotal('zj')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zjtwo" value="${resultMap.zjtwo}" onchange="setTotal('zj')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zjthree" value="${resultMap.zjthree}" onchange="setTotal('zj')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zjfour" value="${resultMap.zjfour}" onchange="setTotal('zj')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="zjfive" value="${resultMap.zjfive}" onchange="setTotal('zj')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">讲师</td>
			<td><input type="text" name="jstotal" value="${resultMap.jstotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jsone" value="${resultMap.jsone}" onchange="setTotal('js')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jstwo" value="${resultMap.jstwo}" onchange="setTotal('js')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jsthree" value="${resultMap.jsthree}" onchange="setTotal('js')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jsfour" value="${resultMap.jsfour}" onchange="setTotal('js')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jsfive" value="${resultMap.jsfive}" onchange="setTotal('js')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">教授</td>
			<td><input type="text" name="jsototal" value="${resultMap.jsototal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jsoone" value="${resultMap.jsoone}" onchange="setTotal('jso')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jsotwo" value="${resultMap.jsotwo}" onchange="setTotal('jso')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jsothree" value="${resultMap.jsothree}" onchange="setTotal('jso')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jsofour" value="${resultMap.jsofour}" onchange="setTotal('jso')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jsofive" value="${resultMap.jsofive}" onchange="setTotal('jso')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">副教授</td>
			<td><input type="text" name="fjstotal" value="${resultMap.fjstotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fjsone" value="${resultMap.fjsone}" onchange="setTotal('fjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fjstwo" value="${resultMap.fjstwo}" onchange="setTotal('fjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fjsthree" value="${resultMap.fjsthree}" onchange="setTotal('fjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fjsfour" value="${resultMap.fjsfour}" onchange="setTotal('fjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="fjsfive" value="${resultMap.fjsfive}" onchange="setTotal('fjs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">技师</td>
			<td><input type="text" name="jistotal" value="${resultMap.jistotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jisone" value="${resultMap.jisone}" onchange="setTotal('jis')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jistwo" value="${resultMap.jistwo}" onchange="setTotal('jis')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jisthree" value="${resultMap.jisthree}" onchange="setTotal('jis')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jisfour" value="${resultMap.jisfour}" onchange="setTotal('jis')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="jisfive" value="${resultMap.jisfive}" onchange="setTotal('jis')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">药师</td>
			<td><input type="text" name="ystotal" value="${resultMap.ystotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="ysone" value="${resultMap.ysone}" onchange="setTotal('ys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="ystwo" value="${resultMap.ystwo}" onchange="setTotal('ys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="ysthree" value="${resultMap.ysthree}" onchange="setTotal('ys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="ysfour" value="${resultMap.ysfour}" onchange="setTotal('ys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="ysfive" value="${resultMap.ysfive}" onchange="setTotal('ys')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">护师</td>
			<td><input type="text" name="hstotal" value="${resultMap.hstotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="hsone" value="${resultMap.hsone}" onchange="setTotal('hs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="hstwo" value="${resultMap.hstwo}" onchange="setTotal('hs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="hsthree" value="${resultMap.hsthree}" onchange="setTotal('hs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="hsfour" value="${resultMap.hsfour}" onchange="setTotal('hs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="hsfive" value="${resultMap.hsfive}" onchange="setTotal('hs')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
		<tr>
			<td style="text-align: center;" width="120px;">其他</td>
			<td><input type="text" name="othertotal" value="${resultMap.othertotal}" readonly="readonly" class="inputText" title="自动计算合计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="otherone" value="${resultMap.otherone}" onchange="setTotal('other')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="othertwo" value="${resultMap.othertwo}" onchange="setTotal('other')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="otherthree" value="${resultMap.otherthree}" onchange="setTotal('other')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="otherfour" value="${resultMap.otherfour}" onchange="setTotal('other')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
			<td><input type="text" name="otherfive" value="${resultMap.otherfive}" onchange="setTotal('other')" class="inputText validate[custom[number]]" readonly="readonly" title="自动统计，不可修改" style="width: 80%;"/></td>
		</tr>
	</table>
	<c:if test="${param.view != GlobalConstant.FLAG_Y}">
		<div align="center" style="margin-top: 10px">
			<input id="prev" type="button" onclick="nextOpt('step6')" class="search" value="上一步"/>
			<input id="nxt" type="button" onclick="nextOpt('step8')" class="search" value="下一步"/>
		</div>
	</c:if>
</form>

<table id="template" style="display: none">
	<tr id="memberList">
		<td><input type="checkbox" class="toDel"></td>
		<td class="seq"><input name="memberList_serialNum" type="text" style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
		<td>
			<input type="text" class="inputText validate[required]" name="memberList_name" value="" style="width: 80%"/>
		</td>
		<td>
			<input type="text" class="inputText validate[required,custom[integer]]" name="memberList_age" value=""
				   style="width: 80%" onchange="theLevelAgeTotal();"/>
		</td>
		<td>
			<select name="memberList_education" class="inputText validate[required]" style="width: 80%;">
				<option value="">请选择</option>
				<c:forEach var="dict" items="${dictTypeEnumUserEducationList}">
					<option value="${dict.dictName }">${dict.dictName }</option>
				</c:forEach>
			</select>
		</td>

		<td>
			<select name="memberList_title" class="inputText validate[required]" style="width: 80%;" onchange="theLevelAgeTotal();">
				<option value="">请选择</option>
				<option value="住院医师">
					住院医师
				</option>
				<option value="主治医师"
						<c:if test="${memberList.objMap.memberList_title eq '主治医师'}">selected="selected"</c:if>>
					主治医师
				</option>
				<%--<option value="主任医师"
						<c:if test="${memberList.objMap.memberList_title eq '主任医师'}">selected="selected"</c:if>>
					主任医师
				</option>--%>
				<option value="副主任医师"
						<c:if test="${memberList.objMap.memberList_title eq '副主任医师'}">selected="selected"</c:if>>
					副主任医师
				</option>
				<option value="主任医师"
						<c:if test="${memberList.objMap.memberList_title eq '主任医师'}">selected="selected"</c:if>>
					主任医师
				</option>
				<option value="主管技师"
						<c:if test="${memberList.objMap.memberList_title eq '主管技师'}">selected="selected"</c:if>>
					主管技师
				</option>
				<option value="副主任技师"
						<c:if test="${memberList.objMap.memberList_title eq '副主任技师'}">selected="selected"</c:if>>
					副主任技师
				</option>
				<option value="主任技师"
						<c:if test="${memberList.objMap.memberList_title eq '主任技师'}">selected="selected"</c:if>>
					主任技师
				</option>
				<option value="主管药师"
						<c:if test="${memberList.objMap.memberList_title eq '主管药师'}">selected="selected"</c:if>>
					主管药师
				</option>
				<option value="主任药师"
						<c:if test="${memberList.objMap.memberList_title eq '主任药师'}">selected="selected"</c:if>>
					主任药师
				</option>
				<option value="副主任药师"
						<c:if test="${memberList.objMap.memberList_title eq '副主任药师'}">selected="selected"</c:if>>
					副主任药师
				</option>
				<option value="研究实习员"
						<c:if test="${memberList.objMap.memberList_title eq '研究实习员'}">selected="selected"</c:if>>
					研究实习员
				</option>
				<option value="助理研究员"
						<c:if test="${memberList.objMap.memberList_title eq '助理研究员'}">selected="selected"</c:if>>
					助理研究员
				</option>
				<option value="副研究员"
						<c:if test="${memberList.objMap.memberList_title eq '副研究员'}">selected="selected"</c:if>>
					副研究员
				</option>
				<option value="研究员"
						<c:if test="${memberList.objMap.memberList_title eq '研究员'}">selected="selected"</c:if>>
					研究员
				</option>
				<option value="主管护师"
						<c:if test="${memberList.objMap.memberList_title eq '主管护师'}">selected="selected"</c:if>>
					主管护师
				</option>
				<option value="副主任护师"
						<c:if test="${memberList.objMap.memberList_title eq '副主任护师'}">selected="selected"</c:if>>
					副主任护师
				</option>
				<option value="主任护师"
						<c:if test="${memberList.objMap.memberList_title eq '主任护师'}">selected="selected"</c:if>>
					主任护师
				</option>
				<option value="助教"
						<c:if test="${memberList.objMap.memberList_title eq '助教'}">selected="selected"</c:if>>
					助教
				</option>
				<option value="讲师"
						<c:if test="${memberList.objMap.memberList_title eq '讲师'}">selected="selected"</c:if>>
					讲师
				</option>
				<option value="教授"
						<c:if test="${memberList.objMap.memberList_title eq '教授'}">selected="selected"</c:if>>
					教授
				</option>
				<option value="副教授"
						<c:if test="${memberList.objMap.memberList_title eq '副教授'}">selected="selected"</c:if>>
					副教授
				</option>
				<option value="技师"
						<c:if test="${memberList.objMap.memberList_title eq '技师'}">selected="selected"</c:if>>
					技师
				</option>
				<option value="药师"
						<c:if test="${memberList.objMap.memberList_title eq '药师'}">selected="selected"</c:if>>
					药师
				</option>
				<option value="护师"
						<c:if test="${memberList.objMap.memberList_title eq '护师'}">selected="selected"</c:if>>
					护师
				</option>
				<option value="其他"
						<c:if test="${memberList.objMap.memberList_title eq '其他'}">selected="selected"</c:if>>
					其他
				</option>
			</select>
		</td>
		<td>
			<input type="text" class="inputText validate[required]" name="memberList_dept" value=""
				   style="width: 80%"/>
		</td>
		<td>
			<input type="text" class="inputText validate[required]" name="memberList_spe" value="" style="width: 80%"/>
		</td>
		<td>
			<input type="text" class="inputText validate[required]" name="memberList_bearResponsibility" value=""
				   style="width: 80%"/>
		</td>
	</tr>
</table>


