<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="queryFont" value="true"/>
    <jsp:param name="jquery_datePicker" value="true"/>
    <jsp:param name="jquery_cxselect" value="true"/>
    <jsp:param name="jquery_form" value="true"/>
</jsp:include>

<script src="<s:url value='/js/yearSelect/checkYear.js'/>" type="text/javascript"></script>
<style type="text/css">
    .indexNum + div{
        z-index: 99;
        position: relative!important;
        top:0!important;
        left:0!important;
    }
</style>
<script type="text/javascript">
    $(document).ready(function(){
        $('#sessionNumber').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        $('#graduationYear').datepicker({
            startView: 2,
            maxViewMode: 2,
            minViewMode: 2,
            format: 'yyyy'
        });
        <c:forEach items="${resDocTypeEnumList}" var="type">
            <c:forEach items="${datas}" var="data">
            if("${data}"=="${type.id}"){
                $("#"+"${data}").attr("checked","checked");
            }
            </c:forEach>
            <c:if test="${empty datas}">
                $("#"+"${type.id}").attr("checked","checked");
            </c:if>
        </c:forEach>

        toPage(1);
    });

    function toPage(page) {
        if($(".docType:checked").length==0) {
            jboxTip("请选择人员类型！");
            return false;
        }
        $("#currentPage").val(page);
        jboxStartLoading();
        jboxPostLoad("contentDiv","<s:url value='/jsres/statistic/doctorDataList'/>",$("#searchForm").serialize(),false);
    }

    function exportDoc(){
        var url = "<s:url value='/jsres/statistic/expDoctorDataList'/>";
        jboxTip("导出中…………");
        jboxExp($("#searchForm"),url);
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
        if (${roleFlag == 'local'}) {
            $("#speSel").likeSearchInit({});
        }
        if (${roleFlag == 'global'}) {
            $("#orgSel").likeSearchInit({});
        }
    });

    function toSpeId(speId) {
        $("#speId").val(speId);
    }

    function toOrgFlow(orgFlow) {
        $("#orgFlow").val(orgFlow);
    }

    function changeStatus() {
        if (${roleFlag == 'local'}) {
            if ($("#speSel").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
                $("#speId").val("");
            }
        }

        if (${roleFlag == 'global'}) {
            if ($("#orgSel").val().replace(/(^\s*)|(\s*$)/g, "") == "") {
                $("#orgFlow").val("");
            }
        }
    }

</script>
<div class="main_hd">
    <h2 class="underline">
        学员填写量统计
    </h2>
</div>
<div class="main_bd" id="div_table_0" >
    <div class="div_search">
        <form id="searchForm" method="post">
            <input id="currentPage" type="hidden" name="currentPage"/>
            <input id="roleFlag" type="hidden" name="roleFlag" value ="${roleFlag}"/>
            <table class="searchTable">
                <c:if test="${roleFlag eq GlobalConstant.USER_LIST_LOCAL}">
                    <tr>
                        <td class="td_left">学员姓名：</td>
                        <td><input type="text" name="userName" value="${param.userName}" class="input"/></td>
                        <td class="td_left">证&ensp;件&ensp;号：</td>
                        <td><input type="text" name="idNo" style="width: 122px" value="${param.idNo}" class="input"/></td>
                        <td class="td_left">专&#12288;&#12288;业：</td>
                        <td>
<%--                            <select name="speId" id="speId" class="select">--%>
<%--                                <option value="">请选择</option>--%>
<%--                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
<%--                                    <option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
                            <input type="hidden" id="speId" name="speId" value="">
                            <input id="speSel" class="toggleView input" type="text"  placeholder="请选择专业"
                                   style="width: 128px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 140px -4px;"
                                   name="speName" value="" autocomplete="off" title="${param.speName}" onkeydown="changeStatus();" onkeyup="changeStatus();" onmouseover="this.title = this.value"/>
                            <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:5px;">
                                <div id="boxHome" style="max-height: 200px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 170px;border-top: none;position: relative;display: none;">
                                    <p class="item" flow="" value="全部" onclick="toSpeId('');" style="height: 30px;padding-left: 10px;text-align: left;">全部</p>
                                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                        <p class="item" flow="${dict.dictId}" value="${dict.dictName}" onclick="toSpeId('${dict.dictId}');" style="height: 30px;padding-left: 10px;text-align: left;">${dict.dictName}</p>
                                    </c:forEach>
                                </div>
                            </div>
                        </td>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td><input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input indexNum" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td class="td_left">结业年份：</td>
                        <td><input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}" class="input indexNum" readonly="readonly"/></td>
                        <td class="td_left">填&ensp;写&ensp;率：</td>
                        <td colspan="5"><input type="text" style="width: 50px" name="fillInPercentLow" value="${param.fillInPercentLow}" class="input"/>%&#12288;-&#12288;<input type="text" style="width: 50px" name="fillInPercentHigh" value="${param.fillInPercentHigh}" class="input"/>%</td>
                    </tr>
                    <tr>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                        <td colspan="4">
                            <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                            <input class="btn_green" type="button" value="导&#12288;出" onclick="exportDoc();"/>
                        </td>
                    </tr>
                </c:if>
                <c:if test="${roleFlag ne GlobalConstant.USER_LIST_LOCAL}">
                    <tr>
                        <td class="td_left">基&#12288;&#12288;地：</td>
                        <td>
