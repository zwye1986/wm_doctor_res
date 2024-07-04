<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
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
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script>
        function saveForm() {
            if ($("#editform").validationEngine("validate")) {
                autoValue($("#editform"),"autoValue");
                jboxSubmit($("#editform"), "<s:url value='/res/rec/saveRegistryForm'/>", function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        parentRefresh();
                        jboxClose();
                    }
                }, function (resp) {
                    jboxTip("${GlobalConstant.SAVE_FAIL}");
                }, true);
            }
        }
        function parentRefresh() {
            window.parent.frames['mainIframe'].window.loadProcess();
        }
        function addAccessories() {
            $("#accessoriesTb").append($("#accessoriesTemplate tr:eq(0)").clone());
        }
        function delAccessories() {
            var checkboxs = $("input[name='accessoriesIds']:checked");
            if (checkboxs.length == 0) {
                jboxTip("请勾选要删除的！");
                return false;
            }
            jboxConfirm("确认删除?", function () {
                var trs = $('#accessoriesTb').find(':checkbox:checked');
                $.each(trs, function (i, n) {
                    $(n).parent('td').parent("tr").remove();
                });
            });
        }
        function moveTr(obj) {
            jboxConfirm("确认删除？", function () {
                var flowinput = $(obj).parents("td").find("input[name='accessoriesFlow']");
                var nameinput = $(obj).parents("td").find("input[name='accessoriesName']");
                flowinput.remove();
                nameinput.remove()
                var tr = obj.parentNode;
                $("#accessoriesIsRe").val("Y");
                $(tr).after("<li><input type='file' class='validate[required]' name='accessories'/></li>");
                tr.remove();
                var trParent = obj.parentNode.parentNode;

            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="editform" enctype="multipart/form-data" method="post">
                <input type="hidden" name="formFileName" value="${formFileName}"/>
                <input type="hidden" name="schDeptFlow" value="${param.schDeptFlow}"/>
                <input type="hidden" name="recFlow" value="${rec.recFlow}"/>
                <input type="hidden" name="processFlow" value="${currRegProcess.processFlow}"/>
                <input type="hidden" name="operUserFlow" value="${param.operUserFlow}">
                <table class="basic" width="100%">
                    <tr>
                        <th style="text-align: left;">&#12288;社区重点人群的中医养生保健方案</th>
                    </tr>
                    <tr>
                        <td style="padding-top: 10px;padding-bottom: 10px;">
                            <c:if test="${!empty doctor}">
                                <textarea style="width: 92%;min-height: 200px;" name="content">${formDataMap['content']}</textarea>
                            </c:if>
                            <c:if test="${empty doctor}">
                                <label>${formDataMap['content']}</label>
                            </c:if>
                        </td>
                    </tr>
                </table>
                <table id="accessoriesTable" class="basic" width="100%" style="margin-top: 20px;">
                    <tr>
                        <th colspan="6" style="text-align: left;">&#12288;附件信息
                            <c:if test="${!empty doctor}">
                                <span style="float: right;margin-right: 10px;margin-bottom: 5px;">
                                    <img class='opBtn' title='删除' style='cursor: pointer;'
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick='delAccessories();'/>
                                    <img class="opBtn" title="新增"
                                         src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                         style="cursor: pointer;" onclick="addAccessories();"/>
                                </span>
                            </c:if>

                        </th>
                    </tr>
                    <tbody id="accessoriesTb">
                    <c:set var="fileFlows" value="${pdfn:split(formDataMap['accessories_Flow'],',')}"/>
                    <c:set var="fileNames" value="${pdfn:split(formDataMap['accessories'],',')}"/>
                    <c:if test="${not empty formDataMap['accessories_Flow']}">
                        <c:forEach var="fileFlow" items="${fileFlows}" varStatus="status">
                            <tr>
                                <td width="4%">
                                    <input type="checkbox" name="accessoriesIds"/>
                                </td>
                                <td>
                                    <input type="text" id="accessoriesIsRe" value="Y" name="accessoriesIsRe"
                                           style="display: none;"/>
                                    <input type="text" value="${fileFlows[status.index]}" name="accessoriesFlow"
                                           style="display: none;"/>
                                    <input type="text" value="${fileNames[status.index]}" name="accessoriesName"
                                           style="display: none;"/>
                                    <ul>
                                        <li>
                                            <a href="<s:url value='/res/rec/fileDown'/>?fileFlow=${fileFlows[status.index]}">【${fileNames[status.index]}】</a><img
                                                class='opBtn' title='删除' style='cursor: pointer;'
                                                src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                                onclick='moveTr(this);'></img>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    </tbody>
                </table>
                <p align="center">
                    <c:if test="${!(rec.auditStatusId eq recStatusEnumTeacherAuditY.id) && !param.isRead}">
                        <input class="search" type="button" value="保&#12288;存" onclick="saveForm();"/>
                    </c:if>
                    <input class="search" type="button" value="关&#12288;闭" onclick="jboxClose();"/>
                </p>
            </form>
        </div>
        <div style="display: none">
            <!-- 模板 -->
            <table id="accessoriesTemplate">
                <tr>
                    <td width="4%">
                        <input type="checkbox" name="accessoriesIds"/>
                    </td>
                    <td>
                        <input type="file" name="accessories"/>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>
