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
        <input type="text" name="recordFlow" value="${topic.recordFlow}" hidden="hidden">
        <input type="hidden" name="tabFlag" value="topic">
        <div style="line-height:20px;font-size:16px;color:grey;padding:5px 0px 5px 0px;">&#12288;&#12288;请填写本人承担的科研课题相关信息（要求本人为课题负责人），导师资格审核工作将以此信息为准。每项课题需同时提供课题相关的各类资料证明照片，包括：【1、项目合同书首页；2、课题组成员签字页；3、含项目资助经费页面；4、含项目进度内容页面（项目起始时间）；5、项目主管部门盖章页】，每张图片文件大小不能超过2M（2048K）。本年度12月31日前结题的课题经费不纳入统计范围。《国家自然科学基金资助项目》只需上传资助计划书首页即可。</div>
            <table class="basic" style="width:100%; margin-top:3px; margin-bottom:30px;">
            <tr>
                <td class="ltd" style="min-width:160px;width:1%;">课题名称：</td>
                <td style="width:99%;"><input type="text" class="validate[required]" name="topicTitle" style="width:400px;" value="${topic.topicTitle}"></td>
            </tr>
            <tr>
                <td class="ltd">课题级别：</td>
                <td><select name="topicLevelId" class="validate[required]" style="width:156px;">
                    <option value="">请选择</option>
                    <option value="country" ${topic.topicLevelId eq 'country'?'selected':''}>国家级</option>
                    <option value="province" ${topic.topicLevelId eq 'province'?'selected':''}>省级</option>
                    <option value="city" ${topic.topicLevelId eq 'city'?'selected':''}>市级</option>
                </select><input type="hidden" name="topicLevelName" value="${topic.topicLevelName}" ></td>
            </tr>
            <tr>
                <td class="ltd">立项单位：</td>
                <td><input type="text" class="validate[required]" name="projectUnit" value="${topic.projectUnit}" ></td>
            </tr>
            <tr>
                <td class="ltd">课题经费：</td>
                <td><input type="text" class="validate[required,custom[number]]" name="topicMoney" value="${topic.topicMoney}"> 万元</td>
            </tr>
            <tr>
                <td class="ltd">结题时间：</td>
                <td><input type="text" class="validate[required]" name="knotTime" value="${topic.knotTime}"></td>
            </tr>
            <tr>
                <td class="ltd">项目合同书首页：</td>
                <td>
                    <input type="button" onclick="showFlieBtn(this,'contractPicUrlDiv')" class="search" value="重新上传">
                    <div id="contractPicUrlDiv" hidden="hidden">
                        <input type="file" id="contractPicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                        <input type="button" class="search" onclick="uploadImage('contractPicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                        <input type="hidden" class="contractPicUrl" name="contractPicUrl" value="${topic.contractPicUrl}">
                    </div>
                </td>
            </tr>
            <tr>
                <td class="ltd">课题成员签字页：</td>
                <td>
                    <input type="button" onclick="showFlieBtn(this,'memberPicUrlDiv')" class="search" value="重新上传">
                    <div id="memberPicUrlDiv" hidden="hidden">
                        <input type="file" id="memberPicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                        <input type="button" class="search" onclick="uploadImage('memberPicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                        <input type="hidden" class="memberPicUrl" name="memberPicUrl" value="${topic.memberPicUrl}">
                    </div>
                </td>                            </tr>
            <tr>
                <td class="ltd">项目资助经费页：</td>
                <td>
                    <input type="button" onclick="showFlieBtn(this,'fundsPicUrlDiv')" class="search" value="重新上传">
                    <div id="fundsPicUrlDiv" hidden="hidden">
                        <input type="file" id="fundsPicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                        <input type="button" class="search" onclick="uploadImage('fundsPicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                        <input type="hidden" class="fundsPicUrl" name="fundsPicUrl" value="${topic.fundsPicUrl}">
                    </div>
                </td>                            </tr>
            <tr>
                <td class="ltd">项目进度内容页：</td>
                <td>
                    <input type="button" onclick="showFlieBtn(this,'schedulePicUrlDiv')" class="search" value="重新上传">
                    <div id="schedulePicUrlDiv" hidden="hidden">
                        <input type="file" id="schedulePicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                        <input type="button" class="search" onclick="uploadImage('schedulePicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                        <input type="hidden" class="schedulePicUrl" name="schedulePicUrl" value="${topic.schedulePicUrl}">
                    </div>
                </td>                            </tr>
            <tr>
                <td class="ltd">主管部门盖章页：</td>
                <td>
                    <input type="button" onclick="showFlieBtn(this,'sealPicUrlDiv')" class="search" value="重新上传">
                    <div id="sealPicUrlDiv" hidden="hidden">
                        <input type="file" id="sealPicUrl" name="image" style="width:176px;font-size:14px;color:#707070;">
                        <input type="button" class="search" onclick="uploadImage('sealPicUrl');" value="上传图片"><span style="color:red;">&#12288;注意：图片上传后记得保存</span>
                        <input type="hidden" class="sealPicUrl" name="sealPicUrl" value="${topic.sealPicUrl}">
                    </div>
                </td>                            </tr>
            </table>
    <div style="text-align:center;"><input type="button" onclick="saveTutor(this)" class="search" value="保存课题信息"></div>
    </form>
</div>
</div>
</body>
</html>
