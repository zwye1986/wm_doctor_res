<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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

		function add(templateId,total) {
			if (templateId) {
				if ($('.' + templateId + ' .toDel').length < total) {
					$('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
					reSeq(templateId);
					//projCount(templateId);
				} else {
					jboxTip('该项最多新增'+total+'条！');
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
					//projCount(templateId);
				}, null);
			}
		}

		function reSeq(templateId) {
			$('.' + templateId + ' .seq').children("input").each(function (i, n) {
				$(n).val(i + 1);
			});
		}

		function projCount(templateId) {
			$('.projCount', $('.' + templateId).closest('table')).val($('.' + templateId + ' .toDel').length);
		}

		$(function () {
//		$('#template tr').each(function(){
//			var id = this.id;
//			if(id){
//				if(!$('.'+id+' .toDel').length){
//					add(id);
//				}
//			}
//		});
		});
	</script>
</c:if>
<style>
	.basic td {
		text-align: center;
		padding-left: 0px;
	}
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
	  id="projForm" style="position: relative;">
	<input type="hidden" id="pageName" name="pageName" value="step9"/>
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

	<font style="font-size: 14px; font-weight:bold;color: #333;">八、创新团队主要业绩（二）</font>
	<table class="basic" style="width: 100%;margin-top: 10px;">
		<tr>
			<th colspan="7" style="text-align: left;padding-left: 15px;">
				3、近五年为第一或者通讯作者发表的SCI和中华级学术论文共
				<input type="text" name="teamLearningPaper" value="${resultMap.teamLearningPaper}" class="inputText projCount"
					   style="width: 20px;"/>
				篇，限填15项代表作。(中华级论文影响因子统一填写0.5)
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
								 style="cursor: pointer;" onclick="add('teamLearningPapers',15);"/>
							<img title="删除" style="cursor: pointer;"
								 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
								 onclick="del('teamLearningPapers');"/>
						</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
				<td style="text-align: center;" width="5%">选择</td>
			</c:if>
			<td style="text-align: center;" width="5%">序号</td>
			<td style="text-align: center;" width="15%"><font color="red">*</font>时间</td>
			<td style="text-align: center;" width="20%"><font color="red">*</font>论文名称</td>
			<td style="text-align: center;" width="20%"><font color="red">*</font>刊物名称</td>
            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
			    <td style="text-align: center;" width="20%"><font color="red">*</font>姓名</td>
            </c:if>
			<td style="text-align: center;" width="20%"><font color="red">*</font>影响因子</td>
		</tr>
		<tbody class="teamLearningPapers">
		<c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
			<c:forEach var="teamLearningPapers" items="${resultMap.teamLearningPapers}" varStatus="teamLearningPapersStatus">
				<tr>
					<td><input type="checkbox" class="toDel"></td>
					<td class="seq"><input name="teamLearningPapers_serialNum" type="text" value="${teamLearningPapers.objMap.teamLearningPapers_serialNum}"  style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
					<td>
						<input type="text" class="inputText ctime validate[required]" name="teamLearningPapers_publishDate"
							   value="${teamLearningPapers.objMap.teamLearningPapers_publishDate}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="teamLearningPapers_paperName"
							   value="${teamLearningPapers.objMap.teamLearningPapers_paperName}" style="width: 80%"/>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="teamLearningPapers_publicactionName"
							   value="${teamLearningPapers.objMap.teamLearningPapers_publicactionName}" style="width: 80%"/>
					</td>
					<td>
						<input type="text" name="teamLearningPapers_peopleName" value="${teamLearningPapers.objMap.teamLearningPapers_peopleName}" class="inputText validate[required]" style="width: 90%"/>
					</td>
					<td>
						<input type="text" name="teamLearningPapers_reason" value="${teamLearningPapers.objMap.teamLearningPapers_reason}" class="inputText validate[required]" style="width: 90%"/>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${param.view eq GlobalConstant.FLAG_Y}">
			<c:forEach var="teamLearningPapers" items="${resultMap.teamLearningPapers}" varStatus="teamLearningPapersStatus">
				<tr>
					<td>${teamLearningPapers.objMap.teamLearningPapers_serialNum}</td>
					<td>
							${teamLearningPapers.objMap.teamLearningPapers_publishDate}
					</td>
					<td>
							${teamLearningPapers.objMap.teamLearningPapers_paperName}
					</td>
					<td>
							${teamLearningPapers.objMap.teamLearningPapers_publicactionName}
					</td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
					    <td>
							${teamLearningPapers.objMap.teamLearningPapers_peopleName}
					    </td>
                    </c:if>
					<td>
							${teamLearningPapers.objMap.teamLearningPapers_reason}
					</td>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>

	<table class="basic" style="width: 100%;margin-top: 10px;">
		<tr>
			<th colspan="6" style="text-align: left;padding-left: 15px;">
				4、近五年学术杂志任职情况，共
				<input type="text" name="teamLearningMagazine" value="${resultMap.teamLearningMagazine}"
					   class="inputText projCount" style="width: 20px;"/>
				份杂志，限填8份代表杂志。
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
								 style="cursor: pointer;" onclick="add('teamLearningMagazines',8);"/>
							<img title="删除" style="cursor: pointer;"
								 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
								 onclick="del('teamLearningMagazines');"/>
						</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
				<td style="text-align: center;" width="5%">选择</td>
			</c:if>
			<td style="text-align: center;" width="5%">序号</td>
			<td style="text-align: center;" width="5%"><font color="red">*</font>任期年限</td>
			<td style="text-align: center;" width="25%"><font color="red">*</font>学术杂志名称</td>
			<td style="text-align: center;" width="20%"><font color="red">*</font>职位</td>
            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
			    <td style="text-align: center;" width="20%"><font color="red">*</font>姓名</td>
            </c:if>
		</tr>
		<tbody class="teamLearningMagazines">
		<c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
			<c:forEach var="teamLearningMagazines" items="${resultMap.teamLearningMagazines}"
					   varStatus="teamLearningMagazinesStatus">
				<tr>
					<td><input type="checkbox" class="toDel"></td>
					<td class="seq"><input name="teamLearningMagazines_serialNum" type="text" value="${teamLearningMagazines.objMap.teamLearningMagazines_serialNum}"  style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
					<td>
						<input type="text" class="inputText validate[required,custom[number]]"
							   name="teamLearningMagazines_officeYear"
							   value="${teamLearningMagazines.objMap.teamLearningMagazines_officeYear}" style="width: 80%"/>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="teamLearningMagazines_name"
							   value="${teamLearningMagazines.objMap.teamLearningMagazines_name}" style="width: 80%"/>
					</td>
					<td>
						<select name="teamLearningMagazines_title" class="inputText validate[required]" style="width: 90%;">
							<option value="">请选择</option>
							<option value="主编"
									<c:if test="${teamLearningMagazines.objMap.teamLearningMagazines_title eq '主编'}">selected</c:if>>
								主编
							</option>
							<option value="副主编"
									<c:if test="${teamLearningMagazines.objMap.teamLearningMagazines_title eq '副主编'}">selected</c:if>>
								副主编
							</option>
							<option value="编委"
									<c:if test="${teamLearningMagazines.objMap.teamLearningMagazines_title eq '编委'}">selected</c:if>>
								编委
							</option>
						</select>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="teamLearningMagazines_peopleName"
							   value="${teamLearningMagazines.objMap.teamLearningMagazines_peopleName}" style="width: 80%"/>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${param.view eq GlobalConstant.FLAG_Y}">
			<c:forEach var="teamLearningMagazines" items="${resultMap.teamLearningMagazines}"
					   varStatus="teamLearningMagazinesStatus">
				<tr>
					<td>${teamLearningMagazines.objMap.teamLearningMagazines_serialNum}</td>
					<td>
							${teamLearningMagazines.objMap.teamLearningMagazines_officeYear}
					</td>
					<td>
							${teamLearningMagazines.objMap.teamLearningMagazines_name}
					</td>
					<td>
							${teamLearningMagazines.objMap.teamLearningMagazines_title}
					</td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
					    <td>
							${teamLearningMagazines.objMap.teamLearningMagazines_peopleName}
					    </td>
                    </c:if>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>

	<table class="basic" style="width: 100%;margin-top: 10px;">
		<tr>
			<th colspan="6" style="text-align: left;padding-left: 15px;">
				5、近五年学会任职情况，共
				<input type="text" name="teamLearningWork" value="${resultMap.teamLearningWork}" class="inputText projCount"
					   style="width: 20px;"/>
				个职务，限填8项最高职务。
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
								 style="cursor: pointer;" onclick="add('teamLearningWorks',8);"/>
							<img title="删除" style="cursor: pointer;"
								 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
								 onclick="del('teamLearningWorks');"/>
						</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
				<td style="text-align: center;" width="5%">选择</td>
			</c:if>
			<td style="text-align: center;" width="5%">序号</td>
			<td style="text-align: center;" width="5%"><font color="red">*</font>任期年限</td>
			<td style="text-align: center;" width="25%"><font color="red">*</font>学会（分会、学组）名称</td>
			<td style="text-align: center;" width="20%"><font color="red">*</font>职位</td>
            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
			    <td style="text-align: center;" width="20%"><font color="red">*</font>姓名</td>
            </c:if>
		</tr>
		<tbody class="teamLearningWorks">
		<c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
			<c:forEach var="teamLearningWorks" items="${resultMap.teamLearningWorks}" varStatus="teamLearningWorksStatus">
				<tr>
					<td><input type="checkbox" class="toDel"></td>
					<td class="seq"><input name="teamLearningWorks_serialNum" type="text" value="${teamLearningWorks.objMap.teamLearningWorks_serialNum}"  style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
					<td>
						<input type="text" class="inputText validate[required,custom[number]]"
							   name="teamLearningWorks_officeYear" value="${teamLearningWorks.objMap.teamLearningWorks_officeYear}"
							   style="width: 80%"/>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="teamLearningWorks_name"
							   value="${teamLearningWorks.objMap.teamLearningWorks_name}" style="width: 80%"/>
					</td>
					<td>
						<select name="teamLearningWorks_title" class="inputText validate[required]" style="width: 90%;">
							<option value="">请选择</option>
							<option value="主委"
									<c:if test="${teamLearningWorks.objMap.teamLearningWorks_title eq '主委'}">selected</c:if>>主委
							</option>
							<option value="副主委"
									<c:if test="${teamLearningWorks.objMap.teamLearningWorks_title eq '副主委'}">selected</c:if>>
								副主委
							</option>
							<option value="组长"
									<c:if test="${teamLearningWorks.objMap.teamLearningWorks_title eq '组长'}">selected</c:if>>组长
							</option>
							<option value="常委"
									<c:if test="${teamLearningWorks.objMap.teamLearningWorks_title eq '常委'}">selected</c:if>>常委
							</option>
							<option value="委员"
									<c:if test="${teamLearningWorks.objMap.teamLearningWorks_title eq '委员'}">selected</c:if>>委员
							</option>
						</select>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="teamLearningWorks_peopleName"
							   value="${teamLearningWorks.objMap.teamLearningWorks_peopleName}" style="width: 80%"/>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${param.view eq GlobalConstant.FLAG_Y}">
			<c:forEach var="teamLearningWorks" items="${resultMap.teamLearningWorks}" varStatus="teamLearningWorksStatus">
				<tr>
					<td>${teamLearningWorks.objMap.teamLearningWorks_serialNum}</td>
					<td>
							${teamLearningWorks.objMap.teamLearningWorks_officeYear}
					</td>
					<td>
							${teamLearningWorks.objMap.teamLearningWorks_name}
					</td>
					<td>
							${teamLearningWorks.objMap.teamLearningWorks_title}
					</td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
					    <td>
							${teamLearningWorks.objMap.teamLearningWorks_peopleName}
					    </td>
                    </c:if>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>

	<c:if test="${param.view != GlobalConstant.FLAG_Y}">
		<div align="center" style="margin-top: 10px">
			<input id="prev" type="button" onclick="nextOpt('step8')" class="search" value="上一步"/>
			<input id="nxt" type="button" onclick="nextOpt('step9_0')" class="search" value="下一步"/>
		</div>
	</c:if>
