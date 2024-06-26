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
            startDate: "2015",
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

        getCityArea();
        searchOrgList();
        init();

    });
    function init() {
        var catIdTds = $("td[catId]")
        var chineseMedicineSum = 0;
        var tCMGeneralSum = 0;
        var tCMAssiGeneralSum = 0;
        var chineseMedicineChangesSum = 0;
        var tCMGeneralChangesSum = 0;
        var tCMAssiGeneralChangesSum = 0;

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
            if ("${jszyTrainCategoryEnumChineseMedicine}Changes" == catId) {
                chineseMedicineChangesSum += Number(catIdCount);
            }
            if ("${jszyTrainCategoryEnumTCMGeneral}Changes" == catId) {
                tCMGeneralChangesSum += Number(catIdCount);
            }
            if ("${jszyTrainCategoryEnumTCMAssiGeneral}Changes" == catId) {
                tCMAssiGeneralChangesSum += Number(catIdCount);
            }
        });
        $("#${jszyTrainCategoryEnumChineseMedicine}Sum").text(chineseMedicineSum);
        $("#${jszyTrainCategoryEnumTCMGeneral}Sum").text(tCMGeneralSum);
        $("#${jszyTrainCategoryEnumTCMAssiGeneral}Sum").text(tCMAssiGeneralSum);
        <%--$("#${jszyTrainCategoryEnumChineseMedicine}ChangesSum").text(chineseMedicineChangesSum);--%>
        <%--$("#${jszyTrainCategoryEnumTCMGeneral}ChangesSum").text(tCMGeneralChangesSum);--%>
        <%--$("#${jszyTrainCategoryEnumTCMAssiGeneral}ChangesSum").text(tCMAssiGeneralChangesSum);--%>
        $("#zongji").text(chineseMedicineSum + tCMGeneralSum + tCMAssiGeneralSum);
    }
    function toPage(page) {
        if(page!=undefined){
            $("#currentPage").val(page);
        }
        var currentPage = $("#currentPage").val();
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
        var trainYears = "";
        <c:forEach items="${jszyResTrainYearEnumList}" var="trainYear">
        if ($("#" + "${trainYear.id}").attr("checked")) {
            trainYears += "&trainYears=" + $("#" + "${trainYear.id}").val();
        }
        </c:forEach>
        if (trainYears == "") {
            jboxTip("请选择培养年限！");
            return false;
        }
        jboxPostLoad("content", "<s:url value='/jszy/doctorRecruit/zpxytj'/>", $("#searchForm").serialize(), true);
    }

    function exportExcel() {
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
        var trainYears = "";
        <c:forEach items="${jszyResTrainYearEnumList}" var="trainYear">
        if ($("#" + "${trainYear.id}").attr("checked")) {
            trainYears += "&trainYears=" + $("#" + "${trainYear.id}").val();
        }
        </c:forEach>
        if (trainYears == "") {
            jboxTip("请选择培养年限！");
            return false;
        }
        var url = "<s:url value='/jszy/doctorRecruit/exportZpxytj'/>";
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
    function checkAllTrainYear(obj) {
        var f = false;
        if ($(obj).attr("checked") == "checked") {
            f = true;
        }
        $(".trainYear").each(function () {
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
    function changeAllTrainYearBox() {
        if ($(".trainYear:checked").length == $(".trainYear").length) {
            $("#trainYearAll").attr("checked", "checked");
        } else {
            $("#trainYearAll").removeAttr("checked");
        }
    }
    function getCityArea(){
        var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
        var provIds = "320000";
        jboxGet(url,null, function(json) {
            // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
            var newJsonData=new Array();
            var j=0;
            for(var i=0;i<json.length;i++){
                if(provIds==json[i].v){
                    var citys=json[i].s;
                    for(var k=0;k<citys.length;k++){
                        newJsonData[j++]=citys[k];
                    }
                }
            }
            console.log("${cityId}");
            $.cxSelect.defaults.url = newJsonData;
            $.cxSelect.defaults.nodata = "none";
            $("#provCityAreaId").cxSelect({
                selects : ["city"/* ,"area" */],
                nodata : "none",
                firstValue : ""
            });
        },null,false);
    }

    /**
     * 加载关联培训基地
     */
    function searchOrgList(){
        var cityId1 =$("#cityId").val();
        var cityId2 ="${cityId}";
        var cityId = cityId1||cityId2;
        jboxStartLoading();
        var url = "<s:url value='/jsres/doctor/searchOrgList'/>?orgCityId=" + cityId;
        jboxGet(url, null, function(resp){
            jboxEndLoading();
            if("" != resp){
                var dataObj = resp;
                //清空原来培训基地！！！
                $("select[name=sorgFlow] option[value != '']").remove();
                for(var i = 0; i<dataObj.length; i++){
                    var orgFlow = dataObj[i].orgFlow;
                    if(orgFlow != "${param.orgFlow}"){//过滤当前基地
                        var orgName = dataObj[i].orgName;
                        $option =$("<option></option>");
                        $option.attr("value", orgFlow);
                        $option.text(orgName);
                        $("select[name=sorgFlow]").append($option);
                    }
                }
                if(""!="${orgFlow}"){
                    $("select[name=sorgFlow] option[value='${orgFlow}']").attr("selected",true);
                }
            }
        }, null, false);
    }

    function changesDetail(orgFlow,catSpeId){
        var data = "";
        <c:forEach items="${jszyResDocTypeEnumList}" var="type">
        if ($("#" + "${type.id}").attr("checked")) {
            data += "&datas=" + $("#" + "${type.id}").val();
        }
        </c:forEach>
        var trainYears = "";
        <c:forEach items="${jszyResTrainYearEnumList}" var="trainYear">
        if ($("#" + "${trainYear.id}").attr("checked")) {
            trainYears += "&trainYears=" + $("#" + "${trainYear.id}").val();
        }
        </c:forEach>
        if (trainYears == "") {
            jboxTip("请选择培养年限！");
            return false;
        }

        var sessionNumber = ${sessionNumber}
        var url = "<s:url value='/jszy/doctorRecruit/changesDetail'/>?orgFlow="
                + orgFlow+"&catSpeId="+catSpeId+"&sessionNumber="+sessionNumber+"&trainYears="+trainYears+"&data="+data;
        jboxStartLoading();
        jboxOpen(url, "异动详情列表",1000, 400);
        jboxEndLoading();
    }
</script>
<div class="main_hd">
    <h2 class="underline">在培学员统计</h2>
</div>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <form id="searchForm">
            <input type="hidden" name="currentPage" id="currentPage" value="${currentPage}"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">地&#12288;&#12288;市：</td>
                    <td id="provCityAreaId">
                        <select id="cityId" name="cityId" onchange="searchOrgList();"
                                class="city select validate" data-value="${cityId}"   style="width: 126px;">
                        </select>
                    </td>
                    <td class="td_left">基&#12288;&#12288;地：</td>
                    <td>
                        <select id="orgFlow" name="sorgFlow" class="validate select" onchange="checkResOrgSpe(this.value);" style="width: 126px;">
                            <option value="">请选择</option>
                        </select>
                    </td>
                    <td class="td_left">培养年限：</td>
                    <td colspan="2">
                      <%--  <label><input type="checkbox" id="trainYearAll" value="trainYearAll" name="trainYearAll"
                                      onclick="checkAllTrainYear(this);"/>全部&nbsp;</label>--%>
                        <c:forEach items="${jszyResTrainYearEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}" value="${type.id}" class="trainYear"
                                          name="trainYears" onclick="changeAllTrainYearBox();"/>${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">年&#12288;&#12288;级：</td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber"
                               value="${empty sessionNumber ? param.sessionNumber : sessionNumber}"
                               class="input" readonly="readonly"/>
                    </td>
                    <td class="td_left" >人员类型：</td>
                    <td  colspan="4">
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
    <div id="content">
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid" id="grid" >
                <colgroup>
                    <col width="10%"/>
                    <col width="20%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                    <col width="10%"/>
                </colgroup>
                <tr>
                    <th rowspan="2" style="min-width: 100px;border: 1px solid #e7e7eb;">地市</th>
                    <th rowspan="2" style="min-width: 150px;border: 1px solid #e7e7eb;">基地名称</th>
                    <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
                        <th colspan="2" style="min-width: 150px;border: 1px solid #e7e7eb;">${type.name}</th>
                    </c:forEach>
                    <th rowspan="2" style="min-width: 50px;border: 1px solid #e7e7eb;">在培学员<br>合计</th>
                </tr>
                <tr>
                    <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
                        <th  style="border: 1px solid #e7e7eb;">在培</th>
                        <th  style="border: 1px solid #e7e7eb;">异动</th>
                    </c:forEach>
                </tr>
                <c:forEach items="${cityMap}" var="city"  >
                    <c:set var="rowspan" value="${fn:length(city.value)}"></c:set>
                    <c:forEach items="${city.value}" var="org" varStatus="s">
                        <c:set var="sum" value="0"></c:set>
                        <c:set var="changesSum" value="0"></c:set>
                        <c:if test="${s.first}">
                            <tr>
                                <td rowspan="${rowspan}">${org.orgCityName}</td>
                                <td>${org.orgName}</td>
                                <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
                                    <c:set var="key" value="${org.orgFlow}${type.id}"></c:set>
                                    <td catId="${type.id}"
                                        catIdCount="${empty countInfoMap[key] ? 0 : countInfoMap[key]}">${empty countInfoMap[key] ? 0 : countInfoMap[key]}</td>
                                    <c:set var="sum" value="${sum + countInfoMap[key]}"></c:set>
                                    <td catId="${type.id}Changes" catIdCount="${empty countChangesMap[key] ? 0 : countChangesMap[key]}"
                                        style="color:#459ae9;cursor: pointer;background-color: white;" onclick="changesDetail('${org.orgFlow}','${type.id}');"
                                    >${empty countChangesMap[key] ? 0 : countChangesMap[key]}</td>
                                </c:forEach>
                                <td>${sum}</td>
                            </tr>
                        </c:if>
                        <c:if test="${!s.first}">
                            <tr>
                                <td>${org.orgName}</td>
                                <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
                                    <c:set var="key" value="${org.orgFlow}${type.id}"></c:set>
                                    <td catId="${type.id}"
                                        catIdCount="${empty countInfoMap[key] ? 0 : countInfoMap[key]}">${empty countInfoMap[key] ? 0 : countInfoMap[key]}</td>
                                    <c:set var="sum" value="${sum + countInfoMap[key]}"></c:set>
                                    <td catId="${type.id}Changes" catIdCount="${empty countChangesMap[key] ? 0 : countChangesMap[key]}"
                                        style="color:#459ae9;cursor: pointer;background-color: white;" onclick="changesDetail('${org.orgFlow}','${type.id}');"
                                    >${empty countChangesMap[key] ? 0 : countChangesMap[key]}</td>
                                </c:forEach>
                                <td>${sum}</td>
                            </tr>
                        </c:if>

                    </c:forEach>
                </c:forEach>
                <tr>
                    <td colspan="2">合计</td>
                    <c:forEach items="${jszyTrainCategoryEnumList}" var="type">
                        <td id="${type.id}Sum"></td>
                        <td id="${type.id}ChangesSum">-</td>
                    </c:forEach>
                    <td id="zongji"></td>
                </tr>
            </table>
        </div>
     <%--   <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(orgs)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>--%>
    </div>
</div>