<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="jquery_datePicker" value="true"/>
</jsp:include>

<script type="text/javascript">
    $(document).ready(function (){
        if(!'${param.ishos}' || '${param.ishos}' == 'N') {
            $('#sessionNumber').datepicker({
                startView: 2,
                maxViewMode: 2,
                minViewMode: 2,
                format: 'yyyy'
            });
            $('#sessionNumber').datepicker().on("changeDate.datepicker.amui", function (event) {
                if('${param.viewFlag}' != 'Y') {
                    var curYear = $('#sessionNumber').val();
                    if (curYear == new Date().getFullYear()) {
                        $("#sessionDiv").css("display", "inline");
                    } else {
                        $("#sessionDiv").css("display", "none");
                    }
                }
                getInfo();
            });
        }
        $("#baseInfoManage").css("display", "");
        getInfo();
    })
    ;


    function getInfo() {
        var loading = jboxStartLoading();
        jboxPostLoad("basicInfoMain", "<s:url value='/jsres/base/findAllBaseInfo'/>?viewFlag=${param.viewFlag}&baseInfoName=${GlobalConstant.BASIC_INFO}&baseFlow=${param.baseFlow}&ishos=${ishos}&baseInfoMain=Y", $("#searchForm").serialize(), false);
        loading.close().remove();
    }

    function duplicateBeforeYear() {
        var loading = jboxStartLoading();
        var beforeYear = new Date().getFullYear() - 1;
        $("#sessionNumber").val('' + beforeYear);
        jboxPostLoad("hosContent", "<s:url value='/jsres/base/findAllBaseInfo'/>?viewFlag=${param.viewFlag}&baseInfoName=${GlobalConstant.BASIC_MAIN_ALL}&baseFlow=${param.baseFlow}&ishos=${ishos}", $("#searchForm").serialize(), false);
        loading.close().remove();
    }
</script>
<style>
    .auditPass {
        height: 30px;
        width: 100px
    }
    .infoAudit h4 {
        color: #000000;
        font: 15px 'Microsoft Yahei';
        font-weight: 500;
    }

    .infoAudit table th {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 500;
    }
    .infoAudit table td {
        color: #000000;
        font: 14px 'Microsoft Yahei';
        font-weight: 400;
    }
</style>
</head>
<body>
<div class="infoAudit" style="height: auto">
        <%--<div>
            <div style="width: 4px;height: 20px;background-color: #44b549"></div>
            <div style="margin-left: 12px;margin-top: -20px;color: #000000; font: 15px 'Microsoft Yahei'; font-weight: 500;">基地信息</div>
        </div>--%>
    <div style="margin: 0 30px 20px 0px; text-align: right;">
        <form id="searchForm">
            <input type="hidden" name="orgFlow" value="${orgFlow}">
            <input type="hidden" name="ishos" value="${ishos}">
            <c:set var="sessionShow" value="none"></c:set>
            <c:if test="${param.viewFlag != GlobalConstant.FLAG_Y and pdfn:getCurrYear() eq sessionNumber}">
                <c:set var="sessionShow" value="inline"></c:set>
            </c:if>
            <div id="sessionDiv" style="display: ${sessionShow}">
                <input type="button" class="btn_green"  value="复制上一年信息" onclick="duplicateBeforeYear()" />
            </div>
            &nbsp;&nbsp;
            <label style="color: #000000; font: 14px 'Microsoft Yahei'; font-weight: 400;">年份：</label>
            <c:if test="${not empty ishos}">
                <label>${sessionNumber}</label>
                <input class="input" name="sessionNumber" id="sessionNumber" type="hidden"
                       value="${sessionNumber}"/>
            </c:if>
            <c:if test="${empty ishos}">
                <input class="input" name="sessionNumber" id="sessionNumber" style="width: 100px;height: 29px;padding: 0px;"
                       value="${empty param.sessionNumber?pdfn:getCurrYear():param.sessionNumber}"/>
            </c:if>
        </form>
    </div>
    <div id="basicInfoMain" style="width: 100%;"></div>
</div>
</body>
</html>