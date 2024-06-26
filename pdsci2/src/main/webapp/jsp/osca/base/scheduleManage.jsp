<form id="scheduleForm" method="post">
    <input type="hidden" name="clinicalName" value="${clinicalName}"/>
    <input type="hidden" name="speName" value="${speName}"/>
    <input type="hidden" name="clinicalFlow" value="${param.clinicalFlow}"/>
    <input type="hidden" name="subjectFlow" value="${param.subjectFlow}"/>
    <input type="hidden" id="currentPage3" name="currentPage3" value="1"/>
    <table class="basic" style="width:100%;border:0px;margin: 10px 0px;">
        <tr>
            <td style="border:0px;">
                <span style=""></span>考核进度：
                <select name="complete" style="width:137px;" class="select">
                    <option value="">全部</option>
                    <option value="Y" ${param.complete eq 'Y'?'selected':''}>完成</option>
                    <option value="N" ${param.complete eq 'N'?'selected':''}>未完成</option>
                </select>
                <span style="padding-left:10px;"></span>考试阶段：
                <select name="categoryId" style="width:137px;" class="select">
                    <option value="">全部</option>
                    <c:forEach items="${trainCategoryEnumList}" var="cate">
                        <option value="${cate.id}" ${param.categoryId eq cate.id?'selected':''}>${cate.name}</option>
                    </c:forEach>
                </select>
                <span style="padding-left:10px;"></span>考核时间：
                <select name="recrodFlow" style="width:137px;" class="select">
                    <option value="">全部</option>
                    <c:forEach items="${timeList}" var="time">
                        <option value="${time.recrodFlow}" ${param.recrodFlow eq time.recrodFlow?'selected':''}>${time.examStartTime}~${time.examEndTime}</option>
                    </c:forEach>
                </select>
                <span style="padding-left:20px;"></span>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage3(1)"/>
            </td>
        </tr>
    </table>
</form>
<table class="xllist" style="width:100%;">
    <tr style="background-color:#F5F5F5;">
        <th rowspan="2">序号</th>
        <th rowspan="2">准考证号</th>
        <th rowspan="2">姓名</th>
        <th rowspan="2">培训专业</th>
        <th rowspan="2">考试阶段</th>
        <th colspan="${fn:length(stationList)}">考试进度</th>
    </tr>
    <tr style="background-color:#F5F5F5;">
        <c:forEach items="${stationList}" var="station">
            <th>${station.stationName}</th>
        </c:forEach>
    </tr>
    <c:set var="serival" value="0" />
    <c:forEach items="${scheduleList}" var="info" varStatus="i">
        <c:if test="${(param.complete eq 'Y' && info.NUM eq fn:length(stationList)) || (param.complete eq 'N' && info.NUM ne fn:length(stationList)) || empty param.complete}">
            <c:set var="serival" value="${serival+1}"/>
            <tr>
                <td>${serival}</td>
                <td>${info.TICKET_NUMBER}</td>
                <td>${info.DOCTOR_NAME}</td>
                <td>${info.SPE_NAME}</td>
                <td>${info.TRAINING_TYPE_NAME}</td>
                <c:forEach begin="0" end="${fn:length(stationList)-1}" varStatus="s">
                    <c:forEach items="${fn:split(info.STATION_FLOW,',')}" var="sta" varStatus="si">
                        <c:if test="${sta eq stationList[s.index].stationFlow}">
                            <td style="height:50px;">
                                <c:if test="${fn:split(info.EXAM_STATUS_ID,',')[si.index] eq 'Waiting'}"><img src="<s:url value="/jsp/osca/images/hkz.png" />"></c:if>
                                <c:if test="${fn:split(info.EXAM_STATUS_ID,',')[si.index] eq 'StayAssessment'}"><img src="<s:url value="/jsp/osca/images/wkh.png" />"></c:if>
                                <c:if test="${fn:split(info.EXAM_STATUS_ID,',')[si.index] eq 'Assessment'}"><img src="<s:url value="/jsp/osca/images/ywc.png" />"></c:if>
                                <c:if test="${fn:split(info.EXAM_STATUS_ID,',')[si.index] eq 'AssessIng'}"><img src="<s:url value="/jsp/osca/images/yks.png" />"></c:if>
                            </td>
                            <c:set var="exitFlag" value="${s.index}"></c:set>
                        </c:if>
                    </c:forEach>
                    <c:if test="${exitFlag ne s.index || empty exitFlag || empty fn:split(info.STATION_FLOW,',')[0]}">
                        <td style="height:50px;"><img src="<s:url value="/jsp/osca/images/wkh.png" />"></td>
                        <c:set var="exitFlag"></c:set>
                    </c:if>
                </c:forEach>
            </tr>
        </c:if>
    </c:forEach>
</table>
<div style="margin-top:100px;">
    <div style="float:left;margin:10px 0px">
        <img src="<s:url value="/jsp/osca/images/b-ywc.png" />">&nbsp;考生完成本站考核&#12288;
        <img src="<s:url value="/jsp/osca/images/b-yks.png" />">&nbsp;考生已开始考核&#12288;
        <img src="<s:url value="/jsp/osca/images/b-hkz.png" />">&nbsp;考生候考中&#12288;
        <img src="<s:url value="/jsp/osca/images/b-wkh.png" />">&nbsp;考生本站未考核
    </div>
    <div style="float:right;">
        <c:set var="pageView" value="${pdfn:getPageView(scheduleList)}" scope="request"/>
        <pd:pagination toPage="toPage3"/>
    </div>
</div>