<%--                            <select name="orgFlow" class="select">--%>
<%--                                <option value="">请选择</option>--%>
<%--                                <c:forEach items="${orgs}" var="org">--%>
<%--                                    <option value="${org.orgFlow}" ${param.orgFlow eq org.orgFlow?'selected':''}>${org.orgName}</option>--%>
<%--                                </c:forEach>--%>
<%--                            </select>--%>
                            <input type="hidden" id="orgFlow" name="orgFlow" value="">
                            <input id="orgSel" class="toggleView input" type="text"  placeholder="请选择基地"
                                   style="width: 122px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 140px -4px;"
                                   name="orgName" value="" autocomplete="off" title="${param.orgName}" onkeydown="changeStatus();" onkeyup="changeStatus();" onmouseover="this.title = this.value"/>
                            <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:5px;">
                                <div id="boxHome" style="max-height: 200px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 300px;border-top: none;position: relative;display: none;">
                                    <p class="item" flow="" value="全部" onclick="toOrgFlow('');" style="height: 30px;padding-left: 10px;text-align: left;">全部</p>
                                    <c:forEach items="${orgs}" var="org">
                                        <p class="item" flow="${org.orgFlow}" value="${org.orgName}" onclick="toOrgFlow('${org.orgFlow}');" style="height: 30px;padding-left: 10px;text-align: left;">${org.orgName}</p>
                                    </c:forEach>
                                </div>
                            </div>
                        </td>
                        <td class="td_left">学员姓名：</td>
                        <td><input type="text" name="userName" value="${param.userName}" class="input"/></td>
                        <td class="td_left">证&ensp;件&ensp;号：</td>
                        <td><input type="text" name="idNo" style="width: 122px" value="${param.idNo}" class="input"/></td>
                        <td class="td_left">专&#12288;&#12288;业：</td>
                        <td>
                            <select name="speId" class="select">
                                <option value="">请选择</option>
                                <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">
                                    <option value="${dict.dictId}" ${param.speId eq dict.dictId?'selected':''}>${dict.dictName}</option>
                                </c:forEach>
                            </select>
<%--                            <input type="hidden" id="speId" name="speId" value="">--%>
<%--                            <input id="speSel" class="toggleView input" type="text"  placeholder="请选择专业"--%>
<%--                                   style="width: 128px;background-image: url(<s:url value='/jsp/res/images/reorder_w.png'/>);background-repeat: no-repeat;background-position: 140px -4px;"--%>
<%--                                   name="speName" value="" autocomplete="off" title="${param.speName}" onkeydown="changeStatus();" onkeyup="changeStatus();" onmouseover="this.title = this.value"/>--%>
<%--                            <div style="width: 0px;height: 0px;overflow: visible;float: left; position:relative; top:35px;left:5px;">--%>
<%--                                <div id="boxHome" style="max-height: 200px;overflow: auto;border: 1px #ccc solid;background-color: white;min-width: 170px;border-top: none;position: relative;display: none;">--%>
<%--                                    <p class="item" flow="" value="全部" onclick="toSpeId('');" style="height: 30px;padding-left: 10px;text-align: left;">全部</p>--%>
<%--                                    <c:forEach items="${dictTypeEnumDoctorTrainingSpeList}" var="dict">--%>
<%--                                        <p class="item" flow="${dict.dictId}" value="${dict.dictName}" onclick="toSpeId('${dict.dictId}');" style="height: 30px;padding-left: 10px;text-align: left;">${dict.dictName}</p>--%>
<%--                                    </c:forEach>--%>
<%--                                </div>--%>
<%--                            </div>--%>
                        </td>
                    </tr>
                    <tr>
                        <td class="td_left">年&#12288;&#12288;级：</td>
                        <td>
                            <input type="text" id="sessionNumber" name="sessionNumber" value="${param.sessionNumber}" class="input indexNum" readonly="readonly" style="width: 122px"/>
                        </td>
                        <td class="td_left">结业年份：</td>
                        <td><input type="text" id="graduationYear" name="graduationYear" value="${param.graduationYear}" class="input indexNum" readonly="readonly"/></td>
                        <td class="td_left">填&ensp;写&ensp;率：</td>
                        <td colspan="3"><input type="text" style="width: 50px" name="fillInPercentLow" value="${param.fillInPercentLow}" class="input"/>%&#12288;-&#12288;<input type="text" style="width: 50px" name="fillInPercentHigh" value="${param.fillInPercentHigh}" class="input"/>%</td>
                    </tr>
                    <tr>
                        <td class="td_left">人员类型：</td>
                        <td colspan="3">
                            <c:forEach items="${resDocTypeEnumList}" var="type">
                                <label><input type="checkbox" id="${type.id}"value="${type.id}"class="docType" name="datas" />${type.name}&nbsp;</label>
                            </c:forEach>
                        </td>
                        <td colspan="2">
                            <input class="btn_green" type="button" value="查&#12288;询" onclick="toPage(1);"/>
                            <input class="btn_green" type="button" value="导&#12288;出" onclick="exportDoc();"/>
                        </td>
                    </tr>
                </c:if>
            </table>
        </form>
    </div>
    <div id="contentDiv" style="max-width: 950px">

    </div>
</div>