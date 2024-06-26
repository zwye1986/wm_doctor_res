<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $("#currentPage").val(page);
        }
        searchDoctor();
    }
    //报名审核
    function searchDoctor(){
        jboxPostLoad("content","<s:url value='/czyyjxres/secretaries/changeTea'/>?isQuery=Y",$('#auditForm').serialize(),true);
    }
    function registerTeacher(resumeFlow){
        jboxOpen("<s:url value='/czyyjxres/hospital/registerTeacher?deptFlow=${deptFlow}&resumeFlow='/>"+resumeFlow, "登记老师",800,500,true);
    }
    function exportInfo(){
        jboxExp($('#auditForm'),"<s:url value='/czyyjxres/secretaries/exportQueryStu'/>?isQuery=Y");
    }
</script>
<div class="main_hd">
    <h2>学员评价</h2>
</div>
<div class="main_bd" id="div_table_0" >
    <div class="div_table" style="">
        <form id="auditForm">
            <input type="hidden" id="statusId" name="statusId" value="${param.statusId}" />
            <input id="currentPage" type="hidden" name="currentPage" value="1"/>
            <table>
                <tr>
                    <td>姓&#12288;&#12288;名：</td>
                    <td><input type="text" name="userName" value="${param.userName}" class="input"  style="width:100px;"/></td>
                    <td>进修批次：</td>
                    <td>
                        <select name="batchFlow" class="select" style="width:110px;">
                            <option value="">全部</option>
                            <c:forEach items="${batchLst}" var="dict">
                                <option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>带教老师：</td>
                    <td><input type="text" name="teaName" value="${param.teaName}" class="input"  style="width:100px;"/></td>
                    <td>
                        状态：
                    </td>
                    <td>
                        <select name="isSecretart" class="select" style="width:100px;">
                            <option value="" <c:if test="${empty param.isSecretart}">selected="selected"</c:if>></option>
                            <option value="Y" <c:if test="${param.isSecretart eq 'Y'}">selected="selected"</c:if>>已分配</option>
                            <option value="N" <c:if test="${param.isSecretart eq 'N'}">selected="selected"</c:if>>未分配</option>
                        </select>
                    </td>
                    <td>
                        &#12288;&#12288;<input type="button" class="btn_green" value="查&#12288;询" onclick="searchDoctor()" />
                        <input type="button" class="btn_green" value="导&#12288;出" onclick="exportInfo()" />
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="div_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th>姓名</th>
                    <th>毕业专业</th>
                    <th>进修时间</th>
                    <th>进修专业</th>
                    <th>进修批次</th>
                    <th>带教老师</th>
                    <th>状态</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${list}" var="user">
                    <tr>
                        <td>${user.sysUser.userName}</td>
                        <td>${user.schoolSpeName}</td>
                        <td>${user.stuTimeName}</td>
                        <td>${user.speName}</td>
                        <td>${user.stuBatName}</td>
                        <td>${user.teacherName}</td>
                        <td>
                            <c:if test="${not empty user.teacherFlow}">已分配</c:if>
                            <c:if test="${empty user.teacherFlow}">未分配</c:if>
                        </td>
                        <td>
                            <a onclick="registerTeacher('${user.resumeFlow}')">[分配老师]</a>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty list}">
                    <tr>
                        <td colspan="8">暂无信息</td>
                    </tr>
                </c:if>
            </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
</div>