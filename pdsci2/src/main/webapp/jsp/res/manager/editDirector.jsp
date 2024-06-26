<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_ui_combobox" value="false"/>
        <jsp:param name="jquery_ui_sortable" value="false"/>
        <jsp:param name="jquery_cxselect" value="false"/>
        <jsp:param name="jquery_scrollTo" value="false"/>
        <jsp:param name="jquery_jcallout" value="false"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fullcalendar" value="false"/>
        <jsp:param name="jquery_fngantt" value="false"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
        <jsp:param name="jquery_iealert" value="false"/>
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <style type="text/css">
    </style>
    <script type="text/javascript">
        <c:if test="${'manager'eq roleFlag}">
        $(document).ready(function(){
            var ue = UE.getEditor('resNoticeContent_ueditor', {
                elementPathEnabled:false,
                autoHeight: false,
                imagePath: '${sysCfgMap['upload_base_url']}/',
                imageManagerPath: '${sysCfgMap['upload_base_url']}/',
                filePath: '${sysCfgMap['upload_base_url']}/',
                videoPath: '${sysCfgMap['upload_base_url']}/',
                wordImagePath: '${sysCfgMap['upload_base_url']}/',
                snapscreenPath: '${sysCfgMap['upload_base_url']}/',
                catcherPath: '${sysCfgMap['upload_base_url']}/',
                scrawlPath: '${sysCfgMap['upload_base_url']}/',
                toolbars:[
                    [/* 'fullscreen' */, /*'source',*/ /* '|' */, 'undo', 'redo', '|','lineheight',
                        'bold', 'italic', 'underline', 'fontborder', /* 'strikethrough', */ /*'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',*/
                        /* 'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                         'customstyle', 'paragraph', */ 'fontfamily', 'fontsize', '|',
                        /* 'directionalityltr', 'directionalityrtl', 'indent', '|',*/
                        'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                        /* 'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|', */
                        'insertimage',  'emotion','scrawl', 'insertvideo', 'music', 'attachment', /*'map', 'gmap', 'insertframe','insertcode', 'webapp', 'pagebreak',*/ /*'template', 'background'*/, '|',
                        /* 'horizontal', 'date', 'time', 'spechars', 'snapscreen',  'wordimage', '|',*/
                        /*'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|'*/,
                        /*'print'*/,  'preview', /*'searchreplace', 'help' */, /*'drafts'*//* 'wordimage' */]
                ]
                ,initialStyle:'body{font-family: SimSun; font-size:14px}'
            });//实例化编辑器
        });
        </c:if>
        function save(){
            var resNoticeTitle = $("#resNoticeTitle").val();
            if(jQuery.trim(resNoticeTitle) == ""){
                jboxTip("请输入通知标题！");
                return;
            }
            if(!UE.getEditor('resNoticeContent_ueditor').hasContents()){
                top.jboxTip("请输入指南内容!");
                return;
            }
            jboxConfirm("确认保存？" , function(){
                var url = "<s:url value='/res/liveTraining/saveDirector'/>";
                var data = $('#resNoticeContent').serialize();
                jboxPost(url,data,function(resp){
                    if(resp=='1'){
                        top.jboxTip("操作成功");
                        top.document.mainIframe.location.reload();
                        jboxClose();
                    }else{
                        jboxTip("操作失败");
                    }
                },null,false);
            });
        }
        function doClose(){
            jboxClose();
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <form id="resNoticeContent" method="post">
            <input type="hidden" name="recordFlow" value="${tarinNotice.recordFlow}"/>
            <div class="title1 clearfix" align="left">
                <c:if test="${'manager' eq roleFlag}">
                    <label>指南标题：</label><input type="text" id="resNoticeTitle" name="resNoticeTitle" value="${tarinNotice.resNoticeTitle}" style="width: 20%"/>
                </c:if>
                <c:if test="${!('manager' eq roleFlag)}">
                    <div>指南标题：${tarinNotice.resNoticeTitle}</div>
                </c:if>
            </div>
            <c:if test="${'manager' eq roleFlag}">
                <script id="resNoticeContent_ueditor" name="resNoticeContent" type="text/plain" style="width:99%;height:300px;position:relative;">${tarinNotice.resNoticeContent}</script>
            </c:if>
            <c:if test="${!('manager' eq roleFlag)}">
                <div>${tarinNotice.resNoticeContent}</div>
            </c:if>
        </form>
        <p style="text-align: center;margin: 5px">
            <c:if test="${'manager' eq roleFlag}">
                <input type="button" onclick="save();" class="search" value="保&#12288;存"/>
                <input type="button" onclick="doClose();" class="search" value="关&#12288;闭"/>
            </c:if>
        </p>
    </div>
</div>
</body>
</html>
