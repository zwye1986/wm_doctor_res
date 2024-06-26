<script type="text/javascript">
    $(document).ready(function () {

        <c:if test="${not empty datas}">
        $("li").click(function () {
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
        });
        $('li').first().addClass("tab_select");
        $(".tab_select").click();
        </c:if>
    });
    function showCertificate(recordFlow) {
        var url = "<s:url value='/jszy/graduationManager/showCertificate'/>?roleId=doctor&recordFlow=" + recordFlow;
        jboxLoad("div_table", url, true);
    }
    function docQuery(recordFlow) {
        var url = "<s:url value='/jszy/graduationManager/docQuery'/>?roleId=doctor&recordFlow=" + recordFlow;
        jboxLoad("div_table", url, true);
    }
</script>
<c:if test="${not empty datas}">

    <div class="main_hd">
        <h2>证书信息查询</h2>
        <div class="title_tab">
            <ul>
                <c:forEach items="${datas}" var="data">
                    <li class="tab" onclick="docQuery('${data.recordFlow}');"><a>${data.certificateNumber}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <div id="div_table">

    </div>
</c:if>
<c:if test="${empty datas}">
    <div class="main_hd">
        <h2>证书信息查询</h2>
    </div>
    <div id="div_table">
        <img src="<s:url value='/jsp/jszy/images/notHave.png'/>"></img>
    </div>
</c:if>

