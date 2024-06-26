
<script type="text/javascript">
    function courseDetail(courseFlow) {
        var url = "<s:url value='/pubedu/doctor/courseDetail?courseFlow='/>"+courseFlow;
        window.location.href=url;
    }
</script>

<body style="overflow: auto">
<jsp:include page="doctorHead.jsp"/>
    <div class="body">
        <div class="container" style="float: left">
            <div class="photo1">
                <c:forEach items="${studyCourseList}" var="course">
                    <a onclick="courseDetail('${course.courseFlow}')" target="_blank">
                        <div style="float: left;position: relative;">
                        <img src="${sysCfgMap["upload_base_url"]}/${course.courseImgUrl}">
                            <p style="position: absolute;bottom: 45px;line-height: 45px;font-size: 28px;color:#F0F0F0;width:360px;left:42px;text-align: center;background-color: rgba(0, 0, 0, 0.4);">
                                ${course.courseName}
                            </p>
                        </div>
                    </a>
                </c:forEach>
            </div>
        </div>
    </div>

    <div style="clear: both;" class="foot">
        <div class="foot_inner" style="margin-top: 35px">
            <span>技术支持：南京品德网络信息技术有限公司&nbsp;&nbsp;服务电话：025-69815356&nbsp;69815357</span>
        </div>
    </div>
</body>
</html>
