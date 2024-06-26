<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(document).ready(function () {
            toPage();
            var inputUserList = window.parent.frames["jbox-message-iframe"].$("input[name='userFlow']");
            if (inputUserList.length > 0) {
                for (var i = 0; i < inputUserList.length; i++) {
                    <c:forEach items="${userList}" var="user">
                    if ("${user.userFlow}" == inputUserList[i].value) {
                        $("#${user.userFlow}").attr("checked", "checked");
                    }
                    </c:forEach>
                }
            }
            <c:forEach items="${userFlow}" var="user">
            if (${not empty oldUserList}) {
                <c:forEach items="${oldUserList}" var="oldUser">
                if ("${user.userFlow}" == "${oldUser.userFlow}") {
                    $("#${user.userFlow}").attr("checked", true);
                }
                </c:forEach>
            }
            </c:forEach>
        });

        function saveSubjectUser() {
            var checkLen = $(":checkbox[name='userFlow']:checked").length;
            if (checkLen == 0) {
                jboxTip("请勾选督导确认！");
                return;
            }
            var userFlowLst = "";
            $(":checkbox[name='userFlow']:checked").each(function (i) {
                if (i != 0) {
                    userFlowLst += ",";
                }
                userFlowLst += this.value;
            });
            var url = "<s:url value='/jsres/supervisio/saveSubjectUser'/>?subjectFlow=${param.subjectFlow}&userFlowList=" + userFlowLst;
            jboxPost(url, null, function (resp) {
                jboxTip(resp);
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.toPage(1);
                    jboxClose();
                }
            }, null, false);
        }

        function toPage(page) {
            $("#currentPage").val(page);
            var serialize = $("#searchForm").serialize();
            jboxStartLoading();
            jboxLoad("doctorListZi", "<s:url value='/jsres/supervisio/searchSubjectUser?'/>"+ serialize, false);
            jboxEndLoading();
        }

    </script>
</head>
<body>
<div class="mainright">
    <div class="div_table">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage"/>
            <input type="hidden" id="identification" name="identification" value="${identification}"/>
            <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
                <tr>
                    <td class="td_left">
                        <nobr>专家姓名：</nobr>
                    </td>
                    <td>
                        <input type="text" name="userName" value="${param.userName}" class="input"
                               style="width: 150px;margin-left: 0px;"/>
                    </td>
                    <td class="td_left">
                        <nobr >专家分类：</nobr>
                    </td>
                    <td>
                        <c:if test="${suAoth eq 'Y'}">
                            <select name="userLevelId" id="userLevelId" class="select" style="width: 106px;margin-right: 75px;">
                                <option value="baseExpert">专业专家</option>
                            </select>
                        </c:if>
                        <c:if test="${suAoth ne 'Y'}">
                            <select name="userLevelId" id="userLevelId" class="select" style="width: 106px;margin-right: 75px;">
                                <c:if test="${identification =='expert'}">
                                    <option value="expertLeader" ${param.userLevelId eq 'expertLeader'?'selected':''}>专业专家</option>
                                </c:if>
                                <c:if test="${identification=='management'}">
                                    <option value="management" ${param.userLevelId eq 'management'?'selected':''}>管理专家</option>
                                </c:if>
                            </select>
                        </c:if>
                    </td>

                </tr>
                <tr>
                    <td class="td_left">
                        <nobr>专业：</nobr>
                    </td>
                    <td>
                        <select name="trainingSpeId" id="trainingSpeId" class="select" style="width: 156px;">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                <option value="${dict.dictId}" ${param.trainingSpeId eq dict.dictId?'selected':''}
                                        <c:if test="${'3700' eq dict.dictId}">style="display: none" </c:if>
                                        <c:if test="${'3500' eq dict.dictId}">style="display: none" </c:if>
                                >${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td colspan="8">
                        <input class="btn_green" style="margin-left: 102px" type="button" value="查&#12288;询" onclick="toPage(1);"/>&#12288;
                    </td>
                </tr>
            </table>
        </form>
        <div id="doctorListZi">

        </div>
    </div>
</div>
</body>
</html>