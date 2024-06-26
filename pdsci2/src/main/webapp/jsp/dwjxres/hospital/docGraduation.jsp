<script type="text/javascript">
    //分页
    function toPage(page) {
        if (page != undefined) {
            $("#currentPage").val(page);
        }
        searchDoctor();
    }
    //切换录取状态
    function searchDoctorForStatus(obj) {
        var status = $(obj).attr('id');
        $("#status").val(status);
        $("#currentPage").val(1);
        searchDoctor();
    }
    //查询
    function searchDoctor() {
        jboxPostLoad("content", "<s:url value='/dwjxres/hospital/graduationDoctor'/>?isQuery=Y", $("#searchForm").serialize(), true);
    }
    function exportInfo() {
        var url = "<s:url value='/dwjxres/hospital/exportGraduation'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"), url);
    }
    //打印入科通知
    function printSpeNotice(resumeFlow) {
        jboxTip("打印中,请稍等...");
        var url = '<s:url value="/dwjxres/hospital/printSpeNotice?resumeFlow="/>' + resumeFlow;
        window.location.href = url;
    }
    //人员信息
    function getInfo(userFlow, batchId) {
        jboxOpen("<s:url value='/dwjxres/doctor/getsingupinfoaudit'/>?userFlow=" + userFlow + "&batchId=" + batchId, "人员信息", 1000, 550);
    }
    //结业
    function graduation(resumeFlow) {
        jboxOpen("<s:url value='/dwjxres/hospital/graduation?resumeFlow='/>" + resumeFlow, "结业", 1000, 500, true);
    }
    //发放结业证书
    function issueCertificate(resumeFlow) {
        var url = "<s:url value='/dwjxres/hospital/issueCertificate'/>?resumeFlow=" + resumeFlow;
        jboxConfirm("确认发放结业证书？", function () {
            jboxPost(url, null, function (resp) {
                if (resp == "${GlobalConstant.OPERATE_SUCCESSED}") {
                    searchDoctor();
                    jboxClose();
                }
            }, null, true);
        }, null);
    }
    function showFile(fileFlow) {
        jboxPost("<s:url value='/dwjxres/doctor/checkFile'/>?fileFlow=" + fileFlow, null,
                function (resp) {
                    //直接打开
                    if (resp != "1") {
                        var href = "${sysCfgMap['upload_base_url']}/" + resp;
                        $("#fileSrc").attr("href", href);
                        $("#fileSrc").attr("target", "_blank");
                        $("#fileSrc").find("span").trigger("click");
                    } else {
                        //下载
                        var href = "<s:url value='/dwjxres/doctor/fileDown'/>?fileFlow=" + fileFlow;
                        $("#fileSrc").attr("href", href);
                        $("#fileSrc").removeAttr("target");
                        $("#fileSrc").find("span").trigger("click");
                    }
                }, null, false);
    }
</script>
<div class="main_hd">
    <h2>学员结业</h2>
    <div class="title_tab" id="toptab">
        <ul>
            <li class="${empty param.status?'tab_select':tab}" id="" onclick="searchDoctorForStatus(this);"><a>待结业</a>
            </li>
            <li class="${param.status=='Y'?'tab_select':tab}" id="Y" onclick="searchDoctorForStatus(this);"><a>已结业</a>
            </li>
            <li class="${param.status=='N'?'tab_select':tab}" id="N" onclick="searchDoctorForStatus(this);"><a>延期结业</a>
            </li>
        </ul>
    </div>
</div>
<a id="fileSrc" target="_blank" style="display: none;"><span></span></a>
<div class="main_bd" id="div_table_0">

    <div class="div_table" style="">
        <form id="searchForm">
            <input type="hidden" id="status" name="status" value="${param.status}"/>
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <table>
                <tr>
                    <td>姓&#12288;&#12288;名：</td>
                    <td><input type="text" name="userName" value="${param.userName}" class="input"/></td>
                    <td>进修专业：</td>
                    <td>
                        <select name="speId" class="select" style="width:100px;">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                <option value="${dict.dictId}"
                                        <c:if test="${param.speId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>进修批次：</td>
                    <td>
                        <select name="batchFlow" class="select" style="width:120px;">
                            <option value="">全部</option>
                            <c:forEach items="${batchLst}" var="dict">
                                <option value="${dict.batchFlow}"
                                        <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                    </td>
                    <td>
                    </td>
                    <td>
                        &#12288;&#12288;<input type="button" class="btn_green" value="查&#12288;询"
                                               onclick="searchDoctor()"/>
                        <c:if test="${param.status=='Y' }">
                            <input type="button" class="btn_green" value="导&#12288;出" onclick="exportInfo()"/>
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
                <th>操作</th>
            </tr>
            <c:forEach items="${stuUserLst}" var="user">
                <tr>
                    <td>${extInfoMap[user.resumeFlow].userName}</td>
                    <td>${extInfoMap[user.resumeFlow].idNo}</td>
                    <td>${user.schoolSpeName}</td>
                    <td>${user.stuTimeName}</td>
                    <td>${user.speName}</td>
                    <td>
                        <c:if test="${not empty stuFileMap[user.resumeFlow]}">
                            <a onclick="showFile('${stuFileMap[user.resumeFlow].fileFlow}');">[查看进修鉴定表]</a>
                        </c:if>
                        <c:if test="${user.stuStatusId eq stuStatusEnumAdmited.id or (user.stuStatusId eq stuStatusEnumDelayGraduation.id )}">
                            <a onclick="graduation('${user.resumeFlow}')">[结业信息审核]</a>
                        </c:if>
                        <c:if test="${user.stuStatusId eq stuStatusEnumGraduation.id and user.issueCertificate ne GlobalConstant.FLAG_Y}">
                            <a onclick="issueCertificate('${user.resumeFlow}');">[发放结业证书]</a>
                        </c:if>
                        <c:if test="${user.stuStatusId eq stuStatusEnumGraduation.id and user.issueCertificate eq GlobalConstant.FLAG_Y}">
                            <a href="<s:url value='/dwjxres/doctor/showCertificate?flag=view&resumeFlow=${user.resumeFlow}'/>"
                               target="_blank">[查看结业证书]</a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            <c:if test="${empty stuUserLst}">
                <tr>
                    <td colspan="6">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(stuUserLst)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
</div>