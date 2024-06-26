<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(document).ready(function(){
            $('#sessionNumber').datepicker({
                startView: 2,
                maxViewMode: 2,
                minViewMode:2,
                format:'yyyy'
            });
        });

        function checkFile(file){
            var filePath = file.value;
            var types = ".xlsx,.xls".split(",");
            var regStr = "/";
            for(var i = 0 ;i<types.length;i++){
                if(types[i]){
                    if(i==(types.length-1)){
                        regStr = regStr+"\\"+types[i]+'$';
                    }else{
                        regStr = regStr+"\\"+types[i]+'$|';
                    }
                }
            }
            regStr = regStr+'/i';
            regStr = eval(regStr);
            if($.trim(filePath)!="" && !regStr.test(filePath)){
                file.value = "";
                jboxTip("请上传&nbsp;.xlsx,.xls格式的文件");
            }
        }

        function checkUploadFile(){
            if(false == $("#uploadFileForm").validationEngine("validate")){
                return false;
            }
            var testId = $("#testId").val();
            if(testId == ''){
                jboxTip("考试编号不能为空！");
                return false;
            }
            jboxStartLoading();
            var sessionNumber = $("#sessionNumber").val();
            var url = "<s:url value='/jsres/doctor/checkUploadDoctorFile'/>?sessionNumber="+sessionNumber+"&testId="+testId;
            jboxSubmit($("#uploadFileForm"),url,function(resp){
                if("${GlobalConstant.UPLOAD_FAIL}" != resp){
                    jboxEndLoading();
                    var index = resp.indexOf("/");
                    if(index != -1){
                        returnUrl(resp);
                    }else{//验证文件信息
                        jboxInfo(resp);
                    }
                }
            }, null, false);
        }

        function returnUrl(){
            window.parent.toPage(1);
            doClose();
        }

        function reUpload(){
            $('#uploadErrorDiv').hide();
            $('#uploadFileDiv').show();
        }

        function doClose() {
            if('${param.second}'=='Y'){
                top.jboxClose();
            }
            var openDialog = top.dialog.get('openDialog');
            if(openDialog !=null && openDialog.open){
                openDialog.close().remove();
            }
        }

        $(document).ready(function(){
            if ("${GlobalConstant.FLAG_Y}"=="${result}") {
                window.parent.toPage(1);
                doClose();
            }else if ("${GlobalConstant.FLAG_N}"=="${result}"){
                jboxTip("保存失败");
            }
        });

        function changeTestId() {
            $("#testId").empty();
            $("#testId").append("<option value=''>"+"请选择"+"</option>");
            <c:forEach items="${testList}" var="t">
                var testId = "${t.testId}";
                var year = testId.substr(0,4);
                if(year == $("#sessionNumber").val()){
                    $("#testId").append("<option value='"+testId+"'>"+testId+"</option>");
                }
            </c:forEach>
        }
    </script>
</head>
<body>
<div class="infoAudit">
    <form id="uploadFileForm" method="post" style="position:relative;"  enctype="multipart/form-data">
        <div id="uploadFileDiv" style="display: ${empty result?'':'none'}">
            <div class="search_table">
                <table style="width: 100%; height: 100px;margin-top: 25px;border:1px solid #eee ">
                    <tr>
                        <th class="td_left" style="border-right: 1px solid #eee;border-bottom: 1px solid #eee;background: #F4F5F9">结业年份：</th>
                        <td style="text-align: left;padding-left: 10px;border-bottom: 1px solid #eee;">
                            <input type="text" id="sessionNumber" name="sessionNumber"  class="validate[required] input"
                                   onchange="changeTestId()"
                                   readonly="readonly" style="width: 128px;margin-left: 0px"/>
                        </td>
                    </tr>
                    <tr>
                        <th class="td_left" style="border-right: 1px solid #eee;border-bottom: 1px solid #eee;background: #F4F5F9">考试编号：</th>
                        <td style="text-align: left;padding-left: 10px;border-bottom: 1px solid #eee;">
                            <select name="testId" id="testId" class="select " style="width: 134px">
                                <option value="">请选择</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th style="border-right: 1px solid #eee;border-bottom: 1px solid #eee;background: #F4F5F9">上传文件：</th>
                        <td style="text-align: left;padding-left: 10px;border-bottom: 1px solid #eee;">
                            <input type="file" name="uploadFile" class="validate[required] auto" style="border: none;" onchange="checkFile(this);" accept=".xlsx,.xls"/>
                        </td>
                    </tr>
                    <tr><td colspan="2" style="text-align: left;padding-left: 10px;">允许上传后缀格式：.xlsx,.xls</td></tr>
                </table>
            </div>
            <div class="button">
                <input class="btn_green" type="button" value="确&#12288;定" onclick="checkUploadFile();" />
                <input class="btn_green" type="button" value="取&#12288;消" onclick="doClose();" />
            </div>
        </div>
        <div id="uploadErrorDiv" style="display: ${empty fileErrorMsg?'none':''}">
            <div style="color: red;height: 60px;text-align: center;vertical-align: middle;"><B>${fileErrorMsg}</B></div>
            <div class="button">
                <input class="btn_green" type="button" value="重新上传" onclick="reUpload();" />
                <input class="btn_green" type="button" value="取&#12288;消" onclick="doClose();" />
            </div>
        </div>
    </form>
</div>
</body>
</html>