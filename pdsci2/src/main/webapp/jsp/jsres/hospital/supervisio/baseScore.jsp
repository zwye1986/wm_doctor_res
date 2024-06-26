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

    function goScore(speId, orgFlow, userFlow, subjectFlow, devTime, closedTime, subjectYear,subjectActivitiFlows,isLocalManage) {
        var url = "<s:url value='/jsres/supervisio/searchPlanScore?speId='/>" + speId + "&roleFlag=${roleFlag}&orgFlow=" + orgFlow +
            "&userFlow=" + userFlow + "&subjectFlow=" + subjectFlow + "&subjectYear=" + subjectYear+"&subjectActivitiFlows="+subjectActivitiFlows+"&isLocalManage="+isLocalManage;
        jboxLoad("hosContent", url, false);

    }

    function loadInfo(userFlow,manageUserFlow){
        var url="<s:url value='/jsres/supervisio/searchPlanInfo'/>?subjectFlow=${subjectFlow}&speId=${speId}&roleFlag=${roleFlag}&userFlow="+userFlow
            +"&subjectActivitiFlows=${subjectActivitiFlows}"+"&manageUserFlow="+manageUserFlow;
        jboxLoad("hosContent", url, false);
    }
</script>
<div class="main_hd">
    <div class="title_tab" id="toptab">
        <ul>
            <c:if test="${roleFlag eq 'baseExpert'}">
                <li class="tab" cusTrigger="goScore('${speId}','${orgFlow}','${userFlow}','${subjectFlow}','${openTime}','${closedTime}','${subjectYear}','${subjectActivitiFlows}','');" style="cursor: pointer;">
                    <a>专业表</a>
                </li>
            </c:if>
            <c:if test="${roleFlag ne 'baseExpert'}">
                <li class="tab" cusTrigger="goScore('${speId}','${orgFlow}','','${subjectFlow}','${openTime}','${closedTime}','${subjectYear}','${subjectActivitiFlows}','Y');" style="cursor: pointer;">
                    <a>管理表</a>
                </li>
            </c:if>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="hosContent" >
    </div>
</div>
