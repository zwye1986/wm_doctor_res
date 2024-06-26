<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/common/htmlhead.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="jbox" value="true" />
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_fixedtableheader" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER{background-color: #eee;}
    .cur{color:red;}
</style>
<script type="text/javascript">
    $(document).ready(function(){
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode:2,
            format:'yyyy'
        });
        $('#graduationYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode:2,
            format:'yyyy'
        });
        $(".base_info").css("text-align","center");
    });
    function doclose()
    {
        top.jboxClose();
    }
    //技能成绩详情
    function skillScoreDetail(doctorFlow, scoreFlow, scoreType, scoreYear) {
        var url = "<s:url value='/res/examCfg/scoreDetail?scoreFlow='/>" + scoreFlow + "&doctorFlow=" + doctorFlow + "&scoreType=" + scoreType + "&scoreYear=" + scoreYear;
        if (scoreType == "theoryScore") {
            jboxOpen(url, "理论成绩详情", 800, 400);
        } else {
            jboxOpen(url, "技能成绩详情", 1200, 400);
        }
    }
</script>
<div class="mainright">
    <div class="content">
        <div style=" width:100%;text-align: left;margin-top:8px;margin-bottom: 5px;"><font style="font-size: 18px;">年度技能成绩</font></div>
        <table style=" width:100%;text-align: center;" class="xllist" >
            <tbody>
            <tr style=" width:100%;text-align: center;">
                <th style="text-align: center;width:40%;">考核年份</th>
                <th style="text-align: center;width:60%;">成绩</th>
            </tr>
            <c:forEach items="${doctorSkillScores}" var="resScore">
            <tr>
                <td  style="text-align: center;">${resScore.scorePhaseId}</td>
                <td  style="text-align: center;">
                    <c:if test="${not empty resScore.skillScore }">
                        <a onclick="skillScoreDetail('${sysUser.userFlow}','${resScore.scoreFlow}','skillScore','${resScore.scorePhaseId}');" style="cursor: pointer;">
                                ${resScore.skillScore eq '1'?'合格':(resScore.skillScore eq '0'?'不合格':'缺考')}
                        </a>
                    </c:if>
                    <c:if test="${empty resScore.skillScore }">
                        --
                    </c:if>
                </td>
            </tr>
            </c:forEach>
            <c:if test="${empty doctorSkillScores}">
            <tr>
                <td colspan="4" align="center">无记录！</td>
            </tr>
            </c:if>
            </tbody>
        </table>
    </div>
</div>