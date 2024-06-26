<script>
function asshole(lectureFlow,flag) {
var url = "<s:url value='/jsres/lecture/evaluate'/>?lectureFlow=" + lectureFlow+"&flag="+flag;
var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
jboxMessager(iframe,'讲座评价',450,280);
}
function cannelRegist(data,recordFlow){
    var url='<s:url value="/jsres/lecture/lectureCannelRegist"/>?roleId=${param.roleId}&lectureFlow='+data+"&recordFlow="+recordFlow;
    jboxConfirm("确认取消报名？" , function(){
        jboxPost(url , null , function(resp){
            selTag(this,'getHistoryLectures');
        } , null , true);
    });
}
</script>
<div class="main_bd" id="div_table_0">
    <div class="div_search">
        <div class="basic" style="margin-top: 5px">
            <table class="grid">
                <tr>
                    <th style="width: 10%;">讲座日期</th>
                    <th style="width: 10%;">开始时间</th>
                    <th style="width: 10%;">结束时间</th>
                    <th style="width: 17%;">讲座标题</th>
                    <th style="width: 10%;">讲座地点</th>
                    <th style="width: 10%;">主讲人</th>
                    <th style="width: 10%;">备注</th>
                    <th style="width: 13%;">操作</th>
                </tr>
                <c:forEach items="${lectureInfos}" var="lecture" varStatus="s">
                    <tr>
                        <td>${lecture.lectureTrainDate}</td>
                        <td>${lecture.lectureStartTime}</td>
                        <td>${lecture.lectureEndTime}</td>
                        <td>${lecture.lectureContent}(${lecture.lectureTypeName})</td>
                        <td>${lecture.lectureTrainPlace}</td>
                        <td>${lecture.lectureTeacherName}</td>
                        <c:if test="${!empty lecture.lectureDesc}">
                            <td>${lecture.lectureDesc}</td>
                        </c:if>
                        <c:if test="${empty lecture.lectureDesc}">
                             <td>无</td>
                        </c:if>
                        <td>
                            <c:if test="${(empty evaDetailMap[lecture.lectureFlow])&&(evaMap[lecture.lectureFlow]>=0)&&(!empty scanMap[lecture.lectureFlow])&&(!empty scan2Map[lecture.lectureFlow])}">
                                <a onclick="asshole('${lecture.lectureFlow}','Y')" style="cursor: pointer">[评价]</a>
                            </c:if>
                            <c:if test="${!empty evaDetailMap[lecture.lectureFlow]}">
                                <a onclick="asshole('${lecture.lectureFlow}','N')" style="cursor: pointer">[查看评价]</a>
                            </c:if>
                            <c:if test="${(evaMap[lecture.lectureFlow]<0)&&(!empty scanMap[lecture.lectureFlow])&&(!empty scan2Map[lecture.lectureFlow])&&(empty evaDetailMap[lecture.lectureFlow])}">
                                [评价已关闭]
                            </c:if>
                            <c:if test="${empty scanMap[lecture.lectureFlow] and empty scan2Map[lecture.lectureFlow]}">
                                [未签到]
                            </c:if>
                            <c:if test="${not empty scanMap[lecture.lectureFlow] and empty scan2Map[lecture.lectureFlow]}">
                                [已签到]
                            </c:if>
                            <c:if test="${not empty scanMap[lecture.lectureFlow] and not  empty scan2Map[lecture.lectureFlow]}">
                                [已签退]
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty lectureInfos}">
                     <tr>
                        <td colspan="8">无记录</td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
</div>

