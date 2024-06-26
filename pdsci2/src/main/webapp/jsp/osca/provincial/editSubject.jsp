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
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+$("[name='trainingTypeId']").val()).show();
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
        var delCount = 0;
        $(function(){
            $("#file").change(upload);
            <c:if test="${empty param.subjectFlow}">
                $("#addButton").click();
            </c:if>
            <c:if test="${editFlag eq 'editFlag'}">
                $("select[name='trainingSpeId']").attr("disabled","disabled");
                $("select[name='trainingTypeId']").attr("disabled","disabled");
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
                        if(resp=='1') {top.jboxTip("删除成功!");
                            $(td).closest("tr").remove();
                        }
                        else top.jboxTip("操作失败");
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
                delCount--;
                if(delCount==0){
                    //显示原 上传
                    $("#upload").show();
                }
            });
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
            var trainingTypeName = $("[name='trainingTypeId'] option:checked").text();
            $("[name='trainingTypeName']").val(trainingTypeName);
            var trainingSpeName = $("[name='trainingSpeId'] option:checked").text();
            $("[name='trainingSpeName']").val(trainingSpeName);
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
//                    console.log(data);
                    if(data.status="1"){
//                        console.log("111");
                        var fileName=data.fileName;
                        var fileFlow=data.fileFlow;
                        var tr=$("<tr style='border-bottom: 1px solid silver'></tr>");
                        var onclick="downFile('"+fileFlow+"')";
                        var a=$('<a href="javascript:void(0);" >'+fileName+'</a>');
                        var input=$("<input type='hidden' name='fileFlow' value='"+fileFlow+"'>");
                        var td=$("<td></td>");
//                        console.log(a);
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
//                        console.log(indexObj);
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
                    delCount++;
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

        function linkageSubject(dictId){
            $('#trainingSpeId').val("");//清空上次展现数据
            $('#trainingSpeId option').hide();
            $('#trainingSpeId option[value=""]').show();
            $('#trainingSpeId'+' option.${dictTypeEnumOscaTrainingType.id}\\.'+dictId).show();
        }
    </script>
</head>
<body style="overflow: auto;">
<div class="mainright" style="overflow:visible">
    <div class="content">
        <form id="subjectForm">
        <input name="subjectFlow" type="hidden" value="${subjectMain.subjectFlow}">
        <input name="isResave" type="hidden" value="Y">
        <span>
        培训类型：
        <select id="trainingTypeId" name="trainingTypeId" class="qselect" onchange="linkageSubject(this.value)">
            <option value="">请选择</option>
            <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                <option value="${dict.dictId}" ${subjectMain.trainingTypeId eq dict.dictId?'selected':''}>${dict.dictName}</option>
            </c:forEach>
        </select>
        <input type="hidden" name="trainingTypeName">
        &#12288;
        培训专业：
        <select id="trainingSpeId" name="trainingSpeId" class="xlselect">
            <option value="">请选择</option>
            <c:forEach items="${dictTypeEnumOscaTrainingTypeList}" var="dict">
                <c:set var="dictKey" value="dictTypeEnumOscaTrainingType.${dict.dictId}List"/>
                <c:forEach items="${applicationScope[dictKey]}" var="scope">
                    <option class="${scope.dictTypeId}" value="${scope.dictId}" ${(subjectMain.trainingSpeId eq scope.dictId)?'selected':''}>${scope.dictName}</option>
                </c:forEach>
            </c:forEach>
        </select>
        <input type="hidden" name="trainingSpeName">
        &#12288;
        考核方案名称：
        <input type="text" name="subjectName" value="${subjectMain.subjectName}" class="qtext validate[required]">
        &#12288;
        <c:if test="${roleFlag eq 'hospital'}">
        考核方案类型：
        <input type="text" value="${empty subjectMain.actionTypeName?"院级方案":subjectMain.actionTypeName}" style="width: 80px" class="qtext" disabled="disabled">
        &#12288;
        </c:if>
        <%--合格占比：--%>
        <%--<input type="text" name="passPer" value="${subjectMain.passPer == null?60:subjectMain.passPer}" style="width: 30px;margin-right:0" class="xltext validate[custom[number],required]">%--%>
        </span>
        </form>
        <span style="float: right">
        <input type="button" id="addButton" class="search" value="新增考核项目" onclick="addStation();">
        </span>

        <form id="stationForm">
        <table class="xllist" style="margin-top:10px;">
            <tr>
                <th style="width: 94px;">考核部分</th>
                <th style="width: 100px;">考站</th>
                <th style="width: 183px;">考核内容</th>
                <th style="width: 60px;">分值(分)</th>
                <th style="width: 179px;">表单配置</th>
                <c:if test="${editFlag eq 'editFlag'}">
                    <th style="width: 179px;">自定义表单</th>
                    <c:if test="${roleFlag eq 'hospital'}">
                        <th style="width: 150px;">上传试卷</th>
                    </c:if>
                </c:if>
                <c:if test="${editFlag ne 'editFlag'}">
                    <c:if test="${roleFlag eq 'hospital'}">
                        <th style="width: 150px;">上传试卷</th>
                    </c:if>
                    <th style="width: 60px">操作</th>
                </c:if>
            </tr>
            <tbody id="appendTbody">
                <c:forEach items="${subjectStations}" var="item" varStatus="s">
                    <tr id="stationFrom_${s.index}-">
                        <input type="hidden" value="${item.stationFlow}" name="stationFlow">
                        <td>
                            <select class="xltext validate[required]" style="width: 85%;"name="partFlow">
                                <option/>
                                <c:forEach items="${dictTypeEnumExamPartList}" var="dict">
                                   <option value="${dict.dictId}"
                                   <c:if test="${item.partFlow eq dict.dictId}">selected</c:if>
                                   >${dict.dictName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input class="xltext validate[required]" style="width: 85%;" type="text" name="stationName" value="${item.stationName}">
                        </td>
                        <td>
                            <input class="xltext" style="width: 85%;" type="text" name="examinedContent" value="${item.examinedContent}">
                        </td>
                        <td>
                            <input class="xltext validate[custom[number]] validate[maxSize[5]] validate[required]" style="width: 85%;" type="text" name="stationScore" value="${item.stationScore}">
                        </td>
                        <td id="empty_${s.index}">
                            <input id="stationFrom_${s.index}" class="chosedFrom" name="fromFlows" placeholder="点击选择评分表"
                                   style="width: 90%;cursor: pointer" type="text" readonly="readonly" class="xltext"
                                   onclick="rightIn(this)";
                                   <c:set var="size" value="${fromMap[item.stationFlow].size()}"/>
                                   <c:if test="${size>0}">
                                   value="<c:forEach items="${fromMap[item.stationFlow]}" var="from" varStatus="s" end="1">
                                        ${from.fromName}
                                        <c:if test="${s.last&&(size>s.count)}">...</c:if>
                                        <c:if test="${!s.last}">,</c:if>
                                        </c:forEach>
                                       "
                                   title="<c:forEach items="${fromMap[item.stationFlow]}" var="from">
                                            ${from.fromName}<br/>
                                       </c:forEach>
                                      "
                                   </c:if>
                            />
                            <div class="checkboxA">
                                <div style="text-align: left;margin-left: 5px;">
                                    <input type="text" class="xltext" placeholder="关键字检索" onkeyup="searchFrom(this);">
                                </div>
                                <div style="overflow: auto; height: 78%">
                                <c:forEach items="${froms}" var="from">
                                    <div style="float: left;width: 47.5%;text-align: left;margin-left: 5px;">
                                    <label><input type="checkbox" class="choseForm" id="${item.stationFlow}${from.fromFlow}A" value="${from.fromFlow}" name="${from.fromName}" onchange="changeChoesdForm(this)"
                                            <c:set var="checkFrom"></c:set>
                                            <c:forEach items="${fromMap[item.stationFlow]}" var="from2">
                                                ${from2.fromFlow eq from.fromFlow?"checked=checked":""}
                                                <c:if test="${from2.fromFlow eq from.fromFlow}">
                                                    <c:set var="checkFrom" value="${from2}"></c:set>
                                                </c:if>
                                            </c:forEach>
                                            ${(not empty checkFrom and checkFrom.isRequired eq 'Y')?" isRequired='Y'":" isRequired='N'"}
                                    >${from.fromName}</label>
                                    </div>
                                </c:forEach>
                                </div>
                                <div style="bottom:0;margin: 5px;overflow: auto;height: 15%;border: 1px solid silver">
                                    <table style="width: 100%;border: none;">
                                        <tr>
                                            <th>已选评分表：</th>
                                            <th>评分表单名称</th>
                                            <th>设置必考考项</th>
                                        </tr>
                                        <tbody class="chosedForm">

                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </td>
                        <c:if test="${editFlag eq 'editFlag'}">
                            <td>
                                <input id="stationFrom_${s.index}_b" class="chosedFrom" name="fromFlows_b" placeholder="点击选择评分表"
                                 style="width: 90%;cursor: pointer" type="text" readonly="readonly" class="xltext"
                                       onclick="rightIn(this)";
                                        <c:set var="size2" value="${fromMap2[item.stationFlow].size()}"/>
                                        <c:if test="${size2>0}">
                                        value="<c:forEach items="${fromMap2[item.stationFlow]}" var="from" varStatus="s" end="1">
                                        ${from.fromName}
                                        <c:if test="${s.last&&(size2>s.count)}">...</c:if>
                                        <c:if test="${!s.last}">,</c:if>
                                        </c:forEach>
                                       "
                                        title="<c:forEach items="${fromMap2[item.stationFlow]}" var="from">
                                            ${from.fromName}<br/>
                                       </c:forEach>
                                      "
                                        </c:if>
                                />
                                <div class="checkboxB">
                                    <div style="text-align: left;margin-left: 5px;">
                                        <input type="text" class="xltext" placeholder="关键字检索" onkeyup="searchFrom(this);">
                                    </div>
                                    <div style="overflow: auto; height: 70%">
                                    <c:forEach items="${froms}" var="from">
                                        <div style="float: left;width: 47.5%;text-align: left;margin-left: 5px;">
                                        <label><input type="checkbox" class="choseForm" id="${item.stationFlow}${from.fromFlow}B" value="${from.fromFlow}" name="${from.fromName}" onchange="changeChoesdForm(this)"
                                            <c:set var="checkFrom" ></c:set>
                                            <c:forEach items="${fromMap2[item.stationFlow]}" var="from2">
                                                ${from2.fromFlow eq from.fromFlow?"checked=checked":""}
                                                <c:if test="${from2.fromFlow eq from.fromFlow}">
                                                    <c:set var="checkFrom" value="${from2}"></c:set>
                                                </c:if>
                                            </c:forEach>
                                            ${(not empty checkFrom and checkFrom.isRequired eq 'Y')?" isRequired='Y'":" isRequired='N'"}
                                        >${from.fromName}</label>
                                            </div>
                                    </c:forEach>
                                    </div>
                                    <div style="bottom:0;margin: 5px;overflow: auto;height: 23%;border: 1px solid silver">
                                        <table style="width: 100%;border: none;">
                                            <tr>
                                                <th>已选评分表：</th>
                                                <th>评分表单名称</th>
                                                <th>设置必考考项</th>
                                            </tr>
                                            <tbody class="chosedForm">

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </td>
                            <c:if test="${roleFlag eq 'hospital'}">
                                <td>
                                    <table class="fileTable" style="width: 100%;border: none;">
                                        <tbody class="chosedFiles">
                                            <c:forEach items="${fileMap[item.stationFlow]}" var="file">
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
                                        <tbody id="upload">
                                            <tr>
                                                <td colspan="2"><a href="javascript:void(0);" onclick="uploadFile(this)">上传</a></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </c:if>
                        </c:if>
                        <c:if test="${editFlag ne 'editFlag'}">
                            <c:if test="${roleFlag eq 'hospital'}">
                                <td>
                                    <table class="fileTable" style="width: 100%;border: none;">
                                        <tbody class="chosedFiles">
                                        <c:forEach items="${fileMap[item.stationFlow]}" var="file">
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

                                        <tbody>
                                            <tr>
                                                <td colspan="2"><a href="javascript:void(0);" onclick="uploadFile(this)">上传</a></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </td>
                            </c:if>
                            <td>
                                <a class="delStation" style="cursor: pointer" onclick="removeStation(this,'${item.stationFlow}');">删除</a>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </form>

        <%--克隆项目--%>
        <table id="addStationTable" style="display: none" class="basic" style="width: 100%;">
            <tr>
                <td>
                    <select class="xltext validate[required]" style="width: 85%;" name="partFlow">
                        <option/>
                        <c:forEach items="${dictTypeEnumExamPartList}" var="dict">
                            <option value="${dict.dictId}">${dict.dictName}</option>
                        </c:forEach>
                    </select>
                </td>
                <td>
                    <input class="xltext validate[required]" style="width: 85%;" type="text" name="stationName">
                </td>
                <td>
                    <input class="xltext" style="width: 85%;" type="text" name="examinedContent">
                </td>
                <td>
                    <input class="xltext  validate[custom[number]] validate[maxSize[5]] validate[required]" style="width: 85%;" type="text" name="stationScore">
                </td>
                <td>
                     <input id="froms" name="fromFlows" class="chosedFrom" placeholder="点击选择评分表" style="width: 90%;
                     cursor: pointer" type="text" readonly="readonly" class="xltext" onclick="rightIn(this)";/>
                        <div class="checkboxA">
                            <div style="text-align: left;margin-left: 5px;">
                                <input type="text" class="xltext" placeholder="关键字检索" onkeyup="searchFrom(this);">
                            </div>
                            <div style="overflow: auto; height: 78%">
                                <c:forEach items="${froms}" var="from">
                                <div style="float: left;width: 45.5%;text-align: left;margin-left: 5px">
                                <label><input type="checkbox"  class="choseForm"  value="${from.fromFlow}" name="${from.fromName}"  onchange="changeChoesdForm(this)">${from.fromName}</label>
                                </div>
                                </c:forEach>
                            </div>
                            <div style="bottom:0;margin: 5px;overflow: auto;height: 15%;border: 1px solid silver">
                                <table style="width: 100%;border: none;">
                                    <tr>
                                        <th>已选评分表：</th>
                                        <th>评分表单名称</th>
                                        <th>设置必考考项</th>
                                    </tr>
                                    <tbody class="chosedForm">

                                    </tbody>
                                </table>
                            </div>
                        </div>
                </td>
                <c:if test="${roleFlag eq 'hospital'}">
                    <td>
                        <table class="fileTable" style="width: 100%;border: none;">
                            <tbody class="chosedFiles">
                            </tbody>
                            <tbody>
                                <tr>
                                    <td colspan="2"><a href="javascript:void(0);" onclick="uploadFile(this)">上传</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </td>
                </c:if>
                <td>
                    <a class="delStation" style="cursor: pointer" onclick="removeStation(this,'');">删除</a>
                </td>
            </tr>
        </table>
        <input id="file" style="display: none;" type="file" accept="application/pdf" name="checkFile" />
        <div style="text-align: center;margin-top: 15px">
            <input type="button" value="保&#12288;存" class="search" onclick="save();">&#12288;&#12288;&#12288;&#12288;
            <input type="button" value="取&#12288;消" class="search" onclick="jboxClose();">
            <c:if test="${editFlag eq 'editFlag'}">
            <input type="button" id="001" value="保存自定义表单" class="search" onclick="save_b();">&#12288;&#12288;
            <input type="button" id="002" value="&#12288;取&#12288;&#12288;&#12288;消&#12288;" class="search" onclick="jboxClose();">&#12288;&#12288;&#12288;
            </c:if>
        </div>
    </div>
</div><iframe id="ifile" style="display:none"></iframe>
</body>
</html>
