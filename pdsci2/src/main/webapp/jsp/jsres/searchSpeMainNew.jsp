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
        var maxTime = "2017-03-26 00:00:00";
        if (nottime >= maxTime) {
            clearInterval(timer);
        }
    }


    $(document).ready(function () {
        clearInterval(timer);
        showButton();
        timer = setInterval(showButton, 1000);
        toPage(1);
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
    $(document).ready(function () {
        $.checkYear("sessionNumber","",null);
        if ("${speFlag}" == "0") {
            $("#speFlag").hide();
        }
        getCityArea();
        initOrg();
    });

    function getCityArea(){
        var url = '<s:url value="/js/provCityAreaJson.min.json"/>';
        var provIds = "320000";
        jboxGet(url,null, function(json) {
            // 提示：如果服务器不支持 .json 类型文件，请将文件改为 .js 文件
            var newJsonData=new Array();
            var j=0;
            var html ="<option value=''></option>";
            for(var i=0;i<json.length;i++){
                if(provIds==json[i].v){
                    var citys=json[i].s;
                    for(var k=0;k<citys.length;k++){
                        var city=citys[k];
                        html+="<option value='"+city.v+"'>"+city.n+"</option>";
                    }
                }
            }
            $("#cityId2").html(html);
        },null,false);
    }

    var allOrgs=[];
    function initOrg() {
        var datas=[];
        <c:forEach items="${orgs}" var="org">
            <c:if test="${sessionScope.currUser.orgFlow!=org.orgFlow }">
                var d={};
                d.id="${org.orgFlow}";
                d.text="${org.orgName}";
                d.cityId="${org.orgCityId}";
                datas.push(d);
                allOrgs.push(d);
            </c:if>
        </c:forEach>
        var itemSelectFuntion = function(){
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);

    }

    function changeOrg(obj) {
        var cityId=$(obj).val();
        var datas=[];
        for(var i=0;i<allOrgs.length;i++)
        {
            var org=allOrgs[i];
            if(org.cityId==cityId||cityId=="")
            {
                datas.push(org);
            }
        }
        $("#orgFlow").val("");
        var itemSelectFuntion = function(){
            $("#orgFlow").val(this.id);
        };
        $.selectSuggest('trainOrg',datas,itemSelectFuntion,"orgFlow",true);
    }

    function getChangeOrgDetail(doctorFlow, recordFlow) {
        var url = "<s:url value='/jsres/manage/getChangeOrgDetail'/>?change=Y&doctorFlow=" + doctorFlow + "&recordFlow=" + recordFlow;
        jboxOpen(url, "详情", 1050, 550);
    }
    function search() {
        var url = "<s:url value='/jsres/manage/searchChangeSpeList'/>?viewFlag=${param.viewFlag}";
        if ($('#jointOrg').get(0) != undefined) {
            $('#jointOrg').get(0).checked == true ? $('#jointOrg').val("checked") : $('#jointOrg').val("");
        }
        jboxPostLoad("div_table2", url, $("#inForm").serialize(), true);
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
    
    function exportSpeList() {
        var url="<s:url value='/jsres/manage/exportSpeList'/>";
        jboxTip("导出中…………");
        jboxExp($("#inForm"), url);
        jboxEndLoading();
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
                            <td class="td_left">地&#12288;&#12288;市：</td>
                            <td class="td_right">
                                <select id="cityId2" name="cityId" class="select" onchange="changeOrg(this)" ></select>
                            </td>
                            <td class="td_left">培训基地：</td>
                            <td class="td_right">
                                <input id="trainOrg"  class="toggleView input" type="text"  autocomplete="off" style="margin-left: 0px;width: 134px"  />
                                <input type="hidden" name="orgFlow" id="orgFlow" value="${param.orgFlow}">
                            </td>
                            <td class="td_left">姓&#12288;&#12288;名：</td>
                            <td class="td_right">
                                <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                            </td>
                            <td class="td_left">届&#12288;&#12288;别：</td>
                            <td class="td_right">
                                <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                            </td>
                            <td class="td_left"> 是否通过：</td>
                            <td class="td_right">
                                <select name="passFlag" class="select">
                                    <option value="">请选择</option>
                                    <option value="BaseWaitingAudit"
                                            <c:if test="${param.passFlag eq 'BaseWaitingAudit' }">selected="selected"</c:if>>基地待审核
                                    </option>
                                    <option value="BaseAuditUnPass"
                                            <c:if test="${param.passFlag eq 'BaseAuditUnPass' }">selected="selected"</c:if>>基地审核不通过
                                    </option>
                                    <option value="GlobalWaitingAudit"
                                            <c:if test="${param.passFlag eq 'GlobalWaitingAudit' }">selected="selected"</c:if>>省厅待审核
                                    </option>
                                    <option value="GlobalAuditPass"
                                            <c:if test="${param.passFlag eq 'GlobalAuditPass' }">selected="selected"</c:if>>省厅审核通过
                                    </option>
                                    <option value="GlobalAuditunPass"
                                            <c:if test="${param.passFlag eq 'GlobalAuditunPass' }">selected="selected"</c:if>>省厅审核不通过
                                    </option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td class="td_left">学员状态：</td>
                            <td class="td_right">
                                <select name="doctorStatusId" class="select" style="width: 140px">
                                    <option value="">全部</option>
                                    <option value="20" ${param.doctorStatusId eq '20'?'selected':''}>在培</option>
                                    <option value="22" ${param.doctorStatusId eq '22'?'selected':''}>已考核待结业</option>
                                    <option value="21" ${param.doctorStatusId eq '21'?'selected':''}>结业</option>
                                </select>
                            </td>
                            <td class="td_left">人员类型：</td>
                            <td colspan="3">
                                <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                    <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td id='jointOrg' colspan="2">
                                <label style="cursor: pointer;"><input type="checkbox" id="jointOrgFlag"
                                                                       <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                                                       name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
                            </td>
                            <td colspan="8">
                                <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                                <input type="button" class="btn_green" onclick="exportSpeList()" value="导&#12288;出">
                            </td>
                        </tr>
                    </table>
                </c:if>
                <c:if test="${param.viewFlag ne GlobalConstant.FLAG_Y}">

                    <table class="searchTable">
                        <tr>
                            <td class="td_left">地&#12288;&#12288;市：</td>
                            <td class="td_right">
                                <select id="cityId2" name="cityId" class="select" onchange="changeOrg(this)" style="width: 134px"></select>
                            </td>
                            <td class="td_left">培训基地：</td>
                            <td class="td_right">
                                <input id="trainOrg"  class="toggleView input" type="text" autocomplete="off" style="margin-left: 0px;width: 134px"  />
                                <input type="hidden" name="orgFlow" id="orgFlow" value="${param.orgFlow}">
                            </td>
                            <td class="td_left">姓&#12288;&#12288;名：</td>
                            <td class="td_right">
                                <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                            </td>
                            <td class="td_left">届&#12288;&#12288;别：</td>
                            <td class="td_right">
                                <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                            </td>
                            <td class="td_left">学员状态：</td>
                            <td class="td_right">
                                <select name="doctorStatusId" class="select" style="width: 134px">
                                    <option value="">全部</option>
                                    <option value="20" ${param.doctorStatusId eq '20'?'selected':''}>在培</option>
                                    <option value="22" ${param.doctorStatusId eq '22'?'selected':''}>已考核待结业</option>
                                    <option value="21" ${param.doctorStatusId eq '21'?'selected':''}>结业</option>
                                </select>
                            </td>
                        </tr>
                        <tr>

                            <td class="td_left">人员类型：</td>
                            <td colspan="3">
                                <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                    <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                                </c:forEach>
                            </td>
                            <td id='jointOrg' colspan="2">
                                <label style="cursor: pointer;"><input type="checkbox" id="jointOrgFlag"
                                                                       <c:if test="${param.jointOrgFlag eq GlobalConstant.FLAG_Y}">checked="checked"</c:if>
                                                                       name="jointOrgFlag" value="${GlobalConstant.FLAG_Y}"/>&nbsp;显示协同基地</label>
                            </td>
                        </tr>
                        <tr>
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
                        <td class="td_right">
                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td class="td_right">
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                        </td>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${jsResDocTypeEnumList}" var="type">
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
                        <td class="td_right">
                            <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td class="td_right">
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                        </td>
                        <td class="td_left">是否通过：</td>
                        <td class="td_right">
                            <select name="passFlag" class="select">
                                <option value="">请选择</option>
                                <option value="BaseWaitingAudit"
                                        <c:if test="${param.passFlag eq 'BaseWaitingAudit' }">selected="selected"</c:if>>基地待审核
                                </option>
                                <option value="BaseAuditUnPass"
                                        <c:if test="${param.passFlag eq 'BaseAuditUnPass' }">selected="selected"</c:if>>基地审核不通过
                                </option>
                                <option value="GlobalWaitingAudit"
                                        <c:if test="${param.passFlag eq 'GlobalWaitingAudit' }">selected="selected"</c:if>>省厅待审核
                                </option>
                                <option value="GlobalAuditPass"
                                        <c:if test="${param.passFlag eq 'GlobalAuditPass' }">selected="selected"</c:if>>省厅审核通过
                                </option>
                                <option value="GlobalAuditunPass"
                                        <c:if test="${param.passFlag eq 'GlobalAuditunPass' }">selected="selected"</c:if>>省厅审核不通过
                                </option>
                            </select>
                        </td>
                        <c:if test="${JointOrgCount ne '0'}">
                            <td class="td_left">培训基地：</td>
                            <td class="td_right">
                                <select class="select" name="orgFlow0" style="width: 133px;" onchange="searchDeptList(this.value)">
                                    <option value="all" <c:if test="${orgFlow eq 'all'}">selected="selected"</c:if>>全部</option>
                                    <c:forEach items="${orgList}" var="org">
                                        <option value="${org.orgFlow}" <c:if test="${orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </c:if>
                    </tr>
                    <tr>
<%--                        <c:if test="${JointOrgCount ne '0'}">--%>
<%--                            <td class="td_left">培训基地：</td>--%>
<%--                            <td class="td_right">--%>
<%--                                <select class="select" name="orgFlow0" style="width: 133px;" onchange="searchDeptList(this.value)">--%>
<%--                                    <option value="all" <c:if test="${orgFlow eq 'all'}">selected="selected"</c:if>>全部</option>--%>
<%--                                    <c:forEach items="${orgList}" var="org">--%>
<%--                                        <option value="${org.orgFlow}" <c:if test="${orgFlow == org.orgFlow}">selected="selected"</c:if>>${org.orgName}</option>--%>
<%--                                    </c:forEach>--%>
<%--                                </select>--%>
<%--                            </td>--%>
<%--                        </c:if>--%>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${jsResDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" onclick="changeAllBox();"/>${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                    </tr>
                </table>
                <div style="margin-bottom: 15px">
                    <input type="button" class="btn_green" onclick="toPage(1)" value="查&#12288;询">
                </div>
            </c:if>
        </c:if>
            <c:if test="${GlobalConstant.USER_LIST_CHARGE eq sessionScope.userListScope}">
            <table class="searchTable">
                <tr>
                    <td class="td_left">姓&#12288;&#12288;名：</td>
                    <td>
                        <input type="text" name="doctorName" value="${param.doctorName}" class="input"/>
                    </td>
                    <td class="td_left">届&#12288;&#12288;别：</td>
                    <td>
                        <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input"   readonly="readonly" />
                    </td>
                    <td class="td_left">人员类型：</td>
                    <td colspan="3">
                        <c:forEach items="${jsResDocTypeEnumList}" var="type">
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
    <div class="search_table" id="div_table2">
    </div>
</div>
