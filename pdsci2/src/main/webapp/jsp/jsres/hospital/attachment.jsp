
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
    <jsp:param name="UCSelect" value="true"/>
</jsp:include>

<script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
<script type="text/javascript">
    var countDiv=${pubFiles.size()};

    function doClose() {
        var openDialog = top.dialog.get('openDialog');
        if(openDialog !=null && openDialog.open){
            openDialog.close().remove();
        }
    }

    function add(){
        var a = $("#s").clone();
        a.show().attr("id","").appendTo("#f");
        var file=$(":file",a);
        file.attr("id",file.attr("id")+(++countDiv)).change(function(){
            checkFile(this);
        });
        $("a",a).click(function(){
            file.click();
        });
    }

    $(document).ready(function(){
        if($(".imageOper").length<=1){
            add();
        }
    });

    function jboxPost(posturl,postdata,funcOk,funcErr,showResp){
        $.ajax({
            type : "post",
            url : posturl,
            data : postdata,
            cache : false,
            beforeSend : function(){
                jboxStartLoading();
            },
            success : function(resp) {
                if(showResp==false){

                }else{
                    jboxTip(resp);
                }
                if(funcOk!=null){
                    funcOk(resp);
                }
            },
            error : function() {
                jboxEndLoading();
                jboxTip("操作失败,请刷新页面后重试");
                if(funcErr!=null){
                    funcErr();
                }
            },
            complete : function(){
                jboxEndLoading();
            }
        });
    }

    function jboxStartLoading(){
        dialog({
            id:'loadingDialog2',
            width: 40,
            height: 40
        }).showModal();
    }

    function jboxEndLoading(){
        dialogClose('loadingDialog2');
    }

    function dialogClose(dialogId){
        var myDialog = dialog.get(dialogId);
        if(myDialog!=null&&myDialog.open){
            myDialog.close().remove();
        }
    }

    function del(obj){
        debugger;
        var operDiv=$(obj).closest(".imageOper");
        var id=operDiv.attr("id");
        if($(".imageOper").length==2&&id==null){

        }else{
            jboxConfirm("确认删除？", function() {
                if(id){
                    var fileFlow=$(operDiv).attr("id");
                    var url="<s:url value='/jsres/manage/attachmentDelete'/>?recFlow=${recFlow}"+"&fileFlow="+fileFlow;
                    jboxPost(url,null,function(s){
                        if(s=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
                            operDiv.remove();
                            jboxTip("删除成功!");
                            if($(".imageOper").length<=1){
                                add();
                            }
                        }else{
                            jboxTip("删除失败!");
                        }
                        setTimeout(function () {
                            jboxEndLoading();
                        }, 1500);
                    },null,false);
                }else{
                    operDiv.remove();
                    if($(".imageOper").length<=1){
                        add();
                    }
                }
            });
        }
    };
    function checkFile(obj){
        debugger;
        var filePath = obj.value;
        var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
        if("jpg" != suffix && "png" != suffix && "jpeg" != suffix){
            jboxTip("请上传jpg,png,jpeg格式的图片");
            return;
        }
        var id = obj.id;
        jboxStartLoading();
        $.ajaxFileUpload({
            url:"<s:url value='/jsres/manage/attachmentUpload'/>?recFlow=${recFlow}&recType=${recType}",
            secureuri:false,
            fileElementId:id,
            dataType: 'json',
            success: function (data){
                data=eval("("+data+")");
                if(data){
                    var status=data.status;
                    var imgOperDiv=$("#"+id).closest(".imageOper");
                    if(status=="${GlobalConstant.OPRE_SUCCESSED_FLAG}"){
                        $(".imgc",imgOperDiv).attr("src",data.url).show();
                        imgOperDiv.attr("id",data.flow);
                        $(".upload",imgOperDiv).remove();
                        jboxTip("上传成功！");
                    }else{
                        imgOperDiv.remove();
                        add();
                        jboxTip(data.error);
                    };
                };
            },
            error: function (data, status, e){
                jboxTip('${GlobalConstant.UPLOAD_FAIL}');
            },
            complete:function(){
                jboxEndLoading();
            }
        });
    };
