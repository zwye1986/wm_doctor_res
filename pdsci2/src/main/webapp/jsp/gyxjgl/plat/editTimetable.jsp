<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        function doSave() {
            if(false==$("#sysDictForm").validationEngine("validate")){
                return ;
            }
            var dictNameText = $("select[name='dictId'] option:selected").text();
            $("input[name='dictName']").val(dictNameText);
            var url = "<s:url value='/gyxjgl/student/course/saveTdxlTimetable'/>";
            var data = $('#sysDictForm').serialize();
            jboxPost(url, data, function(resp) {
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}'){
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            },null,true);
        }
        //上传课表
        function uploadCert(){
            $.ajaxFileUpload({
                url:"<s:url value='/gyxjgl/student/course/uploadTdxlFile'/>",
                secureuri:false,
                fileElementId:'fileUpInput',
                dataType: 'json',
                success: function (data, status){
                    if(data.indexOf("success")==-1){
                        jboxTip(data);
                    }else{
                        jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
                        var arr = new Array();
                        arr = data.split(":");
                        var url = arr[1];
                        $("input[name='dictDesc']").attr("value",url);
                    }
                },
                error: function (data, status, e){
                    jboxTip('${GlobalConstant.UPLOAD_FAIL}');
                },
                complete:function(){
                    $('#fileUpInput').val("");
                }
            });
        }
    </script>
</head>
<body>
<form id="sysDictForm" style="height: 100px;" >
    <div class="content">
        <div class="title1 clearfix">
            <div id="tagContent">
                <div class="tagContent selectTag" id="tagContent0">
                    <table style="width: 400px" cellpadding="0" cellspacing="0" class="basic">
                        <input name="dictTypeName" value="同等学力学生课表" type="hidden"/>
                        <input name="dictTypeId" value="GyXjTdxlTimetable" type="hidden"/>
                        <input name="dictFlow" value="${dict.dictFlow}" type="hidden"/>
                        <tr>
                            <th width="150px">&#12288;入学年级：</th>
                            <td width="200px"><input class="validate[required] xltext" name="orgFlow" type="text" value="${dict.orgFlow}" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy'})" /></td>
                        </tr>
                        <tr>
                            <th>&#12288;学习形式：</th>
                            <td>
                                <select style="width: 168px;" name="dictId" class="validate[required] xlselect" >
                                    <option/>
                                    <c:forEach items="${dictTypeEnumGyStudyFormList}" var="studyForm">
                                        <option value="${studyForm.dictId}" ${dict.dictId eq studyForm.dictId?'selected':''}>${studyForm.dictName}</option>
                                    </c:forEach>
                                </select>
                                <input hidden="hidden" name="dictName" value="${dict.dictName }" />
                            </td>
                        </tr>
                        <tr>
                            <th>课表：</th>
                            <td>
                                <input type="button" class="search" onclick="$('#fileUpInput').click();" value="上&emsp;传">
                                <input type="file" id="fileUpInput" name="file" style="display:none" onchange="uploadCert();"/>
                                <input hidden="hidden" name="dictDesc" value="${dict.dictDesc }" />
                            </td>
                        </tr>
                    </table>
                    <div class="button" style="width: 100%">
                        <input class="search" type="button" value="保&#12288;存" onclick="doSave();" />
                        <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>