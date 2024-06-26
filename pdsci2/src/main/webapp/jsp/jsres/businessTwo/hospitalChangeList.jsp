<script type="text/javascript">
    $(document).ready(function(){
        jboxEndLoading();
        initCheck();
    });


    function operPerm(obj, orgFlow, operType){
        var cfgValue = "${GlobalConstant.FLAG_N}";
        if($(obj).attr("checked")=="checked") {
            cfgValue = "${GlobalConstant.FLAG_Y}";
        }
        var $P001 = $("#"+orgFlow+"_guocheng");
        var $P002 = $("#"+orgFlow+"_downExamFile");
        var $_MENU = $("#"+orgFlow+"_MENU");
        if($P001.attr("checked")=="checked") {
            $_MENU.bind("click", function(){
                openMenuPermission(orgFlow);
            });
            $_MENU.attr("style","cursor:pointer;color: blue;");
        }else{
            $_MENU.prop("onclick",null).off("click");
            $_MENU.attr("style","cursor:default;color: grey;");
        }
        $("#cfgCode").val("jsres_"+orgFlow+operType);
        $("#cfgValue").val(cfgValue);
        if(operType == "_P001"){
            $("#desc").val("是否开放过程管理");
        }
        if(operType == "_downExamFile"){
            $("#desc").val("是否开放出科考试卷下载");
        }
        save(orgFlow, operType);
    }
    function save(userFlow,cfg){
        var url = "<s:url value='/jsres/hospitalCfg/save'/>?orgFlow="+userFlow;
        jboxPost(url, $($('#sysCfgForm').html().htmlFormart("jsres_"+userFlow+cfg)).serialize(), function(resp){
            if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                searchHospital();
                jboxTip("操作成功！");
            }else{
                jboxTip("操作失败！");
            }
        }, null, false);
    }

    String.prototype.htmlFormart = function(){
        var html = this;
        for(var index in arguments){
            var reg = new RegExp('\\{'+index+'\\}','g');
            html = html.replace(reg,arguments[index]);
        }
        return html;
    };
    function initCheck() {
        $("input[name='guoCheng']:checked").parents("tr").find("a").attr("style","cursor:pointer;color: blue;")
            .bind("click", function(){
                openMenuPermission($(this).attr("orgflow"));
            });;
    }

    function updateOrgSubmitOne(orgFlow) {
        var orgFlows = orgFlow;
        var url = "<s:url value='/jsres/hospitalCfg/updateOrgSubmit'/>?orgFlows="+orgFlows;
        jboxConfirm("确认提交？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    searchHospital();
                    jboxTip("操作成功！");
                }else{
                    jboxTip("操作失败！");
                }
            }, null, true);
        });
    }

    function getOrgDate(orgFlow) {
        jboxOpen("<s:url value='/jsres/businessTwo/getOrgDate'/>?isQuery=${param.isQuery}&orgFlow="+orgFlow,"时效设置", 400, 200);
    }

    function openMenuPermission(orgFlow){
        jboxOpen("<s:url value='/jsres/businessTwo/getMenu'/>?isQuery=${param.isQuery}&orgFlow="+orgFlow,"功能列表", 600, 300);
    }
</script>
<style type="text/css">
    .search_table th {
        border: 1px solid #bbbbbb;
    }
    .search_table td {
        border: 1px solid #bbbbbb;
    }