</form>

<table id="template" style="display: none">



	<tr id="teamLearningPapers">
		<td><input type="checkbox" class="toDel"></td>
		<td class="seq"><input name="teamLearningPapers_serialNum" type="text" style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
		<td>
			<input type="text" class="inputText ctime validate[required]" name="teamLearningPapers_publishDate" value=""
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
		</td>
		<td>
			<input type="text" class="inputText validate[required]" name="teamLearningPapers_paperName" value=""
				   style="width: 80%"/>
		</td>
		<td>
			<input type="text" class="inputText validate[required]" name="teamLearningPapers_publicactionName" value=""
				   style="width: 80%"/>
		</td>
		<td><input type="text" name="teamLearningPapers_peopleName" class="inputText validate[required]" style="width: 90%"/></td>
		<td>
			<input type="text" name="teamLearningPapers_reason" value="" class="inputText validate[required]" style="width: 90%"/>
		</td>
	</tr>

	<tr id="teamLearningMagazines">
		<td><input type="checkbox" class="toDel"></td>
		<td class="seq"><input name="teamLearningMagazines_serialNum" type="text" style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
		<td>
			<input type="text" class="inputText validate[required,custom[number]]" name="teamLearningMagazines_officeYear"
				   value="" style="width: 80%"/>
		</td>
		<td>
			<input type="text" class="inputText validate[required]" name="teamLearningMagazines_name" value=""
				   style="width: 80%"/>
		</td>
		<td>
			<select name="teamLearningMagazines_title" class="inputText validate[required]" style="width: 90%;">
				<option value="">请选择</option>
				<option value="主编">主编</option>
				<option value="副主编">副主编</option>
				<option value="编委">编委</option>
			</select>
		</td>
		<td><input type="text" name="teamLearningMagazines_peopleName" class="inputText validate[required]" style="width: 90%"/></td>

	</tr>

	<tr id="teamLearningWorks">
		<td><input type="checkbox" class="toDel"></td>
		<td class="seq"><input name="teamLearningWorks_serialNum" type="text" style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
		<td>
			<input type="text" class="inputText validate[required,custom[number]]" name="teamLearningWorks_officeYear"
				   value="" style="width: 80%"/>
		</td>
		<td>
			<input type="text" class="inputText validate[required]" name="teamLearningWorks_name" value=""
				   style="width: 80%"/>
		</td>
		<td>
			<select name="teamLearningWorks_title" class="inputText validate[required]" style="width: 90%;">
				<option value="">请选择</option>
				<option value="主委">主委</option>
				<option value="副主委">副主委</option>
				<option value="组长">组长</option>
				<option value="常委">常委</option>
				<option value="委员">委员</option>
			</select>
		</td>
		<td><input type="text" name="teamLearningWorks_peopleName" class="inputText validate[required]" style="width: 90%"/></td>

	</tr>
</table>
