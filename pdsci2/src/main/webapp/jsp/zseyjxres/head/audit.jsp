<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<style type="text/css">
    #boxHome .item:HOVER{background-color: #eee;}
</style>

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
    //报名审核
    function search(){
        jboxPostLoad("content","<s:url value='/zseyjxres/head/audit'/>?isQuery=Y",$('#auditForm').serialize(),true);
    }
    //打印进修申请表
    function printApplForm(resumeFlow,templeteName) {
        jboxTip("打印中,请稍等...");
        if(templeteName =='cd'){
            templeteName = "成都中医药大学附属医院/四川省中医院";
        }else if(templeteName =='xz'){
            templeteName = "徐州中心医院";
        }else{
            templeteName = "中山大学附属第二医院";
        }
        var url = '<s:url value="/zseyjxres/head/printApplForm?resumeFlow="/>'+resumeFlow+"&templeteName="+encodeURI(encodeURI(templeteName));
        window.location.href = url;
    }
    //报名信息审核
    function auditInfo(userFlow,batchId,flag){
        var isShow = '${isShow}';
        jboxOpen("<s:url value='/zseyjxres/head/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&flag="+flag+"&isShow="+isShow,"用户信息",1000,550);
    }


    //撤销重新审核
    function reAudit(userFlow,resumeFlow,batchId){
        jboxConfirm("确认撤销审核?", function(){
            jboxPost("<s:url value='/zseyjxres/head/reAudit'/>",{"resumeFlow":resumeFlow,"userFlow":userFlow,"batchId":batchId}, function(resp){
                jboxTip(resp);
                window.parent.search();
                jboxClose();
            } , null , true);
        });
    }
    function  exportInfo()
    {
        var url = "<s:url value='/zseyjxres/head/exportAuditPassed'/>";
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
               $("#speId").val($(this).attr("flow"));
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
<div class="main_hd">
    <h2>报名审核</h2>
    <div class="title_tab" id="toptab">
        <ul>
            <li class="${statusId==stuStatusEnumPassing.id?'tab_select':'tab' }" statusId="${stuStatusEnumPassing.id }"><a>待审核</a></li>
            <li class="${statusId==stuStatusEnumPassed.id?'tab_select':'tab' }" statusId="${stuStatusEnumPassed.id }"><a>审核通过</a></li>
            <li class="${statusId==stuStatusEnumUnPassed.id?'tab_select':'tab' }" statusId="${stuStatusEnumUnPassed.id }"><a>审核不通过</a></li>
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
                        &#12288;<input type="button"  class="btn_green" value="查&#12288;询" onclick="search()" />
                      <%--  <c:if test="${statusId eq regStatusEnumPassed.id}">
                            <a href="javascript:void(0);" onclick="exportInfo()" class="btn_green">导&#12288;出</a>
                        </c:if>--%>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="div_table">
        <c:if test="${statusId ne stuStatusEnumUnPassed.id }">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th>姓名</th>
                    <th>证件号码</th>
                    <th>毕业专业</th>
                    <%--<th colspan="2">进修专业(进修时间：月)</th>--%>
                    <%--<th></th>--%>
                    <%--<c:if test="${statusId eq regStatusEnumPassed.id}">
                        <th>录取通知</th>
                    </c:if>--%>
                    <th>操作</th>
                </tr>
                <c:forEach items="${stuUserLst}" var="user">
                    <c:set var="speFormList" value="${extInfoMap[user.resumeFlow].speFormList}"></c:set>
                    <tr>
                        <td>${extInfoMap[user.resumeFlow].userName}</td>
                        <td>${extInfoMap[user.resumeFlow].idNo}</td>
                        <td>${user.schoolSpeName}</td>
                        <%--<td colspan="2">--%>
                            <%--&lt;%&ndash;<c:if test="${flag}">,</c:if>&ndash;%&gt;--%>
                            <%--<c:forEach items="${speFormList}" var="speForm">--%>
                                <%--${speForm.speId}(${speForm.stuTimeId})--%>
                                <%--&lt;%&ndash;<c:set var="flag" value="true" scope="page"/>&ndash;%&gt;--%>
                            <%--</c:forEach>--%>
                        <%--</td>--%>
                       <%-- <c:if test="${statusId eq regStatusEnumPassed.id}">
                            <td>
                                <c:if test="${user.isPublish eq 'Y'}">已发布</c:if>
                                <c:if test="${user.isPublish eq 'N'}">未发布</c:if>
                            </td>
                        </c:if>--%>
                        <td>
                                <c:if test="${statusId eq stuStatusEnumPassing.id }">
                                    <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','N');">[报名信息审核]</a>
                                </c:if>
                                <c:if test="${statusId ne stuStatusEnumPassing.id}">
                                    <c:if test="${user.isPublish eq 'N'}">
                                        <a onclick="reAudit('${user.sysUser.userFlow}','${user.resumeFlow}','${user.stuBatId}')">[撤销审核]</a>
                                    </c:if>
                                    <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y');">[查看报名信息]</a>
                                    <%--<a onclick="printApplForm('${user.resumeFlow}','${sysCfgMap['jx_templete_name']}')">[打印]</a>--%>
                                    <%--<c:if test="${statusId==regStatusEnumPassed.id}">
                                        <c:if test="${user.isPublish eq 'N'}">
                                            <a onclick="showPublish('${user.sysUser.userFlow}','${user.stuBatId}','${user.resumeFlow}');">[发布录取通知]</a>
                                        </c:if>
                                        <c:if test="${user.isPublish != 'N'}">
                                           &nbsp;&#12288;&#12288;&#12288;&#12288;&#12288;&#12288;&nbsp;
                                        </c:if>

                                    </c:if>--%>
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
        <c:if test="${statusId eq stuStatusEnumUnPassed.id }">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th>姓名</th>
                    <th>证件号码</th>
                    <th>毕业专业</th>
                    <%--<th colspan="2">进修专业(进修时间：月)</th>--%>
                    <th>未通过原因</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${stuUserLst}" var="user">
                    <c:set var="speFormList" value="${extInfoMap[user.resumeFlow].speFormList}"></c:set>
                    <tr>
                        <td>${extInfoMap[user.resumeFlow].userName}</td>
                        <td>${extInfoMap[user.resumeFlow].idNo}</td>
                        <td>${user.schoolSpeName}</td>
                        <%--<td colspan="2">--%>
                            <%--<c:forEach items="${speFormList}" var="speForm">--%>
                                <%--${speForm.speId}(${speForm.stuTimeId})--%>
                            <%--</c:forEach>--%>
                        <%--</td>--%>
                        <td>${statusMap[user.resumeFlow].auditContent}</td>
                        <td>
                            <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y');">[报名信息]</a>
                            <%--<c:if test="${user.isPublish eq 'N'}">--%>
                                <a onclick="reAudit('${user.sysUser.userFlow}','${user.resumeFlow}','${user.stuBatId}')">[撤销审核]</a>
                            <%--</c:if>--%>
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
        <c:set var="pageView" value="${pdfn:getPageView(stuUserLst2)}" scope="request"></c:set>
        <pd:pagination-dwjxres toPage="toPage"/>
    </div>
</div>