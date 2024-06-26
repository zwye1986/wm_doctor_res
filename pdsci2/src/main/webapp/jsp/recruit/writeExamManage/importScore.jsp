<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="/jsp/common/doctype.jsp"%>
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
    </jsp:include>

    <script type="text/javascript">

        function save(){
            if(false==$("#excelForm").validationEngine("validate")){
                return false;
            }
            var checkFileFlag = $("#checkFileFlag").val();
            if('${GlobalConstant.FLAG_Y}'!=checkFileFlag){
                jboxTip("请上传Excel文件！");
                return false;
            }
            jboxStartLoading();
            var url = "<s:url value='/recruit/writeExamManage/importExamScore'/>";
            jboxSubmit(
                $('#excelForm'),
                url,
                function(resp){
                    jboxInfo(resp);
                    if(resp.substring(0,5)=="${GlobalConstant.UPLOAD_SUCCESSED}"){
                        setTimeout(function () {
                            window.parent.frames['mainIframe'].window.location.reload(true);
                            jboxClose();
                        },1000)
                    }
                },
                function(resp){
                    jboxEndLoading();
                    jboxTip('${GlobalConstant.UPLOAD_FAIL}');
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
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix" style="overflow: auto;">
            <input type="hidden" id="checkFileFlag" name="checkFileFlag"/>
            <form id="excelForm" method="post" enctype="multipart/form-data">
                <table width="100%" class="basic" >

                    <tr>
                        <th width="30%">招录年份：</th>
                        <td style="padding-left:10px;">
                            <input id="recruitYear" value="${param.recruitYear}" onClick="WdatePicker({dateFmt:'yyyy'})" readonly="readonly" name="recruitYear" type="text" class="qtext"/>
                        </td>
                    </tr>
                    <tr>
                        <th width="30%">Excel文件：</th>
                        <td style="padding-left:10px;">
                            <input id="file" type="file" name="file" onchange="checkFile(this);" class="validate[required]"/>
                        </td>
                    </tr>
                    <tr>
                        <th>模板文件</th>
                        <td style="padding-left:10px;"><a href="<s:url value='/jsp/recruit/writeExamManage/importScore.xlsx'/>">成绩导入模板.xlsx</a></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="color:red;">注：如原来已有笔试结果，导入后会直接覆盖</td>
                    </tr>
                </table>
                <div style="text-align: center;margin-top: 15px;">
                    <input type="button" onclick="save()" class="search" value="导&#12288;入"/>
                    <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>