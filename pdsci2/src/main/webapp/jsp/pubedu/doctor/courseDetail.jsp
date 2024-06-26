<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/pubedu/htmlhead-pubedu.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function showPPT(detailFlow) {
            if(null != detailFlow && detailFlow != ''){
            var url = "<s:url value="/pubedu/doctor/initCoursPPT"/>?detailFlow=" + detailFlow;
            window.open(url);
            }else{
              jboxTip("当前课程暂无PPT!");
            }
            //jboxPost(url,{"pptUrl":pptUrl,"pageNum":pageNum},null , null , true);
        }
        function showData(courseFlow){
            var url = "<s:url value="/pubedu/doctor/initCourseData"/>?courseFlow=" + courseFlow;
            window.location.href = url;
        }
        function showTest() {
            jboxTip("该功能暂未开放！");
        }
        function handoutIsExist(flow) {
            var url = "<s:url value="/pubedu/doctor/handoutIsExist"/>";
            jboxPost(url,{"recordFlow":flow,"typeId":"${courseDetailTypeEnumChapterHandout.id}"},function(resp){
                if(resp == 'Y'){
                    var newTab=window.open('about:blank');
                    newTab.location.href="${sysCfgMap["upload_base_url"]}/${pdfn:encodeString2(detailMap[courseDetailTypeEnumChapterHandout.id].detailUrl)}";//防止窗口被浏览器拦截
                }else{
                    jboxTip("未找到该课程讲义");
                }
            } , null , false);
        }
    </script>
</head>

<body>
<jsp:include page="doctorHead.jsp"/>
<div class="body">
    <div class="container">
        <div class="course_top">
            <img class="top" style="height:114px;" src="${sysCfgMap["upload_base_url"]}/${course.detailImgUrl}"/>
            <div class="text">${course.courseName}</div>
            <div class="text1">${course.teacherName}</div>
        </div>

        <div class="content">
            <div class="photo"><img style="margin-top:260px;"
                                    src="<s:url value='/jsp/pubedu/images/course.png'/>"/>
                <div class="ppt">课程-PPT</div>
                <a target="_blank"
                   onclick="return showPPT('${detailMap[courseDetailTypeEnumCoursePPT.id].detailFlow}')">
                    <div class="btn1 fr"></div>
                </a>
            </div>

            <div class="picter"><img style="margin-top:260px;" src="<s:url value='/jsp/pubedu/images/course.png'/>"/>
                <div class="ppt">课程-讲义</div>
                <a target="_blank" onclick="handoutIsExist('${detailMap[courseDetailTypeEnumChapterHandout.id].detailFlow}')">
                    <div class="btn1 fr"></div>
                </a>
            </div>

            <div class="picter1"><img style="margin-top:260px;" src="<s:url value='/jsp/pubedu/images/course.png'/>"/>
                <div class="ppt">课程-资料</div>
                <a  target="_blank" onclick="return showData('${course.courseFlow}')">
                    <div class="btn1 fr"></div></a>
            </div>

            <div class="picter2"><img style="margin-top:260px;"
                                      src="<s:url value='/jsp/pubedu/images/course.png'/>"/>
                <div class="ppt">课程-考试</div>
                <a onclick="showTest();">
                    <div class="btn1 fr"></div>
                </a>
            </div>
        </div>
    </div>
</div>

<div class="foot">
    <div class="foot_inner">
        <span>技术支持：南京品德网络信息技术有限公司&nbsp;&nbsp;服务电话：025-69815356&nbsp;69815357</span>
    </div>
</div>
</body>
</html>
