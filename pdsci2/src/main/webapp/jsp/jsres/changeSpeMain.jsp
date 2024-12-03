<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
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
    $(document).ready(function () {
        $.checkYear("sessionNumber","",null);
//        $('#sessionNumber').datepicker({
//            startView: 2,
//            maxViewMode: 2,
//            minViewMode: 2,
//            format: 'yyyy'
//        });
        if ("${speFlag}" == "0") {
            $("#speFlag").hide();
        }
    });
    function getChangeOrgDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jsres/manage/getChangeOrgDetail'/>?change=Y&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow;
        jboxOpen(url, "详情", 1050, 550);
    }
    function search() {
        var url = "<s:url value='/jsres/manage/changeSpeMain'/>";
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        if ("${GlobalConstant.FLAG_Y}" == "${param.viewFlag}") {
            url = "<s:url value='/jsres/manage/searchChangeSpe'/>?viewFlag=${param.viewFlag}";
        }
        jboxPostLoad("div_table", url, $("#inForm").serialize(), false);
    }
    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        search();
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
    <div class="div_search">
        <form id="inForm">
            <input type="hidden" name="currentPage" id="currentPage"/>
            <c:if test="${GlobalConstant.USER_LIST_GLOBAL eq sessionScope.userListScope}">
                <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y}">
                    <table class="searchTable">
                        <tr>
                            <td class="td_left">培训基地：</td>
                            <td>
                                <input id="trainOrg" oncontextmenu="return false" name="orgName" value="${param.orgName}"
                                       class="toggleView input" type="text" autocomplete="off"
                                       onkeydown="changeStatus();" onkeyup="changeStatus();"/>
                                <div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left: 0px; top:30px;">
                                    <div class="boxHome trainOrg" id="trainOrgSel"
                                         style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                        <c:forEach items="${orgs}" var="org">
                                            <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}"
                                               style="line-height: 20px; padding:10px 0;cursor: default;width: 200px ;height: 10px"
                                               <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                            >${org.orgName}</p>
                                        </c:forEach>
                                    </div>
                                    <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
                                </div>
                            </td>
                            <td class="td_left">姓&#12288;&#12288;名：</td>
                            <td>
                                <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                            </td>
                            <td class="td_left">年&#12288;&#12288;级：</td>
                            <td>
                                <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                            </td>
                            <td class="td_left"> 是否通过：</td>
                            <td>
                                <select name="passFlag" class="select">
                                    <option value="">请选择</option>
                                    <option value="${GlobalConstant.FLAG_Y}"
                                            <c:if test="${param.passFlag eq GlobalConstant.FLAG_Y }">selected="selected"</c:if>>是
                                    </option>
                                    <option value="${GlobalConstant.FLAG_N }"
                                            <c:if test="${param.passFlag eq GlobalConstant.FLAG_N  }">selected="selected"</c:if>>否
                                    </option>
                                </select>
                            </td>
                        </tr>
                            <tr>
                                <td class="td_left">人员类型：</td>
                                <td colspan="3">
                                    <c:forEach items="${resDocTypeEnumList}" var="type">
                                        <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                                    </c:forEach>
                                </td>
                                <td colspan="2">
                                    <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                                </td>
                            </tr>
                    </table>
                </c:if>
                <c:if test="${param.viewFlag ne GlobalConstant.FLAG_Y}">

                    <table class="searchTable">
                        <tr>
                            <td class="td_left">培训基地：</td>
                            <td>
                                <input id="trainOrg" oncontextmenu="return false" name="orgName" value="${param.orgName}"
                                       class="toggleView input" type="text" autocomplete="off"
                                       onkeydown="changeStatus();" onkeyup="changeStatus();"/>
                                <div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left: 0px; top:30px;">
                                    <div class="boxHome trainOrg" id="trainOrgSel"
                                         style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                        <c:forEach items="${orgs}" var="org">
                                            <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}"
                                               style="line-height: 20px; padding:10px 0;cursor: default;width: 200px ;height: 10px"
                                               <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                            >${org.orgName}</p>
                                        </c:forEach>
                                    </div>
                                    <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
                                </div>
                            </td>
                            <td class="td_left">姓&#12288;&#12288;名：</td>
                            <td>
                                <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                            </td>
                            <td class="td_left">年&#12288;&#12288;级：</td>
                            <td>
                                <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                            </td>
                        </tr>
                        <tr>
                            <td class="td_left">人员类型：</td>
                            <td colspan="3">
                                <c:forEach items="${resDocTypeEnumList}" var="type">
                                    <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                                </c:forEach>
                            </td>
                            <td colspan="2">
                                <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                            </td>
                        </tr>
                    </table>
                </c:if>
            </c:if>
            <c:if test="${GlobalConstant.USER_LIST_LOCAL eq sessionScope.userListScope}">

            <c:if test="${param.viewFlag ne GlobalConstant.FLAG_Y}">
                <table class="searchTable">
                    <tr>
                        <td class="td_left">姓&#12288;&#12288;名：</td>
                        <td>
                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                        </td>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="8">
                            <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                        </td>
                    </tr>
                </table>
            </c:if>
            <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y}">
                <table class="searchTable">
                    <tr>
                        <td class="td_left">姓&#12288;&#12288;名：</td>
                        <td>
                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                        </td>
                        <td class="td_left">是否通过：</td>
                        <td>
                            <select name="passFlag" class="select">
                                <option value="">请选择</option>
                                <option value="${GlobalConstant.FLAG_Y}"
                                        <c:if test="${param.passFlag eq GlobalConstant.FLAG_Y }">selected="selected"</c:if>>是
                                </option>
                                <option value="${GlobalConstant.FLAG_N }"
                                        <c:if test="${param.passFlag eq GlobalConstant.FLAG_N  }">selected="selected"</c:if>>否
                                </option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <c:if test="${JointOrgCount ne '0'}">
                            <td class="td_left">培训基地：</td>
                            <td>
                                <select class="select" name="orgFlow0" style="width: 133px;" onchange="searchDeptList(this.value)">
                                    <option value="all" <c:if test="${orgFlow eq 'all'}">selected="selected"</c:if>>全部</option>
                                    <c:forEach items="${orgList}" var="org">
                                        <option value="${org.orgFlow}" <c:if test="${orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </c:if>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                            </c:forEach>
                            <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                        </td>
                    </tr>
                </table>
            </c:if>
        </c:if>
            <c:if test="${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">
            <table class="searchTable">
                <tr>
                    <td class="td_left">姓&#12288;&#12288;名：</td>
                    <td>
                        <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                    </td>
                    <td class="td_left">年&#12288;&#12288;级：</td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                    </td>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${resDocTypeEnumList}" var="type">
                            <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                        </c:forEach>
                    </td>
                </tr>
                <tr>
                    <td class="td_left"> 是否通过：</td>
                    <td>
                        <select name="passFlag" class="select">
                            <option value="">请选择</option>
                            <option value="${GlobalConstant.FLAG_Y}"
                                    <c:if test="${param.passFlag eq GlobalConstant.FLAG_Y }">selected="selected"</c:if>>是
                            </option>
                            <option value="${GlobalConstant.FLAG_N }"
                                    <c:if test="${param.passFlag eq GlobalConstant.FLAG_N  }">selected="selected"</c:if>>否
                            </option>
                        </select>
                    </td>
                    <td class="td_left">培训基地：</td>
                    <td>
                        <input id="trainOrg" oncontextmenu="return false" name="orgName" value="${param.orgName}"
                               class="toggleView input" type="text" autocomplete="off"
                               onkeydown="changeStatus();" onkeyup="changeStatus();"/>
                        <div id="pDiv" style="width: 0px;height: 0px;overflow: visible;float: left; position:relative;left: 0px; top:30px;">
                            <div class="boxHome trainOrg" id="trainOrgSel"
                                 style="max-height: 250px;overflow: auto; border: 1px #ccc solid;background-color: white;min-width: 166px;border-top: none;position: relative;display:none;">
                                <c:forEach items="${orgs}" var="org">
                                    <p class="item trainOrg allOrg orgs" flow="${org.orgFlow}" value="${org.orgName}"
                                       style="line-height: 20px; padding:10px 0;cursor: default;width: 200px ;height: 10px"
                                       <c:if test="${sessionScope.currUser.orgFlow==org.orgFlow }">style="display: none;"</c:if>
                                    >${org.orgName}</p>
                                </c:forEach>
                            </div>
                            <input type="text" name="orgFlow" id="orgFlow" value="${param.orgFlow}" style="display: none;"/>
                        </div>
                    </td>
                    <td colspan="2">
                        <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                    </td>
                </tr>
            </table>
        </c:if>
        </form>
    </div>
    <div class="search_table">
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
                <th>年级</th>
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
                            <a class="btn audit"
                               onclick="audit('${docOrgHistoryExt.recordFlow}','${docOrgHistoryExt.doctorFlow}')">审核</a>
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
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(historyExts)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>
