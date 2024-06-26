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
            var url = "<s:url value='/gyxjgl/paper/expUpDate'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/gyxjgl/paper/exportDate"/>" method="post">
                <div class="choseDivNewStyle">
                    <input type="hidden" name="role" value="${param.role}"/>
                    <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                    <span style=""></span>学&#12288;&#12288;号：
                    <input type="text" name="stuNo" value="${param.stuNo}">
                    <span style="padding-left:10px;"></span>姓&#12288;&#12288;名：
                    <input type="text" name="userName" value="${param.userName}">
                    <span style="padding-left:10px;"></span>性&#12288;&#12288;别：
                    <select name="sexId" style="width:137px;" class="select">
                        <option></option>
                        <option value="Man" ${param.sexId eq 'Man'?'selected':''}>男</option>
                        <option value="Woman" ${param.sexId eq 'Woman'?'selected':''}>女</option>
                    </select>
                    <span style="padding-left:10px;"></span>专&#12288;&#12288;业：
                    <select name="majorId" style="width:137px;" class="select">
                        <option/>
                        <c:forEach items="${dictTypeEnumGyMajorList}" var="major">
                            <option value="${major.dictId}" ${param.majorId eq major.dictId?'selected':''}>${major.dictName}</option>
                        </c:forEach>
                    </select>
                    <br/>
                    <span style="padding-left:0px;"></span>答辩时间：
                    <select class="select" name="defenceTime" style="width:137px;">
                        <option/>
                        <c:forEach items="${dictTypeEnumGyDefenceTimeList}" var="dt">
                            <option value="${dt.dictName}" ${dt.dictName eq param.defenceTime?'selected':''}>${dt.dictName}</option>
                        </c:forEach>
                    </select>
                    <span style="margin-left:10px;"></span>导&#12288;&#12288;师：
                    <input type="text" name="tutorName" value="${param.tutorName}">
                    <span style="margin-left:10px;"></span>盲&#12288;&#12288;审：
                    <select name="auditResultFlag" style="width:137px;" class="select">
                        <option></option>
                        <option value="Y" <c:if test="${'Y' eq param.auditResultFlag}">selected</c:if>>通过</option>
                        <option value="N" <c:if test="${'N' eq param.auditResultFlag}">selected</c:if>>不通过</option>
                    </select>
                    <span style="margin-left:10px;"></span>答辩结果：
                    <select name="defenceResultFlag" style="width:137px;" class="select">
                        <option></option>
                        <option value="Y" <c:if test="${'Y' eq param.defenceResultFlag}">selected</c:if>>通过</option>
                        <option value="N" <c:if test="${'N' eq param.defenceResultFlag}">selected</c:if>>不通过</option>
                    </select>
                    <br/>
                    <span style="padding-left:0px;"></span>二级机构：
                    <input id="orgName" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" flow="${param.pydwOrgFlow}"/>&#12288;
                    <div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:60px;">
                        <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 160px;border-top: none;position: relative;display: none;">
                            <c:forEach items="${orgList}" var="org">
                                <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
                            </c:forEach>
                        </div>
                    </div>
                    <input type="hidden" id="orgFlow" name="pydwOrgFlow" value="${param.pydwOrgFlow}"/>
                    <c:if test="${param.role eq 'xx'}">
                        <span style="margin-left:-3px;"></span>分&ensp;委&ensp;会：
                        <select name="fwhOrgFlow" style="width:137px;" class="select">
                            <option></option>
                            <c:forEach items="${deptList}" var="dept">
                                <option value="${dept.deptFlow}" <c:if test="${dept.deptFlow eq param.fwhOrgFlow}">selected</c:if>>${dept.deptName}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <c:if test="${param.role eq 'ds'||param.role eq 'xx'}"><span style="margin-left:10px;"></span></c:if><c:if test="${param.role eq 'fwh'}"><span style="margin-left:-3px;"></span></c:if><c:if test="${param.role eq 'pydw'}"><br/></c:if>学习形式：
                    <select style="width: 137px;" name="studyFormId">
                        <option/>
                        <c:forEach items="${dictTypeEnumGyStudyFormList}" var="studyForm">
                            <option value="${studyForm.dictId}" ${param.studyFormId eq studyForm.dictId?'selected':''}>${studyForm.dictName}</option>
                        </c:forEach>
                    </select><c:if test="${param.role eq 'ds'}"><br/></c:if>
                    <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                    <input type="button" class="search" value="导&#12288;出" onclick="expExcel()"/>
                </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th style="min-width:40px;">序号</th>
                <th style="min-width:40px;">学号</th>
                <th style="min-width:40px;">姓名</th>
                <th style="min-width:40px;">性别</th>
                <th style="min-width:60px;">答辩时间</th>
                <th style="min-width:40px;">专业</th>
                <th style="min-width:40px;">导师</th>
                <th style="min-width:80px;">学位论文题目</th>
                <th style="min-width:40px;">盲审</th>
                <th style="min-width:60px;">答辩结果</th>
                <th style="min-width:80px;">所属学位分委会</th>
                <th style="min-width:60px;">二级机构</th>
                <th style="min-width:60px;">联系电话</th>
            </tr>
            <c:forEach items="${defenceList}" var="defence" varStatus="i">
                <tr>
                    <td>${i.index+1}</td>
                    <td>${defence.stuNo}</td>
                    <td>${defence.userName}</td>
                    <td>${defence.sexName}</td>
                    <td>${defence.defenceTime}</td>
                    <td>${defence.majorName}</td>
                    <td>${defence.tutorName}${!empty defence.tutorName && !empty defence.twoTutorName?'，':''}${defence.twoTutorName}</td>
                    <td>${defence.paperChiTitle}</td>
                    <td><c:if test="${defence.auditResultFlag eq 'Y'}">通过</c:if><c:if test="${defence.auditResultFlag eq 'N'}">不通过</c:if></td>
                    <td><c:if test="${defence.defenceResultFlag eq 'Y'}">通过</c:if><c:if test="${defence.defenceResultFlag eq 'N'}">不通过</c:if></td>
                    <td>${defence.fwhOrgName}</td>
                    <td>${defence.pydwOrgName}</td>
                    <td>${defence.tutorPhone}${!empty defence.tutorName && !empty defence.twoTutorName?'，':''}${defence.twoTutorPhone}</td>
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