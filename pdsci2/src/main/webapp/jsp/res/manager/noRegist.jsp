
<script>
    $(document).ready(function(){
        var n="已报名学员（${fn:length(lectureScanRegists)+0}）";
        $("#noRegist").html(n);
    });
</script>

<div class="div_table" style="padding: 10px 0px 0px;">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <th style="text-align: center">序号</th>
            <th style="text-align: center">姓名</th>
            <c:if test="${applicationScope.sysCfgMap['sys_index_url'] eq '/inx/jszy'}">
                <th style="text-align: center">培训专业</th>
                <th style="text-align: center">对应专业</th>
            </c:if>
            <c:if test="${applicationScope.sysCfgMap['sys_index_url'] ne '/inx/jszy'}">
                <th style="text-align: center">学员类型</th>
                <th style="text-align: center">培训专业</th>
            </c:if>
            <th style="text-align: center">届别</th>
        </tr>
        <c:forEach items="${lectureScanRegists}" var="regist" varStatus="s">
            <tr>
                <td style="text-align: center">${s.index+1}</td>
                <td style="text-align: center">${regist.operUserName}</td>
                <td style="text-align: center">${regist.lectureScanRegists}</td>
                <td style="text-align: center">${regist.trainingSpeName}</td>
                <td style="text-align: center">${regist.sessionNumber}</td>
            </tr>
        </c:forEach>
        <c:if test="${empty lectureScanRegists}">
            <tr>
                <td colspan="5" style="text-align: center;">暂无信息</td>
            </tr>
        </c:if>
    </table>
    <c:if test="${not empty lectureScanRegists}">
        <div align="center" style="margin-top: 15px">
            <input type="button" class="btn_green" onclick="exportInfo('noRegist','${lectureInfo.randomSignIn}');" value="导&#12288;出"/>
        </div>
    </c:if>
</div>