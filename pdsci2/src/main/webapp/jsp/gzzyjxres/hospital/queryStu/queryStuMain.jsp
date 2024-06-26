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
        jboxPostLoad("listDiv","<s:url value='/gzzyjxres/hospital/queryStuList?isQuery=Y'/>",$('#auditForm').serialize(),true);
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
        jboxExp($('#auditForm'),"<s:url value='/gzzyjxres/hospital/exportQueryStu'/>");
    }
    $(document).ready(function(){
        toPage(1);
    });
    function auditInfo(userFlow,batchId,isHide,isForeign){

        if(isForeign =='Y'){
            jboxOpen("<s:url value='/gzzyjxres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&isHide="+isHide+"&isForeign="+isForeign,"User Infomations",1000,550);
        }
        jboxOpen("<s:url value='/gzzyjxres/doctor/getsingupinfoaudit'/>?userFlow="+userFlow+"&batchId="+batchId+"&isHide="+isHide+"&isForeign="+isForeign,"用户信息",1000,550);
    }

    //导出胸牌（word格式）
    function exportWordInfo(){
       if($('#isAdmited').find('option:selected').text()!='已报到'){
           jboxTip("只有已报到人员才能导出胸牌！");
           return;
       }

        jboxExp($('#exportForm'),"<s:url value='/gzzyjxres/hospital/chestCardExport'/>");
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
        text-align: right;
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

            <table<%-- width="100%" class="grid searchTable" border="0"--%>>
                <colgroup>
                    <col width="15%"/>
                    <col width="10%"/>
                    <col width="15%"/>
                    <col width="10%"/>
                    <col width="15%"/>
                    <col width="10%"/>
                    <col width="15%"/>
                    <col width="10%"/>
                </colgroup>
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
                    <td class="td_right">审核情况：</td>
                    <td class="td_left">
                        <select name="isAudit" class="select" style="width:100px;">
                            <option value="">请选择</option>
                            <option value="Y">通过</option>
                            <option value="N">不通过</option>
                        </select>
                    </td>
                    <td class="td_right">是否录取：</td>
                    <td class="td_left">
                        <select name="isRecruit" class="select" style="width:100px;">
                            <option value="">请选择</option>
                            <option value="Y">已录取</option>
                            <option value="N">不录取</option>
                        </select>
                    </td>

                    <td class="td_right">是否报到：</td>
                    <td class="td_left">
                        <select id="isAdmited" name="isAdmited" class="select" style="width:110px;">
                            <option value="">请选择</option>
                            <option value="Y">已报到</option>
                            <option value="N">未报到</option>
                        </select>
                    </td>
                  <%--  <td class="td_right">执业类别：</td>
                    <td class="td_left">
                        <select name="certifiedTypeId" class="select" style="width: 100px;">
                            <option value="">请选择</option>
                            <c:forEach items="${dictTypeEnumPracticeGenreList}" var="dict">
                                <option value="${dict.dictId}" ${stuUser.certifiedTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                            </c:forEach>
                        </select>
                    </td>--%>
                   <%-- <td class="td_right">性&#12288;&#12288;别：</td>
                    <td class="td_left">
                        <select name="sexId" class="select" style="width:110px;">
                            <option value="">全部</option>
                            <c:forEach var="sex" items="${userSexEnumList}">
                                <c:if test="${sex.id != userSexEnumUnknown.id}">
                                    <option value="${sex.id}">${sex.name}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                    </td>--%>
                </tr>
              <%--  <tr>
                    &lt;%&ndash;<td class="td_right">年&#12288;&#12288;龄：</td>
                    <td class="td_left"><input type="text" name="startAge"  class="input" style="width: 30px;"/>-<input type="text" name="endAge" class="input"  style="width: 30px;"/></td>
                   &ndash;%&gt;


                </tr>--%>
                <tr>
                    <td class="td_right" style="height: 50px">&#12288;学员类型：</td>
                    <td class="td_left">
                        <select name="isForeign" class="select" style="width: 107px" onchange="showSpe(this.value)">
                            <option value="all">全部</option>
                            <option value="${GlobalConstant.FLAG_N}" <c:if test="${param.isForeign eq GlobalConstant.FLAG_N}">selected</c:if>>境内</option>
                            <option value="${GlobalConstant.FLAG_Y}" <c:if test="${param.isForeign eq GlobalConstant.FLAG_Y}">selected</c:if>>境外</option>
                        </select>
                    </td>
                    <td  colspan="6" style="text-align: left;">
                        <input type="button" class="btn_green" value="查&#12288;询" onclick="search()" />&#12288;

                        <c:if test="${param.isForeign ne 'Y'}">
                        <input type="button" class="btn_green" value="导&#12288;出" onclick="exportInfo()" />&#12288;
                        <%--<input type="button" class="btn_green" value="胸牌导出" onclick="exportWordInfo()" />--%>
                        </c:if>
                        <%--<span style="font: 10px;color: red">（提示：只有已报到人员才能导出胸牌）</span>--%>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div  id="listDiv">

    </div>
</div>