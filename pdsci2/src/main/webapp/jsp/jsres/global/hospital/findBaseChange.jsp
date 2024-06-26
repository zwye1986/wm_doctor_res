<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
    .indexNum + div{
        z-index: 99;
        position: relative!important;
        top:0!important;
        left:0!important;
    }
    .itemDiv {
        line-height: 20px;
    }
</style>
<script type="text/javascript">
    $(document).ready(function () {
        $.checkYear("sessionNumber","",null);
//        $('#sessionNumber').datepicker({
//            startView: 2,
//            maxViewMode: 2,
//            minViewMode: 2,
//            format: 'yyyy'
//        });
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
    });
    function checkAll(obj){
        var f=false;
        if($(obj).attr("checked")=="checked")
        {
            f=true;
        }
        $(".docType").each(function(){
            if(f)
            {
                $(this).attr("checked","checked");
            }else {
                $(this).removeAttr("checked");
            }
        });
    }
    function changeAllBox(){
        if($(".docType:checked").length==$(".docType").length)
        {
            $("#all").attr("checked","checked");
        }else{
            $("#all").removeAttr("checked");
        }
    }
    function getChangeOrgDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jsres/manage/getChangeOrgDetail'/>?change=Y&openType=open&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow;
        jboxOpen(url, "详情", 1050, 550);
    }
    function search() {
        var url = "<s:url value='/jsres/manage/changeBase'/>";

        <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
        jboxPostLoad("doctorContent", url, $("#inForm").serialize(), false);
        </c:if>
        <c:if test="${GlobalConstant.USER_LIST_GLOBAL != sessionScope.userListScope}">
        jboxPostLoad("content", url, $("#inForm").serialize(), false);
        </c:if>
    }
    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }

        var data="";
        $("input[class='docType']:checked").each(function(){
            data+="&datas="+$(this).val();
        });
        if(data==""){
            jboxTip("请选择人员类型！");
            return false;
        }
        search();
    }
    function audit(recordFlow, doctorFlow) {
        var url = "<s:url value='/jsres/manage/audit'/>?doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow + "";
        jboxOpen(url, "基地变更记录审核", 650, 450);
    }
    function check(obj) {
        var url = "<s:url value='/jsres/manage/changeBase'/>";

        <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
            jboxPostLoad("doctorContent", url, $("#inForm").serialize(), false);
        </c:if>
        <c:if test="${GlobalConstant.USER_LIST_GLOBAL != sessionScope.userListScope}">
            jboxPostLoad("content", url, $("#inForm").serialize(), false);
        </c:if>
    }
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#historyOrgFlow").val("");
        }
    }
    (function ($) {
        $.fn.likeSearchInit = function (option) {
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            var spaceId = this[0].id;
            searchInput.on("keyup focus", function () {
                var boxHome = $("#" + spaceId + "Sel");
                boxHome.show();
                var pDiv = $(boxHome).parent();
                // $(pDiv).css("left", $("#train").outerWidth());
                var w = $(this).css("marginTop").replace("px", "");
                w = w - 0 + $(this).outerHeight() + 6 + "px";
                // $(pDiv).css("top", w);
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
                    $("#historyOrgFlow").val($(this).attr("flow"));
                }
            });
        };
    })(jQuery);
    function changeOrgFlow(obj) {
        var items = $("#pDiv").find("p." + $(obj).attr("id") + ".item[value='" + $(obj).val() + "']");
        var flow = $(items).attr("flow") || '';
        $("#historyOrgFlow").val(flow);
    }
    $(function () {
        if ($("#trainOrg").length) {
            $("#trainOrg").likeSearchInit({
                clickActive: function (flow) {
                    $("." + flow).show();
                }
            });
        }
    });
    function exportList(){
        var url = "<s:url value='/jsres/manage/exportBaseList'/>";
        jboxTip("导出中…………");
        jboxSubmit($("#inForm"), url, null, null, false);
        jboxEndLoading();
    }
</script>
<div class="main_hd">
    <c:if test="${GlobalConstant.USER_LIST_GLOBAL != sessionScope.userListScope}">
        <h2 class="underline">基地变更查询</h2>
    </c:if>
