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
                jboxTip("请上传.jpg或者.png格式的图片");
            }
        }

        function checkUploadFile(){
            if ($("#uploadFileForm").validationEngine("validate")) {
                jboxStartLoading();
                $(":file.auto:hidden").attr("disabled",true);
                //var url = "<s:url value='/jsres/doctor/checkUploadFile'/>";
                //var url = "<s:url value='/sys/user/checkUploadHeadPortrait'/>?userFlow=${userFlow}";
                var url = "<s:url value='/sys/user/userHeadImgUpload'/>?userFlow=${userFlow}";
                $("#uploadFileForm").submit();
            }
        }

        function returnUrl(){
            if('${param.second}'=='Y'){
                top.document['jbox-message-iframe'].$('#${param.operType}').text("重新上传");
                top.document['jbox-message-iframe'].$('#${param.operType}Value').val("${filePath}");
                var filePath = "${sysCfgMap['upload_base_url']}/${filePath}";
                top.document['jbox-message-iframe'].$('#${param.operType}Del').show();
                top.document['jbox-message-iframe'].$('#${param.operType}Span').show();
                top.document['jbox-message-iframe'].$('#${param.operType}Span').find("a").attr('href',filePath);
                top.document['jbox-message-iframe'].$("#${param.operType}Se").hide();
            }else{
                window.parent.$('#${param.operType}').text("重新上传");
                window.parent.$('#${param.operType}Value').val("${filePath}");
                var filePath = "${sysCfgMap['upload_base_url']}/${filePath}";
                window.parent.$('#${param.operType}Del').show();
                window.parent.$('#${param.operType}Span').show();
                window.parent.$('#${param.operType}Span').find("a").attr('href',filePath);
                window.parent.$("#${param.operType}Se").hide();
            }
            doClose();
        }


        function doClose() {
            window.parent.doctorInfo('${sessionScope.currUser.userFlow}');
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
                returnUrl();
            }
        });

    </script>
</head>
<body>
<div class="infoAudit">
    <form id="uploadFileForm" method="post" style="position:relative;" action="<s:url value='/jsres/doctor/checkUploadHeadPortrait'/>" enctype="multipart/form-data">
        <input type="hidden" name="operType" value="${param.operType}"/>
        <input type="hidden" name="userFlow" value="${param.userFlow}"/>
        <input type="file" id="imageFile" name="headImg" style="display: none" onchange="uploadImage();"/>
        <input  id="headimgurl" type="hidden"value=""/>
        <div id="uploadFileDiv" style="display:  ${empty result ? '':'none'}">
            <div style="text-align: center;vertical-align: middle;">
                <table style="width: 100%;border: 1px solid #e3e3e3; height: 100px;margin-top: 25px;">
                    <tr><td style="border: 1px solid #e3e3e3;">上传文件：</td>
                        <td style="text-align: left;padding-left: 10px;">
                             <input type="file" name="uploadFile" class="validate[required] auto" style="border: none;" onchange="checkFile(this);"   accept=".jpg,.png"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: left;padding-left: 10px;">
                            <font style="color: red;text-align: left;"><br/>照片要求：
                            <br/>1、2寸白底彩色免冠照片，JPG格式，分辨率不小于413*626，大小不超过300K，人像正立。
                            <br/>2、该照片用于结业证书，请慎重认真上传！<br/></font>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="button">
                <input class="btn_green" type="button" value="确&#12288;定" onclick="checkUploadFile();" />
                <input class="btn_green" type="button" value="取&#12288;消" onclick="doClose();" />
            </div>
        </div>
        <div id="uploadErrorDiv" style="padding-top: 20px; display: ${empty result ? 'none':''}"> <%-- --%>
           <%-- <div style="color: red;height: 60px;text-align: center;vertical-align: middle;">--%>
                    <c:choose>
                        <c:when test="${'Y' eq Msg}">
                            <tr><td style="text-align: center"><h1 style="text-align: center;color: #44b549">上传成功</h1> </td> </tr>
                        </c:when>
                        <c:otherwise>
                            <tr> <td ><h1  style="text-align: center;">上传失败,照片不符合要求，请重新上传！</h1><br/></td> </tr>
                            <tr>
                            <td >
                                <font style="color: red; padding-left: 40px">
                                    照片要求：  1、2寸白底彩色免冠照片，JPG格式，分辨率不小于413*626，大小不超过300K，人像正立。
                                    2、该照片用于结业证书，请慎重认真上传！
                                </font>
                            </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>

            <%--</div>--%>
            <div class="button" style="padding-top: 40px;">
                <input class="btn_green" type="button" value="关&#12288;闭" onclick="doClose();" />
            </div>
        </div>
    </form>
</div>
</body>
</html>