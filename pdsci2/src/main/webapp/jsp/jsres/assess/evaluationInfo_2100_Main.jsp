<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css"
      href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript">
    $(document).ready(function () {
        $("li").click(function () {
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

    function Schedule(fileRoute, fileName) {
        if (fileName == "1A") {
            $("#1A").addClass("tab_select");
            $("#2A").removeClass("tab_select");
            $("#3A").removeClass("tab_select");
        }
        if (fileName == "2A") {
            $("#1A").removeClass("tab_select");
            $("#2A").addClass("tab_select");
            $("#3A").removeClass("tab_select");
        }
        if (fileName == "3A") {
            $("#1A").removeClass("tab_select");
            $("#2A").removeClass("tab_select");
            $("#3A").addClass("tab_select");
        }
        var url = "<s:url value ='/jsres/supervisio/Schedule'/>?orgName=${orgName}&editFlag=${editFlag}&isRead=${isRead}&userFlow=${userFlow}&roleFlag=${roleFlag}&orgFlow=${orgFlow}&speId=${speId}&subjectFlow=${subjectFlow}&fileRoute=" + fileRoute;
        jboxLoad("hosContent", url, false);
    }
</script>
<div class="main_hd">
    <div class="title_tab" id="toptab">
        <ul>
            <li class="tab" id="1A" style="cursor: pointer;">
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_2100_03','1A');">附表3-1</a>
            </li>
            <li class="tab" id="2A" style="cursor: pointer;">
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_2100_04','2A');">附表3-2</a>
            </li>
            <li class="tab" id="3A" style="cursor: pointer;">
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_2100_05','3A');">附表3-3</a>
            </li>
        </ul>
    </div>
</div>
<div id="hosContent">
</div>

