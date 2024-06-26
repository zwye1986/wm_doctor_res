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
        jboxPostLoad("content", "<s:url value='/gzzyjxres/hospital/graduationDoctor'/>?isQuery=Y", $("#searchForm").serialize(), true);
    }
    //导出
    function exportInfo() {
        var url = "<s:url value='/gzzyjxres/hospital/exportGraduation'/>";
        jboxExp($("#searchForm"), url);
    }
    //打印入科通知
    function printSpeNotice(resumeFlow) {
        jboxTip("打印中,请稍等...");
        var url = '<s:url value="/gzzyjxres/hospital/printSpeNotice?resumeFlow="/>' + resumeFlow;
        window.location.href = url;
    }
    //人员信息
    function getInfo(userFlow, batchId) {
        jboxOpen("<s:url value='/gzzyjxres/doctor/getsingupinfoaudit'/>?userFlow=" + userFlow + "&batchId=" + batchId, "人员信息", 1000, 550);
    }
    //结业
    function graduation(resumeFlow) {
        jboxOpen("<s:url value='/gzzyjxres/hospital/graduation?resumeFlow='/>" + resumeFlow, "结业", 1000, 500, true);
    }
    //发放结业证书
    function issueCertificate(resumeFlow) {
        var url = "<s:url value='/gzzyjxres/hospital/issueCertificate'/>?resumeFlow=" + resumeFlow;
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
        jboxPost("<s:url value='/gzzyjxres/doctor/checkFile'/>?fileFlow=" + fileFlow, null,
                function (resp) {
                    //直接打开
                    if (resp != "1") {
                        var href = "${sysCfgMap['upload_base_url']}/" + resp;

//                        window.open(href);
                        window.location.href= href;
//                        $("#fileSrc").attr("href", href);
//                        $("#fileSrc").attr("target", "_blank");
//                        $("#fileSrc").find("span").trigger("click");
                    } else {
                        //下载
                        var href = "<s:url value='/gzzyjxres/doctor/fileDown'/>?fileFlow=" + fileFlow;

                        window.open(href);
//                        $("#fileSrc").attr("href", href);
//                        $("#fileSrc").removeAttr("target");
//                        $("#fileSrc").find("span").trigger("click");
                    }
                }, null, false);
    }

    //英文结业证书打印
    function printCertificate(resumeFlow,flag){

        if(flag =='print'){
            var tip = "确认结业？";
            //先填写结业时间

            jboxOpen("<s:url value='/gzzyjxres/hospital/certificateDate?resumeFlow='/>"+resumeFlow+"&isForeign='Y'", tip,500,300,true);
        }

        if(flag =='view'){
            jboxTip("下载中,请稍等....");
            var url = '<s:url value="/gzzyjxres/doctor/englishCertificate?resumeFlow="/>'+resumeFlow;
            window.location.href = url;
        }



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
    /**
     *显示不同的查询专业
     */
    function showSpe(flag){
        $("#boxHome").html("");
        $("#speSel").val("");
        if(flag =='Y'){
            $("#boxHome").append($(".abroad").clone());
        }else if(flag =='N'){
            $("#boxHome").append($(".domestic").clone());
        }

        $("#speSel").likeSearchInit({

        });
    }

    //打印结业证书
    function printInfo(resumeFlow,flag) {

        if(flag=='print'){
            var tip = "确认结业？";
            //先填写结业时间

            jboxOpen("<s:url value='/gzzyjxres/hospital/certificateDate?resumeFlow='/>"+resumeFlow+"&isForeign='N'", tip,500,300,true);
        }

        if(flag=='view'){
            jboxTip("下载中,请稍等...");
            var url = '<s:url value="/gzzyjxres/doctor/showCertificate?resumeFlow="/>' + resumeFlow;
            window.location.href = url;
        }


    }

</script>
<style type="text/css">
    #boxHome .item:HOVER{background-color: #eee;}
    .td_left{
        border: 0px;
        text-align: left;
    }
    .td_right{
        border: 0px;
        text-align: right;
    }
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
                <colgroup>
                    <col width="10%"/>
                    <col width="15%"/>
                    <col width="10%"/>
                    <col width="15%"/>
                    <col width="10%"/>
                    <col width="30%"/>
                    <col width="10%"/>
                </colgroup>
                <tr>
                    <td class="td_right">进修批次：</td>
                    <td class="td_left">
                        <select name="batchFlow" class="select" style="width:120px;">
                            <option value="">全部</option>
                            <c:forEach items="${batchLst}" var="dict">
                                <option value="${dict.batchFlow}"
                                        <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_right">学员类型：</td>
                    <td class="td_left">
                        <select name="isForeign" class="select" style="margin-left:4px;width: 107px" onchange="showSpe(this.value)">
                            <option value="all">全部</option>
                            <option value="${GlobalConstant.FLAG_N}" <c:if test="${param.isForeign eq GlobalConstant.FLAG_N}">selected</c:if>>境内</option>
                            <option value="${GlobalConstant.FLAG_Y}" <c:if test="${param.isForeign eq GlobalConstant.FLAG_Y}">selected</c:if>>境外</option>
                        </select>
                    </td>
                    <td class="td_right">进修专业：</td>
                    <td class="td_left" colspan="2">
                        <input id="speSel" style="width: 244px" class="toggleView input" type="text" name="speName2" value="${param.speName2}" autocomplete="off" title="${param.speName2}" onmouseover="this.title = this.value"/>
                        <input id="speId"  type="hidden" name="speId" value="${param.speId}"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;left:4px;">
                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 250px;border-top: none;position: relative;display: none;">
                                <c:if test="${param.isForeign eq GlobalConstant.FLAG_N}">
                                    <c:forEach items="${sessionScope.currDeptList}" var="SysDept">
                                        <p class="item" flow="${SysDept.deptFlow}" value="${SysDept.deptName}" style="height: 30px;padding-left: 10px;"}>${SysDept.deptName}</p>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${param.isForeign eq GlobalConstant.FLAG_Y}">
                                    <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                        <p class="item" flow="${dict.dictId}" value="${dict.dictDesc}" style="height: 40px;padding-left: 10px;">${dict.dictDesc}</p>
                                    </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="td_right" style="height: 50px;">姓&#12288;&#12288;名：</td>
                    <td class="td_left"><input type="text" name="userName" value="${param.userName}" class="input" style="width: 109px"/></td>
                    <td class="td_left" colspan="5">
                        &#12288;&#12288;<input type="button" class="btn_green" value="查&#12288;询" onclick="searchDoctor()"/>
                        <c:if test="${param.status eq 'Y' }">
                            <input type="button" class="btn_green" value="导&#12288;出" onclick="exportInfo()"/>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="search_table">
        <table border="0" cellpadding="0" cellspacing="0" class="grid">
            <colgroup>
                <col width="10%"/>
                <col width="15%"/>
                <col width="15%"/>
                <col width="10%"/>
                <col width="10%"/>
                <c:if test="${param.status=='Y'}">
                    <col width="10%"/>
                    <col width="30%"/>
                </c:if>
                <c:if test="${ empty param.status or param.status eq 'N'}">
                    <col width="40%"/>
                </c:if>

            </colgroup>
            <tr>
                <th>姓名</th>
                <th>证件号码</th>
                <th>毕业专业</th>
                <th colspan="2">进修专业(进修时间)</th>
                <c:if test="${param.status=='Y'}">
                <th>结业时间</th>
                <th>操作</th>
                </c:if>
                <c:if test="${ empty param.status or param.status eq 'N'}">
                    <th>操作</th>
                </c:if>
            </tr>
            <c:forEach items="${stuUserLst}" var="user">
                <tr>
                    <td>${extInfoMap[user.resumeFlow].userName}</td>
                    <td>${user.sysUser.idNo}</td>
                    <td>${user.schoolSpeName}</td>
                    <td colspan="2">
                        <c:set var="unit" value="个月"></c:set>
                        <c:set var="unit2" value="天"></c:set>
                        <c:forEach items="${extInfoMap[user.resumeFlow].speFormList}" var="speForm">
                            ${speForm.speName}
                            (
                            ${not empty speForm.stuTimeId?speForm.stuTimeId:(pdfn:signDaysBetweenTowDate(speForm.endDate,speForm.beginDate)+1)}
                            <c:if test="${user.sysUser.isForeign eq 'Y'}">${unit2}</c:if>
                            <c:if test="${user.sysUser.isForeign eq 'N'}">${unit}</c:if>
                            )
                        </c:forEach>
                    </td>
                    <c:if test="${param.status=='Y'}">
                    <td>${user.certificateDate}</td>
                    <td>
                            <c:if test="${user.sysUser.isForeign eq 'N'}">
                                <c:if test="${not empty stuFileMap[user.resumeFlow]}">
                                    <a onclick="showFile('${stuFileMap[user.resumeFlow].fileFlow}');">[查看进修鉴定表]</a>
                                </c:if>
                            </c:if>

                            <c:if test="${user.stuStatusId eq stuStatusEnumAdmited.id or (user.stuStatusId eq stuStatusEnumDelayGraduation.id ) and flag1 eq GlobalConstant.FLAG_Y}">
                                <c:if test="${user.sysUser.isForeign eq 'N'}">
                                <a onclick="graduation('${user.resumeFlow}')">[延期结业审核]</a>
                                </c:if>
                                <%--<a href="<s:url value='/gzzyjxres/doctor/showCertificate?flag=view&resumeFlow=${user.resumeFlow}'/>"--%>
                                <%--target="_blank">[打印结业证书]</a>--%>
                                <%-- 轮转的时长超过三个月才给显示 打印结业证书 则进入已结业--%>
                                <c:if test="${flag1 eq GlobalConstant.FLAG_Y}">
                                    <a onclick="printInfo('${user.resumeFlow}','print')">[打印结业证书]</a>
                                </c:if>
                                <c:if test="${user.sysUser.isForeign eq 'Y'}">
                                <a onclick="printCertificate('${user.resumeFlow}','print')">[英文结业打印]</a>
                                </c:if>
                            </c:if>


                            <c:if test="${user.sysUser.isForeign eq 'N'}">
                                <c:if test="${user.stuStatusId eq stuStatusEnumGraduation.id and user.issueCertificate ne GlobalConstant.FLAG_Y and flag1 eq GlobalConstant.FLAG_Y}">
                                    <a onclick="issueCertificate('${user.resumeFlow}');">[发放结业证书]</a>
                                </c:if>
                                <c:if test="${user.stuStatusId eq stuStatusEnumGraduation.id and user.issueCertificate eq GlobalConstant.FLAG_Y and flag1 eq GlobalConstant.FLAG_Y}">
                                    <a onclick="printInfo('${user.resumeFlow}','view')">[下载结业证书]</a>
                                </c:if>

                        </c:if>
                            <c:if test="${user.sysUser.isForeign eq 'Y'}">
                                <c:if test="${user.stuStatusId eq stuStatusEnumGraduation.id and (not empty user.certificateDate)}">
                                    <a onclick="printCertificate('${user.resumeFlow}','view')">[英文证书下载]</a>
                                </c:if>
                            </c:if>
                        </td>
                    </c:if>

                    <c:if test="${ empty param.status or param.status eq 'N'}">
                    <td>
                        <c:if test="${user.sysUser.isForeign eq 'N'}">
                            <c:if test="${not empty stuFileMap[user.resumeFlow]}">
                                <a onclick="showFile('${stuFileMap[user.resumeFlow].fileFlow}');">[查看进修鉴定表]</a>
                            </c:if>
                        </c:if>

                        <c:if test="${user.stuStatusId eq stuStatusEnumAdmited.id or (user.stuStatusId eq stuStatusEnumDelayGraduation.id ) and flag1 eq GlobalConstant.FLAG_Y}">
                            <c:if test="${user.sysUser.isForeign eq 'N'}">
                            <a onclick="graduation('${user.resumeFlow}')">[延期结业审核]</a>
                                <c:if test="${flag1 eq GlobalConstant.FLAG_Y}">
                                    <a onclick="printInfo('${user.resumeFlow}','print')">[打印结业证书]</a>
                                </c:if>
                            </c:if>
                            <%--<a href="<s:url value='/gzzyjxres/doctor/showCertificate?flag=view&resumeFlow=${user.resumeFlow}'/>"--%>
                               <%--target="_blank">[打印结业证书]</a>--%>
                            <%-- 轮转的时长超过三个月才给显示 打印结业证书 则进入已结业--%>

                            <c:if test="${user.sysUser.isForeign eq 'Y'}">
                            <a onclick="printCertificate('${user.resumeFlow}','print')">[英文证书打印]</a>
                            </c:if>
                        </c:if>


                        <c:if test="${user.sysUser.isForeign eq 'N'}">
                            <c:if test="${user.stuStatusId eq stuStatusEnumGraduation.id and user.issueCertificate ne GlobalConstant.FLAG_Y and flag1 eq GlobalConstant.FLAG_Y}">
                                <a onclick="issueCertificate('${user.resumeFlow}');">[发放结业证书]</a>
                            </c:if>
                        </c:if>
                        <%--<c:if test="${user.stuStatusId eq stuStatusEnumGraduation.id and user.issueCertificate eq GlobalConstant.FLAG_Y}">
                            <a href="<s:url value='/gzzyjxres/doctor/showCertificate?flag=view&resumeFlow=${user.resumeFlow}'/>"
                               target="_blank">[查看结业证书]</a>
                            <a onclick="printCertificate('${user.resumeFlow}')">[英文证书模板]</a>
                        </c:if>--%>
                    </td>
                    </c:if>
                </tr>
            </c:forEach>
            <c:if test="${empty stuUserLst}">
                <tr>
                    <td colspan="99">无记录！</td>
                </tr>
            </c:if>
        </table>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(stuUserLst)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
    <div  style="display:none;width: 0px;height: 0px;overflow: visible;float: left; position:relative; /*top:30px;*/left:4px;">
        <div  style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 350px;border-top: none;position: relative;display: none;">
            <%--<c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">--%>
            <%--<p class="domestic item" flow="${dict.dictId}" value="${dict.dictName}" style="height: 30px;padding-left: 10px;">${dict.dictName}</p>--%>
            <%--</c:forEach>--%>
            <c:forEach items="${sessionScope.currDeptList}" var="SysDept">
                <p class="domestic item" flow="${SysDept.deptFlow}" value="${SysDept.deptName}" style="height: 30px;padding-left: 10px;"}>${SysDept.deptName}</p>
            </c:forEach>
            <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                <p class="abroad item" flow="${dict.dictId}" value="${dict.dictDesc}" style="height: 40px;padding-left: 10px;">${dict.dictDesc}</p>
            </c:forEach>
        </div>
    </div>

</div>