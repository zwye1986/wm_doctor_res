<script type="text/javascript">
    $(document).ready(function(){
        jboxEndLoading();
        initCheck();
    });

    function initCheck()
    {
        $("input[name='guoCheng']:checked").parents("tr").find("a").attr("style","cursor:pointer;color: blue;")
            .bind("click", function(){
                openMenuPermission($(this).attr("dictId"));
            });;
    }
    function operPerm(obj, dictFlow,dictId, operType){
        var cfgValue = "${GlobalConstant.FLAG_N}";
        if($(obj).attr("checked")=="checked") {
            cfgValue = "${GlobalConstant.FLAG_Y}";
        }
        var $P001 = $("#"+operType+dictId);
        var $_MENU = $("#"+dictId+"_MENU");
        if($P001.attr("checked")=="checked") {
            $_MENU.attr("onclick","openMenuPermission('"+dictId+"')");
            $_MENU.attr("style","cursor:pointer;color: blue;");
        }else{
            $_MENU.attr("onclick","");
            $_MENU.attr("style","cursor:default;color: grey;");
        }
        $("#cfgCode").val(operType+dictId);
        $("#cfgValue").val(cfgValue);
        if(operType == "jsres_sendSchool_gc_"){
            $("#desc").val("是否开放过程管理");
        }
        save(dictFlow,dictId, operType);
    }

    function save(dictFlow,dictId,cfg){
        var url = "<s:url value='/jsres/sendSchoolCfg/saveOne'/>?dictFlow="+dictFlow+"&dictId="+dictId;
        jboxPost(url, $($('#sysCfgForm').html().htmlFormart(cfg+dictId)).serialize(), function(resp){
            if(resp == "${GlobalConstant.SAVE_SUCCESSED}"){
                searchSchool();
                jboxTip("操作成功！");
            }else{
                jboxTip("操作失败！");
            }initCheck();
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

    function updateSchoolSubmitOne(dictFlow) {
        var dictFlows = dictFlow;
        var url = "<s:url value='/jsres/sendSchoolCfg/updateSchoolSubmit'/>?dictFlows="+dictFlows;
        jboxConfirm("确认提交？", function () {
            jboxPost(url, null, function (resp) {
                if ("${GlobalConstant.OPRE_SUCCESSED}" == resp) {
                    searchSchool();
                    jboxTip("操作成功！");
                }else{
                    jboxTip("操作失败！");
                }
            }, null, true);
        });
    }

    function openMenuPermission(orgFlow){
        jboxOpen("<s:url value='/jsres/businessTwo/getSchoolMenu'/>?isQuery=${param.isQuery}&orgFlow="+orgFlow,"功能列表", 600, 300);

    }

</script>
<div class="search_table">
    <table border="0" cellpadding="0" cellspacing="0" class="grid">
        <tr>
            <th>派送学校名称</th>
            <th>过程管理</th>
            <th>功能管理</th>
            <th>是否提交</th>
            <th>审核状态</th>
        </tr>
        <c:forEach items="${dictList}" var="dict" varStatus="num">
            <tr id="${dict.dictFlow}">
                <td>${dict.dictName}</td>
                <td>
                    <c:set var="key" value="jsres_sendSchool_gc_${dict.dictId}"/>
                    <input type="checkbox" <c:if test="${not empty param.isQuery}">disabled</c:if> name="guoCheng"  id="jsres_sendSchool_gc_${dict.dictId }" value="${dict.dictId}" ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ?'checked':''}
                           onchange="operPerm(this,'${dict.dictFlow }','${dict.dictId }','jsres_sendSchool_gc_');"/>
                </td>
                <td>
                    <c:set value="openMenuPermission('${dict.dictId}')" var="func"></c:set>
                    <a id="${dict.dictId }_MENU"  dictId="${dict.dictId }"style="cursor:default;color: ${pdfn:jsresPowerCfgMap(key)==GlobalConstant.FLAG_Y ? 'blue':'grey'};"
                       onclick="${pdfn:jsresPowerCfgMap(key) ? func:""}" >功能配置</a>
                </td>
                <td>
                    <c:if test="${dict.isSubmitId eq 'NotSubmit' or empty dict.isSubmitId }">
                        <c:set var="key1" value="jsres_sendSchool_gc_${dict.dictId}"/>

                        <c:if test="${pdfn:jsresPowerCfgMap(key1)==GlobalConstant.FLAG_Y}">
                            <input type="button" value="提交" class="" style="background-color: #e9e9ed;height: 25px;width: 45px;border-radius: 5px" onclick="updateSchoolSubmitOne('${dict.dictFlow}')"/>
                            <input type="checkbox" id="isSubmitId" name="isSubmitId" value="${dict.dictFlow}" style="display: none"/>
                        </c:if>

                    </c:if>
                    <c:if test="${dict.isSubmitId eq 'Submit' and dict.checkStatusId ne 'UnPassed'}">
                        已提交
                    </c:if>
                    <c:if test="${dict.isSubmitId eq 'Submit' and dict.checkStatusId eq 'UnPassed'}">
                        <input type="button" value="重新提交" class="" style="background-color: #e9e9ed;height: 25px;width: 65px;border-radius: 5px" onclick="updateSchoolSubmitOne('${dict.dictFlow}')"/>
                        <input type="checkbox" id="isSubmitId" name="isSubmitId" value="${dict.dictFlow}" style="display: none"/>
                    </c:if>
                </td>
                <td>${dict.checkStatusName }</td>
            </tr>
        </c:forEach>
        <c:if test="${empty dictList}">
            <tr>
                <td colspan="6" style="border:none;">暂无记录！</td>
            </tr>
        </c:if>
    </table>
    <div>
        <c:set var="pageView" value="${pdfn:getPageView(dictList)}" scope="request"/>
        <pd:pagination toPage="toPage"/>
    </div>
</div>
