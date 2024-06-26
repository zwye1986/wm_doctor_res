
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER{background-color: #eee;}
    .cur{color:red;}
</style>
<script type="text/javascript">
    $(document).ready(function(){
        toPage(1);
    });
    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi","<s:url value='/jszy/base/activityQuery/list'/>?roleFlag=${param.roleFlag}&isNew=${param.isNew}&isEval=${param.isEval}",$("#searchForm").serialize(),false);
    }
</script>
<div class="main_bd" id="div_table_0" style="margin-top: 5px;">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" id="currentPage" name="currentPage"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">
                        活动名称：
                    </td>
                    <td>
                        <input type="text" name="activityName" value="" class="input" />
                    </td>
                    <td class="td_left">
                        主&nbsp;讲&nbsp;人：
                    </td>
                    <td>
                        <input type="text" name="userName" value="" class="input" />
                    </td>
                    <td class="td_left">
                        活动形式：
                    </td>
                    <td>
                        <select name="activityTypeId" class="select">
                            <option value="">全部</option>
                            <c:forEach items="${activityTypeEnumList}" var="a">
                                <option value="${a.id}" >${a.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_left">
                        科&#12288;&#12288;室：
                    </td>
                    <td>
                        <select name="deptFlow" class="select">
                            <option value="">全部</option>
                            <c:forEach items="${depts}" var="dept">
                                <option value="${dept.deptFlow}" >${dept.deptName}<c:if test="${dept.orgFlow ne doctor.orgFlow}">(${dept.orgName})</c:if>
                                </option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">
                        开始时间：
                    </td>
                    <td>
                        <input type="text" id="startDate" name="startTime" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </td>
                    <td class="td_left">
                        结束时间：
                    </td>
                    <td>
                        <input type="text" id="endDate" name="endTime" value="" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})">
                    </td>
                    <td id="func" colspan="3">
                        &nbsp;<input class="btn_green" style="margin-left: 0px;" type="button" value="查&#12288;询" onclick="toPage(1);"/>&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="doctorListZi">
    </div>
</div>