<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/jsp/common/doctype.jsp" %>
<jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
    <jsp:param name="basic" value="true"/>
    <jsp:param name="font" value="true"/>
    <jsp:param name="jbox" value="true"/>
    <jsp:param name="jquery_validation" value="true"/>
    <jsp:param name="ueditor" value="true"/>
</jsp:include>
    <script>

        $(document).ready(function(){
            //实例化编辑器
            var ue = UE.getEditor('ueditor', {
                autoHeight: false,
                imagePath: '${sysCfgMap['upload_base_url']}/',
                imageManagerPath: '${sysCfgMap['upload_base_url']}/',
                filePath: '${sysCfgMap['upload_base_url']}/',
                videoPath: '${sysCfgMap['upload_base_url']}/',
                wordImagePath: '${sysCfgMap['upload_base_url']}/',
                snapscreenPath: '${sysCfgMap['upload_base_url']}/',
                catcherPath: '${sysCfgMap['upload_base_url']}/',
                scrawlPath: '${sysCfgMap['upload_base_url']}/'
            });
        });

        // 保存基地简介信息
        function save(){
            var speDesc = UE.getEditor('ueditor').getContent();
            if(!speDesc){
                jboxTip("请输入专业简介信息");
                return;
            }
            var data = {speDesc : speDesc, assignFlow : '${param.assignFlow}'};
            var url = "<s:url value='/jsres/message/saveAssignSpeDesc'/>";
            jboxPost(url,data,function(resp){
                jboxTip(resp);
                if(resp == '${GlobalConstant.OPRE_SUCCESSED}'){
                    setTimeout(function () {
                        jboxClose();
                    },800);
                }
            },null,true);
        }


    </script>
    <div class="div_table" id="divTable" style="height: 500px; overflow-y: auto;">
        <script id="ueditor" type="text/plain" style="width:650px;height:300px;">${assign.speDesc}</script>
        <div align="center" style="margin-top: 20px; margin-bottom:20px;">
            <input type="button" class="btn_green" onclick="save();" value="保&#12288;存"/>&#12288;
            <input type="button" class="btn_green" onclick="jboxClose();" value="关&#12288;闭"/>
        </div>
    </div>