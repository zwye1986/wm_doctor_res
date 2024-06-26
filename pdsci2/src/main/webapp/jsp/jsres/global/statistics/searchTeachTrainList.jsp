<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="login" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
</jsp:include>
<style type="text/css">
    .boxHome .item:HOVER {
        background-color: #eee;
    }

    .cur {
        color: red
    }
</style>
<link rel="stylesheet" type="text/css"
      href="<s:url value='/js/viewer/viewer2.min.css'/>?v=${applicationScope.sysCfgMap['sys_version']}"></link>
<script type="text/javascript"
        src="<s:url value='/js/viewer/viewer.min.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    $(function () {
        <c:forEach items="${teacherTrainingList}" var="teacher" varStatus="status">
        var id = "ratateImg${status.index+1}";
        if (${not empty teacher.certificateUrl}) {
            var viewer = new Viewer(document.getElementById(id), {
                url: 'data-original'
            });
        }
        </c:forEach>
    });

</script>

<div class="main_bd">
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <%--                <th width="8%">证书编号</th>--%>
                <th width="7%">姓名</th>
                <th width="5%">性别</th>
                <th width="9%">技术职称</th>
                <th width="9%">师资级别</th>
                <th width="15%">基地名称</th>
                <th width="8%">科室</th>
                <th width="5%">专业</th>
                <th width="5%">年份</th>
                <th width="14%">证书附件</th>
                <th width="14%">成果附件</th>
                <th width="18%">操作</th>
            </tr>
            <c:forEach items="${teacherTrainingList }" var="teacher" varStatus="status">
                <tr>
                        <%--                    <td>${teacher.certificateNo }</td>--%>
                    <td>${teacher.doctorName }</td>
                    <td>${teacher.sexName }</td>
                    <td>${teacher.technicalTitle }</td>
                    <td>${teacher.teacherLevelName }</td>
                    <td>${teacher.orgName}</td>
                    <td>${teacher.deptName}</td>
                    <td>${teacher.speName}</td>
                    <td>${teacher.trainingYear}</td>
                    <c:if test="${empty teacher.certificateUrl}">
                        <td>
                            <div>
                                <ul>
                                    <li>
                                        <span>附件未上传</span>
                                    </li>
                                    <li><span style="font-size: 12px;color: #459ae9;"
                                              title="证书编号">${teacher.certificateNo }</span></li>
                                </ul>
                            </div>
                        </td>
                    </c:if>
                    <c:if test="${not empty teacher.certificateUrl}">
                        <td style="height: 100px;">
                            <div align="center">
                                <ul>
                                    <li id="ratateImg${status.index+1}">
                                        <img src="${sysCfgMap['upload_base_url']}/${teacher.certificateUrl}"
                                             style="width: 80px;height: 80px;">
                                    </li>
                                    <li><span style="font-size: 12px;color: #459ae9;"
                                              title="证书编号">${teacher.certificateNo }</span></li>
                                </ul>
                            </div>
                        </td>
                    </c:if>
                    <c:if test="${empty teacher.achievementUrl}">
                        <td>
                            <div>
                                <ul>
                                    <li>
                                        <span>未上传</span>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </c:if>
                    <c:if test="${not empty teacher.achievementUrl}">
                        <td style="height: 100px;">
                            <div align="center">
                                <ul>
                                    <li id="ratateImg${status.index+1}">
                                        <img src="${sysCfgMap['upload_base_url']}/${teacher.achievementUrl}"
                                             style="width: 80px;height: 80px;">
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </c:if>

                    <td>
                        <a class="btn" onclick="editInfo('${teacher.recordFlow}','${roleFlag}');">编辑</a>
                        <a class="btn" onclick="deleteInfo('${teacher.recordFlow}');">删除</a>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty teacherTrainingList}">
                <tr>
                    <td colspan="10">无记录</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(teacherTrainingList)}" scope="request"></c:set>
        <pd:pagination-jsres toPage="toPage"/>
    </div>
</div>