<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <style type="text/css">
        #myForm div{margin-top:5px;}
    </style>
    <script type="text/javascript">
        $(function(){
            <c:if test="${empty param.recordFlow}"><%-- 新增 --%>
                $(":radio[name='miurAuditFlag']").eq(0).attr("checked",true);
                linkage('');
            </c:if>
            <c:if test="${not empty param.recordFlow}"><%-- 编辑 --%>
                $('#projTypeId option').hide();
                $('#projTypeId'+' option.${dictTypeEnumAcaScoreType.id}\\.'+${scoreCfg.scoreTypeId}).show();
            </c:if>
        });
        function linkage(dictId){
            $('#projTypeId').val("");//清空上次展现数据
            $('#projTypeId option').hide();
            $('#projTypeId option[value=""]').show();
            $('#projTypeId'+' option.${dictTypeEnumAcaScoreType.id}\\.'+dictId).show();
        }
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var scoreTypeText = $("#scoreTypeId option:selected").text();
            $("#scoreTypeName").val(scoreTypeText);
            var projTypeText = $("#projTypeId option:selected").text();
            $("#projTypeName").val(projTypeText);
            var scoreItemText = $("#scoreItemId option:selected").text();
            $("#scoreItemName").val(scoreItemText);
            var executionText = $("#executionId option:selected").text();
            $("#executionName").val(executionText);
            jboxConfirm("保存后不可编辑，确认保存？", function(){
                jboxPost("<s:url value='/fstu/score/saveConfig'/>", $("#myForm").serialize(), function (resp) {
                    if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                        window.parent.frames['mainIframe'].location.reload();
                        jboxClose();
                    }
                });
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="myForm">
                <table class="basic" style="width: 100%;">
                    <tr>
                        <th style="width: 20%;"><span style="color:red;">*</span>学分类别：</th>
                        <td colspan="3">
                            <input type="hidden" name="recordFlow" value="${scoreCfg.recordFlow}">
                            <select id="scoreTypeId" name="scoreTypeId" style="width:140px;" onchange="linkage(this.value)" class="validate[required] xlselect">
                                <option/>
                                <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                    <option value="${dict.dictId}" <c:if test="${dict.dictId eq scoreCfg.scoreTypeId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th style="width: 20%;"><span style="color:red;">*</span>项目大类：</th>
                        <td colspan="3">
                            <input type="hidden" id="projTypeName" name="projTypeName" value="${scoreCfg.projTypeName}">
                            <input type="hidden" id="scoreTypeName" name="scoreTypeName" value="${scoreCfg.scoreTypeName}">
                            <select id="projTypeId" name="projTypeId" style="width:140px;" class="validate[required] xlselect">
                                <option/>
                                <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                    <c:set var="dictKey" value="dictTypeEnumAcaScoreType.${dict.dictId}List"/>
                                    <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                        <option class="${scope.dictTypeId}" value="${scope.dictId}" <c:if test="${scope.dictId eq scoreCfg.projTypeId and dict.dictId eq scoreCfg.scoreTypeId}">selected</c:if>>${scope.dictName}</option>
                                    </c:forEach>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"><span style="color:red;">*</span>评分项：</th>
                        <td colspan="3">
                            <select id="scoreItemId" name="scoreItemId" style="width:140px;" class="validate[required] xltext">
                                <option/>
                                <c:forEach items="${dictTypeEnumAssessItemList}" var="dict">
                                    <option value="${dict.dictId}" <c:if test="${dict.dictId eq scoreCfg.scoreItemId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select><input type="hidden" id="scoreItemName" name="scoreItemName" value="${scoreCfg.scoreItemName}">
                        </td>
                        <th style="width: 20%;"><span style="color:red;">*</span>完成情况：</th>
                        <td colspan="3">
                            <select id="executionId" name="executionId" style="width:140px;" class="validate[required] xltext">
                                <option/>
                                <c:forEach items="${dictTypeEnumAccomplishResultList}" var="dict">
                                    <option value="${dict.dictId}" <c:if test="${dict.dictId eq scoreCfg.executionId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" id="executionName" name="executionName" value="${scoreCfg.executionName}">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"><span style="color:red;">*</span>分值：</th>
                        <td colspan="3">
                            <input style="width:132px;" type="text" name="score" value="${scoreCfg.score}" class="validate[required,custom[number]] xltext">
                        </td>
                        <th style="width: 20%;"><span style="color:red;">*</span>最大分值：</th>
                        <td colspan="3">
                            <input style="width:132px;" type="text" name="maxScore" value="${scoreCfg.maxScore}" class="validate[required,custom[number]] xltext">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">是否科研部审核：</th>
                        <td colspan="3">
                            <input type="radio" name="miurAuditFlag" value="N" <c:if test="${scoreCfg.miurAuditFlag eq 'N'}">checked="checked"</c:if>>否&#12288;&#12288;
                            <input type="radio" name="miurAuditFlag" value="Y" <c:if test="${scoreCfg.miurAuditFlag eq 'Y'}">checked="checked"</c:if>>是
                        </td>
                        <th style="width: 20%;">是否关联：</th>
                        <td colspan="3">
                            <input type="radio" name="reltiveType" value="thesis" <c:if test="${scoreCfg.reltiveType eq 'book'}">checked="checked"</c:if>>论文
                            <input type="radio" name="reltiveType" value="book" <c:if test="${scoreCfg.reltiveType eq 'thesis'}">checked="checked"</c:if>>著作
                            <input type="radio" name="reltiveType" value="award" <c:if test="${scoreCfg.reltiveType eq 'award'}">checked="checked"</c:if>>奖项
                            <input type="radio" name="reltiveType" value="patent" <c:if test="${scoreCfg.reltiveType eq 'patent'}">checked="checked"</c:if>>科研项目
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">备注：</th>
                        <td colspan="8">
                            <textarea type="text" name="remark" style="width:98%;height:80px;" placeholder="支持250字符" class="validate[maxSize[250]]">${scoreCfg.remark}</textarea>
                        </td>
                    </tr>
                </table>

            <div style="text-align: center;margin-top:20px;">
                <input type="button" class="search" onclick="save();" value="保&#12288;存"/>
                <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
            </div>
        </form>
        </div>
    </div>
</div>
</body>
</html>