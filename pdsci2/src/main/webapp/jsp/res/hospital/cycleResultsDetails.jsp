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
                $("#tags li:first").click();
            }

        });
        function setType(flag,obj){
            $("#tags li").removeClass();
            $(obj).addClass("selectTag");
            $("#recTypeId").val(flag);
            dataChange();
        }
        function dataChange(){
            var url = "<s:url value='/res/manager/doctorRecruit/loadResult?'/>" + $("#searchForm").serialize();
            jboxStartLoading();
            jboxPost("<s:url value='/res/manager/doctorRecruit/loadResult'/>",
                    $("#searchForm").serialize(),
                    function(resp){
                        jboxEndLoading();
                        $("#tagContent").html(resp);
                        $("#tagContent").find("input[type='button']").hide();
                    },null,false);
        }
    </script>
</head>
<body>
<div class="main_hd" >
    <div class="title_tab" style="margin-top: 10px;">
        <ul id="tags">
            <li class="${param.recTypeId eq 'AfterEvaluation'?'selectTag':''}"  onclick="setType('AfterEvaluation',this);">
                <a>出科考核表</a>
            </li>
            <li class="${param.recTypeId eq 'AfterSummary'?'selectTag':''}" onclick="setType('AfterSummary',this);">
                <a>出科小结</a>
            </li>
            <c:set var="monthKey" value="res_${afterRecTypeEnumMonthlyAssessment_clinic.id}_form_flag"/>
            <c:if test="${(GlobalConstant.FLAG_Y eq sysCfgMap[monthKey]) and (GlobalConstant.FLAG_Y eq clinic)}">
              <li class="${param.recTypeId eq 'MonthlyAssessment_clinic'?'selectTag':''}" style="width:auto;text-align: center;"  onclick="setType('MonthlyAssessment_clinic',this);"><a>月度考核表(门诊或医技)</a></li>
           </c:if>
            <c:set var="monthKey2" value="res_${afterRecTypeEnumMonthlyAssessment_inpatientArea.id}_form_flag"/>
            <c:if test="${(GlobalConstant.FLAG_Y eq sysCfgMap[monthKey2]) and (GlobalConstant.FLAG_Y eq area)}">
                <li class="${param.recTypeId eq 'MonthlyAssessment_inpatientArea'?'selectTag':''}" style="width:auto;text-align: center;"  onclick="setType('MonthlyAssessment_inpatientArea',this);"><a>月度考核表(病区)</a></li>
            </c:if>
        </ul>
        <div id="tagContent" style="overflow: auto;padding-bottom: 20px;">

        </div>
    </div>
</div>
<div class="div_search">
    <form id="searchForm" action="<s:url value='/res/manager/doctorRecruit/loadResult'/>" method="post">
        <input type="hidden" id="recTypeId" name="recTypeId" value="${param.recTypeId}">
        <input type="hidden" id="processFlow" name="processFlow" value="${param.processFlow}">
    </form>
</div>


</body>
</html>