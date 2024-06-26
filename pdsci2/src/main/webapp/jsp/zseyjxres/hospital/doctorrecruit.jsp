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
        jboxPostLoad("content","<s:url value='/zseyjxres/hospital/recruitDoctor'/>?isQuery=Y",$("#searchForm").serialize() ,true);
    }
    //打印入科通知
    function printSpeNotice(resumeFlow){
        jboxTip("打印中,请稍等...");
        var url = '<s:url value="/zseyjxres/hospital/printSpeNotice?resumeFlow="/>'+resumeFlow;
        window.location.href = url;
    }
    //人员信息
    function getInfo(userFlow,batchId,flag){
        jboxOpen("<s:url value='/zseyjxres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&flag="+flag,"人员信息",1000,550);
    }
    //是否报到操作
    function admitOper(resumeFlow , admitFlag){
        var tip = admitFlag == "Y"?"确认报到？":"确认未报到？";
        jboxConfirm(tip, function(){
            jboxPost('<s:url value="/zseyjxres/hospital/notrecruit"/>' , {"resumeFlow":resumeFlow,"statusId":admitFlag} , function(resp){
                if(resp=="1"){
                    jboxTip("操作成功");
                }
                searchDoctor();
            } , null , false);
        });
    }
    //登记老师
    function registerTeacher(resumeFlow,deptFlow){
        jboxOpen("<s:url value='/zseyjxres/hospital/registerTeacher?resumeFlow='/>"+resumeFlow+"&deptFlow="+deptFlow, "登记老师",800,500,true);
    }
    //结业
    function graduation(resumeFlow){
        jboxOpen("<s:url value='/zseyjxres/hospital/graduation?resumeFlow='/>"+resumeFlow, "结业",1000,500,true);
    }
    function  exportInfo()
    {
        var url = "<s:url value='/zseyjxres/hospital/exportAdmitPassed'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"),url);
    }
    function stuInfoExport(){
        jboxExp($('#searchForm'),"<s:url value='/zseyjxres/hospital/stuChestCardExport'/>");
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
</script>

<style type="text/css">
    #boxHome .item:HOVER{background-color: #eee;}
</style>
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

    <div class="<%--div_table--%>" style="padding:20px 25px 0;margin-bottom: 10px;">
        <form id="searchForm">
            <input type="hidden" id="status" name="status" value="${param.status}" />
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <table>
                <tr>
                    <td>姓&#12288;&#12288;名：</td>
                    <td><input type="text" name="userName" value="${param.userName}" class="input" style="width: 100px"/></td>
                    <td>&#12288;&#12288;进修专业：</td>
                    <td>
                        <input id="speSel" style="width: 107px" class="toggleView input" type="text" name="speName2" value="${param.speName2}" autocomplete="off" title="${param.speName2}" onmouseover="this.title = this.value"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; /*top:30px;*/left:4px;">
                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 158px;border-top: none;position: relative;display: none;">
                                <c:forEach items="${sessionScope.currDeptList}" var="SysDept">
                                    <p class="item" flow="${SysDept.deptFlow}" value="${SysDept.deptName}" style="height: 30px;padding-left: 10px;">${SysDept.deptName}</p>
                                </c:forEach>
                            </div>
                        </div>
                        <%--<select name="speId" class="select" style="width:100px;">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                <option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>--%>
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
                            <%--<a href="javascript:void(0);" onclick="stuInfoExport()" class="btn_green">胸牌导出</a>--%>
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
                <c:if test="${param.status=='Y'}">
                    <th>工号</th>
                </c:if>
                <th>证件号码</th>
                <th>毕业专业</th>
                <th colspan="2">进修专业(进修时间：月)</th>
                <%--<th></th>--%>
               <%-- <c:if test="${param.status eq 'Y'}">
                    <th>带教老师</th>
                    &lt;%&ndash;<th>是否结业</th>&ndash;%&gt;
                </c:if>--%>
                <th>操作</th>
            </tr>
            <c:forEach items="${stuUserLst}" var="user">
                <tr>
                    <td>${extInfoMap[user.resumeFlow].userName}</td>
                    <c:if test="${param.status=='Y'}">
                        <td>${extInfoMap[user.resumeFlow].userCode}</td>
                    </c:if>
                    <td>${extInfoMap[user.resumeFlow].idNo}</td>
                    <td>${user.schoolSpeName}</td>
                    <td colspan="2">
                        <c:forEach items="${extInfoMap[user.resumeFlow].speFormList}" var="speForm">
                            <%--<c:if test="${flag}">,</c:if>--%>
                            ${speForm.speName}(${speForm.stuTimeId})
                            <%--<c:set var="flag" value="true" scope="request"></c:set>--%>
                        </c:forEach>
                    </td>


                   <%-- <c:if test="${param.status eq 'Y'}">
                        <td>${user.teacherName}</td>
                        &lt;%&ndash;<td>&ndash;%&gt;
                            &lt;%&ndash;<c:if test="${user.isGraduation eq 'Y'}">是</c:if>&ndash;%&gt;
                            &lt;%&ndash;<c:if test="${user.isGraduation eq 'N'}">否</c:if>&ndash;%&gt;
                        &lt;%&ndash;</td>&ndash;%&gt;
                    </c:if>--%>
                    <td>
                        <a onclick="getInfo('${user.userFlow}','${user.stuBatId}','Y')">[人员信息]</a>
                        <c:if test="${user.stuStatusId eq stuStatusEnumRecruitted.id}">
                            <a onclick='admitOper("${user.resumeFlow}" , "Y")'>[报到]</a>
                            <a onclick='admitOper("${user.resumeFlow}" , "N")'>[未报到]</a>
                        </c:if>
                        <c:if test="${user.stuStatusId eq  stuStatusEnumAdmited.id or (user.stuStatusId eq  stuStatusEnumGraduation.id) or (user.stuStatusId eq  stuStatusEnumDelayGraduation.id)}">
                            <%--<a onclick="registerTeacher('${user.resumeFlow}','${user.speId}')">[登记老师]</a>--%>
                            <%--<a onclick="graduation('${user.resumeFlow}')">[结业]</a>--%>
                            <%--<a onclick="printSpeNotice('${user.resumeFlow}')">[入科条]</a>--%>
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