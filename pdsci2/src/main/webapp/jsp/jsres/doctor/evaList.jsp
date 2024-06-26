<style type="text/css">
    .bg{
        width: 60px;
        height: 16px;
        background: url(<s:url value='/jsp/jsres/activity/img/star_gray.png'/>);
        margin-left: 25px;
    }
    .img{
        width: 20px;
        height: 20px;
        margin-left: 5px;
    }
    .over{
        height:16px;
        background:url(<s:url value='/jsp/jsres/activity/img/star_org.png'/>) no-repeat;
    }
    .nowrapTb th,.nowrapTb td{
        white-space: nowrap;
    }
</style>
<script>

    $(document).ready(function(){
        var n="已扫码学员（${fn:length(lectureScanRegists)+0}）";
        $("#evaList").html(n);
    });
</script>
<div class="basic" style="margin-top: 5px;max-height: 370px;overflow: auto;">
    <table class="grid nowrapTb" width="100%">
        <tr style="text-align: center">
            <th style="width: 8%;text-align: center;">序号</th>
            <th style="width: 10%;text-align: center;">姓名</th>
            <th style="width: 12%;text-align: center;">签到时间</th>
            <c:forEach items="${randomSignList}" var="rsl">
                <th style="width: 12%;text-align: center;">随机签到</th>
            </c:forEach>
            <th style="width: 12%;text-align: center;">签退时间</th>
            <th style="width: 9%;text-align: center;">培训类型</th>
            <th style="width: 9%;text-align: center;">培训专业</th>
            <th style="width: 10%;text-align: center;">人员类型</th>
            <th style="width: 5%;text-align: center;">年级</th>
            <th style="width: 15%;word-wrap:break-word;word-break:break-all;text-align: center;overflow: hidden;text-overflow: ellipsis;">评价内容</th>
            <th style="width: 10%;word-wrap:break-word;word-break:break-all;text-align: center;">评分内容</th>
        </tr>
        <c:forEach items="${lectureScanRegists}" var="eva" varStatus="s">
            <tr>
                <td style="text-align: center">${s.index+1}</td>
                <td style="text-align: center">${eva.operUserName}</td>
                <td style="text-align: center">${pdfn:transTime(eva.scanTime)}</td>
                <c:forEach items="${randomSignList}" var="k">
                <c:set value="${eva.operUserFlow}${k.randomFlow}" var="key"></c:set>
                    <td style="text-align: center">${scanMap[key]}</td>
                </c:forEach>
                <td style="text-align: center">${pdfn:transTime(eva.scan2Time)}</td>
                <td style="text-align: center">${eva.trainingTypeName}</td>
                <td style="text-align: center">${eva.trainingSpeName}</td>
                <td style="text-align: center">${doctorMap[eva.recordFlow].doctorTypeName}</td>
                <td style="text-align: center">${eva.sessionNumber}</td>
                <td style="text-align: center">${(empty evaDetailMap[eva.operUserFlow].evaContent)?"未评价":evaDetailMap[eva.operUserFlow].evaContent}</td>
                <td style="text-align: center">
                    <c:if test="${!empty evaDetailMap[eva.operUserFlow].evaScore}">
                        <div class="bg" style="margin-left:0;">
                            <div class="over" style="width:${12*evaDetailMap[eva.operUserFlow].evaScore}px"></div>
                        </div>
                    </c:if>
                    <c:if test="${empty evaDetailMap[eva.operUserFlow].evaScore}">
                        未评分
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        <c:if test="${empty lectureScanRegists}">
            <tr>
                <td colspan="99" style="text-align: center">暂无信息</td>
            </tr>
        </c:if>
    </table>
</div>
<c:if test="${not empty lectureScanRegists}">
    <div align="center" style="margin-top: 15px">
        <input type="button" class="btn_green" onclick="exportInfo('evaList','${lectureInfo.randomSignIn}');" value="导&#12288;出"/>
    </div>
</c:if>



