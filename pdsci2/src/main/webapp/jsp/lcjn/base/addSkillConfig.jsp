<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(function () {
            <c:if test="${!empty param.skillFlow}">
                var suppliesList =[];var suppliesRecordList =[];
                var assetsList =[];var assetsRecordList =[];
                <c:forEach items="${detailList}" var="info">
                    <c:if test="${info.TYPE_ID eq 'SUPPLIES'}">
                        suppliesList.push('${info.MATERIA_FLOW}');
                        suppliesRecordList.push('${info.RECORD_FLOW}');
                    </c:if>
                    <c:if test="${info.TYPE_ID eq 'FIXED'}">
                        assetsList.push('${info.MATERIA_FLOW}');
                        assetsRecordList.push('${info.RECORD_FLOW}');
                    </c:if>
                </c:forEach>
                $("#suppliesList").val(suppliesList);
                $("#suppliesRecordList").val(suppliesRecordList);
                $("#assetsList").val(assetsList);
                $("#assetsRecordList").val(assetsRecordList);
            </c:if>
        });
        function addInfo(flag){
            var url ="<s:url value='/lcjn/base/selectMaterial?skillFlow=${param.skillFlow}&flag='/>"+flag;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,flag=='hc'?"选择耗材":"选择固定资产",300,400,false);
        }
        function delInfo(obj,id,materiaFlow){
            if($("#"+id+" td").length == 1){
                $(obj).closest("table").remove();
                $("#"+id).find("img").attr("style","cursor:pointer;");
            }else{
                $(obj).parent("td").remove();
            }
            if(id == 'suppliesTD'){
                var suppliesFlowArray = $("#suppliesList").val().split(",");
                var index = $.inArray(materiaFlow, suppliesFlowArray);
                suppliesFlowArray.splice(index, 1);
                $("#suppliesList").val(suppliesFlowArray);
                if($("#suppliesRecordList").val()!=""){
                    var recordArray = $("#suppliesRecordList").val().split(",");
                    recordArray.splice(index, 1);
                    $("#suppliesRecordList").val(recordArray);
                }
            }else{
                var assetsFlowArray = $("#assetsList").val().split(",");
                var index = $.inArray(materiaFlow, assetsFlowArray);
                assetsFlowArray.splice(index, 1);
                $("#assetsList").val(assetsFlowArray);
                if($("#assetsRecordList").val()!=""){
                    var recordArray = $("#assetsRecordList").val().split(",");
                    recordArray.splice(index, 1);
                    $("#assetsRecordList").val(recordArray);
                }
            }
        }
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            if($("#suppliesTD td").length == 0 || $("#assetsTD td").length == 0){
                jboxTip("耗材和固定资产不能为空！");
                return;
            }
            var skillText = $("#skillId option:selected").text();
            $("#skillName").val(skillText);
            jboxPost("<s:url value='/lcjn/base/saveSkillConfig'/>", $("#myForm").serialize(), function (resp) {
                if (resp == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="myForm">
            <input type="hidden" name="skillFlow" value="${param.skillFlow}">
            <div id="suppliesDiv" style="width:200px;height:200px;display:none;">耗材div</div>
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:right;width:25%;">技能名称：</td>
                    <td style="width:75%;">
                        <select id="skillId" name="skillId" style="width:137px;" class="validate[required] select" ${!empty param.skillFlow?"disabled":""}>
                            <option/>
                            <c:forEach items="${skillList}" var="skill">
                                <option value="${skill.dictId}" ${lsc.skillId eq skill.dictId?'selected':''}>${skill.dictName}</option>
                            </c:forEach>
                        </select>
                        <input type="hidden" id="skillName" name="skillName" value="${lsc.skillName}">
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">耗材：</td>
                    <td id="suppliesTD">
                        <c:if test="${empty param.skillFlow}">
                            <img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addInfo('hc');" title="添加" />
                        </c:if>
                        <c:if test="${!empty param.skillFlow}">
                            <!-- suppliesCount：耗材总数量（表格布局使用） -->
                            <c:set var="suppliesCount" value="0"/>
                            <c:forEach items="${detailList}" var="info">
                                <c:if test="${info.TYPE_ID eq 'SUPPLIES'}">
                                    <c:set var="suppliesCount" value="${suppliesCount+1}"/>
                                </c:if>
                            </c:forEach>
                            <table width=90%><tr><td style='border:0px;'>
                                <c:set var="num" value="0"/>
                                <c:forEach items="${detailList}" var="info">
                                    <c:if test="${info.TYPE_ID eq 'SUPPLIES'}">
                                        <c:set var="num" value="${num+1}"/>
                                        ${info.MATERIAL_NAME}&nbsp;<input type='text' name="suppliesNum" value="${info.USE_NUM}" placeholder="填写数量" style='width:50px;border-top:0px;border-left:0px;border-right:0px;border-bottom:1px solid gray;'>
                                        <img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/reduce.png'/>" onclick="delInfo(this,'suppliesTD','${info.MATERIA_FLOW}');" title="删除" />
                                        <c:if test="${num ne suppliesCount}"></td>
                                            <c:if test="${num % 3 eq 0}"></tr><tr></c:if>
                                            <td style="border:0px;">
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </td></tr></table>
                            <img style='cursor:pointer;float:right;margin:-26px 20px 0px 0px;' src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addInfo('hc')" title='添加'>
                            <input type='hidden' id="suppliesList" name="suppliesList" />
                            <input type="hidden" id="suppliesRecordList" name="suppliesRecordList">
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">固定资产：</td>
                    <td id="assetsTD">
                        <c:if test="${empty param.skillFlow}">
                            <img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addInfo('gdzc');" title="添加" />
                        </c:if>
                        <c:if test="${!empty param.skillFlow}">
                            <!-- assetsCount：固定资产总数量（表格布局使用） -->
                            <c:set var="assetsCount" value="0"/>
                            <c:forEach items="${detailList}" var="info">
                                <c:if test="${info.TYPE_ID eq 'FIXED'}">
                                    <c:set var="assetsCount" value="${assetsCount+1}"/>
                                </c:if>
                            </c:forEach>
                            <table width=90%><tr><td style='border:0px;'>
                                <c:set var="num" value="${0}"/>
                                <c:forEach items="${detailList}" var="info">
                                    <c:if test="${info.TYPE_ID eq 'FIXED'}">
                                        <c:set var="num" value="${num+1}"/>
                                        ${info.MATERIAL_NAME}&nbsp;<input type='text' name="assetsNum" value="${info.USE_NUM}" placeholder="填写数量" style='width:50px;border-top:0px;border-left:0px;border-right:0px;border-bottom:1px solid gray;'>
                                        <img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/reduce.png'/>" onclick="delInfo(this,'assetsTD','${info.MATERIA_FLOW}');" title="删除" />
                                        <c:if test="${num ne assetsCount}"></td>
                                            <c:if test="${num % 3 eq 0}"></tr><tr></c:if>
                                            <td style="border:0px;">
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </td></tr></table>
                            <img style='cursor:pointer;float:right;margin:-26px 20px 0px 0px;' src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addInfo('gdzc')" title='添加'>
                            <input type='hidden' id="assetsList" name="assetsList" />
                            <input type="hidden" id="assetsRecordList" name="assetsRecordList">
                        </c:if>
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
</body>
</html>