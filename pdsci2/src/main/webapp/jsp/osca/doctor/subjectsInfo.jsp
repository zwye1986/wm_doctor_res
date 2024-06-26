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

        $(function(){
            <c:if test="${editFlag eq 'editFlag'}">
                $(".checkboxA").parent().find(".chosedFrom").removeAttr("onclick");
            </c:if>
        });
        //右侧页面滑动效果
        function rightIn(obj){
            $(obj).next().slideInit({

                width:400,
                speed:500,
                outClose:true
            });
            $(obj).next().rightSlideOpen();
            var chosedForm ="";
            $(obj).next().find("input[type='checkbox'][class='choseForm']:checked").each(function(){
                var required=$(this).attr("isRequired");
                var id=$(this).attr("id");
                var checkbox= '<input type="checkbox" value="'+id+'" onchange="isRequired(this)">';
                if(required=="Y")
                {
                    checkbox= '<input type="checkbox" value="'+id+'" checked="checked" onchange="isRequired(this)">';
                }
                chosedForm +="<tr><td></td><td>"+ $(this).attr("name")+"</td><td>"+checkbox+"</td></tr>";
            })
            $(obj).next().find(".chosedForm").html(chosedForm);
        }
        function isRequired(obj)
        {
            var id=$(obj).val();
            if($(obj).attr("checked")=="checked"||$(obj).attr("checked")==true)
            {
                $("#"+id).attr("isRequired","Y");
            }else{
                $("#"+id).attr("isRequired","N");
            }
        }
        function changeChoesdForm(obj){
            var chosedForm ="";
            var chosedForm2 ="";
            var chosedForm3 ="";
            $(obj).parent().parent().parent().parent().find("input[type='checkbox'][class='choseForm']:checked").each(function(){

                var required=$(this).attr("isRequired");
                var id=$(this).attr("id");
                var checkbox= '<input type="checkbox" value="'+id+'" onchange="isRequired(this)">';
                if(required=="Y")
                {
                    checkbox= '<input type="checkbox" value="'+id+'" checked="checked" onchange="isRequired(this)">';
                }
                chosedForm3 +="<tr><td></td><td>"+ $(this).attr("name")+"</td><td>"+checkbox+"</td></tr>";
                chosedForm += $(this).attr("name")+""+"<br/>";
                chosedForm2 += $(this).attr("name")+",";
            });
            chosedForm2 = chosedForm2.substr(0,chosedForm2.length-1);
            $(obj).parent().parent().parent().parent().find(".chosedForm").html(chosedForm3);
            $(obj).closest("td").find(".chosedFrom").val(chosedForm2).attr("title",chosedForm);
        }
        function searchFrom(obj){
            var fromName = $(obj).val();
            if(fromName==""){
                $(obj).parent().parent().find("label").show();
            }else{
                $(obj).parent().parent().find("label").each(function(){
                    var name = $(this).children().eq(0).attr("name");
                    var array = fromName.split();
                    for(var i=0;i<array.length;i++){
                        if(name.indexOf(array[i])>-1){
                            $(this).show();
                        }else{
                            $(this).hide();
                        }
                    }
                })
            }
        }

        var cnum = {1:'一',2:'二',3:'三',4:'四',5:'五',6:'六',7:'七',8:'八',9:'九',10:'十'
            ,11:'十一',12:'十二',13:'十三',14:'十四',15:'十五',16:'十六',17:'十七',18:'十八',19:'十九',20:'二十'}
        $(function(){
            $("#file").change(upload);
            <c:if test="${empty param.subjectFlow}">
                $("#addButton").click();
            </c:if>
            <c:if test="${editFlag eq 'editFlag'}">
                $("select[name='trainingSpeId']").attr("disabled","disabled");
                $("select[name='partFlow']").attr("disabled","disabled");
                $("input[name='subjectName']").attr("disabled","disabled");
                $("input[name='stationName']").attr("disabled","disabled")
                $("input[name='passPer']").attr("disabled","disabled");
                $("input[name='examinedContent']").attr("disabled","disabled");
                $("input[name='stationScore']").attr("disabled","disabled");
                $("input[type='select']").attr("disabled","disabled");
                $("input[type='button']").css("display","none");
                $("#001,#002").css("display","");
                $("a[class='delStation']").css("display","none");
            </c:if>
        });
        var stationCount = ${size};
        var uniqueId = ${size};
        function addStation(){
            var tr=$("#addStationTable tr").eq(0).clone();
            $(tr).children().eq(4).children().eq(0).attr("id","stationFrom_"+uniqueId);
            $(tr).attr("id","stationFrom_"+uniqueId+"-");

            $(tr).find("input[type='checkbox'][class='choseForm']").each(function(){
                var fromFlow=$(this).val();
                $(this).attr("id","stationFrom_"+uniqueId+fromFlow);
            });
            var content="";
            if(stationCount<=19){
                content = "第" + cnum[(stationCount + 1)] + "站";
            }
            $(tr).children().eq(1).children().eq(0).val(content);

            $("#appendTbody").append($(tr));
            stationCount++;
            uniqueId++;
        }
        function removeStation(td,flow){
            if(flow){
                jboxConfirm("确认删除？",function(){
                    jboxPost('<s:url value="/osca/provincial/deleteStation?stationFlow="/>'+flow,null,function(resp){
                        if(resp=='1') top.jboxTip("删除成功!");
                        else top.jboxTip("操作失败");
                        window.location.reload();
                    },null,false)
                });
                stationCount--;
            }else{
                $(td).closest("tr").remove();
                stationCount--;
            }
        }
        function delFile(obj){
            jboxConfirm("确认删除？",function(){
                $(obj).closest("tr").remove();
            });
            //显示原 上传
            $("#upload").show();
        }
        function save_b(){
            var trs = $("#appendTbody").children();
            var formDatas = [];
            $.each(trs,function(i,n) {
                var id=$(n).attr("id");
                id = id.substring(0,id.length-1);
                var stationFlow = $(n).find("input[name='stationFlow']").val();
                var stationName = $(n).find("input[name='stationName']").val();
                var fromFlows = [];
                $(n).find(".checkboxB input[type='checkbox'][class='choseForm']:checked").each(function(j){
                    var from ={
                        "fromFlow":$(this).val(),
                        "fromName":$(this).attr("name"),
                        "isRequired":$(this).attr("isRequired")
                    };
                    fromFlows.push(from);
                });
                var fileFlows = [];
                $(n).find("input[name='fileFlow']").each(function(j){
                    fileFlows.push($(this).val());
                });
                var data = {
                    "stationFlow": stationFlow,
                    "stationName": stationName,
                    "fromFlow": fromFlows,
                    "fileFlows": fileFlows
                };
                formDatas.push(data);
            });
            var requestData = {"formDatas":formDatas};
            var url = '<s:url value="/osca/provincial/saveHospitalForms"/>';
            jboxPost(url,{jsondata:JSON.stringify(requestData)},function(resp){
                if(resp=='1') top.jboxTip("保存成功");
                if(resp=='0') top.jboxTip("操作失败");
                window.parent.frames['mainIframe'].window.search();
                jboxClose();
            },null,false);
        }

        function save(){
            if(!$("#subjectForm").validationEngine("validate")){
                return false;
            }
            if(!$("#stationForm").validationEngine("validate")){
                return false;
            }
            var subject = $("#subjectForm").serializeJson();
            var trs = $("#appendTbody").children();
            var stationDatas = [];
            $.each(trs,function(i,n) {
                var id=$(n).attr("id");
                var partFlow = $(n).find("select[name='partFlow']").val();
                var stationFlow = $(n).find("input[name='stationFlow']").val();
                var stationName = $(n).find("input[name='stationName']").val();
                var examinedContent = $(n).find("input[name='examinedContent']").val();
                var stationScore = $(n).find("input[name='stationScore']").val();
                var fromFlows = [];
                $(n).find("input[type='checkbox'][class='choseForm']:checked").each(function(j){
                    var from ={
                        "fromFlow":$(this).val(),
                        "fromName":$(this).attr("name"),
                        "isRequired":$(this).attr("isRequired")
                    };
                    fromFlows.push(from);
                });
                var fileFlows = [];
                $(n).find("input[name='fileFlow']").each(function(j){
                    fileFlows.push($(this).val());
                });
                var ordinal = i+1;
                var data = {
                    "partFlow":partFlow,
                    "stationFlow": stationFlow,
                    "stationName": stationName,
                    "examinedContent": examinedContent,
                    "stationScore": stationScore,
                    "ordinal": ordinal,
                    "fromFlow": fromFlows,
                    "fileFlows": fileFlows
                };
                stationDatas.push(data);
            });
                var requestData = {"stationDatas":stationDatas,"subject":subject};
                var url = '<s:url value="/osca/provincial/save/${roleFlag}"/>';
                jboxPost(url,{jsondata:JSON.stringify(requestData)},function(resp){
                    if(resp=='1') top.jboxTip("保存成功");
                    if(resp=='0') top.jboxTip("操作失败");
                    window.parent.frames['mainIframe'].window.search();
                    jboxClose();
                },null,false);
        }
        var indexObj=null;
        function uploadFile(obj)
        {
            indexObj=obj;
            $("#file").click();
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
                url:"<s:url value='/osca/provincial/uploadFile'/>?date="+new Date(),
                secureuri:false,
                fileElementId:id,
                dataType: 'json',
                success: function (data){
                    data=eval("("+data+")");
                    console.log(data);
                    if(data.status="1"){
                        console.log("111");
                        var fileName=data.fileName;
                        var fileFlow=data.fileFlow;
                        var tr=$("<tr style='border-bottom: 1px solid silver'></tr>");
                        var onclick="downFile('"+fileFlow+"')";
                        var a=$('<a href="javascript:void(0);" >'+fileName+'</a>');
                        var input=$("<input type='hidden' name='fileFlow' value='"+fileFlow+"'>");
                        var td=$("<td></td>");
                        console.log(a);
                        $(a).attr("onclick",onclick);
                        $(a).appendTo($(td));
                        $(input).appendTo($(td));

                        //隐藏原 上传
                        $("#upload").hide();
                        var td2=$("" +
                                "<td style='width: 40px;'><a href='javascript:void(0);' onclick='delFile(this)'>删除</a></td>" +
                                "<td style='width: 40px;'><a href='javascript:void(0);' onclick='uploadFile(this)'>上传</a></td>");
                        $(tr).append($(td));
                        $(tr).append($(td2));
                        console.log(indexObj);
                        $(indexObj).parent().parent().parent().parent().find(".chosedFiles").append($(tr));

                    }else{
                        if(data.error)
                        {
                            jboxTip(data.error);
                        }else{
                            jboxTip("上传失败！");
                        }
                    }
                    indexObj=null;
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
<div class="mainright">
    <div class="content">
        <table class="xllist" style="margin-top: 10px;">
            <tr>
                <th style="width: 94px;">考核部分</th>
                <th style="width: 100px;">考站</th>
                <th style="width: 183px;">考核内容</th>
                <th style="width: 60px;">分值（分）</th>
            </tr>
            <tbody id="appendTbody">
                <c:forEach items="${subjectStations}" var="item" varStatus="s">
                    <tr id="stationFrom_${s.index}-">
                        <td>
                            <select class="xltext validate[required]" style="width: 85%;"name="partFlow" disabled="disabled">
                                <option/>
                                <c:forEach items="${dictTypeEnumExamPartList}" var="dict">
                                   <option value="${dict.dictId}"
                                   <c:if test="${item.partFlow eq dict.dictId}">selected</c:if>
                                   >${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input readonly="readonly" class="xltext validate[required]" style="width: 85%;" type="text" name="stationName" value="${item.stationName}">
                        </td>
                        <td>
                            <input readonly="readonly" class="xltext" style="width: 85%;" type="text" name="examinedContent" value="${item.examinedContent}">
                        </td>
                        <td>
                            <input readonly="readonly" class="xltext validate[custom[number]] validate[maxSize[5]] validate[required]" style="width: 85%;" type="text" name="stationScore" value="${item.stationScore}">
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div style="text-align: center;margin-top: 15px">
            <input type="button" value="关&#12288;闭" class="search" onclick="jboxClose();">
        </div>
    </div>
</div>
</body>
</html>
