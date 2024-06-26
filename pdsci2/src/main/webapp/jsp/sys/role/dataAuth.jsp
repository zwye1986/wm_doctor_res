<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
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
        function doSave() {
            if (false == $("#popForm").validationEngine("validate")) {
                return;
            }
            var url = "<s:url value="/sys/role/saveRoleAuth"/>";
            var data = $('#popForm').serialize();
            jboxPost(url, data, function () {
                //window.parent.frames['mainIframe'].window.search();
                jboxClose();
            });
        }

        function doSelectAll(obj) {
            var $check = $(obj);
            $check.parent().next().find(":checkbox").attr("checked", obj.checked);
        }

        $(document).ready(function () {
            debugger;
            <c:forEach items="${sysDicts}" var="dict">
            <c:forEach items="${schools}" var="data">
            if ("${data.trim()}" == "${dict.dictName}") {
                console.log("${data.trim()}" == "${dict.dictName}");
                $("#" + "${data.trim()}").attr("checked", "checked");
            }
            </c:forEach>
            </c:forEach>
        });
    </script>
</head>
<body>
<div class="mainright">
    <form id="popForm" action="<s:url value="/sys/role/saveRoleAuth"/>" method="post">
        <div class="content">
            <div class="title1 clearfix">
                <div id="tagContent">
                    <div class="tagContent selectTag" id="tagContent0">
                        <div>
                            <table width="800" cellpadding="0" cellspacing="0" class="basic">
                                <tr>
                                    <td class="mation-nab" style="width: 100px;">角色名称：
                                        <input name="roleFlow" type="hidden" value="${sysRole.roleFlow}">
                                        <input name="wsId" type="hidden" value="${sysRole.wsId}">
                                    </td>
                                    <td>
                                        ${sysRole.roleName}
                                    </td>
                                </tr>
                                <c:set var="total" value="0" scope="page"></c:set>
                                <tr>
                                    <td class="mation-nab" title="<font color='red'>基地</font>">
                                        基&nbsp;&nbsp;&nbsp;&nbsp;地
                                    </td>
                                    <td>
                                        <c:forEach items="${sysOrgs}" var="org" varStatus="status">
                                            <c:set var="total" value="${total+1 }" scope="page"></c:set>
                                            <span style="width: 220px;display:block; float:left; color: ;"><input
                                                    id="${org.orgFlow}" name="orgFlow" type="radio"
                                                    value="${org.orgFlow}" class="validate[required]"
                                                <c:if test="${not empty sysRoleAuthGx && sysRoleAuthGx.orgFlow eq org.orgFlow}">checked</c:if>
                                            >
											&#12288;<label for="${org.orgFlow}">${org.orgName}
                                            </label>&#12288;
											</span>
                                            <c:if test="${((total) mod 3)==0 }">
                                                <br>
                                            </c:if>
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="mation-nab" title="<font color='red'>派送学校</font>">
                                        派&nbsp;送&nbsp;学&nbsp;校
                                    </td>
                                    <td>
                                        <c:forEach items="${sysDicts}" var="dict" varStatus="status">
                                                <c:set var="total1" value="${total1+1 }" scope="page"></c:set>
                                                <span style="width: 220px;display:block; float:left;"><input
                                                        id="${dict.dictName}" name="dictName" type="checkbox"
                                                        value="${dict.dictName}" class="validate[required]"
<%--                                                        <c:if test="${dict.dictName eq school}">checked</c:if>--%>
                                                >
												&#12288;<label for="${dict.dictName}">${dict.dictName}
                                                    </label>&#12288;
															</span>
                                                <c:if test="${((total1) mod 3)==0 }">
                                                    <br>
                                                </c:if>
                                        </c:forEach>

                                    </td>
                                </tr>
                                <c:set var="total" value="0" scope="page"></c:set>
                            </table>
                            <div class="button" style="width: 800px;">
                                <input class="search" type="button" value="保&#12288;存"
                                       onclick="doSave();"/>
                                <input class="search" type="button" value="关&#12288;闭"
                                       onclick="jboxClose();"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>