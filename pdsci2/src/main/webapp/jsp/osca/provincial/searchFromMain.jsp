<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript">
    $(document).ready(function(){
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
            eval($(this).attr("cusTrigger"));
        });
        if ("${param.liId}" != "") {
            $("#${param.liId}").addClass("tab_select");
        } else {
            $('li').first().addClass("tab_select");
        }
        $(".tab_select a").click();
    });

    function loadInfo(fromFlow){
        var url='<s:url value="/osca/provincial/fromInfo?fromFlow="/>'+fromFlow;
        jboxLoad("hosContent", url, false);
    }

    function exportFrom() {
        var url='<s:url value="/osca/provincial/exportProgrammeFrom?subjectFlow=${subjectFlow}"/>';
        jboxTip("导出中…………");
        jboxExp($("#searchForm"), url);
    }

</script>
<div class="main_hd">
    <div class="title_tab" id="toptab">
        <ul>
            <c:forEach items="${list}" var="s">
                <li class="tab" cusTrigger="loadInfo('${s.fromFlow}');" style="cursor: pointer;">
                    <a>${s.stationName}</a>
                </li>
            </c:forEach>
        </ul>

    </div>
    <c:if test="${ not empty list}">
        <input type="button" class="search" onclick="exportFrom();" style="margin-left: 94%;margin-top: -32px" value="导&#12288;出" />
    </c:if>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="hosContent" style="overflow: auto">
    </div>
</div>
