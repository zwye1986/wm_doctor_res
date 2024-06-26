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
</style>
<script>

    $(document).ready(function(){
        var n="已扫码师资人员（${fn:length(lectureScanRegists)+0}）";
        $("#evaList2").html(n);
    });
</script>
<div class="basic" style="margin-top: 5px;max-height: 370px;overflow: auto;">
    <table class="grid" width="100%">
        <tr style="text-align: center">
            <th style="width: 8%;text-align: center;">序号</th>
            <th style="width: 10%;text-align: center;">姓名</th>
            <th style="width: 10%;text-align: center;">签到时间</th>
            <th style="width: 10%;text-align: center;">签退时间</th>
            <th style="width: 8%;text-align: center">科室</th>
            <th style="width: 8%;text-align: center">是否带教</th>
            <th style="width: 8%;text-align: center">是否科主任</th>
            <th style="width: 8%;text-align: center">是否教秘</th>
            <th style="width: 10%;word-wrap:break-word;word-break:break-all;text-align: center;">评价内容</th>
            <th style="width: 10%;word-wrap:break-word;word-break:break-all;text-align: center;">评分内容</th>
        </tr>
        <c:forEach items="${lectureScanRegists}" var="eva" varStatus="s">
            <tr>
                <td style="text-align: center">${s.index+1}</td>
                <td style="text-align: center">${userMap[eva.operUserFlow].userName}</td>
                <td style="text-align: center">${pdfn:transTime(eva.scanTime)}</td>
                <td style="text-align: center">${pdfn:transTime(eva.scan2Time)}</td>
                <td style="text-align: center">${userMap[eva.operUserFlow].deptName}</td>
                <td style="text-align: center">${teaMap[eva.operUserFlow] eq 'Y'?'是':'否'}</td>
                <td style="text-align: center">${headMap[eva.operUserFlow] eq 'Y'?'是':'否'}</td>
                <td style="text-align: center">${secretaryMap[eva.operUserFlow] eq 'Y'?'是':'否'}</td>
                <td style="text-align: center">${(empty evaDetailMap[eva.operUserFlow].evaContent)?"未评价":evaDetailMap[eva.operUserFlow].evaContent}</td>
                <td style="text-align: center">
                    <c:if test="${!empty evaDetailMap[eva.operUserFlow].evaScore}">
                        <div class="bg">
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
        <input type="button" class="btn_green" onclick="exportInfo('evaList2','${lectureInfo.randomSignIn}');" value="导&#12288;出"/>
    </div>
</c:if>



