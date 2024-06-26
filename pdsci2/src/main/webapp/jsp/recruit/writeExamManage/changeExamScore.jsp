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
            var form = $("#addExamInfoForm");
            if(false==form.validationEngine("validate")){
                return ;
            }
            var url = "<s:url value='/recruit/writeExamManage/saveExamScore'/>";
            jboxPost(url,$("#addExamInfoForm").serialize(),function(resp){
                if(resp == '${GlobalConstant.SAVE_SUCCESSED}'){
                    window.parent.frames['mainIframe'].window.location.reload(true);
                    jboxClose();
                }
            },null,true);
        }
    </script>
</head>
<body>
<div class="mainright">
    <div class="content">
        <div class="title1 clearfix" style="overflow: auto;">
            <form id="addExamInfoForm" name="addExamInfoForm"  method="post" style="position: relative;">
                <input type="hidden" id="recruitFlow" name="recruitFlow" value="${recruitInfoExt.recruitFlow}"/>
                <table width="100%" class="basic" >
                    <tr>
                        <th style="width: 160px">考试分数：</th>
                        <td  colspan="3">
                            <input type="text" class="validate[required,custom[number,min[0],max[100]]] xltext" id="examScore" name="examScore" value="${recruitInfoExt.examScore}"/>
                        </td>
                    </tr>
                </table>
                <div style="text-align: center;margin-top: 15px;">
                    <input type="button" onclick="save()" class="search" value="保&#12288;存"/>
                    <input type="button" class="search" onclick="jboxClose();" value="关&#12288;闭">
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>