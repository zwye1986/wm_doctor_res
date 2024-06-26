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
    <input type="hidden" id="pageName" name="pageName" value="step8"/>
    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
    <input type="hidden" name="recTypeId" value="${param.recTypeId}"/>
    <font style="font-size: 14px; font-weight:bold;color: #333;">七、推广单位项目组成员</font>
    <table class="basic" style="width: 100%">
        <tr>
            <th style="text-align: left;padding-left: 15px;" colspan="9">
                负责人名单
                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('presideList');"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('presideList');"/>
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
            <td style="text-align: center;" width="10%"><font color="red">*</font>性别</td>
            <td style="text-align: center;" width="5%"><font color="red">*</font>年龄</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>职称(职务)</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>单位（部门）</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>工作分工</td>
            <td style="text-align: center;" width="20%"><font color="red">*</font>联系电话</td>
        </tr>
        <tbody class="presideList">
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <c:forEach var="presideList" items="${resultMap.presideList}" varStatus="presideListStatus">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${presideListStatus.count}</td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="presideList_name"
                               value="${presideList.objMap.presideList_name}" style="width: 80%"/>
                    </td>
                    <td>
                        <select name="presideList_sex" class="inputText validate[required]" style="width: 80%;">
                            <option value=""> </option>
                            <option value="男"
                                    <c:if test="${presideList.objMap.presideList_sex eq '男'}">selected="selected"</c:if>>男</option>
                            <option value="女"
                                    <c:if test="${presideList.objMap.presideList_sex eq '女'}">selected="selected"</c:if>>女</option>
                        </select>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required,custom[integer]]" name="presideList_age"
                               value="${presideList.objMap.presideList_age}" style="width: 80%"
                               onchange="theLevelAgeTotal();"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="presideList_title"
                               value="${presideList.objMap.presideList_title}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="presideList_department"
                               value="${presideList.objMap.presideList_department}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="presideList_work"
                               value="${presideList.objMap.presideList_work}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="presideList_phone"
                               value="${presideList.objMap.presideList_phone}" style="width: 80%"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
            <c:forEach var="presideList" items="${resultMap.presideList}" varStatus="presideListStatus">
                <tr>
                    <td>${presideListStatus.count}</td>
                    <td>
                            ${presideList.objMap.presideList_name}
                    </td>
                    <td>
                            ${presideList.objMap.presideList_sex}
                    </td>
                    <td>
                            ${presideList.objMap.presideList_age}
                    </td>
                    <td>
                            ${presideList.objMap.presideList_title}
                    </td>
                    <td>
                            ${presideList.objMap.presideList_department}
                    </td>
                    <td>
                            ${presideList.objMap.presideList_work}
                    </td>
                    <td>
                            ${presideList.objMap.presideList_phone}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>

    <table class="basic" style="width: 100%">
        <tr>
            <th style="text-align: left;padding-left: 15px;" colspan="9">
                项目参加人员名单
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
            <td style="text-align: center;" width="10%"><font color="red">*</font>性别</td>
            <td style="text-align: center;" width="5%"><font color="red">*</font>年龄</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>职称(职务)</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>单位（部门）</td>
            <td style="text-align: center;" width="10%"><font color="red">*</font>工作分工</td>
            <td style="text-align: center;" width="20%"><font color="red">*</font>联系电话</td>
        </tr>
        <tbody class="memberList">
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
            <c:forEach var="memberList" items="${resultMap.memberList}" varStatus="memberListStatus">
                <tr>
                    <td><input type="checkbox" class="toDel"></td>
                    <td class="seq">${memberListStatus.count}</td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="memberList_name"
                               value="${memberList.objMap.memberList_name}" style="width: 80%"/>
                    </td>
                    <td>
                        <select name="memberList_sex" class="inputText validate[required]" style="width: 80%;">
                            <option value=""> </option>
                            <option value="男"
                                    <c:if test="${memberList.objMap.memberList_sex eq '男'}">selected="selected"</c:if>>男</option>
                            <option value="女"
                                    <c:if test="${memberList.objMap.memberList_sex eq '女'}">selected="selected"</c:if>>女</option>
                        </select>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required,custom[integer]]" name="memberList_age"
                               value="${memberList.objMap.memberList_age}" style="width: 80%" />
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="memberList_title"
                               value="${memberList.objMap.memberList_title}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="memberList_department"
                               value="${memberList.objMap.memberList_department}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="memberList_work"
                               value="${memberList.objMap.memberList_work}" style="width: 80%"/>
                    </td>
                    <td>
                        <input type="text" class="inputText validate[required]" name="memberList_phone"
                               value="${memberList.objMap.memberList_phone}" style="width: 80%"/>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
            <c:forEach var="memberList" items="${resultMap.memberList}" varStatus="memberListStatus">
                <tr>
                    <td>${memberListStatus.count}</td>
                    <td>
                            ${memberList.objMap.memberList_name}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_sex}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_age}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_title}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_department}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_work}
                    </td>
                    <td>
                            ${memberList.objMap.memberList_phone}
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
</form>
<c:if test="${param.view != GlobalConstant.FLAG_Y}">
    <div align="center" style="margin-top: 10px">
        <input id="prev" type="button" onclick="nextOpt('step7')" class="search" value="上一步"/>
        <input id="nxt" type="button" onclick="nextOpt('step9')" class="search" value="下一步"/>
    </div>
</c:if>
<table id="template" style="display: none">
    <tr id="memberList">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_name" value="" style="width: 80%"/>
        </td>
        <td>
            <select name="memberList_sex" class="inputText validate[required]" style="width: 80%;">
                <option value=""> </option>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </td>
        <td>
            <input type="text" class="inputText validate[required,custom[integer]]" name="memberList_age" style="width: 80%" />
        </td>

        <td>
            <input type="text" class="inputText validate[required]" name="memberList_title" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_department" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_work" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="memberList_phone" style="width: 80%"/>
        </td>
    </tr>

    <tr id="presideList">
        <td><input type="checkbox" class="toDel"></td>
        <td class="seq"></td>
        <td>
            <input type="text" class="inputText validate[required]" name="presideList_name" value="" style="width: 80%"/>
        </td>
        <td>
            <select name="presideList_sex" class="inputText validate[required]" style="width: 80%;">
                <option value=""> </option>
                <option value="男">男</option>
                <option value="女">女</option>
            </select>
        </td>
        <td>
            <input type="text" class="inputText validate[required,custom[integer]]" name="presideList_age" style="width: 80%" />
        </td>

        <td>
            <input type="text" class="inputText validate[required]" name="presideList_title" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="presideList_department" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="presideList_work" style="width: 80%"/>
        </td>
        <td>
            <input type="text" class="inputText validate[required]" name="presideList_phone" style="width: 80%"/>
        </td>
    </tr>
</table>


