<%@ taglib prefix="pdfn" uri="http://www.njpdkj.com/pdfn" %>
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <script>
        function toPage3(page){
            $("#currentPage3").val(page);
            jboxPostLoad("gradeDiv","<s:url value="/lcjn/lcjnDoctorTrainInfo/doctorScoreList"/>",$("#gradeForm").serialize(),true);
        }

        function editOneInfo1(courseFlow,doctorFlow){
            jboxStartLoading();
            var currentPage3=$("input[name='currentPage3']").val();
            jboxOpen("<s:url value='/lcjn/lcjnDoctorTrainInfo/showDoctorScore'/>?courseFlow="+courseFlow+"&doctorFlow="+doctorFlow+"&currentPage3="+currentPage3, "编辑学员成绩", 400, 240);
        }
        function releaseScore(){
            if(${empty doctorScoreList}){
                jboxTip("该课程下没有学生，无法发布成绩!");
                return;
            }
            var courseFlow=$("input[name='courseFlow']").val();
            jboxConfirm("确认发布该课程成绩吗？",function () {
                var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/releaseScore?courseFlow='/>" + courseFlow;
                jboxPost(url,null,function(resp){
                    jboxTip(resp);
                });
            });
        }
        function importScore(){
            var courseFlow='${courseFlow}';
            var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/showImportScore'/>?courseFlow="+courseFlow;
            typeName="导入学员成绩";
            jboxOpen(url, typeName, 380, 180);
        }
        function exportScore(){
            var enteringStatusId=$("select[name='enteringStatusId']").val();
            var appointDoctorName=$("input[name='appointDoctorName']").val();
            var courseFlow='${courseFlow}';
            var url = "<s:url value='/lcjn/lcjnDoctorTrainInfo/exportScore'/>?courseFlow="+courseFlow+"&doctorName="+appointDoctorName+"&enteringStatusId="+enteringStatusId;
            jboxTip("导出中....");
            $("#exportA3").attr("href",url);
            $("#outToExcelSpan3").click();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
            <div id="gradeDiv" class="labelDiv">
                <form id="gradeForm" action="<s:url value="/lcjn/lcjnDoctorTrainInfo/doctorScoreList"/>" method="post">
                    <div class="choseDivNewStyle">
                    <a class="btn" id="exportA3" type="hidden"><span id="outToExcelSpan3"> </span></a>
                    <input type="hidden" name="courseFlow" value="${courseFlow}"/>
                    <input id="currentPage3" type="hidden" name="currentPage3" value="${currentPage3}"/>
                    <table class="basic" style="width:100%;border:0px;margin:10px 0px;">
                        <tr>
                            <td style="border:0px;">
                                <span style="margin-left: -10px;"></span>姓名：
                                <input type="text" name="appointDoctorName" value="${param.appointDoctorName}">
                                <span style="padding-left:10px;"></span>录入状态：
                                <select name="enteringStatusId" style="width:137px;" class="select">
                                    <option value="">全部</option>
                                    <c:forEach items="${lcjnDoctorScoreEnumList}" var="status">
                                        <option value="${status.id}" ${param.enteringStatusId eq status.id ?'selected':''}>${status.name}</option>
                                    </c:forEach>
                                </select>
                                <span style="padding-left:20px;"></span>
                                <input type="button" class="search" value="查&#12288;询" onclick="toPage3(1)"/>
                                <input type="button" class="search" value="导&#12288;出" onclick="exportScore()"/>
                                <input type="button" class="search" value="成绩导入" onclick="importScore()"/>
							    <input type="button" class="search" value="成绩发布" onclick="releaseScore()"/>
                            </td>
                        </tr>
                    </table>
                    </div>
                </form>
                <table class="xllist" style="width:100%;">
                    <tr>
                        <th>序号</th>
                        <th>用户名</th>
                        <th>姓名</th>
                        <th>培训专业</th>
                        <th>工作单位</th>
                        <th>所在科室</th>
                        <th>成绩</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${doctorScoreList}" var="info" varStatus="i">
                        <tr>
                            <td>${i.index + 1}</td>
                            <td>${info.USER_CODE}</td>
                            <td>${info.USER_NAME}</td>
                            <td>${info.LCJN_SPE_NAME}</td>
                            <td>${info.ORG_NAME}</td>
                            <td>${info.DEPT_NAME}</td>
                            <td>${info.EXAM_SCORE}</td>
                            <td>
                                <a onclick="editOneInfo1('${info.COURSE_FLOW}','${info.USER_FLOW}')" style="cursor:pointer;color:#4195c5;">编辑</a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty doctorScoreList}">
                        <tr><td colspan="99">暂无记录</td></tr>
                    </c:if>
                </table>
                <div id="detail"></div>
                <div style="margin-top:100px;">
                    <c:set var="pageView" value="${pdfn:getPageView(doctorScoreList)}" scope="request"/>
                    <pd:pagination toPage="toPage3"/>
                </div>
            </div>
    </div>
</div>
</body>
</html>
