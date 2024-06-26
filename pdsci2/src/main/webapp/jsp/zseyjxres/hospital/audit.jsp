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
        jboxPostLoad("content","<s:url value='/zseyjxres/hospital/audit'/>?isQuery=Y",$('#auditForm').serialize(),true);
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
        var url = '<s:url value="/zseyjxres/hospital/printApplForm?resumeFlow="/>'+resumeFlow+"&templeteName="+encodeURI(encodeURI(templeteName));
        window.location.href = url;
    }
    //报名信息审核
    function auditInfo(userFlow,batchId,flag){
        jboxOpen("<s:url value='/zseyjxres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&flag="+flag,"用户信息",1000,550);
    }

    //退回窗口
    function sendBack(userFlow,resumeFlow){
        jboxOpen("<s:url value='/zseyjxres/hospital/sendBack'/>?userFlow="+userFlow+"&resumeFlow="+resumeFlow,"退回操作",500,300);
    }


    function  exportInfo()
    {
        var url = "<s:url value='/zseyjxres/hospital/exportAuditPassed'/>";
        jboxTip("导出中...");
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
            <li class="${statusId==stuStatusEnumUnHeadPassed.id?'tab_select':'tab' }" statusId="${stuStatusEnumUnHeadPassed.id }"><a>科室审核不通过</a></li>
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
                    <td>&#12288;进修专业：</td>
                    <td>
                        <input id="speSel" class="toggleView input" type="text" name="speName2" value="${param.speName2}" autocomplete="off" title="${param.speName2}" onmouseover="this.title = this.value"/>
                        <input id="speId"  type="hidden" name="speId" value="${param.speId}"/>

                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; /*top:30px;*/left:4px;">
                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 158px;border-top: none;position: relative;display: none;">
                                <c:forEach items="${sessionScope.currDeptList}" var="SysDept">
                                    <p class="item" flow="${SysDept.deptFlow}" value="${SysDept.deptName}" style="height: 30px;padding-left: 10px;">${SysDept.deptName}</p>
                                </c:forEach>
                            </div>
                        </div>
                    </td>
                    <td>&#12288;进修批次：</td>
                    <td>
                        <select name="batchFlow" class="select" style="width:107px;">
                            <option value="">全部</option>
                            <c:forEach items="${batchLst}" var="dict">
                                <option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <%--<td>
                        <c:if test="${statusId eq regStatusEnumPassed.id}">
                            &#12288;录取通知：
                        </c:if>
                    </td>
                    <td>
                        <c:if test="${statusId eq regStatusEnumPassed.id}">
                            <select name="isPublish" class="select" style="width:107px;">
                                <option value="" <c:if test="${empty param.isPublish}">selected="selected"</c:if>></option>
                                <option value="Y" <c:if test="${param.isPublish eq 'Y'}">selected="selected"</c:if>>已发布</option>
                                <option value="N" <c:if test="${param.isPublish eq 'N'}">selected="selected"</c:if>>未发布</option>
                            </select>
                        </c:if>
                    </td>--%>
                    <td>
                        &#12288;<input type="button"  class="btn_green" value="查&#12288;询" onclick="search()" />
                        <c:if test="${statusId eq regStatusEnumPassed.id}">
                            <a href="javascript:void(0);" onclick="exportInfo()" class="btn_green">导&#12288;出</a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="div_table">
        <c:if test="${statusId ne stuStatusEnumUnPassed.id and statusId ne stuStatusEnumUnHeadPassed.id}">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th>姓名</th>
                    <th>证件号码</th>
                    <th>毕业专业</th>
                    <th colspan="2">进修专业(进修时间：月)</th>
                    <%--<th></th>--%>
                    <%--<c:if test="${statusId eq regStatusEnumPassed.id}">
                        <th>录取通知</th>
                    </c:if>--%>
                    <th>操作</th>
                </tr>
                <%--<c:if test="${statusId eq stuStatusEnumPassing.id}">--%>
                    <c:forEach items="${stuUserLst}" var="user">
                        <c:set var="speFormList" value="${extInfoMap[user.resumeFlow].speFormList}"></c:set>
                        <tr>
                            <td>${extInfoMap[user.resumeFlow].userName}</td>
                            <td>${extInfoMap[user.resumeFlow].idNo}</td>
                            <td>${user.schoolSpeName}</td>
                            <td colspan="2">
                                    <%--<c:if test="${flag}">,</c:if>--%>
                                <c:forEach items="${speFormList}" var="speForm">
                                    ${speForm.speName}(${speForm.stuTimeId})
                                    <%--<c:set var="flag" value="true" scope="page"/>--%>
                                </c:forEach>
                            </td>
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
                                <c:if test="${statusId ne stuStatusEnumPassing.id }">
                                    <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y');">[查看报名信息]</a>
                                    <a onclick="printApplForm('${user.resumeFlow}','${sysCfgMap['jx_templete_name']}')">[打印进修申请表]</a>

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

        <c:if test="${statusId eq stuStatusEnumUnPassed.id}">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th>姓名</th>
                    <th>证件号码</th>
                    <th>毕业专业</th>
                    <th colspan="2">进修专业(进修时间：月)</th>
                    <th>未通过原因</th>
                    <th>操作</th>
                </tr>
                    <c:forEach items="${stuUserLst}" var="user">
                        <c:set var="speFormList" value="${extInfoMap[user.resumeFlow].speFormList}"></c:set>
                        <tr>
                            <td>${extInfoMap[user.resumeFlow].userName}</td>
                            <td>${extInfoMap[user.resumeFlow].idNo}</td>
                            <td>${user.schoolSpeName}</td>
                            <td colspan="2">
                                <c:forEach items="${speFormList}" var="speForm">
                                    ${speForm.speName}(${speForm.stuTimeId})
                                </c:forEach>
                            </td>

                            <td>${user.auditProcess.auditContent}</td>
                            <td>
                                <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y');">[报名信息]</a>
                                <a onclick="sendBack('${user.sysUser.userFlow}','${user.resumeFlow}')">[退回]</a>
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

        <c:if test="${statusId eq stuStatusEnumUnHeadPassed.id}">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <colgroup>
                    <col width="10%"/>
                    <col width="15%"/>
                    <col width="12%"/>
                    <col width="15%"/>
                    <col width="10%"/>
                    <col width="25%"/>
                    <col width="13%"/>
                </colgroup>
                <tr>
                    <th>姓名</th>
                    <th>证件号码</th>
                    <th>毕业专业</th>
                    <th colspan="2">进修专业(进修时间：月)</th>
                    <th>未通过原因</th>
                    <th>操作</th>
                </tr>
                    <%--<c:if test="${statusId eq stuStatusEnumPassing.id}">--%>
                <c:forEach items="${stuUserLst}" var="user">
                    <c:set var="speFormList" value="${extInfoMap[user.resumeFlow].speFormList}"></c:set>
                    <tr>
                        <td>${extInfoMap[user.resumeFlow].userName}</td>
                        <td>${extInfoMap[user.resumeFlow].idNo}</td>
                        <td>${user.schoolSpeName}</td>
                        <td colspan="2">
                            <c:forEach items="${speFormList}" var="speForm">
                                <%--<c:if test="${flag2}">,</c:if>--%>
                                ${speForm.speName}(${speForm.stuTimeId})
                                <%--<c:set var="flag2" value="true" scope="page"/>--%>
                            </c:forEach>
                        </td>

                        <td>
                            <c:forEach items="${statusMap[user.resumeFlow]}" var="headAudit">
                                ${headAudit.deptName}
                                （${headAudit.auditContent}）<br/>
                            </c:forEach>
                        </td>
                        <td>
                            <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y');">[报名信息]</a>
                            <a onclick="sendBack('${user.sysUser.userFlow}','${user.resumeFlow}')">[退回]</a>
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