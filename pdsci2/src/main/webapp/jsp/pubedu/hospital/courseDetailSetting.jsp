<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <title>${sysCfgMap['sys_title_name']}</title>
    <jsp:include page="/jsp/pubedu/htmlhead-dwjxres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        function save(){
            if(false == $("#editForm").validationEngine("validate")){
                return false;
            }
            jboxStartLoading();
            jboxPost("<s:url value='/pubedu/hospital/saveDetail'/>",$("#editForm").serialize(),function(resp){
                jboxEndLoading();
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}') {
                    jboxTip("保存成功");
                    window.parent.location.reload();
                    setTimeout(function(){
                        jboxClose();
                    },2000)
                }else{
                    jboxTip(resp);
                }
            },null,false);
        }

        /**
         * 上传图片
         */
        function uploadFile(type, typeName) {

//            var w = (window.screen.height)*0.7;
//            var h = (window.screen.width)*0.2;
            var url = "<s:url value='/pubedu/hospital/uploadFile'/>?operType=" + type;
            var iframe ="<iframe name='jbox-message-iframe' id='jbox-message-iframe' width='100%' height='"+150+"' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' src='"+url+"'></iframe>";
            jboxMessager(iframe,typeName,300,150,null,true);

        }

        /**
         * 删除图片
         */
        function delFile(type,courseFlow,courseName) {
            jboxConfirm("确认删除？", function () {

                var url = "<s:url value='/pubedu/hospital/delFile?courseFlow='/>"+courseFlow+"&courseName="+encodeURI(encodeURI(courseName))+"&type="+type;
                jboxPost(url, null, function(resp){
                    if (resp == "${GlobalConstant.DELETE_SUCCESSED}") {
                        location.reload(); //重新加载当前文档
                    }
                }, null, false);
//                $("#" + type + "Del").hide();
//                $("#" + type + "Span").hide();
//                $("#" + type).text("上传");
//                $("#" + type + "Value").val("");
//                $("#" + type).addClass("validate[required]");
            });
        }

    </script>
</head>
<body>
<div class="div_table">
    <form id="editForm">
        <table class="grid" style="margin-top: 20px;">
            <tr>
                <input type="hidden" name="courseFlow" value="${studyCourse.courseFlow}" />
                <th style="text-align: right;">课程名称：</th>
                <td style="text-align: left;">
                    <input type="text" style="width:160px;" class="input validate[required]" name="courseName"
                        value="${studyCourse.courseName}" />
                </td>
            </tr>
            <tr>
                <th style="text-align: right;width:15%;">课程封面：</th>
                <td style="text-align: left;">
                    <span id="courseImgUrlSpan" style="display:${!empty studyCourse.courseImgUrl?'':'none'} ">
                        [<a href="${sysCfgMap['upload_base_url']}/${studyCourse.courseImgUrl}" target="_blank">查看图片</a>]&#12288;
                    </span>
                    &nbsp;<a id="courseImgUrl" href="javascript:uploadFile('courseImgUrl','课程封面');" class="btn ${empty studyCourse.courseImgUrl?'':''}">${empty studyCourse.courseImgUrl?'':'重新'}上传</a>&#12288;&nbsp;
                    <%--<a id="courseImgUrlDel" href="javascript:delFile('courseImgUrl','${studyCourse.courseFlow}','${studyCourse.courseName}');" class="btn" style="${(empty studyCourse.courseImgUrl)?'display:none':''}">删除</a>&#12288;--%>
                    <span style="color: grey;font-size: 10%/*float: right*/">图片要求小于${sysCfgMap['inx_image_limit_size']}M</span>
                    <input type="text" style="visibility: hidden" id="courseImgUrlValue" name="courseImgUrl" value="${studyCourse.courseImgUrl}" class="validate[required]">
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">详情封面：</th>
                <td style="text-align: left;vertical-align: middle;">
                    <span id="detailImgUrlSpan" style="display:${!empty studyCourse.detailImgUrl?'':'none'} ">
                        [<a href="${sysCfgMap['upload_base_url']}/${pdfn:encodeString2(studyCourse.detailImgUrl)}" target="_blank">查看图片</a>]&#12288;
                    </span>
                    &nbsp;<a id="detailImgUrl" href="javascript:uploadFile('detailImgUrl','详情页封面');" class="btn ${empty studyCourse.detailImgUrl?'':''}">${empty studyCourse.detailImgUrl?'':'重新'}上传</a>&#12288;&nbsp;
                    <%--<a id="detailImgUrlDel" href="javascript:delFile('detailImgUrl','${studyCourse.courseFlow}','${studyCourse.courseName}');" class="btn" style="${empty studyCourse.detailImgUrl?'display:none':''}">删除</a>&#12288;--%>
                    <span style="color: grey;font-size: 10%;">图片要求小于${sysCfgMap['inx_image_limit_size']}M</span>
                    <input type="text" style="visibility: hidden" id="detailImgUrlValue" name="detailImgUrl" value="${studyCourse.detailImgUrl}" class="validate[required]">
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">授课教师：</th>
                <td style="text-align: left;">
                    <input type="text" class="input validate[required]" name="teacherName" value="${studyCourse.teacherName}" />
                </td>
            </tr>
            <tr>
                <th style="text-align: right;">排序码：</th>
                <td  style="text-align: left;">
                    <input type="text" class="input validate[required,custom[number]]" name="ordinal" value="${studyCourse.ordinal}" />
                </td>
            </tr>
        </table>
    </form>
</div>
<div align="center" style="margin-top: 20px; margin-bottom:20px;">
    <input class="btn_green" style="width:60px;" onclick="save();" value="保&#12288;存" readonly="readonly" />
    <input class="btn_green" style="width:60px;" onclick="jboxClose();" value="关&#12288;闭" readonly="readonly" />
</div>
</body>
</html>