</script>


</head>

<body>

<div class="infoAudit">
    <div style="margin-bottom: 30px;">
        <table  style="width: 100%;margin-bottom: 5px;margin-top: 0px;border: 1px solid #e3e3e3;">
            <tr>
                <th style="border-bottom: 1px solid #e3e3e3;">
                    <c:if test="${not (readonly eq 'Y')}">
                        <div  style="float: right;"><img title="新增" src="<s:url value="/css/skin/Blue/images/add3.png" />" style="margin-right: 10px;cursor: pointer;" onclick="add();"/></div>
                        <div style="float:right;margin-right: 10px;">新增附件</div>
                    </c:if>
                </th>
            </tr>
        </table>

        <div style="height: 400px;overflow: auto;">
            <table  style="width: 100%;margin-bottom: 5px;margin-top: 0px;border: 1px solid #e3e3e3;">
                <tr>
                    <td style="border-bottom: 1px solid #e3e3e3;" id="f">
                        <c:if test="${not empty pubFiles}">
                        <c:forEach items="${pubFiles}" var="pubFile" varStatus="status">
                            <div class="imageOper" style="border: 1px solid #e3e3e3; margin-left: 5px; margin-top: 5px;margin-bottom:5px;  width: 160px;height: 150px;float: left;text-align: center;" id="${pubFile.fileFlow}" title="${pubFile.fileName}">
                                <c:if test="${not (readonly eq 'Y')}">
                                  <div style="float: right;padding-top:5px;padding-right: 5px;position: relative;"><img title="删除" src="<s:url value="/css/skin/Blue/images/del1.png" />" style="margin-right: 5px;cursor: pointer;" onclick="del(this);"/></div>
                                    <a target="_blank" href="${sysCfgMap['upload_base_url']}/${pubFile.filePath}"><img class="imgc" src="${sysCfgMap['upload_base_url']}/${pubFile.filePath}" style="margin-top: -21px;" width="100%" height="100%"/> </a>
                                </c:if>
                                <c:if test="${ readonly eq 'Y'}">
                                   <a target="_blank" href="${sysCfgMap['upload_base_url']}/${pubFile.filePath}"><img class="imgc" src="${sysCfgMap['upload_base_url']}/${pubFile.filePath}" style="margin-top: 0px;" width="100%" height="100%"/> </a>
                                </c:if>
                                <input id="file${status.count}" style="display: none;" type="file" name="checkFile" class="validate[required] v"/>
                            </div>
                        </c:forEach>
                        </c:if>
                    </td>
                </tr>


                <div class="imageOper" style="display: none; border: 1px solid #e3e3e3; margin-left: 5px; margin-top: 5px;margin-bottom:5px;  width: 160px;height: 150px;float: left;text-align: center;" id="s">
                    <c:if test="${not (readonly eq 'Y')}">
                        <div style="float: right;padding-top:5px;padding-right: 5px;position: relative;"><img title="删除" src="<s:url value="/css/skin/Blue/images/del1.png" />" style="margin-right: 5px;cursor: pointer;" onclick="del(this);"/></div>
                        <img class="imgc" style="display: none;margin-top: -21px;" width="100%" height="100%"/>
                        <div style="padding-top:45%;" class="upload"><a>请选择上传</a></div>
                        <input id="file" style="display: none;" type="file" name="checkFile" class="validate[required]"/>
                    </c:if>

                    <c:if test="${readonly eq 'Y'}">
                        <div style="float: right;padding-top:5px;padding-right: 5px;position: relative;"></div>
                        <img class="imgc" style="display: none;margin-top: -21px;" width="100%" height="100%"/>
                        <div style="padding-top:45%;" class="upload">无文件</div>
                        <input id="file" style="display: none;" type="file" name="checkFile" class="validate[required]"/>
                    </c:if>
                </div>

            </table>
        </div>
    </div>
</div>

</body>
</html>
