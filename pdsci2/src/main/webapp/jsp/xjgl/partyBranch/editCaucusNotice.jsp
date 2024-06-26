
<%@include file="/jsp/common/doctype.jsp"%>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true" />
        <jsp:param name="jbox" value="true" />
        <jsp:param name="jquery_form" value="false" />
        <jsp:param name="jquery_ui_tooltip" value="true" />
        <jsp:param name="jquery_ui_combobox" value="false" />
        <jsp:param name="jquery_ui_sortable" value="false" />
        <jsp:param name="jquery_cxselect" value="true" />
        <jsp:param name="jquery_scrollTo" value="false" />
        <jsp:param name="jquery_jcallout" value="false" />
        <jsp:param name="jquery_validation" value="true" />
        <jsp:param name="jquery_datePicker" value="true" />
        <jsp:param name="jquery_fullcalendar" value="false" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
    </jsp:include>
    <script type="text/javascript">

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
                scrawlPath: '${sysCfgMap['upload_base_url']}/',
                elementPathEnabled : false,
                autoFloatEnabled:false,
                toolbars:[
                    ['|', 'undo', 'redo', '|','lineheight',
                        'bold', 'italic', 'underline', 'fontborder', 'fontfamily', 'fontsize', '|',
                        'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                        'link', 'insertimage', 'attachment' ,  'preview', 'wordimage']
                ]
            });//实例化编辑器
            <c:if test="${roleFlag eq 'admin'}">
            init();
            </c:if>
        });

        function init(){
            $(".operDiv").on("mouseenter mouseleave",function(){
                $(this).find("span").toggle();
            });
        }

        function saveNotice(){
            if($("#noticeName").val().length==0){
                jboxTip("通知标题不能为空!");
                return;
            }
            if($("#noticeName").val().length>100){
                jboxTip("通知标题不能大于100个字符或汉字!");
                return;
            }
//		if($("#infoTargetFlow").val().length==0){
//			jboxTip("通知类型不能为空!");
//			return;
//		}
            jboxStartLoading();
            var noticeName = $.trim($("#noticeName").val());
//            var infoFlow = $("#infoFlow").val();
            var content = UE.getEditor('ueditor').getContent();
            var partyBranchId = $("select[name='partyBranchId'] option:selected").val();
            var partyBranchNameText = $("select[name='partyBranchId'] option:selected").text();
            $("input[name='partyBranchName']").val(partyBranchNameText);
            var noticeTypeId = $("select[name='noticeTypeId'] option:selected").val();
            var noticeTypeNameText = $("select[name='noticeTypeId'] option:selected").text();
            $("input[name='noticeTypeName']").val(noticeTypeNameText);
            var requestData ={
//                "infoFlow":infoFlow,
                "noticeName":noticeName,
                "content":content,
                "partyBranchId":partyBranchId,
                "partyBranchName":partyBranchNameText,
                "noticeTypeId":noticeTypeId,
                "noticeTypeName":noticeTypeNameText
            };
            var url = "<s:url value='/xjgl/partyBranch/addCaucusNotice'/>";
            jboxPost(url,requestData,function(resp){
                if(resp=='${GlobalConstant.SAVE_SUCCESSED}'){
                    window.parent.frames['mainIframe'].window.toPage(1);
                }
            },null,true);
        }

        function edit(infoFlow){
            var url = "<s:url value='/xjgl/notice/findNoticeByFlow'/>?infoFlow="+infoFlow;
            jboxGet(url , null , function(resp){
                if(resp){
                    if($("#editForm").is(":hidden")){
                        editFormHidden();
                    }
                    $("#infoFlow").val(resp.infoFlow);
                    $("#title").val(resp.infoTitle);
                    UE.getEditor('ueditor').setContent(resp.infoContent);
                    $("[value='"+resp.infoTargetFlow+"']").attr("selected","selected");
                    $("#bodyDiv").animate({scrollTop:"0px"},500);
                }
            } , null , false);
        }
        function delNotice(infoFlow){
            jboxConfirm("确认删除？" , function(){
                var url = "<s:url value='/xjgl/notice/delNotice'/>?infoFlow="+infoFlow;
                jboxGet(url , null , function(resp){
                    window.location.reload();
                } , null , true);
            });
        }
    </script>

</head>
<body>
<div id="bodyDiv" style="height: 100%;overflow: auto;">
    <div style="margin-bottom: 20px;">
        <div style="margin-top: 5px;padding-left: 15px;padding-right: 15px;">
            <form id="editForm" method="post">
                <input type="hidden" id="infoFlow" name="infoFlow" value=""/>
                <div style="height: 33px;margin-top: 5px;">
                    <table style="width: 100%;">
                        <tr>
                            <td width="7%"><span style="color: red;">*</span>标题：</td>
                            <td width="40%"><input id="noticeName" name="noticeName" class="" style="height: 22px;width:98%;"/></td>
                            <td width="10%">所属党支部：</td>
                            <td width="12%">
                                <select id="partyBranchId" name="partyBranchId" style="height: 26px;width: 98%;" class="">
                                    <option value="">全部</option>
                                    <c:forEach items="${dictTypeEnumXjPartyBranchList}" var="partyBranch">
                                        <option value="${partyBranch.dictId}" ${param.partyBranchId eq partyBranch.dictId?'selected':''}>${partyBranch.dictName}</option>
                                    </c:forEach>
                                </select><input type="hidden" name="partyBranchName" value="">
                            </td>
                            <td width="9%"><span style="color: red;">*</span>通知类型：</td>
                            <td width="10%">
                                <select id="noticeTypeId" name="noticeTypeId" style="height: 26px;width: 98%;" class="">
                                    <option value="1" <c:if test="${param.noticeTypeId eq '1'}">selected="selected"</c:if>>普通通知</option>
                                    <option value="2" <c:if test="${param.noticeTypeId eq '2'}">selected="selected"</c:if>>投票通知</option>
                                </select><input type="hidden" name="noticeTypeName" value="">
                            </td>
                            <td width="5%"><input type="button" id="saveBtn" value="发&#12288;布" onclick="saveNotice();" class="search"/></td>
                        </tr>
                    </table>
                </div>
                <script id="ueditor" name="content" type="text/plain" style="height:250px;"></script>
            </form>
        </div>
    </div>
</div>
</body>
</html>
