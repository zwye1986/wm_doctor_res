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
<style>
    .basic td {
        text-align: center;
        padding-left: 0px;
    }
</style>
<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
      id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step9_1"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>

    <font style="font-size: 14px; font-weight:bold;color: #333;"></font>
    <table class="basic" style="width: 100%;margin-top: 10px;">
        <tr>
            <th colspan="4" style="text-align: left;padding-left: 15px;">
                九、项目规划
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('itemsList');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('itemsList');"/>
						</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                <td style="text-align: center;" width="5%">选择</td>
            </c:if>
            <td style="text-align: center;" width="30%"><font color="red">*</font>年度</td>
            <td style="text-align: center;" width="65%"><font color="red">*</font>计划进度与目标（需划分工作节点，明确关键的、必须实现的节点目标，按照年度划分）
            </td>
        </tr>
        <tbody class="itemsList">

        <c:forEach var="planningList" items="${resultMap.planningList}">
            <tr>
                <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                    <td><input type="checkbox" class="toDel"></td>
                </c:if>
                <td>
                    <input type="text" name="planningList_year"
                           value="${planningList.objMap.planningList_year}"
                           class="inputText ctime validate[required]"
                           onClick="WdatePicker({dateFmt:'yyyy'})"
                           readonly="readonly"/>
                </td>
                <td>
                        <textarea class="validate[required] xltxtarea" style="height: 50px;"
                                  name="planningList_content">${planningList.objMap.planningList_content}</textarea>
                </td>
            </tr>
        </c:forEach>


        </tbody>
    </table>

    <c:if test="${param.view != GlobalConstant.FLAG_Y}">
        <div align="center" style="margin-top: 10px">
            <input id="prev" type="button" onclick="nextOpt('step9')" class="search" value="上一步"/>
            <input id="nxt" type="button" onclick="nextOpt('step10')" class="search" value="下一步"/>
        </div>
    </c:if>
</form>

<table id="template" style="display: none">
    <tr id="itemsList">
        <td><input type="checkbox" class="toDel"></td>
        <td>
            <input type="text" name="planningList_year" class="inputText ctime validate[required]"
                   onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly"/>
        </td>
        <td>
            <textarea class="validate[required] xltxtarea" style="height: 50px;"
                      name="planningList_content"></textarea>
        </td>
    </tr>
</table>
