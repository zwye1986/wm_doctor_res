<c:if test="${param.view != GlobalConstant.FLAG_Y}">

    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            form.append("<input name='nextPageName' value='" + step + "' type='hidden'/>");
            $('#nxt').attr({"disabled": "disabled"});
            $('#prev').attr({"disabled": "disabled"});
            jboxStartLoading();
            form.submit();
        }
        function add(templateId) {
            if (templateId) {
                $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                reSeq(templateId);
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
                }, null);
            }
        }

        function reSeq(templateId) {
            $('.' + templateId + ' .seq').each(function (i, n) {
                $(n).text(i + 1);
            });
        }
    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step6"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">五、研究基础与工作条件</font>
    <table width="100%" cellspacing="0" cellpadding="0" class="basic">
        <tr>
            <th style="text-align: left;">
                &#12288;&#12288;1．与本课题相关的以往研究工作摘要（只需列明题目、发表论文出处、第几完成单位、研究内容论点和创新点摘要。对应的详细资料需按要求纳入申报书附件中）
            </th>
        </tr>
        <tr>
            <td><textarea name="researchWorkAbstract" style="width:98%;height: 150px;"
                          class="">${resultMap.researchWorkAbstract}</textarea></td>
        </tr>
    </table>
    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="7" style="text-align: left;padding-left: 15px;">
                2．本课题将使用的主要科研设备、仪器、试剂、实验动物等条件
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('studyCondition');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('studyCondition');"/>
						</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                <td style="text-align: center;" width="5%">选择</td>
            </c:if>
            <td style="text-align: center;" width="5%">序号</td>
            <td style="text-align: center;" width="15%">名称</td>
            <td style="text-align: center;" width="10%">规格</td>
            <td style="text-align: center;" width="15%">产地\生产商</td>
            <td style="text-align: center;" width="20%">操作部门</td>
            <td style="text-align: center;" width="20%">备注</td>
        </tr>
        <tbody class="studyCondition">
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <c:forEach var="studyCondition" items="${resultMap.studyCondition}"
                       varStatus="status">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${status.count}</td>
                    <td>
                        <input type="text" class="inputText" name="studyCondition_name"
                               value="${studyCondition.objMap.studyCondition_name}" style="width: 90%;"/>
                    </td>
                    <td>
                        <input type="text" class="inputText" name="studyCondition_type"
                               value="${studyCondition.objMap.studyCondition_type}" style="width: 90%;"/>
                    </td>
                    <td>
                        <input type="text" class="inputText" name="studyCondition_producer"
                               value="${studyCondition.objMap.studyCondition_producer}" style="width: 90%;"/>
                    </td>
                    <td>
                        <input type="text" class="inputText" name="studyCondition_dept"
                               value="${studyCondition.objMap.studyCondition_dept}" style="width: 90%;"/>
                    </td>
                    <td>
                        <input type="text" class="inputText" name="studyCondition_remark"
                               value="${studyCondition.objMap.studyCondition_remark}" style="width: 90%;"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
            <c:forEach var="studyCondition" items="${resultMap.studyCondition}"
                       varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>
                            ${studyCondition.objMap.studyCondition_name}
                    </td>
                    <td>
                            ${studyCondition.objMap.studyCondition_type}
                    </td>
                    <td>
                            ${studyCondition.objMap.studyCondition_producer}
                    </td>
                    <td>
                            ${studyCondition.objMap.studyCondition_dept}
                    </td>
                    <td>
                            ${studyCondition.objMap.studyCondition_remark}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step7')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>
<table id="template" style="display: none">
    <tr id="studyCondition">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td>
            <input type="text" class="inputText" name="studyCondition_name" style="width: 90%;"/>
        </td>
        <td>
            <input type="text" class="inputText" name="studyCondition_type" style="width: 90%;"/>
        </td>
        <td>
            <input type="text" class="inputText" name="studyCondition_producer" style="width: 90%;"/>
        </td>
        <td>
            <input type="text" class="inputText" name="studyCondition_dept" style="width: 90%;"/>
        </td>
        <td>
            <input type="text" class="inputText" name="studyCondition_remark" style="width: 90%;"/>
        </td>
    </tr>
</table>