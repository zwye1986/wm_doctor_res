
<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="compatible" value="true"/>
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
        <jsp:param name="jquery_ztree" value="true"/>
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <script type="text/javascript">
        var setting = {
            view: {
                dblClickExpand: false,
                showIcon: false,
                showTitle:false,
                selectedMulti:false
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick: onClick
            }
        };

        function beforeClick(treeId, treeNode) {
            var check = (treeNode.id!=0);
            if (!check){
                jboxTip('不能选择根节点');
                return false;
            }
        }

        function onClick(e, treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
                    nodes = zTree.getSelectedNodes(),
                    v = "";
            id = "";
            nodes.sort(function compare(a,b){return a.id-b.id;});
            for (var i=0, l=nodes.length; i<l; i++) {
                v += nodes[i].name + ",";
                id += nodes[i].id + ",";
            }
            if (v.length > 0 ) v = v.substring(0, v.length-1);
            if (id.length > 0 ) id = id.substring(0, id.length-1);
            var cityObj = $("#columnSel");
            $("#selectColumnId").val(id);
            //alert(id);
            cityObj.attr("value", v);
        }

        function showMenu() {
            var cityObj = $("#columnSel");
            var cityOffset = $("#columnSel").offset();
            $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

            $("body").bind("mousedown", onBodyDown);
        }
        function hideMenu() {
            $("#menuContent").fadeOut("fast");
            $("body").unbind("mousedown", onBodyDown);
        }
        function onBodyDown(event) {
            if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
                hideMenu();
            }
        }
        function uploadImage(){
            if($.trim($("#imageFile").val())==""){
                jboxTip("请先选择图片！");
                return false;
            }
            $.ajaxFileUpload({
                url:"<s:url value='/fstu/newsInfoManage/uploadImg'/>",
                secureuri:false,
                fileElementId:'imageFile',
                dataType: 'json',
                success: function (data, status){
                    if(data.indexOf("success")==-1){
                        jboxTip(data);
                    }else{
                        jboxTip('${GlobalConstant.UPLOAD_SUCCESSED}');
                        var arr = [];
                        arr = data.split(":");
                        $("#titleImg").val(arr[1]);
                        var url = '${imageBaseUrl}'+ arr[1];
                        $("#viewNewImg").attr("href",url);
                        $("#viewNewImg").show();
                        $("#delNewImg").show();

                    }
                },
                error: function (data, status, e){
                    jboxTip('${GlobalConstant.UPLOAD_FAIL}');
                }
            });
        }
        function saveInfo(){
            //去title
            $("img").removeAttr("attr");
            if($("#editForm").validationEngine("validate")){
                var infoTitle = $.trim($("#infoTitle").val());
                var infoFlow = $("#infoFlow").val();
                var columnId = $("#selectColumnId").val();
                var titleImg = $("#titleImg").val();
                var infoTime = $("#infoTime").val();
                var infoKeyword = $("#infoKeyword").val();
                var isTop = $("#isTop").val();
                var infoContent = UE.getEditor('ueditor').getContent();
                var requestData ={
                    "infoFlow":infoFlow,
                    "infoTitle":infoTitle,
                    "columnId":columnId,
                    "titleImg":titleImg,
                    "infoTime":infoTime,
                    "infoKeyword":infoKeyword,
                    "isTop":isTop,
                    "infoContent":infoContent
                };
                var url = "<s:url value='/fstu/newsInfoManage/save'/>";
                jboxPost(url,requestData,function(resp){
                    if(resp=='${GlobalConstant.OPRE_SUCCESSED}'){
                        window.parent.frames['mainIframe'].window.search();
                        jboxClose();
                    }
                },null,true);
            }
        }
        function delImg(infoFlow){
            var requestData ={
                "infoFlow":infoFlow
            };
            var url = "<s:url value='/fstu/newsInfoManage/deleteImg'/>";
            jboxPost(url,requestData,function(resp){
                if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
                    $("#titleImg").val("");
                    $("#editImg").hide();
                    $("#editNewImg").show();
                }
            },null,true);

        }
        function delNewImg(){
            var imgUrl = $("#titleImg").val();
            var requestData ={
                "infoFlow":imgUrl
            };
            var url = "<s:url value='/fstu/newsInfoManage/deleteImg'/>";
            jboxPost(url,requestData,function(resp){
                if(resp=='${GlobalConstant.DELETE_SUCCESSED}'){
                    $("#viewNewImg").hide();
                    $("#delNewImg").hide();
                    $("#titleImg").val("");
                    $("#imageFile").val("");
                }
            },null,true);

        }
        $(document).ready(function(){
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
            });//实例化编辑器
            var url = "<s:url value='/fstu/columnManage/getAllDataJson'/>";
            jboxPostJson(url,null,function(data){
                if(data){
                    zNodes = $.parseJSON(data);
                    $.fn.zTree.init($("#treeDemo"), setting, zNodes);
                }
            },null,false);
        });
    </script>
    <style type="text/css">
        .line {border: none;}
        #ueditor{padding-top: 35px;}
    </style>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix">
            <c:if test='${not empty imageBaseUrl}'>
                <form id="editForm" method="post" style="position: relative;" enctype="multipart/form-data">
                    <table class="basic" width="100%" style="margin: 0 auto;">
                        <tr>
                            <td class="bs_name" colspan="4">资讯信息：</td>
                        </tr>
                        <tr>
                            <th class="td_blue" width="100px">标&#12288;&#12288;题：</th>
                            <td colspan="3">
                                <input class="validate[required,maxSize[100]] text-input xltext" id="infoTitle" type="text" style="width: 70%" value="${info.infoTitle }"/>
                            </td>
                        </tr>
                        <tr>
                            <th class="td_blue">所属栏目：</th>
                            <td colspan="3" id="column">
                                <input type="hidden" id="selectColumnId" value="${info.columnId }" />
                                <input class="validate[required] text-input xltext" id="columnSel" type="text" <c:if test="${info.column != null }"> value="${info.column.columnName }" </c:if> onclick="showMenu(); return false;" readonly="readonly"  />
                            </td>
                        </tr>
                        <tr>
                            <th>标题图片：</th>
                            <input type="hidden" id="titleImg" value="${info.titleImg}">
                            <c:if test="${empty info.titleImg}" >
                                <td  id="editNewImg">
                                    <input type="file"  id="imageFile" name="imageFile" >&nbsp;&nbsp;<input type ="button" value="上传" onclick="uploadImage();">&nbsp;&nbsp;<a href="" target="_blank" style="display: none;" id="viewNewImg" >[查看已上传图片]</a>&nbsp;&nbsp;<a href="javascript:void(0)" id="delNewImg" onclick="delNewImg()" style="display: none">[删除图片]</a>
                                </td>
                            </c:if>
                            <c:if test="${!empty info.titleImg}" >
                                <td  id="editNewImg" style="display: none;">
                                    <input type="file"  id="imageFile" name="imageFile" >&nbsp;&nbsp;<input type ="button" value="上传" onclick="uploadImage();">&nbsp;&nbsp;<a href="" target="_blank" style="display: none;" id="viewNewImg" >[查看已上传图片]</a>&nbsp;&nbsp;<a href="javascript:void(0)" id="delNewImg" onclick="delNewImg()" style="display: none">[删除图片]</a>
                                </td>
                                <td id="editImg"><a href="${imageBaseUrl}${info.titleImg}" target="_blank" title="点击查看大图" id="viewImg" ><img src="${imageBaseUrl}${info.titleImg}" width="150px"/></a>&nbsp;&nbsp;<a href="javascript:void(0)" id="delImg" onclick="delImg('${info.infoFlow}')" style="vertical-align: bottom;">[删除图片]</a></td></c:if>
                            <th>资讯时间：</th>
                            <td>
                                <input id="infoTime" type="text"  class="validate[required] text-input xltext ctime" <c:if test="${!empty info.infoTime}"> value="${info.infoTime}" </c:if> <c:if test="${empty info.infoTime}"> value="${pdfn:getCurrDate()}" </c:if>  style="width: 160px;" readonly="readonly" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                            </td>
                        </tr>
                        <tr>
                            <th>关键字：</th>
                            <td colspan="3">
                                <input class="validate[maxSize[50]] text-input xltext" id="infoKeyword" type="text" value="${info.infoKeyword}" />(多个用逗号","隔开)
                            </td>
                        </tr>
                        <tr>
                            <th class="td_blue">资讯内容：</th>
                            <td colspan="3">
                                <script id="ueditor" type="text/plain" style="width:750px;height:300px;">${info.infoContent}</script>
                            </td>
                        </tr>
                    </table>
                    <p align="center" style="width:100%">
                        <input class="search" type="button" value="保&#12288;存"  onclick="saveInfo();" />
                        <input class="search" type="button" value="关&#12288;闭"  onclick="jboxClose();" />
                        <input id="infoFlow" type="hidden" value="${info.infoFlow }" />
                        <input id="isTop" type="hidden" value="${GlobalConstant.RECORD_STATUS_N}" />
                    </p>
                </form>
            </c:if>
            <c:if test="${empty imageBaseUrl}">请联系系统管理员先在系统配置中配置上传图片的路径</c:if>
        </div>

    </div>
</div>
<div id="menuContent" class="menuContent" style="display:none; position:absolute;z-index: 9999;">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
</div>
</body>
</html>