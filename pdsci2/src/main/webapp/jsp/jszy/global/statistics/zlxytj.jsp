<jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_scrollTo" value="false"/>
    <jsp:param name="jquery_jcallout" value="false"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fullcalendar" value="false"/>
    <jsp:param name="jquery_fngantt" value="false"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_iealert" value="false"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red;
    }

    .grid td {
        border: 1px solid #e7e7eb;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
        <c:forEach items="${trainTypeIds}" var="data">
        if ("${data}" == "${type.id}") {
            $("#" + "${data}").attr("checked", "checked");
        }
        </c:forEach>
        <c:if test="${empty trainTypeIds}">
        $("#ChineseMedicine").attr("checked", "checked");
        $("#TCMGeneral").attr("checked", "checked");
        </c:if>
        </c:forEach>
        // 当枚举选项个数与勾选个数相同时，勾中全部选项
        if ("${fn:length(trainYears)}" == "${fn:length(jszyResTrainYearEnumList)}") {
            $("#trainYearAll").attr("checked", "checked");
        }

        <c:forEach items="${jszyResTrainYearEnumList}" var="trainYear">
        <c:forEach items="${trainYears}" var="data">
        if ("${data}" == "${trainYear.id}") {
            $("#" + "${data}").attr("checked", "checked");
        }
        </c:forEach>
        <c:if test="${empty trainYears}">
        $("#" + "${trainYear.id}").attr("checked", "checked");
        </c:if>
        </c:forEach>
    });
    function toPage() {
        jboxPostLoad("content", "<s:url value='/jszy/statistic/zlxytj'/>", $("#searchForm").serialize(), true);
    }
    function exportExcel() {
        var url = "<s:url value='/jszy/statistic/exportZlxytj'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>
<div class="main_hd">
    <h2 class="underline">招录统计-地区</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <table class="searchTable">
                <tr>
                    <td class="td_left">年&#12288;&#12288;级：</td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}"
                               class="input" readonly="readonly"/>
                    </td>
                    <td class="td_left">培训专业：</td>
                    <td colspan="2">
                        <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}" value="${type.id}" class="trainType"
                                          name="trainTypeIds" />${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td class="td_left">培养年限：</td>
                    <td colspan="2">
                        <c:forEach items="${jszyResTrainYearEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}" value="${type.id}" class="trainYear"
                                          name="trainYears" onclick="changeAllTrainYearBox();"/>${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                </tr>
                <tr>

                    <td id="func" colspan="3">
                        <input class="btn_brown" style="margin-left: 0px;" type="button" value="查&#12288;询"
                               onclick="toPage();"/>&nbsp;
                        <input class="btn_brown" style="margin-left: 0px;" type="button" value="导&#12288;出"
                               onclick="exportExcel();"/>&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="doctorListZi">
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th>地市名称</th>
                    <th>年级</th>
                    <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                        <th>${type.name}</th>
                    </c:forEach>
                    <th>小计</th>
                    <th>合计</th>
                </tr>
                <c:set var="xjsum" value="0"></c:set>
                <c:set var="hjsum" value="0"></c:set>
                <c:forEach items="${citys}" var="city">
                    <c:set var="sessionNumbers" value="${citySessionMap[city.cityId]}"></c:set>
                    <c:set var="rowspan" value="${fn:length(sessionNumbers)}"></c:set>
                    <c:if test="${not empty sessionNumbers}">
                        <c:forEach items="${sessionNumbers}" var="sessionNumber" varStatus="s">
                            <c:if test="${s.first}">
                                <tr>
                                    <td rowspan="${rowspan}">
                                            ${city.cityName}
                                    </td>
                                    <td>
                                            ${sessionNumber}
                                    </td>
                                    <c:set var="sum" value="0"></c:set>
                                    <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                                        <c:set var="key" value="${city.cityId}${sessionNumber}${type.id}"></c:set>
                                        <c:set var="sum" value="${sum+citySessionNumMap[key]}"></c:set>
                                        <%--<td>${empty citySessionNumMap[key]}${key}</td>--%>
                                        <td>${empty citySessionNumMap[key]?0:citySessionNumMap[key]}</td>
                                    </c:forEach>
                                    <td>
                                            ${sum}
                                        <c:set var="xjsum" value="${xjsum+sum}"></c:set>
                                    </td>
                                    <td rowspan="${rowspan}">
                                            ${cityNumMap[city.cityId]}
                                        <c:set var="hjsum" value="${hjsum+cityNumMap[city.cityId]}"></c:set>
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${!s.first}">
                                <tr>
                                    <td>
                                            ${sessionNumber}
                                    </td>
                                    <c:set var="sum" value="0"></c:set>
                                    <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                                        <c:set var="key" value="${city.cityId}${sessionNumber}${type.id}"></c:set>
                                        <c:set var="sum" value="${sum+citySessionNumMap[key]}"></c:set>
                                        <td>${empty citySessionNumMap[key]?0:citySessionNumMap[key]}</td>
                                    </c:forEach>
                                    <td>
                                            ${sum}
                                        <c:set var="xjsum" value="${xjsum+sum}"></c:set>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </c:if>
                </c:forEach>
                <c:if test="${not empty citys}">
                    <tr>
                        <td>合计</td>
                        <td></td>
                        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                            <td>${empty typeNumMap[type.id]?0:typeNumMap[type.id]}</td>
                        </c:forEach>
                        <td>${xjsum}</td>
                        <td>${hjsum}</td>
                    </tr>
                </c:if>
                <c:if test="${empty citys}">
                    <tr>
                        <td colspan="7">无记录！</td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
</div>