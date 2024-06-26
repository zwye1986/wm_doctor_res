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
                if ($('.' + templateId + ' .toDel').length < 5) {
                    $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                    reSeq(templateId);
                    projCount(templateId);
                } else {
                    jboxTip('该项最多新增5条！');
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
                    projCount(templateId);
                }, null);
            }
        }

        function reSeq(templateId) {
            $('.' + templateId + ' .seq').each(function (i, n) {
                $(n).text(i + 1);
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
    <input type="hidden" id="pageName" name="pageName" value="step4"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">三、医学重点人才主要业绩</font>
    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="7" style="text-align: left;padding-left: 15px;">
                1、近五年获省厅级、市级及以上科研成果共<font color="red">*</font>
                <input type="text" name="scientificResult" class="validate[required] inputText"
                       value="<c:if test='${empty resultMap.scientificResult and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.scientificResult}</c:if><c:if test='${! empty resultMap.scientificResult}'>${resultMap.scientificResult}</c:if>"
                       style="width: 20px;"/>
                项，限填5项代表作。
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('scientificResults');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('scientificResults');"/>
						</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                <td style="text-align: center;" width="5%">选择</td>
            </c:if>
            <td style="text-align: center;" width="5%">序号</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>授予时间</td>
            <td style="text-align: center;" width="20%"><font color="red">*</font>成果名称</td>
            <td style="text-align: center;" width="15%"><font color="red">*</font>授予部门</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>等级</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>完成人序次</td>
        </tr>
        <tbody class="scientificResults">
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <c:forEach var="scientificResults" items="${resultMap.scientificResults}"
                       varStatus="scientificResultsStatus">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${scientificResultsStatus.count}</td>
                    <td>
                        <input type="text" class="inputText ctime validate[required]" name="scientificResults_grantDate"
                               value="${scientificResults.objMap.scientificResults_grantDate}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
                    </td>
                    <td>
                        <select name="scientificResults_resultName" class="inputText validate[required]" style="width: 90%;">
                            <option value="">请选择</option>
                            <c:forEach var="dict" items="${dictTypeEnumCgResultNameList}">
                                <option value="${dict.dictName }" <c:if test="${scientificResults.objMap.scientificResults_resultName eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="scientificResults_grantDept"
                               value="${scientificResults.objMap.scientificResults_grantDept}" style="width: 80%"/>
                    </td>
                    <td>
                        <select name="scientificResults_level" class="inputText validate[required]" style="width:90%;">
                            <option value="">请选择</option>
                            <option value="一等" <c:if test="${scientificResults.objMap.scientificResults_level eq '一等'}">selected="selected"</c:if>>一等</option>
                            <option value="二等" <c:if test="${scientificResults.objMap.scientificResults_level eq '二等'}">selected="selected"</c:if>>二等</option>
                            <option value="三等" <c:if test="${scientificResults.objMap.scientificResults_level eq '三等'}">selected="selected"</c:if>>三等</option>
                            <option value="无" <c:if test="${scientificResults.objMap.scientificResults_level eq '无'}">selected="selected"</c:if>>无</option>
                        </select>
                    </td>
                    <td>
                        <select name="scientificResults_order" class="inputText validate[required]" style="width:90%;">
                            <option value="">请选择</option>
                            <option value="第一完成人" <c:if test="${scientificResults.objMap.scientificResults_order eq '第一完成人'}">selected="selected"</c:if>>第一完成人</option>
                            <option value="第二完成人" <c:if test="${scientificResults.objMap.scientificResults_order eq '第二完成人'}">selected="selected"</c:if>>第二完成人</option>
                            <option value="第三完成人" <c:if test="${scientificResults.objMap.scientificResults_order eq '第三完成人'}">selected="selected"</c:if>>第三完成人</option>
                            <option value="第四完成人" <c:if test="${scientificResults.objMap.scientificResults_order eq '第四完成人'}">selected="selected"</c:if>>第四完成人</option>
                            <option value="第五完成人" <c:if test="${scientificResults.objMap.scientificResults_order eq '第五完成人'}">selected="selected"</c:if>>第五完成人</option>
                            <option value="第六完成人" <c:if test="${scientificResults.objMap.scientificResults_order eq '第六完成人'}">selected="selected"</c:if>>第六完成人</option>
                            <option value="第七完成人" <c:if test="${scientificResults.objMap.scientificResults_order eq '第七完成人'}">selected="selected"</c:if>>第七完成人</option>
                            <option value="第八完成人" <c:if test="${scientificResults.objMap.scientificResults_order eq '第八完成人'}">selected="selected"</c:if>>第八完成人</option>
                            <option value="第九完成人" <c:if test="${scientificResults.objMap.scientificResults_order eq '第九完成人'}">selected="selected"</c:if>>第九完成人</option>
                            <option value="第十完成人" <c:if test="${scientificResults.objMap.scientificResults_order eq '第十完成人'}">selected="selected"</c:if>>第十完成人</option>
                            <option value="第十一完成人" <c:if test="${scientificResults.objMap.scientificResults_order eq '第十一完成人'}">selected="selected"</c:if>>第十一完成人</option>
                            <option value="无" <c:if test="${scientificResults.objMap.scientificResults_order eq '无'}">selected="selected"</c:if>>无</option>
                        </select>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
            <c:forEach var="scientificResults" items="${resultMap.scientificResults}"
                       varStatus="scientificResultsStatus">
                <tr>
                    <td style="text-align: center;">${scientificResultsStatus.count}</td>
                    <td style="text-align: center;">
                            ${scientificResults.objMap.scientificResults_grantDate}
                    </td>
                    <td style="text-align: center;">
                            ${scientificResults.objMap.scientificResults_resultName}
                    </td>
                    <td style="text-align: center;">
                            ${scientificResults.objMap.scientificResults_grantDept}
                    </td>
                    <td style="text-align: center;">
                            ${scientificResults.objMap.scientificResults_level}
                    </td>
                    <td style="text-align: center;">
                            ${scientificResults.objMap.scientificResults_order}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="7" style="text-align: left;padding-left: 15px;">
                2、近五年为第一负责人的省级及以上科研课题共<font color="red">*</font>
                <input type="text" name="scientificTopic" class="validate[required] inputText"
                       value="<c:if test='${empty resultMap.scientificTopic and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.scientificTopic}</c:if><c:if test='${! empty resultMap.scientificTopic}'>${resultMap.scientificTopic}</c:if>"
                       style="width: 20px;"/>
                项，限填5项代表作。
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('scientificTopics');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('scientificTopics');"/>
						</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                <td style="text-align: center;" width="5%">选择</td>
            </c:if>
            <td style="text-align: center;" width="5%">序号</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>课题编号</td>
            <td style="text-align: center;" width="20%"><font color="red">*</font>课题名称</td>
            <td style="text-align: center;" width="15%"><font color="red">*</font>课题来源</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>经费(万元)</td>
            <td style="text-align: center;" width="30%"><font color="red">*</font>起止时间</td>
        </tr>
        <tbody class="scientificTopics">
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <c:forEach var="scientificTopics" items="${resultMap.scientificTopics}" varStatus="scientificTopicsStatus">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${scientificTopicsStatus.count}</td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="scientificTopics_topicNo"
                               value="${scientificTopics.objMap.scientificTopics_topicNo}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="scientificTopics_topicName"
                               value="${scientificTopics.objMap.scientificTopics_topicName}" style="width: 80%"/>
                    </td>
                    <td>
                        <select name="scientificTopics_topicSource" class="inputText validate[required]" style="width: 90%;">
                            <option value="">请选择</option>
                            <c:forEach var="dict" items="${dictTypeEnumSubjectSourceList}">
                                <option value="${dict.dictName }" <c:if test="${scientificTopics.objMap.scientificTopics_topicSource eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required,custom[number]]"
                               name="scientificTopics_funds" value="${scientificTopics.objMap.scientificTopics_funds}"
                               style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText ctime validate[required]" name="scientificTopics_startDate"
                               value="${scientificTopics.objMap.scientificTopics_startDate}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 110px;" onchange="checkBDDate(this)" />
                        ~
                        <input type="text" class="inputText ctime validate[required]" name="scientificTopics_endDate"
                               value="${scientificTopics.objMap.scientificTopics_endDate}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 110px;" onchange="checkBDDate(this)"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
            <c:forEach var="scientificTopics" items="${resultMap.scientificTopics}" varStatus="scientificTopicsStatus">
                <tr>
                    <td style="text-align: center;">${scientificTopicsStatus.count}</td>
                    <td style="text-align: center;">
                            ${scientificTopics.objMap.scientificTopics_topicNo}
                    </td>
                    <td style="text-align: center;">
                            ${scientificTopics.objMap.scientificTopics_topicName}
                    </td>
                    <td style="text-align: center;">
                            ${scientificTopics.objMap.scientificTopics_topicSource}
                    </td>
                    <td style="text-align: center;">
                            ${scientificTopics.objMap.scientificTopics_funds}
                    </td>
                    <td style="text-align: center;">
                            ${scientificTopics.objMap.scientificTopics_startDate}
                        ~
                            ${scientificTopics.objMap.scientificTopics_endDate}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="6" style="text-align: left;padding-left: 15px;">
                3、近五年为第一或者通讯作者发表的SCI和中华级学术论文共<font color="red">*</font>
                <input type="text" name="learningPaper" class="validate[required] inputText"
                       value="<c:if test='${empty resultMap.learningPaper and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.learningPaper}</c:if><c:if test='${! empty resultMap.learningPaper}'>${resultMap.learningPaper}</c:if>"
                       style="width: 20px;"/>
                篇，限填5项代表作。(中华级论文影响因子统一填写0.5)
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('learningPapers');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('learningPapers');"/>
						</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                <td style="text-align: center;" width="5%">选择</td>
            </c:if>
            <td style="text-align: center;" width="5%">序号</td>
            <td style="text-align: center;" width="15%"><font color="red">*</font>发表时间</td>
            <td style="text-align: center;" width="25%"><font color="red">*</font>论文名称</td>
            <td style="text-align: center;" width="25%"><font color="red">*</font>刊物名称</td>
            <td style="text-align: center;" width="20%"><font color="red">*</font>影响因子</td>
        </tr>
        <tbody class="learningPapers">
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <c:forEach var="learningPapers" items="${resultMap.learningPapers}" varStatus="learningPapersStatus">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${learningPapersStatus.count}</td>
                    <td>
                        <input type="text" class="inputText ctime validate[required]" name="learningPapers_publishDate"
                               value="${learningPapers.objMap.learningPapers_publishDate}"
                               onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="learningPapers_paperName"
                               value="${learningPapers.objMap.learningPapers_paperName}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="learningPapers_publicactionName"
                               value="${learningPapers.objMap.learningPapers_publicactionName}" style="width: 80%"/>
                    </td>
                   <td>
                        <input type="text" name="learningPapers_reason" value="${learningPapers.objMap.learningPapers_reason}" class="inputText validate[required]" style="width: 90%"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
            <c:forEach var="learningPapers" items="${resultMap.learningPapers}" varStatus="learningPapersStatus">
                <tr>
                    <td style="text-align: center;">${learningPapersStatus.count}</td>
                    <td style="text-align: center;">
                            ${learningPapers.objMap.learningPapers_publishDate}
                    </td>
                    <td style="text-align: center;">
                            ${learningPapers.objMap.learningPapers_paperName}
                    </td>
                    <td style="text-align: center;">
                            ${learningPapers.objMap.learningPapers_publicactionName}
                    </td>
                    <td style="text-align: center;">
                            ${learningPapers.objMap.learningPapers_reason}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="5" style="text-align: left;padding-left: 15px;">
                4、近五年学术杂志任职情况，共<font color="red">*</font>
                <input type="text" name="learningMagazine" class="validate[required] inputText"
                       value="<c:if test='${empty resultMap.learningMagazine and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.learningMagazine}</c:if><c:if test='${! empty resultMap.learningMagazine}'>${resultMap.learningMagazine}</c:if>"
                       style="width: 20px;"/>
                份杂志，限填5份代表杂志。
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('learningMagazines');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('learningMagazines');"/>
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
        </tr>
        <tbody class="learningMagazines">
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <c:forEach var="learningMagazines" items="${resultMap.learningMagazines}"
                       varStatus="learningMagazinesStatus">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${learningMagazinesStatus.count}</td>
                    <td>
                        <input type="text" class="inputText validate[required,custom[number]]"
                               name="learningMagazines_officeYear"
                               value="${learningMagazines.objMap.learningMagazines_officeYear}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="learningMagazines_name"
                               value="${learningMagazines.objMap.learningMagazines_name}" style="width: 80%"/>
                    </td>
                    <td>
                        <select name="learningMagazines_title" class="inputText validate[required]" style="width: 90%;">
                            <option value="">请选择</option>
                            <option value="主编"
                                    <c:if test="${learningMagazines.objMap.learningMagazines_title eq '主编'}">selected</c:if>>
                                主编
                            </option>
                            <option value="副主编"
                                    <c:if test="${learningMagazines.objMap.learningMagazines_title eq '副主编'}">selected</c:if>>
                                副主编
                            </option>
                            <option value="编委"
                                    <c:if test="${learningMagazines.objMap.learningMagazines_title eq '编委'}">selected</c:if>>
                                编委
                            </option>
                        </select>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
            <c:forEach var="learningMagazines" items="${resultMap.learningMagazines}"
                       varStatus="learningMagazinesStatus">
                <tr>
                    <td style="text-align: center;">${learningMagazinesStatus.count}</td>
                    <td style="text-align: center;">
                            ${learningMagazines.objMap.learningMagazines_officeYear}
                    </td>
                    <td style="text-align: center;">
                            ${learningMagazines.objMap.learningMagazines_name}
                    </td>
                    <td style="text-align: center;">
                            ${learningMagazines.objMap.learningMagazines_title}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="5" style="text-align: left;padding-left: 15px;">
                5、近五年学会任职情况，共<font color="red">*</font>
                <input type="text" name="learningWork" class="validate[required] inputText"
                       value="<c:if test='${empty resultMap.learningWork and param.view!=GlobalConstant.FLAG_Y}'>${resultMap.learningWork}</c:if><c:if test='${! empty resultMap.learningWork}'>${resultMap.learningWork}</c:if>"
                       style="width: 20px;"/>
                个职务，限填5项最高职务。
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('learningWorks');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('learningWorks');"/>
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
        </tr>
        <tbody class="learningWorks">
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <c:forEach var="learningWorks" items="${resultMap.learningWorks}" varStatus="learningWorksStatus">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${learningWorksStatus.count}</td>
                    <td>
                        <input type="text" class="inputText validate[required,custom[number]]"
                               name="learningWorks_officeYear" value="${learningWorks.objMap.learningWorks_officeYear}"
                               style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="learningWorks_name"
                               value="${learningWorks.objMap.learningWorks_name}" style="width: 80%"/>
                    </td>
                    <td>
                        <select name="learningWorks_title" class="inputText validate[required]" style="width: 90%;">
                            <option value="">请选择</option>
                            <option value="主委"
                                    <c:if test="${learningWorks.objMap.learningWorks_title eq '主委'}">selected</c:if>>主委
                            </option>
                            <option value="副主委"
                                    <c:if test="${learningWorks.objMap.learningWorks_title eq '副主委'}">selected</c:if>>
                                副主委
                            </option>
                            <option value="组长"
                                    <c:if test="${learningWorks.objMap.learningWorks_title eq '组长'}">selected</c:if>>组长
                            </option>
                            <option value="副组长"
                                    <c:if test="${learningWorks.objMap.learningWorks_title eq '副组长'}">selected</c:if>>副组长
                            </option>
                            <option value="常委"
                                    <c:if test="${learningWorks.objMap.learningWorks_title eq '常委'}">selected</c:if>>常委
                            </option>
                            <option value="委员"
                                    <c:if test="${learningWorks.objMap.learningWorks_title eq '委员'}">selected</c:if>>委员
                            </option>
                        </select>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
            <c:forEach var="learningWorks" items="${resultMap.learningWorks}" varStatus="learningWorksStatus">
                <tr>
                    <td style="text-align: center;">${learningWorksStatus.count}</td>
                    <td style="text-align: center;">
                            ${learningWorks.objMap.learningWorks_officeYear}
                    </td>
                    <td style="text-align: center;">
                            ${learningWorks.objMap.learningWorks_name}
                    </td>
                    <td style="text-align: center;">
                            ${learningWorks.objMap.learningWorks_title}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step5')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>

<table id="template" style="display: none">
    <tr id="scientificResults">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td>
            <input type="text" class="inputText ctime validate[required]" name="scientificResults_grantDate" value=""
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
        </td>
        <td>
            <select name="scientificResults_resultName" class="inputText validate[required]" style="width: 90%;">
                <option value="">请选择</option>
                <c:forEach var="dict" items="${dictTypeEnumCgResultNameList}">
                    <option value="${dict.dictName }">${dict.dictName }</option>
                </c:forEach>
            </select>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="scientificResults_grantDept" value=""
                   style="width: 80%"/>
        </td>
        <td>
            <select name="scientificResults_level" class="inputText validate[required]" style="width:90%;">
                <option value="">请选择</option>
                <option value="一等">一等</option>
                <option value="二等">二等</option>
                <option value="三等">三等</option>
                <option value="无">无</option>
            </select>
        </td>
        <td>
            <select name="scientificResults_order" class="inputText validate[required]" style="width:90%;">
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

    <tr id="scientificTopics">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td>
            <input type="text" class="inputText validate[required]" name="scientificTopics_topicNo" value=""
                   style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="scientificTopics_topicName" value=""
                   style="width: 80%"/>
        </td>
        <td>
            <select name="scientificTopics_topicSource" class="inputText validate[required]" style="width: 90%;">
                <option value="">请选择</option>
                <c:forEach var="dict" items="${dictTypeEnumSubjectSourceList}">
                    <option value="${dict.dictName }">${dict.dictName }</option>
                </c:forEach>
            </select>
        </td>
        <td>
            <input type="text" class="inputText validate[required,custom[number]]" name="scientificTopics_funds"
                   value="" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText ctime validate[required]" name="scientificTopics_startDate" value=""
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 110px;" onchange="checkBDDate(this)"/>
            ~
            <input type="text" class="inputText ctime validate[required]" name="scientificTopics_endDate" value=""
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly" style="width: 110px;" onchange="checkBDDate(this)"/>
        </td>
    </tr>

    <tr id="learningPapers">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td>
            <input type="text" class="inputText ctime validate[required]" name="learningPapers_publishDate" value=""
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" readonly="readonly"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="learningPapers_paperName" value=""
                   style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="learningPapers_publicactionName" value=""
                   style="width: 80%"/>
        </td>
        <td><input type="text" name="learningPapers_reason" class="inputText validate[required]" style="width: 90%"/></td>
    </tr>

    <tr id="learningMagazines">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td>
            <input type="text" class="inputText validate[required,custom[number]]" name="learningMagazines_officeYear"
                   value="" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="learningMagazines_name" value=""
                   style="width: 80%"/>
        </td>
        <td>
            <select name="learningMagazines_title" class="inputText validate[required]" style="width: 90%;">
                <option value="">请选择</option>
                <option value="主编">主编</option>
                <option value="副主编">副主编</option>
                <option value="编委">编委</option>
            </select>
        </td>
    </tr>

    <tr id="learningWorks">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td>
            <input type="text" class="inputText validate[required,custom[number]]" name="learningWorks_officeYear"
                   value="" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="learningWorks_name" value=""
                   style="width: 80%"/>
        </td>
        <td>
            <select name="learningWorks_title" class="inputText validate[required]" style="width: 90%;">
                <option value="">请选择</option>
                <option value="主委">主委</option>
                <option value="副主委">副主委</option>
                <option value="组长">组长</option>
                <option value="副组长">副组长</option>
                <option value="常委">常委</option>
                <option value="委员">委员</option>
            </select>
        </td>
    </tr>
</table>
