<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <style type="text/css">
        #searchForm input[type='text']{width:133px;}
    </style>
    <script type="text/javascript">
        function detailInfo(recordFlow,viewFlag){
            var url = "<s:url value='/xjgl/paper/editSchedule?recordFlow='/>"+recordFlow+"&viewFlag="+viewFlag;
            jboxOpen(url, "答辩日程信息",800,400);
        }
        function toPage(page){
            if($("#orgName").val()!=""){
                $("#orgFlow").val($("#orgName").attr("flow"));
            }else{
                $("#orgFlow").val("");
            }
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
        $(function(){
            $("#orgName").likeSearchInit({});
        });
        function expExcel(){
            var url = "<s:url value='/xjgl/paper/expSchedule'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <c:if test="${param.role eq 'xs'}">
            <form id="searchForm">
                <div class="choseDivNewStyle">
                    <input type="hidden" name="role" value="${param.role}"/>
                    <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                    <input type="button" class="search" value="导&#12288;出" onclick="expExcel()"/>
                </div>
            </form>
        </c:if>
        <c:if test="${param.role ne 'xs'}">
            <form id="searchForm" action="<s:url value="/xjgl/paper/defenceSchedule"/>" method="post">
                <div class="choseDivNewStyle">
                    <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                    <input type="hidden" name="role" value="${param.role}"/>
                    <span style=""></span>学&#12288;&#12288;号：
                    <input type="text" name="stuNo" value="${param.stuNo}">
                    <span style="padding-left:10px;"></span>姓&#12288;&#12288;名：
                    <input type="text" name="userName" value="${param.userName}">
                    <span style="padding-left:10px;"></span>专&#12288;&#12288;业：
                    <select name="majorId" style="width:137px;" class="select">
                        <option/>
                        <c:forEach items="${dictTypeEnumMajorList}" var="major">
                            <option value="${major.dictId}" ${param.majorId eq major.dictId?'selected':''}>${major.dictName}</option>
                        </c:forEach>
                    </select>
                    <span style="padding-left:10px;"></span>培养层次：
                    <select name="trainGradationId" style="width:137px;" class="select">
                        <option></option>
                        <c:forEach items="${dictTypeEnumTrainTypeList}" var="type">
                            <option value="${type.dictId}" ${param.trainGradationId eq type.dictId?'selected':''}>${type.dictName}</option>
                        </c:forEach>
                    </select>
                    <br/>
                    <span style="padding-left:0px;"></span>培养类型：
                    <select name="trainCategoryId" style="width:137px;" class="select">
                        <option></option>
                        <c:forEach items="${dictTypeEnumTrainCategoryList}" var="cate">
                            <option value="${cate.dictId}" ${param.trainCategoryId eq cate.dictId?'selected':''}>${cate.dictName}</option>
                        </c:forEach>
                    </select>
                    <span style="padding-left:10px;"></span>答辩时间：
                    <select class="select" name="defenceTime" style="width:137px;">
                        <option/>
                        <c:forEach items="${dictTypeEnumDefenceTimeList}" var="dt">
                            <option value="${dt.dictName}" ${dt.dictName eq param.defenceTime?'selected':''}>${dt.dictName}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${param.role ne 'ds'}">
                        <span style="margin-left:10px;"></span>导&#12288;&#12288;师：
                        <input type="text" name="tutorName" value="${param.tutorName}">
                    </c:if>
                    <c:if test="${param.role eq 'fwh' or param.role eq 'xx'}">
                        <span style="padding-left:10px;"></span>培养单位：
                        <input id="orgName" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" flow="${param.pydwOrgFlow}"/>&#12288;
                        <div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:720px;">
                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
                                <c:forEach items="${orgList}" var="org">
                                    <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
                                </c:forEach>
                            </div>
                        </div>
                        <input type="hidden" id="orgFlow" name="pydwOrgFlow" value="${param.pydwOrgFlow}"/>
                    </c:if>
                    <c:if test="${param.role eq 'xx'}">
                        <br/>
                        <span style="margin-left:0px;"></span>分&ensp;委&ensp;会：
                        <select name="fwhOrgFlow" style="width:137px;" class="select">
                            <option></option>
                            <c:forEach items="${deptList}" var="dept">
                                <option value="${dept.deptFlow}" <c:if test="${dept.deptFlow eq param.fwhOrgFlow}">selected</c:if>>${dept.deptName}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <span style="margin-left:10px;"></span><c:if test="${param.role eq 'fwh'}"><br/></c:if>学习形式：
                    <select style="width: 137px;" name="studyFormId">
                        <option/>
                        <c:forEach items="${dictTypeEnumStudyFormList}" var="studyForm">
                            <option value="${studyForm.dictId}" ${param.studyFormId eq studyForm.dictId?'selected':''}>${studyForm.dictName}</option>
                        </c:forEach>
                    </select><c:if test="${param.role eq 'pydw'}"><br/></c:if>
                    <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                    <input type="button" class="search" value="导&#12288;出" onclick="expExcel()"/>
                </div>
            </form>
        </c:if>
        <table class="xllist" style="width:100%;">
            <tr>
                <th style="min-width:40px;">学号</th>
                <th style="min-width:40px;">姓名</th>
                <th style="min-width:40px;">导师</th>
                <th style="min-width:60px;">专业代码</th>
                <th style="min-width:60px;">专业名称</th>
                <th style="min-width:60px;">培养层次</th>
                <th style="min-width:60px;">培养类型</th>
                <th style="min-width:60px;">培养单位</th>
                <th style="min-width:50px;">分委会</th>
                <th style="min-width:60px;">答辩时间</th>
                <th style="min-width:80px;">中文论文题目</th>
                <th style="min-width:80px;">英文论文题目</th>
                <th style="min-width:60px;">研究方向</th>
                <th style="min-width:50px;">关键字</th>
                <th style="min-width:70px;">答辩日程</th>
            </tr>
            <c:forEach items="${defenceList}" var="defence" varStatus="i">
                <tr>
                    <td>${defence.stuNo}</td>
                    <td>${defence.userName}</td>
                    <td>${defence.tutorName}${!empty defence.tutorFlow && !empty defence.twoTutorFlow?'，':''}${defence.twoTutorName}</td>
                    <td>${defence.majorId}</td>
                    <td>${defence.majorName}</td>
                    <td>${defence.trainGradationName}</td>
                    <td>${defence.trainCategoryName}</td>
                    <td>${defence.pydwOrgName}</td>
                    <td>${defence.fwhOrgName}</td>
                    <td>${defence.defenceTime}</td>
                    <td>${defence.paperChiTitle}</td>
                    <td>${defence.paperEngTitle}</td>
                    <td>${defence.researchDirection}</td>
                    <td>${defence.keyWord}</td>
                    <td>
                        <c:choose>
                            <c:when test="${param.role eq 'xs' && defence.scheduleFlag ne 'Submit'}">
                                <a onclick="detailInfo('${defence.recordFlow}');" style="cursor:pointer;color:blue;">安排</a>
                            </c:when>
                            <c:when test="${defence.scheduleFlag eq 'Submit'}">
                                <a onclick="detailInfo('${defence.recordFlow}','view');" style="cursor:pointer;color:blue;">查看</a>
                            </c:when>
                            <c:otherwise>待安排</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty defenceList}">
                <tr><td colspan="99">无记录</td></tr>
            </c:if>
        </table>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(defenceList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>