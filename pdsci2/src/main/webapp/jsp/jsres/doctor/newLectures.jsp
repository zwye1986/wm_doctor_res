<script>
    function regist(data){
        jboxConfirm("确认报名?",function(){
            jboxOpen('<s:url value="/jsres/lecture/lectureRegist"/>?roleId=${param.roleId}&lectureFlow='+data,'报名结果',450,200,false);
        },null)
    }
    function cannelRegist(data,recordFlow){
        var url='<s:url value="/jsres/lecture/lectureCannelRegist"/>?roleId=${param.roleId}&lectureFlow='+data+"&recordFlow="+recordFlow;
        jboxConfirm("确认取消报名？" , function(){
            jboxPost(url , null , function(resp){
                selTag(this,'getNewLectures');
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
                            <c:if test="${empty registMap[lecture.lectureFlow] or (not empty registMap[lecture.lectureFlow] and (registMap[lecture.lectureFlow].isRegist ne 'Y' ))}">
                                <c:if test="${empty lecture.limitNum or (!empty lecture.limitNum and  lecture.limitNum>registNumMap[lecture.lectureFlow])}">
                                    <a onclick="regist('${lecture.lectureFlow}')" style="cursor: pointer">[报名]</a>
                                </c:if>
                            </c:if>
                            <c:if test="${not empty registMap[lecture.lectureFlow] and (registMap[lecture.lectureFlow].isRegist eq 'Y' )}">
                                [已报名]
                                <c:if test="${(registMap[lecture.lectureFlow].isScan ne 'Y' )}">
                                    <a onclick="cannelRegist('${lecture.lectureFlow}','${registMap[lecture.lectureFlow].recordFlow}')" style="cursor: pointer">[取消]</a>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty lectureInfos}">
                    <tr>
                        <td colspan="8">暂无讲座信息</td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
</div>

