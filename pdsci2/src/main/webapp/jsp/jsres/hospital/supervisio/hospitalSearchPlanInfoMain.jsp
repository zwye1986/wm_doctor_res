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

    function loadInfo(userFlow){
        var url="<s:url value='/jsres/supervisio/hospitalSchedule'/>?subjectFlow=${subjectFlow}&userFlow="+userFlow+"&speId=${speId}&fileRoute=${fileRoute}&edit=N&roleFlag=local";
        jboxLoad("hosContent", url, false);
    }

</script>
<div class="main_hd">
    <div class="title_tab" id="toptab">
        <ul>
            <c:if test="${not empty leaderOneFlow}">
                <li class="tab" cusTrigger="loadInfo('${leaderOneFlow}','','');" style="cursor: pointer;">
                    <a>${leaderOneName}评分<span style="color: red">${empty leaderOneScore ? "（未提交）" : ""}</span></a>
                </li>
            </c:if>
            <c:if test="${not empty leaderTwoFlow}">
                <li class="tab" cusTrigger="loadInfo('${leaderTwoFlow}','','');" style="cursor: pointer;">
                    <a>${leaderTwoName}评分<span style="color: red">${empty leaderTwoScore ? "（未提交）" : ""}</span></a>
                </li>
            </c:if>
            <c:if test="${not empty leaderThreeFlow}">
                <li class="tab" cusTrigger="loadInfo('${leaderThreeFlow}','','');" style="cursor: pointer;">
                    <a>${leaderThreeName}评分<span style="color: red">${empty leaderThreeScore ? "（未提交）" : ""}</span></a>
                </li>
            </c:if>
            <c:if test="${not empty leaderFourFlow}">
                <li class="tab" cusTrigger="loadInfo('${leaderFourFlow}','','');" style="cursor: pointer;">
                    <a>${leaderFourName}评分<span style="color: red">${empty leaderFourScore ? "（未提交）" : ""}</span></a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="hosContent" >
    </div>
</div>
