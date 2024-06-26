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
        var n="已扫码学员（${fn:length(lectureScanRegists)+0}）";
        $("#evaList").html(n);
    });
</script>
<div class="basic" style="margin-top: 5px;max-height: 370px;overflow: auto;">
    <table class="grid" width="100%">
        <tr style="text-align: center">
            <th style="width: 8%;text-align: center;">序号</th>
            <th style="width: 10%;text-align: center;">姓名</th>
            <c:forEach items="${randomSignList}" var="k">
                <th style="width: 12%;text-align: center;">随机签到</th>
            </c:forEach>
            <th style="width: 9%;text-align: center;">培训类型</th>
            <th style="width: 9%;text-align: center;">培训专业</th>
            <th style="width: 10%;text-align: center;">人员类型</th>
            <th style="width: 5%;text-align: center;">年级</th>
        </tr>
        <c:forEach items="${randomScanList}" var="eva" varStatus="s">
            <tr>
                <td style="text-align: center">${s.index+1}</td>
                <td style="text-align: center">${eva.operUserName}</td>
                <c:forEach items="${randomSignList}" var="k">
                    <c:set value="${k.randomFlow}" var="rf"></c:set>
                    <c:if test="${rf == s.randomFlow}">
                        <td style="text-align: center">${pdfn:transTime(eva.scanTime)}</td>
                    </c:if>
                    <c:if test="${rf != s.randomFlow}">
                        <td style="text-align: center">未签到</td>
                    </c:if>
                </c:forEach>
                <td style="text-align: center">${eva.trainingTypeName}</td>
                <td style="text-align: center">${eva.trainingSpeName}</td>
                <td style="text-align: center">${doctorMap[eva.recordFlow].doctorTypeName}</td>
                <td style="text-align: center">${eva.sessionNumber}</td>
            </tr>
        </c:forEach>
        <c:if test="${empty randomScanList}">
            <tr>
                <td colspan="99" style="text-align: center">暂无信息</td>
            </tr>
        </c:if>
    </table>
</div>
<c:if test="${not empty randomScanList}">
    <div align="center" style="margin-top: 15px">
        <input type="button" class="btn_green" onclick="exportInfo('evaList','${lectureInfo.randomSignIn}');" value="导&#12288;出"/>
    </div>
</c:if>



