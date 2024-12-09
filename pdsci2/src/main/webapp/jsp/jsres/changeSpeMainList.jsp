<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
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

    .cur {
        color: red
    }
</style>
<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    /**
     * 对Date的扩展，将 Date 转化为指定格式的String
     * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符
     * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
     * eg:
     * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
     * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
     * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
     * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二 08:09:04
     * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
     */
    Date.prototype.pattern = function (fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
            "H+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        var week = {
            "0": "\u65e5",
            "1": "\u4e00",
            "2": "\u4e8c",
            "3": "\u4e09",
            "4": "\u56db",
            "5": "\u4e94",
            "6": "\u516d"
        };
        if (/(y+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        }
        if (/(E+)/.test(fmt)) {
            fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f" : "\u5468") : "") + week[this.getDay() + ""]);
        }
        for (var k in o) {
            if (new RegExp("(" + k + ")").test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
            }
        }
        return fmt;
    }
    function getNowFormatDate() {
        var date = new Date();
        return date.pattern("yyyy-MM-dd HH:mm:ss");
//		var seperator1 = "-";
//		var seperator2 = ":";
//		var month = date.getMonth() + 1;
//		var strDate = date.getDate();
//		var m=""+ month.toString();
//		if (parseInt(month)  <= 9) {
//			m = "0" + month.toString();
//		}
//		var d=""+strDate.toString();
//		if (parseInt(strDate) <= 9) {
//			d=strDate = "0" + strDate.toString();
//		}
//		var currentdate = date.getFullYear() + seperator1 + m + seperator1 + d
//				+ " " + date.getHours() + seperator2 + date.getMinutes()
//				+ seperator2 + date.getSeconds();
//		return currentdate;
    }
    var timer = setInterval(showButton, 1000);
    function showButton() {
        var nottime = getNowFormatDate();
//		console.log(nottime);
        var maxTime = "2017-03-26 00:00:00";
        if (nottime >= maxTime) {

            <%--<c:if test="${GlobalConstant.USER_LIST_GLOBAL ne sessionScope.userListScope}">--%>
            <%--$(".audit").hide();--%>
            <%--</c:if>--%>
            clearInterval(timer);
        }
    }


    $(document).ready(function () {
        clearInterval(timer);
        showButton();
        timer = setInterval(showButton, 1000);
    });
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

    $(function () {
        if ($("#trainOrg").length) {
            $("#trainOrg").likeSearchInit({
                clickActive: function (flow) {
                    $("." + flow).show();
                }
            });
        }
        <c:forEach items="${resDocTypeEnumList}" var="type">
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

    function getChangeOrgDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jsres/manage/getChangeOrgDetail'/>?change=Y&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow;
        jboxOpen(url, "详情", 1050, 550);
    }

    function audit(recordFlow, doctorFlow) {
        var url = "<s:url value='/jsres/manage/audit'/>?doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow + "";
        jboxOpen(url, "专业变更记录审核", 680, 450);
    }
    function changeStatus() {
        if ($("#trainOrg").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
            $("#orgFlow").val("");
        }
    }
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

</script>
<div class="main_bd">
<%--    <div class="search_table">--%>
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="7%"/>
                <col width="6%"/>
                <col width="7%"/>
                <col width="13%"/>
                <col width="15%"/>
                <col width="11%"/>
                <col width="11%"/>
                <c:if test="${(GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope
                and GlobalConstant.FLAG_Y eq param.viewFlag) or GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope }">
                    <col width="10%"/>
                </c:if>
                <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y}">
                    <col width="10%"/>
                </c:if>
                <c:if test="${((GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope
                and GlobalConstant.FLAG_Y eq param.viewFlag) or GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope )or param.viewFlag eq GlobalConstant.FLAG_Y}">
                    <col width="10%"/>
                </c:if>
                <c:if test="${!(((GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope
                and GlobalConstant.FLAG_Y eq param.viewFlag) or GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope )or param.viewFlag eq GlobalConstant.FLAG_Y)}">
                    <col width="20%"/>
                </c:if>
            </colgroup>
            <thead>
            <tr>
                <th>姓名</th>
                <th>届别</th>
                <th>人员类型</th>
                <th>培训基地</th>
                <th>审核状态</th>
                <th>原培训专业</th>
                <th>变更后专业</th>
                <c:if test="${(GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope
                and GlobalConstant.FLAG_Y eq param.viewFlag) or GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope }">
                    <th>附件</th>
                </c:if>
                <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y}">
                    <th>审核时间</th>
                </c:if>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${historyExts }" var="docOrgHistoryExt">
                <tr>
                    <td>${docOrgHistoryExt.resDoctor.doctorName}</td>
                    <td>${docOrgHistoryExt.resDoctor.sessionNumber}</td>
                    <td>${docOrgHistoryExt.resDoctor.doctorTypeName}</td>
                    <td>${docOrgHistoryExt.historyOrgName}</td>
                    <td>${docOrgHistoryExt.changeStatusName}</td>
                    <td>${docOrgHistoryExt.historyTrainingSpeName}</td>
                    <td>${docOrgHistoryExt.trainingSpeName}</td>
                    <c:if test="${(GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope
                    and GlobalConstant.FLAG_Y eq param.viewFlag) or GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope }">
                        <td>[<a href="${sysCfgMap['upload_base_url']}/${docOrgHistoryExt.speChangeApplyFile }"
                                target="_blank">查看附件</a>]
                        </td>
                    </c:if>
                    <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y}">
                        <td>${pdfn:transDateTime(docOrgHistoryExt.modifyTime)}</td>
                    </c:if>
                    <td>
                        <a class="btn"
                           onclick="getChangeOrgDetail('${docOrgHistoryExt.doctorFlow}','${docOrgHistoryExt.recordFlow}');">详情</a>
                        <c:if test="${(jsResChangeApplySpeEnumBaseWaitingAudit.id == docOrgHistoryExt.changeStatusId and GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope) || (jsResChangeApplySpeEnumGlobalWaitingAudit.id == docOrgHistoryExt.changeStatusId and GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope)}">

                        <c:if test="${maintenance ne 'Y'}"> <%--客服（运维角色）只能查看——--%>
                            <a class="btn audit"
                               onclick="audit('${docOrgHistoryExt.recordFlow}','${docOrgHistoryExt.doctorFlow}')">审核</a>
                        </c:if>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty historyExts }">
                <tr>
                    <td colspan="20">无记录</td>
                </tr>
            </c:if>
            </tbody>
        </table>
<%--    </div>--%>
    <div class="page" style="padding-right: 0px;">
        <c:set var="pageView" value="${pdfn:getPageView(historyExts)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
