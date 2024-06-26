<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <style type="text/css">
        table .ltd{text-align:right;font-weight:bold;color:#707070;}
    </style>
    <script type="text/javascript">

        function uploadImage(name,flag) {
            if($("#"+name).val() == ""){
                jboxTip("请选择文件");
                return;
            }
            $.ajaxFileUpload({
                url: "<s:url value='/gyxjgl/tutor/imageUpload?tutorFlow=${tutor.doctorFlow}&identifyFlag='/>"+flag,
                secureuri: false,
                fileElementId: name,
                dataType: 'json',
                success: function (data) {
                    if (data.indexOf("success") == -1) {
                        jboxTip(data);
                    } else {
                        jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
                        var url = "${sysCfgMap['upload_base_url']}" + data.split(":")[1];
                        $("img[name='"+name+"']").attr("src", url);//展示图片路径
                        $("."+name).val(data.split(":")[1]);//路径保存参数
                        $(".search",$("."+name).closest("td")).val("重新上传");
                    }
                },
                error:function(){
                    jboxTip('${GlobalConstant.UPLOAD_FAIL}');
                }
            });
        }
        function saveTutor(obj){
            var form = $(obj).parents("form");
            if(!form.validationEngine("validate")){
                return;
            }
            var jcrText = $("select[name='jcrPartitionId'] option:selected").text();
            $("input[name='jcrPartitionName']").val(jcrText);
            var topText = $("select[name='topicLevelId'] option:selected").text();
            $("input[name='topicLevelName']").val(topText);
            var docText = $("select[name='doctorTypeId'] option:selected").text();
            $("input[name='doctorTypeName']").val(docText);
            var fwhText = $("select[name='fwhOrgFlow'] option:selected").text();
            $("input[name='fwhOrgName']").val(fwhText);
            var pydText = $("select[name='pydwOrgFlow'] option:selected").text();
            $("input[name='pydwOrgName']").val(pydText);
            var recText = $("select[name='recruitSpeId'] option:selected").text();
            $("input[name='recruitSpeName']").val(recText);
            jboxPost("<s:url value='/gyxjgl/tutor/saveTutor'/>", form.serialize(), function (obj) {
                if (obj == "${GlobalConstant.SAVE_SUCCESSED}") {
                    window.parent.frames['mainIframe'].location.reload();
                    jboxClose();
                }
            });
        }
        function showFlieBtn(obj,flag){
            $(obj).hide();
            $("#"+flag).show();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form>
            <input type="text" name="recordFlow" value="${paper.recordFlow}" hidden="hidden">
            <input type="hidden" name="tabFlag" value="paper">
            <div style="line-height:20px;font-size:16px;color:grey;padding:5px 0px 5px 0px;">&#12288;&#12288;请填写近三年发表的最具代表性科研论文相关信息，本人必须为第一作者或通讯作者，最多三篇，导师资格审核工作将以此信息为准。每篇论文需同时提供论文相关的各类资料证明照片，包括：【1、期刊首页或pubmed搜索结果截图；2、论文首页照片】</div>
            <table class="basic" style="width:100%; margin-top:3px; margin-bottom:30px;">
                <tr>
                    <td class="ltd" style="min-width:160px;width:1%;">论文标题：</td>
                    <td style="width:99%;"><input type="text" class="validate[required]" name="paperTitle" style="width:400px;" value="${paper.paperTitle}"></td>
                </tr>
                <tr>
                    <td class="ltd">发表时间：</td>
                    <td><input type="text" class="validate[required]" name="publishTime" value="${paper.publishTime}"><span style="color:red;">&#12288;格式：2014-07</span></td>
                </tr>
                <tr>
                    <td class="ltd">期刊名称：</td>
                    <td><input type="text" class="validate[required]" name="periodicalName" value="${paper.periodicalName}"></td>
                </tr>
                <tr>
                    <td class="ltd">影响因子：</td>
                    <td><input type="text" class="validate[required]" name="influenceFactor" value="${paper.influenceFactor}"></td>
                </tr>
                <tr>
                    <td class="ltd">JCR分区：</td>
                    <td><select name="jcrPartitionId" class="validate[required]" style="width:156px;">
                        <option value="">请选择</option>
                        <option value="1" ${paper.jcrPartitionId eq '1'?'selected':''}>一区</option>
                        <option value="2" ${paper.jcrPartitionId eq '2'?'selected':''}>二区</option>
                        <option value="3" ${paper.jcrPartitionId eq '3'?'selected':''}>三区</option>
                        <option value="4" ${paper.jcrPartitionId eq '4'?'selected':''}>四区</option>
                    </select><input type="hidden" name="jcrPartitionName" value="${paper.jcrPartitionName}"></td>
                </tr>
                <tr>
                    <td style="text-align:right;padding:3px 0px 3px 20px;"><span style="line-height:16px;font-weight:bold;color:#707070;">期刊首页照片或pubmed&nbsp;<br/>搜索结果截图：</span></td>
                    <td>
                        <input type="button" onclick="showFlieBtn(this,'periodicalPicUrlDiv')" class="search" value="重新上传">
                        <div id="periodicalPicUrlDiv" hidden="hidden">
                            <input type="file" id="periodicalPicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                            <input type="button" class="search" onclick="uploadImage('periodicalPicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                            <input type="hidden" class="periodicalPicUrl" name="periodicalPicUrl" value="${paper.periodicalPicUrl}">
                        </div>
                    </td>
                </tr>
                <tr>
                    <td class="ltd">论文正文图片：</td>
                    <td>
                        <input type="button" onclick="showFlieBtn(this,'paperPicUrlDiv')" class="search" value="重新上传">
                        <div id="paperPicUrlDiv" hidden="hidden">
                            <input type="file" id="paperPicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                            <input type="button" class="search" onclick="uploadImage('paperPicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                            <input type="hidden" class="paperPicUrl" name="paperPicUrl" value="${paper.paperPicUrl}">
                        </div>
                    </td>
                </tr>
            </table>
            <div style="text-align:center;"><input type="button" onclick="saveTutor(this)" class="search" value="保存论文信息"></div>
        </form>
    </div>
</div>
</body>
</html>
