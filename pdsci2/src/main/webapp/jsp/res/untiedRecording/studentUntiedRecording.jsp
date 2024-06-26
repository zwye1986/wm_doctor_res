<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/DatePicker/WdatePicker.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        td{text-align: left}
        textarea{width: 90%;height: 100px;max-height: 100px;max-width: 96%;margin: 5px 0;}
    </style>
    <script type="text/javascript">

        $(document).ready(function () {
            $("#file").live("change", function () {
                uploadFile();
            });
            /*if ($("#trainOrg").length) {
                $("#trainOrg").likeSearchInit({
                    clickActive: function (flow) {
                        $("." + flow).show();
                    }
                });
            }*/
        });
        function uploadFile() {
            if (false == $("#fileForm").validationEngine("validate")) {
                return false;
            }
            jboxStartLoading();
            var checkResult = checkFile($("#file")[0]);
            if (!checkResult) {
                jboxEndLoading();
                return false;
            }
            var url = "<s:url value='/res/manager/uploadNoticeFile'/>";
            jboxSubmit($("#fileForm"), url, function (resp) {
                if ("${GlobalConstant.UPLOAD_FAIL}" != resp) {
                    jboxEndLoading();
                    var index = resp.indexOf("/");
                    if (index != -1) {
                        returnUrl(resp);
                    } else {//验证文件信息
                        jboxInfo(resp);
                    }
                }
            }, null, false);
        }
        function checkFile(file) {
            var filePath = file.value;
            if (filePath == "") {
                return false;
            }
            var types = "${sysCfgMap['student_account_unbind_upload_attachment_suffix']}".split(",");
            var regStr = "/";
            for (var i = 0; i < types.length; i++) {
                if (types[i]) {
                    if (i == (types.length - 1)) {
                        regStr = regStr + "\\" + types[i] + '$';
                    } else {
                        regStr = regStr + "\\" + types[i] + '$|';
                    }
                }
            }
            regStr = regStr + '/i';
            regStr = eval(regStr);
            if ($.trim(filePath) != "" && !regStr.test(filePath)) {
                file.value = "";
                jboxTip("请上传&nbsp;${sysCfgMap['student_account_unbind_upload_attachment_suffix']}格式的文件");
                return false;
            } else {
                return true;
            }
        }
        /**
         * 返回文件URL
         */
        function returnUrl(filePath) {
            $("#noticePicPath").text("重新上传");
            $("#noticePicPathValue").val(filePath);
            var filePath = "${sysCfgMap['upload_base_url']}/" + filePath;
            $("#noticePicPathDel").show();
            $("#noticePicPathSpan").show();
            $("#noticePicPathSpan").find("a").attr('href', filePath);
        }
        /**
         * 删除文件
         */
        function delFile() {
            jboxConfirm("确认删除？", function () {
                $("#noticePicPathDel").hide();
                $("#noticePicPathSpan").hide();
                $("#noticePicPath").text("上传");
                $("#noticePicPathValue").val("");
                $("#file").val(null);
            });
        }

        /**
         * 删除文件
         */
        function delFile() {
            jboxConfirm("确认删除？", function () {
                $("#noticePicPathDel").hide();
                $("#noticePicPathSpan").hide();
                $("#noticePicPath").text("上传");
                $("#noticePicPathValue").val("");
                $("#file").val(null);
            });
        }

        function save() {
            if(false==$("#addForm").validationEngine("validate")){
                return false;
            }
            debugger;
            var description = $("#untiedDescription").val();
            var PicPathValue = $("#noticePicPathValue").val();
            if( description== "" && PicPathValue == ""){
                jboxTip("解锁说明和解锁附件不能同时为空！！！");
                return false;
            }
            if(/^\s+$/gi.test(description)){
                jboxTip("解锁说明请输入文本内容！！！");
                return false;
            }
            var url = "<s:url value='/res/manager/UnlockUserUnlock'/>";
            var msg = "确认无误点击保存？";

            jboxConfirm(msg,function(){
                jboxSubmit($('#addForm'),url,function(resp) {
                    if(resp=='1'){
                        window.parent.search();
                        jboxClose();
                        jboxTip("操作成功");
                    }else{
                        top.jboxTip("操作失败");
                    }
                }, null, false);
            });
        }
        function chooseFile(id) {
            return $("#file").click();
        }
    </script>
</head>
<body>
<div class="mainright"  >
    <div class="basic" >
        <form id="fileForm" method="post" enctype="multipart/form-data">
            <input type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}" style="display: none"/>
        </form>
        <form id="addForm" style="position: relative;">
            <input name="auditStatusId" id="auditStatusId" type="hidden"  value=""/>
            <input name="recordFlow" id="recordFlow" type="hidden"  value="${Recording.recordFlow}"/>
            <input name="doctorFlow" id="doctorFlow" type="hidden"  value="${doctorFlow}"/>

            <table class="grid" style="width:100%; margin-bottom: 10px; margin-top: 30px;">

                <tr>
                    <th style="text-align: center; width: 20%"> 姓名：</th>
                    <td style="text-align: center; width: 30%">${userName} </td>
                    <th style="text-align: center; width: 20%"> 锁定日期：</th>
                    <td style="text-align: center; width: 30%"> ${Recording.lockDate}</td>
                </tr>

                <tr>
                    <th style="text-align: center" colspan="1">
                        <font >解锁说明：</font>
                    </th>
                    <td style="padding: 0"  colspan="3">
                        <textarea placeholder="请输入解锁说明或者上传解锁附件!" value="${Recording.untiedDescription}" id="untiedDescription" name="untiedDescription" style="width: 100%;height: 100px;"></textarea>
                    </td>
                </tr>
                <tr>
                    <th style="text-align: center" colspan="1">
                        <font >上传解锁附件：</font>
                    </th>
                    <td colspan="3" style="padding-left: 20px">
                        <input type="hidden" id="noticePicPathValue" name="untiedFile"  value="${Recording.untiedFile}"/>
                        <a id="noticePicPath" href="javascript:chooseFile();"  class="btn"> ${empty Recording.untiedFile ? '' : '重新'}上传</a>&nbsp;
                        <a id="noticePicPathDel" href="javascript:delFile();" class="btn" style="${empty Recording.untiedFile ? 'display:none' : ''}">删除</a>&nbsp;
                        <font color="red">（支持jpg、png）</font>
                    </td>
                </tr>

            </table>
        </form>
        <p style="text-align: center;">
            <input type="button" onclick="save();" class="btn_green" value="确认解锁"/>&#12288;
            <input type="button" onclick="jboxClose();" class="btn_green" value="取&#12288;消"/>
        </p>
    </div>
</div>
</body>
</html>
