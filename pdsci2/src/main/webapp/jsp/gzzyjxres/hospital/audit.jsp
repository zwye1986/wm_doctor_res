<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

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
        jboxPostLoad("content","<s:url value='/gzzyjxres/hospital/audit'/>?isQuery=Y",$('#auditForm').serialize(),true);
    }
    //打印进修申请表
    function printApplForm(resumeFlow,templeteName) {
        jboxTip("打印中,请稍等...");
        if(templeteName =='cd'){
            templeteName = "成都中医药大学附属医院/四川省中医院";
        }else if(templeteName =='xz'){
            templeteName = "徐州中心医院";
        }
        var url = '<s:url value="/gzzyjxres/hospital/printApplForm?resumeFlow="/>'+resumeFlow+"&templeteName="+encodeURI(encodeURI(templeteName));
        window.location.href = url;
    }
    //报名信息审核
    function auditInfo(userFlow,batchId,flag,isForeign){
        if(isForeign =='Y')
            jboxOpen("<s:url value='/gzzyjxres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&flag="+flag+"&isForeign="+isForeign,"User Infomations",1000,550);

        jboxOpen("<s:url value='/gzzyjxres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&flag="+flag+"&isForeign="+isForeign,"用户信息",1000,550);
    }

    //退回操作
    function returnInfo(userFlow,resumeFlow){
        jboxConfirm("确认退回?", function(){
            jboxPost("<s:url value='/gzzyjxres/hospital/returnInfo'/>",{"resumeFlow":resumeFlow,"userFlow":userFlow}, function(resp){
                jboxTip(resp);
                toPage($("#currentPage").val());
            } , null , true);
        });
    }
    function  exportInfo()
    {
        var url = "<s:url value='/gzzyjxres/hospital/exportAuditPassed'/>";
//        jboxTip("导出中...");
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
            $("#boxHome").unbind("mouseenter mouseleave"); //先进行解绑，否则失效
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
            <li class="${statusId eq stuStatusEnumPassing.id?'tab_select':'tab' }"  statusId="${stuStatusEnumPassing.id }"><a>待审核</a></li>
            <li class="${statusId eq stuStatusEnumPassed.id?'tab_select':'tab' }"   statusId="${stuStatusEnumPassed.id }"><a>审核通过</a></li>
            <li class="${statusId eq stuStatusEnumUnPassed.id?'tab_select':'tab' }" statusId="${stuStatusEnumUnPassed.id }"><a>审核不通过</a></li>
        </ul>
    </div>
</div>
<div class="main_bd" id="div_table_0" >
    <div class="<%--div_table--%>" style="padding:20px 25px 0;margin-bottom: 10px;">
        <form id="auditForm">
            <input type="hidden" id="statusId" name="statusId" value="${param.statusId}" />
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
                        <select name="batchFlow" class="select" style="width:107px;margin-left: 4px">
                            <option value="">全部</option>
                            <c:forEach items="${batchLst}" var="dict">
                                <option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_right">学员类型：</td>
                    <td class="td_left">
                        <select name="isForeign" class="select" style="width: 107px" onchange="showSpe(this.value)">
                            <option value="all">全部</option>
                            <option value="${GlobalConstant.FLAG_N}" <c:if test="${param.isForeign eq GlobalConstant.FLAG_N}">selected</c:if>>境内</option>
                            <option value="${GlobalConstant.FLAG_Y}" <c:if test="${param.isForeign eq GlobalConstant.FLAG_Y}">selected</c:if>>境外</option>
                        </select>
                    </td>
                    <td class="td_right">进修专业：</td>
                    <td colspan="2" class="td_left">
                        <input id="speSel" style="width: 244px" class="toggleView input" type="text" name="speName2" value="${param.speName2}" autocomplete="off" title="${param.speName2}" onmouseover="this.title = this.value"/>
                        <input id="speId"  type="hidden" name="speId" value="${param.speId}"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:30px;left:4px;">
                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 250px;border-top: none;position: relative;display: none;">
                                <c:if test="${param.isForeign eq GlobalConstant.FLAG_N}">
                                    <c:forEach items="${sessionScope.currDeptList}" var="SysDept">
                                        <p class="item" flow="${SysDept.deptFlow}" value="${SysDept.deptName}" style="height: 30px;padding-left: 10px;"}>${SysDept.deptName}</p>
                                    </c:forEach>
                                    <%--<c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">--%>
                                        <%--<p class="item" flow="${dict.dictId}" value="${dict.dictName}" style="height: 30px;padding-left: 10px;">${dict.dictName}</p>--%>
                                    <%--</c:forEach>--%>
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
                    <td class="td_right" style="height: 50px">姓&#12288;&#12288;名：</td>
                    <td class="td_left">
                        <input type="text" style="width: 101px;" name="userName" value="${param.userName}" class="input" />
                    </td>
                    <td>
                        <input type="button"  class="btn_green" value="查&#12288;询" onclick="search()" />
                    </td>
                    <c:if test="${statusId eq regStatusEnumPassed.id}">
                        <td>
                            &#12288;&#12288;<a href="javascript:void(0);" onclick="exportInfo()" class="btn_green">导&#12288;出</a>
                        </td>
                    </c:if>
                </tr>
            </table>
        </form>
    </div>
    <div class="div_table">
        <c:if test="${statusId ne stuStatusEnumUnPassed.id }">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <colgroup>
                    <col width="10%"/>
                    <col width="20%"/>
                    <col width="10%"/>
                    <col width="20%"/>
                    <col width="20%"/>
                    <col width="20%"/>
                </colgroup>
                <tr>
                    <th>姓名</th>
                    <th>证件号码</th>
                    <th>毕业专业</th>
                    <th colspan="2">进修专业(进修时间)</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${stuUserLst}" var="user">
                    <c:set var="speFormList" value="${extInfoMap[user.resumeFlow].speFormList}"></c:set>
                    <tr>
                        <td>${extInfoMap[user.resumeFlow].userName}</td>
                        <td>${user.sysUser.idNo}</td>
                        <td>${user.schoolSpeName}</td>
                        <td colspan="2">
                            <c:set var="unit" value="个月"></c:set>
                            <c:set var="unit2" value="天"></c:set>

                            <c:forEach items="${speFormList}" var="speForm">
                                ${speForm.speName}
                                (
                                ${speForm.stuTimeId}
                                <c:if test="${user.sysUser.isForeign eq 'Y'}">${unit2}</c:if>
                                <c:if test="${user.sysUser.isForeign eq 'N'}">${unit}</c:if>
                                )
                            </c:forEach>
                        </td>
                        <td>
                                <c:if test="${statusId eq stuStatusEnumPassing.id }">
                                    <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','N','${user.sysUser.isForeign}');">[报名信息审核]</a>
                                </c:if>
                                <c:if test="${statusId ne stuStatusEnumPassing.id }">
                                    <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y','${user.sysUser.isForeign}');">[查看报名信息]</a>
                                    <c:if test="${user.sysUser.isForeign ne 'Y'}">
                                    <a onclick="printApplForm('${user.resumeFlow}','${sysCfgMap['jx_templete_name']}')">[打印]</a>
                                    </c:if>
                                </c:if>
                        </td>
                    </tr>
                </c:forEach>

                <c:if test="${empty stuUserLst}">
                    <tr>
                        <td colspan="6">暂无信息</td>
                    </tr>
                </c:if>
            </table>
        </c:if>
        <c:if test="${statusId eq regStatusEnumUnPassed.id }">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <colgroup>
                    <col width="10%"/>
                    <col width="15%"/>
                    <col width="10%"/>
                    <col width="15%"/>
                    <col width="15%"/>
                    <col width="20%"/>
                    <col width="15%"/>
                </colgroup>
                <tr>
                    <th>姓名</th>
                    <th>证件号码</th>
                    <th>毕业专业</th>
                    <th colspan="2">进修专业(进修时间)</th>
                    <th>未通过原因</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${stuUserLst}" var="user">
                    <c:set var="speFormList" value="${extInfoMap[user.resumeFlow].speFormList}"></c:set>
                    <tr>
                        <td>${extInfoMap[user.resumeFlow].userName}</td>
                        <td>${user.sysUser.idNo}</td>
                        <td>${user.schoolSpeName}</td>
                        <td colspan="2">
                            <c:set var="unit" value="个月"></c:set>
                            <c:set var="unit2" value="天"></c:set>
                            <c:forEach items="${speFormList}" var="speForm">
                                ${speForm.speName}
                                (
                                ${speForm.stuTimeId}
                                <c:if test="${user.sysUser.isForeign eq 'Y'}">${unit2}</c:if>
                                <c:if test="${user.sysUser.isForeign eq 'N'}">${unit}</c:if>
                                )
                            </c:forEach>
                        </td>
                        <td>${user.auditProcess.auditContent}</td>
                        <td>
                            <a onclick="auditInfo('${user.sysUser.userFlow}','${user.stuBatId}','Y','${user.sysUser.isForeign}');">[报名信息]</a>
                            <a onclick="returnInfo('${user.sysUser.userFlow}','${user.resumeFlow}')">[退回]</a>
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

    <div  style="display:none;width: 0px;height: 0px;overflow: visible;float: left; position:relative; /*top:30px;*/left:4px;">
        <div  style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 350px;border-top: none;position: relative;display: none;">
            <c:forEach items="${sessionScope.currDeptList}" var="SysDept">
                <p class="domestic item" flow="${SysDept.deptFlow}" value="${SysDept.deptName}" style="height: 30px;padding-left: 10px;"}>${SysDept.deptName}</p>
            </c:forEach>
            <%--<c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">--%>
                <%--<p class="domestic item" flow="${dict.dictId}" value="${dict.dictName}" style="height: 30px;padding-left: 10px;">${dict.dictName}</p>--%>
            <%--</c:forEach>--%>
            <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                <p class="abroad item" flow="${dict.dictId}" value="${dict.dictDesc}" style="height: 40px;padding-left: 10px;">${dict.dictDesc}</p>
            </c:forEach>
        </div>
    </div>
</div>