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

       <c:forEach items="${sessionNumbers}" var="sessionNumber">
            <c:forEach items="${sessionDatas}" var="data">
            if ("${data}" == "${sessionNumber}") {
                $("#" + "${data}").attr("checked", "checked");
            }
            </c:forEach>
            <c:if test="${empty sessionDatas}">
                 $("#sessionNumber").val("");
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

        var orgFlows = "${orgFlows}".replace('[','').replace(']','').split(',');
        var jointOrgFlows = "${jointOrgFlows}".replace('[','').replace(']','').split(',');
        $.each(orgFlows, function (index, value, array) {
            var sum = $("#" +value.trim()+"SumInput").val();
            $("#" +value.trim()+"Sum").html(sum);
        });
        $.each(jointOrgFlows, function (index, value, array) {
            var sum = $("#" +value.trim()+"SumInput").val();
            $("#" +value.trim()+"Sum").html(sum);
        });
    });
    function toPage() {
        var trainTypeId = $("#trainTypeId").val();
        if (trainTypeId == "") {
            jboxTip("培训类别不能为空！");
            return;
        }
        var data = "";
        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            data += "&datas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        if (data == "") {
//            jboxTip("请选择人员类型！");
//            return false;
        }
        jboxPostLoad("content", "<s:url value='/hbzy/statistic/zlxytj2'/>", $("#searchForm").serialize(), true);
    }

    function exportExcel() {
        var trainTypeId = $("#trainTypeId").val();
        if (trainTypeId == "") {
            jboxTip("培训类别不能为空！");
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
        var url = "<s:url value='/hbzy/statistic/exportZlxytj2'/>";
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
    function checkAllSession(obj) {
        var f = false;
        if ($(obj).attr("checked") == "checked") {
            f = true;
        }
        $(".sessionNumber").each(function () {
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
    function changeAllSessionBox() {
        debugger;
        if ($(".sessionNumber:checked").length == $(".sessionNumber").length) {
            $("#sessionAll").attr("checked", "checked");
        } else {
            $("#sessionAll").removeAttr("checked");
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
    function showSecondSpe(orgFlow, speId, sessionNumber) {
        var data="";
        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            data += "&datas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        var trainYears = "";
        <c:forEach items="${jszyResTrainYearEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            trainYears += "&trainYears=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        var url = "<s:url value='/hbzy/statistic/zltjSecondSpe'/>?orgFlow=" + orgFlow+"&speId="+speId+"&sessionNumber="+sessionNumber+data+trainYears;
        jboxStartLoading();
        jboxOpen(url, "二级专业统计",800, 200);
        jboxEndLoading();
    }
</script>
<div class="main_hd">
    <h2 class="underline">招录统计-基地</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <table class="searchTable">
                <tr>
                    <td class="td_left">年&#12288;&#12288;级：</td>
                   <%-- <td colspan="4">
                        <label><input type="checkbox" id="sessionAll" value="sessionAll" name="sessionAll" checked
                                      onclick="checkAllSession(this);"/>全部&nbsp;</label>
                        <c:forEach items="${sessionNumbers}" var="sessionNumber">
                            <label><input type="checkbox" id="${sessionNumber}" value="${sessionNumber}" class="sessionNumber"
                                          name="sessionDatas" onclick="changeAllSessionBox();"/>${sessionNumber}&nbsp;</label>
                        </c:forEach>
                    </td>--%>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionDatas"
                               value="${empty sessionDatas ? param.sessionNumber : sessionDatas[0]}"
                               class="input" readonly="readonly"/>
                    </td>
                    <%--<td class="td_left">城&#12288;&#12288;市：</td>--%>
                    <%--<td>--%>
                    <%--<select name="orgCityId" id="orgCityId" class="select">--%>
                    <%--<option value="" ${empty param.orgCityId?"selected":""}>全部</option>--%>
                    <%--<option value="320100" ${param.orgCityId eq 320100?"selected":""}>南京市</option>--%>
                    <%--<option value="320200" ${param.orgCityId eq 320200?"selected":""}>无锡市</option>--%>
                    <%--<option value="320300" ${param.orgCityId eq 320300?"selected":""}>徐州市</option>--%>
                    <%--<option value="320400" ${param.orgCityId eq 320400?"selected":""}>常州市</option>--%>
                    <%--<option value="320500" ${param.orgCityId eq 320500?"selected":""}>苏州市</option>--%>
                    <%--<option value="320600" ${param.orgCityId eq 320600?"selected":""}>南通市</option>--%>
                    <%--<option value="320700" ${param.orgCityId eq 320700?"selected":""}>连云港市</option>--%>
                    <%--<option value="320800" ${param.orgCityId eq 320800?"selected":""}>淮安市</option>--%>
                    <%--<option value="320900" ${param.orgCityId eq 320900?"selected":""}>盐城市</option>--%>
                    <%--<option value="321000" ${param.orgCityId eq 321000?"selected":""}>扬州市</option>--%>
                    <%--<option value="321100" ${param.orgCityId eq 321100?"selected":""}>镇江市</option>--%>
                    <%--<option value="321200" ${param.orgCityId eq 321200?"selected":""}>泰州市</option>--%>
                    <%--<option value="321300" ${param.orgCityId eq 321300?"selected":""}>宿迁市</option>--%>
                    <%--</select>--%>
                    <%--</td>--%>
                    <%-- <td class="td_left">培训专业：</td>
                     <td>
                         <select name="trainTypeId" id="trainTypeId" class="select">
                             <c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">
                                 <option value="${trainCategory.id}"
                                         <c:if test="${param.trainTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>
                             </c:forEach>
                         </select>
                     </td>--%>
                    <%--<td class="td_left">住院医师：</td>--%>
                    <%--<td>--%>
                    <%--<select name="trainTypeId" id="trainTypeId" class="select">--%>
                    <%--<option value="">全部</option>--%>
                    <%--<c:forEach items="${jszyTrainCategoryEnumList}" var="trainCategory">--%>
                    <%--<c:if test="${trainCategory.isView eq GlobalConstant.FLAG_Y}">--%>
                    <%--<option value="${trainCategory.id}"--%>
                    <%--<c:if test="${param.trainTypeId==trainCategory.id}">selected="selected"</c:if>>${trainCategory.name}</option>--%>
                    <%--</c:if>--%>
                    <%--</c:forEach>--%>
                    <%--</select>--%>
                    <%--</td>--%>
                    <td style="width: 110px;" colspan="2">
                        <label style="cursor: pointer; " id='jointOrg'>
                            <input type="checkbox"
                                   <c:if test="${param.joint eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                   name="joint" value="${GlobalConstant.FLAG_Y}"/>&nbsp;协同基地
                        </label>
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
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                       <%-- <label><input type="checkbox" id="all" value="all" name="all" checked
                                      onclick="checkAll(this);"/>全部&nbsp;</label>--%>
                        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}" value="${type.id}" class="docType"
                                          name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td id="func" colspan="2">
                        <input class="btn_brown" style="margin-left: 0px;" type="button" value="查&#12288;询"
                               onclick="toPage();"/>&nbsp;
                        <input class="btn_brown" style="margin-left: 0px;" type="button" value="导&#12288;出"
                               onclick="exportExcel();"/>&nbsp;
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="div_search">
        <div id="tableContext"style="width:900px; margin-top: 10px;margin-bottom: 10px;overflow: auto;margin-left: 0px;" >
            <div style="width: 0px; overflow: visible;   top: 0px; left: 0px;">
                <table border="0" cellpadding="0" cellspacing="0" class="grid" id="dataTable" >
                    <tr>
                        <th rowspan="2" style="min-width: 150px;border: 1px solid #e7e7eb;">基地名称</th>
                        <th rowspan="2" style="min-width: 50px;border: 1px solid #e7e7eb;">年级</th>
                        <th colspan="2" style="min-width: 50px;border: 1px solid #e7e7eb;">中医</th>
                        <th colspan="2" style="min-width: 50px;border: 1px solid #e7e7eb;">中医全科</th>
                        <th rowspan="2" style="min-width: 50px;border: 1px solid #e7e7eb;">中医助理全科</th>
                        <%--<th rowspan="2" style="min-width: 50px;border: 1px solid #e7e7eb;">小计</th>--%>
                        <th rowspan="2" style="min-width: 50px;border: 1px solid #e7e7eb;">合计</th>
                    </tr>
                    <tr>
                        <th style="min-width: 50px;border: 1px solid #e7e7eb;">中医学</th>
                        <th style="min-width: 150px;border: 1px solid #e7e7eb;">中西医结合<br>(中西医临床医学)</th>
                        <th style="min-width: 50px;border: 1px solid #e7e7eb;">中医学</th>
                        <th style="min-width: 150px;border: 1px solid #e7e7eb;">中西医结合<br>(中西医临床医学)</th>
                    </tr>
                    <c:set var="sum3501" value="0"></c:set>
                    <c:set var="sum3502" value="0"></c:set>
                    <c:set var="sum3601" value="0"></c:set>
                    <c:set var="sum3602" value="0"></c:set>
                    <c:set var="sum6000" value="0"></c:set>
                    <c:set var="zongji" value="0"></c:set>
                    <c:forEach items="${orgFlows}" var="orgFlow">
                        <c:set var="hejiSum" value="0"></c:set>
                        <c:forEach items="${sessionDatas}" var="sessionNumber" varStatus="s">
                            <c:set var="rowspan" value="${fn:length(sessionDatas)}"></c:set>
                            <c:set var="subSize" value="${fn:length(jointOrgMap.get(orgFlow))}"></c:set>
                            <c:if test="${s.first}">
                                <tr >
                                    <td <c:if test="${param.joint eq 'Y' and (not empty jointOrgMap.get(orgFlow))}">style="color:#459ae9;cursor: pointer;background-color: white;" onclick="showSubList(this);" </c:if>
                                        <c:if test="${param.joint ne 'Y'or empty jointOrgFlows}">style="background-color: white;" </c:if>
                                        class="fix"  subSize="${subSize}" rowspan="${rowspan}" orgFlow="${orgFlow}">
                                            ${orgNameMap[orgFlow]}
                                    </td>
                                    <td>${sessionNumber}</td>
                                        <c:set var="sum" value="0"></c:set>
                                        <c:forEach items="${subKeyList}" var="subKey">
                                            <c:set var="key" value="${orgFlow}${subKey}${sessionNumber}"></c:set>
                                            <c:set var="flow" value="${orgFlow}${subKey}"/>
                                            <c:if test="${orgSpeFlagMap[flow] eq GlobalConstant.FLAG_Y}">
                                                <c:set var="sum" value="${sum+cityOrgNumMap[key]}"></c:set>
                                                <c:choose>
                                                    <c:when test="${fn:contains(subKey,'3501')}">
                                                        <c:set var="sum3501" value="${sum3501+cityOrgNumMap[key]}"></c:set>
                                                    </c:when>
                                                    <c:when test="${fn:contains(subKey,'3502')}">
                                                        <c:set var="sum3502" value="${sum3502+cityOrgNumMap[key]}"></c:set>
                                                    </c:when>
                                                    <c:when test="${fn:contains(subKey,'3601')}">
                                                        <c:set var="sum3601" value="${sum3601+cityOrgNumMap[key]}"></c:set>
                                                    </c:when>
                                                    <c:when test="${fn:contains(subKey,'3602')}">
                                                        <c:set var="sum3602" value="${sum3602+cityOrgNumMap[key]}"></c:set>
                                                    </c:when>
                                                    <c:when test="${fn:contains(subKey,'6000')}">
                                                        <c:set var="sum6000" value="${sum6000+cityOrgNumMap[key]}"></c:set>
                                                    </c:when>
                                                </c:choose>
                                                <td
                                                        <c:if test="${(fn:contains(subKey,'3501') or fn:contains(subKey,'3502')) and !empty cityOrgNumMap[key]}">style="color:#459ae9;cursor: pointer;background-color: white;" onclick="showSecondSpe('${orgFlow}','${subKey}','${sessionNumber}');" </c:if>
                                                        <c:if test="${!(fn:contains(subKey,'3501') or fn:contains(subKey,'3502')) or empty cityOrgNumMap[key]}">style="background-color: white;" </c:if>
                                                >${empty cityOrgNumMap[key]?0:cityOrgNumMap[key]}</td>
                                            </c:if>
                                            <c:if test="${orgSpeFlagMap[flow] != GlobalConstant.FLAG_Y}">
                                                <td>--</td>
                                            </c:if>
                                        </c:forEach>
                                   <%-- <td>
                                        ${sum}
                                    </td>--%>
                                        <c:set var="hejiSum" value="${sum+hejiSum}"></c:set>
                                    <td rowspan="${rowspan}" id="${orgFlow}Sum">
                                        ${hejiSum}
                                    </td>
                                </tr>
                            </c:if>
                            <c:if test="${!s.first}">
                                <tr >
                                    <td>${sessionNumber}</td>
                                    <c:set var="sum" value="0"></c:set>
                                    <c:forEach items="${subKeyList}" var="subKey">
                                        <c:set var="key" value="${orgFlow}${subKey}${sessionNumber}"></c:set>
                                        <c:set var="flow" value="${orgFlow}${subKey}"/>
                                        <c:if test="${orgSpeFlagMap[flow] eq GlobalConstant.FLAG_Y}">
                                            <c:set var="sum" value="${sum+cityOrgNumMap[key]}"></c:set>
                                            <c:choose>
                                                <c:when test="${fn:contains(subKey,'3501')}">
                                                    <c:set var="sum3501" value="${sum3501+cityOrgNumMap[key]}"></c:set>
                                                </c:when>
                                                <c:when test="${fn:contains(subKey,'3502')}">
                                                    <c:set var="sum3502" value="${sum3502+cityOrgNumMap[key]}"></c:set>
                                                </c:when>
                                                <c:when test="${fn:contains(subKey,'3601')}">
                                                    <c:set var="sum3601" value="${sum3601+cityOrgNumMap[key]}"></c:set>
                                                </c:when>
                                                <c:when test="${fn:contains(subKey,'3602')}">
                                                    <c:set var="sum3602" value="${sum3602+cityOrgNumMap[key]}"></c:set>
                                                </c:when>
                                                <c:when test="${fn:contains(subKey,'6000')}">
                                                    <c:set var="sum6000" value="${sum6000+cityOrgNumMap[key]}"></c:set>
                                                </c:when>
                                            </c:choose>
                                            <td
                                                    <c:if test="${(fn:contains(subKey,'3501') or fn:contains(subKey,'3502')) and !empty cityOrgNumMap[key]}">style="color:#459ae9;cursor: pointer;background-color: white;" onclick="showSecondSpe('${orgFlow}','${subKey}','${sessionNumber}');" </c:if>
                                                    <c:if test="${!(fn:contains(subKey,'3501') or fn:contains(subKey,'3502')) or empty cityOrgNumMap[key]}">style="background-color: white;" </c:if>
                                            >${empty cityOrgNumMap[key]?0:cityOrgNumMap[key]}</td>
                                        </c:if>
                                        <c:if test="${orgSpeFlagMap[flow] != GlobalConstant.FLAG_Y}">
                                            <td>--</td>
                                        </c:if>
                                    </c:forEach>
                                   <%-- <td>
                                            ${sum}
                                    </td>--%>
                                        <c:set var="hejiSum" value="${sum+hejiSum}"></c:set>
                                </tr>
                            </c:if>
                            <c:if test="${s.last}">
                                <input type="text"  value="${hejiSum}" hidden id="${orgFlow}SumInput"/>
                            </c:if>
                        </c:forEach>
                        <c:forEach items="${jointOrgMap.get(orgFlow)}" var="jointOrgFlow">
                                <c:set var="hejiSum" value="0"></c:set>
                                <c:forEach items="${sessionDatas}" var="sessionNumber" varStatus="s">
                                    <c:if test="${s.first}">
                                        <tr >
                                            <th class="${orgFlow}" style="border: 1px solid #e7e7eb;font-weight: normal;display: none" rowspan="${rowspan}">
                                                    ${orgNameMap[jointOrgFlow]}
                                            </th>
                                            <th class="${orgFlow}" style="border: 1px solid #e7e7eb;font-weight: normal;display: none">${sessionNumber}</th>
                                            <c:set var="sum" value="0"></c:set>
                                            <c:forEach items="${subKeyList}" var="subKey">
                                                <c:set var="key" value="${jointOrgFlow}${subKey}${sessionNumber}"></c:set>
                                                <c:set var="flow" value="${jointOrgFlow}${subKey}"/>
                                                <c:if test="${orgSpeFlagMap[flow] eq GlobalConstant.FLAG_Y}">
                                                    <c:set var="sum" value="${sum+cityOrgNumMap[key]}"></c:set>
                                                    <th class="${orgFlow}"
                                                        <c:if test="${(fn:contains(subKey,'3501') or fn:contains(subKey,'3502')) and !empty cityOrgNumMap[key]}">style="border: 1px solid #e7e7eb;font-weight: normal;display: none;color:#459ae9;cursor: pointer;" onclick="showSecondSpe('${jointOrgFlow}','${subKey}','${sessionNumber}');" </c:if>
                                                        <c:if test="${!(fn:contains(subKey,'3501') or fn:contains(subKey,'3502')) or empty cityOrgNumMap[key]}">style="border: 1px solid #e7e7eb;font-weight: normal;display: none;" </c:if>
                                                    >${empty cityOrgNumMap[key]?0:cityOrgNumMap[key]}</th>
                                                </c:if>
                                                <c:if test="${orgSpeFlagMap[flow] != GlobalConstant.FLAG_Y}">
                                                    <th class="${orgFlow}" style="border: 1px solid #e7e7eb;font-weight: normal;display: none">--</th>
                                                </c:if>
                                            </c:forEach>
                                           <%-- <th class="${orgFlow}" style="border: 1px solid #e7e7eb;font-weight: normal;display: none">
                                                    ${sum}
                                            </th>--%>
                                                <c:set var="hejiSum" value="${sum+hejiSum}"></c:set>
                                            <th id="${jointOrgFlow}Sum"  class="${orgFlow}" style="border: 1px solid #e7e7eb;font-weight: normal;display: none" rowspan="${rowspan}">
                                                    ${hejiSum}
                                            </th>
                                        </tr>
                                    </c:if>
                                    <c:if test="${!s.first}">
                                        <tr class="${orgFlow}">
                                            <th class="${orgFlow}" style="border: 1px solid #e7e7eb;font-weight: normal;display: none">${sessionNumber}</th>
                                            <c:set var="sum" value="0"></c:set>
                                            <c:forEach items="${subKeyList}" var="subKey">
                                                <c:set var="key" value="${jointOrgFlow}${subKey}${sessionNumber}"></c:set>
                                                <c:set var="flow" value="${jointOrgFlow}${subKey}"/>
                                                <c:if test="${orgSpeFlagMap[flow] eq GlobalConstant.FLAG_Y}">
                                                    <c:set var="sum" value="${sum+cityOrgNumMap[key]}"></c:set>
                                                    <th class="${orgFlow}"
                                                        <c:if test="${(fn:contains(subKey,'3501') or fn:contains(subKey,'3502')) and !empty cityOrgNumMap[key]}">style="border: 1px solid #e7e7eb;font-weight: normal;display: none;color:#459ae9;cursor: pointer;" onclick="showSecondSpe('${jointOrgFlow}','${subKey}','${sessionNumber}');" </c:if>
                                                        <c:if test="${!(fn:contains(subKey,'3501') or fn:contains(subKey,'3502')) or empty cityOrgNumMap[key]}">style="border: 1px solid #e7e7eb;font-weight: normal;display: none;" </c:if>
                                                    >${empty cityOrgNumMap[key]?0:cityOrgNumMap[key]}</th>
                                                </c:if>
                                                <c:if test="${orgSpeFlagMap[flow] != GlobalConstant.FLAG_Y}">
                                                    <th class="${orgFlow}" style="border: 1px solid #e7e7eb;font-weight: normal;display: none">--</th>
                                                </c:if>
                                            </c:forEach>
                                          <%--  <th class="${orgFlow}" style="border: 1px solid #e7e7eb;font-weight: normal;display: none">
                                                    ${sum}
                                            </th>--%>
                                                <c:set var="hejiSum" value="${sum+hejiSum}"></c:set>
                                        </tr>
                                    </c:if>
                                    <c:if test="${s.last}">
                                        <input type="text"  value="${hejiSum}" hidden id="${jointOrgFlow}SumInput"/>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                    </c:forEach>

                    <tr>
                        <td colspan="2">合计</td>

                        <td>${sum3501}</td>
                        <td>${sum3502}</td>
                        <td>${sum3601}</td>
                        <td>${sum3602}</td>
                        <td>${sum6000}</td>
                        <td>${sum3501+sum3502+sum3601+sum3602+sum6000}</td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</div>