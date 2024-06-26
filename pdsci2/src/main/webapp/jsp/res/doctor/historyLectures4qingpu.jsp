<script>
    function asshole(lectureFlow,flag) {
        var url = "<s:url value='/res/lecture4qingpu/evaluate'/>?lectureFlow=" + lectureFlow+"&flag="+flag;
        var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
        jboxMessager(iframe,'讲座评价',800,600);
    }

    function upload(registFlow,isUpload){
        var url = "<s:url value='/res/lecture4qingpu/upload'/>?registFlow="+registFlow+"&isUpload="+isUpload;
        if(isUpload=="Y")
        {
            jboxOpen(url, "上传佐证图片",700,550);
        }else{
            jboxOpen(url, "查看佐证图片",700,550);
        }
    }
</script>
<div class="mainright">
    <div class="content">
        <table class="xllist" style="margin-top: 10px;">
            <tr>
                <th style="width: 9%;">讲座日期</th>
                <th style="width: 9%;">开始时间</th>
                <th style="width: 9%;">结束时间</th>
                <th style="width: 9%;">讲座标题</th>
                <th style="width: 9%;">讲座内容</th>
                <th style="width: 9%;">讲座级别</th>
                <th style="width: 9%;">讲座地点</th>
                <th style="width: 9%;">主讲人</th>
                <th style="width: 9%;">备注</th>
                <th style="width: 5%;">课件</th>
                <th style="width: 10%;">操作</th>
            </tr>
            <c:forEach items="${lectureInfos}" var="lecture" varStatus="s">
                <tr>
                    <td>${lecture.lectureTrainDate}</td>
                    <td>${lecture.lectureStartTime}</td>
                    <td>${lecture.lectureEndTime}</td>
                    <td>${lecture.lectureContent}(${lecture.lectureTypeName})</td>
                    <td>${lecture.lectureDescription}</td>
                    <td>${lecture.lectureLevelName}</td>
                    <td>${lecture.lectureTrainPlace}</td>
                    <td>${lecture.lectureTeacherName}</td>
                    <c:if test="${!empty lecture.lectureDesc}">
                        <td>${lecture.lectureDesc}</td>
                    </c:if>
                    <c:if test="${empty lecture.lectureDesc}">
                        <td>无</td>
                    </c:if>
                    <td>
                        <c:if test="${empty lecture.coursewareUrl}">无</c:if>
                        <c:if test="${not empty lecture.coursewareUrl}"><a href="${sysCfgMap['upload_base_url']}${lecture.coursewareUrl}" target="_blank" style="color:blue">下载</a></c:if>
                    </td>
                    <td>
                        <c:if test="${not empty scanMap[lecture.lectureFlow]}">
                            <a onclick="upload('${registMap[lecture.lectureFlow].recordFlow}','Y')" style="cursor: pointer">[上传图片]</a>
                        </c:if>
                        <c:if test="${(empty evaDetailMap[lecture.lectureFlow])&&(evaMap[lecture.lectureFlow]>=0)&&(!empty scanMap[lecture.lectureFlow])}">
                            <a onclick="asshole('${lecture.lectureFlow}','Y')" style="cursor: pointer">[评价]</a>
                        </c:if>
                        <c:if test="${!empty evaDetailMap[lecture.lectureFlow]}">
                            <a onclick="asshole('${lecture.lectureFlow}','N')" style="cursor: pointer">[查看评价]</a>
                        </c:if>
                        <c:if test="${(evaMap[lecture.lectureFlow]<0)&&(!empty scanMap[lecture.lectureFlow])&&(empty evaDetailMap[lecture.lectureFlow])}">
                            [评价已关闭]
                        </c:if>
                        <c:if test="${empty scanMap[lecture.lectureFlow]}">
                            [未扫码]
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty lectureInfos}">
                <tr>
                    <td colspan="10">暂无历史讲座信息</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>


