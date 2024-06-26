<jsp:include page="/jsp/hbzy/htmlhead-jszy.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_ui_combobox" value="false"/>
    <jsp:param name="jquery_ui_sortable" value="false"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .grid th {
        white-space: nowrap;
        border: 1px solid #e7e7eb;
        min-width: 60px;
    }

    .grid td {
        white-space: nowrap;
        border: 1px solid #e7e7eb;
    }

</style>
<script type="text/javascript">
    $(document).ready(function () {
        heightFiexed();
        heightTh();
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
        <c:forEach items="${datas}" var="data">
        if ("${data}" == "${type.id}") {
            $("#" + "${data}").attr("checked", "checked");
        }
        </c:forEach>
        <c:if test="${empty datas}">
        $("#" + "${type.id}").attr("checked", "checked");
        </c:if>
        </c:forEach>
        <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
            <c:forEach items="${trainTypeIds}" var="data">
            if ("${data}" == "${type.id}") {
                $("#" + "${data}").attr("checked", "checked");
            }
            if("${data}" == "${jszyTrainCategoryEnumChineseMedicine.id}"){
                $(".ChineseMedicineTd").show();
            }
            if("${data}" == "${jszyTrainCategoryEnumTCMGeneral.id}"){
                $(".TCMGeneralTd").show();
            }
            if("${data}" == "${jszyTrainCategoryEnumTCMAssiGeneral.id}"){
                $(".TCMAssiGeneralTd").show();
            }
            </c:forEach>
        <c:if test="${empty trainTypeIds}">
            $("#ChineseMedicine").attr("checked", "checked");
            $("#TCMGeneral").attr("checked", "checked");
            $(".ChineseMedicineTd").show();
            $(".TCMGeneralTd").show();
        </c:if>
        </c:forEach>

        changeOrgNameHeight();
    });

    function changeOrgNameHeight() {
        if (!$("#ChineseMedicine").prop("checked")
                & $("#TCMGeneral").prop("checked")) {
            $(".orgNameTh").css("min-height","81");
            $(".orgNameTh").css("height","81");
        }
        if (!$("#ChineseMedicine").prop("checked")
                & !$("#TCMGeneral").prop("checked")
                & $("#TCMAssiGeneral").prop("checked")) {
            $(".orgNameTh").css("min-height","40");
            $(".orgNameTh").css("height","40");
        }
    }

    function toPage(page) {
        var sessionNumber = $("#sessionNumber").val();
        if (sessionNumber == "") {
            jboxTip("年级不能为空！");
            return;
        }
        var hasCheckedType = "N"
        <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            hasCheckedType="Y";
        }
        </c:forEach>
        if(hasCheckedType == "N"){
            jboxTip("请选择培训专业！");
            return false;
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
        var currentPage = "";
        if (!page || page != undefined) {
            currentPage = page;
        }
        if (page == undefined || page == "") {
            currentPage = 1;
        }
        $("#currentPage").val(currentPage);
        jboxPostLoad("content", "<s:url value='/hbzy/statistic/zlxytj3'/>", $("#searchForm").serialize(), true);
    }

    function exportExcel() {
        var sessionNumber = $("#sessionNumber").val();
        if (sessionNumber == "") {
            jboxTip("年级不能为空！");
            return;
        }
        var hasCheckedType = "N"
        <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            hasCheckedType="Y";
        }
        </c:forEach>
        if(hasCheckedType == "N"){
            jboxTip("请选择培训专业！");
            return false;
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
        var url = "<s:url value='/hbzy/statistic/exportZlxytj3'/>";
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
    function showSubList(obj) {
        var subSize = $(obj).attr("subSize");
        var cityId = $(obj).attr("cityId");
        var orgFlow = $(obj).attr("orgFlow");
        var rowspan = $("#" + cityId).attr("rowspan") || 1;
        if (parseInt(subSize) > 0) {
            if ($("." + orgFlow).is(":hidden")) {
                rowspan = parseInt(rowspan) + parseInt(subSize);
                $("." + orgFlow).show();
            } else {
                rowspan = parseInt(rowspan) - parseInt(subSize);
                $("." + orgFlow).hide();
            }
            $("#" + cityId).attr("rowspan", rowspan);
        }
    }
    function tableFixed(div){
        $("#dateFixed,#topTitle").css("top",$(div).scrollTop()+"px");
        $("#deptFixed,#topTitle").css("left",$(div).scrollLeft()+"px");
    }
    function heightFiexed(){
        if($("#ChineseMedicine").prop("checked")){
            var height = document.body.clientHeight-110;
// 	$("#tableContext").css("height",height+"px");
            //修正高度
            var toFixedTd = $(".toFiexdDept");
            $(".fixedBy").each(function(i){
                $(toFixedTd[i]).height($(this).height());
            });
        }
    }
    function heightTh(){
        var height = document.body.clientHeight-110;
// 	$("#tableContext").css("height",height+"px");
        //修正高度
        var toFixedTd = $(".toFiexd");
        $(".fixed").each(function(i){
            $(toFixedTd[i]).height($(this).height());
        });
    }
    onresize = function(){
        heightFiexed();
        heightTh();
    };
</script>
<div class="main_hd">
    <h2 class="underline">招录统计-专业</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search clearfix">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage" value=""/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">年&#12288;&#12288;级：</td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber"
                               value="${empty sessionNumber ? param.sessionNumber : sessionNumber}"
                               class="input" readonly="readonly"/>
                    </td>
                    <td class="td_left">培训专业：</td>
                    <td colspan="2">
                        <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}" value="${type.id}" class="trainType"
                                          name="trainTypeIds" />${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <label><input type="checkbox" id="all" value="all" name="all" checked
                                      onclick="checkAll(this);"/>全部&nbsp;</label>
                        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"
                                          name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td id="func" colspan="5">
                        <input class="btn_brown" style="margin-left: 0px;" type="button" value="查&#12288;询"
                               onclick="toPage();"/>&nbsp;
                        <input class="btn_brown" style="margin-left: 0px;" type="button" value="导&#12288;出"
                               onclick="exportExcel();"/>&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div style="padding-bottom: 20px;">
        <div class="search_table">
            <!--表格  -->
            <div id="tableContext" style="width:948px;max-height: 600px; margin-top: 10px;overflow: auto;margin-left: 0px;" onscroll="tableFixed(this);">
                <!--第一次 -->
                <div id="dateFixed" style="height: 0px;overflow: visible;position: relative;">
                    <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: auto;">
                        <tr>
                            <th class="toFiexd orgNameTh" rowspan="3" style="min-width: 141px;min-height: 122px;height: 122px;">基地名称</th>
                            <th class="fixed ChineseMedicineTd" style="display: none;" colspan="15">中医</th>
                            <th class="fixed TCMGeneralTd" style="display: none;" colspan="2">中医全科</th>
                            <th class="fixed TCMAssiGeneralTd" style="display: none;" rowspan="3">中医助理全科</th>
                            <th class="fixed" rowspan="3">总计</th>
                        </tr>
                        <tr>
                            <th class="fixed ChineseMedicineTd" colspan="7" style="display: none;">中医学</th>
                            <th class="fixed ChineseMedicineTd" colspan="8" style="display: none;">中西医结合（中西医临床医学）</th>
                            <th class="fixed TCMGeneralTd" rowspan="2" style="display: none;">中医学</th>
                            <th class="fixed TCMGeneralTd" rowspan="2" style="display: none;">中西医结合（中西医临床医学）</th>
                        </tr>
                        <tr>

                            <c:forEach begin="1" end="2" step="1" varStatus="num">
                                <c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="secondTrainingSpe">
                                    <th class="fixed ChineseMedicineTd" style="display: none;">${secondTrainingSpe.dictName}</th>
                                </c:forEach>
                            </c:forEach>
                            <th class="fixed ChineseMedicineTd" style="display: none;">无</th>
                        </tr>
                    </table>
                </div>
                <!--第二次 -->
                <div id="deptFixed" style="height: 0px;overflow: visible;position: relative;">
                    <table class="grid" style="min-width: 141px;max-width: 141px;border-right: 0px">
                        <tr>
                            <th class="orgNameTh" style="min-width: 141px;min-height: 122px;height: 122px;">基地名称</th>
                        </tr>
                        <c:forEach items="${orgs}" var="org">
                            <tr><td style="background: white;" class="toFiexdDept" title="${org.orgName}">${pdfn:cutString(org.orgName,8,true,3)}</td></tr>
                        </c:forEach>
                    </table>
                </div>
                <!--第三次  -->
                <div id="topTitle" style="width: 0px;overflow: visible;position: relative;height: 0px;">
                    <table class="grid" style="width: auto;border-right: 0px" >
                        <tr>
                            <th class="orgNameTh" style="min-width: 141px;min-height: 123px;height: 123px;">基地名称</th>
                        </tr>
                    </table>
                </div>
                <table border="0" cellpadding="0" cellspacing="0" class="grid" style="width: auto;">
                    <tr>
                        <th class="orgNameTh" rowspan="3" style="min-width: 141px;">基地名称</th>
                        <th colspan="15" class="ChineseMedicineTd" style="display: none;">中医</th>
                        <th colspan="2" class="TCMGeneralTd" style="display: none;">中医全科</th>
                        <th rowspan="3" class="TCMAssiGeneralTd" style="display: none;">中医助理全科</th>
                        <th rowspan="3">总计</th>
                    </tr>
                    <tr>
                        <th colspan="7" class="ChineseMedicineTd" style="display: none;">中医学</th>
                        <th colspan="8" class="ChineseMedicineTd" style="display: none;">中西医结合（中西医临床医学）</th>
                        <th rowspan="2" class="TCMGeneralTd" style="display: none;">中医学</th>
                        <th rowspan="2" class="TCMGeneralTd" style="display: none;">中西医结合（中西医临床医学）</th>
                    </tr>
                    <tr>
                        <c:forEach begin="1" end="2" step="1" varStatus="num">
                            <c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="secondTrainingSpe">
                                <th class="ChineseMedicineTd" style="display: none;">${secondTrainingSpe.dictName}</th>
                            </c:forEach>
                        </c:forEach>
                        <th class="ChineseMedicineTd" style="display: none;">无</th>
                    </tr>
                    <c:forEach items="${orgs}" var="org">
                        <c:set var="sum" value="0"></c:set>
                        <tr>
                            <td title="${org.orgName}">${pdfn:cutString(org.orgName,8,true,3)}</td>
                            <c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="secondDict">
                                <td class="ChineseMedicineTd" style="display: none;">
                                    <c:set var="key"
                                           value="${org.orgFlow}${jszyTrainCategoryEnumChineseMedicine.id}3501${secondDict.dictId}"></c:set>
                                        ${empty countInfoMap[key] ? 0:countInfoMap[key]}
                                    <c:set var="sum" value="${sum + countInfoMap[key]}"></c:set>
                                </td>
                            </c:forEach>
                            <c:forEach items="${dictTypeEnumSecondTrainingSpeList}" var="secondDict">
                                <td class="ChineseMedicineTd" style="display: none;">
                                    <c:set var="key"
                                           value="${org.orgFlow}${jszyTrainCategoryEnumChineseMedicine.id}3502${secondDict.dictId}"></c:set>
                                        ${empty countInfoMap[key] ? 0:countInfoMap[key]}
                                    <c:set var="sum" value="${sum + countInfoMap[key]}"></c:set>
                                </td>
                            </c:forEach>
                            <td class="ChineseMedicineTd" style="display: none;">
                                <c:set var="wukey"
                                       value="${org.orgFlow}${jszyTrainCategoryEnumChineseMedicine.id}3502wu"></c:set>
                                    ${empty countInfoMap[wukey] ? 0:countInfoMap[wukey]}
                                <c:set var="sum" value="${sum + countInfoMap[wukey]}"></c:set>
                            </td>
                            <c:set var="key"
                                   value="${org.orgFlow}${jszyTrainCategoryEnumTCMGeneral.id}3601"></c:set>
                            <c:set var="TCMGeneral3601Sum" value="${empty countInfoMap[key] ? 0:countInfoMap[key]}"></c:set>
                            <c:set var="sum" value="${sum + countInfoMap[key]}"></c:set>
                            <td class="TCMGeneralTd" style="display: none;">${TCMGeneral3601Sum}</td>
                            <c:set var="key"
                                   value="${org.orgFlow}${jszyTrainCategoryEnumTCMGeneral.id}3602"></c:set>
                            <c:set var="TCMGeneral3602Sum" value="${empty countInfoMap[key] ? 0:countInfoMap[key]}"></c:set>
                            <c:set var="sum" value="${sum + countInfoMap[key]}"></c:set>
                            <td class="TCMGeneralTd" style="display: none;">${TCMGeneral3602Sum}</td>

                            <td class="TCMAssiGeneralTd" style="display:none;">
                                <c:set var="TCMAssiGeneralkey"
                                       value="${org.orgFlow}TCMAssiGeneral"></c:set>
                                    ${empty countInfoMap[TCMAssiGeneralkey] ? 0:countInfoMap[TCMAssiGeneralkey]}
                                <c:set var="sum" value="${sum + countInfoMap[TCMAssiGeneralkey]}"></c:set>
                            </td>
                            <td>
                                ${empty sum ? 0:sum}
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(orgs)}" scope="request"></c:set>
            <pd:pagination-jszy toPage="toPage"/>
        </div>
    </div>
</div>