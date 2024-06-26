<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_placeholder" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="jquery_ui_tooltip" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>
<script>
    $(function(){
        // 初始化日期
        $("#lectureTrainDate").datepicker();
    });

    function regist(data){
        jboxConfirm("确认报名?",function(){
            jboxOpen('<s:url value="/res/manager/lectureRegistNew"/>?lectureFlow='+data,'报名反馈!',450,200,false);
        },null)
    }
    function registCancel(data){
        jboxConfirm("确认取消报名?",function(){
            var url = "<s:url value='/res/manager/lectureRegistCancel'/>?lectureFlow="+data;
            jboxPost(url,null,function(resp){
                jboxTip(resp);
                setTimeout(function(){
                    window.parent.search('new');
                },2000);
            },null,false);
        },null)
    }

</script>
<div class="main_bd">
    <div style="padding:10px 25px 0;">
        <form id="searchForm" method="post">
            <table class="searchTable" style="border-collapse: separate;border-spacing: 0px 10px;line-height: normal;">
                <tr>
                    <td class="td_right">讲座日期：</td>
                    <td class="td_left">
                        <input class="input" type="text" name="lectureTrainDate" id="lectureTrainDate" value="${param.lectureTrainDate}" readonly="readonly" />
                    </td>
                    <td class="td_right">&#12288;讲座类型：</td>
                    <td class="td_left">
                        <select name="lectureTypeId" class="select" style="width:164px;margin-left: 4px;">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumLectureTypeList}" var="dict">
                                <option value="${dict.dictId}" ${param.lectureTypeId eq dict.dictId ? 'selected' : ''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_right">&#12288;主&nbsp;讲&nbsp;人：</td>
                    <td class="td_left">
                        <input class="input" type="text" name="lectureTeacherName" value="${param.lectureTeacherName}"/>
                    </td>
                </tr>
                <tr>
                    <td class="td_right">讲座标题：</td>
                    <td class="td_left">
                        <input type="text" name="lectureContent" value="${param.lectureContent}" class="input"/>
                    </td>
                    <td class="td_right">&#12288;讲座地点：</td>
                    <td class="td_left">
                        <input type="text" name="lectureTrainPlace" value="${param.lectureTrainPlace}" class="input"/>
                    </td>
                    <td colspan="2">
                        <input type="button" style="margin-left: 13px;" value="查&#12288;询" class="btn_green" onclick="search('new',1);"/>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="div_table" style="padding: 20px 0px 0px;">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th style="width: 10%;">讲座日期</th>
                <th style="width: 10%;">开始时间</th>
                <th style="width: 10%;">结束时间</th>
                <th style="width: 15%;">讲座标题</th>
                <th style="width: 15%;">讲座地点</th>
                <th style="width: 10%;">主讲人</th>
                <th style="width: 15%;">备注</th>
                <th style="width: 15%;">操作</th>
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
                        <c:if test="${empty registMap[lecture.lectureFlow] or (!empty registMap[lecture.lectureFlow] and (registMap[lecture.lectureFlow].isRegist eq 'N' ))}">
                            <a onclick="regist('${lecture.lectureFlow}')"
                               style="cursor: pointer;color: #00a1e5">[报名]</a>
                        </c:if>
                        <c:if test="${!empty registMap[lecture.lectureFlow] and (registMap[lecture.lectureFlow].isRegist eq 'Y' )}">
                            [已报名]&ensp;<a onclick="registCancel('${lecture.lectureFlow}')"
                            style="cursor: pointer;color: #00a1e5">[取消报名]</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty lectureInfos}">
                <tr>
                    <td colspan="8" style="text-align: center">暂无信息</td>
                </tr>
            </c:if>
        </table>
        <div class="page" style="padding-right: 40px;">
            <input id="currentPage" type="hidden" name="currentPage" value=""/>
            <c:set var="pageView" value="${pdfn:getPageView(lectureInfos)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>
</div>