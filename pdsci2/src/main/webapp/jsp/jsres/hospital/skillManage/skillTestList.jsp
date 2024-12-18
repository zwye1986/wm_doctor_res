<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
    </jsp:include>
    <style type="text/css">
        td.appoint,td.differ{cursor:pointer;}
    </style>
    <script type="text/javascript">
        $(function(){
            $('.appoint,.differ').bind('click',function(){
                // var year = $(this).closest("tr").find("td").eq(1).text();
                var clinicalName = $(this).closest("tr").find("td").eq(1).text();
                var speName = $(this).closest("tr").find("td").eq(2).text();
                // var isLocal = $(this).closest("tr").find("td").eq(4).attr("value");
                var isLocal = "N";
                var clinicalFlow = $(this).closest("tr").attr("id");
                var subjectFlow = $(this).closest("tr").attr("subjectFlow");
                var url = "<s:url value='/jsres/skillTimeConfig/checkInfoManage?initFlag=Y&clinicalFlow='/>"+clinicalFlow+"&subjectFlow="+subjectFlow+"&clinicalName="+encodeURI(encodeURI(clinicalName))+"&speName="+encodeURI(encodeURI(speName))+"&isLocal="+isLocal;
                jboxPostLoad("content",url,null,true);
            });
            $('.appoint').bind('hover',function(){
                $(this).attr("title","请点击进入");
            });
            <c:if test="${jumpFlag eq 'Y'}">
            top.$("#menuSetName").text("考核信息管理");
            window.location.href="<s:url value='/osca/base/checkInfoList?isLocal=N'/>";
            </c:if>
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+$("[name='trainingTypeId']").val()).show();
        });
        function toPage(page){
            $("#currentPage").val(page);
            var roleFlag="${GlobalConstant.USER_LIST_LOCAL}";
            jboxPostLoad("content","<s:url value='/jsres/skillTimeConfig/skillTestManage'/>?roleFlag="+roleFlag,$("#searchForm").serialize(),true);
        }
        function addCheckInfo(clinicalFlow,flag,addFlag){
            if(addFlag == 'N'){
                jboxTip("暂无权限使用功能！");
                return false;
            }
            var title = clinicalFlow == ""?"新增":"编辑";
            title = flag == "view"?"查看":title;
            if(flag == "edit"){
                title="编辑";
            }
            var url = "<s:url value='/jsres/skillTimeConfig/addCheckInfo?clinicalFlow='/>"+clinicalFlow+"&flag="+flag+"&isLocal=N";
            jboxOpen(url, title,700,500);
        }
        function releasedInfo(clinicalFlow){
            jboxConfirm("发布后将无法修改考核信息，是否确认发布？", function(){
                var url = "<s:url value='/osca/base/releasedInfo?clinicalFlow='/>"+clinicalFlow;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        toPage(1);
                    }
                });
            });
        }
        function delInfo(clinicalFlow,isReleased){
            if(isReleased == "Y"){//发布后
                jboxConfirm("删除后预约学员信息将一并删除，是否确认删除？", function(){
                    var url = "<s:url value='/osca/base/delCheckInfo?clinicalFlow='/>"+clinicalFlow+"&isReleased="+isReleased;
                    jboxPost(url, null, function(resp){
                        if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                            toPage(1);
                        }
                    }, null, false);
                });
            }else{//发布前
                jboxConfirm("是否确认删除考核信息？", function(){
                    var url = "<s:url value='/osca/base/delCheckInfo?clinicalFlow='/>"+clinicalFlow;
                    jboxPost(url, null, function(resp){
                        if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                            toPage(1);
                        }
                    });
                });
            }
        }
        function queryQrCode(clinicalFlow){
            jboxOpen("<s:url value='/osca/base/queryQrCode'/>?clinicalFlow=" + clinicalFlow,'考核信息二维码',360,360);
        }

    </script>
</head>
<body id="content">
<div class="main_hd">
    <h2 class="underline">技能考核管理</h2>
