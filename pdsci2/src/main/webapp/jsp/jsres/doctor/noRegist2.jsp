
<script>
    $(document).ready(function(){
        var n="已报名师资（${fn:length(lectureScanRegists)+0}）";
        $("#noRegist2").html(n);
    });
</script>
<div class="basic" style="margin-top: 5px;max-height: 370px;overflow: auto;">
    <table class="grid" width="100%">
        <tr>
            <th style="text-align: center">序号</th>
            <th style="text-align: center">姓名</th>
            <th style="text-align: center">科室</th>
            <th style="text-align: center">是否带教</th>
            <th style="text-align: center">是否科主任</th>
            <th style="text-align: center">是否教秘</th>
        </tr>
        <c:forEach items="${lectureScanRegists}" var="regist" varStatus="s">
            <tr>
                <td style="text-align: center">${s.index+1}</td>
                <td style="text-align: center">${userMap[regist.operUserFlow].userName}</td>
                <td style="text-align: center">${userMap[regist.operUserFlow].deptName}</td>
                <td style="text-align: center">${teaMap[regist.operUserFlow] eq 'Y'?'是':'否'}</td>
                <td style="text-align: center">${headMap[regist.operUserFlow] eq 'Y'?'是':'否'}</td>
                <td style="text-align: center">${secretaryMap[regist.operUserFlow] eq 'Y'?'是':'否'}</td>
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
        <input type="button" class="btn_green" onclick="exportInfo('noRegist2','${lectureInfo.randomSignIn}');" value="导&#12288;出"/>
    </div>
</c:if>