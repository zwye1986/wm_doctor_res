<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
        .boxHome .item:HOVER {
            background-color: #eee;
        }
    </style>
    <script type="text/javascript">
        function doclose() {
            jboxClose();
        }

        function save() {

            if (false == $("#myform").validationEngine("validate")) {
                return;
            }
            var reason = $("#targetName").val();
            if (!reason) {
                jboxTip("请输入指标名称！");
                return false;
            }
            jboxConfirm("确认保存？", function () {
                jboxPost("<s:url value='/res/activityTarget/saveAdd'/>",
                    $("#myform").serialize(),
                    function (resp) {
                        if ('${GlobalConstant.SAVE_SUCCESSED}' == resp) {
                            window.parent.toPage(1);
                            jboxClose();
                        }
                    }, null, true);
            });
        }
    </script>
</head>
<body>
<div class="div_table">
    <form id="myform" style="position: relative;" method="post">
        <input name="targetType" type="hidden" value="${param.targetType}"/>
        <input name="targetFlow" id="targetFlow" type="hidden" value="${target.targetFlow}"/>
        <table border="0" cellpadding="0" cellspacing="0" style=" width:100%;text-align: center;border:0px;"
               class="grid">
            <colgroup>
                <col width="25%"/>
                <col width="75%"/>
            </colgroup>
            <tbody>
            <c:if test="${param.targetType eq 'JX'}">
                <tr style=" width:100%;text-align: center;">
                    <td style="border: 0px solid;text-align: right;">
                        <font color="red">*</font>活动形式：
                    </td>
                    <td style="border: 0px solid;text-align: left;">
                        <select name="activityTypeId" class="select validate[required]"
                                style="width: 100%;margin: 0 5px;">
                            <option value=""></option>
                            <c:forEach items="${dictTypeEnumActivityTypeList}" var="activityType">
                                <option value="${activityType.dictId}" ${target.activityTypeId eq activityType.dictId ? 'selected' : ''}>${activityType.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </c:if>
            <tr style=" width:100%;text-align: center;">
                <td style="border: 0px solid;text-align: right;">
                    <font color="red">*</font>指标名称：
                </td>
                <td style="border: 0px solid;text-align: left;">
                    <input name="targetName" type="text" id="targetName" value="${target.targetName}"
                           style="width: 98%;" class="input validate[required]"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
    <div class="button">
        <input type="button" class="btn_green" onclick="save();" value="保&#12288;存"/>&#12288;
        <input type="button" id="submitBtn" class="btn_green" onclick="doclose();" value="关&#12288;闭"/>
    </div>
</div>
</body>
</html>
