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
        $(document).ready(function(){
            <c:if test="${readOnlyFlag eq 'Y'}">
                $("input[type='radio']").attr('disabled','disabled');
            </c:if>
            $("input[type='radio']").each(function(){
                if(!$(this).attr('disabled')){
                    $(this).next().css("color","blue");
                    $(this).next().css("cursor","pointer");
                    $(this).css("cursor","pointer");
                }
            });
        });
        function updateOpt(recordFlow,flag,column){
            var url = "<s:url value='/xjgl/paper/updateQualify?recordFlow='/>"+recordFlow+"&"+column+"="+flag;
            jboxPost(url, null, function(resp){
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    location.reload();
                }
            }, null, true);
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
            var url = "<s:url value='/xjgl/paper/expQualify?role=${param.role}'/>";
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
                </div>
            </form>
        </c:if>
        <c:if test="${param.role ne 'xs'}">
            <form id="searchForm" action="<s:url value="/xjgl/paper/qualifyApply?role=${param.role}"/>" method="post">
                <div class="choseDivNewStyle">
                    <input id="currentPage" type="hidden" name="currentPage" value="1"/>
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
                <th rowspan="2" style="min-width:40px;">学号</th>
                <th rowspan="2" style="min-width:40px;">姓名</th>
                <th rowspan="2" style="min-width:40px;">导师</th>
                <th rowspan="2" style="min-width:60px;">专业名称</th>
                <th rowspan="2" style="min-width:60px;">培养层次</th>
                <th rowspan="2" style="min-width:60px;">培养类型</th>
                <th rowspan="2" style="min-width:60px;">答辩时间</th>
                <th rowspan="2" style="min-width:80px;">中文论文题目</th>
                <th rowspan="2" style="min-width:80px;">英文论文题目</th>
                <th rowspan="2" style="min-width:60px;">研究方向</th>
                <th rowspan="2" style="min-width:50px;">关键字</th>
                <th rowspan="2" style="min-width:60px;">培养单位</th>
                <th rowspan="2" style="min-width:50px;">分委会</th>
                <th rowspan="2" style="min-width:100px;">是否进行学位论文相似性检测</th>
                <th rowspan="2" style="min-width:100px;">导师、学科是否认为学位论文相似性检测结果合格</th>
                <th rowspan="2" style="min-width:70px;">预答辩学位论文结果</th>
                <th rowspan="2" style="min-width:80px;">论文评阅结果</th>
                <th colspan="2" style="min-width:60px;">盲审过程</th>
                <th rowspan="2" style="min-width:120px;">盲审结果</th>
                <th rowspan="2" style="min-width:60px;">其他</th>
                <th rowspan="2" style="min-width:70px;">是否具有答辩资格</th>
            </tr>
            <tr>
                <th style="min-width:60px;">初审</th>
                <th style="min-width:60px;">复审</th>
            </tr>
            <c:forEach items="${defenceList}" var="defence" varStatus="i">
                <tr>
                    <td>${defence.stuNo}</td>
                    <td>${defence.userName}</td>
                    <td>${defence.tutorName}${!empty defence.tutorFlow && !empty defence.twoTutorFlow?'，':''}${defence.twoTutorName}</td>
                    <td>${defence.majorName}</td>
                    <td>${defence.trainGradationName}</td>
                    <td>${defence.trainCategoryName}</td>
                    <td>${defence.defenceTime}</td>
                    <td>${defence.paperChiTitle}</td>
                    <td>${defence.paperEngTitle}</td>
                    <td>${defence.researchDirection}</td>
                    <td>${defence.keyWord}</td>
                    <td>${defence.pydwOrgName}</td>
                    <td>${defence.fwhOrgName}</td>
                    <td>
                        <input type="radio" id="paperSameFlagY${i.index}" name="paperSameFlag${i.index}" ${defence.paperSameFlag eq 'Y'?'checked':''} onchange="updateOpt('${defence.recordFlow}','Y','paperSameFlag')" ${param.role eq 'xs' or param.role eq 'xx' or param.role eq 'ds'?'':'disabled'}><label for="paperSameFlagY${i.index}">是</label>
                        <input type="radio" id="paperSameFlagN${i.index}" name="paperSameFlag${i.index}" ${defence.paperSameFlag eq 'N'?'checked':''} onchange="updateOpt('${defence.recordFlow}','N','paperSameFlag')" ${param.role eq 'xs' or param.role eq 'xx' or param.role eq 'ds'?'':'disabled'}><label for="paperSameFlagN${i.index}">否</label>
                    </td>
                    <td>
                        <input type="radio" id="thinkSameFlagY${i.index}" name="thinkSameFlag${i.index}" ${defence.thinkSameFlag eq 'Y'?'checked':''} onchange="updateOpt('${defence.recordFlow}','Y','thinkSameFlag')" ${param.role eq 'xs' or param.role eq 'xx' or param.role eq 'ds'?'':'disabled'}><label for="thinkSameFlagY${i.index}">是</label>
                        <input type="radio" id="thinkSameFlagN${i.index}" name="thinkSameFlag${i.index}" ${defence.thinkSameFlag eq 'N'?'checked':''} onchange="updateOpt('${defence.recordFlow}','N','thinkSameFlag')" ${param.role eq 'xs' or param.role eq 'xx' or param.role eq 'ds'?'':'disabled'}><label for="thinkSameFlagN${i.index}">否</label>
                    </td>
                    <td>
                        <input type="radio" id="preDefenceFlagY${i.index}" name="preDefenceFlag${i.index}" ${defence.preDefenceFlag eq 'Y'?'checked':''} onchange="updateOpt('${defence.recordFlow}','Y','preDefenceFlag')" ${param.role eq 'ds' or param.role eq 'xx'?'':'disabled'}><label for="preDefenceFlagY${i.index}">合格</label><br/>
                        <input type="radio" id="preDefenceFlagN${i.index}" name="preDefenceFlag${i.index}" ${defence.preDefenceFlag eq 'N'?'checked':''} onchange="updateOpt('${defence.recordFlow}','N','preDefenceFlag')" ${param.role eq 'ds' or param.role eq 'xx'?'':'disabled'}><label for="preDefenceFlagN${i.index}">不合格</label>
                    </td>
                    <td>
                        <input type="radio" id="paperReadFlagY${i.index}" name="paperReadFlag${i.index}" ${defence.paperReadFlag eq 'Y'?'checked':''} onchange="updateOpt('${defence.recordFlow}','Y','paperReadFlag')" ${(param.role eq 'fwh' && defence.trainGradationId eq '1') or param.role eq 'xx'?'':'disabled'}><label for="paperReadFlagY${i.index}">合格</label><br/>
                        <input type="radio" id="paperReadFlagN${i.index}" name="paperReadFlag${i.index}" ${defence.paperReadFlag eq 'N'?'checked':''} onchange="updateOpt('${defence.recordFlow}','N','paperReadFlag')" ${(param.role eq 'fwh' && defence.trainGradationId eq '1') or param.role eq 'xx'?'':'disabled'}><label for="paperReadFlagN${i.index}">不合格</label>
                    </td>
                    <td>
                        <input type="radio" id="firstAuditFlagY${i.index}" name="firstAuditFlag${i.index}" ${defence.firstAuditFlag eq 'Y'?'checked':''} onchange="updateOpt('${defence.recordFlow}','Y','firstAuditFlag')" ${empty defence.lastAuditFlag &&((param.role eq 'fwh' && defence.trainGradationId eq '1') or param.role eq 'xx')?'':'disabled'}><label for="firstAuditFlagY${i.index}">通过</label><br/>
                        <input type="radio" id="firstAuditFlagN${i.index}" name="firstAuditFlag${i.index}" ${defence.firstAuditFlag eq 'N'?'checked':''} onchange="updateOpt('${defence.recordFlow}','N','firstAuditFlag')" ${empty defence.lastAuditFlag &&((param.role eq 'fwh' && defence.trainGradationId eq '1') or param.role eq 'xx')?'':'disabled'}><label for="firstAuditFlagN${i.index}">不通过</label>
                    </td>
                    <td>
                        <input type="radio" id="lastAuditFlagY${i.index}" name="lastAuditFlag${i.index}" ${defence.lastAuditFlag eq 'Y'?'checked':''} onchange="updateOpt('${defence.recordFlow}','Y','lastAuditFlag')" ${defence.firstAuditFlag eq 'N' &&((param.role eq 'fwh' && defence.trainGradationId eq '1') or param.role eq 'xx')?'':'disabled'}><label for="lastAuditFlagY${i.index}">通过</label><br/>
                        <input type="radio" id="lastAuditFlagN${i.index}" name="lastAuditFlag${i.index}" ${defence.lastAuditFlag eq 'N'?'checked':''} onchange="updateOpt('${defence.recordFlow}','N','lastAuditFlag')" ${defence.firstAuditFlag eq 'N' &&((param.role eq 'fwh' && defence.trainGradationId eq '1') or param.role eq 'xx')?'':'disabled'}><label for="lastAuditFlagN${i.index}">不通过</label>
                    </td>
                    <td><input type="radio" name="auditResultFlag${i.index}" ${defence.auditResultFlag eq 'Y'?'checked':''} disabled>通过<br/><input type="radio" name="auditResultFlag${i.index}" ${defence.auditResultFlag eq 'N'?'checked':''} disabled>不通过</td>
                    <td>
                        <input type="radio" id="otherFlagY${i.index}" name="otherFlag${i.index}" ${defence.otherFlag eq 'Y'?'checked':''} onchange="updateOpt('${defence.recordFlow}','Y','otherFlag')" ${(param.role eq 'fwh' && defence.trainGradationId eq '1') or param.role eq 'xx'?'':'disabled'}><label for="otherFlagY${i.index}">是</label>
                        <input type="radio" id="otherFlagN${i.index}" name="otherFlag${i.index}" ${defence.otherFlag eq 'N'?'checked':''} onchange="updateOpt('${defence.recordFlow}','N','otherFlag')" ${(param.role eq 'fwh' && defence.trainGradationId eq '1') or param.role eq 'xx'?'':'disabled'}><label for="otherFlagN${i.index}">否</label>
                    </td>
                    <td>
                        <input type="radio" id="formalDefenceFlagY${i.index}" name="formalDefenceFlag${i.index}" ${defence.formalDefenceFlag eq 'Y'?'checked':''} onchange="updateOpt('${defence.recordFlow}','Y','formalDefenceFlag')" ${param.role eq 'xx'?'':'disabled'}><label for="formalDefenceFlagY${i.index}">是</label>
                        <input type="radio" id="formalDefenceFlagN${i.index}" name="formalDefenceFlag${i.index}" ${defence.formalDefenceFlag eq 'N'?'checked':''} onchange="updateOpt('${defence.recordFlow}','N','formalDefenceFlag')" ${param.role eq 'xx'?'':'disabled'}><label for="formalDefenceFlagN${i.index}">否</label>
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