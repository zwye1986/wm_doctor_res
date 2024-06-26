
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <style>
        #edui1,#edui1_iframeholder{
            width: 100% !important;
        }
        #edui1_iframeholder{
            height: 225px;
        }
    </style>
    <jsp:include page="/jsp/jsres/htmlhead-jsres.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="consult" value="true"/>
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
    <script type="text/javascript">
        $(document).ready(function(){
            var ue = UE.getEditor('rotationStandard_ueditor', {
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
                        'insertimage', /* 'emotion','scrawl', 'insertvideo', 'music', 'attachment'*/, /*'map', 'gmap', 'insertframe','insertcode', 'webapp', 'pagebreak',*/ /*'template', 'background'*/, '|',
                        /* 'horizontal', 'date', 'time', 'spechars', 'snapscreen',  'wordimage', '|',*/
                        /*'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|'*/,
                        /*'print'*/,  'preview', /*'searchreplace', 'help' */, /*'drafts'*//* 'wordimage' */]
                ]
                ,initialStyle:'body{font-family: SimSun; font-size:14px}'
            });//实例化编辑器

            //加载二级单选框
            loadConsultSonType()

            $("#consultQuestion").on("input propertychange", function() {
                var $this = $(this),
                    _val = $this.val(),
                    count = "";
                if(_val.length > 500) {
                    $this.val(_val.substring(0, 500));
                }
                count = 500 - $this.val().length;
                $("#text-count").text(count);
            });
        });

        function replyConsult(){
            if(false==$("#consultAnswer").validationEngine("validate")){
                return false;
            }
            jboxSubmit($("#consultAnswer"),"<s:url value='/jsres/consult/replyConsult'/>",function(resp){
                if(resp=="${GlobalConstant.SAVE_SUCCESSED}"){
                    jboxTip("回复成功!");
                    var currentPage=document.getElementById("currentPage").value;
                    window.parent.toPage(currentPage);
                    setTimeout(function(){jboxClose()},2000);
                }else {
                    jboxTip("回复失败!");
                }
            },null,false);
        }

        function loadConsultSonType() {
            //获取一级单选框id
            var consultTypeId =  $('input[name="consultTypeId"]:checked').val();
            if(consultTypeId=='' || consultTypeId==null){
                $("#consultTypeSonDiv").empty();
                return;
            }else {
                document.getElementById("zxType").style.visibility="visible ";//显示
            }
            //每次点击前，将所有二级单选框清空
            $("#consultTypeSonDiv").empty();
            var title=document.getElementById("consultTypeSonDiv");
            var consultTypeId = 'ConsultType.'+$('input[name="consultTypeId"]:checked').val();
            if (consultTypeId) {
                var url = "<s:url value='/jsres/consult/loadTitle'/>?consultTypeId=" + consultTypeId;
                var loadSonId = document.getElementById("consultTypeSonId").getAttribute('value');
                $.ajax({
                    type : "get",
                    url : url,
                    cache : false,
                    success : function (data) {
                        $("#consultTypeSonDiv").empty();
                        for (var i=0;i<data.length;i++) {
                            if (data[i].dictId == loadSonId){
                                title.innerHTML += '<input checked type="radio" name="consultTypeSonId" id='+ data[i].dictId +' value=' + data[i].dictId + '>';
                            }else {
                                title.innerHTML += '<input type="radio" name="consultTypeSonId" id='+ data[i].dictId +'  value=' + data[i].dictId + '>';
                            }
                            title.innerHTML += '<label for='+ data[i].dictId +'>'+data[i].dictName+'</label>';
                        }
                    }
                })
            }
        }
        function doClose() {
            top.jboxClose();
        }
    </script>
</head>
<body>
<div class="mainright" align="center">
    <div class="content" style="padding: 0px 35px;">
        <div align="left">
            <form id="consultAnswer" method="post" style="height: 650px;overflow-y:auto;">
                <input type="hidden" name="consultInfoFlow" value="${consultInfo.consultInfoFlow}"/>
                <input type="hidden" id="currentPage" name="currentPage" value="${currentPage}"/>
                <div class="flex consult-ques" style="min-width: 50px">
                    <label style="    min-width: 50px;">问题:</label>
                    <div class="consult-textbox" >
                        <textarea name="consultQuestion" id="consultQuestion"  maxlength="500"  class="validate[required] gp-textarea">${consultInfo.consultQuestion}</textarea>
                        <div class="fs14 gp-tip"><span id="text-count">500</span>/500</div>
                    </div>
                </div>


                <div class="flex">
                    <div style="min-width: 50px"><label>
                        答案:
                    </label></div>
                    <div align="left" style="width: 100%;">
                        <textarea id="rotationStandard_ueditor" name="consultAnswer" type="text/plain" style="width:99%;height:300px;position:relative;"></textarea>
                    </div>
                </div>

                <div class="consult-style">
                    <label>请选择所问问题类型:</label>
                    <div id="consultTypeDiv">
                        <c:forEach items="${dictTypeEnumConsultTypeList}" var="dict" varStatus="status">
                            <c:set var="dictKey" value="dictTypeEnumStandardDept.${dict.dictId}List"/>
                            <input class="consultTypeRadio" type="radio" id="${dict.dictId}" value="${dict.dictId}" name="consultTypeId" onclick="loadConsultSonType()"
                                <c:if test="${consultInfo.consultTypeId eq dict.dictId}">checked</c:if>
                            >
                            <label for="${dict.dictId}">${dict.dictName}</label>
                        </c:forEach>
                    </div>
                </div>
                <div class="consult-style">
                    <input type="hidden" id="consultTypeSonId" value="${consultInfo.consultTypeSonId}"/>
                    <div class="td_left" id="zxType" style="visibility:hidden;">请选择具体类型：</div>
                    <div id="consultTypeSonDiv"></div>
                </div>
                <div align="center" style="margin: 20px 0px 15px;">
                    <input type="checkbox" id="isPolicy" value="Y" name="isPolicy">
                    <label for="isPolicy">添加到政策专区</label>
                    <input type="button" value="回&#12288;复" class="btn_green" onclick="replyConsult();"/>
                    <input type="button" value="关&#12288;闭" class="btn_green" onclick="doClose();"/>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>