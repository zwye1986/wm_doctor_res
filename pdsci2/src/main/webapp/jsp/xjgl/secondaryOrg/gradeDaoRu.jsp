<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_ui_tooltip" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <script type="text/javascript">
        var content='';
        String.prototype.htmlFormart = function(){
            var html = this;
            for(var index in arguments){
                var reg = new RegExp('\\{'+index+'\\}','g');
                html = html.replace(reg,arguments[index]);
            }
            return html;
        };
        function importCourse(){
            if(false==$("#excelForm").validationEngine("validate")){
                return false;
            }
            var checkFileFlag = $("#checkFileFlag").val();
            if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
                jboxTip("请上传Excel文件！");
                return false;
            }
            var length=0;
            jboxStartLoading();
            var url = "<s:url value='/xjgl/secondaryOrg/importGradeFromExcel'/>";
            jboxSubmit(
                    $('#excelForm'),
                    url,
                    function(data){
                        length=100;
                        setTimeout(function(){
                            data=eval("("+data+")");
                            $("#succCount").html("成功导入"+(data.succCount==undefined?"0":data.succCount)+"条记录");
                            if(data.loseCount!="0"){
                                length=185;
                                $("#loseCount").html(",失败"+(data.loseCount==undefined?"0":data.loseCount)+"条记录");
                            }
                            if(data.loseList!=null&&data.loseList.length>0){
                                $("#kuang").show();
                                var result="";
                                var row="";
                                var problem="";
                                for(var i=0;i<data.loseList.length;i++){
                                    row=data.loseList[i];
                                    problem=data.problemsMap[row];
                                    result+="第"+row+"行"+problem+"<br/>"+"&#12288;";
                                }
                                $("#title").html("失败原因：");
                                $("#hanghao").html("&#12288;"+result);
                            }
                            top.jboxOpenContent($("#returnData").html(),"提示",360,length,true);
                            window.parent.frames['mainIframe'].window.toPage();
                            jboxClose();
                        },1000);
                    },
                    function(data){
                        top.jboxEndLoading();
                    },false);
        }
        function checkFile(file){
            var filePath = file.value;
            var suffix = filePath.substring(filePath.lastIndexOf(".")+1);
            if("xlsx" == suffix || "xls" == suffix){
                $("#checkFileFlag").val("${GlobalConstant.FLAG_Y}");
            }else{
                $("#checkFileFlag").val("${GlobalConstant.FLAG_N}");
                $(file).val(null);
                jboxTip("请上传Excel文件");
            }
        }
    </script>
</head>
<body>
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="excelForm" method="post" enctype="multipart/form-data">
    <table class="basic" style="width: 100%;">
        <tr>
            <th width="150px">选择导入</th>
            <td>
                <input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
            </td>
        </tr>
        <tr>
            <th>模板文件</th>
            <td><a href="<s:url value='/jsp/xjgl/temeplete/scoreTemp.xls'/>">成绩导入模板.xlsx</a></td>
        </tr>
    </table>
    <div style="text-align: left;">
        注意事项：
        <label style="color: red">
            (1)、学号必须是系统中已经存在的。<br>&#12288;&#12288;&#12288;&#12288;&#12288;
            (2)、课程代码和课程名称不能为空。<br>&#12288;&#12288;&#12288;&#12288;&#12288;
            (3)、课程为学生已选课程。
        </label>
    </div>
    <div style="text-align: center; margin-top: 10px;">
        <input type="button" onclick="importCourse();" class="search" value="导&#12288;入"/>
        <input type="button" class="search" value="关&#12288;闭" onclick="jboxClose();"/>
    </div>
</form>
<!-- 用于显示导入信息的div -->
<div id="returnData"style="display: none;">
    <div style="top:10px;left:40px;position:absolute;">
		<span style="font-size:15px;"><label id="succCount"></label>
		<label id="loseCount"></label></span>
    </div>
    <div style="top:40px;left:40px;position:absolute;">
        <div>
            <label id="title"style="font-size: 15px;"></label>
        </div>
        <div id="kuang" class="kuang" style="width:300px; height:130px; overflow:auto;margin-top: 5px;display: none;">
            <label id="hanghao"style="font-size: 15px;"></label>
        </div>
    </div>
    <div style="bottom:20px;width:350px; text-align:center; position:absolute;">
        <input type="button" style="text-align: center;width: 70px;" class="search" onclick="_dialogClose('openDialog');"value='确&#12288;定'/>
    </div>
</div>
</body>
</html>