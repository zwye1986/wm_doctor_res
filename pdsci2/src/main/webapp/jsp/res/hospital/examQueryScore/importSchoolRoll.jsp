<%@include file="/jsp/common/doctype.jsp" %>
<html>
<head>
    <jsp:include page="/jsp/common/htmlhead.jsp">
        <jsp:param name="basic" value="true"/>
        <jsp:param name="font" value="true"/>
        <jsp:param name="jbox" value="true"/>
        <jsp:param name="jquery_form" value="true"/>
        <jsp:param name="jquery_cxselect" value="true"/>
        <jsp:param name="jquery_validation" value="true"/>
        <jsp:param name="jquery_datePicker" value="true"/>
        <jsp:param name="jquery_fixedtableheader" value="true"/>
        <jsp:param name="jquery_placeholder" value="true"/>
    </jsp:include>
    <style type="text/css">
        #boxHome .item:HOVER{background-color: #eee;}
    </style>
    <script type="text/javascript">
        function daoRu(){
            var scoreYear=$("#scoreYear").val();
            if(scoreYear==""||scoreYear==null)
            {
                jboxTip("请选择成绩年份！");
                return false;
            }
            if(false==$("#excelForm").validationEngine("validate")){
                return false;
            }

            var checkFileFlag = $("#checkFileFlag").val();
            if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
                jboxTip("请上传Excel文件！");
                return false;
            }
            jboxStartLoading();
            var url = "<s:url value='/res/examCfg/importSkillScoreFromExcel'/>";
            jboxSubmit(
                    $('#excelForm'),
                    url,
                    function(resp){
                        top.jboxEndLoading();
                        endloadIng();
                        top.jboxInfo(resp);
                        if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
                            window.parent.toPage(1);
                            top.jboxClose();
                        }

                    },
                    function(resp){
                        top.jboxEndLoading();
                        endloadIng();
                        top.jboxInfo('${GlobalConstant.UPLOAD_FAIL}');
                    },false);
        }
        function endloadIng()
        {
            var openDialog = dialog.get('artLoading');
            if(openDialog !=null && openDialog.open){
                openDialog.close().remove();
            }
            openDialog = dialog.get('loadingDialog');
            if(openDialog !=null && openDialog.open){
                openDialog.close().remove();
            }
            var jboxMainIframeLoading = $("#jboxMainIframeLoading");
            if(jboxMainIframeLoading!=null){
                jboxMainIframeLoading.fadeOut(500,function(){
                    $(jboxMainIframeLoading).remove();
                });
            }
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
<div class="mainright">
    <div class="content">
<input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
<form id="excelForm" method="post" enctype="multipart/form-data">
    <table class="xllist" style="width: 100%;">
        <tr>
            <th style="text-align: right;">成绩所属年份&emsp;</th>
            <td style="text-align: left;">&emsp;
                <input type="text" id="scoreYear" name="scoreYear" value="${pdfn:getCurrYear()}" onclick="WdatePicker({dateFmt:'yyyy'});"
                       class="input validate[required]" readonly="readonly" style="width: 100px;margin-left: 0px"/>
            </td>
        </tr>
        <tr>
            <th style="text-align: right;">请选择导入&emsp;</th>
            <td style="text-align: left;">&emsp;
                <input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
            </td>
        </tr>
        <tr>
            <th style="text-align: right;">模板文件&emsp;</th>
            <td style="text-align: left;">&emsp;<a href="<s:url value='/jsp/res/hospital/examQueryScore/importSchoolRoll.xlsx'/>">学生技能成绩导入模板.xlsx</a></td>
        </tr>
    </table>
</form>
<div style="text-align: center; margin-top: 10px;">
    <input type="button" onclick="daoRu();" value="导&#12288;入" class="searchInput"/>
</div>
    </div>
</div>
</body>
</html>