</style>
<div class="search_table">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <th width="18%">培训基地名称</th>
            <th width="8%">基地代码</th>
            <th width="8%">过程管理</th>
            <th width="12%">数据导入权限</th>
            <th width="10%">出科考核试卷下载</th>
            <th width="8%">自主出卷</th>
            <th width="10%">付费功能时效设置</th>
            <th width="8%">功能管理</th>
            <th width="8%">是否提交</th>
            <th width="10%">审核状态</th>
        </tr>
        <c:forEach items="${sysOrgList }" var="sysOrg">
            <tr>
                <td>${sysOrg.orgName }</td>
                <td>${sysOrg.orgCode }</td>
                <td>
                    <c:set var="key1" value="jsres_${sysOrg.orgFlow }_guocheng"/>
                    <input id="${sysOrg.orgFlow }_guocheng" name="guoCheng" <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_guocheng');"/>
                </td>
                <td>
                    <c:set var="key1" value="jsres_${sysOrg.orgFlow }_daoru"/>
                    <input id="${sysOrg.orgFlow }_daoru" name="daoru" <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_daoru');"/>
                </td>
                <td>
                    <c:set var="key1" value="jsres_${sysOrg.orgFlow }_downExamFile"/>
                    <input id="${sysOrg.orgFlow }_downExamFile" name="downExamFile" <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_downExamFile');"/>
                </td>
                <td>
                    <c:set var="key1" value="jsres_${sysOrg.orgFlow }_createExam"/>
                    <input id="${sysOrg.orgFlow }_createExam" name="createExam" <c:if test="${not empty param.isQuery}">disabled</c:if> type="checkbox"  ${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y?'checked':''} onchange="operPerm(this,'${sysOrg.orgFlow }','_createExam');"/>
                </td>
                <td>
                    <a orgflow="${sysOrg.orgFlow }" style="cursor:default;color: blue;" onclick="getOrgDate('${sysOrg.orgFlow}')">时效设置</a>
                </td>
                <td>
                    <a id="${sysOrg.orgFlow }_MENU"  orgflow="${sysOrg.orgFlow }" style="cursor:default;color: grey;"  onclick="">功能配置</a>
                </td>
                <td>
                    <c:if test="${sysOrg.isSubmitId eq 'NotSubmit' or empty sysOrg.isSubmitId }">
                        <c:set var="key1" value="jsres_${sysOrg.orgFlow }_guocheng"/>
                        <c:set var="key2" value="jsres_${sysOrg.orgFlow }_daoru"/>
                        <c:set var="key3" value="jsres_${sysOrg.orgFlow }_downExamFile"/>
                        <c:set var="key4" value="jsres_${sysOrg.orgFlow }_createExam"/>
                        <c:if test="${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y or pdfn:jsresPowerCfgMap(key2)==GlobalConstant.FLAG_Y
									or pdfn:jsresPowerCfgMap(key3)==GlobalConstant.FLAG_Y or pdfn:jsresPowerCfgMap(key4)==GlobalConstant.FLAG_Y}">
                            <input type="button" value="提交" class="" style="background-color: #e9e9ed;height: 25px;width: 45px;border-radius: 5px" onclick="updateOrgSubmitOne('${sysOrg.orgFlow}')"/>
                            <input type="checkbox" id="isSubmitId" name="isSubmitId" value="${sysOrg.orgFlow}" style="display: none"/>
                        </c:if>

                    </c:if>
                    <c:if test="${sysOrg.isSubmitId eq 'Submit' and sysOrg.checkStatusId ne 'UnPassed'}">
                        已提交
                    </c:if>
                    <c:if test="${sysOrg.isSubmitId eq 'Submit' and sysOrg.checkStatusId eq 'UnPassed'}">
                        <input type="button" value="重新提交" class="" style="background-color: #e9e9ed;height: 25px;width: 65px;border-radius: 5px" onclick="updateOrgSubmitOne('${sysOrg.orgFlow}')"/>
                        <input type="checkbox" id="isSubmitId" name="isSubmitId" value="${sysOrg.orgFlow}" style="display: none"/>
                    </c:if>
                </td>
                <td>${sysOrg.checkStatusName }</td>
            </tr>
        </c:forEach>
        <c:if test="${empty sysOrgList}">
            <tr>
                <td colspan="9" style="border:none;">暂无记录！</td>
            </tr>
        </c:if>
    </table>
</div>
<div class="page" style="padding-right: 40px;">
    <c:set var="pageView" value="${pdfn:getPageView(sysOrgList)}" scope="request"></c:set>
    <pd:pagination-jsres toPage="toPage"/>
</div>