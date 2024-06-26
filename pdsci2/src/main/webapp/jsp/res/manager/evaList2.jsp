<script>
    $(document).ready(function(){
        var n="已扫码师资人员（${fn:length(lectureScanRegists)+0}）";
        $("#evaList2").html(n);
    });

    // 显示评价信息
    function showEval(scanRegistFlow) {
        $("."+scanRegistFlow).toggle();
    }
</script>

<style type="text/css">
    .bg2{
        width: 60px;
        height: 16px;
        background: url(<s:url value='/jsp/res/activity/img/star_gray.png'/>);
        margin-left: 15px;
    }
    .over{
        height:16px;
        background:url(<s:url value='/jsp/res/activity/img/star_org.png'/>) no-repeat;
    }
</style>

<div class="div_table" style="padding: 10px 0px 0px;overflow-y: auto;height: 550px;">
    <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 10px;">
        <c:forEach items="${lectureTargetEvalList}" var="target" varStatus="s">
            <c:if test="${s.first}">
                <tr>
                    <th style="text-align: right;border: 1px solid #e7e7eb;" rowspan="${fn:length(lectureTargetEvalList)}">指标均分：</th>
                    <td style="text-align: center;border: 1px solid #e7e7eb;">
                            ${target.targetName}
                    </td>
                    <td style="text-align: left;border: 1px solid #e7e7eb;">
                        <div class="bg2">
                            <div class="over" style="width:${12*target.evalScore}px"></div>
                        </div>
                    </td>
                </tr>
            </c:if>
            <c:if test="${!s.first}">
                <tr>
                    <td style="text-align: center;border: 1px solid #e7e7eb;">
                            ${target.targetName}
                    </td>
                    <td style="text-align: left;border: 1px solid #e7e7eb;">
                        <div class="bg2">
                            <div class="over" style="width:${12*target.evalScore}px"></div>
                        </div>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </table>
    <div style="width:845px; height:400px; overflow-x:scroll;">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <th style="min-width:60px;text-align: center;">序号</th>
            <th style="min-width:80px;text-align: center;">姓名</th>
            <th style="min-width:80px;text-align: center;">身份证号</th>
            <th style="min-width:80px;text-align: center;">签到时间</th>
            <c:forEach items="${randomSignList}" var="rsl">
                <th style="min-width: 80px; text-align:center;">随机签到</th>
            </c:forEach>
            <th style="min-width:80px; text-align: center;">签退时间</th>
            <th style="min-width:80px; text-align: center;">基地</th>
            <th style="min-width:80px;text-align: center">科室</th>
            <th style="min-width:80px;text-align: center">是否带教</th>
            <th style="min-width:80px;text-align: center">是否教秘</th>
            <th style="min-width:80px;text-align: center">是否科主任</th>
            <th style="min-width:80px;word-wrap:break-word;word-break:break-all;text-align: center;">评分</th>
        </tr>
        <c:forEach items="${lectureScanRegists}" var="eva" varStatus="s">
            <tr onclick="showEval('${eva.recordFlow}')">
                <td style="text-align: center">${s.index+1}</td>
                <td style="text-align: center">${userMap[eva.operUserFlow].userName}</td>
                <td style="text-align: center">${userMap[eva.operUserFlow].idNo}</td>
                <td style="text-align: center">${pdfn:transTime(eva.scanTime)}</td>
                <c:forEach items="${randomSignList}" var="k">
                    <c:set value="${eva.operUserFlow}${k.randomFlow}" var="key"></c:set>
                    <td style="text-align: center">${scanMap[key]}</td>
                </c:forEach>
                <td style="text-align: center">${pdfn:transTime(eva.scan2Time)}</td>
                <td style="text-align: center">${userMap[eva.operUserFlow].orgName}</td>
                <td style="text-align: center">${userMap[eva.operUserFlow].deptName}</td>
                <td style="text-align: center">${teaMap[eva.operUserFlow] eq 'Y'?'是':'否'}</td>
                <td style="text-align: center">${secretaryMap[eva.operUserFlow] eq 'Y'?'是':'否'}</td>
                <td style="text-align: center">${headMap[eva.operUserFlow] eq 'Y'?'是':'否'}</td>
                <td style="text-align: center" >${eva.evalScore}</td>
            </tr>
            <c:forEach items="${lectureTargetEvalList}" var="t" varStatus="s">
                <tr class="${eva.recordFlow}" style="display: none;">
                    <td></td>
                    <td colspan="2" style="text-align: center;">
                            ${t.targetName}
                    </td>
                    <td colspan="5" style="text-align: left;">
                        <c:set var="key" value="${eva.recordFlow}${t.targetFlow}"></c:set>
                        <div class="bg2">
                            <div class="over" style="width:${12*evaDetailMap[key]}px"></div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
        </c:forEach>
        <c:if test="${empty lectureScanRegists}">
            <tr>
                <td colspan="10" style="text-align: center;">暂无信息</td>
            </tr>
        </c:if>
    </table>
    </div>
    <c:if test="${not empty lectureScanRegists}">
        <div align="center" style="margin-top: 15px">
            <input type="button" class="btn_green" onclick="exportInfo('evaList2','${lectureInfo.randomSignIn}');" value="导&#12288;出"/>
        </div>
    </c:if>
</div>