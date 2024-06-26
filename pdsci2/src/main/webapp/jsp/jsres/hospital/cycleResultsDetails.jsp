<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="login" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
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
        .ith{height: 40px;line-height: 40px;padding-left: 10px;}
    </style>
    <script type="text/javascript">
        $(function(){
            if("${empty param.recTypeId}"=="true"){
                $("#reducationTab li:first").click();
            }

        });
        function setType(flag){
            $("#recTypeId").val(flag);
            dataChange();
        }
        function dataChange(){
            /* .serialize() */
            jboxStartLoading();
            var form=$("#searchForm");
            form.submit();
        }
    </script>
</head>
<body>
<div class="main_hd" >
    <div class="title_tab" style="margin-top: 10px;">
        <ul id="reducationTab">
            <li class="${param.recTypeId eq afterRecTypeEnumDOPS.id?'tab_select':'tab'}" onclick="setType('${afterRecTypeEnumDOPS.id}');"><a>${afterRecTypeEnumDOPS.name}</a></li>
            <li class="${param.recTypeId eq afterRecTypeEnumMini_CEX.id?'tab_select':'tab'}" onclick="setType('${afterRecTypeEnumMini_CEX.id}');"><a>${afterRecTypeEnumMini_CEX.name}</a></li>
            <li class="${param.recTypeId eq afterRecTypeEnumAfterEvaluation.id?'tab_select':'tab'}" onclick="setType('${afterRecTypeEnumAfterEvaluation.id}');"><a>${afterRecTypeEnumAfterEvaluation.name}</a></li>
            <li class="${param.recTypeId eq resRecTypeEnumAfterSummary.id?'tab_select':'tab'}" onclick="setType('${resRecTypeEnumAfterSummary.id}');"><a><%--${resRecTypeEnumAfterSummary.name}--%>出科考核表附件</a></li>
        </ul>
    </div>
</div>
<div class="div_search">
    <form id="searchForm" action="<s:url value='/jsres/doctorRecruit/resultsDetails'/>" method="post">
        <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}">
        <input type="hidden" id="resultFlow" name="resultFlow" value="${param.resultFlow}">
    </form>
</div>
<div class="search_table" style="overflow-x: auto;height: 380px;">
    <c:if test="${param.recTypeId==afterRecTypeEnumDOPS.id}">
        <c:if test="${not empty resRecList}">
            <c:forEach items="${resRecList}" var="rec">
                <jsp:include page="/jsp/jsres/teacher/dops.jsp">
                    <jsp:param name="style" value="flase"/>
                    <jsp:param name="noHead" value="true"/>
                    <jsp:param name="recFlow" value="${rec.recFlow}"/>
                </jsp:include>
            </c:forEach>
        </c:if>
        <c:if test="${empty resRecList}">
            <div style="text-align: center;">暂无记录！</div>
        </c:if>
    </c:if>
    <c:if test="${param.recTypeId==afterRecTypeEnumMini_CEX.id}">
        <c:if test="${not empty resRecList}">
            <c:forEach items="${resRecList}" var="rec">
                <%--<div class="main_hd" style="margin-top: -20px;padding-bottom: 15px;">--%>
                    <%--<h2>${rec.deptName}</h2>--%>
                    <%--<div class="title_tab">--%>
                    <%--</div>--%>
                <%--</div>--%>
                <jsp:include page="/jsp/jsres/teacher/mini_cex.jsp">
                    <jsp:param name="style" value="flase"/>
                    <jsp:param name="noHead" value="true"/>
                    <jsp:param name="recFlow" value="${rec.recFlow}"/>
                </jsp:include>
            </c:forEach>
        </c:if>
        <c:if test="${empty resRecList}">
            <div style="text-align: center;">暂无记录！</div>
        </c:if>
    </c:if>
    <c:if test="${param.recTypeId==afterRecTypeEnumAfterEvaluation.id}">
        <c:if test="${not empty resRecList}">
            <c:forEach items="${resRecList}" var="rec">
                <jsp:include page="/jsp/jsres/teacher/evaluation.jsp">
                    <jsp:param name="noHead" value="true"/>
                    <jsp:param name="recFlow" value="${rec.recFlow}"/>
                </jsp:include>
            </c:forEach>
        </c:if>
        <c:if test="${empty resRecList}">
            <div style="text-align: center;">暂无记录！</div>
        </c:if>
    </c:if>
    <c:if test="${param.recTypeId==resRecTypeEnumAfterSummary.id}">
        <c:if test="${empty imageList}" >
            <div style="text-align:center;">暂无记录! </div>
        </c:if>
        <c:forEach items="${imageList}" var="list">
            <div style="margin-left: 15px; margin-top:10px;margin-bottom:5px;  width: 190px;height: 180px;float: left;text-align: center;">
                <a target="_blank" href="${list.imageUrl}"><img src="${list.imageUrl}" width="100%" height="100%"/> </a>
            </div>
        </c:forEach>
    </c:if>
</div>
<div style="text-align: center;"><input type="button" class="btn_green" value="关&#12288;闭" onclick="top.jboxClose();"/></div>
</body>
</html>