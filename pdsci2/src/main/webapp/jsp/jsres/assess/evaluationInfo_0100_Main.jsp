<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css"
      href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
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

    function Schedule(fileRoute, fileName) {
        if (fileName == "1A") {
            $("#1A").addClass("tab_select");
            $("#2A").removeClass("tab_select");
            $("#3A").removeClass("tab_select");
            $("#4A").removeClass("tab_select");
            $("#5A").removeClass("tab_select");
            $("#6A").removeClass("tab_select");
            $("#7A").removeClass("tab_select");
            $("#8A").removeClass("tab_select");
        }
        if (fileName == "2A") {
            $("#1A").removeClass("tab_select");
            $("#2A").addClass("tab_select");
            $("#3A").removeClass("tab_select");
            $("#4A").removeClass("tab_select");
            $("#5A").removeClass("tab_select");
            $("#6A").removeClass("tab_select");
            $("#7A").removeClass("tab_select");
            $("#8A").removeClass("tab_select");
        }
        if (fileName == "3A") {
            $("#1A").removeClass("tab_select");
            $("#2A").removeClass("tab_select");
            $("#3A").addClass("tab_select");
            $("#4A").removeClass("tab_select");
            $("#5A").removeClass("tab_select");
            $("#6A").removeClass("tab_select");
            $("#7A").removeClass("tab_select");
            $("#8A").removeClass("tab_select");
        }
        if (fileName == "4A") {
            $("#1A").removeClass("tab_select");
            $("#2A").removeClass("tab_select");
            $("#3A").removeClass("tab_select");
            $("#4A").addClass("tab_select");
            $("#5A").removeClass("tab_select");
            $("#6A").removeClass("tab_select");
            $("#7A").removeClass("tab_select");
            $("#8A").removeClass("tab_select");
        }
        if (fileName == "5A") {
            $("#1A").removeClass("tab_select");
            $("#2A").removeClass("tab_select");
            $("#3A").removeClass("tab_select");
            $("#4A").removeClass("tab_select");
            $("#5A").addClass("tab_select");
            $("#6A").removeClass("tab_select");
            $("#7A").removeClass("tab_select");
            $("#8A").removeClass("tab_select");
        }
        if (fileName == "6A") {
            $("#1A").removeClass("tab_select");
            $("#2A").removeClass("tab_select");
            $("#3A").removeClass("tab_select");
            $("#4A").removeClass("tab_select");
            $("#5A").removeClass("tab_select");
            $("#6A").addClass("tab_select");
            $("#7A").removeClass("tab_select");
            $("#8A").removeClass("tab_select");
        }
        if (fileName == "7A") {
            $("#1A").removeClass("tab_select");
            $("#2A").removeClass("tab_select");
            $("#3A").removeClass("tab_select");
            $("#4A").removeClass("tab_select");
            $("#5A").removeClass("tab_select");
            $("#6A").removeClass("tab_select");
            $("#7A").addClass("tab_select");
            $("#8A").removeClass("tab_select");
        }
        if (fileName == "8A") {
            $("#1A").removeClass("tab_select");
            $("#2A").removeClass("tab_select");
            $("#3A").removeClass("tab_select");
            $("#4A").removeClass("tab_select");
            $("#5A").removeClass("tab_select");
            $("#6A").removeClass("tab_select");
            $("#7A").removeClass("tab_select");
            $("#8A").addClass("tab_select");
        }
        var url = "<s:url value ='/jsres/supervisio/Schedule'/>?orgName=${orgName}&editFlag=${editFlag}&isRead=${isRead}&userFlow=${userFlow}&roleFlag=${roleFlag}&orgFlow=${orgFlow}&speId=${speId}&subjectFlow=${subjectFlow}&fileRoute=" + fileRoute;
        jboxLoad("hosContent", url, false);
    }
</script>
<div class="main_hd">
    <div class="title_tab" id="toptab">
        <ul>
            <li class="tab" id="1A" style="cursor: pointer;">
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_0100_01_01','1A');">附表1</a>
            </li>
            <li class="tab" id="2A" style="cursor: pointer;">
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_0100_01_02','2A');">附表2</a>
            </li>
            <li class="tab" id="3A" style="cursor: pointer;">
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_0100_01_03','3A');">附表3</a>
            </li>
            <li class="tab" id="4A" style="cursor: pointer;">
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_0100_01_04','4A');">附表4</a>
            </li>
            <li class="tab" id="5A" style="cursor: pointer;">
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_0100_01_05','5A');">附表5</a>
            </li>
            <li class="tab" id="6A" style="cursor: pointer;">
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_0100_01_06','6A');">附表6</a>
            </li>
            <li class="tab" id="7A" style="cursor: pointer;">
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_0100_01_07','7A');">附表7</a>
            </li>
            <li class="tab" id="8A" style="cursor: pointer;">
                <a href="javascript:void(0)" onclick="Schedule('evaluationInfo_0100_01_08','8A');">附表8</a>
            </li>
        </ul>
    </div>
</div>
<div id="hosContent">
</div>

