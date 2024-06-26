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

        $(function () {
            $('#template tr').each(function () {
                var id = this.id;
                if (id) {
                    if (!$('.' + id + ' .toDel').length) {
                        add(id);
                    }
                }
            });
        });

    </script>
</c:if>

<form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
      id="projForm" style="position: relative;">
    <input type="hidden" id="pageName" name="pageName" value="step9"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
    <font style="font-size: 14px; font-weight:bold;color: #333;"></font>
    <table class="basic" style="width: 100%">
        <tr>
            <th style="text-align: left;padding-left: 15px;" colspan="9">
                八、受推单位及项目负责人
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('acceptList');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('acceptList');"/>
						</span>
                </c:if>
            </th>
        </tr>
        <tr>
            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                <td style="text-align: center;" width="7%">选择</td>
            </c:if>
            <td style="text-align: center;" width="7%">序号</td>
            <td style="text-align: center;" width="17%"><font color="red">*</font>受推单位</td>
            <td style="text-align: center;" width="17%"><font color="red">*</font>单位负责人</td>
            <td style="text-align: center;" width="17%"><font color="red">*</font>部门负责人</td>
            <td style="text-align: center;" width="17%"><font color="red">*</font>专科负责人</td>
            <td style="text-align: center;" width="17%"><font color="red">*</font>联系电话</td>
        </tr>
        <tbody class="acceptList">
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <c:forEach var="acceptList" items="${resultMap.acceptList}" varStatus="acceptListStatus">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${acceptListStatus.count}</td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="acceptList_company"
                               value="${acceptList.objMap.acceptList_company}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="acceptList_preside"
                               value="${acceptList.objMap.acceptList_preside}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="acceptList_dept"
                               value="${acceptList.objMap.acceptList_dept}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="acceptList_undivided"
                               value="${acceptList.objMap.acceptList_undivided}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="acceptList_phone"
                               value="${acceptList.objMap.acceptList_phone}" style="width: 80%"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
            <c:forEach var="acceptList" items="${resultMap.acceptList}" varStatus="acceptListStatus">
                <tr>
                    <td>${acceptListStatus.count}</td>
                    <td>
                            ${acceptList.objMap.acceptList_company}
                    </td>
                    <td>
                            ${acceptList.objMap.acceptList_preside}
                    </td>
                    <td>
                            ${acceptList.objMap.acceptList_dept}
                    </td>
                    <td>
                            ${acceptList.objMap.acceptList_undivided}
                    </td>
                    <td>
                            ${acceptList.objMap.acceptList_phone}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</form>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step8')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step10')" class="search" value="下一步"/>
    </div>
</c:if>
<table id="template" style="display: none">
    <tr id="acceptList">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td>
            <input type="text" class="inputText validate[required]" name="acceptList_company" value="" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="acceptList_preside" style="width: 80%" />
        </td>

        <td>
            <input type="text" class="inputText validate[required]" name="acceptList_dept" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="acceptList_undivided" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="acceptList_phone" style="width: 80%"/>
        </td>
    </tr>
</table>


