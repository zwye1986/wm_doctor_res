<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jsres/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script type="text/javascript">
    $(document).ready(function(){
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
        });
        if ("${param.liId}" != "") {
            $("#${param.liId}").addClass("tab_select");
        } else {
            $('li').first().addClass("tab_select");
        }
        $(".tab_select").click();
        var role = $("#role").val();
        if ('${role}'=='speAdmin'){
            jboxPostLoad("doctorContent","<s:url value='/res/evaluateHospitalResult/main'/>?gradeRole=ManageDoctorAssess360&role="+role+"&sessionNumber="+${pdfn:getCurrYear()}, null,false)
        }else {
            jboxPostLoad("doctorContent","<s:url value='/res/evaluateHospitalResult/main'/>?gradeRole=doctor&role="+role+"&sessionNumber="+${pdfn:getCurrYear()}, null,false)
        }
    });

    function selTag(gradeRole,trainingTypeId){
        $("#endScore").val("");
        $("#startScore").val("");
        $("#sessionNumber").val(${pdfn:getCurrYear()});
        $("#userName").val("");
        // qselect
        // $(".qselect option").removeAttr("selected")
        // $(".selectTag").removeClass("selectTag");
        // $(obj).parent().addClass('selectTag');
        $("#gradeRole").val(gradeRole);
        $("#trainingTypeId").val(trainingTypeId);
        gradeSearch();
    }
    function gradeSearch(page) {
        var endScore = $("#endScore").val();
        var startScore = $("#startScore").val();
        if(endScore){
            var ex = /^[0-9]+\d*$/;
            if (!ex.test(endScore)) {
                jboxInfo("得分区间内应填入为整数！");
                return;
            }
            if (endScore < 0 || endScore > 100){
                jboxInfo("得分区间应在0~100范围内！");
                return;
            }
        }
        if(startScore){
            var ex = /^[0-9]+\d*$/;
            if (!ex.test(startScore)) {
                jboxInfo("得分区间内应填入为整数！");
                return;
            }
            if (startScore < 0 || startScore > 100){
                jboxInfo("得分区间应在0~100范围内！");
                return;
            }
            if (endScore){
                if (parseInt(endScore) < parseInt(startScore)){
                    jboxInfo("得分区间填写有误！");
                    return;
                }
            }
        }
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        // $('#gradeSearchForm').submit();
        jboxPostLoad("doctorContent","<s:url value='/res/evaluateHospitalResult/main'/>", $("#gradeSearchForm").serialize(),false)
    }

</script>

<div class="main_hd">
    <h2>评价结果查询</h2>
    <div class="title_tab">
        <input type="hidden" id="role" name="role" value="${role}">
        <ul>
            <li class="tab_select" onclick="selTag('doctor');"><a>学员得分</a></li>
            <c:if test="${role ne 'speAdmin'}">
                <li class="tab" onclick="selTag('teacher','');"><a>带教得分</a></li>
                <li class="tab" onclick="selTag('head','');"><a>科室得分</a></li>
                <li class="tab" onclick="selTag('nurse','');"><a>护士评价</a></li>
<%--                <li class="tab" onclick="selTag('patient','');"><a>病人评价</a></li>--%>
<%--                <li class="tab" onclick="selTag('360doctor','');"><a>360学员得分</a></li>--%>
<%--                <li class="tab" onclick="selTag('360teacher','');"><a>360带教得分</a></li>--%>
            </c:if>
<%--            <li class="tab" onclick="selTag('ManageDoctorAssess360','${trainingTypeId}');"><a>管理得分</a></li>--%>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="doctorContent">

    </div>
</div>


