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

		function add(templateId) {
			if (templateId) {
				if ($('.' + templateId + ' .toDel').length < 10) {
					$('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
					reSeq(templateId);
					//projCount(templateId);
				} else {
					jboxTip('该项最多新增10条！');
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
		function checkBDDate(dt){
			var dates = $(':text',$(dt).closest("td"));
			if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
				jboxTip("开始时间不能大于结束时间！");
				dt.value = "";
			}

		}
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
	<input type="hidden" id="pageName" name="pageName" value="step8"/>
	<input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
	<input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
	<input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

	<font style="font-size: 14px; font-weight:bold;color: #333;">七、创新团队主要业绩（一）</font>
	<table class="basic" style="width: 100%;margin-top: 10px;">
		<tr>
			<th colspan="8" style="text-align: left;padding-left: 15px;">
				1、近五年获省级及以上科研成果（含专利、新药证书）共
				<input type="text" name="teamScientificResult" value="${resultMap.teamScientificResult}"
					   class="inputText projCount" style="width: 20px;"/>
				项，限填10项代表作。
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
								 style="cursor: pointer;" onclick="add('teamScientificResults');"/>
							<img title="删除" style="cursor: pointer;"
								 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
								 onclick="del('teamScientificResults');"/>
						</span>
				</c:if>
			</th>
		</tr>
		<tr>
			<c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
				<td style="text-align: center;" width="5%">选择</td>
			</c:if>
			<td style="text-align: center;" width="5%">序号</td>
			<td style="text-align: center;" width="10%"><font color="red">*</font>时间</td>
			<td style="text-align: center;" width="20%"><font color="red">*</font>成果名称</td>
			<td style="text-align: center;" width="15%"><font color="red">*</font>授予部门</td>
			<td style="text-align: center;" width="10%"><font color="red">*</font>等级</td>
            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
			    <td style="text-align: center;" width="10%"><font color="red">*</font>姓名</td>
            </c:if>
            <td style="text-align: center;" width="10%"><font color="red">*</font>完成人序次</td>
		</tr>
		<tbody class="teamScientificResults">
		<c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
			<c:forEach var="teamScientificResults" items="${resultMap.teamScientificResults}"
					   varStatus="teamScientificResultsStatus">
				<tr>
					<td><input type="checkbox" class="toDel"></td>
					<td class="seq"><input name="teamScientificResults_serialNum" type="text" value="${teamScientificResults.objMap.teamScientificResults_serialNum}"  style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
					<td>
						<input type="text" class="inputText ctime validate[required]" name="teamScientificResults_grantDate"
							   value="${teamScientificResults.objMap.teamScientificResults_grantDate}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
					</td>
					<td>
						<select name="teamScientificResults_resultName" class="inputText validate[required]" style="width: 90%;">
							<option value="">请选择</option>
							<c:forEach var="dict" items="${dictTypeEnumCgResultNameList}">
								<option value="${dict.dictName }" <c:if test="${teamScientificResults.objMap.teamScientificResults_resultName eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="teamScientificResults_grantDept"
							   value="${teamScientificResults.objMap.teamScientificResults_grantDept}" style="width: 80%"/>
					</td>
					<td>
						<select name="teamScientificResults_level" class="inputText validate[required]" style="width:90%;">
							<option value="">请选择</option>
							<option value="一等" <c:if test="${teamScientificResults.objMap.teamScientificResults_level eq '一等'}">selected="selected"</c:if>>一等</option>
							<option value="二等" <c:if test="${teamScientificResults.objMap.teamScientificResults_level eq '二等'}">selected="selected"</c:if>>二等</option>
							<option value="三等" <c:if test="${teamScientificResults.objMap.teamScientificResults_level eq '三等'}">selected="selected"</c:if>>三等</option>
							<option value="无" <c:if test="${teamScientificResults.objMap.teamScientificResults_level eq '无'}">selected="selected"</c:if>>无</option>
						</select>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="teamScientificResults_peopleName"
							   value="${teamScientificResults.objMap.teamScientificResults_peopleName}" style="width: 80%"/>
					</td>
                    <td>
                        <select name="teamScientificResults_order" class="inputText validate[required]" style="width:90%;">
                            <option value="">请选择</option>
                            <option value="第一完成人" <c:if test="${teamScientificResults.objMap.teamScientificResults_order eq '第一完成人'}">selected="selected"</c:if>>第一完成人</option>
                            <option value="第二完成人" <c:if test="${teamScientificResults.objMap.teamScientificResults_order eq '第二完成人'}">selected="selected"</c:if>>第二完成人</option>
                            <option value="第三完成人" <c:if test="${teamScientificResults.objMap.teamScientificResults_order eq '第三完成人'}">selected="selected"</c:if>>第三完成人</option>
                            <option value="第四完成人" <c:if test="${teamScientificResults.objMap.teamScientificResults_order eq '第四完成人'}">selected="selected"</c:if>>第四完成人</option>
                            <option value="第五完成人" <c:if test="${teamScientificResults.objMap.teamScientificResults_order eq '第五完成人'}">selected="selected"</c:if>>第五完成人</option>
                            <option value="第六完成人" <c:if test="${teamScientificResults.objMap.teamScientificResults_order eq '第六完成人'}">selected="selected"</c:if>>第六完成人</option>
                            <option value="第七完成人" <c:if test="${teamScientificResults.objMap.teamScientificResults_order eq '第七完成人'}">selected="selected"</c:if>>第七完成人</option>
                            <option value="第八完成人" <c:if test="${teamScientificResults.objMap.teamScientificResults_order eq '第八完成人'}">selected="selected"</c:if>>第八完成人</option>
                            <option value="第九完成人" <c:if test="${teamScientificResults.objMap.teamScientificResults_order eq '第九完成人'}">selected="selected"</c:if>>第九完成人</option>
                            <option value="第十完成人" <c:if test="${teamScientificResults.objMap.teamScientificResults_order eq '第十完成人'}">selected="selected"</c:if>>第十完成人</option>
                            <option value="第十一完成人" <c:if test="${teamScientificResults.objMap.teamScientificResults_order eq '第十一完成人'}">selected="selected"</c:if>>第十一完成人</option>
                            <option value="无" <c:if test="${teamScientificResults.objMap.teamScientificResults_order eq '无'}">selected="selected"</c:if>>无</option>
                        </select>
                    </td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${param.view eq GlobalConstant.FLAG_Y}">
			<c:forEach var="teamScientificResults" items="${resultMap.teamScientificResults}"
					   varStatus="teamScientificResultsStatus">
				<tr>
					<td>${teamScientificResults.objMap.teamScientificResults_serialNum}</td>
					<td>
							${teamScientificResults.objMap.teamScientificResults_grantDate}
					</td>
					<td>
							${teamScientificResults.objMap.teamScientificResults_resultName}
					</td>
					<td>
							${teamScientificResults.objMap.teamScientificResults_grantDept}
					</td>
					<td>
							${teamScientificResults.objMap.teamScientificResults_level}
					</td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
					    <td>
							${teamScientificResults.objMap.teamScientificResults_peopleName}
					    </td>
                    </c:if>
                    <td>
                            ${teamScientificResults.objMap.teamScientificResults_order}
                    </td>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>

	<table class="basic" style="width: 100%;margin-top: 10px;">
		<tr>
			<th colspan="8" style="text-align: left;padding-left: 15px;">
				2、近五年为第一负责人的省级及以上科研课题共
				<input type="text" name="teamScientificTopic" value="${resultMap.teamScientificTopic}"
					   class="inputText projCount" style="width: 20px;"/>
				项，限填10项代表作。
				<c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
								 style="cursor: pointer;" onclick="add('teamScientificTopics');"/>
							<img title="删除" style="cursor: pointer;"
								 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
								 onclick="del('teamScientificTopics');"/>
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
			    <td style="text-align: center;" width="10%"><font color="red">*</font>课题编号</td>
            </c:if>
			<td style="text-align: center;" width="20%"><font color="red">*</font>课题名称</td>
			<td style="text-align: center;" width="15%"><font color="red">*</font>课题来源</td>
			<td style="text-align: center;" width="10%"><font color="red">*</font>经费(万元)</td>
			<td style="text-align: center;" width="20%"><font color="red">*</font>起止时间</td>
            <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
			    <td style="text-align: center;" width="10%"><font color="red">*</font>姓名</td>
            </c:if>
		</tr>
		<tbody class="teamScientificTopics">
		<c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
			<c:forEach var="teamScientificTopics" items="${resultMap.teamScientificTopics}" varStatus="teamScientificTopicsStatus">
				<tr>
					<td><input type="checkbox" class="toDel"></td>
					<td class="seq"><input name="teamScientificTopics_serialNum" type="text" value="${teamScientificTopics.objMap.teamScientificTopics_serialNum}"  style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
					<td>
						<input type="text" class="inputText validate[required]" name="teamScientificTopics_topicNo"
							   value="${teamScientificTopics.objMap.teamScientificTopics_topicNo}" style="width: 80%"/>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="teamScientificTopics_topicName"
							   value="${teamScientificTopics.objMap.teamScientificTopics_topicName}" style="width: 80%"/>
					</td>
					<td>
						<select name="teamScientificTopics_topicSource" class="inputText validate[required]" style="width: 90%;">
							<option value="">请选择</option>
							<c:forEach var="dict" items="${dictTypeEnumSubjectSourceList}">
								<option value="${dict.dictName }" <c:if test="${teamScientificTopics.objMap.teamScientificTopics_topicSource eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<input type="text" class="inputText validate[required,custom[number]]"
							   name="teamScientificTopics_funds" value="${teamScientificTopics.objMap.teamScientificTopics_funds}"
							   style="width: 80%"/>
					</td>
					<td>
						<input type="text" class="inputText ctime validate[required]" name="teamScientificTopics_startDate"
							   value="${teamScientificTopics.objMap.teamScientificTopics_startDate}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 110px;" onchange="checkBDDate(this)"/>
						~
						<input type="text" class="inputText ctime validate[required]" name="teamScientificTopics_endDate"
							   value="${teamScientificTopics.objMap.teamScientificTopics_endDate}"
							   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 110px;" onchange="checkBDDate(this)"/>
					</td>
					<td>
						<input type="text" class="inputText validate[required]" name="teamScientificTopics_peopleName"
							   value="${teamScientificTopics.objMap.teamScientificTopics_peopleName}" style="width: 80%"/>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${param.view eq GlobalConstant.FLAG_Y}">
			<c:forEach var="teamScientificTopics" items="${resultMap.teamScientificTopics}" varStatus="teamScientificTopicsStatus">
				<tr>
					<td>${teamScientificTopics.objMap.teamScientificTopics_serialNum}</td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
					    <td>
							${teamScientificTopics.objMap.teamScientificTopics_topicNo}
					    </td>
                    </c:if>
					<td>
							${teamScientificTopics.objMap.teamScientificTopics_topicName}
					</td>
					<td>
							${teamScientificTopics.objMap.teamScientificTopics_topicSource}
					</td>
					<td>
							${teamScientificTopics.objMap.teamScientificTopics_funds}
					</td>
					<td>
							${teamScientificTopics.objMap.teamScientificTopics_startDate}
						~
							${teamScientificTopics.objMap.teamScientificTopics_endDate}
					</td>
                    <c:if test="${!(param.expert eq GlobalConstant.FLAG_Y)}">
					    <td>
							${teamScientificTopics.objMap.teamScientificTopics_peopleName}
					    </td>
                    </c:if>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>



	<c:if test="${param.view != GlobalConstant.FLAG_Y}">
		<div align="center" style="margin-top: 10px">
			<input id="prev" type="button" onclick="nextOpt('step7')" class="search" value="上一步"/>
			<input id="nxt" type="button" onclick="nextOpt('step9')" class="search" value="下一步"/>
		</div>
	</c:if>
</form>

<table id="template" style="display: none">
	<tr id="teamScientificResults">
		<td><input type="checkbox" class="toDel"></td>
		<td class="seq"><input name="teamScientificResults_serialNum" type="text" style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
		<td>
			<input type="text" class="inputText ctime validate[required]" name="teamScientificResults_grantDate" value=""
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
		</td>
		<td>
			<select name="teamScientificResults_resultName" class="inputText validate[required]" style="width: 90%;">
				<option value="">请选择</option>
				<c:forEach var="dict" items="${dictTypeEnumCgResultNameList}">
					<option value="${dict.dictName }">${dict.dictName }</option>
				</c:forEach>
			</select>
		</td>
		<td>
			<input type="text" class="inputText validate[required]" name="teamScientificResults_grantDept" value=""
				   style="width: 80%"/>
		</td>
		<td>
			<select name="teamScientificResults_level" class="inputText validate[required]" style="width:90%;">
				<option value="">请选择</option>
				<option value="一等">一等</option>
				<option value="二等">二等</option>
				<option value="三等">三等</option>
				<option value="无">无</option>
			</select>
		</td>
		<td>
			<input type="text" class="inputText validate[required]" name="teamScientificResults_peopleName" value=""
				   style="width: 80%"/>
		</td>
        <td>
            <select name="teamScientificResults_order" class="inputText validate[required]" style="width:90%;">
                <option value="">请选择</option>
                <option value="第一完成人"  >第一完成人</option>
                <option value="第二完成人"  >第二完成人</option>
                <option value="第三完成人"  >第三完成人</option>
                <option value="第四完成人"  >第四完成人</option>
                <option value="第五完成人"  >第五完成人</option>
                <option value="第六完成人"  >第六完成人</option>
                <option value="第七完成人"  >第七完成人</option>
                <option value="第八完成人"  >第八完成人</option>
                <option value="第九完成人"  >第九完成人</option>
                <option value="第十完成人"  >第十完成人</option>
                <option value="第十一完成人">第十一完成人</option>
                <option value="无">无</option>
            </select>
        </td>
	</tr>

	<tr id="teamScientificTopics">
		<td><input type="checkbox" class="toDel"></td>
		<td class="seq"><input name="teamScientificTopics_serialNum" type="text" style="width:50px;border:0px; text-align: center;" readonly="readonly" /></td>
		<td>
			<input type="text" class="inputText validate[required]" name="teamScientificTopics_topicNo" value=""
				   style="width: 80%"/>
		</td>
		<td>
			<input type="text" class="inputText validate[required]" name="teamScientificTopics_topicName" value=""
				   style="width: 80%"/>
		</td>
		<td>
			<select name="teamScientificTopics_topicSource" class="inputText validate[required]" style="width: 90%;">
				<option value="">请选择</option>
				<c:forEach var="dict" items="${dictTypeEnumSubjectSourceList}">
					<option value="${dict.dictName }">${dict.dictName }</option>
				</c:forEach>
			</select>
		</td>
		<td>
			<input type="text" class="inputText validate[required,custom[number]]" name="teamScientificTopics_funds"
				   value="" style="width: 80%"/>
		</td>
		<td>
			<input type="text" class="inputText ctime validate[required]" name="teamScientificTopics_startDate" value=""
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 110px;" onchange="checkBDDate(this)"/>
			~
			<input type="text" class="inputText ctime validate[required]" name="teamScientificTopics_endDate" value=""
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 110px;" onchange="checkBDDate(this)"/>
		</td>
		<td>
			<input type="text" class="inputText validate[required]" name="teamScientificTopics_peopleName" value=""
				   style="width: 80%"/>
		</td>
	</tr>


</table>
