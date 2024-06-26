


<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $("#currentPage").val(page);
        }
        search();
    }
    $(document).ready(function(){
        $("li").click(function(){
            $(".tab_select").addClass("tab");
            $(".tab_select").removeClass("tab_select");
            $(this).removeClass("tab");
            $(this).addClass("tab_select");
            $('#statusId').val($(this).attr("statusId"));
            $("#currentPage").val(1);
            search();
        });
    });
    //学员录取
    function search(){
        jboxPostLoad("content","<s:url value='/zseyjxres/head/_recruitDoctor'/>?isQuery=Y",$('#auditForm').serialize(),true);
    }

    //录取信息审核
    function auditInfo(userFlow,batchId,flag){
        jboxOpen("<s:url value='/zseyjxres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&flag="+flag,"用户信息",1000,550);
    }
    //发布录取通知
    function showPublish(userFlow,batchId,resumeFlow){

        jboxConfirm("确认发布录取通知书?", function(){
            jboxPost("<s:url value='/zseyjxres/hospital/publish'/>",{"resumeFlow":resumeFlow}, function(resp){
                jboxTip(resp);
                if(resp=="发布成功")
                {
                    toPage($("#currentPage").val());
                }
            } , null , true);
        });
        <%--jboxOpen("<s:url value='/gzzyjxres/hospital/showPublish'/>?userFlow="+userFlow+"&batchId="+batchId+"&resumeFlow="+resumeFlow,"发布录取通知",1000,550);--%>
    }
    //退回操作
    function returnInfo(userFlow,resumeFlow){
        jboxConfirm("确认退回?", function(){
            jboxPost("<s:url value='/zseyjxres/hospital/returnInfo'/>",{"resumeFlow":resumeFlow,"userFlow":userFlow}, function(resp){
                jboxTip(resp);
                toPage($("#currentPage").val());
            } , null , true);
        });
    }
    function  exportInfo()
    {
        var url = "<s:url value='/zseyjxres/hospital/exportAuditRecruitted'/>";
        jboxTip("导出中…………");
        jboxExp($("#auditForm"),url);
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
    <h2>学员录取</h2>
    <div class="title_tab" id="toptab">
        <ul>
            <li class="${statusId==stuStatusEnumPassed.id?'tab_select':'tab' }" statusId="${stuStatusEnumPassed.id }"><a>待录取</a></li>
            <li class="${statusId==stuStatusEnumRecruitted.id?'tab_select':'tab' }" statusId="${stuStatusEnumRecruitted.id }"><a>已录取</a></li>
            <li class="${statusId==stuStatusEnumUnRecruitted.id?'tab_select':'tab' }" statusId="${stuStatusEnumUnRecruitted.id }"><a>不录取</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div class="<%--div_table--%>" style="padding:20px 25px 0;margin-bottom: 10px;">
        <form id="auditForm">
            <input type="hidden" id="statusId" name="statusId" value="${param.statusId}" />
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <table>
                <tr>
                    <td>姓&#12288;&#12288;名：</td>
                    <td><input type="text" style="width: 100px;" name="userName" value="${param.userName}" class="input" /></td>

                    <td>&#12288;进修批次：</td>
                    <td>
                        <select name="batchFlow" class="select" style="width:107px;">
                            <option value="">全部</option>
                            <c:forEach items="${batchLst}" var="dict">
                                <option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>
                        <c:if test="${statusId eq stuStatusEnumRecruitted.id}">
                            &#12288;录取通知：
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${statusId eq stuStatusEnumRecruitted.id}">
                            <select name="isPublish" class="select" style="width:107px;">
                                <option value="" <c:if test="${empty param.isPublish}">selected="selected"</c:if>></option>
                                <option value="Y" <c:if test="${param.isPublish eq 'Y'}">selected="selected"</c:if>>已发布</option>
                                <option value="N" <c:if test="${param.isPublish eq 'N'}">selected="selected"</c:if>>未发布</option>
                            </select>
                        </c:if>
                    </td>
                    <td>
                        &#12288;<input type="button"  class="btn_green" value="查&#12288;询" onclick="search()" />
                        <c:if test="${statusId eq stuStatusEnumRecruitted.id}">
                            <a href="javascript:void(0);" onclick="exportInfo()" class="btn_green">导&#12288;出</a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="div_table">
        <c:if test="${statusId ne stuStatusEnumUnRecruitted.id }">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th>姓名</th>
                    <th>证件号码</th>
                    <th>毕业专业</th>
                    <th colspan="2">进修专业(进修时间：月)</th>
                    <c:if test="${statusId eq stuStatusEnumRecruitted.id}">
                        <th>录取通知</th>
                    </c:if>
                    <th>操作</th>
                </tr>
                <c:forEach items="${stuUserLst}" var="user">
                    <tr>
                        <td>${extInfoMap[user.resumeFlow].userName}</td>
                        <td>${extInfoMap[user.resumeFlow].idNo}</td>
                        <td>${user.schoolSpeName}</td>
                        <td colspan="2">
                            <c:forEach items="${extInfoMap[user.resumeFlow].speFormList}" var="speForm">
                                <%--<c:if test="${flag}">,</c:if>--%>
                                ${speForm.speId}(${speForm.stuTimeId})
                                <%--<c:set var="flag" value="true" scope="request"></c:set>--%>
                            </c:forEach>
                        </td>
                        <c:if test="${statusId eq stuStatusEnumRecruitted.id}">
                            <td>
                                <c:if test="${user.isPublish eq 'Y'}">已发布</c:if>
                                <c:if test="${user.isPublish eq 'N'}">未发布</c:if>
                            </td>
                        </c:if>
                        <td>
                            <c:if test="${statusId==stuStatusEnumPassed.id }">
                                <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','N');">[学员录取审核]</a>
                            </c:if>
                            <c:if test="${statusId==stuStatusEnumRecruitted.id }">
                                <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y');">[查看录取信息]</a>
                                <a onclick="printApplForm('${user.resumeFlow}','${sysCfgMap['jx_templete_name']}')">[打印]</a>
                                <c:if test="${statusId==stuStatusEnumRecruitted.id}">
                                    <c:if test="${user.isPublish eq 'N'}">
                                        <a onclick="showPublish('${user.sysUser.userFlow}','${user.stuBatId}','${user.resumeFlow}');">[发布录取通知]</a>
                                    </c:if>
                                </c:if>
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty stuUserLst}">

                    <tr>
                        <td colspan="7">暂无信息</td>
                    </tr>
                </c:if>
            </table>
        </c:if>
        <c:if test="${statusId eq stuStatusEnumUnRecruitted.id }">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th>姓名</th>
                    <th>证件号码</th>
                    <th>毕业专业</th>
                    <th>进修时间</th>
                    <th>进修专业</th>
                    <th>未通过原因</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${stuUserLst}" var="user">
                    <tr>
                        <td>${extInfoMap[user.resumeFlow].userName}</td>
                        <td>${extInfoMap[user.resumeFlow].idNo}</td>
                        <td>${user.schoolSpeName}</td>
                        <td>${user.stuTimeName}</td>
                        <td>${user.speName}</td>
                        <td>${user.auditProcess.auditContent}</td>
                        <td>
                            <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y');">[报名信息]</a>
                          <%--  <a onclick="returnInfo('${user.sysUser.userFlow}','${user.resumeFlow}')">[退回]</a>--%>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty stuUserLst}">

                    <tr>
                        <td colspan="7">暂无信息</td>
                    </tr>
                </c:if>
            </table>
        </c:if>
    </div>
    <div class="page" style="padding-right: 40px;">
        <c:set var="pageView" value="${pdfn:getPageView(stuUserLst)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
</div>