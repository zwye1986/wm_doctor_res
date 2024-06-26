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
    function revokeUser(obj) {
        var userFlow = $(obj).val();
        var revokeUserFlow = $('#revokeUserFlow').val();
        if (revokeUserFlow!="" && revokeUserFlow!=undefined){
            revokeUserFlow=revokeUserFlow+","+userFlow;
        }else {
            revokeUserFlow=userFlow;
        }
        $("#revokeUserFlow").val(revokeUserFlow);
    }
    function revokeUserScore(subjectActivitiFlows,subjectFlow) {
        var revokeUserFlow = $('#revokeUserFlow').val();
        var url = "<s:url value='/jsres/supervisio/revokeUserScore'/>";
        var data = {
            "userFlow": revokeUserFlow,
            "subjectActivitiFlows":subjectActivitiFlows,
            "subjectFlow":subjectFlow
        };
        top.jboxPost(url, data, function (resp) {
            top.jboxTip(resp);
            top.jboxCloseMessager();
        }, null, false);
    }
    </script>
</head>
<body>
<div class="mainright">
        <div id="doctorListZi">
            <input type="hidden"  id="revokeUserFlow" >
            <table class="base_info" style="width: 670px;margin: 10px 0px;">
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
                                       onclick="revokeUser(this)"
                                       style="vertical-align: middle; cursor: pointer;margin-right: 41px"
                                />&nbsp;&nbsp;&nbsp;&nbsp;${user.userName}
                            </label>
                        </td>
                        <td style="text-align: center"><span style="margin-left: -9px">${user.userLevelName}</span></td>
                        <td style="text-align: center;width: 200px"><span style="margin-left: -9px">${user.resTrainingSpeName}</span></td>
                        <td style="text-align: center"><span style="margin-left: -9px">${user.userPhone}</span></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

</div>
<input class="btn_green" style="margin-top: 125px;margin-left: 235px" type="button" value="确&#12288;定" onclick="revokeUserScore('${subjectActivitiFlows}','${subjectFlow}');"/>&#12288;
<input class="btn_green"style="margin-top: 125px"  type="button" value="取&#12288;消" onclick="top.jboxCloseMessager();"/>&#12288;
</body>
</html>