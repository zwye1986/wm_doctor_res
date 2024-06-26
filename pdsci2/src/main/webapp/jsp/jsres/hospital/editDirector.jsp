<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="kindeditor" value="true"/>
    </jsp:include>
    <style type="text/css">
        .noticeEditor {
            max-height: 360px;
            overflow: auto;
        }
    </style>
    <script type="text/javascript">
        <c:if test="${'manager' eq roleFlag or 'global'eq roleFlag}">
        $(document).ready(function(){
            //实例化编辑器
            var editor = KindEditor.create('#resNoticeContent_ueditor', {
                uploadJson : "<s:url value='/kindeditor/upload'/>",
                fileManagerJson : "<s:url value='/kindeditor/upload'/>",
                resizeType : 0,
                allowPreviewEmoticons : false,
                allowImageUpload : true,
                items : [
                    'source', '|', 'undo', 'redo', '|',
                    'preview','template', 'cut', 'copy', 'paste',
                    'plainpaste', 'wordpaste', '|', 'justifyleft',
                    'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist',
                    'insertunorderedlist', 'indent', 'outdent', 'subscript',
                    'superscript', 'clearhtml', 'quickformat', 'selectall',
                    '|', 'fullscreen', '/',
                    'formatblock', 'fontname', 'fontsize', '|', 'forecolor',
                    'hilitecolor', 'bold',
                    'italic', 'underline', 'strikethrough', 'lineheight',
                    'removeformat', '|', 'image',
                    'flash', 'media', 'insertfile', 'table', 'hr',
                    'emoticons', 'link', 'unlink', '|'],//image打开本地上传图片必须写,重要的事情说三遍
                afterBlur : function() {
                    //焦点问题，这里不写会出问题.同步KindEditor的值到textarea文本框
                    this.sync();
                }
            });
        });

        </c:if>
        function save() {
            if ($(".resNoticeTitle").val() == "") {
                top.jboxTip("请输入指南标题!");
                return;
            }
            if ($("#resNoticeContent_ueditor").val() == "") {
                top.jboxTip("请输入指南内容!");
                return;
            }
            jboxConfirm("确认保存？", function () {
                var url = "<s:url value='/jsres/training/saveDirector'/>?roleFlag=${roleFlag}";
                var data = $('#resNoticeContent').serialize();
                jboxPost(url, data, function (resp) {
                    if (resp == '1') {
                        top.jboxTip("操作成功");
                        top.guides();
                        top.jboxClose();
                    } else {
                        jboxTip("操作失败");
                    }
                }, null, true);
            });
        }
        $(document).ready(function () {
            $("#file").live("change", function () {
                uploadFile();
            });
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
            var url = "<s:url value='/jsres/training/uploadNoticePic'/>";
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
            var types = "${sysCfgMap['inx_image_support_suffix']}".split(",");
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
                jboxTip("请上传.jpg, .png格式的图片");
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
        function chooseFile(id) {
            return $("#file").click();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div <c:if test="${!('manager' eq roleFlag or 'global'eq roleFlag)}">style="overflow: auto;max-height: 480px" </c:if>>
        <form id="fileForm" method="post" enctype="multipart/form-data">
            <input type="file" id="file" name="file" accept="${sysCfgMap['inx_image_support_mime']}"
                   style="display: none"/>
        </form>
        <form id="resNoticeContent" method="post" enctype="multipart/form-data">
            <input type="hidden" name="recordFlow" value="${tarinNotice.recordFlow}"/>
            <div class="title1 clearfix" align="left" style="padding-bottom: 10px;">
                <c:if test="${'manager' eq roleFlag or 'global'eq roleFlag}">
                    <label>指南标题：</label><input name="resNoticeTitle" class="input resNoticeTitle"
                                               value="${tarinNotice.resNoticeTitle}" style="width: 20%;height: 35px;"/>
                    <span style="padding-left: 200px;">
                        <label>指南封面：</label>
                        <input type="hidden" id="noticePicPathValue" name="noticePicPath"
                               value="${tarinNotice.noticePicPath}"/>
						<span id="noticePicPathSpan" style="display:${!empty tarinNotice.noticePicPath ? '' : 'none'} ">
							[<a href="${sysCfgMap['upload_base_url']}/${tarinNotice.noticePicPath}"
                                target="_blank">查看图片</a>]&nbsp;
						</span>
						<a id="noticePicPath" href="javascript:chooseFile();"  class="btn"> ${empty tarinNotice.noticePicPath ? '' : '重新'}上传</a>&nbsp;
						<a id="noticePicPathDel" href="javascript:delFile();" class="btn" style="${empty tarinNotice.noticePicPath ? 'display:none' : ''}">删除</a>&nbsp;
                    </span>
                </c:if>
                <c:if test="${!('manager' eq roleFlag or 'global'eq roleFlag)}">
                    <div>
                        <span>指南标题：${tarinNotice.resNoticeTitle}</span>
                        <c:if test="${empty tarinNotice.noticePicPath}">
                            <img style="padding-left: 200px;" width="252px" height="160px"
                                 src="<s:url value='/jsp/jsres/images/no-pic.png'/>">
                        </c:if>
                        <c:if test="${not empty tarinNotice.noticePicPath}">
                            <img style="padding-left: 200px;" width="252px" height="160px"
                                 src="${sysCfgMap['upload_base_url']}/${tarinNotice.noticePicPath}">
                        </c:if>
                    </div>
                </c:if>
            </div>
            <c:if test="${'manager' eq roleFlag or 'global'eq roleFlag}">
                <textarea id="resNoticeContent_ueditor" name="resNoticeContent" type="text/plain"
                        style="width:99%;height:300px;position:relative;"> ${tarinNotice.resNoticeContent}</textarea>
            </c:if>
            <c:if test="${!('manager' eq roleFlag or 'global'eq roleFlag)}">
                <div>${tarinNotice.resNoticeContent}</div>
            </c:if>
        </form>
    </div>
    <p style="text-align: center;margin: 15px">
        <c:if test="${'manager' eq roleFlag or 'global'eq roleFlag}">
            <input type="button" onclick="save();" class="btn_green" value="保&#12288;存"/>&#12288;
            <input type="button" onclick="top.jboxClose();" class="btn_green" value="关&#12288;闭"/>
        </c:if>
    </p>
</div>
</body>
</html>