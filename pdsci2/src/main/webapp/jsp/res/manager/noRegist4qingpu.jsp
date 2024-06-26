
<div style="position:absolute; height:540px;width: 100%; overflow:auto">
            <table class="basic" style="width: 100%">
                <tr>
                    <th style="text-align: center">序号</th>
                    <th style="text-align: center">姓名</th>
                    <th style="text-align: center">培训类型</th>
                    <th style="text-align: center">培训专业</th>
                    <th style="text-align: center">届别</th>
                </tr>
                <c:forEach items="${lectureScanRegists}" var="regist" varStatus="s">
                    <tr>
                        <td style="text-align: center">${s.index+1}</td>
                        <td style="text-align: center">${regist.operUserName}</td>
                        <td style="text-align: center">${regist.trainingTypeName}</td>
                        <td style="text-align: center">${regist.trainingSpeName}</td>
                        <td style="text-align: center">${regist.sessionNumber}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty lectureScanRegists}">
                    <tr>
                        <td colspan="5" style="text-align: center">暂无信息</td>
                    </tr>
                </c:if>
            </table>
</div>
