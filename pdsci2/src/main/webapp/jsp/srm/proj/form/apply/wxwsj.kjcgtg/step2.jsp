<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

        function add(templateId) {
            if (templateId) {
                $('.' + templateId).append($('#' + templateId).clone().attr('id', ''));
                reSeq(templateId);
                projCount(templateId);
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
    </script>
    <style type="text/css">
        .basic .inputText{text-align: left;margin-left: 10px;}
    </style>
</head>
<body>
<div id="main">
    <div class="mainright">
        <div class="content">
            <div style="margin-top: 10px;">
                <form action="<s:url value='/srm/proj/mine/saveStep'/>" method="post" style="position: relative;"
                      id="projForm">
                    <input type="hidden" id="pageName" name="pageName" value="step2"/>
                    <input type="hidden" id="recFlow" name="recFlow" value="${projRec.recFlow}"/>
                    <input type="hidden" id="projFlow" name="projFlow" value="${proj.projFlow}"/>
                    <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}"/>

                    <font style="font-size: 14px; font-weight:bold;color: #333;">一、基本信息表</font>
                    <table class="basic" style="width: 100%; margin-top: 10px;">
                        <tr>
                            <th width="20%"><font color="red">*</font>项目名称：
                            </th>
                            <td colspan="3"><input type="text" name="proj_name"
                                                   value="<c:if test='${empty resultMap.proj_name and param.view!=GlobalConstant.FLAG_Y}'>${proj.projName}</c:if><c:if test='${!empty resultMap.proj_name}'>${resultMap.proj_name}</c:if>"
                                                   class="inputText validate[required]"
                                                   style="width: 80%"/></td>
                        </tr>
                        <tr>
                            <th width="20%">项目所属学科：
                            </th>
                            <td width="30%"><input type="text" name="proj_subject"
                                                   value="${resultMap.proj_subject}"
                                                   class="inputText"
                                                   style="width: 80%"/></td>
                            <th width="20%">相关学科：</th>
                            <td width="30%"><input type="text" name="related_subject"
                                                   value="${resultMap.related_subject}"
                                                   class="inputText"
                                                   style="width: 80%"/></td>
                        </tr>
                    </table>
                    <table class="basic" style="width: 100%; margin-top: 10px;">
                        <tr>
                            <th colspan="6" style="padding-left: 15px;text-align: left">项目推广单位</th>
                        </tr>
                        <tr>
                            <th colspan="1"><font color="red">*</font>名&#12288;&#12288;称：
                            </th>
                            <td colspan="5">
                                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                                    <input type="text" name="extendUnit_name"
                                           value="${resultMap.extendUnit_name}"
                                           class="inputText validate[required]"
                                           style="width: 80%"/></c:if></td>
                        </tr>
                        <tr>
                            <th width="10%"><font color="red">*</font>联系部门：</th>
                            <td width="30%"><input type="text" name="extendUnit_sectors"
                                                   value="${resultMap.extendUnit_sectors}"
                                                   class="inputText validate[required]"
                                                   style="width: 80%"/></td>
                            <th width="10%"><font color="red">*</font>联 系 人：</th>
                            <td width="20%">
                                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                                    <input type="text" name="extendUnit_contacts"
                                           value="${resultMap.extendUnit_contacts}"
                                           class="inputText validate[required]"
                                           style="width: 80%"/></c:if></td>
                            <th width="10%"><font color="red">*</font>电&#12288;&#12288;话：</th>
                            <td width="20%">
                                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                                    <input type="text" name="extendUnit_phoneNum"
                                           value="${resultMap.extendUnit_phoneNum}"
                                           class="inputText validate[required]"
                                           style="width: 80%"/>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th>通讯地址：</th>
                            <td>
                                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                                    <input type="text" name="extendUnit_address" value="${resultMap.extendUnit_address}"
                                           class="inputText"
                                           style="width: 80%"/>
                                </c:if>
                            </td>
                            <th>电子邮箱：</th>
                            <td>
                                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                                    <input type="text" name="extendUnit_mail" value="${resultMap.extendUnit_mail}"
                                           class="inputText"
                                           style="width: 80%"/>
                                </c:if>
                            </td>
                        </tr>
                    </table>
                    <table class="basic" style="width: 100%; margin-top: 10px;">
                        <tr>
                            <th colspan="8" style="text-align: left;padding-left: 15px;">项目负责人情况</th>
                        </tr>
                        <tr>
                            <th  width="10%"><font color="red">*</font>姓&#12288;&#12288;名：</th>
                            <td width="20%"><c:if test="${param.expert != GlobalConstant.FLAG_Y}"><input type="text" name="name"
                                                                                             value="${resultMap.name}"
                                                                                             class="inputText validate[required]"
                                                                                             style="width: 80%"/>
                            </c:if>
                            </td>
                            <th  width="10%"><font color="red">*</font>性&#12288;&#12288;别：</th>
                            <td width="10%">
                                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                                    <select name="sexId" class="inputText validate[required]" style="width: 80%;">
                                        <option value="">请选择</option>
                                        <c:forEach var="sex" items="${userSexEnumList}">
                                            <c:if test="${sex.id != userSexEnumUnknown.id}">
                                                <option value="${sex.name}"
                                                        <c:if test="${resultMap.sexId eq sex.name}">selected="selected"</c:if>>${sex.name}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </c:if>
                            </td>
                            <th width="10%"><font color="red">*</font>年&#12288;&#12288;龄：</th>
                            <td width="10%">
                                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                                    <input type="text" name="age" value="${resultMap.age}"
                                           class="inputText validate[required]" style="width: 80%"/>
                                </c:if>
                            </td>
                            <th width="10%">技术专业：</th>
                            <td width="20%">
                                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                                    <input type="text" name="technology" value="${resultMap.technology}"
                                           class="inputText"
                                           style="width: 80%"/>
                                </c:if>
                            </td>
                        </tr>

                        <tr>
                            <th >职&#12288;&#12288;称：</th>
                            <td>
                                <input type="text" name="positional_titles" value="${resultMap.positional_titles}"
                                       class="inputText"
                                       style="width: 80%"/>
                            </td>
                            <th >职&#12288;&#12288;务：</th>
                            <td>
                                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                                    <input type="text" name="business" value="${resultMap.business}" class="inputText"
                                           style="width: 80%"/>
                                </c:if>
                            </td>
                            <th >电&#12288;&#12288;话：</th>
                            <td colspan="3">
                                <c:if test="${param.expert != GlobalConstant.FLAG_Y}">
                                    <input type="text" name="telephone" value="${resultMap.telephone}"
                                           class="inputText" style="width: 80%"/>
                                </c:if>
                            </td>
                        </tr>
                        <tr>
                            <th>专利情况：</th>
                            <td colspan="7"><input type="text" name="patent" value="${resultMap.patent}"
                                                   class="inputText" style="width: 80%"/>
                            </td>
                        </tr>
                    </table>
                    <table class="basic" style="width: 100%; margin-top: 10px;">
                        <tr>
                            <th colspan="6" style="text-align: left;padding-left: 15px;">项目水平
                                <c:if test="${param.view!=GlobalConstant.FLAG_Y }">
						        <span style="float: right;padding-right: 10px">
							    <img title="新增" src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                     style="cursor: pointer;" onclick="add('projectLevels');"/>
							    <img title="删除" style="cursor: pointer;"
                                     src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                     onclick="del('projectLevels');"/>
						        </span>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                                <td style="text-align: center" width="5%">选择</td>
                            </c:if>
                            <td style="text-align: center"  width="5%">序号</td>
                            <td style="text-align: center"  width="10%"><font color="red">*</font>何年何月</td>
                            <td style="text-align: center"  width="20%"><font color="red">*</font>奖励名称</td>
                            <td style="text-align: center"  width="10%"><font color="red">*</font>奖励等级</td>
                            <td style="text-align: center"  width="15%"><font color="red">*</font>授奖部门</td>
                        </tr>
                        <tbody class="projectLevels">
                        <c:if test="${!(param.view eq GlobalConstant.FLAG_Y)}">
                            <c:forEach var="projectLevels" items="${resultMap.projectLevels}"
                                       varStatus="projectLevelsStatus">
                                <tr>
                                    <td style="text-align: center"><input type="checkbox" class="toDel"></td>
                                    <td style="text-align: center" class="seq">${projectLevelsStatus.count}</td>
                                    <td style="text-align: center">
                                        <input type="text" class="inputText ctime validate[required]"
                                               name="projectLevels_date"
                                               value="${projectLevels.objMap.projectLevels_date}"
                                               onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"/>
                                    </td>
                                    <td style="text-align: center">
                                        <input type="text" class="inputText validate[required]"
                                               name="projectLevels_name"
                                               value="${projectLevels.objMap.projectLevels_name}" style="width: 80%"/>
                                    </td>
                                    <td style="text-align: center">
                                        <input type="text" class="inputText validate[required]"
                                               name="projectLevels_level"
                                               value="${projectLevels.objMap.projectLevels_level}" style="width: 80%"/>
                                    </td>
                                    <td style="text-align: center">
                                        <input type="text" class="inputText validate[required]"
                                               name="projectLevels_dept"
                                               value="${projectLevels.objMap.projectLevels_dept}" style="width: 80%"/>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        <c:if test="${param.view eq GlobalConstant.FLAG_Y}">
                            <c:forEach var="projectLevels" items="${resultMap.projectLevels}"
                                       varStatus="projectLevelsStatus">
                                <tr>
                                    <td style="text-align: center"${projectLevelsStatus.count}</td>
                                    <td style="text-align: center">
                                            ${projectLevels.objMap.projectLevels_date}
                                    </td>
                                    <td style="text-align: center">
                                            ${projectLevels.objMap.projectLevels_name}
                                    </td>
                                    <td style="text-align: center">
                                            ${projectLevels.objMap.projectLevels_level}
                                    </td>
                                    <td style="text-align: center">
                                            ${projectLevels.objMap.projectLevels_dept}
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                    <table cellspacing="0" cellpadding="0" class="basic" style="width: 100%; margin-top: 10px;">
                        <tr>
                            <th style="text-align: left;padding-left: 15px;">立项必要性</th>
                        </tr>
                        <tr>
                            <td style="text-align: left;">
                                &#12288;&#12288;（简述项目技术水平、推广应用价值、推广需求及可行性）。
                            </td>
                        </tr>
                        <tr>
                            <td><textarea name="approve_necessity"
                                          style="width:98%;height: 150px;">${resultMap.approve_necessity}</textarea>
                            </td>
                        </tr>

                    </table>
                </form>

                <c:if test="${param.view!=GlobalConstant.FLAG_Y}">
                    <div align="center" style="margin-top: 10px">
                        <input id="prev" type="button" onclick="nextOpt('step1')" class="search" value="上一步"/>
                        <input id="nxt" type="button" onclick="nextOpt('step3')" class="search" value="下一步"/>
                    </div>
                </c:if>

                <table id="template" style="display: none">
                    <tr id="projectLevels">
                        <td><input type="checkbox" class="toDel"></td>
                        <td class="seq"></td>
                        <td>
                            <input type="text" class="inputText ctime validate[required]"
                                   name="projectLevels_date"
                                   onclick="WdatePicker({dateFmt:'yyyy-MM'})" readonly="readonly"/>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required]" name="projectLevels_name"
                                   style="width: 80%"/>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required]" name="projectLevels_level"
                                   style="width: 80%"/>
                        </td>
                        <td>
                            <input type="text" class="inputText validate[required]" name="projectLevels_dept"
                                   style="width: 80%"/>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