</div>
<div class="main_bd">
    <div class="div_search">
        <form id="searchForm">
            <input id="currentPage" type="hidden" name="currentPage" value="1"/>
            <table class="searchTable">
                <tr>
                    <td class="td_left">考试编号：</td>
                    <td>
                        <input type="text" id="testId" name="testId" value="${param.testId}" class="input"/>
                    </td>
                    <td class="td_left">&#12288;考核名称：</td>
                    <td>
                        <input type="text" id="clinicalName" name="clinicalName" value="${param.clinicalName}" class="input"/>
                    </td>
                    <td class="td_left">&#12288;考核专业：</td>
                    <td>
                        <select name="speId" id="speId" class="select">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="spe">
                                <option value="${spe.dictId}"
                                        <c:if test="${param.speId eq spe.dictId}">selected</c:if>
                                >${spe.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="button" class="btn_green" value="查&#12288;询" onclick="toPage(1)"/>&#12288;
                        <input type="button" class="btn_green" value="新&#12288;增" onclick="addCheckInfo('','','${addFlag}')"/>&#12288;
                    </td>
                    <td colspan="4"></td>
                </tr>
            </table>
        </form>
    </div>
    <div style="padding: 0px 40px;">
        <div class="main_bd clearfix" style="margin-top:20px;">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <%--<th>序号</th>--%>
                    <th>考试编号</th>
                    <th>考核名称</th>
                    <th>考核专业</th>
                    <th>预约时间</th>
                    <th>预约人数</th>
                    <th>剩余人数</th>
                    <th>考核时间</th>
                    <th>考点地址</th>
                    <%--<th>联系电话</th>--%>
                    <%--<th style="width:100px;">二维码</th>--%>
                    <th>操作</th>
                </tr>
                <c:if test="${empty dataList}">
                    <tr>
                        <td colspan="11">无记录！</td>
                    </tr>
                </c:if>
                <c:if test="${not empty dataList}">
                    <c:forEach items="${dataList}" var="info" varStatus="i">
                    <tr id="${info.clinicalFlow}" subjectFlow="${info.subjectFlow}">
                        <%--<td class="appoint">${i.index + 1}</td>--%>
                        <td class="appoint">${info.testId}</td>
                        <td class="appoint">${info.clinicalName}</td>
                        <td class="appoint">${info.speName}</td>
                        <td class="appoint">${info.appointStartTime}~<br/>${info.appointEndTime}</td>
                        <td class="appoint">${info.appointNum}</td>
                        <td class="appoint">${info.appointNum - info.useNum}</td>
                        <td style="line-height: 130%" class="differ" title="<table><tr><th><c:if test="${empty info.examStartTimeList}">暂无考核时间</c:if><c:forEach items="${fn:split(info.examStartTimeList,',')}" var="startTime" varStatus="i">${startTime}
                            <c:forEach items="${fn:split(info.examEndTimeList,',')}" var="endTime" varStatus="j"><c:if test="${i.index eq j.index}">${not empty endTime?'~':''}${endTime}<br/></c:if></c:forEach></c:forEach>
                        </th></tr></table>">
                            <c:forEach items="${fn:split(info.examStartTimeList,',')}" var="startTime" varStatus="i">
                                <c:if test="${i.first}">${startTime}</c:if>
                            </c:forEach>
                            <c:forEach items="${fn:split(info.examEndTimeList,',')}" var="endTime" varStatus="i">
                                <c:if test="${i.first}">${not empty endTime?'~':''}<br/>${endTime}</c:if>
                                <c:if test="${i.last}">${i.count gt 1?'<br/>......':''}</c:if>
                            </c:forEach>
                        </td>
                        <td class="appoint">${info.examAddress}</td>
                        <%--<td class="appoint">${info.skillOrgPhone}</td>--%>
                        <%--<td><a onclick="queryQrCode('${info.clinicalFlow}')" style="cursor:pointer;"><c:out value="查看"/></a></td>--%>
                        <td>
                            <c:if test="${info.isReleased eq 'Y'}">
                                <a href="javascript:;" style="color:grey;"><c:out value="已发布"/></a>
                                <%--预约时间结束后且无待审核的学院，才能编辑考核时间及考点--%>
                                <c:if test="${pdfn:getCurrDateTime('yyyy-MM-dd HH:mm') gt info.appointEndTime && info.dshNum le 0 && addFlag eq 'Y'}">
                                    <a onclick="addCheckInfo('${info.clinicalFlow}','edit');" style="cursor:pointer;color:#4195c5;"><c:out value="编辑"/></a>
                                </c:if>
                                <%--未到考核时间，可删除--%>
                                <c:set var="startTimeFlag" value='0'></c:set>
                                <c:forEach items="${fn:split(info.examStartTimeList,',')}" var="startTime" varStatus="i">
                                    <c:if test="${startTime lt pdfn:getCurrDateTime('yyyy-MM-dd HH:mm')}">
                                        <c:set var="startTimeFlag" value='1'></c:set>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${empty info.examStartTimeList || startTimeFlag eq '0'}">
                                    <a onclick="delInfo('${info.clinicalFlow}','${info.isReleased}');" style="cursor:pointer;color:#4195c5;"><c:out value="删除"/></a>
                                </c:if>
                            </c:if>
                            <c:if test="${info.isReleased ne 'Y'}">
                                <%--考核时间已过，不能发布--%>
                                <c:set var="endTimeFlag" value='0'></c:set>
                                <c:forEach items="${fn:split(info.examEndTimeList,',')}" var="endTime" varStatus="i">
                                    <c:if test="${endTime gt pdfn:getCurrDateTime('yyyy-MM-dd HH:mm')}">
                                        <c:set var="endTimeFlag" value='1'></c:set>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${empty info.examEndTimeList || endTimeFlag eq '1'}">
                                    <a onclick="releasedInfo('${info.clinicalFlow}');" style="cursor:pointer;color:#4195c5;"><c:out value="发布"/></a>
                                </c:if>
                                <c:if test="${addFlag eq 'Y'}">
                                    <a onclick="addCheckInfo('${info.clinicalFlow}');" style="cursor:pointer;color:#4195c5;"><c:out value="编辑"/></a>
                                </c:if>
                                <a onclick="delInfo('${info.clinicalFlow}','${info.isReleased}');" style="cursor:pointer;color:#4195c5;"><c:out value="删除"/></a>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                </c:if>
            </table>
        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(dataList)}" scope="request"/>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>

</div>
</body>
</html>