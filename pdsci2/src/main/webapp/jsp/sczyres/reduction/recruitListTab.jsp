<link rel="stylesheet" type="text/css" href="<s:url value='/jsp/jszy/css/exam.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"/>
<script type="text/javascript">
    $(document).ready(function(){
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
        });

        //加载基本信息或培训信息
        var recruitFlow = "${param.recruitFlow}";
        if(recruitFlow != ""){
            $("#li_"+ recruitFlow).click();
        }else{
            $("li:first").click();
        }
    });

    function getDoctorRecruit(recruitFlow, doctorFlow){
        var url = "<s:url value='/sczy/reduction/getReductionInfo?recruitFlow='/>"+recruitFlow + "&doctorFlow=" + doctorFlow;
        jboxLoad("doctorContent", url, false);
    }
</script>
<div class="main_hd">
    <input id="doctorFlow" style="display: none;" value="${doctorFlow}"/>
    <h2>减免申请</h2>
    <div class="title_tab" id="toptab">
        <ul>
            <c:forEach items="${recruitList}" var="recruit">
                <input type="hidden" class="${recruit.catSpeId}" value="${recruit.speId}"/>
                <li class="tab" id="li_${recruit.recruitFlow}" onclick="getDoctorRecruit('${recruit.recruitFlow}','${sessionScope.currUser.userFlow}');"><a>${recruit.catSpeName} （${empty recruit.speName?'-':recruit.speName}）</a></li>
            </c:forEach>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div id="doctorContent" >
        <c:if test="${empty recruitList}">
            <br/>
            &#12288;您未被录取，无法申请减免！
        </c:if>
    </div>
</div>
