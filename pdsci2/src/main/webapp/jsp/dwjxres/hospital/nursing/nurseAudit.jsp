<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
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
            $('#nursingStatusId').val($(this).attr("nursingStatusId"));
            $("#currentPage").val(1);
            search();
        });
    });
    //报名审核
    function search(){
        jboxPostLoad("content","<s:url value='/dwjxres/nursing/audit'/>?isQuery=Y",$('#auditForm').serialize(),true);
    }
    //打印进修申请表
    function printApplForm(resumeFlow,templeteName) {
        jboxTip("打印中,请稍等...");
        if(templeteName =='cd'){
            templeteName = "成都中医药大学附属医院/四川省中医院";
        }else if(templeteName =='xz'){
            templeteName = "徐州中心医院";
        }
        var url = '<s:url value="/dwjxres/hospital/printApplForm?resumeFlow="/>'+resumeFlow+"&templeteName="+encodeURI(encodeURI(templeteName));
        window.location.href = url;
    }
    //报名信息审核
    function auditInfo(userFlow,batchId,flag){
        jboxOpen("<s:url value='/dwjxres/nursing/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&flag="+flag,"用户信息",1000,550);
    }

    //退回操作
    function returnInfo(userFlow,resumeFlow){
        jboxConfirm("确认退回?", function(){
            jboxPost("<s:url value='/dwjxres/nursing/returnInfo'/>",{"resumeFlow":resumeFlow,"userFlow":userFlow}, function(resp){
                jboxTip(resp);
                toPage($("#currentPage").val());
            } , null , true);
        });
    }
    function  exportInfo()
    {
        var url = "<s:url value='/dwjxres/nursing/exportAuditPassed'/>";
        jboxTip("导出中…………");
        jboxExp($("#auditForm"),url);
    }
</script>
<div class="main_hd">
    <h2>报名审核</h2>
    <div class="title_tab" id="toptab">
        <ul>
            <li class="${nursingStatusId==stuStatusEnumPassing.id?'tab_select':'tab' }" nursingStatusId="${stuStatusEnumPassing.id }"><a>待审核</a></li>
            <li class="${nursingStatusId==stuStatusEnumPassed.id?'tab_select':'tab' }" nursingStatusId="${stuStatusEnumPassed.id }"><a>审核通过</a></li>
            <li class="${nursingStatusId==stuStatusEnumUnPassed.id?'tab_select':'tab' }" nursingStatusId="${stuStatusEnumUnPassed.id }"><a>审核不通过</a></li>
            <li class="${nursingStatusId=='Back'?'tab_select':'tab' }" nursingStatusId="Back"><a>已退回</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div class="div_table" style="">
        <form id="auditForm">
            <input type="hidden" id="nursingStatusId" name="nursingStatusId" value="${param.nursingStatusId}" />
            <input id="currentPage" type="hidden" name="currentPage" value="${param.currentPage}"/>
            <table>
                <tr>
                    <td>姓&#12288;&#12288;名：</td>
                    <td><input type="text" style="width: 100px;" name="userName" value="${param.userName}" class="input" /></td>
                    <td>&#12288;进修专业：</td>
                    <td>
                        <select name="speId" class="select" style="width:107px;">
                            <option value="">全部</option>
                            <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                <option value="${dict.dictId}" <c:if test="${param.speId eq dict.dictId}">selected="selected"</c:if>>${dict.dictName}</option>
                            </c:forEach>
                        </select>
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
                    <td>
                        &#12288;<input type="button"  class="btn_green" value="查&#12288;询" onclick="search()" />
                        <c:if test="${nursingStatusId eq regStatusEnumPassed.id}">
                            <a href="javascript:void(0);" onclick="exportInfo()" class="btn_green">导&#12288;出</a>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="div_table">
        <c:if test="${nursingStatusId ne stuStatusEnumUnPassed.id && nursingStatusId ne 'Back'}">
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
                                <c:if test="${nursingStatusId==stuStatusEnumPassing.id }">
                                    <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','N');">[报名信息审核]</a>
                                </c:if>
                                <c:if test="${nursingStatusId!=stuStatusEnumPassing.id }">
                                    <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y');">[查看报名信息]</a>
                                    <%--<a onclick="printApplForm('${user.resumeFlow}','${sysCfgMap['jx_templete_name']}')">[打印]</a>--%>
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
        <c:if test="${nursingStatusId eq regStatusEnumUnPassed.id or nursingStatusId eq 'Back'}">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th>姓名</th>
                    <th>身份证号</th>
                    <th>毕业专业</th>
                    <th>进修时间</th>
                    <th>进修专业</th>
                    <th>未通过原因</th>
                    <c:if test="${nursingStatusId eq regStatusEnumUnPassed.id}">
                        <th>操作</th>
                    </c:if>
                </tr>
                <c:forEach items="${stuUserLst}" var="user">
                    <tr>
                        <td>${extInfoMap[user.resumeFlow].userName}</td>
                        <td>${extInfoMap[user.resumeFlow].idNo}</td>
                        <td>${user.schoolSpeName}</td>
                        <td>${user.stuTimeName}</td>
                        <td>${user.speName}</td>
                        <td>${user.auditProcess.auditContent}</td>
                        <c:if test="${nursingStatusId eq regStatusEnumUnPassed.id}">
                            <td>
                                <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y');">[报名信息]</a>
                                <%--<a onclick="returnInfo('${user.sysUser.userFlow}','${user.resumeFlow}')">[退回]</a>--%>
                            </td>
                        </c:if>
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