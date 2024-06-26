<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="queryFont" value="true"/>
</jsp:include>
<script type="text/javascript" src="<s:url value='/js/common-art.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>

<script type="text/javascript">
    function jboxButtonConfirm(msg,button1,button2,funcOk,funcCancel,width){
        dialog({
            fixed: true,
            width:width,
            title: '提示',
            cancelValue:'关闭',
            content: msg,
            backdropOpacity:0.1,
            button:[
                {
                    value: button1,
                    callback:funcOk,
                    autofocus: true
                },
                {
                    value: button2,
                    callback:funcCancel
                }
            ]
        }).showModal();
    }

    function searchSchool(){
        if(!$("#orgFlow").val()){
            $("#orgSel").val("");
        }
        var orgName = $("#orgSel").val();
        if(!orgName){
            $("#orgFlow").val("");
        }
        jboxPostLoad("schoolContent" ,"<s:url value='/jsres/business/schoolList'/>?checkStatusId=${param.checkStatusId}" ,$("#searchForm").serialize() , true);
    }

    function toPage(page) {
        if(page){
            $("#currentPage").val(page);
        }
        searchSchool();
    }

    String.prototype.htmlFormart = function(){
        var html = this;
        for(var index in arguments){
            var reg = new RegExp('\\{'+index+'\\}','g');
            html = html.replace(reg,arguments[index]);
        }
        return html;
    };

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
    //======================================
    //页面加载完成时调用
    $(function(){
        $("#orgSel").likeSearchInit({
            clickActive:function(flow){
                $("#orgFlow").val(flow);
                selectOrg();
            }
        });
    });

    function updateCheckSchool() {
        $("input[name='checkStatusId']").prop("checked",true);
        var dictFlows="";
        var count = 0;
        $("input[name='checkStatusId']").each(function(i){
            if(i == 0){
                dictFlows += $(this).val();
                count += 1;
            }else {
                dictFlows += "&dictFlows=" + $(this).val();
                count += 1;
            }
        });
        var url2 = "<s:url value='/jsres/business/checkSchoolReason'/>?dictFlows="+dictFlows;
        var typeName = "高校权限审核";
        jboxOpen(url2, typeName, 350, 210);
    }
    
    function updateCheckSchoolOne(dictFlow) {
        var dictFlows = dictFlow;
        var url2 = "<s:url value='/jsres/business/checkSchoolReason'/>?dictFlows="+dictFlows;
        var typeName = "高校权限审核";
        jboxOpen(url2, typeName, 350, 210);
    }

    function sendBackSchool() {
        $("input[name='sendBackId']").prop("checked",true);
        var dictFlows="";
        var count = 0;
        $("input[name='sendBackId']").each(function(i){
            if(i == 0){
                dictFlows += $(this).val();
                count += 1;
            }else {
                dictFlows += "&dictFlows=" + $(this).val();
                count += 1;
            }
        });
        var url = "<s:url value='/jsres/business/sendBackSchool'/>?dictFlows="+dictFlows;
        jboxConfirm("共"+count+"条，确认退回重新编辑？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    searchSchool();
                    jboxTip("操作成功");
                }
            }, null, true);
        });
    }

    function sendBackSchoolOne(dictFlow) {
        var dictFlows = dictFlow;
        var url = "<s:url value='/jsres/business/sendBackSchool'/>?dictFlows="+dictFlows;
        jboxConfirm("确认退回重新编辑？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    searchSchool();
                    jboxTip("操作成功");
                }
            }, null, true);
        });
    }

</script>

<div class="main_bd" id="div_table_0" >
    <div class="div_search">
        <form id="searchForm" method="post">
            <input id="currentPage" type="hidden" name="currentPage"/>
            <table class="searchTable">
                <tr>
                    <td>
                        派送学校名称：
                        <input type="text" name="dictName" class="input" style="width:150px;">
                    </td>
                    <td>
                        <input type="button" value="查&#12288;询" class="btn_green" onclick="searchSchool();"/>
                        <c:if test="${param.checkStatusId eq 'Passing'}">
                            &nbsp;<input type="button" class="btn_green" value="一键审核" id="checkSchool" onclick="updateCheckSchool()">
                        </c:if>
                        <c:if test="${param.checkStatusId eq 'Passed'}">
                            &nbsp;<input type="button" class="btn_green" value="退&#12288;回" id="sendBackSchools" onclick="sendBackSchool()">
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="contentDiv">
        <div class="search_table">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <th>派送学校名称</th>
                    <th>过程管理</th>
                    <%--<th>功能管理</th>--%>
                    <th>审核状态
                        <c:if test="${param.checkStatusId eq 'Passing'}">
                            <br>
                            <input type="checkbox" name="checkStatus"/>
                        </c:if>
                    </th>
                    <c:if test="${param.checkStatusId eq 'Passed'}">
                        <th>操作
                            <br>
                            <input type="checkbox" name=""/>
                        </th>
                    </c:if>
                </tr>
                <c:forEach items="${dictList}" var="dict" varStatus="num">
                    <tr id="${dict.dictFlow}">
                        <td>${dict.dictName}</td>
                        <td>
                            <c:set var="key" value="jsres_sendSchool_gc_${dict.dictId}"/>
                            <input type="checkbox" disabled name="guoCheng"  id="jsres_sendSchool_gc_${dict.dictId }" value="${dict.dictId}" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''}
                                   onchange="operPerm(this,'${dict.dictFlow }','${dict.dictId }','jsres_sendSchool_gc_');"/>
                        </td>
                        <%--<td>--%>
                            <%--<c:set value="openMenuPermission('${dict.dictId}')" var="func"></c:set>--%>
                            <%--<a id="${dict.dictId }_MENU"  dictId="${dict.dictId }"style="cursor:default;color: ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ? 'blue':'grey'};"--%>
                               <%--onclick="${pdfn:jsresPowerCfgMap(key) ? func:""}" >功能配置</a>--%>
                        <%--</td>--%>
                        <td>
                            <c:if test="${dict.checkStatusId eq 'Passing'}">
                                <input type="button" value="审核" style="background: beige;border: 1px solid #ccc;border-radius: 3px;padding: 1px 5px; cursor:pointer"
                                       onclick="updateCheckSchoolOne('${dict.dictFlow}')"/>
                                <input type="checkbox" id="checkStatusId" name="checkStatusId" value="${dict.dictFlow}" style="display: none"/>
                            </c:if>
                            <c:if test="${dict.checkStatusId ne 'Passing'}">
                                ${dict.checkStatusName }
                            </c:if>
                        </td>
                        <c:if test="${param.checkStatusId eq 'Passed'}">
                            <td><input type="button" value="退回" style="background: beige;border: 1px solid #ccc;border-radius: 3px;padding: 1px 5px; cursor:pointer"
                                       onclick="sendBackSchoolOne('${dict.dictFlow}')"/>
                                <input type="checkbox" id="sendBackId" name="sendBackId" value="${dict.dictFlow}" style="display: none"/>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                <c:if test="${empty dictList}">
                    <tr>
                        <td colspan="10" style="border:none;">暂无记录！</td>
                    </tr>
                </c:if>
            </table>
        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(dictList)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>
</div>
