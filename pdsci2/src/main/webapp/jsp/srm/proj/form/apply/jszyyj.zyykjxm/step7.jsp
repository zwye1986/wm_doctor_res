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
                //   reSeq(templateId);
            }
        }

        function del(templateId) {
            if (templateId) {
                if (!$('.' + templateId + ' .toDel:checked').length) {
                    return jboxTip('请选择需要删除的项目！');
                }
                jboxConfirm('确认删除？', function () {
                    $('.' + templateId + ' .toDel:checked').closest('tr').remove();
                    // reSeq(templateId);
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
    <input type="hidden" id="pageName" name="pageName" value="step7"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;">六、实施计划、考核指标</font>
    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="5" style="text-align: left;padding-left: 15px;">
                总经费：<input type="text" name="amountFunds" value="${resultMap.amountFunds}" class="inputText"/>（万元）
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('startingPlan');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('startingPlan');"/>
						</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                <td style="text-align: center;" width="5%">选择</td>
            </c:if>
            <td style="text-align: center;" width="15%"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>时间安排</td>
            <td style="text-align: center;" width="35%"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>研究内容(分期目标)</td>
            <td style="text-align: center;" width="35%"><span class="redspan" style="color: red;padding: 0px;margin-left: 10px;">*</span>考核指标</td>
            <td style="text-align: center;" width="10%">经费预算</td>
        </tr>
        <tbody class="startingPlan">
        <c:forEach var="startingPlan" items="${resultMap.startingPlan}">
            <tr>
                <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                    <td><input type="checkbox" class="toDel"></td>
                </c:if>
                <td>
                    <input type="text" name="startingPlan_time"
                           value="${startingPlan.objMap.startingPlan_time}"
                           class="inputText validate[required]"/>
                </td>
                <td>
                        <textarea class="xltxtarea validate[required]" style="height: 50px;"
                                  name="startingPlan_content">${startingPlan.objMap.startingPlan_content}</textarea>
                </td>
                <td>
                        <textarea class="xltxtarea validate[required] " style="height: 50px;"
                                  name="startingPlan_examStandard">${startingPlan.objMap.startingPlan_examStandard}</textarea>
                </td>
                <td>
                    <input type="text" name="startingPlan_fundBudget"
                           value="${startingPlan.objMap.startingPlan_fundBudget}"
                           class="inputText"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <td></td>
        </c:if>
        <td>
            其他说明
        </td>
        <td>
                        <textarea class="validate[required] xltxtarea" style="height: 50px;"
                                  name="other_content">${resultMap.other_content}</textarea>
        </td>
        <td>
                        <textarea class="validate[required] xltxtarea" style="height: 50px;"
                                  name="other_examStandard">${resultMap.other_examStandard}</textarea>
        </td>
        <td>
            <input type="text" name="other_fundBudget"
                   value="${resultMap.other_fundBudget}"
                   class="inputText"/>
        </td>
        </tr>
        </tfoot>
    </table>

    <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step6')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step8')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>
<table id="template" style="display: none">
    <tr id="startingPlan">
        <td><input type="checkbox" class="toDel"></td>
        <td>
            <input type="text" name="startingPlan_time"
                   value="${startingPlan.objMap.startingPlan_time}"
                   class="validate[required] inputText"/>
        </td>
        <td>
                        <textarea class="validate[required] xltxtarea" style="height: 50px;"
                                  name="startingPlan_content"></textarea>
        </td>
        <td>
                        <textarea class="validate[required] xltxtarea" style="height: 50px;"
                                  name="startingPlan_examStandard"></textarea>
        </td>
        <td>
            <input type="text" name="startingPlan_fundBudget" class="inputText"/>
        </td>
    </tr>
</table>