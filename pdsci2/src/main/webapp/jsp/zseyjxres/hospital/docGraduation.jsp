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
        jboxPostLoad("content", "<s:url value='/zseyjxres/hospital/graduationDoctor'/>?isQuery=Y", $("#searchForm").serialize(), true);
    }
    function exportInfo() {
        var url = "<s:url value='/zseyjxres/hospital/exportGraduation'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"), url);
    }
    //打印入科通知
    function printSpeNotice(resumeFlow) {
        jboxTip("打印中,请稍等...");
        var url = '<s:url value="/zseyjxres/hospital/printSpeNotice?resumeFlow="/>' + resumeFlow;
        window.location.href = url;
    }
    //人员信息
    function getInfo(userFlow, batchId) {
        jboxOpen("<s:url value='/zseyjxres/doctor/getsingupinfoaudit'/>?userFlow=" + userFlow + "&batchId=" + batchId, "人员信息", 1000, 550);
    }
    //结业
    function graduation(resumeFlow) {
        jboxOpen("<s:url value='/zseyjxres/hospital/graduation?resumeFlow='/>" + resumeFlow, "结业", 1000, 500, true);
    }
    //发放结业证书
    function issueCertificate(resumeFlow) {
        var url = "<s:url value='/zseyjxres/hospital/issueCertificate'/>?resumeFlow=" + resumeFlow;
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
        jboxPost("<s:url value='/zseyjxres/doctor/checkFile'/>?fileFlow=" + fileFlow, null,
                function (resp) {
                    //直接打开
                    if (resp != "1") {
                        var href = "${sysCfgMap['upload_base_url']}/" + resp;
                        $("#fileSrc").attr("href", href);
                        $("#fileSrc").attr("target", "_blank");
                        $("#fileSrc").find("span").trigger("click");
                    } else {
                        //下载
                        var href = "<s:url value='/zseyjxres/doctor/fileDown'/>?fileFlow=" + fileFlow;
                        $("#fileSrc").attr("href", href);
                        $("#fileSrc").removeAttr("target");
                        $("#fileSrc").find("span").trigger("click");
                    }
                }, null, false);
    }
    function printCertificate(flow){
        jboxTip("打印中,请稍等...");
        var url = '<s:url value="/zseyjxres/doctor/englishCertificate?resumeFlow="/>'+flow;
        window.location.href = url;
    }

    /**
     *模糊查询加载
     */
    (function($){
        $.fn.likeSearchInit = function(option){
            option.clickActive = option.clickActive || null;

            var searchInput = this;
            searchInput.on("keyup focus",function(){
                $("#boxHome").show();
                if($.trim(this.value)){
                    $("#boxHome .item").hide();
                    var items = $("#boxHome .item[value*='"+this.value+"']").show();
                    if(!items){
                        $("#boxHome").hide();
                    }
                }else{
                    $("#boxHome .item").show();
                }
            });
            searchInput.on("blur",function(){
                if(!$("#boxHome.on").length){
                    $("#boxHome").hide();
                }
            });
            $("#boxHome").on("mouseenter mouseleave",function(){
                $(this).toggleClass("on");
            });
            $("#boxHome .item").click(function(){
                $("#boxHome").hide();
                var value = $(this).attr("value");
                $("#itemName").val(value);
                searchInput.val(value);
                if(option.clickActive){
                    option['clickActive']($(this).attr("flow"));
                }
            });
        };
    })(jQuery);
    //页面加载完成时调用
    $(function(){
        $("#speSel").likeSearchInit({

        });
    });
    //打印结业证书
    function printInfo(resumeFlow) {
        jboxTip("打印中,请稍等...");
        var url = '<s:url value="/zseyjxres/doctor/showCertificate?resumeFlow="/>' + resumeFlow;
        window.location.href = url;
    }

</script>
<style type="text/css">
    #boxHome .item:HOVER{background-color: #eee;}
</style>

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

    <div class="<%--div_table--%>" style="padding:20px 25px 0;margin-bottom: 10px;">
        <form id="searchForm">
            <input type="hidden" id="status" name="status" value="${param.status}"/>
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <table>
                <tr>
                    <td>姓&#12288;&#12288;名：</td>
                    <td><input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px"/></td>
                    <td>进修专业：</td>
                    <td>
                        <input id="speSel" style="width: 107px" class="toggleView input" type="text" name="speName2" value="${param.speName2}" autocomplete="off" title="${param.speName2}" onmouseover="this.title = this.value"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; /*top:30px;*/left:4px;">
                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 158px;border-top: none;position: relative;display: none;">
                                <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                    <p class="item" flow="${dict.dictId}" value="${dict.dictName}" style="height: 30px;padding-left: 10px;">${dict.dictName}</p>
                                </c:forEach>
                            </div>
                        </div>

                        <%--<select name="speId" class="select" style="width:100px;">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                <option value="${dict.dictId}"
                                        <c:if test="${param.speId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>--%>
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
                <th colspan="2">进修专业(进修时间：月)</th>
                <th>操作</th>
            </tr>
            <c:forEach items="${stuUserLst}" var="user">
                <tr>
                    <td>${extInfoMap[user.resumeFlow].userName}</td>
                    <td>${extInfoMap[user.resumeFlow].idNo}</td>
                    <td>${user.schoolSpeName}</td>

                    <td colspan="2">
                        <c:forEach items="${extInfoMap[user.resumeFlow].speFormList}" var="speForm">
                            ${speForm.speName}(${speForm.stuTimeId})
                        </c:forEach>
                    </td>

                    <td>
                        <c:if test="${not empty stuFileMap[user.resumeFlow]}">
                            <a onclick="showFile('${stuFileMap[user.resumeFlow].fileFlow}');">[查看进修鉴定表]</a>
                        </c:if>
                        <c:if test="${user.stuStatusId eq stuStatusEnumAdmited.id or (user.stuStatusId eq stuStatusEnumDelayGraduation.id ) and flag1 eq GlobalConstant.FLAG_Y}">
                            <a onclick="graduation('${user.resumeFlow}')">[延期结业审核]</a>
                            <%--<a href="<s:url value='/gzzyjxres/doctor/showCertificate?flag=view&resumeFlow=${user.resumeFlow}'/>"--%>
                               <%--target="_blank">[打印结业证书]</a>--%>
                            <c:if test="${flag1 eq GlobalConstant.FLAG_Y}">
                            <a onclick="printInfo('${user.resumeFlow}')">打印结业证书</a>
                            </c:if>
                            <%--<a onclick="printCertificate('${user.resumeFlow}')">[英文证书模板]</a>--%>
                        </c:if>
                        <script type="text/javascript">
                            console.log('${user.stuStatusId eq stuStatusEnumGraduation.id and user.issueCertificate ne GlobalConstant.FLAG_Y and flag1 eq GlobalConstant.FLAG_Y}');
                        </script>

                        <c:if test="${user.stuStatusId eq stuStatusEnumGraduation.id and user.issueCertificate ne GlobalConstant.FLAG_Y and flag1 eq GlobalConstant.FLAG_Y}">
                            <a onclick="issueCertificate('${user.resumeFlow}');">[发放结业证书]</a>
                        </c:if>
                        <%--<c:if test="${user.stuStatusId eq stuStatusEnumGraduation.id and user.issueCertificate eq GlobalConstant.FLAG_Y}">
                            <a href="<s:url value='/gzzyjxres/doctor/showCertificate?flag=view&resumeFlow=${user.resumeFlow}'/>"
                               target="_blank">[查看结业证书]</a>
                            <a onclick="printCertificate('${user.resumeFlow}')">[英文证书模板]</a>
                        </c:if>--%>
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