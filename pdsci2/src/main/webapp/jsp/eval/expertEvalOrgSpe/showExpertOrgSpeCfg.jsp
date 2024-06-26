<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="false"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="treetable" value="true"/>
    </jsp:include>
    <style type="text/css">
    </style>
    <script type="text/javascript">
        $(function () {
            $("#treeTable").treetable({expandable: true,indenterTemplate: "<span class='indenter'></span>"});
        });
        function save() {
            var cfgFlowInput=$("<input type='hidden' name='cfgFlows'> ");
            var recordFlowInput=$("<input type='hidden' name='recordFlows'> ");

            $("#dataForm").find("input[name='cfgFlows']").remove();
            $("#dataForm").find("input[name='recordFlows']").remove();

            $("input[type='checkbox']:checked").each(function(i){
                $(cfgFlowInput).clone().val($(this).val()).appendTo($("#dataForm"));
            });
            $("input[type='checkbox'][recordFlow!='']").not(":checked") .each(function(i){
                $(recordFlowInput).clone().val($(this).attr("recordFlow")).appendTo($("#dataForm"));
            });

            var url = "<s:url value='/eval/expertEvalOrgSpe/saveExpertEvalOrgSpeCfg'/>";
            jboxConfirm("确认保存?",function(){
                jboxPost(url, $("#dataForm").serialize(), function (resp) {
                    if ('${GlobalConstant.SAVE_SUCCESSED}' == resp) {
                        jboxClose();
                    }
                }, null, true);
            });
        }
        function checkParent(obj)
        {
            var parentCfgFlow=$(obj).attr("parentCfgFlow");
            if(parentCfgFlow!="") {
                var checklen = $("input[parentCfgFlow='" + parentCfgFlow + "']:checked").length;
                if (checklen!=0) {
                    $("#" + parentCfgFlow).attr("checked", true);
                }else{
                    $("#" + parentCfgFlow).removeAttr("checked");
                }
                checkParent($("#" + parentCfgFlow));
            }
        }
        function checkOrgCfg(obj)
        {
            var cfgFlow=$(obj).attr("cfgFlow");
            var parentCfgFlow=$(obj).attr("parentCfgFlow");
            if(parentCfgFlow!="") {
                checkParent(obj);
            }
            if(cfgFlow!="") {
                var checkedVal = $(obj).attr("checked");
                if ("checked" == checkedVal) {
                    $("input[parentCfgFlow='" + cfgFlow + "']").attr("checked", true);
                   // $('#treeTable').treetable('expandNode',cfgFlow);
                }else{
                    $("input[parentCfgFlow='" + cfgFlow + "']").removeAttr("checked");
                    //$('#treeTable').treetable('collapseNode',cfgFlow);
                }
            }

        }
    </script>
</head>
<body>
<div class="content">
    <form id="dataForm">
        <input type="hidden" name="expertUserFlow" value="${param.expertUserFlow}">
        <input type="hidden" name="orgFlow" value="${param.orgFlow}">
        <input type="hidden" name="speId" value="${param.speId}">
        <input type="hidden" name="evalYear" value="${param.evalYear}">
    </form>
    <div class="title1 clearfix">
        <div id="tagContent">
            <div class="tagContent selectTag" id="tagContent0">
            <div class="div_search" style="padding: 10px;margin: 10px;float:left;overflow:auto;height: 290px;max-height: 290px;width: 95%;">
                <c:if test="${empty param.evalYear}">
                    请选择评估年份！
                </c:if>
                <c:if test="${not empty param.evalYear and empty allList}">
                    ${param.evalYear}年暂无评分配置
                </c:if>
                <c:if test="${not empty param.evalYear and not empty allList}">
                    <table class="xllist"  id="treeTable"  style="width:100%;margin-top: 10px;text-align: center">
                        <thead>
                            <tr>
                                <th>评估指标</th>
                            </tr>
                        </thead>
                        <tbody id="tbody">
                        <c:forEach items="${allList}" var="cfg">
                            <tr data-tt-id="${cfg.cfgFlow}"
                                    <c:if test="${not empty cfg.parentCfgFlow}">
                                        data-tt-parent-id="${cfg.parentCfgFlow}"
                                    </c:if>
                            >
                                <td style="text-align: left;">
                                    <label>
                                        <input type="checkbox" name="evalCfg"
                                               id="${cfg.cfgFlow}" cfgFlow="${cfg.cfgFlow}" parentCfgFlow="${cfg.parentCfgFlow}"
                                               recordFlow="${orgCfgMap[cfg.cfgFlow].recordFlow}"
                                               onclick="checkOrgCfg(this,'${cfg.cfgFlow}','${param.expertUserFlow}',
                                                       '${param.orgFlow}','${param.evalYear}',
                                                       '${orgCfgMap[cfg.cfgFlow].recordFlow}')"
                                               style="vertical-align: middle; cursor: pointer;"
                                               <c:if test="${orgCfgMap[cfg.cfgFlow].recordStatus eq 'Y'}">checked</c:if>  value="${cfg.cfgFlow}"/>
                                            ${cfg.cfgName}
                                    </label>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
                <div class="button">

                    <c:if test="${not empty param.evalYear and not empty allList}">
                        <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                    </c:if>
                    <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭"/>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>