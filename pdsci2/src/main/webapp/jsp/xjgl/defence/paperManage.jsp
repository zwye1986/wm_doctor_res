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
        function editApply(recordFlow,defenceFlow,viewFlag){
            var url = "<s:url value='/xjgl/paper/editTitle?recordFlow='/>"+recordFlow+"&defenceFlow="+defenceFlow+"&viewFlag="+viewFlag+"&isReplenish=${isReplenish}";
            jboxOpen(url, viewFlag=="view"?"查看":"编辑",800,600);
        }
        function addApply(defenceFlow){
            var url = "<s:url value='/xjgl/paper/editTitle?defenceFlow='/>"+defenceFlow+"&isReplenish=${isReplenish}";
            jboxOpen(url, "新增",800,600);
        }
        function delInfo(recordFlow){
            jboxConfirm("确认删除论期刊论文题目？", function(){
                var url = "<s:url value='/xjgl/paper/delTitle?recordFlow='/>"+recordFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function submitOpt(recordFlow,tutorFlow,twoTutorFlow){
            jboxConfirm("因提交后无法修改，请确认无误后再提交？", function(){
                $("#recordFlow").val(recordFlow);
                $("#tutorFlow").val(tutorFlow);
                $("#twoTutorFlow").val(twoTutorFlow);
                var url = "<s:url value='/xjgl/paper/saveDefence'/>";
                jboxPost(url, $("#myForm").serialize(), function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function expExcel(){
            var url = "<s:url value='/xjgl/paper/expTitleExcel'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#searchForm"), url, null, null, false);
            jboxEndLoading();
        }
        $(function(){
            $("#orgName").likeSearchInit({});
        });
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <c:if test="${empty param.role}">
            <form id="searchForm">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <input type="hidden" name="userFlow" value="${userFlow}">
                <input type="hidden" name="isReplenish" value="${isReplenish}">
                <div style="margin:8px 0px 5px 0px;"><input type="button" class="search" value="导&#12288;出" onclick="expExcel()"/></div>
            </form>
        </c:if>
        <c:if test="${!empty param.role}">
            <form id="searchForm" action="<s:url value="/xjgl/paper/paperList"/>" method="post">
                <div class="choseDivNewStyle">
                    <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                    <input type="hidden" name="isReplenish" value="${isReplenish}">
                    <input type="hidden" name="role" value="${param.role}">
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
                    <span style="padding-left:10px;"></span>${isReplenish eq 'Y'?'补授时间':'答辩时间'}：
                    <c:if test="${isReplenish eq 'Y'}">
                        <select class="validate[required] select" name="replenishTime" style="width:137px;">
                            <option/>
                            <c:forEach items="${dictTypeEnumReplenishTimeList}" var="dt">
                                <option value="${dt.dictName}" ${dt.dictName eq param.replenishTime?'selected':''}>${dt.dictName}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <c:if test="${isReplenish ne 'Y'}">
                        <select class="validate[required] select" name="defenceTime" style="width:137px;">
                            <option/>
                            <c:forEach items="${dictTypeEnumDefenceTimeList}" var="dt">
                                <option value="${dt.dictName}" ${dt.dictName eq param.defenceTime?'selected':''}>${dt.dictName}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <span style="padding-left:10px;"></span>研究方向：
                    <input type="text" name="researchDirection" value="${param.researchDirection}">
                    <span style="padding-left:10px;"></span>关&ensp;键&ensp;词：
                    <input type="text" name="keyWord" value="${param.keyWord}">
                    <br/>
                    <span style="padding-left:0px;"></span>刊物名称：
                    <input type="text" name="journalName" value="${param.journalName}">
                    <span style="padding-left:10px;"></span>发表年份：
                    <input type="text" name="publishYear" value="${param.publishYear}">
                    <span style="padding-left:10px;"></span>卷&#12288;&#12288;期：
                    <input type="text" name="volume" value="${param.volume}">
                    <span style="padding-left:10px;"></span>页面范围：
                    <input type="text" name="pageNumber" value="${param.pageNumber}">
                    <br/>
                    <span style="padding-left:0px;"></span>导&#12288;&#12288;师：
                    <input type="text" name="tutorName" value="${param.tutorName}">
                    <c:if test="${param.role eq 'fwh' or param.role eq 'xx'}">
                        <span style="padding-left:10px;"></span>培养单位：
                        <input id="orgName" type="text" name="orgName" value="${param.orgName}" autocomplete="off" title="${param.orgName}" flow="${param.pydwOrgFlow}"/>&#12288;
                        <div style="width:0px;height:0px;overflow:visible;float:left;position:relative;top:32px;left:290px;">
                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width:142px;border-top: none;position: relative;display: none;">
                                <c:forEach items="${orgList}" var="org">
                                    <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="height: 30px;padding-left: 10px;text-align: left; white-space:nowrap;">${org.orgName}</p>
                                </c:forEach>
                            </div>
                        </div>
                        <input type="hidden" id="orgFlow" name="pydwOrgFlow" value="${param.pydwOrgFlow}"/>
                    </c:if>
                    <c:if test="${param.role eq 'xx'}">
                        <span style="margin-left:-3px;"></span>分&ensp;委&ensp;会：
                        <select name="fwhOrgFlow" style="width:137px;" class="select">
                            <option></option>
                            <c:forEach items="${deptList}" var="dept">
                                <option value="${dept.deptFlow}" <c:if test="${dept.deptFlow eq param.fwhOrgFlow}">selected</c:if>>${dept.deptName}</option>
                            </c:forEach>
                        </select>
                    </c:if>
                    <c:if test="${param.role eq 'ds'||param.role eq 'xx'||param.role eq 'pydw'}"><span style="margin-left:10px;"></span></c:if><c:if test="${param.role eq 'fwh'}"><span style="margin-left:-3px;"></span></c:if>学习形式：
                    <select style="width: 137px;" name="studyFormId">
                        <option/>
                        <c:forEach items="${dictTypeEnumStudyFormList}" var="studyForm">
                            <option value="${studyForm.dictId}" ${param.studyFormId eq studyForm.dictId?'selected':''}>${studyForm.dictName}</option>
                        </c:forEach>
                    </select><c:if test="${param.role eq 'xx'}"><br/></c:if>
                    <input type="button" class="search" value="查&#12288;询" onclick="toPage(1)"/>
                    <input type="button" class="search" value="导&#12288;出" onclick="expExcel()"/>
                </div>
            </form>
        </c:if>
        <table class="xllist" style="width:100%;">
            <tr>
                <th style="min-width:40px;">学号</th>
                <th style="min-width:40px;">姓名</th>
                <th style="min-width:40px;">性别</th>
                <th style="min-width:40px;">专业</th>
                <th style="min-width:40px;">导师</th>
                <th>分委员会</th>
                <th>培养单位</th>
                <th style="min-width:60px;">${isReplenish eq 'Y'?'学位补授时间':'论文答辩年月'}</th>
                <th>论文中文题目</th>
                <th>论文英文题目</th>
                <th>研究方向</th>
                <th>关键字</th>
                <th>发表期刊论文题目</th>
                <th>发表刊物名称</th>
                <th style="min-width:40px;">发表年份</th>
                <th style="min-width:40px;">卷期</th>
                <th style="min-width:40px;">页面范围</th>
                <th style="min-width:60px;">操作</th>
            </tr>
            <c:forEach items="${titleList}" var="title">
                <tr>
                    <td>${title.stuNo}</td>
                    <td>${title.userName}</td>
                    <td>${title.sexName}</td>
                    <td>${title.majorName}</td>
                    <td>${title.tutorName}${!empty title.tutorName && !empty title.twoTutorName?'，':''}${title.twoTutorName}</td>
                    <td>${title.fwhOrgName}</td>
                    <td>${title.pydwOrgName}</td>
                    <td>${isReplenish eq 'Y'?title.replenishTime:title.defenceTime}</td>
                    <td>${title.paperChiTitle}</td>
                    <td>${title.paperEngTitle}</td>
                    <td>${title.researchDirection}</td>
                    <td>${title.keyWord}</td>
                    <td>${title.paperTitle}</td>
                    <td>${title.journalName}</td>
                    <td>${title.publishYear}</td>
                    <td>${title.volume}</td>
                    <td>${title.pageNumber}</td>
                    <td>
                        <c:if test="${empty param.role}">
                            <a onclick="editApply('${title.recordFlow}','${title.defenceFlow}');" style="cursor:pointer;color:blue;">编辑</a>
                            <c:if test="${!empty title.recordFlow}">
                                <a onclick="delInfo('${title.recordFlow}');" style="cursor:pointer;color:blue;">删除</a>
                                <a onclick="addApply('${title.defenceFlow}');" style="cursor:pointer;color:blue;">新增</a>
                            </c:if>
                        </c:if>
                        <c:if test="${!empty param.role}">
                            <a onclick="editApply('${title.recordFlow}','${title.defenceFlow}','view');" style="cursor:pointer;color:blue;">查看</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty titleList}">
                <tr><td colspan="99">无记录</td></tr>
            </c:if>
        </table>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(titleList)}" scope="request"></c:set>
            <pd:pagination toPage="toPage"/>
        </div>
    </div>
</div>
</body>
</html>