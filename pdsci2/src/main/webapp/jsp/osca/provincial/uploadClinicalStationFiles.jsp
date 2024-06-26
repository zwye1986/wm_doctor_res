<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
        <jsp:param name="jquery_fullcalendar" value="true" />
        <jsp:param name="jquery_fngantt" value="false" />
        <jsp:param name="jquery_fixedtableheader" value="true" />
        <jsp:param name="jquery_placeholder" value="true" />
        <jsp:param name="jquery_iealert" value="false" />
        <jsp:param name="ueditor" value="true"/>
        <jsp:param name="slideRight" value="true"/>
    </jsp:include>
    <script type="text/javascript" src="<s:url value='/js/ajaxfileupload.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>
    <%--<script type="text/javascript" src="<s:url value='/js/itemSelect/itemSelect.js'/>?v=${applicationScope.sysCfgMap['sys_version']}"></script>--%>
    <style>
        a{color:#4195c5}
        .checkboxA,.checkboxB{
            display: none;
            background-color:ghostwhite;
        }
        .fileTable td{
            border: 0px;
        }
    </style>
    <script>
        function delFile(obj){
            jboxConfirm("确认删除？",function(){
                $(obj).closest("tr").remove();
            });
        }
        $(function() {
            $("#file").change(upload);
        });
        var indexObj=null;
        function uploadFile(obj)
        {
            indexObj=obj;
            $("#file").click();
        }
        function save() {
            var fileFlows = "";
            $("input[name='fileFlow']").each(function(j){
                fileFlows+="&fileFlows="+$(this).val();
            });
            var url = '<s:url value="/osca/provincial/saveClinicalFiles"/>?clinicalFlow=${param.clinicalFlow}&stationFlow=${param.stationFlow}'+fileFlows;
            jboxPost(url,null,function(resp){
                if(resp=='1')
                {
                    top.jboxTip("保存成功");
                    jboxClose();
                }
                if(resp=='0')
                    top.jboxTip("操作失败");
            },null,false);
        }
        function upload(obj){
            if(indexObj==null)
            {
                jboxTip("请点击对应的[上传]按钮，进行试卷上传！");
                return false;
            }
            obj=this;
            var id = obj.id;
            var filePath = obj.value;
            var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
            if("pdf" != suffix){
                $(obj).val(null);
                jboxTip("请上传pdf文件");
                return false;
            }
            $.ajaxFileUpload({
                url:"<s:url value='/osca/provincial/uploadFile'/>?isClinicalFile=Y",
                secureuri:false,
                fileElementId:id,
                dataType: 'json',
                success: function (data){
                    data=eval("("+data+")");
                    if(data.status="1"){
                        var fileName=data.fileName;
                        var fileFlow=data.fileFlow;
                        var tr=$("<tr style='border-bottom: 1px solid silver'></tr>");
                        var onclick="downFile('"+fileFlow+"')";
                        var a=$('<a href="javascript:void(0);" >'+fileName+'</a>');
                        var input=$("<input type='hidden' name='fileFlow' value='"+fileFlow+"'>");
                        var td=$("<td></td>");
                        $(a).attr("onclick",onclick);
                        $(a).appendTo($(td));
                        $(input).appendTo($(td));
                        var td2=$("<td style='width: 40px;'><a href='javascript:void(0);' onclick='delFile(this)'>删除</a></td>");
                        $(tr).append($(td));
                        $(tr).append($(td2));
                        $(".chosedFiles").append($(tr));

                    }else{
                        if(data.error)
                        {
                            jboxTip(data.error);
                        }else{
                            jboxTip("上传失败！");
                        }
                    }
                    $("#file").change(upload);
                },
                error: function (data, status, e){
                    jboxTip('${GlobalConstant.UPLOAD_FAIL}');
                },
                complete:function(){
                }
            });
        }

        function downFile(fileFlow)
        {
            var url='<s:url value="/osca/provincial/downFile?fileFlow="/>'+fileFlow;
            document.getElementById("ifile").src=url;
        }
    </script>
</head>
<body>
<div class="mainright" style="overflow:visible;">
    <div class="content"style="overflow: auto;max-height:460px;min-height:460px; ">

        <table class="xllist" style="margin-top:10px;">
            <tr>
                <th width="70%">试卷名称</th>
                <th width="30%">操作&#12288;&#12288;<input type="button" value="上&#12288;传" class="search" onclick="uploadFile(this)"></th>
            </tr>
            <tbody class="chosedFiles">
            <c:forEach items="${files}" var="file">
                <tr style="border-bottom: 1px solid silver">
                    <td><a href="javascript:void(0);" onclick="downFile('${file.fileFlow}')">${file.fileName}</a>
                        <input type="hidden" name="fileFlow" value="${file.fileFlow}">
                    </td>
                    <td style="width: 40px;">
                        <a href="javascript:void(0);" onclick="delFile(this)">删除</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

    </div>
    <input id="file" style="display: none;" type="file"   accept="application/pdf" name="checkFile" />
    <div style="text-align: center;margin-top: 15px">
        <input type="button" value="保&#12288;存" class="search" onclick="save();">&#12288;&#12288;&#12288;&#12288;
        <input type="button" value="取&#12288;消" class="search" onclick="jboxClose();">
    </div>
</div><iframe id="ifile" style="display:none"></iframe>
</body>
</html>
