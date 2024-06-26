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

    function loadInfo(planFlow,type,userStatus){
        $("#chickLi").val(userStatus);
        var speId = $("#speId").val();
        var orgFlow = $("#orgFlow").val();
        var doctorName = $("#doctorName").val();
        var url="<s:url value='/jsres/phyAss/planApperList'/>?planFlow="+planFlow+"&type="+type
            +"&userStatus="+userStatus+"&speId="+speId+"&orgFlow="+orgFlow+"&doctorName="+doctorName;
        jboxLoad("hosContent", url, false);
    }

    function loadInfo2(planFlow,type) {
        var userStatus = $("#chickLi").val();
        loadInfo(planFlow,type,userStatus);
    }

    function expertInfo(planFlow,type) {
        var speId = $("#speId").val();
        var orgFlow = $("#orgFlow").val();
        var doctorName = $("#doctorName").val();
        var url="<s:url value='/jsres/phyAss/expertPlanApperList'/>?planFlow="+planFlow+"&type="+type
            +"&userStatus=affirm&speId="+speId+"&orgFlow="+orgFlow+"&doctorName="+doctorName;
        jboxTip("下载中…………");
        jboxSubmit($("#searchForm22"), url, null, null, false);
        jboxEndLoading();
    }
</script>
<div class="main_hd">
    <div class="title_tab" id="toptab" style="font-size: 14px">
        <ul>
            <c:if test="${type ne 'show'}">
                <li class="tab" cusTrigger="loadInfo('${planFlow}','${type}','toAffirm');" style="cursor: pointer;">
                    <a>待确认师资</a>
                </li>
            </c:if>

            <li class="tab" cusTrigger="loadInfo('${planFlow}','${type}','affirm');" style="cursor: pointer;">
                <a>已确认师资</a>
            </li>
            <c:if test="${type ne 'show'}">
                <li class="tab" cusTrigger="loadInfo('${planFlow}','${type}','del');" style="cursor: pointer;">
                    <a>已删除师资</a>
                </li>
            </c:if>

        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div>
        <form id="searchForm22"></form>
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage"/>
            <input type="hidden" id="chickLi" name="chickLi"/>
            <input type="hidden" id="planFlow" name="${planFlow}"/>
            <input type="hidden" id="userStatus" name="chickLi" value="affirm"/>
            <table class="searchTable" style="width: 97%;font-size:14px;border-collapse:separate; border-spacing:0px 10px;margin: auto">
                <tr>
                    <td class="td_left">
                        培训专业：
                        <select name="speId" id="speId" class="select" style="width: 200px;">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                <option value="${dict.dictId}" ${speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left">
                        基&#12288;&#12288;地：
                        <select name="orgFlow" id="orgFlow" class="select" style="width: 200px;">
                            <option value="">全部</option
                            <c:forEach items="${orgs}" var="org">
                                <option value="${org.orgFlow}" ${orgFlow eq org.orgFlow?'selected':''}
                                >${org.orgName}</option>
                            </c:forEach>
                        </select>
                    </td>

                    <td class="td_left">
                        姓&#12288;&#12288;名：
                        <input name="doctorName" id="doctorName" style="margin-left: 0px;width: 200px;height: 31px" placeholder="请输入姓名" value="${doctorName}" class="input">
                    </td>

                    <td class="td_left">
                        <input class="btn_green" type="button" value="查&#12288;询" style="margin-left: -20px"
                               onclick="loadInfo2('${planFlow}','${type}');"/>
                        <c:if test="${type eq 'show'}">
                            <input class="btn_green" type="button" value="导&#12288;出"
                                   onclick="expertInfo('${planFlow}','${type}');"/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="hosContent" >

    </div>
</div>
