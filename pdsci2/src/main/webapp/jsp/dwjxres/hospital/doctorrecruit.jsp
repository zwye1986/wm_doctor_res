<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $("#currentPage").val(page);
        }
        searchDoctor();
    }
    //切换录取状态
    function searchDoctorForStatus(obj){
        var status = $(obj).attr('id');
        $("#status").val(status);
        $("#currentPage").val(1);
        searchDoctor();
    }
    //查询
    function searchDoctor(){
        jboxPostLoad("content","<s:url value='/dwjxres/hospital/recruitDoctor'/>?isQuery=Y",$("#searchForm").serialize() ,true);
    }
    //打印入科通知
    function printSpeNotice(resumeFlow){
        jboxTip("打印中,请稍等...");
        var url = '<s:url value="/dwjxres/hospital/printSpeNotice?resumeFlow="/>'+resumeFlow;
        window.location.href = url;
    }
    //人员信息
    function getInfo(userFlow,batchId,flag){
        jboxOpen("<s:url value='/dwjxres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&flag="+flag,"人员信息",1000,550);
    }
    //是否报到操作
    function admitOper(resumeFlow , admitFlag,titleTypeName){
        var tip = admitFlag == "Y"?"确认报到？":"确认未报到？";
        jboxConfirm(tip, function(){
            jboxPost('<s:url value="/dwjxres/hospital/notrecruit"/>' , {"resumeFlow":resumeFlow,"statusId":admitFlag,"titleTypeName":titleTypeName} , function(resp){
                if(resp=="1"){
                    jboxTip("操作成功");
                }
                searchDoctor();
            } , null , false);
        });
    }
    //登记老师
    function registerTeacher(resumeFlow,deptFlow){
        jboxOpen("<s:url value='/dwjxres/hospital/registerTeacher?resumeFlow='/>"+resumeFlow+"&deptFlow="+deptFlow, "登记老师",800,500,true);
    }
    //结业
    function graduation(resumeFlow){
        jboxOpen("<s:url value='/dwjxres/hospital/graduation?resumeFlow='/>"+resumeFlow, "结业",1000,500,true);
    }
    function  exportInfo()
    {
        var url = "<s:url value='/dwjxres/hospital/exportAdmitPassed'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"),url);
    }
</script>
<div class="main_hd">
    <h2>学员报到</h2>
    <div class="title_tab" id="toptab">
        <ul>
            <li class="${empty param.status?'tab_select':tab}" id="" onclick="searchDoctorForStatus(this);"><a>待报到</a></li>
            <li class="${param.status=='Y'?'tab_select':tab}" id="Y" onclick="searchDoctorForStatus(this);"><a>已报到</a></li>
            <li class="${param.status=='N'?'tab_select':tab}" id="N" onclick="searchDoctorForStatus(this);"><a>未报到</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >

    <div class="div_table" style="">
        <form id="searchForm">
            <input type="hidden" id="status" name="status" value="${param.status}" />
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <table>
                <tr>
                    <td>姓&#12288;&#12288;名：</td>
                    <td><input type="text" name="userName" value="${param.userName}" class="input" /></td>
                    <td>&#12288;&#12288;进修专业：</td>
                    <td>
                        <select name="speId" class="select" style="width:100px;">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                <option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>&#12288;&#12288;进修批次：</td>
                    <td>
                        <select name="batchFlow" class="select" style="width:120px;">
                            <option value="">全部</option>
                            <c:forEach items="${batchLst}" var="dict">
                                <option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                        &#12288;&#12288;<input type="button" class="btn_green" value="查&#12288;询" onclick="searchDoctor()" />
                        <c:if test="${param.status=='Y' }">
                            <a href="javascript:void(0);" onclick="exportInfo()" class="btn_green">导&#12288;出</a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <tr>
                <th>姓名</th>
                <th>身份证号</th>
                <th>毕业专业</th>
                <th>进修时间</th>
                <th>进修专业</th>
                <c:if test="${param.status eq 'Y'}">
                    <th>带教老师</th>
                    <%--<th>是否结业</th>--%>
                </c:if>
                <th>操作</th>
            </tr>
            <c:forEach items="${stuUserLst}" var="user">
                <tr>
                    <td>${extInfoMap[user.resumeFlow].userName}</td>
                    <td>${extInfoMap[user.resumeFlow].idNo}</td>
                    <td>${user.schoolSpeName}</td>
                    <td>${user.stuTimeName}</td>
                    <td>${user.speName}</td>
                    <c:if test="${param.status eq 'Y'}">
                        <td>${user.teacherName}</td>
                        <%--<td>--%>
                            <%--<c:if test="${user.isGraduation eq 'Y'}">是</c:if>--%>
                            <%--<c:if test="${user.isGraduation eq 'N'}">否</c:if>--%>
                        <%--</td>--%>
                    </c:if>
                    <td>
                        <a onclick="getInfo('${user.userFlow}','${user.stuBatId}','Y')">[人员信息]</a>
                        <c:if test="${user.stuStatusId eq stuStatusEnumRecruitted.id}">
                            <a onclick='admitOper("${user.resumeFlow}" , "Y","${extInfoMap[user.resumeFlow].titleTypeName}")'>[报到]</a>
                            <a onclick='admitOper("${user.resumeFlow}" , "N","${extInfoMap[user.resumeFlow].titleTypeName}")'>[未报到]</a>
                        </c:if>
                        <c:if test="${user.stuStatusId eq  stuStatusEnumAdmited.id or (user.stuStatusId eq  stuStatusEnumGraduation.id) or (user.stuStatusId eq  stuStatusEnumDelayGraduation.id)}">
                            <a onclick="registerTeacher('${user.resumeFlow}','${user.speId}')">[登记老师]</a>
                            <%--<a onclick="graduation('${user.resumeFlow}')">[结业]</a>--%>
                            <a onclick="printSpeNotice('${user.resumeFlow}')">[入科条]</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty stuUserLst}">
                <tr>
                    <td colspan="7" >无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(stuUserLst)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
</div>