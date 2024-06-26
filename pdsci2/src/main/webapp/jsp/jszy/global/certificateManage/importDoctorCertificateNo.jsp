<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jszy/htmlhead-jszy.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="true"/>
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
    <style type="text/css">
        #boxHome .item:HOVER {
            background-color: #eee;
        }
    </style>
    <script type="text/javascript">
        function daoRu() {
            if (false == $("#excelForm").validationEngine("validate")) {
                return false;
            }
            var checkFileFlag = $("#checkFileFlag").val();
            if ('${GlobalConstant.FLAG_Y}' != checkFileFlag) {
                jboxTip("请上传Excel文件！");
                return false;
            }
            jboxStartLoading();
            var url = "<s:url value='/jszy/certificateManage/importDoctorCertificateNoFromExcel2'/>";
            jboxSubmit(
                    $('#excelForm'),
                    url,
                    function (resp) {
                        top.jboxEndLoading();
                        endloadIng();
                        top.jboxInfo(resp);
                        if (resp.substring(0, 5) == "${GlobalConstant.UPLOAD_SUCCESSED}") {
                            window.parent.toPage(1);
                            top.jboxClose();
                        }
                    },
                    function (resp) {
                        top.jboxEndLoading();
                        endloadIng();
                        top.jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
                    }, false);
        }
        function endloadIng() {
            var openDialog = dialog.get('artLoading');
            if (openDialog != null && openDialog.open) {
                openDialog.close().remove();
            }
            openDialog = dialog.get('loadingDialog');
            if (openDialog != null && openDialog.open) {
                openDialog.close().remove();
            }
            var jboxMainIframeLoading = $("#jboxMainIframeLoading");
            if (jboxMainIframeLoading != null) {
                jboxMainIframeLoading.fadeOut(500, function () {
                    $(jboxMainIframeLoading).remove();
                });
            }
        }

        function checkFile(file) {
            var filePath = file.value;
            var suffix = filePath.substring(filePath.lastIndexOf(".") + 1);
            if ("xlsx" == suffix || "xls" == suffix) {
                $("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
            } else {
                $("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
                $(file).val(null);
                jboxTip("请上传Excel文件");
            }
        }
    </script>
</head>
<body>
<div  class="div_table">
    <input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
    <form id="excelForm" method="post" enctype="multipart/form-data">
        <table class="grid" style="width: 100%;margin-top: 50px;">
            <tr>
                <th style="text-align: right;">选择文件：</th>
                <td style="text-align: left;">
                    <input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">模板文件：</th>
                <td style="text-align: left;"><a
                        href="<s:url value='/jsp/jszy/global/certificateManage/importSchoolRoll.xlsx'/>">证书信息导入模板.xlsx</a>
                </td>
            </tr>
        </table>
    </form>
    <div style="text-align: center; margin-top: 10px;">
        <input type="button" onclick="daoRu();" value="导&#12288;入" class="btn_brown"/>
    </div>
</div>

</body>
</html>