</div>
<div class="main_bd">
    <div class="div_search">
        <form id="inForm">
            <input type="hidden" name="currentPage" id="currentPage" value="${param.currentPage}"/>
            <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 0px;">
                <tr>
                    <td class="td_left">
                        姓&#12288;&#12288;名：
                    </td>
                    <td style="width: 110px;"><input type="text" name="doctorName" value="${param.doctorName}"
                                                     class="input" style="width: 130px;"/></td>
                    <td class="td_left">&#12288;年&#12288;&#12288;级：</td>
                    <td style="width: 110px;"><input type="text" id="sessionNumber" name="sessionNumber"
                                                     value="${param.sessionNumber}" class="input indexNum" readonly="readonly"
                                                     style="width: 130px;margin-left: 0px"/></td>
                    <td class="td_left">&#12288;原培训基地：</td>
                    <td style="position: relative">
                        <input id="trainOrg" oncontextmenu="return false" name="orgName" value="${param.orgName}"
                               class="toggleView input" type="text" autocomplete="off"
                               onkeydown="changeStatus();" onkeyup="changeStatus();" style="width: 130px;"/>
                        <div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:absolute;left: 5px; top:30px">
                            <div class="boxHome trainOrg" id="trainOrgSel"
                                 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                <c:forEach items="${orgs}" var="org">
                                    <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}"
                                       style="line-height: 20px; padding:10px 0;cursor: default;width: 200px ;height: 10px"
                                       <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                    >${org.orgName}</p>
                                </c:forEach>
                            </div>
                            <input type="text" name="historyOrgFlow" id="historyOrgFlow" value="${param.historyOrgFlow}" style="display: none;"/>
                        </div>
                    </td>
                    <td style="width: auto;">
                        <label style="cursor: pointer;"><input type="checkbox" name="statusFlag"
                                                               value="${GlobalConstant.FLAG_Y}"
                                                               <c:if test="${param.statusFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                                               >&nbsp;未通过记录&#12288;</label>
                    </td>
                </tr>
                <tr>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                    <td colspan="3">
                        &#12288;<input class="btn_green" type="button" onclick="toPage(1)" value="查&#12288;询"/>&#12288;
                        <input class="btn_green" type="button" onclick="exportList()" value="导&#12288;出"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="8%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="8%"/>
                <col width="10%"/>
                <col width="15%"/>
                <col width="10%"/>
                <col width="8%"/>
                <col width="9%"/>
                <col width="8%"/>
            </colgroup>
            <thead>
            <tr>
                <th>姓名</th>
                <th>培训专业</th>
                <th>人员类型</th>
                <th>年级</th>
                <th>转入审核时间</th>
                <th>原培训基地</th>
                <th>变更后基地</th>
                <th>附件</th>
                <th>审核时间</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${historyExts2}" var="docOrgHistoryExt">
                <tr>
                    <td>${docOrgHistoryExt.resDoctor.doctorName}</td>
                    <td>${docOrgHistoryExt.historyTrainingSpeName}</td>
                    <td>${docOrgHistoryExt.resDoctor.doctorTypeName}</td>
                    <td>${docOrgHistoryExt.resDoctor.sessionNumber}</td>
                    <td>${pdfn:transDate(docOrgHistoryExt.inDate)}</td>
                    <td>${docOrgHistoryExt.historyOrgName}</td>
                    <td>${docOrgHistoryExt.orgName}</td>
                    <td>
                        <c:if test="${not empty docOrgHistoryExt.speChangeApplyFile}">
                            [<a href="${sysCfgMap['upload_base_url']}/${docOrgHistoryExt.speChangeApplyFile }" target="_blank">查看附件</a>]&nbsp;
                        </c:if>
                    </td>
                    <td>${pdfn:transDateTime(docOrgHistoryExt.modifyTime)}</td>
                    <td>
                        <a class="btn" style="padding:0 20px;"
                           onclick="getChangeOrgDetail('${docOrgHistoryExt.doctorFlow}','${docOrgHistoryExt.recordFlow}');">详情</a>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty historyExts2 }">
                <tr>
                    <td colspan="10">无记录</td>
                </tr>
            </c:if>
            </tbody>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(historyExts2)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
