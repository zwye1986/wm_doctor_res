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

/*    $(document).ready(function(){
        toPage(1);
    });*/

    function searchTea(){
        if(!$("#orgFlow").val()){
            $("#orgSel").val("");
        }
        var orgName = $("#orgSel").val();
        if(!orgName){
            $("#orgFlow").val("");
        }
        jboxPostLoad("teacherContent" ,"<s:url value='/jsres/business/teacherList'/>?checkStatusId=${param.checkStatusId}" ,$("#searchForm").serialize() , true);
    }

    function clearOrgFlow(){
        $("#orgFlow").val("");
        var html = '<option value="">--请选择--</option>';
        $("#deptFlow").html(html);
    }
    function selectOrg(){
        var url='<s:url value="/jsres/teacherCfg/queryDeptListJson"/>?orgFlow='+$("#orgFlow").val();
        jboxPost(url,null,function(resp){
            //alert(resp);
            if(resp){
                var html = '<option value="">--请选择--</option>';
                $.each(resp,function(index,obj){
                    html += '<option value="'+obj.deptFlow+'">'+obj.deptName+'</option>';
                });
                $("#deptFlow").html(html);
            }
        },null,false);
    }
    function toPage(page) {
        if(page){
            $("#currentPage").val(page);
        }
        searchTea();
    }
    <%--function oper(obj, userFlow,cfg){--%>
        <%--var cfgValue = "${GlobalConstant.FLAG_N}";--%>
        <%--if($(obj).attr("checked")=="checked") {--%>
            <%--cfgValue = "${GlobalConstant.FLAG_Y}";--%>
        <%--}--%>
        <%--$("#cfgCode").val(cfg+userFlow);--%>
        <%--$("#cfgValue").val(cfgValue);--%>
        <%--$("#desc").val("是否开放带教app登录权限");--%>
        <%--save(userFlow,cfg);--%>
    <%--}--%>

    <%--function save(userFlow,cfg){--%>
        <%--var url = "<s:url value='/jsres/teacherCfg/saveOne'/>?userFlow="+userFlow;--%>
        <%--var title = "确认开放权限？";--%>
        <%--if(cfgValue == "N"){--%>
            <%--title = "确认取消权限？";--%>
        <%--}--%>
        <%--jboxButtonConfirm(title,"确认","取消", function () {--%>
            <%--jboxPost(url, $($('#sysCfgForm').html().htmlFormart(cfg+userFlow)).serialize(), function(resp){--%>
                <%--if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){--%>
                    <%--jboxTip("操作成功！");--%>
                    <%--searchTea();--%>
                <%--}else{--%>
                    <%--jboxTip("操作失败！");--%>
                <%--}initCheck();--%>
            <%--}, null, false);--%>
        <%--},function(){--%>
            <%--searchTea();--%>
        <%--},300);--%>
    <%--}--%>

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

    function updateTeaSubmit() {
        $("input[name='isSubmitId']").prop("checked",true);
        var userFlows="";
        var count = 0;
        $("input[name='isSubmitId']").each(function(i){
            if(i == 0){
                userFlows += $(this).val();
                count += 1;
            }else {
                userFlows += "&userFlows=" + $(this).val();
                count += 1;
            }
        });
        var url = "<s:url value='/jsres/powerCfg/updateTeaSubmit'/>?userFlows="+userFlows;
        jboxConfirm("共"+count+"条数据，确认提交？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    searchTea();
                    jboxTip("操作成功！");
                }else{
                    jboxTip("操作失败！");
                }
            }, null, true);
        });
    }

    function updateTeaSubmitOne(userFlow) {
        var userFlows = userFlow;
        var url = "<s:url value='/jsres/powerCfg/updateTeaSubmit'/>?userFlows="+userFlows;
        jboxConfirm("确认提交？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    searchTea();
                    jboxTip("操作成功！");
                }else{
                    jboxTip("操作失败！");
                }
            }, null, true);
        });
    }

    function updateCheckTea() {
        $("input[name='checkStatusId']").prop("checked",true);
        var userFlows="";
        var count = 0;
        $("input[name='checkStatusId']").each(function(i){
            if(i == 0){
                userFlows += $(this).val();
                count += 1;
            }else {
                userFlows += "&userFlows=" + $(this).val();
                count += 1;
            }
        });
        var url2 = "<s:url value='/jsres/business/checkTeaReason'/>?userFlows="+userFlows;
        var typeName = "带教权限审核";
        jboxOpen(url2, typeName, 350, 210);
    }
    
    function updateCheckTeaOne(userFlow) {
        var userFlows = userFlow;
        var url2 = "<s:url value='/jsres/business/checkTeaReason'/>?userFlows="+userFlows;
        var typeName = "带教权限审核";
        jboxOpen(url2, typeName, 350, 210);
    }

    function sendBackTea() {
        $("input[name='sendBackId']").prop("checked",true);
        var userFlows="";
        var count = 0;
        $("input[name='sendBackId']").each(function(i){
            if(i == 0){
                userFlows += $(this).val();
                count += 1;
            }else {
                userFlows += "&userFlows=" + $(this).val();
                count += 1;
            }
        });
        var url = "<s:url value='/jsres/business/sendBackTea'/>?userFlows="+userFlows;
        jboxConfirm("共"+count+"条，确认退回重新编辑？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    searchTea();
                    jboxTip("操作成功");
                }
            }, null, true);
        });
    }

    function sendBackTeaOne(userFlow) {
        var userFlows = userFlow;
        var url = "<s:url value='/jsres/business/sendBackTea'/>?userFlows="+userFlows;
        jboxConfirm("确认退回重新编辑？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    searchTea();
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
                        培训基地：
                        <input id="orgSel" onchange="clearOrgFlow();" class="input"  type="text" name="orgName"
                               value="${param.orgName}" autocomplete="off" style="width: 144px;"/>
                        <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top: 36px; left:74px;">
                            <div id="boxHome" style="max-height: 250px;overflow: auto;border: 1px #ccc solid;
							  vertical-align:middle; background-color: white;min-width: 150px;border-top: none;position: relative;display: none;">
                                <c:forEach items="${applicationScope.sysOrgList}" var="org">
                                    <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="line-height: 20px; padding:10px 0;cursor: default; text-align: left;">${org.orgName}</p>
                                </c:forEach>
                            </div>
                        </div>
                        <input type="text" name="orgFlow" id="orgFlow" value="" style="display: none;"/>
                    </td>
                    <td>
                        科&#12288;&#12288;室：
                        <select name="deptFlow"class="select" id="deptFlow" style="width: 150px;">
                            <option value="">--请选择--</option>
                        </select>
                    </td>
                    <td>
                        姓&#12288;&#12288;名：
                        <input type="text" name="userName" class="input"  style="width: 150px;">
                    </td>
                </tr>
                <tr>
                    <td>
                        用 户 名：
                        <input type="text" name="userCode" class="input" style="width:150px;">
                    </td>
                    <td>
                        <input type="button" value="查&#12288;询" class="btn_green" onclick="searchTea();"/>
                        <c:if test="${param.checkStatusId eq 'Passing'}">
                            &nbsp;<input type="button" class="btn_green" value="一键审核" id="checkTea" onclick="updateCheckTea()">
                        </c:if>
                        <c:if test="${param.checkStatusId eq 'Passed'}">
                            &nbsp;<input type="button" class="btn_green" value="退&#12288;回" id="sendBackTeas" onclick="sendBackTea()">
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
                    <th>培训基地</th>
                    <th>所在科室</th>
                    <th>姓名</th>
                    <th>用户名</th>
                    <th>APP权限</th>
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
                <c:forEach items="${list }" var="userExt">
                    <tr>
                        <td>${userExt.orgName }</td>
                        <td>${userExt.deptName }</td>
                        <td>${userExt.userName }</td>
                        <td>${userExt.userCode }</td>
                        <td>
                            <c:set var="key" value="jsres_teacher_app_login_${userExt.userFlow}"/>
                            <input type="checkbox" disabled id="jsres_teacher_app_login_${userExt.userFlow}" name="appLogins" value="${userExt.userFlow}" ${pdfn:jsresPowerCfgMap(key) == GlobalConstant.FLAG_Y?'checked':''} />
                        </td>
                        <td>
                            <c:if test="${userExt.checkStatusId eq 'Passing'}">
                                <input type="button" value="审核" style="background: beige;border: 1px solid #ccc;border-radius: 3px;padding: 1px 5px; cursor:pointer"
                                       onclick="updateCheckTeaOne('${userExt.userFlow}')"/>
                                <input type="checkbox" id="checkStatusId" name="checkStatusId" value="${userExt.userFlow}" style="display: none"/>
                            </c:if>
                            <c:if test="${userExt.checkStatusId ne 'Passing'}">
                                ${userExt.checkStatusName }
                            </c:if>
                        </td>
                        <c:if test="${param.checkStatusId eq 'Passed'}">
                            <td><input type="button" value="退回" style="background: beige;border: 1px solid #ccc;border-radius: 3px;padding: 1px 5px; cursor:pointer"
                                       onclick="sendBackTeaOne('${userExt.userFlow}')"/>
                                <input type="checkbox" id="sendBackId" name="sendBackId" value="${userExt.userFlow}" style="display: none"/>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                <c:if test="${empty list}">
                    <tr>
                        <td colspan="10" style="border:none;">暂无记录！</td>
                    </tr>
                </c:if>
            </table>
        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(list)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>
</div>
