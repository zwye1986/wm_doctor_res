<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>

<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<style type="text/css">
    .indexNum + div{
        z-index: 99;
        position: relative!important;
        top:0!important;
        left:0!important;
    }
</style>
<script type="text/javascript">
    $(document).ready(function(){
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        <c:forEach items="${jsResDocTypeEnumList}" var="type">
            <c:forEach items="${datas}" var="data">
            if("${data}"=="${type.id}"){
                $("#"+"${data}").attr("checked","checked");
            }
            </c:forEach>
            <c:if test="${empty datas}">
                $("#"+"${type.id}").attr("checked","checked");
            </c:if>
        </c:forEach>

        toPage(1);
    });

    function toPage(page) {
        if($(".docType:checked").length==0) {
            jboxTip("请选择人员类型！");
            return false;
        }
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("contentDiv","<s:url value='/jsres/statistic/doctorDataListAcc'/>",$("#searchForm").serialize(),false);
    }

    function exportDoc(){
        var url = "<s:url value='/jsres/statistic/expDoctorDataListAcc'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"),url);
    }

</script>
<div class="main_hd">
    <h2 class="underline">
        学员填写量统计
    </h2>
</div>
<div class="main_bd" id="div_table_0" >
    <div class="div_search">
        <form id="searchForm" method="post">
            <input id="currentPage" type="hidden" name="currentPage"/>
            <input id="roleFlag" type="hidden" name="roleFlag" value ="${roleFlag}"/>
            <table class="searchTable">
                <c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
                    <tr>
                        <td class="td_left">学员姓名：</td>
                        <td><input type="text" name="userName" value="${param.userName}" class="input"/></td>
                        <td class="td_left">证&ensp;件&ensp;号：</td>
                        <td><input type="text" name="idNo" style="width: 122px" value="${param.idNo}" class="input"/></td>
                        <td class="td_left">专&#12288;&#12288;业：</td>
                        <td>
                            <select name="speId" id="speId" class="select">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td><input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input indexNum" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                       <td colspan="4">
                           <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                           <input class="btn_green" type="button" value="导&#12288;出" onclick="exportDoc();"/>
                       </td>
                    </tr>
                </c:if>
                <c:if test="${roleFlag ne GlobalConstant.USER_LIST_LOCAL}">
                    <tr>
                        <td class="td_left">基&#12288;&#12288;地：</td>
                        <td>
                            <select name="orgFlow" class="select">
                                <option value="">请选择</option>
                                <c:forEach items="${orgs}" var="org">
                                    <option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="td_left">学员姓名：</td>
                        <td><input type="text" name="userName" value="${param.userName}" class="input"/></td>
                        <td class="td_left">证&ensp;件&ensp;号：</td>
                        <td><input type="text" name="idNo" style="width: 122px" value="${param.idNo}" class="input"/></td>
                        <td class="td_left">专&#12288;&#12288;业：</td>
                        <td>
                            <select name="speId" class="select">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input indexNum" readonly="readonly" style="width: 122px"/>
                        </td>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                        <td colspan="2">
                            <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                            <input class="btn_green" type="button" value="导&#12288;出" onclick="exportDoc();"/>
                        </td>
                    </tr>
                </c:if>
            </table>
        </form>
    </div>
    <div id="contentDiv" style="max-width: 950px">

    </div>
</div>