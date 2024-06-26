<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function teaChangePower(obj){
            var powerFlag = $(obj).is(':checked')?"Y":"N";
            jboxPost("<s:url value='/gzykdx/school/changePower?target=teacher&powerFlag='/>"+powerFlag+"&targetFlow="+$(obj).val(), null, function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
        function search(){
            $("#myForm").submit();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm" action="<s:url value='/gzykdx/school/searchByName'/>" method="post">
            <div style="padding:10px 0px;line-height:30px;">
                <span></span>姓名：
                <input type="text" name="userName" value="${param.userName}" onchange="search()">
                <input type="hidden" name="orgFlow" value="${param.orgFlow}">
            </div>
            <table class="xllist">
                <tr>
                    <c:forEach items="${dataList}" var="info" varStatus="i">
                        <td style="border:0px;line-height:40px;max-width:20%;">
                            <input type="checkbox" value="${info.userFlow}" ${info.reportingAuthority eq 'Y'?'checked':''} onchange="teaChangePower(this)">${info.userName}
                        </td>
                        <c:if test="${i.count % 5 eq 0}"></tr><tr></c:if>
                    </c:forEach>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>