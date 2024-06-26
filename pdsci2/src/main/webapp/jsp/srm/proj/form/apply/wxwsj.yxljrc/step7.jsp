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
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />" style="cursor: pointer;" onclick="add('memberList');"/>
							<img title="删除" style="cursor: pointer;" src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick="del('memberList');"/>
						</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
				<td style="text-align: center;" width="5%">选择</td>
			</c:if>
			<td style="text-align: center;" width="5%">序号</td>
<c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
			<td style="text-align: center;" width="10%"><font color="red">*</font>姓名</td>
    </c:if>
			<td style="text-align: center;" width="5%"><font color="red">*</font>年龄</td>
			<td style="text-align: center;" width="10%"><font color="red">*</font>学历</td>
			<td style="text-align: center;" width="10%"><font color="red">*</font>技术职称</td>
<c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
			<td style="text-align: center;" width="20%"><font color="red">*</font>所在单位及科室</td>
    </c:if>
			<td style="text-align: center;" width="10%"><font color="red">*</font>专业</td>
			<td style="text-align: center;" width="20%"><font color="red">*</font>承担主要任务</td>
		</tr>
		<tbody class="memberList">
		<c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
			<c:forEach var="memberList" items="${resultMap.memberList}" varStatus="memberListStatus">
				<tr>
					<td><input type="checkbox" class="toDel"></td>
					<td class="seq"><input name="memberList_serialNum" type="text" value="${memberList.objMap.memberList_serialNum}"  style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
					<td>
						<input type="text" class="inputText validate[required]" name="memberList_name" value="${memberList.objMap.memberList_name}" style="width: 80%"/>
					</td>
					<td>
						<input type="text" class="inputText validate[required,custom[integer]]" name="memberList_age" value="${memberList.objMap.memberList_age}" style="width: 80%" onchange="theLevelAgeTotal();"/>
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
							<option value="住院医师" <c:if test="${memberList.objMap.memberList_title eq '住院医师'}">selected="selected"</c:if>>
								住院医师
							</option>
							<option value="主治医师" <c:if test="${memberList.objMap.memberList_title eq '主治医师'}">selected="selected"</c:if>>
								主治医师
							</option>
							<option value="副主任医师"
									<c:if test="${memberList.objMap.memberList_title eq '副主任医师'}">selected="selected"</c:if>>
								副主任医师
							</option>
							<option value="主任医师" <c:if test="${memberList.objMap.memberList_title eq '主任医师'}">selected="selected"</c:if>>
								主任医师
							</option>
							<option value="主管技师" <c:if test="${memberList.objMap.memberList_title eq '主管技师'}">selected="selected"</c:if>>
								主管技师
							</option>
							<option value="副主任技师" <c:if test="${memberList.objMap.memberList_title eq '副主任技师'}">selected="selected"</c:if>>
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
						<input type="text" class="inputText validate[required]" name="memberList_dept" value="${memberList.objMap.memberList_dept}" style="width: 80%"/>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="memberList_spe" value="${memberList.objMap.memberList_spe}" style="width: 80%"/>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="memberList_bearResponsibility" value="${memberList.objMap.memberList_bearResponsibility}" style="width: 80%"/>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${param.view eq GlobalConstant.FLAG_Y}">
			<c:forEach var="memberList" items="${resultMap.memberList}" varStatus="memberListStatus">
				<tr>
					<td>${memberList.objMap.memberList_serialNum}</td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
					<td>
						${memberList.objMap.memberList_name}
					</td>
                    </c:if>
					<td>
						${memberList.objMap.memberList_age}
					</td>
					<td>
						${memberList.objMap.memberList_education}
					</td>
					<td>
						${memberList.objMap.memberList_title}
					</td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
					<td>
						${memberList.objMap.memberList_dept}
					</td>
                    </c:if>
					<td>
						${memberList.objMap.memberList_spe}
					</td>
					<td>
						${memberList.objMap.memberList_bearResponsibility}
					</td>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
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
			<input type="text" class="inputText validate[required,custom[integer]]" name="memberList_age" value="" style="width: 80%" onchange="theLevelAgeTotal();"/>
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
			<input type="text" class="inputText validate[required]" name="memberList_dept" value="" style="width: 80%"/>
		</td>
		<td>
			<input type="text" class="inputText validate[required]" name="memberList_spe" value="" style="width: 80%"/>
		</td>
		<td>
			<input type="text" class="inputText validate[required]" name="memberList_bearResponsibility" value="" style="width: 80%"/>
		</td>
	</tr>
</table>


