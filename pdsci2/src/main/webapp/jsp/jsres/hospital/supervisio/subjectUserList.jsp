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
            $("#currentPage").val("${param.currentPage}");
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

        function saveSelectUser(obj) {
            var userFlow2 = $(obj).val();
            var userName = $("#" + userFlow2 + "_userName").val();
            var checkedVal = $(obj).attr("checked");
            var identification=$("#identification").val();
            if ("checked" == checkedVal) {
                if (identification=='expert' || ${suAoth =='Y'}){
                    var html = '<input readonly hidden id="' + userFlow2 + '" name="userFlow" itemId="userFlow1"   value="' + userFlow2 + '"></input>' +
                        '<input readonly name="userFlowName" id="' + userFlow2 + 'name" style="width: 93px;height: 30px" value="' + userName + '"></input>' +
                        '<a id="del' + userFlow2 + '" name="delUserFlow" style="margin-right: 40px;" href=\'#\' onclick=\'delParam("'+userFlow2+'")\'><img alt="删除" src="<s:url value="/css/skin/LightBlue/images/del3.png"/>"></a>';
                    window.parent.frames["jbox-message-iframe"].$("#subjectUserDiv").append(html);
                }else if (identification=='management'){
                    var html = '<input readonly hidden id="' + userFlow2 + '" name="userFlow" itemId="userFlow2"   value="' + userFlow2 + '"></input>' +
                        '<input readonly name="userFlowName" id="' + userFlow2 + 'name" style="width: 93px;height: 30px" value="' + userName + '"></input>' +
                        '<a id="del' + userFlow2 + '" name="delUserFlow" style="margin-right: 40px;" href=\'#\' onclick=\'delParam("'+userFlow2+'")\'><img alt="删除" src="<s:url value="/css/skin/LightBlue/images/del3.png"/>"></a>';
                    window.parent.frames["jbox-message-iframe"].$("#subjectUserDivment").append(html);
                }

            } else {
                window.parent.frames["jbox-message-iframe"].$("#" + userFlow2).remove();
                window.parent.frames["jbox-message-iframe"].$("#" + userFlow2 + "name").remove();
                window.parent.frames["jbox-message-iframe"].$("#del" + userFlow2).remove();
            }
            jboxTip("操作成功");
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="div_table" style="overflow: auto;max-height: 470px;margin-left: -50px;margin-right: -45px;">
        <div id="doctorListZi">
            <input type="hidden" name="identification" id="identification" value="${identification}">
            <table class="base_info" style="width: 100%;margin: 10px 0px;">
                <c:if test="${empty userList}">
                    <tr>
                        <th style="text-align: center;">专家姓名</th>
                        <th style="text-align: center;">专家分类</th>
                        <th style="text-align: center;">专业</th>
                        <th style="text-align: center;">电话号码</th>
                        <th style="text-align: center;">工作单位</th>
                    </tr>
                    <tr>
                        <td colspan="5" style="text-align: center;">暂无督导人员信息</td>
                    </tr>
                </c:if>

                <c:if test="${not empty userList}">
                    <tr>
                        <th style="text-align: center;">专家姓名</th>
                        <th style="text-align: center;">专家分类</th>
                        <th style="text-align: center;">专业</th>
                        <th style="text-align: center;">电话号码</th>
                    </tr>
                    <c:forEach items="${userList}" var="user" varStatus="status">
                        <tr>
                            <td style="text-align: left;padding-left: 20px;line-height: 40px;">
                                <c:set var="key" value="${user.userFlow}"/>
                                <input type="hidden" id="${user.userFlow}_userName" name="userName"
                                       value="${user.userName}"/>

                                <label style="display: inline-block; padding-left: 10px; cursor: pointer;">
                                    <input type="checkbox" id="${key}" name="userFlow" value="${user.userFlow}"
                                           onclick="saveSelectUser(this)"
                                           style="vertical-align: middle; cursor: pointer;margin-right: 41px"
                                    />&nbsp;&nbsp;&nbsp;&nbsp;${user.userName}
                                </label>
                            </td>
                            <td style="text-align: center"><span style="margin-left: -9px">${user.userLevelName}</span></td>
                            <td style="text-align: center;width: 200px"><span style="margin-left: -9px">${user.resTrainingSpeName}</span></td>
                            <td style="text-align: center"><span style="margin-left: -9px">${user.userPhone}</span></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
        </div>
        <div style="display: none" id="addUserDiv"></div>
    </div>
</div>
<div class="page" style="text-align: center">
    <c:set var="pageView" value="${pdfn:getPageView(userList)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>
</body>
</html>