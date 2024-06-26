<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style type="text/css">
        #myForm div {
            margin-top: 5px;
        }
    </style>
    <script type="text/javascript">
        var dataJson = {"book":"著作","thesis":"论文","award":"报奖","patent":"科研项目"};

        $(function(){
            if('${score.recordFlow}'!=""){
                jboxPost("<s:url value='/fstu/score/qryCfg'/>", $("#myForm").serialize(), function (resp) {
                    if (null != resp['config']) {
                        var key = resp['config'].reltiveType;
                        if(key){
                            $(".typeList").show();
                            $("[name='scoreName']").attr("readonly","readonly");
                            $(".typeTh").text(dataJson[key]+"列表：");
                            if(null != resp['list'] && resp['list'].length>0){
                                var htmlContent = "";
                                for(var i=0;i<resp['list'].length;i++){
                                    htmlContent += '<lable><input type="radio" name="same" score='+resp["list"][i]["scoreValue"]+' value='+resp["list"][i]["name"]+' onclick="changeName(this)"/>'+resp["list"][i]["name"]+'</lable>&nbsp;';
                                }
                                $(".typeTd").html(htmlContent);
                            }else{
                                $(".typeTd").html("暂无数据，请去"+dataJson[key]+"模块添加");
                            }
                            $("input[type='radio'][value='${score.scoreName}']").attr("checked","checked");
                        }
                    }
                    parent.jboxEndLoading;
                }, null, false);
            }
        });

        function checkYear(obj) {
            var dates = $(':text', $(obj).closest("tr"));
            if (dates[0].value && dates[1].value && dates[0].value > dates[1].value) {
                jboxTip("开始时间不能大于结束时间！");
                obj.value = "";
            }
        }
        function linkage(dictId) {
            $('#firstProjTypeId').val("");//清空上次展现数据
            $('#firstProjTypeId option').hide();
            $('#firstProjTypeId option[value=""]').show();
            $('#firstProjTypeId' + ' option.${dictTypeEnumAcaScoreType.id}\\.' + dictId).show();
        }
        function save(statusId) {
            if (!$("#myForm").validationEngine("validate")) {
                return;
            }
            var firstScoreTypeText = $("#firstScoreTypeId option:selected").text();
            $("#firstScoreTypeName").val(firstScoreTypeText);
            var firstProjTypeText = $("#firstProjTypeId option:selected").text();
            $("#firstProjTypeName").val(firstProjTypeText);
            var firstScoreItemText = $("#firstScoreItemId option:selected").text();
            $("#firstScoreItemName").val(firstScoreItemText);
            var firstExecutionText = $("#firstExecutionId option:selected").text();
            $("#firstExecutionName").val(firstExecutionText);
            $("#auditStatusId").val(statusId);
            var score = $("#myForm").serializeJson();
            var fileTr = $("#projFileTb").children();
            var fileDatas = [];
            //console.log("1234");
            $.each(fileTr, function (i, n) {
                var fileFlow = $(n).find("input[name='fileFlow']").val();
                var fileRemark = $(n).find("input[name='fileRemark']").val();
                var pubFlie = {
                    "fileFlow": fileFlow,
                    "fileRemark": fileRemark
                };
               // console.log("567");
                fileDatas.push(pubFlie);
            });
            var requestData = {'fileList': fileDatas, 'score': score};
            $("#jsondata").val(JSON.stringify(requestData));
            var url = "<s:url value='/fstu/score/saveScore2'/>";
            //console.log("8");
            jboxSubmit($("#myForm"), url, function (obj) {
                if (obj == "${GlobalConstant.OPRE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
        function qryCfg(firstExecutionId) {
            var firstScoreTypeId = $('#firstScoreTypeId').val();
            var firstProjTypeId = $('#firstProjTypeId').val();
            var firstScoreItemId = $('#firstScoreItemId').val();
            if (firstScoreTypeId == "" || firstProjTypeId == "" || firstScoreItemId == "") {
                jboxTip("请先选择学分类别、项目大类和评分项！");
                $('#firstExecutionId').val("");
            } else if (firstExecutionId != "") {
                jboxPost("<s:url value='/fstu/score/qryCfg'/>", $("#myForm").serialize(), function (resp) {
                    $(".typeList").hide();
                    $(".typeTh").html("");
                    $(".typeTd").html("");
                    $("[name='scoreName']").removeAttr("placeholder");
                    $("[name='scoreName']").removeAttr("readonly");
                    if (null != resp['config']) {
                        $('#configRecordFlow').val(resp['config'].recordFlow);
                        $('#myScore').val(resp['config'].score);
                        var score0 = resp['config'].score;
                        $('#firstScore').val(resp['config'].score);
                        $('#firstMaxScore').val(resp['config'].maxScore);
                        $('#firstMiurAuditFlag').val(resp['config'].miurAuditFlag);
                        var key = resp['config'].reltiveType;
                        if(key){
                            $(".typeList").show();
                            $("[name='scoreName']").attr("placeholder","请在列表中选择");
                            $("[name='scoreName']").attr("readonly","readonly");
                            $(".typeTh").text(dataJson[key]+"列表：");
                            if(null != resp['list'] && resp['list'].length>0){
                                var htmlContent = "";
                                for(var i=0;i<resp['list'].length;i++){
                                    htmlContent += '<lable><input type="radio" name="same" score0='+score0+' score='+resp["list"][i]["scoreValue"]+' value='+resp["list"][i]["name"]+' onclick="changeName(this)"/>'+resp["list"][i]["name"]+'</lable>&nbsp;';
                                }
                                $(".typeTd").html(htmlContent);
                            }else{
                                $(".typeTd").html("暂无数据，请去"+dataJson[key]+"模块添加");
                            }
                        }
                    }else{
                        $('#firstExecutionId').val("");
                        jboxTip("该评分层级学分关系未配置！");
                    }
                    parent.jboxEndLoading;
                }, null, false);
            }
        }

        function  changeName(obj){
            $("[name='scoreName']").val($(obj).val());
            if($(obj).attr('score')!='null'){
                $("#myScore").val($(obj).attr('score'));
            }else{
                $("#myScore").val($(obj).attr('score0'));
            }
        }

        function reCheck(obj) {
            $(obj).hide();
            $("#down").hide();
            $("#file").show();
        }
        // 删除文件
        function delFile(fileUrlId) {
            jboxConfirm("确认删除？", function () {
                $("#" + fileUrlId + "Del").hide();
                $("#" + fileUrlId + "Span").hide();
                $("#" + fileUrlId).val("上传");
                $("#" + fileUrlId + "Value").val("");
                $("#file").val(null);
            });
        }
        String.prototype.htmlFormartById = function (data) {
            var html = this;
            for (var key in data) {
                var reg = new RegExp('\\{' + key + '\\}', 'g');
                html = html.replace(reg, data[key]);
            }
            return html;
        }

        function scoreLimit(value) {
            var n = Number(value);
            if (!isNaN(n) && n > $('#firstMaxScore').val()) {
                jboxTip("分值不能大于最大分值！");
                $('#myScore').val('');
            }
        }
        function addFile(tb) {
            if ($("#projFileTb tr").length > 9) {
                jboxTip("附件总数不能超过10个！");
                return false;
            }

            var html = '<tr>' +
                    '<td><input type="file" id="file" name="files" class="validate[required]"/></td>' +
                    '<td><input class="validate[required,maxSize[100]] xltext" style="width: 97%;" name="fileRemark" type="text"/></td>' +
                    '<td><a class="reCheck" href="javascript:void(0);" onclick="delTr(this);">[删除]</a></td>' +
                    '</tr>';
            $('#' + tb).append(html);
        }

        function delTr(tb) {
            jboxConfirm("确认删除？", function () {
                $(tb).parent('td').parent("tr").remove();
            });
        }

        function delFile(obj, fileFlow) {
            var url = '<s:url value="/pub/file/delFileByFlow?fileFlow="/>' + fileFlow;
            jboxConfirm("确认删除？", function () {
                jboxGet(url, null, function () {
                    var tr = $(obj).parent("td").parent("tr");
                    tr.remove();
                }, null, true);
            });
        }
        function clearData(){
            $("#firstExecutionId").val("");
            $("#myScore").val("");
            $("#firstScore").val("");
            $("#firstMaxScore").val("");
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <form id="myForm">
                <input id="jsondata" type="hidden" name="jsondata" value=""/>
                <input id="auditStatusId" type="hidden" name="headerAuditStatusId"/>
                <input type="hidden" name="auditStatusId"/>
                <table class="basic" style="width: 100%;">
                    <tr>
                        <th style="width: 20%;"><span style="color:red;">*</span>学分年份：</th>
                        <td colspan="3">
                            <input type="hidden" name="recordFlow" value="${score.recordFlow}">
                            <input type="hidden" id="configRecordFlow" name="configRecordFlow"
                                   value="${score.configRecordFlow}">
                            <input type="hidden" id="firstMiurAuditFlag" name="firstMiurAuditFlag"
                                   value="${score.firstMiurAuditFlag}">
                            <select name="sessionNumber" style="width:140px;" class="validate[required]">
                                <option/>
                                <c:forEach items="${dictTypeEnumScoreYearList}" var="dict">
                                    <option value="${dict.dictName}"
                                            <c:if test="${dict.dictName eq score.sessionNumber}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <th style="width: 20%;">学员姓名：</th>
                        <td colspan="3">
                            <input type="text" name="userName" value="${user.userName}" readonly="readonly"
                                   style="width:137px;">
                            <input type="hidden" name="userFlow" value="${user.userFlow}">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">学员工号：</th>
                        <td colspan="3">
                            <input type="text" name="userCode" value="${user.userCode}" readonly="readonly"
                                   style="width:137px;">
                        </td>
                        <th style="width: 20%;">用户所属部门：</th>
                        <td colspan="3">
                            <input type="text" name="userDeptName" value="${user.deptName}" readonly="readonly"
                                   style="width:137px;">
                            <input type="hidden" name="userDeptFlow" value="${user.deptFlow}">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"><span style="color:red;">*</span>学分类别：</th>
                        <td colspan="3">
                            <select id="firstScoreTypeId" name="firstScoreTypeId" style="width:140px;" onchange="linkage(this.value);clearData();" class="validate[required]">
                                <option/>
                                <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                    <option value="${dict.dictId}" <c:if test="${dict.dictId eq score.firstScoreTypeId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" id="firstScoreTypeName" name="firstScoreTypeName">
                        </td>
                        <th style="width: 20%;"><span style="color:red;">*</span>项目大类：</th>
                        <td colspan="3">
                            <select id="firstProjTypeId" name="firstProjTypeId" style="width:140px;" class="validate[required]" onchange="clearData();">
                                <option/>
                                <c:forEach items="${dictTypeEnumAcaScoreTypeList}" var="dict">
                                    <c:set var="dictKey" value="dictTypeEnumAcaScoreType.${dict.dictId}List"/>
                                    <c:forEach items="${applicationScope[dictKey]}" var="scope">
                                        <option class="${scope.dictTypeId}" value="${scope.dictId}"
                                                <c:if test="${scope.dictId eq score.firstProjTypeId and dict.dictId eq score.firstScoreTypeId}">selected</c:if>>${scope.dictName}</option>
                                    </c:forEach>
                                </c:forEach>
                            </select>
                            <input type="hidden" id="firstProjTypeName" name="firstProjTypeName">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"><span style="color:red;">*</span>评分项：</th>
                        <td colspan="3">
                            <select id="firstScoreItemId" name="firstScoreItemId" style="width:140px;" class="validate[required]" onchange="clearData();">
                                <option/>
                                <c:forEach items="${dictTypeEnumAssessItemList}" var="dict">
                                    <option value="${dict.dictId}" <c:if test="${dict.dictId eq score.firstScoreItemId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" id="firstScoreItemName" name="firstScoreItemName">
                        </td>
                        <th style="width: 20%;"><span style="color:red;">*</span>完成情况：</th>
                        <td colspan="3">
                            <select id="firstExecutionId" name="firstExecutionId" style="width:140px;" class="validate[required]" onchange="qryCfg(this.value)">
                                <option/>
                                <c:forEach items="${dictTypeEnumAccomplishResultList}" var="dict">
                                    <option value="${dict.dictId}" <c:if test="${dict.dictId eq score.firstExecutionId}">selected</c:if>>${dict.dictName}</option>
                                </c:forEach>
                            </select>
                            <input type="hidden" id="firstExecutionName" name="firstExecutionName">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"><span style="color:red;">*</span>名称：</th>
                        <td colspan="3">
                            <input type="text" name="scoreName" value="${score.scoreName}" style="width:137px;" class="validate[required]">
                        </td>
                        <th style="width: 20%;">编号：</th>
                        <td colspan="3">
                            <input type="text" name="scoreNumber" value="${score.scoreNumber}"
                                   style="width:137px;">
                        </td>
                    </tr>
                    <tr class="typeList" style="display: none;">
                        <th class="typeTh"></th>
                        <td class="typeTd" colspan="7">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">开始时间：</th>
                        <td colspan="3">
                            <input type="text" readonly="readonly" style="width:137px;" name="beginTime"
                                   value="${score.beginTime}" onchange="checkYear(this)"
                                   onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                        </td>
                        <th style="width: 20%;">结束时间：</th>
                        <td colspan="3">
                            <input type="text" readonly="readonly" style="width:137px;" name="endTime"
                                   value="${score.endTime}" onchange="checkYear(this)"
                                   onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;"><span style="color:red;">*</span>分值：</th>
                        <td colspan="3">
                            <input type="text" id="myScore" name="myScore"
                                   onchange="scoreLimit(this.value)"
                                   value="${score.myScore}" class="validate[required,custom[number]]"
                                   style="width:137px;">
                            <input type="hidden" id="firstScore" name="firstScore" value="${score.firstScore}">
                        </td>
                        <th style="width: 20%;">最大分值：</th>
                        <td colspan="3">
                            <input type="text" id="firstMaxScore" name="firstMaxScore"
                                   value="${score.firstMaxScore}" readonly="readonly" style="width:137px;">
                        </td>
                    </tr>
                    <tr>
                        <th style="width: 20%;">说明：</th>
                        <td colspan="8">
                            <textarea type="text" name="instruction" style="width: 546px;height: 80px"
                                      placeholder="支持250字符"
                                      class="validate[maxSize[250]]">${score.instruction}</textarea>
                        </td>
                    </tr>

                </table>
                <table class="basic" style="width:100%;">
                    <thead>
                    <tr>
                        <th colspan="4" class="bs_name">附件上传
                            <c:if test="${viewFlag != 'view'}">
                                    <span style="float: right;padding-right: 10px"><a href="javascript:void(0)"><img
                                            title="新增" src="<s:url value='/'/>css/skin/${skinPath}/images/add.png"
                                            style="cursor: pointer;" onclick="addFile('projFileTb')"/></a></span>
                            </c:if>
                        </th>
                    </tr>
                    <tr>
                        <td width="30%" style="font-weight:bold;">附件名称</td>
                        <td width="40%" style="font-weight:bold;">附件内容</td>
                        <td width="20%" style="font-weight:bold;">附件操作</td>
                    </tr>
                    </thead>
                    <tbody id="projFileTb">
                    <c:forEach var="file" items="${pubFileList}" varStatus="status">
                        <tr>
                            <td>
                                <c:choose>
                                    <c:when test="${not empty file.fileFlow}">
                                        <a id="down"
                                           href='<s:url value="/fstu/dec/fileDown?fileFlow=${file.fileFlow}"/>'>${file.fileName}</a>
                                        <input type="file" id="file" name="files" style="display: none;"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="file" id="file" name="files"/>
                                    </c:otherwise>
                                </c:choose>
                                <input type="hidden" name="fileFlow" value="${file.fileFlow}"/>
                            </td>
                            <td>
                                <input class="validate[required,maxSize[100]] xltext" style="width: 97%;"
                                       name="fileRemark" type="text" value="${file.fileRemark}"/>
                            </td>
                            <td>
                                <a class="reCheck" href="javascript:void(0);" onclick="reCheck(this);">[重新选择文件]</a>&#12288;
                                <a class="reCheck" href="javascript:void(0);"
                                   onclick="delFile(this,'${file.fileFlow}');">[删除]</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div style="text-align: center;margin-top:20px;">
                    <input type="button" class="search" onclick="save('Add');" value="保&#12288;存"/>
                    <input type="button" class="search" onclick="save('Passed');" value="提&#12288;交"/>
                    <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>