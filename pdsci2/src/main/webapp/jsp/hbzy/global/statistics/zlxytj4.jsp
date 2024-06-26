<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
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
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $('#sessionNumber').datepicker({
            startDate: "2017",
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        // 当枚举选项个数与勾选个数相同时，勾中全部选项
        if ("${fn:length(datas)}" == "${fn:length(jszyResDocTypeEnumList)}") {
            $("#all").attr("checked", "checked");
        }
        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
            <c:forEach items="${datas}" var="data">
                if ("${data}" == "${type.id}") {
                    $("#" + "${data}").attr("checked", "checked");
                }
            </c:forEach>
            <c:if test="${empty datas}">
                 $("#" + "${type.id}").attr("checked", "checked");
                 $("#all").attr("checked", "checked");
            </c:if>
        </c:forEach>
        init();
    });
    function init() {
        var catIdTds = $("td[catId]")
        var chineseMedicineSum = 0;
        var tCMGeneralSum = 0;
        var tCMAssiGeneralSum = 0;

        catIdTds.each(function (i, e) {
            var catId = $(e).attr("catId");
            var catIdCount = $(e).attr("catIdCount");
            if ("${jszyTrainCategoryEnumChineseMedicine}" == catId) {
                chineseMedicineSum += Number(catIdCount);
            }
            if ("${jszyTrainCategoryEnumTCMGeneral}" == catId) {
                tCMGeneralSum += Number(catIdCount);
            }
            if ("${jszyTrainCategoryEnumTCMAssiGeneral}" == catId) {
                tCMAssiGeneralSum += Number(catIdCount);
            }
        });
        $("#${jszyTrainCategoryEnumChineseMedicine}Sum").text(chineseMedicineSum);
        $("#${jszyTrainCategoryEnumTCMGeneral}Sum").text(tCMGeneralSum);
        $("#${jszyTrainCategoryEnumTCMAssiGeneral}Sum").text(tCMAssiGeneralSum);
        $("#zongji").text(chineseMedicineSum + tCMGeneralSum + tCMAssiGeneralSum);
    }
    function toPage(page) {
        var sessionNumber = $("#sessionNumber").val();
        if (sessionNumber == "") {
            jboxTip("年级不能为空！");
            return;
        }
        var data = "";
        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            data += "&datas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        if (data == "") {
            jboxTip("请选择人员类型！");
            return false;
        }
        var currentPage;
        if (page != undefined) {
            currentPage = page;
        }
        $("#currentPage").val(currentPage);
        jboxPostLoad("content", "<s:url value='/hbzy/statistic/zlxytj4'/>", $("#searchForm").serialize(), true);
    }

    function exportExcel() {
        var sessionNumber = $("#sessionNumber").val();
        if (sessionNumber == "") {
            jboxTip("年级不能为空！");
            return;
        }
        var url = "<s:url value='/hbzy/statistic/exportZlxytj4'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#searchForm"), url, null, null, false);
        jboxEndLoading();
    }
    function checkAll(obj) {
        var f = false;
        if ($(obj).attr("checked") == "checked") {
            f = true;
        }
        $(".docType").each(function () {
            if (f) {
                $(this).attr("checked", "checked");
            } else {
                $(this).removeAttr("checked");
            }

        });
    }
    function changeAllBox() {
        if ($(".docType:checked").length == $(".docType").length) {
            $("#all").attr("checked", "checked");
        } else {
            $("#all").removeAttr("checked");
        }
    }
</script>
<div class="main_hd">
    <h2 class="underline">招录统计-汇总</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage" value="${param.currentPage}"/>
            <table >
                <tr>
                    <td class="td_left">年&#12288;&#12288;级：</td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber"
                               value="${empty sessionNumber ? param.sessionNumber : sessionNumber}"
                               class="input" readonly="readonly"/>
                    </td>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <label><input type="checkbox" id="all" value="all" name="all"
                                      onclick="checkAll(this);"/>全部&nbsp;</label>
                        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"
                                          name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td colspan="2" id="func">
                        <input class="btn_brown" style="margin-left: 0px;" type="button" value="查&#12288;询"
                               onclick="toPage();"/>&nbsp;
                        <input class="btn_brown" style="margin-left: 0px;" type="button" value="导&#12288;出"
                        onclick="exportExcel();"/>&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="content">
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <colgroup>
                    <col width="40%"/>
                    <col width="15%"/>
                    <col width="15%"/>
                    <col width="15%"/>
                    <col width="15%"/>
                </colgroup>
                <tr>
                    <th>基地名称</th>
                    <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
                        <th>${type.name}</th>
                    </c:forEach>
                    <th>小计</th>
                </tr>
                <c:forEach items="${orgs}" var="org">
                    <c:set var="sum" value="0"></c:set>
                    <tr>
                        <td>${org.orgName}</td>
                        <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
                            <c:set var="key" value="${org.orgFlow}${type.id}"></c:set>
                            <td catId="${type.id}"
                                catIdCount="${empty countInfoMap[key] ? 0 : countInfoMap[key]}">${empty countInfoMap[key] ? 0 : countInfoMap[key]}</td>
                            <c:set var="sum" value="${sum + countInfoMap[key]}"></c:set>
                        </c:forEach>
                        <td>${sum}</td>
                    </tr>
                </c:forEach>
                <tr>
                    <td>合计</td>
                    <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
                        <td id="${type.id}Sum"></td>
                    </c:forEach>
                    <td id="zongji"></td>
                </tr>
            </table>
        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(orgs)}" scope="request"></c:set>
            <pd:pagination-jszy toPage="toPage"/>
        </div>
    </div>
</div>