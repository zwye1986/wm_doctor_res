
<script>
    $(document).ready(function(){
        var n="已报名学员（${fn:length(lectureScanRegists)+0}）";
        $("#noRegist").html(n);
    });
</script>
<div class="basic" style="margin-top: 5px;max-height: 370px;overflow: auto;">
    <table class="grid" width="100%">
        <tr>
            <th style="text-align: center">序号</th>
            <th style="text-align: center">姓名</th>
            <th style="text-align: center">培训类型</th>
            <th style="text-align: center">培训专业</th>
            <th style="text-align: center">人员类型</th>
            <th style="text-align: center">年级</th>
        </tr>
        <c:forEach items="${lectureScanRegists}" var="regist" varStatus="s">
            <tr>
                <td style="text-align: center">${s.index+1}</td>
                <td style="text-align: center">${regist.operUserName}</td>
                <td style="text-align: center">${regist.trainingTypeName}</td>
                <td style="text-align: center">${regist.trainingSpeName}</td>
                <td style="text-align: center">${doctorMap[regist.recordFlow].doctorTypeName}</td>
                <td style="text-align: center">${regist.sessionNumber}</td>
            </tr>
        </c:forEach>
        <c:if test="${empty lectureScanRegists}">
            <tr>
                <td colspan="6" style="text-align: center">暂无信息</td>
            </tr>
        </c:if>
    </table>
</div>
<c:if test="${not empty lectureScanRegists}">
    <div align="center" style="margin-top: 15px">
        <input type="button" class="btn_green" onclick="exportInfo('noRegist','${lectureInfo.randomSignIn}');" value="导&#12288;出"/>
    </div>
</c:if>