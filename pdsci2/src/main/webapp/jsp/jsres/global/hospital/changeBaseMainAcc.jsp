<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
<script type="text/javascript">
    $(document).ready(function(){
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
        });
        if ("${param.liId}" != "") {
            $("#${param.liId}").addClass("tab_select");
        } else {
            $('li').first().addClass("tab_select");
        }
        $(".tab_select").click();
    });

    function changeBaseAudit(){
        <%--var url = "<s:url value='/jsres/manage/auditBaseChange'/>";--%>
        var url = "<s:url value='/jsres/manage/auditBaseChangeMainAcc'/>";
        jboxLoad("doctorContent", url, true);
    }

    function changeBaseMain(){
        <%--var url = "<s:url value='/jsres/manage/changeBase'/>";--%>
        var url = "<s:url value='/jsres/manage/changeBaseMainNewAcc'/>";
        currentJboxLoadNoData("doctorContent", url, true);
    }
</script>

<div class="main_hd">
    <h2>基地变更管理</h2>
    <div class="title_tab" style="margin-top: 0;">
        <ul>
            <li class="tab_select" onclick="changeBaseAudit()"><a>基地变更审核</a></li>
            <li class="tab" onclick="changeBaseMain()"><a>基地变更查询</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="doctorContent">
    </div>
</div>


