<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="queryFont" value="true"/>
        <jsp:param name="consult" value="true"/>
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function doClose() {
            top.jboxClose();
        }
    </script>
</head>
<body>
<div class="mainright" style="overflow:auto;height:450px">
    <form id="consultInfoForm" method="post">
        <div class="content xq-content" style="height: auto">
            <div class="flex">
                <div class="fs12 c65">提出人：</div>
                <span class="fs14">${consultInfo.consultQuestionerName}</span>
            </div>
            <div class="cons-lit" style="line-height:20px">
                <c:if test="${consultInfo.consultQuestionRoleId eq 'Doctor'}">
                    <div>年级:</div>
                    <span>${consultInfo.consultQuestionerGrade}</span>
                    <div>培训基地:</div>
                    <span>${consultInfo.consultQuestionerBaseName}</span>
                </c:if>
                <div>提出时间:</div>
                <span>${consultInfo.consultQuestionCreateTime}</span>
            </div>
            <div>
                <p style="width: 100%;"> ${consultInfo.consultQuestion} </p>
            </div>
            <c:if test="${consultInfo.isAnswer eq 'Y'}">
                <div align="left" class="bordbox" style="width: 100%;background: #F5F5F5;margin: 16px 0px 24px;padding: 8px 12px;">
                        ${consultInfo.consultAnswer}
                </div>
            </c:if>

            <div class="flex align-c just-c xq-close">
                <input type="button" class="btn_green" onclick="doClose()" value="关&#12288;闭"/>
            </div>
        </div>

    </form>
</div>
</body>
</html>