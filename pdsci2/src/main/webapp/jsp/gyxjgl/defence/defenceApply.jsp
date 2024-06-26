<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function editApply(recordFlow,flag){
            var title = flag=='view'?"查看":(recordFlow == ""?"新增":"编辑");
            var url = "<s:url value='/gyxjgl/paper/editDefence?recordFlow='/>"+recordFlow+"&viewFlag="+flag+"&isReplenish=${isReplenish}";
            jboxOpen(url, title,800,460);
        }
        function delInfo(recordFlow){
            jboxConfirm("确认删除答辩申请？", function(){
                var url = "<s:url value='/gyxjgl/paper/delDefence?recordFlow='/>"+recordFlow;
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
                var url = "<s:url value='/gyxjgl/paper/saveDefence'/>";
                jboxPost(url, $("#myForm").serialize(), function(resp){
                    if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                        location.reload();
                    }
                }, null, true);
            });
        }
        function expExcel(){
            var url = "<s:url value='/gyxjgl/paper/expUserExcel'/>";
            jboxTip("导出中…………");
            jboxSubmit($("#myForm"), url, null, null, false);
            jboxEndLoading();
        }
        function toPage(page){
            $("#currentPage").val(page);
            $("#searchForm").submit();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="recordFlow" id="recordFlow">
            <input type="hidden" name="tutorFlow" id="tutorFlow">
            <input type="hidden" name="twoTutorFlow" id="twoTutorFlow">
            <input type="hidden" name="statusId" value="Submit">
            <input type="hidden" name="statusName" value="提交">
            <input type="hidden" name="isReplenish" value="${isReplenish}">
        </form>
        <form id="searchForm">
            <div class="choseDivNewStyle">
                <input id="currentPage" type="hidden" name="currentPage" value="1"/>
                <c:if test="${empty majorCredit or majorCredit.CREDIT le majorCredit.SCORESUM}">
                    <input type="button" class="search" value="新&#12288;增" onclick="editApply('')"/>
                </c:if>
                <input type="button" class="search" value="导&#12288;出" onclick="expExcel()"/>
            </div>
        </form>
        <table class="xllist" style="width:100%;">
            <tr>
                <th style="min-width:50px;line-height: 130%;">学号</th>
                <th style="min-width:50px;line-height: 130%;">姓名</th>
                <th style="min-width:50px;line-height: 130%;">导师</th>
                <th style="min-width:50px;line-height: 130%;">专业代码</th>
                <th style="min-width:50px;line-height: 130%;">专业名称</th>
                <th style="min-width:50px;line-height: 130%;">培养层次</th>
                <th style="min-width:50px;line-height: 130%;">培养类型</th>
                <c:if test="${isReplenish ne 'Y'}">
                    <th style="min-width:50px;line-height: 130%;">答辩时间</th>
                </c:if>
                <c:if test="${isReplenish eq 'Y'}">
                    <th>学位补授时间</th>
                </c:if>
                <th>中文论文题目</th>
                <th>英文论文题目</th>
                <th>研究方向</th>
                <th>关键字</th>
                <th>导师审核</th>
                <th>二级机构审核</th>
                <th style="min-width:60px;">操作</th>
            </tr>
            <c:forEach items="${defenceList}" var="defence">
                <tr>
                    <td>${defence.stuNo}</td>
                    <td>${defence.userName}</td>
                    <td>${defence.tutorName}${!empty defence.tutorName && !empty defence.twoTutorName?'，':''}${defence.twoTutorName}</td>
                    <td>${defence.majorId}</td>
                    <td>${defence.majorName}</td>
                    <td>${defence.trainGradationName}</td>
                    <td>${defence.trainCategoryName}</td>
                    <c:if test="${isReplenish ne 'Y'}">
                        <td>${defence.defenceTime}</td>
                    </c:if>
                    <c:if test="${isReplenish eq 'Y'}">
                        <td>${defence.replenishTime}</td>
                    </c:if>
                    <td>${defence.paperChiTitle}</td>
                    <td>${defence.paperEngTitle}</td>
                    <td>${defence.researchDirection}</td>
                    <td>${defence.keyWord}</td>
                    <c:choose>
                        <c:when test="${defence.statusId eq 'Save'}"><td>-</td><td>-</td></c:when>
                        <c:otherwise>
                            <%--需要导师审核--%>
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
                                <td>${defence.pydwAuditName}</td>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <c:if test="${defence.statusId eq 'Save'}">
                            <a onclick="editApply('${defence.recordFlow}');" style="cursor:pointer;color:blue;">编辑</a>
                            <a onclick="delInfo('${defence.recordFlow}');" style="cursor:pointer;color:blue;">删除</a>
                            <a onclick="submitOpt('${defence.recordFlow}','${defence.tutorFlow}','${defence.twoTutorFlow}');" style="cursor:pointer;color:blue;">提交</a>
                        </c:if>
                        <c:if test="${defence.statusId eq 'Submit'}">
                            <a onclick="editApply('${defence.recordFlow}','view');" style="cursor:pointer;color:blue;">查看</a>
                        </c:if>
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