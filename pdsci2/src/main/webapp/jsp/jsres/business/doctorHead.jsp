<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="font" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
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

    function searchUserList(type){
        $("input[class='docType']").prop("checked","checked");
        var url = "<s:url value='/jsres/business/userList?checkStatusId='/>" + type;
        jboxPostLoad("doctorContent", url, $("#searchForm").serialize(), true);
    }

</script>

<div class="main_hd">
    <h2>学员权限审核</h2>
    <div class="title_tab">
        <ul>
            <li class="tab_select" onclick="searchUserList('Passing')"><a>待审核</a></li>
            <li class="tab" onclick="searchUserList('Passed')"><a>审核通过</a></li>
            <li class="tab" onclick="searchUserList('UnPassed')"><a>审核不通过</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="doctorContent">
    </div>
</div>


