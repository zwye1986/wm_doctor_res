<script>
    function regist(data){
        jboxConfirm("确认报名?",function(){
            jboxOpen('<s:url value="/res/lecture4qingpu/lectureRegistNew"/>?lectureFlow='+data,'报名反馈!',450,200,false);
        },null)
    }
    function registCancel(data){
        jboxConfirm("确认取消报名?",function(){
            var url = "<s:url value='/res/lecture4qingpu/lectureRegistCancel'/>?lectureFlow="+data;
            jboxPost(url,null,function(resp){
                jboxTip(resp);
                setTimeout(function(){
                    window.parent.frames['mainIframe'].location.reload(true);
                },1500);
            },null,false);
        },null)
    }
</script>
<div class="content">
    <div class="resultDiv">
        <table class="xllist" style="margin-top: 10px;">
            <tr>
                <th style="width: 10%;">讲座日期</th>
                <th style="width: 10%;">开始时间</th>
                <th style="width: 10%;">结束时间</th>
                <th style="width: 10%;">讲座标题</th>
                <th style="width: 10%;">讲座内容</th>
                <th style="width: 10%;">讲座级别</th>
                <th style="width: 10%;">讲座地点</th>
                <th style="width: 10%;">主讲人</th>
                <th style="width: 10%;">备注</th>
                <th style="width: 5%;">课件</th>
                <th style="width: 10%;min-width: 70px;">操作</th>
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
                        <c:if test="${empty registMap[lecture.lectureFlow] or (!empty registMap[lecture.lectureFlow] and (registMap[lecture.lectureFlow].isRegist eq 'N' ))}">
                            <a onclick="regist('${lecture.lectureFlow}')"
                               style="cursor: pointer;color: #00a1e5">[报名]</a>
                        </c:if>
                        <c:if test="${!empty registMap[lecture.lectureFlow] and (registMap[lecture.lectureFlow].isRegist eq 'Y' )}">
                            [已报名]<br/><a onclick="registCancel('${lecture.lectureFlow}')"
                            style="cursor: pointer;color: #00a1e5">[取消报名]</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty lectureInfos}">
                <tr>
                    <td colspan="11" style="text-align: center">暂无讲座信息</td>
                </tr>
            </c:if>
        </table>
    </div>
</div>


