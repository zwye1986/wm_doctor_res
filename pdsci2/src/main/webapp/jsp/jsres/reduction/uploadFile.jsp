<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        $(function(){
            isCanDelete();
        });
        function saveReductionAndFile() {
            if (false == $("#uploadFileForm").validationEngine("validate")) {
                return false;
            }
            var jsonData = {};
            var fileFlows = [];
            $("input[name='fileFlows']").each(function () {
                var fileFlow = $(this).val();
                if (fileFlow)
                    fileFlows.push(fileFlow);
            });
            jsonData.fileFlows = fileFlows;
            var url = "<s:url value="/jsres/reduction/saveReductionAndFile?jsonData="/>" + encodeURI(encodeURI(JSON.stringify(jsonData)));
            jboxStartLoading();
            jboxSubmit($("#uploadFileForm"), url, function (resp) {
                jboxTip(resp);
                setTimeout(function () {
                    top.getDoctorRecruit($("#recruitFlow").val(),$("#doctorFlow").val());
                    doClose();
                }, 1000);
            }, null, true);
        }
        function doClose() {
            var openDialog = top.dialog.get('openDialog');
            if (openDialog != null && openDialog.open) {
                openDialog.close().remove();
            }
        }
        function addFile() {
            $('#filesTd').append($("#fileFormat").find("p").clone());
            isCanDelete();
        }
        function moveTr(obj) {
            jboxConfirm("确认删除？", function () {
                var span = obj.parentNode;
                span.remove();
                isCanDelete();
            });
        }
        function isCanDelete(){
            //保持最少一个附件
            var inputUploadFiles = $('#filesTd').find("input[name='uploadFile']")
            if($('#filesTd').find("a").size() > 0){
                if(inputUploadFiles.size() < 2){
                    $(inputUploadFiles.get(0)).removeClass("validate[required]");
                }else {
                    $(inputUploadFiles.get(0)).addClass("validate[required]");
                }
            }else {
                $(inputUploadFiles.get(0)).addClass("validate[required]");
            }
        }
    </script>
</head>
<body>
<div class="div_table" style="margin-top: 20px">
    <form id="uploadFileForm" method="post" style="position:relative;" enctype="multipart/form-data">
        <input type="hidden" id="recordFlow" name="recordFlow" value="${reduction.recordFlow}">
        <input type="hidden" id="recruitFlow" name="recruitFlow" value="${reduction.recruitFlow}">
        <input type="hidden" id="doctorFlow" name="doctorFlow" value="${reduction.doctorFlow}">
        <input type="hidden" id="defaultTrainYear" name="defaultTrainYear" value="3">
        <input type="hidden" id="reduceYear" name="reduceYear" value="${reduction.reduceYear}">
        <input type="hidden" id="afterReduceTrainYear" name="afterReduceTrainYear"
               value="${reduction.afterReduceTrainYear}">
        <div style="text-align: center;vertical-align: middle;">
            <table border="0" cellpadding="0" cellspacing="0" class="grid">
                <tr>
                    <td style="border: 1px solid #e3e3e3;">上传文件：</td>
                    <td id="filesTd" style="text-align: left;padding-left: 10px;max-width: 350px;">
                        <c:if test="${not empty reductionFiles}">
                            <c:forEach items="${reductionFiles}" var="reductionFile" varStatus="number">
                                <p class="fileSpan" style="line-height: 30px;height: 30px;"
                                   title="${reductionFile.fileName}">
                                    <input hidden name="fileFlows" value="${reductionFile.fileFlow}">
                                    <a href="${sysCfgMap['upload_base_url']}/${reductionFile.filePath}"
                                       target="_blank">${pdfn:cutString(reductionFile.fileName,10,true,6)}</a>
                                    &#12288;
                                    <img class='opBtn' title='删除' style='cursor: pointer;'
                                         src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>"
                                         onclick='moveTr(this);'/>
                                </p>
                            </c:forEach>
                        </c:if>
                        <p class="fileSpan" style="line-height: 30px;height: 30px;">
                            <input type="file" name="uploadFile" class="validate[required]" style="border: none;"
                                   accept="${sysCfgMap['inx_image_support_suffix']}"/>
                            &#12288;
                            <img class="opBtn" title="新增"
                                 src="<s:url value="/css/skin/${skinPath}/images/add3.png" />"
                                 style="cursor: pointer;" onclick="addFile();"/>
                        </p>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="text-align: left;padding-left: 10px;height: 45px;">
                        允许上传后缀格式：${sysCfgMap['inx_image_support_suffix']}
                    </td>
                </tr>
            </table>
        </div>
        <div class="button">
            <input class="btn_green" type="button" value="确&#12288;定" onclick="saveReductionAndFile();"/>
            <input class="btn_green" type="button" value="取&#12288;消" onclick="doClose();"/>
        </div>
    </form>
</div>
<%--附件模板--%>
<div id="fileFormat" style="display: none" style="width: 100%">
    <p style="line-height: 30px;height: 30px;">
        <input type="file" name="uploadFile" class="validate[required]" style="border: none;"
               accept="${sysCfgMap['inx_image_support_mime_desc']}"/>
        &#12288;
        <img class='opBtn' title='删除' style='cursor: pointer;'
             src="<s:url value='/css/skin/${skinPath}/images/del1.png'/>" onclick='moveTr(this);'/>
    </p>
</div>
</body>
</html>