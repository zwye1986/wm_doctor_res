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

    function loadInfo(userFlow,manageUserFlow,isLocalManage,baseExpert){
        var url="<s:url value='/jsres/supervisio/searchPlanInfo'/>?subjectFlow=${subjectFlow}&speId=${speId}&roleFlag=${roleFlag}&userFlow="+userFlow+"&suAoth=${suAoth}"
            +"&subjectActivitiFlows=${subjectActivitiFlows}"+"&manageUserFlow="+manageUserFlow+"&isLocalManage="+isLocalManage+"&baseExpert="+baseExpert;
        jboxLoad("hosContent", url, false);
    }

</script>
<div class="main_hd">
    <div class="title_tab" id="toptab">
        <ul>
            <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL ||  roleFlag eq 'management'}">
                <li class="tab" cusTrigger="loadInfo('','${manageUserFlow}','');" style="cursor: pointer;">
                    <a>评分总表</a>
                </li>
            </c:if>
            <c:if test="${roleFlag eq 'local'}">
                <li class="tab" cusTrigger="loadInfo('','','Y');" style="cursor: pointer;">
                    <a>管理表<span style="color: red">${baseManageSubmit ne 'N' ? "（未提交）" : ""}</span></a>
                </li>
                <li class="tab" cusTrigger="loadInfo('${userFlow}','','');"  style="cursor: pointer;">
                    <a>专业表<span style="color: red">${userSubmit ne 'N' ? "（未提交）" : ""}</span></a>
                </li>
            </c:if>
            <c:if test="${roleFlag eq GlobalConstant.USER_LIST_GLOBAL}">
                <li class="tab" cusTrigger="loadInfo('','','Y');" style="cursor: pointer;">
                    <a>基地自评-管理表<span style="color: red">${baseManageSubmit ne 'N' ? "（未提交）" : ""}</span></a>
                </li>
                <li class="tab" cusTrigger="loadInfo('${userFlow}','','','Y');"  style="cursor: pointer;">
                    <a>基地自评-专业表</a>
                </li>
            </c:if>
            <c:if test="${roleFlag eq 'baseExpert'}">
                <li class="tab" cusTrigger="loadInfo('${userFlow}','','');"  style="cursor: pointer;">
                    <a>专业表</a>
                </li>
            </c:if>
            <c:if test="${roleFlag ne 'baseExpert' and roleFlag ne 'local'}">
                <c:forEach items="${userList}" var="suser">
                    <li class="tab" cusTrigger="loadInfo('${suser.userFlow}','','');" style="cursor: pointer;">
                        <a>${suser.userName}评分<span style="color: red">${empty suser.evaluationDate ? "（未提交）" : ""}</span></a>
                    </li>
                </c:forEach>
            </c:if>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="hosContent" >
    </div>
</div>
