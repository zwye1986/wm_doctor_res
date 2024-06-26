<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function subOpt(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var url = "<s:url value='/osca/base/auditAppoint'/>";
            jboxPost(url, $("#myForm").serialize(), function(resp){
                window.parent.frames['mainIframe'].toPage1(1);
                jboxClose();
            }, null, true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="jsonData" value='${param.jsonData}'>
            <div style="margin-top:20px;">请填写未通过原因：</div>
            <div style="margin:10px 0px 0px 40px"><input name="reason" class="validate[required]" type="text"></div>
            <div style="margin-top:20px;text-align:center;">
                <input class="search" onclick="subOpt()" value="提交" type="button">
                <input class="search" onclick="jboxClose()" value="关闭" type="button">
            </div>
        </form>
    </div>
</div>
</body>
</html>