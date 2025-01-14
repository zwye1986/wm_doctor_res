<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<style type="text/css">
    .main_hd h2 {
        color: #000000;
        font: 15px 'Microsoft Yahei';
        font-weight: 400;
    }
</style>
<script type="text/javascript">
    $(document).ready(function() {
        $("li").click(function () {
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
            eval($(this).attr("cusTrigger"));
        });
        if ("${param.liId}" != "") {
            $("#${param.liId}").addClass("tab_select");
        } else {
            $('li').first().addClass("tab_select");
        }
        $(".tab_select a").click();
        <c:if test="${!empty resBase}">
        <c:if test="${resBase.baseStatusId eq baseStatusEnumNotSubmit.id or resBase.baseStatusId eq baseStatusEnumNotPassed.id }">
        $("#submitBtn").show();
        </c:if>
        </c:if>

        <c:if test="${param.viewFlag != GlobalConstant.FLAG_Y  }">
            coopBaseInfo();
        </c:if>

        // $('#sessionNumber').datepicker({
        //     startView: 2,
        //     maxViewMode: 2,
        //     minViewMode: 2,
        //     format: 'yyyy'
        // });
        // $('#sessionNumber').datepicker().on("changeDate.datepicker.amui", function (event) {
        //     var curYear = $("#sessionNumber").val();
        //     coopBaseInfo(curYear);
        // });
    });

    function submitInfo() {
        jboxConfirm("提交后不可修改！请确认修改的信息是否已保存，否则提交的仍是保存前的信息，确认提交?", function () {
            jboxPost("<s:url value='/jsres/base/submitBaseInfo'/>", $("#baseForm").serialize(), function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    setTimeout(function(){
                        $("#submitBtn").hide();
                        $(".tab_select").children().click();
                    },1000);
                }
            } , null , true);
        });
    }

    function getInfo(baseInfoName,baseFlow){
        var resBase = $("#baseStatusId").val();
        if(!resBase){
            loadInfo(baseInfoName,baseFlow);
        }else{
            editInfo(baseInfoName,baseFlow);
        }
    }

    function editInfo(baseInfoName,orgFlow){
        var url="";
        if(baseInfoName=='${trainCategoryTypeEnumAfter2014.id}'||baseInfoName=='${trainCategoryTypeEnumBefore2014.id}'){
            url="<s:url value='/jsres/base/findTrainSpe'/>?editFlag=${GlobalConstant.FLAG_Y}&trainCategoryType="+baseInfoName+"&orgFlow="+orgFlow;
            jboxLoad("trainSpeContent",url,false);
        }else{
            url="<s:url value='/jsres/base/findAllBaseInfo'/>?viewFlag=${param.viewFlag}&editFlag=${GlobalConstant.FLAG_Y}&baseInfoName="+baseInfoName+"&baseFlow="+orgFlow;
            jboxLoad("hosContent", url, false);
        }
    }

    function loadInfo(baseInfoName,baseFlow){
        var r = $("#resBase").val();
        if(baseInfoName != "${GlobalConstant.BASIC_INFO}" && !r){
            $(".tab_select").toggleClass("tab_select tab");
            $("#toptab li:first").toggleClass("tab_select tab");
            jboxTip("请先完善基本信息");
            return false;
        }
        if(${not empty ishos}) {
            var url="<s:url value='/jsres/base/findAllBaseInfo'/>?viewFlag=${param.viewFlag}&baseInfoName="+baseInfoName+"&baseFlow="+baseFlow+"&ishos=${ishos}";
        } else {
            var url="<s:url value='/jsres/base/findAllBaseInfo'/>?viewFlag=${param.viewFlag}&baseInfoName="+baseInfoName+"&baseFlow="+baseFlow;
        }
        jboxLoad("hosContent", url, false);
    }

    function trainSpeInfo(){
        var url="<s:url value='/jsres/base/trainSpeMain'/>?orgFlow=${baseFlow}&isJoin=${isJoin}"+"&ishos=${ishos}";
        jboxLoad("hosContent", url, false);
    }

    function coopBaseInfo(){
        var url="<s:url value='/jsres/base/findCoopBase'/>";
        jboxLoad("hosContent", url, false);
    }

    function commuHospital(){
        jboxLoad("hosContent","<s:url value='/jsp/jsres/hospital/hos/commuHospital.jsp'/>",false);
    }

    function editCoopBase(jointOrgCount, jointFlow) {
        if(!jointFlow && jointOrgCount >= 3) {
            jboxTip("最多只能有三个协同单位！");
            return;
        }
        var orgFlow = $("#baseFlow").val();
        jboxOpen("<s:url value='/jsres/base/editCoopBase'/>?orgFlow=" + orgFlow + "&jointFlow=" + jointFlow, "编辑协同单位", 600, 300);
    }

    // 删除协同关系
    function deleteCoopBase(jointFlow){
        jboxConfirm("确认删除？", function () {
            jboxPost("<s:url value='/jsres/base/deleteCoopBase'/>", {jointFlow: jointFlow}, function (resp) {
                if ("${GlobalConstant.DELETE_SUCCESSED}" == resp) {
                    coopBaseInfo();
                }
            }, null, true);
        });
    }
</script>
<div class="main_hd" id="assistOrgManage" style="display: none">
    <input type="hidden" id="baseStatusId" name="baseStatusId" value="${resBase.baseStatusId}"/>
    <form id="baseForm" style="position:relative;">
        <input type="hidden" name="baseFlow" id="baseFlow" value="${sessionScope.currUser.orgFlow}"/>
        <input type="hidden" id="resBase" value="${resBase.orgFlow}"/>
    </form>
    <c:if test="${GlobalConstant.FLAG_Y != param.viewFlag}">
        <h2>
            协同单位管理
            <!-- 			<input id="submitBtn" class="btn_green" type="button" onclick="submitInfo();" value="提交"  style="display: none; float: right; margin-top: 30px; margin-right: 10px;" /> -->
        </h2>
<%--        <div class="div_table">--%>
<%--            <div style="margin: -20px 10px 20px 0px; text-align: right;">--%>
<%--                <form id="searchForm">--%>
<%--                    <input type="hidden" name="orgFlow" value="${orgFlow}">--%>
<%--                    <input type="hidden" name="ishos" value="${ishos}">--%>
<%--                    <input type="button" class="btn_green"  value="编&#12288;辑" onclick="editCoopBase()" />--%>
<%--                    &nbsp;&nbsp;--%>
<%--                    <label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">年份：</label>--%>
<%--                    <input class="input" name="sessionNumber" id="sessionNumber" style="width: 161px;height: 30px;padding: 0px; padding-left: 4px"--%>
<%--                           value="${empty param.sessionNumber?pdfn:getCurrYear():param.sessionNumber}"/>--%>
<%--                </form>--%>
<%--            </div>--%>
<%--        </div>--%>
    </c:if>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="hosContent" <c:if test="${param.viewFlag eq GlobalConstant.FLAG_Y }">style="height: 700px;overflow: auto;"</c:if>>
    </div>
</div>
