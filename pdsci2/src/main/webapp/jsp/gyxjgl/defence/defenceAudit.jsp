<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
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
        function checkTime(obj){
            var dates = $(':text',$(obj).closest("span"));
            if(dates[0].value && dates[1].value && dates[0].value > dates[1].value){
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function detailInfo(recordFlow){
            var url = "<s:url value='/gyxjgl/paper/editDefence?recordFlow='/>"+recordFlow+"&viewFlag=view&role=${param.role}&isReplenish=${isReplenish}";
            jboxOpen(url, "查看",800,460);
        }
        function auditInfo(recordFlow,tutorFlag){
            var url = "<s:url value='/gyxjgl/paper/editDefence?role=${param.role}&auditFlag=Y&recordFlow='/>"+recordFlow+"&tutorFlag="+tutorFlag+"&isReplenish=${isReplenish}";
            jboxOpen(url, "审核",800,460);
        }
        function expExcel(){
            var url = "<s:url value='/gyxjgl/paper/expExcel'/>?role=${param.role}";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
        function leadTo(){
            jboxOpen("<s:url value='/gyxjgl/paper/leadTo'/>?isReplenish=${isReplenish}","导入",360,150);
        }
        $(function(){
            $("#orgName").likeSearchInit({});
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="searchForm" action="<s:url value="/gyxjgl/paper/paperAudit?role=${param.role}"/>" method="post">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <input type="hidden" name="isReplenish" value="${isReplenish}"/>
                <span style=""></span>学&#12288;&#12288;号：
                <input type="text" name="stuNo" value="${param.stuNo}">
                <span style="padding-left:10px;"></span>姓&#12288;&#12288;名：
                <input type="text" name="userName" value="${param.userName}">
                <span style="padding-left:10px;"></span>专&#12288;&#12288;业：
                <select name="majorId" style="width:137px;" class="select">
                    <option/>
                    <c:forEach items="${dictTypeEnumGyMajorList}" var="major">
                        <option value="${major.dictId}" ${param.majorId eq major.dictId?'selected':''}>${major.dictName}</option>
                    </c:forEach>
                </select>
                <span style="padding-left:10px;"></span>培养层次：
                <select name="trainGradationId" style="width:137px;" class="select">
                    <option></option>
                    <c:forEach items="${dictTypeEnumGyTrainTypeList}" var="type">
                        <option value="${type.dictId}" ${param.trainGradationId eq type.dictId?'selected':''}>${type.dictName}</option>
                    </c:forEach>
                </select>
                <br/>
                <span style="padding-left:0px;"></span>培养类型：
                <select name="trainCategoryId" style="width:137px;" class="select">
                    <option></option>
                    <c:forEach items="${dictTypeEnumGyTrainCategoryList}" var="cate">
                        <option value="${cate.dictId}" ${param.trainCategoryId eq cate.dictId?'selected':''}>${cate.dictName}</option>
                    </c:forEach>
                </select>
                <span style="padding-left:10px;"></span>${isReplenish eq 'Y'?'补授时间':'答辩时间'}：
                <c:if test="${isReplenish eq 'Y'}">
                    <select class="validate[required] select" name="replenishTime" style="width:137px;">
                        <option/>
                        <c:forEach items="${dictTypeEnumGyReplenishTimeList}" var="dt">
                            <option value="${dt.dictName}" ${dt.dictName eq param.replenishTime?'selected':''}>${dt.dictName}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${isReplenish ne 'Y'}">
                    <select class="validate[required] select" name="defenceTime" style="width:137px;">
                        <option/>
                        <c:forEach items="${dictTypeEnumGyDefenceTimeList}" var="dt">
                            <option value="${dt.dictName}" ${dt.dictName eq param.defenceTime?'selected':''}>${dt.dictName}</option>
                        </c:forEach>
                    </select>
                </c:if>
                <c:if test="${!empty param.role && param.role ne 'ds'}">
                    <span style="margin-left:10px;"></span>导&#12288;&#12288;师：
                    <input type="text" name="tutorName" value="${param.tutorName}">
                </c:if>
                <c:if test="${param.role eq 'fwh' or param.role eq 'xx'}">
                    <span style="padding-left:10px;"></span>二级机构：
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
                    <c:forEach items="${dictTypeEnumGyStudyFormList}" var="studyForm">
                        <option value="${studyForm.dictId}" ${param.studyFormId eq studyForm.dictId?'selected':''}>${studyForm.dictName}</option>
                    </c:forEach>
                </select><c:if test="${param.role eq 'pydw'}"><br/></c:if>
                <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                <c:if test="${param.role eq 'xx' and readOnlyFlag ne 'Y'}">
                    <input type="button" class="search" value="导&#12288;入" onclick="leadTo()"/>
                </c:if>
                <input type="button" class="search" value="导&#12288;出" onclick="expExcel()"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th style="min-width:50px;line-height: 130%;">学号</th>
                <th style="min-width:50px;line-height: 130%;">姓名</th>
                <th style="min-width:50px;line-height: 130%;">导师</th>
                <th style="min-width:55px;line-height: 130%;">专业名称</th>
                <th style="min-width:55px;line-height: 130%;">培养层次</th>
                <th style="min-width:55px;line-height: 130%;">培养类型</th>
                <c:if test="${isReplenish ne 'Y'}">
                    <th style="min-width:55px;line-height: 130%;">答辩时间</th>
                </c:if>
                <c:if test="${isReplenish eq 'Y'}">
                    <th style="min-width:55px;line-height: 130%;">学位补授时间</th>
                </c:if>
                <th style="min-width:70px;line-height: 130%;">中文论文题目</th>
                <th style="min-width:70px;line-height: 130%;">英文论文题目</th>
                <th style="min-width:70px;line-height: 130%;">研究方向</th>
                <th style="min-width:60px;line-height: 130%;">关键字</th>
                <th style="min-width:60px;line-height: 130%;">二级机构</th>
                <th style="min-width:60px;line-height: 130%;">分委会</th>
                <th style="min-width:60px;line-height: 130%;">导师审核</th>
                <th style="min-width:55px;line-height: 130%;">二级机构审核</th>
                <th style="min-width:60px;">操作</th>
            </tr>
            <c:forEach items="${defenceList}" var="defence">
                <tr>
                <td style="line-height: 130%;">${defence.stuNo}</td>
                <td style="line-height: 130%;">${defence.userName}</td>
                <td style="line-height: 130%;">${defence.tutorName}${!empty defence.tutorName && !empty defence.twoTutorName?'，':''}${defence.twoTutorName}</td>
                <td style="line-height: 130%;">${defence.majorName}</td>
                <td style="line-height: 130%;">${defence.trainGradationName}</td>
                <td style="line-height: 130%;">${defence.trainCategoryName}</td>
                <c:if test="${isReplenish ne 'Y'}">
                    <td style="line-height: 130%;">${defence.defenceTime}</td>
                </c:if>
                <c:if test="${isReplenish eq 'Y'}">
                    <td style="line-height: 130%;">${defence.replenishTime}</td>
                </c:if>
                <td style="line-height: 130%;">${defence.paperChiTitle}</td>
                <td style="line-height: 130%;">${defence.paperEngTitle}</td>
                <td style="line-height: 130%;">${defence.researchDirection}</td>
                <td style="line-height: 130%;">${defence.keyWord}</td>
                <td style="line-height: 130%;">${defence.pydwOrgName}</td>
                <td style="line-height: 130%;">${defence.fwhOrgName}</td>
                <c:if test="${applicationScope.sysCfgMap['xjgl_audit_tutor'] eq GlobalConstant.FLAG_Y}">
                    <td style="line-height:20px;">
                        <c:if test="${!empty defence.tutorAuditId && !empty defence.twoTutorAuditId}">
                            导师一：${defence.tutorAuditName}<br/>导师二：${defence.twoTutorAuditName}
                        </c:if>
                        <c:if test="${empty defence.tutorAuditId or empty defence.twoTutorAuditId}">
                            ${defence.tutorAuditName}${defence.twoTutorAuditName}
                        </c:if>
                    </td>
                    <td>${empty defence.pydwAuditId?'-':defence.pydwAuditName}</td>
                </c:if>
                <c:if test="${applicationScope.sysCfgMap['xjgl_audit_tutor'] ne GlobalConstant.FLAG_Y}">
                    <td>-</td>
                    <td style="line-height: 130%;">${defence.pydwAuditName}</td>
                </c:if>
                <td style="line-height: 130%;">
                    <c:if test="${param.role eq 'ds' && defence.tutorFlow eq tutorFlow}">
                        <c:if test="${defence.tutorAuditId eq 'Passing'}">
                            <a onclick="auditInfo('${defence.recordFlow}','one');" style="cursor:pointer;color:blue;">审核</a>
                        </c:if>
                        <c:if test="${defence.tutorAuditId ne 'Passing'}">
                            <a onclick="detailInfo('${defence.recordFlow}');" style="cursor:pointer;color:blue;">查看</a>
                        </c:if>
                    </c:if>
                    <c:if test="${param.role eq 'ds' && defence.twoTutorFlow eq tutorFlow}">
                        <c:if test="${defence.twoTutorAuditId eq 'Passing'}">
                            <a onclick="auditInfo('${defence.recordFlow}','two');" style="cursor:pointer;color:blue;">审核</a>
                        </c:if>
                        <c:if test="${defence.twoTutorAuditId ne 'Passing'}">
                            <a onclick="detailInfo('${defence.recordFlow}');" style="cursor:pointer;color:blue;">查看</a>
                        </c:if>
                    </c:if>
                    <c:if test="${param.role eq 'pydw'}">
                        <c:choose>
                            <c:when test="${defence.pydwAuditId eq 'Passing'}">
                                <a onclick="auditInfo('${defence.recordFlow}');" style="cursor:pointer;color:blue;">审核</a>
                            </c:when>
                            <c:otherwise>
                                <a onclick="detailInfo('${defence.recordFlow}');" style="cursor:pointer;color:blue;">查看</a>
                            </c:otherwise>
                        </c:choose>
                    </c:if>
                    <c:if test="${param.role eq 'fwh' or param.role eq 'xx'}">
                        <a onclick="detailInfo('${defence.recordFlow}');" style="cursor:pointer;color:blue;">查看</a>
                    </c:if>
                </td>
            </tr>
            </c:forEach>
        </table>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(defenceList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>