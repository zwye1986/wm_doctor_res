<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function checkFile(file){
            var filePath = file.value;
            debugger
            <%--var types = "${sysCfgMap['inx_image_support_suffix']}".split(",");--%>
            var types = ".jpg,.png".split(",");
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
                jboxTip("请上传&nbsp;.jpg,.png格式的图片");
            }
        }

        function checkUploadFile(){
            if ($("#uploadFileForm").validationEngine("validate")) {
                jboxStartLoading();
                $(":file.auto:hidden").attr("disabled",true);
                $("#uploadFileForm").submit();
            }
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

    </script>
</head>
<body>
<div class="infoAudit">
    <form id="uploadFileForm" method="post" style="position:relative;" action="<s:url value='/jsres/hospital/checkUploadFile'/>" enctype="multipart/form-data">
        <input type="hidden" name="operType" value="${operType }">
        <input type="hidden" name="orgFlow" value="${orgFlow}"/>
        <input type="hidden" name="orgName" value="${orgName}"/>
        <input type="hidden" name="signUrl" value="${resultPath}"/>
        <input type="hidden" name="signFlow" value="${signFlow}"/>
        <div id="uploadFileDiv" style="display: ${empty result?'':'none'}">
            <div style="text-align: center;vertical-align: middle;">
                <table style="width: 100%;border: 1px solid #e3e3e3; height: 100px;margin-top: 25px;">
                    <tr>
                        <th style="border: 1px solid #e3e3e3;background-color: #ccc;">院长姓名：</th>
                        <td style="border: 1px solid #e3e3e3;">
                            <input class="validate[required,minSize[1],maxSize[25]] input" name="presidentName" type="text" style="margin-left: -285px" />
                        </td>
                    </tr>
                    <tr>
                        <th style="border: 1px solid #e3e3e3;background-color: #ccc;">年份：</th>
                        <td style="border: 1px solid #e3e3e3;">
                            <input class="validate[required,custom[integer],min[2000],max[9999]] input" name="sessionNumber" type="text" style="margin-left: -285px" />
                        </td>
                    </tr>
                    <tr>
                        <th style="border: 1px solid #e3e3e3;background-color: #ccc;">上传文件：</th>
                        <td style="text-align: left;padding-left: 10px;"><input type="file" name="uploadFile" class="validate[required] auto" style="border: none;" onchange="checkFile(this);" accept="${sysCfgMap['inx_image_support_mime']}"/></td>
                    </tr>
                    <tr><td colspan="2" style="text-align: left;padding-left: 10px;">允许上传后缀格式：.jpg,.png</td></tr>
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