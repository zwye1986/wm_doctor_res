    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(document).ready(function(){
            jboxInfo("该用户无此权限,如有疑问,请联系管理员...");
            setTimeout(function(){window.parent.location.reload()},2000);
        })
    </script>