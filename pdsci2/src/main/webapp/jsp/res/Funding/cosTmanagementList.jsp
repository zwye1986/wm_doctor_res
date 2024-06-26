<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<script type="text/javascript">
    (function ($) {
        $.fn.likeSearchInit = function (option) {
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            var spaceId = this[0].id;
            searchInput.on("keyup focus", function () {
                var boxHome = $("#" + spaceId + "Sel");
                boxHome.show();
                var pDiv = $(boxHome).parent();
                $(pDiv).css("left", $("#train").outerWidth());
                var w = $(this).css("marginTop").replace("px", "");
                w = w - 0 + $(this).outerHeight() + 6 + "px";
                $(pDiv).css("top", w);
                if ($.trim(this.value)) {
                    $("p." + spaceId + ".item", boxHome).hide();
                    var items = boxHome.find("p." + spaceId + ".item[value*='" + this.value + "']").show();
                    if (!items) {
                        boxHome.hide();
                    }
                    changeOrgFlow(this);
                } else {
                    $("p." + spaceId + ".item", boxHome).show();
                }
            });
            searchInput.on("blur", function () {
                var boxHome = $("#" + spaceId + "Sel");
                if (!$("." + spaceId + ".boxHome.on").length) {
                    boxHome.hide();
                }
            });
            $("." + spaceId + ".boxHome").on("mouseenter mouseleave", function () {
                $(this).toggleClass("on");
            });

            $("." + spaceId + ".boxHome .item").click(function () {
                var boxHome = $("." + spaceId + ".boxHome");
                boxHome.hide();
                var value = $(this).attr("value");
                var input = $("#" + spaceId);
                input.val(value);
                if (option.clickActive) {
                    option.clickActive($(this).attr("flow"));
                    $("#orgFlow").val($(this).attr("flow"));
                }
            });
        };
    })(jQuery);
    function changeOrgFlow(obj) {
        var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
        var flow = $(items).attr("flow") || '';
        $("#orgFlow").val(flow);
    }
    $(document).ready(function () {
        if ($("#trainOrg").length) {
            $("#trainOrg").likeSearchInit({
                clickActive: function (flow) {
                    $("." + flow).show();
                }
            });
        }
    });
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }

    function search() {
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();
        if(startDate > endDate){
            jboxTip("查询上报开始时间不能大于结束时间！");
            return;
        }
        var url = "<s:url value='/res/resFunds/queryCosTmanagementList'/>";
        jboxPostLoad("content", url, $("#submitForm").serialize(), true);
    }
    function importExcel() {
        jboxOpen("<s:url value='/jsres/statistic/leadTo'/>", "导入", 500, 200);
    }

    function editOrAdd(recordFlow){
        var title = "新增经费上报纪录";
        if(recordFlow){
            title = "编辑经费上报纪录";
        }
        var url = "<s:url value='/res/resFunds/queryCosTmanagementById'/>?recordFlow=" + recordFlow;
        jboxOpen(url, title, 800, 600);
       //jboxOpen(url, title, 900, 700);
    }
    function viewSynCos(recordFlow){
        var title = "查看经费上报纪录";
        var url = "<s:url value='/res/resFunds/queryCosTmanagementById'/>?recordFlow=" + recordFlow+"&view=1";
        jboxOpen(url, title, 800, 600);
    }
    function toPage(page) {
        if (!page) {
            page = 1;
        }
        $("#currentPage").val(page);
        search();
    }


</script>
<div class="main_hd">
    <h2 class="underline">综合费用管理</h2>
</div>
<div class="main_bd">
    <div class="div_search">
        <form id="submitForm">
            <input type="hidden" id="sexName" name="sexName" value="${param.sexName }">
            <input type="hidden" id="currentPage" name="currentPage" value="${param.currentPage }">
            <input type="hidden" id="role" name="role" value="${role}">
            <table>
                <tr>
                    <td class="td_left">培训基地：</td>
                    <td>
                        <input id="trainOrg" oncontextmenu="return false" name="orgName" value="${param.orgName}"  class="toggleView input" type="text"
                               autocomplete="off" style="margin-left: 0px;width: 120px"   onkeydown="changeStatus();" onkeyup="changeStatus();"/>
                    </td>
                        <div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;">
                            <div class="boxHome trainOrg" id="trainOrgSel" style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;left: 66px">
                                <c:forEach items="${orgs}" var="org">
                                    <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}" style="line-height: 20px; padding:10px 0;cursor: default;width: 200px ;height: 10px"
                                       <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;" </c:if>
                                    >${org.orgName}</p>
                                </c:forEach>
                            </div>
                            <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
                        </div>
                    <td >
                        上报时间：
                    </td>
                    <td>
                        <input type="text" style="width: 90px" id="startDate" name="startDate" value="${param.startDate}" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </td>
                    <td >至</td>
                    <td>
                        <input type="text" style="width: 90px" id="endDate" name="endDate" value="${param.endDate}" class="input"  readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
                    </td>
                    <td id="func" colspan="3">
                        &#12288;<input class="btn_green" type="button" onclick="toPage(1)" value="查&#12288;询"/>
                        <input class="btn_green" type="button" onclick="editOrAdd('')" value="新&#12288;增"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th  >培训基地</th>
                <th >上报时间</th>
                <th  >资金下拨日期</th>
                <th  >金额(万元)</th>
                <th >用途</th>
                <th  >支出总额</th>
                <th >操作</th>

            </tr>

            <c:forEach items="${teacherTrainingList}" var="teacher">
                <tr>
                    <td >${teacher.orgName }</td>
                    <td >${teacher.reportDate }</td>
                    <td >${teacher.inPlaceDate }</td>
                    <td >${teacher.amountOfExpenses }</td>
                    <td ><a class="btn" onclick="viewSynCos('${teacher.recordFlow}');">查看</a></td>
                    <td >${teacher.amountOfIncome }</td>
                    <td> <a class="btn" onclick="editOrAdd('${teacher.recordFlow}');">编辑</a> </td>
                </tr>
            </c:forEach>
            <c:if test="${empty teacherTrainingList}">
                <tr>
                    <td colspan="66">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(teacherTrainingList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>