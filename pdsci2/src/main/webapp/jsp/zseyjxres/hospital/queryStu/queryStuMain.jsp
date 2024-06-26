<script type="text/javascript">
    //分页
    function toPage(page) {
        if(page!=undefined){
            $("#currentPage").val(page);
        }
        search();
    }
    function search(){
        initExportParam("auditForm","exportForm");
        jboxPostLoad("listDiv","<s:url value='/zseyjxres/hospital/queryStuList?isQuery=Y'/>",$('#auditForm').serialize(),true);
    }
    function initExportParam(paramForm,cloneForm)
    {
        var expForm =$("#"+cloneForm);
        $(expForm).html("");
        var datas = decodeURIComponent($("#"+paramForm).serialize(),true).split("&");
        for(var i=0;i<datas.length;i++)
        {
            var input=document.createElement("input");
            $(input).attr("type","text");
            var data=datas[i].split("=");
            $(input).attr("name",data[0]);
            var val=data[1]==undefined?"":data[1];
            $(input).val(val);
            $(input).appendTo(expForm);
        }
    }
    function exportInfo(){
        jboxExp($('#auditForm'),"<s:url value='/zseyjxres/hospital/exportQueryStu'/>");
    }
    $(document).ready(function(){
        toPage(1);
    });
    function auditInfo(userFlow,batchId,isHide){
        jboxOpen("<s:url value='/zseyjxres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&isHide="+isHide,"用户信息",1000,550);
    }

    //导出胸牌（word格式）
    function exportWordInfo(){
       if($('#isAdmited').find('option:selected').text()!='已报到'){
           jboxTip("只有已报到人员才能导出胸牌！");
           return;
       }

        jboxExp($('#exportForm'),"<s:url value='/zseyjxres/hospital/chestCardExport'/>");
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
<style>
    .searchTable  td{
        border: 0px;
    }
    .searchTable  th{
        border: 0px;
    }
    .searchTable  {
        border: 0px;
    }
    .td_left{
        border: 0px;
        text-align: left;
    }
    .td_right{
        border: 0px;
        float: right;
    }
</style>
<div class="main_hd">
    <h2>学员查询</h2>
</div>

<%--胸牌导出参数--%>
<form id="exportForm" style="display: none">
</form>
<div class="main_bd" id="div_table_0" >
    <div class="div_table" style="">
        <form id="auditForm">
            <input id="currentPage" type="hidden" name="currentPage" value="1"/>

            <table width="100%" class="grid searchTable" border="0">
                <tr>
                    <td class="td_right">进修批次：</td>
                    <td class="td_left">
                        <select name="batchFlow" class="select" style="width:110px;">
                            <option value="">全部</option>
                            <c:forEach items="${batchLst}" var="dict">
                                <option value="${dict.batchFlow}" <c:if test="${batchFlow eq dict.batchFlow}">selected="selected"</c:if>>${dict.batchNo}</option>
                            </c:forEach>
                        </select>
                    </td>

                    <td class="td_right">进修专业：</td>
                    <td class="td_left">
                        <input id="speSel" style="width: 93px;" class="toggleView input" type="text" name="speName" value="${param.speName}" autocomplete="off" title="${param.speName}" onmouseover="this.title = this.value"/>
                        <div style="width: 110px;height: 0px;overflow: visible;float: left; position:relative; /*top:30px;*/left:4px;">
                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 158px;border-top: none;position: relative;display: none;">
                                <c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">
                                    <p class="item" flow="${dict.dictId}" value="${dict.dictName}" style="height: 30px;padding-left: 10px;">${dict.dictName}</p>
                                </c:forEach>
                            </div>
                        </div>

                    <%--<select name="speId" class="select" style="width: 100px;">--%>
                            <%--<option value="">请选择</option>--%>
                            <%--<c:forEach items="${dictTypeEnumDwjxSpeList}" var="dict">--%>
                                <%--<option value="${dict.dictId}" ${stuUser.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
                            <%--</c:forEach>--%>
                        <%--</select>--%>
                    </td>
                    <td class="td_right">进修时间：</td>
                    <td class="td_left">
                        <select name="stuTimeId" class="select" style="width:100px;">
                            <option value="">请选择</option>
                            <option value="1" <c:if test="${stuUser.stuTimeId eq '1'}">selected="selected"</c:if>>1个月</option>
                            <option value="2" <c:if test="${stuUser.stuTimeId eq '2'}">selected="selected"</c:if>>2个月</option>
                            <option value="3" <c:if test="${stuUser.stuTimeId eq '3'}">selected="selected"</c:if>>3个月</option>
                            <option value="6" <c:if test="${stuUser.stuTimeId eq '6'}">selected="selected"</c:if>>6个月</option>
                            <option value="12" <c:if test="${stuUser.stuTimeId eq '12'}">selected="selected"</c:if>>12个月</option>
                        </select>
                    </td>
                    <td class="td_right">执业类别：</td>
                    <td class="td_left">
                        <select name="certifiedTypeId" class="select" style="width: 100px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumPracticeGenreList}" var="dict">
                                <option value="${dict.dictId}" ${stuUser.certifiedTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <%--<tr>
                    <td class="td_right">医院等级：</td>
                    <td class="td_left">
                        <select name="hospitalLevelId" class="select" style="width:110px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumHospitalRankList}" var="dict">
                                <option value="${dict.dictId}" ${stuUser.hospitalLevelId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_right">最高学历：</td>
                    <td class="td_left">
                        <select name="maxEduId" class="select" style="width:100px;">
                            <option value=""/>
                            <c:forEach items="${dictTypeEnumUserEducationList}" var="dict">
                                <option value="${dict.dictId}" >${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_right">职称类别：</td>
                    <td class="td_left">
                        <select name="titleTypeId" class="select" style="width: 100px">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumTitleGenreList}" var="dict">
                                <option value="${dict.dictId}" ${extInfo.titleTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_right">职&#12288;&#12288;称：</td>
                    <td class="td_left">
                        <select name="titleId" class="select" style="width: 100px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumUserTitleList}" var="dict">
                                <option value="${dict.dictId}" ${stuUser.titleId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>--%>
                <tr>
                    <td class="td_right">性&#12288;&#12288;别：</td>
                    <td class="td_left">
                        <select name="sexId" class="select" style="width:110px;">
                            <option value="">全部</option>
                            <c:forEach var="sex" items="${userSexEnumList}">
                                <c:if test="${sex.id != userSexEnumUnknown.id}">
                                    <option value="${sex.id}">${sex.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>
                    <td class="td_right">年&#12288;&#12288;龄：</td>
                    <td class="td_left"><input type="text" name="startAge"  class="input" style="width: 30px;"/>-<input type="text" name="endAge" class="input"  style="width: 30px;"/></td>
                    <td class="td_right">审核情况：</td>
                    <td class="td_left">
                        <select name="isAudit" class="select" style="width:100px;">
                            <option value="">请选择</option>
                            <option value="Y">通过</option>
                            <option value="N">不通过</option>
                        </select>
                    </td>
                    <td class="td_right">
                        是否录取：
                    </td>
                    <td class="td_left">
                        <select name="isRecruit" class="select" style="width:100px;">
                            <option value="">请选择</option>
                            <option value="Y">已录取</option>
                            <option value="N">不录取</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="td_right">
                        是否报到：
                    </td>
                    <td class="td_left">
                        <select id="isAdmited" name="isAdmited" class="select" style="width:110px;">
                            <option value="">请选择</option>
                            <option value="Y">已报到</option>
                            <option value="N">未报到</option>
                        </select>
                    </td>
                    <td colspan="8" style="text-align: left;">
                        &#12288;&nbsp;
                        <input type="button" class="btn_green" value="查&#12288;询" onclick="search()" />&#12288;
                        <input type="button" class="btn_green" value="导&#12288;出" onclick="exportInfo()" />&#12288;
                        <input type="button" class="btn_green" value="胸牌导出" onclick="exportWordInfo()" />
                        <span style="font: 10px;color: red">（提示：只有已报到人员才能导出胸牌）</span>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div  id="listDiv">

    </div>
</div>