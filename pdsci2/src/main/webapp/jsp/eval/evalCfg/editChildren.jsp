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
    </jsp:include>
    <script type="text/javascript">
        function saveUser() {
            if (false == $("#cfgForm").validationEngine("validate")) {
                return;
            }
            var count=0;

            $("#tbody tr").each(function(){
                if($(this).hasClass("selected")){
                    count++;
                };
            });
           if(count<=0)
           {
               jboxTip("请选择【配置文件信息】！");
               return false;
           }
            var url = "<s:url value='/eval/evalCfg/saveItem'/>";
            var getdata = $('#cfgForm').serialize();
            jboxPost(url, getdata, function (resp) {
                if ('${GlobalConstant.SAVE_SUCCESSED}' == resp) {
                    try {
                        window.parent.frames['mainIframe'].window.search();
                    } catch (e) {

                    }
                    jboxClose();
                }
            }, null, true);
        }

        function getFileCfg(){
            var year=$("#evalYear").val();
            jboxPostLoad("fileCfg" ,"<s:url value='/eval/evalCfg/fileDirs'/>?year="+year ,null , true);
        }

        $(document).ready(function(){
            getFileCfg();
        });
    </script>
</head>
<body>

    <div class="content" style="height: 500px;overflow: auto;">
        <div class="title1 clearfix">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                    <form id="cfgForm" style="position: relative;">
                        <input type="hidden" id="cfgFlow" name="cfgFlow" value="${cfg.cfgFlow}"/>
                        <input type="hidden" id="parentCfgFlow" name="parentCfgFlow" value="${pCfg.cfgFlow}"/>
                        <input type="hidden" id="levelId"name="levelId" value="${pCfg.levelId+1}"/>
                        <input type="hidden" id="filePath" name="filePath" value="${cfg.filePath}"/>
                        <input type="hidden" id="pFilePath" name="pfilePath" value="${pCfg.filePath}"/>
                        <input type="hidden" id="isFile"name="isFile" value="${cfg.isFile}"/>
                        <input type="hidden" id="evalYear"name="evalYear" value="${pCfg.evalYear}"/>
                    <table style="width: 100%;height: 400px;" cellpadding="0" cellspacing="0" class="basic">
                        <tr>
                            <th width="15%">父级配置：</th>
                            <td width="15%">
                                ${pCfg.cfgName}
                            </td>
                            <td width="70%" rowspan="8" style="padding-right: 10px;">
                                <div id="fileCfg" style="width: 100%;height: 420px;overflow: auto;">

                                </div>
                            </td>
                        </tr>
                        <tr>
                            <th width="15%">评估年份：</th>
                            <td width="15%">
                                ${pCfg.evalYear}
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>序&#12288;&#12288;号：</th>
                            <td width="30%">
                                <input class="validate[required,custom[integer],min[1],maxSize[5]] xltext" name="ordinal" type="text"
                                       value="${empty cfg.ordinal?ordinal:cfg.ordinal}">
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>评估名称：</th>
                            <td width="30%">
                                <input class="validate[required] xltext" id="cfgName" name="cfgName" type="text"
                                       value="${cfg.cfgName}">
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>评估类型：</th>
                            <td>
                                <select name="typeId" class="validate[required] xlselect">
                                    <c:if test="${pCfg.typeId eq evalCfgEnumMANAGE.id}">
                                        <option value="${evalCfgEnumMANAGE.id}"<c:if test='${cfg.typeId==evalCfgEnumMANAGE.id}'>selected="selected"</c:if>>${evalCfgEnumMANAGE.name}</option>
                                    </c:if>
                                    <c:if test="${pCfg.typeId eq evalCfgEnumALL.id}">
                                        <option></option>
                                        <option value="${evalCfgEnumMANAGE.id}" <c:if test='${cfg.typeId==evalCfgEnumMANAGE.id}'>selected="selected"</c:if>>${evalCfgEnumMANAGE.name}</option>
                                        <option value="${evalCfgEnumCLINICAL.id}" <c:if test='${cfg.typeId==evalCfgEnumCLINICAL.id}'>selected="selected"</c:if>>${evalCfgEnumCLINICAL.name}</option>
                                    </c:if>
                                    <c:if test="${pCfg.typeId eq evalCfgEnumCLINICAL.id}">
                                        <option value="${evalCfgEnumCLINICAL.id}"<c:if test='${cfg.typeId==evalCfgEnumCLINICAL.id}'>selected="selected"</c:if>>${evalCfgEnumCLINICAL.name}</option>

                                        <%--<c:forEach items="${evalCfgEnumList}" var="type">--%>
                                            <%--<c:if test="${type.id !=evalCfgEnumMANAGE.id and type.id !=evalCfgEnumALL.id}">--%>
                                                <%--<option value="${type.id}" <c:if test='${cfg.typeId==type.id}'>selected="selected"</c:if>>${type.name}</option>--%>
                                            <%--</c:if>--%>
                                        <%--</c:forEach>--%>
                                    </c:if>
                                </select>
                            </td>
                        </tr>
                        <tr id="">
                            <th><font color="red">*</font>组长评估：</th>
                            <td>

                                <select name="isExpert" class="validate[required] xlselect">
                                    <option value="N" <c:if test='${cfg.isExpert=="N"}'>selected="selected"</c:if>>否</option>
                                    <option value="Y" <c:if test='${cfg.isExpert=="Y"}'>selected="selected"</c:if>>是</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>访问方式：</th>
                            <td>
                                <input id="actionTypeId${actionTypeEnumSINGLE.id}" class="" type="radio" name="actionTypeId"
                                       value="${actionTypeEnumSINGLE.id}"
                                       <c:if test="${actionTypeEnumSINGLE.id == cfg.actionTypeId}">checked</c:if> />
                                <label for="actionTypeId${actionTypeEnumSINGLE.id}">${actionTypeEnumSINGLE.name}</label>&#12288;
                                <input id="actionTypeId${actionTypeEnumMANY.id}" class="" type="radio" name="actionTypeId"
                                       value="${actionTypeEnumMANY.id}"
                                       <c:if test="${actionTypeEnumMANY.id == cfg.actionTypeId}">checked</c:if> />
                                <label for="actionTypeId${actionTypeEnumMANY.id}">${actionTypeEnumMANY.name}</label>
                            </td>
                        </tr>
                        <tr>
                            <th><font color="red">*</font>文件类型：</th>
                            <td>
                                <input id="isFileY" class="" disabled type="radio" name="isFile1"
                                       value="Y"
                                       <c:if test="${'Y' == cfg.isFile}">checked</c:if> />
                                <label for="isFileY">文件</label>&#12288;
                                <input id="isFileN" class="" disabled type="radio" name="isFile1"
                                       value="N"
                                       <c:if test="${'N' == cfg.isFile}">checked</c:if> />
                                <label for="isFileN">文件夹</label>&#12288;
                                <input id="isFileU" class="" disabled type="radio" name="isFile1"
                                       value="U"
                                       <c:if test="${'U' == cfg.isFile}">checked</c:if> />
                                <label for="isFileU">请求</label>
                            </td>
                        </tr>
                    </table>
                    </form>
                    <div class="button" style="width: 800px;">
                            <input class="search" type="button" value="保&#12288;存" onclick="saveUser();"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>