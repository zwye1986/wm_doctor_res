<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="true"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
    </jsp:include>
    <script type="text/javascript">
        function nextOpt(step) {
            if (false == $("#projForm").validationEngine("validate")) {
                return false;
            }
            var form = $('#projForm');
            var action = form.attr('action');
            action += "?nextPageName=" + step;
            form.attr("action", action);
            form.submit();
        }

        function add(templateId,total) {
            if (templateId) {
                if ($('.' + templateId + ' .toDel').length < total) {
                    $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                    reSeq(templateId);
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
                }, null);
            }
        }

        function reSeq(templateId) {
            $('.' + templateId + ' .seq').each(function (i, n) {
                $(n).text(i + 1);
            });
        }
        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
        $(function () {
            $('#template tr').each(function () {
                var id = this.id;
                if (id) {
                    if (!$('.' + id + ' .toDel').length) {
                        add(id,1);
                    }
                }
            });
        });
        </c:if>
    </script>
    <style type="text/css">
        .bs_tb tbody th {
            background: #fff;
        }

        .bs_tb tbody td {
            text-align: left;
        }

        .bs_tb tbody td input {
            text-align: left;
            margin-left: 10px;
        }
    </style>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post"
                      id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step4"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>
                    <font style="font-size: 14px; font-weight:bold;color: #333; ">五、创新团队人员名单（限10人，必须和申报书人员名单一致）</font>
                    <table class="basic" style="width: 100%">
                        <tr>
                            <th style="text-align: left;padding-left: 15px;" colspan="9">
                                领军人才（限1人）
                            </th>
                        </tr>
                        <tr>
                            <td style="text-align: center;" width="10%"><font color="red">*</font>姓名</td>
                            <td style="text-align: center;" width="5%"><font color="red">*</font>性别</td>
                            <td style="text-align: center;" width="10%"><font color="red">*</font>出生年月</td>
                            <td style="text-align: center;" width="10%"><font color="red">*</font>职称(职务)</td>
                            <td style="text-align: center;" width="15%"><font color="red">*</font>专业</td>
                            <td style="text-align: center;" width="30%"><font color="red">*</font>主要任务</td>
                            <td style="text-align: center;" width="15%"><font color="red">*</font>所在单位</td>
                        </tr>
                        <tbody>
                        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                            <tr>
                                <td>
                                    <input type="text" class="inputText validate[required]" name="leaderName"
                                           value="${resultMap.leaderName}" style="width: 80%"/>
                                </td>
                                <td>
                                    <select name="leaderSex" class="inputText validate[required]" style="width: 80%;">
                                        <option value=""> </option>
                                        <option value="男"
                                                <c:if test="${resultMap.leaderSex eq '男'}">selected="selected"</c:if>>男</option>
                                        <option value="女"
                                                <c:if test="${resultMap.leaderSex eq '女'}">selected="selected"</c:if>>女</option>
                                    </select>
                                </td>

                                <td>
                                    <input class="inputText  validate[required]" type="text" name="leaderBirthday"
                                           value="${resultMap.leaderBirthday}" onClick="WdatePicker({dateFmt:'yyyy-MM'})"
                                           readonly="readonly" style="width: 80%"/>
                                </td>

                                <td>
                                    <select name="leaderTitle" class="inputText validate[required]" style="width: 80%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                            <option value="${dict.dictName }"
                                                    <c:if test="${resultMap.leaderTitle eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="text" class="inputText validate[required]" name="leaderMajor"
                                           value="${resultMap.leaderMajor}" style="width: 80%"/>
                                </td>
                                <td>
                                    <input type="text" class="inputText validate[required]" name="leaderTask"
                                           value="${resultMap.leaderTask}" style="width: 80%"/>
                                </td>
                                <td>
                                    <input type="text" class="inputText validate[required]" name="leaderOrg"
                                           value="${resultMap.leaderOrg}" style="width: 80%"/>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
                            <tr>
                                <td style="text-align: center">
                                        ${resultMap.leaderName}
                                </td>
                                <td style="text-align: center">
                                        ${resultMap.leaderSex}
                                </td>
                                <td style="text-align: center">
                                        ${resultMap.leaderBirthday}
                                </td>
                                <td style="text-align: center">
                                        ${resultMap.leaderTitle}
                                </td>
                                <td style="text-align: center">
                                        ${resultMap.leaderMajor}
                                </td>
                                <td style="text-align: center">
                                        ${resultMap.leaderTask}
                                </td>
                                <td style="text-align: center">
                                        ${resultMap.leaderOrg}
                                </td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>

                    <table class="basic" style="width: 100%">
                        <tr>
                            <th style="text-align: left;padding-left: 15px;" colspan="9">
                                其他人员(限9人)
                                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						<span style="float: right;padding-right: 10px">
							<img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="add('projMember',9);"/>
							<img title="删除" style="cursor: pointer;"
                                 src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                 onclick="del('projMember');"/>
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
                            <td style="text-align: center;" width="5%"><font color="red">*</font>性别</td>
                            <td style="text-align: center;" width="10%"><font color="red">*</font>出生年月</td>
                            <td style="text-align: center;" width="10%"><font color="red">*</font>职称(职务)</td>
                            <td style="text-align: center;" width="15%"><font color="red">*</font>专业</td>
                            <td style="text-align: center;" width="30%"><font color="red">*</font>主要任务</td>
                            <td style="text-align: center;" width="15%"><font color="red">*</font>所在单位</td>
                        </tr>
                        <tbody class="projMember">
                        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                            <c:forEach var="projMember" items="${resultMap.projMember}" varStatus="projMemberStatus">
                                <tr>
                                    <td><input type="checkbox" class="toDel"></td>
                                    <td class="seq">${projMemberStatus.count}</td>
                                    <td>
                                        <input type="text" class="inputText validate[required]" name="projMember_name"
                                               value="${projMember.objMap.projMember_name}" style="width: 80%"/>
                                    </td>
                                    <td>
                                        <select name="projMember_sex" class="inputText validate[required]" style="width: 80%;">
                                            <option value=""> </option>
                                            <option value="男"
                                                    <c:if test="${projMember.objMap.projMember_sex eq '男'}">selected="selected"</c:if>>男</option>
                                            <option value="女"
                                                    <c:if test="${projMember.objMap.projMember_sex eq '女'}">selected="selected"</c:if>>女</option>
                                        </select>
                                    </td>

                                    <td>
                                        <input type="text" class="inputText  validate[required]" name="projMember_birthday"
                                               value="${projMember.objMap.projMember_birthday}"  readonly="readonly" style="width: 80%" onClick="WdatePicker({dateFmt:'yyyy-MM'})"/>
                                    </td>
                                    <td>
                                        <select name="projMember_title" class="inputText validate[required]" style="width: 80%;">
                                            <option value="">请选择</option>
                                            <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                                <option value="${dict.dictName }"
                                                        <c:if test="${projMember.objMap.projMember_title eq dict.dictName }">selected="selected"</c:if>>${dict.dictName }</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="text" class="inputText validate[required]" name="projMember_major"
                                               value="${projMember.objMap.projMember_major}" style="width: 80%"/>
                                    </td>
                                    <td>
                                        <input type="text" class="inputText validate[required]" name="projMember_task"
                                               value="${projMember.objMap.projMember_task}" style="width: 80%"/>
                                    </td>
                                    <td>
                                        <input type="text" class="inputText validate[required]" name="projMember_org"
                                               value="${projMember.objMap.projMember_org}" style="width: 80%"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
                            <c:forEach var="projMember" items="${resultMap.projMember}" varStatus="projMemberStatus">
                                <tr>
                                    <td style="text-align: center">${projMemberStatus.count}</td>
                                    <td style="text-align: center">
                                            ${projMember.objMap.projMember_name}
                                    </td>
                                    <td style="text-align: center">
                                            ${projMember.objMap.projMember_sex}
                                    </td>
                                    <td style="text-align: center">
                                            ${projMember.objMap.projMember_birthday}
                                    </td>
                                    <td style="text-align: center">
                                            ${projMember.objMap.projMember_title}
                                    </td>
                                    <td style="text-align: center">
                                            ${projMember.objMap.projMember_major}
                                    </td>
                                    <td style="text-align: center">
                                            ${projMember.objMap.projMember_task}
                                    </td>
                                    <td style="text-align: center">
                                            ${projMember.objMap.projMember_org}
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>

                </form>
                <div class="button"
                     style="width: 100%; <c:if test="${param.view == GlobalConstant.FLAG_Y}">display:none</c:if>">
                    <%--<a href="javascript:void(0)" target="_self" class="search" onclick="nextOpt('step3')">上一步</a>--%>
                    <%--<a href="javascript:void(0)" target="_self" class="search" onclick="nextOpt('step5')">下一步</a>--%>
                        <input id="prev" type="button" onclick="nextOpt('step3')" class="search" value="上一步"/>
                        <input id="prev" type="button" onclick="nextOpt('step5')" class="search" value="下一步"/>
                </div>

                <table id="template" style="display: none">

                    <tr id="projMember">
                        <td><input type="checkbox" class="toDel"></td>
                        <td class="seq"></td>
                        <td>
                            <input type="text" class="inputText validate[required]" name="projMember_name" value="" style="width: 80%"/>
                        </td>
                        <td>
                            <select name="projMember_sex" class="inputText validate[required]" style="width: 80%;">
                                <option value=""> </option>
                                <option value="男">男</option>
                                <option value="女">女</option>
                            </select>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required]" name="projMember_birthday" onClick="WdatePicker({dateFmt:'yyyy-MM'})"
                                   readonly="readonly" style="width: 80%" />
                        </td>

                        <td>
                            <select name="projMember_title" class="inputText validate[required]" style="width: 80%;">
                                <option value="">请选择</option>
                                <c:forEach var="dict" items="${dictTypeEnumUserTitleList}">
                                    <option value="${dict.dictName }">${dict.dictName }</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required]" name="projMember_major" style="width: 80%"/>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required]" name="projMember_task" style="width: 80%"/>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required]" name="projMember_org" style="width: 80%"/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
