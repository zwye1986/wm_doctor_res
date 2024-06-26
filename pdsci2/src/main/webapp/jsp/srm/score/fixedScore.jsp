<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    </jsp:include>
    <script type="text/javascript">
        function fixedScore(isPublish){
            if (false == $("#scoreForm").validationEngine("validate")) {
                return false;
            }
            var massage = "";
            if(isPublish && isPublish=='Y'){
                massage ="确认公示当前年度已归档积分？";
            }else{
                massage ="确认归档当前年度积分（归档之后，积分将不再会发生变化）？";
            }
            var isPublish = $("#isPublish").val();
            var year = $("#year").val();
            jboxConfirm(massage, function () {

                var url = "<s:url value='/srm/ach/score/fixedScore?year='/>" + year+ "&isPublish="+isPublish;
                jboxPost(url, null, function (resp) {
                    jboxTip(resp);
                    window.parent.frames['mainIframe'].location.reload(true);
                });
            });
        }

    </script>
    <style type="text/css">
        a:link {
            color: #0000ff;
        }

        a:hover {
            color: #FF6615;
        }
    </style>
</head>
<body>
<div class="mainright">
    <br/>
    <form id="scoreForm" action="" method="post"/>
        <input type="hidden" id="isPublish" name="isPublish" value="${isPublish}">
    <table>
        <tr>
            <td width="30%">年度</td>
            <td width="70%"><input class="xltext ctime validate[required]" type="text" name="year" id="year" onClick="WdatePicker({dateFmt:'yyyy'})"
                       readonly="readonly"/></td>
        </tr>
        <tr>
            <td colspan="2">
                &#12288;
            </td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input type="button" class="search" onclick="fixedScore('${isPublish}');" value="确&#12288;认"></td>
        </tr>
    </table>


    </form>
    </div>
</body>
</html>
