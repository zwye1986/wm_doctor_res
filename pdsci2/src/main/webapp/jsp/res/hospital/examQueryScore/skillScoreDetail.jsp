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
</script>
<div class="mainright">
    <div class="content">
        <table style=" width:100%;text-align: center;" class="xllist" >
            <c:if test="${scoreType eq 'theoryScore'}">
            <colgroup>
                <col width="25%"/>
                <col width="25%"/>
                <col width="25%"/>
                <col width="25%"/>
            </colgroup>
            <tbody>
            <tr style=" width:100%;text-align: center;">
                <th style="text-align: center;">姓名</th>
                <th style="text-align: center;">培训专业</th>
                <th style="text-align: center;">成绩年份</th>
                <th style="text-align: center;">成绩</th>
            </tr>
            <c:forEach items="${theoryList}" var="resScore">
            <tr>
                <td  style="text-align: center;">${user.userName}</td>
                <td  style="text-align: center;">${resDoctor.trainingSpeName}</td>
                <td  style="text-align: center;">${resScore.scorePhaseId}</td>
                <td  style="text-align: center;">${resScore.theoryScore}</td></tr>
            </c:forEach>
            <c:if test="${empty theoryList}">
            <tr>
                <td colspan="4" align="center">无记录！</td>
            </tr>
            </c:if>
            </c:if>
            <c:if test="${scoreType eq 'skillScore'}">
            <colgroup>
                <col width="6%"/>
                <col width="6%"/>
                <col width="6%"/>
                <col width="6%"/>
                <col width="4%"/>
                <col width="4%"/>
                <col width="4%"/>
                <col width="4%"/>
                <col width="4%"/>
                <col width="4%"/>
                <col width="4%"/>
                <col width="4%"/>
                <col width="4%"/>
                <col width="4%"/>
                <col width="4%"/>
                <col width="4%"/>
            </colgroup>
            <tbody>
            <tr style=" width:100%;text-align: center;">
                <th style="text-align: center;">姓名</th>
                <th style="text-align: center;">培训专业</th>
                <th style="text-align: center;">成绩年份</th>
                <th style="text-align: center;">第一站</th>
                <th style="text-align: center;">第二站</th>
                <th style="text-align: center;">第三站</th>
                <th style="text-align: center;">小计</th>
                <th style="text-align: center;">第四站</th>
                <th style="text-align: center;">第五站</th>
                <th style="text-align: center;">第六站</th>
                <th style="text-align: center;">第七站</th>
                <th style="text-align: center;">第八站</th>
                <th style="text-align: center;">小计</th>
                <th style="text-align: center;">第九站</th>
                <th style="text-align: center;">总分</th>
                <th style="text-align: center;">是否通过</th>
            </tr>
            <c:forEach items="${skillList}" var="resScore">
                <c:set var="extScore" value="${extScoreMap[resScore.scoreFlow]}"></c:set>
                <c:set var="firstStationScore" value="${extScore.firstStationScore}"></c:set>
                <c:set var="secondStationScore" value="${extScore.secondStationScore}"></c:set>
                <c:set var="thirdStationScore" value="${extScore.thirdStationScore}"></c:set>
                <c:set var="fourthStationScore" value="${extScore.fourthStationScore}"></c:set>
                <c:set var="fifthStationScore" value="${extScore.fifthStationScore}"></c:set>
                <c:set var="sixthStationScore" value="${extScore.sixthStationScore}"></c:set>
                <c:set var="seventhStationScore" value="${extScore.seventhStationScore}"></c:set>
                <c:set var="eighthStationScore" value="${extScore.eighthStationScore}"></c:set>
                <c:set var="ninthStationScore" value="${extScore.ninthStationScore}"></c:set>
                <c:set var="xiji1" value="${firstStationScore+secondStationScore+thirdStationScore}"></c:set>
                <c:set var="xiji2" value="${fourthStationScore+fifthStationScore+sixthStationScore+seventhStationScore+eighthStationScore}"></c:set>
                <c:set var="all" value="${firstStationScore+secondStationScore+thirdStationScore+fourthStationScore+fifthStationScore+sixthStationScore
					 +seventhStationScore+eighthStationScore+ninthStationScore }"></c:set>

                <tr>
                    <td  style="text-align: center;">${user.userName}</td>
                    <td  style="text-align: center;">
                        <c:if test="${jszyFlag ne 'Y'}">${resDoctor.trainingSpeName}</c:if>
                        <c:if test="${jszyFlag eq 'Y'}">${resDoctor.doctorCategoryName}</c:if>
                    </td>
                    <td  style="text-align: center;">${resScore.scorePhaseId}</td>
                    <td  style="text-align: center;">${firstStationScore}</td>
                    <td  style="text-align: center;">${secondStationScore}</td>
                    <td  style="text-align: center;">${thirdStationScore}</td>
                    <td  style="text-align: center;">
                        <fmt:formatNumber type="number" value="${xiji1}" pattern="0.0" maxFractionDigits="1"/>
                    </td>
                    <td  style="text-align: center;">${fourthStationScore}</td>
                    <td  style="text-align: center;">${fifthStationScore}</td>
                    <td  style="text-align: center;">${sixthStationScore}</td>
                    <td  style="text-align: center;">${seventhStationScore}</td>
                    <td  style="text-align: center;">${eighthStationScore}</td>
                    <td  style="text-align: center;">
                        <fmt:formatNumber type="number" value="${xiji2}" pattern="0.0" maxFractionDigits="1"/>
                    </td>
                    <td  style="text-align: center;">${ninthStationScore}</td>
                    <td  style="text-align: center;">
                        <fmt:formatNumber type="number" value="${all}" pattern="0.0" maxFractionDigits="1"/>
                    </td>
                    <td  style="text-align: center;">${resScore.skillScore eq '1'?'合格':(resScore.skillScore eq '0'?'不合格':'缺考')}</td>
                </tr>

            </c:forEach>
            <c:if test="${empty skillList}">
                <tr>
                    <td colspan="16" align="center">无记录！</td>
                </tr>
            </c:if>
            </c:if>
            </tbody>
        </table>
    </div>
    <div class="button">
        <input type="button" class="search" onclick="doclose();" value="关&#12288;闭"/>
    </div>
</div>