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

    function searchHospital(){
        if(!$("#orgFlow").val()){
            $("#orgSel").val("");
        }
        var orgName = $("#orgSel").val();
        if(!orgName){
            $("#orgFlow").val("");
        }
        jboxPostLoad("hospitalContent" ,"<s:url value='/jsres/business/hospitalList'/>?checkStatusId=${param.checkStatusId}" ,$("#searchForm").serialize() , true);
    }

    function toPage(page) {
        if(page){
            $("#currentPage").val(page);
        }
        searchHospital();
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

    function updateCheckHospital() {
        $("input[name='checkStatusId']").prop("checked",true);
        var orgFlows="";
        var count = 0;
        $("input[name='checkStatusId']").each(function(i){
            if(i == 0){
                orgFlows += $(this).val();
                count += 1;
            }else {
                orgFlows += "&orgFlows=" + $(this).val();
                count += 1;
            }
        });
        var url2 = "<s:url value='/jsres/business/checkHospitalReason'/>?orgFlows="+orgFlows;
        var typeName = "医院权限审核";
        jboxOpen(url2, typeName, 350, 210);
    }
    
    function updateCheckHospitalOne(orgFlow) {
        var orgFlows = orgFlow;
        var url2 = "<s:url value='/jsres/business/checkHospitalReason'/>?orgFlows="+orgFlows;
        var typeName = "医院权限审核";
        jboxOpen(url2, typeName, 350, 210);
    }

    function sendBackHospital() {
        $("input[name='sendBackId']").prop("checked",true);
        var orgFlows="";
        var count = 0;
        $("input[name='sendBackId']").each(function(i){
            if(i == 0){
                orgFlows += $(this).val();
                count += 1;
            }else {
                orgFlows += "&orgFlows=" + $(this).val();
                count += 1;
            }
        });
        var url = "<s:url value='/jsres/business/sendBackHospital'/>?orgFlows="+orgFlows;
        jboxConfirm("共"+count+"条，确认退回重新编辑？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    searchHospital();
                    jboxTip("操作成功");
                }
            }, null, true);
        });
    }

    function sendBackHospitalOne(orgFlow) {
        var orgFlows = orgFlow;
        var url = "<s:url value='/jsres/business/sendBackHospital'/>?orgFlows="+orgFlows;
        jboxConfirm("确认退回重新编辑？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    searchHospital();
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
                                <c:forEach items="${allSysOrgList}" var="org">
                                    <p class="item" flow="${org.orgFlow}" value="${org.orgName}" style="line-height: 20px; padding:10px 0;cursor: default; text-align: left;">${org.orgName}</p>
                                </c:forEach>
                            </div>
                        </div>
                        <input type="text" name="orgFlow" id="orgFlow" value="" style="display: none;"/>
                    </td>
                    <td>
                        <input type="button" value="查&#12288;询" class="btn_green" onclick="searchHospital();"/>
                        <c:if test="${param.checkStatusId eq 'Passing'}">
                            &nbsp;<input type="button" class="btn_green" value="一键审核" id="checkTea" onclick="updateCheckHospital()">
                        </c:if>
                        <c:if test="${param.checkStatusId eq 'Passed'}">
                            &nbsp;<input type="button" class="btn_green" value="退&#12288;回" id="sendBackTeas" onclick="sendBackHospital()">
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
                    <th>培训基地名称</th>
                    <th>基地代码</th>
                    <th>过程管理</th>
                    <th>数据导入权限</th>
                    <th>出科考核试卷下载</th>
                    <th>自主出卷</th>
                    <th>付费功能时效设置</th>
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
                <c:forEach items="${sysOrgList }" var="sysOrg">
                    <tr>
                        <td>${sysOrg.orgName }</td>
                        <td>${sysOrg.orgCode }</td>
                        <td>
                            <c:set var="key1" value="jsres_${sysOrg.orgFlow }_guocheng"/>
                            <input id="${sysOrg.orgFlow }_guocheng" name="guoCheng" disabled type="checkbox"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_guocheng');"/>
                        </td>
                        <td>
                            <c:set var="key1" value="jsres_${sysOrg.orgFlow }_daoru"/>
                            <input id="${sysOrg.orgFlow }_daoru" name="daoru" disabled type="checkbox"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_daoru');"/>
                        </td>
                        <td>
                            <c:set var="key1" value="jsres_${sysOrg.orgFlow }_downExamFile"/>
                            <input id="${sysOrg.orgFlow }_downExamFile" name="downExamFile" disabled type="checkbox"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_downExamFile');"/>
                        </td>
                        <td>
                            <c:set var="key1" value="jsres_${sysOrg.orgFlow }_createExam"/>
                            <input id="${sysOrg.orgFlow }_createExam" name="createExam" disabled type="checkbox"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_createExam');"/>
                        </td>
                        <td>
                            <c:set var="key1" value="jsres_payPower_startDate_${sysOrg.orgFlow }"/>
                            ${pdfn:jsresPowerCfgMap(key1)}
                            <br>
                            <c:set var="key2" value="jsres_payPower_endDate_${sysOrg.orgFlow }"/>
                            ${pdfn:jsresPowerCfgMap(key2)}
                        </td>
                        <%--<td>--%>
                            <%--<a id="${sysOrg.orgFlow }_MENU"  orgflow="${sysOrg.orgFlow }" style="cursor:default;color: grey;"  onclick="">功能配置</a>--%>
                        <%--</td>--%>
                        <td>
                            <c:if test="${sysOrg.checkStatusId eq 'Passing'}">
                                <input type="button" value="审核" style="background: beige;border: 1px solid #ccc;border-radius: 3px;padding: 1px 5px; cursor:pointer"
                                       onclick="updateCheckHospitalOne('${sysOrg.orgFlow}')"/>
                                <input type="checkbox" id="checkStatusId" name="checkStatusId" value="${sysOrg.orgFlow}" style="display: none"/>
                            </c:if>
                            <c:if test="${sysOrg.checkStatusId ne 'Passing'}">
                                ${sysOrg.checkStatusName }
                            </c:if>
                        </td>
                        <c:if test="${param.checkStatusId eq 'Passed'}">
                            <td><input type="button" value="退回" style="background: beige;border: 1px solid #ccc;border-radius: 3px;padding: 1px 5px; cursor:pointer"
                                       onclick="sendBackHospitalOne('${sysOrg.orgFlow}')"/>
                                <input type="checkbox" id="sendBackId" name="sendBackId" value="${sysOrg.orgFlow}" style="display: none"/>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
                <c:if test="${empty sysOrgList}">
                    <tr>
                        <td colspan="10" style="border:none;">暂无记录！</td>
                    </tr>
                </c:if>
            </table>
        </div>
        <div class="page" style="padding-right: 40px;">
            <c:set var="pageView" value="${pdfn:getPageView(sysOrgList)}" scope="request"></c:set>
            <pd:pagination-jsres toPage="toPage"/>
        </div>
    </div>
</div>
