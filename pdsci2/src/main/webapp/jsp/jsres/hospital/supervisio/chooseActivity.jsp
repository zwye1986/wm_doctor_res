<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
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
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red;
    }

    .searchTable {
        width: auto;
    }

    .searchTable td {
        width: auto;
        height: auto;
        line-height: auto;
        text-align: left;
        max-width: 150px;;
    }

    .searchTable .td_left {
        word-wrap: break-word;
        width: 5em;
        height: auto;
        line-height: auto;
        text-align: right;
    }
</style>
<script type="text/javascript"
        src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<link href="<s:url value='/css/UCFORM.css'/>" rel="stylesheet" type="text/css">
<script src="<s:url value='/js/jQuery.UCSelect.js'/>" type="text/javascript"></script>
<script type="text/javascript"
        src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('select[name=deptFlow]').UCFormSelect();
        $('select[name=formOfActivity]').UCFormSelect();
        $(".UCSelect").css("width","107px");
        $(".SelectVal").css("width","170px");
        $(".SelectBox").css("width","159%");
        toPage();
    });


    function toPage(page) {
        // jboxStartLoading();
        $("#currentPage").val(page);
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/supervisio/activityList'/>?speName=${speName}&activityFlows=${activityFlows}&userFlows=${userFlows}&speOnly=${speOnly}",
            $("#searchForm").serialize(), false);
    }

    function trainOrgHidden() {
        $("#trainOrg-suggest").css({"display": "none"})
    }
    function saveActivity() {
        var itemIdList = $("input");
        for (var i = 0; i < itemIdList.length; i++) {
            if (itemIdList[i].getAttribute("name") == "activity"){
                if ( itemIdList[i].checked==true){
                    window.parent.frames["jbox-message-iframe"].$("#activity")[0].value=itemIdList[i].getAttribute("itemName");
                    window.parent.frames["jbox-message-iframe"].$("#activityFlows")[0].value=$(itemIdList[i]).val();
                    window.parent.frames["jbox-message-iframe"].$("#startTime")[0].value=itemIdList[i].getAttribute("startTime");
                    window.parent.frames["jbox-message-iframe"].$("#endTime")[0].value=itemIdList[i].getAttribute("endTime");
                    window.parent.frames["jbox-message-iframe"].$("#deptCode")[0].value=itemIdList[i].getAttribute("deptCode");
                    window.parent.frames["jbox-message-iframe"].$("#deptName")[0].value=itemIdList[i].getAttribute("deptName");
                    window.parent.frames["jbox-message-iframe"].$("#teachName")[0].value=itemIdList[i].getAttribute("teachName");
                    window.parent.frames["jbox-message-iframe"].$("#teachFlow")[0].value=itemIdList[i].getAttribute("teachFlow");
                    // window.parent.frames["jbox-message-iframe"].$("#activityEndTime")[0].value=itemIdList[i].getAttribute("activityEndTime");
                    // window.parent.frames["jbox-message-iframe"].$("#activityStartTime")[0].value=itemIdList[i].getAttribute("activityStartTime");
                    jboxClose();
                    return;
                }
            }
        }
    }
</script>

<div class="div_search" style="width: 95%;line-height:normal;">
    <form id="searchForm">
        <input type="hidden" id="currentPage" name="currentPage"/>
        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
            <tr>
                <td class="td_left">
                    <nobr>活动名称：</nobr>
                </td>
                <td>
                    <input id="activityName" name="activityName" class="toggleView input" type="text" autocomplete="off"
                           style="margin-left: 0px;width: 164px;height: 30px;"/>
                </td>
                <td class="td_left">
                    <nobr>主讲人：</nobr>
                </td>
                <td>
                    <input id="userName" name="userName" class="toggleView input" type="text" autocomplete="off"
                           style="margin-left: 0px;width: 164px;height: 30px;"/>
                </td>
                <td class="td_left">
                    <nobr>科&#12288;&#12288;室：</nobr>
                </td>
                <td>
                    <select name="deptFlow" class="select" id="deptFlow" style="width: 170px;" multiple="multiple">
                        <c:forEach items="${speList}" var="dict">
                            <option value="${dict.deptFlow}">${dict.deptName}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="td_left">
                    <nobr>开始时间：</nobr>
                </td>
                <td>
                    <input name="startTime" id="startTime" style="margin-left: 0px;width: 165px;height: 30px;"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" value="" class="input">
                </td>

                <td class="td_left">
                    <nobr>结束时间：</nobr>
                </td>
                <td>
                    <input name="endTime" id="endTime" style="margin-left: 0px;width: 165px;height: 30px;"
                           onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})" value="" class="input">
                </td>
                <td class="td_left">
                    <nobr>活动形式：</nobr>
                </td>
                <td>
                    <select id="formOfActivity" name="formOfActivity" class="select" style="width: 170px;" multiple="multiple">
                        <option value="">教学查房</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
               <td><input class="btn_green" type="button"  style="margin-right: -17px" value="查&#12288;询" onclick="toPage(1);"/>&#12288;</td>
                <td><input class="btn_green" type="button"  style="margin-left: 35px;" value="保&#12288;存" onclick="saveActivity();"/>&#12288;</td>

            </tr>
        </table>

    </form>
</div>
<div id="doctorListZi" style="width: 95%">

</div>