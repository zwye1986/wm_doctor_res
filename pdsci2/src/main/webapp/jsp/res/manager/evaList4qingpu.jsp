<script>

    function upload(registFlow,isUpload){
        var url = "<s:url value='/res/lecture4qingpu/upload'/>?registFlow="+registFlow+"&isUpload="+isUpload;
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        jboxMessager(iframe,'查看佐证图片',700,550);
    }

    function getDocEva(doctorFlow,lectureFlow){
        var url = "<s:url value='/res/lecture4qingpu/getdocEva'/>?doctorFlow="+doctorFlow+"&lectureFlow="+lectureFlow;
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        jboxMessager(iframe,'详情',700,550);
    }

    function exportExl(doctorFlow,lectureFlow){
        var url = "<s:url value='/res/lecture4qingpu/exportDocEva'/>?doctorFlow="+doctorFlow+"&lectureFlow="+lectureFlow;
        window.location.href=url;
    }
</script>
<div style="position:absolute; height:540px;width: 100%; overflow:auto">
            <table class="basic" style="width: 100%;">
                <tr style="text-align: center">
                    <th style="width: 10%;text-align: center;">序号</th>
                    <th style="width: 10%;text-align: center;">姓名</th>
                    <th style="width: 10%;text-align: center;">专业</th>
                    <th style="width: 10%;text-align: center;">届别</th>
                    <th style="width: 20%;word-wrap:break-word;word-break:break-all;text-align: center;">评价内容</th>
                    <th style="width: 10%;word-wrap:break-word;word-break:break-all;text-align: center;">评分内容</th>
                    <th style="width: 10%;text-align: center;">佐证图片</th>
                </tr>
                <c:forEach items="${lectureScanRegists}" var="eva" varStatus="s">
                   <tr>
                        <td style="text-align: center">${s.index+1}</td>
                        <td style="text-align: center">${eva.operUserName}</td>
                        <td style="text-align: center">${eva.trainingSpeName}</td>
                        <td style="text-align: center">${eva.sessionNumber}</td>
                        <td style="text-align: center">${(empty evaDetailMap[eva.operUserFlow].evaContent)?"未评价":evaDetailMap[eva.operUserFlow].evaContent}</td>
                        <%--<c:if test="${lectureInfo.lectureTypeId eq qingpuLectureTypeEnumTrainingForTeachers.id}">--%>
                        <td style="text-align: center" nowrap="">
                            <c:if test="${!empty evaDetailMap[eva.operUserFlow].evaScore}">
                            <ul id="star${s.index}">
                                <li style="float: left;margin-left: 15%;">★</li>
                                <li style="float: left">★</li>
                                <li style="float: left">★</li>
                                <li style="float: left">★</li>
                                <li style="float: left">★</li>
                            </ul>
                            </c:if>
                            <c:if test="${empty evaDetailMap[eva.operUserFlow].evaScore}">
                                未评分
                            </c:if>
                        </td>
                        <%--</c:if>--%>
                       <%--<c:if test="${lectureInfo.lectureTypeId ne qingpuLectureTypeEnumTrainingForTeachers.id}">--%>
                           <%--<td>--%>
                               <%--<c:if test="${not empty evaDetailMap[eva.operUserFlow].evaScore}">--%>
                                <%--<a style="cursor: pointer" onclick="getDocEva('${eva.operUserFlow}','${lectureInfo.lectureFlow}')">${evaDetailMap[eva.operUserFlow].evaScore}</a>--%>
                                <%--&lt;%&ndash;<a style="cursor: pointer" onclick="exportExl('${eva.operUserFlow}','${lectureInfo.lectureFlow}')">[导出]</a>&ndash;%&gt;--%>
                               <%--</c:if>--%>
                               <%--<c:if test="${empty evaDetailMap[eva.operUserFlow].evaScore}">--%>
                                   <%--未评价--%>
                               <%--</c:if>--%>
                           <%--</td>--%>
                       <%--</c:if>--%>
                       <td>
                           <a onclick="upload('${eva.recordFlow}','N')" style="cursor: pointer">[查看]</a>
                       </td>
                    <script>
                        var score = "${evaDetailMap[eva.operUserFlow].evaScore}";
                        $("#star${s.index} li:lt("+score+")").css("color","#ffb60b");
                    </script>
                   </tr>
                </c:forEach>
                <c:if test="${empty lectureScanRegists}">
                    <tr>
                        <td colspan="7" style="text-align: center">暂无信息</td>
                    </tr>
                </c:if>
            </table>
</div>



