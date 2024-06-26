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
        <c:if test="${!empty param.projectFlow}">
            $(function () {
                var suppliesFlowList =[];var suppliesRecordList =[];
                var devicesFlowList =[];var devicesRecordList =[];
                var suppliesNameList =[];var devicesNameList =[];
                <c:forEach items="${materialList}" var="info">
                    <c:if test="${info.typeId eq 'SUPPLIES'}">
                        suppliesFlowList.push('${info.materialFlow}');
                        suppliesNameList.push('${info.materialName}');
                        suppliesRecordList.push('${info.recordFlow}');
                    </c:if>
                    <c:if test="${info.typeId eq 'DEVICES'}">
                        devicesFlowList.push('${info.materialFlow}');
                        devicesNameList.push('${info.materialName}');
                        devicesRecordList.push('${info.recordFlow}');
                    </c:if>
                </c:forEach>
                $("#suppliesFlowList").val(suppliesFlowList);
                $("#suppliesNameList").val(suppliesNameList);
                $("#suppliesRecordList").val(suppliesRecordList);
                $("#devicesFlowList").val(devicesFlowList);
                $("#devicesNameList").val(devicesNameList);
                $("#devicesRecordList").val(devicesRecordList);
            });
        </c:if>
        function addMaterial(flag){
            var url ="<s:url value='/zsey/base/selectMaterial?projectFlow=${param.projectFlow}&flag='/>"+flag;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='100%' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,flag=='sb'?"选择设备":"选择耗材",600,400,false);
        }
        function delMaterial(obj,id,materialFlow){
            if($("#"+id+" td").length == 1){
                $(obj).closest("table").remove();
                $("#"+id).find("img").attr("style","cursor:pointer;");
            }else{
                $(obj).closest("td").remove();
                if($("#"+id+" td").length == 1){
                    $("#"+id+" div").css("text-align","left");
                }
            }
            if(id == 'suppliesTD'){
                var suppliesFlowArray = $("#suppliesFlowList").val().split(",");
                var suppliesNameArray = $("#suppliesNameList").val().split(",");
                var index = $.inArray(materialFlow, suppliesFlowArray);
                suppliesFlowArray.splice(index, 1);
                suppliesNameArray.splice(index, 1);
                $("#suppliesFlowList").val(suppliesFlowArray);
                $("#suppliesNameList").val(suppliesNameArray);
                if($("#suppliesRecordList").length>0 && $("#suppliesRecordList").val()!=""){
                    var recordArray = $("#suppliesRecordList").val().split(",");
                    recordArray.splice(index, 1);
                    $("#suppliesRecordList").val(recordArray);
                }
            }else{
                var devicesFlowArray = $("#devicesFlowList").val().split(",");
                var devicesNameArray = $("#devicesNameList").val().split(",");
                var index = $.inArray(materialFlow, devicesFlowArray);
                devicesFlowArray.splice(index, 1);
                devicesNameArray.splice(index, 1);
                $("#devicesFlowList").val(devicesFlowArray);
                $("#devicesNameList").val(devicesNameArray);
                if($("#devicesRecordList").length>0 && $("#devicesRecordList").val()!=""){
                    var recordArray = $("#devicesRecordList").val().split(",");
                    recordArray.splice(index, 1);
                    $("#devicesRecordList").val(recordArray);
                }
            }
        }
        function save(){
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            if($("#devicesTD td").length == 0 || $("#suppliesTD td").length == 0){
                jboxTip("设备和耗材不能为空！");
                return;
            }
            jboxPost("<s:url value='/zsey/base/saveProjectConfig'/>", $("#myForm").serialize(), function (resp) {
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
            <input type="hidden" name="projectFlow" value="${param.projectFlow}">
            <table class="basic" style="width: 100%;margin: 10px 0px;">
                <tr>
                    <td style="text-align:right;width:17%;min-width:100px;">培训项目名称：</td>
                    <td style="width:75%;"><input type="text" name="projectName" class="validate[required]" value="${project.projectName}"></td>
                </tr>
                <tr>
                    <td style="text-align:right;">所需培训设备：</td>
                    <td id="devicesTD">
                        <c:if test="${empty param.projectFlow}">
                            <img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addMaterial('sb');" title="添加" />
                        </c:if>
                        <c:set var="sbcount" value="0"/>
                        <c:set var="hccount" value="0"/>
                        <c:forEach items="${materialList}" var="mat">
                            <c:if test="${mat.typeId eq 'DEVICES'}"><c:set var="sbcount" value="${sbcount+1}"/></c:if>
                            <c:if test="${mat.typeId eq 'SUPPLIES'}"><c:set var="hccount" value="${hccount+1}"/></c:if>
                        </c:forEach>
                        <c:if test="${!empty param.projectFlow}">
                            <c:if test="${sbcount gt 0}">
                                <table width=85%><tr><td style='border:0px;padding:0px;width:50%;'><div style="white-space:nowrap;width:96%;text-align:${sbcount eq 1?'left':'right'}">
                                    <c:set var="sbinx" value="0"/>
                                    <c:forEach items="${materialList}" var="mat">
                                        <c:if test="${mat.typeId eq 'DEVICES'}">
                                            <c:set var="sbinx" value="${sbinx+1}"/>
                                            ${mat.materialName}&nbsp;<input type='text' name="devicesNum" value="${mat.materialNumber}" placeholder="填写设备数量" style='width:80px;border-top:0px;border-left:0px;border-right:0px;border-bottom:1px solid gray;'>
                                            <img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/reduce.png'/>" onclick="delMaterial(this,'devicesTD','${mat.materialFlow}');" title="删除" />
                                            <c:if test="${sbinx ne sbcount}"></div></td>
                                                <c:if test="${sbinx % 2 eq 0}"></tr><tr></c:if>
                                                <td style="border:0px;padding:0px;width:50%;"><div style='white-space:nowrap;width:96%;text-align:right'>
                                            </c:if>
                                        </c:if></c:forEach>
                                    </div></td></tr></table>
                            </c:if>
                            <input type='hidden' id="devicesRecordList" name="devicesRecordList" />
                            <input type='hidden' id="devicesFlowList" name="devicesFlowList" />
                            <input type="hidden" id="devicesNameList" name="devicesNameList">
                            <img style="cursor:pointer;${sbcount gt 0?'float:right;margin:-26px 20px 0px 0px;':''}" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addMaterial('sb')" title='添加'>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td style="text-align:right;">所需耗材信息：</td>
                    <td id="suppliesTD">
                        <c:if test="${empty param.projectFlow}">
                            <img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addMaterial('hc');" title="添加" />
                        </c:if>
                        <c:if test="${!empty param.projectFlow}">
                            <c:if test="${hccount gt 0}">
                                <table width=85%><tr><td style='border:0px;padding:0px;width:50%;'><div style="white-space:nowrap;width:96%;text-align:${hccount eq 1?'left':'right'}">
                                    <c:set var="hcinx" value="0"/>
                                    <c:forEach items="${materialList}" var="mat">
                                    <c:if test="${mat.typeId eq 'SUPPLIES'}">
                                        <c:set var="hcinx" value="${hcinx+1}"/>
                                        ${mat.materialName}&nbsp;<input type='text' name="suppliesNum" value="${mat.materialNumber}" placeholder="填写设备数量" style='width:80px;border-top:0px;border-left:0px;border-right:0px;border-bottom:1px solid gray;'>
                                        <img style="cursor:pointer;" src="<s:url value='/jsp/inx/lcjn/images/reduce.png'/>" onclick="delMaterial(this,'suppliesTD','${mat.materialFlow}');" title="删除" />
                                        <c:if test="${hcinx ne hccount}"></div></td>
                                            <c:if test="${hcinx % 2 eq 0}"></tr><tr></c:if>
                                            <td style="border:0px;padding:0px;width:50%;"><div style='white-space:nowrap;width:96%;text-align:right'>
                                        </c:if>
                                    </c:if></c:forEach>
                                    </div></td></tr></table>
                            </c:if>
                            <input type='hidden' id="suppliesRecordList" name="suppliesRecordList" />
                            <input type='hidden' id="suppliesFlowList" name="suppliesFlowList" />
                            <input type="hidden" id="suppliesNameList" name="suppliesNameList">
                            <img style="cursor:pointer;${sbcount gt 0?'float:right;margin:-26px 20px 0px 0px;':''}" src="<s:url value='/jsp/inx/lcjn/images/add.png'/>" onclick="addMaterial('hc')" title='添加'>
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