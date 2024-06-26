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
<%--<script type="text/javascript" src="<s:url value='/js/jquery-select/js/jquery.select.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect2.js'/>"></script>
<link href="<s:url value='/css/UCFORM.css'/>" rel="stylesheet" type="text/css">
<script src="<s:url value='/js/jQuery.UCSelect.js'/>" type="text/javascript"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('select[name=trainingSpeId]').UCFormSelect();
        $(".UCSelect").css("width","107px").css("margin-left","0px").css("margin-right","108px");
        $(".SelectVal").css("width","230px");
        $(".SelectBox").css("width","216%");
        toPage();
    });

    function toPage(page) {
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("doctorListZi", "<s:url value='/jsres/supervisio/chooseLeaderList'/>?userFlows=${userFlows}", $("#searchForm").serialize(), false);
    }

    function saveLeader() {
        var num=0;
        var userFlows="";
        var userNames="";
        var itemIdList = $("input");
        for (var i = 0; i < itemIdList.length; i++) {
            if (itemIdList[i].getAttribute("name") == "userFlow"){
                if ( itemIdList[i].checked==true){
                    userFlows=userFlows+$(itemIdList[i]).val();
                    userFlows=userFlows+",";
                    userNames=userNames+itemIdList[i].getAttribute("itemName");
                    userNames=userNames+",";
                    num++;
                }
            }
        }
        if (num > 4){
            jboxTip("最多选择四位专家！");
            return;
        }
        if (userNames !=""){
            userNames=userNames.substring(0,userNames.length-1);
        }
        if (userFlows!=""){
            userFlows=userFlows.substring(0,userFlows.length-1);
        }
        window.parent.frames["jbox-message-iframe"].$("#userName")[0].value = userNames;
        window.parent.frames["jbox-message-iframe"].$("#userFlow")[0].value = userFlows;
        jboxClose();
    }
</script>
<div class="div_search" style="width: 95%;line-height:normal;">
    <form id="searchForm" style="margin-left: -35px;">
        <input type="hidden" id="currentPage" name="currentPage"/>
        <table class="searchTable" style="width: 100%;border-collapse:separate; border-spacing:0px 10px;">
            <tr>
                <td class="td_left">
                    <nobr>专家姓名：</nobr>
                </td>
                <td>
                    <input type="text" name="userName" value="${param.userName}" class="input"
                           style="width: 170px;margin-left: 0px; height: 30px;"/>
                </td>

                <td class="td_left">
                    <nobr style="margin-left: -6px">专业：</nobr>
                </td>
                <td>
                    <select name="trainingSpeId" class="select" id="trainingSpeId" style="width: 150px;" multiple="multiple">
                        <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                            <c:if test="${'3500' ne dict.dictId and '3700' ne dict.dictId}">
                                <option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}">selected</c:if>>${dict.dictName}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td colspan="8" style="text-align: right">
                    <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>&#12288;
                    <input class="btn_green" type="button" value="保&#12288;存" onclick="saveLeader();"/>&#12288;
                </td>
            </tr>
        </table>
        <div style="margin-top: -25px;margin-left: 15px;"> <font color="red">* 最多只能选择四位专家</font> </div>
    </form>
</div>
<div id="doctorListZi" style="width: 95%">

</div>